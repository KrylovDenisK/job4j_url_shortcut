package ru.job4j.shortcut.repo;

import org.springframework.data.repository.CrudRepository;
import ru.job4j.shortcut.model.entity.Site;

public interface SiteRepository extends CrudRepository<Site, Integer> {
    Site findByName(String name);
    Site findByLogin(String login);
}
