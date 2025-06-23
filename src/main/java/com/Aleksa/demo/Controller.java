package com.Aleksa.demo;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@org.springframework.stereotype.Controller
public class Controller {
	
	private ContactFormService cfs;
	private saveBookingFormService bfs;
	private DriverCrud dc;
	
	@Autowired
	public void setBfs(saveBookingFormService bfs) {
		this.bfs = bfs;
	}

	@Autowired
	public void setCfsi(ContactFormService cfs) {
		this.cfs = cfs;
	}
	
	@Autowired
	public void setdc(DriverCrud dc) {
		this.dc=dc;
	}
	
	private PasswordEncoder passwordEncoder;

	@Autowired
	public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
	    this.passwordEncoder = passwordEncoder;
	}

	@GetMapping(path = {"/", "/home", "/welcome", "/index"})
	public String welcomeView(HttpServletRequest req,Model m) {
		String requestURI=req.getRequestURI();
		m.addAttribute("mycurrentpage",requestURI);
		m.addAttribute("bookingForm",new bookingForm());
	    return "home"; 
	}
	
	@GetMapping(path={"/login", "/admin"})
	public String adminLoginView(HttpServletRequest request, Model m) {
		//Proverava da li postoji globalni atribut "logout" i da li je true
        // Ako jeste, dodaje ga u Model da bi Thymeleaf mogao prikazati poruku 
		ServletContext servletContext=request.getServletContext();
		Object attribute=servletContext.getAttribute("logout");
		if(attribute instanceof Boolean) {
			m.addAttribute("logout",attribute);
			servletContext.removeAttribute("logout");
		}
		
		return "login";
	}

	@GetMapping("/about")
	public String AboutView(HttpServletRequest req,Model m) {
		String requestURI=req.getRequestURI();
		m.addAttribute("mycurrentpage",requestURI);
	    return "about"; 
	}

	@GetMapping("/cars")
	public String CarsView(HttpServletRequest req,Model m) {
		String requestURI=req.getRequestURI();
		m.addAttribute("mycurrentpage",requestURI);
	    return "cars";  
	}

	@GetMapping("/services")
	public String ServiceView(HttpServletRequest req,Model m) {
		String requestURI=req.getRequestURI();
		m.addAttribute("mycurrentpage",requestURI);
		 m.addAttribute("contactform", new ContactForm());
	    return "services";  
	}
	 
	@GetMapping("/contact")
	public String ContactView(HttpServletRequest req,Model m) {
		String requestURI=req.getRequestURI();
		m.addAttribute("mycurrentpage",requestURI);
	    return "contact";  
	}
	
	@Autowired
	private SimpMessagingTemplate messagingTemplate;
	
	@PostMapping("/services")
	public String contactform(@Valid @ModelAttribute("contactform") ContactForm contactform,
			BindingResult bindingresult,Model m, RedirectAttributes redirectAttributes) throws JsonProcessingException {
		if(bindingresult.hasErrors()) {
			m.addAttribute("bindingresult",bindingresult);
			return "services";  
		}
		
		ContactForm savecfs=cfs.saveContactService(contactform);
		if(savecfs!=null) {
			redirectAttributes.addFlashAttribute("message","Message sent successfully!");
			Map<String, String> payload = new HashMap<>();
			payload.put("type", "contact");
			payload.put("message", "New contact from " + contactform.getName());

			ObjectMapper mapper = new ObjectMapper();
			String json = mapper.writeValueAsString(payload);

			messagingTemplate.convertAndSend("/topic/notifications", json);
		}else {
			redirectAttributes.addFlashAttribute("message","Something went wrong!");
		}
		
	    return "redirect:/services"; 
	}
	
	 @Autowired
	 private EmailService emailService;
	
	@PostMapping("/home")
	public String bookingform(@Valid @ModelAttribute("bookingForm") bookingForm bookingform,
			BindingResult bindingresult,Model m, RedirectAttributes redirectAttributes) throws JsonProcessingException {
		if(bindingresult.hasErrors()) {
			m.addAttribute("bindingresult",bindingresult);
			return "/home";  
		}else if(bookingform.getAdult()+bookingform.getChildren()>4) {
			m.addAttribute("message","Total number of passengers can not pass 4!");
			return "/home"; 
		}
		
        String to = bookingform.getEmail();
        String subject = "Nova rezervacija";
        String body = "Stigla je nova rezervacija od " + bookingform.getName() + ", Sadrzaj poruke" + bookingform.getMessage();

		bookingForm savebfs=bfs.saveBookingForm(bookingform);
		if(savebfs!=null) {
			redirectAttributes.addFlashAttribute("message","Message sent successfully!");
			Map<String, String> payload = new HashMap<>();
			payload.put("type", "booking");
			payload.put("message", "New reservation from " + bookingform.getName());

			ObjectMapper mapper = new ObjectMapper();
			String json = mapper.writeValueAsString(payload);

			messagingTemplate.convertAndSend("/topic/notifications", json);
			emailService.sendReservationNotification(
				    bookingform.getEmail(),
				    "New reservation",
				    bookingform.getName(),
				    bookingform.getEmail(),
				    bookingform.getTime(),
				    bookingform.getDate(),
				    bookingform.getFrom(),
				    bookingform.getTo()
				);
		}else {
			redirectAttributes.addFlashAttribute("message","Something went wrong!");
		}
		
	    return "redirect:/home"; 
	}
		}
	
	 @Autowired
	 private EmailService emailService;
	
	@PostMapping("/home")
	public String bookingform(@Valid @ModelAttribute("bookingForm") bookingForm bookingform,
			BindingResult bindingresult,Model m, RedirectAttributes redirectAttributes) throws JsonProcessingException {
		if(bindingresult.hasErrors()) {
			m.addAttribute("bindingresult",bindingresult);
			return "/home";  
		}else if(bookingform.getAdult()+bookingform.getChildren()>4) {
			m.addAttribute("message","Total number of passengers can not pass 4!");
			return "/home"; 
		}
		
        String to = bookingform.getEmail();
        String subject = "Nova rezervacija";
        String body = "Stigla je nova rezervacija od " + bookingform.getName() + ", Sadrzaj poruke" + bookingform.getMessage();

		bookingForm savebfs=bfs.saveBookingForm(bookingform);
		if(savebfs!=null) {
			redirectAttributes.addFlashAttribute("message","Message sent successfully!");
			Map<String, String> payload = new HashMap<>();
			payload.put("type", "booking");
			payload.put("message", "New reservation from " + bookingform.getName());

			ObjectMapper mapper = new ObjectMapper();
			String json = mapper.writeValueAsString(payload);

			messagingTemplate.convertAndSend("/topic/notifications", json);
			emailService.sendReservationNotification(
				    bookingform.getEmail(),
				    "New reservation",
				    bookingform.getName(),
				    bookingform.getEmail(),
				    bookingform.getTime(),
				    bookingform.getDate(),
				    bookingform.getFrom(),
				    bookingform.getTo()
				);
		}else {
			redirectAttributes.addFlashAttribute("message","Something went wrong!");
		}
		
	    return "redirect:/home"; 
	}
	
	@GetMapping("/loginDriver")
	public String loginDriverView() {
	    return "loginDriver"; 
	}
	
	@PostMapping("/loginDriver")
	public String loginDriver(@RequestParam("email") String email,
			@RequestParam("password") String password,
			RedirectAttributes redirectAttributes,
			HttpSession session) {
		
		Optional<Driver> driver=dc.findByEmail(email);
		Driver Driver = driver.get();
			if (driver.isPresent() && passwordEncoder.matches(password, Driver.getPassword())) {
	            session.setAttribute("loggedInDriver", driver);
	            return "redirect:/driver/driverDashboard";
		}else {
			redirectAttributes.addFlashAttribute("error", "INVALID CREDENTIALS");
            return "redirect:/loginDriver";
		}
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
