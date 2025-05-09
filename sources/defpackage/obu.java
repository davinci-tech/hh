package defpackage;

import android.os.Bundle;
import android.view.View;
import com.huawei.operation.OpAnalyticsConstants;

/* loaded from: classes6.dex */
public class obu {

    /* renamed from: a, reason: collision with root package name */
    private int f15606a;
    private String b;
    private boolean c;
    private boolean d;
    private String e;
    private String f;
    private View.OnClickListener g;
    private int h;
    private int i;
    private int j;
    private String l;

    public obu(int i) {
        this.f15606a = -1;
        this.e = null;
        this.l = null;
        this.j = -1;
        this.h = -1;
        this.d = false;
        this.f = "";
        this.c = false;
        this.g = null;
        this.i = i;
    }

    public obu(int i, String str, String str2, int i2) {
        this.f15606a = -1;
        this.e = null;
        this.l = null;
        this.j = -1;
        this.h = -1;
        this.d = false;
        this.i = -1;
        this.f = "";
        this.c = false;
        this.g = null;
        this.f15606a = ((Integer) jdy.d(Integer.valueOf(i))).intValue();
        this.e = (String) jdy.d(str);
        this.l = (String) jdy.d(str2);
        this.h = ((Integer) jdy.d(Integer.valueOf(i2))).intValue();
    }

    public obu(int i, String str, String str2, boolean z, String str3) {
        this.f15606a = -1;
        this.e = null;
        this.l = null;
        this.j = -1;
        this.h = -1;
        this.d = false;
        this.i = -1;
        this.f = "";
        this.c = false;
        this.g = null;
        this.f15606a = ((Integer) jdy.d(Integer.valueOf(i))).intValue();
        this.e = (String) jdy.d(str);
        this.l = (String) jdy.d(str2);
        this.d = ((Boolean) jdy.d(Boolean.valueOf(z))).booleanValue();
        this.b = (String) jdy.d(str3);
    }

    public boolean k() {
        return this.c;
    }

    public void d(boolean z) {
        this.c = z;
    }

    public String i() {
        return this.f;
    }

    public void d(String str) {
        this.f = str;
    }

    public int b() {
        return ((Integer) jdy.d(Integer.valueOf(this.i))).intValue();
    }

    public void a(int i) {
        this.i = ((Integer) jdy.d(Integer.valueOf(i))).intValue();
    }

    public boolean g() {
        return ((Boolean) jdy.d(Boolean.valueOf(this.d))).booleanValue();
    }

    public String c() {
        return (String) jdy.d(this.b);
    }

    public int e() {
        return ((Integer) jdy.d(Integer.valueOf(this.f15606a))).intValue();
    }

    public String d() {
        return (String) jdy.d(this.e);
    }

    public String h() {
        return (String) jdy.d(this.l);
    }

    public int j() {
        return ((Integer) jdy.d(Integer.valueOf(this.h))).intValue();
    }

    public View.OnClickListener cUS_() {
        return (View.OnClickListener) jdy.d(this.g);
    }

    public void cUT_(View.OnClickListener onClickListener) {
        this.g = (View.OnClickListener) jdy.d(onClickListener);
    }

    public String toString() {
        return "SettingMainListItem [mId=" + this.f15606a + ", mContent=" + this.e + ", mSummary=" + this.l + ", mResIdRight=" + this.j + ",mResView=" + this.h + "]";
    }

    public Bundle cUR_() {
        Bundle bundle = new Bundle();
        bundle.putInt(OpAnalyticsConstants.OPERATION_ID, e());
        bundle.putString("mContent", d());
        return bundle;
    }
}
