package com.Aleksa.demo;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

public interface ServiceFormService {

	public ServiceForm addService(ServiceForm serviceform,MultipartFile multipartfile) throws Exception;
}
