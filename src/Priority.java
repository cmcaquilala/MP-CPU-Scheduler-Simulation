import java.util.ArrayList;

public class Priority extends BasicScheduler implements Scheduler {
	
	public Priority(ArrayList<Process> table) {
		super(table);
	}
	
	public void start() {
		ArrayList<Process> arrived = new ArrayList<Process>();
		int jobs = table.size();
		
		for(tick = 0; jobs > 0; tick++) {

			this.checkForArrivals(arrived);
			
			if(!arrived.isEmpty()) {
				Process proc = this.getMostPriority(arrived);
				
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
	
	private Process getMostPriority(ArrayList<Process> arrived) {
		Process prio = arrived.get(0);
		
		for(int i = 0; i < arrived.size(); i++) {
			Process arrival = arrived.get(i);
			if(prio.getPriority() > arrival.getPriority()) {
				prio = arrival;
			}
		}
		return prio;
	}
	
	@Override
	public void printTable() {
		System.out.println("P    Priority    Arrival    Burst   Completion   Response   Waiting   Turnaround");
		for(int i = 0; i < table.size(); i++) {
			table.get(i).printAsRow();
		}
		System.out.printf("%46s", "");
		System.out.printf("%11.2f", aveResponse);
		System.out.printf("%10.2f", aveWaiting);
		System.out.printf("%13.2f", aveTurnaround);
	}
}
