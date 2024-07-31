package comm.primeurad.arches.library.application.vo;

import java.util.ArrayList;
import java.util.List;

public class VOAuthor {

    // Variables of VOAuthorMapper class

    private String name;
    private String surname;
    private String day;
    private String month;
    private String year;
    private String email;
    private String phoneNumber;
    private String id;
    private String sex;
    private List<VOBook> voBookList = new ArrayList<>();

    // getters and setters for VOAuthorMapper class

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public List<VOBook> getVoBookList() {
        return voBookList;
    }

    public void setVoBookList(List<VOBook> voBookList) {
        this.voBookList = voBookList;
    }
}
