package com.onpier.libraryapi.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class User {

    private String name;

    private String firstName;

    private LocalDate memberSince;

    private LocalDate memberTill;

    private String gender;
}
