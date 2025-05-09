package com.huawei.hihealthservice.sync;

import android.app.IntentService;
import android.app.Notification;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Process;
import android.os.SystemClock;
import android.util.SparseArray;
import com.huawei.haf.application.BroadcastManager;
import com.huawei.health.R;
import com.huawei.health.manager.powerkit.PowerKitManager;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.HiSyncOption;
import com.huawei.hihealth.HiUserInfo;
import com.huawei.hihealth.data.model.HiTrackMetaData;
import com.huawei.hihealth.data.type.HiSyncType;
import com.huawei.hihealth.dictionary.HiHealthDictManager;
import com.huawei.hihealth.dictionary.utils.DicDataTypeUtil;
import com.huawei.hihealth.util.HiBroadcastUtil;
import com.huawei.hihealth.util.HiJsonUtil;
import com.huawei.hihealthservice.sync.syncdata.HiSyncBase;
import com.huawei.hihealthservice.sync.syncdata.dictionary.config.HiSyncSampleConfig;
import com.huawei.hihealthservice.sync.syncdata.dictionary.detail.HiSyncDictionaryDataDetail;
import com.huawei.hihealthservice.sync.syncdata.dictionary.statistics.HiSyncDictionaryDataStat;
import com.huawei.hms.kit.awareness.barrier.internal.e.a;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.utils.ThermalCallback;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.OperationKey;
import com.huawei.up.utils.NSPException;
import com.huawei.utils.FoundationCommonUtil;
import com.huawei.watchface.api.HwWatchFaceApi;
import defpackage.igp;
import defpackage.iip;
import defpackage.iis;
import defpackage.iiz;
import defpackage.ijb;
import defpackage.ijd;
import defpackage.iks;
import defpackage.ikv;
import defpackage.isf;
import defpackage.ism;
import defpackage.itb;
import defpackage.itd;
import defpackage.ite;
import defpackage.itf;
import defpackage.itg;
import defpackage.iti;
import defpackage.itj;
import defpackage.itk;
import defpackage.itl;
import defpackage.itm;
import defpackage.ito;
import defpackage.itu;
import defpackage.itx;
import defpackage.ity;
import defpackage.itz;
import defpackage.iui;
import defpackage.iut;
import defpackage.iuy;
import defpackage.iuz;
import defpackage.ivc;
import defpackage.ivg;
import defpackage.ivo;
import defpackage.ivz;
import defpackage.iwe;
import defpackage.jbs;
import defpackage.jdh;
import defpackage.kts;
import health.compact.a.CommonUtil;
import health.compact.a.CompileParameterUtil;
import health.compact.a.HiCommonUtil;
import health.compact.a.HiDateUtil;
import health.compact.a.HuaweiHealth;
import health.compact.a.KeyValDbManager;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.StorageParams;
import health.compact.a.Utils;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;

/* loaded from: classes7.dex */
public class HiSyncService extends IntentService {

    /* renamed from: a, reason: collision with root package name */
    private final BroadcastReceiver f4214a;
    private int b;
    private int c;
    private Context d;
    private int e;
    private HiSyncOption f;
    private long g;
    private boolean h;
    private int i;
    private long j;
    private int k;
    private int o;

    static /* synthetic */ long b(HiSyncService hiSyncService, long j) {
        long j2 = hiSyncService.j + j;
        hiSyncService.j = j2;
        return j2;
    }

    public HiSyncService() {
        super("HiH_HiSyncService");
        this.f = null;
        this.o = 0;
        this.h = BaseApplication.isRunningForeground();
        this.j = 0L;
        this.g = 0L;
        this.f4214a = new BroadcastReceiver() { // from class: com.huawei.hihealthservice.sync.HiSyncService.1
            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context, Intent intent) {
                String action = intent.getAction();
                LogUtil.a("HiH_HiSyncService", "ForegroundStatus action: ", action);
                if (HiSyncService.this.o == 0) {
                    ReleaseLogUtil.d("HiH_HiSyncService", "sync is not running");
                    return;
                }
                if (HwWatchFaceApi.ACTION_FOREGROUND_STATUS.equals(action)) {
                    HiSyncService.this.h = intent.getBooleanExtra("isForeground", false);
                    ReleaseLogUtil.e("HiH_HiSyncService", "ForegroundStatus isForeground: ", Boolean.valueOf(HiSyncService.this.h));
                    ThermalCallback.getInstance().controlThreadLevel(Collections.singletonList(Integer.valueOf(HiSyncService.this.o)), !HiSyncService.this.h ? 1 : 0);
                    if (HiSyncService.this.h) {
                        HiSyncService.b(HiSyncService.this, SystemClock.elapsedRealtime() - HiSyncService.this.g);
                        PowerKitManager.e().e("HiH_HiSyncService", 512, "user-firstDataSync");
                        return;
                    }
                    PowerKitManager.e().e("HiH_HiSyncService", 512, "user-firstDataSync");
                    if (HiSyncService.this.j < 300000) {
                        PowerKitManager.e().d("HiH_HiSyncService", 512, 300000 - HiSyncService.this.j, "user-firstDataSync");
                        HiSyncService.this.g = SystemClock.elapsedRealtime();
                        return;
                    }
                    return;
                }
                ReleaseLogUtil.d("HiH_HiSyncService", "ForegroundStatus other action");
            }
        };
    }

    @Override // android.app.IntentService
    protected void onHandleIntent(Intent intent) {
        if (intent == null) {
            ReleaseLogUtil.d("HiH_HiSyncService", "onHandleIntent intent is null, sync stopped!!!");
            return;
        }
        if (!FoundationCommonUtil.isSystemInfoAuthorized(this.d)) {
            ReleaseLogUtil.d("HiH_HiSyncService", "the isSystemInfoAuthorized is false, no need to SyncCloud");
            return;
        }
        long currentTimeMillis = System.currentTimeMillis();
        if (bCc_(intent)) {
            if (!ism.b(this.f, this.b, this.i)) {
                LogUtil.h("HiH_HiSyncService", "onHandleIntent wrong para, sync end ");
                return;
            }
            this.o = Process.myTid();
            if (!BaseApplication.isRunningForeground()) {
                ThermalCallback.getInstance().controlThreadLevel(Collections.singletonList(Integer.valueOf(this.o)), 1);
            }
            int syncDataType = this.f.getSyncDataType();
            this.f.setSyncModel(ism.j());
            try {
                Utils.e(iuz.b(this.f.getSyncAction()));
                ab();
                if (10018 == syncDataType) {
                    HiBroadcastUtil.b(this.d, syncDataType, 0);
                }
                ism.f().a(this.i);
                if (!Utils.o() && 20000 == this.f.getSyncDataType() && ism.k()) {
                    iis.d().d(this.i, iip.b().a(HuaweiHealth.b()));
                }
                KeyValDbManager.b(this.d).a("cloud_st_invalid_flag", "0", new StorageParams(1));
                aa();
                d(true);
                if (this.f.getSyncDataType() == 20000) {
                    ism.f().n();
                }
            } catch (iut e) {
                d(syncDataType, e);
            } catch (Exception e2) {
                d(syncDataType, e2);
            }
            if (a(this.d)) {
                ism.a(this.d, this.i, 300000);
            }
            ReleaseLogUtil.e("HiH_HiSyncService", "###onHandleIntent end! totalTime=", Long.valueOf(System.currentTimeMillis() - currentTimeMillis));
            if (syncDataType == 20000) {
                try {
                    m();
                } catch (Exception e3) {
                    ReleaseLogUtil.e("HiH_HiSyncService", "delDuplicatedStat=", e3.getMessage());
                }
                if (CommonUtil.as()) {
                    t();
                }
            }
        }
    }

    private void m() {
        if (iwe.d(this.d, "delDuplicatedStat", this.i, true) && iuz.i(this.d, this.i)) {
            List<igp> d = ijd.c(this.d).d();
            if (!HiCommonUtil.d(d)) {
                for (igp igpVar : d) {
                    ReleaseLogUtil.e("HiH_HiSyncService", "DataStatManager duplicatedDayStat=", igpVar);
                    ijd.c(this.d).a(igpVar);
                }
            }
            List<igp> d2 = ijb.b().d();
            if (!HiCommonUtil.d(d2)) {
                for (igp igpVar2 : d2) {
                    ReleaseLogUtil.e("HiH_HiSyncService", "DataStatSensitive duplicatedDayStat=", igpVar2);
                    ijb.b().a(igpVar2);
                }
            }
            iwe.b(this.d, "delDuplicatedStat", false, this.i);
        }
    }

    private void t() {
        List<Integer> a2 = iks.e().a(this.i);
        if (HiCommonUtil.d(a2)) {
            ReleaseLogUtil.e("HiH_HiSyncService", "delDuplicatedTrack clientIds null");
            return;
        }
        iiz a3 = iiz.a(this.d);
        if (iwe.d(this.d, "duplicated_tracks", this.i, true) && iuz.i(this.d, this.i)) {
            long currentTimeMillis = System.currentTimeMillis();
            long j = currentTimeMillis;
            long j2 = currentTimeMillis - 1728000000;
            int i = 0;
            while (i < 18) {
                ReleaseLogUtil.e("HiH_HiSyncService", "startTime,", Long.valueOf(j2), " endTime,", Long.valueOf(j));
                int i2 = i;
                List<HiHealthData> e = a3.e(a2, j2, j, 30001);
                if (HiCommonUtil.d(e) || e.size() == 1) {
                    ReleaseLogUtil.e("HiH_HiSyncService", "sequenceHealthDatas null");
                } else {
                    ReleaseLogUtil.e("HiH_HiSyncService", "sequenceHealthDatas size", Integer.valueOf(e.size()));
                    e(e);
                }
                i = i2 + 1;
                j = j2;
                j2 -= 1728000000;
            }
            iwe.b(this.d, "duplicated_tracks", false, this.i);
            Iterator<Integer> it = a2.iterator();
            while (it.hasNext()) {
                iis.d().l(it.next().intValue());
            }
        }
    }

    private void e(List<HiHealthData> list) {
        int size = list.size();
        isf a2 = isf.a(this.d);
        int i = 0;
        while (i < size - 1) {
            int i2 = i + 1;
            if (list.get(i).getStartTime() == list.get(i2).getStartTime()) {
                HiHealthData hiHealthData = list.get(i);
                HiHealthData hiHealthData2 = list.get(i2);
                ReleaseLogUtil.e("HiH_HiSyncService", "first=", Long.valueOf(hiHealthData.getStartTime()));
                hiHealthData.setUserId(this.i);
                if (hiHealthData.getMergedStatus() == hiHealthData2.getMergedStatus()) {
                    ReleaseLogUtil.e("HiH_HiSyncService", "getMergedStatus equal");
                    c(hiHealthData, 0, 0);
                    c(hiHealthData2, 1, 2);
                    a2.prepareRealTimeHealthDataStat(Collections.singletonList(hiHealthData));
                } else {
                    if (hiHealthData.getMergedStatus() == 0) {
                        c(hiHealthData, 0, 0);
                        c(hiHealthData2, 1, 2);
                    } else {
                        c(hiHealthData2, 0, 0);
                        c(hiHealthData, 1, 2);
                    }
                    a2.prepareRealTimeHealthDataStat(Collections.singletonList(hiHealthData));
                }
            }
            i = i2;
        }
        a2.doRealTimeHealthDataStat();
    }

    private void c(HiHealthData hiHealthData, int i, int i2) {
        HiTrackMetaData hiTrackMetaData = (HiTrackMetaData) HiJsonUtil.e(hiHealthData.getMetaData(), HiTrackMetaData.class);
        hiTrackMetaData.setDuplicated(i);
        hiHealthData.setMetaData(HiJsonUtil.e(hiTrackMetaData));
        iiz.a(this.d).e(hiHealthData, i2, i2);
    }

    private boolean bCc_(Intent intent) {
        if (this.d == null) {
            this.d = getApplicationContext();
        }
        try {
            this.f = (HiSyncOption) intent.getParcelableExtra("sync_option");
            this.b = intent.getIntExtra("sync_appId", 0);
            this.i = intent.getIntExtra("sync_main_UserID", 0);
            this.k = intent.getIntExtra("sync_starttime", 0);
            this.c = intent.getIntExtra("sync_endtime", 0);
            this.e = intent.getIntExtra("sync_datatype", 0);
            ReleaseLogUtil.e("HiH_HiSyncService", "onHandleIntent start ", this.f);
            return true;
        } catch (Exception e) {
            ReleaseLogUtil.d("HiH_HiSyncService", "Exception：", e.getClass().getSimpleName());
            return false;
        }
    }

    private void d(boolean z) {
        String valueOf;
        if (this.f.getSyncDataType() == 0) {
            valueOf = HiJsonUtil.e(this.f.getSyncDataTypes());
        } else {
            valueOf = String.valueOf(this.f.getSyncDataType());
        }
        HiBroadcastUtil.a(this.d, valueOf, this.f.getSyncId(), z);
    }

    private void aa() {
        if (CompileParameterUtil.a("SUPPORT_WIFI_WEIGHT_DEVICE", false)) {
            if (CompileParameterUtil.a("SUPPORT_WIFI_PUSHTYPE_NINE", false)) {
                if ((20000 == this.f.getSyncDataType() || HiSyncType.HiSyncDataType.c == this.f.getSyncDataType()) && 2 == this.f.getSyncAction() && 2 == this.f.getPushAction()) {
                    HiBroadcastUtil.e(this.d);
                    return;
                }
                return;
            }
            if (20000 == this.f.getSyncDataType() && 2 == this.f.getSyncAction()) {
                HiBroadcastUtil.e(this.d);
            }
        }
    }

    private void d(int i, Exception exc) {
        ReleaseLogUtil.c("HiH_HiSyncService", "onHandleIntent sync failed, e is ", exc.getMessage());
        if (exc.getMessage() != null && (exc.getMessage().contains("database or disk is full") || exc.getMessage().contains("disk image is malformed"))) {
            SharedPreferenceManager.c("HuaweiHealth", "handleException", exc.getMessage() + " " + System.currentTimeMillis());
        }
        d(exc);
        iwe.j(this.d, iwe.h(this.d) + 1);
        ivc.c(this.f.getSyncAction());
        ivc.a(this.d, i);
        d(false);
        try {
            jdh.c().a(20210701);
        } catch (Exception unused) {
            ReleaseLogUtil.c("HiH_HiSyncService", "sync cancel notify throw exception");
        }
        if (i == 10018 || this.f.getPushAction() == 2) {
            HiBroadcastUtil.b(this.d, i, -1);
        }
    }

    private void ab() throws iut {
        int syncDataType = this.f.getSyncDataType();
        if (!iuz.i(this.d, this.i) && syncDataType != 20000) {
            ReleaseLogUtil.c("HiH_HiSyncService", "nonFirstSync syncDtTp=", Integer.valueOf(syncDataType), " tps=", HiJsonUtil.e(this.f.getSyncDataTypes()));
            return;
        }
        if (CommonUtil.as()) {
            w();
        }
        ReleaseLogUtil.e("HiH_HiSyncService", "startSync syncDtTp=", Integer.valueOf(syncDataType), " tps=", HiJsonUtil.e(this.f.getSyncDataTypes()));
        ity.a(this.d).d();
        b(new itd(this.d, this.i));
        if (syncDataType != 20000) {
            iuy.c(this.d, this.i);
            iuy.d(this.d, this.i);
            iuy.a(this.d, this.i);
            iuy.b(this.d, this.i);
            ity.a(this.d).c();
        }
        if (syncDataType != 0) {
            b(syncDataType);
        } else if (!HiCommonUtil.d(this.f.getSyncDataTypes())) {
            Iterator<Integer> it = this.f.getSyncDataTypes().iterator();
            while (it.hasNext()) {
                b(it.next().intValue());
            }
        }
        if (ism.c(this.i)) {
            if (iuz.d(this.d, this.i, 1, 0L)) {
                ReleaseLogUtil.e("HiH_HiSyncService", "no data sync, do not download last seven datas");
            } else if (f().d()) {
                ism.b(this.i, false);
            }
        }
    }

    private void w() {
        int a2;
        int c = HiDateUtil.c(System.currentTimeMillis());
        int a3 = iwe.a(this.d, "sync_time", this.i, 0);
        if (a3 != 0 && c != a3 && (a2 = iwe.a(this.d, "sync_count", this.i, 0)) != 0) {
            LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap<>(2);
            linkedHashMap.put("sync_time", String.valueOf(a3));
            linkedHashMap.put("sync_count", String.valueOf(a2));
            ivz.d(this.d).e(OperationKey.HEALTH_APP_SYNC_COUNT_2129024.value(), linkedHashMap, false);
            iwe.d(this.d, "sync_time", 0, this.i);
            iwe.d(this.d, "sync_count", 0, this.i);
        }
        int a4 = iwe.a(this.d, "sync_count", this.i, 0);
        iwe.d(this.d, "sync_time", c, this.i);
        iwe.d(this.d, "sync_count", a4 + 1, this.i);
    }

    private void b(int i) throws iut {
        if (i == 1) {
            al();
            return;
        }
        if (i == 2) {
            aj();
            d(30001);
            return;
        }
        if (i != 3) {
            if (i == 4) {
                ag();
                return;
            }
            if (i == 5) {
                d(DicDataTypeUtil.DataType.BLOOD_PRESSURE_SET.value());
                return;
            }
            if (i == 7 || i == 9) {
                b(c(i));
                return;
            }
            if (i != 15) {
                if (i == 10001) {
                    ae();
                    ag();
                    return;
                }
                if (i == 10026) {
                    iwe.i(this.d, iwe.f(this.d) + 1);
                    am();
                    return;
                }
                if (i == 20000) {
                    ivc.d(this.d, this.f.getSyncAction());
                    z();
                    return;
                }
                if (i == 900000000) {
                    af();
                    return;
                }
                if (i == 10003) {
                    ad();
                    return;
                } else if (i == 10004) {
                    ak();
                    HiBroadcastUtil.g(this.d);
                    return;
                } else {
                    a(i);
                    return;
                }
            }
        }
        ae();
    }

    private void a(int i) throws iut {
        if (i == 11) {
            b(c(i));
            ai();
            return;
        }
        if (i == 5000) {
            ac();
            return;
        }
        if (i == 10005) {
            an();
            return;
        }
        if (i != 10021 && i != 10023) {
            if (i == 10028) {
                r();
                return;
            }
            if (i == 10007) {
                iuy.d(this.d, this.i);
                ah();
                return;
            }
            if (i == 10008) {
                al();
                ak();
                ad();
                aj();
                return;
            }
            if (i == 10018) {
                if (this.k == 0 || this.c == 0 || this.e == 0) {
                    HiBroadcastUtil.b(this.d, i, -2);
                    return;
                } else {
                    q();
                    return;
                }
            }
            if (i != 10019) {
                switch (i) {
                    case 10010:
                        ai();
                        break;
                    case 10011:
                    case 10012:
                    case 10013:
                        break;
                    default:
                        if (HiHealthDictManager.d(this.d).d(i) != null) {
                            d(i);
                            break;
                        } else {
                            LogUtil.h("HiH_HiSyncService", "startSync syncDataType is not right, syncDataType is ", Integer.valueOf(i));
                            break;
                        }
                }
                return;
            }
        }
        s();
    }

    private void q() throws iut {
        if (90001 == this.e) {
            ito f = f();
            SparseArray<Integer> bCF_ = iuz.bCF_(HiDateUtil.n(HiDateUtil.a(this.k)), HiDateUtil.a(this.c), 9);
            if (bCF_ == null || bCF_.size() <= 0) {
                LogUtil.h("HiH_HiSyncService", "downloadDetailDataByTime downloadDaysMap is null, stop pullDataByVersion!");
                return;
            }
            for (int size = bCF_.size() - 1; size >= 0; size--) {
                int keyAt = bCF_.keyAt(size);
                f.a(keyAt, bCF_.get(keyAt).intValue());
            }
        }
    }

    private void s() throws iut {
        LogUtil.a("HiH_HiSyncService", "downloadDetailData syncDataType = " + this.f.getSyncDataType());
        LogUtil.a("HiH_HiSyncService", "downloadDetailData downedType = " + this.f.getSyncType());
        int syncDataType = this.f.getSyncDataType();
        if (syncDataType == 10019) {
            f().b();
            return;
        }
        if (syncDataType == 10021) {
            f().a();
            return;
        }
        if (syncDataType != 10023) {
            switch (syncDataType) {
                case 10011:
                    k().e();
                    break;
                case 10012:
                    f().c();
                    break;
                case 10013:
                    f().e();
                    break;
            }
            return;
        }
        c().c();
    }

    private void z() throws iut {
        if (!iuz.i(this.d, this.i)) {
            LogUtil.a("HiH_HiSyncService", "retry firstSync start");
            y();
            return;
        }
        if (this.f.getSyncDataArea() == 1 && !iuz.h(this.d, this.i)) {
            try {
                kts.d(2);
                kts.b(4);
                ReleaseLogUtil.e("HiH_HiSyncService", "sync all data start");
                b(false);
                x();
                iuz.b(this.d, this.i, true);
                ism.a(false);
                b(true);
                ivc.c(this.d);
                ReleaseLogUtil.e("HiH_HiSyncService", "sync all data end");
                return;
            } finally {
                kts.c(2);
                kts.a(4);
            }
        }
        try {
            kts.d(16);
            kts.b(8);
            x();
            ivc.c(this.d);
        } finally {
            kts.c(16);
            kts.a(8);
        }
    }

    private void b(boolean z) {
        Notification.Builder xf_ = jdh.c().xf_();
        xf_.setContentText(getResources().getString(R.string.IDS_hw_show_sync_cloud_history_data)).setAutoCancel(false).setOngoing(false).setSmallIcon(R.drawable.healthlogo_ic_notification);
        if (z) {
            xf_.setContentText(getResources().getString(R.string.IDS_bundle_downloaded_prompt));
        }
        jdh.c().xh_(20210701, xf_.build());
    }

    private void y() throws iut {
        SharedPreferenceManager.e("HiHealthService", "pullAllStatus", false);
        SharedPreferenceManager.e("HiHealthService", "pullSportStatStatus", false);
        iuz.c(this.d, this.i, false);
        try {
            b(this.d, true);
            kts.d(2);
            kts.b(4);
            if (!this.h) {
                PowerKitManager.e().d("HiH_HiSyncService", 512, 300000L, "user-firstDataSync");
                this.g = SystemClock.elapsedRealtime();
            }
            p();
            iuz.c(this.d, this.i, true);
            iuz.f(this.d, 20000);
            ivc.c(this.d);
            ReleaseLogUtil.d("HiH_HiSyncService", "ALTITUDE start version");
            new ite(this.d, this.i).b();
            kts.c(2);
            kts.a(4);
            b(this.d, false);
            iwe.b(this.d, "isInsertBatchStats", false, this.i);
            if (Utils.o()) {
                return;
            }
            iis.d().d(this.i, iip.b().a(HuaweiHealth.b()));
        } catch (Throwable th) {
            ReleaseLogUtil.d("HiH_HiSyncService", "firstSync exception, not complete.");
            int i = this.i;
            iuz.a(i, iuz.e(i) + 1);
            kts.c(2);
            kts.a(4);
            b(this.d, false);
            iwe.b(this.d, "isInsertBatchStats", false, this.i);
            throw th;
        }
    }

    private void p() throws iut {
        ReleaseLogUtil.e("HiH_HiSyncService", "firstSync start");
        iwe.b(this.d, "isInsertBatchStats", !iwe.d(this.d, "firstSyncStartFlag", this.i, false), this.i);
        iwe.b(this.d, "firstSyncStartFlag", true, this.i);
        ity.a(this.d).c();
        long currentTimeMillis = System.currentTimeMillis();
        iuz.e(true);
        jbs.a(this.d).e();
        am();
        itl k = k();
        itz n = n();
        itu o = o();
        itf e = e();
        HiSyncBase b = b();
        ito f = f();
        itm g = g();
        iti j = j();
        itk c = c();
        itg h = h();
        HiSyncBase d = d();
        HiSyncDictionaryDataDetail e2 = e(this.f);
        HiSyncSampleConfig i = i();
        long currentTimeMillis2 = System.currentTimeMillis();
        o.pullDataByTime(iuz.d(currentTimeMillis2, 1), currentTimeMillis2);
        k.c(3.0d);
        ivg.c().a(1, "firstSync sport", new ikv(this.b));
        a(e2, f, k, j, c);
        k.c(1.0d);
        j.pullDataByVersion();
        o.pullDataByVersion();
        e.pullDataByVersion();
        g.pullDataByVersion();
        h.pullDataByVersion();
        HiSyncBase a2 = a();
        a2.pullDataByVersion();
        n.pullDataByVersion();
        b.pullDataByVersion();
        c.b();
        c.pullDataByTime(iuz.d(currentTimeMillis2, 7), currentTimeMillis2);
        c.pullDataByVersion();
        new ite(this.d, this.i).e();
        e2.pullDataByVersion();
        f.g();
        f.pullDataByTime(iuz.d(currentTimeMillis2, 7), currentTimeMillis2);
        f.pullDataByVersion();
        k.d();
        k.pullDataByTime(iuz.d(currentTimeMillis2, 7), currentTimeMillis2);
        k.pullDataByVersion();
        i.pullDataByVersion();
        d.pullDataByVersion();
        HiBroadcastUtil.c(this.d, 0);
        HiBroadcastUtil.b(this.d);
        ivg.c().a(200, "firstSync all", new ikv(this.b));
        n.pushData();
        b.pushData();
        f.pushData();
        c.pushData();
        d.pushData();
        e2.pushData();
        k.pushData();
        o.pushData();
        e.pushData();
        g.pushData();
        j.pushData();
        h.pushData();
        a2.pushData();
        i.pushData();
        iuz.e(false);
        ReleaseLogUtil.e("HiH_HiSyncService", "firstSync end ,totalTime = ", Long.valueOf(System.currentTimeMillis() - currentTimeMillis));
        ivo.b(this.d).d((System.currentTimeMillis() - currentTimeMillis) / 1000, this.i);
    }

    private static void a(HiSyncDictionaryDataDetail hiSyncDictionaryDataDetail, ito itoVar, itl itlVar, iti itiVar, itk itkVar) {
        try {
            hiSyncDictionaryDataDetail.downloadLatestSequenceData(1, 0, 30001);
            itoVar.d(1, 0, 9);
            itlVar.c(1, 0);
            hiSyncDictionaryDataDetail.downloadLatestPointData(0, 3, DicDataTypeUtil.DataType.WEIGHT_BODYFAT_BROAD.value());
            itoVar.d(1, 0, 7);
            itiVar.b(a.C, 90);
            hiSyncDictionaryDataDetail.downloadLatestPointData(1, 0, DicDataTypeUtil.DataType.EMOTION_SET.value());
            itoVar.d(1, 0, 11);
            hiSyncDictionaryDataDetail.downloadLatestPointData(0, 1, DicDataTypeUtil.DataType.BLOOD_PRESSURE_SET.value());
            itkVar.c(1, 0, 16);
            itkVar.c(0, 1, 4);
            if (Utils.o()) {
                hiSyncDictionaryDataDetail.downloadLatestPointData(0, 1, DicDataTypeUtil.DataType.SKIN_TEMPERATURE_SET.value());
            } else {
                hiSyncDictionaryDataDetail.downloadLatestPointData(0, 1, DicDataTypeUtil.DataType.BODY_TEMPERATURE_SET.value());
            }
            hiSyncDictionaryDataDetail.downloadLatestPointData(0, 1, DicDataTypeUtil.DataType.HIGH_BODY_TEMPERATURE_ALARM_SET.value());
            hiSyncDictionaryDataDetail.downloadLatestPointData(0, 1, DicDataTypeUtil.DataType.LOW_BODY_TEMPERATURE_ALARM_SET.value());
            hiSyncDictionaryDataDetail.downloadLatestPointData(0, 1, DicDataTypeUtil.DataType.SUSPECTED_HIGH_TEMPERATURE_ALARM_SET.value());
            hiSyncDictionaryDataDetail.downloadLatestPointData(0, 1, DicDataTypeUtil.DataType.SUSPECTED_HIGH_TEMPERATURE_SET.value());
        } catch (iut e) {
            ReleaseLogUtil.c("HiH_HiSyncService", "downloadLatestData error:", e.getMessage());
        }
    }

    private void x() throws iut {
        ReleaseLogUtil.e("HiH_HiSyncService", "incrementalSync start");
        iwe.b(this.d, "isInsertBatchStats", false, this.i);
        SharedPreferenceManager.e("HiHealthService", "pullAllStatus", true);
        iuy.c(this.d, this.i);
        ivc.b(this.d, 1.0d, 1.0d, 1.0d);
        iuy.d(this.d, this.i);
        iuy.a(this.d, this.i);
        ivc.b(this.d, 1.0d, 1.0d, 1.0d);
        iuy.b(this.d, this.i);
        ivc.b(this.d, 1.0d, 1.0d, 1.0d);
        ity.a(this.d).c();
        long currentTimeMillis = System.currentTimeMillis();
        am();
        a(v());
        HiBroadcastUtil.c(this.d, 0);
        HiBroadcastUtil.b(this.d);
        ivg.c().a(200, "incrementalSync", new ikv(this.b));
        d(u());
        ReleaseLogUtil.e("HiH_HiSyncService", "incrementalSync end,totalTime=", Long.valueOf(System.currentTimeMillis() - currentTimeMillis));
    }

    private void al() throws iut {
        b(k());
        HiBroadcastUtil.b(this.d);
    }

    private void ad() throws iut {
        b(e());
    }

    private void d(int i) throws iut {
        HiSyncOption hiSyncOption = new HiSyncOption(this.f, i);
        HiSyncDictionaryDataStat hiSyncDictionaryDataStat = new HiSyncDictionaryDataStat(this.d, hiSyncOption, this.i);
        e(hiSyncDictionaryDataStat);
        b(e(hiSyncOption));
        d(hiSyncDictionaryDataStat);
    }

    private void ak() throws iut {
        b(o());
    }

    private void aj() throws iut {
        b(n());
    }

    private void ae() throws iut {
        b(b());
    }

    private void ag() throws iut {
        b(c());
    }

    private void ah() throws iut {
        b(g());
    }

    private void ai() throws iut {
        b(j());
    }

    private void am() throws iut {
        itx itxVar = new itx(this.d, this.i);
        d(itxVar);
        e(itxVar);
    }

    private void af() throws iut {
        b(i());
    }

    private void ac() throws iut {
        b(d());
    }

    private void r() throws iut {
        l().c();
    }

    private void an() throws iut {
        itx l = l();
        HiUserInfo userInfo = this.f.getUserInfo();
        if (userInfo == null) {
            LogUtil.b("HiH_HiSyncService", "uploadUserBasic hiUserInfo error!");
        } else {
            l.e(userInfo);
        }
    }

    private itl k() {
        return new itl(this.d, new HiSyncOption(this.f, 1), this.i);
    }

    private itz n() {
        return new itz(this.d, new HiSyncOption(this.f, 2), this.i);
    }

    private itu o() {
        return new itu(this.d, new HiSyncOption(this.f, 10004), this.i);
    }

    private itm g() {
        return new itm(this.d, new HiSyncOption(this.f, 10007), this.i);
    }

    private iti j() {
        return new iti(this.d, new HiSyncOption(this.f, 10010), this.i);
    }

    private itg h() {
        return new itg(this.d, new HiSyncOption(this.f, 10025), this.i);
    }

    private HiSyncBase b() throws iut {
        HiSyncOption hiSyncOption = new HiSyncOption(this.f, 10001);
        if (hiSyncOption.getSyncModel() == 3) {
            return new itb(this.d, hiSyncOption, this.i);
        }
        return new itj(this.d, hiSyncOption, this.i);
    }

    private HiSyncBase d() throws iut {
        return new iui(this.d, new HiSyncOption(this.f, 5000), this.i);
    }

    private HiSyncDictionaryDataDetail e(HiSyncOption hiSyncOption) throws iut {
        return new HiSyncDictionaryDataDetail(this.d, new HiSyncOption(hiSyncOption, hiSyncOption.getSyncDataType()), this.i);
    }

    private HiSyncBase a() throws iut {
        HiSyncOption hiSyncOption = this.f;
        return new HiSyncDictionaryDataStat(this.d, new HiSyncOption(hiSyncOption, hiSyncOption.getSyncDataType()), this.i);
    }

    private ito f() throws iut {
        return new ito(this.d, new HiSyncOption(this.f, 10001), this.i);
    }

    private ito c(int i) throws iut {
        return new ito(this.d, new HiSyncOption(this.f, i), this.i);
    }

    private itk c() {
        return new itk(this.d, new HiSyncOption(this.f, 10001), this.i);
    }

    private itf e() {
        return new itf(this.d, new HiSyncOption(this.f, 10003), this.i);
    }

    private itx l() {
        return new itx(this.d, this.i);
    }

    private HiSyncSampleConfig i() {
        return new HiSyncSampleConfig(this.d, new HiSyncOption(this.f, 900000000), this.i);
    }

    private List<HiSyncBase> v() throws iut {
        ArrayList arrayList = new ArrayList(10);
        arrayList.add(o());
        arrayList.add(k());
        arrayList.add(e());
        arrayList.add(g());
        arrayList.add(j());
        arrayList.add(h());
        arrayList.add(a());
        arrayList.add(n());
        arrayList.add(e(this.f));
        arrayList.add(b());
        arrayList.add(c(20000));
        arrayList.add(c());
        arrayList.add(i());
        arrayList.add(d());
        return arrayList;
    }

    private List<HiSyncBase> u() throws iut {
        ArrayList arrayList = new ArrayList(10);
        arrayList.add(k());
        arrayList.add(n());
        arrayList.add(b());
        arrayList.add(c(20000));
        arrayList.add(c());
        arrayList.add(d());
        arrayList.add(e(this.f));
        arrayList.add(i());
        arrayList.add(o());
        arrayList.add(e());
        arrayList.add(g());
        arrayList.add(j());
        arrayList.add(h());
        arrayList.add(a());
        return arrayList;
    }

    private void b(HiSyncBase hiSyncBase) throws iut {
        LogUtil.a("HiH_HiSyncService", "executeSync hiSyncBase is ", hiSyncBase);
        long currentTimeMillis = System.currentTimeMillis();
        hiSyncBase.pullDataByVersion();
        long currentTimeMillis2 = System.currentTimeMillis();
        LogUtil.a("HiH_HiSyncService", "executeSync downLoad end", hiSyncBase, " totalTime = ", Long.valueOf(currentTimeMillis2 - currentTimeMillis));
        hiSyncBase.pushData();
        LogUtil.a("HiH_HiSyncService", "executeSync upLoad end", hiSyncBase, " totalTime = ", Long.valueOf(System.currentTimeMillis() - currentTimeMillis2));
    }

    private void a(List<HiSyncBase> list) throws iut {
        Iterator<HiSyncBase> it = list.iterator();
        while (it.hasNext()) {
            e(it.next());
        }
    }

    private void d(List<HiSyncBase> list) throws iut {
        Iterator<HiSyncBase> it = list.iterator();
        while (it.hasNext()) {
            d(it.next());
        }
    }

    private void e(HiSyncBase hiSyncBase) throws iut {
        long currentTimeMillis = System.currentTimeMillis();
        hiSyncBase.pullDataByVersion();
        LogUtil.a("HiH_HiSyncService", "pullDataByVersion end", hiSyncBase, " totalTime = ", Long.valueOf(System.currentTimeMillis() - currentTimeMillis));
    }

    private void d(HiSyncBase hiSyncBase) throws iut {
        long currentTimeMillis = System.currentTimeMillis();
        hiSyncBase.pushData();
        LogUtil.a("HiH_HiSyncService", "pushData end", hiSyncBase, " totalTime = ", Long.valueOf(System.currentTimeMillis() - currentTimeMillis));
    }

    private boolean a(Context context) {
        int e = iuz.e();
        if (e < 0) {
            e = iuz.b(context, this.i);
        }
        if (e == 0) {
            return true;
        }
        return System.currentTimeMillis() - iuz.d(this.d, this.i) > 43200000;
    }

    private void d(Exception exc) {
        if (exc == null) {
            return;
        }
        String message = exc.getMessage();
        if (message == null) {
            LogUtil.a("HiH_HiSyncService", "checkNetErrorTimer exception.getMessage() is null");
        } else if (message.contains(NSPException.EXP_NET_ERROR_STR)) {
            LogUtil.a("HiH_HiSyncService", "checkNetErrorTimer EXP_NET_ERROR_STR");
            ism.f().t();
        } else {
            ism.f().n();
        }
    }

    private void b(Context context, boolean z) {
        Intent intent = new Intent(context, (Class<?>) FirstSyncForegroundService.class);
        try {
            if (z) {
                if (!this.h) {
                    ReleaseLogUtil.e("HiH_HiSyncService", "start FirstSync is background, can't startForegroundService");
                    return;
                } else {
                    startForegroundService(intent);
                    return;
                }
            }
            stopService(intent);
        } catch (Throwable th) {
            ReleaseLogUtil.e("HiH_HiSyncService", "executeSyncForeService appear exception:", th.getMessage());
        }
    }

    @Override // android.app.IntentService, android.app.Service
    public void onCreate() {
        super.onCreate();
        LogUtil.a("HiH_HiSyncService", "enter onCreate.");
        BroadcastManager.wj_(this.f4214a);
    }

    @Override // android.app.IntentService, android.app.Service
    public void onDestroy() {
        BroadcastManager.wx_(this.f4214a);
        if (this.o > 0) {
            ThermalCallback.getInstance().controlThreadLevel(Collections.singletonList(Integer.valueOf(this.o)), 0);
        }
        super.onDestroy();
        ism.d(false);
        iuz.e(false);
        ism.a(false);
        LogUtil.a("HiH_HiSyncService", "onDestroy stop all tasks");
    }
}
