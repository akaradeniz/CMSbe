package net.yorksolutions.adilekaradeniz.repositories;

import net.yorksolutions.adilekaradeniz.entities.Commend;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface CommendRepository extends CrudRepository<Commend, Long>, JpaRepository<Commend, Long> {
   Iterable<Commend> findAllByOrderByCreatedDateDesc();
    // List<Commend> findByIndex(Integer index);
}
