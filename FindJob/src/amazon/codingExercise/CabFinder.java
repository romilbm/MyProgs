package amazon.codingExercise;

import java.util.*;

/**
 * Created by romil on 1/15/14.
 */
class CabFinder implements CabStatusListener {
    private CabApp cabApp;

    //min heap - root element will be the farthest to user within the RADIUS
    PriorityQueue<KCCab> remainingCabs;

    //max heap - root element will be nearest. May or may not be available.
    PriorityQueue<KCCab> maxNearestCabs;

    //assume 1km = 50units!
    public static final double RADIUS = 50;

    //the max number of nearest avaiable cabs the user wants to see.
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

        //reverse ordering
        //this comparator will be used to determine the nearest possible cabs
        //they will already be filtered by availability.
        Comparator<KCCab> c = new Comparator<KCCab>() {
            @Override
            public int compare(KCCab o1, KCCab o2) {
                if(o1.getDistanceFromUser() < o2.getDistanceFromUser()){ return 1; }
                else if (o1.getDistanceFromUser() == o2.getDistanceFromUser()){ return 0; }
                else { return -1; }
            }
        };

        maxNearestCabs = new PriorityQueue<KCCab>(maxCabs,c);
        remainingCabs = new PriorityQueue<KCCab>();

        Iterator<Cab> cabIterator = cabApp.getCabs();
        while (cabIterator.hasNext()){
            KCCab cab = new KCCab(cabIterator.next(),cabApp.getUserPosition());
            if(!insertInNearestQueue(cab)){
                remainingCabs.add(cab);
            }
        }
    }

    /**
     * Depending upon its distance from the user, its position in the RADIUS and availability
     * the cab can be put in the list of nearest available cabs or remaining cabs.
     * @param cab A cab to be inserted in the nearest available list.
     * @return true if the cab has been added to the nearest available list false otherwise.
     */
    private boolean insertInNearestQueue(KCCab cab) {
        double distance = cab.getDistanceFromUser();
        if(distance < RADIUS && maxNearestCabs.size() <= maxCabs && cab.isAvailable()){
            if(maxNearestCabs.size()==maxCabs
                    && distance<maxNearestCabs.peek().getDistanceFromUser()){
                //pop out the highest amongst the distances from nearest
                //and put it in remaining
                KCCab ejected = maxNearestCabs.poll();
                remainingCabs.add(ejected);

                //add the new cab to the list of nearest available.
                maxNearestCabs.add(cab);
                return true;
            } else if(maxNearestCabs.size()< maxCabs){
                //add the lower one to the nearest
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
        Cab [] c = new Cab[maxNearestCabs.size()];
        Iterator<KCCab> iterator = maxNearestCabs.iterator();
        for(int i=0; i<maxNearestCabs.size();i++){
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

    /**
     * Takes a newly updated cab. newly updated means its availability or position has changed.
     * Depending on whether this new cab update makes it one of the nearest possible cabs to the user,
     * this method puts it in the list of nearest possible or remaining.
     * @param kcCab A newly updated cab.
     */
    private void balanceHeaps(KCCab kcCab){
        //will compare with the new instance and remove the old copy with old position or availability.
        if(!maxNearestCabs.remove(kcCab)){
            remainingCabs.remove(kcCab);
        }

        //check to see if there is nearest possible list is full
        if(maxNearestCabs.size()<maxCabs){
            //if not, we should try to fill it.
            //check if cab is near now. try to insert in maxNearestCabs
            if(!insertInNearestQueue(kcCab)){
                //it is far now
                remainingCabs.add(kcCab);
                //list of nearest Cabs is still not full.
                //one cab was updated and added to remaining.
                //try to insert the 1st cab in the remaining into the nearest available.
                if(insertInNearestQueue(remainingCabs.peek())){
                    remainingCabs.poll();
                }
            }
        } else {
            //cab was in remaining before
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
        balanceHeaps(kcCab);
    }

    public static void main(String args []){
        List<KCCab> cabList = new ArrayList<KCCab>();

        for(int i=1; i<=40; i++){
            Random generator1 = new Random();
            int x = generator1.nextInt(50);
            Random generator2 = new Random();
            int y = generator2.nextInt(50);
            Random generator3 = new Random();
            int a = generator3.nextInt(3);
            boolean available = a==0?false:true;
            cabList.add(new KCCab(i,available,new Position(x,y)));
        }
        //2 cabs which are near and available
        cabList.get(0).moveTo(new Position(1,1));
        cabList.get(0).changeAvailability(true);
        cabList.get(1).moveTo(new Position(2,2));
        cabList.get(1).changeAvailability(true);
        //2 cabs which are far and available
        cabList.get(2).moveTo(new Position(50,50));
        cabList.get(2).changeAvailability(true);
        cabList.get(3).moveTo(new Position(51,51));
        cabList.get(3).changeAvailability(true);
        //1 cab which is near and unavailable
        cabList.get(4).moveTo(new Position(3,3));
        cabList.get(4).changeAvailability(false);
        //1 cab which is far and unavailable
        cabList.get(5).moveTo(new Position(53,53));
        cabList.get(5).changeAvailability(false);


        KCCabApp kcCabApp = new KCCabApp(cabList, new Position(0,0));
        CabFinder myCabFinder = new CabFinder();
        myCabFinder.initialize(kcCabApp,5);

        Cab [] nearestCabs1 = myCabFinder.getNearestCabs();

        getAllDistances(cabList);
        System.out.println();
        printNearestCabs(nearestCabs1);

        //a cab which was near moved and is still near is still near
        myCabFinder.onCabPositionChanged(new KCCab(1, true, new Position(3,3)));
        Cab [] nearestCabs2 = myCabFinder.getNearestCabs();
        System.out.println();
        printNearestCabs(nearestCabs2);

        //a cab which was near moved and is now far
        myCabFinder.onCabPositionChanged(new KCCab(2, true, new Position(52,52)));
        Cab [] nearestCabs3 = myCabFinder.getNearestCabs();
        System.out.println();
        printNearestCabs(nearestCabs3);

        //a cab which was far moved and is now near
        myCabFinder.onCabPositionChanged(new KCCab(3, true, new Position(1,1)));
        Cab [] nearestCabs4 = myCabFinder.getNearestCabs();
        System.out.println();
        printNearestCabs(nearestCabs4);

        //a cab which was far moved and is still far
        myCabFinder.onCabPositionChanged(new KCCab(4, true, new Position(50,50)));
        Cab [] nearestCabs5 = myCabFinder.getNearestCabs();
        System.out.println();
        printNearestCabs(nearestCabs5);

        //a cab which was near and unavailable becomes available
        myCabFinder.onCabAvailabilityChanged(new KCCab(5, false, new Position(3,3)), true);
        Cab [] nearestCabs6 = myCabFinder.getNearestCabs();
        System.out.println();
        printNearestCabs(nearestCabs6);

        //a cab which was far and unavailable becomes available
        myCabFinder.onCabAvailabilityChanged(new KCCab(5, false, new Position(53,53)), true);
        Cab [] nearestCabs7 = myCabFinder.getNearestCabs();
        System.out.println();
        printNearestCabs(nearestCabs7);

        //a cab which was near and available becomes unavailable
        myCabFinder.onCabAvailabilityChanged(new KCCab(1, true, new Position(3,3)), false);
        Cab [] nearestCabs8 = myCabFinder.getNearestCabs();
        System.out.println();
        printNearestCabs(nearestCabs8);

        //a cab which was far and available becomes unavailable
        myCabFinder.onCabAvailabilityChanged(new KCCab(2, true, new Position(52,52)), false);
        Cab [] nearestCabs9 = myCabFinder.getNearestCabs();
        System.out.println();
        printNearestCabs(nearestCabs9);

    }

    private static void printNearestCabs(Cab[] nearestCabs) {
        for(Cab c: nearestCabs){
            KCCab cab = new KCCab(c, new Position(0,0));
            System.out.println(cab.getID() + "=" + cab.getDistanceFromUser());
        }
    }

    private static void getAllDistances(List<KCCab> cabList) {
//        for(KCCab cab:cabList){
//            cab.setUserPosition(new Position(0,0));
//            System.out.println(cab.getID() + " -- "
//                    + cab.getCabPosition().x + "," + cab.getCabPosition().y + " -- "
//                    + cab.getDistanceFromUser() + " -- "
//                    + cab.isAvailable());
//        }
    }
}