package id.ac.binus.programming.kost.user.controller;

import com.googlecode.jmapper.JMapper;
import id.ac.binus.programming.kost.user.common.ResponseSuccess;
import id.ac.binus.programming.kost.user.dto.UserRoleDTO;
import id.ac.binus.programming.kost.user.entity.UserRole;
import id.ac.binus.programming.kost.user.service.UserRoleService;
import id.ac.binus.programming.kost.user.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "userrole")
public class UserRoleController {
    @Autowired
    UserRoleService userRoleService;

    JMapper<UserRole, UserRoleDTO> mapper2Entity = new JMapper<>(UserRole.class, UserRoleDTO.class, "jmapper/user_role/entity_mapper.xml");
    JMapper<UserRoleDTO, UserRole> mapper2Dto = new JMapper<>(UserRoleDTO.class, UserRole.class, "jmapper/user_role/dto_mapper.xml");

    @PostMapping(value = "/create",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseSuccess<UserRoleDTO>> create(@RequestBody @Validated UserRoleDTO UserRoleDto) {
        /* Untuk mendapatkan nama method */
        String nameofCurrMethod = new Throwable()
                .getStackTrace()[0]
                .getMethodName();

        UserRole UserRoleEntity = mapper2Entity.getDestination(UserRoleDto);
        UserRole UserRoleCreate = userRoleService.create(UserRoleEntity);
        UserRoleDto = mapper2Dto.getDestination(UserRoleCreate);

        /* Response */
        ResponseSuccess<UserRoleDTO> responseSuccess = new ResponseSuccess<>();
        responseSuccess.setMessage("Success Create Data");
        responseSuccess.setService(nameofCurrMethod);
        responseSuccess.setTimestamp(new Timestamp(System.currentTimeMillis()));
        responseSuccess.setData(UserRoleDto);

        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(responseSuccess);
    }

    @PostMapping(value = "/update" ,consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseSuccess<UserRoleDTO>> update(@RequestBody @Validated UserRoleDTO UserRoleDto) {
        /* Untuk mendapatkan nama method */
        String nameofCurrMethod = new Throwable()
                .getStackTrace()[0]
                .getMethodName();
        UserRole UserRoleEntity = mapper2Entity.getDestination(UserRoleDto);
        UserRole UserRoleUpdated = userRoleService.updateById(UserRoleEntity);
        UserRoleDto = mapper2Dto.getDestination(UserRoleUpdated);

        ResponseSuccess<UserRoleDTO> responseSuccess = new ResponseSuccess<>();
        responseSuccess.setMessage("Success Update Data");
        responseSuccess.setService(nameofCurrMethod);
        responseSuccess.setData(UserRoleDto);

        return ResponseEntity.status(HttpStatus.OK).
                contentType(MediaType.APPLICATION_JSON)
                .body(responseSuccess);

    }

    @GetMapping(value = "/retrieve", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseSuccess<List<UserRoleDTO>>> findAll(Pageable pageable) {
        String nameofCurrMethod = new Throwable()
                .getStackTrace()[0]
                .getMethodName();

        List<UserRoleDTO> UserRoleDtoList = new ArrayList<>();
        List<UserRole> UserRoleEntity = userRoleService.findAll(pageable);
        UserRoleEntity.forEach(UserRole -> UserRoleDtoList.add(mapper2Dto.getDestination(UserRole)));

        ResponseSuccess<List<UserRoleDTO>> responseSuccess = new ResponseSuccess<>();
        responseSuccess.setMessage("Success Show Data");
        responseSuccess.setService(nameofCurrMethod);
        responseSuccess.setData(UserRoleDtoList);

        return ResponseEntity.status(HttpStatus.OK).
                contentType(MediaType.APPLICATION_JSON)
                .body(responseSuccess);
    }

    @PostMapping(value = "/findby", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseSuccess<UserRoleDTO>> findById(@RequestBody @Validated UserRoleDTO userRoleDto) {
        /* Untuk mendapatkan nama method */
        String nameofCurrMethod = new Throwable()
                .getStackTrace()[0]
                .getMethodName();
        try {
            UserRole UserRoleUpdated = userRoleService.findById(userRoleDto.getRoleid());

            userRoleDto = mapper2Dto.getDestination(UserRoleUpdated);

            ResponseSuccess<UserRoleDTO> responseSuccess = new ResponseSuccess<>();
            responseSuccess.setMessage("Success Show Data");
            responseSuccess.setService(nameofCurrMethod);
            responseSuccess.setData(userRoleDto);

            return ResponseEntity.status(HttpStatus.OK).
                    contentType(MediaType.APPLICATION_JSON)
                    .body(responseSuccess);
        }catch (Exception e){
            ResponseSuccess<UserRoleDTO> responseSuccess = new ResponseSuccess<>();
            responseSuccess.setMessage("Success Show Data");
            responseSuccess.setService(nameofCurrMethod);
            responseSuccess.setData(userRoleDto);

            return ResponseEntity.status(HttpStatus.OK).
                    contentType(MediaType.APPLICATION_JSON)
                    .body(responseSuccess);
        }

    }

}
