package cz.uun.solodki.userservice.controller;

import cz.uun.solodki.userservice.model.Account;
import cz.uun.solodki.userservice.model.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

@Controller
public class UserController {
	
    @Autowired
    private AccountInterface accountList;
    
    @Autowired
    private OperationInterface ops;
    
    DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

    @RequestMapping(value = "/removeAccount")
    public String deleteAccount(@RequestParam int idd) {
        accountList.deleAccount(idd);
        return "redirect:/accounts";
    }

    @RequestMapping(value = "/accounts", method = RequestMethod.GET)
    public String findByUser(Model model) {
        model.addAttribute("accounts", accountList.showAccounts());
        return "home";
    }

    @RequestMapping(value = "/addAccount", method = RequestMethod.POST)
    public String openAccount(@RequestParam String type) {
        Account newAcc = new Account();
        newAcc.setType(type);
        accountList.addAccountToNewUser(newAcc);
        return "redirect:/accounts/";
    }

    @RequestMapping(value = "/editAccount")
    public String editAccount(@RequestParam int acid, @RequestParam String type, @RequestParam double fee, @RequestParam double interest) {
        Account newAcc = new Account();
        newAcc.setId(acid);
        newAcc.setInterest(interest);
        newAcc.setType(type);
        newAcc.setFee(fee);
        accountList.editAccount(newAcc);
        return "redirect:/accounts/";
    }
    @RequestMapping(value = "/transaction/{accountCode}", method = RequestMethod.GET)
    public String showTransactionForm(Model model, @PathVariable String accountCode) {
        model.addAttribute("accountCode", accountCode);
        model.addAttribute("operation", new Operation());
        return "transaction";
    }

    @RequestMapping(value = "/createTransaction", method = RequestMethod.POST)
    public String doingTransaction(@ModelAttribute("Operation") Operation acE, @RequestParam String accId) {
    	acE.setCodeSource(accId);
    	ops.makeTransaction(acE);
        return "redirect:/operations?code=" + accId;
    }

    @RequestMapping(value = "/operation_search", method = RequestMethod.GET)
    public String showOperations(Model model) {
        
        return "operation_search";
    }

    @RequestMapping(value = "/operations", method = RequestMethod.GET)
    public String showOperations(Model model, @RequestParam(value = "code", defaultValue = "") String code,
    		@RequestParam(value = "date", defaultValue = "") String date, @RequestParam(value = "type", defaultValue = "") String type) {
        model.addAttribute("operations", ops.showOps(code,date,type));
        model.addAttribute("code", code);
        
        double sum = 0;
        for(Operation operationsList : ops.showOps(code,date,type)) {
        	if(operationsList.getCodeDestination().equals(code))
        		sum += operationsList.getValue();
        	else
        		sum -= operationsList.getValue();
        }
        
        model.addAttribute("balance", sum);
        
        return "operations";
    }
    
}