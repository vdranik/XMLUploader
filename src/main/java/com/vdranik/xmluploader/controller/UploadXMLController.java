package com.vdranik.xmluploader.controller;

import java.io.IOException;

import javax.xml.bind.JAXBException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.vdranik.xmluploader.validator.FileUploadBean;
import com.vdranik.xmluploader.validator.FileUploadValidator;

@Controller
public class UploadXMLController extends AbstractController {

	@Autowired
	private FileUploadValidator fileUploadValidator;

	private static final Logger LOGGER = LoggerFactory
			.getLogger(UploadXMLController.class);

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home() {
		LOGGER.info("Load home.jsp page (upload XML)");
		return "home";
	}

	@RequestMapping(value = "/uploadXML", method = RequestMethod.POST)
	public ModelAndView uploadXML(
			@ModelAttribute("uploadedFile") FileUploadBean fileUploadBean,
			BindingResult result) {

		MultipartFile file = fileUploadBean.getFile();
		fileUploadValidator.validate(fileUploadBean, result);

		if (result.hasErrors()) {
			LOGGER.info("/uploadXML: XML don't validate. " + result.toString());
			return new ModelAndView("home");
		}

		try {
			xmlService.updateServerCatalog(file);
		} catch (JAXBException | IOException e) {
			LOGGER.info("/uploadXML: " + e.getMessage());
			e.printStackTrace();
		}

		return new ModelAndView("home", "message", file);
	}
}