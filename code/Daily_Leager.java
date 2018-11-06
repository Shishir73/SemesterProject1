
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
/**
 * GUI tab which contains methods and displays daily ledger 
 */

public class Daily_Leager extends JPanel
{
   
   private GregorianCalendar currentDate = new GregorianCalendar();
   private int currentDay = currentDate.get(GregorianCalendar.DATE);
   private int currentMonth = currentDate.get(GregorianCalendar.MONTH)+1;
   private int currentYear = currentDate.get(GregorianCalendar.YEAR);
   
   private FileAdapter fa;
   
   private JPanel date;
   private JPanel datepanel;
   private JPanel panel1;
   private JPanel mainpanel;
   private JPanel todayledger;
   
   private JTextField field1;
   
   private JTextArea alltodayledger;
   private JScrollPane todayledgerScrollPane;
   
   private JLabel label1;
   private JButton button1;
   private MyButtonListener buttonListener;
   
   private JPanel mainbigpanel;
   
   /**
    * No-argument constructor, used to initialize graphical user interface components
    */
   
   public Daily_Leager()
   {
     
      
      fa = new FileAdapter("hotelfile.bin");
      buttonListener = new MyButtonListener();
      
      mainbigpanel = new JPanel();
      mainbigpanel.setBackground(Color.GRAY);
      
      date = new JPanel();
      label1 = new JLabel("Date:");
      label1.setBackground(Color.GRAY);
      field1 = new JTextField(6);
      field1.setText(currentDay+"/"+currentMonth+"/"+currentYear);
      date.add(label1);
      date.add(field1);
      datepanel = new JPanel();
      datepanel.setBackground(Color.GRAY);
      datepanel.add(date);
      
     
      // <----------------------------------------------------------------------------------------------------->
     
      panel1 = new JPanel();
      panel1.setBackground(Color.GRAY);
      button1 = new JButton ("Show Daily Ledger");
      button1.addActionListener(buttonListener);
      //panel1.add(button1);

      mainpanel = new JPanel();
      mainpanel.setBackground(Color.GRAY);
      mainpanel.add(button1);
      
      // <----------------------------------------------------------------------------------------------------->
       
      todayledger = new JPanel();
      todayledger.setBackground(Color.GRAY);
      alltodayledger = new JTextArea(40,61);
      todayledgerScrollPane = new JScrollPane(alltodayledger);
      todayledger.add(todayledgerScrollPane);
      todayledger.setLayout(new FlowLayout(FlowLayout.CENTER,5,5));

      
      // <----------------------------------------------------------------------------------------------------->
      
   

      mainbigpanel.add(datepanel, BorderLayout.WEST);
      mainbigpanel.add(todayledger, BorderLayout.CENTER);
      mainbigpanel.add(mainpanel, BorderLayout.EAST);
      
     
      add(mainbigpanel);
     
      /**
       *Get main panel of this tab
       *@return  main panel of this class
       */
      
   }
   public JPanel getPanel(){
	   return mainbigpanel;
   }
   
   /**
    * Inner class which contains action listener
    */
   
   private class MyButtonListener implements ActionListener
   {
      
      /**
       * This method is used to call methods in the GUI
       * @return Nothing
       * @exception e5
       */
      
      public void actionPerformed(ActionEvent e)
      {
         if (e.getSource() == button1)
         {
            Hotel h = fa.readFromFile();
            
            MyDate dateObj = new MyDate(currentDay,currentMonth,currentYear);
            
            //set ledger date
            try{
               String sday = field1.getText().substring(0, 2);
               String smonth = field1.getText().substring(3, 5);
               String syear = field1.getText().substring(6, 10);
               
               int day = Integer.parseInt(sday);
               int month = Integer.parseInt(smonth);
               int year = Integer.parseInt(syear);
               
               dateObj = new MyDate(day,month,year);
               }
               catch(Exception e5){
                  //if invalid date - Then show ledger for current date.
                  //Convert to string
                  String d = Integer.toString(dateObj.getDay());
                  d = String.format("%2s", d).replace(" ", "0"); //Length 2. Filled with zeros in front
                  String m = Integer.toString(dateObj.getMonth());
                  m = String.format("%2s", m).replace(" ", "0");
                  String y = Integer.toString(dateObj.getYear());
                  y = String.format("%4s", y).replace(" ", "0"); //Length 4.
                  
                  //if invalid date insert current date.
                  field1.setText(d+"/"+m+"/"+y);
               }
            
            ArrayList<ArrayList<Booking>> ledgerBookings = h.dailyLedger(dateObj);
            
            String str = "";
            str += "Guest Name"+"\t"+"Checked in/out"+"\t"+"Rooms"+"\t"+"\t"+"No.of extra Beds"+"\t"+"Note"+"\n";
            str+="Departures :\n";
            for (int i = 0; i < ledgerBookings.get(0).size(); i++)
            {
               str += ledgerBookings.get(0).get(i).getGuests()[0].getName()+"\t"
                     +ledgerBookings.get(0).get(i).getCheckOut()+"\t";
                     Room[] rooms = ledgerBookings.get(0).get(i).getRooms();
               for (int j = 0; j < rooms.length; j++)
               {
                  str+=rooms[j].getRoomNumber()+" ";
               }
               str+= "\t\t"
                     +ledgerBookings.get(0).get(i).getNumberOfExtraBeds()+"\t\t";
                     if(!(ledgerBookings.get(0).get(i).getBookingNotes()==null))
                        str+=ledgerBookings.get(0).get(i).getBookingNotes();
                     str+="\n";
             
            }
            str+="\nArrivals :\n";
            for (int i = 0; i < ledgerBookings.get(1).size(); i++)
            {
               str += ledgerBookings.get(1).get(i).getGuests()[0].getName()+"\t"
                     +ledgerBookings.get(1).get(i).getCheckIn()+"\t";
               Room[] rooms = ledgerBookings.get(1).get(i).getRooms();
               for (int j = 0; j < rooms.length; j++)
               {
                  str+=rooms[j].getRoomNumber()+" ";
               }
               str+= "\t\t"
                     +ledgerBookings.get(1).get(i).getNumberOfExtraBeds()+"\t\t";
                     if(!(ledgerBookings.get(1).get(i).getBookingNotes()==null))
                        str+=ledgerBookings.get(1).get(i).getBookingNotes();
                     str+="\n";
            }
            
            ArrayList<Booking> stays = new ArrayList<Booking>();
            
            //stays
            //Loop through bookings to list guests currently staying i hotel.
            for(int i = 0; i < h.bookings.size(); i++)
            {
               if(h.bookings.get(i).getArrivalDate().isBefore(dateObj) && dateObj.isBefore(h.bookings.get(i).getDepartureDate()))
                  stays.add(h.bookings.get(i));
            }
            //print results
            str+="\nStays in hotel:\n";
            for (int i = 0; i < stays.size(); i++)
            {
               str += stays.get(i).getGuests()[0].getName()+"\t"
                     +"-\t";
               Room[] rooms = stays.get(i).getRooms();
               for (int j = 0; j < rooms.length; j++)
               {
                  str+=rooms[j].getRoomNumber()+" ";
               }
               str+= "\t\t"
                     +stays.get(i).getNumberOfExtraBeds()+"\t\t";
                     if(!(stays.get(i).getBookingNotes()==null))
                        str+=stays.get(i).getBookingNotes();
                     str+="\n";
            }
            
            
            
            alltodayledger.setText(str);
         }
         
      }
   }
   
//   public static void main(String[] args)
//   {
//      Daily_Leager daily = new Daily_Leager();
//   }
//   
}
