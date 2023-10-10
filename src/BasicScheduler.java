import java.util.ArrayList;

abstract class BasicScheduler implements Scheduler {
	protected ArrayList<Process> table = new ArrayList<Process>();
	protected int tick = 0;
	protected double aveResponse = 0;
	protected double aveWaiting = 0;
	protected double aveTurnaround = 0;	
	protected String gantt = "";
	protected int lastProcess = -1;
	
	public BasicScheduler(ArrayList<Process> table) {
		for(int i = 0; i < table.size(); i++) {
			table.get(i).setID(i+1);
		}
		this.table = table;
	}
	
	public abstract void start();
	
	protected void setLastProcess(int lastProcess) {
		this.lastProcess = lastProcess;
	}
	
	protected int getLastProcess() {
		return this.lastProcess;
	}
	
	protected void checkForArrivals(ArrayList<Process> arrived) {
		// checks for new arrivals
		for(int i = 0; i < table.size(); i++) {
			Process curr = table.get(i);
			if(!curr.isFinished() && curr.getArrival() <= tick && !arrived.contains(curr)) {
				arrived.add(curr);
			}
		}
	}
	
	protected void printArrived(ArrayList<Process> arrived) {
		System.out.print("[");
		for(int i = 0; i< arrived.size(); i++) {
			System.out.print("p" + arrived.get(i).getID() + ":" + arrived.get(i).getxBurst() + ",");
		}
		System.out.println("]");
	}
	
	protected void calculateAverage() {
		for(int i = 0; i < table.size(); i++) {
			Process curr = table.get(i);
			aveResponse += curr.getResponse();
			aveWaiting += curr.getWaiting();
			aveTurnaround += curr.getTurnaround();
		}
		aveResponse /= table.size();
		aveTurnaround /= table.size();
		aveWaiting /= table.size();
	}
	
	protected void updateProcess(Process proc) {
		// update the process
		proc.setFinished(true);
		proc.setCompletion(tick + 1);
		proc.setResponse(proc.getStart() - proc.getArrival());
		proc.setTurnaround(proc.getCompletion() - proc.getArrival());
	}
	
	protected void addGantt(Process proc) {
		if(this.getLastProcess() != proc.getID()) {
			gantt += "|" + (tick) + "(p" + proc.getID() + ")";
		}
		gantt += "X";
		this.setLastProcess(proc.getID());
	}
	
	protected void reset() {
		tick = 0;
		for(int i = 0; i < table.size(); i++) {
			Process curr = table.get(i);
			curr.setxBurst(curr.getBurst());
			curr.setFinished(false);
			curr.setStarted(false);
		}
	}
	
	public void printTable() {
		System.out.println("P     Arrival    Burst   Completion   Response   Waiting   Turnaround");
		for(int i = 0; i < table.size(); i++) {
			table.get(i).printAsRow();
		}
		System.out.printf("%35s", "");
		System.out.printf("%11.2f", aveResponse);
		System.out.printf("%10.2f", aveWaiting);
		System.out.printf("%13.2f", aveTurnaround);
	}
	
	public void printGantt() {
		System.out.println(gantt);
	}
	
	public void printBoth() {
		this.printTable();
		System.out.println();
		System.out.println();
		this.printGantt();
	}
}
