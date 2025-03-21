//Given a string, you need to find the most frequent sub-string that is a part of the original and print it. If two sub-strings are equally frequent, you should print the longer one. 
//If they are with the same length as well, then you should print the one that is lexicographically smaller.
//Example: Sub-strings of the string "abc" are "a", "b", "c", "ab", "bc", "abc". They all have the same frequency, so the longest one is printed - "abc".


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
		buckets = (SLLNode<MapEntry<K,E>>[]) new SLLNode[m];
	}

	private int hash(K key) {
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
    
    public int getSize(){
        return buckets.length;
    }

    public SLLNode<MapEntry<K, E>> getBucket(int id){
        return buckets[id];
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

public class MostFrequentSubstring {
	public static void main (String[] args) throws IOException {
        CBHT<String,Integer> tabela = new CBHT<String,Integer>(300);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String word = br.readLine().trim();

        for(int i = 0; i < word.length(); i++) {
            for(int j = i + 1; j <= word.length(); j++) {
                String sub = word.substring(i, j);
                SLLNode<MapEntry<String, Integer>> searched = tabela.search(sub);

                if(searched == null){
                    tabela.insert(sub, 1);
                }else{
                    tabela.insert(sub, searched.element.value + 1);
                }
            }
        }

        int maxFreq = 0;
        String maxFreqSub = "";

        for(int i = 0; i < tabela.getSize(); i++){
            for(SLLNode<MapEntry<String, Integer>> curr = tabela.getBucket(i); curr != null; curr = curr.succ) {
                String sub = curr.element.key;
                int freq = curr.element.value;

                if(freq > maxFreq || (freq == maxFreq && sub.length() > maxFreqSub.length())) {
                    maxFreqSub = sub;
                    maxFreq = freq;
                }
            }
        }

        System.out.println(maxFreqSub);
    }
}
