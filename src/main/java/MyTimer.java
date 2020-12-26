    import java.awt.Font;     
    import java.awt.GridLayout;     
    import java.awt.Toolkit;     
    import java.awt.event.ActionEvent;     
    import java.awt.event.ActionListener;     
        
    import javax.swing.JButton;     
    import javax.swing.JFrame;     
    import javax.swing.JLabel;     
        
    public class MyTimer extends JFrame implements Runnable, ActionListener{     
        private int hour;     
        private int minute;     
        private int second;     
        private JLabel timeLabel;     
        private boolean canrun;
        
        
        public MyTimer() {     
            this(2,0,0);     
        }     
             
        public MyTimer(int hour, int minute, int second) {     
            this.setTime(hour, minute, second);     
            this.setLayout(new GridLayout(1,2));     
            timeLabel = new JLabel();     
            timeLabel.setFont(new Font(Font.SERIF, Font.BOLD, 20));     
            this.setText();     
            this.add(timeLabel);     
            JButton btn = new JButton("开始");     
            btn.addActionListener(this);     
            this.add(btn);     
            this.pack();     
            this.setResizable(false);     
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);     
            Toolkit tool = Toolkit.getDefaultToolkit();     
            double width = tool.getScreenSize().getWidth();     
            double height = tool.getScreenSize().getHeight();     
            this.setLocation((int)((width-this.getWidth())/2)     
                    ,(int)((height-this.getHeight())/2));     
            this.setVisible(true);     
        }     
             
        private void setTime(int hour, int minute, int second) {     
            this.hour = hour;     
            this.minute = minute;     
            this.second = second;     
        }     
             
        private void setText() {     
            this.timeLabel.setText((this.hour<10?"0"+this.hour:this.hour)     
                    +":"+(this.minute<10?"0"+this.minute:this.minute)     
                    +":"+(this.second<10?"0"+this.second:this.second));     
        }     
             
        public void actionPerformed(ActionEvent event) {     
            ((JButton)event.getSource()).setEnabled(false);  
            canrun = true;
            new Thread(this).start();     
        }     
        
        public static void main(String[] args) {     
            new MyTimer(1,0,0);     
        }     
        
        public void run() {     
            while (canrun) {     
                try {     
                    Thread.sleep(1000);     
                } catch (InterruptedException e) {     
                    e.printStackTrace();     
                }     
                this.second--;     
                if (this.second<0) {     
                    this.minute--;     
                    this.second=59;     
                }     
                if (this.minute<0) {     
                    this.hour--;     
                    this.minute=59;     
                }     
                if (this.hour<0) {     
                    break;     
                }     
                this.setText();     
            }     
                 
        }     
             
    }    