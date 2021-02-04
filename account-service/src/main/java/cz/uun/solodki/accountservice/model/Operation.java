package cz.uun.solodki.accountservice.model;


public class Operation {

    private int id;
    private String codeSource, codeDestination;
    private double value;
    private String date;
    private String text;

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

    public String getCodeDestination() {
        return codeDestination;
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

    private String type;
    
    public Operation(String codeSource, String codeDestination, double value, String text, String type) {
        assert (type.equals("transfer") || type.equals("credit_card"));
        this.codeSource = codeSource;
        this.codeDestination = codeDestination;
        this.value = value;
        this.text = text;
        this.type = type;
    }
    
    public void setType(String type) {
        assert (type.equals("transfer") || type.equals("credit_card"));
        this.type = type;
    }

}
