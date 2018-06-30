package com.iiit.iiitcontacts.dummy;

import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class DummyContent{

    public static final List<DummyItem> ITEMS = new ArrayList<DummyItem>();
    public static final Map<String, DummyItem> ITEM_MAP = new HashMap<String, DummyItem>();
    private static final int COUNT = 25;

   /* static {
        for (int i = 1; i <= COUNT; i++) {
            addItem(createDummyItem(i));
        }
    }*/

    public static void addItem(DummyItem item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.id, item);
    }

        /*private static DummyItem createDummyItem(int position) {
        return new DummyItem(String.valueOf(position), "Contact " + String.valueOf(position), "Contact" + String.valueOf(position) + "@gmail.com", "882641366" + String.valueOf(position));
    }*/

    public static class DummyItem  implements Comparable<DummyItem> {
        public String id;
        public String name;
        public String email;
        public String number;
        public String photo;

        public String getName() {
            return name;
        }

        public String getEmail() {
            if(email!=null)
                return email;
            return "Backup@gmail.com";
        }

        public String getNumber() {
            return number;
        }

        public String getId() {
            return id;
        }

        public String getPhoto() {
            return photo;
        }

        public DummyItem(String id, String name, String number, String email,String photo) {
            this.id = id;
            this.name= name;
            this.email= email;
            this.number = number;
            this.photo = photo;
        }

        @Override
        public String toString() {
            return name;
        }

        @Override
        public int compareTo(@NonNull DummyItem dummyItem) {
            return name.compareTo(dummyItem.name);
        }
    }
}