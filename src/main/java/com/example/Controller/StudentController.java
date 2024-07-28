package com.example.Controller;
import com.example.ApiResponse.ApiResponse;
import com.example.Model.Student;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/student")
public class StudentController {

    private final List<Student> students = new ArrayList<>();

    @GetMapping("/get")
    public List<Student> getStudents() {
        return students;
    }

    @PostMapping("/add")
    public ApiResponse addStudent(@RequestBody Student student) {
        students.add(student);
        return new ApiResponse("Student added successfully");
    }

    @GetMapping("/students/{index}")
    public ApiResponse search(@PathVariable int index) {
        if (index >= 0 && index < students.size()) {
            Student student = students.get(index);

            boolean isGraduated = student.getStatus().equalsIgnoreCase("graduated");
            return new ApiResponse("Name: " + student.getName() + ", Age: " + student.getAge() + ", Degree: " + student.getDegree() + ", Graduated: " + isGraduated);
        }
        return new ApiResponse("Student not found");
    }

    @PutMapping("/update/{index}")
    public ApiResponse updateStudent(@PathVariable int index, @RequestBody Student updatedStudent) {
        if (index >= 0 && index < students.size()) {
            students.set(index, updatedStudent);
            return new ApiResponse("Student updated successfully");
        }
        return new ApiResponse("Student not found");
    }

    @DeleteMapping("/delete/{index}")
    public ApiResponse deleteStudent(@PathVariable int index) {
        if (index >= 0 && index < students.size()) {
            students.remove(index);
            return new ApiResponse("Student deleted successfully");
        }
        return new ApiResponse("Student not found");
    }
    @GetMapping("/name")
    public String getName() {
        return "ahmed almroba";
    }

    @GetMapping("/age")
    public String getAge() {
        return "25";
    }

    @GetMapping("/college/degree")
    public String getDegree() {
        return "Bachelor computer science";
    }

    @GetMapping("/study/status")
    public boolean getStudyStatus() {
        return true;
    }

    @GetMapping("/names")
    public ArrayList<String>  getnamee() {
        ArrayList<String> names = new ArrayList<>();
        names.add("ahmed");
        names.add("mohmmed");
        names.add("almroba");
        return names;
    }
}
