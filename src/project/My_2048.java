package project;

import java.util.Date;
import java.util.Random;    // ���������
import java.awt.event.*;  // �ṩ�����¼��Ľӿں���
import java.awt.Color;     // �ṩ������ɫ����
import java.awt.Font;        // �ṩ��������ص���ͽӿ�
import java.awt.EventQueue;  //�������ڻ���ͬλ����������ε�Ӧ�ó�������¼��������
import javax.swing.JFrame;   // ���
import javax.swing.JPanel;   // �������  ���Լ��뵽 JFrame ��
import javax.swing.JLabel;   // ��ʾ�ı���ͼ���ͬʱ��ʾ����
import javax.swing.BorderFactory; // ��Ʊ߿�
import javax.swing.SwingConstants;
import javax.swing.JTextField; // �����ı�����
import javax.swing.JOptionPane;//����



public class My_2048 extends JFrame{
     
    private static final long serialVersionUID = 1L;
    private JPanel ScoresPanel;
    private JPanel MainPanel;
    private JPanel TimeSpentPanel;
    private JPanel TipsPanel;
     
    private JLabel MaxScoreLabel;
    private JLabel CurrentScoreLabel;  // ��ǰ�÷�
    private JLabel TipsLabel;          // ��ʾ
    private JLabel ScoreValueLabel;    // ��ǰ�ķ���ֵ
    private JLabel[][] Texts;          // �ı�
       private JLabel TimeSpentLabel;     // ��ʾ�˴γ������� ������ʱ��
       private JLabel TimeSpentValueLabel;
     
    private JTextField MaxScoreField;  // ��¼�������ı�
     
    private int SurDiamonts = 16;      // ��ʾʣ�෽����Ŀ
    private int RecordScores = 0;      // ��¼��ǰ�ķ���
    private int MaxScores;      // ��¼������
    private String CurrentTime = "" ;          // ��ǰʱ��
    private int Mark1, Mark2, Mark3, Mark4;  // �ж���Ϸ�Ƿ����
    Font Font1 = new Font("",Font.BOLD,15);
    Font Font2 = new Font("",Font.BOLD,30);
    Random MyRandom = new Random();     // ���������
     
    public  String  Current_time() throws InterruptedException{   // ��ȡʱ��
        Date dt = new Date(System.currentTimeMillis());
        while(1>0){
            Thread.sleep(1000);
            dt.setTime(System.currentTimeMillis());
            //CurrentTime = dt.toString();
            return dt.toString();
        }
    }
     
     
    public My_2048() throws InterruptedException{    // ���췽��
        super();
        setResizable(false);   // ���ò����� �������ڴ�С
        getContentPane().setLayout(null); // ���ò��ֹ�����
        setBounds(700, 20, 500, 680);    // ������������λ�� ����С
        setTitle("                                     2048 game ");  // ����
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // �رմ���,�������ڴ�פ��
         
        TimeSpentPanel = new JPanel();  // ����ʱ����ʾ��
        TimeSpentPanel.setBackground(Color.RED);  // 
        TimeSpentPanel.setBounds(20, 20, 450, 25);
        TimeSpentPanel.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.RED));
        getContentPane().add(TimeSpentPanel);
        TimeSpentPanel.setLayout(null);  //
         
        MainPanel = new JPanel();   // ���������
        MainPanel.setBounds(20, 75, 450, 440);
        getContentPane().add(MainPanel);
        MainPanel.setLayout(null);
         
        Texts = new JLabel[4][4];
        for(int i = 0; i < 4 ; i++){
            for(int j = 0; j < 4 ;j++){
                Texts[i][j] = new JLabel();   // ������ǩ
                Texts[i][j].setFont(Font2);   // ���������С
                Texts[i][j].setHorizontalAlignment(SwingConstants.CENTER);  // �������־���
                Texts[i][j].setBounds(120*i, 115*j, 90 ,90);  // ����ÿ�����С,λ��
                Texts[i][j].setOpaque(true);   // 
                Texts[i][j].setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.BLACK));
                MainPanel.add(Texts[i][j]);  // ��ÿ��С����뵽  mainPanel ��
            } // for j
        }  // for i
         
      
        
        ScoresPanel = new JPanel();    // �����Ʒְ�
        ScoresPanel.setBackground(Color.GREEN);  // �Ʒְ�ı�����ɫ
        ScoresPanel.setBounds(20, 520, 450, 30);  // �Ʒְ�λ��
        ScoresPanel.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.RED));
        getContentPane().add(ScoresPanel);  // ���Ʒְ���ӵ�����
        ScoresPanel.setLayout(null); // ���ò���
         
        TipsPanel = new JPanel();  // ��ʾ����
        TipsPanel.setBackground(Color.YELLOW);
        TipsPanel.setFont(Font1);  // ������ʾ����Ĵ�С
        TipsPanel.setBounds(20, 560, 450, 70);
        TipsPanel.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.BLACK));
        getContentPane().add(TipsPanel);
         
        MaxScoreLabel = new JLabel("��߷�: ");  // ������߷ֱ�ǩ
        MaxScoreLabel.setFont(Font1);
        MaxScoreLabel.setBounds(10, 3, 220, 27);
        ScoresPanel.add(MaxScoreLabel);  // ����߷ֱ�ǩ���뵽 ScoresPanel ��
         
        ScoreValueLabel = new JLabel("��ǰ�÷�: "); // ���õ�ǰ�÷ֱ�ǩ
        ScoreValueLabel.setFont(Font1);
        ScoreValueLabel.setBounds(230, 3, 220, 27);  
        ScoresPanel.add(ScoreValueLabel);
         
        MaxScoreField = new JTextField(MaxScores);  // �������ı�����
        MaxScoreField.setBackground(Color.GREEN);
        MaxScoreField.setFont(Font1);
        MaxScoreField.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.GREEN));
        MaxScoreField.setBounds(80, 4, 100, 20);
        MaxScoreField.setEditable(false);
        ScoresPanel.add(MaxScoreField);
         
        CurrentScoreLabel = new JLabel(String.valueOf(RecordScores)); // ���õ�ǰ�÷�ֵ��ǩ
        CurrentScoreLabel.setFont(Font1);
        CurrentScoreLabel.setBounds(320, 4, 100, 20);
        CurrentScoreLabel.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.GREEN));
        CurrentScoreLabel.setBackground(Color.red);
        ScoresPanel.add(CurrentScoreLabel);
         
        TipsLabel = new JLabel("��ʹ�á�,��,��,��������,��esc�����¿�ʼ");
        TipsLabel.setFont(Font1);
        TipsLabel.setBounds(70, 4, 250, 20);
        TipsPanel.add(TipsLabel);
        
        TipsLabel = new JLabel(" ע�⣺���������Ҿ��޷��ƶ�ʱ����Ϸ������");
        TipsLabel.setFont(Font1);
        TipsLabel.setBounds(80, 4, 250, 20);
        TipsPanel.add(TipsLabel);
         
        TimeSpentLabel = new JLabel("��Ϸ��ʼʱ��Ϊ : ");
        TimeSpentLabel.setFont(Font1);
        TimeSpentLabel.setBounds(20, 3, 150, 27);
        TimeSpentPanel.add(TimeSpentLabel);
         
        TimeSpentValueLabel = new JLabel(String.valueOf(CurrentTime));
        TimeSpentValueLabel.setFont(Font1);
        TimeSpentValueLabel.setBounds(160, 3, 300, 27);
        TimeSpentPanel.add(TimeSpentValueLabel);
        TimeSpentValueLabel.setText(String.valueOf(Current_time()));
        
       
        
        MaxScoreField.addKeyListener(new KeyAdapter() {  // Ϊ��߷ֱ�ǩ��Ӽ�����
            public void keyPressed(KeyEvent e){
                Major(e);
            }
        });
        Creat_2();   // �������� �µ� 2
        Creat_2();
        
        
    } // my_2048
    protected void SetColor(int i,int j,String str){  //  ��ĳ���ָ����������ɫ������
        switch(str){
        case "" :
        case "2" :
            Texts[i][j].setBackground(Color.WHITE);  
            break;
        case "4" :
            Texts[i][j].setBackground(Color.LIGHT_GRAY);  // ǳ��
            break;
        case "8" :
            Texts[i][j].setBackground(Color.YELLOW);  
            break;
        case "16" :
            Texts[i][j].setBackground(Color.ORANGE);// ��ɫ
            break;
        case "32" :
            Texts[i][j].setBackground(Color.PINK);
            break;
        case "64":
            Texts[i][j].setBackground(Color.RED);
            break;
        case "128":
            Texts[i][j].setBackground(Color.MAGENTA);
            break;
        case "256":
            Texts[i][j].setBackground(Color.GREEN);
            break;
        case "512" :
            Texts[i][j].setBackground(Color.BLUE);
            break;
        case "1024":
            Texts[i][j].setBackground(Color.GRAY);
            break;
        case "2048" :
            Texts[i][j].setBackground(Color.LIGHT_GRAY);
            break;
        case "4096" :
            Texts[i][j].setBackground(Color.DARK_GRAY);
            break;
        default:
                break;
        } // switch
    } // SetColor
    
    protected void Creat_2(){  // �²���һ�� 2 
        int i ,j;
        boolean r = false;
        String str;
         
        if(SurDiamonts > 0){  // ���ʣ�෽��
            while(!r){
                i = MyRandom.nextInt(4);
                j = MyRandom.nextInt(4);
                str = Texts[i][j].getText();
                if((str.compareTo("") == 0)){
                    Texts[i][j].setText("2");
                    SetColor(i, j, "2");
                     
                    SurDiamonts --;  // ʣ�෽������ 1
                    r = true;
                    Mark1 = Mark2 = Mark3 = Mark4 = 0;  
                }
            }
        }
        else if(Mark1 >0 && Mark2 >0 && Mark3 > 0 && Mark4 > 0){        // mark1 ��mark4ͬʱ�����̸�ֵΪ1˵���κη���������ܲ����µ�����2��˵����Ϸʧ��
        	MaxScores = RecordScores;
            MaxScoreField.setText(String.valueOf(MaxScores));
        	JOptionPane.showMessageDialog(this,"       Game over��\n        You got " + String.valueOf(RecordScores) + "��");
            
        }
    }  // Creat
    
    protected void Major(final KeyEvent e){  // ����Ӧ���������ķ�Ӧ
        int KeyCode = e.getKeyCode();  // ��ȡ��������
        int Pre;   // ��ֹ����
        int Num;
        String Str;
        String Str_1;
        switch (KeyCode){
        
        case KeyEvent.VK_ESCAPE :
        	initGame();
        	break;
         
        case KeyEvent.VK_LEFT :  // ���������� �� ʱ
            for(int j = 0; j < 4; j++){
                Pre = 5;
                for(int k = 0; k < 3; k++){
                    for(int i = 1; i < 4; i++){    // ���� 16 ���ո�
                        Str = Texts[i][j].getText();   // ��ȡ��ǰ�ո������
                        Str_1 = Texts[i - 1][j].getText();  // ��õ�ǰ��ǰ�ո���ߵĵ�һ���ո������
                     
                        if(Str_1.compareTo("") == 0){  // �����ǰ�ո����ߵ�һ���ո�����Ϊ��
                            Texts[i - 1][j].setText(Str);  // �趨 �� 1 ��ֵΪ ��ǰ�����ֵ
                            SetColor(i-1, j, Str);  //  �趨�� 1 ����ɫ
                            Texts[i][j].setText("");  // ����ǰ�����ֵ��Ϊ ��
                            SetColor(i, j, "");   // �趨��ǰ�������ɫ
                        }  // if �� 1 ����Ϊ��
                        else if(Str.compareTo(Str_1) == 0 && i != Pre && i != Pre -1){  // ���������������  ��  û�������������
                            Num  = Integer.parseInt(Str);  // ����ǰ����� ����ת�� Ϊ ����
                            RecordScores += Num ;  //  ��¼�ĵ�ǰ�÷� ����
                            SurDiamonts ++;    // ���෽�����Ŀ����
                            Str = String.valueOf(2 * Num);  
                            Texts[i - 1][j].setText(Str);  // ����������Ӻ� ��ӵ� �� 1
                            SetColor(i-1, j, Str);  // ���� 1 ������ɫ
                            Texts[i][j].setText("");  // ����ǰ���� ֵ ��Ϊ �� 
                            SetColor(i, j, "");
                            Pre = i;
                        } // else if  ��������ֵ��� ��
                    } // for  i
                }  // for k
            }  // for j
            Mark3 = 1;
            Creat_2();  // ����һ���µ� 2 
            break;
        
        case KeyEvent.VK_RIGHT :   // ���������� �� ʱ
            for(int j = 0; j < 4; j ++){
                Pre = 5;
                for(int k = 0; k < 5; k++){
                    for(int i = 2; i >= 0; i--){
                        Str = Texts[i][j].getText();
                        Str_1 = Texts[i + 1][j].getText();
                         
                        if(Str_1.compareTo("") == 0){
                            Texts[i + 1][j].setText(Str);
                            SetColor(i+1, j, Str);
                            Texts[i][j].setText("");
                            SetColor(i, j, "");
                        }
                        else if(Str.compareTo(Str_1) == 0 && i != Pre && i != Pre + 1){
                            Num  = Integer.parseInt(Str);
                            RecordScores += Num ;
                            SurDiamonts ++;
                            Str = String.valueOf(2 * Num);
                            Texts[i + 1][j].setText(Str );
                            SetColor(i+1, j, Str);
                            Texts[i][j].setText("");
                            SetColor(i, j, "");
                            Pre= i;
                        }
                    }
                }
            }
            Mark4 = 1;
            Creat_2();
            break;
        
        case KeyEvent.VK_DOWN :   // ���������� �� ʱ
            for(int i = 0; i < 4; i ++){
                Pre = 5;
                for(int k = 0; k < 3; k++){
                    for(int j = 2; j >= 0; j--){  // ����16������
                        Str = Texts[i][j].getText();  // ��õ�ǰ�ո������
                        Str_1 = Texts[i][j + 1].getText();  // ��ȡ��ǰ�ո������һ���ո������
                         
                        if(Str_1.compareTo("") == 0){   // �� ��ǰ�ո�����ĵ�һ���ո�Ϊ��
                            Texts[i][j + 1].setText(Str);
                            SetColor(i, j+1, Str);
                            Texts[i][j].setText("");
                            SetColor(i, j, "");
                        }  // if ��ǰ�ո������һ���ո������Ϊ��
                        else if(Str.compareTo(Str_1) == 0 && j !=Pre && j != Pre+ 1){
                            Num  = Integer.parseInt(Str);
                            RecordScores += Num ;
                            SurDiamonts ++;
                            Str = String.valueOf(2 * Num);
                            Texts[i][j + 1].setText(Str);
                            SetColor(i, j+1, Str);
                            Texts[i][j].setText("");
                            SetColor(i, j, "");
                            Pre = j;
                        }
                    }  // for j
                }  // for k
            }  // for i
            Mark2 = 1;
            Creat_2();
            break;
        
        case KeyEvent.VK_UP:   // ���������� �� ʱ  
            for(int i = 0; i < 4;i++){
                Pre = 5;
                for(int j = 0; j < 3;j++){
                    for(int k = 1;k < 4;k++){  // ����ȫ������
                        Str = Texts[i][k].getText();  // ��ȡ��ǰλ�õ��ַ�
                        Str_1 = Texts[i][k-1].getText(); // ��ȡ��ǰλ�õ��ϱߵĵ�һ���ַ�
                         
                        if(Str_1.compareTo("") == 0){  // �����ߵ�һ���ַ�Ϊ��
                            Texts[i][k-1].setText(Str);  // ���ַ�����(�ַ���ֵ)
                            SetColor(i, k-1, Str);
                            Texts[i][k].setText("");   // ��ǰ�ַ���Ϊ ��
                            SetColor(i, k, "");
                        } // if
                        else if ((Str.compareTo(Str_1) == 0) && (k != Pre) &&( k != Pre-1)) {  // �����ǰ�ַ�����ߵ�һ���ַ����
                            Num = Integer.parseInt(Str); // ���ַ��ͱ���ת��Ϊ���ͱ���
                            RecordScores += Num;  // ��¼�ĵ�ǰ����Ҫ����
                            SurDiamonts ++;   // ʣ��Ŀշ������Ŀ����
                            Str = String.valueOf(2 * Num);  // str ��ֵ ����һ��
                            Texts[i][k-1] .setText(Str);  // ��ߵĵ�һ�������ַ��������
                            SetColor(i, k-1, Str);  // ����ߵķ���ı���ɫ
                            Texts[i][k].setText("");  // ��ǰ����ֵ�ÿ�
                            SetColor(i, k, "");
                            Pre = k;
                        }
                    } // for k
                }  // for j
            } // for i
            Mark1 = 1;
            Creat_2();  // �²���һ�� 2 
        
        default:
            break;
        } // switch
        
        CurrentScoreLabel.setText(String.valueOf(RecordScores));
    }
    
    private void initGame() {
    	RecordScores = 0;

		Texts = new JLabel[4][4];
		for(int i = 0; i < 4 ; i++){
			for(int j = 0; j < 4 ;j++){
				Texts[i][j] = new JLabel();   // ������ǩ
				Texts[i][j].setFont(Font2);   // ���������С
				Texts[i][j].setHorizontalAlignment(SwingConstants.CENTER);  // �������־���
				Texts[i][j].setBounds(120*i, 115*j, 90 ,90);  // ����ÿ�����С,λ��
				Texts[i][j].setOpaque(true);   // 
				Texts[i][j].setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.BLACK));
				MainPanel.add(Texts[i][j]);  // ��ÿ��С����뵽  mainPanel ��
			} // for j
		}// for i
       
		Mark1 = Mark2 = Mark3 = Mark4 = 0;
		setVisible(false);
		EventQueue.invokeLater(new Runnable(){   // ����һ���߳�
            public void run(){
                try{
                    My_2048 frame = new My_2048();
                    
                    frame.setVisible(true);
                //  Thread thread = new Thread(frame);
                //  thread.start();
                }
                catch(Exception e1){   // ��׽�쳣
                    e1.printStackTrace();
                }
            }
        });
       
    }
    
    public static void main(String[] args) throws InterruptedException {
       
        EventQueue.invokeLater(new Runnable(){   // ����һ���߳�:�������������Ϻ����ᱻ����
            public void run(){
                try{
                    My_2048 frame = new My_2048();
                    frame.setVisible(true);
                }
                catch(Exception e1){   // ��׽�쳣
                    e1.printStackTrace();
                }
            }
        });
    }
 
}
