import java.io.Serializable;
/**
 * A class representing a guest with name, birthday, address, nationality and phone number
 * @author Shishir Sharma, Jeppe Jensen
 * @version 1.0
 *
 */
public class Guest implements Serializable
{
   private String name;
   private MyDate birthday;
   private Address address;
   private String nationality;
   private String phoneNumber;
   /**
    * Five-argument constrctor initializing the guest.
    * @param name full name of the guest.
    * @param birthday the birthday of the guest. (Uses MyDate class)
    * @param address the address of the guest.(Uses Address class)
    * @param nationality the nationality of the guest.
    * @param phoneNumber the phone number of the guest.
    */
   public Guest(String name, MyDate birthday, Address address, String nationality, String phoneNumber)
   {
      this.name = name;
      this.birthday = birthday;
      this.address = address;
      this.nationality = nationality;
      this.phoneNumber = phoneNumber;
   }
   /**
    * No-argument constructor needed if all fields are empty.
    */
   
   public Guest()
   {
      
   }
   /**
    * One-argument constructor initialize only guest's name.
    * @param name full name of a guest.
    */
   public Guest(String name)
   {
      this.name = name;
   }
   /**
    * Gets  name of a guest.
    * @return name of a guest.
    */

   public String getName()
   {
      return name;
   }
/**
 * Sets  name of a guest.
 * @param name of a guest.
 */
   public void setName(String name)
   {
      this.name = name;
   }
   /**
    * Gets  birthday of a guest.(Uses MyDate class)
    * @return birthday of a guest.
    */

   public MyDate getBirthday()
   {
      return birthday;
   }

   /**
    * Sets birthday of a guest.(Uses MyDate class)
    * @param birthday of a guest.
    */
   public void setBirthday(MyDate birthday)
   {
      this.birthday = birthday;
   }
/**
 * Gets address of a guest.(Uses Address class)
 * @return address of a guest.
 */
   public Address getAddress()
   {
      return address;
   }
   /**
    * Sets address of a guest.(Uses Address class)
    * @param address od a guest.
    */

   public void setAddress(Address address)
   {
      this.address = address;
   }
/**
 * Gets nationality of a guest.
 * @return nationality of a guest.
 */
   public String getNationality()
   {
      return nationality;
   }
/**
 * Sets nationality of a guest.
 * @param nationality of a guest.
 */
   public void setNationality(String nationality)
   {
      this.nationality = nationality;
   }
/**
 * Gets phone number of a guest.
 * @return phone number of a guest.
 */
   public String getPhoneNumber()
   {
      return phoneNumber;
   }
   /**
    * Sets phone number of guest.
    * @param phoneNumber of a guest.
    */

   public void setPhoneNumber(String phoneNumber)
   {
      this.phoneNumber = phoneNumber;
   }
   
   /**
    * Compare name, birthday, address, nationality and phone number of two guests.If any of the fields is empty, it does not compare it.
    * @param obj the object to compare with.
    * @return true if all fields in the given object are equal to this guest.
    */
   public boolean possibleEquals(Object obj) //if some fields are null (only this object - NOT argument object. A design decision to limit search results. Otherwise (if(!(name==null)) to be if(!(name==null || temp.name==null)) )). Then they will not be compared.
   {
      if(!(obj instanceof Guest))
         return false;
      
      Guest temp = (Guest)obj;
      
      if(!(name==null || name.equals("")))
         if(!name.equals(temp.getName()))
            return false;
      if(!(birthday==null))
         if(!birthday.equals(temp.getBirthday())) //since variable type Integer can not be null. PossibleEquals is not possible on Date objects. However, anticipating we don't have partially defined dates, then we can treat it as a string. Then we will still be able to specify a search using an entire Date object.
            return false;
      if(!(address==null))
         if(!address.possibleEquals(temp.getAddress()))
            return false;
      if(!(nationality==null || nationality.equals("")))
         if(!nationality.equals(temp.getNationality()))
            return false;
      if(!(phoneNumber==null || phoneNumber.equals("")))
         if(!phoneNumber.equals(temp.getPhoneNumber()))
            return false;
      
      return true;
   }
   /**
    * Returns a string representation of the guest.
    * @return a string representation of the guest in format:name birthday address phone number.
    */
   public String toString() 
   {
      return name+" "+birthday+" "+address+" "+nationality+" "+phoneNumber;
   }
   
}
