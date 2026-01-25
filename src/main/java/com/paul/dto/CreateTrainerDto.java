package com.paul.dto;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class CreateTrainerDto {

    private String firstName;
    private String lastName;
    private String specialization;
}
