package raulgarcia.contacts.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import raulgarcia.contacts.model.Contact;

@Repository
public interface IContactRepository extends JpaRepository<Contact, Integer> {
}