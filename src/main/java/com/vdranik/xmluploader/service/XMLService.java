package com.vdranik.xmluploader.service;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.vdranik.xmluploader.jaxb.Catalog;
import com.vdranik.xmluploader.jaxb.Cd;

@Component
public class XMLService implements AbsractXMLService<Catalog> {

	public static final Class<Catalog> CLAZZ = Catalog.class;
	public static final String MAIN_TAG = "TITLE";
	public static final String SERVER_XML_PATH = "D:/System/workspace2015/XMLUploader/src/main/resources/cd_catalog.xml";

	@Autowired
	private TitleComparator titleComparator;

	@Override
	public Catalog unmarshalXML(InputStream inputStream) throws JAXBException {
		JAXBContext jaxbContext = JAXBContext.newInstance(CLAZZ);
		Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
		return (Catalog) jaxbUnmarshaller.unmarshal(inputStream);
	}

	@Override
	public Catalog unmarshalXML(String xmlPath) throws JAXBException {
		File file = new File(xmlPath);
		JAXBContext jaxbContext = JAXBContext.newInstance(CLAZZ);
		Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
		return (Catalog) jaxbUnmarshaller.unmarshal(file);
	}

	@Override
	public void marshallXML(Catalog t) throws JAXBException {
		final File file = new File(SERVER_XML_PATH);
		JAXBContext jaxbContext = JAXBContext.newInstance(CLAZZ);
		Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

		jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		jaxbMarshaller.marshal(t, file);
		jaxbMarshaller.marshal(t, System.out);
	}

	@Override
	public void updateServerCatalog(MultipartFile file) throws JAXBException,
			IOException {
		Catalog inputCatalog = unmarshalXML(file.getInputStream());
		Catalog serverCatalog = unmarshalServerXML();

		serverCatalog.getCdMap().putAll(inputCatalog.getCdMap());
		serverCatalog.getCds().clear();
		List<Cd> cds = new LinkedList<Cd>(serverCatalog.getCdMap().values());
		java.util.Collections.sort(cds, titleComparator);
		serverCatalog.setCds(cds);

		marshallXML(serverCatalog);
	}

	public Catalog unmarshalServerXML() throws JAXBException {
		return (Catalog) unmarshalXML(SERVER_XML_PATH);
	}
}