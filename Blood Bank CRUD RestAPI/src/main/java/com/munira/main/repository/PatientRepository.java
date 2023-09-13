package com.munira.main.repository;

import com.munira.main.model.Patient;

import java.util.List;
import java.util.Optional;

public interface PatientRepository {

    Optional<Patient> getById(Integer id);

    List<Patient> getAll();

    void add(Patient patient);

    void delete(Integer id);

    void update(Patient patient);
}
