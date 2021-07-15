package com.ex.handleXML.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ex.handleXML.model.RequestEntity;
import com.ex.handleXML.repository.RequestEntityDao;
import com.ex.handleXML.services.GetRequestService;

@Service
public class GetRequestServiceImpl implements GetRequestService {
	
	@Autowired RequestEntityDao requestEntityDao;

	@Override
	public List<RequestEntity> selectRequestByInsuredId(String insuredId) throws Exception {
		List<RequestEntity> requestEntityList= requestEntityDao.selectRequestByInsuredId(insuredId);
		System.out.println("***" + requestEntityList.toString());
		return requestEntityList;
	}
}