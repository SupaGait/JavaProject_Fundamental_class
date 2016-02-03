package fr.shazilgerard.findmypatient.servlet;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.shazilgerard.findmypatient.controller.IdentityController;
import fr.shazilgerard.findmypatient.datamodel.PatientManagement;

/**
 * Servlet implementation class Create
 */
@WebServlet("/Create")
public final class Create extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public Create() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("Got a Post request.");
		System.out.println(request.getRequestURI());
		
		// Get data
		Map<String, String[]> parameterMap = request.getParameterMap();
		
		// New manager
		IdentityController controller = new IdentityController();
		//PatientManagement patientManagement = controller.getPatientManagement();
		
		// Extract
		extractParameterValue(parameterMap, "user");
		extractParameterValue(parameterMap, "room");
		
		
	}
	private String extractParameterValue(Map<String, String[]> parameterMap, String parameterName)
	{
		String parameterValue= null;
		String[] values = parameterMap.get(parameterName);
		if(values != null)
		{
			parameterValue = values[0];
		}
		return parameterValue;
	}

}
