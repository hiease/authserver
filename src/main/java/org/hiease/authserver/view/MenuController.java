package org.hiease.authserver.view;

import org.hiease.authserver.data.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
        List<Resource> resources = this.resourceRepository.findByCurrentUser();
//        List<Resource> rootMenus = resources.stream()
//                .filter(resource -> resource.getParentId() == null).collect(Collectors.toList());

        Menu menu = new Menu();
        if(user.getIsAdmin().equals("Y")){
            menu.setRoleRes(this.resourceRepository.findAll());
        }
        else {
            menu.setRoleRes(this.userRepository.findCurrentUser().getRoles().get(0).getResources());
        }

        menu.buildMenu(menu, resources);
        return menu.getChildren();
    }
}
