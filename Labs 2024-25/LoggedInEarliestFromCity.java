//At the input of this task you will be given rows with the following format:
//First_name Last_name budget ip_address time city price
//Example
//Jovan Todorov 1000 10.73.112.200 15:30 Bitola 760
//Which means that the person with First name and Last name Jovan Todorov, has a budget of 1000 denars, has an IP address with network 10.73.112 and host number 200, and joined at 15:30 to buy a 
//ticked to Bitola which costs 760 denars.
//You will be given N such rows, followed by an empty row and M more rows of the same format, which we will use for testing.
//From the test row you need to extract the city and then answer the following question with this city:
//Of the N people at the input, from the ones who buy a ticket to the same city
//how many of them joined later than 11:59; and
//of these, which one joined the earliest?
//(if there are more with the same minimum time then which one is the first one in the entry?) (there will always be at least one)
//You will need to do this for all M rows for testing!

import java.time.LocalTime;
import java.util.*;

class MapEntry<K extends Comparable<K>,E> implements Comparable<K> {
  
    K key;
    E value;

    public MapEntry (K key, E val) {
        this.key = key;
        this.value = val;
    }

    public int compareTo (K that) {

        @SuppressWarnings("unchecked")
        MapEntry<K,E> other = (MapEntry<K,E>) that;
        return this.key.compareTo(other.key);
    }

    public String toString () {
        return "<" + key + "," + value + ">";
    }
}


class SLLNode<E> {
    protected E element;
    protected SLLNode<E> succ;

    public SLLNode(E elem, SLLNode<E> succ) {
        this.element = elem;
        this.succ = succ;
    }

    @Override
    public String toString() {
        return element.toString();
    }
}


class OBHT<K extends Comparable<K>,E> {

    // An object of class OBHT is an open-bucket hash table, containing entries
    // of class MapEntry.
    private MapEntry<K,E>[] buckets;

    // buckets[b] is null if bucket b has never been occupied.
    // buckets[b] is former if bucket b is formerly-occupied
    // by an entry that has since been deleted (and not yet replaced).

    static final int NONE = -1; // ... distinct from any bucket index.

    @SuppressWarnings({ "rawtypes", "unchecked" })
    private static final MapEntry former = new MapEntry(null, null);
    // This guarantees that, for any genuine entry e,
    // e.key.equals(former.key) returns false.

    private int occupancy = 0;
    // ... number of occupied or formerly-occupied buckets in this OBHT.

    @SuppressWarnings("unchecked")
    public OBHT (int m) {
        // Construct an empty OBHT with m buckets.
        buckets = (MapEntry<K,E>[]) new MapEntry[m];
    }


    private int hash (K key) {
        // Translate key to an index of the array buckets.
        return Math.abs(key.hashCode()) % buckets.length;
    }


    public int search (K targetKey) {
        // Find which if any bucket of this OBHT is occupied by an entry whose key
        // is equal to targetKey. Return the index of that bucket.
        int b = hash(targetKey); int n_search=0;
        for (;;) {
            MapEntry<K,E> oldEntry = buckets[b];
            if (oldEntry == null)
                return NONE;
            else if (targetKey.equals(oldEntry.key))
                return b;
            else
            {
                b = (b + 1) % buckets.length;
                n_search++;
                if(n_search==buckets.length)
                    return NONE;

            }
        }
    }


    public void insert (K key, E val) {
        // Insert the entry <key, val> into this OBHT.
        MapEntry<K,E> newEntry = new MapEntry<K,E>(key, val);
        int b = hash(key); int n_search=0;
        for (;;) {
            MapEntry<K,E> oldEntry = buckets[b];
            if (oldEntry == null) {
                if (++occupancy == buckets.length) {
                    System.out.println("Hash tabelata e polna!!!");
                }
                buckets[b] = newEntry;
                return;
            }
            else if (oldEntry == former
                    || key.equals(oldEntry.key)) {
                buckets[b] = newEntry;
                return;
            }
            else
            {
                b = (b + 1) % buckets.length;
                n_search++;
                if(n_search==buckets.length)
                    return;

            }
        }
    }


    @SuppressWarnings("unchecked")
    public void delete (K key) {
        // Delete the entry (if any) whose key is equal to key from this OBHT.
        int b = hash(key); int n_search=0;
        for (;;) {
            MapEntry<K,E> oldEntry = buckets[b];

            if (oldEntry == null)
                return;
            else if (key.equals(oldEntry.key)) {
                buckets[b] = former;//(MapEntry<K,E>)former;
                return;
            }
            else{
                b = (b + 1) % buckets.length;
                n_search++;
                if(n_search==buckets.length)
                    return;

            }
        }
    }


    public String toString () {
        String temp = "";
        for (int i = 0; i < buckets.length; i++) {
            temp += i + ":";
            if (buckets[i] == null)
                temp += "\n";
            else if (buckets[i] == former)
                temp += "former\n";
            else
                temp += buckets[i] + "\n";
        }
        return temp;
    }


    public OBHT<K,E> clone () {
        OBHT<K,E> copy = new OBHT<K,E>(buckets.length);
        for (int i = 0; i < buckets.length; i++) {
            MapEntry<K,E> e = buckets[i];
            if (e != null && e != former)
                copy.buckets[i] = new MapEntry<K,E>(e.key, e.value);
            else
                copy.buckets[i] = e;
        }
        return copy;
    }

    public MapEntry<K,E> getBucket(int index) {
        return buckets[index];
    }
}


class Person{
    String name;
    String surname;
    String ipAddress;
    int salary;
    int moneySpent;
    String city;
    String time;

    Person(String name,String surname, String ipAddress, int salary, String time, int moneySpent, String city){
        this.name = name;
        this.surname = surname;
        this.ipAddress = ipAddress;
        this.salary = salary;
        this.moneySpent = moneySpent;
        this.city = city;
        this.time = time;
    }

    int timeToMins(){
        int hr = Integer.parseInt(time.split(":")[0]);
        int min = Integer.parseInt(time.split(":")[1]);
        return hr * 60 * min;
    }

    @Override
    public String toString() {
        return name + " " + surname + " with salary " + salary + " from address " + ipAddress + " who logged in at " + time;
    }
}

public class Solution {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        sc.nextLine();
        OBHT<String, List<Person>> table = new OBHT<>(n * 2);

        for (int i = 0; i < n; i++) {
            String[] parts = sc.nextLine().split("\\s+");
            String name = parts[0], surname = parts[1], ip = parts[3], time = parts[4], city = parts[5];
            int budget = Integer.parseInt(parts[2]), price = Integer.parseInt(parts[6]);
            int index = table.search(city);
            if (index == -1) {
                table.insert(city, new ArrayList<>());
            }
            index = table.search(city);
            if (Integer.parseInt(time.split(":")[0]) >= 12)
                table.getBucket(index).value.add(new Person(name, surname, ip, budget, time, price, city));
        }


        int m = sc.nextInt();
        sc.nextLine();

        for (int i = 0; i < m; i++) {
            String[] parts = sc.nextLine().split("\\s+");
            String city = parts[5];
            int index = table.search(city);
            List<Person> people = table.getBucket(index).value;
            System.out.println("City: " + city + " has the following number of customers:");
            System.out.println(people.size());
            System.out.println("The user who logged on earliest after noon from that city is:");

            Person earliestPerson = people.get(0);
            for(Person person : people){
                LocalTime logInTime = LocalTime.parse(person.time);
                if(logInTime.isBefore(LocalTime.parse(earliestPerson.time))){
                    earliestPerson = person;
                }
            }
            System.out.println(earliestPerson);
            System.out.println();
        }
    }
}
