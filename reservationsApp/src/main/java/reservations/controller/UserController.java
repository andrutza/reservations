package reservations.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import reservations.model.Room;
import reservations.model.User;
import reservations.repository.RestTemplateClient;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class UserController {

    @RequestMapping(value = "/updateUser", method = RequestMethod.GET)
    public ModelAndView user() {
        List<User> userList = new RestTemplateClient().getUsers();
        Map map = new HashMap<>();
        map.put("user", new User());
        map.put("users", userList);
        return new ModelAndView("updateUser", map);
    }

    @RequestMapping(value="/updateUser",  method= RequestMethod.POST)
    public ModelAndView updateUser(@ModelAttribute("user")User user) {
        List<User> userList = new RestTemplateClient().updateUser(user.getId(), user.getPassword());
        Map map = new HashMap<>();
        map.put("user", new User());
        map.put("users", userList);
        return new ModelAndView("updateUser", map);
    }

}
