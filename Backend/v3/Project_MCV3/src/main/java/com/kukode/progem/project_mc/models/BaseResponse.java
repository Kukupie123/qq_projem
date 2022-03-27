package com.kukode.progem.project_mc.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


@ToString
@AllArgsConstructor
@NoArgsConstructor
@Data
/**
 * BaseResponse is the base class that is going to be used as the response object
 * The Data variable is going to be of type generic which is going to be the data itself
 * Where as the message is going to be the message that is sent along with the data to help clients
 * Figure out things that needs to be made clear
 * Example : When we get an error when sign-in we are going to have data as null OR false and message will specify what went wrong
 * NOTE: BaseResponse is recommended to be wrapped up with Response entity as shown ResponseEntity<BaseResponse<T>>
 */
public class BaseResponse<T> {
    T data;
    String message;
}
