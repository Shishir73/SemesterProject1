import java.io.Serializable;
/**
 * A class creating a date object with day, month, year.
 * @author Shishir Sharma, Jeppe Jensen
 * @version 1.0
 */
public class MyDate implements Serializable
{
	
   private int day;
   private int month;
   private int year;
   /**
    * Three-argument constructor initializing the Date
    * @param day  the Date's day
    * @param month the Date's month
    * @param year the Date's year
    */
  
   public MyDate(int day, int month, int year)
   {
      this.day = day;
      this.month = month;
      this.year = year;
   }
/**
 * Gets the day for specific Date
 * @return the day
 */
   public int getDay()
   {
      return day;
   }
/**
 * Sets the day 
 * @param day specific day will be set
 */
   public void setDay(int day)
   {
      this.day = day;
   }
   /**
    * Gets the month for specific Date
    * @return month
    */

   public int getMonth()
   {
      return month;
   }
/**
 * Sets the month
 * @param month specific month will be set
 */
   public void setMonth(int month)
   {
      this.month = month;
   }

   /**
    * Gets the year
    * @return year for specific Date
    */
   public int getYear()
   {
      return year;
   }

   /**
    * Sets the year
    * @param year specific year will be set
    */
   public void setYear(int year)
   {
      this.year = year;
   }
   /**
    * Define if specific date is before another date
    * @param date2 the object to compare with
    * @return true if two objects are equal and return false if they are not.
    */
   
   public boolean isBefore(MyDate date2)
   {
      if(year<date2.getYear() || year==date2.getYear() && month<date2.getMonth() || year==date2.getYear() && month==date2.getMonth() && day<date2.getDay())
         return true;
      else
         return false;
   }
   /**
    * sets the Date to next day
    */
   
   public void nextDay()
   {
      day += 1;

      if (day > daysInMonth())
      {
         day = 1;
         month += 1;
         if (month > 12)
         {
            month = 1;
            year += 1;
         }
      }
   }
   /**
    * Gets number of days in month 
    * @return number of days
    */
   public int daysInMonth()
   {
      if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8
            || month == 10 || month == 12)
      {
         return 31;
      }
      if (month == 4 || month == 6 || month == 9 || month == 11)
      {
         return 30;
      }
      if (month == 2 && isLeapYear())
      {
         return 29;
      }
      if (month == 2 && !(isLeapYear()))
      {
         return 28;
      }
      else
      {
         return 0;
      }
   }
   /**
    * Defines if the specific year is Leap
    * @return true if the year is Leap
    */
   public boolean isLeapYear()
   {
      if (year % 4 == 0 && !(year % 100 == 0) || year % 400 == 0)
         return true;
      else
         return false;
   }
   /**
    * Compares  day, month and year of two dates
    * @param obj the object to compare with
    * @return true if the given object is equal to this date
    */
   public boolean equals(Object obj)
   {
      if(!(obj instanceof MyDate))
         return false;
      
      MyDate temp =(MyDate)obj;
      
      return temp.day==day && temp.month==month && temp.year==year;
   }
   /**
    * Copies the date
    * @return returns date object with same date
    * 
    */
   
   public MyDate copy()
   {
      MyDate temp = new MyDate(day, month, year);
      return temp;
   }
   /**
    * returns a string representation of the date
    * @return a string representation of the date in format: day/month/year.
    */
   
   public String toString()
   {
      return day+"/"+month+"/"+year;
   }
}
