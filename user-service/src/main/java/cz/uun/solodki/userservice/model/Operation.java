package cz.uun.solodki.userservice.model;

public class Operation {

    private int id;
    private String codeSource, codeDestination;
    private double value;
    private String date, text, type;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCodeSource() {
        return codeSource;
    }

    public void setCodeSource(String codeSource) {
        this.codeSource = codeSource;
    }

    public void setCodeDestination(String codeDestination) {
        this.codeDestination = codeDestination;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getType() {
        return type;
    }

    public Operation(String codeSource, String codeDestination, double value, String date, String text, String type) {
        assert (type.equals("transfer") || type.equals("credit_card"));
        this.codeSource = codeSource;
        this.codeDestination = codeDestination;
        this.value = value;
        this.date = date;
        this.text = text;
        this.type = type;
    }
    
    public Operation(String codeSource, String type) {
        assert (type.equals("transfer") || type.equals("credit_card"));
        this.codeSource = codeSource;
        this.codeDestination = "";
        this.value = 0;
        this.date = "";
        this.text = "";
        this.type = type;
    }
    
    public Operation() {
    }

    
    public void setType(String type) {
        assert (type.equals("transfer") || type.equals("credit_card"));
        this.type = type;
    }

    public String getCodeDestination() {
        return codeDestination;
    }
    

}
