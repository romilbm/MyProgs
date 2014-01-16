package amazon.codingExercise;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by romil on 1/15/14.
 */
public class KCCabApp implements CabApp {
    private List<Cab> allCabs = new ArrayList<Cab>();
    private Position userPosition;

    public KCCabApp(List<KCCab> cabs, Position userPosition){
        //null check for both params
        this.userPosition = userPosition;
        allCabs.addAll(cabs);
    }

    public void setUserPosition(Position userPosition){
        //null check for user position
        this.userPosition = userPosition;
    }

    public void addCabs(KCCab cab){
        allCabs.add(cab);
    }

    @Override
    public Position getUserPosition() {
        return userPosition;
    }

    @Override
    public Iterator<Cab> getCabs() {
        return allCabs.iterator();
    }

    @Override
    public void register(CabStatusListener listener) {

    }
}
