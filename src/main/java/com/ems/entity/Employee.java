package com.ems.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "employees")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Employee {

    @Id
    private String empId;
    private String empName;
    private int age;
    private String address;
    private double salary;
    private String Department;


}
