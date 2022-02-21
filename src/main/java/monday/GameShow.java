package monday;

import java.util.Random;

public class GameShow {

    // takes number of simulations as input and prints the outcomes of stay and switch.
    // if the number of simulations is not passed, it's defaulted to 1000 simulations.
    // if a non number is passed as argument, then corresponding error message is shown to the user.
    public static void main(String[] args) {
        int simulations = 1000;
        if (args.length == 1) {
            try {
                simulations = Integer.parseInt(args[0]);
            } catch (NumberFormatException e) {
                System.out.println("Enter valid number of simulations");
            }
        }else{
            System.out.println("Too many parameters");
        }
        //prepare allocations of 2 goats and 1 auto-mobile
        boolean[][] allocations = getRandomAllocations(simulations);
        int stayCount = 0;
        int switchCount = 0;
        Random r = new Random();
        for (boolean[] allocation : allocations) {
            // when contestant makes a pick, the host opens a door which has goat behind.
            // so technically there are only 2 doors left now, one has auto-mobile and one has goat.
            // so the contestant's pick is either correct or incorrect based on whether they chose
            // to stay or switch, there are no other scenarios.
            int contestantPick = r.nextInt(3);
            if (allocation[contestantPick]) {
                stayCount++;
            } else {
                switchCount++;
            }
        }
        System.out.printf("Stay : count %d = %.2f", stayCount, (float) stayCount*100 / simulations);
        System.out.println();
        System.out.printf("Switch : count %d = %.2f", switchCount, (float) switchCount*100 / simulations);
    }

    /*
     * Returns random allocations for the iterations provided.
     * To prepare allocations, a door is randomly picked and automobile is assigned to it which is represented by true,
     * automatically other 2 doors are assigned with goats, which is represented by false.
     * */
    private static boolean[][] getRandomAllocations(int simulations) {
        boolean[][] allocations = new boolean[simulations][3];
        int[] randomDoorPicks = new Random().ints(simulations, 0, 3).toArray();
        for (int i = 0; i < simulations; i++) {
            int pickedDoor = randomDoorPicks[i];
            allocations[i][pickedDoor] = true;
        }
        return allocations;
    }
}
