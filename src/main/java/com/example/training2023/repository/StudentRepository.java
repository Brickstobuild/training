package com.example.training2023.repository;


import com.example.training2023.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

//This repository class is created for data access and <Student-- tb table name, Integer --data type of primary key column in table student>

public interface StudentRepository  extends JpaRepository <Student,Integer>
{


    //method to write query
public List<Student> findByName(String name);  //select * from student where name=value


}
