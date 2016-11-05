package org.hiease.authserver.view;

import org.hiease.authserver.data.Menu;
import org.hiease.authserver.data.Resource;
import org.hiease.authserver.data.ResourceRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
        List<Resource> resources = resourceRepository.findByCurrentUser();
        List<Resource> rootMenus = resources.stream()
                .filter(resource -> resource.getParentId() == null).collect(Collectors.toList());

        Menu menu = new Menu();
        menu.buildMenu(menu, rootMenus);
        return menu.getChildren();
    }
}
