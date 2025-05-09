package com.huawei.hwcloudmodel.hwwear.hag.model.tide;

import java.util.List;
import java.util.Map;

/* loaded from: classes5.dex */
public class HagTideDataBean {
    private Map<String, Object> hagReportBiMap;
    private List<HagTideStationBean> tideStations;

    public Map<String, Object> getHagReportBiMap() {
        return this.hagReportBiMap;
    }

    public void setHagReportBiMap(Map<String, Object> map) {
        this.hagReportBiMap = map;
    }

    public List<HagTideStationBean> getTideStations() {
        return this.tideStations;
    }

    public void setTideStations(List<HagTideStationBean> list) {
        this.tideStations = list;
    }

    public String toString() {
        return "HagTideDataBean{tideStations=" + this.tideStations + ", hagReportBiMap=" + this.hagReportBiMap + '}';
    }
}
