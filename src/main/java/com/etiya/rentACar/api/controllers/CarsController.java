package com.etiya.rentACar.api.controllers;

import java.util.List;

import javax.validation.Valid;

import com.etiya.rentACar.business.abstracts.CityService;
import com.etiya.rentACar.core.utilities.results.DataResult;
import com.etiya.rentACar.core.utilities.results.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.etiya.rentACar.business.abstracts.CarService;
import com.etiya.rentACar.business.requests.carRequests.CreateCarRequest;
import com.etiya.rentACar.business.requests.carRequests.DeleteCarRequest;
import com.etiya.rentACar.business.requests.carRequests.UpdateCarRequest;
import com.etiya.rentACar.business.responses.carResponses.ListCarDto;

@RestController
@RequestMapping("/api/cars")
public class CarsController {
	private CarService carService;
	private CityService cityService;

	public CarsController(CarService carService, CityService cityService) {
		this.carService = carService;
		this.cityService = cityService;
	}

	@PostMapping("/add")
	public Result add(@RequestBody @Valid CreateCarRequest createCarRequest) {

		return this.carService.add(createCarRequest);
	}
	@PostMapping("/update")
    public Result update(@RequestBody  UpdateCarRequest updateCarRequest) {

		return  this.carService.update(updateCarRequest);
    }
	@PostMapping("/delete")
    public Result delete(@RequestBody DeleteCarRequest deleteCarRequest) {

		return this.carService.delete(deleteCarRequest);
	}
	@GetMapping("/getall")
	public DataResult<List<ListCarDto>>getAll(){

		return this.carService.getAll();
	}

	@GetMapping("/getallbymodelyear")
	public DataResult<List<ListCarDto>>getAllByModelYear(@RequestParam("modelYear") int modelYear){

		return this.carService.getAllByModelYear(modelYear);
	}
	@GetMapping("/getallpaged")
	public DataResult<List<ListCarDto>> getAllPaged(int pageNo,int pageSize) {
		
		return this.carService.getAllPaged(pageNo, pageSize);
	}
	
	@GetMapping("/getallsorted")
	 public  DataResult<List<ListCarDto>> getAllSorted(String option,String fields){
		
		return this.carService.getAllSorted(option,fields);
	}

	@GetMapping("/getbycityid")
	public DataResult<List<ListCarDto>> getAllByCityName(@RequestParam("cityId") int cityId){
		return this.carService.getByCityId(cityId);
	}
		
	}
	

