package cn.itcast.user.web.servlet;

import cn.itcast.commons.CommonUtils;
import cn.itcast.user.domain.User;
import cn.itcast.user.service.UserException;
import cn.itcast.user.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * UserServlet层
 *
 * @author dongkaihua
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8"); // 请求编码
        resp.setContentType("text/html;charset=utf-8"); //响应编码
        //依赖UserService
        UserService userService = new UserService();
        /**
         * 1.获取表单数据，封装到 user 中
         * 2.调用 service 的 login() 方法， 传递 form 过去!
         * 3.如果 service 的 login() 方法，没有抛出异常!返回一-个User对象!
         * 4.有异常:获取异常信息，保存到 request 域， 保存 form,转发到 login.jsp
         * 5.没异常:保存返回的 user 对象到 session 中! ! !重定向到 welcome.jsp (显示当前用户信息! )
         */
        User form = CommonUtils.toBean(req.getParameterMap(), User.class);
        try {
            User user = userService.login(form);
            req.getSession().setAttribute("sessionUser", user);
            resp.sendRedirect(req.getContextPath() + "/user/welcome.jsp");
        } catch (UserException e) {
            req.setAttribute("msg", e.getMessage());
            req.setAttribute("user", form);
            req.getRequestDispatcher("/user/login.jsp").forward(req, resp);
        }
    }
}
