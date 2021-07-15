/**
 *
 */
package com.ex.handleXML.rest;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ex.handleXML.model.RequestEntity;
import com.ex.handleXML.services.GetRequestService;
import com.ex.handleXML.services.PostRequestService;

/**
 * @author liliyab
 *
 */
@RestController
@RequestMapping("/api")
public class RequestController {
	@Autowired
	PostRequestService postRequestService;
	@Autowired
	GetRequestService getRequestService;
		
	@RequestMapping(value = "/selectByInsuredId/{insuredId}", produces = "application/json", method = { RequestMethod.GET })	
	public List<RequestEntity> selectByInsuredId( @PathVariable("insuredId")  String insuredId) throws Exception {
			return getRequestService.selectRequestByInsuredId(insuredId);	
	}
	
	@RequestMapping(value = "/request", produces = "application/json", method = { RequestMethod.GET })
	@Scheduled(cron = "${read.file.interval}")
	public String handleRequest() throws Exception {
		System.out.println("The time is now: " + LocalDateTime.now());
		return postRequestService.convertXmlToObject();
	}
}