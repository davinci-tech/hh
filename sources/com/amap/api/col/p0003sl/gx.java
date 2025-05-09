package com.amap.api.col.p0003sl;

import android.content.Context;
import android.os.Message;
import android.text.TextUtils;
import com.amap.api.col.p0003sl.fo;
import com.amap.api.col.p0003sl.hw;
import com.amap.api.services.core.AMapException;
import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.interfaces.INearbySearch;
import com.amap.api.services.nearby.NearbySearch;
import com.amap.api.services.nearby.NearbySearchResult;
import com.amap.api.services.nearby.UploadInfo;
import com.amap.api.services.nearby.UploadInfoCallback;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.regex.Pattern;

/* loaded from: classes8.dex */
public final class gx implements INearbySearch {
    private static long e;
    private String b;
    private Context c;
    private fo d;
    private ExecutorService f;
    private UploadInfoCallback k;
    private TimerTask l;

    /* renamed from: a, reason: collision with root package name */
    private List<NearbySearch.NearbyListener> f1087a = new ArrayList();
    private LatLonPoint g = null;
    private String h = null;
    private boolean i = false;
    private Timer j = new Timer();

    public gx(Context context) throws AMapException {
        hx a2 = hw.a(context, fc.a(false));
        if (a2.f1161a != hw.c.SuccessCode) {
            throw new AMapException(a2.b, 1, a2.b, a2.f1161a.a());
        }
        this.c = context.getApplicationContext();
        this.d = fo.a();
    }

    @Override // com.amap.api.services.interfaces.INearbySearch
    public final void addNearbyListener(NearbySearch.NearbyListener nearbyListener) {
        synchronized (this) {
            try {
                this.f1087a.add(nearbyListener);
            } catch (Throwable th) {
                fd.a(th, "NearbySearch", "addNearbyListener");
            }
        }
    }

    @Override // com.amap.api.services.interfaces.INearbySearch
    public final void removeNearbyListener(NearbySearch.NearbyListener nearbyListener) {
        synchronized (this) {
            if (nearbyListener == null) {
                return;
            }
            try {
                this.f1087a.remove(nearbyListener);
            } catch (Throwable th) {
                fd.a(th, "NearbySearch", "removeNearbyListener");
            }
        }
    }

    @Override // com.amap.api.services.interfaces.INearbySearch
    public final void clearUserInfoAsyn() {
        try {
            gj.a().a(new Runnable() { // from class: com.amap.api.col.3sl.gx.1
                @Override // java.lang.Runnable
                public final void run() {
                    Message obtainMessage = gx.this.d.obtainMessage();
                    obtainMessage.arg1 = 8;
                    obtainMessage.obj = gx.this.f1087a;
                    try {
                        try {
                            gx.this.a();
                            obtainMessage.what = 1000;
                            if (gx.this.d != null) {
                                gx.this.d.sendMessage(obtainMessage);
                            }
                        } catch (AMapException e2) {
                            obtainMessage.what = e2.getErrorCode();
                            fd.a(e2, "NearbySearch", "clearUserInfoAsyn");
                            if (gx.this.d != null) {
                                gx.this.d.sendMessage(obtainMessage);
                            }
                        }
                    } catch (Throwable th) {
                        if (gx.this.d != null) {
                            gx.this.d.sendMessage(obtainMessage);
                        }
                        throw th;
                    }
                }
            });
        } catch (Throwable th) {
            fd.a(th, "NearbySearch", "clearUserInfoAsynThrowable");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int a() throws AMapException {
        try {
            if (this.i) {
                throw new AMapException(AMapException.AMAP_CLIENT_UPLOADAUTO_STARTED_ERROR);
            }
            if (!a(this.b)) {
                throw new AMapException(AMapException.AMAP_CLIENT_USERID_ILLEGAL);
            }
            fm.a(this.c);
            return new fp(this.c, this.b).d().intValue();
        } catch (AMapException e2) {
            throw e2;
        }
    }

    @Override // com.amap.api.services.interfaces.INearbySearch
    public final void setUserID(String str) {
        this.b = str;
    }

    @Override // com.amap.api.services.interfaces.INearbySearch
    public final void startUploadNearbyInfoAuto(UploadInfoCallback uploadInfoCallback, int i) {
        TimerTask timerTask;
        synchronized (this) {
            if (i < 7000) {
                i = 7000;
            }
            try {
                this.k = uploadInfoCallback;
                if (this.i && (timerTask = this.l) != null) {
                    timerTask.cancel();
                }
                this.i = true;
                a aVar = new a(this, (byte) 0);
                this.l = aVar;
                this.j.schedule(aVar, 0L, i);
            } catch (Throwable th) {
                fd.a(th, "NearbySearch", "startUploadNearbyInfoAuto");
            }
        }
    }

    @Override // com.amap.api.services.interfaces.INearbySearch
    public final void stopUploadNearbyInfoAuto() {
        synchronized (this) {
            try {
                TimerTask timerTask = this.l;
                if (timerTask != null) {
                    timerTask.cancel();
                }
            } finally {
                this.i = false;
                this.l = null;
            }
            this.i = false;
            this.l = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int a(UploadInfo uploadInfo) {
        return this.i ? AMapException.CODE_AMAP_CLIENT_UPLOADAUTO_STARTED_ERROR : b(uploadInfo);
    }

    private static boolean a(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        return Pattern.compile("^[a-z0-9A-Z_-]{1,32}$").matcher(str).find();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int b(UploadInfo uploadInfo) {
        try {
            fm.a(this.c);
            if (uploadInfo == null) {
                return 2202;
            }
            long time = new Date().getTime();
            if (time - e < 6500) {
                return 2203;
            }
            e = time;
            String userID = uploadInfo.getUserID();
            if (!a(userID)) {
                return 2201;
            }
            if (TextUtils.isEmpty(this.h)) {
                this.h = userID;
            }
            if (!userID.equals(this.h)) {
                return 2201;
            }
            LatLonPoint point = uploadInfo.getPoint();
            if (point != null && !point.equals(this.g)) {
                new fr(this.c, uploadInfo).d();
                this.g = point.copy();
                return 1000;
            }
            return 2204;
        } catch (AMapException e2) {
            return e2.getErrorCode();
        } catch (Throwable unused) {
            return 1900;
        }
    }

    @Override // com.amap.api.services.interfaces.INearbySearch
    public final void uploadNearbyInfoAsyn(final UploadInfo uploadInfo) {
        if (this.f == null) {
            this.f = Executors.newSingleThreadExecutor();
        }
        this.f.submit(new Runnable() { // from class: com.amap.api.col.3sl.gx.2
            @Override // java.lang.Runnable
            public final void run() {
                try {
                    Message obtainMessage = gx.this.d.obtainMessage();
                    obtainMessage.arg1 = 10;
                    obtainMessage.obj = gx.this.f1087a;
                    obtainMessage.what = gx.this.a(uploadInfo);
                    gx.this.d.sendMessage(obtainMessage);
                } catch (Throwable th) {
                    fd.a(th, "NearbySearch", "uploadNearbyInfoAsyn");
                }
            }
        });
    }

    @Override // com.amap.api.services.interfaces.INearbySearch
    public final void searchNearbyInfoAsyn(final NearbySearch.NearbyQuery nearbyQuery) {
        try {
            gj.a().a(new Runnable() { // from class: com.amap.api.col.3sl.gx.3
                @Override // java.lang.Runnable
                public final void run() {
                    Message obtainMessage = gx.this.d.obtainMessage();
                    obtainMessage.arg1 = 9;
                    fo.f fVar = new fo.f();
                    fVar.f1046a = gx.this.f1087a;
                    obtainMessage.obj = fVar;
                    try {
                        try {
                            fVar.b = gx.this.searchNearbyInfo(nearbyQuery);
                            obtainMessage.what = 1000;
                            if (gx.this.d != null) {
                                gx.this.d.sendMessage(obtainMessage);
                            }
                        } catch (AMapException e2) {
                            obtainMessage.what = e2.getErrorCode();
                            fd.a(e2, "NearbySearch", "searchNearbyInfoAsyn");
                            if (gx.this.d != null) {
                                gx.this.d.sendMessage(obtainMessage);
                            }
                        }
                    } catch (Throwable th) {
                        if (gx.this.d != null) {
                            gx.this.d.sendMessage(obtainMessage);
                        }
                        throw th;
                    }
                }
            });
        } catch (Throwable th) {
            fd.a(th, "NearbySearch", "searchNearbyInfoAsynThrowable");
        }
    }

    @Override // com.amap.api.services.interfaces.INearbySearch
    public final NearbySearchResult searchNearbyInfo(NearbySearch.NearbyQuery nearbyQuery) throws AMapException {
        try {
            fm.a(this.c);
            if (!a(nearbyQuery)) {
                throw new AMapException("无效的参数 - IllegalArgumentException");
            }
            return new fq(this.c, nearbyQuery).d();
        } catch (AMapException e2) {
            throw e2;
        } catch (Throwable th) {
            fd.a(th, "NearbySearch", "searchNearbyInfo");
            throw new AMapException(AMapException.AMAP_CLIENT_UNKNOWN_ERROR);
        }
    }

    private static boolean a(NearbySearch.NearbyQuery nearbyQuery) {
        return (nearbyQuery == null || nearbyQuery.getCenterPoint() == null) ? false : true;
    }

    @Override // com.amap.api.services.interfaces.INearbySearch
    public final void destroy() {
        synchronized (this) {
            try {
                this.j.cancel();
            } catch (Throwable th) {
                fd.a(th, "NearbySearch", "destryoy");
            }
        }
    }

    final class a extends TimerTask {
        private a() {
        }

        /* synthetic */ a(gx gxVar, byte b) {
            this();
        }

        @Override // java.util.TimerTask, java.lang.Runnable
        public final void run() {
            try {
                if (gx.this.k != null) {
                    int b = gx.this.b(gx.this.k.OnUploadInfoCallback());
                    Message obtainMessage = gx.this.d.obtainMessage();
                    obtainMessage.arg1 = 10;
                    obtainMessage.obj = gx.this.f1087a;
                    obtainMessage.what = b;
                    gx.this.d.sendMessage(obtainMessage);
                }
            } catch (Throwable th) {
                fd.a(th, "NearbySearch", "UpdateDataTask");
            }
        }
    }
}
