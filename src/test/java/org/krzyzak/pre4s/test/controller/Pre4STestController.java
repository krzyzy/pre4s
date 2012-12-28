package org.krzyzak.pre4s.test.controller;

import org.krzyzak.pre4s.test.ApplicationException;
import org.krzyzak.pre4s.test.FooEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

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


    @RequestMapping(method = RequestMethod.GET, value = "test", headers="Accept=application/xml, application/json")
    @ResponseBody
    public FooEntity saveDebugOutput(@RequestParam(value="fail") Boolean throwException) throws ApplicationException {
        if (throwException)
            throw new ApplicationException("requested fail");
        return new FooEntity("test");
    }

    @ExceptionHandler({ApplicationException.class})
    public ModelAndView handle(ApplicationException ae) {
        return null;
    }

}
