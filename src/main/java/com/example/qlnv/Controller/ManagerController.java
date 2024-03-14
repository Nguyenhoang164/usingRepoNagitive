package com.example.qlnv.Controller;

import com.example.qlnv.Model.Manager;
import com.example.qlnv.Model.PageRequestDTO;
import com.example.qlnv.Repository.IManagerRepository;
import com.example.qlnv.Service.IManagerService;
import jdk.dynalink.linker.LinkerServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/managers")
public class ManagerController {
    @Autowired
    private IManagerService managerService;
    @GetMapping("")
    public ResponseEntity<Iterable<Manager>> showAllManager(@RequestBody PageRequestDTO pageRequest){
        Pageable pageable = PageRequest.of(pageRequest.getPage(), pageRequest.getSize(), Sort.by("id"));
        Page<Manager> managers = managerService.findAll(pageable);
        List<Manager> managerList = new ArrayList<>();
        for (Manager manager : managers){
            managerList.add(manager);
        }
        if (managerList.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }else{
            return new ResponseEntity<>(managerList,HttpStatus.OK);
        }
    }
    @PostMapping("/add")
    public ResponseEntity<String> createManger(@RequestBody Manager manager){
        String nameRegex = "^[A-Z].*";
        String numberRegex = "^[0-9]{11}$";
        if(manager.getFullName().matches(nameRegex) && manager.getNumber().matches(numberRegex)&&("nam".equalsIgnoreCase(manager.getSex())||"nữ".equalsIgnoreCase(manager.getSex()))){
            managerService.save(manager);
            return new ResponseEntity<>("create manager complete",HttpStatus.OK);
        }else{
            return new ResponseEntity<>("name or number, sex was error",HttpStatus.EXPECTATION_FAILED);
        }
    }
    @DeleteMapping ("/delete/{id}")
    public ResponseEntity<String> deleteManger(@PathVariable("id") int id){
        Optional<Manager> manager = managerService.findById(id);
        if (!manager.isPresent()){
            return new ResponseEntity<>("manager id not exit",HttpStatus.NO_CONTENT);
        }
        managerService.delete(manager.get());
        return new ResponseEntity<>("delete manager complete",HttpStatus.OK);

    }
    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateManagerById(@PathVariable("id")int id,@RequestBody Manager manager){
            manager.setId(id);
            managerService.save(manager);
            return new ResponseEntity<>("manager is update",HttpStatus.OK);
        }
    @GetMapping("/search/{variable}")
    public ResponseEntity<Iterable<Manager>> showAllManagerBySearch(@PathVariable("variable") String variable){
        Iterable<Manager> managers = managerService.findAllByFullNameContaining(variable);
        Iterable<Manager> managerList = managerService.findAllByAddressContaining(variable);
        List<Manager> list = new ArrayList<>();
        for (Manager manager : managers){
            list.add(manager);
        }
        for (Manager manager : managerList){
            list.add(manager);
        }
        if (list == null){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }else{
            return new ResponseEntity<>(list,HttpStatus.OK);
        }
    }
}
