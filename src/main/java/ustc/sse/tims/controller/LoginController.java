package ustc.sse.tims.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ustc.sse.tims.bean.User;
import ustc.sse.tims.mapper.UserMapper;

import javax.servlet.http.HttpSession;

/**
 * @author ZHGQ
 * @project TIMSServer
 * @Package ustc.sse.tims.controller
 * @date 2019/3/12-15:07
 * @Copyright: (c) 2019 USTC. All rights reserved.
 * @Description:
 */

@Controller
public class LoginController {

    @Autowired
    UserMapper userMapper;

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam("username") String userName ,
                        @RequestParam("password") String passWord,
                        HttpSession session, Model model){

        User user = userMapper.getUserByName(userName);
        if(user == null || user.equals("")){
            model.addAttribute("msg1","用户不存在");
            return "login";
        }
        else if(passWord.equals(user.getPassWord())){
            session.setAttribute("loginUser",userName);
            return "redirect:/index";
        }
        else{
            model.addAttribute("msg2","密码错误");
            return "login";
        }

    }

    @GetMapping("/logout")
    public String logout(HttpSession session, Model model){
        session.removeAttribute("loginUser");
        model.addAttribute("logout-msg","已退出登录");
        return "index";
    }

}
