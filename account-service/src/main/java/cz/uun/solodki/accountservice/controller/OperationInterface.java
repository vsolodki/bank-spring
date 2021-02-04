package cz.uun.solodki.accountservice.controller;

import cz.uun.solodki.accountservice.model.Operation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "operations-service", url = "localhost:8002")
public interface OperationInterface {
	
	@PostMapping(value = "/createTransaction")
	Operation makeTransaction(@RequestBody Operation operationsList);

}