package springmvc.springmvc_thymeleaf_springsecurity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springmvc.springmvc_thymeleaf_springsecurity.entities.Patient;
import springmvc.springmvc_thymeleaf_springsecurity.repository.PatientRepository;

import java.util.Date;
import java.util.List;

@SpringBootApplication
public class HopitalApp implements CommandLineRunner {
    @Autowired
    private PatientRepository patientRepository;
    public static void main(String[] args) {
        SpringApplication.run(HopitalApp.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        patientRepository.save(new Patient(null,"Ziad",new Date(),false,23));
        patientRepository.save(new Patient(null,"Abdelhakim",new Date(),false,122));
        patientRepository.save(new Patient(null,"Youssef",new Date(),true,333));
        patientRepository.save(new Patient(null, "Sara", new Date(), false, 45));
        patientRepository.save(new Patient(null, "Mohammed", new Date(), true, 65));
        patientRepository.save(new Patient(null, "Fatima", new Date(), false, 30));
        patientRepository.save(new Patient(null, "Ali", new Date(), true, 50));
        patientRepository.save(new Patient(null, "Layla", new Date(), false, 35));
        patientRepository.save(new Patient(null, "Nadia", new Date(), true, 28));
        patientRepository.save(new Patient(null, "Ahmed", new Date(), false, 40));
        patientRepository.save(new Patient(null, "Hassan", new Date(), true, 55));
        patientRepository.save(new Patient(null, "Aisha", new Date(), false, 33));
        patientRepository.save(new Patient(null, "Jamil", new Date(), true, 60));
    }
}
