package com.vdranik.xmluploader.validator;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import org.xml.sax.SAXException;

@Component
public class FileUploadValidator implements Validator {

	private static final String VALID_EXTENSION = "xml";
	private static final String STANDART_SCHEMA_URL = "http://www.w3.org/2001/XMLSchema";
	private static final String XSD_PATH = "D:/System/workspace2015/XMLUploader/src/main/resources/cd_catalog.xsd";

	public boolean supports(Class<?> clazz) {
		return FileUploadBean.class.equals(clazz);
	}

	public void validate(Object target, Errors errors) {
		FileUploadBean fileUploadBean = (FileUploadBean) target;

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "file",
				"file.upload.required");

		if (fileUploadBean.getFile().isEmpty()) {
			errors.rejectValue("file", "file.upload.required");
			return;
		}

		String fileName = fileUploadBean.getFile().getOriginalFilename();

		if (!errors.hasFieldErrors("file") && !isValidFileExtension(fileName)) {
			errors.rejectValue("file", "file.upload.invalid.format");
			return;
		}

		try {
			if (!validateXMLSchema(XSD_PATH, fileUploadBean.getFile()
					.getInputStream())) {
				errors.rejectValue("file", "file.upload.xml.invalid.schema");
				return;
			}
		} catch (IOException e) {
			errors.rejectValue("file", "file.upload.inputstream.invalid");
		}
	}

	private boolean isValidFileExtension(String fileName) {
		String extension;
		int dotPos = fileName.lastIndexOf(".");
		extension = fileName.substring(dotPos + 1);
		return extension.equalsIgnoreCase(VALID_EXTENSION);
	}

	private boolean validateXMLSchema(String xsdPath, InputStream xml) {
		try {
			SchemaFactory factory = SchemaFactory
					.newInstance(STANDART_SCHEMA_URL);
			Schema schema = factory.newSchema(new File(xsdPath));
			javax.xml.validation.Validator validator = schema.newValidator();
			validator.validate(new StreamSource(xml));
		} catch (SAXException e) {
			System.out.println("Exception: " + e.getMessage());
			return false;
		} catch (IOException e) {
			System.out.println("Exception: " + e.getMessage());
			return false;
		}

		return true;
	}
}