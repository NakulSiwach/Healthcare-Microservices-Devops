package com.spe.appointmentservice.client;

import com.spe.appointmentservice.dto.PatientDTO;
import  org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


//@FeignClient(name = "PATIENTSERVICE", url = "http://localhost:8081/api/patients")
@FeignClient("PATIENTSERVICE")
public interface PatientClient {

    @GetMapping("/api/patients/{id}")
    PatientDTO getPatientById(@PathVariable Long id);
}
