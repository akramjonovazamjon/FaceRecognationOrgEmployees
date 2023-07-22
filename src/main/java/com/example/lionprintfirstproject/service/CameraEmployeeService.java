package com.example.lionprintfirstproject.service;

import com.example.lionprintfirstproject.dto.CameraResponse;
import com.example.lionprintfirstproject.dto.camera.*;
import com.example.lionprintfirstproject.entity.Employee;
import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.FileSystemResource;
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
    private final String USER_CREATE_URL = "/ISAPI/AccessControl/UserInfo/Record?format=json";
    private final String USER_UPDATE_URL = "/ISAPI/AccessControl/UserInfo/Modify?format=json";
    private final String USER_IMG_UPDATE_URL = "/ISAPI/Intelligent/FDLib/FDSetUp?format=json";

    public boolean saveEmployeeToCamera(Employee employee, String host, boolean isCreate) {
        if (!saveUser(employee, host, isCreate)) {
            return false;
        }
        return updateUserImg(employee, host);

    }

    public boolean saveUser(Employee employee, String host, Boolean isCreate) {
        EmployeeForCamera employeeForCamera = EmployeeForCamera.of(new UserInfo(new Valid(true, employee.getBeginTime().toString(), employee.getEndTime().toString()), employee.getFirstName(), "normal", String.valueOf(employee.getId()), employee.getGender().name().toLowerCase()));

        HttpEntity<?> employeeForSave = new HttpEntity<>(employeeForCamera);

        ResponseEntity<CameraResponse> exchange = restTemplate.exchange(host + (isCreate ? USER_CREATE_URL : USER_UPDATE_URL), isCreate ? HttpMethod.POST : HttpMethod.PUT, employeeForSave, CameraResponse.class);
        return Objects.requireNonNull(exchange.getBody()).getStatusCode() == 1;
    }

    public boolean updateUserImg(Employee employee, String host) {
        MultiValueMap<String, Object> formData = new LinkedMultiValueMap<>();
        formData.add("FaceDataRecord", new Gson().toJson(new FaceDateRecord(String.valueOf(employee.getId()), "blackFD", "1")));
        String[] split = employee.getImageUrl().split("/");
        String s = split[split.length - 1];
        formData.add("img", new FileSystemResource("src/main/resources/images/" + s));

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(formData, headers);

        ResponseEntity<CameraResponse> exchange1 = restTemplate.exchange(host + USER_IMG_UPDATE_URL, HttpMethod.PUT, requestEntity, CameraResponse.class);

        return Objects.requireNonNull(exchange1.getBody()).getStatusCode() == 1;
    }
}
