import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ConnectException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

@WebServlet("/displayusertable")
public class displayusertable extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out=response.getWriter();
        StringBuilder json=new StringBuilder();
        String  jsonstr="";
        //连接数据库查询所有用户
        mydb os=new mydb();
        try {
            Statement ps=os.getstmt();
            String sql="select 用户名,密码 from airplan.用户表";
            ResultSet rs=ps.executeQuery(sql);
            //处理结果
            json.append("[");
            while(rs.next())
            {
                //过去每个用户的信息
                String name=rs.getString("用户名");
                String secret=rs.getString("密码");
                json.append("{\"用户名\":\"");
                json.append(name);
                json.append("\",\"密码\":\"");
                json.append(secret);
                json.append("\"},");
            }
            jsonstr=json.substring(0,json.length()-1)+"]";
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
        }
        out.print(jsonstr);
    }
}
