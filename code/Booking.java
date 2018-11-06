import java.util.ArrayList;
import java.io.Serializable;
/**
 * A class representing specific Booking with guests, rooms, arrival date, deparutre date, check-in,check-out,number of extra beds, discount, booking notes.
 * @author Jeppe Jensen
 *@version 1.0
 */
public class Booking implements Serializable
{
   private double discountPercentage;
   private int numberOfExtraBeds;
   private boolean checkIn;
   private boolean checkOut;
   private MyDate arrivalDate;
   private MyDate departureDate;
   private String bookingNotes;
   ArrayList<Guest> guests = new ArrayList<Guest>();
   ArrayList<Room> rooms = new ArrayList<Room>();
   /**
    * No-argument constructor needed in case all the fields are epmty.
    */
   public Booking()
   {
      
   }
   /**
    * Four-argument constructor 
    * @param arrivalDate arrival date of  guest(s) for  Booking.
    * @param departureDate departure date of guest(s) for  Booking.
    * @param numberOfExtraBeds the number of extra beds for Booking
    * @param guest list with guests for Booking
    * @param room list with rooms for Booking
    * Creates rooms and guests array list, sets discount precentage to 0, sets booking notes to empty,check-in and check-out to false.
    */
   public Booking(MyDate arrivalDate,MyDate departureDate, int numberOfExtraBeds, Guest guest, Room room)
   {
      this.arrivalDate = arrivalDate;
      this.departureDate = departureDate;
      this.numberOfExtraBeds = numberOfExtraBeds;
      
      guests = new ArrayList<Guest>();
      rooms = new ArrayList<Room>();
      guests.add(guest);
      rooms.add(room);
      
      discountPercentage=0;
      bookingNotes="";
      checkIn=false;
      checkOut=false;
      
   }
   /**
    * Sets the discount percentage for a Booking
    * @param discountPercentage the discount precentage will be set
    */
   
   public void setDiscountPercentage(double discountPercentage)
   {
      this.discountPercentage=discountPercentage;
   }
   /**
    * Gets discoun precentage for a Booking
    * @return discountPrecentage
    */
   public double getDiscountPercentage()
   {
      return discountPercentage;
   }
   /**
    * Sets the number of extra beds for a Booking
    * @param numberOfExtraBeds will be set
    */
   public void setNumberOfExtraBeds(int numberOfExtraBeds)
   {
      this.numberOfExtraBeds=numberOfExtraBeds;
   }
   /**
    * Gets the number of extra beds for a Booking
    * @return numberOfExtraBeds
    */
   public int getNumberOfExtraBeds()
   {
      return numberOfExtraBeds;
   }
   /**
    * Sets the arrival date of the guest(s) for  Booking(Uses MyDate class)
    * @param arrivalDate departure date will be set
    */
   public void setArrivalDate(MyDate arrivalDate)
   {
      this.arrivalDate = arrivalDate;
   }
   /**
    * Gets arrival date of the guest(s) for Booking(Uses MyDate class)
    * @return arrovalDate
    */
   
   public MyDate getArrivalDate()
   {
      return arrivalDate;
   }
   /**
    * Sets departure date of the guest(s) for Booking(Uses MyDate)
    * @param departureDate departure date will be set
    */
   public void setDepartureDate(MyDate departureDate)
   {
      this.departureDate = departureDate;
   }
   /**
    * Gets the departure date of the guest for Booking(Uses MyDate class)
    * @return departureDate
    */
   
   public MyDate getDepartureDate()
   {
      return departureDate;
   }
   /**
    * Sets booking notes for Booking
    * @param bookingNotes
    */
   
   public void setBookingNotes(String bookingNotes)
   {
      this.bookingNotes = bookingNotes;
   }
   /**
    * Gets the booking notes for Booking
    * @return bookingNotes
    */
   public String getBookingNotes()
   {
      return bookingNotes;
   }
   /**
    * Returns information about check-in for Booking(default se it to fals)
    * @return checkin true or false for Booking
    */
   public boolean getCheckIn()
   {
      return checkIn;
   }
   /**
    * Returns information about check-out for Booking(default set it to false )
    * @return check-out true or false for Booking
    */
   public boolean getCheckOut()
   {
      return checkOut;
   }
   /**
    * Sets check-in  for a specific Booking
    * @param checkIn to true or false for Booking(default set it to false)
    */
   
   public void setCheckIn(boolean checkIn)
   {
      this.checkIn = checkIn;
   }
   /**
    * Sets check-out for a specific Booking
    * @param checkOut to true or false  for Booking(default set it to false)
    */
   
   public void setCheckOut(boolean checkOut)
   {
      this.checkOut = checkOut;
   }
   
   /**
    * Gets all rooms for specific Booking
    * @return tempArray an array list with all rooms fo specific Boking
    */
   public Room[] getRooms()
   {
      Room[] tempArray = new Room[rooms.size()];
      return rooms.toArray(tempArray);
   }
   
   /**
    * Adds more rooms to specific Booking
    * @param room more rooms will be added
    */
   public void addRoom(Room room)
   {
      this.rooms.add(room);
   }
   
/**
 * Remove specific room for Booking
 * @param rooms specific room will be removed from the list with rooms from Booking
 */
   
   public void removeRoom(int index)
   {
      rooms.remove(index);
   }
   /**
    * Gets list with all guest for specific Booking
    * @return index remoove specific guest from guest list
    */
   
   public Guest[] getGuests()
   {
      Guest[] tempArray = new Guest[guests.size()];
      return guests.toArray(tempArray);
      
   }
   /**
    * Adds more guest for specific Booking
    * @param guest more guest(s) will be added
    */
   
   public void addGuest(Guest guest)
   {
      guests.add(guest);
   }
   /**
    * Removes specific guest from Booking
    * @param index specific guest will be removed
    */
   public void removeGuest(int index)
   {
      guests.remove(index);
   }
   /**
    * Compares arrival date, departure date, booking note, guests and rooms. If any of the fields is equal to null it will not compare them
    * @param obj the object to compare with
    * @return true if all the fields(which are not null) are equal
    */
   public boolean possibleEquals(Object obj) //if some fields are null (only this object - NOT argument object. A design decision to limit search results). Then they will not be compared.
   {
      if(!(obj instanceof Booking))
         return false;
      
      Booking temp = (Booking)obj;
      
     /* if(true) //Problem that it must contain a value even if you have none. Then you are forced to specify a value. Must be left out since we are not allowed to specify that value as unknown (null). Solution. Choose standard value when field is empty and skip search condition when value in argument. Or make boolean ArrayList in class, utilized only by search object, specifying if the field were left empty or not.
         if(!(discountPercentage==temp.getDiscountPercentage()))
            return false;
      if(true)
         if(!(numberOfExtraBeds==temp.getNumberOfExtraBeds()))
            return false;
      if(true)
         if(!(checkIn==temp.getCheckIn()))
            return false;
      if(true)
         if(!(checkOut==temp.getCheckOut()))
            return false;*/
      if(!(arrivalDate==null))
         if(!arrivalDate.equals(temp.getArrivalDate())) //since variable type Integer can not be null. PossibleEquals is not possible on Date objects. However, anticipating we don't have partially defined dates, then we can treat it as a string. Then we will still be able to specify a search using an entire Date object.
            return false;
      if(!(departureDate==null))
         if(!departureDate.equals(temp.getDepartureDate())) //since variable type Integer can not be null. PossibleEquals is not possible on Date objects. However, anticipating we don't have partially defined dates, then we can treat it as a string. Then we will still be able to specify a search using an entire Date object.
            return false;
      if(!(bookingNotes==null || bookingNotes.equals("")))
         if(!bookingNotes.equals(temp.getBookingNotes()))
            return false;
      if(guests.size() > 0 && temp.guests.size() > 0 /*it always will be*/ ) //if just one guest from this object possibleEquals one from the argument object. then this object still maybe possibleEquals argument object.
      {
         boolean tempTrue = false;
         int nbnull = 0;
         for(int i = 0; i < guests.size(); i++) 
         {
            for(int k = 0; k < temp.guests.size(); k++)
            {
               if(guests.get(i)==null)
               {
                  nbnull+=1; //if ArrayList contains only null objects, leave out ArrayList from comparison.
                  break; //avoid NullPoinException from running possibleEquals() method on null object.
               }
               if(guests.get(i).possibleEquals(temp.guests.get(k)))
               {
                  tempTrue=true;
                  break;
               }
            }
         }
         if(tempTrue==false  && nbnull < guests.size())
            return false;
      }
      if(rooms.size() > 0 && temp.rooms.size() > 0) 
      {
         boolean tempTrue = false;
         for(int i = 0; i < rooms.size(); i++) 
         {
            for(int k = 0; k < temp.rooms.size(); k++)
            {
               if(rooms.get(i)==null)
               {
                  tempTrue=true;
                  break;
               }
               if(rooms.get(i).possibleEquals(temp.rooms.get(k)))
               {
                  tempTrue=true;
                  break;
               }
            }
         }
         if(tempTrue==false)
            return false;
      }
      
      
      
      return true;
   }

   /**
    * Return a string representation of a Booking 
    * @return a String information about the booking(arival date, dearture date,all guests adn all rooms)
    */
   public String toString()
   {
      String str = "";
      
      if(guests.size() >0)
         str += guests.get(0).getName();
      
      str += " "+arrivalDate+" "+departureDate+" ";
      
      for(int i = 0; i<rooms.size();i++)
      {
         str += rooms.get(i).getRoomNumber()+" ";
      }
      
      str+= ""+numberOfExtraBeds+" "+checkIn+" "+checkOut+" "+discountPercentage+" ";
      
      
      for(int i = 1; i < guests.size(); i++)
      {
         str += guests.get(i) +" ";
      }
      
      str+=bookingNotes;
      
      return str;
      
   }
   
   
   
   
}
