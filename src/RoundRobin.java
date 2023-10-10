import java.util.ArrayList;

public class RoundRobin extends BasicScheduler implements Scheduler {
	int quantum = 0;
	int microtick = 0;
	
	public RoundRobin(ArrayList<Process> table, int quantum) {
		super(table);
		this.quantum = quantum;
	}
	
	public void start() {
		ArrayList<Process> arrived = new ArrayList<Process>();
		int jobs = table.size();
		
		for(tick = 0; jobs > 0; tick++) {

			this.checkForArrivals(arrived);
			
			if(!arrived.isEmpty()) {
				
				if(microtick >= quantum) {
					this.cycle(arrived);
					microtick = 0;
				}
				microtick++;
				
				Process proc = arrived.get(0);
				
				// for each element:
				// if they have arrived but is not running,
				// add to their waiting time.
				for(int i = 0; i < arrived.size(); i++) {
					Process curr = arrived.get(i);
					if(!curr.equals(proc)) {
						curr.setWaiting(curr.waiting + 1);
					}
				}
				
				if(!proc.hasStarted()) {
					proc.setStart(tick);
					proc.setStarted(true);
				}
				
				proc.tick();
				this.addGantt(proc);
				
				// if the burst is finished
				if(proc.getxBurst()<= 0) {
					// update the process
					this.updateProcess(proc);
					jobs--;
					arrived.remove(proc);
					
					// reset microticks
					microtick = 0;
				}
				
			} else {
				this.gantt += "-";
			}
			
		}
		
		gantt += "|(" + (tick) + ")";
		this.calculateAverage();
		this.reset();
	}
	
	private void cycle(ArrayList<Process> arrived) {
		Process first = arrived.get(0);
		
		for(int i = 0; i < arrived.size()-1; i++) {
			arrived.set(i, arrived.get(i+1));
		}
		arrived.set(arrived.size()-1, first);
	}
}
