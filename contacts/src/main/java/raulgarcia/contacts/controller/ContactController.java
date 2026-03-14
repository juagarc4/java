package raulgarcia.contacts.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import raulgarcia.contacts.model.Contact;
import raulgarcia.contacts.service.IContactService;

@Controller
@AllArgsConstructor
@RequestMapping("/contacts")
public class ContactController {
    private final IContactService contactService;

    @GetMapping({"", "/"})
    public String index(ModelMap model) {
        model.addAttribute("title", "Contacts list");
        model.addAttribute("view", "contact/index");
        model.addAttribute("contacts", this.contactService.listContacts());
        return "common/layout";
    }

    @GetMapping("/add")
    public String add(ModelMap model) {
        model.addAttribute("title", "Add contact");
        model.addAttribute("view", "contact/add");
        model.addAttribute("contact", new Contact());
        return "common/layout";
    }

    @GetMapping("/edit")
    public String edit(@RequestParam("contact") Integer id, ModelMap model) {
        Contact contact = contactService.getContactById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "ERROR EDITING: Contact not found."));
        model.addAttribute("title", "Edit contact");
        model.addAttribute("view", "contact/edit");
        model.addAttribute("contact", contact);
        return "common/layout";

    }

    @PostMapping("/save")
    public String save(@Valid @ModelAttribute("contact") Contact contact, BindingResult result) {
        if (result.hasErrors()) {
            return (contact.getId() != null) ? "contacts/edit" : "contacts/add";
        }
        contactService.saveContact(contact);
        return "redirect:/contacts";
    }

    @PostMapping("/delete")
    public String delete(@RequestParam("contact") Integer id) {
        Contact contact = contactService.getContactById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Contact not found"));
        contactService.deleteContactById(id);
        return "redirect:/contacts";
    }
}
