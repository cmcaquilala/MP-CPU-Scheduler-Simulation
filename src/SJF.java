import java.util.ArrayList;

public class SJF extends BasicScheduler implements Scheduler {
	
	public SJF(ArrayList<Process> table) {
		super(table);
	}
	
	public void start() {
		ArrayList<Process> arrived = new ArrayList<Process>();
		int jobs = table.size();
		
		for(tick = 0; jobs > 0; tick++) {
			
			this.checkForArrivals(arrived);
			
			if(!arrived.isEmpty()) {
				Process proc = this.getShortestJob(arrived);
				
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
					proc.setWaiting(proc.getStart() - proc.getArrival());
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
			if(arrived.get(i).hasStarted()) {
				return arrived.get(i);
			}
		}
		
		for(int i = 0; i < arrived.size(); i++) {
			Process arrival = arrived.get(i);
			if(shortest.getxBurst() > arrival.getxBurst()) {
				shortest = arrival;
			}
		}
		return shortest;
	}
}
