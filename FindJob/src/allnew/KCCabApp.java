package allnew;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class KCCabApp implements CabApp {
    private List<Cab> allCabs = new ArrayList<Cab>();
    private Position userPosition;

    public KCCabApp(List<KCCab> cabs, Position userPosition){
        this.userPosition = userPosition;
        allCabs.addAll(cabs);
    }

    /**
     * Gets the current location of the user
     */
    @Override
    public Position getUserPosition(){
        return userPosition;
    }

    /**
     * Returns an iterator that gives access to the list of all cabs in the city
     */
    @Override
    public Iterator<Cab> getCabs(){
        return allCabs.iterator();
    }

    /**
     * Registers a CabStatusListener object for change notifications of cab object data.
     */
    public void register(CabStatusListener listener){

    }
}
