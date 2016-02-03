<%@page import="fr.shazilgerard.findmypatient.datamodel.Patient"%>
<%@page import="java.util.List"%>
<%@page import="fr.shazilgerard.findmypatient.datamodel.PatientManagement"%>
<%@page import="fr.shazilgerard.findmypatient.controller.IdentityController"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Patient list</title>
<%
	// Create our controller
	IdentityController controller = new IdentityController();
	PatientManagement patientMng = controller.getPatientManagement();
	
	// Execute 
	List<Patient> patientList = patientMng.readAll();
%>
</head>
<body>
	<table>
	<tr>
	<% for(Patient patient : patientList) { %>
	<tr>
		<td> <%= patient.getName() %> </td>
	</tr>
	<% } %>
	</table>
</body>
</html>