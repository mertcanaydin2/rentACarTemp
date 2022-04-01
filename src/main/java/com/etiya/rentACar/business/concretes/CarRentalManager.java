package com.etiya.rentACar.business.concretes;

import com.etiya.rentACar.business.abstracts.AdditionalServiceService;
import com.etiya.rentACar.business.abstracts.CarRentalService;
import com.etiya.rentACar.business.abstracts.CarService;
import com.etiya.rentACar.business.requests.carRentalRequests.CreateCarRentalRequest;
import com.etiya.rentACar.business.requests.carRentalRequests.DeleteCarRentalRequest;
import com.etiya.rentACar.business.requests.carRentalRequests.UpdateCarRentalRequest;
import com.etiya.rentACar.business.requests.carRequests.UpdateCarStatusRequest;
import com.etiya.rentACar.business.responses.additionalServiceResponses.ListAdditionalServiceDto;
import com.etiya.rentACar.business.responses.carRentalResponses.ListCarRentalDto;
import com.etiya.rentACar.business.responses.carResponses.CarDto;
import com.etiya.rentACar.core.utilities.mapping.ModelMapperService;
import com.etiya.rentACar.core.utilities.results.DataResult;
import com.etiya.rentACar.core.utilities.results.Result;
import com.etiya.rentACar.core.utilities.results.SuccessDataResult;
import com.etiya.rentACar.core.utilities.results.SuccessResult;
import com.etiya.rentACar.dataAccess.abstracts.CarRentalDao;
import com.etiya.rentACar.entities.CarRental;
import com.etiya.rentACar.entities.CarStates;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.Period;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CarRentalManager implements CarRentalService {

    private CarRentalDao carRentalDao;
    private ModelMapperService modelMapperService;
    private CarService carService;
    private AdditionalServiceService additionalServiceService;


    public CarRentalManager(CarRentalDao carRentalDao, ModelMapperService modelMapperService, CarService carService, AdditionalServiceService additionalServiceService) {
        this.carRentalDao = carRentalDao;
        this.modelMapperService = modelMapperService;
        this.carService = carService;
        this.additionalServiceService = additionalServiceService;

    }

    @Override
    public Result add(CreateCarRentalRequest createCarRentalRequest) {

        carService.checkIfCarAvaible(createCarRentalRequest.getCarId());


        CarRental carRental = this.modelMapperService.forRequest().map(createCarRentalRequest, CarRental.class);

        carRental.setReturnDate(null);

        this.carRentalDao.save(carRental);

        UpdateCarStatusRequest updateCarStatusRequest = new UpdateCarStatusRequest();
        updateCarStatusRequest.setId(createCarRentalRequest.getCarId());
        updateCarStatusRequest.setStates(CarStates.Rented);

        //updateCarStatus tanımla. (CarService)

        return new SuccessResult("CAR_ADDED_TO_RENTAL");
    }

    @Override
    public Result update(UpdateCarRentalRequest updateCarRentalRequest) {

        CarRental carRental = this.modelMapperService.forRequest()

                .map(updateCarRentalRequest, CarRental.class);

        this.carRentalDao.save(carRental);

        return new SuccessResult("CAR_RENTAL_UPDATE");
    }

    @Override
    public Result delete(DeleteCarRentalRequest deleteCarRentalRequest) {

        this.carRentalDao.deleteById(deleteCarRentalRequest.getRentalId());

        return new SuccessResult("CAR_RENTAL_DELETED");
    }

    @Override
    public DataResult<List<ListCarRentalDto>> getAll() {

        List<CarRental> carRentals = this.carRentalDao.findAll();

        List<ListCarRentalDto> response = carRentals.stream()

                .map(carRental -> this.modelMapperService.forDto()
                        .map(carRental, ListCarRentalDto.class))
                .collect(Collectors.toList());

        return new SuccessDataResult<List<ListCarRentalDto>>(response);
    }

    @Override
    public DataResult<List<ListCarRentalDto>> getAllPaged(int pageNo, int pageSize) {

        Pageable pageable = PageRequest.of(pageNo - 1, pageSize);

        List<CarRental> carRentals = this.carRentalDao.findAll(pageable).getContent();

        List<ListCarRentalDto> response = carRentals.stream()

                .map(carRental -> this.modelMapperService.forDto()
                        .map(carRental, ListCarRentalDto.class))
                .collect(Collectors.toList());

        return new SuccessDataResult<List<ListCarRentalDto>>(response);
    }

    @Override
    public DataResult<List<ListCarRentalDto>> getAllSorted(String option, String fields) {

        Sort sort = Sort.by(Sort.Direction.valueOf(option), fields);

        List<CarRental> carRentals = this.carRentalDao.findAll(sort);

        List<ListCarRentalDto> response = carRentals.stream()

                .map(carRental -> this.modelMapperService.forDto()
                        .map(carRental, ListCarRentalDto.class))
                .collect(Collectors.toList());

        return new SuccessDataResult<List<ListCarRentalDto>>(response);
    }

    @Override
    public DataResult<List<ListCarRentalDto>> getAllByCarId(int carId) {
        List<CarRental> result = this.carRentalDao.getAllByCarId(carId);

        List<ListCarRentalDto> response = result.stream()
                .map(carRental -> this.modelMapperService.forDto()
                        .map(carRental, ListCarRentalDto.class))
                .collect(Collectors.toList());

        return new SuccessDataResult<List<ListCarRentalDto>>(response);
    }

    public double lastTotalPrice(CreateCarRentalRequest createCarRentalRequest){
        CarDto carDto = this.carService.getById(createCarRentalRequest.getCarId());
        long carLateness = lateness(createCarRentalRequest);
        double rentTotalPrice = carLateness * carDto.getDailyPrice();
        double additionalPropertyTotalPrice = carLateness * additionalPropertyTotal(createCarRentalRequest);
        double checkCityDiff = checkCityDifference(createCarRentalRequest);
        return (rentTotalPrice + additionalPropertyTotalPrice + checkCityDiff);
    }

    public long lateness(CreateCarRentalRequest createCarRentalRequest){

        Period day = Period.between(createCarRentalRequest.getRentDate(), createCarRentalRequest.getReturnDate());
        int carLateness = day.getDays();

        return carLateness;
    }

    public double checkCityDifference(CreateCarRentalRequest createCarRentalRequest){
        if(createCarRentalRequest.getRentCity() != createCarRentalRequest.getReturnCity()){
            //Burayı yap.
            return 750;
        }
        return 0;
    }


    public double additionalPropertyTotal(CreateCarRentalRequest createCarRentalRequest) {
       /* double totalPrice = 0;
        List<ListAdditionalServiceDto> additionalServiceDtoList = this.additionalServiceService.getById(createCarRentalRequest.getAdditionalServiceId());
        for (ListAdditionalServiceDto item : additionalServiceDtoList) {
            totalPrice += item.getDailyPrice();
        }
        return totalPrice;*/
        return 100;
    }

}
