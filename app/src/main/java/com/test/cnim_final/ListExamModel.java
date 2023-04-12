package com.test.cnim_final;

public class ListExamModel
{
    private int id;
    private String materie;
    private int date;

    //constructors

    public ListExamModel(int id, String materie, int date) {
        this.id = id;
        this.materie = materie;
        this.date = date;
    }

    public ListExamModel() {
    }

    //toString is necessary for printing the contents of a class object

    @Override
    public String toString() {
        return "ListExamModel{" +
                "id=" + id +
                ", materie='" + materie + '\'' +
                ", date=" + date +
                '}';
    }


    //getters and setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMaterie() {
        return materie;
    }

    public void setMaterie(String materie) {
        this.materie = materie;
    }

    public int getDate() {
        return date;
    }

    public void setDate(int date) {
        this.date = date;
    }
}
