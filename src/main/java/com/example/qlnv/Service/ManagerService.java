package com.example.qlnv.Service;

import com.example.qlnv.Model.Manager;
import com.example.qlnv.Repository.IManagerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ManagerService implements IManagerService{
    @Autowired
    private IManagerRepository managerRepository;
    @Override
    public Iterable<Manager> showAllManager() {
        return managerRepository.findAll();
    }

    @Override
    public void save(Manager manager) {
       managerRepository.save(manager);
    }

    @Override
    public void delete(Manager manager) {
     managerRepository.delete(manager);
    }

    @Override
    public Page<Manager> findAll(Pageable pageable) {
        return managerRepository.findAll(pageable);
    }

    @Override
    public Optional<Manager> findById(int id) {
        return managerRepository.findById(id);
    }

    @Override
    public Iterable<Manager> findAllByFullNameContaining(String fullName) {
        return managerRepository.findAllByFullNameContaining(fullName);
    }

    @Override
    public Iterable<Manager> findAllByAddressContaining(String address) {
        return managerRepository.findAllByAddressContaining(address);
    }

    @Override
    public Iterable<Manager> findAllByAgeContaining(int age) {
        return managerRepository.findAllByAgeContaining(age);
    }


}
