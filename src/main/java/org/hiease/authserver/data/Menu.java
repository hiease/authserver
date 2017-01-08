package org.hiease.authserver.data;

import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static java.util.Comparator.comparing;

/**
 * Created by qihaiyan on 2016/11/1.
 */

enum MenuType {
    heading,
    toggle,
    link
}

@NoArgsConstructor
public class Menu {

    private Long id;

    private String name;

    private String icon;

    private MenuType type;

    protected List<Resource> roleResources;

    private List<Menu> children = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public MenuType getType() {
        return type;
    }

    public void setType(MenuType type) {
        this.type = type;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public List<Menu> getChildren() {
        return children;
    }

    public void buildMenu(Menu parent, List<Resource> resources) {

        resources.stream()
                .filter(res -> res.getParentId() == null).forEach(resource -> {
            Menu headMenu = new HeadMenu();
            headMenu.setId(resource.getId());
            headMenu.setName(resource.getName());
            headMenu.buildMenu(
                    headMenu,
                    resources.stream()
                            .filter(res -> Objects.equals(res.getParentId(), resource.getId())).sorted(comparing(Resource::getShowOrder)).collect(Collectors.toList())
            );
            this.addChildren(headMenu);
        });
    }

    public void addChildren(Menu child) {
        children.add(child);
    }
}

@Getter
@Setter
@ToString
@NoArgsConstructor
class HeadMenu extends Menu {

    private final MenuType type = MenuType.heading;

    private List<Menu> children = new ArrayList<>();

    @Override
    public void buildMenu(Menu parent, List<Resource> resources) {
        resources.forEach(resource -> {
            if (resource.getChildren().size() > 0) {
                Menu toggleMenu = new ToggleMenu();
                toggleMenu.setId(resource.getId());
                toggleMenu.setName(resource.getName());
                toggleMenu.buildMenu(
                        toggleMenu,
                        resources.stream().filter(res -> Objects.equals(res.getParentId(), resource.getId())).collect(Collectors.toList()));
                this.addChildren(toggleMenu);
            } else {
                Menu pageMenu = new PageMenu(resource.getId(), resource.getName(), resource.getUrl(), resource.getIcon());
                this.addChildren(pageMenu);
            }
        });
    }

    public void addChildren(Menu child) {
        children.add(child);
    }
}

@Getter
@Setter
@ToString
@NoArgsConstructor
class ToggleMenu extends Menu {
    private final MenuType type = MenuType.toggle;

    private List<PageMenu> pages = new ArrayList<PageMenu>();

    @Override
    public void buildMenu(Menu parent, List<Resource> resources) {
        for (Resource resource : resources) {
            if (resource == null) continue;
            PageMenu pageMenu = new PageMenu(resource.getId(), resource.getName(), resource.getUrl(), resource.getIcon());
            this.addPages(pageMenu);
        }
    }

    public void addPages(PageMenu child) {
        this.pages.add(child);
    }
}

@Getter
@Setter
@ToString
class PageMenu extends Menu {

    private final MenuType type = MenuType.link;
    private String url;

    PageMenu(Long id, String name, String url, String icon) {
        this.setId(id);
        this.setName(name);
        this.setUrl(url);
        this.setIcon(icon);
    }
}