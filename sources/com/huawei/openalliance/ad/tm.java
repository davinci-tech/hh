package com.huawei.openalliance.ad;

import android.content.Context;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.huawei.health.R;
import com.huawei.openalliance.ad.views.dsa.DomesticDsaView;

/* loaded from: classes5.dex */
public class tm extends tj {
    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        ho.b("PPSTransparencyDialog", "onDetachedFromWindow");
        c();
    }

    public void l() {
        if (this.i instanceof DomesticDsaView) {
            ((DomesticDsaView) this.i).a();
        }
    }

    public void k() {
        if (this.i instanceof DomesticDsaView) {
            ((DomesticDsaView) this.i).b();
        }
    }

    public String getScreenOrientation() {
        this.f7534a = com.huawei.openalliance.ad.utils.d.b(this.p);
        this.b = com.huawei.openalliance.ad.utils.d.a(this.p);
        return this.f7534a > this.b ? "2" : "1";
    }

    @Override // com.huawei.openalliance.ad.tj
    protected int getLayoutId() {
        return g() ? R.layout.hiad_transparency_dialog_splash : com.huawei.openalliance.ad.utils.x.t(getContext()) ? R.layout.hiad_transparency_dialog_tv : R.layout.hiad_transparency_dialog;
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x0094  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0099  */
    @Override // com.huawei.openalliance.ad.tj
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    protected void e() {
        /*
            r5 = this;
            boolean r0 = r5.f()
            if (r0 != 0) goto La
            r5.c()
            return
        La:
            android.content.Context r0 = r5.p
            r1 = 1108344832(0x42100000, float:36.0)
            int r0 = com.huawei.openalliance.ad.utils.ao.a(r0, r1)
            java.lang.String r1 = r5.getScreenOrientation()
            boolean r2 = com.huawei.openalliance.ad.utils.dd.c()
            r3 = 1086324736(0x40c00000, float:6.0)
            r4 = 0
            if (r2 == 0) goto L77
            java.lang.String r2 = "1"
            boolean r1 = java.util.Objects.equals(r1, r2)
            if (r1 == 0) goto L30
            int[] r1 = r5.d
            r1 = r1[r4]
            int[] r2 = r5.e
            r2 = r2[r4]
            goto L7f
        L30:
            android.content.Context r1 = r5.p
            int r1 = com.huawei.openalliance.ad.utils.d.r(r1)
            r2 = 1
            if (r1 != r2) goto L53
            int[] r1 = r5.d
            r1 = r1[r4]
            int[] r2 = r5.e
            r2 = r2[r4]
            int r1 = r1 + r2
            android.content.Context r2 = r5.p
            int r2 = com.huawei.openalliance.ad.utils.ao.a(r2, r3)
            int r1 = r1 - r2
            int r2 = r0 / 2
            int r1 = r1 - r2
            android.content.Context r2 = r5.p
            int r2 = com.huawei.openalliance.ad.utils.ao.h(r2)
            goto L89
        L53:
            android.content.Context r1 = r5.p
            int r1 = com.huawei.openalliance.ad.utils.d.r(r1)
            if (r1 != 0) goto L8e
            int[] r1 = r5.d
            r1 = r1[r4]
            int[] r2 = r5.e
            r2 = r2[r4]
            int r1 = r1 + r2
            android.content.Context r2 = r5.p
            int r2 = com.huawei.openalliance.ad.utils.ao.a(r2, r3)
            int r1 = r1 - r2
            int r2 = r0 / 2
            int r1 = r1 - r2
            android.content.Context r2 = r5.p
            int r2 = com.huawei.openalliance.ad.utils.ao.h(r2)
            int r2 = r2 * 2
            goto L89
        L77:
            int[] r1 = r5.d
            r1 = r1[r4]
            int[] r2 = r5.e
            r2 = r2[r4]
        L7f:
            int r1 = r1 + r2
            android.content.Context r2 = r5.p
            int r2 = com.huawei.openalliance.ad.utils.ao.a(r2, r3)
            int r1 = r1 - r2
            int r2 = r0 / 2
        L89:
            int r1 = r1 - r2
            int r4 = r5.a(r0, r1)
        L8e:
            boolean r0 = com.huawei.openalliance.ad.utils.dd.c()
            if (r0 == 0) goto L99
            android.widget.ImageView r0 = r5.o
            int r1 = -r4
            float r1 = (float) r1
            goto L9c
        L99:
            android.widget.ImageView r0 = r5.o
            float r1 = (float) r4
        L9c:
            r0.setX(r1)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.openalliance.ad.tm.e():void");
    }

    @Override // com.huawei.openalliance.ad.tj
    protected void d() {
        this.f = 16.0f;
        super.d();
    }

    public void a(boolean z, Integer num, jb jbVar) {
        if (this.i instanceof DomesticDsaView) {
            ((DomesticDsaView) this.i).a(z, num, jbVar);
        }
    }

    @Override // com.huawei.openalliance.ad.tj
    protected void a() {
        this.c = (RelativeLayout) findViewById(R.id.haid_transparency_dialog_root);
        this.k = findViewById(R.id.margin_view);
        this.l = findViewById(R.id.anchor_view);
        this.g = (com.huawei.openalliance.ad.views.h) findViewById(R.id.top_dsa_view);
        this.m = (ImageView) findViewById(R.id.top_dsa_iv);
        this.h = (com.huawei.openalliance.ad.views.h) findViewById(R.id.bottom_dsa_view);
        this.n = (ImageView) findViewById(R.id.bottom_dsa_iv);
        m();
    }

    /* loaded from: classes9.dex */
    class a implements hi {
        @Override // com.huawei.openalliance.ad.hi
        public void a() {
            tm.this.c();
        }

        private a() {
        }
    }

    public int a(int i, int i2) {
        int i3 = this.j;
        int i4 = (this.f7534a - i3) - i;
        if (i2 < i3) {
            i2 = i3;
        }
        return i2 > i4 ? i4 : i2;
    }

    private void m() {
        a aVar = new a();
        if (this.h instanceof DomesticDsaView) {
            ((DomesticDsaView) this.h).setDsaJumpListener(aVar);
        }
        if (this.g instanceof DomesticDsaView) {
            ((DomesticDsaView) this.g).setDsaJumpListener(aVar);
        }
    }

    public tm(Context context, int[] iArr, int[] iArr2, Boolean bool) {
        super(context, iArr, iArr2, bool);
    }

    public tm(Context context, int[] iArr, int[] iArr2, int i) {
        super(context, iArr, iArr2, i);
    }
}
