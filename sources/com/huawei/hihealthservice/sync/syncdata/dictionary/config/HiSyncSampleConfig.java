package com.huawei.hihealthservice.sync.syncdata.dictionary.config;

import android.content.Context;
import com.huawei.hihealth.HiSampleConfig;
import com.huawei.hihealth.HiSampleConfigKey;
import com.huawei.hihealth.HiSyncOption;
import com.huawei.hihealthservice.sync.syncdata.HiSyncBase;
import com.huawei.hihealthservice.sync.syncdata.dictionary.config.CloudSampleConfig;
import com.huawei.hwcloudmodel.healthdatacloud.HealthDataCloudFactory;
import com.huawei.hwcloudmodel.model.unite.SyncKey;
import com.huawei.hwlogsmodel.LogUtil;
import defpackage.igq;
import defpackage.iis;
import defpackage.ijq;
import defpackage.ijr;
import defpackage.iku;
import defpackage.ikv;
import defpackage.ity;
import defpackage.ius;
import defpackage.iut;
import defpackage.iuz;
import defpackage.ivc;
import defpackage.ivg;
import defpackage.jbs;
import defpackage.lqi;
import defpackage.lql;
import health.compact.a.HiCommonUtil;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes7.dex */
public class HiSyncSampleConfig implements HiSyncBase {
    private static final String TAG = "HiH_HiSyncSampCfg";
    private final Context mContext;
    private final int mMainUserId;
    private final HealthDataCloudFactory mParamsFactory;
    private final HiSyncOption mSyncOption;
    private final ity mSyncVersionMgr;
    private int mUploadFailCount = 0;
    private int mUploadTotalCount = 0;
    private final ijr mSyncAnchorMgr = ijr.d();

    @Override // com.huawei.hihealthservice.sync.syncdata.HiSyncBase
    public void pullDataByTime(long j, long j2) throws iut {
    }

    public HiSyncSampleConfig(Context context, HiSyncOption hiSyncOption, int i) {
        this.mContext = context;
        this.mSyncOption = hiSyncOption;
        this.mMainUserId = i;
        this.mParamsFactory = jbs.a(context).d();
        this.mSyncVersionMgr = ity.a(context);
    }

    @Override // com.huawei.hihealthservice.sync.syncdata.HiSyncBase
    public void pullDataByVersion() throws iut {
        ReleaseLogUtil.e(TAG, "pullDataByVersion begin");
        ivc.c(1.0d, "SYNC_CONFIG_DOWNLOAD_PERCENT_MAX");
        List<SyncKey> d = this.mSyncVersionMgr.d(900000000);
        if (HiCommonUtil.d(d)) {
            ReleaseLogUtil.d(TAG, "dictionary data not exist in cloud");
            ivc.b(this.mContext);
        } else {
            downloadSampleConfig(d.get(0));
            ivc.b(this.mContext);
            ReleaseLogUtil.e(TAG, "pullDataByVersion end");
        }
    }

    private void downloadSampleConfig(SyncKey syncKey) throws iut {
        if (syncKey == null) {
            return;
        }
        long longValue = syncKey.getVersion().longValue();
        if (longValue <= 0) {
            ReleaseLogUtil.d(TAG, "downloadSampleConfig cloud has no such data");
            return;
        }
        GetSampleConfigByVersionReq getSampleConfigByVersionReq = new GetSampleConfigByVersionReq();
        igq c = this.mSyncAnchorMgr.c(this.mMainUserId, 0L, 900000000);
        if (c == null) {
            LogUtil.a(TAG, " syncAnchorTable is null");
            getSampleConfigByVersionReq.setVersion(0L);
            downloadSampleConfigByVersion(getSampleConfigByVersionReq, longValue);
        } else if (c.a() < longValue) {
            getSampleConfigByVersionReq.setVersion(c.a());
            downloadSampleConfigByVersion(getSampleConfigByVersionReq, longValue);
        } else {
            LogUtil.a(TAG, " do not need downloadSampleConfig, DBversion is ", Long.valueOf(c.a()), ", cloudVersion is ", Long.valueOf(longValue));
        }
    }

    private void downloadSampleConfigByVersion(GetSampleConfigByVersionReq getSampleConfigByVersionReq, long j) throws iut {
        long downloadSampleConfigByRequest;
        do {
            downloadSampleConfigByRequest = downloadSampleConfigByRequest(getSampleConfigByVersionReq);
            ReleaseLogUtil.e(TAG, " downloadSampleConfigByVersion downCurrentVersion is ", Long.valueOf(downloadSampleConfigByRequest), " cloudVersion is ", Long.valueOf(j));
            if (downloadSampleConfigByRequest <= -1) {
                return;
            }
            if (!this.mSyncAnchorMgr.d(this.mMainUserId, 900000000, downloadSampleConfigByRequest, 0L)) {
                ReleaseLogUtil.d(TAG, "downloadSampleConfigByVersion saveVersionToDB failed!");
                return;
            }
            getSampleConfigByVersionReq.setVersion(downloadSampleConfigByRequest);
        } while (downloadSampleConfigByRequest < j);
    }

    private long downloadSampleConfigByRequest(GetSampleConfigByVersionReq getSampleConfigByVersionReq) throws iut {
        GetSampleConfigByVersionRsp getSampleConfigByVersionRsp = (GetSampleConfigByVersionRsp) lqi.d().d(getSampleConfigByVersionReq.getUrl(), this.mParamsFactory.getHeaders(), lql.b(this.mParamsFactory.getBody(getSampleConfigByVersionReq)), GetSampleConfigByVersionRsp.class);
        if (!ius.a(getSampleConfigByVersionRsp, false)) {
            ReleaseLogUtil.d(TAG, "downloadSampleConfigByRequest SyncError checkCloudRsp");
            return -1L;
        }
        List<CloudSampleConfig> infoList = getSampleConfigByVersionRsp.getInfoList();
        if (HiCommonUtil.d(infoList)) {
            ReleaseLogUtil.d(TAG, "downloadSampleConfigByRequest detailInfos is null or empty");
            return -1L;
        }
        long currentVersion = getSampleConfigByVersionRsp.getCurrentVersion();
        if (currentVersion <= getSampleConfigByVersionReq.getVersion().longValue()) {
            ReleaseLogUtil.d(TAG, "downloadSampleConfigByRequest downloadVersion is ", Long.valueOf(currentVersion), " smaller than currentVersion ", getSampleConfigByVersionReq.getVersion());
            return -1L;
        }
        if (saveSampleConfig(infoList)) {
            return currentVersion;
        }
        return -1L;
    }

    private boolean saveSampleConfig(List<CloudSampleConfig> list) throws iut {
        List<HiSampleConfig> cloudSampleConfigToLocal = cloudSampleConfigToLocal(list);
        if (HiCommonUtil.d(cloudSampleConfigToLocal)) {
            ReleaseLogUtil.d(TAG, "localSampleConfigList empty");
            return false;
        }
        ReleaseLogUtil.e(TAG, "localSampleConfigList size is ", Integer.valueOf(cloudSampleConfigToLocal.size()));
        for (HiSampleConfig hiSampleConfig : cloudSampleConfigToLocal) {
            if (hiSampleConfig == null) {
                LogUtil.h(TAG, "localSampleConfig is null");
            } else {
                ijq.e().b(this.mMainUserId, hiSampleConfig);
                ivg.c().a(103, hiSampleConfig.getConfigId(), new ikv(this.mContext.getPackageName()));
            }
        }
        return true;
    }

    private List<HiSampleConfig> cloudSampleConfigToLocal(List<CloudSampleConfig> list) throws iut {
        ArrayList arrayList = new ArrayList(list.size());
        for (CloudSampleConfig cloudSampleConfig : list) {
            if (cloudSampleConfig != null) {
                ikv e = iku.a(this.mContext).e(iuz.d(this.mContext), this.mMainUserId, cloudSampleConfig.getDeviceCode(), true);
                if (e == null) {
                    ReleaseLogUtil.e(TAG, "HealthContext is null, deviceCode is ", Long.valueOf(cloudSampleConfig.getDeviceCode()));
                } else {
                    arrayList.add(new HiSampleConfig.Builder().j(cloudSampleConfig.getType()).d(cloudSampleConfig.getConfigId()).h(cloudSampleConfig.getScopeApp()).g(cloudSampleConfig.getScopeDeviceType()).b(cloudSampleConfig.getConfigData()).a(cloudSampleConfig.getConfigDescription()).e(cloudSampleConfig.getMetaData()).c(cloudSampleConfig.getModifyTime()).a(e.b()).e(1).c());
                }
            }
        }
        return arrayList;
    }

    @Override // com.huawei.hihealthservice.sync.syncdata.HiSyncBase
    public void pushData() throws iut, IllegalArgumentException {
        ReleaseLogUtil.e(TAG, "pushData begin");
        List<Integer> i = iis.d().i(this.mMainUserId);
        if (!HiCommonUtil.d(i)) {
            uploadSampleConfig(i);
        } else {
            ReleaseLogUtil.d(TAG, "no client get, maybe no data need to pushData");
        }
        deleteSampleConfig();
        ReleaseLogUtil.e(TAG, "pushData end");
    }

    private void uploadSampleConfig(List<Integer> list) throws iut, IllegalArgumentException {
        long uploadSampleConfigOnce;
        do {
            if (this.mUploadFailCount < 2) {
                List<HiSampleConfig> d = ijq.e().d(list, null, 0, 10);
                if (!HiCommonUtil.d(d)) {
                    uploadSampleConfigOnce = uploadSampleConfigOnce(d);
                    if (uploadSampleConfigOnce == 0) {
                        ReleaseLogUtil.d(TAG, "cloudVersion is zero!");
                    }
                }
            }
            this.mUploadFailCount = 0;
            return;
        } while (this.mSyncAnchorMgr.d(this.mMainUserId, 900000000, uploadSampleConfigOnce, 0L));
        ReleaseLogUtil.d(TAG, "downloadSampleConfigByVersion saveVersionToDB failed!");
    }

    private long uploadSampleConfigOnce(List<HiSampleConfig> list) throws iut, IllegalArgumentException {
        if (!iuz.i()) {
            int i = this.mUploadTotalCount + 1;
            this.mUploadTotalCount = i;
            iuz.e(i, this.mSyncOption.getSyncManual());
        } else {
            int i2 = this.mUploadTotalCount + 1;
            this.mUploadTotalCount = i2;
            if (i2 > 5) {
                ReleaseLogUtil.e(TAG, "isOverSea mUploadTotalCount too much");
                this.mUploadFailCount += 2;
                return 0L;
            }
        }
        List<CloudSampleConfig> localSampleConfigToCloud = localSampleConfigToCloud(list);
        SetSampleConfigReq setSampleConfigReq = new SetSampleConfigReq();
        setSampleConfigReq.setmInfoList(localSampleConfigToCloud);
        SetSampleConfigRsp setSampleConfigRsp = new SetSampleConfigRsp();
        while (this.mUploadFailCount < 2) {
            setSampleConfigRsp = (SetSampleConfigRsp) lqi.d().d(setSampleConfigReq.getUrl(), this.mParamsFactory.getHeaders(), lql.b(this.mParamsFactory.getBody(setSampleConfigReq)), SetSampleConfigRsp.class);
            if (setSampleConfigRsp != null && !HiCommonUtil.d(setSampleConfigRsp.getFailResult())) {
                getSuccessSampleConfig(list, setSampleConfigRsp);
                Iterator<HiSampleConfig> it = list.iterator();
                while (it.hasNext()) {
                    ijq.e().a(it.next().getId(), 1);
                }
                ReleaseLogUtil.e(TAG, "uploadSampleConfig failed ! resultCode is ", setSampleConfigRsp.getResultCode(), ", resultDesc is ", setSampleConfigRsp.getResultDesc());
                this.mUploadFailCount++;
            } else if (!ius.a(setSampleConfigRsp, false)) {
                this.mUploadFailCount++;
            } else {
                Iterator<HiSampleConfig> it2 = list.iterator();
                while (it2.hasNext()) {
                    ijq.e().a(it2.next().getId(), 1);
                }
                ReleaseLogUtil.e(TAG, "uploadSampleConfig OK ! size = ", Integer.valueOf(localSampleConfigToCloud.size()));
                return setSampleConfigRsp.getTimestamp();
            }
        }
        ReleaseLogUtil.e(TAG, "uploadSampleConfig failed ! size = ", Integer.valueOf(localSampleConfigToCloud.size()));
        return setSampleConfigRsp.getTimestamp();
    }

    private void getSuccessSampleConfig(List<HiSampleConfig> list, SetSampleConfigRsp setSampleConfigRsp) {
        Iterator<HiSampleConfig> it = list.iterator();
        while (it.hasNext()) {
            HiSampleConfig next = it.next();
            for (CloudSampleConfig cloudSampleConfig : setSampleConfigRsp.getFailResult()) {
                if (cloudSampleConfig.getType().equals(next.getType()) && cloudSampleConfig.getConfigId().equals(next.getConfigId()) && cloudSampleConfig.getScopeApp().equals(next.getScopeApp()) && cloudSampleConfig.getScopeDeviceType().equals(next.getScopeDeviceType())) {
                    it.remove();
                }
            }
        }
    }

    private List<CloudSampleConfig> localSampleConfigToCloud(List<HiSampleConfig> list) {
        ArrayList arrayList = new ArrayList(list.size());
        for (HiSampleConfig hiSampleConfig : list) {
            if (hiSampleConfig != null) {
                ikv f = iis.d().f(hiSampleConfig.getClientId());
                if (f == null) {
                    ReleaseLogUtil.e(TAG, "HealthContext is null, clientId is ", Integer.valueOf(hiSampleConfig.getClientId()));
                } else {
                    arrayList.add(new CloudSampleConfig.Builder().type(hiSampleConfig.getType()).configId(hiSampleConfig.getConfigId()).scopeApp(hiSampleConfig.getScopeApp()).scopeDeviceType(hiSampleConfig.getScopeDeviceType()).configData(hiSampleConfig.getConfigData()).configDescription(hiSampleConfig.getConfigDescription()).metaData(hiSampleConfig.getMetaData()).deviceCode(f.a()).modifyTime(hiSampleConfig.getModifiedTime()).build());
                }
            }
        }
        return arrayList;
    }

    private void deleteSampleConfig() throws iut, IllegalArgumentException {
        List<Integer> e = iis.d().e(this.mMainUserId);
        if (HiCommonUtil.d(e)) {
            LogUtil.h(TAG, "no clientIDs get , who is ", Integer.valueOf(this.mMainUserId));
            return;
        }
        while (this.mUploadFailCount < 2) {
            List<HiSampleConfig> d = ijq.e().d(e, null, 2, 10);
            if (HiCommonUtil.d(d)) {
                return;
            }
            ReleaseLogUtil.e(TAG, "deleteSampleConfig getDeleteSampleConfigList size = ", Integer.valueOf(d.size()));
            deleteSampleConfigOnce(e, d);
        }
        this.mUploadFailCount = 0;
    }

    private void deleteSampleConfigOnce(List<Integer> list, List<HiSampleConfig> list2) throws iut {
        ArrayList arrayList = new ArrayList(list2.size());
        for (HiSampleConfig hiSampleConfig : list2) {
            if (hiSampleConfig != null) {
                arrayList.add(new HiSampleConfigKey.Builder().b(hiSampleConfig.getType()).d(hiSampleConfig.getConfigId()).e(hiSampleConfig.getScopeApp()).a(hiSampleConfig.getScopeDeviceType()).d());
            }
        }
        if (HiCommonUtil.d(arrayList)) {
            ReleaseLogUtil.d(TAG, "deleteSampleConfigKeyList is empty");
            return;
        }
        DeleteSampleConfigReq deleteSampleConfigReq = new DeleteSampleConfigReq();
        deleteSampleConfigReq.setDeleteSampleConfigKeyList(arrayList);
        while (this.mUploadFailCount < 2) {
            DeleteSampleConfigRsp deleteSampleConfigRsp = (DeleteSampleConfigRsp) lqi.d().d(deleteSampleConfigReq.getUrl(), this.mParamsFactory.getHeaders(), lql.b(this.mParamsFactory.getBody(deleteSampleConfigReq)), DeleteSampleConfigRsp.class);
            if (deleteSampleConfigRsp != null && deleteSampleConfigRsp.getResultCode().intValue() != 0) {
                if (!HiCommonUtil.d(deleteSampleConfigRsp.getFailResult())) {
                    arrayList.removeAll(deleteSampleConfigRsp.getFailResult());
                }
                ReleaseLogUtil.e(TAG, "DeleteSampleConfig failed ! resultCode is ", deleteSampleConfigRsp.getResultCode(), ", resultDesc", deleteSampleConfigRsp.getResultDesc());
                this.mUploadFailCount++;
                Iterator it = arrayList.iterator();
                while (it.hasNext()) {
                    ijq.e().b(list, (HiSampleConfigKey) it.next(), 2);
                }
            } else {
                if (ius.a(deleteSampleConfigRsp, false)) {
                    Iterator it2 = arrayList.iterator();
                    while (it2.hasNext()) {
                        ijq.e().b(list, (HiSampleConfigKey) it2.next(), 2);
                    }
                    ReleaseLogUtil.e(TAG, "deleteSampleConfig OK ! ");
                    return;
                }
                this.mUploadFailCount++;
            }
        }
        ReleaseLogUtil.d(TAG, "deleteSampleConfig failed !");
        this.mUploadFailCount++;
    }

    public String toString() {
        return getClass().getSimpleName();
    }
}
