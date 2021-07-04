package ru.job4j.shortcut.secur;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.job4j.shortcut.model.entity.Site;
import ru.job4j.shortcut.repo.SiteRepository;

import java.util.Collections;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private SiteRepository siteRepository;

    public UserDetailsServiceImpl(SiteRepository siteRepository) {
        this.siteRepository = siteRepository;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        Site site = siteRepository.findByLogin(login);
        if (site == null) {
            throw new UsernameNotFoundException(login);
        }

        return new org.springframework.security.core.userdetails.User(site.getLogin(), site.getPassword(),
                Collections.emptyList());
    }



}
