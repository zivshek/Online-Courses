package com.example.sharingapp;

import android.content.Context;

public class EditContactCommand extends Command {

    private ContactList _contactList;
    private Contact _oldContact;
    private Contact _newContact;
    private Context _context;

    public EditContactCommand(ContactList contactList, Contact oldContact, Contact newContact, Context context) {
        _contactList = contactList;
        _oldContact = oldContact;
        _newContact = newContact;
        _context = context;
    }

    @Override
    public void execute() {
        _contactList.deleteContact(_oldContact);
        _contactList.addContact(_newContact);
        setIsExecuted(_contactList.saveContacts(_context));
    }
}
