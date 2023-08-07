package telran.spring.student.service;

import java.util.List;

import telran.spring.student.dto.*;

public interface StudentService {

	Boolean addStudent(StudentCreateDto studentCreateDto);

	StudentDto findStudent(Integer id);

	StudentDto removeStudent(Integer id);

	StudentCreateDto updateStudent(Integer id, StudentUpdateDto studentUpdateDto);

	Boolean addScore(Integer id, ScoreDto scoreDto);

	List<StudentDto> findStudentsByName(String name);

	Long getStudentsNamesQuantity(List<String> names);

	List<StudentDto> getStudentsByExamScore(String exam, Integer score);

}
