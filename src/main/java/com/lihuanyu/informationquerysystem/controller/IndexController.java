package com.lihuanyu.informationquerysystem.controller;

import com.lihuanyu.informationquerysystem.model.ClassroomMessage;
import com.lihuanyu.informationquerysystem.service.AvailableClassroomService;
import com.lihuanyu.informationquerysystem.service.LoginService;
import com.lihuanyu.informationquerysystem.util.QueryUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by skyADMIN on 16/3/27.
 */
@Controller
public class IndexController {

    @Autowired
    private LoginService loginService;

    @Autowired
    private AvailableClassroomService availableClassroomService;

    @RequestMapping(value = "/", method = RequestMethod.GET, params = "verify_request")
    public String processAuth(String verify_request) throws Exception {
        return loginService.processAuth(verify_request);
    }

    @RequestMapping("/")
    public String showIndex(Model model){
        if (!loginService.isLogin()){
            return "redirect:/yibanauth";
        }else {
            QueryUtil queryUtil = new QueryUtil();
            ClassroomMessage classroomMessage = availableClassroomService.processMessage(queryUtil.findCurrentAvailableClassroom());
            model.addAttribute("classmessage",classroomMessage);
            return "index";
        }
    }
}
