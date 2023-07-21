package com.example.lionprintfirstproject.service;

import com.example.lionprintfirstproject.dto.CameraResponse;
import com.example.lionprintfirstproject.dto.camera.*;
import com.example.lionprintfirstproject.entity.Employee;
import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class CameraEmployeeService {
    private final RestTemplate restTemplate;

    public boolean saveEmployeeToCamera(Employee employee, MultipartFile image, String host) {
        EmployeeForCamera employeeForCamera =
                EmployeeForCamera.of(new UserInfo(new Valid(true, employee.getBeginTime().toString(), employee.getEndTime().toString()), employee.getFirstName(), "normal", String.valueOf(employee.getId())));

        HttpEntity<?> employeeForSave = new HttpEntity<>(employeeForCamera);

        ResponseEntity<CameraResponse> exchange = restTemplate.exchange(host+"/ISAPI/AccessControl/UserInfo/Record?format=json", HttpMethod.POST, employeeForSave, CameraResponse.class);
        if (Objects.requireNonNull(exchange.getBody()).getStatusCode()!=1) {
            return false;
        }
        MultiValueMap<String, Object> formData = new LinkedMultiValueMap<>();
        formData.add("FaceDataRecord", new FaceDateRecord(employee.getId().toString(), "blackFD", "1"));
        formData.add("img", image);

        // Set the Content-Type header to "multipart/form-data"
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        // Create the HttpEntity with headers and form data
        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(formData, headers);

        ResponseEntity<CameraResponse> exchange1 = restTemplate.exchange(host + "/ISAPI/Intelligent/FDLib/FDSetUp?format=json", HttpMethod.POST, requestEntity, CameraResponse.class);
        if (Objects.requireNonNull(exchange1.getBody()).getStatusCode()!=1) {
            return false;
        }
        return true;
    }
}
