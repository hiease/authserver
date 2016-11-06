package org.hiease.authserver.data;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

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
    private String name;

    private MenuType type;

    private List<Menu> children = new ArrayList<>();

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

    public List<Menu> getChildren() {
        return children;
    }

    public void buildMenu(Menu parent, List<Resource> resources) {
        for (Resource resource : resources) {
            Menu headMenu = new HeadMenu();
            headMenu.setName(resource.getName());
            headMenu.buildMenu(headMenu, resource.getChildren());
            this.addChildren(headMenu);
        }
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
        for (Resource resource : resources) {
            if (resource == null) continue;
            if (resource.getChildren().size() > 0) {
                Menu toggleMenu = new ToggleMenu();
                toggleMenu.setName(resource.getName());
                toggleMenu.buildMenu(toggleMenu, resource.getChildren());
                this.addChildren(toggleMenu);
            } else {
                Menu pageMenu = new PageMenu(resource.getName(), resource.getUrl());
                this.addChildren(pageMenu);
            }
        }
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
    private List<PageMenu> pages = new ArrayList<>();

    @Override
    public void buildMenu(Menu parent, List<Resource> resources) {
        for (Resource resource : resources) {
            if (resource == null) continue;
            Menu pageMenu = new PageMenu(resource.getName(), resource.getUrl());
            this.addChildren(pageMenu);
        }
    }

    public void addChildren(PageMenu child) {
        pages.add(child);
    }
}

@Getter
@Setter
@ToString
class PageMenu extends Menu {
    private final MenuType type = MenuType.link;
    private String url;

    PageMenu(String name, String url) {
        this.setName(name);
        this.setUrl(url);
    }
}