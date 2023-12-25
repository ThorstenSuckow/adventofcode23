package adventofcode23.lib;

public class Interval implements Cloneable {

    private long start;

    private long end;
    public Interval(final long start, final long end) {
        if (start > end) {
            throw new IllegalArgumentException(start + " must be less than or equal to " + end);
        }
        this.start = start;
        this.end = end;
    }

    public static Interval make(long start, long end) {
        return new Interval(start, end);
    }

    public Interval intersectionWith(Interval target) {

        if (!intersects(target)) {
            //System.out.println(this+ " does not intersect with " + target);
            return null;
        }

        if (this.contains(target)) {
            return target.clone();
        }

        if (target.contains(this)) {
            return this.clone();
        }

        if (start < target.getStart()) {
            return Interval.make(target.getStart(), Math.min(end, target.getEnd()));
        }

        if (end > target.getEnd()) {
            return Interval.make(Math.max(start, target.getStart()), target.getEnd());
        }

        throw new RuntimeException("intersection not found for A:" + this + ", B:" + target);

    }

    public Interval[] complementTo(Interval target) {

        //   A       B
        // [1, 4], [1, 5] -> null
        if (start >= target.start && end <= target.end) {
            return null;
        }

        //   A       B
        // [1, 4], [4, 5] -> [1, 3]
        if (start < target.getStart() && end <= target.getStart()) {
            return new Interval[]{
                Interval.make(start, end == target.getStart() ? end - 1 : end)
            };
        }

        //   A       B
        // [1, 8], [3, 5] -> [1, 2]
        if (start < target.getStart() && end > target.getEnd()) {
            return new Interval[]{
                Interval.make(start, target.getStart() - 1),
                Interval.make(target.end + 1, end)
            };
        }

        //   A       B
        // [4, 8], [3, 5] -> [6, 8]
        if (start >= target.getStart() && start <= target.getEnd() && end > target.getEnd()) {
            return new Interval[]{
                Interval.make(target.getEnd() + 1, end)
            };
        }

        //   A       B
        // [5, 8], [3, 5] -> [6, 8]
        if (start >= target.getEnd() && end > target.getEnd()) {
            return new Interval[]{
                Interval.make(target.getEnd() + 1, end)
            };
        }

        //   A       B
        // [2, 4], [3, 5] -> [6, 8]
        if (end >= target.getStart() && end <= target.getEnd() && start < target.getStart()) {
            return new Interval[]{
                Interval.make(start, target.getStart() - 1)
            };
        }


        throw new RuntimeException("complement not found for A:" + this + ", B:" + target);
    }


    public boolean equals(Object b) {

        if (!(b instanceof Interval target)) {
            return false;
        }

        if (target.getStart() == start && target.getEnd() == end) {
            return true;
        }

        return false;

    }


    /**
     * Returns true if this Interval contains the specified interval.
     * @param b
     * @return
     */
    public boolean contains(Interval b) {
        return start <= b.getStart() && end >= b.getEnd();
    }

    public boolean intersects(final Interval b) {

        if (
            (end >= b.start && end <= b.end)
            || (start >= b.start && start <= b.end)
            || (b.end >= start && b.end <= end)
            || (b.start >= start && b.start <= end)) {
            return true;
        }

        return false;
    }


    public long getEnd() {
        return end;
    }

    public long getStart() {
        return start;
    }

    public boolean contains(long value) {
        return start <= value && end >= value;
    }

    public String toString() {
        return "["+ start + ", " + end + "]";
    }

    public Interval clone() {
        return new Interval(start, end);
    }

}
