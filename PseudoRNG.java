/* PseudoRNG.java
 * Description: PRN Generator for ISSM project.
 * Status: Needs testing
 * Version: 2020.4.30-1
 * Authors: Christopher Herras-Antig, Dylan Ott, Kevin Bullock, Name 4
 */

import java.util.ArrayList;

public class PseudoRNG {
    
    ArrayList<Float> randomList;

    public PseudoRNG() {
        randomList = generateRNList();
    }

    public PseudoRNG(int starting) {
        randomList = generateRNList(starting);
    }
    
    private ArrayList<Float> generateRNList(){
        ArrayList<Float> returnList = new ArrayList<Float>();
        int startingNum = 5965;

        for(int i = 0; i < 100; i++){
            int toSplit = startingNum * startingNum;
            ArrayList<Integer> digits = new ArrayList<Integer>(10);
            for(int j = 0; j < 8; j++){
                
                if (toSplit > 0){
                    digits.add(0, toSplit % 10);
                    toSplit /= 10;
                }
                else{
                    digits.add(0, 0);
                }
            }
            String newNum = digits.get(2).toString() + digits.get(3).toString() + digits.get(4).toString() + digits.get(5).toString();
            startingNum = Integer.parseInt(newNum);
            float toAdd = (float)startingNum / 10000;
            returnList.add(toAdd);
        }


        return returnList;
    }


    private ArrayList<Float> generateRNList(int starting){
        ArrayList<Float> returnList = new ArrayList<Float>();
        int startingNum = starting;

        for(int i = 0; i < 100; i++){
            int toSplit = startingNum * startingNum;
            ArrayList<Integer> digits = new ArrayList<Integer>(8);
            for(int j = 0; j < 8; j++){
                
                if (toSplit > 0){
                    digits.add(0, toSplit % 10);
                    toSplit /= 10;
                }
                else{
                    digits.add(0, 0);
                }
            }
            String newNum = digits.get(2).toString() + digits.get(3).toString() + digits.get(4).toString() + digits.get(5).toString();
            startingNum = Integer.parseInt(newNum);
            float toAdd = (float)startingNum / 10000;
            System.out.println(toAdd);
            returnList.add(toAdd);
        }


        return returnList;
    }

    public float nextFloat(){
        return randomList.remove(0);
    }
}