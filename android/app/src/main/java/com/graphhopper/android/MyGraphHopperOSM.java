package com.graphhopper.android;

import android.util.Log;

import com.graphhopper.GraphHopper;
import com.graphhopper.reader.osm.GraphHopperOSM;
import com.graphhopper.routing.util.DataFlagEncoder;
import com.graphhopper.routing.util.DefaultEdgeFilter;
import com.graphhopper.routing.util.FlagEncoder;
import com.graphhopper.routing.util.HintsMap;
import com.graphhopper.routing.weighting.BlockAreaWeighting;
import com.graphhopper.routing.weighting.CurvatureWeighting;
import com.graphhopper.routing.weighting.FastestWeighting;
import com.graphhopper.routing.weighting.GenericWeighting;
import com.graphhopper.routing.weighting.PriorityWeighting;
import com.graphhopper.routing.weighting.ShortFastestWeighting;
import com.graphhopper.routing.weighting.ShortestWeighting;
import com.graphhopper.routing.weighting.Weighting;
import com.graphhopper.storage.DAType;
import com.graphhopper.storage.Graph;
import com.graphhopper.storage.GraphEdgeIdFinder;
import com.graphhopper.util.Parameters;

/**
 * Created by David Prilutsky on 10/15/2017.
 */

public class MyGraphHopperOSM extends GraphHopperOSM {

    @Override
    public Weighting createWeighting(HintsMap hintsMap, FlagEncoder encoder, Graph graph) {
        String weightingStr = hintsMap.getWeighting().toLowerCase();
        Log.d("IN CREATE WEIGHTING", "!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!********************************************");
        hintsMap.setWeighting("fastest");
        //Weighting sup = super.createWeighting(hintsMap,encoder,(Graph)null);
        return new CustomWeighting(encoder, hintsMap);
    }
}
