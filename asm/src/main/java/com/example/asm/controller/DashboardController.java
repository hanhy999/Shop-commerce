package com.example.asm.controller;




import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("dashboard")
public class DashboardController {

    // @Autowired
    // AccountService accountService;

    @GetMapping("home")
    public String home() {
        System.out.println("go here");

        return "admin/dashboard";

    }

    // ------categories

    // -----product

    // -----notification
    @GetMapping("notification")
    public String notification(Model model) {
        return "admin/notifications";
    }

}
