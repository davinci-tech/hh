package com.huawei.hianalytics;

import android.text.TextUtils;
import com.huawei.hianalytics.core.common.EnvUtils;
import com.huawei.hianalytics.core.log.HiLog;
import com.huawei.media.controller.MediaControlCenter;
import com.huawei.media.controller.MediaEvent;
import com.huawei.media.controller.MediaEventFilter;
import com.huawei.media.controller.MediaEventObserver;
import com.huawei.media.controller.MediaPlayerInfo;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: classes4.dex */
public class z {

    /* renamed from: a, reason: collision with root package name */
    public a0 f3966a;

    /* renamed from: a, reason: collision with other field name */
    public final Map<String, String> f116a = new HashMap();

    /* renamed from: a, reason: collision with other field name */
    public boolean f117a = false;

    /* renamed from: a, reason: collision with other field name */
    public String f115a = "";
    public boolean b = false;

    /* renamed from: a, reason: collision with other field name */
    public final MediaControlCenter f113a = MediaControlCenter.getInstance();

    /* renamed from: a, reason: collision with other field name */
    public final MediaEventObserver f114a = new b();

    public static class c {

        /* renamed from: a, reason: collision with root package name */
        public static final z f3968a = new z();
    }

    public final boolean a(MediaEvent mediaEvent) {
        if (this.f116a.get(mediaEvent.getPlayerId()) != null) {
            this.f115a = this.f116a.get(mediaEvent.getPlayerId());
        }
        if (TextUtils.isEmpty(this.f115a)) {
            return false;
        }
        w a2 = w.a();
        String str = this.f115a;
        if (a2.f93a.isEmpty()) {
            return true;
        }
        return a2.f93a.contains(str);
    }

    public void b() {
        synchronized (this) {
            if (this.f117a) {
                HiLog.d("MediaHelper", "register: hasInit");
                return;
            }
            try {
                a();
            } catch (Throwable th) {
                HiLog.w("MediaHelper", "register error: " + th.getMessage());
            }
            if (this.f116a.isEmpty()) {
                return;
            }
            synchronized (this) {
                HiLog.d("MediaHelper", "unregister");
                if (this.f117a) {
                    this.f113a.unregisterEventObserver(this.f114a);
                }
                c();
                this.f117a = true;
            }
        }
    }

    public final void c() {
        if (w.a().f94a) {
            w a2 = w.a();
            a2.getClass();
            if (a2.f95b.contains(j.b(EnvUtils.getAppContext()))) {
                MediaEventFilter mediaEventFilter = new MediaEventFilter();
                Iterator<String> it = this.f116a.keySet().iterator();
                while (it.hasNext()) {
                    mediaEventFilter.addPlayerId(it.next());
                }
                mediaEventFilter.addEventType(20);
                mediaEventFilter.addEventType(3);
                mediaEventFilter.addEventType(11);
                mediaEventFilter.addEventType(4);
                this.f113a.registerEventObserver(this.f114a, mediaEventFilter);
            }
        }
    }

    public class b implements MediaEventObserver {
        /* JADX WARN: Code restructure failed: missing block: B:97:0x01b8, code lost:
        
            if (r2.b >= com.huawei.hianalytics.w.a().f99e) goto L84;
         */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public void onEvent(com.huawei.media.controller.MediaEvent r16) {
            /*
                Method dump skipped, instructions count: 615
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: com.huawei.hianalytics.z.b.onEvent(com.huawei.media.controller.MediaEvent):void");
        }

        public b() {
        }
    }

    public final void a() {
        try {
            List<MediaPlayerInfo> playerInfoList = this.f113a.getPlayerInfoList();
            if (playerInfoList.isEmpty()) {
                playerInfoList = this.f113a.getHistoryMediaPlayerInfoList();
            }
            this.f116a.clear();
            for (MediaPlayerInfo mediaPlayerInfo : playerInfoList) {
                this.f116a.put(mediaPlayerInfo.getPlayerId(), mediaPlayerInfo.getPackageName());
            }
        } catch (Exception unused) {
            HiLog.w("MediaHelper", "RPI Exception");
        }
    }
}
