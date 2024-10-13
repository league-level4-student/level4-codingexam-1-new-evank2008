package scheduler;


public enum Days{
	Sun(new LinkedList<Event>()), Mon(new LinkedList<Event>()), Tue(new LinkedList<Event>()), Wed(new LinkedList<Event>()), Thu(new LinkedList<Event>()), Fri(new LinkedList<Event>()), Sat(new LinkedList<Event>());
	LinkedList<Event> list;
	private Days(LinkedList<Event> list) {
		this.list=list;
	}
}

