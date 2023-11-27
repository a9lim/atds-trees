package Entity;

import java.util.HashMap;

public class Behavior<T> {
    private HashMap<Integer,T> timings;

    public Behavior(){
        timings = new HashMap<>();
    }

    public void put(int i, T s){
        timings.put(i,s);
    }

    public void put(int[] j, T[] s){
        for(int i = 0 ; i < j.length; i++)
            timings.put(j[i],s[i]);
    }

    public T get(int i){
        return timings.get(i);
    }



}
