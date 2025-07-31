package com.application.messageq.dto;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Dept {

    private String deptname;
    private int total;
    private int availseats;

}
