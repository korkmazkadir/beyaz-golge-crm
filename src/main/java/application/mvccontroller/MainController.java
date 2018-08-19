package application.mvccontroller;

import application.model.Camp;
import application.model.User;
import application.model.UserRole;
import application.service.AthleteService;
import application.service.CampService;
import application.service.RegistrationService;
import application.service.UserService;
import java.security.Principal;
import java.sql.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {

    @Autowired
    CampService campService;

    @Autowired
    UserService userService;
    
    @Autowired
    RegistrationService registrationService;
    
    @Autowired
    AthleteService athleteService;

    @RequestMapping(value = {"", "/", "/login"}, method = RequestMethod.GET)
    public String loginPage(Principal principal, Model model) {

        if (principal == null) {
            System.out.println("principal is null");
            return "login";
        }

        model.addAttribute("camps", campService.getAllCamps());
        model.addAttribute("camp_page", true);
        return "index";
    }

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String camps(Principal principal, Model model) {
        List<Camp> camps = campService.getAllCamps();
        model.addAttribute("camps", camps);
        model.addAttribute("camp_page", true);
        return "index";
    }

    @RequestMapping(value = "/index", method = RequestMethod.POST)
    public String createUpdateCamp(
            @RequestParam(value = "id", required = false) Long id,
            @RequestParam(value = "name", required = true) String name,
            @RequestParam(value = "place", required = true) String place,
            @RequestParam(value = "startDate", required = true) Date startDate,
            @RequestParam(value = "endDate", required = true) Date endDate,
            Model model) {

        Camp camp = campService.saveCamp(new Camp(id, name, place, startDate, endDate));

        if (camp == null) {
            System.out.println("Camp create error occured try again later...");
        }

        model.addAttribute("camps", campService.getAllCamps());
        model.addAttribute("camp_page", true);
        return "index";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String registrationPage(Principal principal, Model model) {
        List<Camp> camps = campService.getAllCamps();
        model.addAttribute("registration_page", true);
        model.addAttribute("camps", camps);
        return "registration";
    }

    @RequestMapping(value = "/pre-registration-list", method = RequestMethod.GET)
    public String preRegistrationPage(Principal principal, Model model) {
        model.addAttribute("pre_registration_page", true);
        model.addAttribute("registrations",registrationService.getAllPreRegistrations());
        return "pre-registration-list-2";
    }
    
    @RequestMapping(value = "/athlete-list", method = RequestMethod.GET)
    public String athleteListPage(Principal principal, Model model) {
        model.addAttribute("athlete_list_page", true);
        model.addAttribute("athletes",athleteService.getAllAthletes());
        return "athlete-list";
    }

    @RequestMapping(value = "/reports", method = RequestMethod.GET)
    public String reportPage(Principal principal, Model model) {
        model.addAttribute("report_page", true);
        return "reports";
    }

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public String userPage(Principal principal, Model model) {
        List<User> users = userService.getAllUsers();
        model.addAttribute("users", users);
        model.addAttribute("user_page", true);
        return "users";
    }

    @RequestMapping(value = "/users", method = RequestMethod.POST)
    public String createUser(
            @RequestParam(value = "id", required = false) Long id,
            @RequestParam(value = "username", required = true) String username,
            @RequestParam(value = "role", required = true) int role,
            @RequestParam(value = "password", required = true) String password,
            Model model) {

        User user = userService.saveUser(new User(id, username, password, UserRole.values()[role]));

        System.out.println(user);

        if (user == null) {
            System.out.println("User not created or saved. Try later :(");
        }

        model.addAttribute("users", userService.getAllUsers());
        model.addAttribute("user_page", true);
        return "users";
    }

    @RequestMapping(value = "/camp-details", method = RequestMethod.GET)
    public String campDetailPage(Principal principal, Model model) {
        model.addAttribute("camp_detailpage", true);
        return "camp-details";
    }

    @ModelAttribute("user_name")
    public String getUserName(Principal principal) {
        if (principal == null || principal.getName() == null) {
            return "anonymous user";
        }
        return principal.getName();
    }

    @ModelAttribute("context_path")
    public String getContextPath() {
        return "";
    }

}
