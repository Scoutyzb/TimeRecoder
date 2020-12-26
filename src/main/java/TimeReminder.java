import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import java.awt.Font;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JSpinner;
public class TimeReminder extends JFrame implements Runnable{
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private JLabel date;
    private JLabel time;
    private JButton StartButton;
    private Time startTime;
    private boolean run;
    public TimeReminder() {
        //鍒濆鍖栧浘褰㈢晫闈�
        this.setVisible(true);
        this.setTitle("鏁板瓧鏃堕挓");
        this.setSize(270, 225);
        this.setLocation(200, 200);
        this.setResizable(true);
        JPanel panel = new JPanel();
        getContentPane().add(panel, BorderLayout.CENTER);
        panel.setLayout(null);
        //鏃堕棿
        time = new JLabel();
        time.setBounds(31, 42, 196, 59);
        time.setFont(new Font("Arial", Font.PLAIN, 50));
        panel.add(time);
        //鏃ユ湡
        date = new JLabel();
        date.setFont(new Font("微软雅黑", Font.PLAIN, 13));
        date.setBounds(47, 10, 180, 22);
        panel.add(date);
        
        StartButton = new JButton("Start");
        StartButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		run = true;
        	}
        });
        StartButton.setBounds(31, 133, 93, 23);
        panel.add(StartButton);
        
        JButton StopButton = new JButton("Stop");
        StopButton.setBounds(151, 133, 93, 23);
        panel.add(StopButton);
        
        JSpinner spinner = new JSpinner();
        spinner.setBounds(31, 108, 35, 22);
        panel.add(spinner);
        
        JLabel lblNewLabel = new JLabel("New label");
        lblNewLabel.setBounds(76, 111, 54, 15);
        panel.add(lblNewLabel);
        
        JButton LogButton = new JButton("Log");
        LogButton.setBounds(151, 107, 93, 23);
        panel.add(LogButton);
    }
    //鐢ㄤ竴涓嚎绋嬫潵鏇存柊鏃堕棿
    public void run() {
    	while(true) {
	        while(run){
	            try{
	                date.setText(new SimpleDateFormat("yyyy 骞� MM 鏈� dd 鏃�  EEEE").format(new Date()));
	                time.setText(new SimpleDateFormat("HH:mm:ss").format(new Date()));
	            }catch(Throwable t){
	                t.printStackTrace();
	            }
	        }
	        System.out.println("yes");
    	}
    }
    public static void main(String[] args) {
        new Thread(new TimeReminder()).start();
    }
}

