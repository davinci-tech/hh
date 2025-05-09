package defpackage;

import android.widget.CompoundButton;

/* loaded from: classes6.dex */
public class obz {

    /* renamed from: a, reason: collision with root package name */
    private int f15609a = -1;
    private int d = 0;
    private int j = 1;
    private String e = null;
    private String f = null;
    private String i = null;
    private boolean c = false;
    private CompoundButton.OnCheckedChangeListener h = null;
    private boolean g = false;
    private boolean b = true;

    public void e(int i) {
        this.f15609a = i;
    }

    public int e() {
        return this.f15609a;
    }

    public void a(String str) {
        this.e = str;
    }

    public String d() {
        return this.e;
    }

    public void d(String str) {
        this.f = str;
    }

    public String j() {
        return this.f;
    }

    public void e(String str) {
        this.i = str;
    }

    public String c() {
        return this.i;
    }

    public void c(int i) {
        this.j = i;
    }

    public int i() {
        return this.j;
    }

    public void e(boolean z) {
        this.c = z;
    }

    public boolean f() {
        return this.c;
    }

    public boolean h() {
        return this.g;
    }

    public void cVo_(CompoundButton.OnCheckedChangeListener onCheckedChangeListener) {
        this.h = onCheckedChangeListener;
    }

    public CompoundButton.OnCheckedChangeListener cVn_() {
        return this.h;
    }

    public void b(int i) {
        this.d = i;
    }

    public int b() {
        return this.d;
    }

    public void d(boolean z) {
        this.b = z;
    }

    public boolean g() {
        return this.b;
    }

    public String toString() {
        return "DeviceSettingFactoryListItem [mId=" + this.f15609a + ", mContent=" + this.e + ", mSubContent=" + this.f + ", mIsChecked=" + this.c + ", mType=" + this.j + "]";
    }
}
