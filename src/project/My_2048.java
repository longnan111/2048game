package project;

import java.util.Date;
import java.util.Random;    // 产生随机数
import java.awt.event.*;  // 提供各类事件的接口和类
import java.awt.Color;     // 提供用于颜色的类
import java.awt.Font;        // 提供与字体相关的类和接口
import java.awt.EventQueue;  //将来自于基础同位体类和受信任的应用程序类的事件列入队列
import javax.swing.JFrame;   // 框架
import javax.swing.JPanel;   // 面板容器  可以加入到 JFrame 中
import javax.swing.JLabel;   // 显示文本、图像或同时显示二者
import javax.swing.BorderFactory; // 设计边框
import javax.swing.SwingConstants;
import javax.swing.JTextField; // 单行文本输入
import javax.swing.JOptionPane;//弹出



public class My_2048 extends JFrame{
     
    private static final long serialVersionUID = 1L;
    private JPanel ScoresPanel;
    private JPanel MainPanel;
    private JPanel TimeSpentPanel;
    private JPanel TipsPanel;
     
    private JLabel MaxScoreLabel;
    private JLabel CurrentScoreLabel;  // 当前得分
    private JLabel TipsLabel;          // 提示
    private JLabel ScoreValueLabel;    // 当前的分数值
    private JLabel[][] Texts;          // 文本
       private JLabel TimeSpentLabel;     // 显示此次程序运行 经历的时间
       private JLabel TimeSpentValueLabel;
     
    private JTextField MaxScoreField;  // 记录最大分数文本
     
    private int SurDiamonts = 16;      // 表示剩余方块数目
    private int RecordScores = 0;      // 记录当前的分数
    private int MaxScores;      // 记录最大分数
    private String CurrentTime = "" ;          // 当前时间
    private int Mark1, Mark2, Mark3, Mark4;  // 判断游戏是否结束
    Font Font1 = new Font("",Font.BOLD,15);
    Font Font2 = new Font("",Font.BOLD,30);
    Random MyRandom = new Random();     // 产生随机数
     
    public  String  Current_time() throws InterruptedException{   // 获取时间
        Date dt = new Date(System.currentTimeMillis());
        while(1>0){
            Thread.sleep(1000);
            dt.setTime(System.currentTimeMillis());
            //CurrentTime = dt.toString();
            return dt.toString();
        }
    }
     
     
    public My_2048() throws InterruptedException{    // 构造方法
        super();
        setResizable(false);   // 设置不允许 调整窗口大小
        getContentPane().setLayout(null); // 设置布局管理器
        setBounds(700, 20, 500, 680);    // 设置在容器中位置 及大小
        setTitle("                                     2048 game ");  // 标题
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 关闭窗口,程序不在内存驻留
         
        TimeSpentPanel = new JPanel();  // 创建时间显示栏
        TimeSpentPanel.setBackground(Color.RED);  // 
        TimeSpentPanel.setBounds(20, 20, 450, 25);
        TimeSpentPanel.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.RED));
        getContentPane().add(TimeSpentPanel);
        TimeSpentPanel.setLayout(null);  //
         
        MainPanel = new JPanel();   // 创建主面板
        MainPanel.setBounds(20, 75, 450, 440);
        getContentPane().add(MainPanel);
        MainPanel.setLayout(null);
         
        Texts = new JLabel[4][4];
        for(int i = 0; i < 4 ; i++){
            for(int j = 0; j < 4 ;j++){
                Texts[i][j] = new JLabel();   // 创建标签
                Texts[i][j].setFont(Font2);   // 设置字体大小
                Texts[i][j].setHorizontalAlignment(SwingConstants.CENTER);  // 设置文字居中
                Texts[i][j].setBounds(120*i, 115*j, 90 ,90);  // 设置每个框大小,位置
                Texts[i][j].setOpaque(true);   // 
                Texts[i][j].setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.BLACK));
                MainPanel.add(Texts[i][j]);  // 将每个小块加入到  mainPanel 中
            } // for j
        }  // for i
         
      
        
        ScoresPanel = new JPanel();    // 创建计分板
        ScoresPanel.setBackground(Color.GREEN);  // 计分板的背景颜色
        ScoresPanel.setBounds(20, 520, 450, 30);  // 计分板位置
        ScoresPanel.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.RED));
        getContentPane().add(ScoresPanel);  // 将计分板添加到窗体
        ScoresPanel.setLayout(null); // 设置布局
         
        TipsPanel = new JPanel();  // 提示容器
        TipsPanel.setBackground(Color.YELLOW);
        TipsPanel.setFont(Font1);  // 设置提示字体的大小
        TipsPanel.setBounds(20, 560, 450, 70);
        TipsPanel.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.BLACK));
        getContentPane().add(TipsPanel);
         
        MaxScoreLabel = new JLabel("最高分: ");  // 设置最高分标签
        MaxScoreLabel.setFont(Font1);
        MaxScoreLabel.setBounds(10, 3, 220, 27);
        ScoresPanel.add(MaxScoreLabel);  // 将最高分标签加入到 ScoresPanel 中
         
        ScoreValueLabel = new JLabel("当前得分: "); // 设置当前得分标签
        ScoreValueLabel.setFont(Font1);
        ScoreValueLabel.setBounds(230, 3, 220, 27);  
        ScoresPanel.add(ScoreValueLabel);
         
        MaxScoreField = new JTextField(MaxScores);  // 最大分数文本区域
        MaxScoreField.setBackground(Color.GREEN);
        MaxScoreField.setFont(Font1);
        MaxScoreField.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.GREEN));
        MaxScoreField.setBounds(80, 4, 100, 20);
        MaxScoreField.setEditable(false);
        ScoresPanel.add(MaxScoreField);
         
        CurrentScoreLabel = new JLabel(String.valueOf(RecordScores)); // 设置当前得分值标签
        CurrentScoreLabel.setFont(Font1);
        CurrentScoreLabel.setBounds(320, 4, 100, 20);
        CurrentScoreLabel.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.GREEN));
        CurrentScoreLabel.setBackground(Color.red);
        ScoresPanel.add(CurrentScoreLabel);
         
        TipsLabel = new JLabel("请使用↑,↓,←,→来控制,按esc键重新开始");
        TipsLabel.setFont(Font1);
        TipsLabel.setBounds(70, 4, 250, 20);
        TipsPanel.add(TipsLabel);
        
        TipsLabel = new JLabel(" 注意：当上下左右均无法移动时，游戏结束！");
        TipsLabel.setFont(Font1);
        TipsLabel.setBounds(80, 4, 250, 20);
        TipsPanel.add(TipsLabel);
         
        TimeSpentLabel = new JLabel("游戏开始时间为 : ");
        TimeSpentLabel.setFont(Font1);
        TimeSpentLabel.setBounds(20, 3, 150, 27);
        TimeSpentPanel.add(TimeSpentLabel);
         
        TimeSpentValueLabel = new JLabel(String.valueOf(CurrentTime));
        TimeSpentValueLabel.setFont(Font1);
        TimeSpentValueLabel.setBounds(160, 3, 300, 27);
        TimeSpentPanel.add(TimeSpentValueLabel);
        TimeSpentValueLabel.setText(String.valueOf(Current_time()));
        
       
        
        MaxScoreField.addKeyListener(new KeyAdapter() {  // 为最高分标签添加监听器
            public void keyPressed(KeyEvent e){
                Major(e);
            }
        });
        Creat_2();   // 产生俩个 新的 2
        Creat_2();
        
        
    } // my_2048
    protected void SetColor(int i,int j,String str){  //  对某数字个方块进行颜色的设置
        switch(str){
        case "" :
        case "2" :
            Texts[i][j].setBackground(Color.WHITE);  
            break;
        case "4" :
            Texts[i][j].setBackground(Color.LIGHT_GRAY);  // 浅灰
            break;
        case "8" :
            Texts[i][j].setBackground(Color.YELLOW);  
            break;
        case "16" :
            Texts[i][j].setBackground(Color.ORANGE);// 橙色
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
    
    protected void Creat_2(){  // 新产生一个 2 
        int i ,j;
        boolean r = false;
        String str;
         
        if(SurDiamonts > 0){  // 如果剩余方块
            while(!r){
                i = MyRandom.nextInt(4);
                j = MyRandom.nextInt(4);
                str = Texts[i][j].getText();
                if((str.compareTo("") == 0)){
                    Texts[i][j].setText("2");
                    SetColor(i, j, "2");
                     
                    SurDiamonts --;  // 剩余方块数减 1
                    r = true;
                    Mark1 = Mark2 = Mark3 = Mark4 = 0;  
                }
            }
        }
        else if(Mark1 >0 && Mark2 >0 && Mark3 > 0 && Mark4 > 0){        // mark1 到mark4同时被键盘赋值为1说明任何方向键都不能产生新的数字2，说明游戏失败
        	MaxScores = RecordScores;
            MaxScoreField.setText(String.valueOf(MaxScores));
        	JOptionPane.showMessageDialog(this,"       Game over！\n        You got " + String.valueOf(RecordScores) + "！");
            
        }
    }  // Creat
    
    protected void Major(final KeyEvent e){  // 对相应动作做出的反应
        int KeyCode = e.getKeyCode();  // 获取按键代码
        int Pre;   // 防止连加
        int Num;
        String Str;
        String Str_1;
        switch (KeyCode){
        
        case KeyEvent.VK_ESCAPE :
        	initGame();
        	break;
         
        case KeyEvent.VK_LEFT :  // 当键盘输入 ← 时
            for(int j = 0; j < 4; j++){
                Pre = 5;
                for(int k = 0; k < 3; k++){
                    for(int i = 1; i < 4; i++){    // 遍历 16 个空格
                        Str = Texts[i][j].getText();   // 获取当前空格的内容
                        Str_1 = Texts[i - 1][j].getText();  // 获得当前当前空格左边的第一个空格的内容
                     
                        if(Str_1.compareTo("") == 0){  // 如果当前空格的左边第一个空格内容为空
                            Texts[i - 1][j].setText(Str);  // 设定 左 1 的值为 当前方块的值
                            SetColor(i-1, j, Str);  //  设定左 1 的颜色
                            Texts[i][j].setText("");  // 将当前方块的值置为 空
                            SetColor(i, j, "");   // 设定当前方块的颜色
                        }  // if 左 1 内容为空
                        else if(Str.compareTo(Str_1) == 0 && i != Pre && i != Pre -1){  // 俩个方框内容相等  且  没发生多次相加情况
                            Num  = Integer.parseInt(Str);  // 将当前方框的 内容转化 为 整型
                            RecordScores += Num ;  //  记录的当前得分 增加
                            SurDiamonts ++;    // 空余方格的数目增加
                            Str = String.valueOf(2 * Num);  
                            Texts[i - 1][j].setText(Str);  // 将两个数相加后 添加到 左 1
                            SetColor(i-1, j, Str);  // 给左 1 设置颜色
                            Texts[i][j].setText("");  // 将当前方块 值 置为 空 
                            SetColor(i, j, "");
                            Pre = i;
                        } // else if  两个方块值相等 且
                    } // for  i
                }  // for k
            }  // for j
            Mark3 = 1;
            Creat_2();  // 创建一个新的 2 
            break;
        
        case KeyEvent.VK_RIGHT :   // 当键盘输入 → 时
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
        
        case KeyEvent.VK_DOWN :   // 当键盘输入 ↓ 时
            for(int i = 0; i < 4; i ++){
                Pre = 5;
                for(int k = 0; k < 3; k++){
                    for(int j = 2; j >= 0; j--){  // 遍历16个方格
                        Str = Texts[i][j].getText();  // 获得当前空格的内容
                        Str_1 = Texts[i][j + 1].getText();  // 获取当前空格下面第一个空格的内容
                         
                        if(Str_1.compareTo("") == 0){   // 当 当前空格下面的第一个空格为空
                            Texts[i][j + 1].setText(Str);
                            SetColor(i, j+1, Str);
                            Texts[i][j].setText("");
                            SetColor(i, j, "");
                        }  // if 当前空格下面第一个空格的内容为空
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
        
        case KeyEvent.VK_UP:   // 当键盘输入 ↑ 时  
            for(int i = 0; i < 4;i++){
                Pre = 5;
                for(int j = 0; j < 3;j++){
                    for(int k = 1;k < 4;k++){  // 遍历全部方块
                        Str = Texts[i][k].getText();  // 获取当前位置的字符
                        Str_1 = Texts[i][k-1].getText(); // 获取当前位置的上边的第一个字符
                         
                        if(Str_1.compareTo("") == 0){  // 如果左边第一个字符为空
                            Texts[i][k-1].setText(Str);  // 将字符左移(字符赋值)
                            SetColor(i, k-1, Str);
                            Texts[i][k].setText("");   // 当前字符置为 空
                            SetColor(i, k, "");
                        } // if
                        else if ((Str.compareTo(Str_1) == 0) && (k != Pre) &&( k != Pre-1)) {  // 如果当前字符和左边第一个字符相等
                            Num = Integer.parseInt(Str); // 将字符型变量转化为整型变量
                            RecordScores += Num;  // 记录的当前分数要增加
                            SurDiamonts ++;   // 剩余的空方格的数目增加
                            Str = String.valueOf(2 * Num);  // str 的值 增加一倍
                            Texts[i][k-1] .setText(Str);  // 左边的第一个方块字符变成俩倍
                            SetColor(i, k-1, Str);  // 给左边的方块改变颜色
                            Texts[i][k].setText("");  // 当前方块值置空
                            SetColor(i, k, "");
                            Pre = k;
                        }
                    } // for k
                }  // for j
            } // for i
            Mark1 = 1;
            Creat_2();  // 新产生一个 2 
        
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
				Texts[i][j] = new JLabel();   // 创建标签
				Texts[i][j].setFont(Font2);   // 设置字体大小
				Texts[i][j].setHorizontalAlignment(SwingConstants.CENTER);  // 设置文字居中
				Texts[i][j].setBounds(120*i, 115*j, 90 ,90);  // 设置每个框大小,位置
				Texts[i][j].setOpaque(true);   // 
				Texts[i][j].setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.BLACK));
				MainPanel.add(Texts[i][j]);  // 将每个小块加入到  mainPanel 中
			} // for j
		}// for i
       
		Mark1 = Mark2 = Mark3 = Mark4 = 0;
		setVisible(false);
		EventQueue.invokeLater(new Runnable(){   // 创建一个线程
            public void run(){
                try{
                    My_2048 frame = new My_2048();
                    
                    frame.setVisible(true);
                //  Thread thread = new Thread(frame);
                //  thread.start();
                }
                catch(Exception e1){   // 捕捉异常
                    e1.printStackTrace();
                }
            }
        });
       
    }
    
    public static void main(String[] args) throws InterruptedException {
       
        EventQueue.invokeLater(new Runnable(){   // 创建一个线程:这个方法调用完毕后，它会被销毁
            public void run(){
                try{
                    My_2048 frame = new My_2048();
                    frame.setVisible(true);
                }
                catch(Exception e1){   // 捕捉异常
                    e1.printStackTrace();
                }
            }
        });
    }
 
}
