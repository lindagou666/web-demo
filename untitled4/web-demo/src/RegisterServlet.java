import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn= DriverManager.getConnection("jdbc:mysql:///test?characterEncoding=utf8&useSSL=false", "root", "123456");
            System.out.println("数据库连接成功");
            String sql = "insert into Register (username,pwd,email,phone) value (?,?,?,?)";// 定义sql
            PreparedStatement ps = conn.prepareStatement(sql);
            String username= req.getParameter("username");
            String email = req.getParameter("email");
            String phone = req.getParameter("phone");
            String pwd = req.getParameter("pwd");
            System.out.println(username);
            System.out.println(email);
            System.out.println(phone);
            System.out.println(pwd);
            ps.setString(1,username);
            ps.setString(2,pwd);
            ps.setString(3,email);
            ps.setString(4,phone);
            int i = ps.executeUpdate();
            if(i>0){
                resp.sendRedirect("login.html");
            }else {
                resp.sendRedirect("login.html");
                resp.getWriter().write("注册失败");
            }
        }catch (Exception e){
            e.printStackTrace();
            resp.getWriter().write("注册失败");
        }
    }
}

