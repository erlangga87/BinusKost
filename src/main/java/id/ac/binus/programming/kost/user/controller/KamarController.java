package id.ac.binus.programming.kost.user.controller;

import com.googlecode.jmapper.JMapper;
import id.ac.binus.programming.kost.user.common.ResponseSuccess;
import id.ac.binus.programming.kost.user.dto.KamarDTO;
import id.ac.binus.programming.kost.user.dto.UserDTO;
import id.ac.binus.programming.kost.user.dto.UserRoleDTO;
import id.ac.binus.programming.kost.user.entity.Kamar;
import id.ac.binus.programming.kost.user.entity.User;
import id.ac.binus.programming.kost.user.entity.UserRole;
import id.ac.binus.programming.kost.user.service.KamarService;
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
@RequestMapping(value = "/kamar")
public class KamarController {
    @Autowired
    KamarService kamarService;

    JMapper<Kamar, KamarDTO> mapper2Entity = new JMapper<>(Kamar.class, KamarDTO.class, "jmapper/kamar/entity_mapper.xml");
    JMapper<KamarDTO, Kamar> mapper2Dto = new JMapper<>(KamarDTO.class, Kamar.class, "jmapper/kamar/dto_mapper.xml");

    @PostMapping(value = "/create",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseSuccess<KamarDTO>> create(@RequestBody @Validated KamarDTO kamarDTO) {
        /* Untuk mendapatkan nama method */
        String nameofCurrMethod = new Throwable()
                .getStackTrace()[0]
                .getMethodName();

        try {
            Kamar kamarEntity = mapper2Entity.getDestination(kamarDTO);
            Kamar kamar = kamarService.create(kamarEntity,nameofCurrMethod);
            kamarDTO = mapper2Dto.getDestination(kamar);

            /* Response */
            ResponseSuccess<KamarDTO> responseSuccess = new ResponseSuccess<>();
            responseSuccess.setMessage("Success Create Data");
            responseSuccess.setService(nameofCurrMethod);
            responseSuccess.setTimestamp(new Timestamp(System.currentTimeMillis()));
            responseSuccess.setData(kamarDTO);

            return ResponseEntity
                    .status(HttpStatus.OK)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(responseSuccess);
        }catch (Exception e){
            /* Response */
            ResponseSuccess<KamarDTO> responseSuccess = new ResponseSuccess<>();
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

    @PostMapping(value = "/update" ,consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseSuccess<KamarDTO>> update(@RequestBody @Validated KamarDTO kamarDTO) {
        /* Untuk mendapatkan nama method */
        String nameofCurrMethod = new Throwable()
                .getStackTrace()[0]
                .getMethodName();
        try {
            Kamar kamarEntity = mapper2Entity.getDestination(kamarDTO);
            Kamar kamar = kamarService.updateById(kamarEntity, nameofCurrMethod);
            kamarDTO = mapper2Dto.getDestination(kamar);

            ResponseSuccess<KamarDTO> responseSuccess = new ResponseSuccess<>();
            responseSuccess.setMessage("Success Update Data");
            responseSuccess.setService(nameofCurrMethod);
            responseSuccess.setData(kamarDTO);

            return ResponseEntity.status(HttpStatus.OK).
                    contentType(MediaType.APPLICATION_JSON)
                    .body(responseSuccess);
        }catch (Exception e){
            /* Response */
            ResponseSuccess<KamarDTO> responseSuccess = new ResponseSuccess<>();
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
    public ResponseEntity<ResponseSuccess<List<KamarDTO>>> findAll(Pageable pageable) {
        String nameofCurrMethod = new Throwable()
                .getStackTrace()[0]
                .getMethodName();

        List<KamarDTO> kamarDtoList = new ArrayList<>();
        List<Kamar> kamarEntity = kamarService.findAll(pageable);
        kamarEntity.forEach(Kamar -> kamarDtoList.add(mapper2Dto.getDestination(Kamar)));

        ResponseSuccess<List<KamarDTO>> responseSuccess = new ResponseSuccess<>();
        responseSuccess.setMessage("Success Show Data");
        responseSuccess.setService(nameofCurrMethod);
        responseSuccess.setData(kamarDtoList);

        return ResponseEntity.status(HttpStatus.OK).
                contentType(MediaType.APPLICATION_JSON)
                .body(responseSuccess);
    }

    @PostMapping(value = "/findby", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseSuccess<KamarDTO>> findById(@RequestBody @Validated KamarDTO kamarDto) {
        /* Untuk mendapatkan nama method */
        String nameofCurrMethod = new Throwable()
                .getStackTrace()[0]
                .getMethodName();
        try {
            Kamar kamar = kamarService.findById(kamarDto.getKamarid());

            kamarDto = mapper2Dto.getDestination(kamar);

            ResponseSuccess<KamarDTO> responseSuccess = new ResponseSuccess<>();
            responseSuccess.setMessage("Success Show Data");
            responseSuccess.setService(nameofCurrMethod);
            responseSuccess.setData(kamarDto);

            return ResponseEntity.status(HttpStatus.OK).
                    contentType(MediaType.APPLICATION_JSON)
                    .body(responseSuccess);
        }catch (Exception e){
            /* Response */
            ResponseSuccess<KamarDTO> responseSuccess = new ResponseSuccess<>();
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
    public ResponseEntity<ResponseSuccess<KamarDTO>> delete(@RequestBody @Validated KamarDTO kamarDTO) {
        /* Untuk mendapatkan nama method */
        String nameofCurrMethod = new Throwable()
                .getStackTrace()[0]
                .getMethodName();
        try {
            Kamar kamarEntity = mapper2Entity.getDestination(kamarDTO);
            Kamar kamar = kamarService.delete(kamarEntity.getKamarid());

            kamarDTO = mapper2Dto.getDestination(kamar);

            ResponseSuccess<KamarDTO> responseSuccess = new ResponseSuccess<>();
            responseSuccess.setMessage("Success Delete Data");
            responseSuccess.setService(nameofCurrMethod);
            responseSuccess.setData(kamarDTO);

            return ResponseEntity.status(HttpStatus.OK).
                    contentType(MediaType.APPLICATION_JSON)
                    .body(responseSuccess);
        }catch (Exception e){
            /* Response */
            ResponseSuccess<KamarDTO> responseSuccess = new ResponseSuccess<>();
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
