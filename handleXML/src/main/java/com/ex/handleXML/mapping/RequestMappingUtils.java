package com.ex.handleXML.mapping;

import java.util.List;

import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;

import com.ex.handleXML.dto.Request;
import com.ex.handleXML.model.RequestEntity;

@Service
public class RequestMappingUtils {

	private RequestMapper mapper = Mappers.getMapper(RequestMapper.class);

	public List<RequestEntity> requestListToRequestEntityList(List<Request> requestList) {
		return mapper.requestListToRequestEntityList(requestList);
	}
	
	public List<Request> requestEntityListToRequestList(List<RequestEntity> requestEntityList) {
		return mapper.requestEntityListToRequestList(requestEntityList);
	}
}
