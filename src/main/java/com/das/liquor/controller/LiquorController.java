package com.das.liquor.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.das.liquor.model.Liquor;
import com.das.liquor.repository.LiquorRepository;

@RestController
@RequestMapping("api/v1/")
public class LiquorController {
	@Autowired
	private LiquorRepository liquorRepository;
	@Autowired
	private com.audit.client.AuditClient auditClient;

	@RequestMapping(value = "liquors", method = RequestMethod.GET)
	public List<Liquor> list() {
		return liquorRepository.findAll();
	}

	@RequestMapping(value = "liquors", method = RequestMethod.POST)
	public Liquor create(@RequestBody Liquor liquor) {

		liquor.setCreatedUser("DC");
		liquor.setCreatedDate(new Date());
		Liquor returnLiqor =  liquorRepository.save(liquor);
		auditClient.createAuditLog(returnLiqor.getId().toString(), "Liq", returnLiqor);
		return returnLiqor;

	}

	@RequestMapping(value = "liquors/{id}", method = RequestMethod.GET)
	public Liquor get(@PathVariable Long id) {
		return liquorRepository.findOne(id);
	}

	@RequestMapping(value = "liquors/{id}", method = RequestMethod.DELETE)
	public Liquor delete(@PathVariable Long id) {
		Liquor existingLiquor = liquorRepository.findOne(id);
		liquorRepository.delete(existingLiquor);
		return existingLiquor;
	}

	@RequestMapping(value = "liquors/{id}", method = RequestMethod.PUT)
	public Liquor update(@PathVariable Long id, @RequestBody Liquor liquor) {
		Liquor existingLiquor = liquorRepository.findOne(id);
		existingLiquor.setLastModifiedBy("DC_MOD");
		existingLiquor.setLastModifiedDate(new Date());


		BeanUtils.copyProperties(liquor, existingLiquor);
		auditClient.createAuditLog(id.toString(), "Liq", existingLiquor);

		return liquorRepository.saveAndFlush(existingLiquor);
	}	
	
	@RequestMapping(value = "liquors/history/{id}", method = RequestMethod.GET)
	public List<Map> history(@PathVariable Long id) {
		List <Map> liquorHistory = auditClient.getAuditLog(id.toString(), "Liq");

		return liquorHistory;
	}
	
	@RequestMapping(value = "liquors/history/one/{historyObjectId}", method = RequestMethod.GET)
	public Map getOneLiquor(@PathVariable String historyObjectId) {
		return auditClient.getOneHistoryLog(historyObjectId);
	}


}
