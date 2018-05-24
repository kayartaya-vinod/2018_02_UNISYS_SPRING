package com.unisys.spring.mongodb.demo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.unisys.spring.mongodb.demo.model.Contact;

public interface ContactsRepository extends MongoRepository<Contact, String> {
}
