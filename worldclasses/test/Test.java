package worldclasses.test;

import java.util.ArrayList;

public class Test {

    private ArrayList<Question> questions;

    public Test(ArrayList<Question> questions) {
        this.questions = questions;
    }

    public Test() {
        this(new ArrayList<>());
    }

    @Override
    public String toString() {
        return "Test{" + "questions=" + questions + '}';
    }

    public ArrayList<Question> getQuestions() {
        return this.questions;
    }

    public void setQuestion(ArrayList<Question> questions) {
        this.questions = questions;
    }
}
