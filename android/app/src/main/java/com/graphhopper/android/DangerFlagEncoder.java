package com.graphhopper.android;

import android.util.Log;

import com.graphhopper.reader.ReaderWay;
import com.graphhopper.routing.util.CarFlagEncoder;
import com.graphhopper.routing.util.EncodedValue;
import com.graphhopper.util.EdgeIteratorState;

/**
 * Created by David Prilutsky on 10/18/2017.
 */

public class DangerFlagEncoder extends CarFlagEncoder {
    public static final int DANGER_KEY = 113;

    private EncodedValue dangerEncoder;

    @Override
    public int defineWayBits(int index, int shift) {
        // first two bits are reserved for route handling in superclass
        shift = super.defineWayBits(index, shift);

        //EncodedValue(String name, int shift, int bits, double factor, long defaultValue, int maxValue)
        dangerEncoder = new EncodedValue("Danger", shift, 4, 1, 0, 10);
        shift += dangerEncoder.getBits();

        Log.d("IN DANGER", "!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!DEFINE WAY BITS!!!!!!!!!!********************************************");
        return shift;
    }

    @Override
    public long handleWayTags(ReaderWay way, long allowed, long priorityFromRelation) {
        long flags = super.handleWayTags(way, allowed, priorityFromRelation);
        //Add the danger level. Will update this later!
        flags = dangerEncoder.setValue(flags, 5);
        Log.d("IN DANGER", "!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!HANDLE WAY TAGS!!!!!!!!!!********************************************");
        return flags;
    }

    @Override
    public double getDouble(long flags, int key) {
        Log.d("IN DANGER", "!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!GET DOUBLE!!!!!!!!!!********************************************");
        switch (key) {
            case DangerFlagEncoder.DANGER_KEY:
                //not sure why dividing by 10
                Log.d("IN DANGER", "!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!GIVING DANGER!!!!!!!!!********************************************");
                return (double) dangerEncoder.getValue(flags);
            default:
                return super.getDouble(flags, key);
        }
    }

    @Override
    public void applyWayTags(ReaderWay way, EdgeIteratorState edge) {
        super.applyWayTags(way, edge);
        edge.setFlags(this.dangerEncoder.setValue(edge.getFlags(), 5));
        Log.d("IN DANGER", "!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!APPLY WAY TAGS!!!!!!!!!!********************************************");
    }
    @Override
    public String toString() {
        Log.d("IN DANGER", "!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!TO STRING!!!!!!!!!!********************************************");
        return "car";
    }

    @Override
    public int getVersion() {
        Log.d("IN DANGER", "!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!VERSION!!!!!!!!!!********************************************");
        return 1;
    }

}
