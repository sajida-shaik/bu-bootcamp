package module3;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;

public class ContactTest {
   
    private Contact contact; 

    @BeforeEach
  void setUp() {
    contact = new Contact("Ada Lovelace", "+1 617 555 0101");
  } 
 
  @Test
  void getName_returnsCorrectName() {
    assertEquals("Ada Lovelace", contact.getName());
  } 
 
  @Test
  void getPhone_returnsCorrectPhone() {
    assertEquals("+1 617 555 0101", contact.getPhone());
  } 
 
  @Test
  void toString_containsBothFields() {
    assertTrue(contact.toString().contains("Ada Lovelace"));
    assertTrue(contact.toString().contains("+1 617 555 0101"));
  }

  @Test
  void testDifferentContactsWithSameName() {
    Contact contact1 = new Contact("Ada Lovelace", "+1 617 555 0101");
    Contact contact2 = new Contact("Ada Lovelace", "+1 551 223 7896");
    
    assertEquals(contact1.getName(), contact2.getName());
    assertFalse(contact1.getPhone().equals(contact2.getPhone()));
  }
}
