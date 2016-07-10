package com.example.admission.service;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.dropwizard.Configuration;

public class AdmissionConfiguration extends Configuration	 {

	@JsonProperty
    @NotEmpty
    public String mongohost = "localhost";
 
    @JsonProperty
    @Min(1)
    @Max(65535)
    public int mongoport = 27017;
 
    @JsonProperty
    @NotEmpty
    public String mongodb = "mydb";
    
  
    public double surcharge = 10.0;
    
    public double extraSurcharge = 5.0;    
    
}
