package defpackage;

import com.huawei.hwdevice.mainprocess.mgr.hwmusiccontrolmgr.datatype.MusicSong;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;

/* loaded from: classes6.dex */
public class ocg {

    /* renamed from: a, reason: collision with root package name */
    private ArrayList<MusicSong> f15613a;
    private String d;

    public void b(String str) {
        this.d = str;
    }

    public String b() {
        return this.d;
    }

    public void a(ArrayList<MusicSong> arrayList) {
        this.f15613a = arrayList;
    }

    public ArrayList<MusicSong> d() {
        return this.f15613a;
    }

    public static final b a() {
        return new b();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static class b implements Comparator, Serializable {
        private static final long serialVersionUID = -3362383176034179661L;

        b() {
        }

        @Override // java.util.Comparator
        public int compare(Object obj, Object obj2) {
            return ocf.d(obj instanceof ocg ? ((ocg) obj).e() : "", obj2 instanceof ocg ? ((ocg) obj2).e() : "");
        }
    }

    public String e() {
        return ocf.a(this.d);
    }
}
