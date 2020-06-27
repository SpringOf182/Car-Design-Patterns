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
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;

import net.sf.json.JSONObject;
@WebServlet("/IdentifierServlet")
public class IdentifierServlet extends HttpServlet {

	/**
		 * Constructor of the object.
		 */
	public IdentifierServlet() {
		super();
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
        String name = request.getParameter("name").trim();
        int uid = Integer.parseInt(request.getParameter("UID").trim());
        String id = request.getParameter("id").trim();
        String host = "https://idenauthen.market.alicloudapi.com";
	    String path = "/idenAuthentication";
	    String method = "POST";
	    String appcode = "06e2159da1204568bfda34bd25b4788d";
	    System.out.println(name);
	    System.out.println(id);
	    Map<String, String> headers = new HashMap<String, String>();
	    //�����header�еĸ�ʽ(�м���Ӣ�Ŀո�)ΪAuthorization:APPCODE 83359fd73fe94948385f570e3c139105
	    headers.put("Authorization", "APPCODE " + appcode);
	    //����API��Ҫ�󣬶������Ӧ��Content-Type
	    headers.put("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
	    Map<String, String> querys = new HashMap<String, String>();
	    Map<String, String> bodys = new HashMap<String, String>();
	    bodys.put("idNo", id);
	    bodys.put("name", name);
	    try(PrintWriter out = response.getWriter()) {
	    	/**
	    	* ��Ҫ��ʾ����:
	    	* HttpUtils���
	    	* https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/src/main/java/com/aliyun/api/gateway/demo/util/HttpUtils.java
	    	* ����
	    	*
	    	* ��Ӧ�����������
	    	* https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/pom.xml
	    	*/
	    	HttpResponse response1 = HttpUtils.doPost(host, path, method, headers, querys, bodys);
	    	System.out.println(response1.toString());
	    	//��ȡresponse��body
	    	//System.out.println(EntityUtils.toString(response1.getEntity()));
	    	//��ȡ�Ƿ�������Ƿ�ƥ��
	    	JSONObject json = JSONObject.fromObject(EntityUtils.toString(response1.getEntity()));
	    	JSONObject jsonObject = new JSONObject();
	        if(json.getString("respCode").equals("0000")){
	            if(UserFunction.alterIdentifier(uid))
	            	 jsonObject.put("Result", "success");
	            else{
		            jsonObject.put("Result", "failed");
		        }
	        }else{
	            jsonObject.put("Result", "failed");
	        }
	        System.out.println("ok");
	        out.write(jsonObject.toString());
	    } catch (Exception e) {
	    	e.printStackTrace();
	    }
	}
}
