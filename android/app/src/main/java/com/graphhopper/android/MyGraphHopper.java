package com.graphhopper.android;

import com.graphhopper.GraphHopper;
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
import com.graphhopper.storage.Graph;
import com.graphhopper.storage.GraphEdgeIdFinder;
import com.graphhopper.util.Parameters;

/**
 * Created by David Prilutsky on 10/15/2017.
 */

public class MyGraphHopper extends GraphHopper {

    @Override
    public Weighting createWeighting(HintsMap hintsMap, FlagEncoder encoder, Graph graph) {
        String weightingStr = hintsMap.getWeighting().toLowerCase();
        Weighting weighting = null;

        if (encoder.supports(GenericWeighting.class)) {
            weighting = new GenericWeighting((DataFlagEncoder) encoder, hintsMap);
        } else if ("shortest".equalsIgnoreCase(weightingStr)) {
            weighting = new ShortestWeighting(encoder);
        } else if ("fastest".equalsIgnoreCase(weightingStr) || weightingStr.isEmpty()) {
            if (encoder.supports(PriorityWeighting.class))
                weighting = new PriorityWeighting(encoder, hintsMap);
            else
                weighting = new FastestWeighting(encoder, hintsMap);
        } else if ("curvature".equalsIgnoreCase(weightingStr)) {
            if (encoder.supports(CurvatureWeighting.class))
                weighting = new CurvatureWeighting(encoder, hintsMap);

        } else if ("short_fastest".equalsIgnoreCase(weightingStr)) {
            weighting = new ShortFastestWeighting(encoder, hintsMap);
        }

        if (weighting == null)
            throw new IllegalArgumentException("weighting " + weighting + " not supported");

        return weighting;
    }
}
