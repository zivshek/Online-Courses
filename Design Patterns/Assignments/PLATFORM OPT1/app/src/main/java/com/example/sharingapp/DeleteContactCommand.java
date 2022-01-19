package com.example.sharingapp;

import android.content.Context;

public class DeleteContactCommand extends Command {

    private ContactList _contactList;
    private Contact _contact;
    private Context _context;

    public DeleteContactCommand(ContactList contactList, Contact contact, Context context) {
        _contactList = contactList;
        _contact = contact;
        _context = context;
    }

    @Override
    public void execute() {
        _contactList.deleteContact(_contact);
        setIsExecuted(_contactList.saveContacts(_context));
    }
}
