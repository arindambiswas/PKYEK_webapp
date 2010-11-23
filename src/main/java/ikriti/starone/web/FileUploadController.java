package ikriti.starone.web;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import ikriti.starone.hb.Member;
import ikriti.starone.service.MemberService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class FileUploadController {

	private static String File_Path = "D:\\workspace\\Temp\\";
	private static Logger logger = LoggerFactory.getLogger(MemberController.class);

	MemberService memberService;
		
	@Autowired
	public void setMemberService(MemberService memberService)
	{
		this.memberService = memberService;
	}

	public Member getTestMember()
	{
		Member testMember = new Member();
		return testMember;
	}
	
	@RequestMapping("/fileupload/result.do")
	public ModelAndView fileUpload(@RequestParam(value = "fileName",required = true) String fileName,byte[] data)
	{	
		
		System.out.println("responding to fileupload");
		ModelAndView mav = new ModelAndView("fileupload/result");
			         try{  
			             //create the dir that we will store files  
			             File dir = new File(File_Path);  
			            dir.mkdirs();  
			               
			             File file = new File(File_Path + fileName);  
			             FileOutputStream output = new FileOutputStream(file);  
			             //output.write();  
			             output.close();
			             mav.addObject("successFlag",true);
			             mav.addObject("fileName", fileName);
			         }  
			        catch(FileNotFoundException e)
			        {  	
			        	mav.addObject("successFlag",false);
			        	mav.addObject("Exception", e);
			             return mav;  
			        }  
			         catch(IOException e)
			        {	
			        	 mav.addObject("successFlag",false);
			        	 mav.addObject("Exception", e);
			             return mav;  
			        }  
			      
			        return mav;  
	} 
}
