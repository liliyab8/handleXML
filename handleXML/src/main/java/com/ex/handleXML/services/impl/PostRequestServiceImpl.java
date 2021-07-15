/**
 *
 */
package com.ex.handleXML.services.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.ValidationEvent;
import javax.xml.bind.ValidationEventHandler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.ex.handleXML.dto.Event;
import com.ex.handleXML.dto.Product;
import com.ex.handleXML.dto.Request;
import com.ex.handleXML.dto.RequestDetails;
import com.ex.handleXML.dto.Root;
import com.ex.handleXML.mapping.RequestMappingUtils;
import com.ex.handleXML.model.RequestEntity;
import com.ex.handleXML.repository.RequestEntityDao;
import com.ex.handleXML.services.PostRequestService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * @author liliyab
 *
 */
@Service
public class PostRequestServiceImpl implements PostRequestService {

	@Autowired
	RequestMappingUtils requestMappingUtils;
	@Autowired
	RequestEntityDao requestEntityDao;
	@Value("${input.file.path}")
	private String inpuFilePath;

	public String convertXmlToObject() throws Exception {
		Root xmlRequest = null;
		List<Root> xmlRequestList = new ArrayList();
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").setPrettyPrinting().create();

		try {

			File folder = new File(inpuFilePath);
			File[] listOfFiles = folder.listFiles();

			if (listOfFiles != null && listOfFiles.length > 0) {
				JAXBContext jaxbContext = JAXBContext.newInstance(Root.class);
				Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
//test - check why not maps
				jaxbUnmarshaller.setEventHandler(new ValidationEventHandler() {
					@Override
					public boolean handleEvent(ValidationEvent event) {
						throw new RuntimeException(event.getMessage(), event.getLinkedException());}});

				for (int i = 0; i < listOfFiles.length; i++) {
					if (listOfFiles[i].isFile() && listOfFiles[i].length() > 0) {
						xmlRequest = (Root) jaxbUnmarshaller.unmarshal(listOfFiles[i]);
						xmlRequestList.add(xmlRequest);
					}
				}
				System.out.println(xmlRequestList.toString());
				List<Request> requestList = mapRequestList(xmlRequestList);
				List<RequestEntity> requestEntityList = requestMappingUtils.requestListToRequestEntityList(requestList);
				requestEntityDao.saveAll(requestEntityList);

				return gson.toJson(xmlRequest);
			}
//InputStream is = JacksonXml.class.getResourceAsStream("/countries.xml");
		} catch (JAXBException e) {
			e.printStackTrace();
		}
	}

	private List<Request> mapRequestList(List<Root> rootList) {
		List<Request> requestList = rootList.stream().map(rl -> mapRequest(rl)).collect(Collectors.toList()).stream()
				.flatMap(List::stream).collect(Collectors.toList());
		return requestList;
	}

	private List<Request> mapRequest(Root root) {
		List<Request> requestList = root.getEvent().stream().map(e -> mapEventAndProduct(e))
				.collect(Collectors.toList()).stream().flatMap(List::stream).collect(Collectors.toList()).stream()
				.map(r -> mapRequestDetails(root.getRequestDetails(), r)).collect(Collectors.toList());
		return requestList;
	}

	private Request mapRequestDetails(RequestDetails requestDetails, Request request) {
		request.setRequestId(requestDetails.getId());
		request.setSourceCompany(requestDetails.getSourceCompany());
		request.setAcceptDate(requestDetails.getAcceptDate());
		return request;
	}

	private List<Request> mapEventAndProduct(Event event) {
		List<Request> requestList = event.getProduct().stream().map(p -> mapProduct(p)).collect(Collectors.toList());
		requestList.stream().forEach(r -> mapEvent(event, r));
		return requestList;
	}

	private Request mapEvent(Event event, Request request) {
		request.setEventType(event.getType());
		request.setEventId(event.getId());
		request.setInsuredId(event.getInsuredId());
		return request;
	}

	public Request mapProduct(Product product) {
		Request request = new Request();
		request.setStartDate(product.getStartDate());
		request.setProductType(product.getType());
		request.setPrice(product.getPrice());
		request.setEndDate(product.getEndDate());
		return request;
	}
}
