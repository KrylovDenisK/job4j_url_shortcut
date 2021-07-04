package ru.job4j.shortcut.controller;

import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.job4j.shortcut.model.entity.Site;
import ru.job4j.shortcut.model.entity.Url;
import ru.job4j.shortcut.model.views.ViewUrl;
import ru.job4j.shortcut.service.ApplicationService;

import java.net.URI;
import java.security.Principal;
import java.util.List;


@RestController
public class ApplicationController {
    private ApplicationService applicationService;

    public ApplicationController(ApplicationService applicationService) {
        this.applicationService = applicationService;
    }

    @PostMapping("/registration")
    public ResponseEntity<Site> registartion(@RequestBody Site site) {
        return new ResponseEntity<>(applicationService.siteRegistration(site),
                HttpStatus.CREATED);
    }

    @PostMapping("/convert")
    @JsonView(ViewUrl.Convert.class)
    public ResponseEntity<Url> convert(@RequestBody Url url, Principal principal) {
        return new ResponseEntity<>(applicationService.urlRegistration(url, principal.getName()),
                HttpStatus.CREATED);
    }

    @GetMapping("/redirect/{code}")
    public ResponseEntity<Void> redirect(@PathVariable("code") String code) throws Exception {
        Url url = applicationService.findUrlForRedirect(code);
        return ResponseEntity.status(HttpStatus.FOUND)
                .location(new URI(url.getName()))
                .build();
    }

    @GetMapping("/statistic")
    @JsonView(ViewUrl.Statistic.class)
    public ResponseEntity<List<Url>> statistic(Principal principal) {
         return new ResponseEntity<>(applicationService.getStatistic(principal.getName()),
                        HttpStatus.OK);
    }

}
