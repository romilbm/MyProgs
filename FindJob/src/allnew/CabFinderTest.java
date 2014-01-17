package allnew;

import org.junit.Before;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.assertEquals;

/**
 * Created by romil on 1/16/14.
 */
public class CabFinderTest{
    List<KCCab> cabList;
    KCCabApp kcCabApp;

    @Before
    public void setup(){
        cabList = new ArrayList<KCCab>();
        cabList.add(new KCCab(1, true, new Position(1,1)));
        cabList.add(new KCCab(2, true, new Position(50,50)));
        cabList.add(new KCCab(3, true, new Position(35,35)));
        cabList.add(new KCCab(4, false, new Position(2,2)));
        cabList.add(new KCCab(5, false, new Position(33,33)));
        cabList.add(new KCCab(6, false, new Position(51,51)));
        cabList.add(new KCCab(7, true, new Position(32,32)));
        cabList.add(new KCCab(8, true, new Position(3,3)));

        kcCabApp = new KCCabApp(cabList, new Position(0,0));
    }

    @Test
    public void testNormalCase(){
        CabFinder myCabFinder = new CabFinder();
        myCabFinder.initialize(kcCabApp,3);

        Set<KCCab> expectedCabs = new HashSet<KCCab>();
        expectedCabs.add(new KCCab(1,true,new Position(1,1)));
        expectedCabs.add(new KCCab(8,true,new Position(3,3)));
        expectedCabs.add(new KCCab(7,true,new Position(32,32)));

        Cab [] nearestCabs = myCabFinder.getNearestCabs();

        Set<Cab> actualCabs = new HashSet<Cab>();
        Collections.addAll(actualCabs, nearestCabs);

        assertEquals(expectedCabs, actualCabs);
    }

    @Test
    public void testCabNearGoesFar(){
        CabFinder myCabFinder = new CabFinder();
        myCabFinder.initialize(kcCabApp,3);

        myCabFinder.onCabPositionChanged(new KCCab(1, true, new Position(52,52)));

        Set<KCCab> expectedCabs = new HashSet<KCCab>();
        expectedCabs.add(new KCCab(3,true,new Position(35,35)));
        expectedCabs.add(new KCCab(8,true,new Position(3,3)));
        expectedCabs.add(new KCCab(7,true,new Position(32,32)));

        Cab [] nearestCabs = myCabFinder.getNearestCabs();

        Set<Cab> actualCabs = new HashSet<Cab>();
        Collections.addAll(actualCabs, nearestCabs);

        assertEquals(expectedCabs, actualCabs);
    }

    @Test
    public void testNearUnavailableIsAvailable(){
        CabFinder myCabFinder = new CabFinder();
        myCabFinder.initialize(kcCabApp,3);

        myCabFinder.onCabAvailabilityChanged(new KCCab(4, false, new Position(2,2)), true);

        Set<KCCab> expectedCabs = new HashSet<KCCab>();

        expectedCabs.add(new KCCab(1,true,new Position(1,1)));
        expectedCabs.add(new KCCab(8,true,new Position(3,3)));
        expectedCabs.add(new KCCab(4,true,new Position(2,2)));

        Cab [] nearestCabs = myCabFinder.getNearestCabs();

        Set<Cab> actualCabs = new HashSet<Cab>();
        Collections.addAll(actualCabs,nearestCabs);

        assertEquals(expectedCabs, actualCabs);
    }
}