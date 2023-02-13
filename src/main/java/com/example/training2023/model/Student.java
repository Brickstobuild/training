package com.example.training2023.model;


import javax.persistence.Entity;
import javax.persistence.Id;

//This java class is created same as data table in Db [name of class equal to table name) and variable names should be same as column names of table.

@Entity
public class Student {

    @Id
    private  int rollno;
    private String name;


    public  int getRollno ()
    {
        return  rollno;
    }

    public  void setRollno (int rollno)
    {
        this.rollno=rollno;
    }

    public  String  getName ()
    {
        return  name;
    }

    public  void setName (String name)
    {
        this.name=name;
    }



}
