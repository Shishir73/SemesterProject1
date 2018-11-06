import java.io.FileNotFoundException;
import java.io.IOException;
/**
 * A File Adapter retrive to read from file and wright to it
 * @author Hristo Getov, Jeppe Jensen
 * @version 1.0
 *
 */
public class FileAdapter {
	private MyFileIO mfio;
	private String filename;
/**
 * One-argument constructor seting the file name
 * @param fileName the name and path where the Hotel object will be save to
 */
	public FileAdapter(String fileName) {
		mfio = new MyFileIO();
		this.filename = fileName;

	}
/**
 * Uses MyFileIO to get the object with all bookings from file
 * @return Hotel object
 */
	public Hotel readFromFile() {

		Hotel hotel = new Hotel();
		try {
			hotel = (Hotel) mfio.readObjectFromFile(filename);
		} catch (FileNotFoundException e) {
			System.out.println("File not found!");
		} catch (IOException e) {
			System.out.println(" IO Error reading file!");

		} catch (ClassNotFoundException e) {
			System.out.println("Class not found!");
		}
		return hotel;

	}

/**
 * Uses MyFileIO to save the new object with all bookings
 * @param hotel object with all new and old bookings
 * @throws IO exception if there is error with savng the objec to the file
 */
	public void saveToFile(Hotel hotel) throws IOException
{
	try
	{
		mfio.writeToFile(filename, hotel);
	}
	catch(FileNotFoundException e)
	{
		System.out.println("File not found!");
	}
	catch(IOException e)
	{
		System.out.println("IO Error writing to file! \n"+e.getMessage());
	}
	
	
	
	
}
}
