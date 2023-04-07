package cn.shukuo.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import cn.shukuo.entity.SiteItem;
import cn.shukuo.service.ItemService;
import cn.shukuo.utils.JSONResult;

@RestController
public class ItemListController {
	private final static Logger logger = LoggerFactory.getLogger(ItemListController.class);

	@Autowired
	private ItemService service;

	@GetMapping("/getList")
	private JSONResult getList() {

		try {
			List<SiteItem> e = service.getList();
			if (e != null)
				logger.info("Get item list {} records", e.size());
			return JSONResult.ok(e);

		} catch (Exception e) {
			logger.error("Get item list failed.");
			throw e;
		}
	}
	
	@GetMapping("/getItemById/{id}")
	private JSONResult getItemById(@PathVariable long id) {

		try {
			SiteItem e = service.getDetail(id);
//			System.out.println(e);
			if (e != null)
				logger.info("Get item details records");
			return JSONResult.ok(e);

		} catch (Exception e) {
			logger.error("Get item details failed.");
			throw e;
		}
	}

}
