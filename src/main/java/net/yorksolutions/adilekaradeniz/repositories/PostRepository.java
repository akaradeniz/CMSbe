package net.yorksolutions.adilekaradeniz.repositories;

import net.yorksolutions.adilekaradeniz.entities.Posts;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.xml.crypto.Data;
import java.util.Date;
import java.util.Optional;

@Repository
public interface PostRepository extends CrudRepository<Posts, Long> {
    // Optional<Posts> findByIdAndAuthor(Long id);
     Iterable<Posts> findPostsByOrderByCreatedDateDesc();


}
