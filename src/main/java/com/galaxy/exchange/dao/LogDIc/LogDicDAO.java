package com.galaxy.exchange.dao.LogDIc;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.galaxy.exchange.entity.LogDic;

public interface LogDicDAO extends JpaRepository<LogDic, Long> {

	@Query("from LogDic l where l.id != ?")
	public Page<LogDic> findLogdic(Long id, Pageable pageable);
	
	
}
