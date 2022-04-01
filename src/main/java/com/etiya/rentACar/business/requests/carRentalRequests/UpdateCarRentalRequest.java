package com.etiya.rentACar.business.requests.carRentalRequests;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateCarRentalRequest {

    private int id;


    private int customerId;


    private LocalDate rentDate;

    @NotNull
    private LocalDate returnDate;

    private int carId;

}
