package com.example.sharingapp;

import android.content.Context;

public class AddContactCommand extends Command {

    private ContactList _contactList;
    private Contact _contact;
    private Context _context;

    public AddContactCommand(ContactList contactList, Contact contact, Context context) {
        _contactList = contactList;
        _contact = contact;
        _context = context;
    }

    @Override
    public void execute() {
        _contactList.addContact(_contact);
        setIsExecuted(_contactList.saveContacts(_context));
    }
}
