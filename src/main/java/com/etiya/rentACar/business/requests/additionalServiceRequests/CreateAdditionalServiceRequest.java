package com.etiya.rentACar.business.requests.additionalServiceRequests;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateAdditionalServiceRequest {


    @JsonIgnore
    private int id;

    @NotNull
    private String name;

    @NotNull
    @Min(1)
    private double dailyPrice;
}
