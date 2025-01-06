package com.global.book.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import net.javacrumbs.shedlock.spring.annotation.SchedulerLock;

@Component
public class PriceComputing { // class to test schedule
	
	Logger log = LoggerFactory.getLogger(PriceComputing.class);
	
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
