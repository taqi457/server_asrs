package de.p39.asrs.server.controller.auth;

import de.p39.asrs.server.controller.db.dao.AuthenticationDAO;
import de.p39.asrs.server.controller.input.info.UserInfo;
import de.p39.asrs.server.model.auth.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import de.p39.asrs.server.model.auth.User;

import java.util.HashSet;
import java.util.Set;

@Controller
public class UserController {
	
	@Autowired
	private AuthenticationService authService;

	@Autowired
    private SecurityService securityService;

    @Autowired
    private UserValidator userValidator;

    @Autowired
    private AuthenticationDAO authDAO;

    BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String registration(Model model) {
        model.addAttribute("userForm", new User());

        return "registration";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String registration(@ModelAttribute("userForm") User userForm, BindingResult bindingResult, Model model) {
        userValidator.validate(userForm, bindingResult);

        if (bindingResult.hasErrors()) {
            return "registration";
        }

        authService.save(userForm);

        securityService.autologin(userForm.getUsername(), userForm.getPasswordConfirm());

        return "redirect:/welcome";
    }

    @RequestMapping(value = "/loginuser", method = RequestMethod.GET)
    public String login(Model model, String error, String logout) {
        if (error != null)
            model.addAttribute("error", "Your username and password is invalid.");

        if (logout != null)
            model.addAttribute("message", "You have been logged out successfully.");

        return "login";
    }

    @GetMapping("useroverview")
    public String GetOverview(Model model){
        model.addAttribute("users", authDAO.findAllUsers());
        return "useroverview";
    }

    @GetMapping("newuser")
    public String NewUserForm(Model model){
        model.addAttribute("UserInfo", new UserInfo());
        return "userform";
    }

    @GetMapping("edituser/{id}")
    public String EditUserForm(Model model, @PathVariable("id") Long id){
        model.addAttribute("UserInfo", new UserInfo());
        User user = authDAO.findById(id);
        String username = user.getUsername();
        model.addAttribute("user", user);
        model.addAttribute("username", user.getUsername());
        return "edituser";
    }

    @PostMapping("edituser")
    public String handleEdit(@RequestParam("id") Long id, @ModelAttribute UserInfo info){
        User user = authDAO.findById(id);
        user.setUsername(info.getUsername());
        if (info.getPassword() != ""){

            user.setPassword(bcrypt.encode(info.getPassword()));
        }
        user.setPassword(info.getPassword());
        authDAO.update(user);
        return "redirect:/edituser/" + id;
    }

    @PostMapping("newuser")
    public String handleEdit(@ModelAttribute UserInfo info){
        User user = new User();
        user.setUsername(info.getUsername());
        user.setPassword(bcrypt.encode(info.getPassword()));
        Set<Role> roles = new HashSet<>();
        roles.add(Role.USER);
        user.setRoles(roles);
        User new_user = authDAO.update(user);
        return "redirect:/edituser/" + new_user.getId();
    }

}
