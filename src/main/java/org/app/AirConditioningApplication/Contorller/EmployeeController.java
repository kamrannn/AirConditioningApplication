package org.app.AirConditioningApplication.Contorller;

import org.app.AirConditioningApplication.Model.Employee;
import org.app.AirConditioningApplication.Model.WorkLog;
import org.app.AirConditioningApplication.Service.EmployeeService;
import org.app.AirConditioningApplication.response.ApiResponse;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("Employee")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/list")
    public ApiResponse list() {
        return employeeService.showAll();
    }

    @PostMapping("/add")
    public ApiResponse add(@RequestBody Employee employee) {
        return employeeService.save(employee);
    }

    @PutMapping("/update")
    public ApiResponse update(@RequestBody Employee employee) {
        return employeeService.save(employee);
    }

    @DeleteMapping("/delete")
    public ApiResponse delete(@RequestParam Long Id) {
        return employeeService.delete(Id);
    }

    @GetMapping("/getByID")
    public ApiResponse getById(@RequestParam Long Id) {
        return employeeService.getById(Id);
    }

    //This api will add workLog against employee because employee will exist for worklog
    @PostMapping("/addWorkLog")
    public ApiResponse addWorkLog(@RequestParam String email, @RequestBody WorkLog workLog) {
        return employeeService.addNewWorkLog(email, workLog);
    }

    //This api will only show workLog by email of employee
    @GetMapping("/GetWorkLogByEmail")
    public ApiResponse workLogByEmail(@RequestParam String email){
        return employeeService.showWorkLog(email);
    }

}
