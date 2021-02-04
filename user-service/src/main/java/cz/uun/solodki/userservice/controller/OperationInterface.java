package cz.uun.solodki.userservice.controller;

import cz.uun.solodki.userservice.model.Operation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "operations-service", url = "localhost:8002")
public interface OperationInterface {
	
	@GetMapping(value = "/operations")
	List<Operation> showOps(@RequestParam String code, @RequestParam String date, @RequestParam String type);
	
	@PostMapping(value = "/createTransaction")
	Operation makeTransaction(@RequestBody Operation operationsList);

}