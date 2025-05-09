package com.huawei.health.sportservice.datasource;

import com.huawei.health.device.datatype.IntermitentJumpData;
import com.huawei.health.sportservice.SportLaunchParams;
import com.huawei.health.sportservice.SportLifecycle;
import com.huawei.health.sportservice.constants.SportParamsType;
import com.huawei.health.sportservice.inter.IndoorToSource;
import com.huawei.health.sportservice.manager.BaseSportManager;
import com.huawei.health.sportservice.template.ComponentName;
import com.huawei.health.sportservice.template.SportComponentType;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.hwsmartinteractmgr.data.SmartMsgConstant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

@SportComponentType(classify = 3, name = ComponentName.SKIP_GROUP_SOURCE)
/* loaded from: classes8.dex */
public class SkipGroupSource extends BaseSource<Integer> implements SportLifecycle, IndoorToSource {
    private static final int ROPE_CONTROL_INTERMITTENT_REST = 2;
    private static final int ROPE_CONTROL_INTERMITTENT_SPORT = 1;
    private static final String TAG = "SportService_SkipGroupSource";
    private boolean isSendAccumulatedData;
    private int mActionGroup = 1;
    private int mCalorie;
    private IntermitentJumpData mIntermitentJumpData;
    private int mRopeStatus;
    private HashMap<Integer, Integer> mSkipCalorieMap;
    private int mSkipTimes;
    private HashMap<Integer, Integer> mSkipTimesMap;
    private int mSportTime;
    private HashMap<Integer, Integer> mSportTimeMap;
    private int mStumblingTimes;

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.huawei.health.sportservice.datasource.BaseSource
    public void generateData(Integer num) {
    }

    @Override // com.huawei.health.sportservice.datasource.BaseSource
    protected void updateSourceData() {
    }

    @Override // com.huawei.health.sportservice.SportLifecycle
    public void onPreSport() {
        SportLaunchParams sportLaunchParams = (SportLaunchParams) BaseSportManager.getInstance().getParas(SportParamsType.SPORT_LAUNCH_PARAS);
        if (sportLaunchParams != null) {
            this.mIntermitentJumpData = (IntermitentJumpData) sportLaunchParams.getExtra("type_intermittent_jump_mode_data", IntermitentJumpData.class);
        }
        ArrayList arrayList = new ArrayList();
        arrayList.add(Integer.valueOf(SmartMsgConstant.MSG_TYPE_RIDE_USER));
        arrayList.add(40002);
        arrayList.add(40001);
        if (this.mIntermitentJumpData != null) {
            arrayList.add(40011);
            arrayList.add(6);
            arrayList.add(40051);
            arrayList.add(40052);
        }
        BaseSportManager.getInstance().subscribeToIndoorEquipment(arrayList, this);
    }

    @Override // com.huawei.health.sportservice.inter.IndoorToSource
    public void onIndoorDataChanged(int i, Object obj) {
        if (i == 40002) {
            if (obj instanceof Integer) {
                if (this.mIntermitentJumpData != null || this.mStumblingTimes < ((Integer) obj).intValue()) {
                    this.mStumblingTimes = ((Integer) obj).intValue();
                    BaseSportManager.getInstance().stagingAndNotification("STUMBLING_ROPE_DATA", Integer.valueOf(this.mStumblingTimes));
                    return;
                }
                return;
            }
            return;
        }
        if (i != 40005) {
            if (i == 40011) {
                if (obj instanceof Integer) {
                    Integer num = (Integer) obj;
                    if (num.intValue() > 0) {
                        BaseSportManager.getInstance().stagingAndNotification("ROPE_INTERMITTENT_GROUP_NO_DATA", obj);
                    }
                    if (this.mActionGroup < num.intValue()) {
                        sendResetData();
                    }
                    this.mActionGroup = num.intValue();
                    return;
                }
                return;
            }
            onSplitIndoorDataChanged(i, obj);
            return;
        }
        if (obj instanceof Integer) {
            int intValue = ((Integer) obj).intValue();
            this.mRopeStatus = intValue;
            if (intValue == 1) {
                this.isSendAccumulatedData = false;
            }
            if (intValue == 2 && this.mIntermitentJumpData != null && !this.isSendAccumulatedData) {
                skipRestNotification();
            }
            BaseSportManager.getInstance().stagingAndNotification("ROPE_MACHINE_STATUS_DATA", obj);
        }
    }

    private void sendResetData() {
        LogUtil.a(TAG, "sendResetData");
        BaseSportManager.getInstance().stagingAndNotification("SKIP_NUM_DATA", 0);
        BaseSportManager.getInstance().stagingAndNotification("ROPE_INTERMITTENT_JUMP_ENERGY_DATA", 0);
        BaseSportManager.getInstance().stagingAndNotification("ROPE_INTERMITTENT_JUMP_SPEED_DATA", 0);
        BaseSportManager.getInstance().stagingAndNotification("STUMBLING_ROPE_DATA", 0);
        BaseSportManager.getInstance().stagingAndNotification("SKIPPING_INTERMITTENT_REST_TIME_DATA", 0);
        BaseSportManager.getInstance().stagingAndNotification("ROPE_MACHINE_STATUS_DATA", 1);
    }

    /* JADX WARN: Removed duplicated region for block: B:25:0x0060  */
    /* JADX WARN: Removed duplicated region for block: B:27:? A[ADDED_TO_REGION, REMOVE, RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void onSplitIndoorDataChanged(int r3, java.lang.Object r4) {
        /*
            r2 = this;
            r0 = 6
            if (r3 == r0) goto L70
            r0 = 40001(0x9c41, float:5.6053E-41)
            if (r3 == r0) goto L2f
            switch(r3) {
                case 40051: goto L17;
                case 40052: goto Ld;
                default: goto Lb;
            }
        Lb:
            goto L87
        Ld:
            com.huawei.health.sportservice.manager.BaseSportManager r3 = com.huawei.health.sportservice.manager.BaseSportManager.getInstance()
            java.lang.String r0 = "SKIPPING_INTERMITTENT_REST_TIME_DATA"
            r3.stagingAndNotification(r0, r4)
            goto L87
        L17:
            boolean r3 = r4 instanceof java.lang.Integer
            if (r3 != 0) goto L1c
            return
        L1c:
            r3 = r4
            java.lang.Integer r3 = (java.lang.Integer) r3
            int r3 = r3.intValue()
            r2.mSportTime = r3
            com.huawei.health.sportservice.manager.BaseSportManager r3 = com.huawei.health.sportservice.manager.BaseSportManager.getInstance()
            java.lang.String r0 = "SKIPPING_INTERMITTENT_SPORT_TIME_DATA"
            r3.stagingAndNotification(r0, r4)
            goto L87
        L2f:
            boolean r3 = r4 instanceof java.lang.Integer
            if (r3 != 0) goto L34
            return
        L34:
            com.huawei.health.device.datatype.IntermitentJumpData r3 = r2.mIntermitentJumpData
            if (r3 == 0) goto L4a
            int r3 = r2.mSkipTimes
            r0 = r4
            java.lang.Integer r0 = (java.lang.Integer) r0
            int r1 = r0.intValue()
            if (r3 == r1) goto L4a
            int r3 = r0.intValue()
            r2.mSkipTimes = r3
            goto L5b
        L4a:
            int r3 = r2.mSkipTimes
            java.lang.Integer r4 = (java.lang.Integer) r4
            int r0 = r4.intValue()
            if (r3 < r0) goto L55
            return
        L55:
            int r3 = r4.intValue()
            r2.mSkipTimes = r3
        L5b:
            int r3 = r2.mRopeStatus
            r4 = 2
            if (r3 == r4) goto L6f
            com.huawei.health.sportservice.manager.BaseSportManager r3 = com.huawei.health.sportservice.manager.BaseSportManager.getInstance()
            int r4 = r2.mSkipTimes
            java.lang.String r0 = "SKIP_NUM_DATA"
            java.lang.Integer r4 = java.lang.Integer.valueOf(r4)
            r3.stagingAndNotification(r0, r4)
        L6f:
            return
        L70:
            boolean r3 = r4 instanceof java.lang.Integer
            if (r3 != 0) goto L75
            return
        L75:
            r3 = r4
            java.lang.Integer r3 = (java.lang.Integer) r3
            int r3 = r3.intValue()
            r2.mCalorie = r3
            com.huawei.health.sportservice.manager.BaseSportManager r3 = com.huawei.health.sportservice.manager.BaseSportManager.getInstance()
            java.lang.String r0 = "ROPE_INTERMITTENT_JUMP_ENERGY_DATA"
            r3.stagingAndNotification(r0, r4)
        L87:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.health.sportservice.datasource.SkipGroupSource.onSplitIndoorDataChanged(int, java.lang.Object):void");
    }

    private void skipRestNotification() {
        this.isSendAccumulatedData = true;
        int intermittentJumpGroups = this.mIntermitentJumpData.getIntermittentJumpGroups();
        if (this.mSportTimeMap == null) {
            this.mSportTimeMap = new HashMap<>(intermittentJumpGroups);
        }
        if (this.mSkipTimesMap == null) {
            this.mSkipTimesMap = new HashMap<>(intermittentJumpGroups);
        }
        if (this.mSkipCalorieMap == null) {
            this.mSkipCalorieMap = new HashMap<>(intermittentJumpGroups);
        }
        this.mSportTimeMap.put(Integer.valueOf(this.mActionGroup), Integer.valueOf(this.mSportTime));
        this.mSkipTimesMap.put(Integer.valueOf(this.mActionGroup), Integer.valueOf(this.mSkipTimes));
        this.mSkipCalorieMap.put(Integer.valueOf(this.mActionGroup), Integer.valueOf(this.mCalorie));
        BaseSportManager.getInstance().stagingAndNotification("SKIPPING_ACCUMULATED_DURATION_DATA", Integer.valueOf(calculateAccumulatedData(this.mSportTimeMap)));
        BaseSportManager.getInstance().stagingAndNotification("SKIPPING_ACCUMULATED_COUNT_DATA", Integer.valueOf(calculateAccumulatedData(this.mSkipTimesMap)));
        BaseSportManager.getInstance().stagingAndNotification("ROPE_ACCUMULATED_ENERGY_DATA", Integer.valueOf(calculateAccumulatedData(this.mSkipCalorieMap)));
        LogUtil.a(TAG, "-mTotalSportTime: ", Integer.valueOf(calculateAccumulatedData(this.mSportTimeMap)), "-mTotalSkipTimes:", Integer.valueOf(calculateAccumulatedData(this.mSkipTimesMap)), "-mTotalCalorie:", Integer.valueOf(calculateAccumulatedData(this.mSkipCalorieMap)));
    }

    private int calculateAccumulatedData(HashMap<Integer, Integer> hashMap) {
        int i = 0;
        if (hashMap != null && hashMap.size() != 0) {
            Iterator<Integer> it = hashMap.values().iterator();
            while (it.hasNext()) {
                i += it.next().intValue();
            }
        }
        return i;
    }

    @Override // com.huawei.health.sportservice.datasource.BaseSource
    protected String getLogTag() {
        return TAG;
    }
}
