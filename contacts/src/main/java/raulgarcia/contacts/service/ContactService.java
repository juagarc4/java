package raulgarcia.contacts.service;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import raulgarcia.contacts.model.Contact;
import raulgarcia.contacts.repository.IContactRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ContactService implements IContactService {

    private final IContactRepository contactRepository;
    private static final Logger logger = LoggerFactory.getLogger(ContactService.class);

    @Override
    public List<Contact> listContacts() {
        return contactRepository.findAll();
    }

    @Override
    public Optional<Contact> getContactById(Integer id) {

        return contactRepository.findById(id);
    }

    @Override
    public void saveContact(Contact contact) {
        try {
            Contact contactSaved = contactRepository.save(contact);
            logger.info("Contact with ID {} saved successfully", contactSaved.getId());
        } catch (Exception e) {
            logger.error("Database error while saving contact: {}", e.getMessage());
            throw new RuntimeException("Error saving user in the database", e);
        }
    }

    @Override
    public void deleteContactById(Integer id) {
        contactRepository.deleteById(id);
    }

}
