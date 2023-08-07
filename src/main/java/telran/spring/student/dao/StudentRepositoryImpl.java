package telran.spring.student.dao;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Component;

import telran.spring.student.model.Student;

@Component
public class StudentRepositoryImpl implements StudentRepository {

	Map<Integer, Student> students = new ConcurrentHashMap<>(); // для одновременного пользования
	
	@Override
	public Student save(Student student) {
		students.put(student.getId(), student);
		return student;
	}

	@Override
	public Optional<Student> findById(int id) {
		return Optional.ofNullable(students.get(id));
	}

	@Override
	public void deleteById(int id) {
		students.remove(id);
	}

	@Override
	public Iterable<Student> findAll() {
		return students.values();
	}

}
