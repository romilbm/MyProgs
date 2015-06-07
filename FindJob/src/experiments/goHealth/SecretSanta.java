package experiments.goHealth;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

/**
 * A class that contains method(s) that help implement the Party game, Secret Santa
 * Created by romil on 2/6/14.
 */
public class SecretSanta {

    /**
     * This method will assign a participant to be the Secret Santa of some other participant randomly chosen.
     * A participant can't be his own Secret Santa.
     * @param participants list of participants who want to play the game.
     * @return the list of individuals paired with the participants where participants[i] is matched with assignment[i],
     *      0<=i<sizeOf(participants).
     */
    public static String [] generateAssignments(final String[] participants){
        if(participants.length < 2){
            return participants;
        }

        //copy the names in the array into an arrayList.
        ArrayList<String> assignmentList = new ArrayList<String>();
        assignmentList.addAll(Arrays.asList(participants));

        Random generator = new Random();

        //start from the position at the end.
        for(int i=participants.length-1; i>=0; i--){

            //check to see if a participant is still assigned to himself.
            if(assignmentList.get(i).equals(participants[i])){

                //if he is, swap his name with a random position before him in the lit.
                //if he is at the 1st place, then swap him with a position after him.
                int swapIndex = (i>0) ? (generator.nextInt(i)) : generator.nextInt(participants.length-1)+1;

                //swap
                String temp = assignmentList.get(i);
                assignmentList.set(i, assignmentList.get(swapIndex));
                assignmentList.set(swapIndex,temp);
            } 
        }

        final String[] assignments = new String[participants.length];

        for(int j=0; j<participants.length; j++){
            assignments[j] = assignmentList.get(j);
        }

        return assignments;
    }
}
