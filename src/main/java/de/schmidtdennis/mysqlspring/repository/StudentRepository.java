package de.schmidtdennis.mysqlspring.repository;

import de.schmidtdennis.mysqlspring.model.Student;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends CrudRepository<Student, String> {
}
