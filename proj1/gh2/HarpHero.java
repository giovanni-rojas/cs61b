package gh2;
import edu.princeton.cs.algs4.StdAudio;
import edu.princeton.cs.algs4.StdDraw;
import deque.ArrayDeque;

/**
 * A client that uses the synthesizer package to replicate a plucked harp string sound
 */
public class HarpHero {
    public static final String keyboard = "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";

    public static final double CONCERT_A = 440.0;
    public static final double CONCERT_C = CONCERT_A * Math.pow(2, 3.0 / 12.0);

    public static void main(String[] args) {
        //char[] keys = keyboard.toCharArray();

        /* create full 37 keys from 110Hz to 880Hz */
        ArrayDeque<HarpString> strings = new ArrayDeque<>();

        for (int i = 0; i < keyboard.length(); i++) {
            double exponent = (i - 24) / 12.0;
            double frequency = 440.0 * Math.pow(2, exponent);
            strings.addLast(new HarpString(frequency));
        }

        while (true) {

            /* check if the user has typed a key; if so, process it */
            if (StdDraw.hasNextKeyTyped()) {
                char key = StdDraw.nextKeyTyped();
                if(keyboard.indexOf(key) == -1) {
                    continue;
                }
                strings.get(keyboard.indexOf(key)).pluck();
            }

            /* compute the superposition of samples */
            double sample = 0.0;

            for (int i = 0; i < keyboard.length(); i++) {
                sample = sample + strings.get(i).sample();
            }

            /* play the sample on standard audio */
            StdAudio.play(sample);

            /* advance the simulation of each harp string by one step */
            for (int i = 0; i < keyboard.length(); i++) {
                strings.get(i).tic();
            }
        }
    }
}

