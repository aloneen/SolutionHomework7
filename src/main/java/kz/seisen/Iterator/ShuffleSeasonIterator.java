package kz.seisen.Iterator;

import java.util.*;


public class ShuffleSeasonIterator implements EpisodeIterator {
    private final List<Episode> shuffled;
    private int index = 0;

    public ShuffleSeasonIterator(Season season, long seed) {
        shuffled = new ArrayList<>(season.getEpisodes());
        Collections.shuffle(shuffled, new Random(seed));
    }
    @Override public boolean hasNext() {
        return index < shuffled.size();
    }
    @Override public Episode next() {
        return shuffled.get(index++);
    }
}
