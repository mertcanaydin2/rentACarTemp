package com.etiya.rentACar.api.controllers;


import com.etiya.rentACar.business.abstracts.AdditionalServiceService;
import com.etiya.rentACar.business.requests.additionalServiceRequests.CreateAdditionalServiceRequest;
import com.etiya.rentACar.business.requests.additionalServiceRequests.DeleteAdditionalServiceRequest;
import com.etiya.rentACar.business.requests.additionalServiceRequests.UpdateAdditionalServiceRequest;
import com.etiya.rentACar.business.responses.additionalServiceResponses.ListAdditionalServiceDto;
import com.etiya.rentACar.core.utilities.results.DataResult;
import com.etiya.rentACar.core.utilities.results.Result;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/additionalservices")
public class AdditionalServiceController {

    private AdditionalServiceService additionalServiceService;

    public AdditionalServiceController(AdditionalServiceService additionalServiceService) {
        this.additionalServiceService = additionalServiceService;
    }

    @PostMapping("/add")
    public Result add(@RequestBody @Valid CreateAdditionalServiceRequest createAdditionalServiceRequest){
        return this.additionalServiceService.add(createAdditionalServiceRequest);
    }

    @PostMapping("/update")
    public Result update(@RequestBody @Valid UpdateAdditionalServiceRequest updateAdditionalServiceRequest){
        return this.additionalServiceService.update(updateAdditionalServiceRequest);
    }

    @PostMapping("/delete")
    public Result delete(@RequestBody @Valid DeleteAdditionalServiceRequest deleteAdditionalServiceRequest){
        return this.additionalServiceService.delete(deleteAdditionalServiceRequest);
    }

    @GetMapping("/getall")
    public DataResult<List<ListAdditionalServiceDto>> getAll(){
        return this.additionalServiceService.getAll();
    }

}
