package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.HashSet;
import java.util.Set;

@Controller
public class HomeController {
    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    MessageRepository messageRepository;

    @RequestMapping("/")
    public String index(Principal principal, Model model) {
        String username = principal.getName();
        model.addAttribute("currentuser", username);
        model.addAttribute("allusers", userRepository.findAll());
        model.addAttribute("messages", messageRepository.findAll());
        return "home";
    }

    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/logout")
    public String logout() {
        return "redirect:/login?logout=true";
    }

    @RequestMapping("/admin")
    public String admin(Principal principal, Model model) {
        String username = principal.getName();
        model.addAttribute("allusers", userRepository.findAll());
        model.addAttribute("messages", messageRepository.findAll());
        model.addAttribute("currentuser", username);
        return "admin";
    }

    @GetMapping("/register")
    public String process(Model model){
        model.addAttribute("user", new User());
        return "register";
    }
    @PostMapping("/register")
    public String processRegisterationPage(@Valid @ModelAttribute("user") User user, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("user", user);
            user.clearPassword();
            return "register";
        }
        else {
        model.addAttribute("message", "User Account Created!");
        user.setEnabled(true);
        userRepository.save(user);
        Role role = new Role(user.getUsername(), "ROLE_USER");
        roleRepository.save(role);
       }
           return "redirect:/";
    }

    //user == catagoty    message == car
    @RequestMapping("/create")
    public String createPost(@ModelAttribute("user") User user, Model model, Principal principal){
        String username = principal.getName();
      model.addAttribute("msg", new Message(username, "defulemessg"));
        model.addAttribute("currentuser", username);
      model.addAttribute("count", userRepository.count());
      model.addAttribute("user", userRepository.findAll());

        return "create";
    }

    @RequestMapping("/save")
    public String saveMsg(@ModelAttribute("message") Message message){
        messageRepository.save(message);
        return "redirect:/";
    }
    @RequestMapping("/deletemsg/{id}")
    public String deletemesssage(@PathVariable("id") long id){
        messageRepository.delete(messageRepository.findById(id).get());
        return "redirect:/admin";
    }
    @RequestMapping("/deleteuser/{id}")
    public String deteletuser(@PathVariable ("id") long id){
        if(userRepository.findById(id).isPresent()){
        userRepository.delete(userRepository.findById(id).get());}

        return "redirect:/admin";
    }



}



