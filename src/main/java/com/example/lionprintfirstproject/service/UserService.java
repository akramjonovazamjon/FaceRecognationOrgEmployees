package com.example.lionprintfirstproject.service;

import com.example.lionprintfirstproject.dto.user.CreateUser;
import com.example.lionprintfirstproject.dto.user.LoginDto;
import com.example.lionprintfirstproject.dto.user.TokenResult;
import com.example.lionprintfirstproject.dto.user.UpdateUser;
import com.example.lionprintfirstproject.entity.User;
import com.example.lionprintfirstproject.entity.UserRole;
import com.example.lionprintfirstproject.exception.user.UserExistByUsernameException;
import com.example.lionprintfirstproject.exception.user.UserNotFoundByIdException;
import com.example.lionprintfirstproject.exception.user.UserNotFoundByUsernameException;
import com.example.lionprintfirstproject.exception.user.UserPasswordNoMatchesException;
import com.example.lionprintfirstproject.repository.UserRepository;
import com.example.lionprintfirstproject.util.JwtUtils;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;

    @SneakyThrows
    public User create(CreateUser dto) {

        if (repository.existsByUsername(dto.username())) {
            throw new UserExistByUsernameException(dto.username());
        }

        User user = User.of(dto.fullName(), dto.username(), passwordEncoder.encode(dto.password()), UserRole.valueOf(dto.role()));

        return repository.save(user);
    }

//    private static final String BASE_IMAGE_PATH = "src/main/resources/";
//
//    private String saveUserPicture(MultipartFile picture) throws IOException {
//        if (Objects.isNull(picture) || picture.isEmpty()) {
//            throw new PictureNotFoundException();
//        }
//        String imageUrl = String.format("images/%s.jpg", UUID.randomUUID());
//        File file = new File(BASE_IMAGE_PATH + imageUrl);
//        file.getParentFile().mkdirs();
//        file.createNewFile();
//        try (FileOutputStream outputStream = new FileOutputStream(file)) {
//            byte[] mainContent = picture.getBytes();
//            outputStream.write(mainContent);
//        }
//        return imageUrl;
//    }

    @Value("${jwt.secret}")
    private String tokenSecret;

    public TokenResult login(LoginDto dto) {
        User user = repository.findByUsername(dto.username()).orElseThrow(() -> new UserNotFoundByUsernameException(dto.username()));
        if (!passwordEncoder.matches(dto.password(), user.getPassword())) {
            throw new UserPasswordNoMatchesException();
        }

        String token = JwtUtils.generateToken(user.asDetailedUser(), tokenSecret);
        return new TokenResult(token);
    }

    public void update(Long id, UpdateUser dto) {
        if (repository.existsByUsernameAndIdNot(dto.username(), id)) {
            throw new UserExistByUsernameException(dto.username());
        }

        User user = repository.findById(id).orElseThrow(() -> new UserNotFoundByIdException(id));

        user.update(dto);

        repository.save(user);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
