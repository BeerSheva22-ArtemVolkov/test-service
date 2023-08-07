package telran.spring.student.dao;

import java.util.Optional;
import java.util.stream.Stream;

import org.springframework.data.repository.CrudRepository;

import telran.spring.student.model.Student;

// 
public interface StudentRepository {

	// Репозиторий должен уметь реализовывать CRUD

	Student save(Student student);

	Optional<Student> findById(int id);

	void deleteById(int id);
	
	Iterable<Student> findAll();

	
}
