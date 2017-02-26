package reservations.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import reservations.repository.RestTemplateClient;

@Controller
public class GoController {
    @RequestMapping(method = RequestMethod.GET, value = "/go")
    public String printHello(ModelMap model) {
        model.addAttribute("message", "Hello Spring MVC Framework!");
        new RestTemplateClient().getReservations();
        return "hello";
    }
}

