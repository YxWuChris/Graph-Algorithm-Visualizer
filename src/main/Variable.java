package main;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Variable {

	private StringProperty name;
	private StringProperty value;
	
	public Variable(String name, String value){
		this.name = new SimpleStringProperty(name);
		this.value = new SimpleStringProperty(value);
	}
	
	public void setName(String name) {
		this.name = new SimpleStringProperty(name);
	}
	
	public void setValue(String value) {
		this.value = new SimpleStringProperty(value);
	}
	
	public StringProperty getName() {
	    return name;
	}
	
	public StringProperty getValue() {
		return value;
	}
}
