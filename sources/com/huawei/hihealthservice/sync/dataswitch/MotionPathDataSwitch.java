package com.huawei.hihealthservice.sync.dataswitch;

import android.content.Context;
import com.amap.api.location.AMapLocation;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.data.model.HiTrackMetaData;
import com.huawei.hihealth.dictionary.HiHealthDictManager;
import com.huawei.hihealth.util.HiInChinaUtil;
import com.huawei.hihealth.util.HiJsonUtil;
import com.huawei.hihealthservice.old.model.OldToNewMotionPath;
import com.huawei.hwcloudmodel.healthdatacloud.model.SampleSequence;
import com.huawei.hwcloudmodel.model.samplepoint.SamplePoint;
import com.huawei.hwcloudmodel.model.unite.Location;
import com.huawei.hwcloudmodel.model.unite.MotionPathDetail;
import com.huawei.hwfoundationmodel.trackmodel.MotionPath;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.utils.Constants;
import defpackage.iku;
import defpackage.ikv;
import defpackage.isv;
import defpackage.iut;
import defpackage.iuz;
import defpackage.iwj;
import defpackage.koq;
import health.compact.a.HiCommonUtil;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes7.dex */
public class MotionPathDataSwitch {
    private iku b;
    private Context d;

    public MotionPathDataSwitch(Context context) {
        Context applicationContext = context.getApplicationContext();
        this.d = applicationContext;
        this.b = iku.a(applicationContext);
    }

    public List<HiHealthData> d(List<MotionPathDetail> list, int i, int i2) throws iut {
        HiHealthData d;
        if (koq.b(list)) {
            LogUtil.h("HiH_MotionPathDataSwitch", "cloudTrackToLocal motionPathDetails is null or empty");
            return null;
        }
        ArrayList arrayList = new ArrayList(list.size());
        for (MotionPathDetail motionPathDetail : list) {
            if (motionPathDetail != null && (d = d(motionPathDetail, i, i2)) != null) {
                try {
                    HiTrackMetaData hiTrackMetaData = (HiTrackMetaData) HiJsonUtil.e(d.getMetaData(), HiTrackMetaData.class);
                    if (200 == hiTrackMetaData.getTrackType() && AMapLocation.COORD_TYPE_WGS84.equals(hiTrackMetaData.getCoordinate())) {
                        try {
                            if (e(d)) {
                                LogUtil.c("HiH_MotionPathDataSwitch", "need transfer point");
                                hiTrackMetaData.setCoordinate(AMapLocation.COORD_TYPE_GCJ02);
                            }
                        } catch (Exception unused) {
                            ReleaseLogUtil.c("HiH_MotionPathDataSwitch", "transferGPSLocation Exception");
                        }
                    }
                    arrayList.add(d);
                } catch (Exception e) {
                    ReleaseLogUtil.d("HiH_MotionPathDataSwitch", e.getClass().getSimpleName(), ", Exception track is (", motionPathDetail.getStartTime(), ", ", motionPathDetail.getRecordId(), Constants.RIGHT_BRACKET_ONLY);
                    LogUtil.c("HiH_MotionPathDataSwitch", "Exception track info (", motionPathDetail.getStartTime(), ", ", motionPathDetail.getEndTime(), ", ", motionPathDetail.getSportType(), ", ", motionPathDetail.getRunState(), ", ", motionPathDetail.getTotalDistance(), Constants.RIGHT_BRACKET_ONLY);
                }
            }
        }
        return arrayList;
    }

    private boolean e(HiHealthData hiHealthData) {
        if (hiHealthData == null) {
            return false;
        }
        String sequenceData = hiHealthData.getSequenceData();
        if (sequenceData == null || sequenceData.isEmpty()) {
            return false;
        }
        try {
            StringBuffer stringBuffer = new StringBuffer(16);
            boolean z = false;
            for (String str : sequenceData.split("\n")) {
                String[] split = str.split(";");
                int length = split.length;
                if (str.contains(MotionPath.LBS_DATA_MAP_TAG)) {
                    double[] dArr = new double[length - 2];
                    long parseLong = Long.parseLong(split[1].split("=")[1]);
                    for (int i = 2; i < length; i++) {
                        dArr[i - 2] = Double.parseDouble(split[i].split("=")[1]);
                    }
                    if (!z) {
                        int c = HiInChinaUtil.c(dArr[0], dArr[1]);
                        if (3 != c && 1 != c) {
                            return false;
                        }
                        z = true;
                    }
                    str = d(iuz.b(this.d, dArr), parseLong).toString();
                }
                stringBuffer.append(str).append(System.lineSeparator());
            }
            hiHealthData.setSequenceData(stringBuffer.toString());
            return true;
        } catch (NumberFormatException unused) {
            ReleaseLogUtil.c("HiH_MotionPathDataSwitch", "transferGpsLocation NumberFormatException");
            return false;
        }
    }

    private StringBuffer d(double[] dArr, long j) {
        StringBuffer stringBuffer = new StringBuffer(48);
        stringBuffer.append("tp=lbs;k=").append(j).append(";lat=").append(dArr[0]).append(";lon=").append(dArr[1]).append(";alt=").append(dArr[2]).append(";t=").append(dArr[3]).append(";");
        return stringBuffer;
    }

    private HiHealthData d(MotionPathDetail motionPathDetail, int i, int i2) throws iut {
        try {
            if (i2 == 2) {
                return b(motionPathDetail, i);
            }
            if (i2 == 3) {
                return c(motionPathDetail, i);
            }
            LogUtil.h("HiH_MotionPathDataSwitch", "oneCloudTrackToLocal no such hiSyncModel");
            return null;
        } catch (iut e) {
            throw e;
        } catch (Exception unused) {
            ReleaseLogUtil.c("HiH_MotionPathDataSwitch", "oneCloudTrackToLocal Exception track is ", motionPathDetail);
            return null;
        }
    }

    public List<MotionPathDetail> c(List<HiHealthData> list, int i) {
        if (i == 2) {
            return isv.b(this.d).a(list);
        }
        if (i == 3) {
            return isv.b(this.d).d(list);
        }
        LogUtil.h("HiH_MotionPathDataSwitch", "localTrackToCloud no such hiSyncModel");
        return null;
    }

    public HiHealthData b(MotionPathDetail motionPathDetail, int i) throws iut {
        HashMap hashMap;
        if (motionPathDetail == null) {
            return null;
        }
        ikv a2 = this.b.a(iuz.d(this.d), i, motionPathDetail.getDeviceCode().longValue());
        if (a2 == null) {
            ReleaseLogUtil.c("HiH_MotionPathDataSwitch", "oneCloudTrackToLocalByUnite hiHealthContext is null");
            return null;
        }
        HiHealthData d = isv.b(this.d).d(a2, motionPathDetail);
        String attribute = motionPathDetail.getAttribute();
        if (attribute == null || attribute.isEmpty() || attribute.equals("(null)")) {
            HiTrackMetaData hiTrackMetaData = new HiTrackMetaData();
            OldToNewMotionPath c = isv.b(this.d).c(hiTrackMetaData, motionPathDetail);
            d.setMetaData(HiJsonUtil.d(hiTrackMetaData, HiTrackMetaData.class));
            List<Location> location = motionPathDetail.getLocation();
            if (location != null && !location.isEmpty()) {
                hashMap = new HashMap(location.size());
                for (Location location2 : location) {
                    hashMap.put(location2.getTimestamp(), new double[]{location2.getLatitude(), location2.getLongitude(), location2.getAltitude(), 0.0d});
                }
            } else {
                hashMap = new HashMap(1);
            }
            c.setLbsDataMap(hashMap);
            c.setHeartRateList(motionPathDetail.getHeartRateList());
            d.setSequenceData(c.toString());
        } else {
            int indexOf = attribute.indexOf("&&");
            int indexOf2 = attribute.indexOf("@is");
            int indexOf3 = attribute.indexOf("@is", indexOf);
            if (indexOf == -1 || indexOf2 == -1 || indexOf3 == -1) {
                ReleaseLogUtil.c("HiH_MotionPathDataSwitch", "oneCloudTrackToLocalByUnite sequenceData or metaData is null , cloudTrack is ", motionPathDetail.getRecordId());
                return null;
            }
            String substring = attribute.substring(indexOf2 + 3, indexOf);
            String substring2 = attribute.substring(indexOf3 + 3);
            d.setSequenceData(substring);
            d.setMetaData(e(motionPathDetail, substring2));
        }
        return d;
    }

    private String e(MotionPathDetail motionPathDetail, String str) {
        HiTrackMetaData hiTrackMetaData = (HiTrackMetaData) HiJsonUtil.e(str, HiTrackMetaData.class);
        if (hiTrackMetaData.getTotalSteps() == 0) {
            Integer totalSteps = motionPathDetail.getTotalSteps();
            hiTrackMetaData.setTotalSteps(totalSteps == null ? 0 : totalSteps.intValue());
        }
        if (hiTrackMetaData.getTotalCalories() == 0) {
            Integer totalCalories = motionPathDetail.getTotalCalories();
            hiTrackMetaData.setTotalCalories(totalCalories == null ? 0 : totalCalories.intValue());
        }
        if (hiTrackMetaData.getTotalDistance() == 0) {
            Integer totalDistance = motionPathDetail.getTotalDistance();
            hiTrackMetaData.setTotalDistance(totalDistance == null ? 0 : totalDistance.intValue());
        }
        if (hiTrackMetaData.getTotalTime() == 0) {
            Long totalTime = motionPathDetail.getTotalTime();
            hiTrackMetaData.setTotalTime(totalTime != null ? totalTime.longValue() : 0L);
        }
        if (hiTrackMetaData.getSportType() == 0) {
            Integer sportType = motionPathDetail.getSportType();
            hiTrackMetaData.setSportType(sportType == null ? 0 : iwj.d(sportType.intValue()));
        }
        if (hiTrackMetaData.getVendor() == null) {
            hiTrackMetaData.setVendor(motionPathDetail.getVendor());
        }
        if (hiTrackMetaData.getCoordinate() == null) {
            hiTrackMetaData.setCoordinate(motionPathDetail.getCoordinate());
        }
        if (hiTrackMetaData.getSportDataSource() == 0) {
            Integer sportDataSource = motionPathDetail.getSportDataSource();
            hiTrackMetaData.setSportDataSource(sportDataSource == null ? 0 : sportDataSource.intValue());
        }
        if (hiTrackMetaData.getAbnormalTrack() == 0) {
            Integer abnormalTrack = motionPathDetail.getAbnormalTrack();
            hiTrackMetaData.setAbnormalTrack(abnormalTrack == null ? 0 : abnormalTrack.intValue());
        }
        if (hiTrackMetaData.getDuplicated() == 0) {
            Integer valueOf = Integer.valueOf(motionPathDetail.getDuplicateTrack());
            hiTrackMetaData.setDuplicated(valueOf != null ? valueOf.intValue() : 0);
        }
        return HiJsonUtil.e(hiTrackMetaData);
    }

    public HiHealthData c(MotionPathDetail motionPathDetail, int i) throws iut {
        List<SamplePoint> samplePoints;
        ikv a2 = this.b.a(iuz.d(this.d), i, motionPathDetail.getDeviceCode().longValue());
        if (a2 != null && (samplePoints = motionPathDetail.getSamplePoints()) != null && samplePoints.size() == 2) {
            String str = null;
            String str2 = null;
            for (int i2 = 0; i2 < 2; i2++) {
                String key = samplePoints.get(i2).getKey();
                if (key == null) {
                    return null;
                }
                if (key.equals("TRACK_METADATA")) {
                    str = samplePoints.get(i2).getValue();
                } else if (key.equals("TRACK_SEQUENCE_DATA")) {
                    str2 = samplePoints.get(i2).getValue();
                } else {
                    LogUtil.c("HiH_MotionPathDataSwitch", "oneCloudTrackToLocalBySamplePoint else");
                }
            }
            if (str != null && str2 != null) {
                return isv.b(this.d).d(a2, motionPathDetail, str, str2);
            }
        }
        return null;
    }

    public List<HiHealthData> a(List<SampleSequence> list, int i, boolean z) throws iut {
        if (HiCommonUtil.d(list)) {
            return new ArrayList();
        }
        ArrayList arrayList = new ArrayList(list.size());
        Iterator<SampleSequence> it = list.iterator();
        while (it.hasNext()) {
            HiHealthData a2 = a(it.next(), i, z);
            if (a2 == null) {
                ReleaseLogUtil.c("HiH_MotionPathDataSwitch", "hiHealthData is null");
            } else {
                arrayList.add(a2);
            }
        }
        return arrayList;
    }

    private HiHealthData a(SampleSequence sampleSequence, int i, boolean z) throws iut {
        if (sampleSequence == null) {
            return null;
        }
        String detailData = sampleSequence.getDetailData();
        if (HiCommonUtil.b(detailData)) {
            ReleaseLogUtil.c("HiH_MotionPathDataSwitch", "oneCloudForDictionarySequenceData sequenceData is null");
            return null;
        }
        ikv a2 = this.b.a(iuz.d(this.d), i, sampleSequence.getDeviceCode());
        if (a2 == null) {
            return null;
        }
        HiHealthData hiHealthData = new HiHealthData();
        a2.i(1);
        ikv.b(hiHealthData, a2);
        hiHealthData.setTimeZone(sampleSequence.getTimeZone());
        hiHealthData.setStartTime(sampleSequence.getStartTime());
        hiHealthData.setEndTime(sampleSequence.getEndTime());
        hiHealthData.setSequenceData(detailData);
        hiHealthData.setMetaData(sampleSequence.getSummaryData());
        hiHealthData.setSimpleData(sampleSequence.getExtendData());
        int subType = sampleSequence.getSubType();
        int type = sampleSequence.getType() + subType;
        if (HiHealthDictManager.d(this.d).d(type) == null) {
            if (!z) {
                return null;
            }
            subType = iwj.d(subType);
            type = sampleSequence.getType();
        }
        hiHealthData.setType(type);
        hiHealthData.setSubType(subType);
        return hiHealthData;
    }
}
