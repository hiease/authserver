package org.hiease.authserver.view;

import org.hiease.authserver.data.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by qihaiyan on 2016/11/1.
 */

@RestController
public class MenuController {

    @Autowired
    ResourceRepository resourceRepository;

    @Autowired
    UserRepository userRepository;

    /*
     菜单json格式：
	                [{
	                name: 'xxx',
	                type: 'heading',
	                children: [
	                    {
	                        name: 'xxx',
	                        type: 'toggle',
	                        pages: [{
	                            name: 'xxx',
	                            url: 'xxx',
	                            type: 'link'
	                        },
	                           ...]
	                    },
	                    ...]
	                  },
	                 ...
	                 ]
	             */
    @RequestMapping("/menus")
    public List<Menu> menus() {

        User user = this.userRepository.findCurrentUser();

        List<Resource> resources;

        if (!StringUtils.isEmpty(user.getIsAdmin()) && user.getIsAdmin().equals("Y")) {
            resources = this.resourceRepository.findAll();
        } else {
            List<Resource> res = new ArrayList<>();
            this.userRepository.findCurrentUser().getRoles().forEach((role) -> {
                res.addAll(role.getResources());
            });
            resources = res.stream().distinct().collect(Collectors.toList());
        }
//        if= this.resourceRepository.findByCurrentUser();
//        List<Resource> roleRes = new ArrayList<>();
//        List<Role> roles = this.userRepository.findCurrentUser().getRoles();
//        roles.forEach((role) -> {
//            roleRes.addAll(role.getResources());
//        });

//        List<Resource> rootMenus = resources.stream()
//                .filter(resource -> resource.getParentId() == null).collect(Collectors.toList());

        Menu menu = new Menu();
//        if (!StringUtils.isEmpty(user.getIsAdmin()) && user.getIsAdmin().equals("Y")) {
//            menu.setRoleRes(this.resourceRepository.findAll());
//        } else {
//            menu.setRoleRes(roleRes);
//        }



        menu.buildMenu(menu, resources);
        return menu.getChildren();
    }
}
