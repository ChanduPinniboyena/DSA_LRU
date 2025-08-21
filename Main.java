import java.util.*;
class Main {
    public static void main(String[] args) {
        // Create an LRU cache with capacity 3
        LRU cache = new LRU(3);
        
        // Add some elements to the cache
        System.out.println("Adding elements to cache:");
        cache.add(1);
        cache.add(2);
        cache.add(3);
        printCache(cache);
        
        // Add an existing element (should move it to front)
        System.out.println("\nAdding existing element 1:");
        cache.add(1);
        printCache(cache);
        
        // Add new element when cache is full (should remove least recently used)
        System.out.println("\nAdding new element 4 (cache full):");
        cache.add(4);
        printCache(cache);
        
        // Add another new element
        System.out.println("\nAdding new element 5:");
        cache.add(5);
        printCache(cache);
    }
    
    private static void printCache(LRU cache) {
        List<Integer> elements = cache.getCacheElements();
        System.out.println("Current cache elements (from least to most recently used):");
        for (int element : elements) {
            System.out.print(element + " ");
        }
        System.out.println();
    }
}
class CDLLNode {
    int val;
    CDLLNode next;
    CDLLNode prev;
    CDLLNode(int val) {
        this.val = val;
        next = null;
        prev = null;
    }
}

class CDLL {
    CDLLNode head;
    public CDLL() {
        head = null;
    }
    
    public CDLLNode insertAtbegin(int val) {
        CDLLNode nn = new CDLLNode(val);
        nn.next = nn;
        nn.prev = nn;
        if (head == null) {
            head = nn;
            return nn;
        }
        CDLLNode last = head.prev;
        nn.next = head;
        head.prev = nn;
        nn.prev = last;
        last.next = nn;
        head = nn;
        return head;
    }
    
    public int delelast() {
        if (head == null) {
            return -1;
        }
        if (head.next == head) {
            CDLLNode temp = head;
            head = null;
            return temp.val;
        }
        CDLLNode last = head.prev;
        CDLLNode lastprev = last.prev;
        lastprev.next = head;
        head.prev = lastprev;
        return last.val;
    }
    
    public void movetostart(CDLLNode node) {
        if (head == null) {
            return;
        }
        if (head == node) {
            return;
        }
        CDLLNode nodeprev = node.prev;
        CDLLNode nodenext = node.next;
        nodeprev.next = nodenext;
        nodenext.prev = nodeprev;
        CDLLNode last = head.prev;
        node.next = head;
        head.prev = node;
        node.prev = last;
        last.next = node;
        head = node;
    }
}

class LRU {
    CDLL list;
    int k;
    int sz;
    Map<Integer, CDLLNode> mp;
    
    public LRU(int k) {
        this.k = k;
        list = new CDLL();
        mp = new HashMap<>();
        sz = 0;
    }
    
    public void add(int x) {
        if (mp.containsKey(x)) {
            CDLLNode temp = mp.get(x);
            list.movetostart(temp);
        } else {
            if (sz < k) {
                CDLLNode N1 = list.insertAtbegin(x);
                mp.put(x, N1);
                sz++;
            } else {
                int v = list.delelast();
                mp.remove(v);
                CDLLNode N1 = list.insertAtbegin(x);
                mp.put(x, N1);
            }
        }
    }

    public List<Integer> getCacheElements() {
        List<Integer> ans = new ArrayList<>();
        CDLLNode h = list.head;
        if (h == null) {
            return ans;
        }
        ans.add(h.prev.val);
        CDLLNode temp = h.prev.prev;
        while (temp != h.prev) {
            ans.add(temp.val);
            temp = temp.prev;
        }
        return ans;
    }
}

