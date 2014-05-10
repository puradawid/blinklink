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
    
    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "registered")
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

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 53 * hash + (this.name != null ? this.name.hashCode() : 0);
        hash = 53 * hash + (this.surname != null ? this.surname.hashCode() : 0);
        hash = 53 * hash + (this.password != null ? this.password.hashCode() : 0);
        hash = 53 * hash + (this.email != null ? this.email.hashCode() : 0);
        hash = 53 * hash + (this.lastLogin != null ? this.lastLogin.hashCode() : 0);
        hash = 53 * hash + (this.groups != null ? this.groups.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final BlinkUser other = (BlinkUser) obj;
        if ((this.name == null) ? (other.name != null) : !this.name.equals(other.name)) {
            return false;
        }
        if ((this.surname == null) ? (other.surname != null) : !this.surname.equals(other.surname)) {
            return false;
        }
        if ((this.password == null) ? (other.password != null) : !this.password.equals(other.password)) {
            return false;
        }
        if ((this.email == null) ? (other.email != null) : !this.email.equals(other.email)) {
            return false;
        }
        if (this.lastLogin != other.lastLogin && (this.lastLogin == null || !this.lastLogin.equals(other.lastLogin))) {
            return false;
        }
        if (this.groups != other.groups && (this.groups == null || !this.groups.equals(other.groups))) {
            return false;
        }
        return true;
    }
    
    
}
