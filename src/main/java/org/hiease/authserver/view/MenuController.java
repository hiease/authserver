package org.hiease.authserver.view;

import org.hiease.authserver.data.Menu;
import org.hiease.authserver.data.Resource;
import org.hiease.authserver.data.ResourceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
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

        List<Menu> menus = new ArrayList<Menu>();
        for (Resource rootMenu : rootMenus) {
            Menu menu = new Menu();
            menu.setName(rootMenu.getName());
            List<Resource> toggleMenus = resources.stream()
                    .filter(resource -> resource.getParentId() == rootMenu.getId()).collect(Collectors.toList());
            menu.addToggleMenu(toggleMenus);
            menus.add(menu);
        }
//        buildMenu(menus);
//        Menu menu = new Menu();
//        menu.init();
//        menus.add(menu);
        return menus;
    }

//    private void buildMenu(TreeNode parentNode,long parentId) {
//		// 构造当前节点的子节点
//		List<Resource> childMenuInfoList = (List<AppAuthRoleRes>) CollectionUtils.select(menuInfos, new TreePredicate(parentId));
//
//		if (childMenuInfoList.size() > 0) {
//			for (AppAuthRoleRes menuInfo : childMenuInfoList) {
//				TreeNode treeNode = new DefaultTreeNode(menuInfo.getMenuInfo(),parentNode);
//				menuMap.put(menuInfo.getMenuInfo(),treeNode);
//				buildTreeNode(treeNode, menuInfo.getMenuInfo().getId());
//			}
//		}
//	}

    /*
    private void buildMenuModel(TreeNode parentNode,DefaultSubMenu parentMenu) {
		List<TreeNode> subNodes = parentNode.getChildren();

		if (subNodes.size() > 0) {
			for (TreeNode subNode : subNodes) {
				if(subNode.isLeaf()) {
	    			AppMenuInfo menuInfo = (AppMenuInfo)subNode.getData();
	    			 DefaultMenuItem item = new DefaultMenuItem(menuInfo.getMenuName());
	    			 item.setIcon(menuInfo.getMenuIcon());
	    			 item.setUpdate(":centerpanel");
	    			 item.setCommand("#{menuView.setMainContent('"+menuInfo.getActionInfo().getActionUrl()+"')}");
	 				 if(subNode.getParent()==rootTreeNode) {
						 model.addElement(item);
					 }
	 				 else {
		    			 parentMenu.addElement(item);
	 				 }
				}
				else {
					DefaultSubMenu submenu = new DefaultSubMenu(((AppMenuInfo)subNode.getData()).getMenuName());
					if(subNode.getParent()==rootTreeNode) {
						model.addElement(submenu);
					}
					else {
						parentMenu.addElement(submenu);
					}
					buildMenuModel(subNode,submenu);
				}
			}
		}
	}
     */
}
