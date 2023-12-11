package adventofcode23.lib;


public class Range {

    private long destinationRangeStart;
    private long sourceRangeStart;
    private long rangeLength;
    public Range (
        final long destinationRangeStart,
        final long sourceRangeStart,
        final long rangeLength
    ) {

        if (rangeLength == 0) {
            throw new IllegalArgumentException();
        }
        this.destinationRangeStart = destinationRangeStart;
        this.sourceRangeStart = sourceRangeStart;
        this.rangeLength = rangeLength;
    }
    public Interval getSourceInterval() {
        return new Interval(sourceRangeStart, sourceRangeStart + rangeLength - 1);
    }

    public Interval getDestinationInterval() {
        return new Interval(destinationRangeStart, destinationRangeStart + rangeLength - 1);
    }

    /**
     * Returns the delta between destination snd source. Negative values indicate
     * destination is less than source, positive indicate destination is greater than source.
     * @return
     */
    public long getDestinationDelta() {
        return destinationRangeStart - sourceRangeStart;
    }

    public long getDestinationeValue(long value) {
        if (getSourceInterval().contains(value)) {
            return getDestinationDelta() + value;
        }
        throw new RuntimeException(getSourceInterval() + " does not contain " + value);
    }

    public String toString() {
        return  destinationRangeStart + " " + sourceRangeStart + " " +  rangeLength;
    }

    public Interval toDestinationInterval(final Interval interval) {
        if (interval == null) {
            return null;
        }
        return Interval.make(interval.getStart() + getDestinationDelta(), interval.getEnd() + getDestinationDelta());
    }
}