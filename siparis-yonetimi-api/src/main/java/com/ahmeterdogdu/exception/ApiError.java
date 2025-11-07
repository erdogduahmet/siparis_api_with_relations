package com.ahmeterdogdu.exception;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ApiError<T> {

	private String id;
	
	private Date errorTime;
	
	private T erors;
}
