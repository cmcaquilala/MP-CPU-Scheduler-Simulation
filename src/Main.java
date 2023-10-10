import java.util.ArrayList;
import java.util.Scanner;

public class Main {
	static int userMenu = -1;
	
	public static void main(String[] args) {
		Scanner mm = new Scanner(System.in);
		while(userMenu != 0) {
			
			try {
				ArrayList<Process> table = new ArrayList<Process>();
				
				System.out.println("Welcome! This is Caquilala's Task Scheduler.");
				System.out.println("What type of task are you thinking of?");
				System.out.println("1 - First Come First Served");
				System.out.println("2 - Shortest Job First");
				System.out.println("3 - Shortest Time Remaining First");
				System.out.println("4 - Priority Scheduler");
				System.out.println("5 - Round Robin");
				System.out.println();
				System.out.println("0 - Exit");
				System.out.println();
				
				System.out.print("> ");
				
				userMenu = Integer.parseInt(mm.nextLine());
				
				System.out.println();
				System.out.println();
				if(userMenu == 1) {
					System.out.println("You chose 1 - First Come First Served.");
					System.out.println();
					System.out.println();
					System.out.println("Start by inputting processes by arrival time and burst.");
					System.out.println("Follow the format [*arrival*,*burst*]");
					System.out.println("Leave blank to end.");
					System.out.println();
					
					String usr = "x";
					while(!usr.isBlank()) {
						System.out.print("> ");
						usr = mm.nextLine();
						
						if(usr.isBlank()) {
							break;
						}
						
						table.add(toProcess(usr));
						
					}
					
					FCFS sched = new FCFS(table);
					sched.start();
					
					System.out.println();
					System.out.println("Here are the figures!");
					System.out.println();
					System.out.println("======================");
					System.out.println("First Come First Served");
					System.out.println("======================");
					sched.printBoth();
					System.out.println();
					System.out.println();
					
				} else if(userMenu == 2) {
					System.out.println("You chose 2 - Shortest Job First.");
					System.out.println();
					System.out.println();
					System.out.println("Start by inputting processes by arrival time and burst.");
					System.out.println("Follow the format [*arrival*,*burst*]");
					System.out.println("Leave blank to end.");
					System.out.println();
					
					String usr = "x";
					while(!usr.isBlank()) {
						System.out.print("> ");
						usr = mm.nextLine();
						
						if(usr.isBlank()) {
							break;
						}
						
						table.add(toProcess(usr));
						
					}
					
					SJF sched = new SJF(table);
					sched.start();
					
					System.out.println();
					System.out.println("Here are the figures!");
					System.out.println();
					System.out.println("======================");
					System.out.println("Shortest Job First");
					System.out.println("======================");
					sched.printBoth();
					System.out.println();
					System.out.println();
				} else if(userMenu == 3) {
					System.out.println("You chose 3 - Shortest Time Remaining First.");
					System.out.println();
					System.out.println();
					System.out.println("Start by inputting processes by arrival time and burst.");
					System.out.println("Follow the format [*arrival*,*burst*]");
					System.out.println("Leave blank to end.");
					System.out.println();

					String usr = "x";
					while(!usr.isBlank()) {
						System.out.print("> ");
						usr = mm.nextLine();
						
						if(usr.isBlank()) {
							break;
						}
						
						table.add(toProcess(usr));
						
					}
					
					SRTF sched = new SRTF(table);
					sched.start();
					
					System.out.println();
					System.out.println("Here are the figures!");
					System.out.println();
					System.out.println("======================");
					System.out.println("Shortest Time Remaining First");
					System.out.println("======================");
					sched.printBoth();
					System.out.println();
					System.out.println();
				} else if(userMenu == 4) {
					System.out.println("You chose 4 - Priority Scheduler.");
					System.out.println();
					System.out.println();
					System.out.println("Start by inputting processes by arrival time, burst, and priority.");
					System.out.println("Follow the format [*arrival*,*burst*,*priority*]");
					System.out.println("Leave blank to end.");
					System.out.println();
					
					String usr = "x";
					while(!usr.isBlank()) {
						System.out.print("> ");
						usr = mm.nextLine();
						
						if(usr.isBlank()) {
							break;
						}
						
						table.add(toProcessPrio(usr));
						
					}

					Priority sched = new Priority(table);
					sched.start();
					
					System.out.println();
					System.out.println("Here are the figures!");
					System.out.println();
					System.out.println("======================");
					System.out.println("Priority Scheduler");
					System.out.println("======================");
					sched.printBoth();
					System.out.println();
					System.out.println();
				} else if(userMenu == 5) {
					System.out.println("You chose 5 - Round Robin.");
					System.out.println();
					System.out.println();
					System.out.println("Start by inputting the time quantum.");
					System.out.print("> ");
					int tq = Integer.parseInt(mm.nextLine());
					System.out.println();
					System.out.println("Then, input the processes by arrival time and burst.");
					System.out.println("Follow the format [*arrival*,*burst*]");
					System.out.println("Leave blank to end.");
					System.out.println();
					
					String usr = "x";
					while(!usr.isBlank()) {
						System.out.print("> ");
						usr = mm.nextLine();
						
						if(usr.isBlank()) {
							break;
						}
						
						table.add(toProcess(usr));
						
					}
					
					
					RoundRobin sched = new RoundRobin(table, tq);
					sched.start();
					
					System.out.println();
					System.out.println("Here are the figures!");
					System.out.println();
					System.out.println("======================");
					System.out.println("Round Robin");
					System.out.println("======================");
					sched.printBoth();
					System.out.println();
					System.out.println();
				} else if(userMenu == 0) {
					break;
				} else {
					System.out.println("I'm afraid I didn't get that.");
					System.out.println();
					System.out.println();
				}
				
				System.out.println();
				System.out.println("Continue? (1/0)");
				System.out.print("> ");
				
				userMenu = Integer.parseInt(mm.nextLine());
				
				System.out.println();
				System.out.println();
				System.out.println();
			} catch (Exception e) {
				System.out.println();
				System.out.println("Please check your inputs.");
				System.out.println();
				System.out.println();
				System.out.println();
			}
			
		}
		mm.close();
		System.out.println("Thank you!");
	}
	
	private static Process toProcess(String x) {
		String[] arr = x.split(",");
		
		int arrival = Integer.parseInt(arr[0]);
		int burst = Integer.parseInt(arr[1]);
		
		return new Process(arrival,burst);
	}
	
	private static Process toProcessPrio(String x) {
		String[] arr = x.split(",");
		
		int arrival = Integer.parseInt(arr[0]);
		int burst = Integer.parseInt(arr[1]);
		int priority = Integer.parseInt(arr[2]);
		
		return new Process(arrival,burst,priority);
	}
}
