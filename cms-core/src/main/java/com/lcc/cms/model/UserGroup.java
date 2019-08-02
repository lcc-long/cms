package com.lcc.cms.model;

import javax.persistence.*;

@Entity
@Table(name = "t_user_group")
public class UserGroup {
    @Id
    @GeneratedValue
    private int id;
    @ManyToOne
    @JoinColumn(name = "u_id")
    private User user;
    @ManyToOne
    @JoinColumn(name = "g_id")
    private Group group;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }
}
