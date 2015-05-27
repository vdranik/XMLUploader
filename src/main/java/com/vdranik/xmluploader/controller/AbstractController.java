package com.vdranik.xmluploader.controller;

import org.springframework.beans.factory.annotation.Autowired;

import com.vdranik.xmluploader.service.XMLService;

public abstract class AbstractController {

	@Autowired
	protected XMLService xmlService;
}
