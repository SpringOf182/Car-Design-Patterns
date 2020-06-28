

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

/**
 * Servlet implementation class PublishOrderServlet
 */
@WebServlet("/PublishOrderServlet")
public class PublishOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PublishOrderServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=utf-8");
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");

        try (PrintWriter out = response.getWriter()) {
        	
            int UID = Integer.parseInt(request.getParameter("UID").trim());
            String pickUp = request.getParameter("pickUp").trim();
            String delivery = request.getParameter("delivery").trim();
            String company = request.getParameter("company").trim();
            double price = Double.parseDouble(request.getParameter("price"));
            double weight = Double.parseDouble(request.getParameter("weight"));
            String verificationCode = request.getParameter("verificationCode").trim();
            String contactNumber = request.getParameter("contactNumber").trim();
            String memo = request.getParameter("memo").trim();
            String latestTime = request.getParameter("latestTime").trim();
            
            
            JSONObject jsonObject = new JSONObject();

            if (OrderDAO.insertOrder(UID,pickUp,delivery,company,price, weight, verificationCode, contactNumber, memo, latestTime)) {
                jsonObject.put("Result", "success");
            } else {
                jsonObject.put("Result", "failed");
            }
            out.write(jsonObject.toString());
        }
	}
}
