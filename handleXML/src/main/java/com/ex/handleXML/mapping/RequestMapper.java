package com.ex.handleXML.mapping;

import java.util.List;

import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.factory.Mappers;

import com.ex.handleXML.dto.Request;
import com.ex.handleXML.model.RequestEntity;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface RequestMapper {

	RequestMapper INSTANCE = Mappers.getMapper(RequestMapper.class);

	RequestEntity requestToRequestEntity(Request request);	
	
	@IterableMapping(qualifiedByName = "requestToRequestEntity")
	List<RequestEntity> requestListToRequestEntityList(List<Request> request);
	
	Request requestEntityToRequest(RequestEntity requestEntity);
	List<Request> requestEntityListToRequestList(List<RequestEntity> requestEntityList);
}