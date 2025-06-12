package com.Aleksa.demo;

public interface AdminCredentialsService {

	public String checkAdminCredentials(String oldname,String oldpassword);
	public String updateAdminCredentials(String newname,String newpassword,String oldname);
}
