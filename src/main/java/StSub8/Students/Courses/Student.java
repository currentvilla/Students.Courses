package StSub8.Students.Courses;

import java.util.List;

public class Student {

  private String id;
  private String fullName;
  private String furigana;
  private String nickname;
  private String email;
  private String area;
  private Integer age;
  private String gender;

  private List<StudentCourse> courses;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getFullName() {
    return fullName;
  }

  public void setFullName(String fullName) {
    this.fullName = fullName;
  }

  public String getFurigana() {
    return furigana;
  }

  public void setFurigana(String furigana) {
    this.furigana = furigana;
  }

  public String getNickname() {
    return nickname;
  }

  public void setNickname(String nickname) {
    this.nickname = nickname;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getArea() {
    return area;
  }

  public void setArea(String area) {
    this.area = area;
  }

  public Integer getAge() {
    return age;
  }

  public void setAge(Integer age) {
    this.age = age;
  }

  public String getGender() {
    return gender;
  }

  public void setGender(String gender) {
    this.gender = gender;
  }


  public List<StudentCourse> getCourses() {
    return courses;
  }

  public void setCourses(List<StudentCourse> courses) {
    this.courses = courses;
  }

}
