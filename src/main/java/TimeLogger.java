import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.security.PublicKey;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TimeLogger {
	private String name; 
	private File logfile; 
	Calendar cal;
	public TimeLogger(Date todayDate) {
		// TODO Auto-generated constructor stub
		cal = Calendar.getInstance();
		cal.setFirstDayOfWeek(Calendar.SUNDAY);//设置周一为一周的第一天
		cal.setTime(todayDate);
		int num = cal.get(Calendar.WEEK_OF_YEAR);
		
		
		this.name = ("./log/"+cal.get(Calendar.YEAR) +"_Year_Week_" + cal.get(Calendar.WEEK_OF_YEAR)+".tlg");
		logfile = new File(name);
		
		if(!logfile.exists()) {
			try {
				logfile.createNewFile();
			} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			}
		}		
	}
	public boolean addLog(Date startTime,Date stopTime,long mills) {
		BufferedWriter out;
		try {
			out = new BufferedWriter(new FileWriter(logfile,true));
			out.write(new SimpleDateFormat("yyyy-MM-dd").format(stopTime)+"\t"
					+new SimpleDateFormat("HH:mm:ss").format(startTime)+"\t"
					+new SimpleDateFormat("HH:mm:ss").format(stopTime)+"\t"
					+String.valueOf(mills+1)+"\n" );
			out.flush();
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
		
	}
	public long tlgParser() {
		 return 0;
		
		
	}
	
	
	public void open() {

	}
}
