package com.spe.appointmentservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("DOCTORSERVICE")
public interface DoctorClient {

    @GetMapping("/api/doctors/{id}")
    Object getDoctorById(@PathVariable Long id);
}
