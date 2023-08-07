package telran.spring.student.dto;

import java.util.Map;

import lombok.*;

//@Getter
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StudentDto {

	Integer id;
	String name;
	Map<String, Integer> scores;
}
