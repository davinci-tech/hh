package com.huawei.hihealthservice.sync.syncdata.dictionary.detail;

import android.content.Context;
import android.util.SparseArray;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.HiSyncOption;
import com.huawei.hihealth.dictionary.HiHealthDictManager;
import com.huawei.hihealth.dictionary.model.HiHealthDictField;
import com.huawei.hihealth.dictionary.model.HiHealthDictType;
import com.huawei.hihealth.dictionary.utils.DicDataTypeUtil;
import com.huawei.hihealth.util.HiJsonUtil;
import com.huawei.hihealthservice.store.merge.HiDicSequenceMerge;
import com.huawei.hihealthservice.sync.dataswitch.HealthDataSwitch;
import com.huawei.hihealthservice.sync.dataswitch.MotionPathDataSwitch;
import com.huawei.hihealthservice.sync.syncdata.HiSyncBase;
import com.huawei.hihealthservice.sync.syncdata.dictionary.detail.GetSampleSequenceByVersionReq;
import com.huawei.hwcloudmodel.healthdatacloud.HealthDataCloudFactory;
import com.huawei.hwcloudmodel.healthdatacloud.model.AddHealthDataReq;
import com.huawei.hwcloudmodel.healthdatacloud.model.BaseMetaData;
import com.huawei.hwcloudmodel.healthdatacloud.model.GetHealthDataByTimeReq;
import com.huawei.hwcloudmodel.healthdatacloud.model.GetHealthDataByVersionReq;
import com.huawei.hwcloudmodel.healthdatacloud.model.GetHealthDataLatestReq;
import com.huawei.hwcloudmodel.healthdatacloud.model.GetHealthDataLatestRsp;
import com.huawei.hwcloudmodel.healthdatacloud.model.SampleSequence;
import com.huawei.hwcloudmodel.model.unite.AddHealthDataRsp;
import com.huawei.hwcloudmodel.model.unite.GetHealthDataByTimeRsp;
import com.huawei.hwcloudmodel.model.unite.GetHealthDataByVersionRsp;
import com.huawei.hwcloudmodel.model.unite.HealthDetail;
import com.huawei.hwcloudmodel.model.unite.ParamValidDetail;
import com.huawei.hwcloudmodel.model.unite.SyncKey;
import com.huawei.hwlogsmodel.LogUtil;
import defpackage.igq;
import defpackage.iis;
import defpackage.iiz;
import defpackage.ijr;
import defpackage.ikv;
import defpackage.isf;
import defpackage.ism;
import defpackage.isv;
import defpackage.ity;
import defpackage.ius;
import defpackage.iut;
import defpackage.iuy;
import defpackage.iuz;
import defpackage.ivc;
import defpackage.ivg;
import defpackage.ivu;
import defpackage.jbs;
import defpackage.koq;
import defpackage.lqi;
import defpackage.lql;
import health.compact.a.CommonUtil;
import health.compact.a.HiCommonUtil;
import health.compact.a.HiDateUtil;
import health.compact.a.LogAnonymous;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes7.dex */
public class HiSyncDictionaryDataDetail implements HiSyncBase {
    private static final String TAG = "HiH_HiSyncDictData";
    List<igq> localVersions;
    private Context mContext;
    private long mCurrentVersion;
    private iiz mDataSequenceManager;
    private HealthDataSwitch mHealthDataSwitch;
    private isf mHiHealthDataInsertStore;
    private jbs mHwCloudMgr;
    private int mMainUserId;
    private MotionPathDataSwitch mMotionPathDataSwitch;
    private isv mMotionPathDataSwitchHelper;
    private HealthDataCloudFactory mParamsFactory;
    private HiSyncOption mSyncOption;
    List<Integer> mSyncTypes;
    private ity mSyncVersionMgr;
    private int mUploadFailCount = 0;
    private int mUploadTotalCount = 0;
    private ijr mSyncAnchorMgr = ijr.d();

    @Override // com.huawei.hihealthservice.sync.syncdata.HiSyncBase
    public void pullDataByTime(long j, long j2) throws iut {
    }

    public HiSyncDictionaryDataDetail(Context context, HiSyncOption hiSyncOption, int i) {
        this.mContext = context;
        this.mSyncOption = hiSyncOption;
        this.mMainUserId = i;
        jbs a2 = jbs.a(context);
        this.mHwCloudMgr = a2;
        this.mParamsFactory = a2.d();
        this.mSyncVersionMgr = ity.a(this.mContext);
        this.mHiHealthDataInsertStore = isf.a(this.mContext);
        this.mHealthDataSwitch = new HealthDataSwitch(this.mContext);
        this.mMotionPathDataSwitch = new MotionPathDataSwitch(this.mContext);
        this.mMotionPathDataSwitchHelper = isv.b(context);
        this.mDataSequenceManager = iiz.a(this.mContext);
    }

    @Override // com.huawei.hihealthservice.sync.syncdata.HiSyncBase
    public void pullDataByVersion() throws iut {
        ReleaseLogUtil.e(TAG, "pullDataByVersion begin");
        ivc.c(20.0d, "SYNC_DICTIONARY_DATA_DOWNLOAD_PERCENT_MAX");
        this.mSyncTypes = new ArrayList(1);
        if (HiHealthDictManager.d(this.mContext).d(this.mSyncOption.getSyncDataType()) != null) {
            this.mSyncTypes.add(Integer.valueOf(this.mSyncOption.getSyncDataType()));
        } else {
            this.mSyncTypes = HiHealthDictManager.d(this.mContext).d();
        }
        LogUtil.c(TAG, "dictionary health types = ", this.mSyncTypes);
        List<SyncKey> a2 = this.mSyncVersionMgr.a(this.mSyncTypes);
        this.localVersions = this.mSyncAnchorMgr.b(this.mMainUserId, 0L, this.mSyncTypes);
        if (!HiCommonUtil.d(a2)) {
            ReleaseLogUtil.e(TAG, "pullDataByVersion syncKeys: ", HiJsonUtil.e(a2));
            double size = 1.0d / a2.size();
            for (SyncKey syncKey : a2) {
                ivc.b(this.mContext, 1.0d, size, 20.0d);
                if (syncKey.getType().intValue() == 30001) {
                    downloadSequenceDataByType(syncKey);
                } else if (!iuz.i(this.mContext, this.mMainUserId) && syncKey.getType().intValue() == DicDataTypeUtil.DataType.ALTITUDE_TYPE_SET.value()) {
                    ReleaseLogUtil.d(TAG, "ALTITUDE_TYPE_SET isFirstSync");
                } else if (!iuz.i(this.mContext, this.mMainUserId) && iuz.f13619a.containsKey(syncKey.getType())) {
                    ReleaseLogUtil.d(TAG, "FirstSync return ", syncKey.getType());
                    if (syncKey.getVersion().longValue() != 0) {
                        long f = HiDateUtil.f(System.currentTimeMillis());
                        downloadAllStatByTime(syncKey.getType().intValue(), iuz.bCF_(iuz.d(f, iuz.f13619a.get(syncKey.getType()).d()), f, 8));
                        if (!this.mSyncAnchorMgr.d(this.mMainUserId, syncKey.getType().intValue(), syncKey.getVersion().longValue(), 0L)) {
                            ReleaseLogUtil.d(TAG, "saveVersionToDB failed!", syncKey.getType());
                        }
                    }
                } else {
                    HiHealthDictType d = HiHealthDictManager.d(this.mContext).d(syncKey.getType().intValue());
                    if (d == null) {
                        ReleaseLogUtil.d(TAG, "dictType is null, syncKey is ", syncKey);
                    } else if (d.e() == 0) {
                        downloadPointDataByType(syncKey);
                    } else if (d.e() == 1) {
                        downloadSequenceDataByType(syncKey);
                    } else {
                        ReleaseLogUtil.d(TAG, "category error, syncKey is ", syncKey);
                    }
                }
            }
            ReleaseLogUtil.e(TAG, "pullDataByVersion end");
            return;
        }
        ReleaseLogUtil.d(TAG, "dictionary data not exist in cloud");
    }

    private void downloadSequenceDataByType(SyncKey syncKey) throws iut {
        LogUtil.c(TAG, "downloadSequenceDataByType key = ", syncKey);
        if (syncKey == null) {
            ReleaseLogUtil.d(TAG, "downloadSequenceDataByType syncKey is null");
            return;
        }
        int intValue = syncKey.getType().intValue();
        long longValue = syncKey.getVersion().longValue();
        if (longValue <= 0) {
            LogUtil.h(TAG, "downloadSequenceDataByType the maxVersion is not right , maxVersion is ", Long.valueOf(longValue));
            return;
        }
        igq oneSyncLocalVersion = getOneSyncLocalVersion(intValue);
        if (oneSyncLocalVersion == null) {
            downloadSequenceDataByVersion(new GetSampleSequenceByVersionReq.Builder().type(intValue).version(0L).dataType(this.mSyncOption.getSyncMethod()).build(), longValue);
        } else if (oneSyncLocalVersion.a() < longValue) {
            downloadSequenceDataByVersion(new GetSampleSequenceByVersionReq.Builder().type(intValue).version(oneSyncLocalVersion.a()).dataType(this.mSyncOption.getSyncMethod()).build(), longValue);
        } else {
            LogUtil.c(TAG, "do not need download data, DBversion is ", Long.valueOf(oneSyncLocalVersion.a()), ", maxVersion is ", Long.valueOf(longValue));
        }
    }

    private void downloadPointDataByType(SyncKey syncKey) throws iut {
        if (syncKey == null) {
            ReleaseLogUtil.d(TAG, "downloadPointDataByType syncKey is null");
            return;
        }
        int intValue = syncKey.getType().intValue();
        long longValue = syncKey.getVersion().longValue();
        LogUtil.c(TAG, "downloadOneTypeDataByVersion type is ", Integer.valueOf(intValue), ", maxVersion is ", Long.valueOf(longValue));
        if (longValue <= 0) {
            ReleaseLogUtil.d(TAG, "downloadOneTypeDataByVersion cloud has no such data, type is ", Integer.valueOf(intValue));
            return;
        }
        GetHealthDataByVersionReq getHealthDataByVersionReq = new GetHealthDataByVersionReq();
        getHealthDataByVersionReq.setDataType(Integer.valueOf(this.mSyncOption.getSyncMethod()));
        getHealthDataByVersionReq.setType(Integer.valueOf(intValue));
        igq oneSyncLocalVersion = getOneSyncLocalVersion(intValue);
        if (oneSyncLocalVersion == null) {
            LogUtil.c(TAG, " syncAnchorTable is null");
            getHealthDataByVersionReq.setVersion(0L);
            downloadPointDataByVersion(getHealthDataByVersionReq, longValue);
        } else if (oneSyncLocalVersion.a() < longValue) {
            getHealthDataByVersionReq.setVersion(oneSyncLocalVersion.a());
            downloadPointDataByVersion(getHealthDataByVersionReq, longValue);
        } else {
            LogUtil.c(TAG, " do not need downloadOneTypeDataByVersion data, type is ", Integer.valueOf(intValue), ", DBversion is ", Long.valueOf(oneSyncLocalVersion.a()), ", maxVersion is ", Long.valueOf(longValue));
        }
    }

    private void downloadSequenceDataByVersion(GetSampleSequenceByVersionReq getSampleSequenceByVersionReq, long j) throws iut {
        long downloadSequenceDataByRequest;
        ReleaseLogUtil.e(TAG, "performDownloadByVersion req = ", getSampleSequenceByVersionReq, " maxVersion = ", Long.valueOf(j));
        this.mCurrentVersion = getSampleSequenceByVersionReq.getVersion();
        do {
            downloadSequenceDataByRequest = downloadSequenceDataByRequest(getSampleSequenceByVersionReq);
            ReleaseLogUtil.e(TAG, "performDownloadByVersion downCurrentVersion = ", Long.valueOf(downloadSequenceDataByRequest), " maxVersion = ", Long.valueOf(j));
            if (downloadSequenceDataByRequest <= -1) {
                return;
            }
            if (!this.mSyncAnchorMgr.d(this.mMainUserId, getSampleSequenceByVersionReq.getType(), downloadSequenceDataByRequest, 0L)) {
                ReleaseLogUtil.d(TAG, "performDownloadByVersion saveVersionToDB failed ");
            }
            getSampleSequenceByVersionReq = getSampleSequenceByVersionReq.toBuilder().version(this.mCurrentVersion).dataType(this.mSyncOption.getSyncMethod()).build();
        } while (downloadSequenceDataByRequest < j);
    }

    private void downloadPointDataByVersion(GetHealthDataByVersionReq getHealthDataByVersionReq, long j) throws iut {
        long downloadPointDataByRequest;
        ReleaseLogUtil.e(TAG, " downloadPointDataByVersion rep = ", getHealthDataByVersionReq, ", maxVersion = ", Long.valueOf(j));
        int i = 0;
        do {
            downloadPointDataByRequest = downloadPointDataByRequest(getHealthDataByVersionReq);
            LogUtil.c(TAG, " downloadPointDataByVersion downCurrentVersion is ", Long.valueOf(downloadPointDataByRequest), " maxVersion is ", Long.valueOf(j));
            i++;
            if (downloadPointDataByRequest <= -1) {
                return;
            }
            if (!this.mSyncAnchorMgr.d(this.mMainUserId, getHealthDataByVersionReq.getType().intValue(), downloadPointDataByRequest, 0L)) {
                ReleaseLogUtil.d(TAG, "downloadPointDataByVersion saveVersionToDB failed!");
                return;
            }
            getHealthDataByVersionReq.setVersion(downloadPointDataByRequest);
            if (ism.h() && !iuz.f()) {
                ReleaseLogUtil.d(TAG, " downloadPointDataByVersion() background is running");
                return;
            } else if (!iuz.f() && i >= 20 && getHealthDataByVersionReq.getType().intValue() != DicDataTypeUtil.DataType.ALTITUDE_TYPE_SET.value()) {
                ReleaseLogUtil.d(TAG, " downloadPointDataByVersion() pullDataByVersion too many times");
                return;
            }
        } while (downloadPointDataByRequest < j);
    }

    private long downloadSequenceDataByRequest(GetSampleSequenceByVersionReq getSampleSequenceByVersionReq) throws iut {
        GetSampleSequenceByVersionRsp getSampleSequenceByVersionRsp = (GetSampleSequenceByVersionRsp) lqi.d().d(getSampleSequenceByVersionReq.getUrl(), this.mParamsFactory.getHeaders(), lql.b(this.mParamsFactory.getBody(getSampleSequenceByVersionReq)), GetSampleSequenceByVersionRsp.class);
        if (!ius.a(getSampleSequenceByVersionRsp, false)) {
            ReleaseLogUtil.d(TAG, "downloadSequenceDataByRequest SyncError checkCloudRsp");
            return -1L;
        }
        long currentVersion = getSampleSequenceByVersionRsp.getCurrentVersion();
        if (currentVersion <= this.mCurrentVersion) {
            ReleaseLogUtil.d(TAG, "downloadSequenceDataByRequest downloadVersion is ", Long.valueOf(currentVersion), " smaller than currentVersion ", Long.valueOf(this.mCurrentVersion));
            return -1L;
        }
        List<BaseMetaData> deleteInfos = getSampleSequenceByVersionRsp.getDeleteInfos();
        if (!HiCommonUtil.d(deleteInfos) && !iuz.f()) {
            deleteSequences(deleteInfos);
        }
        List<SampleSequence> detailInfos = getSampleSequenceByVersionRsp.getDetailInfos();
        if (HiCommonUtil.d(detailInfos)) {
            ReleaseLogUtil.d(TAG, "downloadSequenceDataByRequest cloudTracks is null or empty");
            this.mCurrentVersion = currentVersion;
            return currentVersion;
        }
        this.mCurrentVersion = currentVersion;
        saveSequenceData(detailInfos, false);
        return currentVersion;
    }

    private void deleteSequences(List<BaseMetaData> list) {
        boolean z;
        Context context;
        int i;
        ArrayList arrayList = new ArrayList(10);
        for (BaseMetaData baseMetaData : list) {
            ReleaseLogUtil.d(TAG, "deleteSequence ", baseMetaData.toString());
            List<Integer> b = iis.d().b(baseMetaData.getDeviceCode());
            if (HiCommonUtil.d(b)) {
                ReleaseLogUtil.d(TAG, "deleteSequence clientId error");
            } else {
                int[] iArr = {30001};
                List<HiHealthData> a2 = this.mDataSequenceManager.a(baseMetaData.getStartTime(), baseMetaData.getEndTime(), iArr, b, 0);
                if (a2 == null || a2.isEmpty()) {
                    ReleaseLogUtil.d(TAG, "deleteSequence not found");
                } else {
                    HiHealthData hiHealthData = a2.get(0);
                    try {
                        z = !ivu.i(this.mContext, iArr[0]) ? ivu.e(this.mContext, iArr[0]) : false;
                        try {
                            try {
                                int e = this.mDataSequenceManager.e(hiHealthData);
                                if (e <= 0) {
                                    ReleaseLogUtil.d(TAG, "deleteSequences() fail");
                                } else {
                                    hiHealthData.setUserId(this.mMainUserId);
                                    hiHealthData.setSyncStatus(2);
                                    hiHealthData.setType(hiHealthData.getType() + hiHealthData.getSubType());
                                    arrayList.add(hiHealthData);
                                }
                                if (z && e > 0) {
                                    ivu.j(this.mContext, iArr[0]);
                                }
                            } catch (Exception e2) {
                                e = e2;
                                ReleaseLogUtil.c(TAG, "deleteSequences Exception ", LogAnonymous.b((Throwable) e));
                                if (z) {
                                    context = this.mContext;
                                    i = iArr[0];
                                    ivu.c(context, i);
                                }
                            }
                        } catch (Throwable th) {
                            th = th;
                            if (z) {
                                ivu.c(this.mContext, iArr[0]);
                            }
                            throw th;
                        }
                    } catch (Exception e3) {
                        e = e3;
                        z = false;
                    } catch (Throwable th2) {
                        th = th2;
                        z = false;
                    }
                    if (z) {
                        context = this.mContext;
                        i = iArr[0];
                        ivu.c(context, i);
                    }
                }
            }
        }
        deleteAndDoMerge(arrayList, iis.d().e(this.mMainUserId));
        prepareAndDoHealthDataStat(arrayList);
    }

    private void deleteAndDoMerge(List<HiHealthData> list, List<Integer> list2) {
        HiDicSequenceMerge hiDicSequenceMerge = new HiDicSequenceMerge(this.mContext);
        if (HiCommonUtil.d(list)) {
            return;
        }
        for (HiHealthData hiHealthData : list) {
            hiDicSequenceMerge.merge(hiHealthData, hiHealthData.getClientId(), list2);
        }
    }

    private void prepareAndDoHealthDataStat(List<HiHealthData> list) {
        if (HiCommonUtil.d(list)) {
            return;
        }
        isf a2 = isf.a(this.mContext);
        a2.prepareRealTimeHealthDataStat(list);
        a2.doRealTimeHealthDataStat();
    }

    private long downloadPointDataByRequest(GetHealthDataByVersionReq getHealthDataByVersionReq) throws iut {
        GetHealthDataByVersionRsp a2 = this.mHwCloudMgr.a(getHealthDataByVersionReq);
        if (!ius.a(a2, false)) {
            ReleaseLogUtil.d(TAG, "downOneTypeDataOnce() SyncError checkCloudRsp");
            return -1L;
        }
        long currentVersion = a2.getCurrentVersion();
        if (currentVersion <= getHealthDataByVersionReq.getVersion()) {
            ReleaseLogUtil.d(TAG, "downloadPointDataByRequest downloadVersion is ", Long.valueOf(currentVersion), " smaller than currentVersion ", Long.valueOf(getHealthDataByVersionReq.getVersion()));
            return -1L;
        }
        List<HealthDetail> detailInfos = a2.getDetailInfos();
        if (HiCommonUtil.d(detailInfos)) {
            ReleaseLogUtil.d(TAG, " downOneTypeDataOnce() detailInfos is null or empty");
            return currentVersion;
        }
        if (!savePointData(detailInfos, getHealthDataByVersionReq.getType().intValue(), getHealthDataByVersionReq.getVersion())) {
            return -1L;
        }
        ivg.c().b(getHealthDataByVersionReq.getType().intValue(), new ikv(this.mContext.getPackageName()));
        return currentVersion;
    }

    /* JADX WARN: Code restructure failed: missing block: B:34:0x00a5, code lost:
    
        if (r5 != false) goto L35;
     */
    /* JADX WARN: Code restructure failed: missing block: B:36:0x00c4, code lost:
    
        if (r7 != r13.size()) goto L39;
     */
    /* JADX WARN: Code restructure failed: missing block: B:37:0x00c6, code lost:
    
        defpackage.ivg.c().a(r3, "sync download", new defpackage.ikv(r12.mContext.getPackageName()));
     */
    /* JADX WARN: Code restructure failed: missing block: B:38:0x00da, code lost:
    
        r12.mHiHealthDataInsertStore.prepareRealTimeHealthDataStat(r13);
        r12.mHiHealthDataInsertStore.doRealTimeHealthDataStat();
        com.huawei.hwlogsmodel.LogUtil.a(com.huawei.hihealthservice.sync.syncdata.dictionary.detail.HiSyncDictionaryDataDetail.TAG, "saveData end saveDetailTime =  ", java.lang.Long.valueOf(java.lang.System.currentTimeMillis() - r0));
     */
    /* JADX WARN: Code restructure failed: missing block: B:39:0x00f6, code lost:
    
        return;
     */
    /* JADX WARN: Code restructure failed: missing block: B:40:0x00b9, code lost:
    
        defpackage.ivu.c(com.huawei.haf.application.BaseApplication.e(), r3);
     */
    /* JADX WARN: Code restructure failed: missing block: B:43:0x00b7, code lost:
    
        if (r5 == false) goto L36;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void saveSequenceData(java.util.List<com.huawei.hwcloudmodel.healthdatacloud.model.SampleSequence> r13, boolean r14) throws defpackage.iut {
        /*
            Method dump skipped, instructions count: 259
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.hihealthservice.sync.syncdata.dictionary.detail.HiSyncDictionaryDataDetail.saveSequenceData(java.util.List, boolean):void");
    }

    /* JADX WARN: Code restructure failed: missing block: B:16:0x0067, code lost:
    
        if (r2 != false) goto L22;
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x007d, code lost:
    
        return true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x0078, code lost:
    
        defpackage.ivu.c(r6.mContext, r0);
     */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x0076, code lost:
    
        if (r2 == false) goto L23;
     */
    /* JADX WARN: Removed duplicated region for block: B:26:0x0082  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private boolean savePointData(java.util.List<com.huawei.hwcloudmodel.model.unite.HealthDetail> r7, int r8, long r9) throws defpackage.iut {
        /*
            r6 = this;
            java.lang.String r0 = " saveData()"
            java.lang.Object[] r0 = new java.lang.Object[]{r0}
            java.lang.String r1 = "HiH_HiSyncDictData"
            com.huawei.hwlogsmodel.LogUtil.c(r1, r0)
            iuu r0 = defpackage.iuu.e()
            java.util.Collections.sort(r7, r0)
            com.huawei.hihealthservice.sync.dataswitch.HealthDataSwitch r0 = r6.mHealthDataSwitch
            int r2 = r6.mMainUserId
            java.util.List r7 = r0.e(r7, r2, r9)
            boolean r9 = health.compact.a.HiCommonUtil.d(r7)
            r10 = 1
            if (r9 == 0) goto L2b
            java.lang.String r7 = "local data empty"
            java.lang.Object[] r7 = new java.lang.Object[]{r7}
            health.compact.a.hwlogsmodel.ReleaseLogUtil.d(r1, r7)
            return r10
        L2b:
            r9 = 0
            java.lang.Object r0 = r7.get(r9)
            com.huawei.hihealth.HiHealthData r0 = (com.huawei.hihealth.HiHealthData) r0
            int r0 = r0.getType()
            java.lang.Integer r2 = java.lang.Integer.valueOf(r0)
            int r3 = r7.size()
            java.lang.Integer r3 = java.lang.Integer.valueOf(r3)
            java.lang.String r4 = "local point data type is "
            java.lang.String r5 = ", size is "
            java.lang.Object[] r2 = new java.lang.Object[]{r4, r2, r5, r3}
            health.compact.a.hwlogsmodel.ReleaseLogUtil.e(r1, r2)
            android.content.Context r2 = r6.mContext     // Catch: java.lang.Throwable -> L6a java.lang.Exception -> L6c
            boolean r2 = defpackage.ivu.i(r2, r0)     // Catch: java.lang.Throwable -> L6a java.lang.Exception -> L6c
            if (r2 != 0) goto L5c
            android.content.Context r2 = r6.mContext     // Catch: java.lang.Throwable -> L6a java.lang.Exception -> L6c
            boolean r2 = defpackage.ivu.e(r2, r0)     // Catch: java.lang.Throwable -> L6a java.lang.Exception -> L6c
            goto L5d
        L5c:
            r2 = r9
        L5d:
            r6.savePointDataDb(r7, r8)     // Catch: java.lang.Exception -> L6d java.lang.Throwable -> L7e
            if (r2 == 0) goto L67
            android.content.Context r7 = r6.mContext     // Catch: java.lang.Exception -> L6d java.lang.Throwable -> L7e
            defpackage.ivu.j(r7, r0)     // Catch: java.lang.Exception -> L6d java.lang.Throwable -> L7e
        L67:
            if (r2 == 0) goto L7d
            goto L78
        L6a:
            r7 = move-exception
            goto L80
        L6c:
            r2 = r9
        L6d:
            java.lang.Object[] r7 = new java.lang.Object[r10]     // Catch: java.lang.Throwable -> L7e
            java.lang.String r8 = "savePointData Exception"
            r7[r9] = r8     // Catch: java.lang.Throwable -> L7e
            health.compact.a.hwlogsmodel.ReleaseLogUtil.c(r1, r7)     // Catch: java.lang.Throwable -> L7e
            if (r2 == 0) goto L7d
        L78:
            android.content.Context r7 = r6.mContext
            defpackage.ivu.c(r7, r0)
        L7d:
            return r10
        L7e:
            r7 = move-exception
            r9 = r2
        L80:
            if (r9 == 0) goto L87
            android.content.Context r8 = r6.mContext
            defpackage.ivu.c(r8, r0)
        L87:
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.hihealthservice.sync.syncdata.dictionary.detail.HiSyncDictionaryDataDetail.savePointData(java.util.List, int, long):boolean");
    }

    private void savePointDataDb(List<HiHealthData> list, int i) {
        this.mHiHealthDataInsertStore.saveSyncHealthDetailData(list, this.mMainUserId);
        if (HiHealthDictManager.d(this.mContext).o(i)) {
            this.mHiHealthDataInsertStore.prepareRealTimeHealthDataStat(list);
            this.mHiHealthDataInsertStore.doRealTimeHealthDataStat();
        }
    }

    private void downloadAllStatByTime(int i, SparseArray<Integer> sparseArray) throws iut {
        if (sparseArray == null || sparseArray.size() <= 0) {
            ReleaseLogUtil.d(TAG, "downloadAllStatByTime() downloadDaysMap is null, stop pullDataByVersion!");
            return;
        }
        for (int size = sparseArray.size() - 1; size >= 0; size--) {
            int keyAt = sparseArray.keyAt(size);
            downloadByTime(i, HiDateUtil.a(keyAt), HiDateUtil.a(sparseArray.get(keyAt).intValue()) + 86400000);
        }
    }

    public void downloadByTime(int i, long j, long j2) throws iut {
        if (j > j2 || j <= 0) {
            ReleaseLogUtil.d(TAG, "downloadByTime the time is not right");
            return;
        }
        ReleaseLogUtil.e(TAG, "downloadByTime is ", Integer.valueOf(i), ",", Long.valueOf(j), " ,", Long.valueOf(j2));
        GetHealthDataByTimeReq initByTimeRep = initByTimeRep(i, j, j2);
        GetHealthDataByTimeRsp e = this.mHwCloudMgr.e(initByTimeRep);
        if (!ius.a(e, false)) {
            ReleaseLogUtil.d(TAG, "pullDataByTime error");
            return;
        }
        List<HealthDetail> detailInfos = e.getDetailInfos();
        if (detailInfos == null || detailInfos.isEmpty()) {
            ReleaseLogUtil.d(TAG, "pullDataByTime data error");
        } else {
            if (savePointData(detailInfos, initByTimeRep.getType().intValue(), 0L)) {
                return;
            }
            ReleaseLogUtil.d(TAG, "pullDataByTime save data error");
        }
    }

    private GetHealthDataByTimeReq initByTimeRep(int i, long j, long j2) {
        GetHealthDataByTimeReq getHealthDataByTimeReq = new GetHealthDataByTimeReq();
        getHealthDataByTimeReq.setQueryType(2);
        getHealthDataByTimeReq.setDataType(2);
        getHealthDataByTimeReq.setStartTime(Long.valueOf(j));
        getHealthDataByTimeReq.setEndTime(Long.valueOf(j2));
        getHealthDataByTimeReq.setType(Integer.valueOf(i));
        return getHealthDataByTimeReq;
    }

    @Override // com.huawei.hihealthservice.sync.syncdata.HiSyncBase
    public void pushData() throws iut, IllegalArgumentException {
        ReleaseLogUtil.e(TAG, "pushData begin");
        if (!ism.m()) {
            ReleaseLogUtil.d(TAG, "dataPrivacy switch is closed, push end");
            return;
        }
        List<Integer> e = iis.d().e(this.mMainUserId);
        this.mSyncTypes = new ArrayList(1);
        if (HiHealthDictManager.d(this.mContext).d(this.mSyncOption.getSyncDataType()) != null) {
            this.mSyncTypes.add(Integer.valueOf(this.mSyncOption.getSyncDataType()));
        } else {
            this.mSyncTypes = HiHealthDictManager.d(this.mContext).c();
        }
        if (HiCommonUtil.d(e)) {
            ReleaseLogUtil.e(TAG, "no need upload data");
            if (this.mSyncTypes.size() == 1) {
                iuy.d(this.mContext, this.mMainUserId, this.mSyncTypes.get(0).intValue());
                return;
            }
            return;
        }
        ArrayList arrayList = new ArrayList(this.mSyncTypes.size());
        ArrayList arrayList2 = new ArrayList(this.mSyncTypes.size());
        Iterator<Integer> it = this.mSyncTypes.iterator();
        while (it.hasNext()) {
            int intValue = it.next().intValue();
            HiHealthDictType d = HiHealthDictManager.d(this.mContext).d(intValue);
            if (d == null) {
                ReleaseLogUtil.d(TAG, "dictType=null,typeId=", Integer.valueOf(intValue));
            } else if (d.e() == 1) {
                arrayList2.add(Integer.valueOf(intValue));
            } else if (d.e() != 0) {
                ReleaseLogUtil.d(TAG, "typeId needn't syncCloud,typeId=", Integer.valueOf(intValue));
            } else {
                arrayList.add(Integer.valueOf(intValue));
            }
        }
        uploadSampleSequenceData(e, arrayList2);
        uploadSamplePointData(e, arrayList);
        ReleaseLogUtil.e(TAG, "pushData end");
    }

    private void uploadSampleSequenceData(List<Integer> list, List<Integer> list2) throws iut {
        List<Integer> d = iiz.a(this.mContext).d();
        if (HiCommonUtil.d(d)) {
            return;
        }
        d.retainAll(list);
        ReleaseLogUtil.e(TAG, "uploadSampleSequenceData clientIds=", HiJsonUtil.e(d));
        if (HiCommonUtil.d(d)) {
            return;
        }
        ArrayList arrayList = new ArrayList(list2.size());
        ArrayList arrayList2 = new ArrayList(list2.size());
        Iterator<Integer> it = list2.iterator();
        while (it.hasNext()) {
            int intValue = it.next().intValue();
            HiHealthDictType d2 = HiHealthDictManager.d(this.mContext).d(intValue);
            if (d2 != null) {
                if (d2.g() == 0) {
                    arrayList.add(Integer.valueOf(intValue));
                } else {
                    arrayList2.add(Integer.valueOf(d2.g()));
                }
            }
        }
        List<Integer> e = iis.d().e(this.mMainUserId);
        boolean z = false;
        if (!HiCommonUtil.d(arrayList) && !HiCommonUtil.d(iiz.a(this.mContext).b(e, CommonUtil.b(arrayList), new int[]{0}, 2, 1))) {
            z = true;
        }
        boolean z2 = (HiCommonUtil.d(arrayList2) || z || HiCommonUtil.d(iiz.a(this.mContext).b(e, new int[]{30001}, CommonUtil.b(arrayList2), 2, 1))) ? z : true;
        Iterator<Integer> it2 = list2.iterator();
        while (it2.hasNext()) {
            int intValue2 = it2.next().intValue();
            if (z2) {
                iuy.d(this.mContext, this.mMainUserId, intValue2);
            }
            Iterator<Integer> it3 = d.iterator();
            while (it3.hasNext()) {
                uploadSequenceData(it3.next().intValue(), intValue2);
            }
        }
    }

    private void uploadSamplePointData(List<Integer> list, List<Integer> list2) throws iut {
        LogUtil.a(TAG, "uploadSamplePointData clientIds=", HiJsonUtil.e(list));
        if (list2.size() > 1) {
            list2 = iuz.c("type_id", 0);
            if (HiCommonUtil.d(list2)) {
                return;
            }
        }
        List<Integer> c = iuz.c("client_id", 0);
        if (HiCommonUtil.d(c)) {
            return;
        }
        LogUtil.a(TAG, "uploadSamplePointData syncClients=", HiJsonUtil.e(c));
        c.retainAll(list);
        if (HiCommonUtil.d(c)) {
            return;
        }
        ReleaseLogUtil.e(TAG, "uploadSamplePointData retainAll ", HiJsonUtil.e(c));
        Iterator<Integer> it = list2.iterator();
        while (it.hasNext()) {
            int intValue = it.next().intValue();
            List<HiHealthDictField> j = HiHealthDictManager.d(this.mContext).j(intValue);
            ArrayList arrayList = new ArrayList(j.size());
            ArrayList arrayList2 = new ArrayList(j.size());
            for (HiHealthDictField hiHealthDictField : j) {
                arrayList.add(Integer.valueOf(hiHealthDictField.c()));
                if (hiHealthDictField.g()) {
                    arrayList2.add(Integer.valueOf(hiHealthDictField.c()));
                }
            }
            Iterator<Integer> it2 = c.iterator();
            while (it2.hasNext()) {
                uploadPointData(it2.next().intValue(), arrayList, intValue, arrayList2);
            }
        }
    }

    private void uploadSequenceData(int i, int i2) throws iut, IllegalArgumentException {
        List<HiHealthData> b;
        this.localVersions = this.mSyncAnchorMgr.b(this.mMainUserId, 0L, this.mSyncTypes);
        igq oneSyncLocalVersion = getOneSyncLocalVersion(i2);
        long a2 = oneSyncLocalVersion != null ? oneSyncLocalVersion.a() : 0L;
        while (this.mUploadFailCount < 2) {
            HiHealthDictType d = HiHealthDictManager.d(this.mContext).d(i2);
            if (d != null) {
                ArrayList arrayList = new ArrayList(1);
                arrayList.add(Integer.valueOf(i));
                if (d.g() == 0) {
                    b = this.mDataSequenceManager.b(arrayList, new int[]{i2}, new int[]{0}, 0, 10);
                } else {
                    b = this.mDataSequenceManager.b(arrayList, new int[]{30001}, new int[]{d.g()}, 0, 5);
                }
                if (HiCommonUtil.d(b) || !uploadSequenceDataOnce(b, a2)) {
                    break;
                }
            } else {
                return;
            }
        }
        this.mUploadFailCount = 0;
    }

    private void uploadPointData(int i, List<Integer> list, int i2, List<Integer> list2) throws iut, IllegalArgumentException {
        this.localVersions = this.mSyncAnchorMgr.b(this.mMainUserId, 0L, this.mSyncTypes);
        igq oneSyncLocalVersion = getOneSyncLocalVersion(i2);
        long a2 = oneSyncLocalVersion != null ? oneSyncLocalVersion.a() : 0L;
        do {
            if (this.mUploadFailCount < 2) {
                List<HiHealthData> uploadDataList = getUploadDataList(i2, list, i, (200 / list.size()) * list.size());
                if (HiCommonUtil.d(uploadDataList)) {
                    LogUtil.a(TAG, "no china data to upload ", Integer.valueOf(i2), " clientId ", Integer.valueOf(i));
                    do {
                        if (this.mUploadFailCount < 2) {
                            long currentTimeMillis = System.currentTimeMillis();
                            List<HiHealthData> e = ivu.d(this.mContext, i2).e(i, list2, HiDateUtil.t(currentTimeMillis), HiDateUtil.f(currentTimeMillis));
                            if (HiCommonUtil.d(e)) {
                                LogUtil.c(TAG, "no oversea data to upload", Integer.valueOf(i2));
                            } else {
                                a2 = addHealthData(e, i, i2, true, a2);
                            }
                        }
                        this.mUploadFailCount = 0;
                        return;
                    } while (a2 != -1);
                    return;
                }
                a2 = addHealthData(uploadDataList, i, i2, false, a2);
                iuz.c(500);
            }
        } while (a2 != -1);
    }

    private List<HiHealthData> getUploadDataList(int i, List<Integer> list, int i2, int i3) {
        List<HiHealthData> c = ivu.d(this.mContext, i).c(i2, list, i3);
        if (HiCommonUtil.d(c)) {
            return new ArrayList();
        }
        ArrayList arrayList = new ArrayList(10);
        ArrayList arrayList2 = new ArrayList(c.size());
        for (HiHealthData hiHealthData : c) {
            String str = hiHealthData.getStartTime() + "_" + hiHealthData.getEndTime();
            if (!arrayList2.contains(str)) {
                List<HiHealthData> d = ivu.d(this.mContext, i).d(i2, list, hiHealthData.getStartTime(), hiHealthData.getEndTime());
                if (!HiCommonUtil.d(d)) {
                    arrayList.addAll(d);
                }
                arrayList2.add(str);
            }
        }
        return arrayList;
    }

    private void uploadSequenceDone(List<HiHealthData> list) {
        if (list == null || list.isEmpty()) {
            return;
        }
        int i = 0;
        for (HiHealthData hiHealthData : list) {
            long dataId = hiHealthData.getDataId();
            long j = hiHealthData.getLong("modified_time");
            if (this.mDataSequenceManager.d(dataId, j)) {
                i = this.mDataSequenceManager.a(dataId, j);
            }
            ReleaseLogUtil.e(TAG, "uploadSequenceDone sID=", Long.valueOf(dataId), " trackTm=", Long.valueOf(hiHealthData.getStartTime()), " mdfdTm=", HiDateUtil.m(j), " update res=", Integer.valueOf(i));
        }
    }

    private void updateDataSyncStatus(List<HiHealthData> list, int i) {
        for (HiHealthData hiHealthData : list) {
            ivu.d(this.mContext, i).e(hiHealthData.getDataId(), hiHealthData.getModifiedTime(), 1);
            hiHealthData.setUserId(this.mMainUserId);
            hiHealthData.setSyncStatus(1);
        }
        this.mHiHealthDataInsertStore.prepareRealTimeHealthDataStat(list, false);
        this.mHiHealthDataInsertStore.doRealTimeHealthDataStat(false);
    }

    private boolean uploadSequenceDataOnce(List<HiHealthData> list, long j) throws iut {
        if (!iuz.i()) {
            int i = this.mUploadTotalCount + 1;
            this.mUploadTotalCount = i;
            iuz.e(i, this.mSyncOption.getSyncManual());
        } else {
            int i2 = this.mUploadTotalCount + 1;
            this.mUploadTotalCount = i2;
            if (i2 > 10) {
                this.mUploadFailCount += 2;
                ReleaseLogUtil.d(TAG, "uploadSequenceDataOnce uploadTotalCount");
                return false;
            }
        }
        List<SampleSequence> e = this.mMotionPathDataSwitchHelper.e(list);
        if (HiCommonUtil.d(e)) {
            ReleaseLogUtil.d(TAG, "uploadTrackDataOnce cloudTrack is null or empty ");
            return false;
        }
        AddSampleSequenceReq addSampleSequenceReq = new AddSampleSequenceReq(e);
        addSampleSequenceReq.setLocalMaxVersion(Long.valueOf(j));
        while (this.mUploadFailCount < 2) {
            AddSampleSequenceRsp addSampleSequenceRsp = (AddSampleSequenceRsp) lqi.d().d(addSampleSequenceReq.getUrl(), this.mParamsFactory.getHeaders(), lql.b(this.mParamsFactory.getBody(addSampleSequenceReq)), AddSampleSequenceRsp.class);
            if (addSampleSequenceRsp != null && addSampleSequenceRsp.getResultCode().intValue() == 1001) {
                ParamValidDetail paramValidDetail = addSampleSequenceRsp.getParamValidDetail();
                iuz.b(addSampleSequenceRsp, paramValidDetail);
                if (paramValidDetail == null) {
                    this.mUploadFailCount++;
                } else {
                    iuz.b(list, paramValidDetail);
                    uploadSequenceDone(list);
                    sequenceDownloadMore(addSampleSequenceReq, addSampleSequenceRsp, list.get(0).getType());
                    ReleaseLogUtil.e(TAG, "uploadSequenceDataOnce failed ! resultCode is ", addSampleSequenceRsp.getResultCode(), ", resultDesc is ", addSampleSequenceRsp.getResultDesc());
                    return true;
                }
            } else if (!ius.a(addSampleSequenceRsp, false)) {
                this.mUploadFailCount++;
            } else {
                uploadSequenceDone(list);
                sequenceDownloadMore(addSampleSequenceReq, addSampleSequenceRsp, list.get(0).getType());
                ReleaseLogUtil.e(TAG, "uploadSequenceDataOnce OK ! uploadCount is ", Integer.valueOf(this.mUploadTotalCount), ", date size = ", Integer.valueOf(list.size()), ",type,", Integer.valueOf(list.get(0).getType()));
                return true;
            }
        }
        ReleaseLogUtil.e(TAG, "uploadSequenceDataOnce failed ! uploadCount is ", Integer.valueOf(this.mUploadTotalCount), ", date size = ", Integer.valueOf(list.size()), ",type,", Integer.valueOf(list.get(0).getType()));
        return false;
    }

    private void sequenceDownloadMore(AddSampleSequenceReq addSampleSequenceReq, AddSampleSequenceRsp addSampleSequenceRsp, int i) throws iut {
        ReleaseLogUtil.e(TAG, "sequence LocalMaxVersion=", addSampleSequenceReq.getLocalMaxVersion(), ",rsp=", Long.valueOf(addSampleSequenceRsp.getTimestamp()), ",isHasMore=", Boolean.valueOf(addSampleSequenceRsp.isHasMore()), ",cloudTp=", Integer.valueOf(i));
        if (addSampleSequenceRsp.isHasMore()) {
            SyncKey syncKey = new SyncKey();
            syncKey.setType(Integer.valueOf(i));
            syncKey.setVersion(Long.valueOf(addSampleSequenceRsp.getTimestamp()));
            syncKey.setDeviceCode(0L);
            downloadSequenceDataByType(syncKey);
            return;
        }
        if (this.mSyncAnchorMgr.d(this.mMainUserId, i, addSampleSequenceRsp.getTimestamp(), 0L)) {
            return;
        }
        ReleaseLogUtil.d(TAG, "sequence downloadMore saveVersionToDB failed!");
    }

    private long addHealthData(List<HiHealthData> list, int i, int i2, boolean z, long j) throws iut, IllegalArgumentException {
        if (z || !iuz.i()) {
            int i3 = this.mUploadTotalCount + 1;
            this.mUploadTotalCount = i3;
            iuz.e(i3, this.mSyncOption.getSyncManual());
        } else {
            int i4 = this.mUploadTotalCount + 1;
            this.mUploadTotalCount = i4;
            if (i4 > 5) {
                ReleaseLogUtil.e(TAG, "isOverSea mUploadTotalCount too much");
                this.mUploadFailCount += 2;
                return -1L;
            }
        }
        List<HealthDetail> d = this.mHealthDataSwitch.d(list, i, i2);
        AddHealthDataReq addHealthDataReq = new AddHealthDataReq();
        addHealthDataReq.setTimeZone(list.get(0).getTimeZone());
        addHealthDataReq.setDetailInfo(d);
        addHealthDataReq.setLocalMaxVersion(Long.valueOf(j));
        while (true) {
            int i5 = this.mUploadFailCount;
            if (i5 < 2) {
                AddHealthDataRsp d2 = this.mHwCloudMgr.d(addHealthDataReq);
                if (!ius.a(d2, false)) {
                    this.mUploadFailCount++;
                } else {
                    ReleaseLogUtil.e(TAG, "addDataToCloud OK! uploadCount=", Integer.valueOf(this.mUploadFailCount), " tp=", Integer.valueOf(i2), ",dtsz=", Integer.valueOf(list.size()), " cId=", Integer.valueOf(i));
                    updateDataSyncStatus(list, i2);
                    pointDownloadMore(addHealthDataReq, d2, i2);
                    return d2.getTimestamp().longValue();
                }
            } else {
                ReleaseLogUtil.e(TAG, "addDataToCloud failed! uploadCount", Integer.valueOf(i5), " tp=", Integer.valueOf(i2), ", date sz=", Integer.valueOf(list.size()), " clientId ", Integer.valueOf(i));
                return -1L;
            }
        }
    }

    private void pointDownloadMore(AddHealthDataReq addHealthDataReq, AddHealthDataRsp addHealthDataRsp, int i) throws iut {
        ReleaseLogUtil.e(TAG, "point LocalMaxVersion=", addHealthDataReq.getLocalMaxVersion(), ",rsp=", addHealthDataRsp.getTimestamp(), ",isHasMore=", Boolean.valueOf(addHealthDataRsp.isHasMore()), ",cloudTp=", Integer.valueOf(i));
        if (addHealthDataRsp.isHasMore()) {
            SyncKey syncKey = new SyncKey();
            syncKey.setType(Integer.valueOf(i));
            syncKey.setVersion(addHealthDataRsp.getTimestamp());
            downloadPointDataByType(syncKey);
            return;
        }
        if (this.mSyncAnchorMgr.d(this.mMainUserId, i, addHealthDataRsp.getTimestamp().longValue(), 0L)) {
            return;
        }
        ReleaseLogUtil.d(TAG, "point downloadMore saveVersionToDB failed!");
    }

    public igq getOneSyncLocalVersion(int i) {
        if (HiCommonUtil.d(this.localVersions)) {
            return null;
        }
        for (igq igqVar : this.localVersions) {
            if (i == igqVar.e()) {
                return igqVar;
            }
        }
        return null;
    }

    public String toString() {
        return getClass().getSimpleName();
    }

    public void downloadLatestSequenceData(int i, int i2, int i3) throws iut {
        ReleaseLogUtil.e(TAG, "downloadLatestData enter");
        GetSampleSequenceLatestReq getSampleSequenceLatestReq = new GetSampleSequenceLatestReq();
        getSampleSequenceLatestReq.setType(i3);
        getSampleSequenceLatestReq.setDataType(2);
        if (i3 == DicDataTypeUtil.DataType.SLEEP_DETAILS.value()) {
            getSampleSequenceLatestReq.setTimestamp(HiDateUtil.o(System.currentTimeMillis()) + 1);
        } else {
            getSampleSequenceLatestReq.setTimestamp(HiDateUtil.g(System.currentTimeMillis()));
        }
        if (i > 0) {
            getSampleSequenceLatestReq.setDays(i);
        }
        if (i2 > 0) {
            getSampleSequenceLatestReq.setCounts(i2);
        }
        GetSampleSequenceLatestRsp getSampleSequenceLatestRsp = (GetSampleSequenceLatestRsp) lqi.d().d(getSampleSequenceLatestReq.getUrl(), this.mParamsFactory.getHeaders(), lql.b(this.mParamsFactory.getBody(getSampleSequenceLatestReq)), GetSampleSequenceLatestRsp.class);
        if (!ius.a(getSampleSequenceLatestRsp, false)) {
            ReleaseLogUtil.d(TAG, "downloadLatestData warning");
            return;
        }
        List<SampleSequence> detailInfos = getSampleSequenceLatestRsp.getDetailInfos();
        if (koq.b(detailInfos)) {
            ReleaseLogUtil.d(TAG, "downloadLatestData dataList is Empty");
            return;
        }
        saveSequenceData(detailInfos, true);
        if (i3 == 30001) {
            ivg.c().a(4, "latestDataDownload", new ikv(this.mContext.getPackageName()));
        }
    }

    public void downloadLatestPointData(int i, int i2, int i3) throws iut {
        ReleaseLogUtil.e(TAG, "downloadLatestData enter");
        GetHealthDataLatestReq getHealthDataLatestReq = new GetHealthDataLatestReq();
        getHealthDataLatestReq.setType(i3);
        getHealthDataLatestReq.setDataType(2);
        getHealthDataLatestReq.setTimestamp(HiDateUtil.g(System.currentTimeMillis()));
        if (i > 0) {
            getHealthDataLatestReq.setDays(i);
        }
        if (i2 > 0) {
            getHealthDataLatestReq.setCounts(i2);
        }
        GetHealthDataLatestRsp c = this.mHwCloudMgr.c(getHealthDataLatestReq);
        if (!ius.a(c, false)) {
            ReleaseLogUtil.d(TAG, "downloadLatestData warning");
            return;
        }
        List<HealthDetail> detailInfos = c.getDetailInfos();
        if (koq.b(detailInfos)) {
            ReleaseLogUtil.d(TAG, "downloadLatestData dataList is Empty");
        } else {
            savePointData(detailInfos, i3, 0L);
            ivg.c().a(ivg.d(i3), "latestDataDownload", new ikv(this.mContext.getPackageName()));
        }
    }
}
