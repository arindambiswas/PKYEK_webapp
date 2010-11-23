
<%@page language="java"%>
<%
	Object flag = request.getAttribute("successflag");
	if(flag != null)
	{
		Object file = request.getAttribute("fileName");
%>
Hello Your file <%=file%> is successfully saved on disk
<% 			
	}
	else
	{
		Exception exp = (Exception)request.getAttribute("Exception");
%>
Error in file uploading<%=exp %> 
<%		
	}
%>
