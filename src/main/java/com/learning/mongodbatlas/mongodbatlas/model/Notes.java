package com.learning.mongodbatlas.mongodbatlas.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class Notes {

    private String date;
    private String title;
    private String content;
}
