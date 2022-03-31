package com.etiya.rentACar.business.concretes;

import com.etiya.rentACar.business.abstracts.CityService;
import com.etiya.rentACar.business.constants.BusinessMessages;
import com.etiya.rentACar.business.requests.cityRequests.CreateCityRequest;
import com.etiya.rentACar.business.requests.cityRequests.DeleteCityRequest;
import com.etiya.rentACar.business.requests.cityRequests.UpdateCityRequest;
import com.etiya.rentACar.business.responses.cityResponses.CityDto;
import com.etiya.rentACar.business.responses.cityResponses.ListCityDto;
import com.etiya.rentACar.business.responses.colorResponses.ListColorDto;
import com.etiya.rentACar.core.utilities.mapping.ModelMapperService;
import com.etiya.rentACar.core.utilities.results.DataResult;
import com.etiya.rentACar.core.utilities.results.Result;
import com.etiya.rentACar.core.utilities.results.SuccessDataResult;
import com.etiya.rentACar.core.utilities.results.SuccessResult;
import com.etiya.rentACar.dataAccess.abstracts.CityDao;
import com.etiya.rentACar.entities.City;
import com.etiya.rentACar.entities.Color;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CityManager implements CityService {


    private CityDao cityDao;
    private ModelMapperService modelMapperService;

    public CityManager(CityDao cityDao, ModelMapperService modelMapperService) {
        this.cityDao = cityDao;
        this.modelMapperService = modelMapperService;
    }

    /*@Override
    public DataResult<List<ListCityDto>> getAllByCityName(String cityName) {
        return null;
    }*/

    @Override
    public DataResult<List<ListCityDto>> getAll() {
        List<City> cities = this.cityDao.findAll();
        List<ListCityDto> response = cities.stream()
                .map(city -> modelMapperService.forDto().
                        map(city,ListCityDto.class)).collect(Collectors.toList());

        return new SuccessDataResult<List<ListCityDto>>(response);
    }

    /*@Override
    public DataResult<List<ListCityDto>> getAllPaged(int pageNo, int pageSize) {
        return null;
    }*/

    @Override
    public Result add(CreateCityRequest createCityRequest) {
        City city = this.modelMapperService.forRequest().map(createCityRequest,City.class);
        this.cityDao.save(city);
        return new SuccessResult(BusinessMessages.CityMessage.CITY_ADDED);
    }

    @Override
    public Result update(UpdateCityRequest updateCityRequest) {
        City city = this.modelMapperService.forRequest().map(updateCityRequest,City.class);
        this.cityDao.save(city);
        return new SuccessResult(BusinessMessages.CityMessage.CITY_UPDATED);
    }

    @Override
    public Result delete(DeleteCityRequest deleteCityRequest) {
        this.cityDao.deleteById(deleteCityRequest.getId());
        return new SuccessResult(BusinessMessages.CityMessage.CITY_DELETED);
    }


}
