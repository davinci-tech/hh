package defpackage;

import android.text.TextUtils;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.LogAnonymous;
import org.json.JSONArray;
import org.json.JSONException;

/* loaded from: classes4.dex */
public class ffg {
    private int i = 450;
    private int e = 420;
    private int b = 390;
    private int c = 360;
    private int d = 330;

    /* renamed from: a, reason: collision with root package name */
    private int f12484a = 300;

    public int j() {
        return this.i;
    }

    public void h(int i) {
        this.i = i;
    }

    public int b() {
        return this.e;
    }

    public void e(int i) {
        this.e = i;
    }

    public int d() {
        return this.b;
    }

    public void c(int i) {
        this.b = i;
    }

    public int c() {
        return this.c;
    }

    public void a(int i) {
        this.c = i;
    }

    public int e() {
        return this.d;
    }

    public void d(int i) {
        this.d = i;
    }

    public int a() {
        return this.f12484a;
    }

    public void b(int i) {
        this.f12484a = i;
    }

    public int d(float f) {
        float f2 = this.e;
        if (f > f2 && f <= this.i) {
            return 0;
        }
        float f3 = this.b;
        if (f > f3 && f <= f2) {
            return 1;
        }
        float f4 = this.c;
        if (f > f4 && f <= f3) {
            return 2;
        }
        float f5 = this.d;
        if (f <= f5 || f > f4) {
            return (f <= ((float) this.f12484a) || f > f5) ? -1 : 4;
        }
        return 3;
    }

    public void c(String str) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.b("Track_PaceZoneConfig", "paceZoneConfigStr is null");
            return;
        }
        try {
            JSONArray jSONArray = new JSONArray(str);
            if (jSONArray.length() == 6) {
                this.i = jSONArray.getInt(0);
                this.e = jSONArray.getInt(1);
                this.b = jSONArray.getInt(2);
                this.c = jSONArray.getInt(3);
                this.d = jSONArray.getInt(4);
                this.f12484a = jSONArray.getInt(5);
            }
        } catch (JSONException e) {
            LogUtil.b("Track_PaceZoneConfig", "transformPaceZoneConfig:", LogAnonymous.b((Throwable) e));
        }
    }

    public int b(int i, boolean z) {
        int i2;
        if (i != 1) {
            if (i != 2) {
                if (i != 3) {
                    if (i != 4) {
                        if (i == 5) {
                            if (z) {
                                return this.f12484a;
                            }
                            return this.d;
                        }
                        LogUtil.b("Track_PaceZoneConfig", "paceZone should be given right data ", Integer.valueOf(i));
                        if (i > 0) {
                            return this.i;
                        }
                        return this.f12484a;
                    }
                    if (z) {
                        i2 = this.d;
                    } else {
                        return this.c;
                    }
                } else if (z) {
                    i2 = this.c;
                } else {
                    return this.b;
                }
            } else if (z) {
                i2 = this.b;
            } else {
                return this.e;
            }
        } else if (z) {
            i2 = this.e;
        } else {
            return this.i;
        }
        return i2 + 1;
    }

    public int[] f() {
        return new int[]{this.i, this.e, this.b, this.c, this.d, this.f12484a};
    }
}
