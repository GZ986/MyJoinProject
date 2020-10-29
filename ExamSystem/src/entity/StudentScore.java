package entity;

public class StudentScore {
        private int number;
        private String username;
        private String college;
        private String major;
        private String score;

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
        return college;
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

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public StudentScore(){
    }

    public StudentScore(int number, String username, String college, String major, String Score){
        this.number = number;
        this.username = username;
        this.college = college;
        this.major = major;
        this.score = score;
    }

    @Override
    public String toString() {
        return "OwnScore{" +
                "number=" + number +
                ", username='" + username + '\'' +
                ", college='" + college + '\'' +
                ", major='" + major + '\'' +
                ", score='" + score + '\'' +
                '}';
    }


}
