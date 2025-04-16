package com.spe.appointmentservice.client;

import  org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


//@FeignClient(name = "PATIENTSERVICE", url = "http://localhost:8081/api/patients")
@FeignClient("PATIENTSERVICE/api/patients")
public interface PatientClient {

    @GetMapping("/{id}")
    Object getPatientById(@PathVariable Long id);
}
