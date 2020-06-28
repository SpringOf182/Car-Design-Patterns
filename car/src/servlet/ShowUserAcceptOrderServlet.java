

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * Servlet implementation class ShowUserAcceptOrderServlet
 */
@WebServlet("/ShowUserAcceptOrderServlet")
public class ShowUserAcceptOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShowUserAcceptOrderServlet() {
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
        
        try(PrintWriter out = response.getWriter()){
        	int recipentUID = Integer.parseInt(request.getParameter("recipentUID"));
     //   	Map<String, JSONArray> params = new HashMap<>();
            JSONObject jsonObject = new JSONObject();
            JSONArray orderList = OrderDAO.queryUserAcceptOrder(recipentUID);
            jsonObject.put("orderList", orderList);
            if (orderList != null) {
                jsonObject.put("Result", "success");
            } else {
                jsonObject.put("Result", "failed");
            }
      //      jsonObject.put("params", params);
            out.write(jsonObject.toString());
        }
	}

}
