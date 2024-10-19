package scheduler;

import java.util.Scanner;

//TODO: add better UI for displaying events
//let user interact thru console w/scanner
//add event, remove event, next/prev day
/*
 * Objective: Create a weekly scheduling application.
 * 
 * You may create any additional enums, classes, methods or variables needed
 * to accomplish the requirements below:
 * 
 * - You should use an array filled with enums for the days of the week and each
 *   enum should contain a LinkedList of events that includes a time and what is 
 *   happening at the event.
 * 
 * - The user should be able to to interact with your application through the
 *   console and have the option to add events, view events or remove events by
 *   day.
 *   
 * - Each day's events should be sorted by chronological order.
 *  
 * - If the user tries to add an event on the same day and time as another event
 *   throw a SchedulingConflictException(created by you) that tells the user
 *   they tried to double book a time slot.
 *   
 * - Make sure any enums or classes you create have properly encapsulated member
 *   variables.
 */
public class Scheduler {
    Days[] week = Days.values();
    Commands com = new Commands(week);
    Scanner scan = new Scanner(System.in);
    public static void main(String[] args) throws SchedulingConflictException {
    	new Scheduler().run();

    }
void run() throws SchedulingConflictException{
	/*
	com.insert(week[0].list,new Event("wake up", 935));
	com.insert(week[0].list,new Event("get out of bed", 1200));
	com.insert(week[0].list,new Event("poo", 1100));
	com.insert(week[0].list,new Event("have breakfast", 1130));
	*/
	dump();
}
public void dump() throws SchedulingConflictException {
	Days day = week[com.currentDay];
	Node<Event> now = day.list.getHead();
	System.out.println(day.name());

	while(now!=null) {
		if(now.getValue().getTime()<1000) {
			System.out.print(" ");
		}
		System.out.println(now.getValue().getATime()+": "+now.getValue().getFunction());
		now=now.getNext();
	}
	System.out.println("ADD-REMOVE-PREVIOUS-NEXT");
	com.execute(scan.nextLine().toLowerCase());
	dump();
}
}

