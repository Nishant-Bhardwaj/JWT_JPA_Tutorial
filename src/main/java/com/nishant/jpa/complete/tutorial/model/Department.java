package com.nishant.jpa.complete.tutorial.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

@Embeddable
@Builder
@NoArgsConstructor
@Data
@AllArgsConstructor
public class Department {

    private String departmentName;
}
