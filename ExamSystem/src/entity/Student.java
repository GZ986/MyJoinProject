package entity;

public class Student {
        private int number;
        private String username;
        private String college;
        private String major;
        private String password;

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCollege() {
        return this.college;
    }

    public void setCollege(String college) {
        this.college = college;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Student(){

    }

    public Student(int number,String username,String college,String major,String password){
        this.number = number;
        this.username = username;
        this.college = college;
        this.major = major;
        this.password = password;
    }

    @Override
    public String toString() {
        return "Student{" +
                "number=" + number +
                ", username='" + username + '\'' +
                ", college='" + college + '\'' +
                ", major='" + major + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}

