package com.spe.patientservice.service;

import com.spe.patientservice.dto.PatientDTO;
import com.spe.patientservice.model.Patient;
import com.spe.patientservice.repository.PatientRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;



@Service
public class PatientService {

    @Autowired
    private PatientRepo patientRepository;

    public Patient createPatient(PatientDTO patientDTO) {
        Patient patient = Patient.builder()
                .name(patientDTO.getName())
                .email(patientDTO.getEmail())
                .phone(patientDTO.getPhone())
                .address(patientDTO.getAddress())
                .build();
        return patientRepository.save(patient);
    }

    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }

    public Optional<Patient> getPatientById(Long id) {
        return patientRepository.findById(id);
    }

    public Patient updatePatient(Long id, PatientDTO patientDTO) {
        Patient existing = patientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Patient not found"));

        existing.setName(patientDTO.getName());
        existing.setEmail(patientDTO.getEmail());
        existing.setPhone(patientDTO.getPhone());
        existing.setAddress(patientDTO.getAddress());

        return patientRepository.save(existing);
    }

    public void deletePatient(Long id) {
        patientRepository.deleteById(id);
    }
}
