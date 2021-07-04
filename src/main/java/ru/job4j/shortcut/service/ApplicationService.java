package ru.job4j.shortcut.service;

import ru.job4j.shortcut.model.entity.Site;
import ru.job4j.shortcut.model.entity.Url;
import java.util.List;

public interface ApplicationService {
    Site siteRegistration(Site site);
    Url urlRegistration(Url url, String login);
    Url findUrlForRedirect(String code);
    List<Url> getStatistic(String login);
}
