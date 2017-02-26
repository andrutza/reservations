package reservations.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import reservations.model.Reservation;
import reservations.repository.RestTemplateClient;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class ReservationController {

    @RequestMapping(value = "/addReservation", method = RequestMethod.GET)
    public ModelAndView reservation1() {
        return new ModelAndView("addReservation", "reservation", new Reservation());
    }

    @RequestMapping(value="/addReservation",  method= RequestMethod.POST)
    public String addReservation(@ModelAttribute("reservation")Reservation reservation, ModelMap model) {
        List<Reservation> reservationList = new RestTemplateClient().addReservation(reservation);
        model.addAttribute("reservations", reservationList);
        return "reservations";
    }

    @RequestMapping(value = "/deleteReservation", method = RequestMethod.GET)
    public String reservation2(Model model) {
        List<Reservation> reservations = new RestTemplateClient().getReservations();
        model.addAttribute("reservations", reservations);
        return "deleteReservation";
    }

    @RequestMapping(value="/delete/reservation",  method= RequestMethod.GET)
    public String deleteReservation(@RequestParam Integer reservationId, @RequestParam Integer roomId, ModelMap model) {
        model.addAttribute("reservations", new RestTemplateClient().removeReservation(reservationId, roomId));
        return "reservations";
    }

    @RequestMapping(value="/reservations", method= RequestMethod.GET)
    public String reservations(Model model) {
        model.addAttribute("reservations", new RestTemplateClient().getReservations());
        return "reservations";
    }

    @RequestMapping(value="/reservationDetails", method= RequestMethod.GET)
    public String reservationDetails(Model model) {
        model.addAttribute("reservationsDetails", new RestTemplateClient().getReservationDetails());
        return "reservationDetails";
    }
}
