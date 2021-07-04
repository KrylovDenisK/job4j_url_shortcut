package ru.job4j.shortcut.model.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import ru.job4j.shortcut.model.views.ViewUrl;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "urls")
public class Url {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "name")
    @JsonView({ViewUrl.Statistic.class, ViewUrl.Statistic.class})
    private String name;
    @Column(name = "code")
    private String code;
    @ManyToOne
    @JoinColumn(name = "site_id")
    private Site site;
    @Column(name = "total")
    @JsonView({ViewUrl.Statistic.class, ViewUrl.Statistic.class})
    private Integer total;

    @JsonCreator
    public static Url of(@JsonProperty("url") String name) {
        Url url = new Url();
        url.name = name;
        return url;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @JsonProperty("url")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Site getSite() {
        return site;
    }

    public void setSite(Site site) {
        this.site = site;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Url url = (Url) o;
        return Objects.equals(id, url.id)
                && Objects.equals(name, url.name)
                && Objects.equals(code, url.code)
                && Objects.equals(site, url.site);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, code, site);
    }
}
