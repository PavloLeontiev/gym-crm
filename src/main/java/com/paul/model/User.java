package com.paul.model;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User implements Identifiable<Long>, UsernameIndex {

    private Long id;
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private Boolean isActive;
}
