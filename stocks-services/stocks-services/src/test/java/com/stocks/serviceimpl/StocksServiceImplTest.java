package com.stocks.serviceimpl;

import com.stocks.constants.AppConstants;
import com.stocks.dao.StocksDao;
import com.stocks.entity.StocksEntity;
import com.stocks.model.StocksModel;
import com.stocks.utils.ObjectMapperUtils;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class StocksServiceImplTest {

    @InjectMocks
    private StocksServiceImpl stocksService;

    @Mock
    private StocksDao stocksDao;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testMockCreation(){
        assertNotNull(stocksDao);
        assertNotNull(stocksService);
    }

    @Test
    public void testSaveOrUpdateStockData() {
        StocksEntity respEntity = new StocksEntity();
        respEntity.setId("1");
        respEntity.setQuarter("Q1");

        StocksModel stocksModel = ObjectMapperUtils.map(respEntity, StocksModel.class);
        when(stocksDao.saveOrUpdateStockData(any(StocksEntity.class))).thenReturn(respEntity);

        Map<String, Object> responseData = stocksService.saveOrUpdateStockData(stocksModel);

        assertEquals(AppConstants.RESPONSE_SAVE_SUCCESS, responseData.get("responseMsg"));
        assertEquals(AppConstants.RESPONSE_CODE_SUCCESS, responseData.get("responseCode"));
        assertEquals(stocksModel.getId(), ((StocksModel) responseData.get("data")).getId());
        assertEquals(stocksModel.getQuarter(), ((StocksModel) responseData.get("data")).getQuarter());
    }

    @Test
    public void testSaveOrUpdateStockDataWithNullObject() {
        StocksEntity respEntity = new StocksEntity();
        respEntity.setId("1");
        respEntity.setQuarter("Q1");

        when(stocksDao.saveOrUpdateStockData(any(StocksEntity.class))).thenReturn(respEntity);

        Map<String, Object> responseData = stocksService.saveOrUpdateStockData(null);

        assertEquals(AppConstants.RESPONSE_SAVE_FAILIURE, responseData.get("responseMsg"));
        assertEquals(AppConstants.RESPONSE_CODE_FAILURE, responseData.get("responseCode"));
        assertNull(responseData.get("data"));
    }

    @Test
    public void testFindByStock() {
        List<StocksEntity> stocksEntityList = new ArrayList<>();
        StocksEntity respEntity = new StocksEntity();
        respEntity.setId("1");
        respEntity.setQuarter("Q1");

        stocksEntityList.add(respEntity);

        when(stocksDao.findByStock("STOCK")).thenReturn(stocksEntityList);
        when(stocksDao.findByStock("WRONG_STOCK")).thenReturn(null);

        List<StocksModel> stockModelList = ObjectMapperUtils.mapAll(stocksEntityList, StocksModel.class);

        Map<String, Object> responseData = stocksService.findByStock("STOCK");

        assertEquals(stockModelList.size(),((List<StocksModel>) responseData.get("stocks")).size());
        assertEquals(stockModelList.get(0).getId(),((List<StocksModel>) responseData.get("stocks")).get(0).getId());
        assertEquals(AppConstants.RESPONSE_SUCCESS_MSG, responseData.get("responseMsg"));
        assertEquals(AppConstants.RESPONSE_CODE_SUCCESS, responseData.get("responseCode"));

        //for failure scenario
        responseData = stocksService.findByStock("WRONG_STOCK");
        assertNull(responseData.get("stocks"));
        assertEquals(AppConstants.RESPONSE_FAILURE_MSG, responseData.get("responseMsg"));
        assertEquals(AppConstants.RESPONSE_CODE_FAILURE, responseData.get("responseCode"));
    }

    @Test
    public void testBatchInsert() {
        MultipartFile multipart = mock(MultipartFile.class);

        Map<String, Object> responseData = stocksService.batchInsert(multipart);
        assertNotNull(responseData);
        assertEquals(AppConstants.RESPONSE_SUCCESS_MSG, responseData.get("responseMsg"));
        assertEquals(AppConstants.RESPONSE_CODE_SUCCESS, responseData.get("responseCode"));
    }
}
