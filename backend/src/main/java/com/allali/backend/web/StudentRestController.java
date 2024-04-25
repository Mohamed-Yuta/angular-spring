package com.allali.backend.web;

import com.allali.backend.entities.Payment;
import com.allali.backend.entities.Student;
import com.allali.backend.repositories.PaymentRepository;
import com.allali.backend.repositories.StudentRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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

}
