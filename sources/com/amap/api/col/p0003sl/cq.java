package com.amap.api.col.p0003sl;

import android.content.Context;
import com.amap.api.col.p0003sl.cp;
import com.amap.api.maps.MapsInitializer;
import com.autonavi.base.amap.api.mapcore.IAMapDelegate;
import com.autonavi.base.amap.mapcore.FileUtil;
import java.io.File;
import java.util.Arrays;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes2.dex */
public final class cq extends lb {

    /* renamed from: a, reason: collision with root package name */
    private Context f946a;
    private IAMapDelegate b;
    private cp c;
    private String d;
    private String e;
    private String g;
    private a h;
    private int i;

    public interface a {
        void a(byte[] bArr, int i);

        void b(byte[] bArr, int i);
    }

    public cq(Context context, a aVar, int i, String str) {
        this.d = null;
        this.e = null;
        this.g = null;
        this.f946a = context;
        this.h = aVar;
        this.i = i;
        if (this.c == null) {
            this.c = new cp(context, "", i != 0);
        }
        this.c.b(str);
        StringBuilder sb = new StringBuilder();
        sb.append(i);
        sb.append(str == null ? "" : str);
        sb.append(".amapstyle");
        this.d = sb.toString();
        this.e = context.getCacheDir().getPath();
    }

    public cq(Context context, IAMapDelegate iAMapDelegate) {
        this.d = null;
        this.e = null;
        this.g = null;
        this.i = 0;
        this.f946a = context;
        this.b = iAMapDelegate;
        if (this.c == null) {
            this.c = new cp(context, "");
        }
    }

    public final void a(String str) {
        cp cpVar = this.c;
        if (cpVar != null) {
            cpVar.c(str);
        }
        this.g = str;
    }

    private byte[] b(String str) {
        if (str == null || this.e == null) {
            return null;
        }
        return FileUtil.readFileContents(this.e + File.separator + str);
    }

    private void a(String str, byte[] bArr) {
        if (str == null || bArr == null || this.e == null) {
            return;
        }
        FileUtil.saveFileContents(this.e + File.separator + str, bArr);
    }

    private String c(String str) {
        if (str == null) {
            return null;
        }
        Object b = dr.b(this.f946a, "amap_style_config", "lastModified".concat(String.valueOf(str)), "");
        if (!(b instanceof String) || b == "") {
            return null;
        }
        return (String) b;
    }

    private void a(String str, String str2) {
        if (str == null || str2 == null) {
            return;
        }
        dr.a(this.f946a, "amap_style_config", "lastModified".concat(String.valueOf(str)), str2);
    }

    @Override // com.amap.api.col.p0003sl.lb
    public final void runTask() {
        JSONObject jSONObject;
        try {
            if (MapsInitializer.getNetWorkEnable()) {
                if (this.c != null) {
                    String str = this.g + this.d;
                    String c = c(str);
                    if (c != null) {
                        this.c.d(c);
                    }
                    byte[] b = b(str);
                    a aVar = this.h;
                    if (aVar != null && b != null) {
                        aVar.a(b, this.i);
                    }
                    cp.a d = this.c.d();
                    if (d != null && d.f945a != null) {
                        try {
                            jSONObject = new JSONObject(new String(d.f945a));
                        } catch (JSONException unused) {
                            jSONObject = null;
                        }
                        if (jSONObject == null) {
                            if (this.h != null) {
                                if (!Arrays.equals(d.f945a, b)) {
                                    this.h.b(d.f945a, this.i);
                                }
                            } else {
                                IAMapDelegate iAMapDelegate = this.b;
                                if (iAMapDelegate != null) {
                                    iAMapDelegate.setCustomMapStyle(iAMapDelegate.getMapConfig().isCustomStyleEnable(), d.f945a);
                                }
                            }
                            a(str, d.f945a);
                            a(str, d.c);
                        }
                    }
                }
                iv.a(this.f946a, dv.a());
                IAMapDelegate iAMapDelegate2 = this.b;
                if (iAMapDelegate2 != null) {
                    iAMapDelegate2.setRunLowFrame(false);
                }
            }
        } catch (Throwable th) {
            iv.c(th, "CustomStyleTask", "download customStyle");
            th.printStackTrace();
        }
    }

    public final void a() {
        this.f946a = null;
        if (this.c != null) {
            this.c = null;
        }
    }

    public final void b() {
        dt.a().a(this);
    }
}
