import java.util.ArrayList;

public class SRTF extends BasicScheduler implements Scheduler {
	
	public SRTF(ArrayList<Process> table) {
		super(table);
	}
	
	public void start() {
		ArrayList<Process> arrived = new ArrayList<Process>();
		int jobs = table.size();
		
		for(tick = 0; jobs > 0; tick++) {

			this.checkForArrivals(arrived);
			
			if(!arrived.isEmpty()) {
				Process proc = this.getShortestJob(arrived);
				
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
				}
				
			} else {
				this.gantt += "-";
			}
			
		}
		
		gantt += "|(" + (tick) + ")";
		this.calculateAverage();
		this.reset();
	}
	
	private Process getShortestJob(ArrayList<Process> arrived) {
		Process shortest = arrived.get(0);
		
		for(int i = 0; i < arrived.size(); i++) {
			Process arrival = arrived.get(i);
			if(shortest.getxBurst() > arrival.getxBurst()) {
				shortest = arrival;
			}
		}
		return shortest;
	}
}
