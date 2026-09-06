package Module3;

import java.util.ArrayList;
import java.util.HashMap;

public class ContactManager {

    public static void main(String[] args) {

        HashMap<String, Contact> contacts = new HashMap<>();

        // Adding Contacts
        contacts.put("Sajida", new Contact("Sajida", "+1 5512347890"));
        contacts.put("Aryan", new Contact("Aryan", "+1 6472781245"));
        contacts.put("Vihu", new Contact("Vihu", "+1 6478947536"));
        contacts.put("Xavier", new Contact("Xavier", "+1 5027843698"));
        contacts.put("Mary", new Contact("Mary", "+1 5514781023"));

        
        retrieveContactAntPrint(contacts.get("Sajida"));
        retrieveContactAntPrint(contacts.get("Nalla"));
        
        ArrayList<Contact> sorted = getSortedList(contacts);

        System.out.println("\n\n=== All Contacts ===");
        printSortedList(sorted);

        removeContact("Aly", contacts);
        removeContact("Xavier", contacts);
        sorted = getSortedList(contacts);

        System.out.println("\n\n=== All Contacts post the remove contact ===");
        printSortedList(sorted);
    }

    static void printSortedList(ArrayList<Contact> sortedList) {

        for (Contact contact : sortedList) {
            System.out.println(contact.getName() + " | " + contact.getPhone());
        }
    }

    static ArrayList<Contact> getSortedList(HashMap<String, Contact> contactMap) {

        if (contactMap == null) {
            System.out.println("Contact Map is emtpy");
            return null;
        }
        ArrayList<Contact> sorted = new ArrayList<>(contactMap.values());
        sorted.sort((a,b) -> a.getName().compareTo(b.getName()));

        return sorted;
    }

    static void retrieveContactAntPrint(Contact contact) {

        if (contact == null) {
            System.out.println("\nContact not found...");
        } else {
            System.out.println("\nContact is found => " + contact.getName() + " | " + contact.getPhone());
        }
    }

    static void removeContact(String contactToRemove, HashMap<String, Contact> contactMap) {

        if (contactMap != null && contactMap.get(contactToRemove) != null) {
            contactMap.remove(contactToRemove);
            System.out.println("\n" + contactToRemove + " is removed from the HashMap");
        } else {
            System.out.println("\n" + contactToRemove + " is missing");
        }
    }
}
