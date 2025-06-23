package com.Aleksa.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import jakarta.servlet.http.HttpServletRequest;e

@Controller
public class AdminController {

	
	private ContactFormService cfs;
	
	@Autowired
	public void setCfs(ContactFormService cfs) {
		this.cfs = cfs;
	}

	
	private AdminCredentialsService acs;	
	
	@Autowired
	public void setAcs(AdminCredentialsService acs) {
		this.acs = acs;
	}
	
	private saveBookingFormService sbfs;
	
	@Autowired
	public void setSbfs(saveBookingFormService sbfs) {
		this.sbfs = sbfs;
	}
	
	private ServiceFormService sfs;
	
	@Autowired
	public void setSfs(ServiceFormService sfs) {
		this.sfs = sfs;
	}

	private BookingFormCrud order;
	
	@Autowired
	public void setBookingFormRepository(BookingFormCrud order) {
		this.order = order;
	}
	
	private ContactFormCrud orderContact;

	@Autowired
	public void setContactFormRepository(ContactFormCrud orderContact) {
		this.orderContact = orderContact;
	}

	private DriverFormService dc;
	
	@Autowired
	public void setDc(DriverFormService dc) {
		this.dc = dc;
	}
	
	private PasswordEncoder passwordEncoder;

	@Autowired
	public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
	    this.passwordEncoder = passwordEncoder;
	}
	

	private CarFormServiceImpl cc;

	@Autowired
	public void setCc(CarFormServiceImpl cc) {
		this.cc = cc;
	}


	private CarCrud cc;
	
	@Autowired
	public void setCarCrud(CarCrud cc) {
	    this.cc=cc;
	}
	

	@GetMapping(path={"admin/dashboard"})
	public String AboutView(HttpServletRequest req,Model m) {
		String requestURI=req.getRequestURI();
		m.addAttribute("mycurrentpage",requestURI);
	    return "/dashboard"; 
	}
	
	@GetMapping(path={"admin/readAllContacts"})
	public String readAllContacts(HttpServletRequest req,Model m) {
		String requestURI=req.getRequestURI();
		m.addAttribute("mycurrentpage",requestURI);
		m.addAttribute("allcontacts",cfs.displayContactService());
		m.addAttribute("allcontacts",orderContact.OrderByIdDesc());
	    return "/readAllContacts"; 
	}
	
	@GetMapping(path={"/deleteContact/{id}"})
	public String deleteContact(@PathVariable("id") int id,RedirectAttributes redirectAttribute) {
		cfs.deleteContact(id);
		redirectAttribute.addFlashAttribute("Message","Contact successfully deleted!");
		 return "redirect:/admin/readAllContacts";
	}

	@GetMapping("admin/changeCredentials")
	public String showChangeCredentialsForm() {
	    return "/changeCredentials";
	}

	@PostMapping(path={"/changeCredentials"})
	public String changeCredentials(@RequestParam("oldname") String oldname,
	        @RequestParam("newname") String newname,
	        @RequestParam("oldpassword") String oldpassword,
	        @RequestParam("newpassword") String newpassword,
	        RedirectAttributes redirectattribute) {
	    String result = acs.checkAdminCredentials(oldname, oldpassword);
	    if(result.equals("Success")) {
	    	result=acs.updateAdminCredentials(newname, newpassword, oldname);
	    	redirectattribute.addFlashAttribute("message",result);
	    }else {
	    	redirectattribute.addFlashAttribute("message",result);
	    }

	    return "redirect:/admin/changeCredentials";

	}

	@GetMapping(path={"admin/readAllBookings"})
	public String readallBooking(HttpServletRequest req,Model m) {
		String requestURI=req.getRequestURI();
		m.addAttribute("mycurrentpage",requestURI);
		m.addAttribute("allBookings",sbfs.readAllBooking());
		m.addAttribute("allBookings",order.findAllByOrderByIdDesc());
	    return "readAllBookings"; 
	}

	@GetMapping(path={"/deleteBooking/{id}"})
	public String deleteBooking(@PathVariable("id") int id,RedirectAttributes redirectAttribute) {
		sbfs.deleteBookingService(id);
		redirectAttribute.addFlashAttribute("Message","Booking successfully deleted!");
		 return "redirect:/admin/readAllBookings";
	}


	@GetMapping(path={"admin/deleteBooking/{id}"})
	public String deleteBooking(@PathVariable("id") int id,RedirectAttributes redirectAttribute) {
		sbfs.deleteBookingService(id);
		redirectAttribute.addFlashAttribute("Message","Contact successfully deleted!");
		 return "redirect:/admin/readAllBookings";
	}

	@GetMapping(path={"addService"})
	public String addServiceView() {
	    return "addService"; 
	}
	
	
	@PostMapping(path={"/addService"})
	public String addService(@ModelAttribute ServiceForm serviceform,
			@RequestParam("image") MultipartFile multipartFile,RedirectAttributes redirectAttribute) {
		
		String original=multipartFile.getOriginalFilename();
		serviceform.setImage(original);
		try {
			ServiceForm sf=sfs.addService(serviceform, multipartFile);
			if(sf!=null) {
				redirectAttribute.addFlashAttribute("message","Successfully added!");
			}else {
				redirectAttribute.addFlashAttribute("message","Something went wrong!");
			}
		} catch (Exception e) {
			redirectAttribute.addFlashAttribute("message","Something went very wrong!");
		}
		
		
	    return "redirect:/admin/addService"; 
	}
	
	
	@GetMapping(path={"insertDriver"})
	public String statsView(Model model) {
		model.addAttribute("driver", new Driver());
	    return "insertDriver"; 
	}

	@PostMapping("/admin/insertDriver")
    public String registerDriver(@Valid @ModelAttribute("driver") Driver driver,
    		BindingResult result,
    		@RequestParam("email") String email,
            RedirectAttributes redirectAttributes) {
        try {
        	if (result.hasErrors()) {
        		return "insertDriver";
        }
        	if(!dc.existsByEmail(email)) {
        	driver.setPassword(driver.getPassword());
            dc.saveDriverService(driver);
            redirectAttributes.addFlashAttribute("message", "Driver successfully registered!");
        }else{
        	redirectAttributes.addFlashAttribute("emailMessage", "Email is already taken!");
        }}catch (Exception e) {
        	redirectAttributes.addFlashAttribute("message", "Error: " + e.getMessage());
        }
        return "redirect:/admin/insertDriver";
    }
    public String registerDriver(@ModelAttribute("driver") Driver driver, 
                               RedirectAttributes redirectAttributes) {
        try {
            driver.setPassword(driver.getPassword());
            dc.saveDriverService(driver);
            redirectAttributes.addFlashAttribute("message", "Driver successfully registered!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("message", "Error: " + e.getMessage());
        }
        return "redirect:/admin/insertDriver";
    }
	@PostMapping("admin/insertDriver")
	public String registerDriver(@ModelAttribute("driver") Driver driver, RedirectAttributes redirectAttributes) {
	    try {
	        String encodedPassword = passwordEncoder.encode(driver.getPassword());
	        driver.setPassword(encodedPassword);

	        dc.saveDriverService(driver);
	        redirectAttributes.addFlashAttribute("message", "Driver successfully registered!");
	    } catch (Exception e) {
	        redirectAttributes.addFlashAttribute("message", "Error: " + e.getMessage());
	    }

	    return "redirect:/admin/insertDriver";
	}
	
	@GetMapping(path={"admin/readAllDrivers"})
	public String readallDriver(HttpServletRequest req,Model m) {
		String requestURI=req.getRequestURI();
		m.addAttribute("mycurrentpage",requestURI);
		m.addAttribute("allDrivers",dc.displayDriverService());
		return "readAllDrivers";
	}
	
	@GetMapping(path={"/deleteDriver/{id}"})
	public String deleteDrivers(@PathVariable("id") int id,RedirectAttributes redirectAttribute) {
		dc.deleteDriver(id);
		redirectAttribute.addFlashAttribute("Message","Driver successfully deleted!");
		return "redirect:/admin/readAllDrivers";
	}
	
	private DriverCrud fid;
	
	@Autowired
	public void setFid(DriverCrud fid) {
		this.fid = fid;
	}

	@GetMapping("/updateDriver/{id}")
	public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
	    Driver driver = fid.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid driver ID: " + id));

	    model.addAttribute("driver", driver);
	    return "updateDriver"; 
	}

	@PostMapping("/updateDriver")
	public String updateDriver(@Valid @ModelAttribute("driver") Driver driver,
			BindingResult result,@RequestParam("password") String password,
			RedirectAttributes redirectAttribute) {
	public String updateDriver(@ModelAttribute("driver") Driver driver,
			@RequestParam("password") String password,
			RedirectAttributes redirectAttribute) {
	public String updateDriver(@ModelAttribute("driver") Driver driver,RedirectAttributes redirectAttribute) {
		Driver existingDriver=fid.findById(driver.getId()).orElse(null);
		if(existingDriver==null) {
			return "redirect:/admin/readAllDrivers";
		}
		
		if(result.hasErrors()) {
			return "updateDriver";
		}
		existingDriver.setUsername(driver.getUsername());
	    existingDriver.setFullName(driver.getFullName());
	    existingDriver.setEmail(driver.getEmail());
	    existingDriver.setPhone(driver.getPhone());
	    existingDriver.setLicence(driver.getLicence());
    	System.out.println(password);
    	
	    if(password!=null && !password.isBlank()) {
	         existingDriver.setPassword(password);
		

	    if(driver.getPassword()!=null && !driver.getPassword().isBlank()) {
	    	 String encryptedPassword = passwordEncoder.encode(driver.getPassword());
	         existingDriver.setPassword(encryptedPassword);
	    }
	    
	    if(dc.saveDriverService(existingDriver)!=null) {
	    	redirectAttribute.addFlashAttribute("UpdateMessage","Driver successfully updated!");
	    }
		return "redirect:/admin/readAllDrivers";
	}
	
	@GetMapping(path={"admin/AssignCar"})
	public String AssignCarView(Model model) {
		model.addAttribute("drivers", dc.displayDriverService());
	    model.addAttribute("cars", cc.displayCar());       
	    model.addAttribute("cars", cc.findAll());       
	    return "AssignCar"; 
	}
	
	@PostMapping(path= {"admin/AssignCar"})
	public String assignCarToDriver(
			@RequestParam("carSelect") String carModel,
			@RequestParam String plateNumber,
			@RequestParam("driverId") int driverID,
			RedirectAttributes redirectAttributes) {
		try {
			
			Driver driver = fid.findById(driverID)
	                .orElseThrow(() -> new RuntimeException("Driver not found"));

	        Car existingCar = cc.findByDriverId(driverID).orElse(null);

	        if (existingCar != null) {
	            existingCar.setPlateNumbers(plateNumber);
	            existingCar.setModel(carModel);
	            cc.saveCarService(existingCar);

	            redirectAttributes.addFlashAttribute("message", "Car successfully updated!");
	        } else {
	            Car newCar = new Car();
	            newCar.setModel(carModel);
	            newCar.setPlateNumbers(plateNumber);
	            newCar.setDriver_id(driver);

	            cc.saveCarService(newCar);
	            redirectAttributes.addFlashAttribute("message", "Car successfully registered!");
			
		}} catch (Exception e) {
			Driver driver=fid.findById(driverID).orElseThrow(()->new RuntimeException("Driver not found"));
			Car car=new Car();
			
			car.setModel(carModel);
			car.setDriver_id(driver);
			car.setPlateNumbers(plateNumber);
			cc.save(car);
			
			redirectAttributes.addFlashAttribute("message","Successfully assigned car!");
			
		} catch (Exception e) {
			 redirectAttributes.addFlashAttribute("message", "Error: " + e.getMessage());
		     e.printStackTrace(); 
		}
		return "redirect:/admin/AssignCar";
	}

	@GetMapping(path={"AssignCar"})
	public String AssignCarView(Model model) {
		model.addAttribute("driver", dc.displayDriverService());
	    return "AssignCar"; 
	}
}
