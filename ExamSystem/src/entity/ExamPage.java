package entity;

public class ExamPage {
         private int id;
         private String name;
         private String time;
         private String teacher;

    public int getId() {
        return id;
    }

    public void setId(int id){this.id = id;}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public ExamPage(){

    }

    public ExamPage(int id,String name,String time,String teacher){
        this.id = id;
        this.name = name;
        this.time = time;
        this.teacher = teacher;
    }

    @Override
    public String toString() {
        return "ExamPage{" +
                "id='" + id + '\''+
                "name='" + name + '\'' +
                ", time='" + time + '\'' +
                ", teacher='" + teacher + '\'' +
                '}';
    }


}
