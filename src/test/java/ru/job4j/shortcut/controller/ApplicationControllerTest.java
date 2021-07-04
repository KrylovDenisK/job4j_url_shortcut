package ru.job4j.shortcut.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.core.Is;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import ru.job4j.shortcut.Job4jUrlShortcutApplication;
import ru.job4j.shortcut.model.entity.Site;
import ru.job4j.shortcut.service.ApplicationService;
import ru.job4j.shortcut.service.generator.GenaratingValue;


import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest(classes = Job4jUrlShortcutApplication.class)
@AutoConfigureMockMvc
class ApplicationControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private GenaratingValue genaratingValue;

    @MockBean
    private ApplicationService applicationService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void whenDoRegistrationWhenReturnStatus201() throws Exception {
        String login = genaratingValue.generate(6);
        String password = genaratingValue.generate(6);
        Site site = Site.of("test", login, password, true);
        Mockito.when(applicationService.siteRegistration(any())).thenReturn(site);
        mockMvc.perform(post("/registration")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(site)))
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.registration", Is.is(true)))
                .andExpect(jsonPath("$.login", Is.is(login)))
                .andExpect(jsonPath("$.password", Is.is(password)));
    }
}