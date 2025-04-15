package com.nakulsiwach.doctorservice.service;

import com.spe.doctor.dto.DoctorDTO;
import com.spe.doctor.model.Doctor;
import com.spe.doctor.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DoctorService {

    @Autowired
    private DoctorRepository doctorRepository;

    public Doctor createDoctor(DoctorDTO doctorDTO) {
        Doctor doctor = new Doctor(
                null, // Assuming ID is auto-generated
                doctorDTO.getName(),
                doctorDTO.getEmail(),
                doctorDTO.getSpecialty(),
                doctorDTO.getPhone(),
                doctorDTO.getAddress()
        );

        return doctorRepository.save(doctor);
    }

    public List<Doctor> getAllDoctors() {
        return doctorRepository.findAll();
    }

    public Optional<Doctor> getDoctorById(Long id) {
        return doctorRepository.findById(id);
    }

    public Doctor updateDoctor(Long id, DoctorDTO doctorDTO) {
        Doctor existing = doctorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Doctor not found"));

        existing.setName(doctorDTO.getName());
        existing.setEmail(doctorDTO.getEmail());
        existing.setSpecialty(doctorDTO.getSpecialty());
        existing.setPhone(doctorDTO.getPhone());
        existing.setAddress(doctorDTO.getAddress());

        return doctorRepository.save(existing);
    }

    public void deleteDoctor(Long id) {
        doctorRepository.deleteById(id);
    }
}

