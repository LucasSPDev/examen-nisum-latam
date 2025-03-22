package com.nisum.examen.model;

import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.CascadeType;
import javax.persistence.Column;

import java.util.Objects;

@Entity
@Table(name = "USER")
public class User {

	@Id
	@Column(name = "uuid")
	private String uuid;

	@Column(name = "created")
	private Date created;

	@Column(name = "modified")
	private Date modified;

	@Column(name = "last_login")
	private Date lastLogin;

	@Column(name = "active")
	private boolean active;

	@Column(name = "token")
	private String token;

	@Column(name = "name")
	private String name;

	@Column(name = "email")
	private String email;

	@Column(name = "password")
	private String password;

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Phone> phones;

	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public Date getCreated() {
		return created;
	}
	public void setCreated(Date created) {
		this.created = created;
	}
	public Date getModified() {
		return modified;
	}
	public void setModified(Date modified) {
		this.modified = modified;
	}
	public Date getLastLogin() {
		return lastLogin;
	}
	public void setLastLogin(Date lastLogin) {
		this.lastLogin = lastLogin;
	}

	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	public List<Phone> getPhones() {
		return phones;
	}
	public void setPhones(List<Phone> phones) {
		this.phones = phones;
	}

	public boolean getActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		User user = (User) o;
		return active == user.active &&
				Objects.equals(uuid, user.uuid) &&
				Objects.equals(created, user.created) &&
				Objects.equals(modified, user.modified) &&
				Objects.equals(lastLogin, user.lastLogin) &&
				Objects.equals(token, user.token) &&
				Objects.equals(name, user.name) &&
				Objects.equals(email, user.email) &&
				Objects.equals(password, user.password) &&
				Objects.equals(phones, user.phones);
	}

	@Override
	public int hashCode() {
		return Objects.hash(uuid, created, modified, lastLogin, active, token, name, email, password, phones);
	}

	@Override
	public String toString() {
		return "User [uuid=" + uuid + ", created=" + created + ", modified=" + modified + ", lastLogin=" + lastLogin
				+ ", active=" + active + ", token=" + token + ", name=" + name + ", email=" + email + ", password="
				+ password + ", phones=" + phones + "]";
	}


}
