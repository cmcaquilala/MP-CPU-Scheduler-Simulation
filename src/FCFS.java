import java.util.ArrayList;

public class FCFS extends BasicScheduler implements Scheduler {
	
	public FCFS(ArrayList<Process> table) {
		super(table);
	}
	
	public void start() {
		ArrayList<Process> arrived = new ArrayList<Process>();
		int jobs = table.size();
		
		for(tick = 0; jobs > 0; tick++) {

			this.checkForArrivals(arrived);
			
			if(!arrived.isEmpty()) {
				Process proc = arrived.get(0);
				
				if(!proc.hasStarted()) {
					proc.setStart(tick);
					proc.setStarted(true);
				}
				
				proc.tick();
				this.addGantt(proc);
				
				if(proc.getxBurst() <= 0) {
					// update the process
					this.updateProcess(proc);
					proc.setWaiting(proc.getStart() - proc.getArrival());
					jobs--;
					arrived.remove(0);					
				}	
				
			} else {
				this.gantt += "-";
			}
		
		}
		
		gantt += "|(" + (tick) + ")";
		this.calculateAverage();
		this.reset();
	}
}
