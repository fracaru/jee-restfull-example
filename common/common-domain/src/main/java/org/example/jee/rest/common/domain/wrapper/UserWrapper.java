package org.example.jee.rest.common.domain.wrapper;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.example.jee.rest.common.domain.UserExample;


@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class UserWrapper {

	@XmlElement
	private List<UserExample> users;

	public List<UserExample> getUsers() {
		return users;
	}

	public void setUsers(List<UserExample> users) {
		this.users = users;
	}

}
