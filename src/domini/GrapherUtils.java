package domini;

/**
 * This class contains helper methods for {@link Grapher}.
 * @author Oriol Munoz Princep
 */
public abstract class GrapherUtils {

    private static final double JWINK_MINIMUM_DISTANCE = 0.75d; //it never should be lower than this

    /**
     * Returns a boolean depending on whether two String objects are similar or not
     * according to containment, length and Jaro-Winkler's distance.
     * Both String are treated as case insensitive.
     * @param s1 first String, not null
     * @param s2 second String, not null
     * @return whether or not s1 and s2 are similar
     * @see domini.Grapher Grapher
     */
    public static boolean areSimilar(String s1, String s2) {
        if (s1.equals(s2)) return true;
        //Small Strings are problematic
        if (s1.length() < 4 || s2.length() < 4) return false;
        //If one contains the other, they probably have the same root
        //They're wikipedia articles, so odds are they are similar
        //Otherwise, do jaro-winkler's distance
        String cis1 = s1.toLowerCase();
        String cis2 = s2.toLowerCase();
        return cis1.contains(s2) || cis2.contains(s1) || JaroWinklerDistance(cis1, cis2) >= JWINK_MINIMUM_DISTANCE;
    }

    /**
     * Helper methods that calculates the Jaro-Winkler distance of two String.
     * @param s1 , not null, lowercase
     * @param s2 , not null, lowecase
     * @return The Jaro-Winkler Distance of s1 and s2, ranging between 0 (no similarity at all) and 1 (equal).
     */
    private static double JaroWinklerDistance(String s1, String s2) {
        if (s1.length() > s2.length()) {
            String tmp = s2;
            s2 = s1;
            s1 = tmp;

        }

        int maxDistance = s2.length()/2;
        int coincidences = 0;
        int transpositions = 0;
        int prevpos = -1;
        for (int i = 0; i < s1.length(); i++) {
            char c = s1.charAt(i);

            for (int j = Math.max(0, i - maxDistance);
                     j < Math.min(s2.length(), i + maxDistance);
                     j++) {
                if (c == s2.charAt(j)) {
                    coincidences++;
                    if (prevpos != -1 && j < prevpos) ++transpositions;
                    prevpos = j;
                    break;
                }
            }
        }

        //No similarity whatsoever
        if (coincidences == 0) return 0.0d;

        //Jaro score (formula)
        double len1 = (double) s1.length();
        double len2 = (double) s2.length();
        double cd = coincidences;
        double score = ((coincidences/len1) + (coincidences/len2) + (coincidences-transpositions)/cd)/3.0d;

        int l = 0;
        int last = 4;
        while (l < last && s1.charAt(l) == s2.charAt(l)) l++;

        //Jaro-Winkler score (formula)
        score = score + (0.1*l * (1 - score));

        return score;
    }
}
