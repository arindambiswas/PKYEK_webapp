package ikriti.starone.remote;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.commons.logging.LogFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.flex.remoting.RemotingDestination;
import org.springframework.flex.remoting.RemotingInclude;
import org.springframework.stereotype.Service;

@Service("fileUploadService")
@RemotingDestination(channels = { "my-amf" })
public class FileUploadService 
{
	//private static String serverPath_1 = "http://192.168.1.123:8080";   
	private static String serverPath_1 = "http://apptikka.com/starone/images_PKYEK/";
	private static Logger logger = LoggerFactory.getLogger(FileUploadService.class);
	
	// change this to the desired path you wish the files to be uploaded
	//private static String FilePath = "C:\\usr\\local\\tomcat\\webapps\\images\\";
	private static String FilePath = "http://apptikka.com//starone//images_PKYEK//";
	
	@RemotingInclude
	public static String fileUpload(String fileName, byte[] data)
	{
		String returnStr = ""; 
		logger.info("in the fileUpload");
		try 
		{
			// create the dir that we will store files
			File dir = new File(FilePath);
			dir.mkdirs();

			File file = new File(FilePath + fileName);
			//System.out.println("File path: " + FilePath + fileName);
			FileOutputStream output = new FileOutputStream(file);
			output.write(data);
			output.close();
			System.out.println("File path: " + FilePath + fileName);
			
			//no need of code
			//int lowerIndex = FilePath.indexOf("\\", 20);
			
			//System.out.println("index of lower limit:"+lowerIndex);
			
			//String fullFileName = FilePath+fileName;
			//String part_2 = fullFileName.substring(lowerIndex, fullFileName.length());
			//part_2 = part_2.replace("\\","/");
			//returnStr = serverPath_1+part_2;
			returnStr = serverPath_1+ fileName;
			System.out.println("Final return file path:"+returnStr);
			
		}
		catch(NullPointerException e)
		{	
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		catch (FileNotFoundException e)
		{
			System.out.println(e.getMessage());
		}
		catch (IOException e)
		{
			System.out.println(e.getMessage());
		}

		return returnStr;
	}
}
