package part2;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.*;

/**
 * 2 – A huge phone book containing pairs of the form {phone number, person's name} was
 * stored as a vector sorted by name in alphabetical order. Write a program that finds the
 * phone number of a given person in this list, bearing in mind that the list is very large and that
 * users need the search results to be as fast as possible.
 *
 * *** Best option is findByNameCollectionBinary ***
 *
 */
public class Exercise2 {

    private List<Contact> listContact;

    class Contact implements Comparable<Contact>{
        String name;
        String number;

        public Contact(String name, String number) {
            this.name = name;
            this.number = number;
        }

        @Override
        public String toString() {
            return name + " " + number;
        }

        @Override
        public int compareTo(Contact o) {
            return name.compareTo(o.name) ;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Contact contact = (Contact) o;

            return Objects.equals(name, contact.name);
        }

        @Override
        public int hashCode() {
            return name != null ? name.hashCode() : 0;
        }
    }

    private List<Contact> buildSortedListContact(int size) {

        listContact = new ArrayList<>(size);

        for (int i = 0; i < size; i++)
            listContact.add(new Contact("Contact" + i, "9999" + i));

        Collections.sort(listContact);
        return listContact;
    }

    private Contact findByNameStream(String name) {

        return  listContact.stream()
                .filter(it -> it.name.equals(name)).findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Name not found"));
    }

    private Contact findByNameParallelStream(String name) {

        return  listContact.parallelStream()
                .filter(it -> it.name.equals(name)).findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Name not found"));
    }

    private Contact findByNameArrayBinary(String name) {

        Contact [] array = listContact.toArray(new Contact[0]);
        int i = Arrays.binarySearch(array, new Contact(name, ""));

        return array[i];
    }

    /**
     * Best option
     */
    private Contact findByNameCollectionBinary(String name) {

        int i = Collections.binarySearch(listContact, new Contact(name, ""));
        return listContact.get(i);
    }


    public static void main(String[] args) {

        final int defaultSize = 14_099_000;

        Exercise2 e = new Exercise2();
        List<Contact> list = e.buildSortedListContact(defaultSize);
        int position = new Random().nextInt(defaultSize);
        Contact contactTofind = list.get(position);
        System.out.println("Find by " + contactTofind + ". Position: " + position);


        LocalDateTime start = LocalDateTime.now();
        Contact contact = e.findByNameStream(contactTofind.name);
        LocalDateTime end = LocalDateTime.now();
        Duration duration = Duration.between(start, end);
        System.out.println("findByName "+ contact + " " + duration.toMillis() + " milliseconds");

        LocalDateTime start2 = LocalDateTime.now();
        Contact contact2 = e.findByNameParallelStream(contactTofind.name);
        LocalDateTime end2 = LocalDateTime.now();
        Duration duration2 = Duration.between(start2, end2);
        System.out.println("findByNameParallelStream "+ contact2 + " " + duration2.toMillis() + " milliseconds");


        LocalDateTime start3 = LocalDateTime.now();
        Contact contact3 = e.findByNameArrayBinary(contactTofind.name);
        LocalDateTime end3 = LocalDateTime.now();
        Duration duration3 = Duration.between(start3, end3);
        System.out.println("findByNameArrayBinary "+ contact3 + " " + duration3.toMillis() + " milliseconds");

        LocalDateTime start4 = LocalDateTime.now();
        Contact contact4 = e.findByNameCollectionBinary(contactTofind.name);
        LocalDateTime end4 = LocalDateTime.now();
        Duration duration4 = Duration.between(start4, end4);
        System.out.println("findByNameCollectionBinary "+ contact4 + " " + duration4.toMillis() + " milliseconds");

    }
}
