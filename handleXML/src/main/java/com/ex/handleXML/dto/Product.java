package com.ex.handleXML.dto;

import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * @author liliyab
 *
 */
@XmlRootElement(name = "Product")
@XmlAccessorType(XmlAccessType.FIELD)
@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "PRODUCT")
public class Product {
	private String StartDate;
	private String Type;
	private String Price;
	private String EndDate;
}