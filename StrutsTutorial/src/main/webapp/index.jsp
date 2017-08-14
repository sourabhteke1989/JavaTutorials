<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<body>
<h2><s:property value="message"/></h2>
	<s:form action="adminLogin">
		<s:textfield label="User ID" key="userName"></s:textfield>
		<s:password label="Password" key="password"></s:password>
		<s:submit value="Login"></s:submit>
	</s:form>
</body>
</html>
