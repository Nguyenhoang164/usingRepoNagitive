package com.example.qlnv.Repository;

import com.example.qlnv.Model.Manager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

public interface IManagerRepository extends CrudRepository<Manager , Integer> {
    Page<Manager> findAll(Pageable pageable);
    Iterable<Manager> findAllByFullNameContaining(String fullName);
    Iterable<Manager> findAllByAddressContaining(String address);
    Iterable<Manager> findAllByAgeContaining(int age);
}
