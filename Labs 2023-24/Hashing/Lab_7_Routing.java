//You should simulate routing by using a hash table. Every router is one bucket in the hash table and it receives the input packets only through one interface. 
//Since the router performs the routing of the packet by using the static routes it knows, when it receives a packet it should tell if it is possible to route the packet in the network (postoi or nepostoi). 
//It is important that all addresses have network mask /24 which means that the last 8 bits are allocated for addressing. 
//We assume that all addresses are busy and the packet can be transferred to any device in a network if the network exists in the routing table. For example, 
//if the address 10.10.10.0 can be found in the routing table, it means that the router can transfer the packet to all devices in that network (10.10.10.1- 10.10.10.254).
//The number of the routers is given in the first input line. In the next lines for each router the IP address of the interface and IP addresses of the networks to which it has static routes are given. 
//Then the number of routing attempts are entered. In the next lines for each attempt an input interface and device IP address are given. The router should answer if it knows the address or not.
//Class Name: RoutingHashJava


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

    public void insert(K key, E val) {
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

public class RoutingHashJava {
    public static void main (String[] args) throws IOException {
        BufferedReader  br = new BufferedReader (new InputStreamReader(System.in));
        CBHT<String, String[]> table;
        int n = Integer.parseInt(br.readLine());

        table = new CBHT<String, String[]>(2*n);

        for (int i = 1; i <= n; i++) {
            String interfacee = br.readLine();
            String routing_table = br.readLine();

            String [] routes = routing_table.split(",");
            table.insert(interfacee, routes);
        }

        int m = Integer.parseInt(br.readLine());

        for(int i = 1; i <= m; i++){
            String ipAddressInterface = br.readLine();
            String network = br.readLine();

            SLLNode<MapEntry<String, String[]>> node = table.search(ipAddressInterface);
            boolean exists = false;

            if(node != null){
                String[] result = node.element.value;
                String[] byte_network = network.split("\\.");


                for(int j = 0; j < result.length; j++){
                    String[] byte_table = result[j].split("\\.");

                    if(byte_table[0].compareTo(byte_network[0]) == 0 && byte_table[1].compareTo(byte_network[1]) == 0 && byte_table[2].compareTo(byte_network[2]) == 0){
                        System.out.println("postoi");
                        exists = true;
                        break;
                    }

                }
                if(!exists){
                    System.out.println("ne postoi");
                }
            }
            else{
                System.out.println("ne postoi");
            }
        }
    }
}
