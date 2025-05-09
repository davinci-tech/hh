package com.huawei.hihealthservice.old.model;

import com.huawei.hwcloudmodel.model.unite.MotionPathHeartRate;
import com.huawei.hwfoundationmodel.trackmodel.MotionPath;
import com.huawei.hwlogsmodel.LogUtil;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: classes7.dex */
public class OldToNewMotionPath implements Serializable {
    private static final String HEARTRATE_LIST_TAG = "tp=h-r";
    private static final String LBSDATAMAP_TAG = "tp=lbs";
    private static final String PACEMAP_TAG = "tp=p-m";
    public static final int SPORT_TYPE_AEROBICS = 139;
    public static final int SPORT_TYPE_BADMINTON = 129;
    public static final int SPORT_TYPE_BASKETBALL = 271;
    public static final int SPORT_TYPE_BIKE = 259;
    public static final int SPORT_TYPE_BODY_BUILDING = 278;
    public static final int SPORT_TYPE_CLIMB_HILL = 260;
    public static final int SPORT_TYPE_CLIMB_STAIRS = 261;
    public static final int SPORT_TYPE_CROSS_COUNTRY_RACE = 280;
    public static final int SPORT_TYPE_CROSS_TRAINER = 273;
    private static final int SPORT_TYPE_DEFAULT = 0;
    public static final int SPORT_TYPE_FOOTBALL = 270;
    public static final int SPORT_TYPE_GOLF = 263;
    public static final int SPORT_TYPE_INDOOR_BIKE = 265;
    public static final int SPORT_TYPE_OPEN_AREA_SWIM = 266;
    public static final int SPORT_TYPE_OTHER_SPORT = 279;
    public static final int SPORT_TYPE_PILATES = 138;
    public static final int SPORT_TYPE_PINGPONG = 267;
    public static final int SPORT_TYPE_ROW_MACHINE = 274;
    public static final int SPORT_TYPE_RUN = 258;
    public static final int SPORT_TYPE_SWIM = 262;
    public static final int SPORT_TYPE_TABLE = 128;
    public static final int SPORT_TYPE_TENNIS = 130;
    public static final int SPORT_TYPE_TREADMILL = 264;
    public static final int SPORT_TYPE_TREAD_MACHINE = 275;
    public static final int SPORT_TYPE_VOLLEYBALL = 133;
    public static final int SPORT_TYPE_WALK = 257;
    public static final int SPORT_TYPE_YOGA = 137;
    private static final String VERSION = "version=1001";
    private static final long serialVersionUID = -3733753552518843511L;
    private List<MotionPathHeartRate> heartRateList;
    private Map<Long, double[]> lbsDataMap;
    private Map<Integer, Float> paceMap;

    public void setLbsDataMap(Map<Long, double[]> map) {
        this.lbsDataMap = map;
    }

    public Map<Long, double[]> getLbsDataMap() {
        return this.lbsDataMap;
    }

    public Map<Integer, Float> getPaceMap() {
        return this.paceMap;
    }

    public void setPaceMap(Map<Integer, Float> map) {
        this.paceMap = map;
    }

    public List<MotionPathHeartRate> getHeartRateList() {
        return this.heartRateList;
    }

    public void setHeartRateList(List<MotionPathHeartRate> list) {
        this.heartRateList = list;
    }

    public String toString() {
        long currentTimeMillis = System.currentTimeMillis();
        ArrayList arrayList = new ArrayList();
        StringBuffer stringBuffer = new StringBuffer();
        StringBuffer stringBuffer2 = new StringBuffer();
        Map<Long, double[]> map = this.lbsDataMap;
        if (map != null) {
            Iterator<Map.Entry<Long, double[]>> it = map.entrySet().iterator();
            while (it.hasNext()) {
                appendLbs(stringBuffer2, it.next());
                arrayList.add(stringBuffer2.toString());
                stringBuffer2.setLength(0);
            }
        }
        Map<Integer, Float> map2 = this.paceMap;
        if (map2 != null) {
            Iterator<Map.Entry<Integer, Float>> it2 = map2.entrySet().iterator();
            while (it2.hasNext()) {
                appendPace(stringBuffer2, it2.next());
                arrayList.add(stringBuffer2.toString());
                stringBuffer2.setLength(0);
            }
        }
        List<MotionPathHeartRate> list = this.heartRateList;
        if (list != null) {
            Iterator<MotionPathHeartRate> it3 = list.iterator();
            while (it3.hasNext()) {
                appendHeartrate(stringBuffer2, it3.next());
                arrayList.add(stringBuffer2.toString());
                stringBuffer2.setLength(0);
            }
        }
        Iterator it4 = arrayList.iterator();
        while (it4.hasNext()) {
            stringBuffer.append((String) it4.next());
        }
        LogUtil.c("OldToNewMotionPath", "toString totalTime = ", Long.valueOf(System.currentTimeMillis() - currentTimeMillis));
        return stringBuffer.toString();
    }

    private void appendHeartrate(StringBuffer stringBuffer, MotionPathHeartRate motionPathHeartRate) {
        stringBuffer.append("tp=h-r").append(MotionPath.CONTENT_KEY).append(motionPathHeartRate.getTime()).append(";").append(MotionPath.CONTENT_VALUE).append(motionPathHeartRate.getHeartRate()).append(";").append(System.lineSeparator());
    }

    private void appendPace(StringBuffer stringBuffer, Map.Entry<Integer, Float> entry) {
        stringBuffer.append("tp=p-m").append(MotionPath.CONTENT_KEY).append(entry.getKey()).append(";").append(MotionPath.CONTENT_VALUE).append(entry.getValue()).append(";").append(System.lineSeparator());
    }

    private void appendLbs(StringBuffer stringBuffer, Map.Entry<Long, double[]> entry) {
        stringBuffer.append("tp=lbs").append(MotionPath.CONTENT_KEY).append(entry.getKey()).append(";").append("lat=").append(entry.getValue()[0]).append(";").append("lon=").append(entry.getValue()[1]).append(";").append("alt=").append(entry.getValue()[2]).append(";").append(System.lineSeparator());
    }
}
