package com.Aleksa.demo;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.servlet.http.HttpSession;


@Controller
public class DriverController {

    private final DriverCrud dc;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public DriverController(DriverCrud dc, PasswordEncoder passwordEncoder) {
        this.dc = dc;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/loginDriver")
    public String loginDriverView() {
        return "loginDriver"; 
    }

    @PostMapping("/loginDriver")
    public String loginDriver(@RequestParam("username") String username,
                             @RequestParam("password") String password,
                             HttpSession session,
                             RedirectAttributes redirectAttributes) {      
        Optional<Driver> driverOpt = dc.findByUsername(username);

        if (driverOpt.isEmpty()) {
            redirectAttributes.addFlashAttribute("error", "USER DOES NOT EXIST");
            return "redirect:/loginDriver";
        }
        Driver driver = driverOpt.get();
        
        if (!passwordEncoder.matches(password, driver.getPassword())) {
            redirectAttributes.addFlashAttribute("error", "INVALID CREDENTIALS");
            return "redirect:/loginDriver";
        }
        
        session.setAttribute("loggedInDriver", driver);
        return "redirect:/driver/driverDashboard";
    }

    @GetMapping("/driver/driverDashboard")
    public String dashboard(HttpSession session, Model model) {
        Driver driver = (Driver) session.getAttribute("loggedInDriver");

        if (driver == null) {
            return "redirect:/loginDriver";
        }

        model.addAttribute("driver", driver);
        return "driverDashboard";
    }
}