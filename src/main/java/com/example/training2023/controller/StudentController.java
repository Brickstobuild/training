package com.example.training2023.controller;

import com.example.training2023.model.Student;
import com.example.training2023.service.StudentService;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


//Controller is the entry point of spring application and after that controller will redirect the requests to service
@RestController
@RequestMapping ("/student")

public class StudentController {

    @Autowired
    StudentService service;

    @GetMapping("/all")

    public List<Student> getAllStudents ()
    {
        return service.getAllStudents();
    }

    /*
    @GetMapping("/id/{rollno}")

    public Student getStudentByRollno(@PathVariable("rollno") int roll)
    {
        return service.getStudentByRollno(roll);
    }

     */

    //handling error msg -- by sending the user that there is some error in his request to db.
    @GetMapping("/id/{rollno}")
    public ResponseEntity <Object> getStudentByRollno(@PathVariable("rollno") int roll) {
        Student student = service.getStudentByRollno(roll);
        if (student.getRollno() == 0) {
            return new ResponseEntity<>("student not exists", HttpStatus.BAD_REQUEST);
        } else
            return new ResponseEntity<>(student, HttpStatus.OK);
    }


    @PostMapping("/add")

    public  void addStudent(@RequestBody Student student){

        service.addStudent(student);
    }


    //used for updating values in database
    @PutMapping("/update")

    public  void updateStudent(@RequestBody Student student) {

        service.upsertStudent(student);
    }

        //delete value from table
    @DeleteMapping("/delete/{rollno}")

    public  void deleteStudent(@PathVariable("rollno") int rollno)
        {

            service.deletStudent(rollno);

    }


    @GetMapping("/getbyname/{name}")

    public  List<Student> getByName(@PathVariable("name") String name)    {

        return service.getByName(name);

    }

    //to upload data from csv file to db
    @PostMapping("/upload")
    public ResponseEntity<?> handleFileUpload(@RequestParam("file")MultipartFile file) {
        String filename = file.getOriginalFilename();
        try {
            service.readFileContents(file.getInputStream());
            FileUtils.forceDelete(file.getResource().getFile());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        return ResponseEntity.ok("File uploaded successfully");

    }


    }

