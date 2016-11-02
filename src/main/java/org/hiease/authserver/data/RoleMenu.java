//package org.hiease.authserver.data;
//
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.data.rest.core.config.Projection;
//
///**
// * Created by qihaiyan on 2016/11/2.
// */
//
//@Projection(name = "excerpt", types = RoleResource.class)
//public interface RoleMenu {
//
//    @Value("#{target.resource.getName()}")
//    String getName();
//    @Value("#{target.resource.getUrl()}")
//    String getUrl();
//}