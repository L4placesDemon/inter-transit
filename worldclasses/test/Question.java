package worldclasses.test;

import java.util.ArrayList;
import tools.Pair;

public class Question {

	/* ATTRIBUTES ___________________________________________________________ */
	private String image;
    private String statement;

    private ArrayList<Pair<String, Integer>> answers;

	/* CONSTRUCTORS _________________________________________________________ */
    public Question(String enunciado, ArrayList<Pair<String, Integer>> options) {
        this.statement = enunciado;
        this.answers = options;
    }

	/* ______________________________________________________________________ */
    public Question(String enunciado) {
        this(enunciado, new ArrayList<>());
    }

	/* METHODS ______________________________________________________________ */
    @Override
    public String toString() {
        return "Question{" + "enunciado=" + statement + ", options=" + answers + '}';
    }

	/* GETTERS ______________________________________________________________ */
    public String getImage() {
    	return this.image;
    }

	/* ______________________________________________________________________ */
    public String getStatement() {
        return this.statement;
    }

	/* ______________________________________________________________________ */
    public ArrayList<Pair<String, Integer>> getAnswers() {
        return this.answers;
    }
    
	/* SETTERS ______________________________________________________________ */
    public void setImage(String image) {
    	this.image = image;
    }

	/* ______________________________________________________________________ */
    public void setStatement(String enunciado) {
        this.statement = enunciado;
    }

	/* ______________________________________________________________________ */
    public void setAnswers(ArrayList<Pair<String, Integer>> answers) {
        this.answers = answers;
    }
}
