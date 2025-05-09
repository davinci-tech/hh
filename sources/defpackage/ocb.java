package defpackage;

import com.huawei.hwdevice.mainprocess.mgr.hwmusiccontrolmgr.datatype.MusicSong;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;

/* loaded from: classes6.dex */
public class ocb {

    /* renamed from: a, reason: collision with root package name */
    private ArrayList<MusicSong> f15610a;
    private String c;

    public void c(String str) {
        this.c = str;
    }

    public String d() {
        return this.c;
    }

    public void e(ArrayList<MusicSong> arrayList) {
        this.f15610a = arrayList;
    }

    public ArrayList<MusicSong> e() {
        return this.f15610a;
    }

    public static final c c() {
        return new c();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static class c implements Comparator, Serializable {
        private static final long serialVersionUID = -3362383176034179661L;

        c() {
        }

        @Override // java.util.Comparator
        public int compare(Object obj, Object obj2) {
            if ((obj instanceof ocb) && (obj2 instanceof ocb)) {
                return ocf.d(((ocb) obj).b(), ((ocb) obj2).b());
            }
            return 0;
        }
    }

    public String b() {
        return ocf.a(this.c);
    }
}
