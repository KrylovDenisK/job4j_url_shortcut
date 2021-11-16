package ru.job4j.shortcut.repo;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.job4j.shortcut.model.entity.Url;



import java.util.List;
import java.util.Optional;

public interface UrlRepository extends CrudRepository<Url, Integer> {
    Optional<Url> findByCode(String code);
    List<Url> findBySite_Login(String login);
    @Transactional
    @Modifying
    @Query("UPDATE Url u SET u.total = u.total + 1 WHERE u.id = :id")
    void totalUpdate(@Param("id") Integer id);

}
