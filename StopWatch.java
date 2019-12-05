import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
class StopWatch 
{
    JFrame f;
    JPanel p1,p2,p4,p3,p5,p6;
    JLabel minLabel,collen,secLabel,collen1,msecLabel;
    Font font,fontButton;
    JButton bStart,bSplit,bStop;
    JTable jt;
    DefaultTableModel dtm;
    JScrollPane sp;
    int min=0,sec=0,msec=0,index=1;
    boolean spider_man=true;

    StopWatch()
    {
        f=new JFrame("Stop Watch");
        p1=new JPanel(); 
        f.setBounds(200,200,500,420);
        p1.setLayout(new GridLayout(2,1));
        
        //Top panel
        p2=new JPanel(); 
        p2.setLayout(new GridLayout(3,1));   

        //CLOCK
        p3=new JPanel();

        minLabel=new JLabel("00");
        collen=new JLabel(":");
        secLabel=new JLabel("00");
        collen1=new JLabel(":");
        msecLabel=new JLabel("000");
        p3.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 20));
        font=new Font("Impact",Font.PLAIN, 32);

        minLabel.setForeground(Color.BLUE);
        minLabel.setFont(font);
        collen.setForeground(Color.BLUE);
        collen.setFont(font);
        collen1.setForeground(Color.BLUE);
        collen1.setFont(font);
        msecLabel.setForeground(Color.BLUE);
        secLabel.setFont(font);
        secLabel.setForeground(Color.BLUE);
        msecLabel.setFont(font);
        msecLabel.setForeground(Color.BLUE);
      
        p3.add(minLabel);
        p3.add(collen);
        p3.add(secLabel);
        p3.add(collen1);
        p3.add(msecLabel);        
        
        // JButton
        p5=new JPanel();
        p5.setLayout(new FlowLayout(FlowLayout.CENTER, 50, 20));

        fontButton = new Font("Cambria",Font.BOLD,16);
       
        bStart=new JButton("Start");
        bStart.setToolTipText("Click here start the Clock");
        
        bStart.setFont(fontButton);
        bStart.addActionListener((e)->
        {
            bSplit.setEnabled(true);
            bStart.setEnabled(false);
            spider_man=true;
           // System.out.println("line 70");
            class Clock123 extends Thread
            {   
                public void run()
                {
                	while(spider_man)
                    {
                        msec++;
                        if(sec>=59)
                        {           
                            sec=0;
                            min++;
                            
                            if(min<=9)
                                minLabel.setText("0"+String.valueOf(min));
                            else
                                minLabel.setText(String.valueOf(min));
                            
                        }
                        if(msec==999)
                        {
                            msec=0;
                            sec++;           
                            //formating 2 digit number for sec
                            if(sec<=9)
                                secLabel.setText("0"+String.valueOf(sec));
                            else
                                secLabel.setText(String.valueOf(sec));
                        }
                        
                        //Formating for 3 dight number of milisecound
                        if(msec<=9)
                            msecLabel.setText("00"+String.valueOf(msec));
                        else if(msec<=99)
                            msecLabel.setText("0"+String.valueOf(msec));
                        else
                            msecLabel.setText(String.valueOf(msec));
                                           
                        try
                        {
                            Thread.sleep(1);
                        }
                        catch(InterruptedException E)
                        {
                            System.out.println(E);
                        }
                        if(sec%2==0)
                        {
                            collen.setEnabled(false);
                            collen1.setEnabled(false);
                        }
                        else
                        {
                            collen.setEnabled(true);
                            collen1.setEnabled(true);
                        }



                        bStop.setEnabled(true);
                        bStop.addActionListener(e1->
                        {
                            bStart.setEnabled(true);
                            sp.setVisible(true);
                            spider_man=false;
                            bStop.setEnabled(false);
                        });
                    }
                }
            }
            Clock123 c=new Clock123();
            c.start();
        });

        p5.add(bStart);
        bStop=new JButton("Stop");
        bStop.setToolTipText("Click here to Stop the clock");
        bStop.setFont(fontButton);
        bStop.setEnabled(false);
        p5.add(bStop);
        
        p6=new JPanel();
        bSplit=new JButton("Split");
        bSplit.setToolTipText("Click here to save the current time");
        bSplit.setFont(fontButton);
        bSplit.setEnabled(false);
        
        bSplit.addActionListener(e->{
            sp.setVisible(true); 
            String temp=minLabel.getText()+":"+secLabel.getText()+":"+msecLabel.getText();
            String row[]={String.valueOf(index),temp};
            dtm.insertRow(0, row);
            index++;
        });
        p6.add(bSplit);             
        p2.add(p3);
        p2.add(p5);
        p2.add(p6);

        //Bottom panel
        p4=new JPanel();
       
        String head[]={"Sr.No","Time"};
        String items[][]={};
       
        dtm=new DefaultTableModel(items,head);
        jt=new JTable(dtm);
        //jt.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        sp=new JScrollPane(jt);
        jt.setFont(fontButton);
        jt.setRowSelectionAllowed(false);
        jt.setColumnSelectionAllowed(false);
        sp.setVisible(false); 

        p4.add(sp);

        p1.add(p2);
        p1.add(p4);
        f.add(p1);
        
        //Icon
        ImageIcon ii=new ImageIcon("E:/JavaAdv/StopWatch/icons8-watch-98.png");
        Image i=ii.getImage();
        f.setIconImage(i);
        f.setResizable(false);
        f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        f.setVisible(true);
    }
    public static void main(String arg[])
    {
        StopWatch s=new StopWatch();       
    }
}