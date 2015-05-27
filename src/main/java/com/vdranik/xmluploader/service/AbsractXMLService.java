package com.vdranik.xmluploader.service;

import java.io.IOException;
import java.io.InputStream;

import javax.xml.bind.JAXBException;

import org.springframework.web.multipart.MultipartFile;

public interface AbsractXMLService<T> {
	
	T unmarshalXML(InputStream inputStream) throws JAXBException;
	T unmarshalXML(String xmlPath) throws JAXBException;
	void marshallXML(T t) throws JAXBException;;
	void updateServerCatalog(MultipartFile file) throws JAXBException, IOException;
	
}