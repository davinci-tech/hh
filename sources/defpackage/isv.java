package defpackage;

import android.content.Context;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.data.model.HiTrackMetaData;
import com.huawei.hihealth.util.HiJsonUtil;
import com.huawei.hihealthservice.old.model.OldToNewMotionPath;
import com.huawei.hwcloudmodel.healthdatacloud.model.SampleSequence;
import com.huawei.hwcloudmodel.model.unite.Location;
import com.huawei.hwcloudmodel.model.unite.MotionPathDetail;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.HiCommonUtil;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* loaded from: classes7.dex */
public class isv {
    private static Context c;

    static class a {
        private static final isv c = new isv();
    }

    private isv() {
    }

    public static isv b(Context context) {
        c = context.getApplicationContext();
        return a.c;
    }

    public List<SampleSequence> e(List<HiHealthData> list) {
        if (HiCommonUtil.d(list)) {
            return null;
        }
        ikv f = iis.d().f(list.get(0).getClientId());
        if (f == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList(list.size());
        for (HiHealthData hiHealthData : list) {
            if (hiHealthData != null) {
                if (HiCommonUtil.b(hiHealthData.getSequenceData())) {
                    ReleaseLogUtil.c("HiH_MotionPathDtSHlp", "localTrackToCloudByUnite localTrack sequenceData error");
                } else {
                    arrayList.add(new SampleSequence.Builder().dataId(hiHealthData.getDataId()).deviceCode(f.a()).startTime(hiHealthData.getStartTime()).endTime(hiHealthData.getEndTime()).type(hiHealthData.getType()).subType(hiHealthData.getSubType()).timeZone(hiHealthData.getTimeZone()).mSummaryData(hiHealthData.getMetaData()).detailData(hiHealthData.getSequenceData()).extendData(hiHealthData.getSimpleData()).build());
                }
            }
        }
        return arrayList;
    }

    public List<MotionPathDetail> a(List<HiHealthData> list) {
        ArrayList arrayList = null;
        if (list != null && !list.isEmpty()) {
            ikv f = iis.d().f(list.get(0).getClientId());
            if (f == null) {
                return null;
            }
            arrayList = new ArrayList(list.size());
            for (HiHealthData hiHealthData : list) {
                String metaData = hiHealthData.getMetaData();
                if (HiCommonUtil.b(metaData)) {
                    ReleaseLogUtil.c("HiH_MotionPathDtSHlp", "localTrackToCloudByUnite localTrack metaData error ,it is ", hiHealthData);
                } else {
                    HiTrackMetaData hiTrackMetaData = (HiTrackMetaData) HiJsonUtil.e(metaData, HiTrackMetaData.class);
                    if (hiTrackMetaData == null) {
                        ReleaseLogUtil.c("HiH_MotionPathDtSHlp", "localTrackToCloudByUnite trackMetaData is null!");
                    } else if (!c(hiTrackMetaData)) {
                        ReleaseLogUtil.e("HiH_MotionPathDtSHlp", "isValidTrackMetaDataPaceValue isUpdata =", Boolean.valueOf(a(hiHealthData)), Long.valueOf(hiHealthData.getStartTime()));
                    } else {
                        MotionPathDetail e = e(f, hiHealthData, metaData, hiTrackMetaData);
                        if (e != null) {
                            ReleaseLogUtil.e("HiH_MotionPathDtSHlp", e.getMotionPathKeyInfo());
                            arrayList.add(e);
                        }
                    }
                }
            }
        }
        return arrayList;
    }

    private MotionPathDetail e(ikv ikvVar, HiHealthData hiHealthData, String str, HiTrackMetaData hiTrackMetaData) {
        MotionPathDetail motionPathDetail = new MotionPathDetail();
        motionPathDetail.setDataId(hiHealthData.getDataId());
        motionPathDetail.setDeviceCode(Long.valueOf(ikvVar.a()));
        motionPathDetail.setStartTime(Long.valueOf(hiHealthData.getStartTime()));
        motionPathDetail.setEndTime(Long.valueOf(hiHealthData.getEndTime()));
        motionPathDetail.setTimeZone(hiHealthData.getTimeZone());
        motionPathDetail.setAbnormalTrack(Integer.valueOf(hiTrackMetaData.getAbnormalTrack()));
        motionPathDetail.setDuplicateTrack(Integer.valueOf(hiTrackMetaData.getDuplicated()));
        motionPathDetail.setSportType(Integer.valueOf(iwj.c(hiTrackMetaData.getSportType())));
        motionPathDetail.setTotalCalories(Integer.valueOf(hiTrackMetaData.getTotalCalories()));
        motionPathDetail.setTotalDistance(Integer.valueOf(hiTrackMetaData.getTotalDistance()));
        motionPathDetail.setTotalSteps(Integer.valueOf(hiTrackMetaData.getTotalSteps()));
        motionPathDetail.setTotalTime(Long.valueOf(hiTrackMetaData.getTotalTime()));
        motionPathDetail.setPartTimeMap(hiTrackMetaData.getPartTimeMap());
        motionPathDetail.setVendor(hiTrackMetaData.getVendor());
        motionPathDetail.setCoordinate(hiTrackMetaData.getCoordinate());
        motionPathDetail.setPaceMap(c(hiTrackMetaData.getPaceMap()));
        motionPathDetail.setSportDataSource(hiTrackMetaData.getSportDataSource());
        ArrayList arrayList = new ArrayList(1);
        Location location = new Location();
        location.setName("gps point is in attribute HW_EXT_TRACK_DETAIL,not here");
        arrayList.add(location);
        motionPathDetail.setLocation(arrayList);
        String sequenceData = hiHealthData.getSequenceData();
        if (HiCommonUtil.b(sequenceData)) {
            LogUtil.b("HiH_MotionPathDtSHlp", "localTrackToCloudByUnite localTrack sequenceData error ,it is ", hiHealthData);
            return null;
        }
        motionPathDetail.setAttribute("HW_EXT_TRACK_DETAIL@is" + sequenceData + "&&HW_EXT_TRACK_SIMPLIFY@is" + str);
        return motionPathDetail;
    }

    private boolean a(HiHealthData hiHealthData) {
        return iiz.a(c).e(hiHealthData.getDataId(), 3) > 0;
    }

    private boolean c(HiTrackMetaData hiTrackMetaData) {
        if (hiTrackMetaData.getPaceMap() != null) {
            Iterator<Map.Entry<Integer, Float>> it = hiTrackMetaData.getPaceMap().entrySet().iterator();
            while (it.hasNext()) {
                if (!iuz.e(it.next().getValue().floatValue(), 1000000.0d, 0.0d)) {
                    return false;
                }
            }
        }
        if (hiTrackMetaData.getPartTimeMap() == null) {
            return true;
        }
        Iterator<Map.Entry<Double, Double>> it2 = hiTrackMetaData.getPartTimeMap().entrySet().iterator();
        while (it2.hasNext()) {
            if (!iuz.e(it2.next().getValue().doubleValue(), 1000000.0d, 0.0d)) {
                return false;
            }
        }
        return true;
    }

    private static Map<String, Float> c(Map<Integer, Float> map) {
        if (map == null || map.isEmpty()) {
            return new HashMap();
        }
        HashMap hashMap = new HashMap(map.size());
        for (Map.Entry<Integer, Float> entry : map.entrySet()) {
            hashMap.put(Integer.toString(entry.getKey().intValue()), entry.getValue());
        }
        return hashMap;
    }

    public List<MotionPathDetail> d(List<HiHealthData> list) {
        ikv f;
        if (list == null || list.isEmpty()) {
            return null;
        }
        ArrayList arrayList = new ArrayList(list.size());
        for (HiHealthData hiHealthData : list) {
            MotionPathDetail motionPathDetail = new MotionPathDetail();
            long startTime = hiHealthData.getStartTime();
            long endTime = hiHealthData.getEndTime();
            String timeZone = hiHealthData.getTimeZone();
            motionPathDetail.setDataId(hiHealthData.getDataId());
            motionPathDetail.setStartTime(Long.valueOf(startTime));
            motionPathDetail.setEndTime(Long.valueOf(endTime));
            motionPathDetail.setTimeZone(timeZone);
            String metaData = hiHealthData.getMetaData();
            String sequenceData = hiHealthData.getSequenceData();
            HiTrackMetaData hiTrackMetaData = (HiTrackMetaData) HiJsonUtil.e(metaData, HiTrackMetaData.class);
            if (hiTrackMetaData != null && sequenceData != null && (f = iis.d().f(hiHealthData.getClientId())) != null) {
                motionPathDetail.setAbnormalTrack(Integer.valueOf(hiTrackMetaData.getAbnormalTrack()));
                motionPathDetail.setDuplicateTrack(Integer.valueOf(hiTrackMetaData.getDuplicated()));
                motionPathDetail.setDeviceCode(Long.valueOf(f.a()));
                motionPathDetail.setTotalSteps(Integer.valueOf(hiTrackMetaData.getTotalSteps()));
                motionPathDetail.setTotalCalories(Integer.valueOf(hiTrackMetaData.getTotalCalories()));
                motionPathDetail.setTotalDistance(Integer.valueOf(hiTrackMetaData.getTotalDistance()));
                motionPathDetail.setTotalTime(Long.valueOf(hiTrackMetaData.getTotalTime()));
                ArrayList arrayList2 = new ArrayList(2);
                arrayList2.add(iup.d("TRACK_METADATA", metaData, startTime, endTime, (String) null));
                arrayList2.add(iup.d("TRACK_SEQUENCE_DATA", sequenceData, startTime, endTime, (String) null));
                motionPathDetail.setSamplePoints(arrayList2);
                arrayList.add(motionPathDetail);
            }
        }
        return arrayList;
    }

    public OldToNewMotionPath c(HiTrackMetaData hiTrackMetaData, MotionPathDetail motionPathDetail) {
        long longValue = motionPathDetail.getTotalTime().longValue();
        int intValue = motionPathDetail.getTotalDistance().intValue();
        hiTrackMetaData.setTotalTime(longValue);
        hiTrackMetaData.setTotalDistance(intValue);
        hiTrackMetaData.setAvgPace(intValue == 0 ? 0.0f : longValue / intValue);
        hiTrackMetaData.setTotalSteps(motionPathDetail.getTotalSteps().intValue());
        hiTrackMetaData.setTotalCalories(motionPathDetail.getTotalCalories().intValue());
        hiTrackMetaData.setSportId(motionPathDetail.getRecordId());
        LogUtil.c("HiH_MotionPathDtSHlp", "sportType is ", motionPathDetail.getSportType());
        hiTrackMetaData.setSportType(iwj.d(motionPathDetail.getSportType().intValue()));
        Map<String, Float> paceMap = motionPathDetail.getPaceMap();
        OldToNewMotionPath oldToNewMotionPath = new OldToNewMotionPath();
        if (paceMap != null && !paceMap.isEmpty()) {
            Set<Map.Entry<String, Float>> entrySet = paceMap.entrySet();
            HashMap hashMap = new HashMap(entrySet.size());
            for (Map.Entry<String, Float> entry : entrySet) {
                try {
                    hashMap.put(Integer.valueOf(Integer.parseInt(entry.getKey())), entry.getValue());
                } catch (NumberFormatException unused) {
                    ReleaseLogUtil.e("HiH_MotionPathDtSHlp", "setData NumberFormatException");
                }
            }
            hiTrackMetaData.setPaceMap(hashMap);
            oldToNewMotionPath.setPaceMap(hashMap);
        }
        hiTrackMetaData.setAbnormalTrack(motionPathDetail.getAbnormalTrack().intValue());
        hiTrackMetaData.setDuplicated(motionPathDetail.getDuplicateTrack());
        hiTrackMetaData.setPartTimeMap(motionPathDetail.getPartTimeMap());
        return oldToNewMotionPath;
    }

    public HiHealthData d(ikv ikvVar, MotionPathDetail motionPathDetail, String str, String str2) {
        HiHealthData d = d(ikvVar, motionPathDetail);
        d.setMetaData(str);
        d.setSequenceData(str2);
        return d;
    }

    public HiHealthData d(ikv ikvVar, MotionPathDetail motionPathDetail) {
        HiHealthData hiHealthData = new HiHealthData();
        ikvVar.i(1);
        ikv.b(hiHealthData, ikvVar);
        hiHealthData.setType(30001);
        hiHealthData.setTimeZone(motionPathDetail.getTimeZone());
        hiHealthData.setStartTime(motionPathDetail.getStartTime().longValue());
        hiHealthData.setEndTime(motionPathDetail.getEndTime().longValue());
        return hiHealthData;
    }
}
