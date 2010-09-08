package com.bekk.wa.webframework.database.utils;

import java.net.URL;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

@ContextConfiguration(locations = { "/testAppCtx.xml" })
public abstract class SpringJunit4Base extends AbstractJUnit4SpringContextTests {

	// configure log4j
	static {
		URL log4j = Thread.currentThread().getContextClassLoader().getResource(
				"log4j.xml");
		DOMConfigurator.configure(log4j);
	}

	protected Logger logger = Logger.getLogger(getClass());
/*
	protected String[] getConfigLocations() {
		return new String[] { "classpath:applicationContext.xml",
				"classpath:testAppCtx.xml" };
	}
*/

}
