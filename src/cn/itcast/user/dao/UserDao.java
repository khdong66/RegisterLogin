package cn.itcast.user.dao;

import cn.itcast.user.domain.User;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import java.io.FileOutputStream;
import java.io.OutputStreamWriter;


/**
 * 数据类
 *
 * @author dongkaihua
 */
public class UserDao {
    private String path = "/Users/dongkaihua/Documents/users.xml";

    /**
     * 按用户名查询
     *
     * @param username
     * @return
     */
    public User findByUsername(String username) {
        /**
         * 1.得到Document
         * 2.xpath查询！
         *  3. 校验查询结果是否为null，如果为null，返回null
         *  4. 如果不为null，需要把Element封装到User对象中
         */
        // 创建解析器
        SAXReader reader = new SAXReader();
        try {
            Document document = reader.read(this.path);
            // 通过xpath查询得到Element
            Element element = (Element) document.selectSingleNode("//user[@username='" + username + "']");
            // 校验element是否为null，如果为null，返回null
            if (element == null) return null;
            // 把element数据封装到User对象中
            User user = new User();
            String attrUsername = element.attributeValue("username");// 获取该元素的名为username属性值
            String attrPassword = element.attributeValue("password");// 获取该元素的名为password属性值
            user.setUsername(attrUsername);
            user.setPassword(attrPassword);
            return user;
        } catch (DocumentException e) {
            throw new RuntimeException(e);
        }

    }

    /**
     * 添加用户
     *
     * @param user
     */
    public void add(User user) {
        /**
         * 1.得到Documnt
         * 2.通过Documen得到root元素！<users>
         * 3.使用参数user，转发成Element对象
         * 4.把element添加到root元素中
         * 5.保存Document
         */
        // 得到Documen
        SAXReader reader = new SAXReader();
        try {
            Document document = reader.read(path);
            // 得到根元素
            Element root = document.getRootElement();
            // 通过根元素创建新元素
            Element userEle = root.addElement("user");
            // 为userEle设置属性
            userEle.addAttribute("username", user.getUsername());
            userEle.addAttribute("password", user.getPassword());
            /**
             * 保存文档
             */
            // 创建输出格式化器
            OutputFormat format = new OutputFormat("\t", true);// 缩进使用\t，是否换行，使用是！
            format.setTrimText(true);// 清空原有的缩进和换行
            // 创建XmlWriter
            try {
                XMLWriter writer = new XMLWriter(
                        new OutputStreamWriter(
                                new FileOutputStream(path), "UTF-8"), format);
                writer.write(document);
                writer.close();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        } catch (DocumentException e) {
            throw new RuntimeException(e);
        }

    }
}
