package kz.seisen.Iterator;

import java.util.*;

public class Season implements Iterable<Episode> {
    private final List<Episode> episodes = new ArrayList<>();
    public void addEpisode(Episode e) { episodes.add(e); }

    // default for-each
    @Override
    public Iterator<Episode> iterator() {
        return (Iterator<Episode>) new SeasonIterator(this);
    }
    // specific iterator getters
    public EpisodeIterator normalIterator()      { return new SeasonIterator(this); }
    public EpisodeIterator reverseIterator()     { return new ReverseSeasonIterator(this); }
    public EpisodeIterator shuffleIterator(long seed) { return new ShuffleSeasonIterator(this, seed); }

    List<Episode> getEpisodes() { return episodes; }
}
