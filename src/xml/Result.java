package xml;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * class for marshalling
 * 
 * @author DenRUS
 */
@XmlRootElement
public class Result {
	public String[] res;
	public Result(String[] args) {this.res = args;}
	public Result(){};
	
	/**
	 * marshallise itself
	 * 
	 * @param outputFileName name of the file to write result in
	 */
	public void marsh(String outputFileName)	{
		OutputStream os = null;
		try {
			os = new FileOutputStream(new File(outputFileName));
			JAXBContext.newInstance(Result.class).createMarshaller().marshal(this, os);
			os.close();
		} catch (IOException ex) {
			System.out.println("Oh..again troubles..");
			ex.printStackTrace();
		} catch (JAXBException ex) {
			System.out.println("Oh..again troubles..");
			ex.printStackTrace();
		}
	}
}
