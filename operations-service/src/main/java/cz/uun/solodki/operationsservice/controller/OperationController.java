package cz.uun.solodki.operationsservice.controller;

import cz.uun.solodki.operationsservice.model.Operation;
import cz.uun.solodki.operationsservice.repository.OperationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import java.util.*;

@RestController
public class OperationController {
    @Autowired
    private OperationRepository operationRepository;
    
    @Autowired
    EntityManager em;

    @RequestMapping(value = "/createTransaction", method = RequestMethod.POST)
    public ResponseEntity<Operation> makeTransaction(@RequestBody Operation operationsList) {
    	Operation opss = new Operation(operationsList.getCodeSource(), operationsList.getCodeDestination(), operationsList.getAmount(), operationsList.getText(), "transfer");
        operationRepository.save(opss);
        return new ResponseEntity<Operation>(opss, HttpStatus.CREATED);
    }
    
    @RequestMapping(value = "/transaction/{code}", method = RequestMethod.GET)
    public String showTransactionWindow(Model model, @PathVariable String code) {
        model.addAttribute("accountCode", code);
        return "transaction";
    }
    
    @RequestMapping(value = "/operation_search", method = RequestMethod.GET)
    public String showOperationSearch(Model model, @RequestParam(value = "errorMessage", defaultValue = " ") String errorMessage) {
        model.addAttribute("errorMessage", errorMessage);
        return "operation_search";
    }
    
    
    @GetMapping(value = "/operations")
    public List<Operation> showOps(@RequestParam(value = "code", defaultValue = "") String code,
    		@RequestParam(value = "date", defaultValue = "") String date, @RequestParam(value = "type", defaultValue = "") String type) {
        
    	List<Operation> ops = new ArrayList<Operation>();
    	int noCriteria = 0;
        if (!code.isEmpty()) { ops.addAll(operationRepository.findByCodeSourceOrCodeDestination(code, code)); noCriteria++; }
        if (!date.isEmpty()) { ops.addAll(operationRepository.findByDate(date)); noCriteria++; }
        if (!type.isEmpty()) { ops.addAll(operationRepository.findByType(type)); noCriteria++; }
        
        Set<Operation> hs1 = new LinkedHashSet<>(ops);
        List<Operation> fina = new ArrayList<Operation>();
        for(Operation o: hs1) {
        	if(Collections.frequency(ops, o) == noCriteria)
        		fina.add(o);
        }
        
        return fina;
    }

}
