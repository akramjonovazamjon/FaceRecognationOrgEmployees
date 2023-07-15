package com.example.lionprintfirstproject.mapper;

import com.example.lionprintfirstproject.controller.vm.EmployeeWorkingDayVm;
import com.example.lionprintfirstproject.entity.EmployeeWorkingDay;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface EmployeeWorkingDayMapper {

    EmployeeWorkingDayVm asEmployeeWorkingDayVm(EmployeeWorkingDay employeeWorkingDay);

}
