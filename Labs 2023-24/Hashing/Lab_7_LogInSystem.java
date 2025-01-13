//You are supposed to simulate a log in system. The user enters username and password. If such user exists in the system you are supposed to print "Najaven" to the standard output, 
//else print "Nenajaven" and give the user another prompt for credentials. This stops when the user will enter username and password that match, a certain user credentials in the system.

//Input: In the first line of the input you are given a single integer N. In the following N lines you are given usernames and passwords separated with one white space. 
//These are the users that exist in the system. After this you are supposed to read usernames and passwords from the standard input until, a user can be successfully logged in.

//Output: Print "Nenajaven" to the standard output for every failed log in try, until we get a successful log in. Then you have to print "Najaven"

//Example. Input: 3 ana banana pero zdero trpe trpi ana ana ana banana trpe trpi KRAJ

//Output: Nenajaven Najaven

//Note: Use closed bucket hash table. Figure out the hash table size by yourself. The hash function has already been given to you.
//Class name: Lozinki


import java.io.BufferedReader;
import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Hashtable;


class MapEntry<K extends Comparable<K>,E> implements Comparable<K> {

    // Each MapEntry object is a pair consisting of a key (a Comparable
    // object) and a value (an arbitrary object).
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

class CBHT<K extends Comparable<K>, E> {

    // An object of class CBHT is a closed-bucket hash table, containing
    // entries of class MapEntry.
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
        // Find which if any node of this CBHT contains an entry whose key is
        // equal
        // to targetKey. Return a link to that node (or null if there is none).
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
                // Make newEntry replace the existing entry ...
                curr.element = newEntry;
                return;
            }
        }
        // Insert newEntry at the front of the 1WLL in bucket b ...
        buckets[b] = new SLLNode<MapEntry<K,E>>(newEntry, buckets[b]);
    }

    public void delete(K key) {
        // Delete the entry (if any) whose key is equal to key from this CBHT.
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


public class Lozinki {
    public static void main (String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        CBHT<String, String> table;
        table = new CBHT<String, String>(2*N);

        //inserting elements into the hash table
        for(int i = 1; i <= N; i++) {
            String line = br.readLine();
            String[] userNamePass = line.split(" ");
            table.insert(userNamePass[0],userNamePass[1]);
        }

        String result = "";
        String attempt = br.readLine();
        while(attempt.compareTo("KRAJ") != 0){
            String[] usernamePass = attempt.split(" ");
            SLLNode<MapEntry<String, String>> searched = table.search(usernamePass[0]);

            if(searched == null){
                result += "Nenajaven\n";
                attempt = br.readLine();
            }
            else if (searched.element.value.equals(usernamePass[1])){
                result += "Najaven\n";
                break;
            }
            else{
                result += "Nenajaven\n";
                attempt = br.readLine();
            }
        }

        System.out.println(result);
    }
}
