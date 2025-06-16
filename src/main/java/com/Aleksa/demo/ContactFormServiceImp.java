package com.Aleksa.demo;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContactFormServiceImp implements ContactFormService{

	private ContactFormCrud cfc;

	@Autowired
	public void setCfc(ContactFormCrud cfc) {
		this.cfc = cfc;
	}



	@Override
	public ContactForm saveContactService(ContactForm contactform) {
		return cfc.save(contactform);
		
		
	}

	@Override
	public List<ContactForm> displayContactService() {
		return cfc.findAll();
	}



	@Override
	public void deleteContact(int id) {
		cfc.deleteById(id);
	}

}
