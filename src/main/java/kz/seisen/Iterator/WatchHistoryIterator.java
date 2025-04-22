package kz.seisen.Iterator;

import java.util.*;

public class WatchHistoryIterator implements EpisodeIterator {
    private final EpisodeIterator inner;
    private final Set<String> seenTitles;
    private Episode nextUnseen;

    public WatchHistoryIterator(EpisodeIterator inner, Set<String> seen) {
        this.inner = inner;
        this.seenTitles = seen;
        advance();
    }

    private void advance() {
        nextUnseen = null;
        while (inner.hasNext()) {
            Episode e = inner.next();
            if (!seenTitles.contains(e.getTitle())) {
                nextUnseen = e;
                break;
            }
        }
    }

    @Override public boolean hasNext() {
        return nextUnseen != null;
    }

    @Override public Episode next() {
        Episode result = nextUnseen;
        seenTitles.add(result.getTitle());
        advance();
        return result;
    }
}
