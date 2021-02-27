import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.*;
import javax.swing.*;
import java.text.*;


class Billing extends JFrame implements ActionListener
{	
	
	static Connection cn=null;
	Statement st=null;
	ResultSet rs=null;
	


	JLabel lmain,lpname,lpno,ldad,lddis,lrt,ltamt,temp;
	JTextField tfname,tfno,tfdateadd,tfrtype,tftamt;
	JButton bsub,bclr,bback;
	

	Billing()
	{
		super("Billing Information");
		setSize(1024,768);
		setVisible(true);
		setLayout(null);
		
		lmain=new JLabel("Billing Information");
		lmain.setBounds(440,35,107,15);
		add(lmain);

		lpname=new JLabel("Patient Name :");
		lpname.setBounds(104,97,85,25);
		add(lpname);

		tfname=new JTextField(30);
		tfname.setBounds(230,100,225,20);
		add(tfname);

		lpno=new JLabel("Patient No. :");
		lpno.setBounds(570,97,70,25);
		add(lpno);

		tfno=new JTextField(30);
		tfno.setBounds(694,100,70,20);
		add(tfno);

		ldad=new JLabel("Date of Admission :");
		ldad.setBounds(104,175,120,25);
		add(ldad);

		tfdateadd=new JTextField(20);
		tfdateadd.setBounds(230,178,80,20);
		add(tfdateadd);
	
		lddis=new JLabel("Date of Discharge :");
		lddis.setBounds(570,175,120,25);
		add(lddis);
		
		lrt=new JLabel("Room Type :");
		lrt.setBounds(104,242,70,25);
		add(lrt);

		tfrtype=new JTextField(20);
		tfrtype.setBounds(230,242,80,20);
		add(tfrtype);

		ltamt=new JLabel("Total Amount :");
		ltamt.setBounds(104,380,85,25);
		add(ltamt);

		tftamt=new JTextField(20);
		tftamt.setBounds(230,380,120,20);
		add(tftamt);
		

		bsub=new JButton("SEARCH" ,new ImageIcon("images/setting.png"));
		bsub.setBounds(300,643,110,30);
		add(bsub);	

		bclr=new JButton("CLEAR",new ImageIcon("images/LOGGOFF.PNG"));
		bclr.setBounds(470,643,100,30);
		add(bclr);

		bback=new JButton("BACK",new ImageIcon("images/restore.png"));
		bback.setBounds(580,643,100,30);
		add(bback);


	try{
		Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
		cn=DriverManager.getConnection("Jdbc:Odbc:pat");
	   }

	catch(Exception e)
		{
			System.out.println(e);
		}	


		bclr.addActionListener(new clear());
		bsub.addActionListener(new submit());
		bback.addActionListener(new back());


		try
		{
		Calendar cal=Calendar.getInstance();
		SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
		df.setLenient(false);  
		System.out.println(df.format(cal.getTime()));
		String dd1=df.format(cal.getTime());
		

		temp=new JLabel(dd1);
		temp.setBounds(694,178,80,20);
		add(temp);

	
		}
		catch (Exception e)
			{
				new ErrorDialog2();
			}

		




		}

		
		
		public void actionPerformed(ActionEvent ae)
		{}
	
	

	class clear implements ActionListener
	{
		public void actionPerformed(ActionEvent ae)
			{
				tfname.setText("");
				tfno.setText("");
				tfdateadd.setText("");
				tfrtype.setText("");
				tftamt.setText("");
			}
	}



	class back implements ActionListener
	{
		public void actionPerformed(ActionEvent ae)
		{
			new start();
			setVisible(false);
		}
	}


class submit extends Frame implements ActionListener
{
	public void actionPerformed(ActionEvent ae)
	{	
		try
		{
			Integer no,num=Integer.parseInt(tfno.getText());
			String name,room,dateadd,rtype;

			Statement st=cn.createStatement();
			ResultSet rs=st.executeQuery("SELECT * FROM PAT WHERE patientno="+num);

			if(rs.next())
			{

				no=rs.getInt("patientno");
				name=rs.getString("name");
				dateadd=rs.getString("dateadd");
				System.out.println(dateadd);
				//d=rs.getString("dateadd");
				
				rtype=rs.getString("rtype");
								
				tfname.setText(name);
				tfdateadd.setText(dateadd);
				tfrtype.setText(rtype);					

			}




			try
			{
				Calendar cal=Calendar.getInstance();
				SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
				df.setLenient(false);  

				int dnow=cal.get(Calendar.DAY_OF_MONTH);
				int mnow=cal.get(Calendar.MONTH);
				int ynow=cal.get(Calendar.YEAR);
				int	mnowF=mnow+1;
				//cal.setTime(df.parse(tfdateadd.getText()));


				Calendar cal1=Calendar.getInstance();
				cal1.setTime(df.parse(tfdateadd.getText()));
				


				SimpleDateFormat sf2=new SimpleDateFormat("yyyy,MM,dd");
				sf2.setLenient(false);  





		



				int daddd=cal1.get(Calendar.DAY_OF_MONTH);
				int daddMM=cal1.get(Calendar.MONTH);
				int daddYY=cal1.get(Calendar.YEAR);
				int daddMMF=daddMM+1;


			


			
				long from = new java.util.GregorianCalendar(ynow,mnowF,dnow).getTime().getTime(); 
				long to = new java.util.GregorianCalendar(daddYY,daddMMF,daddd).getTime().getTime();
				double difference = from-to;


				long days = Math.round((difference/(1000*60*60*24)));

				long bill=0;

				String rt=tfrtype.getText();


				if(rt.equals("Deluxe"))
				{
					System.out.println(tfrtype.getText());
					int m=2000;
					System.out.println(m);			
					bill=days*m;
					System.out.println("total  bill = "+bill);
				}
				if(rt.equals("Private"))
				{
					int m=800;
					System.out.println(m);			
					bill=days*m;
					System.out.println("total  bill = "+bill);
				}
				if(rt.equals("Semi-Private"))
				{
					int m=600;
					System.out.println(m);			
					bill=days*m;
					System.out.println("total  bill = "+bill);
				}
				if(rt.equals("General"))
				{
					int m=400;
					System.out.println(m);			
					bill=days*m;
					System.out.println("total  bill = "+bill);
				}

				

				//Final Bill
				String FinalBill=(new Long(bill)).toString();
				tftamt.setText(FinalBill);


		
			}

		catch (Exception e)
			{
				//new ErrorDialog2();
				System.out.println(e);
			}



		}
		catch (SQLException sq)
		{
			System.out.println(sq);
		}



	}
}

	public static void main(String[] args) 
	{
		new Billing();
	}
}



