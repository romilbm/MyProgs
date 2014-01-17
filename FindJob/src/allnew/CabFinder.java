package allnew;

import java.util.Comparator;
import java.util.Iterator;
import java.util.PriorityQueue;

/**
 * Created by romil on 1/16/14.
 */
class CabFinder implements CabStatusListener {

    private CabApp cabApp;

    //max-heap - the root element will be the farthest from the user within a given radius.
    private PriorityQueue<KCCab> maxNearestCabs;

    //min-heap - the root element will be the nearest to the user.
    private PriorityQueue<KCCab> remainingCabs;

    //the max number of nearest available cabs the user can see.
    private int maxCabs;

    //assume 1km = 50units!
    public static final double RADIUS = 50;

    /**
     * Initiates CabFinder. Called only once per app startup.
     * @app An application object providing services implemented by
     *      the rest of the application.
     * @maxCabs Nearest number of cabs that can be returned to the user
     */
    public void initialize(CabApp app, int maxCabs){
        this.cabApp = app;
        this.maxCabs = maxCabs;

        //reverse ordering
        //this comparator will be used to determine the nearest possible cabs
        //they will already be filtered by availability.
        Comparator<KCCab> c = new Comparator<KCCab>(){
            @Override
            public int compare(KCCab o1, KCCab o2){
                if(o1.getDistanceFromUser() <o2.getDistanceFromUser()){return 1;}
                else if(o1.getDistanceFromUser() == o2.getDistanceFromUser()){ return 0; }
                else {return -1;}
            }
        };

        maxNearestCabs = new PriorityQueue<KCCab>(maxCabs,c);
        remainingCabs = new PriorityQueue<KCCab>();

        Iterator<Cab> cabIterator = cabApp.getCabs();
        //iterate over and get all the cabs
        while(cabIterator.hasNext()){
            KCCab cab = new KCCab(cabIterator.next(), cabApp.getUserPosition());
            //insert either in nearest or in remaningCabs
            //try to insert in nearest
            if(!insertInNearestQueue(cab)){
                remainingCabs.add(cab);
            }
        }
    }

    /**
     * Depending on its distance from the user, its position in the RADIUS and availability,
     * the cab can be put in the list of nearest available cabs.
     * @param cab A cab to be inserted in the nearest available list.
     * @return true if the cab had been added to the nearest available list, false otherwise.
     */
    private boolean insertInNearestQueue(KCCab cab){
        double distance = cab.getDistanceFromUser();
        //check if it lies in the radius
        //if it is available
        //if the queue is already full
        if(distance<RADIUS && cab.isAvailable() && maxNearestCabs.size()<=maxCabs){
            //if just full then see if the farthest can be replaced
            if(maxNearestCabs.size() == maxCabs && distance < maxNearestCabs.peek().getDistanceFromUser()){
                //pop out the highest amongst the distances from nearest
                //and out it in remaining
                KCCab ejected = maxNearestCabs.poll();
                remainingCabs.add(ejected);

                //now add the new cab to the nearest list
                maxNearestCabs.add(cab);
                return true;
            } else if(maxNearestCabs.size() < maxCabs) {
                //there is place so add it.
                maxNearestCabs.add(cab);
                return true;
            }
            return false;
        }
        return false;
    }

    /**
     * Gets nearest cabs within 1km of the current user’s location.
     * These must be the *nearest possible* @maxCabs in the 1km area.
     * @return An unordered list of the nearest cabs.
     */
    public Cab[] getNearestCabs() {
        Cab [] c= new Cab[maxNearestCabs.size()];
        Iterator<KCCab> iterator = maxNearestCabs.iterator();
        for(int i=0; i<maxNearestCabs.size();i++){
            KCCab cab = iterator.next();
            c[i] = cab;
        }
        return c;
    }

    /**
     * Asynchronous Callback per CabStatusListener. Called when the position of a cab has changed.
     */
    public void onCabPositionChanged(Cab cab) {
        //get newly updated cab and balance the heaps
        KCCab kcCab = new KCCab(cab, cabApp.getUserPosition());
        balanceHeaps(kcCab);
    }

    private void balanceHeaps (KCCab kcCab) {
        //compare with the new instance and remove the old copy with old position
        if(!maxNearestCabs.remove(kcCab)){
            remainingCabs.remove(kcCab);
        }

        //check to see if the nearest possible list is full
        if(maxNearestCabs.size()<maxCabs){
            //if nor, we should try to fill it.
            //check if cab is nearby now. try to insert in nearest list.
            if(!insertInNearestQueue(kcCab)){
                //it is still far away.
                remainingCabs.add(kcCab);
                //nearest list is still not full
                //we should try once to look at if the nearest one in remaining can be inserted.
                if(insertInNearestQueue(remainingCabs.peek())){
                    remainingCabs.poll();
                }
            }
        } else {
            //cab was in remaining
            //check if it is nearby now and insert it.
            if(!insertInNearestQueue(kcCab)){
                //it is far now.
                remainingCabs.add(kcCab);
            }
        }
    }

    /**
     * Asynchronous Callback per CabStatusListener (see below). Called when a cab’s availability changes.
     * @cab The cab whose availability has changed
     * @isAvailable true if the cab is now available, false otherwise
     */
    public void onCabAvailabilityChanged(Cab cab, boolean isAvailable) {
        KCCab kcCab = new KCCab(cab, cabApp.getUserPosition());
        kcCab.changeAvailability(isAvailable);
        balanceHeaps(kcCab);
    }
}
