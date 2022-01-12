package pl.sdacademy.java.adv.school.domain.student;

import pl.sdacademy.java.adv.school.domain.student.model.Student;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class StudentService {
    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getStudentsSortedByCityAndName() {

        List<Student> allStudents = studentRepository.findAllStudents();

        List<Student> lista = allStudents.stream()
                .sorted(Comparator.comparing(Student::getCity)
                        .thenComparing(Student::getLastName)
                        .thenComparing(Student::getFirstName))
                .toList();
        return lista;

    }

    public List<Student> getStudentsSortedByAge() {

        List<Student> allStudentsAge = studentRepository.findAllStudents();

        return allStudentsAge.stream()
                .sorted(Comparator.comparing(Student::getBirthDate))
                .collect(Collectors.toUnmodifiableList());

    }

    public List<Student> getStudentsSortedByAgeDesc() {
        List<Student> allStudentsAge = studentRepository.findAllStudents();

        return allStudentsAge.stream()
                .sorted(Comparator.comparing(Student::getBirthDate).reversed()).toList();
    }

    public Map<String, List<Student>> getStudentsGroupedByCityAndSortedByName() {

        return studentRepository.findAllStudents().stream()
                .sorted(Comparator.comparing(Student::getLastName).thenComparing(Student::getFirstName))
                .collect(Collectors.groupingBy(Student::getCity));
    }

    public Map<String, Student> getStudentsMappedByIdentifier() {
        return studentRepository.findAllStudents().stream()
                .collect(Collectors.toMap(Student::getId, student -> student) );
    }

}