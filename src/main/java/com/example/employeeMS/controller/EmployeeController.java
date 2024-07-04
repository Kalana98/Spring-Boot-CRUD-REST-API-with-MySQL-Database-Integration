package com.example.employeeMS.controller;

import com.example.employeeMS.dto.EmployeeDTO;
import com.example.employeeMS.dto.RespondsDTO;
import com.example.employeeMS.service.EmployeeService;
import com.example.employeeMS.util.VarList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private RespondsDTO respondsDTO;

    @PostMapping(value = "/saveEmployee")
    public ResponseEntity saveEmployee(@RequestBody EmployeeDTO employeeDTO){
        try{

            String res = employeeService.saveEmployee(employeeDTO);
            if(res.equals("00")){
                respondsDTO.setCode(VarList.RSP_SUCCESS);
                respondsDTO.setMessage("Success");
                respondsDTO.setContent(employeeDTO);
                return new ResponseEntity(respondsDTO, HttpStatus.ACCEPTED);
            }else if (res.equals("06")){
                respondsDTO.setCode(VarList.RSP_DUPLICATED);
                respondsDTO.setMessage("Employee already registered");
                respondsDTO.setContent(employeeDTO);
                return new ResponseEntity(respondsDTO, HttpStatus.BAD_REQUEST);
            }else{
                respondsDTO.setCode(VarList.RSP_FAIL);
                respondsDTO.setMessage("Error");
                respondsDTO.setContent(null);
                return new ResponseEntity(respondsDTO, HttpStatus.BAD_REQUEST);
            }

        }catch (Exception ex){
            respondsDTO.setCode(VarList.RSP_ERROR);
            respondsDTO.setMessage(ex.getMessage());
            respondsDTO.setContent(null);
            return new ResponseEntity(respondsDTO, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PutMapping (value = "/updateEmployee")
    public ResponseEntity updateEmployee(@RequestBody EmployeeDTO employeeDTO){
        try{
            String res = employeeService.updateEmployee(employeeDTO);
            if(res.equals("00")){
                respondsDTO.setCode(VarList.RSP_SUCCESS);
                respondsDTO.setMessage("Success");
                respondsDTO.setContent(employeeDTO);
                return new ResponseEntity(respondsDTO, HttpStatus.ACCEPTED);
            }else if (res.equals("01")){
                respondsDTO.setCode(VarList.RSP_DUPLICATED);
                respondsDTO.setMessage("Not A Registered Employee");
                respondsDTO.setContent(employeeDTO);
                return new ResponseEntity(respondsDTO, HttpStatus.BAD_REQUEST);
            }else{
                respondsDTO.setCode(VarList.RSP_FAIL);
                respondsDTO.setMessage("Error");
                respondsDTO.setContent(null);
                return new ResponseEntity(respondsDTO, HttpStatus.BAD_REQUEST);
            }

        }catch (Exception ex){
            respondsDTO.setCode(VarList.RSP_ERROR);
            respondsDTO.setMessage(ex.getMessage());
            respondsDTO.setContent(null);
            return new ResponseEntity(respondsDTO, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping("/getAllEmployees")
    public ResponseEntity getAllEmployees(){
        try{
            List<EmployeeDTO> employeeDTOList = employeeService.getAllEmployees();
            respondsDTO.setCode(VarList.RSP_SUCCESS);
            respondsDTO.setMessage("Success");
            respondsDTO.setContent(employeeDTOList);
            return new ResponseEntity(respondsDTO, HttpStatus.ACCEPTED);
        }catch(Exception ex){
            respondsDTO.setCode(VarList.RSP_ERROR);
            respondsDTO.setMessage(ex.getMessage());
            respondsDTO.setContent(null);
            return new ResponseEntity(respondsDTO, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/searchEmployee/{empID}")
    public ResponseEntity searchEmployee(@PathVariable int empID){
        try {
            EmployeeDTO employeeDTO = employeeService.searchEmployee(empID);
            if (employeeDTO != null) {
                respondsDTO.setCode(VarList.RSP_SUCCESS);
                respondsDTO.setMessage("Success");
                respondsDTO.setContent(employeeDTO);
                return new ResponseEntity(respondsDTO, HttpStatus.ACCEPTED);
            } else {
                respondsDTO.setCode(VarList.RSP_NO_DATA_FOUND);
                respondsDTO.setMessage("No Employee Available for this emp ID");
                respondsDTO.setContent(null);
                return new ResponseEntity(respondsDTO, HttpStatus.BAD_REQUEST);
            }
        }catch(Exception ex){
            respondsDTO.setCode(VarList.RSP_ERROR);
            respondsDTO.setMessage(ex.getMessage());
            respondsDTO.setContent(null);
            return new ResponseEntity(respondsDTO, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/deleteEmployee/{empID}")
    public ResponseEntity deleteEmployee(@PathVariable int empID){
        try {
            String res = employeeService.deleteEmployee(empID);
            if (res.equals("00")) {
                respondsDTO.setCode(VarList.RSP_SUCCESS);
                respondsDTO.setMessage("Success");
                respondsDTO.setContent(null);
                return new ResponseEntity(respondsDTO, HttpStatus.ACCEPTED);
            } else {
                respondsDTO.setCode(VarList.RSP_NO_DATA_FOUND);
                respondsDTO.setMessage("No Employee Available for this emp ID");
                respondsDTO.setContent(null);
                return new ResponseEntity(respondsDTO, HttpStatus.BAD_REQUEST);
            }
        }catch(Exception ex){
            respondsDTO.setCode(VarList.RSP_ERROR);
            respondsDTO.setMessage(ex.getMessage());
            respondsDTO.setContent(null);
            return new ResponseEntity(respondsDTO, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
