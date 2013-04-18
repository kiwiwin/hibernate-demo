package org.kiwi.hibernatedemo;

public class Note {
    private Integer id;
    private String question;
    private String answer;

    public Note() {
    }

    public Note(String question, String answer) {
        setQuestion(question);
        setAnswer(answer);
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    private void setQuestion(String question) {
        this.question = question;
    }

    public String getQuestion() {
        return question;
    }

    public String getAnswer() {
        return answer;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
