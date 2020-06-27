package car;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

/**
 * Servlet implementation class AcceptOrderServlet
 */
@WebServlet("/AcceptOrderServlet")
public class AcceptOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AcceptOrderServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
        response.setContentType("text/html;charset=utf-8");
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");

        try (PrintWriter out = response.getWriter()) {

            int OID = Integer.parseInt(request.getParameter("OID").trim());
            int recipentUID = Integer.parseInt(request.getParameter("recipentUID").trim());
            String recipentPhoneNumber = request.getParameter("recipentPhoneNumber").trim();

            //Map<String, String> params = new HashMap<>();
            JSONObject jsonObject = new JSONObject();
            
            Context context = new Context();
            PublishedState publishedState = new PublishedState();

            if (publishedState.doAction(OID, recipentUID, recipentPhoneNumber,context)) {
                jsonObject.put("Result", "success");
            } else {
                jsonObject.put("Result", "failed");
            }

            //jsonObject.put("params", params);
            out.write(jsonObject.toString());
        }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
