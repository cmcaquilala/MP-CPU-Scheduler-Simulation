
public class Process {
	int id = 0;
	int priority = -1;
	int arrival = 0;
	int burst = 0;
	int xburst = 0;
	int start = 0;
	int completion = 0;
	int response = 0;
	int waiting = 0;
	int turnaround = 0;
	private boolean isFinished = false;
	private boolean hasStarted = false;
	
	public Process(int arrival, int burst) {
		this.arrival = arrival;
		this.burst = burst;
		this.xburst = burst;
	}
	
	public Process(int arrival, int burst, int priority) {
		this.arrival = arrival;
		this.burst = burst;
		this.xburst = burst;
		this.priority = priority;
	}
	
	public boolean isFinished() {
		return isFinished;
	}
	
	public void setFinished(boolean bool) {
		isFinished = bool;
	}
	
	public boolean hasStarted() {
		return hasStarted;
	}
	
	public void setStarted(boolean bool) {
		hasStarted = bool;
	}
	
	public int getID() {
		return id;
	}

	public void setID(int id) {
		this.id = id;
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	public int getArrival() {
		return arrival;
	}

	public void setArrival(int arrival) {
		this.arrival = arrival;
	}
	
	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getBurst() {
		return burst;
	}

	public void setBurst(int burst) {
		this.burst = burst;
	}
	
	public int getxBurst() {
		return xburst;
	}
	
	public void setxBurst(int xburst) {
		this.xburst = xburst;
	}

	public void tick() {
		this.xburst = this.xburst - 1;
	}

	public int getCompletion() {
		return completion;
	}

	public void setCompletion(int completion) {
		this.completion = completion;
	}

	public int getResponse() {
		return response;
	}

	public void setResponse(int response) {
		this.response = response;
	}

	public int getWaiting() {
		return waiting;
	}

	public void setWaiting(int waiting) {
		this.waiting = waiting;
	}

	public int getTurnaround() {
		return turnaround;
	}

	public void setTurnaround(int turnaround) {
		this.turnaround = turnaround;
	}
	
	public void printAsRow() {
		System.out.printf("%2d",id);
		if(priority != -1) {
			System.out.printf("%11d", priority);			
		}
		System.out.printf("%11d", arrival);
		System.out.printf("%9d", burst);
		System.out.printf("%13d", completion);
		System.out.printf("%11d", response);
		System.out.printf("%10d", waiting);
		System.out.printf("%13d", turnaround);
		System.out.println();
	}
}
