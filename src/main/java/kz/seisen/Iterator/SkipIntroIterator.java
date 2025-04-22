package kz.seisen.Iterator;

import java.util.Iterator;

public class SkipIntroIterator implements Iterator<EpisodeView> {
    private final EpisodeIterator inner;
    private final int skipSec;
    public SkipIntroIterator(EpisodeIterator inner, int skipSec) {
        this.inner = inner;
        this.skipSec = skipSec;
    }
    @Override public boolean hasNext() {
        return inner.hasNext();
    }
    @Override public EpisodeView next() {
        Episode ep = inner.next();
        return new EpisodeView(ep, skipSec);
    }
}
