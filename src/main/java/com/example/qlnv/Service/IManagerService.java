package com.example.qlnv.Service;

import com.example.qlnv.Model.Manager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface IManagerService {
    Iterable<Manager> showAllManager();
    void save(Manager manager);
    void delete(Manager manager);
    Page<Manager> findAll(Pageable pageable);
    Optional<Manager> findById(int id);
    Iterable<Manager> findAllByFullNameContaining(String fullName);
    Iterable<Manager> findAllByAddressContaining(String address);
    Iterable<Manager> findAllByAgeContaining(int age);
}
