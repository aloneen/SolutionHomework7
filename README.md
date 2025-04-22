# Solution Homework 7.

# Streaming-Service Binge Mode

## Overview
Implements a uniform `Iterator` for TV series episodes across multiple storage formats, supporting:
- **Normal**, **Reverse**, **Shuffle** traversal
- **Binge** mode across seasons
- **Skip Intro** wrapper
- **Watch-History** filter
- Performance test for 10 000 episodes

## Why Iterator?
- **Encapsulation**: Hides internal storage (lists, lazy loaders, etc.).
- **Flexibility**: Easily add traversal orders (shuffle, reverse) without changing client code.
- **Compose**: Chain or wrap iterators (binge, skip intro, history filter).
- **Uniform API**: Client code uses only `hasNext()/next()`.

## Usage
1. Build `Season` objects and add `Episode`s.
2. Obtain desired iterator:
   ```java
   Season s = new Season();
   EpisodeIterator it = s.shuffleIterator(seed);
