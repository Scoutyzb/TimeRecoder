
public  class TimeTransfer {
	private long hours;
	private long minutes;
	private long seconds;
	
	public void update(long mills) {
		long mill = mills/1000;
		this.hours = mill/(60*60);
		this.minutes = (mill - this.hours * 60 * 60)/60;
		this.seconds = (mill - this.hours * 60 *  60 - this.minutes * 60);
		//System.out.println(mills);
		//System.out.println(this.hours);
		//System.out.println(this.minutes);
		//System.out.println(this.seconds);
	}

	public long getHours() {
		return hours;
	}

	public long getMinutes() {
		return minutes;
	}

	public long getSeconds() {
		return seconds;
	}
}
