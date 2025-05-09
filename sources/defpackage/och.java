package defpackage;

import com.huawei.hwdevice.mainprocess.mgr.hwmusiccontrolmgr.datatype.MusicSong;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;

/* loaded from: classes6.dex */
public class och {
    private ArrayList<MusicSong> b;
    private String c;

    public void e(String str) {
        this.c = str;
    }

    public String c() {
        return this.c;
    }

    public void b(ArrayList<MusicSong> arrayList) {
        this.b = arrayList;
    }

    public ArrayList<MusicSong> a() {
        return this.b;
    }

    public static final c e() {
        return new c();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static class c implements Comparator, Serializable {
        private static final long serialVersionUID = -3362383176034179661L;

        c() {
        }

        @Override // java.util.Comparator
        public int compare(Object obj, Object obj2) {
            return ocf.d(obj instanceof och ? ((och) obj).d() : "", obj2 instanceof och ? ((och) obj2).d() : "");
        }
    }

    public String d() {
        return ocf.a(this.c);
    }
}
