package com.ldt.Employee.utility;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class APIResponse {
    private String status;

    private String message;

    private int statusCode;

    private Object object;

}
