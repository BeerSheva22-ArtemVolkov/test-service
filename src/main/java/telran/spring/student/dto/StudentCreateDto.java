package telran.spring.student.dto;

import java.util.Map;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class StudentCreateDto {

	Integer id;
	String name;
	String password;
}
