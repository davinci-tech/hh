package com.huawei.healthcloud.plugintrack.physicalfitness.h5;

import android.util.SparseArray;
import com.huawei.haf.application.BaseApplication;
import com.huawei.health.h5pro.service.anotation.H5ProMethod;
import com.huawei.health.h5pro.service.anotation.H5ProService;
import com.huawei.health.sport.utils.SportSupportUtil;
import com.huawei.hihealth.HiDataReadOption;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.api.HiHealthManager;
import com.huawei.hihealth.data.listener.HiDataReadResultListener;
import com.huawei.hihealth.data.model.HiTrackMetaData;
import com.huawei.hwcommonmodel.utils.DateFormatUtil;
import com.huawei.hwlogsmodel.LogUtil;
import defpackage.gso;
import defpackage.gyo;
import defpackage.gyp;
import defpackage.koq;
import defpackage.nrv;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@H5ProService(name = "physicalFitnessApi")
/* loaded from: classes.dex */
public class PhysicalFitnessH5Interface {
    private static final int RECORD_NUM = 30;
    private static final int RUN_REFERENCE_DISTANCE = 1000;
    private static final String TAG = "Track_PhysicalFitnessH5Interface";
    private static final int WALK_REFERENCE_DISTANCE = 3000;

    @H5ProMethod(name = "startPhysicalFitness")
    public static void startPhysicalFitness(int i, int i2, float f, PhysicalFitnessH5Callback<gyp> physicalFitnessH5Callback) {
        if (physicalFitnessH5Callback == null) {
            ReleaseLogUtil.d(TAG, "startPhysicalFitness failed, physicalFitnessCallBack is null");
            return;
        }
        ReleaseLogUtil.e(TAG, "startPhysicalFitness, sportType = ", Integer.valueOf(i), ", targetType = ", Integer.valueOf(i2), ", targetValue = ", Float.valueOf(f));
        if (!SportSupportUtil.a(i)) {
            physicalFitnessH5Callback.onFailure(-1, "this sportType is invalid");
            return;
        }
        gso.e().e(physicalFitnessH5Callback);
        gso.e().aTs_(0, gso.e().aTm_(i, i2, f, 1), null, BaseApplication.e(), null);
    }

    @H5ProMethod(name = "isSupportAiSport")
    public static void isSupportAiSport(int i, PhysicalFitnessH5Callback<Boolean> physicalFitnessH5Callback) {
        if (physicalFitnessH5Callback == null) {
            ReleaseLogUtil.d(TAG, "isSupportAiSport failed, physicalFitnessCallBack is null");
            return;
        }
        ReleaseLogUtil.e(TAG, "isSupportAiSport in, sportType = ", Integer.valueOf(i));
        boolean h = i == 283 ? SportSupportUtil.h() : false;
        if (SportSupportUtil.e(i)) {
            h = SportSupportUtil.i();
        }
        ReleaseLogUtil.e(TAG, "isSupportAiSport = ", Boolean.valueOf(h));
        physicalFitnessH5Callback.onSuccess(Boolean.valueOf(h));
    }

    @H5ProMethod(name = "acquireBestResult")
    public static void acquireBestResult(int i, float f, PhysicalFitnessH5Callback<gyo> physicalFitnessH5Callback) {
        if (physicalFitnessH5Callback == null) {
            ReleaseLogUtil.d(TAG, "acquireBestResult failed, physicalFitnessCallBack is null");
            return;
        }
        ReleaseLogUtil.e(TAG, "acquireBestResult start, sportType = ", Integer.valueOf(i), ", targetValue = ", Float.valueOf(f));
        if (!SportSupportUtil.c(i)) {
            physicalFitnessH5Callback.onFailure(-1, "this sportType is invalid");
            return;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(1, -1);
        long timeInMillis = calendar.getTimeInMillis();
        long currentTimeMillis = System.currentTimeMillis();
        ReleaseLogUtil.e(TAG, "acquireBestResult startTime = ", Long.valueOf(timeInMillis), ", endTime = ", Long.valueOf(currentTimeMillis));
        HiDataReadOption hiDataReadOption = new HiDataReadOption();
        int b = DateFormatUtil.b(timeInMillis);
        int b2 = DateFormatUtil.b(currentTimeMillis);
        hiDataReadOption.setTimeInterval(String.valueOf(b), String.valueOf(b2), HiDataReadOption.TimeFormatType.DATE_FORMAT_YYYY_MM_DD);
        int[] iArr = {i};
        if (i == 258 || i == 264) {
            iArr = new int[]{258, 264};
        }
        hiDataReadOption.setType(iArr);
        hiDataReadOption.setSortOrder(1);
        hiDataReadOption.setCount(30);
        acquireLastYearSportData(i, f * 1000.0f, hiDataReadOption, physicalFitnessH5Callback);
    }

    private static void acquireLastYearSportData(final int i, final float f, HiDataReadOption hiDataReadOption, final PhysicalFitnessH5Callback<gyo> physicalFitnessH5Callback) {
        HiHealthManager.d(BaseApplication.e()).fetchSequenceDataBySportType(hiDataReadOption, new HiDataReadResultListener() { // from class: com.huawei.healthcloud.plugintrack.physicalfitness.h5.PhysicalFitnessH5Interface.5
            @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
            public void onResultIntent(int i2, Object obj, int i3, int i4) {
            }

            @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
            public void onResult(Object obj, int i2, int i3) {
                List hiHealthData = PhysicalFitnessH5Interface.getHiHealthData(obj, i2);
                if (!koq.b(hiHealthData)) {
                    gyo historyBestResult = PhysicalFitnessH5Interface.getHistoryBestResult(i, f, hiHealthData);
                    ReleaseLogUtil.e(PhysicalFitnessH5Interface.TAG, "acquireBestResult historyBestResult = ", historyBestResult);
                    PhysicalFitnessH5Callback.this.onSuccess(historyBestResult);
                } else {
                    ReleaseLogUtil.d(PhysicalFitnessH5Interface.TAG, "getHiHealthData healthDataList is empty");
                    PhysicalFitnessH5Callback.this.onFailure(-1, "data is empty");
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static List<HiHealthData> getHiHealthData(Object obj, int i) {
        if (!(obj instanceof SparseArray)) {
            LogUtil.h(TAG, "getHiHealthData data is error");
            return Collections.emptyList();
        }
        SparseArray sparseArray = (SparseArray) obj;
        if (!koq.e(sparseArray.get(i), HiHealthData.class)) {
            LogUtil.h(TAG, "getHiHealthData dataArray not instanceof List");
            return Collections.emptyList();
        }
        return (List) sparseArray.get(i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static gyo getHistoryBestResult(int i, float f, List<HiHealthData> list) {
        gyo gyoVar = new gyo();
        float f2 = 0.0f;
        int i2 = 0;
        float f3 = 0.0f;
        for (HiHealthData hiHealthData : list) {
            if (hiHealthData != null) {
                HiTrackMetaData hiTrackMetaData = (HiTrackMetaData) nrv.b(hiHealthData.getMetaData(), HiTrackMetaData.class);
                if (!isInvalidTrack(hiTrackMetaData)) {
                    int totalDistance = hiTrackMetaData.getTotalDistance();
                    if (i == 257) {
                        if (totalDistance >= 3000) {
                            i2++;
                            f3 = acquirePace(hiTrackMetaData.getAvgPace(), f3);
                        }
                    } else if (totalDistance >= f) {
                        i2++;
                        if (totalDistance >= 1000) {
                            f2 = acquirePace(hiTrackMetaData.getBestPace(), f2);
                        } else {
                            f2 = acquirePace(hiTrackMetaData.getAvgPace(), f2);
                        }
                    }
                }
            }
        }
        if (i == 257) {
            gyoVar.d(f3);
        } else {
            gyoVar.d(f2);
        }
        gyoVar.e(i2);
        return gyoVar;
    }

    private static boolean isInvalidTrack(HiTrackMetaData hiTrackMetaData) {
        return hiTrackMetaData == null || hiTrackMetaData.getSportDataSource() == 2 || hiTrackMetaData.getAbnormalTrack() != 0 || hiTrackMetaData.getDuplicated() == 1;
    }

    private static float acquirePace(float f, float f2) {
        if (f == 0.0f || f2 == 0.0f) {
            return Math.max(f, f2);
        }
        return Math.min(f, f2);
    }
}
