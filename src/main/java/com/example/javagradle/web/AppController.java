package com.example.javagradle.web;

import com.example.javagradle.bl.dto.ClientDTO;
import com.example.javagradle.bl.dto.RoleDTO;
import com.example.javagradle.bl.dto.UserDTO;
import com.example.javagradle.bl.service.ClientService;
import com.example.javagradle.bl.service.RoleService;
import com.example.javagradle.bl.service.UserService;
import com.example.javagradle.bl.utils.PasswordEncrypter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@Slf4j
public class AppController {

    @Autowired
    private UserService user;
    @Autowired
    private RoleService role;
    @Autowired
    private ClientService client;

    @GetMapping(value = "/")
    public String loadHome() {
        return "index";
    }

    @GetMapping("/app/login")
    public String loginUser(Model model, UserDTO userDTO) {
        model.addAttribute("user", userDTO);
        return "app/login";
    }

    @GetMapping("/app/signup")
    public String signupUser(Model model, UserDTO user, ClientDTO client) {
        model.addAttribute("user", user);
        model.addAttribute("client", client);
        return "app/signup";
    }

    @GetMapping("/app/dashboard")
    public String dashboardUser() {
        return "app/dashboard";
    }

    @GetMapping("/app/forgot")
    public String forgotUser() {
        return "app/forgot";
    }

    @GetMapping("/admin/login")
    public String loginAdmin(Model model, UserDTO userDTO) {
        model.addAttribute("user", userDTO);
        return "admin/login";
    }

    @GetMapping("/admin/dashboard")
    public String dashboardAdmin() {
        return "admin/dashboard";
    }

    @GetMapping("/admin/forgot")
    public String forgotAdmin() {
        return "admin/forgot";
    }

    @PostMapping("/app/newUser")
    public String addNewUser(UserDTO user, ClientDTO client) {
        this.client.saveOne(client);
        var clientRole = (RoleDTO) this.role.findByID(1);
        user.setRoles(clientRole);
        user.setClient(client);
        user.setPassword(new PasswordEncrypter().encriptarPassword(user.getPassword()));
        this.user.saveOne(user);
        return "redirect:/app/login?success";
    }

    @PostMapping("/checkAccess")
    public String checkAccess(UserDTO user) {
        if (user.getRoles().getId() == 1) {
            return "redirect:/app/dashboard";
        } if (user.getRoles().getId() == 2) {
            return "redirect:/admin/dashboard";
        }
        return "redirect:/app/login";
    }
}
