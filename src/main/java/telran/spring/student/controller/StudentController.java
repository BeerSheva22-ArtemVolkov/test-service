package telran.spring.student.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;
import telran.spring.student.dto.*;
import telran.spring.student.service.StudentService;

@RestController
@RequiredArgsConstructor
public class StudentController {

//	@Autowired
//	StudentService studentService;
//	StudentService studentService = new StudentServiceImpl(); // Аггрегация
//	public StudentController(StudentService studentService) { // Композиция
//		this.studentService = studentService;
//	}
	
	// не нужно писать @Autowired, тк есть аннотация @RequiredArgsConstructor, которая создаст объект класса
	final StudentService studentService; 
	
	@PostMapping("/student")
	public Boolean addStudent(@RequestBody StudentCreateDto studentCreateDto) {
		return studentService.addStudent(studentCreateDto);
	}

	@GetMapping("/student/{id}")
	public StudentDto findStudent(@PathVariable Integer id) {
		return studentService.findStudent(id);
	}

	@DeleteMapping("/student/{id}")
	public StudentDto removeStudent(@PathVariable Integer id) {
		return studentService.removeStudent(id);
	}
	
	@PutMapping("/student/{id}")
	public StudentCreateDto editStudent(@PathVariable Integer id, @RequestBody StudentUpdateDto studentUpdateDto) {
		return studentService.updateStudent(id, studentUpdateDto);
	}
	
	@GetMapping("/student/name/{name}")
	public List<StudentDto> findStudentsByName(@PathVariable String name){
		return studentService.findStudentsByName(name);
	}
	
	@PostMapping("/quantity/students")
	public long studentNamesQuantity(@RequestBody List<String> names) {
		return studentService.getStudentsNamesQuantity(names);
	}

	
	
	
}
