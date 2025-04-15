package com.spe.appointmentservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "doctor-service", url = "http://localhost:8082/api/doctors")
public interface DoctorClient {

    @GetMapping("/{id}")
    Object getDoctorById(@PathVariable Long id);
}
