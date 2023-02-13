package com.example.training2023.service;

import com.example.training2023.model.Student;
import com.example.training2023.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;


//This service is where actual work happens

@Service
public class StudentService
{

    @Autowired
    StudentRepository repo;


    //Student in list student data type.
    public List <Student> getAllStudents ()
    {
        return repo.findAll(); // find all is similar to -- select * from student"
    }

//This metho will throw an error if we give value not present in db.
    /*   to get results based on condition
    public Employee getEmployeeByEmpno(int empno)
    {
        return repo.findById(empno).get();
    }

    */

//handling error from above function --if we give value which is not present in db -- its returning 404 not found
    //we are handling that exception using Optional class.
    public Student getStudentByRollno(int roll)
    {
        Optional <Student> opStudent=repo.findById(roll);  //select * from student where rollno=value;
        if (opStudent.isPresent())
              return opStudent.get();
        else
            return new Student();

    }


    public void addStudent(Student student){

           repo.save(student);  //insert into student table the  values();
    }

    public void upsertStudent(Student student){

        repo.save(student);  //insert or update into student table the  values();
    }


    public void deletStudent(int rollno){

        repo.deleteById(rollno);  //delete from  student ;
    }

    //We are using the query created in repository
    public List<Student> getByName(String name)
    {
        return repo.findByName(name);

    }

}
