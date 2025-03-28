

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

    private MapEntry<K,E>[] buckets;

    static final int NONE = -1; // ... distinct from any bucket index.

    @SuppressWarnings({ "rawtypes", "unchecked" })
    private static final MapEntry former = new MapEntry(null, null);


    private int occupancy = 0;

    @SuppressWarnings("unchecked")
    public OBHT (int m) {
        buckets = (MapEntry<K,E>[]) new MapEntry[m];
    }


    private int hash (K key) {
        return Math.abs(key.hashCode()) % buckets.length;
    }


    public int search (K targetKey) {
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
        int b = hash(key); int n_search=0;
        for (;;) {
            MapEntry<K,E> oldEntry = buckets[b];

            if (oldEntry == null)
                return;
            else if (key.equals(oldEntry.key)) {
                buckets[b] = former;
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
        return name + " " + surname + " with salary " + salary + " from address " + ipAddress + " who spent " + moneySpent;
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

            String[] ipParts = ip.split("\\.");
            String network = ipParts[0] + "." + ipParts[1] + "." + ipParts[2];

            int index = table.search(network);

            if (index == -1) {
                table.insert(network, new ArrayList<>());
            }

            index = table.search(network);
            if (budget >= price)
                table.getBucket(index).value.add(new Person(name, surname, ip, budget, time, price, city));
        }


        int m = sc.nextInt();
        sc.nextLine();

        for (int i = 0; i < m; i++) {
            String[] parts = sc.nextLine().split("\\s+");
            String ip = parts[3];

            String[] ipParts = ip.split("\\.");
            String network = ipParts[0] + "." + ipParts[1] + "." + ipParts[2];

            int index = table.search(network);

            List<Person> people = table.getBucket(index).value;
            System.out.println("IP network: " +  network + " has the following number of users:");
            System.out.println(people.size());
            System.out.println("The user who spent the most from that network is:");

            Person max = people.get(0);
            for(Person person : people){
                if(person.moneySpent > max.moneySpent){
                    max = person;
                }
            }
            System.out.println(max);
            System.out.println();
        }
    }
}
