package reservations.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import reservations.model.Reservation;
import reservations.model.Room;
import reservations.repository.RestTemplateClient;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class RoomController {

    @RequestMapping(value = "/addRoom", method = RequestMethod.GET)
    public ModelAndView room() {
        List<Room> roomList = new RestTemplateClient().getRooms();
        Map map = new HashMap<>();
        map.put("room", new Room());
        map.put("rooms", roomList);
        return new ModelAndView("addRoom", map);
    }

    @RequestMapping(value="/addRoom",  method= RequestMethod.POST)
    public ModelAndView addRoom(@ModelAttribute("room")Room room) {
        List<Room> roomList = new RestTemplateClient().addRoom(room);
        Map map = new HashMap<>();
        map.put("room", new Room());
        map.put("rooms", roomList);
        return new ModelAndView("addRoom", map);
    }

}
