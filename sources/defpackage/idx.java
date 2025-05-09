package defpackage;

import java.util.Map;

/* loaded from: classes.dex */
public class idx extends idy {
    private Map<Integer, Double> mMap;

    public idx() {
        this(0, null, 0L, 0L);
    }

    public idx(int i, Map map, long j, long j2) {
        setType(i);
        this.mMap = map;
        setStartTime(j);
        setEndTime(j2);
    }

    public Map getMap() {
        return this.mMap;
    }
}
