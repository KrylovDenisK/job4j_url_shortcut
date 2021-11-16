package ru.job4j.shortcut.service;

import org.springframework.stereotype.Service;
import ru.job4j.shortcut.exceptions.ItemNotFoundException;
import ru.job4j.shortcut.model.entity.Site;
import ru.job4j.shortcut.model.entity.Url;
import ru.job4j.shortcut.repo.SiteRepository;
import ru.job4j.shortcut.repo.UrlRepository;
import ru.job4j.shortcut.service.generator.GenaratingValue;

import java.util.List;

@Service
public class ApplicationServiceImpl implements ApplicationService {
    private SiteRepository siteRepository;
    private GenaratingValue stringGenerator;
    private UrlRepository urlRepository;

    public ApplicationServiceImpl(SiteRepository siteRepository, GenaratingValue stringGenerator, UrlRepository urlRepository) {
        this.siteRepository = siteRepository;
        this.stringGenerator = stringGenerator;
        this.urlRepository = urlRepository;
    }

    @Override
    public Site siteRegistration(Site site) {
        return siteRepository.save(generateLoginPassword(site));
    }

    private Site generateLoginPassword(Site site) {
        site.setLogin(stringGenerator.generate(10));
        site.setPassword(stringGenerator.generate(8));
        site.setRegistration(true);
        return site;
    }

    @Override
    public Url urlRegistration(Url url, String login) {
        url.setCode(stringGenerator.generate(6));
        url.setSite(siteRepository.findByLogin(login));
        url.setTotal(0);
        return urlRepository.save(url);
    }

    @Override
    public Url findUrlForRedirect(String code) {
        Url url = urlRepository.findByCode(code).orElseThrow(() -> new ItemNotFoundException("Url code not found"));
        urlRepository.totalUpdate(url.getId());
        return url;
    }

    @Override
    public List<Url> getStatistic(String login) {
        return urlRepository.findBySite_Login(login);
    }
}
