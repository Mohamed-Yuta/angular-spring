package com.allali.backend;

import com.allali.backend.entities.Payment;
import com.allali.backend.entities.Student;
import com.allali.backend.enums.PaymentType;
import com.allali.backend.repositories.PaymentRepository;
import com.allali.backend.repositories.StudentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.info.ProjectInfoProperties;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.util.Random;
import java.util.UUID;

@SpringBootApplication
public class BackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);
	}
	@Bean
	CommandLineRunner start(StudentRepository studentRepository , PaymentRepository paymentRepository){
		return args -> {
			studentRepository.save(Student.builder()
					.id(UUID.randomUUID().toString())
					.firstName("Amine")
							.code("AB2233")
					.build());
			studentRepository.save(Student.builder()
					.id(UUID.randomUUID().toString())
							.code("AB3344")
					.firstName("Basma")
					.build());
			studentRepository.save(Student.builder()
					.id(UUID.randomUUID().toString())
					.firstName("Mouad")
							.code("AB1122")
					.build());

			PaymentType[] paymentTypes = PaymentType.values();
			Random random = new Random();
			studentRepository.findAll().forEach(student -> {
				for (int i =0 ; i<5 ; i++ ){
					int index = random.nextInt(paymentTypes.length);
					Payment payment = Payment.builder()
							.amount(1000 + Math.random()*10000)
							.date(LocalDate.now())
							.type(paymentTypes[index])
							.file(UUID.randomUUID().toString())
							.student(student)
							.build();
					paymentRepository.save(payment);
				}
			});
		};
	}
}
