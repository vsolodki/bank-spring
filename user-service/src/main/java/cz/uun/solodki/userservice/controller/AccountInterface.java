package cz.uun.solodki.userservice.controller;

import cz.uun.solodki.userservice.model.Account;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "account-service", url = "localhost:8004")
public interface AccountInterface {
	
	@GetMapping(value = "/accounts")
	List<Account> showAccounts();
	
	@PostMapping(value = "/addAccount")
	Account addAccountToNewUser(@RequestBody Account accountList);
	
	@PutMapping(value = "/editAccount")
	Account editAccount(@RequestBody Account accountList);
	
	@DeleteMapping(value = "/removeAccount")
	void deleAccount(@RequestParam int idd);

}