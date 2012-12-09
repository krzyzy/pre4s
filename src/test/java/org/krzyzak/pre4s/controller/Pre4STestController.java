package org.krzyzak.pre4s.controller;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created with IntelliJ IDEA.
 * User: tomasz
 * Date: 09.12.12
 * Time: 19:55
 * To change this template use File | Settings | File Templates.
 */
@Controller
@RequestMapping("/pre4s")
@Component
public class Pre4STestController {


    @RequestMapping(method = RequestMethod.GET, value = "test")
    @ResponseBody
    public String saveDebugOutput() {
        return "dupa";
    }

}
