package id.ac.binus.programming.kost.user.service;

import id.ac.binus.programming.kost.user.entity.UserRole;
import id.ac.binus.programming.kost.user.repository.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

@Service
public class UserRoleService {
    @Autowired
    UserRoleRepository userRoleRepository;

    public List<UserRole> findAll(Pageable pageable) {
        return userRoleRepository.findAll();
    }

    public UserRole findById(String id) {
        return userRoleRepository.find(id);
    }

    public UserRole updateById(UserRole userRole) {
        return userRoleRepository.save(userRole);
    }

    public UserRole create(UserRole userRole) {
        Calendar calendar = Calendar.getInstance();
        userRole.setRoleid(GenerateId(calendar.getTime(),4));
        return userRoleRepository.save(userRole);
    }

    public UserRole delete(String id) {
        return userRoleRepository.delete(id);
    }

    public static String GenerateId(Date date, int numdigit) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyMMdd");
        Random rand = new Random();
        String dateFormat = "";
        if (date != null) {
            dateFormat = sdf.format(date);
        }

        String[] arr = new String[]{"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"};
        String randomkey = "";

        for(int i = 0; i < numdigit; ++i) {
            int random = rand.nextInt(36);
            randomkey = randomkey + arr[random];
        }

        return dateFormat + randomkey;
    }

}
