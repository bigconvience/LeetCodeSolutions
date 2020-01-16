package com.leetcode.linkedlist;

import java.util.LinkedHashMap;
import java.util.Map;

public class LRUCache {

    public static void main(String[] args) {
        LRUCache lruCache = new LRUCache(2);
        lruCache.put(1, 1);
        lruCache.put(2, 2);
        lruCache.get(1);
        lruCache.put(3, 3);
        lruCache.get(2);
        lruCache.get(3);
    }

    private int mCapacity;
    private Map<Integer, Integer> mMap;

    public LRUCache(int capacity) {
        mCapacity = capacity;
        mMap = new LinkedHashMap<>(0, 0.75f, true);
    }

    public int get(int key) {
        return mMap.getOrDefault(key, new Integer(-1));
    }

    public void put(int key, int value) {
        if (mMap.containsKey(key)) {
            mMap.put(key, value);
        } else {
           mMap.put(key, value);
           trimToSize(mCapacity);
        }
    }

    private void trimToSize(int maxSize) {
        while (true) {
            if (mMap.size() <= maxSize) {
                break;
            }

            Map.Entry<Integer, Integer> toEvict = mMap.entrySet().iterator().next();

            if (toEvict == null) {
                break;
            }
            mMap.remove(toEvict.getKey());
        }
    }
}
