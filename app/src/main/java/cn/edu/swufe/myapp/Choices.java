package cn.edu.swufe.myapp;

public class Choices {
    private int id;
    private String question;
    private String result;
    private String time;

    public Choices() {
        super();
        question = "";
        result = "";
        time = "";
    }
    public Choices(String question, String result,String time) {
        super();
        this.question=question;
        this.result = result;
        this.time=time;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
