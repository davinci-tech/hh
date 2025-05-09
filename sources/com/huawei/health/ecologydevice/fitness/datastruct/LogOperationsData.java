package com.huawei.health.ecologydevice.fitness.datastruct;

import com.huawei.featureuserprofile.route.hwgpxmodel.Wpt;
import com.huawei.health.R;
import com.huawei.health.device.model.HealthDevice;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import defpackage.cyf;
import defpackage.cyk;
import defpackage.cyw;
import defpackage.cza;
import defpackage.dds;
import defpackage.dij;
import defpackage.dis;
import defpackage.ixx;
import defpackage.nrh;
import defpackage.nsj;
import health.compact.a.HuaweiHealth;
import health.compact.a.util.LogUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes3.dex */
public class LogOperationsData extends BaseRopeData {
    private static final int CHARGING_ENDS_EVENT = 1;
    private static final int CUT_SONGS_DURING_EXERCISE_EVENT = 3;
    private static final int END_OF_EXERCISE_EVENT = 2;
    private static final int ROPE_FUNCTION_CLICK_EVENT = 4;
    private static final String TAG = "LogOperationsData";
    private static final long TIME_MULTIPLIER = 1000;
    private static final int VOICE_ASSISTANT_TYPE_EVENT = 6;
    private static final int VOLUME_SETTING_EVENT = 5;
    private List<cyk> mRecords;
    private final dds mRopeDeviceManager;
    private final String mSmartProductId;
    private StringBuffer mStringBuffer;

    public LogOperationsData() {
        super(30);
        dds c = dds.c();
        this.mRopeDeviceManager = c;
        this.mRecords = new ArrayList();
        this.mStringBuffer = null;
        c.g();
        this.mSmartProductId = dij.e(c.j());
    }

    public String getLogStr() {
        Object obj = this.mFitnessData.get(40061);
        return obj instanceof String ? (String) obj : "";
    }

    public void setLogStr(String str) {
        this.mFitnessData.put(40061, str);
    }

    private void parseBiLogList() {
        int i = this.mOffset;
        int e = cyw.e(this.mData, 17, i);
        boolean z = (e & 2) != 0;
        LogUtil.d(TAG, "isHasMoreData = ", Boolean.valueOf(z));
        if (!z && (e & 1) == 0) {
            LogUtil.d(TAG, "No bi record in devices");
            return;
        }
        cyk cykVar = new cyk();
        int e2 = cyw.e(this.mData, 20, i + 1);
        LogUtil.d(TAG, "bi record time = ", Integer.valueOf(e2));
        cykVar.a(e2);
        int e3 = cyw.e(this.mData, 17, i + 5);
        LogUtil.d(TAG, "event = ", Integer.valueOf(e3));
        HashMap hashMap = new HashMap();
        hashMap.put("kind_name", HealthDevice.HealthDeviceKind.HDK_ROPE_SKIPPING.toString());
        hashMap.put("macAddress", dis.b(this.mRopeDeviceManager.d()));
        hashMap.put("prodId", this.mSmartProductId);
        parseBiContent(cykVar, hashMap, i + 6, e3);
        cykVar.c(hashMap);
        this.mRecords.add(cykVar);
        LogUtil.d(TAG, "biLogDatum = ", cykVar.toString());
        if (z) {
            return;
        }
        LogUtil.d(TAG, "mRecords.size() = ", Integer.valueOf(this.mRecords.size()));
        int i2 = 0;
        for (cyk cykVar2 : this.mRecords) {
            ixx.d().d(BaseApplication.getContext(), cykVar2.a(), cykVar2.d(), 0);
            i2++;
            if (i2 >= 50) {
                break;
            }
        }
        this.mRecords.clear();
        deleteDeviceBiRecords();
    }

    private void deleteDeviceBiRecords() {
        dds.c().d(6, 3, new int[]{1});
    }

    private void parseBiContent(cyk cykVar, Map<String, Object> map, int i, int i2) {
        switch (i2) {
            case 1:
                parseChangingEndsEvent(cykVar, map, i);
                break;
            case 2:
                parseEndOfExerciseEvent(cykVar, map, i);
                break;
            case 3:
                cykVar.e(AnalyticsValue.CUT_SONGS_DURING_EXERCISE_VALUE.value());
                putCutSongsDuringExerciseEvent(map, i);
                break;
            case 4:
                cykVar.e(AnalyticsValue.ROPE_FUNCTION_CLICK_VALUE.value());
                putClickEventPara(map, i);
                break;
            case 5:
                parseVolumeSettingEvent(cykVar, map, i);
                break;
            case 6:
                parseVoiceAssistantEvent(cykVar, map, i);
                break;
        }
    }

    private void putClickEventPara(Map<String, Object> map, int i) {
        int e = cyw.e(this.mData, 17, i);
        map.put("click", Integer.valueOf(e));
        LogUtil.d(TAG, "click = ", Integer.valueOf(e));
        int e2 = cyw.e(this.mData, 17, i + 1);
        LogUtil.d(TAG, "eventType = ", Integer.valueOf(e2));
        map.put("eventType", Integer.valueOf(e2));
    }

    private void putCutSongsDuringExerciseEvent(Map<String, Object> map, int i) {
        int e = cyw.e(this.mData, 17, i);
        LogUtil.d(TAG, "listLength = ", Integer.valueOf(e));
        int i2 = i + 1;
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        ArrayList arrayList3 = new ArrayList();
        for (int i3 = 0; i3 < e; i3++) {
            arrayList.add(Integer.valueOf(cyw.e(this.mData, 18, i2)));
            arrayList2.add(Integer.valueOf(cyw.e(this.mData, 18, i2 + 2)));
            arrayList3.add(Integer.valueOf(cyw.e(this.mData, 17, i2 + 4)));
            i2 += 5;
        }
        map.put("musicIdList", arrayList);
        map.put("musicDurationList", arrayList2);
        map.put("musicNumberList", arrayList3);
        map.put("recordTime", nsj.e(cyw.e(this.mData, 20, i2) * 1000));
    }

    private void parseVoiceAssistantEvent(cyk cykVar, Map<String, Object> map, int i) {
        cykVar.e(AnalyticsValue.VOICE_ASSISTANT_TYPE_EVENT.value());
        map.put("type", Integer.valueOf(cyw.e(this.mData, 17, i)));
    }

    private void parseVolumeSettingEvent(cyk cykVar, Map<String, Object> map, int i) {
        cykVar.e(AnalyticsValue.VOLUME_SETTING_EVENT.value());
        map.put("volume", Integer.valueOf(cyw.e(this.mData, 17, i)));
    }

    private void parseEndOfExerciseEvent(cyk cykVar, Map<String, Object> map, int i) {
        cykVar.e(AnalyticsValue.END_OF_EXERCISE_EVENT.value());
        map.put(Wpt.MODE, Integer.valueOf(cyw.e(this.mData, 17, i)));
        map.put("jump_numbers", Integer.valueOf(cyw.e(this.mData, 18, i + 1)));
        int e = cyw.e(this.mData, 18, i + 3);
        map.put("jump_duration", Integer.valueOf(e));
        long e2 = cykVar.e();
        map.put("jump_start_time", nsj.e((e2 - e) * 1000));
        map.put("jump_end_time", nsj.e(e2 * 1000));
        int e3 = cyw.e(this.mData, 18, i + 5);
        LogUtil.d(TAG, "courseId = ", Integer.valueOf(e3));
        map.put("course_id", Integer.valueOf(e3));
    }

    private void parseChangingEndsEvent(cyk cykVar, Map<String, Object> map, int i) {
        long e = cykVar.e();
        map.put("charge_end_time", nsj.e(e * 1000));
        cykVar.e(AnalyticsValue.CHARGING_ENDS_EVENT.value());
        long e2 = cyw.e(this.mData, 20, i);
        map.put("charge_start_time", nsj.e(1000 * e2));
        map.put("duration", Long.valueOf(e - e2));
        map.put("start_battery", Integer.valueOf(cyw.e(this.mData, 17, i + 4)));
        map.put("end_bettary", Integer.valueOf(cyw.e(this.mData, 17, i + 5)));
    }

    @Override // com.huawei.health.ecologydevice.fitness.datastruct.BaseRopeData
    public BaseRopeData parseData() {
        LogUtil.d(TAG, "parseData, mCode = ", Integer.valueOf(this.mCode));
        int i = this.mCode;
        if (i == 1) {
            parseBiLogList();
        } else if (i == 2) {
            if (this.mStringBuffer == null) {
                this.mStringBuffer = new StringBuffer();
            }
            if (this.mDataLength != 0) {
                String b = cyw.b(cyw.b(this.mData, this.mOffset, this.mDataLength), 0);
                LogUtil.d(TAG, "logStr = ", b);
                setLogStr(b);
                this.mStringBuffer.append(b);
            } else {
                cza.a(this.mCode, this.mStringBuffer.toString());
                setLogStr(this.mStringBuffer.toString());
                this.mStringBuffer.setLength(0);
                this.mStringBuffer = null;
                nrh.d(HuaweiHealth.a(), HuaweiHealth.a().getResources().getString(R.string._2130847693_res_0x7f0227cd));
            }
        }
        return this;
    }

    @Override // com.huawei.health.ecologydevice.fitness.datastruct.BaseRopeData
    public cyf getTransmitCommand() {
        byte[] bArr;
        cyf.c cVar = new cyf.c();
        cVar.d(this.mCommand);
        cVar.b(this.mCode);
        if (this.mCode != 3) {
            bArr = new byte[0];
        } else {
            bArr = new byte[1];
            cyw.a(bArr, this.mPara[0], 0);
        }
        cVar.e(bArr);
        return cVar.d();
    }
}
