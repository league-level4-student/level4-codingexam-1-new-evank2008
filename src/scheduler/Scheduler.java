package scheduler;
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

    public static void main(String[] args) throws SchedulingConflictException {
    	new Scheduler().run();

    }
void run() throws SchedulingConflictException{
	insert(week[0].list,new Event("wake up", 935));
	insert(week[0].list,new Event("get out of bed", 1200));
	insert(week[0].list,new Event("pee", 1100));
	insert(week[0].list,new Event("poo", 1100));
	insert(week[0].list,new Event("have breakfast", 1130));
	dump(week[0]);
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
				return;
			}
		//if time is greater than previous but less than next
		if(current.getValue().getTime()>prev.getValue().getTime()&&current.getValue().getTime()<next.getValue().getTime()) {
			prev.setNext(current);
			current.setPrev(prev);
			next.setPrev(current);
			current.setNext(next);
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
public void dump(Days day) {
	Node<Event> now = day.list.getHead();
	System.out.println(day.name());

	while(now!=null) {
		if(now.getValue().getTime()<1000) {
			System.out.print(" ");
		}
		System.out.println(now.getValue().getATime()+": "+now.getValue().getFunction());
		now=now.getNext();
	}
}
}

