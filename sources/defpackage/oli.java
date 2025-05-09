package defpackage;

import android.content.BroadcastReceiver;
import android.content.ComponentCallbacks2;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Configuration;
import android.media.MediaMetadata;
import android.media.browse.MediaBrowser;
import android.media.session.MediaController;
import android.media.session.PlaybackState;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.media.MediaMetadataCompat;
import android.text.TextUtils;
import androidx.core.util.Consumer;
import com.google.gson.reflect.TypeToken;
import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.handler.BaseHandler;
import com.huawei.haf.handler.HandlerExecutor;
import com.huawei.health.musicservice.api.AudioService;
import com.huawei.health.musicservice.api.IAudioPlayer;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwdevice.phoneprocess.mgr.exercise.HwExerciseConstants;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.openalliance.ad.constant.VastAttribute;
import com.huawei.pluginachievement.manager.model.KakaConstants;
import com.huawei.ui.homehealth.healthheadlinecard.HealthHeadLinesInfoManager;
import com.huawei.ui.homehealth.healthheadlinecard.MediaStatusSubscriber;
import defpackage.oli;
import health.compact.a.BroadcastManagerUtil;
import health.compact.a.LocalBroadcast;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.CopyOnWriteArrayList;

/* loaded from: classes6.dex */
public class oli {

    /* renamed from: a, reason: collision with root package name */
    private int f15765a;
    private CopyOnWriteArrayList<okx> aa;
    private boolean ab;
    private boolean ac;
    private boolean ad;
    private boolean ae;
    private int af;
    private boolean ag;
    private boolean ah;
    private boolean ai;
    private int aj;
    private String ak;
    private volatile MediaBrowser al;
    private String am;
    private MediaController an;
    private ConcurrentHashMap<String, Integer> ao;
    private List<MediaStatusSubscriber> ap;
    private Queue<Runnable> aq;
    private final BroadcastReceiver ar;
    private List<enq> as;
    private List<oly> aw;
    private ConcurrentHashMap<String, String> b;
    private e c;
    private c d;
    private boolean e;
    private final ComponentCallbacks2 f;
    private Context g;
    private int h;
    private long i;
    private enq j;
    private IAudioPlayer.PlayMode k;
    private String l;
    private int m;
    private String n;
    private volatile int o;
    private float p;
    private int q;
    private int r;
    private int s;
    private float t;
    private int u;
    private final Runnable v;
    private Handler w;
    private String x;
    private String y;
    private int z;

    /* synthetic */ void u() {
        d();
        this.o = 0;
        olu p = p();
        if (p == null) {
            p = new olu();
        }
        List<enq> c2 = p.c();
        if (koq.c(c2)) {
            this.j = c2.get(0);
        }
        Iterator<MediaStatusSubscriber> it = this.ap.iterator();
        while (it.hasNext()) {
            it.next().onMediaChanged(this.j, this.o);
        }
        this.ah = true;
    }

    private oli() {
        this.g = BaseApplication.e();
        this.j = new enq();
        this.ae = false;
        this.r = 0;
        this.q = 0;
        this.af = 0;
        this.t = 0.0f;
        this.u = 0;
        this.p = 0.0f;
        this.i = 0L;
        this.s = 0;
        this.z = 0;
        this.e = false;
        this.h = 0;
        this.y = "2";
        this.n = "2";
        this.m = 2;
        this.ap = new CopyOnWriteArrayList();
        this.aq = new ConcurrentLinkedDeque();
        this.as = new CopyOnWriteArrayList();
        this.aw = new CopyOnWriteArrayList();
        this.ao = new ConcurrentHashMap<>();
        this.b = new ConcurrentHashMap<>();
        this.o = 0;
        this.ag = false;
        this.f15765a = 0;
        this.aj = 0;
        this.c = new e();
        this.d = new c();
        this.ad = false;
        this.ab = false;
        this.ac = false;
        this.ah = false;
        this.ai = false;
        this.aa = new CopyOnWriteArrayList<>();
        this.ar = new BroadcastReceiver() { // from class: oli.4
            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context, Intent intent) {
                if (!TextUtils.equals(oli.a().q(), "headLine")) {
                    LogUtil.h("MediaCenter", "mNotificationReceiver, is not headline media");
                    return;
                }
                if (intent.getAction().equals("notification_removed")) {
                    oli.this.i(-4);
                    return;
                }
                if (intent.getAction().equals("notification_play_or_pause_btn_clicked")) {
                    health.compact.a.LogUtil.c("MediaCenter", "notification button clicked...");
                    if (oli.this.s == 3) {
                        oli.this.ab = true;
                        oli oliVar = oli.this;
                        oliVar.a(2, oliVar.s);
                        return;
                    } else {
                        oli.this.ac = true;
                        oli.this.f(11);
                        return;
                    }
                }
                health.compact.a.LogUtil.e("MediaCenter", "unknown event, this should NOT happen...");
            }
        };
        this.f = new ComponentCallbacks2() { // from class: oli.5
            @Override // android.content.ComponentCallbacks
            public void onConfigurationChanged(Configuration configuration) {
            }

            @Override // android.content.ComponentCallbacks2
            public void onTrimMemory(int i2) {
                LogUtil.a("MediaCenter", "ComponentCallbacks2 level is: ", Integer.valueOf(i2));
                if ((i2 == 5 || i2 == 10) && oli.this.s == 2) {
                    oli.this.d();
                    oli.this.ai = true;
                }
            }

            @Override // android.content.ComponentCallbacks
            public void onLowMemory() {
                LogUtil.a("MediaCenter", "ComponentCallbacks2 onLowMemory");
                if (oli.this.s == 2) {
                    oli.this.d();
                    oli.this.ai = true;
                }
            }
        };
        this.v = new Runnable() { // from class: olk
            @Override // java.lang.Runnable
            public final void run() {
                oli.this.u();
            }
        };
        ah();
        this.k = al();
        HandlerExecutor.e(new Runnable() { // from class: oll
            @Override // java.lang.Runnable
            public final void run() {
                oli.this.x();
            }
        });
    }

    /* synthetic */ void x() {
        if (this.w == null) {
            this.w = new i();
            LogUtil.a("MediaCenter", "init handler");
        }
    }

    public static oli a() {
        return j.e;
    }

    public void z() {
        this.d = new c();
        this.r = 0;
        this.i = 0L;
        this.as.clear();
        this.j = new enq();
        this.o = 0;
        j("");
        this.aa.clear();
    }

    private void ah() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("notification_removed");
        intentFilter.addAction("notification_play_or_pause_btn_clicked");
        BroadcastManagerUtil.bFA_(this.g, this.ar, intentFilter, LocalBroadcast.c, null);
    }

    public void e(final Consumer<Boolean> consumer) {
        if (!HandlerExecutor.c()) {
            HandlerExecutor.a(new Runnable() { // from class: oli.1
                @Override // java.lang.Runnable
                public void run() {
                    oli.this.e(consumer);
                }
            });
            return;
        }
        if (this.al == null || !this.al.isConnected()) {
            ReleaseLogUtil.e("R_MediaCenter", "mediaBrowser start to connect");
            this.al = new MediaBrowser(this.g, new ComponentName(BaseApplication.e(), (Class<?>) AudioService.class), new d(consumer), null);
            this.al.connect();
            ae();
            return;
        }
        if (consumer != null) {
            consumer.accept(true);
            ReleaseLogUtil.e("R_MediaCenter", "mediaBrowser already connected before");
        } else {
            ReleaseLogUtil.c("R_MediaCenter", "init do nothing");
        }
    }

    public void ab() {
        b(this.m);
    }

    public void d(final int i2) {
        Object[] objArr = new Object[4];
        objArr[0] = "playWithInitList audioItemList size = ";
        List<enq> list = this.as;
        objArr[1] = Integer.valueOf(list != null ? list.size() : 0);
        objArr[2] = ", detailList = ";
        List<oly> list2 = this.aw;
        objArr[3] = Integer.valueOf(list2 != null ? list2.size() : 0);
        LogUtil.a("MediaCenter", objArr);
        if (!d("playWithInitList")) {
            e(new Consumer<Boolean>() { // from class: oli.3
                @Override // androidx.core.util.Consumer
                /* renamed from: d, reason: merged with bridge method [inline-methods] */
                public void accept(Boolean bool) {
                    if (bool == null || !bool.booleanValue()) {
                        return;
                    }
                    if (!koq.b(oli.this.as) && !koq.b(oli.this.aw)) {
                        if (oli.this.ah) {
                            olu p = oli.this.p();
                            if (p == null) {
                                return;
                            }
                            List<enq> c2 = p.c();
                            List<oly> d2 = p.d();
                            if (koq.b(c2) || koq.b(d2)) {
                                LogUtil.b("MediaCenter", "playWithInitList failed cause play list is empty!");
                                return;
                            } else {
                                oli.this.b(new ArrayList(c2), new ArrayList(d2), 0, null);
                                oli.this.ah = false;
                            }
                        } else {
                            oli.this.b(new ArrayList(oli.this.as), new ArrayList(oli.this.aw), oli.this.ai ? oli.this.o : 0, null);
                        }
                        oli.this.y();
                        if (oli.this.ai) {
                            oli.this.c(r7.o);
                            oli.this.ai = false;
                            return;
                        }
                        oli.this.b(i2);
                        return;
                    }
                    LogUtil.b("MediaCenter", "playWithInitList failed cause play list is empty!");
                }
            });
            return;
        }
        this.ac = true;
        this.ad = false;
        this.e = false;
        this.an.getTransportControls().play();
        f(i2);
    }

    /* renamed from: e, reason: merged with bridge method [inline-methods] */
    public void b(final int i2) {
        LogUtil.a("MediaCenter", "play biFrom:", Integer.valueOf(i2));
        if (!d("play")) {
            this.aq.add(new Runnable() { // from class: ols
                @Override // java.lang.Runnable
                public final void run() {
                    oli.this.b(i2);
                }
            });
            e((Consumer<Boolean>) null);
            return;
        }
        this.ac = true;
        this.ad = false;
        this.e = false;
        this.an.getTransportControls().play();
        f(i2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void f(int i2) {
        if (i2 != 0) {
            this.aj = i2;
        }
        if (i2 == 11) {
            this.f15765a = i2;
        }
        this.c.e();
        this.d.a(i2, 1, this.r);
        this.h = 3;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(int i2, int i3) {
        this.d.c(this.r);
        this.d.c(i3, i2, this.k);
        if (i3 == 3) {
            j(this.q);
        }
    }

    public void a(int i2) {
        if (!d(VastAttribute.PAUSE)) {
            if (this.s == 3) {
                LogUtil.a("MediaCenter", "pause while not connecting, add operation");
                this.aq.add(new Runnable() { // from class: olp
                    @Override // java.lang.Runnable
                    public final void run() {
                        oli.this.ad();
                    }
                });
                return;
            }
            return;
        }
        this.ab = true;
        this.e = true;
        int i3 = this.s;
        this.an.getTransportControls().pause();
        a(i2, i3);
    }

    public void ad() {
        a(2);
    }

    public void i(int i2) {
        LogUtil.a("MediaCenter", "stop biEvent = ", Integer.valueOf(i2));
        int i3 = this.s;
        if (i3 == 1 || i3 == 0) {
            return;
        }
        if (!d("stop")) {
            this.aq.add(new Runnable() { // from class: olo
                @Override // java.lang.Runnable
                public final void run() {
                    oli.this.am();
                }
            });
            return;
        }
        this.ad = true;
        this.e = true;
        int i4 = this.s;
        this.an.getTransportControls().stop();
        this.s = 1;
        this.z = 1;
        this.h = 1;
        d(i2, i4);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void ak() {
        LogUtil.a("MediaCenter", "resetOnStop");
        this.d = new c();
        e eVar = new e();
        this.c = eVar;
        eVar.c = this.i;
        b(0, (int) this.i);
        this.ae = true;
        this.ad = false;
    }

    private void d(int i2, int i3) {
        this.d.c(this.r);
        this.d.c(i3, i2, this.k);
        this.ag = true;
        if (i2 == -3 || (this.k != IAudioPlayer.PlayMode.PLAY_ONCE && (i3 == 3 || i3 == 2))) {
            b(false);
            j(this.q);
            this.af = 0;
        }
        this.ag = false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void am() {
        i(2);
    }

    public void ac() {
        if (!d("skipToNext")) {
            this.aq.add(new Runnable() { // from class: olv
                @Override // java.lang.Runnable
                public final void run() {
                    oli.this.ac();
                }
            });
            return;
        }
        this.ag = !TextUtils.isEmpty(this.j.h());
        this.an.getTransportControls().skipToNext();
        this.e = false;
        this.c.e();
    }

    public void ag() {
        if (!d("skipToPrev")) {
            this.aq.add(new Runnable() { // from class: oln
                @Override // java.lang.Runnable
                public final void run() {
                    oli.this.ag();
                }
            });
            return;
        }
        this.ag = !TextUtils.isEmpty(this.j.h());
        this.an.getTransportControls().skipToPrevious();
        this.e = false;
        this.c.e();
    }

    /* renamed from: d, reason: merged with bridge method [inline-methods] */
    public void b(final long j2) {
        if (!d("seekTo")) {
            this.aq.add(new Runnable() { // from class: olj
                @Override // java.lang.Runnable
                public final void run() {
                    oli.this.b(j2);
                }
            });
            return;
        }
        LogUtil.a("MediaCenter", "seekTo position = ", Long.valueOf(j2), ", mCurrentItemDuration = ", Long.valueOf(this.i));
        this.an.getTransportControls().seekTo(j2);
        this.c.b(this.r, j2);
        this.d.a(this.r, j2);
        b((int) j2, (int) this.i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(int i2, int i3) {
        float f = i2;
        float f2 = f / i3;
        this.t = f2;
        this.p = f2;
        this.r = i2;
        int round = Math.round(f / 1000.0f);
        this.q = round;
        this.u = round;
    }

    public void c(long j2) {
        c(j2, this.m);
    }

    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public void c(final long j2, final int i2) {
        if (!d("skipToItem")) {
            this.aq.add(new Runnable() { // from class: olt
                @Override // java.lang.Runnable
                public final void run() {
                    oli.this.c(j2, i2);
                }
            });
            return;
        }
        this.ag = !TextUtils.isEmpty(this.j.h());
        LogUtil.a("MediaCenter", "skip to index = ", Long.valueOf(j2), ", playList size = ", Integer.valueOf(this.as.size()));
        this.an.getTransportControls().skipToQueueItem(j2);
        this.an.getTransportControls().sendCustomAction(IAudioPlayer.ParameterKeys.AUDIO_CANCEL_AUTO_STOP, new Bundle());
        this.e = false;
        if (i2 != 0) {
            this.aj = i2;
        }
        this.c.e();
        this.ac = true;
        if (this.z != 3) {
            this.d.a(i2, 1, 0L);
        }
        this.h = 3;
        this.am = "headLine";
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void af() {
        this.d.c(!this.ag ? this.i : this.r);
        int i2 = this.z;
        boolean z = i2 == 9 || i2 == 10 || i2 == 11;
        if (i2 == 3 || z) {
            this.d.a();
        }
        this.d.e(0L);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:16:0x0074  */
    /* JADX WARN: Removed duplicated region for block: B:19:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void b(boolean r8) {
        /*
            r7 = this;
            boolean r0 = r7.ae
            if (r0 != 0) goto L7a
            enq r0 = r7.j
            java.lang.String r0 = r0.h()
            boolean r0 = android.text.TextUtils.isEmpty(r0)
            if (r0 != 0) goto L7a
            long r0 = r7.i
            r2 = 0
            int r0 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r0 >= 0) goto L19
            goto L7a
        L19:
            oli$e r0 = r7.c
            int r1 = r7.r
            long r1 = (long) r1
            oli.e.e(r0, r1)
            enq r0 = r7.j
            java.lang.String r0 = r0.f()
            boolean r1 = android.text.TextUtils.isEmpty(r0)
            if (r1 == 0) goto L2e
            goto L43
        L2e:
            java.lang.String r1 = "\\s+"
            java.lang.String[] r0 = r0.split(r1)
            int r1 = r0.length
            r2 = 1
            java.lang.String r3 = "MediaCenter"
            if (r1 > r2) goto L46
            java.lang.String r0 = "length is less than 1"
            java.lang.Object[] r0 = new java.lang.Object[]{r0}
            com.huawei.hwlogsmodel.LogUtil.b(r3, r0)
        L43:
            r0 = 0
        L44:
            r6 = r0
            goto L56
        L46:
            int r1 = r1 + (-1)
            r2 = r0[r1]
            java.lang.String r4 = "teacher name: "
            java.lang.Object[] r2 = new java.lang.Object[]{r4, r2}
            com.huawei.hwlogsmodel.LogUtil.a(r3, r2)
            r0 = r0[r1]
            goto L44
        L56:
            oli$e r0 = r7.c
            long r1 = r7.i
            oli.e.b(r0, r1)
            oli$e r1 = r7.c
            int r2 = r7.f15765a
            enq r0 = r7.j
            java.lang.String r3 = r0.n()
            enq r0 = r7.j
            java.lang.String r4 = r0.d()
            java.lang.String r5 = r7.x
            oli.e.a(r1, r2, r3, r4, r5, r6)
            if (r8 != 0) goto L79
            oli$e r8 = r7.c
            oli.e.e(r8)
        L79:
            return
        L7a:
            r8 = 0
            r7.ae = r8
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.oli.b(boolean):void");
    }

    /* renamed from: c, reason: merged with bridge method [inline-methods] */
    public void a(final IAudioPlayer.PlayMode playMode) {
        if (!d("setPlayMode")) {
            this.aq.add(new Runnable() { // from class: olq
                @Override // java.lang.Runnable
                public final void run() {
                    oli.this.a(playMode);
                }
            });
            return;
        }
        Bundle bundle = new Bundle();
        bundle.putString(IAudioPlayer.ParameterKeys.AUDIO_PLAY_MODE, playMode.toString());
        this.an.getTransportControls().sendCustomAction(IAudioPlayer.ParameterKeys.AUDIO_PLAY_MODE, bundle);
        this.k = playMode;
        String name = playMode.name();
        LogUtil.a("MediaCenter", "setPlayMode ", name);
        SharedPreferenceManager.c("healthHeadLines", "playMode", name);
    }

    private IAudioPlayer.PlayMode al() {
        IAudioPlayer.PlayMode playMode;
        String e2 = SharedPreferenceManager.e("healthHeadLines", "playMode", "");
        if (IAudioPlayer.PlayMode.SEQUENCE_CIRCLE.name().equals(e2)) {
            playMode = IAudioPlayer.PlayMode.SEQUENCE_CIRCLE;
        } else if (IAudioPlayer.PlayMode.PLAY_ONCE.name().equals(e2)) {
            playMode = IAudioPlayer.PlayMode.PLAY_ONCE;
        } else if (IAudioPlayer.PlayMode.RANDOM_CIRCLE.name().equals(e2)) {
            playMode = IAudioPlayer.PlayMode.RANDOM_CIRCLE;
        } else {
            playMode = IAudioPlayer.PlayMode.SEQUENCE_PLAY;
        }
        LogUtil.a("MediaCenter", "restorePlayMode userPlayMode = ", e2, ", playMode = ", playMode.name());
        return playMode;
    }

    public void y() {
        a(al());
    }

    /* renamed from: c, reason: merged with bridge method [inline-methods] */
    public void b(final List<enq> list, final List<oly> list2, final int i2, final Consumer<Boolean> consumer) {
        if (!(list instanceof Serializable)) {
            LogUtil.b("MediaCenter", "setPlayList failed cause audioItemList is not Serializable");
            return;
        }
        if (!d("setPlayList")) {
            this.aq.add(new Runnable() { // from class: olx
                @Override // java.lang.Runnable
                public final void run() {
                    oli.this.b(list, list2, i2, consumer);
                }
            });
            e(consumer);
            LogUtil.b("MediaCenter", "setPlayList failed cause is not connected!");
            return;
        }
        this.as.clear();
        this.ao.clear();
        if (koq.c(list)) {
            b(list);
            this.as.addAll(list);
        }
        this.aw.clear();
        if (koq.c(list2)) {
            this.aw.addAll(list2);
        }
        this.o = i2;
        LogUtil.a("MediaCenter", "setPlayList audioItemList size = ", Integer.valueOf(list.size()));
        if (list.size() > 200) {
            d(list);
        } else {
            c(list);
        }
    }

    private void c(List<enq> list) {
        if (koq.b(list)) {
            LogUtil.h("MediaCenter", "setAudioPlayList fail. audioItemList is empty.");
            return;
        }
        LogUtil.a("MediaCenter", "setAudioPlayList inputList size = ", Integer.valueOf(list.size()));
        Bundle bundle = new Bundle();
        bundle.putSerializable(IAudioPlayer.ParameterKeys.AUDIO_SET_PLAY_LIST, (Serializable) list);
        this.an.getTransportControls().sendCustomAction(IAudioPlayer.ParameterKeys.AUDIO_SET_PLAY_LIST, bundle);
    }

    private void e(List<enq> list) {
        if (koq.b(list)) {
            LogUtil.h("MediaCenter", "addAudioPlayList fail. appendPlayList is empty.");
            return;
        }
        LogUtil.a("MediaCenter", "addAudioPlayList appendPlayList size = ", Integer.valueOf(list.size()));
        Bundle bundle = new Bundle();
        bundle.putSerializable(IAudioPlayer.ParameterKeys.AUDIO_ADD_PLAY_LIST, (Serializable) list);
        this.an.getTransportControls().sendCustomAction(IAudioPlayer.ParameterKeys.AUDIO_ADD_PLAY_LIST, bundle);
    }

    private void d(List<enq> list) {
        if (koq.b(list)) {
            LogUtil.h("MediaCenter", "dealWithLargePlaylist fail. audioItemList is empty.");
            return;
        }
        int size = list.size() / 200;
        LogUtil.a("MediaCenter", "dealWithLargePlaylist audioItemList.size() = " + list.size(), " setPageNum = ", Integer.valueOf(size));
        for (int i2 = 0; i2 <= size; i2++) {
            if (i2 == 0) {
                c(new ArrayList(list.subList(0, 200)));
            } else {
                int i3 = i2 * 200;
                e(new ArrayList(list.subList(i3, Math.min(i3 + 200, list.size()))));
            }
        }
    }

    public void a(final List<enq> list, final List<oly> list2) {
        if (!d("appendPlayList")) {
            this.aq.add(new Runnable() { // from class: olr
                @Override // java.lang.Runnable
                public final void run() {
                    oli.this.d(list, list2);
                }
            });
            return;
        }
        if (koq.c(list)) {
            b(list);
            this.as.addAll(list);
        }
        if (koq.c(list2)) {
            this.aw.addAll(list2);
        }
        e(list);
    }

    /* synthetic */ void d(List list, List list2) {
        a((List<enq>) list, (List<oly>) list2);
    }

    public void b(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        int e2 = e(str);
        if (e2 < 0) {
            LogUtil.b("MediaCenter", "removeAudioItemById failed, cause not found mediaId = ", str, " in list!");
        } else {
            a(this.as.get(e2));
        }
    }

    /* renamed from: d, reason: merged with bridge method [inline-methods] */
    public void a(final enq enqVar) {
        if (enqVar == null) {
            return;
        }
        if (!d("removeAudioItem")) {
            this.aq.add(new Runnable() { // from class: olm
                @Override // java.lang.Runnable
                public final void run() {
                    oli.this.a(enqVar);
                }
            });
            return;
        }
        int indexOf = this.as.indexOf(enqVar);
        if (indexOf < this.o) {
            this.o--;
        } else if (indexOf == this.o) {
            if (this.o >= this.as.size() - 1) {
                this.o = 0;
            }
            this.ag = !TextUtils.isEmpty(this.j.h());
        } else {
            LogUtil.i("MediaCenter", "removeItemIndex = ", Integer.valueOf(indexOf), ", do nothing with it.");
        }
        this.as.remove(enqVar);
        if (koq.d(this.aw, indexOf)) {
            this.aw.get(indexOf).c(true);
        }
        this.ao.remove(enqVar.h());
        for (String str : this.ao.keySet()) {
            int e2 = e(str);
            if (e2 >= 0 && e2 > indexOf) {
                this.ao.put(str, Integer.valueOf(e2 - 1));
            }
        }
        Bundle bundle = new Bundle();
        bundle.putSerializable(IAudioPlayer.ParameterKeys.AUDIO_DELETE_ITEM, enqVar);
        this.an.getTransportControls().sendCustomAction(IAudioPlayer.ParameterKeys.AUDIO_DELETE_ITEM, bundle);
    }

    public List<oly> c() {
        ArrayList arrayList = new ArrayList();
        for (oly olyVar : this.aw) {
            if (olyVar != null && !olyVar.k()) {
                arrayList.add(olyVar);
            }
        }
        return arrayList;
    }

    boolean d(String str) {
        if (this.an != null && this.al.isConnected()) {
            return true;
        }
        Object[] objArr = new Object[5];
        objArr[0] = str;
        objArr[1] = " checkConnected failed, mMediaController = ";
        objArr[2] = this.an;
        objArr[3] = ", isConnected = ";
        objArr[4] = Boolean.valueOf(this.al != null && this.al.isConnected());
        ReleaseLogUtil.c("R_MediaCenter", objArr);
        return false;
    }

    public void d() {
        if (this.al != null && this.al.isConnected()) {
            ReleaseLogUtil.e("R_MediaCenter", "healthHeadLine mediaBrowser disconnect.");
            this.al.disconnect();
            ai();
        }
        this.al = null;
        this.an = null;
        ReleaseLogUtil.e("R_MediaCenter", "healthHeadLine mediaBrowser set null.");
    }

    class d extends MediaBrowser.ConnectionCallback {
        private Consumer<Boolean> e;

        public d(Consumer<Boolean> consumer) {
            this.e = consumer;
        }

        @Override // android.media.browse.MediaBrowser.ConnectionCallback
        public void onConnected() {
            if (oli.this.al != null) {
                if (!oli.this.al.isConnected()) {
                    LogUtil.b("MediaCenter", "mediaBrowser onConnected but media browser was disconnected before, abort!");
                    return;
                }
                LogUtil.a("MediaCenter", "mediaBrowser onConnected");
                oli.this.an = new MediaController(oli.this.g, oli.this.al.getSessionToken());
                oli.this.an.registerCallback(new g());
                while (!oli.this.aq.isEmpty()) {
                    Runnable runnable = (Runnable) oli.this.aq.peek();
                    if (runnable != null) {
                        runnable.run();
                        oli.this.aq.poll();
                    }
                }
                Consumer<Boolean> consumer = this.e;
                if (consumer != null) {
                    consumer.accept(true);
                    this.e = null;
                    return;
                }
                return;
            }
            LogUtil.b("MediaCenter", "mediaBrowser onConnected but media browser is null, abort!");
        }

        @Override // android.media.browse.MediaBrowser.ConnectionCallback
        public void onConnectionSuspended() {
            LogUtil.a("MediaCenter", "mediaBrowser onConnectionSuspended");
        }

        @Override // android.media.browse.MediaBrowser.ConnectionCallback
        public void onConnectionFailed() {
            LogUtil.a("MediaCenter", "mediaBrowser onConnectionFailed");
            Consumer<Boolean> consumer = this.e;
            if (consumer != null) {
                consumer.accept(false);
                this.e = null;
            }
        }
    }

    class g extends MediaController.Callback {
        private g() {
        }

        private void b() {
            oli.this.b(false);
            oli.this.af();
            h();
        }

        private void dcs_(PlaybackState playbackState) {
            long position = playbackState.getPosition();
            if (position < 0) {
                LogUtil.h("MediaCenter", "currentTime is negative, reset to 0... actual:", Long.valueOf(position));
                position = 0;
            }
            LogUtil.a("MediaCenter", "progress changed: ", Long.valueOf(position));
            oli oliVar = oli.this;
            oliVar.b((int) position, (int) oliVar.i);
            Iterator it = oli.this.ap.iterator();
            while (it.hasNext()) {
                ((MediaStatusSubscriber) it.next()).onProgressChanged(oli.this.r, (int) oli.this.i, oli.this.t, oli.this.q);
            }
        }

        private void e() {
            if (!koq.b(oli.this.aa)) {
                Iterator it = oli.this.aa.iterator();
                while (it.hasNext()) {
                    okx okxVar = (okx) it.next();
                    if (okxVar != null) {
                        LogUtil.a("MediaCenter", "mInnerApi = ", okxVar);
                        okxVar.e();
                    }
                }
                return;
            }
            LogUtil.a("MediaCenter", "mInnerApiList is empty. no need to hide miniPlayer.");
        }

        @Override // android.media.session.MediaController.Callback
        public void onPlaybackStateChanged(PlaybackState playbackState) {
            if (playbackState == null) {
                LogUtil.h("MediaCenter", "onPlaybackStateChanged, state is null ,this should NOT happen...");
                return;
            }
            if (playbackState.getExtras() != null) {
                Bundle extras = playbackState.getExtras();
                if (!"headLine".equals(extras.getString("category"))) {
                    LogUtil.h("MediaCenter", "onPlaybackStateChanged not headLine media");
                    oli.this.am = extras.getString("category");
                    e();
                    return;
                }
            }
            int state = playbackState.getState();
            if (state != 3 || oli.this.z != 3) {
                if (state == 3 && oli.this.e) {
                    LogUtil.a("MediaCenter", "receive PLAYING under ignore circumstance, mInnerCurrentStatue: ", Integer.valueOf(oli.this.z));
                    oli.this.e = false;
                    return;
                }
                LogUtil.a("MediaCenter", "onPlaybackStateChanged：", playbackState);
                boolean z = (oli.this.z == 0 || oli.this.z == 1) ? false : true;
                oli.this.z = state;
                if (oli.this.w == null) {
                    LogUtil.b("MediaCenter", "onPlaybackStateChanged delay long time task handle failed, cause handler is not init");
                } else if (state == 2) {
                    oli.this.w.postDelayed(oli.this.v, 1800000L);
                } else {
                    oli.this.w.removeCallbacks(oli.this.v);
                }
                boolean z2 = state == 9 || state == 10 || state == 11;
                boolean z3 = oli.this.as.size() == 1;
                boolean z4 = oli.this.k == IAudioPlayer.PlayMode.SEQUENCE_PLAY && koq.d(oli.this.as, oli.this.o) && oli.this.o == oli.this.as.size() - 1 && state == 1 && !oli.this.e;
                boolean z5 = oli.this.k == IAudioPlayer.PlayMode.PLAY_ONCE && state == 1 && !z2;
                if ((!z || !z3 || (!z2 && state != 1)) && !z4 && !z5) {
                    if (oli.this.s != 1 && state == 1) {
                        LogUtil.a("MediaCenter", "onPlaybackStateChanged not handle single media skip situation, go stop.", " mCurrentProgress = ", Float.valueOf(oli.this.t), "; mCurrentTimeInSec = ", Integer.valueOf(oli.this.q));
                        c();
                    }
                } else {
                    LogUtil.a("MediaCenter", "onPlaybackStateChanged handle single media skip situation isSkipMedia = ", Boolean.valueOf(z2));
                    b();
                    int a2 = (int) oli.this.j.a();
                    oli oliVar = oli.this;
                    if (z5) {
                        a2 = oliVar.q;
                    }
                    oliVar.j(a2);
                    oli.this.af = 0;
                    oli.this.an();
                }
                if (oli.this.s != 2 && state == 2) {
                    a();
                }
                if (oli.this.s != 3 && state == 3) {
                    d();
                }
                if (z2) {
                    oli.this.ag = false;
                }
                if (state == 2 || state == 1 || state == 3 || state == 6 || state == 7) {
                    oli.this.s = state;
                    oli.this.h = state;
                    Iterator it = oli.this.ap.iterator();
                    while (it.hasNext()) {
                        ((MediaStatusSubscriber) it.next()).onPlaybackStateChanged(playbackState);
                    }
                    return;
                }
                return;
            }
            dcs_(playbackState);
        }

        private void d() {
            if (oli.this.ac) {
                oli.this.ac = false;
            } else {
                oli.this.d.a(12, 1, 0L);
            }
        }

        private void c() {
            if (!oli.this.ad) {
                oli.this.b(false);
                if (oli.this.q > 0 && oli.this.t > 0.0f) {
                    oli oliVar = oli.this;
                    oliVar.j(oliVar.q);
                }
            } else {
                oli.this.ad = false;
            }
            oli.this.ak();
        }

        private void a() {
            oli.this.b(true);
            if (oli.this.ab) {
                oli.this.ab = false;
                return;
            }
            oli oliVar = oli.this;
            oliVar.j(oliVar.q);
            oli.this.d.c(oli.this.r);
            oli.this.d.c(3, 2, oli.this.k);
        }

        @Override // android.media.session.MediaController.Callback
        public void onMetadataChanged(MediaMetadata mediaMetadata) {
            if (mediaMetadata == null) {
                return;
            }
            String string = mediaMetadata.getString(MediaMetadataCompat.METADATA_KEY_MEDIA_ID);
            ReleaseLogUtil.e("R_MediaCenter", "onMetadataChanged = ", mediaMetadata.getString(MediaMetadataCompat.METADATA_KEY_TITLE), "; mediaId = ", string);
            oli.this.j(string);
            if (!"headLine".equals(mediaMetadata.getString(MediaMetadataCompat.METADATA_KEY_GENRE))) {
                LogUtil.h("MediaCenter", "onMetadataChanged not headLine media");
                oli.this.am = mediaMetadata.getString(MediaMetadataCompat.METADATA_KEY_GENRE);
                e();
                return;
            }
            if (!TextUtils.isEmpty(mediaMetadata.getString(MediaMetadataCompat.METADATA_KEY_DISPLAY_ICON_URI))) {
                LogUtil.a("MediaCenter", "It's the second time that onMetadataChanged callback, do not consider");
                return;
            }
            b();
            oli.this.an();
            int a2 = (int) oli.this.j.a();
            int i = oli.this.ag ? oli.this.q : a2 - oli.this.q < 2 ? a2 : oli.this.q;
            LogUtil.a("MediaCenter", "currentProgressTime = ", Integer.valueOf(i), "; mIsUserSkipped = ", Boolean.valueOf(oli.this.ag), "; mCurrentTimeInSec = ", Integer.valueOf(oli.this.q), "; duration = ", Integer.valueOf(a2));
            if (i > 0) {
                oli.this.j(i);
            }
            if (TextUtils.isEmpty(oli.this.j.h())) {
                h();
            }
            if (!TextUtils.isEmpty(string)) {
                Object obj = oli.this.ao.get(string);
                if (obj == null) {
                    LogUtil.h("MediaCenter", "onMetadataChanged indexObj = ", obj);
                    return;
                }
                Integer num = (Integer) obj;
                if (num.intValue() < oli.this.as.size()) {
                    oli.this.o = num.intValue();
                    oli oliVar = oli.this;
                    oliVar.j = (enq) oliVar.as.get(oli.this.o);
                    oli.this.i = mediaMetadata.getLong(MediaMetadataCompat.METADATA_KEY_DURATION);
                    oli.this.u = 0;
                    oli.this.p = 0.0f;
                    oli.this.t = 0.0f;
                    oli.this.af = 0;
                    for (MediaStatusSubscriber mediaStatusSubscriber : oli.this.ap) {
                        mediaStatusSubscriber.onMediaChanged(oli.this.j, oli.this.o);
                        LogUtil.a("MediaCenter", "onMetadataChanged subscriber = ", mediaStatusSubscriber, ", mCurrentItem = ", oli.this.j, ", mCurrentItemIndex = ", Integer.valueOf(oli.this.o), "; mPlayList.size() = ", Integer.valueOf(oli.this.as.size()));
                    }
                    if (oli.this.as.size() - oli.this.o <= 2) {
                        LogUtil.a("MediaCenter", "need to update playlist. type = ", Integer.valueOf(oli.this.m));
                        if (oli.this.m == 2 || oli.this.m == 6) {
                            ojw.a();
                        }
                        if (oli.this.m == 8 || oli.this.m == 9) {
                            ojz.e().a();
                            return;
                        }
                        return;
                    }
                    return;
                }
                LogUtil.h("MediaCenter", "indexObj >= mPlayList.size(). indexObj = ", obj, "; mPlayList.size() = ", Integer.valueOf(oli.this.as.size()));
                return;
            }
            LogUtil.h("MediaCenter", "onMetadataChanged mediaId is empty: ", string);
        }

        private void h() {
            if (oli.this.c.d != 3) {
                if (oli.this.k != IAudioPlayer.PlayMode.SEQUENCE_PLAY && oli.this.k != IAudioPlayer.PlayMode.SEQUENCE_CIRCLE) {
                    if (oli.this.k != IAudioPlayer.PlayMode.RANDOM_CIRCLE) {
                        if (oli.this.k == IAudioPlayer.PlayMode.PLAY_ONCE) {
                            oli.this.c.d = 5;
                        } else {
                            LogUtil.i("mBiInfo1100056.nextPlayMode is ", Integer.valueOf(oli.this.c.d), ", do nothing with it.");
                        }
                    } else {
                        oli.this.c.d = 2;
                    }
                } else {
                    oli.this.c.d = 1;
                }
            }
            if (oli.this.aj != 0) {
                if (oli.this.aj == 3) {
                    int i = oli.this.f15765a;
                    oli oliVar = oli.this;
                    oliVar.f15765a = oliVar.aj;
                    oli.this.aj = i;
                } else {
                    oli oliVar2 = oli.this;
                    oliVar2.f15765a = oliVar2.aj;
                }
            }
            oli.this.c.b = oli.this.c.d;
            oli.this.c.d = 0;
            oli oliVar3 = oli.this;
            oliVar3.x = oliVar3.ak;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void an() {
        if (this.ag) {
            return;
        }
        enq enqVar = this.j;
        if (enqVar == null || !TextUtils.isEmpty(enqVar.h())) {
            long j2 = this.i;
            if (j2 == 0 || (j2 / 1000) - this.q >= 2) {
                return;
            }
            HashMap hashMap = new HashMap(2);
            enq enqVar2 = this.j;
            hashMap.put(HwExerciseConstants.METHOD_PARAM_WORKOUT_ID, enqVar2 != null ? enqVar2.h() : null);
            LogUtil.a("MediaCenter", "Kaka bi report paramMap = ", hashMap);
            bzw.e().finishKakaTask(this.g, 40012, hashMap);
        }
    }

    public void aa() {
        ojw.e((Consumer<Long>) new Consumer() { // from class: com.huawei.ui.homehealth.healthheadlinecard.MediaCenter$$ExternalSyntheticLambda8
            @Override // androidx.core.util.Consumer
            public final void accept(Object obj) {
                oli.this.b((Long) obj);
            }
        });
    }

    public /* synthetic */ void b(Long l) {
        HashMap hashMap = new HashMap(2);
        hashMap.put(KakaConstants.SLEEP_MUSIC_DURATION, l);
        LogUtil.a("MediaCenter", "reportDurationFinishKakaTask bi report paramMap = ", hashMap);
        bzw.e().finishKakaTask(this.g, 40013, hashMap);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void j(int i2) {
        enq enqVar = this.j;
        if (enqVar == null || TextUtils.isEmpty(enqVar.h()) || this.i == 0) {
            LogUtil.h("MediaCenter", "first time play or wrong with current item, failed to postProgressToCloud");
            return;
        }
        int i3 = this.af;
        int i4 = i3 >= i2 ? 0 : i2 - i3;
        LogUtil.a("MediaCenter", "postToCloud media: ", this.j.h(), ", title = ", this.j.n(), ", progressTimeInSec: ", Integer.valueOf(i2), ", progressInterval: ", Integer.valueOf(i4), ", mCurrentItemDuration = ", Long.valueOf(this.i), ", mLastPostTimeInSec = ", Integer.valueOf(this.af));
        HealthHeadLinesInfoManager.d().e(this.j.h(), i2, i4);
        this.af = i2;
    }

    public enq f() {
        return this.j;
    }

    public void h(int i2) {
        this.s = i2;
    }

    public int n() {
        return this.s;
    }

    public int e() {
        return this.h;
    }

    public void e(enq enqVar) {
        LogUtil.a("MediaCenter", "setCurrentItem id = ", enqVar.h());
        this.j = enqVar;
    }

    public void a(MediaStatusSubscriber mediaStatusSubscriber) {
        this.ap.add(mediaStatusSubscriber);
    }

    public void v() {
        if (koq.b(this.ap)) {
            LogUtil.h("MediaCenter", "SubscriberList is empty.");
            return;
        }
        Iterator<MediaStatusSubscriber> it = this.ap.iterator();
        while (it.hasNext()) {
            it.next().onMediaChanged(this.j, this.o);
        }
    }

    public void b(MediaStatusSubscriber mediaStatusSubscriber) {
        this.ap.remove(mediaStatusSubscriber);
    }

    private void b(List<enq> list) {
        int size = this.as.size();
        Iterator<enq> it = list.iterator();
        while (it.hasNext()) {
            this.ao.put(it.next().h(), Integer.valueOf(size));
            size++;
        }
    }

    public List<enq> t() {
        CopyOnWriteArrayList copyOnWriteArrayList;
        synchronized (this) {
            copyOnWriteArrayList = new CopyOnWriteArrayList(this.as);
        }
        return copyOnWriteArrayList;
    }

    public void e(List<enq> list, List<oly> list2) {
        synchronized (this) {
            this.as.clear();
            if (list != null) {
                this.as.addAll(list);
            }
            this.aw.clear();
            if (list2 != null) {
                this.aw.addAll(list2);
            }
        }
    }

    public List<oly> w() {
        CopyOnWriteArrayList copyOnWriteArrayList;
        synchronized (this) {
            copyOnWriteArrayList = new CopyOnWriteArrayList(this.aw);
        }
        return copyOnWriteArrayList;
    }

    public void c(int i2) {
        LogUtil.a("MediaCenter", "setCurrentItemIndex = ", Integer.valueOf(i2));
        this.o = i2;
    }

    public int g() {
        return this.o;
    }

    public float m() {
        return this.t;
    }

    public long h() {
        return this.i;
    }

    public int r() {
        return this.u;
    }

    public float l() {
        return this.p;
    }

    public IAudioPlayer.PlayMode o() {
        return this.k;
    }

    public String s() {
        return this.y;
    }

    public void g(String str) {
        this.y = str;
    }

    public String j() {
        return this.n;
    }

    public void c(String str) {
        this.n = str;
    }

    public int i() {
        return this.m;
    }

    public void g(int i2) {
        this.m = i2;
        if (i2 != 8) {
            this.ak = null;
        }
    }

    public void i(String str) {
        this.ak = str;
    }

    public int e(String str) {
        Integer num = this.ao.get(str);
        if (num == null) {
            LogUtil.b("MediaCenter", "getIndexByMediaId failed, mediaId = ", str, " not in mPlayIndexMap = ", this.ao);
            return -1;
        }
        return num.intValue();
    }

    static final class c {
        private final b c;
        private int d;
        private long e;

        private c() {
            this.c = new b("BiInfo1100055");
            this.e = 0L;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void a(long j, long j2) {
            synchronized (this) {
                this.c.d(j, j2);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void a() {
            synchronized (this) {
                this.e += this.c.b();
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void c(long j) {
            synchronized (this) {
                this.c.e(j);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void e(long j) {
            synchronized (this) {
                this.c.d(j);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void a(int i, int i2, long j) {
            c(true, i, i2, (IAudioPlayer.PlayMode) null);
            e(j);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void c(int i, int i2, IAudioPlayer.PlayMode playMode) {
            if (i != 3) {
                LogUtil.h("MediaCenter", "lastState is not playing, so do not doBi");
            } else {
                c(false, this.d, i2, playMode);
                this.e = 0L;
            }
        }

        private void c(boolean z, int i, int i2, IAudioPlayer.PlayMode playMode) {
            int i3;
            HashMap hashMap = new HashMap(16);
            hashMap.put("click", "1");
            if (!z && playMode != null) {
                hashMap.put(KakaConstants.SLEEP_MUSIC_DURATION, Integer.valueOf((int) ((this.e + this.c.b()) / 1000)));
                if (playMode == IAudioPlayer.PlayMode.SEQUENCE_PLAY) {
                    i3 = 1;
                } else if (playMode == IAudioPlayer.PlayMode.RANDOM_CIRCLE) {
                    i3 = 2;
                } else if (playMode == IAudioPlayer.PlayMode.PLAY_ONCE) {
                    i3 = 5;
                } else if (playMode == IAudioPlayer.PlayMode.SEQUENCE_CIRCLE) {
                    i3 = 3;
                } else {
                    LogUtil.i("MediaCenter", "playMode is ", playMode, ", do nothing with it.");
                    i3 = 0;
                }
                hashMap.put("playMode", Integer.valueOf(i3));
            }
            hashMap.put("event", Integer.valueOf(i2));
            hashMap.put("from", Integer.valueOf(i));
            LogUtil.a("MediaCenter", "HEALTH_HEADLINES_USE_1100055 healthHeadLinesMap = " + hashMap);
            ixx.d().d(BaseApplication.e(), AnalyticsValue.HEALTH_HEADLINES_USE_1100055.value(), hashMap, 0);
            this.d = i;
        }
    }

    static final class e {

        /* renamed from: a, reason: collision with root package name */
        private final b f15769a;
        private int b;
        private long c;
        private int d;

        private e() {
            this.f15769a = new b("BiInfo1100056");
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void e() {
            synchronized (this) {
                this.d = 3;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void b(long j, long j2) {
            synchronized (this) {
                this.f15769a.d(j, j2);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void b(long j) {
            synchronized (this) {
                this.f15769a.e(j);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void c() {
            synchronized (this) {
                this.f15769a.e();
            }
        }

        private long b() {
            return this.f15769a.b();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void a(int i, String str, String str2, String str3, String str4) {
            synchronized (this) {
                if (this.c == 0) {
                    LogUtil.b("MediaCenter", "totalDuration is zero, should not post 1100056");
                    return;
                }
                HashMap hashMap = new HashMap(16);
                hashMap.put("click", "1");
                hashMap.put("playMode", Integer.valueOf(this.b));
                hashMap.put("finishRate", Float.valueOf(b() / this.c));
                hashMap.put("resourceName", str);
                hashMap.put("resourceDate", str2);
                if (i != 8) {
                    str3 = null;
                }
                hashMap.put("topicName", str3);
                if (i != 7) {
                    str4 = null;
                }
                hashMap.put("Presenter", str4);
                hashMap.put("from", Integer.valueOf(i));
                LogUtil.a("MediaCenter", "HEALTH_HEADLINES_SINGLE_RESOURCE_END_1100056 healthHeadLinesMap = ", hashMap, ", totalDuration = ", Long.valueOf(this.c));
                ixx.d().d(BaseApplication.e(), AnalyticsValue.HEALTH_HEADLINES_SINGLE_RESOURCE_END_1100056.value(), hashMap, 0);
            }
        }
    }

    static final class b {

        /* renamed from: a, reason: collision with root package name */
        private final List<a> f15767a = new ArrayList<a>() { // from class: oli.b.5
            {
                add(new a());
            }
        };
        private String b;

        public b(String str) {
            this.b = "";
            this.b = str;
        }

        public String toString() {
            return "{\"playIntervals\":" + this.f15767a + '}';
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void d(long j, long j2) {
            e(j);
            this.f15767a.add(a(j2));
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void e(long j) {
            if (koq.b(this.f15767a)) {
                return;
            }
            this.f15767a.get(r0.size() - 1).e = j;
        }

        private a a(long j) {
            a aVar = new a();
            aVar.d = j;
            return aVar;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void e() {
            d(0L);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void d(long j) {
            this.f15767a.clear();
            a aVar = new a();
            aVar.d = j;
            this.f15767a.add(aVar);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public long b() {
            long j = 0;
            if (koq.b(this.f15767a)) {
                return 0L;
            }
            ArrayList arrayList = new ArrayList(0);
            for (a aVar : this.f15767a) {
                if (aVar.d < aVar.e) {
                    arrayList.add(aVar);
                }
            }
            if (koq.b(arrayList)) {
                return 0L;
            }
            Collections.sort(arrayList, new Comparator<a>() { // from class: oli.b.2
                @Override // java.util.Comparator
                /* renamed from: e, reason: merged with bridge method [inline-methods] */
                public int compare(a aVar2, a aVar3) {
                    return (int) (aVar2.d - aVar3.d);
                }
            });
            ArrayList<a> arrayList2 = new ArrayList(0);
            a aVar2 = (a) arrayList.get(0);
            arrayList2.add(aVar2);
            for (int i = 1; i < arrayList.size(); i++) {
                a aVar3 = (a) arrayList.get(i);
                if (aVar3.d <= aVar2.e) {
                    a aVar4 = new a(aVar2.d, Math.max(aVar3.e, aVar2.e));
                    arrayList2.remove(arrayList2.size() - 1);
                    arrayList2.add(aVar4);
                } else {
                    arrayList2.add(aVar3);
                }
                aVar2 = (a) arrayList2.get(arrayList2.size() - 1);
            }
            LogUtil.a("MediaCenter", this.b, " user actual play duration intervals ", arrayList2);
            for (a aVar5 : arrayList2) {
                j += aVar5.e - aVar5.d;
            }
            return j;
        }
    }

    static final class a {
        long d;
        long e;

        public a(long j, long j2) {
            this.d = j;
            this.e = j2;
        }

        public a() {
        }

        public String toString() {
            return "[" + this.d + ", " + this.e + ']';
        }
    }

    static class j {
        private static final oli e = new oli();
    }

    static class i extends BaseHandler<oli> {
        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.huawei.haf.handler.BaseHandler
        /* renamed from: dcr_, reason: merged with bridge method [inline-methods] */
        public void handleMessageWhenReferenceNotNull(oli oliVar, Message message) {
        }

        private i(oli oliVar) {
            super(oliVar);
        }
    }

    public void c(String str, String str2) {
        this.b.put(str, str2);
    }

    public String a(String str) {
        String str2;
        return (str == null || (str2 = this.b.get(str)) == null) ? "" : str2;
    }

    public void c(List<enq> list, List<oly> list2) {
        ReleaseLogUtil.e("R_MediaCenter", "saveMediaInfo start");
        olu oluVar = new olu();
        oluVar.d(list);
        oluVar.e(list2);
        a(oluVar);
    }

    private void a(olu oluVar) {
        SharedPreferenceManager.c("healthHeadLines", "todayDailyMediaInfo", nrv.e(oluVar, new TypeToken<olu>() { // from class: oli.2
        }.getType()));
    }

    public void b() {
        SharedPreferenceManager.d("healthHeadLines", "dailyMediaInfo");
    }

    public olu p() {
        return (olu) nrv.c(SharedPreferenceManager.e("healthHeadLines", "todayDailyMediaInfo", ""), new TypeToken<olu>() { // from class: oli.9
        }.getType());
    }

    private void ae() {
        this.g.registerComponentCallbacks(this.f);
    }

    public void ai() {
        this.g.unregisterComponentCallbacks(this.f);
    }

    public String q() {
        return this.am;
    }

    public String k() {
        return this.l;
    }

    public void j(String str) {
        this.l = str;
    }

    public void d(okx okxVar) {
        CopyOnWriteArrayList<okx> copyOnWriteArrayList = this.aa;
        if (copyOnWriteArrayList == null) {
            LogUtil.a("MediaCenter", "mInnerApiList is null. Fail to addInnerApi.");
        } else {
            copyOnWriteArrayList.add(okxVar);
            LogUtil.a("MediaCenter", "add mInnerApiList.size = ", Integer.valueOf(this.aa.size()));
        }
    }

    public void a(okx okxVar) {
        CopyOnWriteArrayList<okx> copyOnWriteArrayList = this.aa;
        if (copyOnWriteArrayList == null) {
            LogUtil.a("MediaCenter", "mInnerApiList is null. Fail to remove.");
        } else {
            copyOnWriteArrayList.remove(okxVar);
            LogUtil.a("MediaCenter", "remove mInnerApiList.size = ", Integer.valueOf(this.aa.size()));
        }
    }
}
