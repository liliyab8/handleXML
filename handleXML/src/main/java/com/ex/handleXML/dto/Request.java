package com.ex.handleXML.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@XmlRootElement(name = "Event")
@XmlAccessorType(XmlAccessType.FIELD)
@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Request {
	private String requestId;
	private String sourceCompany;
	private String acceptDate;
	private String eventId;
	private String eventType;
	private String insuredId;
	private String startDate;
	private String productType;
	private String price;
	private String endDate;
}