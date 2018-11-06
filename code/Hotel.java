import java.util.ArrayList;
import java.io.Serializable;
/**
 * Class containing lists of Rooms, Bookings,Guests, Room Types and Prices for rooms
 * @author Jeppe Jensen, Nikola Vasilev
 *@version 1.0
 */
public class Hotel implements Serializable
{

   ArrayList<String> roomTypesNames = new ArrayList<String>(); //hold the room type name on the hotel ex. single, double, suite.
   ArrayList<Double> roomTypesPrices = new ArrayList<Double>(); //holds the price corresponding to the indexes in roomTypeNames. (same length) 
   
   ArrayList<Room> rooms = new ArrayList<Room>(); //holds the room of the hotels.
   ArrayList<Booking> bookings = new ArrayList<Booking>(); //hold the bookings of the hotel
   ArrayList<Guest> guests = new ArrayList<Guest>(); // We don't need this unless we want to save guests with no bookings. Like we would be able to save rooms that has no booking. Otherwise guest objects are referenced in booking objects.
   /**
    * Empty constructor creates object which are not completely full with information
    */
   public Hotel()
   {
      
   }
   /**
    * Three-argument constructor initializing type of the room, price for different room types, total number of added rooms
    * @param roomTypesNames The type of room for Booking
    * @param roomTypesPrices the price of room for Booking
    * @param rooms the amount of rooms added to Booking
    */
   public Hotel(ArrayList<String> roomTypesNames, ArrayList<Double> roomTypesPrices, ArrayList<Room> rooms)
   {
      this.roomTypesNames=roomTypesNames;
      this.roomTypesPrices=roomTypesPrices;
      this.rooms=rooms;
   }
   /**
    * Add room to booking
    * @param room the room added to Booking
    */
   public void addRoom(Room room)
   {
      rooms.add(room);
   }
   /**
    * remove room from Booking
    * @param index the position of the room from the list
    */
   public void removeRoom(int index)
   {
      rooms.remove(index);
   }
   /**
    * Gets list with all rooms
    * @return rooms an array with all rooms
    */
   public Room[] getRooms()
   {
      Room[] temp = new Room[rooms.size()];
      rooms.toArray(temp);
      return temp;
   }
   /**
    * Adds new Booking to the list
    * @param booking the booking to add to the Hotel
    */
   public void addBooking(Booking booking)
   {
      bookings.add(booking);
   }
   /**
    * Remove bookings from the list
    * @param index the position in the booking list that will be removed
    */
   public void removeBooking(int index)
   {
      bookings.remove(index);
   }
   /**
    * Gets list with all Bookings
    * @return bookings an array with all Bookings
    */
   public Booking[] getBookings()
   {
      Booking[] temp = new Booking[bookings.size()];
      bookings.toArray(temp);
      return temp;
   }
   /**
    * Adds a guest to the list
    * @param guest a guest add to the list
    */
   public void addGuest(Guest guest)
   {
      guests.add(guest);
   }
   /**
    * Remove guest from the list
    * @param index the position in the guest list that will be removed
    */
   public void removeGuest(int index)
   {
      guests.remove(index);
   }
   /**
    * Gets list with all guests
    * @return guests an array with all guests
    */
   public Guest[] getGuests()
   {
      Guest[] temp = new Guest[guests.size()];
      guests.toArray(temp);
      return temp;
   }
   /**
    * Adds type of the room to Booking
    * @param name  specific room type will be added 
    * @param price the price for this type of room will be added
    */
   public void addRoomType(String name, double price)
   {
      roomTypesNames.add(name);
      roomTypesPrices.add(price);
   }
   /**
    * Removes specific room type
    * @param index the position in the list that will be remooved
    */
   
   public void removeRoomType(int index)
   {
      roomTypesNames.remove(index);
      roomTypesPrices.remove(index);
   }
   /**
    * Gets list with the type of rooms
    * @return roomTypesName a list with room types
    */
   public ArrayList<String> getRoomTypesNames()
   {
      
      return roomTypesNames;
   }
   /**
    * Gets list with price for different room types
    * @return roomTypePrice a list with price for different room types
    */
   public ArrayList<Double> getRoomTypesPrices()
   {
      
      return roomTypesPrices;
   }
   /**
    * Checking availability of all rooms and return list with available rooms sorted by type
    * @param arrivalDate the arrival date of the guest
    * @param departureDate the departure date of the guest
    * @return temp two dimensional array list with type of available rooms 
    */
   public ArrayList<ArrayList<Room>> checkAvailability(MyDate arrivalDate, MyDate departureDate)
   {
      //copies room list. As as a starting template for list of available rooms.
       ArrayList<Room> availableRooms = new ArrayList<Room>(rooms);
       
       //Removes rooms not available for entire period in between arrivalDate and departureDate (Availability period).
       
       //Go through all booking
       for(int i = 0; i<bookings.size(); i++)
       {
          MyDate bookingArrivalDate = bookings.get(i).getArrivalDate();
          MyDate bookingDepartureDate= bookings.get(i).getDepartureDate();
          
          //If booking arrival date or departure date is inside availability period (period overlap) or arrival date is before or equal availability period departure date is after or equal (spanning the entire period). Then remove room from availability list.
          if(bookingArrivalDate.isBefore(departureDate) && arrivalDate.isBefore(bookingArrivalDate) || bookingDepartureDate.isBefore(departureDate) && arrivalDate.isBefore(bookingDepartureDate) || ( bookingArrivalDate.isBefore(arrivalDate) || bookingArrivalDate.equals(arrivalDate) ) && ( departureDate.isBefore(bookingDepartureDate) || departureDate.equals(bookingDepartureDate) ) )
          {
             //Where date period overlaps, remove rooms in booking from availability list.
             //Retrieve rooms from booking
             Room[] remove = bookings.get(i).getRooms();
             
             //cycle through rooms
             for(int k = 0;k<remove.length;k++)
             {
                //Compare with available rooms
                for(int j = 0; j < availableRooms.size(); j++)
                {
                   //if rooms equals, remove room from availability list.
                   if( remove[k].equals(availableRooms.get(j)) )
                   {
                      availableRooms.remove(j);
                      break;
                   }
                   
                      
                }
             }
          }
       }
       
       //Split available rooms into room types
          
       //Create 2 dimensional ArrayList.
      ArrayList<ArrayList<Room>> temp = new ArrayList<ArrayList<Room>>();
      
      //add ArrayList for every room type.
      for(int i = 0; i<roomTypesNames.size(); i++)
      {
         temp.add(new ArrayList<Room>());
      }
      
      //If arrival date not before departure date, then return empty array.
      if(!(arrivalDate.isBefore(departureDate)))
         return temp; 
      
      //divide available rooms by room type
      //cycle through available rooms
      for(int i = 0; i<availableRooms.size(); i++)
      {
         //Compare with roomTypeNames
         for(int k = 0; k<roomTypesNames.size(); k++)
         {
            //if room type equal roomTypesNames index, add room to same index in temp.
            if(availableRooms.get(i).getRoomType().equals(roomTypesNames.get(k)))
                  temp.get(k).add(availableRooms.get(i));
         }
      }
      
      
      //returns 2 dimensional ArrayLists with rooms divided by room type. The index of this ArrayList equals the index of the room type in roomTypesNames.
      return temp;
   }
   /**
    * Searching for a specific booking
    * @param booking the booking with all guest details we searching for
    * @return booking object booking details
    */
   public ArrayList<Booking> searchForBooking(Booking booking) //Returns all bookings with a guest matching entered details.  If some Guest fields are left empty. Bookings with a Guest matching all other field are returned.
   {
      
      ArrayList<Booking> temp = new ArrayList<Booking>();
      
      //cycle all bookings. If entered details all match a booking, then add booking to booking ArrayList temp. Start with most recent bookings (highest index).
      for(int i = bookings.size()-1; i>=0; i--)
      {
         if(booking.possibleEquals(bookings.get(i)))  //important that possibleEquals is run on search object and the existing objects as argument. 
            temp.add(bookings.get(i));                //if some fields are null (only this object - NOT argument object. A design decision to limit search results. Otherwise (if(!(name==null)) to be if(!(name==null || temp.name==null)) )). Then they will not be compared.
      }
      return temp;
   }
   /**
    * Searching for a guest by entering details(name or birthday or nationality, etc.)
    * @param guest all details for the guest
    * @return guest object with all inputed guest details
    */
   public ArrayList<Guest> searchForGuest(Guest guest) //returns all guests matching entered details. If some fields are left empty. Guests matching all other field are returned.
   {
      ArrayList<Guest> temp = new ArrayList<Guest>();
      
      //cycle through all guests. Start with most recent.
      
      for(int i = guests.size()-1; i >= 0; i--)
      {
         if(guest.possibleEquals(guests.get(i)))  //important that possibleEquals is run on search object and the existing objects as argument.
            temp.add(guests.get(i));              //if some fields are null (only this object - NOT argument object. A design decision to limit search results. Otherwise (if(!(name==null)) to be if(!(name==null || temp.name==null)) )). Then they will not be compared.
      }
         
      return temp;
   }
   /**
    * Checking arrivals and departures for specific day 
    * @param date sets arrival and departure date
    * @return two dimensional array list with all arrivals and departures for specific date
    */
   public ArrayList<ArrayList<Booking>> dailyLedger(MyDate date)
   {
      
      ArrayList<ArrayList<Booking>> temp = new ArrayList<ArrayList<Booking>>();
      
      //For departures
      temp.add(new ArrayList<Booking>());
      //For Arrivals
      temp.add(new ArrayList<Booking>());
      
      //could keep the names of the individual ArrayLists in A third ArrayList...no. 
      
      
      //cycle through all bookings
      for(int i=0;i<bookings.size();i++)
      {
         //if arrival or departure date match, add to daily ledger
         if(bookings.get(i).getDepartureDate().equals(date))
            temp.get(0).add(bookings.get(i));
         if(bookings.get(i).getArrivalDate().equals(date))
            temp.get(1).add(bookings.get(i));
      }
      
      return temp;
      
   }
   /**
    * Calculating the price for Booking
    * @param booking the specific booking we are doing calculation for
    * @return totalPrice the calculated price for booking 
    */
   
   public double calculatePrice(Booking booking)
   {
      //get room list from booking.
      Room[] rooms = booking.getRooms();
      
      //holds total price of booking.
      double totalPrice=0;
      
      //loop through the rooms in booking.
      for(int i = 0; i<rooms.length; i++)
      {
         //loop through the room types.
         for (int k = 0; k<roomTypesNames.size(); k++)
         {
         //Add standard price of room type to total price.
         if(rooms[i].getRoomType().equals(roomTypesNames.get(k)))
               {
                  totalPrice+=roomTypesPrices.get(k);
               }
         }
      }
      
      //Multiply duration of days stayed
      int durationOfStayInDays=0;
      MyDate tempDate = booking.getArrivalDate().copy();
      while(!tempDate.equals(booking.getDepartureDate()))
      {
         tempDate.nextDay();
         durationOfStayInDays+=1;
      }
      totalPrice*=durationOfStayInDays;
      
      //Deduct discount percentage from total price
      totalPrice*=(1-(booking.getDiscountPercentage()/100));
      
      return totalPrice;
   }
   /**
    * Returns a string representation of bookings
    * @return a string representation of the hotel in the format:Room Types: roomTypesNames: Room Price: roomTypesPrices: Rooms: rooms: Bookings:bookings Guests: guests - each specification followed by a new line character
    */
   public String toString()
   {
      String str="Room Types: "+roomTypesNames+"\nRoomPrices: "+roomTypesPrices+"\n Room: "+rooms;
      str+="\nBookings: "+bookings+"\nGuests: "+guests;
      return str;
   }
   
}
