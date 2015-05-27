package com.vdranik.xmluploader.jaxb;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Cd {

	@XmlElement(name = "TITLE", required = true)
	private String title;

	@XmlElement(name = "ARTIST", required = true)
	private String artist;

	@XmlElement(name = "COUNTRY", required = true)
	private String country;

	@XmlElement(name = "COMPANY", required = true)
	private String company;

	@XmlElement(name = "PRICE", required = true)
	private double price;

	@XmlElement(name = "YEAR", required = true)
	private int year;

	public Cd() {
		super();
	}

	public Cd(String title, String artist, String country, String company,
			float price, int year) {
		super();
		this.title = title;
		this.artist = artist;
		this.country = country;
		this.company = company;
		this.price = price;
		this.year = year;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getArtist() {
		return artist;
	}

	public void setArtist(String artist) {
		this.artist = artist;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	@Override
	public String toString() {
		return "Cd [title=" + title + ", artist=" + artist + ", country="
				+ country + ", company=" + company + ", price=" + price
				+ ", year=" + year + "]";
	}
}