package telran.spring.student.service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.objenesis.instantiator.basic.NewInstanceInstantiator;
import org.springframework.stereotype.Component;

import telran.spring.student.dao.StudentRepository;
import telran.spring.student.dto.*;
import telran.spring.student.model.Student;

@Component // благодоря этой аннотации, помещается в апапликационный контекст 
public class StudentServiceImpl implements StudentService {

	@Autowired
	StudentRepository studentRepository;

	@Override
	public Boolean addStudent(StudentCreateDto studentCreateDto) {
		if (studentRepository.findById(studentCreateDto.getId()).isPresent()) {
			return false;
		}
		Student student = new Student(studentCreateDto.getId(), studentCreateDto.getName(),
				studentCreateDto.getPassword());
		studentRepository.save(student);
		return true;
	}

	@Override
	public StudentDto findStudent(Integer id) {
		Student student = studentRepository.findById(id).orElse(null);
		return student == null ? null
				: StudentDto.builder().id(student.getId()).name(student.getName()).scores(student.getScores()).build();
	}

	@Override
	public StudentDto removeStudent(Integer id) {
		Student student = studentRepository.findById(id).orElse(null);
		if (student == null) {
			return null;
		} else {
			studentRepository.deleteById(id);
			return StudentDto.builder().id(id).name(student.getName()).scores(student.getScores()).build();
		}
	}

	@Override
	public StudentCreateDto updateStudent(Integer id, StudentUpdateDto studentUpdateDto) {
		Student student = studentRepository.findById(id).orElse(null);
		if (student == null) {
			return null;
		} else {
			student.setName(studentUpdateDto.getName());
			student.setPassword(studentUpdateDto.getPassword());
			return StudentCreateDto.builder().id(id).name(student.getName()).build();
		}
	}

	@Override
	public Boolean addScore(Integer id, ScoreDto scoreDto) {
		Student student = studentRepository.findById(id).orElse(null);
		if (student == null) {
			return null;
		}
		return student.addScore(scoreDto.getExamName(), scoreDto.getScore());
	}

	@Override
	public List<StudentDto> findStudentsByName(String name) {
		// spliterator - преобразует коллекцию или итератор студентов в сплитератор
		// Сплитератор используется для параллельной обработки элементов коллекции
		// StreamSupport.stream - создает стрим из сплитератора
		// Стрим представляет собой последовательность элементов, которые можно обрабатывать в функциональном стиле
		return StreamSupport.stream(studentRepository.findAll().spliterator(), false)
				.filter(s -> name.equalsIgnoreCase(s.getName()))
				.map(s -> new StudentDto(s.getId(), s.getName(), s.getScores())).collect(Collectors.toList());
	}

	@Override
	public Long getStudentsNamesQuantity(List<String> names) {
		return StreamSupport.stream(studentRepository.findAll().spliterator(), false)
			.filter(s -> names.contains(s.getName()))
			.count();
	}

	@Override
	public List<StudentDto> getStudentsByExamScore(String exam, Integer score) {
		return StreamSupport.stream(studentRepository.findAll().spliterator(), false)
				.filter(s -> s.getScores().containsKey(exam) && s.getScores().get(exam) > score)
				.map(s -> new StudentDto(s.getId(), s.getName(), s.getScores()))
				.collect(Collectors.toList());
	}

}
