package com.amap.api.col.p0003sl;

import android.content.Context;
import com.amap.api.col.p0003sl.cr;
import com.amap.api.maps.MapsInitializer;
import com.autonavi.base.amap.mapcore.FileUtil;

/* loaded from: classes2.dex */
public final class cs extends lb {

    /* renamed from: a, reason: collision with root package name */
    private Context f948a;
    private cr b;
    private cy c;
    private a d;

    public interface a {
        void a(String str, cy cyVar);
    }

    public cs(Context context) {
        this.f948a = context;
        if (this.b == null) {
            this.b = new cr(context, "");
        }
    }

    public final void a(String str) {
        cr crVar = this.b;
        if (crVar != null) {
            crVar.b(str);
        }
    }

    @Override // com.amap.api.col.p0003sl.lb
    public final void runTask() {
        String str;
        try {
            if (MapsInitializer.getNetWorkEnable()) {
                cr crVar = this.b;
                if (crVar != null) {
                    cr.a d = crVar.d();
                    if (d == null || d.f947a == null) {
                        str = null;
                    } else {
                        str = a(this.f948a) + "/custom_texture_data";
                        a(str, d.f947a);
                    }
                    a aVar = this.d;
                    if (aVar != null) {
                        aVar.a(str, this.c);
                    }
                }
                iv.a(this.f948a, dv.a());
            }
        } catch (Throwable th) {
            iv.c(th, "CustomStyleTask", "download customStyle");
            th.printStackTrace();
        }
    }

    private static void a(String str, byte[] bArr) {
        FileUtil.writeDatasToFile(str, bArr);
    }

    private static String a(Context context) {
        return FileUtil.getMapBaseStorage(context);
    }

    public final void a() {
        this.f948a = null;
        if (this.b != null) {
            this.b = null;
        }
    }

    public final void b() {
        dt.a().a(this);
    }

    public final void a(a aVar) {
        this.d = aVar;
    }

    public final void a(cy cyVar) {
        this.c = cyVar;
    }
}
