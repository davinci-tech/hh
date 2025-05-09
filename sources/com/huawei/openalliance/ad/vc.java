package com.huawei.openalliance.ad;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.widget.ImageView;
import com.huawei.openalliance.ad.utils.az;
import java.lang.ref.WeakReference;

/* loaded from: classes5.dex */
public class vc implements Runnable {

    /* renamed from: a, reason: collision with root package name */
    private String f7768a;
    private WeakReference<ImageView> b;
    private dk c;
    private Context d;
    private Boolean e;
    private az.a f;

    @Override // java.lang.Runnable
    public void run() {
        if (this.d == null) {
            return;
        }
        rt rtVar = new rt();
        rtVar.b(false);
        rtVar.c(true);
        rtVar.a("icon");
        rtVar.c(this.f7768a);
        Boolean bool = this.e;
        if (bool != null && !bool.booleanValue()) {
            rtVar.c(fh.b(this.d).e());
        }
        ru a2 = new rr(this.d, rtVar).a();
        if (a2 == null) {
            return;
        }
        String a3 = a2.a();
        if (TextUtils.isEmpty(a3)) {
            return;
        }
        String c = this.c.c(a3);
        if (TextUtils.isEmpty(c)) {
            return;
        }
        com.huawei.openalliance.ad.utils.az.a(this.d, c, new az.a() { // from class: com.huawei.openalliance.ad.vc.1
            @Override // com.huawei.openalliance.ad.utils.az.a
            public void a(final Drawable drawable) {
                if (drawable == null) {
                    return;
                }
                com.huawei.openalliance.ad.utils.dj.a(new Runnable() { // from class: com.huawei.openalliance.ad.vc.1.1
                    @Override // java.lang.Runnable
                    public void run() {
                        ImageView imageView;
                        if (vc.this.f != null) {
                            vc.this.f.a(drawable);
                        }
                        if (vc.this.b == null || vc.this.b.get() == null || (imageView = (ImageView) vc.this.b.get()) == null) {
                            return;
                        }
                        imageView.setBackground(null);
                        imageView.setImageDrawable(drawable);
                    }
                });
            }

            @Override // com.huawei.openalliance.ad.utils.az.a
            public void a() {
                if (vc.this.f != null) {
                    vc.this.f.a();
                }
            }
        });
    }

    public vc(Context context, String str, ImageView imageView, Boolean bool) {
        this.f7768a = str;
        this.b = new WeakReference<>(imageView);
        context = context != null ? context.getApplicationContext() : context;
        this.d = context;
        this.c = dh.a(context, "normal");
        this.e = bool;
    }
}
