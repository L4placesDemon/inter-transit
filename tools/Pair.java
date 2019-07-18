package tools;

import java.io.Serializable;

public class Pair<K, V> implements Serializable {

	/* ATTRIBUTES ___________________________________________________________ */
	private static final long serialVersionUID = -3561005297157629141L;
	
    private K key;
    private V value;

    /* CONSTRUCTORS _________________________________________________________ */
    public Pair(K key, V value) {
        this.key = key;
        this.value = value;
    }

    /* METHODS ______________________________________________________________ */
    @Override
    public String toString() {
        return "Pair{key=" + this.getKey() + ", value=" + this.getValue() + "}";
//        return "{" + this.getKey() + ": " + this.getValue() + "}";
    }

    /* GETTERS ______________________________________________________________ */
    public K getKey() {
        return this.key;
    }

    /* ______________________________________________________________________ */
    public V getValue() {
        return this.value;
    }

    /* SETTERS ______________________________________________________________ */
    public void setKey(K key) {
        this.key = key;
    }

    /* ______________________________________________________________________ */
    public void setValue(V value) {
        this.value = value;
    }
}
