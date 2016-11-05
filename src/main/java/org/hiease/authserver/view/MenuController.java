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

        Menu menu = new Menu();
        menu.buildMenu(menu, rootMenus);
        return menu.getChildren();
    }

//    private void buildMenu(Resource parent, int level) {
//		// 构造当前节点的子节点
//		List<Resource> children = parent.getChildren();
//
//		if (children.size() > 0) {
//			for (Resource child : children) {
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
