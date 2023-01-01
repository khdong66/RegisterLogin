package cn.itcast.user.web.servlet;

import cn.itcast.vcode.utils.VerifyCode;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;

@WebServlet("/VerifyCodeServlet")
public class VerifyCodeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8"); // 请求编码
        resp.setContentType("text/html;charset=utf-8"); //响应编码
        //1. 创建验证码类
        VerifyCode vc = new VerifyCode();
        //2. 得到验证码图片
        BufferedImage image = vc.getImage();
        //3. 把图片上的文本保存到session中
        req.getSession().setAttribute("session_vcode", vc.getText());
        //4. 把图片响应给客户端
        VerifyCode.output(image, resp.getOutputStream());
    }
}
