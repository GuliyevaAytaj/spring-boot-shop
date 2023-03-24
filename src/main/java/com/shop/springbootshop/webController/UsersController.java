package com.shop.springbootshop.webController;
//
//import com.shop.springbootshop.DTOmodel.AuthenticationRequestDTO;
//import com.shop.springbootshop.DTOmodel.AuthenticationResponse;

import com.shop.springbootshop.DTOmodel.UsersDTO;
import com.shop.springbootshop.business.mapper.UsersMapping;
import com.shop.springbootshop.business.service.UsersService;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Configuration
@SecurityScheme(
        name = "Bearer Authentication",
        type = SecuritySchemeType.HTTP,
        bearerFormat = "JWT",
        scheme = "bearer"
)
@RestController
@RequestMapping("/api/v1/admin")
public class UsersController {

    @Autowired
    private final UsersService usersService;


    @Autowired
    private final UsersMapping mapper;

    public UsersController(UsersService usersService, UsersMapping mapper) {
        this.usersService = usersService;
        this.mapper = mapper;
    }


    @SecurityRequirement(name = "Bearer Authentication")
    @GetMapping("/find-all")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<UsersDTO>> getAllUsers() {
        List<UsersDTO> users = (usersService.getAllUsers());
        return
                ResponseEntity.ok(users);
    }

    @SecurityRequirement(name = "Bearer Authentication")
    @GetMapping("/find-by-id")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Optional<UsersDTO>> getById(String id) {
        Optional<UsersDTO> users = (usersService.getById(id));
        return
                ResponseEntity.ok(users);
    }


    @SecurityRequirement(name = "Bearer Authentication")
    @PutMapping("/user-update")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void updateById(@RequestParam String id, @RequestBody UsersDTO usersDTO) {
        usersService.updateUser(id, usersDTO);
    }

    @SecurityRequirement(name = "Bearer Authentication")
    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<?> deleteUser(String id) {
        Optional<UsersDTO> user = usersService.getById(id);
        if (user.isPresent()) {
            usersService.deleteById(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
