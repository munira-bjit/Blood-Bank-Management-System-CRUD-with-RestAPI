package com.munira.main.controller;

import com.munira.main.model.Patient;
import com.munira.main.repository.PatientRepository;
//import com.munira.main.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class MainController {

    @Autowired
    private PatientRepository patientRepository;

    @GetMapping("/patient/{id}")
    public ResponseEntity<Patient> getPatientById(@PathVariable Integer id) {
        Optional<Patient> patientOptional = patientRepository.getById(id);
        return new ResponseEntity<>(patientOptional.get(), HttpStatus.OK);
    }

    @GetMapping("/patient/all")
    public ResponseEntity<List<Patient>> getAllPatient() {
        List<Patient> patientList = patientRepository.getAll();
        return new ResponseEntity<>(patientList, HttpStatus.OK);
    }

    @PostMapping("/save-patient")
    public ResponseEntity<String> savePatient(@RequestBody Patient patient) {
        patientRepository.add(patient);
        return new ResponseEntity<>("Patient added successfully", HttpStatus.OK);
    }

    @DeleteMapping("/delete-patient/{id}")
    public ResponseEntity<String> deletePatient(@PathVariable Integer id) {
        patientRepository.delete(id);
        return new ResponseEntity<>("Patient deleted successfully", HttpStatus.OK);
    }

    @PutMapping("/update-patient/{id}")
    public ResponseEntity<String> updatePatient(@PathVariable Integer id, @RequestBody Patient patient) {
        patientRepository.update(patient);
        return new ResponseEntity<>("Patient updated successfully", HttpStatus.OK);
    }
}
