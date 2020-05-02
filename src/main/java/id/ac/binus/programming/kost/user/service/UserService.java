package id.ac.binus.programming.kost.user.service;

import id.ac.binus.programming.kost.user.dto.UserDTO;
import id.ac.binus.programming.kost.user.entity.User;
import id.ac.binus.programming.kost.user.entity.UserRole;
import id.ac.binus.programming.kost.user.repository.UserRepository;
import id.ac.binus.programming.kost.user.repository.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.logging.Logger;
import java.util.regex.Pattern;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    UserRoleRepository userRoleRepository;

    public List<User> findAll(Pageable pageable) {
        return userRepository.findAll();
    }

    public User findById(String id) throws Exception {
        if (id == null || id == "")
            throw new Exception("userid is required");
        return userRepository.find(id);
    }

    public User updateById(User user, String operation) throws Exception {
        User userDB = userRepository.find(user.getUserid());
        if (userDB == null)
            throw new Exception("userid not found");
        UserRole userRoleDB =  userRoleRepository.find(user.getRoleid());
        if (userRoleDB == null){
            throw new Exception("roleid not found");
        }
        validation(user, operation);
        return userRepository.save(user);
    }

    public User create(User user, String operation) throws Exception {
        Calendar calendar = Calendar.getInstance();
        validation(user,operation);
        UserRole userRoleDB =  userRoleRepository.find(user.getRoleid());
        if (userRoleDB == null)
            throw new Exception("roleid not found");
        User userDB = userRepository.findByUsernameAndPassword(user.getEmail(),user.getUsername());
        if (userDB !=null)
            throw new Exception("email or username already exist");
        user.setPassword(md5Java(user.getPassword()));
        user.setUserid(GenerateId(calendar.getTime(),4));
        return userRepository.save(user);
    }

    public User delete(String userid) throws Exception {
        if (userid == null)
            throw new Exception("userid is required");
        User userDB = userRepository.find(userid);
        if (userDB == null)
            throw new Exception("userid not found");
        return userRepository.delete(userid);
    }

    public User login (User user) throws Exception {
        User userDB = userRepository.findByUsernameAndPassword(user.getUsername(),md5Java(user.getPassword()));
        if (userDB == null){
            throw new Exception("Login failed");
        }
        return userDB;
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

    public void validation(User user, String operation) throws Exception {
        if (operation.toUpperCase().equals("UPDATE")){
            if (user.getUserid() == null || user.getUserid() == "")
                throw new Exception("userid is required");
        }
        if (user.getRoleid() == null || user.getRoleid() == "")
            throw new Exception("roleid is required");
        if (user.getEmail() == null || user.getEmail() == "")
            throw new Exception("email is required");
        else
            if (!Pattern.matches("^[A-Za-z0-9+_.-]+@(.+)$",user.getEmail()))
                throw new Exception("email is invalid");
        if (user.getUsername() == null || user.getUsername() == "")
            throw new Exception("username is required");
        if (user.getPassword() == null || user.getPassword() == "")
            throw new Exception("password is required");
        if (user.getFirstname() == null || user.getFirstname() == "")
            throw new Exception("firstname is required");
        if (user.getLastname() == null || user.getLastname() == "")
            throw new Exception("lastname is required");
    }
    public static String md5Java(String message)
    {
        String digest = null;
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] hash = md.digest(message.getBytes("UTF-8"));
            //merubah byte array ke dalam String Hexadecimal
            StringBuilder sb = new StringBuilder(2*hash.length);
            for(byte b : hash)
            {
                sb.append(String.format("%02x", b&0xff));
            }
            digest = sb.toString();
        } catch (UnsupportedEncodingException ex)
        {
        } catch (NoSuchAlgorithmException ex)
        {
        }
        return digest;
    }


}
