package ikriti.starone.remote;

import java.util.Date;

import ikriti.starone.remote.BaseService;
import ikriti.starone.constants.MemberStatus;
import ikriti.starone.hb.EnumGender;
import ikriti.starone.hb.EnumMemberStatus;
import ikriti.starone.hb.FbUser;
import ikriti.starone.hb.Member;
import ikriti.starone.service.FbUserService;
import ikriti.starone.service.MemberService;
import ikriti.starone.vo.EnumGenderVO;
import ikriti.starone.vo.EnumMemberStatusVO;
import ikriti.starone.vo.FbUserVO;
import ikriti.starone.vo.MaParticipantVO;
import ikriti.starone.vo.MemberVO;

import org.springframework.beans.factory.annotation.Autowired;

import com.collectivezen.util.mail.GoogleMail;
import com.collectivezen.util.mail.MailConfig;
import com.collectivezen.util.mail.MailContent;
import com.trg.search.Search;

public class RemoteMemberService extends BaseService
{

	MemberService memberService;
	FbUserService fbUserService;
	
	@Autowired
	public void setMemberService(MemberService memberService)
	{
		this.memberService = memberService;
	}

	@Autowired
	public void setFbUserService(FbUserService fbUserService)
	{
		this.fbUserService = fbUserService;
	}


	public MemberVO register(MemberVO memberVO)
	{
		Search search = new Search();

		return memberVO;
	}
	
	

	public FbUserVO associateFB(FbUserVO fbUserVO)
	{
		/*
		 * 1. Get facebookId
		 * 2. Check whether facebookId has corresponding member
		 * 3. If not, create a member associating the facebook and the member
		 * 4. If yes, populate the memberVO with details from the database
		 * 4. return fbuser
		 */

		Search search = new Search();
		search.addFilterEqual("facebookId", fbUserVO.getFacebookId());
		FbUser fbUser = fbUserService.searchUnique(search);

		MemberVO memberVO = fbUserVO.getMember();
		
		System.out.println("RemoteMemberService.associateFB : fbUser = "+ fbUser);
		
		if(fbUser == null)
		{
			System.out.println("RemoteMemberService.associateFB : fbUser is new, fbuser = "+ fbUser);
			
			Member member = new Member();
			member.setFirstname(memberVO.getFirstname());
			member.setPhotoUrl(fbUserVO.getPhotoUrl());
			member.setCreateDate(new Date());
			
			EnumMemberStatus memberStatus = new EnumMemberStatus();
			memberStatus.setId(MemberStatus.ACTIVE);
			member.setEnumMemberStatus(memberStatus);
			
			memberService.save(member);
			System.out.println("RemoteMemberService.associateFB : member saved "+ member);

			fbUser = new FbUser();
			
			fbUser.setMember(member);
			fbUser.setFacebookId(fbUserVO.getFacebookId());
			fbUser.setPhotoUrl(fbUserVO.getPhotoUrl());
			fbUser.setCreateDate(new Date());
			
			fbUserService.save(fbUser);
			
			memberVO.setId(member.getId());
			memberVO.setPhotoUrl(member.getPhotoUrl());
			fbUserVO.setId(fbUser.getId());

		}
		else
		{
			System.out.println("RemoteMemberService.associateFB : fbUser.getId() = "+ fbUser.getId());
			Member member = fbUser.getMember();
			
			memberVO.setFirstname(member.getFirstname());
			memberVO.setPhotoUrl(member.getPhotoUrl());
			memberVO.setId(member.getId());
			
			fbUserVO.setPhotoUrl(fbUser.getPhotoUrl());
			fbUserVO.setId(fbUser.getId());
		}

		System.out.println("RemoteMemberService.associateFB : fbUserVO = "+ fbUserVO);

		return fbUserVO;
	}
	
	public FbUserVO testAssociateFB()
	{
		
		FbUserVO fbUserVO = new FbUserVO();
		fbUserVO.setFacebookId("2");
		fbUserVO.setPhotoUrl("my_photo_url");
		
		MemberVO memberVO = new MemberVO();
		memberVO.setFirstname("Rajesh Ghosh");
		memberVO.setPhotoUrl(fbUserVO.getPhotoUrl());
		
		fbUserVO.setMember(memberVO);
		
		fbUserVO = associateFB(fbUserVO);
		
		return fbUserVO;
	}
	
}
