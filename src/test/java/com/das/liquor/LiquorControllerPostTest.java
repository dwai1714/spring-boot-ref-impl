package com.das.liquor;

import java.net.URL;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.audit.client.AuditClient;
import com.das.liquor.model.Liquor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author DC
 *
 */
// @TODO: need more tests
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class LiquorControllerPostTest {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	
	@Autowired
	private TestRestTemplate testRestTemplate;

	@LocalServerPort
	private int port;

	private URL base;
	
	@Autowired
	private AuditClient aClient ;
	


	


	@Autowired
	private ObjectMapper objectMapper;

	@Before
	public void setUp() throws Exception {
		this.base = new URL("http://localhost:" + port + "/");
	}

	/**
	 * @throws JsonProcessingException
	 * @throws Exception
	 */

	@Test
	public void postTest() throws Exception {
		logger.debug("Testing attribute Controller method for adding Class");
		Liquor item1 = new Liquor(1L, "100 Pipers", "100 Pipers 12 Year", "Whisky", 48, 12, 1, 1994);


		String url = this.base.toString() + "api/v1/liquors";
		//AuditService audService = new AuditService("1", "Liquor", item1);
		
		//audService.createAuditLog();

		ResponseEntity<Liquor> response = testRestTemplate.postForEntity(url, item1, Liquor.class);
		HttpStatus statusCode = response.getStatusCode();
		
		//assertThat(response.getStatusCode().is2xxSuccessful());
		//System.out.println("auditServerUrl is " + this.getServerUrl());
					
		//logger.info("Post Status Code " + statusCode.toString());
		//System.out.println("Post Status Code " + statusCode.toString());

	}
//	@Test
	public void getTest() throws Exception {
		aClient.getAuditLog("1", "Liq");


	}
//	@Test
	public void getOneObjectFromHistoryTest() throws Exception {
		aClient.getOneHistoryLog("5973c8a2bd5f8041c3ef71a0");


	}

}
