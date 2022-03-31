package com.etiya.rentACar.api.controllers;

import com.etiya.rentACar.business.abstracts.CityService;
import com.etiya.rentACar.business.requests.cityRequests.CreateCityRequest;
import com.etiya.rentACar.business.responses.cityResponses.ListCityDto;
import com.etiya.rentACar.core.utilities.results.DataResult;
import com.etiya.rentACar.core.utilities.results.Result;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/cities")
public class CityController {

    private CityService cityService;

    public CityController(CityService cityService) {
        this.cityService = cityService;
    }

    @PostMapping("/add")
    public Result add (@RequestBody @Valid CreateCityRequest createCityRequest){
        return this.cityService.add(createCityRequest);
    }

    @GetMapping("/getAll")
    public DataResult<List<ListCityDto>> getAll(){
        return cityService.getAll();
    }
}
