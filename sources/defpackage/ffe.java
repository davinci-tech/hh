package defpackage;

import com.huawei.health.R;

/* loaded from: classes4.dex */
public class ffe {

    /* renamed from: a, reason: collision with root package name */
    private int[] f12481a;
    private String[] b;
    private String[] c;
    private String[] d;
    private int[] e;
    private String[] g = arx.b().getResources().getStringArray(R.array._2130968618_res_0x7f04002a);

    public ffe(int i) {
        if (i == 0 || i == 1) {
            this.d = arx.b().getResources().getStringArray(R.array._2130968619_res_0x7f04002b);
            this.f12481a = arx.b().getResources().getIntArray(R.array._2130968702_res_0x7f04007e);
            this.e = arx.b().getResources().getIntArray(R.array._2130968703_res_0x7f04007f);
            this.c = arx.b().getResources().getStringArray(R.array._2130968620_res_0x7f04002c);
            this.b = arx.b().getResources().getStringArray(R.array._2130968621_res_0x7f04002d);
            return;
        }
        this.d = arx.b().getResources().getStringArray(R.array._2130968622_res_0x7f04002e);
        this.f12481a = arx.b().getResources().getIntArray(R.array._2130968704_res_0x7f040080);
        this.e = arx.b().getResources().getIntArray(R.array._2130968705_res_0x7f040081);
        this.c = arx.b().getResources().getStringArray(R.array._2130968623_res_0x7f04002f);
        this.b = arx.b().getResources().getStringArray(R.array._2130968624_res_0x7f040030);
    }

    public String d(int i) {
        if (i >= 0) {
            String[] strArr = this.g;
            if (i < strArr.length) {
                return strArr[i];
            }
        }
        return String.valueOf(i);
    }

    public String c(int i) {
        if (i >= 0) {
            String[] strArr = this.d;
            if (strArr.length > i) {
                return strArr[i];
            }
        }
        return String.valueOf(i);
    }

    public int b(int i) {
        if (i >= 0) {
            int[] iArr = this.f12481a;
            if (iArr.length >= i) {
                return iArr[i];
            }
        }
        return this.f12481a[0];
    }

    public int a(int i) {
        if (i >= 0) {
            int[] iArr = this.e;
            if (iArr.length >= i) {
                return iArr[i];
            }
        }
        return this.e[0];
    }
}
