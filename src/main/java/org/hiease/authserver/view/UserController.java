package org.hiease.authserver.view;

import org.hiease.authserver.data.Organization;
import org.hiease.authserver.data.User;
import org.hiease.authserver.data.UserRepository;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.security.oauth2.provider.token.ConsumerTokenServices;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by qihaiyan on 2016/11/13.
 */

@RestController
public class UserController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    private AuthorizationServerTokenServices authorizationServerTokenServices;

    @Autowired
    private ConsumerTokenServices consumerTokenServices;

    @RequestMapping("/user")
    public Principal user(Principal user) {
        return user;
    }

    @RequestMapping("/logout")
    public void logout(HttpServletRequest request, HttpServletResponse response) throws IOException {

        request.getSession().invalidate();

        String redirectUrl = request.getParameter("redirect");

        response.sendRedirect(redirectUrl);

        return;
    }

    private List<String> getDataOrg(List<Organization> orgs) {
        List<String> dataorgs = new ArrayList<>();
        for (Organization org : orgs) {
            dataorgs.add(org.getId().toString());
            if (org.getChildren().size() > 0) {
                dataorgs.addAll(getDataOrg(org.getChildren()));
            }
        }
//        orgs.forEach((org) -> {
//            dataorgs.add(org.getId().toString());
//            if (org.getChildren().size() > 0) {
//                getDataOrg(org.getChildren());
//            }
//        });
        return dataorgs;
    }

    @RequestMapping("/api/findCurrentUserDataOrg")
    public ResponseEntity findCurrentUserOrg() {
        User user = this.userRepository.findCurrentUser();
        Organization userOrg = user.getDepartment().getOrganization();
        List<Organization> childOrgs = userOrg.getChildren().stream().filter((u) -> u.getId() != userOrg.getId()).collect(Collectors.toList());
        List<String> dataorgs = this.getDataOrg(childOrgs);
        dataorgs.add(userOrg.getId().toString());
        return ResponseEntity.ok().body(dataorgs.toString());
    }

    @RequestMapping("/api/updateUserPasswd")
    public ResponseEntity<?> updateUserPasswd(@RequestBody String passwd) {
        JSONObject jsonObject = new JSONObject(passwd);
        this.userRepository.updateUserPasswd(User.PASSWORD_ENCODER.encode(jsonObject.getString("userPass")));
        return ResponseEntity.ok().body("{ \"result\": { \"success\": true, \"error\": null } }");
    }

    @RequestMapping("/api/resetUserPasswd")
    public ResponseEntity<?> resetUserPasswd(@RequestBody String user) {
        JSONObject jsonObject = new JSONObject(user);
        String username = jsonObject.getString("username");
        this.userRepository.updatePasswdByUsername(username, User.PASSWORD_ENCODER.encode(username));
        return ResponseEntity.ok().body("{ \"result\": { \"success\": true, \"error\": null } }");
    }
}
