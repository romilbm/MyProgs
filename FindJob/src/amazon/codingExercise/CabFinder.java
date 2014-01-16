package amazon.codingExercise;

import java.util.*;

/**
 * Created by romil on 1/15/14.
 */
class CabFinder implements CabStatusListener {
    private CabApp cabApp;
    PriorityQueue<KCCab> remainingCabs;
    PriorityQueue<KCCab> maxNearestCabs;
    public static final int RADIUS = 100;
    private int maxCabs;

    /**
     * Initiates CabFinder. Called only once per app startup.
     * @app An application object providing services implemented by
     *      the rest of the application.
     * @maxCabs Nearest number of cabs that can be returned to the user
     */
    public void initialize(CabApp app, int maxCabs) {
        this.cabApp = app;
        this.maxCabs = maxCabs;

        Comparator<KCCab> c = new Comparator<KCCab>() {
            @Override
            public int compare(KCCab o1, KCCab o2) {
                if(o1.getDistanceFromUser() < o2.getDistanceFromUser()){ return 1; }
                else if (o1.getDistanceFromUser() == o2.getDistanceFromUser()){ return 0; }
                else { return -1; }
            }
        };

        maxNearestCabs = new PriorityQueue<KCCab>(maxCabs,c); //max heap
        remainingCabs = new PriorityQueue<KCCab>(); //min heap

        populateCabLists();
    }

    private void populateCabLists() {
        Iterator<Cab> cabIterator = cabApp.getCabs();
        while (cabIterator.hasNext()){
            KCCab cab = new KCCab(cabIterator.next(),cabApp.getUserPosition());
            if(!insertInNearestQueue(cab)){
                remainingCabs.add(cab);
            }
        }
    }

    private boolean insertInNearestQueue(KCCab cab) {
        double distance = cab.getDistanceFromUser();
        //if(distance < RADIUS && maxNearestCabs.size() <= maxCabs && cab.isAvailable()){
        if(distance < RADIUS && maxNearestCabs.size() <= maxCabs){
            if(maxNearestCabs.size()==maxCabs
                    && distance<maxNearestCabs.peek().getDistanceFromUser()){
                maxNearestCabs.poll();
            }
            maxNearestCabs.add(cab);
            return true;
        }
        return false;
    }

    /**
     * Gets nearest cabs within 1km of the current user’s location.
     * These must be the *nearest possible* @maxCabs in the 1km area.
     * @return An unordered list of the nearest cabs.
     */
    public Cab[] getNearestCabs() {
        Cab [] c = new Cab[maxCabs];
        Iterator<KCCab> iterator = maxNearestCabs.iterator();
        for(int i=0; i<maxCabs;i++){
            KCCab cab = iterator.next();
            c[i] = cab;
        }
        return c;
    }

    /**
     * Asynchronous Callback per CabStatusListener (see below). Called when the position of a cab has changed.
     */
    public void onCabPositionChanged(Cab cab) {
        KCCab kcCab = new KCCab(cab, cabApp.getUserPosition());
        balanceHeaps(kcCab);
    }

    private void balanceHeaps(KCCab kcCab){
        //will compare with the new instance and remove the old copy with old position
        if(!maxNearestCabs.remove(kcCab)){
            remainingCabs.remove(kcCab);
        }

        if(maxNearestCabs.size()<maxCabs){
            //the cab was nearby before and was removed from maxNearCabs
            //check if it is near now also. try to insert in maxNearestCabs
            if(!insertInNearestQueue(kcCab)){
                //it is far now
                remainingCabs.add(kcCab);
                //the cab was removed from nearestCabs and not added there again.
                //so there is an empty spot
                //try to see if the 1st cab in the remaining is near enough.
                if(insertInNearestQueue(remainingCabs.peek())){
                    remainingCabs.poll();
                }
            }
        } else {
            //cab was far before and was removed from remainingCabs
            //check if it is near now. try to insert in maxNearestCabs
            if(!insertInNearestQueue(kcCab)){
                //it is far now
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

        if(maxNearestCabs.remove(kcCab)){
            maxNearestCabs.add(kcCab);
        } else {
            remainingCabs.remove(kcCab);
        }

    }

    public static void main(String args []){
        List<KCCab> cabList = new ArrayList<KCCab>();


        for(int i=1; i<=20; i++){
            Random generator1 = new Random();
            int x = generator1.nextInt(50);
            Random generator2 = new Random();
            int y = generator2.nextInt(50);
            cabList.add(new KCCab(i,true,new Position(x,y)));
        }

        getAllDistances(cabList);

        KCCabApp kcCabApp = new KCCabApp(cabList, new Position(0,0));

        CabFinder myCabFinder = new CabFinder();
        myCabFinder.initialize(kcCabApp,5);

        Cab [] nearestCabs = myCabFinder.getNearestCabs();

        System.out.println();

        for(Cab c: nearestCabs){
            KCCab cab = new KCCab(c, new Position(0,0));
            System.out.println(cab.getID() + "=" + cab.getDistanceFromUser());
        }

    }

    private static void getAllDistances(List<KCCab> cabList) {
        for(KCCab cab:cabList){
            cab.setUserPosition(new Position(0,0));
            System.out.println(cab.getID() + " -- "
                    + cab.getCabPosition().x + "," + cab.getCabPosition().y + " -- "
                    + cab.getDistanceFromUser());
        }
    }
}