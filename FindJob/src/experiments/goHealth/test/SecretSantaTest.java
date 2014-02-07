package experiments.goHealth.test;

import org.junit.Test;

import static experiments.goHealth.SecretSanta.generateAssignments;
import static org.junit.Assert.assertEquals;

/**
 * Created by romil on 2/7/14.
 */
public class SecretSantaTest {

    @Test
    public void testEmptyList(){
        final String[] participants = new String[] {};
        final String[] assignments = generateAssignments(participants);
        assertEquals(true, isAssignmentValid(participants, assignments));

    }

    @Test
    public void testOneParticipant(){
        final String[] participants = new String[] {"Kyle"};
        final String[] assignments = generateAssignments(participants);
        assertEquals(true, isAssignmentValid(participants, assignments));
    }

    @Test
    public void testTwoParticipants(){
        final String[] participants = new String[] { "Kyle", "Kenny"};
        final String[] assignments = generateAssignments(participants);
        assertEquals(true, isAssignmentValid(participants, assignments));
    }

    @Test
    public void testMoreThanTwoParticipantsThree(){
        final String[] participants = new String[] { "Kyle", "Kenny", "Eric"};
        int i=0;

        do{
            final String[] assignments = generateAssignments(participants);
            assertEquals(true, isAssignmentValid(participants, assignments));
            i++;
        } while(i < 50);
    }

    @Test
    public void testMoreThanTwoParticipants(){
        final String[] participants = new String[] { "Kyle", "Kenny", "Eric", "Stan","Stewie", "Brian", "A", "B", "C", "D" };
        int i=0;
        do{
            final String[] assignments = generateAssignments(participants);
            assertEquals(true, isAssignmentValid(participants, assignments));
            i++;
        } while(i < 100);
    }

    private boolean isAssignmentValid(String [] participants, String assignment[]){
        if(participants.length != assignment.length){
            return false;
        }

        if(participants.length == 0 || participants.length == 1){
            return true;
        }

        for(int i=0; i<participants.length; i++){
            if(participants[i] == assignment[i]){
                return false;
            }
        }
        return true;
    }
}
