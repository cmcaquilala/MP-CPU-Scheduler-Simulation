import java.util.ArrayList;

public class PremadeExample {
	
	public static void main(String[] args) {
		ArrayList<Process> a = new ArrayList<Process>();
		ArrayList<Process> b = new ArrayList<Process>();
		ArrayList<Process> c = new ArrayList<Process>();
		ArrayList<Process> d = new ArrayList<Process>();
		ArrayList<Process> e = new ArrayList<Process>();

		// FIFO
		a.add(new Process(12,5));
		a.add(new Process(15,9));
		a.add(new Process(16,4));
		a.add(new Process(0,13));

		// SJF
		b.add(new Process(12,5));
		b.add(new Process(15,4));
		b.add(new Process(16,9));
		b.add(new Process(0,13));
		
//		b.add(new Process(0,6));
//		b.add(new Process(0,8));
//		b.add(new Process(0,7));
//		b.add(new Process(0,3));

//		b.add(new Process(0,6));
//		b.add(new Process(1,8));
//		b.add(new Process(2,7));
//		b.add(new Process(3,3));
		
		// SRTF
		c.add(new Process(3,2));
		c.add(new Process(5,6));
		c.add(new Process(0,8));
		c.add(new Process(5,4));
		c.add(new Process(0,6));
		c.add(new Process(3,8));
		
//		c.add(new Process(0,8));
//		c.add(new Process(1,4));
//		c.add(new Process(2,9));
//		c.add(new Process(3,5));
		
		// Priority
		d.add(new Process(0,10,3));
		d.add(new Process(3,1,1));
		d.add(new Process(0,2,4));
		d.add(new Process(0,1,5));
		d.add(new Process(2,5,2));
		
		// Round Robin
		e.add(new Process(0,9));
		e.add(new Process(5,5));
		e.add(new Process(9,8));
		int quantum = 3;
		
//		e.add(new Process(0,24));
//		e.add(new Process(0,3));
//		e.add(new Process(0,3));
//		int quantum = 4;
		
		
		System.out.println("======================");
		System.out.println("First In First Out");
		System.out.println("======================");
		FCFS fifo = new FCFS(a);
		fifo.start();
		fifo.printBoth();
		
		System.out.println();
		System.out.println();
		
		System.out.println("======================");
		System.out.println("Shortest Job First");
		System.out.println("======================");
		SJF sjf = new SJF(b);
		sjf.start();
		sjf.printBoth();
		
		System.out.println();
		System.out.println();
		
		System.out.println("======================");
		System.out.println("Shortest Remaining Time First");
		System.out.println("======================");
		SRTF srtf = new SRTF(c);
		srtf.start();
		srtf.printBoth();
		
		System.out.println();
		System.out.println();
		
		System.out.println("======================");
		System.out.println("Priority");
		System.out.println("======================");
		Priority prio = new Priority(d);
		prio.start();
		prio.printBoth();
		
		System.out.println();
		System.out.println();
		
		System.out.println("======================");
		System.out.println("Round Robin");
		System.out.println("======================");
		RoundRobin rr = new RoundRobin(e, quantum);
		rr.start();
		rr.printBoth();
		
		System.out.println();

	}
	
}
