package scheduler;

public class SchedulingConflictException extends Exception{
	
public SchedulingConflictException(String errorCause) {
	System.err.println("ERROR: "+errorCause+"\n");
}
}
