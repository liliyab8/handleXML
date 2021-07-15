package com.ex.handleXML.services;

import java.util.List;

import com.ex.handleXML.model.RequestEntity;

public interface GetRequestService {
	public List<RequestEntity> selectRequestByInsuredId(String insuredId) throws Exception;
}
