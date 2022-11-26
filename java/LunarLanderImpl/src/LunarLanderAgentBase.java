import java.util.Arrays;

public class LunarLanderAgentBase {
    // The resolution of the observation space
    // The four variables of the observation space, from left to right:
    //   0: X component of the vector pointing to the middle of the platform from the lander
    //   1: Y component of the vector pointing to the middle of the platform from the lander
    //   2: X component of the velocity vector of the lander
    //   3: Y component of the velocity vector of the lander
    static final int[] OBSERVATION_SPACE_RESOLUTION = {181, 36, 15, 15}; // TODO

    final double[][] observationSpace;
    double[][][][][] qTable;
    final int[] envActionSpace;
    private final int nIterations;

    double epsilon = 1.0;
    int iteration = 0;
    boolean test = false;

    // your variables here
    // ...
    double[][][][][] bestTable;
    double bestReward = -200;
    double lastReward = -200;

    double alpha = 0.1;
    double gamma = 0.6;
    int epsilon_step = 100;
    double epsilon_decay = 0.9;
    int save_interval = 1000;

    int epoch = 0;

    public LunarLanderAgentBase(double[][] observationSpace, int[] actionSpace, int nIterations) {
        this.observationSpace = observationSpace;
        this.qTable =
                new double[OBSERVATION_SPACE_RESOLUTION[0]]
                        [OBSERVATION_SPACE_RESOLUTION[1]]
                        [OBSERVATION_SPACE_RESOLUTION[2]]
                        [OBSERVATION_SPACE_RESOLUTION[3]]
                        [actionSpace.length];
        this.envActionSpace = actionSpace;
        this.nIterations = nIterations;
    }

    public static int[] quantizeState(double[][] observationSpace, double[] state) {
        int[] posXQuantile = new int[]{
                -300,-290,-280,-270,-260,-250,-240,-230,-220,-210,-200,
                -195,-190,-185,-180,-175,-170,-165,-160,-155,-150,
                -145,-140,-135,-130,-125,-120,-115,-110,-105,-100,
                -98,-96,-94,-92,-90,-88,-86,-84,-82,-80,-78,-76,-74,
                -72,-70,-68,-66,-64,-62,-60,-58,-56,-54,-52,-50,-48,
                -46,-44,-42,-40,-38,-36,-34,-32,-30,-28,-26,-24,-22,
                -20,-19,-18,-17,-16,-15,-14,-13,-12,-11,-10,-9,-8,-7,
                -6,-5,-4,-3,-2,-1,0,
                1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,
                22,24,26,28,30,32,34,36,38,40,42,44,46,48,50,52,54,
                56,58,60,62,64,66,68,70,72,74,76,78,80,82,84,86,88,
                90,92,94,96,98,100,105,110,115,120,125,130,135,140,
                145,150,155,160,165,170,175,180,185,190,195,200,
                210,220,230,240,250,260,270,280,290,300
        };

        int[] posYQuantile = new int[] {
                0,1,2,3,4,6,8,10,15,20,25,30,35,40,45,50,55,60,
                65,70,75,80,85,90,95,100,110,120,130,140,150,
                160,170,180,190,200
        };

        int[] speedQuantile = new int[] {
                -7,-6,-5,-4,-3,-2,-1,0,1,2,3,4,5,6,7
        };

        int quantizeLevel = quantizeLevel(state[0],0);
        int posX = Arrays.binarySearch(posXQuantile, (int) ((Math.round(state[0]/quantizeLevel))*quantizeLevel));
        //System.out.println("Number: " + state[0] + "\tQuantized: " + (int) ((Math.round(state[0]/quantizeLevel))*quantizeLevel) + "\tIndex: " + Arrays.binarySearch(posXQuantile, (int) ((Math.round(state[0]/quantizeLevel))*quantizeLevel))+ "\tLevel: "+ quantizeLevel);
        quantizeLevel = quantizeLevel(state[1],1);
        int posY = Arrays.binarySearch(posYQuantile,(int) ((Math.round(state[1]/quantizeLevel))*quantizeLevel));
        int speedX = Arrays.binarySearch(speedQuantile, (int)Math.round(state[2]));
        int speedY = Arrays.binarySearch(speedQuantile, (int)Math.round(state[3]));

        return new int[] {posX,posY,speedX,speedY};
    }

    private static int quantizeLevel(double value, int type) {
        //0: posX 1: posY 3: speedX
        switch (type) {
            case 0:     //posX
            {
                double absValue = Math.abs(value);
                if(absValue <= 20) return 1;
                else if(absValue <= 100)  return 2;
                else if(absValue <= 200)   return 5;
                else return 10;
            }
            case 1:     //posY
            {
                if(value <= 4) return 1;
                else if(value <= 10) return 2;
                else if(value <= 100)  return 5;
                else return 10;
            }
        }
        return 0;
    }

    public void epochEnd(double epochRewardSum) {
        return; // TODO
    }

    public void learn(double[] oldState, int action, double[] newState, double reward) {

        return; // TODO
    }

    public void trainEnd() {
        // ... TODO
       // qTable = null; // TODO
        test = true;
    }
}
