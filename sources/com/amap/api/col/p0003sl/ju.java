package com.amap.api.col.p0003sl;

import android.content.Context;
import com.huawei.pluginachievement.manager.model.MedalConstants;
import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes2.dex */
public abstract class ju extends hu {

    /* renamed from: a, reason: collision with root package name */
    protected Context f1231a;
    protected hz b;
    protected byte[] g;

    public abstract byte[] c();

    public abstract byte[] d();

    public boolean f() {
        return true;
    }

    protected boolean h() {
        return false;
    }

    public ju(Context context, hz hzVar) {
        if (context != null) {
            this.f1231a = context.getApplicationContext();
        }
        this.b = hzVar;
        setBinary(true);
    }

    @Override // com.amap.api.col.p0003sl.ka
    public Map<String, String> getParams() {
        String f = hn.f(this.f1231a);
        String a2 = hq.a();
        String a3 = hq.a(this.f1231a, a2, "key=".concat(String.valueOf(f)));
        HashMap hashMap = new HashMap();
        hashMap.put("ts", a2);
        hashMap.put(MedalConstants.EVENT_KEY, f);
        hashMap.put("scode", a3);
        return hashMap;
    }

    @Override // com.amap.api.col.p0003sl.ka
    public final byte[] getEntityBytes() {
        byte[] bArr = this.g;
        if (bArr != null) {
            return bArr;
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            byteArrayOutputStream.write(i());
            byteArrayOutputStream.write(j());
            byteArrayOutputStream.write(k());
            byteArrayOutputStream.write(l());
            byte[] byteArray = byteArrayOutputStream.toByteArray();
            this.g = byteArray;
            return byteArray;
        } catch (Throwable th) {
            try {
                is.a(th, "bre", "geb");
                try {
                    byteArrayOutputStream.close();
                    return null;
                } catch (Throwable th2) {
                    is.a(th2, "bre", "geb");
                    return null;
                }
            } finally {
                try {
                    byteArrayOutputStream.close();
                } catch (Throwable th3) {
                    is.a(th3, "bre", "geb");
                }
            }
        }
    }

    private static byte[] i() {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            byteArrayOutputStream.write(ia.a("PANDORA$"));
            byteArrayOutputStream.write(new byte[]{1});
            byteArrayOutputStream.write(new byte[]{0});
            return byteArrayOutputStream.toByteArray();
        } catch (Throwable th) {
            try {
                is.a(th, "bre", "gbh");
                try {
                    byteArrayOutputStream.close();
                    return null;
                } catch (Throwable th2) {
                    is.a(th2, "bre", "gbh");
                    return null;
                }
            } finally {
                try {
                    byteArrayOutputStream.close();
                } catch (Throwable th3) {
                    is.a(th3, "bre", "gbh");
                }
            }
        }
    }

    private byte[] j() {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            byteArrayOutputStream.write(new byte[]{3});
            if (f()) {
                Context context = this.f1231a;
                boolean h = h();
                hz hzVar = this.b;
                byte[] a2 = hq.a(context, h, hzVar != null && "navi".equals(hzVar.a()));
                byteArrayOutputStream.write(a(a2));
                byteArrayOutputStream.write(a2);
            } else {
                byteArrayOutputStream.write(new byte[]{0, 0});
            }
            byte[] a3 = ia.a(e());
            if (a3 != null && a3.length > 0) {
                byteArrayOutputStream.write(a(a3));
                byteArrayOutputStream.write(a3);
            } else {
                byteArrayOutputStream.write(new byte[]{0, 0});
            }
            byte[] a4 = ia.a(g());
            if (a4 != null && a4.length > 0) {
                byteArrayOutputStream.write(a(a4));
                byteArrayOutputStream.write(a4);
            } else {
                byteArrayOutputStream.write(new byte[]{0, 0});
            }
            return byteArrayOutputStream.toByteArray();
        } catch (Throwable th) {
            try {
                is.a(th, "bre", "gpd");
                try {
                    byteArrayOutputStream.close();
                } catch (Throwable th2) {
                    is.a(th2, "bre", "gred");
                }
                return new byte[]{0};
            } finally {
                try {
                    byteArrayOutputStream.close();
                } catch (Throwable th3) {
                    is.a(th3, "bre", "gred");
                }
            }
        }
    }

    public String g() {
        return String.format("platform=Android&sdkversion=%s&product=%s", this.b.c(), this.b.a());
    }

    protected static byte[] a(byte[] bArr) {
        return ia.a(bArr.length);
    }

    private byte[] k() {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            byte[] c = c();
            if (c != null && c.length != 0) {
                byteArrayOutputStream.write(new byte[]{1});
                byteArrayOutputStream.write(a(c));
                byteArrayOutputStream.write(c);
                return byteArrayOutputStream.toByteArray();
            }
            byteArrayOutputStream.write(new byte[]{0});
            byte[] byteArray = byteArrayOutputStream.toByteArray();
            try {
                byteArrayOutputStream.close();
            } catch (Throwable th) {
                is.a(th, "bre", "grrd");
            }
            return byteArray;
        } catch (Throwable th2) {
            try {
                is.a(th2, "bre", "grrd");
                try {
                    byteArrayOutputStream.close();
                } catch (Throwable th3) {
                    is.a(th3, "bre", "grrd");
                }
                return new byte[]{0};
            } finally {
                try {
                    byteArrayOutputStream.close();
                } catch (Throwable th4) {
                    is.a(th4, "bre", "grrd");
                }
            }
        }
    }

    private byte[] l() {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            byte[] d = d();
            if (d != null && d.length != 0) {
                byteArrayOutputStream.write(new byte[]{1});
                byte[] a2 = hq.a(d);
                byteArrayOutputStream.write(a(a2));
                byteArrayOutputStream.write(a2);
                return byteArrayOutputStream.toByteArray();
            }
            byteArrayOutputStream.write(new byte[]{0});
            byte[] byteArray = byteArrayOutputStream.toByteArray();
            try {
                byteArrayOutputStream.close();
            } catch (Throwable th) {
                is.a(th, "bre", "gred");
            }
            return byteArray;
        } catch (Throwable th2) {
            try {
                is.a(th2, "bre", "gred");
                try {
                    byteArrayOutputStream.close();
                } catch (Throwable th3) {
                    is.a(th3, "bre", "gred");
                }
                return new byte[]{0};
            } finally {
                try {
                    byteArrayOutputStream.close();
                } catch (Throwable th4) {
                    is.a(th4, "bre", "gred");
                }
            }
        }
    }

    protected String e() {
        return "2.1";
    }
}
