package com.huawei.openalliance.ad.activity;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.openalliance.ad.constant.MapKeyNames;
import com.huawei.openalliance.ad.db.bean.ContentRecord;
import com.huawei.openalliance.ad.hi;
import com.huawei.openalliance.ad.hk;
import com.huawei.openalliance.ad.ho;
import com.huawei.openalliance.ad.utils.ao;
import com.huawei.openalliance.ad.utils.dd;
import com.huawei.openalliance.ad.views.dsa.DomesticDsaView;
import com.huawei.openalliance.ad.views.h;

/* loaded from: classes9.dex */
public class DomesticDsaActivity extends com.huawei.openalliance.ad.activity.a {
    private final ContentRecord q = new ContentRecord();
    private String r;

    @Override // com.huawei.openalliance.ad.activity.a
    protected int d() {
        return R.layout.hiad_activity_dsa;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.huawei.openalliance.ad.activity.a, com.huawei.openalliance.ad.activity.e, com.huawei.openalliance.ad.activity.f, android.app.Activity
    public void onCreate(Bundle bundle) {
        ho.a("DomesticDsaActivity", "onCreate");
        super.onCreate(bundle);
        m();
    }

    @Override // com.huawei.openalliance.ad.activity.a, com.huawei.openalliance.ad.activity.f, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }

    @Override // com.huawei.openalliance.ad.activity.a
    protected boolean i() {
        this.r = new SafeIntent(getIntent()).getStringExtra(MapKeyNames.DSA_URL);
        this.q.e(true);
        this.q.S(this.r);
        return super.i();
    }

    @Override // com.huawei.openalliance.ad.activity.a
    protected void g() {
        ImageView imageView;
        float f;
        int a2 = ao.a(this, 36.0f);
        int i = this.d;
        int i2 = (this.f6613a - i) - a2;
        ho.a("DomesticDsaActivity", "mAnchorViewLoc: %s, mAnchorViewSize: %s", Integer.valueOf(this.h[0]), Integer.valueOf(this.i[0]));
        int a3 = ((this.h[0] + this.i[0]) - ao.a(this, 6.0f)) - (a2 / 2);
        if (a3 >= i) {
            i = a3;
        }
        if (i <= i2) {
            i2 = i;
        }
        if (dd.c()) {
            imageView = this.o;
            f = -i2;
        } else {
            imageView = this.o;
            f = i2;
        }
        imageView.setX(f);
    }

    @Override // com.huawei.openalliance.ad.activity.a, com.huawei.openalliance.ad.activity.f, android.app.Activity
    public void finish() {
        ho.a("DomesticDsaActivity", "finish");
        super.finish();
    }

    @Override // com.huawei.openalliance.ad.activity.a
    public void e() {
        f();
        this.l.a(this.h, this.i);
        this.l.setAdContent(this.q);
    }

    class a implements hi {
        @Override // com.huawei.openalliance.ad.hi
        public void a() {
            DomesticDsaActivity.this.finish();
        }

        private a() {
        }
    }

    @Override // com.huawei.openalliance.ad.activity.a
    protected void c() {
        this.e = (RelativeLayout) findViewById(R.id.dsa_activity_root);
        this.f = findViewById(R.id.margin_view);
        this.g = findViewById(R.id.dsa_anchor_view);
        this.j = (h) findViewById(R.id.top_dsa_view);
        this.m = (ImageView) findViewById(R.id.top_dsa_iv);
        this.k = (h) findViewById(R.id.bottom_dsa_view);
        this.n = (ImageView) findViewById(R.id.bottom_dsa_iv);
        l();
    }

    private void m() {
        if (this.e == null || this.j == null || this.k == null) {
            return;
        }
        this.e.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.openalliance.ad.activity.DomesticDsaActivity.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                DomesticDsaActivity.this.finish();
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        this.j.setViewClickListener(new hk() { // from class: com.huawei.openalliance.ad.activity.DomesticDsaActivity.2
            @Override // com.huawei.openalliance.ad.hk
            public void a() {
                DomesticDsaActivity.this.finish();
            }
        });
        this.k.setViewClickListener(new hk() { // from class: com.huawei.openalliance.ad.activity.DomesticDsaActivity.3
            @Override // com.huawei.openalliance.ad.hk
            public void a() {
                DomesticDsaActivity.this.finish();
            }
        });
    }

    private void l() {
        if (this.j instanceof DomesticDsaView) {
            ((DomesticDsaView) this.j).setDsaJumpListener(new a());
        }
        if (this.k instanceof DomesticDsaView) {
            ((DomesticDsaView) this.k).setDsaJumpListener(new a());
        }
    }
}
