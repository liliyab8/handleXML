package com.ex.handleXML.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ex.handleXML.model.RequestEntity;

@Repository
public interface RequestEntityDao extends JpaRepository<RequestEntity, Long> {	
	
	@Query("SELECT r FROM RequestEntity r WHERE r.insuredId = ?1 group by sourceCompany, r.id")
	List<RequestEntity> selectRequestByInsuredId(String insuredId);
}