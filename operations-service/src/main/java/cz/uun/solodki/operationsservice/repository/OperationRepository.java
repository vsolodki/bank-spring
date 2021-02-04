package cz.uun.solodki.operationsservice.repository;

import cz.uun.solodki.operationsservice.model.Operation;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OperationRepository extends CrudRepository<Operation, Integer> {
    List<Operation> findByCodeSourceOrCodeDestination(String codeSource, String codeDestination);
    List<Operation> findByDate(String date);
    List<Operation> findByType(String type);
    List<Operation> findAll();
}
