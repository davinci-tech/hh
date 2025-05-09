package com.huawei.ui.thirdpartservice.syncdata;

import android.content.Context;
import android.text.TextUtils;
import android.util.SparseArray;
import com.google.gson.reflect.TypeToken;
import com.huawei.haf.application.BaseApplication;
import com.huawei.hihealth.HiDataReadOption;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.api.HiHealthManager;
import com.huawei.hihealth.api.HiHealthNativeApi;
import com.huawei.hihealth.data.listener.HiDataReadResultListener;
import com.huawei.hihealth.data.model.HiTrackMetaData;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.skinner.internal.OnRegisterSkinAttrsListener;
import com.huawei.ui.thirdpartservice.syncdata.PlatformManager;
import com.huawei.ui.thirdpartservice.syncdata.SyncDataManager;
import com.huawei.ui.thirdpartservice.syncdata.constants.SyncDataConstant;
import defpackage.ixu;
import defpackage.jdx;
import defpackage.koq;
import defpackage.moh;
import defpackage.sjs;
import health.compact.a.CommonUtil;
import health.compact.a.LogAnonymous;
import java.io.File;
import java.io.IOException;
import java.util.List;

/* loaded from: classes7.dex */
public class SyncDataManager {

    /* renamed from: a, reason: collision with root package name */
    private PlatformManager f10564a = new PlatformManager();
    private final SyncStatusListener c;
    private final Context e;

    public interface SyncStatusListener {
        void endSyncData();
    }

    public SyncDataManager(Context context, SyncStatusListener syncStatusListener) {
        this.e = context.getApplicationContext();
        this.c = syncStatusListener;
    }

    public void b() {
        PlatformManager platformManager = this.f10564a;
        if (platformManager != null) {
            platformManager.b();
        }
    }

    public void d() {
        PlatformManager platformManager = this.f10564a;
        if (platformManager != null) {
            platformManager.c();
        }
    }

    public /* synthetic */ void a() {
        this.f10564a.c(new PlatformManager.PlatformStatusCallback() { // from class: com.huawei.ui.thirdpartservice.syncdata.SyncDataManager.2
            @Override // com.huawei.ui.thirdpartservice.syncdata.PlatformManager.PlatformStatusCallback
            public void onInitFinish(boolean z) {
                if (!z || SyncDataManager.this.f10564a == null) {
                    SyncDataManager.this.c.endSyncData();
                } else {
                    SyncDataManager syncDataManager = SyncDataManager.this;
                    syncDataManager.e(syncDataManager.f10564a.d(), System.currentTimeMillis());
                }
            }

            @Override // com.huawei.ui.thirdpartservice.syncdata.PlatformManager.PlatformStatusCallback
            public void onSyncFinish() {
                LogUtil.a("SyncDataManager", "PlatformManager onSyncFinish");
                SyncDataManager.this.e();
                SyncDataManager.this.c.endSyncData();
            }
        });
    }

    public void c() {
        jdx.b(new Runnable() { // from class: sip
            @Override // java.lang.Runnable
            public final void run() {
                SyncDataManager.this.a();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(long j, long j2) {
        LogUtil.a("SyncDataManager", " load sync data: ", Long.valueOf(j), " to ", Long.valueOf(j2));
        HiDataReadOption hiDataReadOption = new HiDataReadOption();
        hiDataReadOption.setTimeInterval(j, j2);
        hiDataReadOption.setType(this.f10564a.a());
        hiDataReadOption.setSortOrder(0);
        HiHealthNativeApi.a(this.e).fetchSequenceDataBySportType(hiDataReadOption, new HiDataReadResultListener() { // from class: com.huawei.ui.thirdpartservice.syncdata.SyncDataManager.1
            @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
            public void onResultIntent(int i, Object obj, int i2, int i3) {
            }

            @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
            public void onResult(Object obj, int i, int i2) {
                if (i != 0 || !(obj instanceof SparseArray)) {
                    SyncDataManager.this.c.endSyncData();
                } else {
                    SyncDataManager.this.dXX_((SparseArray) obj);
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void dXX_(SparseArray<?> sparseArray) {
        boolean z = false;
        if (!koq.e(sparseArray.get(0), HiHealthData.class)) {
            this.c.endSyncData();
            return;
        }
        List<HiHealthData> list = (List) sparseArray.get(0);
        if (list.isEmpty()) {
            this.c.endSyncData();
            return;
        }
        for (HiHealthData hiHealthData : list) {
            if (this.f10564a.b(hiHealthData)) {
                LogUtil.a("SyncDataManager", "execute upload start time: ", Long.valueOf(hiHealthData.getStartTime()), " end time:", Long.valueOf(hiHealthData.getEndTime()));
                HiTrackMetaData a2 = a(hiHealthData);
                if (a2 != null) {
                    e(this.e, a2, hiHealthData.getStartTime(), hiHealthData.getEndTime());
                    z = true;
                }
            }
        }
        if (z) {
            return;
        }
        LogUtil.a("SyncDataManager", "no data need sync");
        this.c.endSyncData();
    }

    private void e(Context context, final HiTrackMetaData hiTrackMetaData, long j, long j2) {
        HiDataReadOption hiDataReadOption = new HiDataReadOption();
        hiDataReadOption.setTimeInterval(j, j2);
        hiDataReadOption.setType(new int[]{OnRegisterSkinAttrsListener.REGISTER_BY_SCAN});
        HiHealthManager.d(context).readHiHealthData(hiDataReadOption, new HiDataReadResultListener() { // from class: com.huawei.ui.thirdpartservice.syncdata.SyncDataManager.4
            @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
            public void onResultIntent(int i, Object obj, int i2, int i3) {
            }

            @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
            public void onResult(Object obj, int i, int i2) {
                if (i == 0 && (obj instanceof SparseArray)) {
                    SyncDataManager.this.dXY_((SparseArray) obj, hiTrackMetaData);
                } else {
                    LogUtil.h("SyncDataManager", "Query detail data error: ");
                }
            }
        });
    }

    private HiTrackMetaData a(HiHealthData hiHealthData) {
        HiTrackMetaData hiTrackMetaData = (HiTrackMetaData) ixu.e(hiHealthData.getMetaData(), new TypeToken<HiTrackMetaData>() { // from class: com.huawei.ui.thirdpartservice.syncdata.SyncDataManager.5
        });
        if (hiTrackMetaData == null) {
            LogUtil.h("SyncDataManager", "Track meta data is null.");
            return null;
        }
        int abnormalTrack = hiTrackMetaData.getAbnormalTrack();
        int duplicated = hiTrackMetaData.getDuplicated();
        if (abnormalTrack == 0 && duplicated == 0) {
            return hiTrackMetaData;
        }
        LogUtil.h("SyncDataManager", "TrackMetaData is abnormal or repetition: ", Integer.valueOf(abnormalTrack), ", ", Integer.valueOf(duplicated));
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void dXY_(SparseArray<?> sparseArray, HiTrackMetaData hiTrackMetaData) {
        LogUtil.a("SyncDataManager", "parseSingleData: ");
        Object obj = sparseArray.get(OnRegisterSkinAttrsListener.REGISTER_BY_SCAN);
        if (!koq.e(obj, HiHealthData.class)) {
            LogUtil.h("SyncDataManager", "paseSingleData onResult obj not instanceof List");
            return;
        }
        List list = (List) obj;
        if (list.isEmpty()) {
            LogUtil.h("SyncDataManager", "paseSingleData onResult list is empty");
            return;
        }
        HiHealthData hiHealthData = (HiHealthData) list.get(0);
        if (hiHealthData == null) {
            LogUtil.h("SyncDataManager", "paseSingleData onResult hiHealthData is null");
            return;
        }
        if (this.f10564a.b(hiHealthData)) {
            try {
                String c = CommonUtil.c(BaseApplication.e().getFilesDir().getCanonicalPath());
                if (TextUtils.isEmpty(c)) {
                    LogUtil.h("SyncDataManager", "readTemporaryMotionPath savePath is empty");
                    return;
                }
                String d = sjs.d(c, hiHealthData.getSequenceFileUrl());
                if (TextUtils.isEmpty(d)) {
                    LogUtil.h("SyncDataManager", "Sequence file null: ", c, hiHealthData.getSequenceFileUrl());
                } else {
                    LogUtil.a("SyncDataManager", "createGpxFile");
                    this.f10564a.e(hiHealthData, hiTrackMetaData, sjs.c(d));
                }
            } catch (IOException e) {
                LogUtil.b("SyncDataManager", LogAnonymous.b((Throwable) e));
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e() {
        try {
            if (!moh.c(i())) {
                LogUtil.h("SyncDataManager", "delete gpx file error.");
            } else {
                LogUtil.a("SyncDataManager", "delete gpx file success.");
            }
        } catch (IOException e) {
            LogUtil.h("SyncDataManager", "delete gpx file error.", e.getMessage());
        }
    }

    private File i() throws IOException {
        return new File(CommonUtil.c(BaseApplication.e().getFilesDir().getCanonicalPath()), SyncDataConstant.SYNC_DATA_TRACKS_FOLDER + File.separator + SyncDataConstant.SYNC_DATA_TRACKS_EXPORT_FOLDER);
    }
}
