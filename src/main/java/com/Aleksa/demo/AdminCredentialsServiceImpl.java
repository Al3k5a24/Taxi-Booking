package com.Aleksa.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AdminCredentialsServiceImpl implements AdminCredentialsService {

	private AdminCrud admincrud;
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
		this.passwordEncoder = passwordEncoder;
	}

	@Autowired
	public void setAdmincrud(AdminCrud admincrud) {
		this.admincrud = admincrud;
	}
	
	@Override
	public String checkAdminCredentials(String oldname, String oldpassword) {
		java.util.Optional<Admin> byUsername=admincrud.findByUsername(oldname);
		if(byUsername.isPresent()) {
			Admin admin=byUsername.get();
			boolean matches=passwordEncoder.matches(oldpassword,admin.getPassword());
			if(matches) {
				return "Success";
			}else {
				return "Wrond credentials!";
			}
		}else {
			return "Wrong Credentials";
		}
	}

	@Override
	public String updateAdminCredentials(String newname, String newpassword, String oldname) {
	   int updateadmin=admincrud.updateCredentials(newname,passwordEncoder.encode(newpassword), oldname);
	
	   if(updateadmin==1) {
		   return "Updated successfully!";
	   }else {
		   return "Failed";
	   }
	}

}
