package com.graphhopper.android;

import android.util.Log;

import com.graphhopper.routing.util.FlagEncoder;
import com.graphhopper.routing.weighting.AbstractWeighting;
import com.graphhopper.routing.weighting.FastestWeighting;
import com.graphhopper.util.EdgeIteratorState;
import com.graphhopper.util.PMap;

/**
 * Created by David Prilutsky on 10/15/2017.
 */

public class CustomWeighting extends FastestWeighting{

    public CustomWeighting(FlagEncoder encoder, PMap map) {
        super(encoder, map);
    }

    @Override
    public double calcWeight(EdgeIteratorState edge, boolean reverse, int prevOrNextEdgeId) {
        double dan = flagEncoder.getDouble(edge.getFlags(), DangerFlagEncoder.DANGER_KEY);
        Log.d("LOGGING THE DANGER", "!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!" + Double.toString(dan) + "###############################");
        return super.calcWeight(edge, reverse, prevOrNextEdgeId);
    }

}
