// UserController.java
package com.trioshop.controller.user;

import com.trioshop.SessionConst;
import com.trioshop.model.dto.item.ItemCondition;
import com.trioshop.model.dto.item.ItemDetailSearch;
import com.trioshop.model.dto.item.ItemInfoByUser;
import com.trioshop.model.dto.user.*;
import com.trioshop.service.user.UserInfoService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class UserController {


    @Autowired
    HttpSession session;

    @Autowired
    private UserInfoService userInfoService;

    @GetMapping("/login")
    public String loginPage_G() {
        return "/user/userInfo/login";
    }

    //@ModelAttribute 객체를 받아오는거 @RequestParam 변수명을 가져오는거
    @PostMapping("/login")
    public ModelAndView loginPage(@ModelAttribute UserIdPasswd userIdPasswd) {
        ModelAndView mv = new ModelAndView();

        UserInfoBySession user = userInfoService.isValidUser(userIdPasswd);
        System.out.println("user = " + user);
        if (user == null) {
            mv.setViewName("/user/userInfo/login");
            mv.addObject("error", "아이디 또는 비밀번호가 올바르지 않습니다.");
            return mv;
        }

        session.setAttribute(SessionConst.LOGIN_MEMBER, user);
        //스프링이 자동으로 관리하는 세션 객체에 속성이 설정됩니다. 이렇게 하면 사용자가 로그인한 정보를 세션에 저장할 수 있습니다.
        if (user.getGradeCode() == 4) {
            mv.setViewName("redirect:/trioAdmin");
            return mv;
        } else {
            mv.setViewName("redirect:/");
            return mv;
        }
    }

    @GetMapping("/logout")
    public String logoutPage(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }

    //@ModelAttribute 객체로 반환 UserJoin객체를 userJoin 라는 이름으로 가져온것임
    @GetMapping("/join")
    public String joinPage_G(@ModelAttribute("userJoin") UserJoin userJoin) {
        return "/user/userInfo/join";
    }

    @PostMapping("/join")
    public ModelAndView joinPage(@ModelAttribute("userJoin") UserJoin userJoin) {
        ModelAndView mv = new ModelAndView();
        try {
            boolean isRegistered = userInfoService.saveUserInfo(userJoin);
            if (isRegistered) {
                mv.setViewName("redirect:/login");
                mv.addObject("success", "회원가입에 성공했습니다.");
            } else {
                mv.setViewName("redirect:/join");
                mv.addObject("error", "이미 사용중인 계정입니다.");
            }
        } catch (Exception e) {
            mv.setViewName("redirect:/join");
            mv.addObject("exception", "회원가입 중 오류가 발생했습니다.");
        }
        return mv;
    }

    @GetMapping("/findId")
    public String findId_G() {
        return "/user/userInfo/findId";
    }

    //여기서 @RequestParam 쓴이유는 객체에서 필요한 정보만을 뺴오기 위해서 사용한거다. 그리고 반환하기위해서이다.
    @PostMapping("/findId")
    public ModelAndView findIdPage(@RequestParam String userName, @RequestParam String userTel) {
        UserFindId userId = userInfoService.isfindId(userName, userTel);
        ModelAndView mv = new ModelAndView("/user/userInfo/findId");

        if (userId != null && userId.getUserId() != null) { // 사용자
            if (userId.getUserName().equals(userName) && userId.getUserTel().equals(userTel)) { // 사용자 이름과 전화번호가 일치하는 경우
                mv.addObject("userInfo", userId);
            } else {
                mv.addObject("message", "일치하는 정보를 찾을 수 없습니다.");
            }
        } else {
            mv.addObject("message", "일치하는 정보를 찾을 수 없습니다.");
        }
        return mv;
    }

    @GetMapping("/findPw")
    public String findPw_G() {
        return "/user/userInfo/findPw";
    }

    //dd
    //여기서 @RequestParam 쓴이유는 객체에서 필요한 정보만을 뺴오기 위해서 사용한거다. 그리고 반환하기위해서이다.
    @PostMapping("/findPw")
    public ModelAndView findPw(@RequestParam String userName, @RequestParam String userId, @RequestParam String userPasswd, @RequestParam String confirmPassword) {
        ModelAndView mv = new ModelAndView();
        UserFindPw findPw = userInfoService.isfindPw(userName, userId);
        if (findPw != null && findPw.getUserName().equals(userName) && findPw.getUserId().equals(userId)) {
            if (userPasswd.equals(confirmPassword)) {
                userInfoService.isupdate(userPasswd);
                mv.addObject("message", "비밀번호가 성공적으로 변경되었습니다.");
            } else {
                mv.addObject("message", "비밀번호가 일치하지 않습니다.");
            }
        } else {
            mv.addObject("message", "일치하는 정보를 찾을 수 없습니다.");
        }
        return mv;
    }


    @GetMapping("/myPage")
    public String myPage() {
        return "/user/userInfo/myPage";
    }

}





