//At the input of this task you will be given rows with the following format:
//First_name Last_name budget ip_address time city price
//Example
//Jovan Todorov 1000 10.73.112.200 15:30 Bitola 760
//Which means that the person with First name and Last name Jovan Todorov, has a budget of 1000 denars, has an IP address with network 10.73.112 and host number 200, and joined at 15:30 to buy a ticked to Bitola 
//which costs 760 denars.
//You will be given N such rows, followed by an empty row and M more rows of the same format, which we will use for testing.
//From the test row you need to extract the city and then answer the following question with this city:
//Of the N people at the input, from the ones who buy a ticket to the same city
//how many of them had enough budget to buy the ticket; and
//of these, which one paid the highest amount?
//(if there are more with the same highest amount then which one is the first one in the input?) (there will always be at least one such)
//You will need to do this for all M rows for testing!


import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.*;

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
    static final int NONE = -1;

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

    public MapEntry<K, E> getBucket(int bucket){
        return buckets[bucket];
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
}


class City{
    String name;
}

class Person{
    String name;
    String surname;
    String ipAddress;
    int salary;
    int moneySpent;
    String city;

    Person(String name,String surname, String ipAddress, int salary, int moneySpent, String city){
        this.name = name;
        this.surname = surname;
        this.ipAddress = ipAddress;
        this.salary = salary;
        this.moneySpent = moneySpent;
        this.city = city;
    }

    boolean isBroke(){
        return salary - moneySpent >= 0;
    }

    @Override
    public String toString() {
        return name + " " + surname + " with salary " + salary + " from address " + ipAddress + " who spent " + moneySpent;
    }
}
public class Solution {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();

        OBHT<String, List<Person>> table = new OBHT<>(2 * N);

        for(int i = 0; i < N; i++){
            String name = sc.next();
            String surname = sc.next();
            int budget = sc.nextInt();
            String ip = sc.next();
            String time = sc.next();
            String city = sc.next();
            int moneySpent = sc.nextInt();
            Person person = new Person(name, surname, ip, budget, moneySpent, city);
            int id = table.search(city);
            if(id == -1){
                table.insert(city, new ArrayList<>());
            }
            id = table.search(city);
            if(budget >= moneySpent){
                table.getBucket(id).value.add(person);
            }
        }

        sc.nextLine();
        int m = sc.nextInt();

        for(int i = 0; i < m; i++){
            String name = sc.next();
            System.out.println(name);
            String surname = sc.next();
            int budget = sc.nextInt();
            String ip = sc.next();
            String time = sc.next();
            String city = sc.next();
            int moneySpent = sc.nextInt();
            int id = table.search(city);
            List<Person> people = table.getBucket(id).value;

            System.out.println("City: " + city + " has the following number of customers: ");
            System.out.println(people.size());
            System.out.println("The user who spent the most purchasing for that city is:");

            Person maxSpender = null;
            for(Person person : people){
                if(person.salary >= person.moneySpent){
                    if(maxSpender == null || person.moneySpent > maxSpender.moneySpent){
                        maxSpender = person;
                    }
                }
            }

            System.out.println(maxSpender);
            System.out.println();
        }
    }
}
