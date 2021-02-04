package cz.uun.solodki.accountservice.model;

import javax.persistence.*;

@Entity
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(name = "code")
    private String code;
    @Column(name = "type")
    private String type;
    @Column(name = "fee")
    private double fee;
    @Column(name = "interest")
    private double interest;

    public Account(String type, double fee, double interest) {
        this.code = generateCode(id);
        this.type = type;
        this.fee = fee;
        this.interest = interest;
    }

    public Account() {
    }

    @Override
    public String toString() {
        return "Account [id=" + id + ", code=" + code + ", type=" + type + "]";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getFee() {
        return fee;
    }

    public void setFee(double fee) {
        this.fee = fee;
    }

    public double getInterest() {
        return interest;
    }

    public void setInterest(double interest) {
        this.interest = interest;
    }

    public static String generateCode(int id) {
        String countryCode = "CZ";
        String bankCode = "2021";
        String accountNumber = String.format("%011d", id);
        return countryCode + " " + bankCode + " " + accountNumber;
    }
}