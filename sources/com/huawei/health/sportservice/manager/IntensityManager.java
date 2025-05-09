package com.huawei.health.sportservice.manager;

import android.content.Context;
import com.huawei.haf.application.BaseApplication;
import com.huawei.health.sportservice.template.ComponentName;
import com.huawei.health.sportservice.template.SportComponentType;
import com.huawei.hihealth.HiDataInsertOption;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.api.HiHealthManager;
import com.huawei.hihealth.data.listener.HiDataOperateListener;
import com.huawei.hwfoundationmodel.trackmodel.MotionPath;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.jsoperation.JsUtil;
import defpackage.ffn;
import defpackage.fhs;
import defpackage.knw;
import defpackage.kob;
import defpackage.koc;
import defpackage.koq;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SportComponentType(classify = 1, name = ComponentName.INTENSITY_MANAGER)
/* loaded from: classes8.dex */
public class IntensityManager extends BaseIntensityManager {
    private static final int CADENCE_THRESHOLD_110 = 110;
    private static final int CADENCE_THRESHOLD_30 = 30;
    private static final int CADENCE_THRESHOLD_60 = 60;
    private static final int CADENCE_THRESHOLD_80 = 80;
    private static final long MILLIS_PER_MINUTE = 60000;
    private static final int MINUTE_OF_FIVE_SEC = 12;
    private static final int PADDLE_FREQUENCY_THRESHOLD = 25;
    private static final int POWER_THRESHOLD = 50;
    private static final int RESISTANCE_THRESHOLD_1 = 1;
    private static final int RESISTANCE_THRESHOLD_2 = 2;
    private static final int RESISTANCE_THRESHOLD_3 = 3;
    private static final int RESISTANCE_THRESHOLD_5 = 5;
    private static final int RESISTANCE_THRESHOLD_6 = 6;
    private static final int SPEED_THRESHOLD = 20;
    private static final String TAG = "SportService_IntensityManager";
    private static final byte TYPE_UNKNOWN = 0;
    private Context mContext;
    private Map<Integer, Byte> mIntensityDataMap = new HashMap(10);
    private int mSportType = 0;
    private long mStartTimeBySecond = 0;

    public static boolean isNeedCadence(int i) {
        return i == 265 || i == 273;
    }

    @Override // com.huawei.health.sportservice.SportLifecycle
    public void onPreSport() {
        LogUtil.a(TAG, "onPreSport()");
        this.mContext = BaseApplication.e();
        this.mSportType = BaseSportManager.getInstance().getSportType();
    }

    /* JADX WARN: Code restructure failed: missing block: B:17:0x0037, code lost:
    
        if (isCalculateTotalData() != false) goto L30;
     */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x0060, code lost:
    
        r4 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x0044, code lost:
    
        if (isCalculateTotalData() != false) goto L30;
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x0051, code lost:
    
        if (isCalculateTotalData() != false) goto L30;
     */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x005e, code lost:
    
        if (isCalculateTotalData() != false) goto L30;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private boolean handleSportIntensity(java.util.List<defpackage.koc> r4, java.util.List<defpackage.ffn> r5, java.util.List<defpackage.knw> r6, java.util.List<java.lang.Integer> r7, java.util.List<defpackage.kob> r8) {
        /*
            r3 = this;
            int r0 = r3.mSportType
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)
            java.lang.String r1 = "handleSportIntensity() mSportType = "
            java.lang.Object[] r0 = new java.lang.Object[]{r1, r0}
            java.lang.String r1 = "SportService_IntensityManager"
            com.huawei.hwlogsmodel.LogUtil.a(r1, r0)
            int r0 = r3.mSportType
            r2 = 265(0x109, float:3.71E-43)
            if (r0 == r2) goto L54
            r7 = 283(0x11b, float:3.97E-43)
            if (r0 == r7) goto L47
            r7 = 273(0x111, float:3.83E-43)
            if (r0 == r7) goto L3a
            r5 = 274(0x112, float:3.84E-43)
            if (r0 == r5) goto L2d
            java.lang.String r4 = " sportType unknown"
            java.lang.Object[] r4 = new java.lang.Object[]{r4}
            com.huawei.hwlogsmodel.LogUtil.h(r1, r4)
            goto L62
        L2d:
            boolean r4 = r3.isHandleRowIntensity(r0, r4, r6)
            if (r4 == 0) goto L62
            boolean r4 = r3.isCalculateTotalData()
            if (r4 == 0) goto L62
            goto L60
        L3a:
            boolean r4 = r3.isHandleEllipticalIntensity(r0, r4, r5)
            if (r4 == 0) goto L62
            boolean r4 = r3.isCalculateTotalData()
            if (r4 == 0) goto L62
            goto L60
        L47:
            boolean r4 = r3.isHandleRopeSkippingIntensity(r0, r8)
            if (r4 == 0) goto L62
            boolean r4 = r3.isCalculateTotalData()
            if (r4 == 0) goto L62
            goto L60
        L54:
            boolean r4 = r3.isHandleBikeIntensity(r0, r4, r5, r7)
            if (r4 == 0) goto L62
            boolean r4 = r3.isCalculateTotalData()
            if (r4 == 0) goto L62
        L60:
            r4 = 1
            goto L63
        L62:
            r4 = 0
        L63:
            java.lang.String r5 = "handleSportIntensity() result is: "
            java.lang.Boolean r6 = java.lang.Boolean.valueOf(r4)
            java.lang.Object[] r5 = new java.lang.Object[]{r5, r6}
            com.huawei.hwlogsmodel.LogUtil.a(r1, r5)
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.health.sportservice.manager.IntensityManager.handleSportIntensity(java.util.List, java.util.List, java.util.List, java.util.List, java.util.List):boolean");
    }

    private boolean isHandleEllipticalIntensity(int i, List<koc> list, List<ffn> list2) {
        int i2;
        LogUtil.a(TAG, "Start to handle Elliptical Intensity");
        if (isHandlePowerIntensity(i, list)) {
            return true;
        }
        if (!koq.b(list2)) {
            LogUtil.a(TAG, "cadenceDataList.size() = ", Integer.valueOf(list2.size()));
            int i3 = 0;
            while (i3 < list2.size()) {
                int i4 = i3;
                int i5 = 0;
                while (true) {
                    i2 = i3 + 12;
                    if (i4 >= i2 || i4 >= list2.size()) {
                        break;
                    }
                    i5 += list2.get(i4).e();
                    i4++;
                }
                if (i5 / 12 > 110) {
                    this.mIntensityDataMap.put(Integer.valueOf(i3 / 12), Byte.valueOf((byte) i));
                }
                i3 = i2;
            }
            return true;
        }
        LogUtil.h(TAG, "handle Elliptical Intensity failed");
        return false;
    }

    private boolean isHandlePowerIntensity(int i, List<koc> list) {
        int i2;
        if (koq.b(list)) {
            return false;
        }
        LogUtil.a(TAG, "start to isHandlePowerIntensity, powerDataList.size() = ", Integer.valueOf(list.size()));
        int i3 = 0;
        while (i3 < list.size()) {
            int i4 = i3;
            int i5 = 0;
            while (true) {
                i2 = i3 + 12;
                if (i4 >= i2 || i4 >= list.size()) {
                    break;
                }
                i5 += list.get(i4).b();
                i4++;
            }
            if (i5 / 12 > 50) {
                this.mIntensityDataMap.put(Integer.valueOf(i3 / 12), Byte.valueOf((byte) i));
            }
            i3 = i2;
        }
        return true;
    }

    private boolean isCalculateTotalData() {
        LogUtil.a(TAG, " isCalculateTotalData");
        List<HiHealthData> uploadMinuteToList = setUploadMinuteToList();
        if (uploadMinuteToList.size() <= 0) {
            LogUtil.h(TAG, "sport intensity data null ");
            return true;
        }
        HiDataInsertOption hiDataInsertOption = new HiDataInsertOption();
        hiDataInsertOption.setDatas(uploadMinuteToList);
        LogUtil.a(TAG, "before insertHiHealthData size is ", Integer.valueOf(uploadMinuteToList.size()), "dataList = ", uploadMinuteToList.toString());
        HiHealthManager.d(this.mContext).insertHiHealthData(hiDataInsertOption, new InsertCallback());
        LogUtil.a(TAG, "after insertHiHealthData ");
        return true;
    }

    private List<HiHealthData> setUploadMinuteToList() {
        byte byteValue;
        ArrayList arrayList = new ArrayList(16);
        for (Map.Entry<Integer, Byte> entry : this.mIntensityDataMap.entrySet()) {
            if (entry != null && (byteValue = entry.getValue().byteValue()) != 0) {
                if (byteValue != 9 && byteValue != 27 && byteValue != 17 && byteValue != 18) {
                    LogUtil.b(TAG, " sportType unkown should not happen");
                    return arrayList;
                }
                HiHealthData hiHealthData = new HiHealthData();
                long intValue = (entry.getKey().intValue() * 60000) + this.mStartTimeBySecond;
                hiHealthData.setTimeInterval(intValue, 60000 + intValue);
                hiHealthData.setValue(8);
                hiHealthData.setType(7);
                hiHealthData.setDeviceUuid(fhs.c(this.mContext));
                arrayList.add(hiHealthData);
            }
        }
        return arrayList;
    }

    static class InsertCallback implements HiDataOperateListener {
        private InsertCallback() {
        }

        @Override // com.huawei.hihealth.data.listener.HiDataOperateListener
        public void onResult(int i, Object obj) {
            if (obj != null) {
                LogUtil.a(IntensityManager.TAG, "InsertCallBack errorCode = ", Integer.valueOf(i));
            } else {
                LogUtil.h(IntensityManager.TAG, "InsertCallBack errorCode = ", Integer.valueOf(i), " obj == null ");
            }
        }
    }

    private boolean isHandleRowIntensity(int i, List<koc> list, List<knw> list2) {
        int i2;
        LogUtil.a(TAG, "Start to handle Row Intensity ");
        if (isHandlePowerIntensity(i, list)) {
            return true;
        }
        if (!koq.b(list2)) {
            LogUtil.a(TAG, "paddleFrequencyList.size() = ", Integer.valueOf(list2.size()));
            int i3 = 0;
            while (i3 < list2.size()) {
                int i4 = i3;
                int i5 = 0;
                while (true) {
                    i2 = i3 + 12;
                    if (i4 >= i2 || i4 >= list2.size()) {
                        break;
                    }
                    i5 += (int) list2.get(i4).b();
                    i4++;
                }
                if (i5 / 12 > 25) {
                    this.mIntensityDataMap.put(Integer.valueOf(i3 / 12), Byte.valueOf((byte) i));
                }
                i3 = i2;
            }
            return true;
        }
        LogUtil.h(TAG, "handle Row Intensity failed");
        return false;
    }

    private boolean isHandleBikeIntensity(int i, List<koc> list, List<ffn> list2, List<Integer> list3) {
        int i2;
        LogUtil.a(TAG, "Start to handle Bike Intensity");
        if (isHandlePowerIntensity(i, list)) {
            return true;
        }
        if (!koq.b(list2) && !koq.b(list3)) {
            LogUtil.a(TAG, "cadenceDataList.size() = ", Integer.valueOf(list2.size()), "resistanceList.size() = ", Integer.valueOf(list3.size()));
            if (list3.size() * 12 > list2.size() + 11 || list3.size() * 12 < list2.size()) {
                LogUtil.h(TAG, "ParamInvalidException, resistanceList.size() does not match cadenceDataList.size() ");
                return false;
            }
            int i3 = 0;
            while (i3 < list2.size()) {
                int i4 = i3;
                int i5 = 0;
                while (true) {
                    i2 = i3 + 12;
                    if (i4 >= i2 || i4 >= list2.size()) {
                        break;
                    }
                    i5 += list2.get(i4).e();
                    i4++;
                }
                int i6 = i5 / 12;
                int i7 = i3 / 12;
                int intValue = list3.get(i7).intValue();
                if (((intValue == 1 || intValue == 2) && i6 > 80) || ((intValue >= 3 && intValue <= 5 && i6 > 60) || (intValue >= 6 && i6 > 30))) {
                    this.mIntensityDataMap.put(Integer.valueOf(i7), Byte.valueOf((byte) i));
                }
                i3 = i2;
            }
            return true;
        }
        LogUtil.h(TAG, "handle Bike Intensity failed");
        return false;
    }

    private boolean isHandleRopeSkippingIntensity(int i, List<kob> list) {
        int i2;
        LogUtil.a(TAG, "Start to handle RopeSkipping Intensity");
        if (!koq.b(list)) {
            int i3 = 0;
            while (i3 < list.size()) {
                int i4 = i3;
                int i5 = 0;
                while (true) {
                    i2 = i3 + 12;
                    if (i4 >= i2 || i4 >= list.size()) {
                        break;
                    }
                    i5 += list.get(i4).c();
                    i4++;
                }
                if (i5 / 12 > 20) {
                    this.mIntensityDataMap.put(Integer.valueOf(i3 / 12), Byte.valueOf((byte) i));
                }
                i3 = i2;
            }
            return true;
        }
        LogUtil.h(TAG, "handle RopeSkipping Intensity failed");
        return false;
    }

    @Override // com.huawei.health.sportservice.manager.BaseIntensityManager, com.huawei.health.sportservice.SportLifecycle
    public void onStartSport() {
        super.onStartSport();
        long currentTimeMillis = System.currentTimeMillis() - (System.currentTimeMillis() % 60000);
        this.mStartTimeBySecond = currentTimeMillis;
        LogUtil.a(TAG, "onStartSport() ", Long.valueOf(currentTimeMillis));
    }

    @Override // com.huawei.health.sportservice.manager.BaseIntensityManager, com.huawei.health.sportservice.SportLifecycle
    /* renamed from: onStopSport */
    public void m134x32b3e3a1() {
        LogUtil.a(TAG, "onStopSport()");
        super.m134x32b3e3a1();
        Object data = BaseSportManager.getInstance().getData(JsUtil.DataFunc.MOTION_PATH_DATA);
        if (data instanceof MotionPath) {
            if (fhs.e(this.mSportType)) {
                MotionPath motionPath = (MotionPath) data;
                handleSportIntensity(motionPath.requestPowerList(), motionPath.requestRidePostureDataList(), motionPath.requestPaddleFrequencyList(), motionPath.requestResistanceList(), null);
            } else if (!isSkip()) {
                LogUtil.h(TAG, "error mSportType: ", Integer.valueOf(this.mSportType));
            } else {
                handleSportIntensity(null, null, null, null, ((MotionPath) data).requestSkippingSpeedList());
            }
        }
    }

    private boolean isSkip() {
        return this.mSportType == 283;
    }
}
