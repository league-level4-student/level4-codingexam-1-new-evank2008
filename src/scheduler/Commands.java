package scheduler;

import java.util.Scanner;

public class Commands {
	String deets;
Days[] week;
Scanner scan = new Scanner(System.in);
int currentDay = 0;
public Commands(Days[] week) {
	this.week=week;
}
public void insert(LinkedList<Event> list, Event event) throws SchedulingConflictException{
	Node<Event> current = new Node<Event>(event);
	if(list.getHead()==null) {
		list.add(event);
	}else if(current.getValue().getTime()<list.getHead().getValue().getTime()) {
		current.setNext(list.getHead());
		list.getHead().setPrev(current);
		list.setHead(current);
	} //if you reach here, the current event is later than the head's.
	else if(list.getHead().getNext()==null) {
		list.add(event);
	}//if you reach here, the list contains at least 2 events and the first is earlier than current.
	else {
		Node<Event> prev = list.getHead();
		Node<Event> next = list.getHead().getNext();

		while(true) {//if next hits null, you just add
			if(next==null) {
				list.add(event);
				System.out.println("Event scheduled.");
				return;
			}
		//if time is greater than previous but less than next
		if(current.getValue().getTime()>prev.getValue().getTime()&&current.getValue().getTime()<next.getValue().getTime()) {
			prev.setNext(current);
			current.setPrev(prev);
			next.setPrev(current);
			current.setNext(next);
			System.out.println("Event scheduled.");
			return;
		}
		//if you encounter a scheduling error
		if(current.getValue().getTime()==prev.getValue().getTime()||current.getValue().getTime()==next.getValue().getTime()) {
			new SchedulingConflictException("Cannot schedule 2 events at the same time. Failed to schedule "+current.getValue().getFunction()+" at "+current.getValue().getATime());
			return;
		}
		
		prev=next;
		next=next.getNext();
		}
	}
}
void add() throws SchedulingConflictException {
	System.out.println("What event?");
	deets = scan.nextLine();
	if(deets==null) {
		deets = scan.nextLine();
	}
	System.out.println("What time? Use military format.");
	int time = scan.nextInt();
	//this nextLine is to clear out the invisible line break bug
	scan.nextLine();
	insert(week[currentDay].list,new Event(deets, time));
	
}
void remove() {
	System.out.println("What time?");
	int time = scan.nextInt();
	scan.nextLine();
	boolean stop = false;
	boolean success=false;
	int pos = 0;
	Node<Event> checkee=week[currentDay].list.getHead();
	while(!stop) {
		if(checkee.getValue().getTime()==time) {
			week[currentDay].list.remove(pos);
			stop = true;
			success=true;
		} else {
			pos++;
			checkee=checkee.getNext();
			if(checkee==null) {
				stop=true;
			}
		}
	}
	if(success) {
		System.out.println("Event removed.");
	} else {
		System.out.println("No event at gven time.");
	}
}
void execute(String command) throws SchedulingConflictException {
	switch(command) {
	case "add":
		add();
		break;
	case "remove":
		remove();
		break;
	case "previous":
	case "prev":
		currentDay--;
		if(currentDay<0) {
			System.out.println("Moving to final day");
			currentDay=6;
		}
		break;
	case "next":
		currentDay++;
		if(currentDay>6) {
			System.out.println("Moving to first day");
			currentDay=0;
		}
	}
}
}