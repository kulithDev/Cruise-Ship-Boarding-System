public class Cabin {

    private Passenger[] passengerList = new Passenger[3];

    public Cabin(){

    }

    public Passenger[] getPassengerList() {
        return passengerList;
    }

    public boolean isFull() {

        boolean full = true;
        for (int i = 0; i < passengerList.length; i++) {

            if (passengerList[i] == null) {

                full = false;
                break;
            }
        }
        return full;
    }

    public void addToList(Passenger new_guy){
        for (int i = 0; i < passengerList.length; i++) {

            if (passengerList[i] == null) {

                passengerList[i] = new_guy;
                break;
            }
        }
    }

    public void print_passengers() {

        for(int i=0; i < passengerList.length; i++) {
            if (passengerList[i] != null) {
                System.out.println(passengerList[i].getFirstName());

            }
        }
    }

    public boolean isEmpty(){
        boolean empty = true;
        for (int i = 0; i < passengerList.length; i++) {

            if (passengerList[i] != null) {

                empty = false;
                break;
            }
        }
        return empty;

    }

    public boolean check(String fname){

        boolean state = false;
        for (int i = 0; i < passengerList.length; i++) {
            if (passengerList[i] != null){
                if (fname.equals(passengerList[i].getFirstName())){
                    state = true;
                    break;
                }
            }

        }
        return state;
    }

    public void removepassenger(String fname){

        for (int i = 0; i < passengerList.length; i++) {
            if (passengerList[i] != null){
                if (passengerList[i].getFirstName().equals(fname)){
                    passengerList[i] = null;
                }
            }
        }
    }

    public int getPassengerListSize(){

        int count = 0;
        for (int i = 0; i < passengerList.length; i++) {
            if (passengerList[i] != null){
                count += 1;
            }
        }
        return count;
    }

}
