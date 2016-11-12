package org.hiease.authserver.data;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;

@Getter
@Setter
@ToString
@Entity
@NoArgsConstructor
public class Announcement extends AbstractEntity {
    private String title;
    private String content;
    private String status;
}