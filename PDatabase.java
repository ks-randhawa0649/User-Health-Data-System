import java.awt.*;
import java.io.*;
import java.sql.*;
import java.util.*;
import javax.swing.*;
import javax.swing.table.*;
 
class PatientTableFromDatabase extends JFrame
{
	static Connection cn=null;
	Statement st=null;
	ResultSet rs=null;

    PatientTableFromDatabase()
    {
        Vector columnNames = new Vector();
        Vector data = new Vector();

 
        try
        {
            //  Connect to the Database
 
String driver = "sun.jdbc.odbc.JdbcOdbcDriver";
 
//String url = "jdbc:odbc:Teenergy"; 
//if using ODBC Data Source name
//String url = "jdbc:odbc:Driver={Microsoft
//r={Microsoft Access Driver
//(*.mdb)};DBQ=c:/Project/database/patientinfo.mdb";
  //          String userid = "";
   //         String password = "";
 
    //        Class.forName( driver );
//Connection connection = DriverManager.getConnection( url,userid, password );

	try{
	
	Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
	cn=DriverManager.getConnection("Jdbc:Odbc:pat");
	}

	catch(Exception e)
		{
			System.out.println(e);
		}	

 
            //  Read data from a table
 
            String sql = "Select * from PAT";
			Statement stmt = cn.createStatement();
            ResultSet rs = stmt.executeQuery( sql );
            ResultSetMetaData md = rs.getMetaData();
            int columns = md.getColumnCount();
 
            //  Get column names
 
            for (int i = 1; i <= columns; i++)
            {
				columnNames.addElement(md.getColumnName(i));
            }
 
            //  Get row data
 
            while (rs.next())
            {
                Vector row = new Vector(columns);
 
                for (int i = 1; i <= columns; i++)
                {
				row.addElement(rs.getObject(i)); 

                }
 
                data.addElement( row );
            }
 
           // rs.close();
            //stmt.close();
        }
        catch(Exception e)
        {
            System.out.println( e );
        }
 
        //  Create table with database data
 
JTable table = new JTable(data, columnNames);

 
JScrollPane scrollPane = new JScrollPane(table);
        getContentPane().add( scrollPane );
 
        JPanel buttonPanel = new JPanel();
getContentPane().add( buttonPanel, BorderLayout.SOUTH );
    }

 
    public static void main(String[] args)
    {
		PatientTableFromDatabase frame = new PatientTableFromDatabase();
		frame.setDefaultCloseOperation( EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}

