package ru.levelup.elena.roshchina.qa.homework_03;

import ru.levelup.elena.roshchina.qa.homework_03.aircrafts.Aircraft;

import java.util.*;

public class CollectionTaskMain {

    public static final int START = 1;
    public static final int SEQUENCE_SIZE = 100000;
    public static final int TEST_BIT_SIZE = 20;

    public static void main(String[] args){

        ArrayList<Integer> arr =  new ArrayList<Integer>();
        for (int i=START; i< START+SEQUENCE_SIZE; i++) arr.add(i);
        for (int i=0; i<10; i++) Collections.shuffle(arr);
        Set<Integer> arrSet = new HashSet<Integer>(arr);
        System.out.println("Last " + TEST_BIT_SIZE + " members of mixed sequence: ");
        for (int i = SEQUENCE_SIZE - TEST_BIT_SIZE; i < SEQUENCE_SIZE; i++) System.out.print(arr.get(i) + ", ");
        System.out.println();

        System.out.println("Size of List equals to size of Set: " + (arr.size() == arrSet.size()));
        if (arr.size() == arrSet.size()) System.out.println("Thus, each of these values is unique.");
        HashMap<Integer, ArrayList<Integer>> classified = new HashMap<Integer, ArrayList<Integer>>();

        // create containers for dividers
        int[] divider = {2,3,5,7};
        for (int div: divider){
            classified.put(div, new ArrayList<Integer>());
        }

        boolean isPlaced;
        for (int i=0; i<SEQUENCE_SIZE; i++){
            isPlaced = false;
            for (int div: divider){
                if (!isPlaced && arr.get(i) % div == 0 ) {
                    classified.get(div).add(arr.get(i));
                    isPlaced = true;
                }
            }
        }

        System.out.println("Partial output of the obtained lists:");
        for (int div: divider){
            System.out.print("Divider = " + div + ", Total  = " + classified.get(div).size() + "\n\t");
            for (int i=0; i<Math.min(TEST_BIT_SIZE, classified.get(div).size()); i++){
                System.out.print(classified.get(div).get(i) + ", ");
            }
            if (TEST_BIT_SIZE < classified.get(div).size()) System.out.print("...");
            System.out.println();
        }
    }
}
