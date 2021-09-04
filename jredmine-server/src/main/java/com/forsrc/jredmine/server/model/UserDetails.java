package com.forsrc.jredmine.server.model;

import java.io.Serializable;
import java.security.Principal;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.persistence.Version;

import org.springframework.security.core.GrantedAuthority;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "t_user", indexes = {
        @Index(name = "index_user_username", columnList = "username")}, uniqueConstraints = {
        @UniqueConstraint(columnNames = {"username"})})
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler", "fieldHandler"})
public class UserDetails extends BaseModel<String> implements org.springframework.security.core.userdetails.UserDetails, Principal, Cacheable<String>, Serializable {

    private static final long serialVersionUID = 7053075402341362549L;

    @Id
    // @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "username", unique = true, length = 200, nullable = false)
    private String username;

    @Column(name = "password", length = 200, nullable = false)
    private String password;

    @Column(name = "enabled", length = 1, nullable = false, columnDefinition = "INT DEFAULT 1")
    private Integer enabled;

    @Column(name = "created_at", insertable = false, updatable = false, nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    private Date createdAt;

    @Column(name = "updated_at", insertable = false, updatable = true, nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    private Date updatedAt;

    @Column(name = "version", insertable = false, updatable = true, nullable = false, columnDefinition = "INT DEFAULT 0")
    @Version
    private int version;
    
    @Column(name = "jwt_token", length = 1000, nullable = true)
    private String jwtToken;

    @OneToMany(mappedBy = "username")
    private Set<Authority> authorities = new HashSet<>();

    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return isEnabled();
    }

    @Override
    public boolean isAccountNonLocked() {
        return isEnabled();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return isEnabled();
    }

    @Override
    public boolean isEnabled() {
        return enabled != null && enabled.equals(1);
    }


    public String getPassword() {
        return password;
    }


    public Integer getEnabled() {
        return enabled;
    }


    public Date getCreatedAt() {
        return createdAt;
    }


    public Date getUpdatedAt() {
        return updatedAt;
    }


    public int getVersion() {
        return version;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }
    
    public String getJwtToken() {
		return jwtToken;
	}

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", enabled=" + enabled +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", version=" + version +
                ", jwtToken=" + jwtToken +
                ", authorities=" + authorities +
                '}';
    }

    @Override
    public String getName() {
        return username;
    }

    @Override
    public String getPk() {
        return getUsername();
    }
    
    @Override
    public void setPk(String pk) {

    }
}