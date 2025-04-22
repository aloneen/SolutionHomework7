package kz.seisen.Iterator;

import java.util.*;
public class ReverseSeasonIterator implements EpisodeIterator {
    private final List<Episode> episodes;
    private int index;
    public ReverseSeasonIterator(Season season) {
        this.episodes = season.getEpisodes();
        this.index = episodes.size() - 1;
    }
    @Override public boolean hasNext() {
        return index >= 0;
    }
    @Override public Episode next() {
        return episodes.get(index--);
    }
}
