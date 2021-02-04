package cz.uun.solodki.operationsservice.model;

import javax.persistence.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
public class Operation {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(name = "code_source")
    private String codeSource;
    @Column(name = "code_destination")
    private String codeDestination;
    @Column(name = "amount")
    private double amount;
    @Column(name = "date")
    private String date;
    @Column(name = "text")
    private String text;
    @Column(name = "type")
    private String type;
    
    public Operation(String codeSource, String codeDestination, double amount, String text, String type) {
        assert (type.equals("transfer") || type.equals("credit_card"));
        this.codeSource = codeSource;
        this.codeDestination = codeDestination;
        this.amount = amount;
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        this.date = dateFormat.format(new Date());
        this.text = text;
        this.type = type;
    }

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

    public double getAmount() {
        return amount;
    }

    public void setAmount(double value) {
        this.amount = value;
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

    public Operation(String codeSource, String type) {
        assert (type.equals("transfer") || type.equals("credit_card"));
        this.codeSource = codeSource;
        this.codeDestination = "";
        this.amount = 0;
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
    
    @Override
    public boolean equals(Object obj) {
    	if (obj.getClass() != Operation.class) {
    		return false;
    	} else {
    		Operation operation = (Operation) obj;
    		return operation.getId() == this.id;
    	}
    	
    }

}
