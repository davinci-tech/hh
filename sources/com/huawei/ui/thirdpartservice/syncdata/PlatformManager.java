package com.huawei.ui.thirdpartservice.syncdata;

import androidx.core.location.LocationRequestCompat;
import com.google.android.gms.common.util.ArrayUtils;
import com.google.android.gms.common.util.CollectionUtils;
import com.huawei.haf.application.BaseApplication;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.data.model.HiTrackMetaData;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.route.TrackInfo;
import com.huawei.ui.thirdpartservice.syncdata.BasePlatformHandler;
import com.huawei.ui.thirdpartservice.syncdata.PlatformManager;
import com.huawei.ui.thirdpartservice.syncdata.callback.RefreshTokenCallback;
import com.huawei.ui.thirdpartservice.syncdata.strava.StravaConfig;
import defpackage.jdx;
import defpackage.sio;
import defpackage.sjh;
import defpackage.sjn;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.CountDownLatch;

/* loaded from: classes7.dex */
public class PlatformManager implements BasePlatformHandler.SyncResultListener {

    /* renamed from: a, reason: collision with root package name */
    private Set<String> f10563a;
    private PlatformStatusCallback b;
    private boolean d = false;
    private ArrayList<BasePlatformHandler> e = new ArrayList<>(3);

    interface PlatformStatusCallback {
        void onInitFinish(boolean z);

        void onSyncFinish();
    }

    PlatformManager() {
    }

    public void c(PlatformStatusCallback platformStatusCallback) {
        this.b = platformStatusCallback;
        LogUtil.a("PlatformManager", "init");
        if (this.d) {
            platformStatusCallback.onInitFinish(false);
            return;
        }
        Set<String> set = this.f10563a;
        if (set != null) {
            set.clear();
        }
        if (!CollectionUtils.isEmpty(this.e)) {
            this.f10563a = new HashSet(this.e.size());
            platformStatusCallback.onInitFinish(!this.d);
            return;
        }
        final CountDownLatch countDownLatch = new CountDownLatch(3);
        LogUtil.b("PlatformManager", "  CountDownLatch  3");
        jdx.b(new Runnable() { // from class: sil
            @Override // java.lang.Runnable
            public final void run() {
                PlatformManager.this.b(countDownLatch);
            }
        });
        jdx.b(new Runnable() { // from class: sik
            @Override // java.lang.Runnable
            public final void run() {
                PlatformManager.this.c(countDownLatch);
            }
        });
        jdx.b(new Runnable() { // from class: sin
            @Override // java.lang.Runnable
            public final void run() {
                PlatformManager.this.e(countDownLatch);
            }
        });
        try {
            countDownLatch.await();
        } catch (InterruptedException unused) {
            LogUtil.h("PlatformManager", " InterruptedException");
        }
        LogUtil.a("PlatformManager", "  countDown   release  mSyncDataHandlers.size():", Integer.valueOf(this.e.size()));
        if (this.e.isEmpty()) {
            this.d = false;
            this.b.onSyncFinish();
            platformStatusCallback.onInitFinish(this.d);
        } else {
            this.f10563a = new HashSet(this.e.size());
            platformStatusCallback.onInitFinish(!this.d);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: d, reason: merged with bridge method [inline-methods] */
    public void e(final CountDownLatch countDownLatch) {
        if (String.valueOf(7).equals(LoginInit.getInstance(BaseApplication.e()).getAccountInfo(1009))) {
            final sio sioVar = new sio(BaseApplication.e(), this);
            sioVar.checkConnectStatus(new RefreshTokenCallback() { // from class: sij
                @Override // com.huawei.ui.thirdpartservice.syncdata.callback.RefreshTokenCallback
                public final void refreshResult(boolean z, boolean z2) {
                    PlatformManager.this.c(sioVar, countDownLatch, z, z2);
                }
            });
        } else {
            countDownLatch.countDown();
        }
    }

    public /* synthetic */ void c(BasePlatformHandler basePlatformHandler, CountDownLatch countDownLatch, boolean z, boolean z2) {
        if (z && z2) {
            this.e.add(basePlatformHandler);
        }
        countDownLatch.countDown();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public void c(final CountDownLatch countDownLatch) {
        String accountInfo = LoginInit.getInstance(BaseApplication.e()).getAccountInfo(1009);
        if (String.valueOf(5).equals(accountInfo) || String.valueOf(7).equals(accountInfo)) {
            final sjh sjhVar = new sjh(BaseApplication.e(), this);
            sjhVar.checkConnectStatus(new RefreshTokenCallback() { // from class: sig
                @Override // com.huawei.ui.thirdpartservice.syncdata.callback.RefreshTokenCallback
                public final void refreshResult(boolean z, boolean z2) {
                    PlatformManager.this.a(sjhVar, countDownLatch, z, z2);
                }
            });
        } else {
            countDownLatch.countDown();
        }
    }

    public /* synthetic */ void a(BasePlatformHandler basePlatformHandler, CountDownLatch countDownLatch, boolean z, boolean z2) {
        if (z && z2) {
            this.e.add(basePlatformHandler);
        }
        countDownLatch.countDown();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: g, reason: merged with bridge method [inline-methods] */
    public void b(final CountDownLatch countDownLatch) {
        new StravaConfig().dYg_(null, LoginInit.getInstance(BaseApplication.e()).getAccountInfo(1010), new StravaConfig.StravaCountryCallback() { // from class: sis
            @Override // com.huawei.ui.thirdpartservice.syncdata.strava.StravaConfig.StravaCountryCallback
            public final void onStravaCountryCallback(boolean z) {
                PlatformManager.this.c(countDownLatch, z);
            }
        });
    }

    public /* synthetic */ void c(final CountDownLatch countDownLatch, boolean z) {
        LogUtil.a("PlatformManager", "checkCountrySupport  isSupport :", Boolean.valueOf(z));
        if (z) {
            final sjn sjnVar = new sjn(BaseApplication.e(), this);
            sjnVar.checkConnectStatus(new RefreshTokenCallback() { // from class: sim
                @Override // com.huawei.ui.thirdpartservice.syncdata.callback.RefreshTokenCallback
                public final void refreshResult(boolean z2, boolean z3) {
                    PlatformManager.this.b(sjnVar, countDownLatch, z2, z3);
                }
            });
        } else {
            countDownLatch.countDown();
        }
    }

    public /* synthetic */ void b(BasePlatformHandler basePlatformHandler, CountDownLatch countDownLatch, boolean z, boolean z2) {
        if (z && z2) {
            this.e.add(basePlatformHandler);
        }
        countDownLatch.countDown();
    }

    long d() {
        Iterator<BasePlatformHandler> it = this.e.iterator();
        long j = Long.MAX_VALUE;
        while (it.hasNext()) {
            long oauthTime = it.next().getOauthTime();
            if (oauthTime != -1) {
                j = Math.min(j, oauthTime);
            }
        }
        LogUtil.a("PlatformManager", "  calculateStartTime   ", Long.valueOf(j));
        if (j == LocationRequestCompat.PASSIVE_INTERVAL) {
            this.d = false;
            this.b.onSyncFinish();
        }
        return j;
    }

    int[] a() {
        HashSet hashSet = new HashSet();
        Iterator<BasePlatformHandler> it = this.e.iterator();
        while (it.hasNext()) {
            hashSet.addAll(it.next().getSyncDataType());
        }
        return ArrayUtils.toPrimitiveArray(hashSet);
    }

    void e(HiHealthData hiHealthData, HiTrackMetaData hiTrackMetaData, TrackInfo trackInfo) {
        Iterator<BasePlatformHandler> it = this.e.iterator();
        while (it.hasNext()) {
            BasePlatformHandler next = it.next();
            if (next.needUpload(hiHealthData)) {
                this.d = true;
                next.runSyncTask(hiHealthData, hiTrackMetaData, trackInfo);
            }
        }
    }

    boolean b(HiHealthData hiHealthData) {
        Iterator<BasePlatformHandler> it = this.e.iterator();
        while (it.hasNext()) {
            if (it.next().needUpload(hiHealthData)) {
                return true;
            }
        }
        return false;
    }

    @Override // com.huawei.ui.thirdpartservice.syncdata.BasePlatformHandler.SyncResultListener
    public void syncResultCallback(String str, boolean z, String str2) {
        LogUtil.a("PlatformManager", str, Boolean.valueOf(z), str2);
        if (this.f10563a == null) {
            return;
        }
        Iterator<BasePlatformHandler> it = this.e.iterator();
        while (it.hasNext()) {
            BasePlatformHandler next = it.next();
            if (next.isSyncFinish()) {
                this.f10563a.add(next.getPlatform());
            }
        }
        if (this.f10563a.size() == this.e.size()) {
            this.d = false;
            this.b.onSyncFinish();
        }
    }

    public void b() {
        Iterator<BasePlatformHandler> it = this.e.iterator();
        while (it.hasNext()) {
            it.next().release();
        }
        this.e.clear();
        this.e.trimToSize();
    }

    void c() {
        Iterator<BasePlatformHandler> it = this.e.iterator();
        while (it.hasNext()) {
            it.next().forceStopSync();
        }
    }
}
