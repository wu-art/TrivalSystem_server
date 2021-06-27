package com.BsTravel.controller;


import com.BsTravel.entity.Manager;
import com.BsTravel.entity.Response;
import com.BsTravel.service.ManagerService;
import com.BsTravel.utils.CreateImageCode;
import com.BsTravel.utils.VerificationCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.util.Base64Utils;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.plaf.synth.SynthRootPaneUI;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.Console;
import java.io.IOException;
import java.security.Key;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/manager")
@CrossOrigin //允许跨域
public class ManagerController extends HttpServlet {
    @Autowired
    private ManagerService managerService;

    //注册
    @RequestMapping(value = "register",method = RequestMethod.POST)
    public Response register(@RequestBody Map<String,String> person){
        String username = person.get("username");
        String password = person.get("password");
        String phone = person.get("phone");
        if (username != null && password != null && phone != null){
            List<Manager> user = managerService.queryByName(username);
            if(user.size() == 0){
                int isadd = managerService.addUser(username,password,phone);
                if (isadd > 0){
                    return new Response("注册成功", 1, true);
                }else {
                    return new Response("注册失败", -1, false);
                }
            }else {
                return new Response("注册失败：用户名已存在",-1,false);
            }
        }else {
            return new Response("注册失败：用户名、密码、手机号不能为空",-1,false);
        }
    }

    //登录
    @RequestMapping(value = "login",method = RequestMethod.POST)
    public Response login(@RequestBody Map<String,String>person,HttpServletRequest request) throws ServletException, IOException {
        String username = person.get("username");
        String password = person.get("password");
        //String code1 = person.get("code");
        //获取session中保存的验证码(获取不到session中的code,原因:谷歌不支持session和cookie技术)
        //String sec = (String) session.getAttribute("verifyCode");
        //https://www.cnblogs.com/hujunwei/p/10939673.html参考
        //String userCode = (String)request.getSession().getAttribute("verifyCode");

        if(username != null && password != null){
            List<Manager> user = managerService.queryByName(username);
            if(user.size() == 0){
                return new Response("登录失败：用户名不存在", -1, false);
            }else {
                if (user.get(0).getPassword().equals(password)){
                    return new Response("登录成功", 1, true);
                }else {
                    return new Response("登录失败：密码错误", -1, false);
                }
            }
        }else {
            return new Response("登录失败：用户名密码不能为空",-1,false);
        }
    }

    /*
     * 生成验证码
     * IOException
     * */
    @GetMapping("/getImage")
    public Map<String, String> getImage(HttpServletRequest request,HttpSession session) throws IOException {
        Map<String, String> result = new HashMap<>();
        CreateImageCode createImageCode = new CreateImageCode();
        // 获取验证码
        String securityCode = createImageCode.getCode();
        //System.out.println("验证码:" + securityCode);
        // 验证码存入session
        session.setAttribute("verifyCode",securityCode);
        String key = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        request.getServletContext().setAttribute(key, securityCode);
        // 生成图片
        BufferedImage image = createImageCode.getBuffImg();
        //进行base64编码
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ImageIO.write(image, "png", bos);
        String string = Base64Utils.encodeToString(bos.toByteArray());
        result.put("key", key);
        result.put("image", string);
        result.put("code",securityCode);
        return result;
    }
}
