
<%@page import="ikriti.starone.hb.Member"%><%
	Member member = (Member) request.getAttribute("member");
%>
Hello World, <%= member.getFirstname() %>