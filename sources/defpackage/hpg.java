package defpackage;

import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;
import com.huawei.healthcloud.plugintrack.manager.voice.BaseSoundEffectManager;
import com.huawei.healthcloud.plugintrack.model.SportBeat;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.watchface.videoedit.presenter.PresenterUtils;
import defpackage.jdt;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.io.File;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

/* loaded from: classes4.dex */
public class hpg extends BaseSoundEffectManager {

    /* renamed from: a, reason: collision with root package name */
    private final Context f13307a;
    private volatile boolean c;
    private boolean d;
    private final AudioManager e;
    private String f;
    private c g;
    private gxc h;
    private SportBeat i;
    private int j;
    private Timer o;

    public static boolean c() {
        return true;
    }

    public hpg(SportBeat sportBeat) {
        super(BaseApplication.getContext());
        this.c = false;
        this.d = false;
        Context context = BaseApplication.getContext();
        this.f13307a = context;
        this.i = sportBeat;
        this.e = (AudioManager) context.getSystemService(PresenterUtils.AUDIO);
    }

    private void e(String str) {
        gxc gxcVar = new gxc(c(str), 0, d(str, "mp3"));
        ArrayList arrayList = new ArrayList();
        arrayList.add(gxcVar);
        a(arrayList);
    }

    public String d(String str, String str2) {
        return "beat_res" + File.separator + "1.0.0" + File.separator + str2 + File.separator + str + "." + str2;
    }

    public void j() {
        if (c()) {
            if (this.g == null) {
                c cVar = new c();
                this.g = cVar;
                jdt.c(this.f13307a, true, cVar);
            }
            String a2 = a();
            final String c2 = c(a2);
            if (!a(c2)) {
                aVj_(new SoundPool.OnLoadCompleteListener() { // from class: hpg.4
                    @Override // android.media.SoundPool.OnLoadCompleteListener
                    public void onLoadComplete(SoundPool soundPool, int i, int i2) {
                        hpg.this.i(c2);
                    }
                });
                e(a2);
            } else {
                i(c2);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void i(String str) {
        this.c = true;
        int b = b() == 0 ? 170 : b();
        if (b > 120) {
            d(str, b);
        } else {
            a(str, b);
        }
    }

    private void d(String str, int i) {
        gxc gxcVar = this.b.get(str);
        if (gxcVar == null) {
            LogUtil.b("Track_SportBeatPlayer", "option is null");
            return;
        }
        float g = g();
        this.j = c(gxcVar.d(), g, g, gxcVar.c(), -1, i / 176.68f);
        Timer timer = new Timer();
        this.o = timer;
        timer.scheduleAtFixedRate(new d(this), 0L, 1000L);
    }

    private void a(String str, int i) {
        Timer timer = new Timer();
        this.o = timer;
        timer.scheduleAtFixedRate(new e(this, str), 0L, 60000 / i);
    }

    private void k() {
        d(this.j);
        Timer timer = this.o;
        if (timer != null) {
            timer.cancel();
            this.o = null;
        }
    }

    static final class e extends TimerTask {
        int b;
        WeakReference<hpg> d;
        String e;

        e(hpg hpgVar, String str) {
            this.d = new WeakReference<>(hpgVar);
            this.e = str;
        }

        @Override // java.util.TimerTask, java.lang.Runnable
        public void run() {
            hpg hpgVar = this.d.get();
            if (hpgVar != null) {
                if (hpgVar.c) {
                    hpgVar.d(this.b);
                    gxc gxcVar = (gxc) hpgVar.b.get(this.e);
                    if (gxcVar != null) {
                        float g = hpgVar.g();
                        this.b = hpgVar.c(gxcVar.d(), g, g, gxcVar.c(), 0, 1.0f);
                        return;
                    } else {
                        LogUtil.b("Track_SportBeatPlayer", "option is null");
                        return;
                    }
                }
                return;
            }
            LogUtil.b("Track_SportBeatPlayer", "MetronomeTask player is null");
        }
    }

    static final class d extends TimerTask {
        WeakReference<hpg> e;

        public d(hpg hpgVar) {
            this.e = new WeakReference<>(hpgVar);
        }

        @Override // java.util.TimerTask, java.lang.Runnable
        public void run() {
            hpg hpgVar = this.e.get();
            if (hpgVar != null) {
                float g = hpgVar.g();
                hpgVar.b(hpgVar.j, g, g);
            } else {
                LogUtil.b("Track_SportBeatPlayer", "UpdateVolumeTask player is null");
            }
        }
    }

    public void h() {
        k();
        String a2 = a();
        gxc d2 = d(c(a2));
        if (d2 != null) {
            this.h = d2;
            this.f = a2;
        }
        this.c = false;
    }

    public void f() {
        if (this.h != null) {
            a(c(this.f), this.h);
        }
        j();
    }

    public gxc d(String str) {
        if (!this.b.containsKey(str)) {
            LogUtil.b("Track_SportBeatPlayer", "remove mSoundPool is null");
            return null;
        }
        gxc gxcVar = this.b.get(str);
        this.b.remove(str);
        return gxcVar;
    }

    public void a(String str, gxc gxcVar) {
        if (this.b.containsKey(str)) {
            LogUtil.b("Track_SportBeatPlayer", "remove mSoundPool is null");
        } else {
            this.b.put(str, gxcVar);
        }
    }

    public void a(SportBeat sportBeat) {
        if (this.i.equals(sportBeat)) {
            return;
        }
        this.i = sportBeat;
    }

    public void i() {
        h();
    }

    @Override // com.huawei.healthcloud.plugintrack.manager.voice.BaseSoundEffectManager
    public void d() {
        super.d();
        this.h = null;
        k();
        jdt.c(this.f13307a, false, this.g);
        ReleaseLogUtil.e("Track_SportBeatPlayer", "destroy");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public float g() {
        return this.e.isMusicActive() ? 0.8f : 1.0f;
    }

    public String c(String str) {
        return "SPORT_BEAT" + str;
    }

    public int b() {
        return this.i.getFrequency();
    }

    public boolean e() {
        return this.i.isOpen();
    }

    public String a() {
        int audio = this.i.getAudio();
        return audio != 1 ? audio != 2 ? audio != 3 ? "b01" : "b04" : "b03" : "b02";
    }

    class c extends jdt.c {
        private c() {
        }

        @Override // jdt.c, android.telephony.PhoneStateListener
        public void onCallStateChanged(int i, String str) {
            super.onCallStateChanged(i, str);
            if (hpg.this.i == null) {
                return;
            }
            LogUtil.a("Track_SportBeatPlayer", "onCallStateChanged state = ", Integer.valueOf(i), ", mIsPlaying = ", Boolean.valueOf(hpg.this.e()));
            if (i == 1 || i == 2) {
                if (hpg.this.c) {
                    hpg.this.h();
                    hpg.this.d = true;
                    return;
                }
                return;
            }
            if (hpg.this.e() && !hpg.this.c && hpg.this.d) {
                hpg.this.f();
            }
        }
    }
}
