import java.io.Serializable;
/**
 * A class Representing the type and number of room 
 * @author Jeppe Jensen
 *@version 1.0
 */
public class Room implements Serializable
{
   private String roomNumber;
   private String roomType;
   private boolean smokingAllowed;
   /**
    * No-argument constructor if all fields are empty
    */
   public Room()
   {
      
   }
   /**
    * Three-argument constructor
    * @param roomNumber the room number for specific room
    * @param roomType the type of the room
    * @param smokingAllowed if smoking is allowed in the room
    */
   public Room(String roomNumber, String roomType, boolean smokingAllowed)
   {
      this.roomNumber = roomNumber;
      this.roomType = roomType;
      this.smokingAllowed = smokingAllowed;
   }
   /**
    * Gets the room number for specific room
    * @return roomNumber return the number of room
    */

   public String getRoomNumber()
   {
      return roomNumber;
   }
/**
 * Sets the room number for specific room
 * @param roomNumber what room number will be set
 */
   public void setRoomNumber(String roomNumber)
   {
      this.roomNumber = roomNumber;
   }
/**
 * Gets the type of a room
 * @return roomType return the type of room
 */
   public String getRoomType()
   {
      return roomType;
   }
/**
 * Sets the type of a room
 * @param roomType what room number will be set
 */
   public void setRoomType(String roomType)
   {
      this.roomType = roomType;
   }
   /**
    * Gets information if smokin in the room is allowed
    * @return true if smoking is allowed and false if it is not
    */

   public boolean getSmokingAllowed()
   {
      return smokingAllowed;
   }
   /**
    * Sets information if smoking is allowed in the room
    * @param smokingAllowed if smoking is allowed or not
    */

   public void setSmokingAllowed(boolean smokingAllowed)
   {
      this.smokingAllowed = smokingAllowed;
   }
   
   /**
    * Returns String representation of the room
    * @return a String representation of the room in format: room number room type smoking allowed.
    */
   public String toString()
   {
      return roomNumber+" "+roomType+" "+smokingAllowed;
   }
   /**
    * Compares room number,room type, smoking allowed of two rooms
    * @param obj object to compare with
    * returns true if the given object is equal to this room
    */
   public boolean equals(Object obj)
   {
      if(!(obj instanceof Room))
         return false;
      
      Room temp = (Room)obj;
      
      return roomNumber.equals(temp.getRoomNumber()) && roomType.equals(temp.getRoomType()) && smokingAllowed==temp.getSmokingAllowed();
   }
   /**
    * Compares room number, room type, smoking allowed of two rooms(if any of the fields is equal to null, does not compare them)
    * @param obj object to compare with
    * @return return true if all the fields in given object are equal to this room
    */
   
   public boolean possibleEquals(Object obj)
   {
      if(!(obj instanceof Room))
         return false;
      
      Room temp = (Room)obj;
      
      if(!(roomNumber==null || roomNumber.equals("")))
         if(!roomNumber.equals(temp.roomNumber))
            return false;
      if(!(roomType==null || roomType.equals("")))
         if(!roomType.equals(temp.roomType)) //since variable type Integer can not be null. PossibleEquals is not possible on Date objects. However, anticipating we don't have partially defined dates, then we can treat it as a string. Then we will still be able to specify a search using an entire Date object.
            return false;
      /*if(!(smokingAllowed==null)) //boolean not allowed to be null. Therefore left out of PossibleEquals. We may not have that specific information when we do a search.
         if(!smokingAllowed.equals(temp.smokingAllowed)) 
            return false;*/

      return true;
   
   }
   
}
