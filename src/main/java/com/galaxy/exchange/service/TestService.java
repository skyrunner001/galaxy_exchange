package com.galaxy.exchange.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.galaxy.exchange.common.page.QueryPageRequest;
import com.galaxy.exchange.dao.BaseDAO;
import com.galaxy.exchange.dao.LogDIc.LogDicDAO;
import com.galaxy.exchange.entity.LogDic;

@Service
@Transactional
public class TestService {
	
	@Autowired
	private BaseDAO baseDAO;
	
	@Autowired
	private LogDicDAO logDicDAO;
	
	public static final Logger log	= LoggerFactory.getLogger(TestService.class);
	
	public void test1(){
		String hql = "from LogDic";
		List<LogDic> list = (List<LogDic>)baseDAO.queryListByHqlMapparams(hql, null);
		log.info(String.valueOf(list.size()));
	}
	
	public void test2(){
		int pageNo = 1;
		int pageSize = 10;
		Sort sort = initSortOfScore();
		Pageable pageable = new QueryPageRequest(pageNo,pageSize,sort);
		Page<LogDic> page = logDicDAO.findLogdic(1l, pageable);
		log.info(String.valueOf(page.getTotalElements()));
		
	}
	
	private Sort initSortOfScore() {
		List<Order> list = new ArrayList<Order>();
		Order order = new Order(Direction.DESC, "typeId");
		list.add(order);
		Sort sort = new Sort(list);
		return sort;
	}
}
