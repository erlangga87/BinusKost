package id.ac.binus.programming.kost.user.controller;

import com.googlecode.jmapper.JMapper;
import id.ac.binus.programming.kost.user.common.ResponseSuccess;
import id.ac.binus.programming.kost.user.dto.UserDTO;
import id.ac.binus.programming.kost.user.entity.User;
import id.ac.binus.programming.kost.user.service.UserService;
import org.omg.CORBA.portable.ApplicationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.transaction.SystemException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/user")
public class UserKostController {
    @Autowired
    UserService userService;

    JMapper<User, UserDTO> mapper2Entity = new JMapper<>(User.class, UserDTO.class, "jmapper/user/entity_mapper.xml");
    JMapper<UserDTO, User> mapper2Dto = new JMapper<>(UserDTO.class, User.class, "jmapper/user/dto_mapper.xml");

    @PostMapping(value = "/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseSuccess<UserDTO>> create(@RequestBody @Validated UserDTO userDTO) {
        /* Untuk mendapatkan nama method */
        String nameofCurrMethod = new Throwable()
                .getStackTrace()[0]
                .getMethodName();
        try {
                User userEntity = mapper2Entity.getDestination(userDTO);
                User user = userService.create(userEntity,nameofCurrMethod);
                userDTO = mapper2Dto.getDestination(user);

                /* Response */
                ResponseSuccess<UserDTO> responseSuccess = new ResponseSuccess<>();
                responseSuccess.setMessage("Success Create Data");
                responseSuccess.setService(nameofCurrMethod);
                responseSuccess.setTimestamp(new Timestamp(System.currentTimeMillis()));
                responseSuccess.setData(userDTO);

                return ResponseEntity
                        .status(HttpStatus.OK)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(responseSuccess);
            }catch (Exception e){
                /* Response */
                ResponseSuccess<UserDTO> responseSuccess = new ResponseSuccess<>();
                responseSuccess.setMessage(e.getMessage());
                responseSuccess.setService(nameofCurrMethod);
                responseSuccess.setTimestamp(new Timestamp(System.currentTimeMillis()));
                responseSuccess.setData(null);

                return ResponseEntity
                        .status(HttpStatus.BAD_REQUEST)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(responseSuccess);
            }
    }

    @PostMapping(value = "/update", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseSuccess<UserDTO>> update(@RequestBody @Validated UserDTO userDTO) {
        /* Untuk mendapatkan nama method */
        String nameofCurrMethod = new Throwable()
                .getStackTrace()[0]
                .getMethodName();
        try {
            User userEntity = mapper2Entity.getDestination(userDTO);
            User user = userService.updateById(userEntity, nameofCurrMethod);
            userDTO = mapper2Dto.getDestination(user);

            ResponseSuccess<UserDTO> responseSuccess = new ResponseSuccess<>();
            responseSuccess.setMessage("Success Update Data");
            responseSuccess.setService(nameofCurrMethod);
            responseSuccess.setData(userDTO);

            return ResponseEntity.status(HttpStatus.OK).
                    contentType(MediaType.APPLICATION_JSON)
                    .body(responseSuccess);
        }catch (Exception e){
            /* Response */
            ResponseSuccess<UserDTO> responseSuccess = new ResponseSuccess<>();
            responseSuccess.setMessage(e.getMessage());
            responseSuccess.setService(nameofCurrMethod);
            responseSuccess.setTimestamp(new Timestamp(System.currentTimeMillis()));
            responseSuccess.setData(null);

            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(responseSuccess);
        }

    }

    @GetMapping(value = "/retrieve", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseSuccess<List<UserDTO>>> findAll(Pageable pageable) {
        String nameofCurrMethod = new Throwable()
                .getStackTrace()[0]
                .getMethodName();

        List<UserDTO> UserDtoList = new ArrayList<>();
        List<User> UserEntity = userService.findAll(pageable);
        UserEntity.forEach(User -> UserDtoList.add(mapper2Dto.getDestination(User)));

        ResponseSuccess<List<UserDTO>> responseSuccess = new ResponseSuccess<>();
        responseSuccess.setMessage("Success Show Data");
        responseSuccess.setService(nameofCurrMethod);
        responseSuccess.setData(UserDtoList);

        return ResponseEntity.status(HttpStatus.OK).
                contentType(MediaType.APPLICATION_JSON)
                .body(responseSuccess);
    }

    @PostMapping(value = "/findby", produces = MediaType.APPLICATION_PROBLEM_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseSuccess<UserDTO>> findById(@RequestBody @Validated UserDTO userDTO) {
        /* Untuk mendapatkan nama method */
        String nameofCurrMethod = new Throwable()
                .getStackTrace()[0]
                .getMethodName();
        try {
            User user = userService.findById(userDTO.getUserid());

            userDTO = mapper2Dto.getDestination(user);

            ResponseSuccess<UserDTO> responseSuccess = new ResponseSuccess<>();
            responseSuccess.setMessage("Success Show Data");
            responseSuccess.setService(nameofCurrMethod);
            responseSuccess.setData(userDTO);

            return ResponseEntity.status(HttpStatus.OK).
                    contentType(MediaType.APPLICATION_JSON)
                    .body(responseSuccess);
        }catch (Exception e){
            ResponseSuccess<UserDTO> responseSuccess = new ResponseSuccess<>();
            responseSuccess.setMessage(e.getMessage());
            responseSuccess.setService(nameofCurrMethod);
            responseSuccess.setData(null);

            return ResponseEntity.status(HttpStatus.OK).
                    contentType(MediaType.APPLICATION_JSON)
                    .body(responseSuccess);
        }

    }

    @PostMapping(value = "/login", produces = MediaType.APPLICATION_PROBLEM_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseSuccess<UserDTO>> login(@RequestBody @Validated UserDTO userDTO) {
        /* Untuk mendapatkan nama method */
        String nameofCurrMethod = new Throwable()
                .getStackTrace()[0]
                .getMethodName();
        try {
            User userEntity = mapper2Entity.getDestination(userDTO);
            User user = userService.login(userEntity);
            userDTO = mapper2Dto.getDestination(user);

            ResponseSuccess<UserDTO> responseSuccess = new ResponseSuccess<>();
            responseSuccess.setMessage("Success Login");
            responseSuccess.setService(nameofCurrMethod);
            responseSuccess.setData(userDTO);

            return ResponseEntity.status(HttpStatus.OK).
                    contentType(MediaType.APPLICATION_JSON)
                    .body(responseSuccess);
        }catch (Exception e){
            /* Response */
            ResponseSuccess<UserDTO> responseSuccess = new ResponseSuccess<>();
            responseSuccess.setMessage(e.getMessage());
            responseSuccess.setService(nameofCurrMethod);
            responseSuccess.setTimestamp(new Timestamp(System.currentTimeMillis()));
            responseSuccess.setData(null);

            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(responseSuccess);
        }
    }

    @PostMapping(value = "/delete", produces = MediaType.APPLICATION_PROBLEM_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseSuccess<UserDTO>> delete(@RequestBody @Validated UserDTO userDTO) {
        /* Untuk mendapatkan nama method */
        String nameofCurrMethod = new Throwable()
                .getStackTrace()[0]
                .getMethodName();
        try {
            User userEntity = mapper2Entity.getDestination(userDTO);
            User user = userService.delete(userEntity.getUserid());

            userDTO = mapper2Dto.getDestination(user);

            ResponseSuccess<UserDTO> responseSuccess = new ResponseSuccess<>();
            responseSuccess.setMessage("Success Delete Data");
            responseSuccess.setService(nameofCurrMethod);
            responseSuccess.setData(userDTO);

            return ResponseEntity.status(HttpStatus.OK).
                    contentType(MediaType.APPLICATION_JSON)
                    .body(responseSuccess);
        }catch (Exception e){
            /* Response */
            ResponseSuccess<UserDTO> responseSuccess = new ResponseSuccess<>();
            responseSuccess.setMessage(e.getMessage());
            responseSuccess.setService(nameofCurrMethod);
            responseSuccess.setTimestamp(new Timestamp(System.currentTimeMillis()));
            responseSuccess.setData(null);

            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(responseSuccess);
        }

    }

}
