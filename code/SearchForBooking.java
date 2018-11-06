import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Iterator;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
/**
 * A user interface that allows  displaing and modifying information about bookings
 * @author Hristo Getov, Jeppe Jensen
 *@version 1.0
 */
public class SearchForBooking extends JFrame { 
   
   private GregorianCalendar currentDate = new GregorianCalendar();
   private int cd = currentDate.get(GregorianCalendar.DATE);
   private int cm = currentDate.get(GregorianCalendar.MONTH)+1;
   private int cy = currentDate.get(GregorianCalendar.YEAR);
   private MyDate today = new MyDate(cd,cm,cy);
   private String stoday = stringMyDate(today);
   
   //hold available rooms from 'checkavailab' action. To share with other actions.
   private ArrayList<ArrayList<Room>> availableRooms;
   
   
   //holds added rooms
   private Booking active; //holds active booking
   
   private ArrayList<Booking> sr; //hold booking search results
   private ArrayList<Integer> srIndex; //holds index of booking search results
   private int srActive; //holds index of active search result.
   
   private int currentGuestIndex = -1; //saves the index of the active guest within a booking. For saving active index, when changing to another guest in the same booking. Initially -1, since no guest is selected.
   
   private Hotel h;
   
   private FileAdapter fa;
   
   private MyButtonListener buttonListener;
   
   private JPanel mainpanel;
   private JPanel mainpanel2;
   private JPanel mainpanel3;

   private JPanel panel;
   private JTextField field1;
   private JLabel label1;

   private JPanel panel2;
   private JLabel label2;
   private JTextField field2;

   private JPanel roomboxpanel;
   private JComboBox<String> roomBox;
   private JLabel roomBoxLabel;

   private JPanel guestdetail;
   private JLabel guestdetails;

   private JPanel guestboxpanel;
   private JComboBox<String> guestbox;
   private JButton addButton;

   private JPanel newsearch;
   private JButton clear;

   private JPanel name;
   private JTextField namefield;
   private JLabel namelabel;

   private JPanel birthday;
   private JTextField birthdayfield;
   private JLabel birthdaylabel;

   private JPanel address;
   private JLabel Addressla;

   private JPanel street;
   private JTextField streetfield;
   private JLabel streetlabel;

   private JPanel streetnm;
   private JTextField streetnmfield;
   private JLabel streetnmlabel;

   private JPanel city;
   private JTextField cityfield;
   private JLabel citylabel;

   private JPanel zipcode;
   private JTextField zipcodefield;
   private JLabel zipcodelabel;

   private JPanel nationality;
   private JTextField nationalityfield;
   private JLabel nationalitylabel;

   private JPanel phone;
   private JTextField phonefield;
   private JLabel phonelabel;
   

   private JPanel availablerooms;
   private JComboBox<String> availableroombox;
   private JLabel availabllable;

   private JPanel roomnumbers;
   private JComboBox<String> roomnumbersbox;
   private JLabel roomnumlabel;

   private JPanel fake;
   private JLabel searchedresaults;
   private JComboBox<String> booklistbox;

   private JButton checkavailab;
   private JButton searchforbooking;
   private JButton addroom;
   private JButton checkin;
   private JButton checkout;
   private JButton savebooking;
   private JPanel addedroompa;
   private JLabel addedroomla;
   private JComboBox<String> addedroom;
   
   private Daily_Leager daily;
   private JPanel mainbigpanel;
   private JPanel panel1;
   private JButton delete;
   private JTabbedPane tab;
   private JPanel bookingnamepane;
   private JLabel bookingnamela;
   private JTextField bookingname;
   private JButton removeroom;
   private JButton removeguest;
   
   private JPanel textpane;
   private JTextArea text;
   
   private JPanel extrabed;
   private JLabel extrabedla;
   private JTextField extratext;
   
   private JPanel discount;
   private JLabel discountla;
   private JTextField discounttext;
   private JLabel dateformat1;
   private JLabel dateformat2;
   private JLabel dateformate3;
    
   /**
    * No-argument constructor initializing GUI components
    */
   
   public SearchForBooking()  {
      super("Search for Booking");
      //listener = new MyButtonListener();
      
      Font font=new Font("Aqua",Font.ITALIC,13);
   

      fa = new FileAdapter("hotelfile.bin");
      
      h = fa.readFromFile();  //loads hotel from file. After every change of object, save to file, followed by read from file.
      
      buttonListener = new MyButtonListener();
      
      //initially no search results.
      active = new Booking();
      srActive = -1;
      
      //Search results. Indexes from hotel booking list.
      sr = new ArrayList<Booking>();
      srIndex = new ArrayList<Integer>();
      
      //convert current day to string
      //Convert to string
      String scd = Integer.toString(cd);
      scd = String.format("%2s", scd).replace(" ", "0"); //Length 2. Filled with zeros in front
      String scm = Integer.toString(cm);
      scm = String.format("%2s", scm).replace(" ", "0");
      String scy = Integer.toString(cy);
      scy = String.format("%4s", scy).replace(" ", "0"); //Length 4.
      
      //departure date - tomorrow default by setting
      MyDate tomorrow =  new MyDate(cd,cm,cy);
      tomorrow.nextDay();
      String stomorrow = stringMyDate(tomorrow);
      
      //Arrival Date
      panel1 = new JPanel();
      panel1.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
      panel1.setPreferredSize(new Dimension(300, 35));
      panel1.setBackground(Color.GRAY);
      field1 = new JTextField(10);
      field1.setPreferredSize(new Dimension(150,25));
      field1.setText(stoday);
      label1 = new JLabel("Arrival Date  ");
      dateformat1 = new JLabel("(dd/mm/yyyy)");
      dateformat1.setFont(font);
      dateformat1.setForeground(new Color(255,0,0));
      panel1.add(field1);
      panel1.add(label1);
      panel1.add(dateformat1);

      //Departure date
      panel2 = new JPanel();
      panel2.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 2));
      panel2.setPreferredSize(new Dimension(300, 35));
      panel2.setBackground(Color.GRAY);
      field2 = new JTextField(10);
      field2.setPreferredSize(new Dimension(150,25));
      field2.setText(stomorrow);
      label2 = new JLabel("Departure Date ");
      dateformat2 = new JLabel("(dd/mm/yyyy)");
      dateformat2.setFont(font);
      dateformat2.setForeground(new Color(255,0,0));
      panel2.add(field2);
      panel2.add(label2);
      panel2.add(dateformat2);

      //Available room types
      roomboxpanel = new JPanel();
      roomboxpanel.setLayout(new FlowLayout(FlowLayout.LEFT));
      roomboxpanel.setPreferredSize(new Dimension(180, 75));
      roomboxpanel.setBackground(Color.GRAY);
      roomBox = new JComboBox<String>();
      roomBox.setPreferredSize(new Dimension(130, 25));
      roomBoxLabel = new JLabel("Avilable room types");
      roomboxpanel.add(roomBoxLabel);
      roomboxpanel.add(roomBox);

      guestdetail = new JPanel();
      guestdetail.setLayout(new FlowLayout(FlowLayout.LEFT));
      guestdetail.setPreferredSize(new Dimension(180, 25));
      guestdetail.setBackground(Color.GRAY);
      guestdetails = new JLabel("Personal details>");
      guestdetail.add(guestdetails);
      
      //add guest button booking guest list
      guestboxpanel = new JPanel();
      guestboxpanel.setLayout(new FlowLayout(FlowLayout.LEFT));
      guestboxpanel.setPreferredSize(new Dimension(330, 35));
      guestboxpanel.setBackground(Color.GRAY);
      guestbox = new JComboBox<String>();
      guestbox.setPreferredSize(new Dimension(150, 25));
      guestbox.addActionListener(buttonListener);
      //guestbox.addItem("Booking guest list");
      addButton = new JButton("Add Guest");
      addButton.addActionListener(buttonListener);
      guestboxpanel.add(guestbox);
      guestboxpanel.add(addButton);
      
      //maybe irrelevant since already shown in  Booking list combo box
      bookingnamepane = new JPanel();
      bookingnamepane.setLayout(new FlowLayout(FlowLayout.CENTER));
      bookingnamepane.setPreferredSize(new Dimension(230,65));
      bookingnamepane.setBackground(Color.GRAY);
      bookingnamela = new JLabel("Country");
      bookingnamela.setPreferredSize(new Dimension(160,25));
      bookingname=new JTextField(15);
      bookingname.setPreferredSize(new Dimension(230,25));
      bookingnamepane.add(bookingnamela);
      bookingnamepane.add(bookingname);      
      
      //New search - Should clear all displayed fields and boxes
      newsearch = new JPanel();
      newsearch.setLayout(new FlowLayout(FlowLayout.CENTER));
      newsearch.setPreferredSize(new Dimension(180, 50));
      newsearch.setBackground(Color.GRAY);
      clear = new JButton("New Search/Booking");
      clear.addActionListener(buttonListener);
      newsearch.add(clear);

      //Name
      name = new JPanel();
      name.setLayout(new FlowLayout(FlowLayout.LEFT));
      name.setPreferredSize(new Dimension(230, 50));
      name.setBackground(Color.GRAY);
      namefield = new JTextField(20);
      namefield.setPreferredSize(new Dimension(230,25));
      namelabel = new JLabel("Name");
      name.add(namelabel);
      name.add(namefield);
      
      //Birthday
      birthday = new JPanel();
      birthday.setLayout(new FlowLayout(FlowLayout.LEFT));
      birthday.setPreferredSize(new Dimension(150, 50));
      birthday.setBackground(Color.GRAY);
      birthdayfield = new JTextField(12);
      birthdayfield.setPreferredSize(new Dimension(150,25));
      birthdaylabel = new JLabel("Birthday");
      dateformate3 = new JLabel("(dd/mm/yyyy)");
      dateformate3.setFont(font);
      dateformate3.setForeground(new Color(255,0,0));
      birthday.add(birthdaylabel);
      birthday.add(dateformate3);
      birthday.add(birthdayfield);
      
      
      //nationality
      nationality = new JPanel();
      nationality.setLayout(new FlowLayout(FlowLayout.LEFT));
      nationality.setPreferredSize(new Dimension(150, 50));
      nationality.setBackground(Color.GRAY);
      nationalitylabel = new JLabel("Nationality:");
      nationalityfield = new JTextField(12);
      nationalityfield.setPreferredSize(new Dimension(150,25));
      nationality.add(nationalitylabel);
      nationality.add(nationalityfield);
      
      //phone number
      phone = new JPanel();
      phone.setLayout(new FlowLayout(FlowLayout.LEFT));
      phone.setPreferredSize(new Dimension(210, 50));
      phone.setBackground(Color.GRAY);
      phonefield = new JTextField(12);
      phonefield.setPreferredSize(new Dimension(150,25));
      phonelabel = new JLabel("Phone number:");

      phone.add(phonelabel);
      phone.add(phonefield);
      
      // Remove guest from Booking
      removeguest = new JButton("Remove Guest");
      removeguest.setPreferredSize(new Dimension(120,25));
      removeguest.addActionListener(buttonListener);
      
      // Text field showing the calculated price, confirmation messages like: Check-in / check-out completed. After pressing check-in / check-out button... 
      textpane = new JPanel();
      textpane.setLayout(new FlowLayout(FlowLayout.LEFT));
      textpane.setBackground(Color.GRAY);
      text = new JTextArea(20,50);
      textpane.add(text);

      //Booking list
      fake = new JPanel();
      fake.setPreferredSize(new Dimension(200, 63));
      fake.setBackground(Color.GRAY);
      searchedresaults = new JLabel("Bookings List");
      booklistbox = new JComboBox<String>();
      booklistbox.setPreferredSize(new Dimension(150, 25));
      booklistbox.addActionListener(buttonListener);
      fake.add(searchedresaults);
      fake.add(booklistbox);
      
      
      
      address = new JPanel();
      address.setLayout(new FlowLayout(FlowLayout.CENTER));
      address.setPreferredSize(new Dimension(220, 25));
      address.setBackground(Color.GRAY);
      Addressla = new JLabel(" Personal Address:");
      address.add(Addressla);

      //Street name
      street = new JPanel();
      street.setLayout(new FlowLayout(FlowLayout.LEFT));
      street.setPreferredSize(new Dimension(160, 50));
      street.setBackground(Color.GRAY);
      streetfield = new JTextField(12);
      streetfield.setPreferredSize(new Dimension(150,25));
      streetlabel = new JLabel("Street name:");
      street.add(streetlabel);
      street.add(streetfield);

      //House number
      streetnm = new JPanel();
      streetnm.setLayout(new FlowLayout(FlowLayout.LEFT));
      streetnm.setPreferredSize(new Dimension(160, 50));
      streetnm.setBackground(Color.GRAY);
      streetnmfield = new JTextField(8);
      streetnmfield.setPreferredSize(new Dimension(150,25));
      streetnmlabel = new JLabel("Street number:");
      streetnm.add(streetnmlabel);
      streetnm.add(streetnmfield);

      //city
      city = new JPanel();
      city.setLayout(new FlowLayout(FlowLayout.LEFT));
      city.setPreferredSize(new Dimension(160, 50));
      city.setBackground(Color.GRAY);
      citylabel = new JLabel("City");
      cityfield = new JTextField(12);
      cityfield.setPreferredSize(new Dimension(150,25));
      city.add(citylabel);
      city.add(cityfield);

      //Zip code
      zipcode = new JPanel();
      zipcode.setLayout(new FlowLayout(FlowLayout.LEFT));
      zipcode.setPreferredSize(new Dimension(160, 50));
      zipcode.setBackground(Color.GRAY);
      zipcodelabel = new JLabel("postal code");
      zipcodefield = new JTextField(12);
      zipcodefield.setPreferredSize(new Dimension(150,25));
      zipcode.add(zipcodelabel);
      zipcode.add(zipcodefield);

      //Buttons:
      
      //check availability
      checkavailab = new JButton("Check Availability");
      checkavailab.setPreferredSize(new Dimension(200,25));
      checkavailab.addActionListener(buttonListener);
      
      //Search for booking
      searchforbooking = new JButton("Search for booking");
      searchforbooking.setPreferredSize(new Dimension(200,25));
      searchforbooking.addActionListener(buttonListener);
      
      //Delete Booking
      delete=new JButton("Delete Booking");
      delete.setPreferredSize(new Dimension(200,25));
      delete.addActionListener(buttonListener);
      
      //Check in
      checkin = new JButton("Check-In");
      checkin.setPreferredSize(new Dimension(200,25));
      checkin.addActionListener(buttonListener);
      
      //check out
      checkout= new JButton("Check-Out");
      checkout.setPreferredSize(new Dimension(200,25));
      checkout.addActionListener(buttonListener);
      
      //save Booking
      savebooking= new JButton("Make/Change Booking");
      savebooking.setPreferredSize(new Dimension(200,25));
      savebooking.addActionListener(buttonListener);
      
      //combo box
      //Available room types
      availablerooms = new JPanel();
      availablerooms.setLayout(new FlowLayout(FlowLayout.RIGHT));
      availablerooms.setPreferredSize(new Dimension(300,55));
      availablerooms.setBackground(Color.GRAY);
      availabllable = new JLabel("Available Room Types");
      availableroombox = new JComboBox<String>();
      availableroombox.addActionListener(buttonListener);
      availableroombox.setPreferredSize(new Dimension(200,25));
      availablerooms.add(availabllable);
      availablerooms.add(availableroombox);
      
      //Available room numbers
      roomnumbers = new JPanel();
      roomnumbers.setLayout(new FlowLayout(FlowLayout.RIGHT));
      roomnumbers.setPreferredSize(new Dimension(300,55));
      roomnumbers.setBackground(Color.GRAY);
      roomnumlabel = new JLabel("Available Room numbers");
      roomnumbersbox = new JComboBox<String>();
      roomnumbersbox.setPreferredSize(new Dimension(200,25));
      roomnumbers.add(roomnumlabel);
      roomnumbers.add(roomnumbersbox);
      
      //Button - Add selected room to booking
      addroom = new JButton("Add Room");
      addroom.setPreferredSize(new Dimension(200,25));
      addroom.addActionListener(buttonListener);
      
      //Button - Remove selected room from booking
      removeroom = new JButton("Remove Room");
      removeroom.setPreferredSize(new Dimension(200,25));
      removeroom.addActionListener(buttonListener);
      
      
      //Current rooms added to booking
      addedroompa = new JPanel();
      addedroompa.setLayout(new FlowLayout(FlowLayout.RIGHT));
      addedroompa.setPreferredSize(new Dimension(300,55));
      addedroompa.setBackground(Color.GRAY);
      addedroomla = new JLabel("Added room to booking");
      addedroom=new JComboBox<String>();
      addedroom.setPreferredSize(new Dimension(200,30));
      addedroompa.add(addedroomla);
      addedroompa.add(addedroom);
      
      // field for adding extra bed
      extrabed = new JPanel();
      extrabed.setLayout(new FlowLayout(FlowLayout.CENTER));
      extrabed.setPreferredSize(new Dimension(200,50));
      extrabed.setBackground(Color.GRAY);
      extrabedla = new JLabel("Extra Beds");
      extrabedla.setPreferredSize(new Dimension(100,15));
      extratext = new JTextField(15);
      extratext.setPreferredSize(new Dimension(150,25));
      extrabed.add(extrabedla);
      extrabed.add(extratext);
      
      //field for adding discount
      discount = new JPanel();
      discount.setLayout(new FlowLayout(FlowLayout.CENTER));
      discount.setPreferredSize(new Dimension(200,50));
      discount.setBackground(Color.GRAY);
      discountla = new JLabel("Discount (%)");
      discountla.setPreferredSize(new Dimension(100,15));
      discounttext = new JTextField(15);
      discounttext.setPreferredSize(new Dimension(150,25));
      discount.add(discountla);
      discount.add(discounttext);
      
      
      mainbigpanel =new JPanel();
      mainbigpanel.setBackground(Color.gray);
      
      tab = new JTabbedPane();
      
      
      
      
      //Main column panels
      //column 1
      mainpanel = new JPanel();
      mainpanel.setLayout(new FlowLayout(FlowLayout.LEFT));
      mainpanel.setPreferredSize(new Dimension(300, 600));
      mainpanel.setBackground(Color.GRAY);
      mainpanel.add(panel1);
      mainpanel.add(panel2);
      mainpanel.add(guestdetail);
      mainpanel.add(guestboxpanel);
      mainpanel.add(name);
      mainpanel.add(birthday);
      mainpanel.add(nationality);
      mainpanel.add(phone);
      mainpanel.add(removeguest);
      mainpanel.add(textpane);

      //column 2
      mainpanel2 = new JPanel();
      mainpanel2.setLayout(new FlowLayout(FlowLayout.CENTER));
      mainpanel2.setPreferredSize(new Dimension(200, 480));
      mainpanel2.setBackground(Color.GRAY);
      mainpanel2.add(fake);
       mainpanel2.add(address);
       mainpanel2.add(city);
      mainpanel2.add(street);
      mainpanel2.add(streetnm);  
      mainpanel2.add(zipcode);
      mainpanel2.add(bookingnamepane);
      mainpanel2.add(newsearch);

      //column 3
      mainpanel3 = new JPanel();
      mainpanel3.setLayout(new FlowLayout(FlowLayout.RIGHT));
      mainpanel3.setPreferredSize(new Dimension(370, 600));
      mainpanel3.setBackground(Color.GRAY);
      mainpanel3.add(checkavailab);
      mainpanel3.add(searchforbooking);
      mainpanel3.add(delete);
      //searchforbooking.addActionListener(listener);
      mainpanel3.add(checkin);
      mainpanel3.add(checkout);
      mainpanel3.add(savebooking);
      mainpanel3.add(availablerooms);
      mainpanel3.add(roomnumbers);  
      mainpanel3.add(addroom);
      mainpanel3.add(removeroom);
      mainpanel3.add(addedroompa);
      mainpanel3.add(extrabed);
      mainpanel3.add(discount);
       
      //Main panel
      mainbigpanel.add(mainpanel, BorderLayout.WEST);
      mainbigpanel.add(mainpanel2,BorderLayout.CENTER);
      mainbigpanel.add(mainpanel3,BorderLayout.EAST);

      
      tab.add("Search For Booking", mainbigpanel);
      tab.add("Daily Ledger", new Daily_Leager().getPanel());
      
      add(tab);
      
      setSize(1000, 720);
      setVisible(true);
      //setResizable(false);

      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      setLocationRelativeTo(null);
	}
	/**
	 * Clears all  text fields 
	 * @author Jeppe Jensen
	 * @version 1.0
	 */
	public void clearAllFields()
	{
      //clear search results
	   srActive=-1;
      srIndex.clear();
      sr.clear();
      booklistbox.removeAllItems();
      availableroombox.removeAllItems();
      addedroom.removeAllItems();
      field1.setText("");
      field2.setText("");
      text.setText("");
      extratext.setText("");
      discounttext.setText("");
      active = new Booking();
	}
	/**
	 * Prints all added rooms to a booking
	 * @author Jeppe Jensen
	 * @version 1.0
	 */
	public void printAddedRooms()
	{
	   addedroom.removeAllItems();
	   
	   for(int i = 0; i < active.rooms.size(); i++)
	   {
	      addedroom.addItem(active.rooms.get(i).getRoomNumber());
	   }
	}
	/**
	 * Prints all guest in booking
	 * @author Jeppe Jensen
	 * @version 1.0
	 */
	public void printSelectedGuest() 
	{
      Guest sg = active.guests.get(currentGuestIndex);
      
      //print all input fields from selected guests
      namefield.setText(sg.getName());
      if(!(sg.getBirthday()==null))
         birthdayfield.setText(stringMyDate(sg.getBirthday()));
      if(!(sg.getNationality()==null))
         nationalityfield.setText(sg.getNationality());
      if(!(sg.getPhoneNumber()==null))
         phonefield.setText(sg.getPhoneNumber());
      if(!(sg.getAddress()==null))
      {
         if(!(sg.getAddress().getStreet()==null))
            streetfield.setText(sg.getAddress().getStreet());
         if(!(sg.getAddress().getStreetNumber()==null))
            streetnmfield.setText(sg.getAddress().getStreetNumber());
         if(!(sg.getAddress().getCity()==null))
            cityfield.setText(sg.getAddress().getCity());
         if(!(sg.getAddress().getPostalCode()==null))
            zipcodefield.setText(sg.getAddress().getPostalCode());
         if(!(sg.getAddress().getCountry()==null))
            bookingname.setText(sg.getAddress().getCountry());
      }
	}
	/**
	 * Gets arrival date from text field
	 * @return arrivalDate take the inputed information from arrival date field
	 * @author Jeppe Jensen
	 * @version 1.0
	 */
	
	public MyDate getArrivalDate()
	{
	   try{
      String sday = field1.getText().substring(0, 2);
      String smonth = field1.getText().substring(3, 5);
      String syear = field1.getText().substring(6, 10);
      
      int day = Integer.parseInt(sday);
      int month = Integer.parseInt(smonth);
      int year = Integer.parseInt(syear);
      
      MyDate arrivalDate = new MyDate(day,month,year);
      
      return arrivalDate;
	   }
      catch(Exception e){
         return null;
      }
	}
	/**
	 * Gets departure date and 
	 * @return departureDate take the inputed information from departure date field
	 * @author Jeppe Jensen
	 * @version 1.0
	 */
	public MyDate getDepartureDate()
	{
	   try{
	   String sday = field2.getText().substring(0, 2);
	   String smonth = field2.getText().substring(3, 5);
	   String syear = field2.getText().substring(6, 10);
      
	   int day = Integer.parseInt(sday);
	   int month = Integer.parseInt(smonth);
	   int year = Integer.parseInt(syear);
	   
	   MyDate departureDate = new MyDate(day,month,year);
	   
	   return departureDate;
	   }
	   catch(Exception e){
	      return null;
	   }
	}
	/**
	 * Gets birthday of guest 
	 * @return birthday takes the inputed information from birthday field
	 * @author Jeppe Jensen
	 * @version 1.0
	 */
	
	  public MyDate getBirthday() //formats from String to MyDate
	   {
	      try{
	      String sday = birthdayfield.getText().substring(0, 2);
	      String smonth = birthdayfield.getText().substring(3, 5);
	      String syear = birthdayfield.getText().substring(6, 10);
	      
	      int day = Integer.parseInt(sday);
	      int month = Integer.parseInt(smonth);
	      int year = Integer.parseInt(syear);
	      
	      MyDate birthday = new MyDate(day,month,year);
	      
	      return birthday;
	      }
	      catch(Exception e){
	         return null;
	      }
	   }
	  /**
	   * Transform MyDate objects from string to integer
	   * @param date
	   * @return
	   */
     public String stringMyDate(MyDate date) //formats from MyDate to String
     {
        //Convert to string
        String d = Integer.toString(date.getDay());
        d = String.format("%2s", d).replace(" ", "0"); //Length 2. Filled with zeros in front
        String m = Integer.toString(date.getMonth());
        m = String.format("%2s", m).replace(" ", "0");
        String y = Integer.toString(date.getYear());
        y = String.format("%4s", y).replace(" ", "0"); //Length 4.
        
        return d+"/"+m+"/"+y;
     }
     /**
      * Save information from text fields to active booking(active is only a class variable. Will try to save to h.bookings if make/change booking button is triggered)
      * @author Jeppe Jensen
      * @version 1.0
      */
     public void saveSelectedBooking()
     {
        //. 
        active.setArrivalDate(getArrivalDate());
        active.setDepartureDate(getDepartureDate());
        try{active.setNumberOfExtraBeds(Integer.parseInt(extratext.getText()));} catch(Exception e3){}
        try{active.setDiscountPercentage(Double.parseDouble(discounttext.getText()));} catch(Exception e4){}
        
        //Rooms already saved
        //Other info? Number of extra beds discount percentage, booking notes
        guestbox.removeAllItems(); //should save all guest fields through guestbox listener.
        
        addedroom.removeAllItems();;
     }
     /**
      * Stores information for new guest
      * @author Jeppe Jensen
      * @version 1.0
      */
     
     public void saveSelectedGuest()
     {
        
        if(srIndex.size() == 0) //If no active object. At user input to search object
        {
           System.out.println("new search guest");
           currentGuestIndex = active.guests.size();
           active.addGuest(new Guest(""));
        }
        
        //save guest field information to selected guest. 
        
        if(!(currentGuestIndex < 0)) // if active guest 
        {
           
           Guest sg = active.guests.get(currentGuestIndex);
           
           //save guest field information to current guest.
           if(!(namefield.getText().equals(""))) // If name was deleted , don't save.
              sg.setName(namefield.getText());
           sg.setBirthday(getBirthday());
           sg.setNationality(nationalityfield.getText());
           sg.setPhoneNumber(phonefield.getText());
           //if no address object, make it.
           if(sg.getAddress()==null)
              sg.setAddress(new Address());
           sg.getAddress().setStreet(streetfield.getText());
           sg.getAddress().setStreetNumber(streetnmfield.getText());
           sg.getAddress().setCity(cityfield.getText());
           sg.getAddress().setPostalCode(zipcodefield.getText());
           sg.getAddress().setCountry(bookingname.getText());
           
           //clear all guest specific fields
           namefield.setText("");
           birthdayfield.setText("");
           nationalityfield.setText("");
           phonefield.setText("");
           streetfield.setText("");
           streetnmfield.setText("");
           cityfield.setText("");
           zipcodefield.setText("");
           bookingname.setText("");
           //reset active guest index 
           currentGuestIndex = -1;
        }
     }
	/**
	 * Inner action listener class
	 * @author Jeppe Jensen
	 * @version 1.0
	 *
	 */

   private class MyButtonListener implements ActionListener
   {
      public void actionPerformed(ActionEvent e)
      {
         
         if (e.getSource() == checkavailab)
         {
            if(!(getArrivalDate() == null || getDepartureDate() == null))
            {
               MyDate arrivalDate = active.getArrivalDate();
               MyDate departureDate = active.getDepartureDate();
               
               if(srActive==-1)//Retrieve dates from fields, if no active booking (when making booking.
               {
                  arrivalDate = getArrivalDate();
                  departureDate = getDepartureDate();
               }
               
               //It would be nice if one could lock date input fields after checkavailab, until booking is saved. Otherwise people can add rooms, then change the dates, then save with any date they want. - next time keep it simple.
               
               //Check availability
               //Available rooms
               availableRooms = h.checkAvailability(arrivalDate, departureDate);
               
               //If rooms already added to booking, remove from availability list.
               for(int i = 0; i < active.rooms.size(); i++)
               {
                  for (int k = 0; k < availableRooms.size(); k++)
                  {
                     for(int j = 0; j <availableRooms.get(k).size(); j++)
                     {
                        if(active.rooms.get(i).equals(availableRooms.get(k).get(j)))
                        {
                           availableRooms.get(k).remove(j);
                        }
                     }
                  }
               }
               
               //Add available room types to combo box
               availableroombox.removeAllItems(); //clear if check availability is pressed multiple times.
               for(int i = 0; i < availableRooms.size(); i++)
               {
                  if(availableRooms.get(i).size() > 0)
                     availableroombox.addItem(h.roomTypesNames.get(i));
               }
            }
         }
         
         
         if (e.getSource() == availableroombox)
         {
            //clear roomnumbersbox
            roomnumbersbox.removeAllItems();
            
            if (availableroombox.getSelectedItem() instanceof String)
            {
               for (int i = 0; i < h.roomTypesNames.size(); i++)
               {
                  //find index of available room type and print list of available rooms numbers to combo box.
                  if(availableroombox.getSelectedItem().equals(h.roomTypesNames.get(i)))
                  {
                     for(int k = 0; k < availableRooms.get(i).size(); k++)
                     {
                        roomnumbersbox.addItem(availableRooms.get(i).get(k).getRoomNumber());
                     }
                  }
               }
               
            }
         }
         
         
         
         if (e.getSource() == addroom)
         {
            
            if (roomnumbersbox.getSelectedItem() instanceof String)
            {
               //Add selected room to booking or search
               
               //loop through available rooms to find room matching selected room name string
               mainloop:
               for(int i = 0; i < availableRooms.size(); i++)
               {
                  for(int k = 0; k < availableRooms.get(i).size(); k++)
                  {
                     if(roomnumbersbox.getSelectedItem().equals(availableRooms.get(i).get(k).getRoomNumber()))
                     {
                        //System.out.println(roomnumbersbox.getSelectedIndex());
                        active.addRoom(availableRooms.get(i).get(k)); //add to rooms list
                        printAddedRooms(); //updates added rooms text field
                        availableRooms.get(i).remove(k); //Remove from available rooms
                        roomnumbersbox.removeItemAt(roomnumbersbox.getSelectedIndex()); //remove from GUI room list
                        //System.out.println("room  was added...");
                        //System.out.println(addedRooms);
                        break mainloop; // to avoid NullPointerException if roomnumbersbox is empty.
                     }
                     
                  }
               }
            }
            
         }
         
         
         
         if (e.getSource() == searchforbooking)
         {
            
            //if there's an active booking, use it as search object.
            
            //should trigger saving all field through booklistbox listener 
            if(srActive>=0)
               booklistbox.removeAllItems();
            else
            {
               //if no active booking, then save input fields to active object.
               active = new Booking();
               saveSelectedBooking();
               saveSelectedGuest();
            }
            
            //feeds active booking object as argument to searchForBooking() in hotel.
            sr = h.searchForBooking(active);
            
            //clear old active booking
            srActive = -1;
            srIndex.clear();
            active = new Booking();
            
            //Retrieve indexes from hotel booking list.
            for(int i = 0; i < sr.size(); i++)
            {
               for(int k = 0; k < h.bookings.size(); k++)
               {
                  if(sr.get(i).equals(h.bookings.get(k)))
                  {
                     srIndex.add(k);
                     break;
                  }
               }
               
            }
            //Display search results in combo box, name of primary guest.
            for(int i = 0; i < sr.size(); i++)
               booklistbox.addItem(sr.get(i).guests.get(0).getName());
         }
         
         
         
         if (e.getSource() == booklistbox)
         {
            //clear fields
            addedroom.removeAllItems();
            guestbox.removeAllItems();
            availableroombox.removeAllItems();
            
            //if was not triggered by clearing the list. (Selected from list)
            if(!(booklistbox.getSelectedIndex()==-1)) //when list is cleared, then index changes to -1 and runs this listener. To avoid ArrayOutOfBound Exception.
            {
               //Retrieves selected booking from hotel booking list and sets as active.
               srActive = booklistbox.getSelectedIndex();
               active = h.bookings.get(srIndex.get(srActive));
               
               //prints active booking information to fields.
               //
               //Print rooms and arrival departure dates
               printAddedRooms();
               field1.setText(stringMyDate(active.getArrivalDate()));
               field2.setText(stringMyDate(active.getDepartureDate()));
               extratext.setText(Integer.toString(active.getNumberOfExtraBeds()));
               discounttext.setText(Double.toString(active.getDiscountPercentage()));
               
               //Display information from booking in guest box
               for(int i = 0; i < active.guests.size(); i++)
               {
                  guestbox.addItem(active.getGuests()[i].getName());
                  currentGuestIndex = 0;
               }
            }
         }
         
         
         
         
         if (e.getSource() == guestbox)
         {
            
            //saves previous guest
            saveSelectedGuest();
            
            //keep index of current guest to save to right index when switching between guest in same booking
            currentGuestIndex = guestbox.getSelectedIndex(); // if guest list is somehow emptied, listeners will always run (the way Java works), and -1 will be saved as this value - automatic reset.
            
            if(!(guestbox.getSelectedIndex()==-1)) //when list is cleared, then index changes to -1 and runs guest box listener. To avoid this.
            {
               printSelectedGuest();
            }
         }
         
         if (e.getSource() == delete)
         {
            if(srActive >= 0) // booking is active, then delete from hotel booking list.
            {
               h.removeBooking(srIndex.get(srActive));
               //clear search results
               //save changes
               try{fa.saveToFile(h);} catch(Exception e2){}
               clearAllFields();
               text.setText("booking deleted");
               
             //set booking Notes field confirmation text
               
            }
         }
         
         if (e.getSource() == checkin)
         {
            if(srActive >= 0)
            {
               if(h.bookings.get(srIndex.get(srActive)).getCheckIn()==false)
               {  //do check-out
                  h.bookings.get(srIndex.get(srActive)).setCheckIn(true);
                  //calculate price:
                  try{fa.saveToFile(h);} catch(Exception e2){};
                  text.setText("check-in completed.");
               }
               else
               { //undo check-out
                  h.bookings.get(srIndex.get(srActive)).setCheckIn(false);
                  try{fa.saveToFile(h);} catch(Exception e2){};
                  text.setText("check-in has been un-done\n Guest not checked in. Saved..");
               }
            }
            
         }
         
         if (e.getSource() == checkout)
         {
            if(srActive >= 0)
            {
               if(h.bookings.get(srIndex.get(srActive)).getCheckOut()==false)
               {  //do check-out
                  h.bookings.get(srIndex.get(srActive)).setCheckOut(true);
                  //calculate price:
                  double price = h.calculatePrice(active);
                  try{fa.saveToFile(h);} catch(Exception e2){};
                  text.setText("check-out completed \n Total price: "+ price);
               }
               else
               { //undo check-out
                  h.bookings.get(srIndex.get(srActive)).setCheckOut(false);
                  try{fa.saveToFile(h);} catch(Exception e2){};
                  text.setText("check-out has been un-done\n Guest not checked out. Saved..");
               }
            }
         }
         
         if (e.getSource() == savebooking)
         {
            if(srActive >= 0 && active.rooms.size() > 0) //save changes to booking
            {
               //saves old dates for comparison
               MyDate oldArrival = active.getArrivalDate();
               MyDate oldDeparture = active.getDepartureDate();
               
               System.out.println("oldArrival: "+oldArrival+", oldDeparture: "+oldDeparture);
               
               //new dates
               MyDate field1 = getArrivalDate(); //entered values
               MyDate field2 = getDepartureDate();
               
               //should trigger saving all fields to active booking and clearing fields, including new arrival and departure dates.
               saveSelectedBooking();
               
               //restores old dates - otherwise h.checkavailability() will fail, since stay is already prolonged, and rooms therefore already taken for the extended period.
               active.setArrivalDate(oldArrival);
               active.setDepartureDate(oldDeparture);
               
               //check if new arrival and departure dates will be accepted. Not interfering with other bookings.
               
               
               System.out.println("newArrival: "+field1+", newDeparture: "+field2);
               
               //test if possible
               boolean extendBefore = false;
               boolean extendAfter = false;
               String message = "";
               //before old booking
               if(!(field1 == null)) //Invalid date format entered (by user).
               {
                  if(field1.isBefore(field2)) //check if still before new departure date
                  {
                     extendBefore=true;
                     if(field1.isBefore(oldArrival)) //if before old arrival - check availability
                     {
                        MyDate ekstraPeriodEnd = oldArrival; // Old arrival is before new departure
                        if(field2.isBefore(oldArrival)) //if new departure is before old arrival
                           ekstraPeriodEnd = field2;
                        
                        //check availability for interval in between Arrival dates.
                        ArrayList<ArrayList<Room>> temp = h.checkAvailability(field1, ekstraPeriodEnd);
                        
                        //checks if rooms in booking are available
                        for(int i = 0; i < active.rooms.size(); i++)
                        {
                           boolean available = false;
                           for(int k = 0; k < temp.size(); k++)
                           {
                              for(int j = 0; j < temp.get(k).size(); j++)
                              {
                                 if(active.rooms.get(i).equals(temp.get(k).get(j)))
                                 {
                                    available = true;
                                 }
                              }
                           }
                           if(available == false)
                           {
                              message+="Room not available - before old arrival date\n\n";
                              System.out.println(message);
                              extendBefore=false;
                              break;
                           }
                        }

                     }
                     
                  }
                  else
                  {
                     System.out.println("Arrival date must be before departure date.");
                  }
               }
               else
               {
                  System.out.println("Invalid date format entered - Arrival date");
               }
               if(field1.equals(oldArrival))
                  extendBefore = true;
               //after old booking
               if(!(field2 == null)) //Invalid date format entered (by user).
               {
                  if(field1.isBefore(field2)) //check if still before new departure date
                  {
                     extendAfter=true;
                     if(oldDeparture.isBefore(field2)) //if after old departure - check availability
                     {
                        MyDate ekstraPeriodStart = oldDeparture; // Old departure is after new arrival
                        if(oldDeparture.isBefore(field1)) //if new arrival is after old departure
                           ekstraPeriodStart = field1;
                        
                        //check availability for interval in between Arrival dates.
                        ArrayList<ArrayList<Room>> temp = h.checkAvailability(ekstraPeriodStart, field2);
                        
                        //checks if rooms in booking are available
                        for(int i = 0; i < active.rooms.size(); i++)
                        {
                           boolean available = false;
                           for(int k = 0; k < temp.size(); k++)
                           {
                              for(int j = 0; j < temp.get(k).size(); j++)
                              {
                                 if(active.rooms.get(i).equals(temp.get(k).get(j)))
                                 {
                                    available = true;
                                    System.out.println("After: available = true");
                                 }
                              }
                           }
                           if(available == false)
                           {
                              message+="Room not available - after old departure date\n\n";
                              System.out.println(message);
                              extendAfter=false;
                              break;
                           }
                        }

                     }
                     
                  }
                  else
                  {
                     System.out.println("Arrival date must be before departure date.");
                  }
               }
               else
               {
                  System.out.println("Invalid date format entered - Departure date");
               }
               if(field2.equals(oldDeparture))
                  extendAfter = true;
               
               //If news dates are available, change booking. Otherwise show error message
               if (extendBefore == true && extendAfter == true)
               {
                  System.out.println("entered dates okay");
                  //Save new dates to booking
                  active.setArrivalDate(field1);
                  active.setDepartureDate(field2);
               }
               else
               {
                  //write error to booking notes - Unable to change booking dates: "Error message"
                  message += "Unable to change dates\n\n";
               }
               
               System.out.println(message);
               //Clear all variables
               //feed booking to search for booking, so displayed again. Possibly make searchAndShow() method.
               h.removeBooking(srIndex.get(srActive));
               h.bookings.add(active);
               //clear search results
               clearAllFields();
               //save changes
               try{fa.saveToFile(h);} catch(Exception e2){}
             //set booking Notes field confirmation text
               message+="Booking was changed";
               text.setText(message);
               
            }
            
            else if(!(active.rooms.size() > 0)) //all room were removed from booking
            {
               text.setText("add a room to booking, before change is allowed. \n changes not saved.");
            }
            
            else //make booking
            {
               //take information from all fields. make new booking with information. must contain necessary fields. Then save to file.
               saveSelectedBooking();
               
               saveSelectedGuest(); //saves guests to active
               Booking temp = new Booking();
               temp.guests = active.guests; //hold guests from active be overwritten.
               saveSelectedBooking(); // save other booking details to active. (and possibly overwriting guests.
               active.guests = temp.guests; // Retrieve guests.
               
               //checks if mandatory information is available.
               if(active.guests.size() > 0)
               {
                  if(!(active.guests.get(0).getName().equals("")))
                  {
                     if(!(active.getArrivalDate() == null || active.getDepartureDate() == null))
                     {
                        if(active.rooms.size() > 0)
                        {
                           active.setArrivalDate(getArrivalDate()); //use enter dates
                           active.setDepartureDate(getDepartureDate());
                           System.out.println("Arrival and departure date:\n"+active.getArrivalDate()+"\n"+active.getDepartureDate());
                           h.addBooking(active);
                           //clear search results
                           clearAllFields();
                           //save changes
                           try{fa.saveToFile(h);} catch(Exception e2){}
                           //set booking Notes field confirmation text
                           
                           
                           System.out.println("New booking was created");
                           
                           text.setText("New booking was created");
                           
                        }
                        else
                        {
                           text.setText("No rooms were added.");
                        }
                     }
                     else
                     {
                        text.setText("Wrong date format or no date entered.");
                     }
                  }
                  else
                  {
                     text.setText("primary guest has no name");
                  }
               }
               else
               {
                  text.setText("no guest enteret");
               }
               
               
               
            }
            
         }
         
         if (e.getSource() == addButton) //add guest to booking
         {
            
            saveSelectedGuest();
            
            currentGuestIndex = active.guests.size();
            active.guests.add(new Guest("name"));
            guestbox.addItem(active.guests.get(active.guests.size()-1).getName() );
            printSelectedGuest();
            
          //take information from all fields. Add to active booking.
            
         }
         
         if (e.getSource() == removeguest)
         {
            if(guestbox.getItemCount() > 1  && srActive >= 0) //works only for existing booking with more than one guest
            {
               active.removeGuest(guestbox.getSelectedIndex());
               h.removeBooking(srIndex.get(srActive));
               h.bookings.add(active);
               //save changes
               try{fa.saveToFile(h);} catch(Exception e2){}
               clearAllFields();
               text.setText("Guest removed from booking.");
               h = fa.readFromFile();
               active = h.bookings.get(h.bookings.size()-1); //changed booking no
               searchforbooking.doClick(); //active is feed to search engine. Current booking shown as only result. Now without removed guest (Rooms can only be in one booking for every day. Therefore booking is unique).
            }
         }
         
         if (e.getSource() == clear)
         {
            clearAllFields();
         }
         
         if (e.getSource() == removeroom)
         {
            if(active.getRooms().length > 1) //one room must stay in booking
            {
               //get room name from Combo box
               String roomNumber = (String) addedroom.getSelectedItem();
               
               //Remove room from Combo box
               addedroom.removeItemAt(addedroom.getSelectedIndex());
               
               //loop through rooms in booking. Remove room when matching roomNumber.
               for(int i = 0; i < active.rooms.size(); i++)
               {
                  if(active.rooms.get(i).getRoomNumber().equals(roomNumber))
                     active.removeRoom(i);
               }
            }
         }
         
      }
   }

public static void main(String[] args) {
	SearchForBooking booking = new SearchForBooking();
}
}
