//Boris Jurosevic
//Final CS 1410
//System Contact Management
//Date: December 2, 2012

import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;



public class ContactSystem extends JFrame{
	//Specify the size of five string fields in the record
	final static int NAME_SIZE = 32;
	final static int TYPE_SIZE = 32;
	final static int CITY_SIZE = 20;
	final static int PHONE_SIZE = 15;
	final static int EMAIL_SIZE = 15;
	final static int RECORD_SIZE =
			(NAME_SIZE + TYPE_SIZE + CITY_SIZE + PHONE_SIZE + EMAIL_SIZE);

	//access contact.dat using RandomAccessFile
	private RandomAccessFile raf;
	
	//Text Fields
	private JTextField jbtName = new JTextField(NAME_SIZE);
	private JTextField jbtType = new JTextField(TYPE_SIZE);
	private JTextField jbtCity = new JTextField(CITY_SIZE);
	private JTextField jbtPhone = new JTextField(PHONE_SIZE);
	private JTextField jbtEmail = new JTextField(EMAIL_SIZE);
	
	//Buttons
	private JButton jbtAdd = new JButton("Add");
	private JButton jbtFirst = new JButton("First");
	private JButton jbtNext = new JButton("Next");
	private JButton jbtPrevious = new JButton("Previous");
	private JButton jbtLast = new JButton("Last");
	
	public ContactSystem(){
		//open or create a random access file
		
		try {
			raf = new RandomAccessFile("contact.txt", "rw");
		}
		catch(IOException ex) {
			System.out.print("Error: " + ex);
			System.exit(0);
		}
		
		//Panel p1 for holding labels Name , Type, Email or phone
		JPanel p1 = new JPanel();
		p1.setLayout(new GridLayout(3,1));
		p1.add(new JLabel("Name Contact"));
		p1.add(new JLabel("Email Address"));
		p1.add(new JLabel("Type of Contact"));
		
		
		//Panel jpPhone for holding Phone
		JPanel jpPhone = new JPanel();
		jpPhone.setLayout(new BorderLayout());
		jpPhone.add(new JLabel("Phone"), BorderLayout.WEST);
		jpPhone.add(jbtPhone, BorderLayout.CENTER);
		
		//Panel jpEmail for holding Phone
		JPanel jpEmail = new JPanel();
		jpEmail.setLayout(new BorderLayout());
		jpEmail.add(new JLabel("Phone"), BorderLayout.WEST);
		jpEmail.add(jbtEmail, BorderLayout.CENTER);
		
		// Panel p2 for holding jpPhone and jpEmail
		JPanel p2 = new JPanel();
		p2.setLayout(new BorderLayout());
		p2.add(jpPhone, BorderLayout.WEST);
		p2.add(jpPhone, BorderLayout.CENTER);
		
		JPanel p3 = new JPanel();
		p3.setLayout(new BorderLayout());
		p3.add(jbtCity, BorderLayout.CENTER);
		p3.add(p2, BorderLayout.EAST);
		
		//panel p4 for holding jtfName, jtfType, and p3
		JPanel p4 = new JPanel();
		p4.setLayout(new GridLayout(3,1));
		p4.add(jbtName);
		p4.add(jbtType);
		p4.add(p3);
		
		//place p1 and p4 into jpContact
		JPanel jpContact = new JPanel(new BorderLayout());
		jpContact.add(p1, BorderLayout.WEST);
		jpContact.add(p4, BorderLayout.CENTER);
		
		//set panel with line border
		jpContact.setBorder(new BevelBorder(BevelBorder.RAISED));
		
		//add buttons to a panel
		JPanel jpButton = new JPanel();
		jpButton.add(jbtAdd);
		jpButton.add(jbtFirst);
		jpButton.add(jbtNext);
		jpButton.add(jbtPrevious);
		jpButton.add(jbtLast);
		
		//add jpContact and jpButton to the frame
		
		add(jpContact, BorderLayout.CENTER);
		add(jpButton, BorderLayout.SOUTH);
		
		jbtAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				writeContact();
				
			}
		});
		jbtFirst.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (raf.length() > 0) readContact(0);
				}
				catch(IOException ex){
					ex.printStackTrace();
				}
			}
		});
		jbtNext.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				try {
					long currentPosition = raf.getFilePointer();
					if (currentPosition < raf.length())
						readContact(currentPosition);
					
				}
				catch (IOException ex) {
					ex.printStackTrace();
				}
			}
		});
		
		jbtPrevious.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					long currentPosition = raf.getFilePointer();
					if(currentPosition - 2 * RECORD_SIZE > 0)
						//why 2 * 2 * RECORD_SIZE? see the the follow-up remarks
						readContact(currentPosition - 2 * 2 * RECORD_SIZE);
					else
						readContact(0);
					
				}
				catch(IOException ex){
					ex.printStackTrace();
				}
			}
		});
		
		jbtLast.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					long lastPosition = raf.length();
					if(lastPosition > 0)
						//why 2 * RECORD_SIZE? see the follow up remarks
						readContact(lastPosition - 2 * RECORD_SIZE);
					
				}
				catch (IOException ex) {
					ex.printStackTrace();
				}
			}
		});
		
		try {
			if (raf.length() > 0) readContact(0);
			
		}
		
		catch (IOException ex) {
			ex.printStackTrace();
			
		}
		
		
	}
	
	//write a record at the end of file
	
	public void writeContact() {
		
		try {
			raf.seek(raf.length());
			FixedLengthStringIO.writeFixedLengthString(
					jbtName.getText(), NAME_SIZE, raf);
			FixedLengthStringIO.writeFixedLengthString(
					jbtType.getText(), TYPE_SIZE, raf);
			FixedLengthStringIO.writeFixedLengthString(
					jbtCity.getText(), CITY_SIZE, raf);
			FixedLengthStringIO.writeFixedLengthString(
					jbtPhone.getText(), PHONE_SIZE, raf);
			FixedLengthStringIO.writeFixedLengthString(
					jbtEmail.getText(), EMAIL_SIZE, raf);
			
		}
		catch (IOException ex) {
			ex.printStackTrace();
		}
	}
	
	 //read a contact at the specific position
    public void readContact(long position) throws IOException{
	 raf.seek(position);
	 String name = FixedLengthStringIO.readFixedLengthString(
			NAME_SIZE, raf);
	 String type = FixedLengthStringIO.readFixedLengthString(
			TYPE_SIZE, raf);
	 String city = FixedLengthStringIO.readFixedLengthString(
			CITY_SIZE, raf);
	 String phone = FixedLengthStringIO.readFixedLengthString(
			PHONE_SIZE, raf);
	 String email = FixedLengthStringIO.readFixedLengthString(
			EMAIL_SIZE, raf);

	  jbtName.setText(name);
	  jbtType.setText(type);
	  jbtCity.setText(city);
	  jbtPhone.setText(phone);
	  jbtEmail.setText(email);
}	

	
	 /**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		ContactSystem frame = new ContactSystem();
		frame.pack();
		frame.setTitle("Contact System");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);

	}

}






