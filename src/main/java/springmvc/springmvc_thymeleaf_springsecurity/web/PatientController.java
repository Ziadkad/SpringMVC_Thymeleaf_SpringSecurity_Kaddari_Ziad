package springmvc.springmvc_thymeleaf_springsecurity.web;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import springmvc.springmvc_thymeleaf_springsecurity.entities.Patient;
import springmvc.springmvc_thymeleaf_springsecurity.repository.PatientRepository;

import java.util.List;

@Controller
@AllArgsConstructor
public class PatientController {
    private PatientRepository patientRepository;
    @GetMapping("/index")
    public  String index(Model model,
                         @RequestParam(name="page", defaultValue = "0") int page, @RequestParam(name = "size", defaultValue = "10") int size, @RequestParam(name = "keyword", defaultValue = "") String kw) {
        Page<Patient> patientList = patientRepository.findByNomContains(kw,PageRequest.of(page,size));
        model.addAttribute("listPatients", patientList.getContent());
        model.addAttribute("pages", new int[patientList.getTotalPages()]);
        model.addAttribute("currentPage", page);
        model.addAttribute("keyword",kw);
        return "patients";
    }
    @GetMapping("/delete")
    public String delete(Long id, String keyword, String page){
        patientRepository.deleteById(id);
        return  "redirect:/index?page="+page+"&keyword="+keyword;
    }
    @GetMapping("/")
    public String home() {
        return "redirect:/index";
    }
    @GetMapping("/patients")
    @ResponseBody
    public List<Patient> listPatients(){
        return patientRepository.findAll();
    }
    @GetMapping("/formPatients")
    public String formPatients(Model model){
        model.addAttribute("patient",new Patient());
        return "formPatients";
    }
    @PostMapping(path = "/save")
    public String save(Model model,Patient patient){
        patientRepository.save(patient);
        return "redirect:/index";
    }
    @GetMapping("/editPatient")
    public String editPatient(Model model, Long id){
        Patient patient = patientRepository.findById(id).orElse(null);
        if(patient==null) throw new RuntimeException("Patient introuvable");
        model.addAttribute("patient",patient);
        return "editPatient";
    }

    @GetMapping(value="/login")
    public String login(Model model){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName(); //get logged in username

        model.addAttribute("username", name);
        return "login";
    }

    @GetMapping("/logout")
    public String logout(){
        return "redirect:/login?logout";
    }
}
