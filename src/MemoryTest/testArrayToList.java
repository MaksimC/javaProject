package MemoryTest;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by emaktse on 12.12.2015.
 */
public class testArrayToList {

    public static void main (String[] args) {


        ArrayList<Integer> list = new ArrayList<Integer>(10);

        for (int i = 0; i < 10; i++) {
            list.add(i);
        }
        Collections.shuffle(list);
        System.out.println(list);

        Integer[] randNumber = list.toArray(new Integer[0]);

        for (int i = 0; i < randNumber.length; i++) {
            System.out.println(randNumber[i]);
        }
    }
}
