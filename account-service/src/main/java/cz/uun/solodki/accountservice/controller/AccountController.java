package cz.uun.solodki.accountservice.controller;

import cz.uun.solodki.accountservice.model.Account;
import cz.uun.solodki.accountservice.model.Operation;
import cz.uun.solodki.accountservice.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AccountController {

    @Autowired
    private AccountRepository accountList;

    @Autowired
    private OperationInterface opp;
    
    @GetMapping(value = "/accounts")
    public List<Account> showAccounts() { return accountList.findAll(); }
    
    @GetMapping(value = "/addAccount")
    public String showAddAccount(Model model, @RequestParam int usr_id) {
        model.addAttribute("usr_id", usr_id);
        return "addAccount";
    }
    
    @PutMapping(value = "/editAccount")
	public Account editAccount(@RequestBody Account ac) {
    	Account ate = accountList.findById(ac.getId());
    	ate.setFee(ac.getFee());
    	ate.setInterest(ac.getInterest());
    	ate.setType(ac.getType());
    	accountList.save(ate);
		return ate;
    }

    @DeleteMapping(value = "/removeAccount")
    public void deleAccount(@RequestParam int idd) { accountList.deleteById(idd); }

    @PostMapping (value = "/addAccount")
    public ResponseEntity<Account> addAccountToNewUser(@RequestBody Account acb) {
        String accountType = acb.getType();
        double fee, interest;
        switch (accountType) {
            case "Transaction account":
                fee = 5;
                interest = 0.01;
                break;
            case "Life Savings":
                fee = 20;
                interest = 0.02;
                break;
            case "Life Insurance":
                fee = 15;
                interest = 0.03;
                break;
            default:
                fee = 0;
                interest = 0;
                break;
        }
        Account account = new Account(accountType, fee, interest);
        accountList.save(account);
        
        opp.makeTransaction(new Operation("Bank", account.getCode(), 10, "Bank fee", "transfer"));
        return new ResponseEntity<Account>(account, HttpStatus.CREATED);
    }


}