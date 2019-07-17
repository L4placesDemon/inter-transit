package worldclasses.test;

import java.util.ArrayList;

public class Test {

	/* ATTRIBUTES ___________________________________________________________ */
	private String name;
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
        return "Test{" + "questions=" + questions + '}';
    }

    /* GETTERS ______________________________________________________________ */
    public String getName() {
    	return this.name;
    }
    /* ______________________________________________________________________ */
    public ArrayList<Question> getQuestions() {
        return this.questions;
    }

    /* SETTERS ______________________________________________________________ */
    public void setName(String name) {
    	this.name = name;
    }
    /* ______________________________________________________________________ */
    public void setQuestion(ArrayList<Question> questions) {
        this.questions = questions;
    }
}
