package Programmers;

import java.lang.reflect.Array;
import java.util.*;

public class LargestNumber {

    public static class Elem implements Comparator<Elem> {
        int i = 0;

        Elem(int i){
            this.i = i;
        }
        @Override
        public String toString(){

            return Integer.toString(i);
        }
        @Override
        public int compare(Elem o1, Elem o2) {
            String i1 = Integer.toString(o1.i);
            String i2 = Integer.toString(o2.i);
            int a=  Integer.parseInt(i1 + i2);
            int a2=  Integer.parseInt(i2+i1);
            if(a > a2){
                return -1;
            }else{
                return 1;
            }
        }
    }
    public static String solution(int[] numbers) {
        LinkedList<Elem> arr= new LinkedList<Elem>();
        for(int i =0; i < Arrays.stream(numbers).count(); i++){
            arr.add(new Elem(numbers[i]));
        }
        Collections.sort(arr, new Elem(0));
        String a = "";
        for(int i = 0; i< arr.size(); i++){
            a = a + Integer.toString(arr.get(i).i);
        }
        a= Integer.toString(Integer.parseInt(a));
        return a;
    }

    public static void main(String[] args){
        //int[] numbers = {9,5, 30, 3, 34 };
        int[] numbers = {0,0,0,0,0};
        solution(numbers);






    }
}
