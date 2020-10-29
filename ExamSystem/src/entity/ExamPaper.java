package entity;

public class ExamPaper {
         private int qid;
         private String type;
         private String content;
         private int value;

    public int getQid() {
        return qid;
    }

    public void setQid(int qid) {
        this.qid = qid;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public ExamPaper(){
    }

    public ExamPaper(int qid , String type, String content,int value){
        this.qid = qid;
        this.type = type;
        this.content = content;
        this.value = value;
    }


    @Override
    public String toString() {
        return "ExamPaper{" +
                "qid=" + qid +
                ", type='" + type + '\'' +
                ", content='" + content + '\'' +
                ", value=" + value +
                '}';
    }


}
