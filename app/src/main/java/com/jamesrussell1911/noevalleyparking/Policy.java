package com.jamesrussell1911.noevalleyparking;

/**
 * Defines a street sweeping policy
 */
public class Policy {
    public final CompassDir dir;
    public final int sweep_day_of_week;
    public final Frequency frequency;

    /**
     * @param dir - What Compass Direction within the block it applies to
     * @param sweep_day_of_week - What day of the week the sweeping occurs on
     * @param frequency - How often the sweeping happens
     */
    public Policy(
            CompassDir dir,
            int sweep_day_of_week,
            Frequency frequency
    ) {
        this.dir = dir;
        this.sweep_day_of_week = sweep_day_of_week;
        this.frequency = frequency;
    }

    public boolean isPermitted(int day_of_week, boolean is_odd_week) {
        if (day_of_week == sweep_day_of_week &&
            (frequency == Frequency.WEEKLY || (is_odd_week && frequency == Frequency.ODD_WEEKS))
                ) {
            return false;
        }
        return true;
    }
}