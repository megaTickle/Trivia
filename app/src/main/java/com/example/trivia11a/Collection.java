package com.example.trivia11a;

import java.util.ArrayList;

public class Collection {

    private ArrayList<Questions> arrl;
    private int index;

    public Collection(){
        Questions q1 = new Questions("1+10","7","11","3","5",2);
        Questions q2 = new Questions("1+2","5","1","3","7",3);
        Questions q3 = new Questions("1+3","3","4","1","9",2);
        Questions q4 = new Questions("1+4","5","8","2","10",1);
        Questions q5 = new Questions("1+0","1","11","5","3",1);

        arrl = new ArrayList<>();
        arrl.add(q1);
        arrl.add(q2);
        arrl.add(q3);
        arrl.add(q4);
        arrl.add(q5);
    }

    public void initQusetions(){
        index = 0;
    }

    public Questions getNextQusetion(){
        Questions q = arrl.get(index);
        index++;
        return q;
    }

    public boolean isNotLastQuestion(){
        return (index < arrl.size());
    }

    public int getIndex(){
        return index;
    }
}
