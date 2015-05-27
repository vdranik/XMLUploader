package com.vdranik.xmluploader.service;

import java.util.Comparator;

import org.springframework.stereotype.Component;

import com.vdranik.xmluploader.jaxb.Cd;

@Component
public class TitleComparator implements Comparator<Cd> {

	public int compare(Cd cd1, Cd cd2) {
		String title1 = cd1.getTitle();
		String title2 = cd2.getTitle();
		return title1.compareTo(title2);
	}
}