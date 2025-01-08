//In this task, you will work with two objects "Employees" and "Projects".
//An employee is defined with 2 values: Name (String), Age (integer).
//A project is defined with 2 values: Working Time (integer), Hourly Salary (integer).
//For a given project, the total salary is calculated as the product of the working time and the hourly salary.
//Employees will be offered projects, and they should choose the project from which they will receive the highest salary (if they have a choice of multiple projects with the same highest salary, then the first one).
//Your task will be to help employees by using CBHT with 10 buckets where for each employee you will store their best offer.
//For the hash function, use the product of the employee's age and the ASCII value of the first letter of the employee's name.


import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;

class MapEntry<K extends Comparable<K>,E> implements Comparable<K> {

    K key;
    E value;

    public MapEntry (K key, E val) {
        this.key = key;
        this.value = val;
    }

    public int compareTo (K that) {
        // Compare this map entry to that map entry.
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


class CBHT<K extends Comparable<K>, E> {
    private SLLNode<MapEntry<K,E>>[] buckets;

    @SuppressWarnings("unchecked")
    public CBHT(int m) {
        // Construct an empty CBHT with m buckets.
        buckets = (SLLNode<MapEntry<K,E>>[]) new SLLNode[m];
    }

    private int hash(K key) {
        // Translate key to an index of the array buckets.
        return Math.abs(key.hashCode()) % buckets.length;
    }

    public SLLNode<MapEntry<K,E>> search(K targetKey) {
        int b = hash(targetKey);
        for (SLLNode<MapEntry<K,E>> curr = buckets[b]; curr != null; curr = curr.succ) {
            if (targetKey.equals(((MapEntry<K, E>) curr.element).key))
                return curr;
        }
        return null;
    }

    public void insert(K key, E val) {		// Insert the entry <key, val> into this CBHT.
        MapEntry<K, E> newEntry = new MapEntry<K, E>(key, val);
        int b = hash(key);
        for (SLLNode<MapEntry<K,E>> curr = buckets[b]; curr != null; curr = curr.succ) {
            if (key.equals(((MapEntry<K, E>) curr.element).key)) {
                curr.element = newEntry;
                return;
            }
        }
        buckets[b] = new SLLNode<MapEntry<K,E>>(newEntry, buckets[b]);
    }

    public void delete(K key) {
        int b = hash(key);
        for (SLLNode<MapEntry<K,E>> pred = null, curr = buckets[b]; curr != null; pred = curr, curr = curr.succ) {
            if (key.equals(((MapEntry<K,E>) curr.element).key)) {
                if (pred == null)
                    buckets[b] = curr.succ;
                else
                    pred.succ = curr.succ;
                return;
            }
        }
    }

    public String toString() {
        String temp = "";
        for (int i = 0; i < buckets.length; i++) {
            temp += i + ":";
            for (SLLNode<MapEntry<K,E>> curr = buckets[i]; curr != null; curr = curr.succ) {
                temp += curr.element.toString() + " ";
            }
            temp += "\n";
        }
        return temp;
    }

}

 class Person implements Comparable<Person> {
     String name;
     int age;

     Person(String name, int age) {
         this.age = age;
         this.name = name;
     }

     @Override
     public boolean equals(Object o) {
        Person person = (Person) o;
        return age == person.age && name.equals(person.name);
     }
     @Override
     public int hashCode() {
        int h = age * (int)name.charAt(0);
        return h % 10;
     }

     @Override
     public String toString() {
         return "<" + name + ", " + age + ">";
     }

     @Override
     public int compareTo(Person o) {
         if(name.compareTo(o.name) != 0){
             return name.compareTo(o.name);
         }
         return Integer.compare(age, o.age);
     }
 }

 class Project {
    int hours;
    int salary;

     Project(int hours, int salary) {
        this.salary = salary;
        this.hours = hours;
     }

     @Override
     public String toString() {
        return "<" + hours + ", " + salary + ">";
     }
 }

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        CBHT<Person, Project> table = new CBHT<>(10);

        int N = Integer.parseInt(br.readLine());

        for(int i = 1; i <= N; i++){
            String line = br.readLine();
            String[] input = line.split(" ");
            Person person = new Person(input[0], Integer.parseInt(input[1]));
            Project project = new Project(Integer.parseInt(input[2]), Integer.parseInt(input[3]));
            table.insert(person, project);
        }

        System.out.println(table);
    }
}

