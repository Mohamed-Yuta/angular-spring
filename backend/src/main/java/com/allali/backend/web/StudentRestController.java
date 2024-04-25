package com.allali.backend.web;

import com.allali.backend.entities.Payment;
import com.allali.backend.entities.Student;
import com.allali.backend.enums.PaymentStatus;
import com.allali.backend.enums.PaymentType;
import com.allali.backend.repositories.PaymentRepository;
import com.allali.backend.repositories.StudentRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@RestController
@AllArgsConstructor
public class StudentRestController {
    private StudentRepository studentRepository ;
    private PaymentRepository paymentRepository ;


    @GetMapping("/students")
    public List<Student> getStudents(){
        return studentRepository.findAll();
    }
    @GetMapping("/students/{id}")
    public Student getStudent(@PathVariable String id ){
        return studentRepository.findById(id).orElse(null);
    }
    @GetMapping("/payments")
    public List<Payment> allPayments(){
        return paymentRepository.findAll();
    }
    @GetMapping("payments/{id}")
    public Payment getPayment(@PathVariable Long id ) {
        return paymentRepository.findById(id).get();
    }
    @GetMapping("payments/{code}")
    public List<Payment> allPaymentsByCode(@PathVariable String code){
        return paymentRepository.findByStudentCode(code);
    }

    @PostMapping(value = "/payments",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public  Payment savePayment(@RequestParam MultipartFile file ,
                                LocalDate date , double amount , PaymentType type,
                                String studentCode) throws IOException {
        Path path = Paths.get(System.getProperty("user.home"),"students-app","payments");
        if(!Files.exists(path)){
            Files.createDirectories(path);
        }
        String fileId = UUID.randomUUID().toString();
        Path filePath = Paths.get(System.getProperty("user.home"),"students-app","payments",fileId+".pdf");
        Files.copy(file.getInputStream(),filePath);
        Student student = studentRepository.findByCode(studentCode);
        Payment payment = Payment.builder()
                .type(type)
                .amount(amount)
                .date(date)
                .status(PaymentStatus.CREATED)
                .student(student)
                .file(filePath.toUri().toString()).build();
        Payment savedPayment = paymentRepository.save(payment);
        return savedPayment;
    }

}
