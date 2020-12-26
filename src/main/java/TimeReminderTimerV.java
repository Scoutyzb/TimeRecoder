import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.System.Logger;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.text.TabStop;
import javax.swing.JSpinner;
import javax.swing.JPopupMenu;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JCheckBoxMenuItem;

public class TimeReminderTimerV  extends JFrame{
    private static final long serialVersionUID = 1L;
    private JLabel date;
    private JLabel time;
    
    private JButton startButton = new JButton("Start");;
    private JButton stopButton = new JButton("Stop");
    private final JButton logButton = new JButton("Log");
    
    
    private Date startTime;//
    private Date EndTime;//每个计时开始与结束时间，做日志用
    private Date todayDate;//今日时间，做日志名称+dateLabel;
    private Date dualTime;//倒计时的期限时间
    
    private JSpinner spinner = new JSpinner();
    
    private int setminutes;//时间设置
    
    private boolean run = false;//是否更新时间
  
    private Timer timer = new Timer(1000, new TimeRunner());//更新时间线程
    private TimeLogger timeLogger;
    private boolean top = false;
    
    private final JLabel alltime = new JLabel();
    private final JLabel all_week_time_label = new JLabel();
    
    private long haveSaved=0;
    private long logSaved=0;
    
    public TimeReminderTimerV(){

        init();
        initpanel();
        timer.start();
        
    }
    public void initpanel() {
       	setResizable(false);
    	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	
		// TODO Auto-generated constructor stub
        this.setVisible(true);
        this.setTitle("鏁板瓧鏃堕挓");
        this.setSize(273, 291);
        this.setLocation(200, 200);
        JPanel panel = new JPanel();
        getContentPane().add(panel, BorderLayout.CENTER);
        panel.setLayout(null);
        //鏃堕棿
        time = new JLabel();
        time.setBounds(31, 30, 196, 59);
        time.setFont(new Font("Arial", Font.PLAIN, 50));
        panel.add(time);
        time.setText("00:00:00");
        //鏃ユ湡
        
        date = new JLabel();
        date.setFont(new Font("微软雅黑", Font.PLAIN, 18));
        date.setBounds(47, 10, 180, 22);
        panel.add(date);
        date.setText(new SimpleDateFormat("yyyy 年 MM 月 dd 日 ").format(todayDate));
         
        //timeLogger = new TimeLogger(todayDate);
        
        spinner.setBounds(31, 99, 40, 22);
        spinner.setValue(25);
        panel.add(spinner);
        
        JLabel Minutes = new JLabel("Minutes");
        Minutes.setBounds(81, 99, 54, 21);
        panel.add(Minutes);
        
        
        startButton.addActionListener(new RunorNot(true));
        startButton.setBounds(31, 131, 93, 23);
        panel.add(startButton);
        
        
        stopButton.setBounds(145, 131, 93, 23);
        stopButton.addActionListener(new RunorNot(false));
        panel.add(stopButton);
        
        
        
        logButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		
        	}
        });
        logButton.setBounds(145, 99, 93, 23);
        
        panel.add(logButton);
        alltime.setText("00:00:00");
        alltime.setFont(new Font("Arial", Font.PLAIN, 50));
        alltime.setBounds(31, 187, 196, 59);
        
        panel.add(alltime);
        all_week_time_label.setText("本周已计时：");
        all_week_time_label.setFont(new Font("微软雅黑", Font.PLAIN, 18));
        all_week_time_label.setBounds(47, 164, 180, 22);
        
        panel.add(all_week_time_label);
        
        JPopupMenu popupMenu = new JPopupMenu();
        
        addPopup(this, popupMenu);
        
        JCheckBoxMenuItem chckbxmntmNewCheckItem = new JCheckBoxMenuItem("New check item");
        setAlwaysOnTop(top);
        chckbxmntmNewCheckItem.setSelected(top);
        chckbxmntmNewCheckItem.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		top = !top;
        		setAlwaysOnTop(top);
        	}
        });
        popupMenu.add(chckbxmntmNewCheckItem);
    }
    
    public void init() {
    	//今日日期初始
    	todayDate = new Date();
    	//timeLogger初始
    	timeLogger = new TimeLogger(todayDate);
    	//logsaved初始,havesaved初始
    	logSaved = timeLogger.tlgParser();
    	
    	
    }
    
    
    class RunorNot implements ActionListener{
    	private  boolean status;
    	public RunorNot(boolean status) {
    		this.status = status;
    	}
    	
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			run = this.status;
			System.out.println(run);
			if(status) {
				setminutes = (Integer) spinner.getValue();
				startTime = new Date();
				//System.out.println(setminutes);
				dualTime = new Date(startTime.getTime()+setminutes*60*1000);
				//System.out.println(new SimpleDateFormat("yyyy  MM  dd HH:mm:ss").format(startTime));
			}
			else {
				Date stopTime = new Date();
				if(timeLogger.addLog(startTime, stopTime, (stopTime.getTime()-startTime.getTime())/1000)){
		
					JOptionPane.showMessageDialog(null, "导入成功", "导入结果",JOptionPane.INFORMATION_MESSAGE);    
				}
				else {
					JOptionPane.showMessageDialog(null, "导入失败", "导入结果",JOptionPane.INFORMATION_MESSAGE);   
				};	
			}
		}
    }
    class TimeRunner implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if(run){
				Date nowtime = new Date();
				long diff =  dualTime.getTime()-nowtime.getTime();
				if(diff<=0)
					new RunorNot(false).actionPerformed(null);
				TimeTransfer djsTrans = new TimeTransfer();
				djsTrans.update(diff+1000);
				time.setText(String.format("%02d",djsTrans.getHours())+":"+String.format("%02d",djsTrans.getMinutes())+":"+String.format("%02d",djsTrans.getSeconds()));
				
				long passed = nowtime.getTime()-startTime.getTime(); 
				TimeTransfer passTrans = new TimeTransfer();
				passTrans.update(passed+haveSaved+logSaved);
				alltime.setText(String.format("%02d",passTrans.getHours())+":"+String.format("%02d",passTrans.getMinutes())+":"+String.format("%02d",passTrans.getSeconds()));
				
				
				//System.out.println(String.format("%02d",djstrans.getHours())+":"+String.format("%02d",djstrans.getMinutes())+":"+String.format("%02d",djstrans.getSeconds()));
			}
		}
    	
    }
    
    
    public static void main(String[] args) {
		new TimeReminderTimerV();
		
	}
	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}
}
