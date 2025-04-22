package kz.seisen.Iterator;

import java.util.*;

public class BingeIterator implements EpisodeIterator {
    private final Iterator<Season> seasonIter;
    private EpisodeIterator currentEpisodeIter;

    public BingeIterator(Series series) {
        this.seasonIter = series.getSeasons().iterator();
        this.currentEpisodeIter = seasonIter.hasNext()
                ? seasonIter.next().normalIterator()
                : null;
    }

    @Override public boolean hasNext() {
        while (currentEpisodeIter != null && !currentEpisodeIter.hasNext()) {
            if (!seasonIter.hasNext()) return false;
            currentEpisodeIter = seasonIter.next().normalIterator();
        }
        return currentEpisodeIter != null && currentEpisodeIter.hasNext();
    }

    @Override public Episode next() {
        return hasNext() ? currentEpisodeIter.next() : null;
    }
}