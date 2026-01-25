package com.paul.dto;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class TrainerDto {
    private Long id;
    private Long userId;
    private String firstName;
    private String lastName;
    private String username;
    private boolean isActive;
    private String specialization;
}
