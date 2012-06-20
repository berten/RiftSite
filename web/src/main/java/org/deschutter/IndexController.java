package org.deschutter;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.portlet.ModelAndView;

/**
 *
 * @author berten
 */
@Controller
public class IndexController {

    @RequestMapping("/index")
    public ModelAndView index() {
        return new ModelAndView("index");
    }
}
