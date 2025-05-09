package defpackage;

import android.content.res.Resources;
import com.huawei.health.R;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwdevice.mainprocess.mgr.hwmusiccontrolmgr.datatype.MusicMenu;
import com.huawei.hwdevice.mainprocess.mgr.hwmusiccontrolmgr.datatype.MusicPageStruct;
import com.huawei.hwdevice.mainprocess.mgr.hwmusiccontrolmgr.datatype.MusicSong;
import com.huawei.hwdevice.mainprocess.mgr.hwmusiccontrolmgr.datatype.MusicStruct;
import com.huawei.hwdevice.mainprocess.mgr.hwmusiccontrolmgr.datatype.NegotiationData;
import com.huawei.hwdevicemgr.R$string;
import com.huawei.hwlogsmodel.LogUtil;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/* loaded from: classes5.dex */
public class jjm {

    /* renamed from: a, reason: collision with root package name */
    private static final Object f13896a = new Object();
    private static jjm e;
    private int b;
    private boolean f;
    private boolean g;
    private int h;
    private List<MusicSong> j;
    private boolean k;
    private List<MusicStruct> m;
    private int n;
    private List<MusicPageStruct> o;
    private ArrayList<MusicSong> d = new ArrayList<>(16);
    private MusicMenu i = new MusicMenu();
    private MusicMenu c = new MusicMenu();

    public static jjm e() {
        jjm jjmVar;
        synchronized (f13896a) {
            if (e == null) {
                e = new jjm();
            }
            jjmVar = e;
        }
        return jjmVar;
    }

    public boolean a() {
        return this.g;
    }

    public void d(boolean z) {
        this.g = z;
    }

    public boolean j() {
        return this.f;
    }

    public boolean i() {
        DeviceInfo d = jpt.d("MusicUiManager");
        if (d == null) {
            LogUtil.h("MusicUiManager", "isSupportMoreMusic, deviceInfo is null.");
            return false;
        }
        boolean c = cwi.c(d, 122);
        LogUtil.a("MusicUiManager", "isSupportMoreMusic: ", Boolean.valueOf(c));
        return c;
    }

    public int a(int i, int i2) {
        int i3 = i - i2;
        if (i3 > 1000) {
            return i3 / 10;
        }
        if (i3 > 100) {
            return i3 / 15;
        }
        return 3;
    }

    private Resources bHx_() {
        return BaseApplication.getContext().getResources();
    }

    public void d(int i, int i2, final IBaseResponseCallback iBaseResponseCallback) {
        this.k = false;
        if (iBaseResponseCallback == null) {
            LogUtil.h("MusicUiManager", "loadAllMusic callback is null");
            return;
        }
        this.n = 0;
        final int i3 = i2 - i;
        a(new IBaseResponseCallback() { // from class: jjl
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public final void d(int i4, Object obj) {
                jjm.this.e(iBaseResponseCallback, i3, i4, obj);
            }
        });
    }

    /* synthetic */ void e(IBaseResponseCallback iBaseResponseCallback, int i, int i2, Object obj) {
        LogUtil.a("MusicUiManager", "loadAllMusic errorCode : ", Integer.valueOf(i2));
        if (i2 == 1) {
            iBaseResponseCallback.d(i2, obj);
            return;
        }
        if (i2 != 0) {
            iBaseResponseCallback.d(i2, null);
            return;
        }
        if (obj instanceof MusicSong) {
            int i3 = this.n + 1;
            this.n = i3;
            iBaseResponseCallback.d(0, new jkg(this.n, (int) ((i3 / i) * 100.0f), String.format(Locale.ROOT, bHx_().getString(R$string.IDS_hwh_motiontrack_target_progress), Integer.valueOf(this.n), Integer.valueOf(i)), (MusicSong) obj));
        }
    }

    private void a(final IBaseResponseCallback iBaseResponseCallback) {
        if (iBaseResponseCallback == null) {
            LogUtil.h("MusicUiManager", "getAllPageMusicInfo callback is null");
            return;
        }
        if (this.k) {
            iBaseResponseCallback.d(3, null);
        } else if (this.h < this.o.size()) {
            c(new IBaseResponseCallback() { // from class: jjo
                @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                /* renamed from: onResponse */
                public final void d(int i, Object obj) {
                    jjm.this.d(iBaseResponseCallback, i, obj);
                }
            });
        } else {
            iBaseResponseCallback.d(1, null);
        }
    }

    /* synthetic */ void d(IBaseResponseCallback iBaseResponseCallback, int i, Object obj) {
        LogUtil.a("MusicUiManager", "getAllPageMusicInfo errorCode : ", Integer.valueOf(i));
        if (i == 0) {
            iBaseResponseCallback.d(0, obj);
        } else if (i == 1) {
            iBaseResponseCallback.d(1, obj);
            a(iBaseResponseCallback);
        } else {
            iBaseResponseCallback.d(3, null);
        }
    }

    public void b(boolean z) {
        this.k = z;
    }

    public String d(int i, int i2) {
        if (!i() || i == i2) {
            return String.format(Locale.ROOT, bHx_().getString(R$string.IDS_settings_music_songs_number), Integer.valueOf(i));
        }
        return String.format(Locale.ROOT, bHx_().getString(R$string.IDS_hw_health_music_loaded), bHx_().getQuantityString(R.plurals._2130903352_res_0x7f030138, i, Integer.valueOf(i)));
    }

    public String b(int i, int i2) {
        int i3 = !i() ? i : i2;
        if (i <= i2) {
            i = i3;
        }
        if (i <= 0) {
            return bHx_().getString(R$string.IDS_hw_health_music_watch_all_music);
        }
        return String.format(Locale.ROOT, bHx_().getString(R$string.IDS_settings_music_songs_number), Integer.valueOf(i));
    }

    public void d(IBaseResponseCallback iBaseResponseCallback) {
        if (iBaseResponseCallback == null) {
            LogUtil.h("MusicUiManager", "loadMoreMusic callback is null");
            return;
        }
        if (this.o.isEmpty()) {
            iBaseResponseCallback.d(2, null);
            LogUtil.h("MusicUiManager", "loadMoreMusic mMusicPageStructList is empty");
        } else if (this.h > this.o.size()) {
            iBaseResponseCallback.d(2, null);
            LogUtil.h("MusicUiManager", "loadMoreMusic mCurrentPageIndex is out of bound : ", Integer.valueOf(this.h));
        } else {
            this.f = true;
            c(iBaseResponseCallback);
        }
    }

    private void c(final IBaseResponseCallback iBaseResponseCallback) {
        final MusicPageStruct musicPageStruct = this.o.get(this.h);
        if (this.b <= musicPageStruct.getMusicEndFrameIndex()) {
            jjd.b(BaseApplication.getContext()).b(this.b, new IBaseResponseCallback() { // from class: jjk
                @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                /* renamed from: onResponse */
                public final void d(int i, Object obj) {
                    jjm.this.a(iBaseResponseCallback, musicPageStruct, i, obj);
                }
            });
            return;
        }
        this.h++;
        jkb jkbVar = new jkb();
        jkbVar.b(this.j);
        jkbVar.d(this.m);
        iBaseResponseCallback.d(1, jkbVar);
        this.f = false;
        this.j = null;
        if (this.h < this.o.size()) {
            this.b = this.o.get(this.h).getMusicStartFrameIndex();
            d(true);
        } else {
            d(false);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: e, reason: merged with bridge method [inline-methods] */
    public void a(int i, Object obj, IBaseResponseCallback iBaseResponseCallback, MusicPageStruct musicPageStruct) {
        if (this.k) {
            iBaseResponseCallback.d(3, null);
            return;
        }
        if (i == 1 && bkz.a(obj, MusicStruct.class)) {
            List<MusicStruct> list = (List) obj;
            if (this.j == null) {
                this.j = new ArrayList(16);
            }
            if (this.m == null) {
                this.m = new ArrayList(16);
            }
            for (MusicStruct musicStruct : list) {
                MusicSong musicSong = new MusicSong();
                musicSong.setFileName(musicStruct.getFileName());
                musicSong.setSongName(musicStruct.getMusicName());
                musicSong.setSongSingerName(musicStruct.getMusicSinger());
                musicSong.setSongIndex(String.valueOf(musicStruct.getMusicIndex()));
                this.j.add(musicSong);
                this.m.add(musicStruct);
                iBaseResponseCallback.d(0, musicSong);
            }
            this.b++;
            c(iBaseResponseCallback);
            return;
        }
        iBaseResponseCallback.d(2, null);
        this.j = null;
        this.b = musicPageStruct.getMusicStartFrameIndex();
        this.f = false;
    }

    public void b(NegotiationData negotiationData) {
        this.o = negotiationData.getMusicPageStructList();
        this.h = 1;
        this.b = 0;
        d(false);
        this.j = null;
        if (this.o.size() > 1) {
            this.b = this.o.get(this.h).getMusicStartFrameIndex();
            d(true);
        }
    }

    public void h() {
        if (e != null) {
            e = null;
        }
    }

    public ArrayList<MusicSong> c() {
        return this.d;
    }

    public void e(ArrayList<MusicSong> arrayList) {
        this.d = arrayList;
    }

    public MusicMenu b() {
        return this.i;
    }

    public void d(MusicMenu musicMenu) {
        this.i = musicMenu;
    }

    public MusicMenu d() {
        return this.c;
    }

    public void e(MusicMenu musicMenu) {
        this.c = musicMenu;
    }
}
