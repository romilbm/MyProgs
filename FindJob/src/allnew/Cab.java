package allnew;


/**
 * Amazon interview coding exercise on 1/15/14.
 */
interface Cab {
    /**
     * Unique identifier of a cab.
     */
    int getID();

    /**
     * Gets the current position of the cab
     */
    Position getCabPosition();

    /**
     * Returns whether or not the cab is available
     */
    boolean isAvailable();
}


