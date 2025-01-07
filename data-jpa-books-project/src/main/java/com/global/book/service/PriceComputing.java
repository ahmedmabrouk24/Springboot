package com.global.book.service;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import lombok.extern.log4j.Log4j2;
import net.javacrumbs.shedlock.spring.annotation.SchedulerLock;

@Component
@Log4j2
public class PriceComputing { // class to test schedule
	
	//@Scheduled(fixedRate = 2000)
	@SchedulerLock(name = "bookComputePrice")
	@Async
	public void computPrice() throws InterruptedException { 
		Thread.sleep(4000);
		log.info("Computing price");
	}
	
	//@Scheduled(fixedRate = 2000)
	@SchedulerLock(name = "bookComputeDiscound")
	@Async
	public void computDiscound() throws InterruptedException { 
		Thread.sleep(4000);
		log.info("Computing discound");
	}
}
