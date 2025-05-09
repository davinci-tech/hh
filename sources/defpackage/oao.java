package defpackage;

import com.huawei.hwdevice.mainprocess.mgr.hwmusiccontrolmgr.datatype.MusicSong;
import java.util.ArrayList;

/* loaded from: classes6.dex */
public class oao {
    private static final Object c = new Object();
    private static oao d;
    private ArrayList<MusicSong> b = new ArrayList<>(16);

    private oao() {
    }

    public static oao d() {
        oao oaoVar;
        synchronized (c) {
            if (d == null) {
                d = new oao();
            }
            oaoVar = d;
        }
        return oaoVar;
    }

    public void e() {
        a();
    }

    private static void a() {
        synchronized (c) {
            d = null;
        }
    }

    public ArrayList<MusicSong> b() {
        return this.b;
    }
}
