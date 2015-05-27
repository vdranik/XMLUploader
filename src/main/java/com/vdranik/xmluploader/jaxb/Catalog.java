package com.vdranik.xmluploader.jaxb;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="CATALOG")
@XmlAccessorType(XmlAccessType.NONE)
public class Catalog {

	@XmlElement(name="CD")
	private Collection<Cd> cds = new LinkedList<Cd>();
	private Map<String, Cd> cdMap = new HashMap<String, Cd>();
	 
	public Collection<Cd> getCds() {
		return cds;
	}

	public void setCds(Collection<Cd> cds) {
		this.cds = cds;
	}

	public Map<String, Cd> getCdMap() {
		for(Cd cd: cds){
			cdMap.put(cd.getTitle(), cd);
		}
		return cdMap;
	}
}