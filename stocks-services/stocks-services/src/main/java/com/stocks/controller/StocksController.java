package com.stocks.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.stocks.constants.API;
import com.stocks.model.StocksModel;
import com.stocks.service.StocksService;

@RestController
public class StocksController {

	@Autowired
	private StocksService stocksService;

	@PostMapping("/")
	public ResponseEntity<?> saveOrUpdateStockData(@RequestBody StocksModel stocksModel) {
		return new ResponseEntity<Map<String, Object>>(stocksService.saveOrUpdateStockData(stocksModel), HttpStatus.OK);
	}
	
	@GetMapping(API.GET_STOCKS_BY_STOCK_NAME)
	public ResponseEntity<?> findByStock(@PathVariable String stock) {
		return new ResponseEntity<Map<String, Object>>(stocksService.findByStock(stock), HttpStatus.OK);
	}
	
	@PostMapping(API.BULK_INSERT)
	public ResponseEntity<?> bulkInsert(@RequestParam MultipartFile file) throws Exception 
	{
		return new ResponseEntity<Map<String, Object>>(stocksService.batchInsert(file),HttpStatus.OK);
	}
	
}
