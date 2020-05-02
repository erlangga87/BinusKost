package id.ac.binus.programming.kost.user.entity;

import javax.persistence.*;

@Entity
@Table(name = "user_role_id")
public class UserRole {

    @Id
    @Column(name = "roleid")
    private String roleid;

    @Column(name = "rolename")
    private String rolename;

    public String getRoleid() {
        return roleid;
    }

    public void setRoleid(String roleid) {
        this.roleid = roleid;
    }

    public String getRolename() {
        return rolename;
    }

    public void setRolename(String rolename) {
        this.rolename = rolename;
    }
}
