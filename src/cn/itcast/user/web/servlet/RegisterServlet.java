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
import java.util.HashMap;
import java.util.Map;

/**
 * UserServlet层
 *
 * @author dongkaihua
 */
@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8"); // 请求编码
        resp.setContentType("text/html;charset=utf-8"); //响应编码
        //依赖UserService
        UserService userService = new UserService();
        // 1. 封装表单数据(封装到User对象中)
        User form = CommonUtils.toBean(req.getParameterMap(), User.class);
        /**
         * 添加新任务（表单校验）
         * 1. 创建一个Map，用来装载所有的表单错误信息
         *   在校验过程中，如果失败，向map添加错误信息，其中key为表单字段名称
         * 2. 校验之后，查看map长度是否大于0，如果大于0，说明有错误信息，就是有错误！
         *  保存map到request中，保存form到request中，转发到regist.jsp
         * 3. 如果map为空，说明没有错误信息，向下执行
         */
        //用来装载所有的错误信息
        Map<String, String> errors = new HashMap<>();
        //对用户名进行校验
        String username = form.getUsername(); //获取表单的username
        if (username == null || username.trim().isEmpty()) {
            errors.put("username", "用户名不能为空！");
        } else if (username.length() < 3 || username.length() > 15) {
            errors.put("username", "用户名长度必须在3~15之间！");
        }
        // 对密码进行校验
        String password = form.getPassword();
        if (password == null || password.trim().isEmpty()) {
            errors.put("password", "密码不能为空！");
        } else if (password.length() < 3 || password.length() > 15) {
            errors.put("password", "密码长度必须在3~15之间！");
        }
        // 对验证码进行校验
        String sessionVerifyCode = (String) req.getSession().getAttribute("session_vcode");
        String verifyCode = form.getVerifyCode();
        if (verifyCode == null || verifyCode.trim().isEmpty()) {
            errors.put("verifyCode", "验证码不能为空！");
        } else if (verifyCode.length() != 4) {
            errors.put("verifyCode", "验证码长度必须为4！");
        } else if (!verifyCode.equalsIgnoreCase(sessionVerifyCode)) {
            errors.put("verifyCode", "验证码错误！");
        }
        // 判断map是否为空，不为空，说明存在错误
        if (errors != null && errors.size() > 0) {
            /**
             * 1. 保存errors到request域
             * 2. 保存form到request域
             * 3. 转发到regist.jsp
             */
            req.setAttribute("errors", errors);
            req.setAttribute("user", form);
            req.getRequestDispatcher("/user/regist.jsp").forward(req, resp);
            return;
        }
        /**
         * 2. 调用UserService的regist()方法，传递form过去
         * 3. 得到异常：获取异常信息，保存到request域，转发到regist.jsp中显示
         * 4. 没有异常：输出注册成功！
         */
        try {
            userService.regist(form);
            resp.getWriter().print("<h1>注册成功！</h1><a href='"
                    + req.getContextPath() +
                    "/user/login.jsp" + "'>点击这里去登录</a>");
        } catch (UserException e) {
            // 获取异常信息，保存到request域
            req.setAttribute("msg", e.getMessage());
            // 还要保存表单数据，到request域
            req.setAttribute("user",form); // 用来在表单中回显！
            // 转发到regist.jsp
            req.getRequestDispatcher("/user/regist.jsp").forward(req, resp);
        }
    }
}
