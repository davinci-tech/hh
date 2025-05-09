package com.huawei.health.interactor;

import android.content.Context;
import android.text.TextUtils;
import android.util.SparseArray;
import com.google.gson.JsonSyntaxException;
import com.huawei.hihealth.HiDataReadOption;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.api.HiHealthManager;
import com.huawei.hihealth.data.listener.HiDataReadResultListener;
import com.huawei.hihealth.data.model.HiTrackMetaData;
import com.huawei.hihealth.util.HiJsonUtil;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwfoundationmodel.trackmodel.MotionPath;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.pluginachievement.manager.model.TrackData;
import defpackage.gwk;
import defpackage.mct;
import defpackage.mfg;
import health.compact.a.CommonUtil;
import health.compact.a.HiDateUtil;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: classes3.dex */
public abstract class AchieveMedalsInteractors {
    private static final int DURATION_TIME_PARAM = -7;
    private static final String TAG = "AchieveMedalsInteractors";
    protected Context mContext;
    private long mRecentStamp;

    private boolean isValidType(int i) {
        return (i == 257 || i == 258 || i == 264 || i == 280) || (i == 259) || (i == 262 || i == 266);
    }

    public abstract void behave();

    public abstract void unRegister();

    protected ArrayList<TrackData> searchSingleRunLongest(List<HiHealthData> list) {
        ArrayList<TrackData> arrayList = new ArrayList<>(10);
        String b = mct.b(this.mContext, "_syncWearTimtstamp");
        LogUtil.a(TAG, "search recentstamp is ", b);
        this.mRecentStamp = TextUtils.isEmpty(b) ? getTimeSevenDaysBefore() : CommonUtil.g(b);
        if (list.size() == 0) {
            return arrayList;
        }
        LogUtil.a(TAG, "single records size is ", Integer.valueOf(list.size()));
        boolean z = false;
        for (HiHealthData hiHealthData : list) {
            long startTime = hiHealthData.getStartTime();
            if (startTime > this.mRecentStamp) {
                this.mRecentStamp = startTime;
                z = true;
            }
            try {
                HiTrackMetaData hiTrackMetaData = (HiTrackMetaData) HiJsonUtil.e(hiHealthData.getMetaData(), HiTrackMetaData.class);
                int abnormalTrack = hiTrackMetaData.getAbnormalTrack();
                int duplicated = hiTrackMetaData.getDuplicated();
                LogUtil.a(TAG, "getAbnormalTrack=", Integer.valueOf(abnormalTrack), "duplicateResult=", Integer.valueOf(duplicated));
                if (abnormalTrack == 0 && duplicated == 0) {
                    int sportDataSource = hiTrackMetaData.getSportDataSource();
                    if (sportDataSource == 2 || sportDataSource == 3) {
                        LogUtil.h(TAG, "sportDataSource=", Integer.valueOf(sportDataSource));
                    } else {
                        TrackData trackData = getTrackData(hiHealthData, hiTrackMetaData);
                        if (trackData != null) {
                            arrayList.add(trackData);
                        }
                    }
                }
            } catch (JsonSyntaxException unused) {
                LogUtil.h(TAG, "trackMetaData is error");
            }
        }
        if (z) {
            HashMap hashMap = new HashMap();
            hashMap.put("_syncWearTimtstamp", String.valueOf(this.mRecentStamp));
            mct.b(this.mContext, hashMap);
            LogUtil.a(TAG, "timestamp saved is ", Long.valueOf(this.mRecentStamp));
        }
        return arrayList;
    }

    private TrackData getTrackData(HiHealthData hiHealthData, HiTrackMetaData hiTrackMetaData) {
        String str;
        String str2;
        String str3;
        Map<String, String> extendTrackMap = hiTrackMetaData.getExtendTrackMap();
        if (extendTrackMap == null || extendTrackMap.size() == 0) {
            str = "";
            str2 = "";
            str3 = str2;
        } else {
            str = extendTrackMap.get("skipNum");
            str3 = extendTrackMap.get("maxSkippingTimes");
            str2 = extendTrackMap.get("skipSpeed");
        }
        long startTime = hiHealthData.getStartTime();
        long endTime = hiHealthData.getEndTime();
        int sportType = hiTrackMetaData.getSportType();
        int totalDistance = hiTrackMetaData.getTotalDistance();
        TrackData trackData = new TrackData();
        trackData.saveDistance(totalDistance);
        trackData.saveTrackTime(startTime);
        trackData.saveType(sportType);
        trackData.saveEndTime(endTime);
        trackData.savePaceMap(hiTrackMetaData.getPaceMap());
        trackData.savePartTimeMap(hiTrackMetaData.getPartTimeMap());
        float bestPace = hiTrackMetaData.getBestPace();
        if (hiTrackMetaData.getTrackType() != 0 || hiTrackMetaData.getTrackType() != 1) {
            if (bestPace == 0.0f) {
                bestPace = dealDataSourceIsDevice(hiHealthData);
            }
            trackData.saveBestPace(bestPace);
        } else {
            trackData.saveBestPace(hiTrackMetaData.getBestPace());
        }
        trackData.saveSportDataSource(hiTrackMetaData.getSportDataSource());
        if (sportType == 283) {
            totalDistance = mfg.b(str);
            trackData.saveTrackNum(totalDistance);
            trackData.setMaxSkippingTimes(mfg.b(str3));
            trackData.setSkipSpeed(mfg.b(str2));
            trackData.setTotalTime(hiTrackMetaData.getTotalTime());
        } else if (!isValidType(sportType) || totalDistance <= 0) {
            return null;
        }
        LogUtil.a(TAG, "whole data from hihealth studio is*", Integer.valueOf(totalDistance), "&&", Integer.valueOf(sportType), " time ", Long.valueOf(startTime));
        return trackData;
    }

    private float dealDataSourceIsDevice(HiHealthData hiHealthData) {
        Map<Integer, Float> requestPaceMap;
        String sequenceFileUrl = hiHealthData.getSequenceFileUrl();
        if (TextUtils.isEmpty(sequenceFileUrl)) {
            LogUtil.h(TAG, "fileUrl is null");
            return 0.0f;
        }
        MotionPath c = gwk.c(this.mContext, sequenceFileUrl);
        if (c == null || (requestPaceMap = c.requestPaceMap()) == null || requestPaceMap.size() == 0) {
            return 0.0f;
        }
        Iterator<Map.Entry<Integer, Float>> it = requestPaceMap.entrySet().iterator();
        float f = 0.0f;
        while (it.hasNext()) {
            float floatValue = it.next().getValue().floatValue();
            if (f == 0.0f) {
                f = floatValue;
            }
            if (f < floatValue) {
                f = floatValue;
            }
        }
        return f;
    }

    protected long getTimeSevenDaysBefore() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.add(6, -7);
        return calendar.getTimeInMillis();
    }

    protected void readSingleTrackData(final IBaseResponseCallback iBaseResponseCallback) {
        LogUtil.a(TAG, "called when wear data changed !");
        HiDataReadOption hiDataReadOption = new HiDataReadOption();
        hiDataReadOption.setType(new int[]{30002});
        hiDataReadOption.setSortOrder(1);
        String b = mct.b(this.mContext, "_syncWearTimtstamp");
        long timeSevenDaysBefore = getTimeSevenDaysBefore();
        LogUtil.a(TAG, "sevendaybefore timestamp get is ", Long.valueOf(timeSevenDaysBefore));
        long g = CommonUtil.g(b);
        if (!TextUtils.isEmpty(b)) {
            timeSevenDaysBefore = g;
        }
        this.mRecentStamp = timeSevenDaysBefore;
        LogUtil.a(TAG, "timestamp get is ", Long.valueOf(timeSevenDaysBefore));
        if (System.currentTimeMillis() < this.mRecentStamp) {
            this.mRecentStamp = mfg.c(System.currentTimeMillis(), true);
            HashMap hashMap = new HashMap();
            hashMap.put("_syncWearTimtstamp", String.valueOf(this.mRecentStamp));
            mct.b(this.mContext, hashMap);
        }
        hiDataReadOption.setTimeInterval(HiDateUtil.t(this.mRecentStamp), HiDateUtil.f(System.currentTimeMillis()));
        HiHealthManager.d(this.mContext).readHiHealthData(hiDataReadOption, new HiDataReadResultListener() { // from class: com.huawei.health.interactor.AchieveMedalsInteractors.4
            @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
            public void onResultIntent(int i, Object obj, int i2, int i3) {
            }

            @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
            public void onResult(Object obj, int i, int i2) {
                LogUtil.a(AchieveMedalsInteractors.TAG, "achieve moudle get single run to read wear data onResult enter");
                if (i != 0 || obj == null) {
                    LogUtil.h(AchieveMedalsInteractors.TAG, "achieve moudle get single run data fail! errorcode = ", Integer.valueOf(i), "&& anchor is ", Integer.valueOf(i2));
                    return;
                }
                LogUtil.a(AchieveMedalsInteractors.TAG, "achieve moudle get single run to read wear data onResult data is not null");
                SparseArray sparseArray = (SparseArray) obj;
                Object obj2 = sparseArray.get(30002);
                if (sparseArray.size() <= 0) {
                    LogUtil.h(AchieveMedalsInteractors.TAG, "trackdata from DataStudio is none");
                    return;
                }
                List list = (List) obj2;
                LogUtil.a(AchieveMedalsInteractors.TAG, "obtainFrom DataStudio Achieve size is ", Integer.valueOf(list.size()));
                iBaseResponseCallback.d(i, list);
            }
        });
    }
}
