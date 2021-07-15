package com.ex.handleXML.dto;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * @author liliyab
 *
 */
@XmlRootElement(name = "Root")
@XmlAccessorType(XmlAccessType.FIELD)
@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Root {
	@XmlElement
	private List<Event> Event;
	@XmlElement
	private RequestDetails RequestDetails;
}
