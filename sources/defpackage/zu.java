package defpackage;

import com.huawei.android.hicloud.sync.service.aidl.UnstructData;
import java.util.ArrayList;

/* loaded from: classes8.dex */
public class zu {

    /* renamed from: a, reason: collision with root package name */
    private String f17777a;
    private String c;
    private int d;
    private ArrayList<UnstructData> b = new ArrayList<>(10);
    private ArrayList<UnstructData> e = new ArrayList<>(10);

    public String a() {
        return this.f17777a;
    }

    public ArrayList<UnstructData> b() {
        return this.e;
    }

    public String c() {
        return this.c;
    }

    public ArrayList<UnstructData> d() {
        return this.b;
    }

    public void d(String str) {
        this.c = str;
    }

    public int e() {
        return this.d;
    }

    public void e(String str) {
        this.f17777a = str;
    }

    public void e(ArrayList<UnstructData> arrayList) {
        this.b = arrayList;
    }

    public String toString() {
        return "UpdateResult [id=" + this.f17777a + ", guid=" + this.c + ", recycleStatus=" + this.d + ", downFileList=" + this.b.toString() + ", deleteFileList=" + this.e.toString() + "]";
    }
}
