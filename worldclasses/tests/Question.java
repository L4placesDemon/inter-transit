package worldclasses.tests;

import java.io.Serializable;
import java.util.ArrayList;

public class Question implements Serializable {

    /* ATTRIBUTES ___________________________________________________________ */
    private String statement;
    private ArrayList<String> answers;
    private String answerOption;

    /* CONSTRUCTORS _________________________________________________________ */
    public Question() {
        answers = new ArrayList<>(4);
    }

    /* ______________________________________________________________________ */
    public Question(String statement, String a1, String a2, String a3, String a4) {
        this();
        this.statement = statement;
        this.answers.add(a1);
        this.answers.add(a2);
        this.answers.add(a3);
        this.answers.add(a4);
    }

    /* ______________________________________________________________________ */
    public Question(String statement, ArrayList<String> answers) {
        this.statement = statement;
        this.answers = answers;
    }

    /* METHODS ______________________________________________________________ */
    @Override
    public String toString() {
        return this.statement;
    }

    /* ______________________________________________________________________ */
    public void addOption(String option) {
        this.answers.add(option);
    }

    /* ______________________________________________________________________ */
    public void removeOption(String option) {
        this.answers.remove(option);
    }

    /* GETTERS ______________________________________________________________ */
    public String getStatement() {
        return this.statement;
    }

    /* ______________________________________________________________________ */
    public ArrayList<String> getAnswers() {
        return this.answers;
    }

    /* SETTERS ______________________________________________________________ */
    public void setStatement(String statement) {
        this.statement = statement;
    }

    /* ______________________________________________________________________ */
    public void setAnswers(ArrayList<String> answers) {
        this.answers = answers;
    }

    /* ______________________________________________________________________ */
    public void setCorrectAnswerIndex(int index) {
        this.answerOption = answers.get(index);
    }
}
