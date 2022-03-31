package com.etiya.rentACar.business.abstracts;

import com.etiya.rentACar.business.requests.cityRequests.CreateCityRequest;
import com.etiya.rentACar.business.requests.cityRequests.DeleteCityRequest;
import com.etiya.rentACar.business.requests.cityRequests.UpdateCityRequest;

import com.etiya.rentACar.business.responses.cityResponses.ListCityDto;
import com.etiya.rentACar.core.utilities.results.DataResult;
import com.etiya.rentACar.core.utilities.results.Result;

import java.util.List;

public interface CityService {

    //DataResult<List<ListCityDto>> getAllByCityName(String cityName);
    DataResult<List<ListCityDto>> getAll();
   // DataResult<List<ListCityDto>> getAllPaged(int pageNo, int pageSize);


    Result add(CreateCityRequest createCityRequest);
    Result update(UpdateCityRequest updateCityRequest);
    Result delete(DeleteCityRequest deleteCityRequest);
}
