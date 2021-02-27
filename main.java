import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class  main extends JFrame implements ActionListener
{
	    static JFrame frame;
    private String username;
    private String password;
    private static JFrame loginFrame;
    private static JPanel panel1;
    private static JPanel panel2;
    private static JPanel panel3;
    private JButton loginBtn;
    private JButton exitBtn;
    int dialogtype = JOptionPane.PLAIN_MESSAGE;
    String dialogmessage;
    String dialogs;
    private JLabel nameLbl;
    private JLabel userLbl;
    private JLabel passwordLbl;
    private static JTextField userTxt;
   // private static JTextField passwordTxt;
    private static JPasswordField passwordTxt;
    public String loginname;
    public String loginpass;
    
    // class Veriables
   // clsConnection connect = new clsConnection();
    //Connection variable
    
   //     Connection conn;  //for connecting database
        
    	Dimension screen 	= 	Toolkit.getDefaultToolkit().getScreenSize();
    
  //  static Date td 			= new Date();

	main()
	{

		
		panel1 = new JPanel();
   panel1.setLayout(new FlowLayout());
   nameLbl = new JLabel("Hospital Managment ");
    
   panel2 = new JPanel();
   panel2.setLayout(new GridLayout(2,2));
   userLbl = new JLabel("Username :");
   userTxt = new JTextField(20);
  
   passwordLbl = new JLabel("Password :");
//   passwordTxt = new JTextField(20);
   passwordTxt = new JPasswordField(20);
   // passwordTxt.setEchoChar('?');
   
   panel3 = new JPanel();
   panel3.setLayout(new FlowLayout());
   
   loginBtn = new JButton("Login", new ImageIcon("images/key.gif"));
   
   loginBtn.addActionListener(this);
   exitBtn = new JButton("Exit", new ImageIcon("images/Keys.gif"));
   
   exitBtn.addActionListener(this);
	panel1.add(nameLbl);
	panel1.setOpaque(true);
    panel2.add(userLbl);
	panel2.add(userTxt);
	panel2.add(passwordLbl);
	panel2.add(passwordTxt);
	panel2.setOpaque(true);
   	panel3.add(loginBtn);
	panel3.add(exitBtn);
	panel3.setOpaque(true);
	frame = new JFrame("Hospital Managment");
       frame.setSize(300,200);
        
	Container pane = frame.getContentPane();   
	pane.setLayout(new BoxLayout(pane, BoxLayout.Y_AXIS));
    //pane.setLayout(new GridLayout(3,1));
	pane.add(panel1);
	pane.add(panel2);
	pane.add(panel3);
	frame.setLocation((screen.width - 500)/2,((screen.height-350)/2));	
    frame.setVisible(true);
    frame.addWindowListener(new WindowAdapter()
        {
            public void windowClosing(WindowEvent e)
            {
                System.exit(0);
            }
        });

	}

//	public void actionPerformed(ActionEvent ae)
//	{}

//	class submit implements ActionListener
	//	{
			public void actionPerformed(ActionEvent event)
				{
				/*	String user,pass;
					user=tfuser.getText();
					pass=tfpass.getText();
					if(user.equals("student") && pass.equals("student"))
					{
						new studstart();
						setVisible(false);

					}*/
					
					Object source = event.getSource(); 
        if(source.equals(loginBtn))
        {
           //login();
           String loginname,loginpass;
           loginname = userTxt.getText().trim();
           	loginpass = passwordTxt.getText().trim();
           	 if(loginname.equals("s") && loginpass.equals("s"))
					{
					dialogmessage = "Welcome - " +loginname;
                    dialogtype = JOptionPane.INFORMATION_MESSAGE;
                    JOptionPane.showMessageDialog((Component)null, dialogmessage, dialogs, dialogtype);
                    userTxt.setText("");
						new start();
						setVisible(false);
						 frame.dispose();

					}
					else{
						JOptionPane.showMessageDialog(null,"Invaild User name and password" , "WARNING!!!",JOptionPane.INFORMATION_MESSAGE);
						userTxt.setText("");
                        passwordTxt.setText("");
					}
						
           
        } 
        else if(source.equals(exitBtn))
        {
            		System.exit(0);
        }
				}
	//	}
	
/*	public void login()
        {
        	loginname = userTxt.getText().trim();
           	loginpass = passwordTxt.getText().trim();

                  try{
                           if(userTxt.equals("admin") && passwordTxt.equals("admin"))
					{
						new studstart();
						setVisible(false);

					}
                  }
                  catch(Exception ex)
          {
          	JOptionPane.showMessageDialog(null,"GENERAL EXCEPTION" , "WARNING!!!",JOptionPane.INFORMATION_MESSAGE);
          	}*/	

/*	class exit implements ActionListener
		{
			public void actionPerformed(ActionEvent ae)
				{
					System.exit(0);

				}
		}*/

      //  }
      
	public static void main(String[] args)
	{
		new main();
	}
}
