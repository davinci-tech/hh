package com.huawei.hms.scankit;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import com.huawei.health.R;
import com.huawei.hms.ml.scan.HmsScan;
import com.huawei.hms.scankit.aiscan.common.BarcodeFormat;
import com.huawei.hms.scankit.p.e5;
import com.huawei.hms.scankit.p.f5;
import com.huawei.hms.scankit.p.i8;
import com.huawei.hms.scankit.p.j0;
import com.huawei.hms.scankit.p.l1;
import com.huawei.hms.scankit.p.o4;
import com.huawei.hms.scankit.p.u6;
import com.huawei.hms.scankit.p.v5;
import com.huawei.hms.scankit.p.v6;
import java.util.Collection;
import java.util.Map;

/* loaded from: classes9.dex */
public class a extends Handler implements v6 {

    /* renamed from: a, reason: collision with root package name */
    private final f5 f5700a;
    private d b;
    private final int c;
    private EnumC0150a d;
    private Context e;
    private final j0 f;
    private final ViewfinderView g;
    private boolean h;
    private boolean i;
    private boolean j;
    private e5 k;
    private boolean l;

    /* renamed from: com.huawei.hms.scankit.a$a, reason: collision with other inner class name */
    enum EnumC0150a {
        PREVIEW,
        SUCCESS,
        DONE
    }

    a(Context context, ViewfinderView viewfinderView, f5 f5Var, Collection<BarcodeFormat> collection, Map<l1, ?> map, String str, j0 j0Var, Rect rect, int i, boolean z, boolean z2) {
        this.g = viewfinderView;
        this.f5700a = f5Var;
        this.c = i;
        this.e = context;
        d dVar = new d(context, j0Var, this, collection, map, str, this);
        this.b = dVar;
        dVar.a(rect);
        this.b.a(z2);
        this.b.start();
        this.l = z;
        j0Var.a(new j(this.b));
        this.d = EnumC0150a.SUCCESS;
        this.f = j0Var;
        j0Var.p();
        o4.a("scan-time", "start preview time:" + System.currentTimeMillis());
        f();
        v5.a(null);
    }

    public void a(e5 e5Var) {
        this.k = e5Var;
    }

    public int b() {
        return this.c;
    }

    public void c(boolean z) {
        this.h = z;
    }

    public boolean d() {
        return this.j;
    }

    public void e() {
        this.d = EnumC0150a.DONE;
        this.f.q();
        Message.obtain(this.b.a(), R.id.scankit_quit).sendToTarget();
        try {
            this.b.b();
            this.b.join(50L);
        } catch (InterruptedException unused) {
            o4.e("CaptureHandler", "quitSynchronously   wait interrupt");
        }
        this.b = null;
        removeMessages(R.id.scankit_decode_succeeded);
        removeMessages(R.id.scankit_decode_failed);
    }

    public void f() {
        if (this.d == EnumC0150a.SUCCESS) {
            this.d = EnumC0150a.PREVIEW;
            this.f.o();
        }
    }

    @Override // android.os.Handler
    public void handleMessage(Message message) {
        HmsScan hmsScan;
        int i = message.what;
        if (i == R.id.scankit_restart_preview) {
            f();
            return;
        }
        if (i != R.id.scankit_decode_succeeded) {
            if (i == R.id.scankit_decode_failed) {
                this.d = EnumC0150a.PREVIEW;
                this.f.o();
                return;
            }
            return;
        }
        this.d = EnumC0150a.SUCCESS;
        Object obj = message.obj;
        if (obj instanceof HmsScan[]) {
            HmsScan[] hmsScanArr = (HmsScan[]) obj;
            if (hmsScanArr.length <= 0 || (hmsScan = hmsScanArr[0]) == null || TextUtils.isEmpty(hmsScan.originalValue)) {
                o4.d("CaptureHandler", "retrieve  HmsScan lenth is 0");
            } else {
                o4.d("CaptureHandler", "scan successful");
                Bitmap bitmap = null;
                float f = 0.0f;
                if (this.i) {
                    o4.d("CaptureHandler", "scan successful & return bitmap");
                    Bundle data = message.getData();
                    if (data != null) {
                        byte[] byteArray = data.getByteArray("barcode_bitmap");
                        f = data.getFloat("barcode_scaled_factor", 0.0f);
                        if (byteArray != null && byteArray.length > 0) {
                            bitmap = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
                        }
                    }
                    this.f5700a.a(hmsScanArr, bitmap, f);
                } else {
                    this.f5700a.a(hmsScanArr, null, 0.0f);
                }
                if (!this.l) {
                    return;
                }
            }
            f();
        }
    }

    private u6 b(u6 u6Var) {
        float b;
        float c;
        int max;
        Point b2 = i8.b(this.e);
        Point e = this.f.e();
        int i = b2.x;
        int i2 = b2.y;
        if (i < i2) {
            b = (u6Var.b() * ((i * 1.0f) / e.y)) - (Math.max(b2.x, e.y) / 2.0f);
            c = u6Var.c() * ((i2 * 1.0f) / e.x);
            max = Math.min(b2.y, e.x);
        } else {
            b = (u6Var.b() * ((i * 1.0f) / e.x)) - (Math.min(b2.y, e.y) / 2.0f);
            c = u6Var.c() * ((i2 * 1.0f) / e.y);
            max = Math.max(b2.x, e.x);
        }
        return new u6(b, c - (max / 2.0f));
    }

    public boolean a() {
        e5 e5Var = this.k;
        if (e5Var != null) {
            return e5Var.a();
        }
        return false;
    }

    public boolean c() {
        return this.i;
    }

    @Override // com.huawei.hms.scankit.p.v6
    public void a(u6 u6Var) {
        if (this.g != null) {
            this.g.a(b(u6Var));
        }
    }

    public void a(boolean z) {
        this.i = z;
    }

    public void b(boolean z) {
        this.j = z;
    }
}
