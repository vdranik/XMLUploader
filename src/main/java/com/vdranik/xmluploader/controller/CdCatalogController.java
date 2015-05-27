package com.vdranik.xmluploader.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Collection;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBException;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.vdranik.xmluploader.jaxb.Catalog;
import com.vdranik.xmluploader.jaxb.Cd;
import com.vdranik.xmluploader.service.XMLService;

@Controller
public class CdCatalogController extends AbstractController {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(CdCatalogController.class);

	@RequestMapping(value = "/cdcatalog", method = RequestMethod.GET)
	public String cdcatalog(Model model) {
		LOGGER.info("Load cdcatalog.jsp page");

		Catalog catalog = null;

		try {
			catalog = xmlService.unmarshalServerXML();
		} catch (JAXBException e) {
			LOGGER.info("/cdcatalog: " + e.getMessage());
			e.printStackTrace();
		}

		Collection<Cd> cds = catalog.getCds();
		model.addAttribute("cds", cds);
		return "cdcatalog";
	}

	@RequestMapping(value = "/cdcatalog/downloadFiles", method = RequestMethod.GET)
	public @ResponseBody void downloadFiles(HttpServletRequest request,
			HttpServletResponse response) {

		ServletContext context = request.getServletContext();
		File downloadFile = new File(XMLService.SERVER_XML_PATH);
		FileInputStream inputStream = null;
		OutputStream outStream = null;

		try {
			inputStream = new FileInputStream(downloadFile);

			response.setContentLength((int) downloadFile.length());
			response.setContentType(context
					.getMimeType(XMLService.SERVER_XML_PATH));

			String headerKey = "Content-Disposition";
			String headerValue = String.format("attachment; filename=\"%s\"",
					downloadFile.getName());
			response.setHeader(headerKey, headerValue);

			outStream = response.getOutputStream();
			IOUtils.copy(inputStream, outStream);

			LOGGER.info("Successful download file!");
		} catch (Exception e) {
			LOGGER.info("/cdcatalog/downloadFiles: " + e.getMessage());
			e.printStackTrace();
		} finally {
			try {
				if (null != inputStream)
					inputStream.close();
				if (null != inputStream)
					outStream.close();
			} catch (IOException e) {
				LOGGER.info("/cdcatalog/downloadFiles: " + e.getMessage());
				e.printStackTrace();
			}
		}
	}
}