import java.io.Serializable;
/**
 * A class representing an address with street,street number, postal code, city, country 
 * @author Shishir Sharma, Jeppe Jensen
 *@version 1.0
 */
public class Address implements Serializable
{
   private String street;
   private String streetNumber;
   private String postalCode;
   private String city;
   private String country;
   /**
    * No-Argument constructor needed if all fields are empty.
    */
   public Address()
   {
      
   }
   /**
    * Four-argument constructor.
    * @param street address's street name. 
    * @param streetNumber address's street number.
    * @param postalCode address's postal code.
    * @param city address's city name.
    * @param country address's country name.
    */
   public Address(String street, String streetNumber, String postalCode, String city, String country)
   {
      this.street = street;
      this.streetNumber = streetNumber;
      this.postalCode = postalCode;
      this.city = city;
      this.country = country;
   }
/**
 * Gets the street name of the address.
 * @return street name.
 */
   public String getStreet()
   {
      return street;
   }
/**
 * Sets the street name of the address.
 * @param street what the address's street name will be set to.
 */
   public void setStreet(String street)
   {
      this.street = street;
   }
/**
 * Gets the street number of the address.
 * @return street number.
 */
   public String getStreetNumber()
   {
      return streetNumber;
   }
/**
 * Sets street number of the address.
 * @param streetNumber what address's street number will be set to.
 */
   public void setStreetNumber(String streetNumber)
   {
      this.streetNumber = streetNumber;
   }
/**
 * Gets postal code of the address.
 * @return postal code.
 */
   public String getPostalCode()
   {
      return postalCode;
   }
/**
 * Sets postal code of the address.
 * @param postalCode what address's postal code will be set to.
 */
   public void setPostalCode(String postalCode)
   {
      this.postalCode = postalCode;
   }
/**
 * Gets city name of the address.
 * @return city name.
 */
   public String getCity()
   {
      return city;
   }
/**
 * Sets city name of the address.
 * @param city what address's city name will be set to.
 */
   public void setCity(String city)
   {
      this.city = city;
   }
/**
 * Gets country name of the address.
 * @return country name.
 */
   public String getCountry()
   {
      return country;
   }
/**
 * Sets country name of the address.
 * @param country what address's country name will be set to.
 */
   public void setCountry(String country)
   {
      this.country = country;
   }
   /**
    * Returns a string representation of the address.
    * @return a string representation of the address in format: street streetnumber new line city new line country
    */
   public String toString()
   {
      return street+" "+streetNumber+"\n"+postalCode+" "+city+"\n"+country;
   }
   /**
    * Compares street, street number, postal code, city, country. If some fields are null it will not compare them
    * @param obj the object to compare with
    * @return true if all the fields (which are not null) are equal
    * 
    */
   public boolean possibleEquals(Object obj)
   {
      if(!(obj instanceof Address))
         return false;
      
      Address temp = (Address)obj;
      
      if(!(street==null || street.equals("")))
         if(!street.equals(temp.street))
            return false;
      if(!(streetNumber==null || streetNumber.equals("")))
         if(!streetNumber.equals(temp.streetNumber)) 
            return false;
      if(!(postalCode==null || postalCode.equals("")))
         if(!postalCode.equals(temp.postalCode))
            return false;
      if(!(city==null || city.equals("")))
         if(!city.equals(temp.city))
            return false;
      if(!(country==null || country.equals("")))
         if(!country.equals(temp.country))
            return false;
      
      return true;
   }
}
