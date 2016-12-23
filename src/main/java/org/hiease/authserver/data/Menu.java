package org.hiease.authserver.data;

import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;

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

//    @Autowired
//    UserRepository userRepository;

    private Long id;

    private String name;

    private MenuType type;

    protected List<Resource> roleResources;

    private List<Menu> children = new ArrayList<>();

    public void setRoleRes(List<Resource> resources){
        this.roleResources = resources;
    }

//    public List<Resource> getRoleRes(){
//        return this.roleResources;
//    }

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

    public List<Menu> getChildren() {
        return children;
    }

    public void buildMenu(Menu parent, List<Resource> resources) {

//        this.roleResources = this.userRepository.findCurrentUser().getRoles().get(0).getResources();

        for (Resource resource : resources) {
            Menu headMenu = new HeadMenu();
            headMenu.setRoleRes(this.roleResources);
            headMenu.setId(resource.getId());
            headMenu.setName(resource.getName());
            headMenu.buildMenu(headMenu, resource.getChildren());
            this.addChildren(headMenu);
        }
    }

    public void addChildren(Menu child) {
        children.add(child);
    }

    public boolean isRoleMenu(Resource resource, List<Resource> roleResources){
        for (Resource roleRes : roleResources) {
            if (roleRes.getId().equals(resource.getId())){
                return true;
            }
        }
        return false;
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
            if (!isRoleMenu(resource, this.roleResources)) continue;
            if (resource.getChildren().size() > 0) {
                Menu toggleMenu = new ToggleMenu();
                toggleMenu.setRoleRes(this.roleResources);
                toggleMenu.setId(resource.getId());
                toggleMenu.setName(resource.getName());
                toggleMenu.buildMenu(toggleMenu, resource.getChildren());
                this.addChildren(toggleMenu);
            } else {
                Menu pageMenu = new PageMenu(resource.getId(), resource.getName(), resource.getUrl());
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

    private List<PageMenu> pages = new ArrayList<PageMenu>();

    @Override
    public void buildMenu(Menu parent, List<Resource> resources) {
        for (Resource resource : resources) {
            if (resource == null) continue;
            if (!isRoleMenu(resource, this.roleResources)) continue;
            PageMenu pageMenu = new PageMenu(resource.getId(), resource.getName(), resource.getUrl());
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

    PageMenu(Long id, String name, String url) {
        this.setId(id);
        this.setName(name);
        this.setUrl(url);
    }
}