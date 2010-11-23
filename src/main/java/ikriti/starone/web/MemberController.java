package ikriti.starone.web;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import ikriti.starone.web.MemberController;
import ikriti.starone.hb.Member;
import ikriti.starone.service.MemberService;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.trg.search.Search;

@Controller
public class MemberController
{
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

	@RequestMapping("/member/details.do")
	public ModelAndView details(@RequestParam(value="id", required = true) String memberId,
			HttpServletRequest request)
	{
		System.out.println("responding to /member/details.do");
		ModelAndView mav = new ModelAndView("member/details");
		Search search = new Search();
		
		Member member = null;
		try
		{
			search.addFilterEqual("id", memberId);
			member = memberService.searchUnique(search);
			
			mav.addObject("member", member);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

		return mav;
	}

	@RequestMapping("/member/ajax/details.do")
	public @ResponseBody 
	Member ajaxDetails(@RequestParam(value="id", required = true) String memberId,
			HttpServletRequest request)
	{
		System.out.println("responding to /member/ajax/details.do");
		Search search = new Search();
		
		Member member = null;
		try
		{
			search.addFilterEqual("memberId", memberId);
			member = memberService.searchUnique(search);
			
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

		return member;
	} 
	
	public static void main(String[] args)
	{
		logger.debug("main()");
	}
}
