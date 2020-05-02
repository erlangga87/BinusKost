package id.ac.binus.programming.kost.user.service;

import id.ac.binus.programming.kost.user.entity.Kamar;
import id.ac.binus.programming.kost.user.entity.User;
import id.ac.binus.programming.kost.user.entity.UserRole;
import id.ac.binus.programming.kost.user.repository.KamarRepository;
import id.ac.binus.programming.kost.user.repository.UserRepository;
import id.ac.binus.programming.kost.user.repository.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.regex.Pattern;

@Service
public class KamarService {
    @Autowired
    KamarRepository kamarRepository;

    @Autowired
    UserRepository userRepository;

    public List<Kamar> findAll(Pageable pageable) {
        return kamarRepository.findAll();
    }

    public Kamar findById(String kamarid) throws Exception {
        if (kamarid == null)
            throw new Exception("kamarid is required");
        return kamarRepository.find(kamarid);
    }

    public Kamar updateById(Kamar kamar,String operation) throws Exception {
        validation(kamar, operation);
        User penghuni = userRepository.find(kamar.getPenghuni());
        if (penghuni == null)
            throw new Exception("penghuni not found");
        return kamarRepository.save(kamar);
    }

    public Kamar create(Kamar kamar,String operation) throws Exception {
        Calendar calendar = Calendar.getInstance();
        validation(kamar, operation);
        User penghuni = userRepository.find(kamar.getPenghuni());
        if (penghuni == null)
            throw new Exception("penghuni not found");
        kamar.setKamarid(GenerateId(calendar.getTime(),4));
        return kamarRepository.save(kamar);
    }

    public Kamar delete(String kamarid) throws Exception {
        Kamar kamarDB = kamarRepository.find(kamarid);
        if (kamarDB == null)
            throw new Exception("kamarid not found");
        return kamarRepository.delete(kamarid);
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
    public void validation(Kamar kamar, String operation) throws Exception {
        if (operation.toUpperCase().equals("UPDATE")){
            if (kamar.getKamarid() == null || kamar.getKamarid() == "")
                throw new Exception("kamarid is required");
        }
        if (kamar.getHarga() == null || kamar.getHarga() == "")
            throw new Exception("harga is required");
        if (kamar.getLuaskamar() == null || kamar.getLuaskamar() == "")
            throw new Exception("luas kamar is required");
        if (kamar.getKasurdanlemari() == null)
            throw new Exception("info kasur dan lemari is required");
        if (kamar.getKmdalam() == null )
            throw new Exception("info KM dalam is required");
        if (kamar.getAc() == null)
            throw new Exception("info ac is required");
        if (kamar.getStatus() == null)
            throw new Exception("status is required");
    }
}
