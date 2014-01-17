package allnew;

/**
 * Created by romil on 1/16/14.
 */
//An implementation of Cab.
//It contains methods that make it easier to setup and test.
//It implements comparable because we want to be comparing which cabs are nearest to user.
public class KCCab implements Cab,Comparable<KCCab>{
    private final int id;
    private boolean availability;
    private Position cabPosition;
    private Position userPosition;
    private double distanceFromUser;

    //helps setup test instances of KCCab
    public KCCab(int id, boolean availability, Position cabPosition){
        this.id = id;
        this.availability = availability;
        this.cabPosition = cabPosition;
    }

    public KCCab(Cab cab, Position userPosition){
        this.id = cab.getID();
        this.availability = cab.isAvailable();
        this.cabPosition = cab.getCabPosition();
        this.userPosition = userPosition;
        this.distanceFromUser = calcDistanceFromUser();
    }

    public double getDistanceFromUser(){
        return distanceFromUser;
    }

    private double calcDistanceFromUser(){
        return Math.sqrt(
                Math.pow((cabPosition.x - userPosition.x), 2)
                        +Math.pow((cabPosition.y - userPosition.y), 2)
        );
    }
    public void moveTo(Position newPosition){
        cabPosition = newPosition;
    }

    public void changeAvailability(boolean availability){
        this.availability = availability;
    }

    public int getID(){
        return id;
    }

    public Position getCabPosition(){
        return cabPosition;
    }

    public boolean isAvailable(){
        return availability;
    }

    @Override
    public boolean equals(Object obj){
        if(obj == null){
            return false;
        }

        if(!(obj instanceof KCCab)){
            return false;
        }

        KCCab cab = (KCCab) obj;
        return (this.id == cab.getID());
    }

    @Override
    public int hashCode(){
        int code = 13;
        code = 32 * code + id;
        return code;
    }

    //normal compare for all cabs considering their availability.
    @Override
    public int compareTo(KCCab cab){
        if(this.isAvailable() == cab.isAvailable()){
            if(this.distanceFromUser < cab.getDistanceFromUser()){
                return -1;
            } else if (this.distanceFromUser == cab.getDistanceFromUser()) {
                return 0;
            } else {
                return 1;
            }
        } else {
            if(this.isAvailable()){
                return -1;
            } else {
                return 1;
            }
        }
    }
}
