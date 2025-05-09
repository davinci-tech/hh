package com.huawei.featureuserprofile.asset;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import com.apprichtap.haptic.base.PrebakedEffectId;
import com.huawei.featureuserprofile.asset.MyAssetsActivity;
import com.huawei.featureuserprofile.award.ui.MyAwardActivity;
import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hms.support.api.entity.common.CommonConstant;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.threecircle.ActiveTipStringUtils;
import com.huawei.ui.commonui.adapter.BaseRecyclerAdapter;
import com.huawei.ui.commonui.adapter.RecyclerHolder;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.button.HealthButton;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.recycleview.HealthLinearLayoutManager;
import com.huawei.ui.commonui.recycleview.HealthRecycleView;
import defpackage.ixx;
import defpackage.jdw;
import defpackage.koq;
import defpackage.njn;
import defpackage.nrz;
import defpackage.nsf;
import defpackage.nsn;
import health.compact.a.CommonUtil;
import health.compact.a.LanguageUtil;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.StorageParams;
import health.compact.a.Utils;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/* loaded from: classes.dex */
public class MyAssetsActivity extends BaseActivity {
    private Context b;
    private String c;
    private String e;
    private String g;
    private boolean h;
    private HealthButton i;
    private List<d> d = new ArrayList();

    /* renamed from: a, reason: collision with root package name */
    private a f1968a = new a(this.d);

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_my_assets);
        this.b = this;
        c();
        d();
    }

    private void d() {
        HealthButton healthButton = (HealthButton) findViewById(R.id.btn_use_code);
        this.i = healthButton;
        healthButton.setOnClickListener(new View.OnClickListener() { // from class: bqx
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                MyAssetsActivity.this.tD_(view);
            }
        });
        this.i.setVisibility(Utils.o() ? 8 : 0);
        b();
    }

    public /* synthetic */ void tD_(View view) {
        f();
        d(4);
        ViewClickInstrumentation.clickOnView(view);
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        a();
    }

    private void c() {
        Intent intent = getIntent();
        if (intent == null) {
            LogUtil.h("MyAssets", "getData intent is null");
            return;
        }
        this.g = intent.getStringExtra("coupon");
        this.c = intent.getStringExtra("card_case");
        this.e = intent.getStringExtra("award");
        this.h = intent.getBooleanExtra("point", false);
        LogUtil.a("MyAssets", "mCouponTime:", this.g, " mCardCaseTime:", this.c, " mAwardTime:", this.e);
    }

    private void a() {
        LogUtil.a("MyAssets", "enter checkRedPoint");
        if (koq.b(this.d) || this.f1968a == null) {
            ReleaseLogUtil.c("MyAssets", "checkRedPoint with null, return");
            return;
        }
        for (d dVar : this.d) {
            if (dVar != null) {
                int i = dVar.b;
                if (i == R.string._2130845022_res_0x7f021d5e) {
                    dVar.a(!TextUtils.isEmpty(this.g));
                } else if (i == R.string._2130845023_res_0x7f021d5f) {
                    dVar.a(!TextUtils.isEmpty(this.c));
                } else if (i == R.string._2130845024_res_0x7f021d60) {
                    dVar.a(!TextUtils.isEmpty(this.e));
                } else {
                    dVar.a(this.h);
                }
            }
        }
        this.f1968a.a(this.d);
    }

    public void c(int i) {
        if (nsn.o()) {
            LogUtil.a("MyAssets", "click too fast");
            return;
        }
        if (i == R.string._2130845022_res_0x7f021d5e) {
            g();
            njn.a(4, 2);
            return;
        }
        if (i == R.string._2130845023_res_0x7f021d5f) {
            i();
            njn.a(3, 2);
            return;
        }
        if (i == R.string._2130845024_res_0x7f021d60) {
            h();
            Intent intent = new Intent(this.b, (Class<?>) MyAwardActivity.class);
            intent.putExtra("from", "1");
            this.b.startActivity(intent);
            d(3);
            return;
        }
        if (i == R.string._2130846747_res_0x7f02241b) {
            e();
            d(5);
        } else {
            LogUtil.h("MyAssets", "onClick default");
        }
    }

    private void e() {
        Intent intent = new Intent(CommonConstant.ACTION.HWID_SCHEME_URL, Uri.parse("huaweihealth://huaweihealth.app/openwith?address=com.huawei.health.h5.external-task?h5pro=true&urlType=4&pName=com.huawei.health&isImmerse&path=mypoints"));
        intent.addCategory("android.intent.category.DEFAULT").addFlags(268435456);
        jdw.bGh_(intent, BaseApplication.e());
        this.h = false;
    }

    private void f() {
        Intent intent = new Intent(CommonConstant.ACTION.HWID_SCHEME_URL, Uri.parse("huaweihealth://huaweihealth.app/openwith?address=com.huawei.health.h5.vip?isImmerse&path=MembersExchange&pName=com.huawei.health&h5pro=true&from=2"));
        intent.addCategory("android.intent.category.DEFAULT").addFlags(268435456);
        jdw.bGh_(intent, getApplicationContext());
    }

    private static void d(int i) {
        HashMap hashMap = new HashMap();
        hashMap.put("click", 1);
        hashMap.put("type", Integer.valueOf(i));
        hashMap.put("activity", 2);
        ixx.d().d(BaseApplication.e(), AnalyticsValue.MY_ASSET_ENTRANCE_2120121.value(), hashMap, 0);
    }

    private void g() {
        if (TextUtils.isEmpty(this.g)) {
            return;
        }
        String accountInfo = LoginInit.getInstance(com.huawei.hwcommonmodel.application.BaseApplication.getContext()).getAccountInfo(1011);
        SharedPreferenceManager.e(com.huawei.hwcommonmodel.application.BaseApplication.getContext(), Integer.toString(PrebakedEffectId.RT_VICTORY), accountInfo + "key_coupon_time", this.g, new StorageParams());
        this.g = "";
    }

    private void i() {
        if (TextUtils.isEmpty(this.c)) {
            return;
        }
        String accountInfo = LoginInit.getInstance(com.huawei.hwcommonmodel.application.BaseApplication.getContext()).getAccountInfo(1011);
        SharedPreferenceManager.e(com.huawei.hwcommonmodel.application.BaseApplication.getContext(), Integer.toString(PrebakedEffectId.RT_VICTORY), accountInfo + "key_card_time", this.c, new StorageParams());
        this.c = "";
    }

    private void h() {
        if (TextUtils.isEmpty(this.e)) {
            return;
        }
        long g = CommonUtil.g(this.e);
        String accountInfo = LoginInit.getInstance(com.huawei.hwcommonmodel.application.BaseApplication.getContext()).getAccountInfo(1011);
        SharedPreferenceManager.e(Integer.toString(PrebakedEffectId.RT_VICTORY), accountInfo + "key_award_time", g);
        this.e = "";
    }

    private void b() {
        HealthRecycleView healthRecycleView = (HealthRecycleView) findViewById(R.id.assets_recycle);
        int m = CommonUtil.m(com.huawei.hwcommonmodel.application.BaseApplication.getContext(), LoginInit.getInstance(com.huawei.hwcommonmodel.application.BaseApplication.getContext()).getAccountInfo(1009));
        if (m == 7 || m == 1) {
            this.d.add(new d(R.string._2130845022_res_0x7f021d5e, R.drawable._2131429891_res_0x7f0b0a03, !TextUtils.isEmpty(this.g)));
            this.d.add(new d(R.string._2130845023_res_0x7f021d5f, R.drawable._2131429850_res_0x7f0b09da, !TextUtils.isEmpty(this.c)));
        }
        this.d.add(new d(R.string._2130845024_res_0x7f021d60, R.drawable._2131430266_res_0x7f0b0b7a, !TextUtils.isEmpty(this.e)));
        if (ActiveTipStringUtils.b()) {
            this.d.add(new d(R.string._2130846747_res_0x7f02241b, R.drawable._2131430255_res_0x7f0b0b6f, this.h));
        }
        this.f1968a.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener() { // from class: bqw
            @Override // com.huawei.ui.commonui.adapter.BaseRecyclerAdapter.OnItemClickListener
            public final void onItemClicked(RecyclerHolder recyclerHolder, int i, Object obj) {
                MyAssetsActivity.this.a(recyclerHolder, i, obj);
            }
        });
        healthRecycleView.setAdapter(this.f1968a);
        healthRecycleView.setLayoutManager(new HealthLinearLayoutManager(this));
        this.f1968a.a(this.d);
    }

    public /* synthetic */ void a(RecyclerHolder recyclerHolder, int i, Object obj) {
        if (obj instanceof d) {
            c(((d) obj).b);
        }
    }

    /* loaded from: classes7.dex */
    static class a extends BaseRecyclerAdapter<d> {
        private List<d> d;

        public a(List<d> list) {
            super(list, R.layout.activity_my_assets_item);
            this.d = new ArrayList();
        }

        @Override // com.huawei.ui.commonui.adapter.BaseRecyclerAdapter
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public void convert(RecyclerHolder recyclerHolder, int i, d dVar) {
            LogUtil.a("MyAssets", "convert holder ", recyclerHolder, " itemData ", dVar, " position ", Integer.valueOf(i));
            if (recyclerHolder == null || dVar == null || koq.b(this.d)) {
                return;
            }
            if (i == this.d.size() - 1) {
                recyclerHolder.cwK_(R.id.assets_divider).setVisibility(8);
            } else {
                recyclerHolder.cwK_(R.id.assets_divider).setVisibility(0);
            }
            ((HealthTextView) recyclerHolder.cwK_(R.id.assets_text)).setText(dVar.b);
            recyclerHolder.cwK_(R.id.assets_red_point).setVisibility(dVar.d ? 0 : 8);
            Drawable cKq_ = nsf.cKq_(dVar.f1969a);
            if (LanguageUtil.bc(com.huawei.hwcommonmodel.application.BaseApplication.getContext())) {
                ((ImageView) recyclerHolder.cwK_(R.id.assets_arrow)).setBackgroundResource(R.drawable._2131427841_res_0x7f0b0201);
                cKq_ = nrz.cKm_(com.huawei.hwcommonmodel.application.BaseApplication.getContext(), cKq_);
            }
            ((ImageView) recyclerHolder.cwK_(R.id.assets_icon)).setImageDrawable(cKq_);
        }

        public void a(List<d> list) {
            this.d = list;
            refreshDataChange(list);
        }
    }

    /* loaded from: classes7.dex */
    static class d {

        /* renamed from: a, reason: collision with root package name */
        private final int f1969a;
        private final int b;
        private boolean d;

        d(int i, int i2, boolean z) {
            this.b = i;
            this.f1969a = i2;
            this.d = z;
        }

        public void a(boolean z) {
            this.d = z;
        }
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }
}
