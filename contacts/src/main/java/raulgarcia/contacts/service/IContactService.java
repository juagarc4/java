package raulgarcia.contacts.service;

import raulgarcia.contacts.model.Contact;

import java.util.List;
import java.util.Optional;

public interface IContactService {
    List<Contact> listContacts();

    Optional<Contact> getContactById(Integer id);

    void saveContact(Contact contact);

    void deleteContactById(Integer id);

}
