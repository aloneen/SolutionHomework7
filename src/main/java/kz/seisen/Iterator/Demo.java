package kz.seisen.Iterator;

import java.util.HashSet;
import java.util.Set;



public class Demo {
    public static void main(String[] args) {
        // build one season
        Season s = new Season();
        for (int i = 1; i <= 5; i++)
            s.addEpisode(new Episode("Ep" + i, 300 + i*10));

        System.out.println("Normal:");
        EpisodeIterator it = s.normalIterator();
        while (it.hasNext())
            System.out.println("  " + it.next().getTitle());

        System.out.println("\nReverse:");
        it = s.reverseIterator();
        while (it.hasNext())
            System.out.println("  " + it.next().getTitle());

        System.out.println("\nShuffle:");
        it = s.shuffleIterator(123);
        while (it.hasNext())
            System.out.println("  " + it.next().getTitle());

        // binge across two seasons
        Series series = new Series();
        series.addSeason(s);
        Season s2 = new Season();
        for (int i = 6; i <= 8; i++)
            s2.addEpisode(new Episode("Ep" + i, 300 + i*10));
        series.addSeason(s2);

        System.out.println("\nBinge all:");
        it = new BingeIterator(series);
        while (it.hasNext())
            System.out.println("  " + it.next().getTitle());

        // skip intro demo
        System.out.println("\nSkip Intro (10s):");
        SkipIntroIterator skipIt =
                new SkipIntroIterator(series.getSeasons().get(0).normalIterator(), 10);
        while (skipIt.hasNext())
            skipIt.next().play();

        // watch-history demo
        System.out.println("\nUnseen episodes:");
        Set<String> seen = new HashSet<>();
        seen.add("Ep1"); // pretend watched
        WatchHistoryIterator histIt =
                new WatchHistoryIterator(new BingeIterator(series), seen);
        while (histIt.hasNext())
            System.out.println("  Will watch: " + histIt.next().getTitle());
    }
}

