import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@WebServlet("/doLogin")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");
        req.getRequestDispatcher("login.html").forward(req,resp);
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql:///test?characterEncoding=utf8&useSSL=false", "root", "123456");
            String sql = "select * from Register where username = ? and pwd = ?";
            PreparedStatement ps=conn.prepareStatement(sql);
            ps.setString(1,req.getParameter("username"));
            ps.setString(2,req.getParameter("pwd"));
            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()){
// 登录成功
                System.out.println("登录成功");

            }else {
                System.out.println("账号或者密码错误");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
