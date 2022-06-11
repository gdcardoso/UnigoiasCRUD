package com.example.unigoiascrud;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "user_table")
public class UserModal {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String userName;
    private String courseDescription;
    private String estiloCurso;
    public UserModal(String userName, String courseDescription, String estiloCurso) {
        this.userName = userName;
        this.courseDescription = courseDescription;
        this.estiloCurso = estiloCurso;
    }
    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public String getCourseDescription() {
        return courseDescription;
    }
    public void setCourseDescription(String courseDescription) {
        this.courseDescription = courseDescription;
    }
    public String getEstiloCurso() {
        return estiloCurso;
    }
    public void setEstiloCurso(String estiloCurso) {
        this.estiloCurso = estiloCurso;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
}