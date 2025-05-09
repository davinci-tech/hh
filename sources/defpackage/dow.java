package defpackage;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.MediaMetadata;
import android.media.session.PlaybackState;
import android.os.Bundle;
import android.text.TextUtils;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import com.huawei.health.playercontroller.api.PlayerEventApi;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import defpackage.eoi;
import health.compact.a.BroadcastManagerUtil;
import health.compact.a.LocalBroadcast;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.lang.ref.WeakReference;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes3.dex */
public class dow {

    /* renamed from: a, reason: collision with root package name */
    private enq f11758a;
    private long b;
    private evl c;
    private boolean d;
    private String e;
    private PlaybackState f;
    private a g;
    private eoi h;
    private String i;
    private BroadcastReceiver j;
    private long l;
    private CopyOnWriteArrayList<evl> m;
    private PlayerEventApi n;

    static class a extends eoi.e {
        private WeakReference<dow> b;

        @Override // eoi.e
        public void onMetadataChanged(MediaMetadata mediaMetadata) {
        }

        a(dow dowVar) {
            this.b = new WeakReference<>(dowVar);
        }

        @Override // eoi.e
        public void onPlaybackStateChanged(PlaybackState playbackState) {
            if (playbackState == null) {
                LogUtil.h("PlayerEventManager", "onPlaybackStateChanged, state is null ,this should NOT happen...");
                return;
            }
            dow dowVar = this.b.get();
            if (dowVar == null || TextUtils.isEmpty(dowVar.e)) {
                LogUtil.h("PlayerEventManager", "onPlaybackStateChanged manager is null");
                return;
            }
            Bundle extras = playbackState.getExtras();
            if (extras == null || !"sleepMusic".equals(extras.getString("category"))) {
                dowVar.a();
            } else {
                LogUtil.c("PlayerEventManager", "onPlaybackStateChanged state:", playbackState.toString());
                dowVar.Yk_(playbackState);
            }
        }
    }

    /* loaded from: classes8.dex */
    static class c extends BroadcastReceiver {
        private WeakReference<dow> b;

        public c(dow dowVar) {
            this.b = new WeakReference<>(dowVar);
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            LogUtil.a("PlayerEventManager", "notification event action:", intent.getAction());
            dow dowVar = this.b.get();
            if (dowVar != null) {
                if (!TextUtils.equals(dowVar.e, "sleepMusic")) {
                    LogUtil.h("PlayerEventManager", "mNotificationReceiver, is not sleep media");
                    return;
                }
                if (intent.getAction().equals("notification_play_or_pause_btn_clicked") || intent.getAction().equals("com.huawei.mediacontroller.intent.action.ButtonClick")) {
                    LogUtil.a("PlayerEventManager", "notification button clicked...  ", dowVar.f);
                    if (dowVar.f != null && dowVar.c != null) {
                        if (dowVar.n == null) {
                            LogUtil.h("PlayerEventManager", "notification mPlayerEventApi is null");
                            return;
                        } else {
                            dowVar.n.onPlayStatusChangeFromNotification(dowVar.c, dowVar.f.getState());
                            return;
                        }
                    }
                    LogUtil.h("PlayerEventManager", "current status invalid, return");
                    return;
                }
                return;
            }
            LogUtil.h("PlayerEventManager", "onPlaybackStateChanged manager is null");
        }
    }

    private void d() {
        if (this.j == null) {
            this.j = new c(this);
            IntentFilter intentFilter = new IntentFilter();
            if (eow.b()) {
                intentFilter.addAction("com.huawei.mediacontroller.intent.action.ButtonClick");
                BroadcastManagerUtil.bFC_(BaseApplication.getContext(), this.j, intentFilter, "android.permission.MEDIA_CONTENT_CONTROL", null);
            } else {
                intentFilter.addAction("notification_removed");
                intentFilter.addAction("notification_play_or_pause_btn_clicked");
                BroadcastManagerUtil.bFA_(BaseApplication.getContext(), this.j, intentFilter, LocalBroadcast.c, null);
            }
        }
    }

    private void f() {
        try {
            if (this.j != null) {
                BaseApplication.getContext().unregisterReceiver(this.j);
                this.j = null;
            }
        } catch (IllegalArgumentException e2) {
            LogUtil.b("PlayerEventManager", e2.getMessage());
        }
    }

    static class e {

        /* renamed from: a, reason: collision with root package name */
        private static final dow f11759a = new dow();
    }

    private dow() {
        this.h = eoi.c();
    }

    public static dow c() {
        return e.f11759a;
    }

    public void e(String str) {
        if (TextUtils.equals(str, this.i)) {
            LogUtil.h("PlayerEventManager", "initPlayerEventApi params is same");
            return;
        }
        this.i = str;
        h();
        d();
        try {
            JSONObject jSONObject = new JSONObject(str);
            this.e = jSONObject.getString("category");
            String string = jSONObject.getString("eventParamList");
            LogUtil.a("PlayerEventManager", "initPlayerEventApi businessTag:", this.e, "  paramListStr:", string);
            if (TextUtils.isEmpty(this.e)) {
                i();
                f();
            } else {
                this.n = dou.c(this.e);
                CopyOnWriteArrayList<evl> a2 = a(string);
                this.m = a2;
                LogUtil.a("PlayerEventManager", "initPlayerEventApi mPlayEventParamInfoList size:", Integer.valueOf(a2.size()));
            }
        } catch (JsonSyntaxException | JSONException unused) {
            LogUtil.b("PlayerEventManager", "parseParams fail:parameter invalid");
        }
    }

    private CopyOnWriteArrayList<evl> a(String str) {
        JsonArray asJsonArray = JsonParser.parseString(str).getAsJsonArray();
        Gson gson = new Gson();
        CopyOnWriteArrayList<evl> copyOnWriteArrayList = new CopyOnWriteArrayList<>();
        Iterator<JsonElement> it = asJsonArray.iterator();
        while (it.hasNext()) {
            copyOnWriteArrayList.add((evl) gson.fromJson(it.next(), evl.class));
        }
        return copyOnWriteArrayList;
    }

    public evl a(int i) {
        if (koq.b(this.m, i)) {
            return null;
        }
        return this.m.get(i);
    }

    private void h() {
        if (this.g == null) {
            a aVar = new a(this);
            this.g = aVar;
            this.h.d(aVar);
        }
    }

    private void i() {
        a aVar = this.g;
        if (aVar != null) {
            this.h.a(aVar);
            this.g = null;
        }
    }

    public void e() {
        i();
        f();
        CopyOnWriteArrayList<evl> copyOnWriteArrayList = this.m;
        if (copyOnWriteArrayList != null) {
            copyOnWriteArrayList.clear();
        }
        this.e = "";
        this.i = "";
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void Yk_(PlaybackState playbackState) {
        if (this.n == null) {
            LogUtil.h("PlayerEventManager", "onPlaybackStateChanged mPlayerEventApi is null");
            return;
        }
        int state = playbackState.getState();
        Yl_(playbackState);
        if (state == 1) {
            ReleaseLogUtil.e("R_PlayerEventManager", "onPlaybackStateChanged PlaybackState.STATE_STOPPED");
            if (this.f == null) {
                ReleaseLogUtil.d("R_PlayerEventManager", "onPlaybackStateChanged mLastState is null");
                return;
            }
            enq enqVar = this.f11758a;
            if (enqVar != null && Math.abs(enqVar.a() - (this.f.getPosition() / 1000)) < 3) {
                this.n.onPlayerFinishedMediaEvent(this.c, this.f11758a.a(), ((int) this.b) / 1000, this.l);
            } else {
                this.n.onPlayerStopEvent(this.c, this.f.getPosition() / 1000, ((int) this.b) / 1000, this.l, this.d);
            }
            this.b = 0L;
            this.c = null;
            this.f11758a = null;
        } else if (state == 2) {
            ReleaseLogUtil.e("R_PlayerEventManager", "onPlaybackStateChanged PlaybackState.STATE_PAUSED");
            this.n.onPlayerPauseEvent(this.c, playbackState.getPosition() / 1000, ((int) this.b) / 1000, this.l);
            this.d = true;
        } else if (state == 3) {
            this.d = false;
            PlaybackState playbackState2 = this.f;
            if (playbackState2 != null && (playbackState2.getState() == 10 || this.f.getState() == 6)) {
                enq enqVar2 = this.f11758a;
                if (enqVar2 != null && this.c != null) {
                    LogUtil.a("PlayerEventManager", "onPlaybackStateChanged PlaybackState.STATE_PLAYING, mCurAudioItem=", enqVar2.toString());
                    this.n.onPlayerFinishedMediaEvent(this.c, this.f11758a.a(), ((int) this.b) / 1000, this.l);
                }
            } else {
                PlaybackState playbackState3 = this.f;
                if (playbackState3 == null || (playbackState3.getState() == 3 && this.f.getPosition() == 0)) {
                    ReleaseLogUtil.e("R_PlayerEventManager", "onPlaybackStateChanged start playing");
                    this.f11758a = b();
                    evl a2 = a(this.h.f());
                    this.c = a2;
                    enq enqVar3 = this.f11758a;
                    if (enqVar3 != null) {
                        this.n.onPlayerStartPlayEvent(a2, enqVar3.a(), ((int) this.b) / 1000, this.l);
                    }
                    this.b = 0L;
                    this.l = System.currentTimeMillis();
                } else {
                    this.n.onPlayerProgressChange(this.c, playbackState.getPosition() / 1000, ((int) this.b) / 1000);
                }
            }
        }
        this.f = playbackState;
    }

    private void Yl_(PlaybackState playbackState) {
        if (playbackState.getState() != 3) {
            return;
        }
        PlaybackState playbackState2 = this.f;
        int abs = (int) Math.abs(playbackState.getPosition() - (playbackState2 == null ? 0L : playbackState2.getPosition()));
        LogUtil.c("PlayerEventManager", "updateEffectiveDuration mEffectiveDuration=", Long.valueOf(this.b));
        if (abs < 2000) {
            this.b += abs;
        }
    }

    private enq b() {
        if (koq.b(this.h.j(), this.h.f())) {
            LogUtil.h("PlayerEventManager", "getCurrentAudioItem getPlayList error, index:", Integer.valueOf(this.h.f()));
            return null;
        }
        return this.h.j().get(this.h.f());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a() {
        PlaybackState playbackState = this.f;
        if (playbackState == null) {
            return;
        }
        this.n.onPlayerStopEvent(this.c, playbackState.getPosition() / 1000, ((int) this.b) / 1000, this.l, this.d);
        LogUtil.h("PlayerEventManager", "onPlaybackStateChanged not sleep music media");
        this.b = 0L;
        this.c = null;
        this.f11758a = null;
        this.f = null;
    }
}
