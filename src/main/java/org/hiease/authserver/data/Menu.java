package org.hiease.authserver.data;

import lombok.*;
import sun.jvm.hotspot.debugger.Page;

import javax.persistence.Entity;
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

@Getter
@Setter
@ToString
@NoArgsConstructor
public class Menu {

    private String name;

    private final MenuType type = MenuType.heading;

    private List<ToggleMenu> children = new ArrayList<ToggleMenu>();

    public void init() {
        List<PageMenu> pages = new ArrayList<PageMenu>();
        PageMenu pageMenu1 = new PageMenu("page1","http://1");
        PageMenu pageMenu2 = new PageMenu("page2","http://2");
        pages.add(pageMenu1);
        pages.add(pageMenu2);
        ToggleMenu toggleMenu = new ToggleMenu();
        toggleMenu.setName("toggle1");
        toggleMenu.setPages(pages);
        this.name = "main";
        this.getChildren().add(toggleMenu);
    }

}

@Getter
@Setter
@ToString
@NoArgsConstructor
class ToggleMenu {
    private String name;

    private final MenuType type = MenuType.toggle;

    private List<PageMenu> pages = new ArrayList<PageMenu>();

}

@Getter
@Setter
@ToString
class PageMenu {
    private String name;

    private final MenuType type = MenuType.link;

    private String url;

    PageMenu(String name, String url) {
        this.setName(name);
        this.setUrl(url);
    }

}