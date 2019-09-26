package com.yuhang.stock.dbservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.yuhang.stock.dbservice.model.Quote;

public interface QuotesRepository extends JpaRepository<Quote, Integer>{

	List<Quote> findByUserName(String username);	
	
	// We have to tell Spring to treat that query as native one by |nativeQuery = true|. 
	// Otherwise it will try to validate the query according to the JPA specification.
	@Query(value = "select * from quotes where user_name=?1 and quote=?2", nativeQuery = true)
	List<Quote> findByUserNameAndSymbol(String username, String symbol);
}