//You need to do a spell check of a text written in English, if it's correctly written. For that, first you are given a dictionary of words (i.e. a list of all the words that are used), and then you are given a text. As a result you should print all the words that are spelled incorrectly or are not present in the dictionary.

//Input: First you are given an integer N - number of terms in the dictionary, and then in the next N rows you have the words themselves. Then you are given a text, which should be spell-checked.

//Output: You should print a list of words that are written incorrectly (each in a separate row). If all words are written correctly you should print: Bravo.

//Note: You should ignore punctual marks such as dot(.), comma(,), exclamation mark(!) and question mark(?). You should also take in consideration uppercase / lowercase letters, i.e. 
//if the words in the dictionary are with only lowercase letters, in a sentence they can appear with a capitalized first letter, and that should be considered as correct. 
//Work with an Open bucket hash table. You should determine the number of buckets and the hash function yourselves.


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

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
        return "(" + key + "," + value + ")";
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

    public MapEntry<K,E> getBucket(int i){
        return buckets[i];
    }

    public int search (K targetKey) {
        int b = hash(targetKey); int n_search=0;
        for (;;) {
            MapEntry<K,E> oldEntry = buckets[b];
            if (oldEntry == null)
                return NONE;
            else if (targetKey.equals(oldEntry.key))
                return b;
            else{
                b = (b + 1) % buckets.length;
                n_search++;
                if(n_search==buckets.length)
                    return NONE;
            }
        }
    }

    public void insert (K key, E val) {
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
            } else if (oldEntry == former
                    || key.equals(oldEntry.key)) {
                buckets[b] = newEntry;
                return;
            } else{
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
            } else{
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
}


class Zbor implements Comparable<Zbor>{
    String zbor;

    public Zbor(String zbor) {
        this.zbor = zbor;
    }
    @Override
    public boolean equals(Object obj) {
        Zbor pom = (Zbor) obj;
        return this.zbor.equals(pom.zbor);
    }
    @Override
    public int hashCode() {
        return ((int)zbor.charAt(0) + 1 ) * 356;
    }
    @Override
    public String toString() {
        return zbor;
    }
    @Override
    public int compareTo(Zbor arg0) {
        return zbor.compareTo(arg0.zbor);
    }
}


public class Speluvanje {
    public static void main(String[] args) throws IOException {
        OBHT<Zbor, String> tabela;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        //---Vie odluchete za goleminata na hesh tabelata----
        tabela = new OBHT<Zbor,String>(N*2);

        for(int i = 0; i < N; i++) {
            String word = br.readLine();
            Zbor zbor = new Zbor(word);
            tabela.insert(zbor, word);
        }

        String line = br.readLine();
        line = line.replace("?", "");
        line = line.replace(",", "");
        line = line.replace(".", "");
        line = line.replace("!", "");
        
        
         if(line.equals("")){
            System.out.println("Bravo");
            return;
        }
        
        String[] input = line.split(" ");
        int j = 0;
        String [] not_found = new String[N];
        int found = 0;

        for(int i = 0; i < input.length; i++) {
            Zbor zb = new Zbor(input[i].toLowerCase());
            int id = tabela.search(zb);
            if(id != -1){
                found++;
            }else{
                not_found[j++] = input[i];
            }
        }

        if(found == input.length){
            System.out.println("Bravo");
        }
        else{
            for(int i = 0; i < j; i++){
                System.out.println(not_found[i]);
            }
        }
    }
}
