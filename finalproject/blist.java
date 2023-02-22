import java.awt.*;
import java.util.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
import java.awt.event.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class blist extends JFrame implements ActionListener
{
	Container con=this.getContentPane();
	DefaultTableModel dm;
	JTable jt;
	Vector rows,header,row1;

	
	Connection cn;
	String str;
	Statement stmt;
	
	JButton close=new JButton("CLOSE");

	public blist()
	{
		con.setLayout(null);
		con.setBackground(Color.orange);
		rows=new Vector();
		header=new Vector();
		header.addElement("PLANT CODE");
		header.addElement("PLANT NAME");
		header.addElement("DESCRIPTION");
		header.addElement("FERTILIZER");
		header.addElement("PRICE");
		header.addElement("STOCK AVAILABLE");
	
		try
		{
    	Class.forName("org.postgresql.Driver");
		cn=DriverManager.getConnection("jdbc:postgresql://localhost/agri","postgres","1234");
		stmt=cn.createStatement();
		}
		catch(SQLException ae)
		{
			System.out.print("Driver not found");
			ae.printStackTrace();	
		}
		catch(ClassNotFoundException ae)
		{
			System.out.print("Class not found");
				ae.printStackTrace();
		}
		try
		{
			ResultSet rs=stmt.executeQuery("select * from plant");
			while(rs.next())
			{
			   row1=new Vector();
				row1.addElement(rs.getString(1));
				row1.addElement(rs.getString(2));
				row1.addElement(rs.getString(3));
				row1.addElement(rs.getString(4));
				row1.addElement(rs.getString(5));
				row1.addElement(rs.getString(6));
				rows.addElement(row1);
			}
		}
		catch(NullPointerException e)
		{
			System.out.print("Exception is there" +e);
				e.printStackTrace();
		}
		catch(SQLException aw)
		{
			System.out.print("Exception for sql" +aw);
				aw.printStackTrace();
		}
		
		
		dm=new DefaultTableModel(rows,header);
		jt=new JTable(dm);		
		JScrollPane p=new JScrollPane(jt);
		p.setBounds(10,50,700,450);
		con.add(p);
		jt.setRowSelectionAllowed(false);
		jt.setEnabled(false);

		close.setBounds(320,520,100,30);
		con.add(close);
	    close.addActionListener(this);
		close.setToolTipText("Close");
		
		this.setTitle("**  PLANT STOCK  **");
		
		setSize(800,580);
		setVisible(true);
	}
	
	public static void main(String args[])
	{
		blist bl=new blist();
		bl.setSize(800,580);
		bl.setVisible(true);
	}


	public void actionPerformed(ActionEvent e)
	{
		String title=e.getActionCommand();
		if(title=="CLOSE")
		{
			dispose();
		}
		
	
	}
}
