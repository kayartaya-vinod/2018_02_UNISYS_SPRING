package com.unisys.spring.mongodb.demo.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.Data;
import lombok.NoArgsConstructor;

@Document
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@Data
@NoArgsConstructor
public class Contact {
	@Id
	private String id;
	@XmlElement(name = "first-name")
	@Field("first_name")
	private String firstName;
	@XmlElement(name = "last-name")
	@Field("last_name")
	private String lastName;
	private String gender;
	private String email;
	private String phone;
	private String address;
	private String city;
	private String state;
	private String country;
}
