package scheduler;


public enum Days{
	Sun(null), Mon(null), Tue(null), Wed(null), Thu(null), Fri(null), Sat(null);
	LinkedList<Event> list;
	private Days(LinkedList<Event> list) {
		this.list=list;
	}
}

