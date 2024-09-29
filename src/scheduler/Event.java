package scheduler;

public class Event {
private String function;
private int time;
void setFunction(String function) {
	this.function=function;
}
void setTime(int time) {
	if(time<=2359&&time>=0000) {
	this.time=time;
	} else {
		//error
	}
}
String getFunction() {
	return function;
}
int getTime() {
	return time;
}
String getATime() {
int hour;
int minute;
minute = time-(time/100)*100;
hour = time/100;
String whichM;
if(hour>12) {
	whichM="P.M.";
	hour-=12;
} else {whichM="A.M.";}
if(minute<10) {
	return hour+":0"+minute+" "+whichM;
}
	return hour+":"+minute+" "+whichM;
}
}
