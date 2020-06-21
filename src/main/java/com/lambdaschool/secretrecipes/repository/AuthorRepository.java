package com.lambdaschool.secretrecipes.repository;



import com.lambdaschool.secretrecipes.models.Author;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

public interface AuthorRepository extends CrudRepository<Author, Long> {

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM Wrote WHERE authorid = :authorid AND bookid = :bookid")
    void deleteAuthorsBooks(long authorid, long bookid);
}