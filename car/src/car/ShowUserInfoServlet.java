package car;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

/**
 * Servlet implementation class ShowUserInfo
 */
@WebServlet("/ShowUserInfoServlet")
public class ShowUserInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShowUserInfoServlet() {
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
            
            JSONObject jsonObject = new JSONObject();
            JSONObject user = new JSONObject();
            UserFunction userDAO = new UserFunction();
            user = userDAO.queryUser(UID);
            if (user != null) {
                jsonObject.put("Result", "success");
                jsonObject.put("user", user);
            } else {
                jsonObject.put("Result", "failed");
            }
            out.write(jsonObject.toString());
        }
	}

}
