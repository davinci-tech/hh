package com.huawei.hihealthservice.sync.syncdata.dictionary.statistics;

import android.content.Context;
import android.os.Process;
import android.util.SparseArray;
import com.apprichtap.haptic.base.PrebakedEffectId;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.HiSyncOption;
import com.huawei.hihealth.dictionary.HiHealthDictManager;
import com.huawei.hihealth.dictionary.model.HiHealthDictStat;
import com.huawei.hihealth.dictionary.model.HiHealthDictType;
import com.huawei.hihealth.dictionary.utils.DicDataTypeUtil;
import com.huawei.hihealth.util.HiJsonUtil;
import com.huawei.hihealthservice.sync.dataswitch.HealthStatisticsSwitch;
import com.huawei.hihealthservice.sync.syncdata.HiSyncBase;
import com.huawei.hihealthservice.sync.syncdata.dictionary.statistics.HealthStatisticsDownloadReq;
import com.huawei.hwcloudmodel.healthdatacloud.HealthDataCloudFactory;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.utils.ThermalCallback;
import com.huawei.hwdevice.phoneprocess.mgr.exercise.HwExerciseConstants;
import com.huawei.hwlogsmodel.LogUtil;
import defpackage.igo;
import defpackage.iis;
import defpackage.ijr;
import defpackage.isf;
import defpackage.ism;
import defpackage.ius;
import defpackage.iut;
import defpackage.iuz;
import defpackage.ivc;
import defpackage.iwe;
import defpackage.jbs;
import defpackage.koq;
import defpackage.lqi;
import defpackage.lql;
import health.compact.a.CommonUtil;
import health.compact.a.HiCommonUtil;
import health.compact.a.HiDateUtil;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* loaded from: classes7.dex */
public class HiSyncDictionaryDataStat implements HiSyncBase {
    private static final String TAG = "HiH_HiSyncDicDaStat";
    private Context mContext;
    private HealthStatisticsSwitch mConverter;
    private int mMainUserId;
    private HealthDataCloudFactory mParamsFactory;
    private HiSyncOption mSyncOption;
    private int mUpLoadFailCount = 0;
    private int mUploadCount = 0;
    private ijr mSyncTypeManager = ijr.d();
    private Set<Integer> mDownloadedTypes = new HashSet();
    private iis mDataClientManager = iis.d();
    private boolean mIsOverSea = iuz.i();

    @Override // com.huawei.hihealthservice.sync.syncdata.HiSyncBase
    public void pullDataByTime(long j, long j2) throws iut {
    }

    public HiSyncDictionaryDataStat(Context context, HiSyncOption hiSyncOption, int i) {
        this.mContext = context;
        this.mSyncOption = hiSyncOption;
        this.mMainUserId = i;
        this.mParamsFactory = jbs.a(context).d();
        this.mConverter = new HealthStatisticsSwitch(this.mContext);
        List<Integer> arrayList = new ArrayList<>(1);
        if (HiHealthDictManager.d(this.mContext).d(this.mSyncOption.getSyncDataType()) != null) {
            arrayList.add(Integer.valueOf(this.mSyncOption.getSyncDataType()));
        } else {
            arrayList = HiHealthDictManager.d(this.mContext).c();
        }
        if (koq.b(arrayList)) {
            ReleaseLogUtil.d(TAG, "allTypes is empty!");
            return;
        }
        Iterator<Integer> it = arrayList.iterator();
        while (it.hasNext()) {
            int intValue = it.next().intValue();
            if (intValue != DicDataTypeUtil.DataType.ACTIVE_HOUR.value() && intValue != DicDataTypeUtil.DataType.SPORT_GOAL_ACHIEVEMENT_DATA.value()) {
                HiHealthDictType d = HiHealthDictManager.d(this.mContext).d(intValue);
                if (d == null) {
                    ReleaseLogUtil.d(TAG, "dictTp=null,tPId=", Integer.valueOf(intValue));
                } else if (d.e() == 0) {
                    this.mDownloadedTypes.add(Integer.valueOf(intValue));
                }
            }
        }
    }

    @Override // com.huawei.hihealthservice.sync.syncdata.HiSyncBase
    public void pullDataByVersion() throws iut {
        SparseArray<Integer> bCF_;
        ReleaseLogUtil.e(TAG, "pullDataByVersion() begin!");
        ivc.c(1.0d, "SYNC_DICT_STAT_DOWNLOAD_PERCENT_MAX");
        if (this.mDownloadedTypes.isEmpty()) {
            ReleaseLogUtil.e(TAG, "mDownloadedTypes is empty pullDataByVersion() end");
            ivc.b(this.mContext);
            return;
        }
        long a2 = HiDateUtil.a(HiDateUtil.c(System.currentTimeMillis()));
        if (iuz.f()) {
            bCF_ = iuz.bCF_(1614528000000L, a2, 90);
        } else {
            bCF_ = iuz.bCF_(a2 - HwExerciseConstants.TEN_DAY_SECOND, a2, 90);
        }
        if (bCF_ == null || bCF_.size() == 0) {
            ReleaseLogUtil.e(TAG, "not data to sync");
            ivc.b(this.mContext);
        } else {
            downloadData(bCF_);
            ivc.b(this.mContext);
            ReleaseLogUtil.e(TAG, "pullDataByVersion() end!");
        }
    }

    /* renamed from: lambda$pullDataByVersion$0$com-huawei-hihealthservice-sync-syncdata-dictionary-statistics-HiSyncDictionaryDataStat, reason: not valid java name */
    /* synthetic */ void m598xe75c6ea5(SparseArray sparseArray) {
        if (!BaseApplication.isRunningForeground()) {
            ThermalCallback.getInstance().controlThreadLevel(Collections.singletonList(Integer.valueOf(Process.myTid())), 1);
        }
        try {
            try {
                downloadData(sparseArray);
            } catch (iut e) {
                ReleaseLogUtil.d(TAG, "exception happens ", e.getMessage());
            }
        } finally {
            ThermalCallback.getInstance().controlThreadLevel(Collections.singletonList(Integer.valueOf(Process.myTid())), 0);
        }
    }

    private void downloadData(SparseArray<Integer> sparseArray) throws iut {
        for (int size = sparseArray.size() - 1; size >= 0; size--) {
            int keyAt = sparseArray.keyAt(size);
            downloadHealthStatByTime(keyAt, sparseArray.get(keyAt).intValue());
        }
    }

    private void downloadHealthStatByTime(int i, int i2) throws iut {
        if (i > i2 || i <= 0) {
            ReleaseLogUtil.d(TAG, "downloadHealthStatByTime the time is not right");
            return;
        }
        ReleaseLogUtil.e(TAG, "downlHlhStatByTm sTm=", Integer.valueOf(i), ",eDay=", Integer.valueOf(i2));
        HealthStatisticsDownloadReq build = new HealthStatisticsDownloadReq.Builder().startTime(i).endTime(i2).dataSource(2).types(this.mDownloadedTypes).build();
        ReleaseLogUtil.e(TAG, "getHealthStatRsp:", build.toString());
        HealthStatisticsDownloadRsp healthStatisticsDownloadRsp = (HealthStatisticsDownloadRsp) lqi.d().d(build.getUrl(), this.mParamsFactory.getHeaders(), lql.b(this.mParamsFactory.getBody(build)), HealthStatisticsDownloadRsp.class);
        if (ius.a(healthStatisticsDownloadRsp, false)) {
            this.mSyncTypeManager.a(this.mMainUserId, PrebakedEffectId.RT_SPEED_UP, i2, 0L);
            saveHealthStatistics(healthStatisticsDownloadRsp.getData());
        }
    }

    private void saveHealthStatistics(Map<String, List<HealthStatistics>> map) {
        int transferHealthStatData;
        for (Map.Entry<String, List<HealthStatistics>> entry : map.entrySet()) {
            String key = entry.getKey();
            if (HiHealthDictManager.d(this.mContext).d(CommonUtil.h(key)) == null) {
                ReleaseLogUtil.d(TAG, key, " is not exist in dictionary configure file");
            } else {
                List<HealthStatistics> value = entry.getValue();
                if (koq.b(value)) {
                    ReleaseLogUtil.d(TAG, key, " no value");
                } else {
                    List<igo> d = this.mConverter.d(CommonUtil.h(key), value, this.mMainUserId);
                    if (iwe.d(this.mContext, "isInsertBatchStats", this.mMainUserId, false) && !iuz.b(d)) {
                        transferHealthStatData = isf.a(this.mContext).insertBatchDayStatTable(d);
                    } else {
                        transferHealthStatData = isf.a(this.mContext).transferHealthStatData(d);
                    }
                    ReleaseLogUtil.e(TAG, "result code = ", Integer.valueOf(transferHealthStatData));
                }
            }
        }
    }

    @Override // com.huawei.hihealthservice.sync.syncdata.HiSyncBase
    public void pushData() throws iut {
        ReleaseLogUtil.e(TAG, "pushData begin");
        if (!ism.m()) {
            ReleaseLogUtil.d(TAG, "dataPrivacy switch is closed ,can not pushData right now ,push end!");
            return;
        }
        int h = this.mDataClientManager.h(this.mMainUserId);
        if (h <= 0) {
            ReleaseLogUtil.d(TAG, "no statClient get, maybe no data need to push ,push end !");
            return;
        }
        List<Integer> arrayList = new ArrayList<>(1);
        if (HiHealthDictManager.d(this.mContext).d(this.mSyncOption.getSyncDataType()) != null) {
            arrayList.add(Integer.valueOf(this.mSyncOption.getSyncDataType()));
        } else {
            arrayList = HiHealthDictManager.d(this.mContext).c();
        }
        if (koq.b(arrayList)) {
            ReleaseLogUtil.d(TAG, "no dictionary stat data need to push ,push end !");
            return;
        }
        Iterator<Integer> it = arrayList.iterator();
        while (it.hasNext()) {
            int intValue = it.next().intValue();
            if (intValue != DicDataTypeUtil.DataType.ACTIVE_HOUR.value() && intValue != DicDataTypeUtil.DataType.SPORT_GOAL_ACHIEVEMENT_DATA.value()) {
                HiHealthDictType d = HiHealthDictManager.d(this.mContext).d(intValue);
                if (d == null) {
                    ReleaseLogUtil.d(TAG, "dictTp=null,tpId=", Integer.valueOf(intValue));
                } else if (d.e() != 1 && d.e() != 3) {
                    List<String> m = HiHealthDictManager.d(this.mContext).m(intValue);
                    if (HiCommonUtil.d(m)) {
                        ReleaseLogUtil.d(TAG, "tpId=", Integer.valueOf(intValue), " no statDt");
                    } else {
                        ArrayList arrayList2 = new ArrayList(m.size());
                        ArrayList arrayList3 = new ArrayList(m.size());
                        Iterator<String> it2 = m.iterator();
                        while (it2.hasNext()) {
                            int d2 = HiHealthDictManager.d(this.mContext).d(intValue, it2.next(), 0);
                            if (d2 != 0) {
                                arrayList2.add(Integer.valueOf(d2));
                                if (HiHealthDictManager.d(this.mContext).k(d2)) {
                                    arrayList3.add(Integer.valueOf(d2));
                                }
                            }
                        }
                        int i = this.mIsOverSea;
                        int[] iArr = {50, i};
                        int[] b = i != 0 ? CommonUtil.b(arrayList3) : CommonUtil.b(arrayList2);
                        String[] statFieldNameByStatType = this.mIsOverSea ? getStatFieldNameByStatType(arrayList3) : getStatFieldNameByStatType(arrayList2);
                        while (true) {
                            if (this.mUpLoadFailCount >= 2) {
                                break;
                            }
                            List<HiHealthData> e = iuz.e(this.mContext, h, b, statFieldNameByStatType, iArr);
                            if (koq.b(e)) {
                                ReleaseLogUtil.d(TAG, "noNeed statData push");
                                break;
                            } else if (uploadHealthStatistics(e, b)) {
                                iuz.d(this.mContext, e, b, h);
                            }
                        }
                        this.mUpLoadFailCount = 0;
                    }
                }
            }
        }
        ReleaseLogUtil.e(TAG, "pushData end");
    }

    private boolean uploadHealthStatistics(List<HiHealthData> list, int[] iArr) throws iut {
        if (!this.mIsOverSea) {
            int i = this.mUploadCount + 1;
            this.mUploadCount = i;
            iuz.e(i, this.mSyncOption.getSyncManual());
        } else {
            int i2 = this.mUploadCount + 1;
            this.mUploadCount = i2;
            if (i2 > 3) {
                this.mUpLoadFailCount += 2;
                return false;
            }
        }
        Map<String, List<HealthStatistics>> a2 = this.mConverter.a(list, iArr);
        if (a2.isEmpty()) {
            this.mUpLoadFailCount++;
            return false;
        }
        HealthStatisticsUploadReq healthStatisticsUploadReq = new HealthStatisticsUploadReq(a2, list.get(0).getTimeZone());
        while (true) {
            if (this.mUpLoadFailCount < 2) {
                if (!ius.a((HealthStatisticsUploadRsp) lqi.d().d(healthStatisticsUploadReq.getUrl(), this.mParamsFactory.getHeaders(), lql.b(this.mParamsFactory.getBody(healthStatisticsUploadReq)), HealthStatisticsUploadRsp.class), false)) {
                    this.mUpLoadFailCount++;
                } else {
                    ReleaseLogUtil.e(TAG, "upload success ! uploadCount is ", Integer.valueOf(this.mUploadCount), " types is ", iArr);
                    Object[] objArr = new Object[2];
                    objArr[0] = "stat is ";
                    objArr[1] = CommonUtil.aq() ? HiJsonUtil.e(a2) : null;
                    LogUtil.c(TAG, objArr);
                    return true;
                }
            } else {
                ReleaseLogUtil.e(TAG, "upload failed ! uploadCount is ", Integer.valueOf(this.mUploadCount), " types is ", iArr);
                Object[] objArr2 = new Object[2];
                objArr2[0] = "stat is ";
                objArr2[1] = CommonUtil.aq() ? HiJsonUtil.e(a2) : null;
                LogUtil.c(TAG, objArr2);
                return false;
            }
        }
    }

    private String[] getStatFieldNameByStatType(List<Integer> list) {
        ArrayList arrayList = new ArrayList(list.size());
        Iterator<Integer> it = list.iterator();
        while (it.hasNext()) {
            HiHealthDictStat a2 = HiHealthDictManager.d(this.mContext).a(it.next().intValue());
            if (a2 != null) {
                arrayList.add(a2.c());
            }
        }
        String[] strArr = new String[arrayList.size()];
        for (int i = 0; i < arrayList.size(); i++) {
            strArr[i] = (String) arrayList.get(i);
        }
        return strArr;
    }

    public String toString() {
        return getClass().getSimpleName();
    }
}
