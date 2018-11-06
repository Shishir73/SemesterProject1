import java.util.Scanner;
import java.util.ArrayList;
public class Test
{

   public static void main(String[] args) throws Exception
   {
      
      //Scanner object
      Scanner kb = new Scanner(System.in);
      
      //file adapter
      FileAdapter fa = new FileAdapter("hotelfile.bin");
      FileAdapter fb = new FileAdapter("hotelfile.bin");
      
      //Create Hotel object and and other objects. In the future this information will be stored in the File.
      
      //room types
      ArrayList<String> rt = new ArrayList<String>();
      rt.add("Single");rt.add("Double");rt.add("DoubleSuite");
      
      //room prices
      ArrayList<Double> rp = new ArrayList<Double>();
      rp.add(150.0);rp.add(250.0);rp.add(400.0);
      
      //rooms
      ArrayList<Room> r = new ArrayList<Room>();
      r.add(new Room("301",rt.get(0),true));
      r.add(new Room("302",rt.get(0),true));
      r.add(new Room("303",rt.get(1),true));
      r.add(new Room("304",rt.get(1),true));
      r.add(new Room("305",rt.get(1),true));
      r.add(new Room("201",rt.get(0),true));
      r.add(new Room("202",rt.get(0),true));
      r.add(new Room("203",rt.get(2),true));
      r.add(new Room("204",rt.get(2),true));
      r.add(new Room("205",rt.get(2),true));
      
      //Hotel
      Hotel h = new Hotel(rt,rp,r);
      
     
      //Hotel h = fa.readFromFile();
      
      //Object obj = fa.readFromFile();
      
      //Hotel h = (Hotel) obj;
      //For testing
      
      
      //
      //Hotel class
      
      //addRooms
      //h.addRoom(new Room("3000",rt.get(1),false));
      //h.addRoom(new Room("2000",rt.get(1),false));
      
      //h.removeRoom(0);
      
      
      //makeBooking
      
    MyDate arrivalDate = new MyDate(26,5,2017);
      MyDate departureDate = new MyDate(30,5,2017);
      int extraBeds=0;
      
      Guest g1 = new Guest("Kiro Pora");
      
      g1.setBirthday(new MyDate(19,11,1990));
      g1.setAddress(new Address());
      h.addBooking(new Booking(arrivalDate,departureDate,extraBeds,g1, h.getRooms()[0]));
      h.addGuest(g1);
      h.getBookings()[0].setDiscountPercentage(10);
      //h.bookings.get(0).addRoom(h.getRooms()[0]);
     // h.removeBooking(0);
      System.out.println(h.getBookings());
      
      
      MyDate arrivalDate2 = new MyDate(28,5,2017);
      MyDate departureDate2 = new MyDate(1,6,2017);
      int extrabed=1;
      
      Guest g2 = new Guest("Gosho");
      
      g2.setBirthday(new MyDate(28,22,1985));
      g2.setAddress(new Address());
      h.addBooking(new Booking(arrivalDate2,departureDate2,extrabed,g2,h.getRooms()[1]));
      h.addGuest(g2);
      h.getBookings()[0].setDiscountPercentage(10);
      //h.bookings.get(0).addRoom(h.getRooms()[0]);
      //h.removeBooking(0);
      System.out.println(h.getBookings());
      
      MyDate arrivalDate3 = new MyDate(28,5,2017);
      MyDate departureDate3 = new MyDate(1,6,2017);
      int extrabed2=0;
      
      Guest g3 = new Guest("Sasho");
      
      g3.setBirthday(new MyDate(2,2,1985));
      g3.setAddress(new Address());
      h.addBooking(new Booking(arrivalDate3,departureDate3,extrabed2,g3,h.getRooms()[3]));
      h.addGuest(g3);
      h.getBookings()[0].setDiscountPercentage(10);
      //h.bookings.get(0).addRoom(h.getRooms()[0]);
      //h.removeBooking(0);
      System.out.println(h.getBookings());
      
      //System.out.println(h.guests.size());
      
      //addGuest
      //h.addGuest(g1);
      //h.removeGuest(0);
      
      //System.out.println(h.getGuests()[0]);
      
      //checkAvailability
      //System.out.println(h.checkAvailability(/*arrivalDate*/ new Date(4,1,2017), /*departureDate*/new Date(6,1,2017)));
      
      //daily ledger
      //System.out.println(h.dailyLedger(new Date(1,1,2017)));
      
      //Calculate price
      //System.out.println(h.calculatePrice(h.getBookings()[0]));
 
      //get guests
      /*Guest[] temp = h.getBookings()[0].getGuests();
      for(int i = 0; i<temp.length;i++)
      {
         System.out.println(temp[i]+", ");         
      }*/
      
      //Search for booking
      /*Guest g2 = new Guest("Hans");
      h.addGuest(g2);
      g2.setBirthday(new Date(1,1,0));*/
      //g2.setName(null);
      
      //h.addBooking(new Booking((new Date(27,10,2017)),((new Date(10,11,2017))),0,g2,h.getRooms()[2]));
      
//      for (int i = 0; i < h.getBookings().length; i++) {
//		
//      System.out.println(h.getBookings()[i]);
//      }
//      
//      System.out.println(h);
//      
      
      
     fa.saveToFile(h);
   /*  ArrayList<Hotel> a = new ArrayList<>();
     a.addAll(fb.readFromFile());
      for (int i = 0; i < a.size(); i++) {
		System.out.println(a.get(i).);
	}*/
       
      
      //System.out.println(b1.rooms.size());
      
      //Search for guest
      //Guest g3 = new Guest("Jesus");
      //g3.setBirthday(new Date(1,1,0));
      //System.out.println(h.searchForGuest(g3));
      
      //print hotel details
      //System.out.println(h.toString());
      
      
      //
      //Booking class
      
      //set number of extra beds
      //h.bookings.get(0).setNumberOfExtraBeds(3);
      //System.out.println(h.bookings.get(0).getNumberOfExtraBeds());
      
      //set ArrivaDate, DepartureDate
      /*h.bookings.get(0).setArrivalDate(new Date(2,1,2017));
      System.out.println(h.bookings.get(0).getArrivalDate());
      h.bookings.get(0).setDepartureDate(new Date(7,1,2017));
      System.out.println(h.bookings.get(0).getDepartureDate());*/
      
      //getCheckin and getCheckout
      /*System.out.println(h.bookings.get(0).getCheckIn());
      h.getBookings()[0].setCheckIn(true);
      System.out.println(h.bookings.get(0).getCheckIn());
      
      System.out.println(h.bookings.get(0).getCheckOut());
      h.getBookings()[0].setCheckOut(true);
      System.out.println(h.bookings.get(0).getCheckOut());*/
      
      

      kb.close();
      
      
   }

}
