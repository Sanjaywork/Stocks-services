package com.stocks.controller;

import com.stocks.constants.AppConstants;
import com.stocks.model.StocksModel;
import com.stocks.serviceimpl.StocksServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertEquals;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class StocksControllerTest {

    @InjectMocks
    private StocksController stocksController;

    @Mock
    private StocksServiceImpl stocksService;

    private StocksModel stocksModel;

    @Before
    public void init() {
        stocksModel = mock(StocksModel.class);
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testMockCreation(){
        assertNotNull(stocksController);
        assertNotNull(stocksService);
        assertNotNull(stocksModel);
    }

    @Test
    public void testSaveOrUpdateStockData() {
        Map<String, Object> respObject = new HashMap<>();
        respObject.put("responseMsg", AppConstants.RESPONSE_SAVE_SUCCESS);
        respObject.put("responseCode", AppConstants.RESPONSE_CODE_SUCCESS);
        respObject.put("data", stocksModel);

        when(stocksService.saveOrUpdateStockData(stocksModel)).thenReturn(respObject);

        ResponseEntity<?> stocksResp = stocksController.saveOrUpdateStockData(stocksModel);

        assertNotNull(stocksResp);
        assertEquals(HttpStatus.OK, stocksResp.getStatusCode());
        assertEquals(AppConstants.RESPONSE_SAVE_SUCCESS, ((Map<String, Object>)stocksResp.getBody()).get("responseMsg"));
        assertEquals(AppConstants.RESPONSE_CODE_SUCCESS, ((Map<String, Object>)stocksResp.getBody()).get("responseCode"));
    }

    @Test
    public void testFindByStock() {
        Map<String, Object> respObject = new HashMap<>();
        respObject.put("responseMsg", AppConstants.RESPONSE_SUCCESS_MSG);
        respObject.put("responseCode", AppConstants.RESPONSE_CODE_SUCCESS);
        respObject.put("stock", stocksModel);

        when(stocksService.findByStock("STOCK")).thenReturn(respObject);

        ResponseEntity<?> stocksResp = stocksController.findByStock("STOCK");

        assertNotNull(stocksResp);
        assertEquals(HttpStatus.OK, stocksResp.getStatusCode());
        assertEquals(AppConstants.RESPONSE_SUCCESS_MSG, ((Map<String, Object>)stocksResp.getBody()).get("responseMsg"));
        assertEquals(AppConstants.RESPONSE_CODE_SUCCESS, ((Map<String, Object>)stocksResp.getBody()).get("responseCode"));
    }

    @Test
    public void testBulkInsert() {
        Map<String, Object> respObject = new HashMap<>();
        respObject.put("responseMsg", AppConstants.RESPONSE_SUCCESS_MSG);
        respObject.put("responseCode", AppConstants.RESPONSE_CODE_SUCCESS);
        respObject.put("stock", stocksModel);

        MultipartFile multiPartFile = mock(MultipartFile.class);
        when(stocksService.batchInsert(multiPartFile)).thenReturn(respObject);

        ResponseEntity<?> stocksResp = null;
        try {
            stocksResp = stocksController.bulkInsert(multiPartFile);
            assertNotNull(stocksResp);
            assertEquals(HttpStatus.OK, stocksResp.getStatusCode());
            assertEquals(AppConstants.RESPONSE_SUCCESS_MSG, ((Map<String, Object>)stocksResp.getBody()).get("responseMsg"));
            assertEquals(AppConstants.RESPONSE_CODE_SUCCESS, ((Map<String, Object>)stocksResp.getBody()).get("responseCode"));
        } catch (Exception e) {
            assertNull(stocksResp);
        }
    }


}
