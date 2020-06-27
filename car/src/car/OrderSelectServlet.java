package car;

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
 * Servlet implementation class OrderSelectServlet
 */
@WebServlet("/OrderSelectServlet")
public class OrderSelectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OrderSelectServlet() {
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
            //    Map<String, JSONArray> params = new HashMap<>();
                  JSONObject jsonObject = new JSONObject();
                  
                  String pickUp = request.getParameter("pickUp").trim();
                  String delivery = request.getParameter("delivery").trim();
                  String company = request.getParameter("company").trim();
         //       out.println("pickUp:"+pickUp);
         //       out.println("delivery:"+delivery);
         //       out.println("company:"+company);
                  if(pickUp.equals("Пе"))
                	  pickUp = "%";
                  if(delivery.equals("Пе"))
                	  delivery = "%";
                  if(company.equals("Пе"))
                	  company = "%";
                  JSONArray orderList = OrderDAO.querySelectedOrder(pickUp, delivery, company);
                  jsonObject.put("orderList", orderList);
             //   jsonObject.put("params", params);
                  if (orderList!= null) {
                      jsonObject.put("Result", "success");
                  } else {
                      jsonObject.put("Result", "failed");
                  }
                  out.write(jsonObject.toString());
              }
	}

}
