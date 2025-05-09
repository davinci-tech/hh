package defpackage;

import android.content.Context;
import com.huawei.health.R;
import com.huawei.hwcommonmodel.application.BaseApplication;
import health.compact.a.UnitUtil;
import java.io.Serializable;
import java.util.List;

/* loaded from: classes4.dex */
public class gxq implements Serializable {
    private static final long serialVersionUID = 6937177666241928825L;

    /* renamed from: a, reason: collision with root package name */
    private int f12993a;
    private boolean b = false;
    private int c;
    private final int d;
    private float e;
    private int h;

    public gxq(int i, List<Integer> list) {
        this.d = i;
        for (int i2 = 0; i2 < list.size(); i2++) {
            if (this.d == list.get(i2).intValue()) {
                if (i2 == list.size() - 1) {
                    this.h = Integer.MAX_VALUE;
                } else {
                    this.h = list.get(i2 + 1).intValue();
                }
            }
        }
    }

    public int c() {
        return this.d;
    }

    public void d(int i) {
        this.f12993a = i;
    }

    public float a() {
        if (this.b) {
            return Math.round(this.e);
        }
        return this.e;
    }

    public void d(float f) {
        this.e = f;
    }

    public void c(int i) {
        this.c = i;
    }

    public String d() {
        Context context = BaseApplication.getContext();
        double d = UnitUtil.h() ? 1.609344d : 1.0d;
        String a2 = fhq.a(BaseApplication.getContext(), (float) (this.h * d));
        String a3 = fhq.a(BaseApplication.getContext(), (float) (this.d * d));
        if (this.d == 0) {
            return context.getString(R.string._2130848587_res_0x7f022b4b, a2);
        }
        if (this.h > 1000) {
            return context.getString(R.string._2130850348_res_0x7f02322c, a3);
        }
        return context.getString(R.string._2130850349_res_0x7f02322d, a3, a2);
    }

    public String f() {
        return UnitUtil.d(this.f12993a);
    }

    public String b() {
        if (this.f12993a < 60 || this.e <= 0.0f) {
            return "--";
        }
        Context context = BaseApplication.getContext();
        int i = this.c;
        if (i == -1) {
            return context.getString(R.string._2130846344_res_0x7f022288);
        }
        if (i == 0) {
            return context.getString(R.string._2130843959_res_0x7f021937);
        }
        if (i == 1) {
            return context.getString(R.string._2130846028_res_0x7f02214c);
        }
        if (i == 2) {
            return context.getString(R.string._2130846029_res_0x7f02214d);
        }
        return context.getString(R.string._2130846343_res_0x7f022287);
    }

    public void b(boolean z) {
        this.b = z;
    }

    public boolean e() {
        return this.b;
    }
}
