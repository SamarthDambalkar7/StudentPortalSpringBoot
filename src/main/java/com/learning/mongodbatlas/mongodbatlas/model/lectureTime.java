package com.learning.mongodbatlas.mongodbatlas.model;

import java.time.LocalTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class lectureTime {

    private LocalTime fromTime;

    private LocalTime toTime;

}
