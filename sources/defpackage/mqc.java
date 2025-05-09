package defpackage;

import android.util.SparseArray;
import android.util.SparseIntArray;
import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes6.dex */
public class mqc {

    @SerializedName("huid")
    private long c;

    @SerializedName("nickName")
    private String f;

    @SerializedName("userAccount")
    private String g;
    private List<String> d = new ArrayList(10);

    /* renamed from: a, reason: collision with root package name */
    private SparseArray<mqo> f15107a = new SparseArray<>(10);
    private SparseArray<mqo> h = new SparseArray<>(10);
    private SparseIntArray j = new SparseIntArray(10);
    private int e = 0;
    private int b = 0;

    public void d(long j) {
        this.c = j;
    }

    public long d() {
        return this.c;
    }

    public void e(String str) {
        this.f = str;
    }

    public String g() {
        return this.f;
    }

    public String h() {
        return this.g;
    }

    public void d(String str) {
        this.g = str;
    }

    public void b(List<String> list) {
        this.d = list;
    }

    public List<String> c() {
        return new ArrayList(this.d);
    }

    public void cmZ_(SparseArray<mqo> sparseArray) {
        this.f15107a = sparseArray;
    }

    public SparseArray<mqo> cmW_() {
        SparseArray<mqo> sparseArray = this.f15107a;
        if (sparseArray != null) {
            return sparseArray.clone();
        }
        return null;
    }

    public void cna_(SparseArray<mqo> sparseArray) {
        this.h = sparseArray;
    }

    public SparseArray<mqo> cmX_() {
        SparseArray<mqo> sparseArray = this.h;
        if (sparseArray != null) {
            return sparseArray.clone();
        }
        return null;
    }

    public void cnb_(SparseIntArray sparseIntArray) {
        this.j = sparseIntArray;
    }

    public SparseIntArray cmY_() {
        SparseIntArray sparseIntArray = this.j;
        if (sparseIntArray != null) {
            return sparseIntArray.clone();
        }
        return null;
    }

    public void a(int i) {
        this.e = i;
    }

    public int e() {
        return this.e;
    }

    public void e(int i) {
        this.b = i;
    }

    public int a() {
        return this.b;
    }
}
