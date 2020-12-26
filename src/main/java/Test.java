import java.awt.image.DataBufferByte;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Test {
	
	public static void main(String[] args) throws Exception {

	Date date = new Date();
	Calendar cal = Calendar.getInstance();
	cal.setFirstDayOfWeek(Calendar.SUNDAY);//设置周一为一周的第一天
	cal.setTime(date);
	int num = cal.get(Calendar.YEAR);
	System.out.println(num);
	}
}
