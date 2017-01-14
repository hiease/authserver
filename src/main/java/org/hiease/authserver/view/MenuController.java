package org.hiease.authserver.view;

import org.hiease.authserver.data.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Comparator.comparing;

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

        Menu menu = new Menu();

        menu.buildMenu(menu, resources);
        return menu.getChildren();
    }
}
