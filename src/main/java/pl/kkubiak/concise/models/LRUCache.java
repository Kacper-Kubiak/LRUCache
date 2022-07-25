package pl.kkubiak.concise.models;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import pl.kkubiak.concise.utils.Node;

import java.util.HashMap;

@Component
public class LRUCache {
    private HashMap<String, Node> cache;

    @Value("${lrucache.capacity}")
    private int capacity;

    private Node head;
    private Node tail;

    public LRUCache() {
        this.invalidate();
    }

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.invalidate();
    }

    public String get(String key) {
        if(cache.get(key) == null) return "-1";
        Node node = cache.get(key);
        //Refresh place in cache
        moveToHead(node);
        return node.getValue();
    }

    public void put(String key, String value) {
        //If key exist move to head
        if(cache.get(key) != null) {
            Node node = cache.get(key);
            node.setValue(value);
            moveToHead(node);
        }
        //If key is new add do head
        else {
            //If cache is full remove last used element
            if (cache.size() >= capacity) {
                cache.remove(tail.getPrevious().getKey());
                deleteNode(tail.getPrevious());
            }
            Node node = new Node(key, value);
            cache.put(key, node);
            addToHead(node);
        }
    }

    public void changeCapacity(int capacity) {
        this.capacity = capacity;
        while(cache.size() > capacity) {
            cache.remove(tail.getPrevious().getKey());
            deleteNode(tail.getPrevious());
        }
    }

    public void invalidate() {
        cache = new HashMap<>();
        head = new Node("", "");
        tail = new Node("", "");
        head.setNext(tail);
        tail.setPrevious(head);
        head.setPrevious(null);
        tail.setNext(null);
    }

    public int getCacheSize() {
        return cache.size();
    }

    public int getCapacity() {
        return capacity;
    }

    private void deleteNode(Node node) {
        node.getPrevious().setNext(node.getNext());
        node.getNext().setPrevious(node.getPrevious());
    }

    private void addToHead(Node node) {
        node.setNext(head.getNext());
        node.getNext().setPrevious(node);
        node.setPrevious(head);
        head.setNext(node);
    }

    private void moveToHead(Node node) {
        deleteNode(node);
        addToHead(node);
    }
}
