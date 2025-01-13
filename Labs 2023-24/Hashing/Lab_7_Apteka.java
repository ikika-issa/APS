//It is necessary to make a computer application that will speed up the operation of a pharmacy. 
//The application should enable the user (pharmacist) to quickly search through the huge set of drugs entered into the system. The way he should search is as follows: 
//it is enough to enter the first 3 letters of the name of the drug so that a list of the drugs available in the system can be displayed. 
//The job of the pharmacist is to check if the drug is in the system and to give information to the client. The information he should give to the customer is whether the drug is on the positive list of drugs, 
//what is the price and how many pieces of the drug are in stock. If the drug exists, the customer orders it, stating how many pieces he will buy. The pharmacist should record this action on the system 
//(that is, reduce the stock of drugs by as many pieces as he dispensed to the client). If the customer's order is greater than the drug stock in the system, no action is taken.

//Input: From the standard input, a number N is first given which represents the number of drugs that will be entered into the system. In the next N lines are given the names of the drugs, 
//whether they are on the positive list (1/0), the price and number of pieces, all separated by a space. Lines are then given with drug names and number of pieces ordered by the customer. 
//The word is given to indicate the end KRAJ.

//Output: The following information should be printed on the standard output for each of the inputs: IME POZ/NEG CENA BR_LEKOVI. If the drug is not found, Nema takov lek. is printed. 
//If the customer's order is larger than the stock, it is printed  Nema dovolno lekovi, otherwise Napravena naracka.

//Note: The problem should be solved with a hash table. The function that maps drug names to numbers is as follows: h(w)=(29∗(29∗(29∗0+ASCII(c1))+ASCII(c2))+ASCII(c3))%102780 where the word w=c1c2c3c4c5…. is composed of all capital letters.
//Also, for the drugs, a separate class should be made which will have the specified characteristics of the drug in the system as attributes.
//Class name: Apteka.


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Objects;


class CBHT<K, E> {
    private SLLNode<MapEntry<K,E>>[] buckets;

    @SuppressWarnings("unchecked")
    public CBHT(int m) {
        buckets = (SLLNode<MapEntry<K,E>>[]) new SLLNode[m];
    }

    private int hash(K key) {
        int h =(29*(29*(((String)(key)).charAt(0) + ((String)(key)).charAt(1))+((String)(key)).charAt(2)))%102780;
        return h % buckets.length;
    }

     public SLLNode<MapEntry<K,E>> search(K targetKey) {
         int b = hash(targetKey);
         for (SLLNode<MapEntry<K,E>> curr = buckets[b]; curr != null; curr = curr.succ) {
             if (targetKey.equals(curr.element.key))     return curr;
         }
         return null;
     }

    public void insert(K key, E val) {
        MapEntry<K, E> newEntry = new MapEntry<K, E>(key, val);
        int b = hash(key);
        for (SLLNode<MapEntry<K,E>> curr = buckets[b]; curr != null; curr = curr.succ) {
            if (key.equals(curr.element.key)) {
                curr.element = newEntry;
                return;
            }
        }
        buckets[b] = new SLLNode<MapEntry<K,E>>(newEntry, buckets[b]);
    }

    public void delete(K key) {
        int b = hash(key);
        for (SLLNode<MapEntry<K,E>> pred = null, curr = buckets[b]; curr != null; pred = curr, curr = curr.succ) {
            if (key.equals(curr.element.key)) {
                if (pred == null)   buckets[b] = curr.succ;
                else                pred.succ = curr.succ;
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
class MapEntry<K,E> {
    K key;
    E value;
    public MapEntry (K key, E val) {
        this.key = key;
        this.value = val;
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

class Lek{
    String ime;
    int pozLista;
    int cena;
    int kolicina;

    public String getIme() {                return ime;                 }
    public void setIme(String ime) {        this.ime = ime;	            }
    public int getCena() {      		    return cena;	            }
    public void setCena(int cena) {		    this.cena = cena;           }
    public int getKolicina() {  		    return kolicina;	        }
    public void setKolicina(int kolicina) { this.kolicina = kolicina;	}
    public int getPozLista() {      		return pozLista;        	}

    public Lek(String ime, int pozLista, int cena, int kolicina) {
        this.ime = ime.toUpperCase();
        this.pozLista = pozLista;
        this.cena = cena;
        this.kolicina = kolicina;
    }

    @Override
    public String toString() {
        if(pozLista==1) return ime+"\n"+"POZ\n"+cena+"\n"+kolicina;
        else return ime+"\n"+"NEG\n"+cena+"\n"+kolicina;
    }
}

class LekKluch{
    String ime;
    public LekKluch(String ime) {
        this.ime = ime.toUpperCase();
    }

    @Override
    public int hashCode() {
        // TODO implement
        return super.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LekKluch lekKluch = (LekKluch) o;
        return Objects.equals(ime, lekKluch.ime);
    }
}

public class Apteka {
    public static void main(String[] args) throws  IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        CBHT<String, Lek> table = new CBHT<>(2*N);

        for(int i = 1; i <= N; i++){
            String line = br.readLine();
            String[] input = line.split(" ");

            Lek lek = new Lek(input[0], Integer.parseInt(input[1]), Integer.parseInt(input[2]), Integer.parseInt(input[3]));
            table.insert(input[0].toUpperCase(), lek);
        }

        String order = (br.readLine()).toUpperCase();
        while(!order.equals("KRAJ")){
            int quantity = Integer.parseInt(br.readLine());
            SLLNode<MapEntry<String, Lek>> result = table.search(order);

            if(result == null){
                System.out.println("Nema takov lek");
                order = (br.readLine()).toUpperCase();
            }
            else if(result.element.value.ime.equals(order)){
                System.out.println(result.element.value.toString());

                if(result.element.value.kolicina < quantity){
                    System.out.println("Nema dovolno lekovi");
                }
                else{
                    int old = result.element.value.kolicina;
                    result.element.value.setCena(old - quantity);
                    table.insert(order, result.element.value);
                    System.out.println("Napravena naracka");
                }
                order = (br.readLine()).toUpperCase();
            }else{
                order = (br.readLine()).toUpperCase();
            }
        }
    }
}

