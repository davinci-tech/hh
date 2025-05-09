package defpackage;

import android.text.SpannableStringBuilder;
import com.huawei.ui.commonui.linechart.common.DataInfos;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes9.dex */
public class qml {
    private SpannableStringBuilder c;
    private long f;
    private SpannableStringBuilder j;
    private String m;
    private List<nkz> h = new ArrayList();
    private String g = null;
    private boolean b = false;
    private long i = 0;

    /* renamed from: a, reason: collision with root package name */
    private long f16486a = 0;
    private boolean e = false;
    private DataInfos d = DataInfos.NoDataPlaceHolder;

    public void a(long j) {
        this.f = j;
    }

    public long b() {
        return this.f;
    }

    public void c(String str) {
        this.m = str;
    }

    public String g() {
        return this.m;
    }

    public void c(DataInfos dataInfos) {
        this.d = dataInfos;
    }

    public DataInfos a() {
        return this.d;
    }

    public void b(long j, long j2) {
        this.i = j;
        this.f16486a = j2;
    }

    public void c(List<nkz> list) {
        this.h.clear();
        this.h.addAll(list);
    }

    public void e(String str) {
        this.g = str;
    }

    public String j() {
        return this.g;
    }

    public boolean l() {
        return this.b;
    }

    public void c(boolean z) {
        this.b = z;
    }

    public List<nkz> h() {
        return this.h;
    }

    public long f() {
        return this.i;
    }

    public long c() {
        return this.f16486a;
    }

    public SpannableStringBuilder dFF_() {
        return this.c;
    }

    public SpannableStringBuilder dFG_() {
        return this.j;
    }

    public void dFH_(SpannableStringBuilder spannableStringBuilder) {
        this.c = spannableStringBuilder;
    }

    public void dFI_(SpannableStringBuilder spannableStringBuilder) {
        this.j = spannableStringBuilder;
    }

    public boolean i() {
        return this.e;
    }

    public void d(boolean z) {
        this.e = z;
    }

    public String toString() {
        return "PressureData{mDataInfos=" + this.d + ", mJumpTime=" + this.f + '}';
    }
}
