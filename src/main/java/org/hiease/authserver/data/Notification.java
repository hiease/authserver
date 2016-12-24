package org.hiease.authserver.data;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.Entity;

/**
 * Created by qihaiyan on 2016/12/24.
 */


@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@ToString
public class Notification extends AbstractEntity {

    private String username;

    private String content;

    private String type;

    private String status;

}
