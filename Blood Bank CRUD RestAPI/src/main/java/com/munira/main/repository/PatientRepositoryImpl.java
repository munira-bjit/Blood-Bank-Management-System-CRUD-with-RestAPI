package com.munira.main.repository;

import com.munira.main.model.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Repository
public class PatientRepositoryImpl implements PatientRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Optional<Patient> getById(Integer id) {
        String query = "select * from patientlist where id = ?";
        List<Patient> patients = jdbcTemplate.query(query, new Object[]{id}, (rs, rowNum) ->
                new Patient(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("bloodgroup")
                )
        );
        return patients.isEmpty() ? Optional.of(new Patient(-1, "No patient found", "")) : Optional.of(patients.get(0));
    }


    @Override
    public List<Patient> getAll() {
        String query = "select * from patientlist";
        return jdbcTemplate.query(query, (resultSet, rowNum) ->
                new Patient(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("bloodgroup")
                )
        );
    }

    @Override
    public void add(Patient patient) {
        String query = "insert into patientlist (id, name, bloodgroup) values (?, ?, ?)";
        jdbcTemplate.update(query, patient.getId(), patient.getName(), patient.getBloodGroup());
    }

    @Override
    public void delete(Integer id) {
        String query = "delete from patientlist where id = ?";
        jdbcTemplate.update(query, id);
    }

    @Override
    public void update(Patient patient) {
        String query = "update patientlist set name = ?, bloodgroup = ? where id = ?";
        jdbcTemplate.update(query, patient.getName(), patient.getBloodGroup(), patient.getId());

    }
}
