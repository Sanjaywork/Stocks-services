	package com.stocks.serviceimpl;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;

import com.stocks.constants.AppConstants;
import com.stocks.dao.StocksDao;
import com.stocks.entity.StocksEntity;
import com.stocks.model.StocksModel;
import com.stocks.service.StocksService;
import com.stocks.utils.ObjectMapperUtils;

@Service
public class StocksServiceImpl implements StocksService{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(StocksServiceImpl.class);
	
	@Autowired
	private StocksDao stocksDao;
	
	@Override
	public Map<String,Object> saveOrUpdateStockData(StocksModel model) 
	{
		StocksEntity stocksEntity = null;
		Map<String,Object> responseObject=null;
		try 
		{
			responseObject=new HashMap<>();
			
			//Mapping Model to Entity
			stocksEntity = ObjectMapperUtils.map(model, StocksEntity.class);
			
			//Calling Dao
			stocksEntity = stocksDao.saveOrUpdateStockData(stocksEntity);
			
			if(stocksEntity==null)
				throw new Exception(AppConstants.RESPONSE_SAVE_FAILIURE);
				
			
			responseObject.put("reponseCode", AppConstants.RESPONSE_CODE_SUCCESS);
			responseObject.put("reponseMsg", AppConstants.RESPONSE_SAVE_SUCCESS);
			responseObject.put("data", ObjectMapperUtils.map(stocksEntity, StocksModel.class));

		} catch (Exception exception) 
		{
			LOGGER.error(exception.getMessage());
			responseObject.put("reponseCode", AppConstants.RESPONSE_CODE_FAILURE);
			responseObject.put("reponseMsg", AppConstants.RESPONSE_SAVE_FAILIURE);
		} finally
		{
			stocksEntity = null;
			model = null;
		}
		return responseObject;
	}

	@Override
	public Map<String, Object> findByStock(String stock) 
	{
		Map<String,Object> responseObject=null;
		List<StocksModel> stockModelList = null;
		List<StocksEntity> stocksEntityList = null;
		try 
		{
			responseObject=new HashMap<>();
			stockModelList=new ArrayList<>();
			
			//Calling Dao
			stocksEntityList=stocksDao.findByStock(stock);
			
			if (!CollectionUtils.isEmpty(stocksEntityList)) 
				stockModelList = ObjectMapperUtils.mapAll(stocksEntityList, StocksModel.class);	//Mapping Entity to Model
			
			responseObject.put("reponseCode", AppConstants.RESPONSE_CODE_SUCCESS);
			responseObject.put("reponseMsg", AppConstants.RESPONSE_SUCCESS_MSG);
			responseObject.put("stocks", stockModelList);
			
		}catch (Exception exception) {
			LOGGER.error(exception.getMessage());
			
			responseObject.put("reponseCode", AppConstants.RESPONSE_CODE_FAILURE);
			responseObject.put("reponseMsg", exception.getMessage());
		} finally {
			stocksEntityList = null;
		}
		
		return responseObject;
	}

	@Override
	public Map<String, Object> batchInsert(MultipartFile file) 
	{
		File file1=null;
		String fileName=null;
		Map<String,Object> responseObject=null;
		try 
		{
			responseObject=new HashMap<>();
			
			fileName="stocks_"+System.currentTimeMillis()+".csv";
			file1=new File((new File("").getAbsolutePath()+File.separator+fileName));
			file.transferTo(file1);
			
			stocksDao.batchInsert(fileName);
			file1.delete();
			
			responseObject.put("reponseCode", AppConstants.RESPONSE_CODE_SUCCESS);
			responseObject.put("reponseMsg", AppConstants.RESPONSE_SUCCESS_MSG);
			
		} catch (Exception e) 
		{
			LOGGER.error(e.getMessage());
			responseObject.put("reponseCode", AppConstants.RESPONSE_CODE_FAILURE);
			responseObject.put("reponseMsg", e.getMessage());
		}
		return responseObject;
	}
}
