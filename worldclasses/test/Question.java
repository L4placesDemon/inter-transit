package worldclasses.test;

import java.util.ArrayList;
import tools.Pair;

public class Question {

    private String enunciado;

    private ArrayList<Pair<String, Integer>> options;

    public Question(String enunciado, ArrayList<Pair<String, Integer>> options) {
        this.enunciado = enunciado;
        this.options = options;
    }

    public Question(String enunciado) {
        this(enunciado, new ArrayList<>());
    }

    @Override
    public String toString() {
        return "Question{" + "enunciado=" + enunciado + ", options=" + options + '}';
    }

    public String getEnunciado() {
        return this.enunciado;
    }

    public ArrayList<Pair<String, Integer>> getOptions() {
        return this.options;
    }

    public void setEnunciado(String enunciado) {
        this.enunciado = enunciado;
    }

    public void setOptions(ArrayList<Pair<String, Integer>> options) {
        this.options = options;
    }
}
