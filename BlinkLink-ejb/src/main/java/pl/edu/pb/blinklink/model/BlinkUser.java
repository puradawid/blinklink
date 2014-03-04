package pl.edu.pb.blinklink.model;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author dawid
 */
@Entity
public class BlinkUser implements Serializable {
    
    protected String name;
    protected String surname;
    
    protected String password;
    
    @Id
    protected String email;
    
    @Temporal(TemporalType.TIMESTAMP)
    protected Date lastLogin;
    
    @ManyToMany(fetch = FetchType.EAGER)
    protected Collection<BlinkGroup> groups;
    
    //constructors
    public BlinkUser() {} //default constructor
    
    public BlinkUser(String email, String password)
    {
        setEmail(email);
        setPassword(password);
    }
    
    //getters and setters 
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(Date lastLogin) {
        this.lastLogin = lastLogin;
    }

    public Collection<BlinkGroup> getGroups() {
        return groups;
    }

    public void setGroups(Collection<BlinkGroup> groups) {
        this.groups = groups;
    }
    
    public void setPassword(String password)
    {
        this.password = password;
    }
    
    public String getPassword()
    {
        return this.password;
    }
}
