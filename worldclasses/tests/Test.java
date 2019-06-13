package worldclasses.tests;

import java.io.Serializable;
import java.util.ArrayList;

public class Test implements Serializable {

    /* ATTRIBUTES ___________________________________________________________ */
    private ArrayList<Question> questions;

    /* CONSTRUCTORS _________________________________________________________ */
    public Test(ArrayList<Question> questions) {
        this.questions = questions;
    }

    /* ______________________________________________________________________ */
    public Test() {
        this(new ArrayList<>());
    }

    /* METHODS ______________________________________________________________ */
    @Override
    public String toString() {
        return this.questions.toString();
    }

    /* ______________________________________________________________________ */
    public void addQuestion(Question question) {
        this.questions.add(question);
    }

    /* ______________________________________________________________________ */
    public void removeQuestion(Question question) {
        this.questions.remove(question);
    }

    /* GETTERS ______________________________________________________________ */
    public ArrayList<Question> getQuestions() {
        return this.questions;
    }

    /* SETTERS ______________________________________________________________ */
    public void setQuestions(ArrayList<Question> questions) {
        this.questions = questions;
    }
}
