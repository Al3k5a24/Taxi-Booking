package com.Aleksa.demo;

import java.io.FileOutputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import jakarta.transaction.Transactional;
@Service
public class ServiceFormServiceImpl implements ServiceFormService {

	private ServiceFormCrud sfc;
	
	@Autowired
	public void setSfc(ServiceFormCrud sfc) {
		this.sfc = sfc;
	}
	
	@Transactional(rollbackOn=Exception.class)
	@Override
	public ServiceForm addService(ServiceForm serviceform, MultipartFile multipartfile) throws Exception {
		ServiceForm save=null;
		try {
			save=sfc.save(serviceform);
			if(save!=null) {
				String path="F:\\Java\\Java + Spring Boot\\demo\\src\\main\\resources\\static\\serviceimages\\"+multipartfile.getOriginalFilename();
				byte[] bytes=multipartfile.getBytes();
				
				FileOutputStream fos=new FileOutputStream(path);
				fos.write(bytes);	
			}

		} catch (Exception e) {
			save=null;
			throw e;
		}
		return save;
	}


}
