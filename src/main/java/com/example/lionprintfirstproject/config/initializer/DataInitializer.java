package com.example.lionprintfirstproject.config.initializer;

import com.example.lionprintfirstproject.dto.user.CreateUser;
import com.example.lionprintfirstproject.entity.*;
import com.example.lionprintfirstproject.repository.*;
import com.example.lionprintfirstproject.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalTime;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {
    private final UserRepository userRepository;
    private final OrganizationRepository organizationRepository;
    private  final PasswordEncoder passwordEncoder;
    private final BranchRepository branchRepository;
    private final DepartmentRepository departmentRepository;
    private final JobRepository jobRepository;
    private final ScheduleRepository scheduleRepository;
    @Override
    public void run(String... args) throws Exception {
        if (userRepository.findByUsername("vali").isEmpty()) {
            User user = User.of("Vali Valiyev", "vali", passwordEncoder.encode("123"), UserRole.valueOf("ADMIN"));
        userRepository.save(user);
        }
        Organization organization=organizationRepository.findByName("LionPrint").orElse(null);
        if (organization==null){
            organization = Organization.builder()
                    .name("LionPrint")
                    .info("LionPrint is always with you")
                    .build();
            organization=organizationRepository.save(organization);
        }
        Branch branch = branchRepository.findByNameAndOrganizationId("LionPrint main", organization.getId()).orElse(null);
        if (branch==null){
            branch = Branch.builder()
                    .name("LionPrint main")
                    .organization(organization)
                    .info("Main branch")
                    .build();
            branch=branchRepository.save(branch);
        }
        Department department = departmentRepository.findByNameAndBranchId("IT Department", branch.getId()).orElse(null);
        if (department==null){
            department=Department.builder()
                    .name("IT Department")
                    .info("IT department")
                    .branch(branch)
                    .build();
            department=departmentRepository.save(department);
        }
        Job job = jobRepository.findByName("Software Engineer").orElse(null);
        if (job==null){
            job=Job.builder()
                    .name("Software Engineer")
                    .info("Software Engineer")
                    .build();
            job=jobRepository.save(job);
        }
        Schedule schedule = scheduleRepository.findByStartTimeAndEndTime(LocalTime.of(8,0,0),LocalTime.of(18,0,0)).orElse(null);
        if (schedule==null){
            schedule=Schedule.builder()
                    .startTime(LocalTime.of(8,0,0))
                    .endTime(LocalTime.of(18,0,0))
                    .build();
            schedule=scheduleRepository.save(schedule);
        }

    }
}
