package com.huawei.hwdevice.phoneprocess.mgr.hwsyncmgr.periodrri;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.RemoteException;
import android.text.TextUtils;
import com.huawei.datatype.PeriodRriEntity;
import com.huawei.health.devicemgr.business.entity.DeviceCapability;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.health.healthdatamgr.api.HealthDataMgrApi;
import com.huawei.hihealth.data.model.HiStressMetaData;
import com.huawei.hwbasemgr.HwBaseManager;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.datatypes.TransferFileInfo;
import com.huawei.hwcommonmodel.utils.ThermalCallback;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.hwservicesmgr.ITransferSleepAndDFXFileCallback;
import defpackage.cwi;
import defpackage.dkx;
import defpackage.jlg;
import defpackage.jpo;
import defpackage.jpp;
import defpackage.jqj;
import defpackage.jrq;
import defpackage.jvl;
import defpackage.jyp;
import defpackage.keb;
import defpackage.keq;
import defpackage.kes;
import defpackage.ket;
import defpackage.sqo;
import health.compact.a.BroadcastManagerUtil;
import health.compact.a.CommonUtil;
import health.compact.a.KeyValDbManager;
import health.compact.a.Services;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import org.apache.commons.io.FileUtils;

/* loaded from: classes5.dex */
public class HwDevicePeriodRriFileManager extends HwBaseManager {

    /* renamed from: a, reason: collision with root package name */
    private static final Object f6341a = new Object();
    private static HwDevicePeriodRriFileManager e;
    private ITransferSleepAndDFXFileCallback.Stub b;
    private long c;
    private long d;
    private boolean f;
    private c g;
    private Timer h;
    private IBaseResponseCallback i;
    private long j;
    private long m;
    private final Object n;

    private HwDevicePeriodRriFileManager(Context context) {
        super(context);
        this.f = false;
        this.d = 0L;
        this.c = -1L;
        this.n = new Object();
        this.h = null;
        this.g = null;
        this.b = new ITransferSleepAndDFXFileCallback.Stub() { // from class: com.huawei.hwdevice.phoneprocess.mgr.hwsyncmgr.periodrri.HwDevicePeriodRriFileManager.1
            @Override // com.huawei.hwservicesmgr.ITransferSleepAndDFXFileCallback
            public void onSuccess(int i, final String str, String str2) throws RemoteException {
                ReleaseLogUtil.e("BTSYNC_PeriodRri_HwDevicePeriodRriFileManager", "mPeriodFileCallback() onSuccess");
                jrq.b("HwDevicePeriodRriFileManager", new Runnable() { // from class: com.huawei.hwdevice.phoneprocess.mgr.hwsyncmgr.periodrri.HwDevicePeriodRriFileManager.1.5
                    @Override // java.lang.Runnable
                    public void run() {
                        HwDevicePeriodRriFileManager.this.d(str);
                    }
                });
            }

            @Override // com.huawei.hwservicesmgr.ITransferSleepAndDFXFileCallback
            public void onFailure(int i, String str) throws RemoteException {
                ReleaseLogUtil.e("BTSYNC_PeriodRri_HwDevicePeriodRriFileManager", "stress sync fail : ", Integer.valueOf(i));
                HwDevicePeriodRriFileManager.this.b();
            }

            @Override // com.huawei.hwservicesmgr.ITransferSleepAndDFXFileCallback
            public void onProgress(int i, String str) throws RemoteException {
                LogUtil.c("HwDevicePeriodRriFileManager", "onProgress progress:", Integer.valueOf(i));
            }
        };
        this.i = new IBaseResponseCallback() { // from class: com.huawei.hwdevice.phoneprocess.mgr.hwsyncmgr.periodrri.HwDevicePeriodRriFileManager.5
            /* JADX WARN: Removed duplicated region for block: B:12:0x0036  */
            /* JADX WARN: Removed duplicated region for block: B:16:0x0039  */
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public void d(int r3, java.lang.Object r4) {
                /*
                    r2 = this;
                    java.lang.String r0 = "SaveData result errorCode: "
                    java.lang.Integer r1 = java.lang.Integer.valueOf(r3)
                    java.lang.Object[] r0 = new java.lang.Object[]{r0, r1}
                    java.lang.String r1 = "BTSYNC_PeriodRri_HwDevicePeriodRriFileManager"
                    health.compact.a.hwlogsmodel.ReleaseLogUtil.e(r1, r0)
                    java.lang.String r0 = "HwDevicePeriodRriFileManager"
                    if (r4 == 0) goto L31
                    if (r3 != 0) goto L16
                    goto L26
                L16:
                    r1 = 4
                    if (r3 != r1) goto L28
                    java.lang.String r3 = r4.toString()
                    java.lang.String r4 = "true"
                    boolean r3 = r3.equals(r4)
                    if (r3 == 0) goto L28
                L26:
                    r3 = 1
                    goto L32
                L28:
                    java.lang.String r3 = "SaveData enter else"
                    java.lang.Object[] r3 = new java.lang.Object[]{r3}
                    com.huawei.hwlogsmodel.LogUtil.h(r0, r3)
                L31:
                    r3 = 0
                L32:
                    com.huawei.hwdevice.phoneprocess.mgr.hwsyncmgr.periodrri.HwDevicePeriodRriFileManager r4 = com.huawei.hwdevice.phoneprocess.mgr.hwsyncmgr.periodrri.HwDevicePeriodRriFileManager.this
                    if (r3 == 0) goto L39
                    java.lang.String r1 = "Save successful."
                    goto L3b
                L39:
                    java.lang.String r1 = "Save fail."
                L3b:
                    com.huawei.hwdevice.phoneprocess.mgr.hwsyncmgr.periodrri.HwDevicePeriodRriFileManager.c(r4, r3, r1)
                    java.lang.String r3 = "save data end."
                    java.lang.Object[] r3 = new java.lang.Object[]{r3}
                    com.huawei.hwlogsmodel.LogUtil.a(r0, r3)
                    return
                */
                throw new UnsupportedOperationException("Method not decompiled: com.huawei.hwdevice.phoneprocess.mgr.hwsyncmgr.periodrri.HwDevicePeriodRriFileManager.AnonymousClass5.d(int, java.lang.Object):void");
            }
        };
        a();
    }

    public static HwDevicePeriodRriFileManager getInstance() {
        HwDevicePeriodRriFileManager hwDevicePeriodRriFileManager;
        synchronized (f6341a) {
            if (e == null) {
                e = new HwDevicePeriodRriFileManager(BaseApplication.getContext());
            }
            hwDevicePeriodRriFileManager = e;
        }
        return hwDevicePeriodRriFileManager;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public void d(String str) {
        File file;
        File file2;
        FileInputStream fileInputStream = null;
        try {
            try {
                if (!TextUtils.isEmpty(str) && str.contains("rrisqi_data")) {
                    LogUtil.a("HwDevicePeriodRriFileManager", "rri sync success");
                    File filesDir = BaseApplication.getContext().getFilesDir();
                    if (str.contains("/")) {
                        file = new File(str);
                    } else {
                        file = new File(filesDir, str);
                    }
                    try {
                        String c2 = CommonUtil.c(file.getCanonicalPath());
                        if (TextUtils.isEmpty(c2)) {
                            c("saveTransferDataContent realFilePath is empty");
                            sqo.am("HwDevicePeriodRriFileManager saveTransferDataContent: realFilePath is empty");
                            a((FileInputStream) null, file);
                            return;
                        }
                        file2 = FileUtils.getFile(c2);
                        try {
                            fileInputStream = FileUtils.openInputStream(file2);
                            PeriodRriEntity c3 = keq.c(fileInputStream);
                            if (c3 == null) {
                                c("saveTransferDataContent periodRriEntity is null");
                                sqo.am("HwDevicePeriodRriFileManager saveTransferDataContent: periodRriEntity is null");
                                a(fileInputStream, file2);
                                return;
                            } else {
                                a(c3);
                                ReleaseLogUtil.e("BTSYNC_PeriodRri_HwDevicePeriodRriFileManager", "transFile type is Stress file size :", Long.valueOf(file2.length()));
                                d(c3);
                            }
                        } catch (FileNotFoundException unused) {
                            c("saveTransferDataContent FileNotFoundException");
                            sqo.am("HwDevicePeriodRriFileManager saveTransferDataContent: FileNotFoundException");
                            a(fileInputStream, file2);
                        } catch (IOException unused2) {
                            c("saveTransferDataContent IOException");
                            sqo.am("HwDevicePeriodRriFileManager saveTransferDataContent: IOException");
                            a(fileInputStream, file2);
                        }
                    } catch (FileNotFoundException unused3) {
                        file2 = file;
                    } catch (IOException unused4) {
                        file2 = file;
                    } catch (Throwable th) {
                        th = th;
                        a(fileInputStream, file);
                        throw th;
                    }
                } else {
                    LogUtil.h("HwDevicePeriodRriFileManager", "on this time no data startTime :", Long.valueOf(j()), "endTime :", Long.valueOf(this.m));
                    c("finalTransferDataContent is empty or error");
                    file2 = null;
                }
            } catch (Throwable th2) {
                fileInputStream = null;
                th = th2;
                file = str;
            }
        } catch (FileNotFoundException unused5) {
            file2 = null;
        } catch (IOException unused6) {
            file2 = null;
        } catch (Throwable th3) {
            th = th3;
            file = null;
        }
        a(fileInputStream, file2);
    }

    private void d(PeriodRriEntity periodRriEntity) {
        int fetchVersion = periodRriEntity.fetchVersion();
        ReleaseLogUtil.e("BTSYNC_PeriodRri_HwDevicePeriodRriFileManager", "parse file version is :", Integer.valueOf(fetchVersion));
        if (fetchVersion >= 3) {
            if (fetchVersion == 3) {
                e(periodRriEntity);
                return;
            } else {
                b();
                return;
            }
        }
        boolean c2 = c(periodRriEntity);
        LogUtil.a("HwDevicePeriodRriFileManager", "isAllDataDirty :", Boolean.valueOf(c2));
        if (c2) {
            g();
        } else {
            d(kes.c().d(periodRriEntity));
        }
    }

    private void g() {
        long j = this.d + 1;
        this.m = j;
        LogUtil.a("HwDevicePeriodRriFileManager", "update mUploadLastTime :", Long.valueOf(j), "All data is dirty");
        a(this.m);
        b();
    }

    private void a(FileInputStream fileInputStream, File file) {
        try {
            if (fileInputStream != null) {
                fileInputStream.close();
            } else {
                LogUtil.h("HwDevicePeriodRriFileManager", "finally closeStream fileInputStream is null");
            }
            if (file != null) {
                LogUtil.a("HwDevicePeriodRriFileManager", "delete result:", Boolean.valueOf(file.delete()));
            }
            LogUtil.a("HwDevicePeriodRriFileManager", "finally close");
        } catch (IOException unused) {
            LogUtil.b("HwDevicePeriodRriFileManager", "closeStream IOException");
        }
    }

    public void getPeriodRriFileFromDevice() {
        ReleaseLogUtil.e("BTSYNC_PeriodRri_HwDevicePeriodRriFileManager", "getPeriodRriFileFromDevice enter");
        DeviceInfo b = keb.b("HwDevicePeriodRriFileManager");
        if (b != null && jpo.c(b.getDeviceIdentify()) == 2) {
            ReleaseLogUtil.d("BTSYNC_PeriodRri_HwDevicePeriodRriFileManager", "getPeriodRriFileFromDevice mode is FAMILY_PAIR_MODE");
            ket.a().e("HwDevicePeriodRriFileManager");
            return;
        }
        if (b == null || b.getDeviceConnectState() != 2) {
            ReleaseLogUtil.d("BTSYNC_PeriodRri_HwDevicePeriodRriFileManager", "getPeriodRriFileFromDevice DeviceInfo is null or not connect");
            ket.a().e("HwDevicePeriodRriFileManager");
            return;
        }
        if (!ThermalCallback.getInstance().isTriggerTask()) {
            ReleaseLogUtil.d("BTSYNC_PeriodRri_HwDevicePeriodRriFileManager", "getPeriodRriFileFromDevice does not have the conditions to trigger the task.");
            return;
        }
        if (!keb.c(keb.b("HwDevicePeriodRriFileManager"))) {
            ReleaseLogUtil.d("BTSYNC_PeriodRri_HwDevicePeriodRriFileManager", "getPeriodRriFileFromDevice checkHiHealthType is false");
            ket.a().e("HwDevicePeriodRriFileManager");
            return;
        }
        if (this.f) {
            ReleaseLogUtil.d("BTSYNC_PeriodRri_HwDevicePeriodRriFileManager", "getPeriodRriFileFromDevice is running");
            return;
        }
        this.f = true;
        h();
        this.c = -1L;
        DeviceCapability e2 = keb.e();
        if (e2 == null) {
            ReleaseLogUtil.d("BTSYNC_PeriodRri_HwDevicePeriodRriFileManager", "capability is null");
            b();
        } else if (!e2.isSupportPressAutoMonitor()) {
            ReleaseLogUtil.d("BTSYNC_PeriodRri_HwDevicePeriodRriFileManager", "no support press data");
            b();
        } else if (e2.isSupportRriNewFile()) {
            b(true, b);
        } else {
            b(false, b);
        }
    }

    public boolean isPeriodRriSyncing() {
        return this.f;
    }

    private void b(final boolean z, final DeviceInfo deviceInfo) {
        jrq.b("HwDevicePeriodRriFileManager", new Runnable() { // from class: com.huawei.hwdevice.phoneprocess.mgr.hwsyncmgr.periodrri.HwDevicePeriodRriFileManager.4
            @Override // java.lang.Runnable
            public void run() {
                if (!HwDevicePeriodRriFileManager.this.c(deviceInfo)) {
                    int[] f = HwDevicePeriodRriFileManager.this.f();
                    int i = f[0];
                    int i2 = f[1];
                    if (i >= i2 && i - i2 <= 300) {
                        LogUtil.a("HwDevicePeriodRriFileManager", "future time :", Integer.valueOf(i));
                        i = i2 - 61;
                    } else if (i - i2 > 300) {
                        LogUtil.a("HwDevicePeriodRriFileManager", "future time :", Integer.valueOf(i));
                        i = i2 - 61;
                        HwDevicePeriodRriFileManager.this.a(i);
                    } else if (i < 0) {
                        i = 0;
                    } else {
                        LogUtil.a("HwDevicePeriodRriFileManager", "normal time no need change.");
                    }
                    if (HwDevicePeriodRriFileManager.this.b == null) {
                        HwDevicePeriodRriFileManager.this.c("mPeriodFileCallback is null");
                        return;
                    }
                    HwDevicePeriodRriFileManager.this.j = System.currentTimeMillis();
                    if (z) {
                        ReleaseLogUtil.e("BTSYNC_PeriodRri_HwDevicePeriodRriFileManager", "syncPeriodRriFileFromDevice startRequestFile");
                        jyp.c().b(HwDevicePeriodRriFileManager.this.b(f), HwDevicePeriodRriFileManager.this.b);
                        return;
                    }
                    ReleaseLogUtil.e("BTSYNC_PeriodRri_HwDevicePeriodRriFileManager", "syncPeriodRriFileFromDevice getFile");
                    TransferFileInfo transferFileInfo = new TransferFileInfo();
                    transferFileInfo.setType(4);
                    transferFileInfo.setPriority(2);
                    transferFileInfo.setSuspend(0);
                    transferFileInfo.setTaskType(0);
                    transferFileInfo.setRecordId(new ArrayList(16));
                    transferFileInfo.setStartTime(i);
                    transferFileInfo.setEndTime(i2);
                    jvl.b().b(transferFileInfo, HwDevicePeriodRriFileManager.this.b);
                    return;
                }
                ReleaseLogUtil.e("BTSYNC_PeriodRri_HwDevicePeriodRriFileManager", "User need do pressure adjust first");
                HwDevicePeriodRriFileManager.this.b();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean c(DeviceInfo deviceInfo) {
        boolean c2 = cwi.c(deviceInfo, 206);
        boolean b = dkx.b();
        if (c2 && !b) {
            ReleaseLogUtil.e("BTSYNC_PeriodRri_HwDevicePeriodRriFileManager", "checkPressureAdjust isSupportEmotionAutoMonitor");
            return false;
        }
        if (!i()) {
            ReleaseLogUtil.e("BTSYNC_PeriodRri_HwDevicePeriodRriFileManager", "User do not pressure adjust");
            b();
            return true;
        }
        ReleaseLogUtil.e("BTSYNC_PeriodRri_HwDevicePeriodRriFileManager", "User has pressure adjust");
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public jqj b(int[] iArr) {
        jqj jqjVar = new jqj();
        jqjVar.a("rrisqi_data.bin");
        jqjVar.d(16);
        jqjVar.a(false);
        jqjVar.e(iArr);
        jqjVar.c((String) null);
        return jqjVar;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int[] f() {
        int currentTimeMillis = (int) (System.currentTimeMillis() / 1000);
        int j = (int) j();
        int[] iArr = {j, currentTimeMillis};
        this.m = currentTimeMillis;
        ReleaseLogUtil.e("BTSYNC_PeriodRri_HwDevicePeriodRriFileManager", "lastSyncTime :", Integer.valueOf(j), ", now :", Integer.valueOf(currentTimeMillis));
        return iArr;
    }

    private boolean c(PeriodRriEntity periodRriEntity) {
        boolean z = false;
        if (periodRriEntity == null) {
            LogUtil.h("HwDevicePeriodRriFileManager", "entity is null");
            return false;
        }
        List<PeriodRriEntity.RriDataEntity> fetchRriDataList = periodRriEntity.fetchRriDataList();
        ArrayList arrayList = new ArrayList(16);
        if (fetchRriDataList != null && !fetchRriDataList.isEmpty()) {
            boolean z2 = true;
            for (PeriodRriEntity.RriDataEntity rriDataEntity : fetchRriDataList) {
                if (e(rriDataEntity)) {
                    LogUtil.c("HwDevicePeriodRriFileManager", "ready remove dirtyData :", rriDataEntity.toString());
                    long fetchStartTime = rriDataEntity.fetchStartTime();
                    long j = this.d;
                    if (fetchStartTime > j) {
                        j = rriDataEntity.fetchStartTime();
                    }
                    this.d = j;
                } else {
                    arrayList.add(rriDataEntity);
                    z2 = false;
                }
            }
            z = z2;
        } else {
            LogUtil.h("HwDevicePeriodRriFileManager", "entity has no data");
        }
        periodRriEntity.configRriDataList(arrayList);
        return z;
    }

    private boolean e(PeriodRriEntity.RriDataEntity rriDataEntity) {
        if (rriDataEntity == null) {
            LogUtil.h("HwDevicePeriodRriFileManager", "dataEntity is null");
            return false;
        }
        List<Integer> fetchRriList = rriDataEntity.fetchRriList();
        if (fetchRriList != null && !fetchRriList.isEmpty()) {
            Iterator<Integer> it = fetchRriList.iterator();
            while (it.hasNext()) {
                if (it.next().intValue() > 2000) {
                    LogUtil.c("HwDevicePeriodRriFileManager", "This is dirty data :", rriDataEntity.toString());
                    return true;
                }
            }
            return false;
        }
        LogUtil.h("HwDevicePeriodRriFileManager", "This data has no dirty data");
        return false;
    }

    @Override // com.huawei.hwbasemgr.HwBaseManager
    public Integer getModuleId() {
        return 10;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b() {
        this.f = false;
        c();
        BroadcastManagerUtil.bFG_(BaseApplication.getContext(), new Intent("com.huawei.health.action.PERIOD_RRI_SYNC_FINISH"));
        LogUtil.a("HwDevicePeriodRriFileManager", "finishSync sendBroadcastPackage end");
        ket.a().e("HwDevicePeriodRriFileManager");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(String str) {
        Object[] objArr = new Object[1];
        if (str == null) {
            str = "null exception";
        }
        objArr[0] = str;
        ReleaseLogUtil.e("BTSYNC_PeriodRri_HwDevicePeriodRriFileManager", objArr);
        b();
    }

    private void a(PeriodRriEntity periodRriEntity) {
        List<PeriodRriEntity.RriDataEntity> fetchRriDataList = periodRriEntity.fetchRriDataList();
        if (fetchRriDataList != null && !fetchRriDataList.isEmpty()) {
            PeriodRriEntity.RriDataEntity rriDataEntity = fetchRriDataList.get(fetchRriDataList.size() - 1);
            long fetchStartTime = rriDataEntity.fetchStartTime() + 1;
            this.m = fetchStartTime;
            LogUtil.a("HwDevicePeriodRriFileManager", "max time :", Long.valueOf(fetchStartTime), "time is :", Long.valueOf(rriDataEntity.fetchStartTime()));
            return;
        }
        LogUtil.h("HwDevicePeriodRriFileManager", "no rri data. update time by press time");
        List<HiStressMetaData> fetchPressDataList = periodRriEntity.fetchPressDataList();
        if (fetchPressDataList != null && !fetchPressDataList.isEmpty()) {
            int size = fetchPressDataList.size() - 1;
            long fetchStressEndTime = fetchPressDataList.get(size).fetchStressEndTime() + 1;
            this.m = fetchStressEndTime;
            LogUtil.a("HwDevicePeriodRriFileManager", "max time :", Long.valueOf(fetchStressEndTime), "time is :", Long.valueOf(fetchPressDataList.get(size).fetchStressEndTime()));
            return;
        }
        LogUtil.h("HwDevicePeriodRriFileManager", "Data is wrong, upload time by app system time");
    }

    private long j() {
        Cursor queryStorageData = queryStorageData("HWDevicePeriodRRIFileManager", 1, "Device_ID='" + KeyValDbManager.b(BaseApplication.getContext()).e("user_id") + keb.b() + "' OR Device_ID='" + keb.b() + "'");
        long j = 0;
        if (queryStorageData == null) {
            LogUtil.h("HwDevicePeriodRriFileManager", "get lastTimeStamp query error");
            return 0L;
        }
        if (queryStorageData.moveToNext()) {
            LogUtil.a("HwDevicePeriodRriFileManager", "getLastTimestamp moveToNext() is not null");
            j = bNl_(queryStorageData);
        }
        queryStorageData.close();
        LogUtil.a("HwDevicePeriodRriFileManager", "getLastTimestamp :", Long.valueOf(j));
        return j;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(long j) {
        String e2 = KeyValDbManager.b(BaseApplication.getContext()).e("user_id");
        Cursor queryStorageData = queryStorageData("HWDevicePeriodRRIFileManager", 1, "Device_ID='" + e2 + keb.b() + "'");
        if (queryStorageData != null) {
            if (queryStorageData.moveToNext()) {
                ContentValues contentValues = new ContentValues();
                contentValues.put("Time_Stamp", Long.valueOf(j));
                updateStorageData("HWDevicePeriodRRIFileManager", 1, contentValues, "Device_ID='" + e2 + keb.b() + "'");
                LogUtil.a("HwDevicePeriodRriFileManager", "setLastTimestamp update: ", Long.valueOf(j));
            } else {
                insertStorageData("HWDevicePeriodRRIFileManager", 1, bNm_(j, e2 + keb.b()));
                LogUtil.a("HwDevicePeriodRriFileManager", "setLastTimestamp new: ", Long.valueOf(j));
            }
            queryStorageData.close();
        }
        deleteStorageData("HWDevicePeriodRRIFileManager", 1, "Device_ID='" + keb.b() + "'");
    }

    private long bNl_(Cursor cursor) {
        return cursor.getLong(cursor.getColumnIndex("Time_Stamp"));
    }

    private ContentValues bNm_(long j, String str) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("Device_ID", str);
        contentValues.put("Time_Stamp", Long.valueOf(j));
        return contentValues;
    }

    private void a() {
        if (createStorageDataTable("HWDevicePeriodRRIFileManager", 1, e()) != 0) {
            LogUtil.h("HwDevicePeriodRriFileManager", "database is bad.");
            if (!deleteDatabase()) {
                LogUtil.h("HwDevicePeriodRriFileManager", "data base error.");
            } else {
                createStorageDataTable("HWDevicePeriodRRIFileManager", 1, e());
            }
        }
    }

    private boolean i() {
        final boolean[] zArr = new boolean[1];
        final Semaphore semaphore = new Semaphore(0);
        ((HealthDataMgrApi) Services.a("HWHealthDataMgr", HealthDataMgrApi.class)).getUserPressureAdjustDatas(new IBaseResponseCallback() { // from class: com.huawei.hwdevice.phoneprocess.mgr.hwsyncmgr.periodrri.HwDevicePeriodRriFileManager.3
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public void d(int i, Object obj) {
                boolean z;
                LogUtil.a("HwDevicePeriodRriFileManager", "isAlreadyDoPressureAdjust errorCode :", Integer.valueOf(i));
                if (i == 0 && (obj instanceof String)) {
                    HiStressMetaData c2 = jlg.c((String) obj);
                    if (c2 == null) {
                        LogUtil.h("HwDevicePeriodRriFileManager", "metaData is null");
                        semaphore.release();
                        return;
                    }
                    LogUtil.a("HwDevicePeriodRriFileManager", "isAlreadyDoPressureAdjust startTime :", new Date(c2.fetchStressStartTime()));
                    int fetchStressCalibFlag = c2.fetchStressCalibFlag();
                    LogUtil.a("HwDevicePeriodRriFileManager", "calibrationMark :", Integer.valueOf(fetchStressCalibFlag));
                    int fetchStressScore = c2.fetchStressScore();
                    z = true;
                    if (fetchStressCalibFlag == 1 && fetchStressScore > 0 && fetchStressScore < 100) {
                        LogUtil.a("HwDevicePeriodRriFileManager", "isAlreadyDoPressureAdjust isAlreadyDoPressureAdjust");
                        zArr[0] = z;
                        semaphore.release();
                    }
                }
                z = false;
                zArr[0] = z;
                semaphore.release();
            }
        });
        try {
            if (semaphore.tryAcquire(5L, TimeUnit.SECONDS)) {
                LogUtil.a("HwDevicePeriodRriFileManager", "semaphore tryAcquire()");
            } else {
                LogUtil.h("HwDevicePeriodRriFileManager", "semaphore tryAcquire() fail");
            }
        } catch (InterruptedException unused) {
            LogUtil.b("HwDevicePeriodRriFileManager", "InterruptedException");
        }
        return zArr[0];
    }

    private void e(PeriodRriEntity periodRriEntity) {
        this.c = periodRriEntity.fetchFailTime();
        List<HiStressMetaData> fetchPressDataList = periodRriEntity.fetchPressDataList();
        ArrayList arrayList = new ArrayList(16);
        if (fetchPressDataList != null && fetchPressDataList.size() > 0) {
            for (HiStressMetaData hiStressMetaData : fetchPressDataList) {
                hiStressMetaData.configStressStartTime(hiStressMetaData.fetchStressStartTime() * 1000);
                hiStressMetaData.configStressEndTime(hiStressMetaData.fetchStressEndTime() * 1000);
                hiStressMetaData.configStressDevNo(keb.a());
                hiStressMetaData.configStressFeatureAtts(d());
                arrayList.add(hiStressMetaData);
            }
        } else {
            ReleaseLogUtil.d("BTSYNC_PeriodRri_HwDevicePeriodRriFileManager", "periodRriEntity.fetchPressDataList() is null");
            sqo.am("HwDevicePeriodRriFileManager startNewCycleMeasure: dataBeanList is null or empty");
        }
        if (arrayList.size() > 0) {
            d(arrayList);
        } else {
            e(false, "No periodRri data.");
        }
    }

    private void d(List<HiStressMetaData> list) {
        boolean d = keq.d(list);
        LogUtil.a("HwDevicePeriodRriFileManager", "saveData isAdjustStressData: ", Boolean.valueOf(d));
        if (!d) {
            e(false, "isAdjustStressDate is false");
            return;
        }
        DeviceInfo b = keb.b("HwDevicePeriodRriFileManager");
        if (b == null) {
            e(false, "currentDeviceInfo is null");
            return;
        }
        jpp.i(b);
        String b2 = keb.b();
        HealthDataMgrApi healthDataMgrApi = (HealthDataMgrApi) Services.a("HWHealthDataMgr", HealthDataMgrApi.class);
        if (healthDataMgrApi != null) {
            healthDataMgrApi.setStressData(b2, list, this.i);
        } else {
            LogUtil.h("HwDevicePeriodRriFileManager", "saveData healthDataMgrApi is null");
            e(false, "saveData healthDataMgrApi is null");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(boolean z, String str) {
        ReleaseLogUtil.e("BTSYNC_PeriodRri_HwDevicePeriodRriFileManager", "onResponse isSuccess :", Boolean.valueOf(z), ", message:", str);
        if (z) {
            LogUtil.a("HwDevicePeriodRriFileManager", "push data to UI success,update time success");
            long j = this.c;
            if (j != -1) {
                long j2 = j / 1000;
                this.m = j2;
                LogUtil.a("HwDevicePeriodRriFileManager", "save pressure fail.get failTime :", Long.valueOf(j2));
            }
            a(this.m);
            if (CommonUtil.as()) {
                sqo.d("RriFile sync duration: " + (System.currentTimeMillis() - this.j));
            }
        } else {
            LogUtil.h("HwDevicePeriodRriFileManager", "save fail, not update time");
        }
        b();
    }

    private List<String> d() {
        ArrayList arrayList = new ArrayList(16);
        for (int i = 0; i < 12; i++) {
            arrayList.add("0");
        }
        return arrayList;
    }

    private void h() {
        synchronized (this.n) {
            c cVar = this.g;
            if (cVar != null) {
                cVar.cancel();
            }
            this.g = new c();
            Timer timer = new Timer("HwDevicePeriodRriFileManager");
            this.h = timer;
            timer.schedule(this.g, 1200000L);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c() {
        LogUtil.h("HwDevicePeriodRriFileManager", "enter mSyncTimer");
        synchronized (this.n) {
            c cVar = this.g;
            if (cVar != null) {
                cVar.cancel();
                this.g = null;
                LogUtil.h("HwDevicePeriodRriFileManager", "sync timeout,task cancel");
            }
            Timer timer = this.h;
            if (timer != null) {
                timer.cancel();
                this.h = null;
                LogUtil.h("HwDevicePeriodRriFileManager", "sync timeout,mSyncTimer cancel");
            }
        }
    }

    class c extends TimerTask {
        c() {
            LogUtil.a("HwDevicePeriodRriFileManager", "SyncTimerTask start");
        }

        @Override // java.util.TimerTask, java.lang.Runnable
        public void run() {
            ReleaseLogUtil.e("BTSYNC_PeriodRri_HwDevicePeriodRriFileManager", "SyncTimerTask timeout");
            HwDevicePeriodRriFileManager.this.f = false;
            HwDevicePeriodRriFileManager.this.c();
            ket.a().e("HwDevicePeriodRriFileManager");
        }
    }

    private String e() {
        return "_id integer primary key autoincrement,Device_ID NVARCHAR(300) not null,Time_Stamp integer not null";
    }
}
