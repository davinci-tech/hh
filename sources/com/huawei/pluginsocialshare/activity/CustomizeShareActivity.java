package com.huawei.pluginsocialshare.activity;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.GridLayoutManager;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.R;
import com.huawei.health.socialshare.model.EditShareCommonView;
import com.huawei.health.socialshare.model.socialsharebean.ShareDataInfo;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.operation.ble.BleConstants;
import com.huawei.pluginsocialshare.activity.CustomizeShareActivity;
import com.huawei.pluginsocialshare.adapter.EditShareAdapter;
import com.huawei.pluginsocialshare.view.ShareButtonView;
import com.huawei.pluginsocialshare.view.ShareRecycleScrollItemView;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.recycleview.HealthRecycleView;
import com.huawei.ui.commonui.subheader.HealthSubHeader;
import com.huawei.ui.commonui.switchbutton.HealthSwitchButton;
import com.huawei.ui.commonui.titlebar.CustomTitleBar;
import com.huawei.ui.commonui.view.ShareSquareLayout;
import defpackage.fdu;
import defpackage.fea;
import defpackage.feb;
import defpackage.feh;
import defpackage.jdv;
import defpackage.koq;
import defpackage.mto;
import defpackage.mud;
import defpackage.mus;
import defpackage.mva;
import defpackage.mvl;
import defpackage.mvp;
import defpackage.mwa;
import defpackage.mwd;
import defpackage.mwe;
import defpackage.nrf;
import defpackage.nrh;
import defpackage.nsn;
import health.compact.a.CommonUtil;
import health.compact.a.EnvironmentInfo;
import health.compact.a.LanguageUtil;
import health.compact.a.Utils;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: classes.dex */
public class CustomizeShareActivity extends BaseActivity implements EditShareAdapter.OnBackgroundChangeListener, DownloadInterface {
    private static final int[] c = {R.drawable.share_geometry_1, R.drawable.share_geometry_2, R.drawable.share_geometry_3};

    /* renamed from: a, reason: collision with root package name */
    private EditShareAdapter f8513a;
    private String ab;
    private feh ad;
    private HealthSubHeader ae;
    private CustomTitleBar af;
    private EditShareAdapter ag;
    private HealthSwitchButton ah;
    private String aj;
    private HealthRecycleView an;
    private Bitmap b;
    private boolean e;
    private FrameLayout i;
    private CustomizeShareActivity j;
    private RelativeLayout m;
    private TextView n;
    private int o;
    private View p;
    private boolean q;
    private ShareSquareLayout r;
    private List<feb> s;
    private ShareButtonView u;
    private fdu v;
    private HealthRecycleView x;
    private View y;
    private mva z;
    private List<Integer> l = new ArrayList();
    private List<ShareDataInfo> f = new ArrayList(8);
    private String ai = "id_type";
    private double aa = 4.0d;
    private int h = 1;
    private int am = 0;
    private List<ShareDataInfo> g = new ArrayList();
    private List<Integer> k = new ArrayList();
    private List<EditShareCommonView> w = new ArrayList();
    private Map<String, Object> ac = new HashMap(10);
    private boolean d = true;
    private boolean t = false;

    @Override // com.huawei.pluginsocialshare.activity.DownloadInterface
    public void notifyBackgroudChanged(fea feaVar) {
    }

    @Override // com.huawei.pluginsocialshare.activity.DownloadInterface
    public void notifyBackgroudFail() {
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        overridePendingTransition(R.anim._2130772057_res_0x7f010059, R.anim._2130772061_res_0x7f01005d);
        super.onCreate(bundle);
        setContentView(R.layout.activity_step_detail_share);
        cancelAdaptRingRegion();
        this.j = this;
        if (!LoginInit.getInstance(BaseApplication.getContext()).getIsLogined()) {
            finish();
            return;
        }
        c();
        l();
        t();
        n();
        q();
        p();
    }

    private void q() {
        this.u = (ShareButtonView) findViewById(R.id.share_button_view);
        fdu fduVar = new fdu(1);
        this.v = fduVar;
        fduVar.b(4);
        this.v.i(false);
        this.v.b(this.ab);
        int i = this.o;
        if (i == 10002) {
            this.ac.put("event", "1");
            this.ac.put("shareStyle", "customized_sharing");
        } else {
            this.ac.put(BleConstants.SPORT_TYPE, Integer.valueOf(i));
        }
        this.v.b(this.ac);
        this.u.setShareContent(this.v);
        this.u.setPerViewImage(this.r);
        this.u.setLogLayout(this.p);
    }

    private void e(fea feaVar) {
        if (feaVar == null || koq.b(feaVar.a())) {
            o();
        } else {
            refreshDataMark(feaVar.a());
        }
    }

    private void o() {
        this.g.clear();
        for (EditShareCommonView editShareCommonView : this.w) {
            if (editShareCommonView != null && !editShareCommonView.getIsNeedHide()) {
                mvp mvpVar = new mvp();
                mvpVar.a(editShareCommonView.getBitmap());
                mvpVar.setId(editShareCommonView.getWatermarkId());
                this.g.add(mvpVar);
            }
        }
    }

    private void l() {
        for (int i : c) {
            this.l.add(Integer.valueOf(i));
        }
    }

    private void c(fea feaVar) {
        if (feaVar == null || koq.b(feaVar.d())) {
            this.ai = "id_type";
            d(this.l);
        } else {
            this.ai = "path_type";
            a(feaVar.d());
        }
    }

    private void c() {
        this.i = (FrameLayout) findViewById(R.id.hw_health_edit_share_activity_show);
        this.y = findViewById(R.id.share_watermark_icon);
        this.p = findViewById(R.id.logo_layout);
        this.n = (TextView) findViewById(R.id.share_watermark_text);
        this.af = (CustomTitleBar) findViewById(R.id.hw_health_edit_share_titlebar);
        this.r = (ShareSquareLayout) findViewById(R.id.hw_health_edit_share_show);
        this.an = (HealthRecycleView) findViewById(R.id.rv_watermark);
        HealthSubHeader healthSubHeader = (HealthSubHeader) findViewById(R.id.sub_watermark_header);
        this.ae = healthSubHeader;
        healthSubHeader.setSubHeaderBackgroundColor(0);
        ((HealthSubHeader) findViewById(R.id.sub_header)).setSubHeaderBackgroundColor(0);
        c(false);
        RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.download_error_layout);
        this.m = relativeLayout;
        relativeLayout.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.pluginsocialshare.activity.CustomizeShareActivity.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                CustomizeShareActivity.this.m.setVisibility(8);
                CustomizeShareActivity.this.i();
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        setViewSafeRegion(false, (LinearLayout) findViewById(R.id.multiple_source_choose));
        f();
        a();
        r();
        mwd.cqK_(this.r, this.j, false);
    }

    private void p() {
        if (CommonUtil.bv() || CommonUtil.as()) {
            return;
        }
        ((RelativeLayout) findViewById(R.id.layout_share_userinfo)).setVisibility(0);
        HealthSubHeader healthSubHeader = (HealthSubHeader) findViewById(R.id.sub_header_userinfo);
        healthSubHeader.setVisibility(0);
        healthSubHeader.setSubHeaderBackgroundColor(0);
        HealthSwitchButton healthSwitchButton = (HealthSwitchButton) findViewById(R.id.share_userinfo_switch);
        this.ah = healthSwitchButton;
        healthSwitchButton.setChecked(mwd.i());
        this.ah.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.huawei.pluginsocialshare.activity.CustomizeShareActivity$$ExternalSyntheticLambda3
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                CustomizeShareActivity.this.cpa_(compoundButton, z);
            }
        });
    }

    /* synthetic */ void cpa_(CompoundButton compoundButton, boolean z) {
        if (this.e) {
            LogUtil.a("Share_StepDetailShareActivity", "just update status");
            this.e = false;
            a();
            ViewClickInstrumentation.clickOnView(compoundButton);
            return;
        }
        if (z) {
            mwd.e(true);
            a();
            ViewClickInstrumentation.clickOnView(compoundButton);
        } else {
            mwd.cqJ_(this.j, R.string._2130847207_res_0x7f0225e7, R.string._2130847201_res_0x7f0225e1, new View.OnClickListener() { // from class: mtt
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    CustomizeShareActivity.this.coY_(view);
                }
            }, new View.OnClickListener() { // from class: mtv
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    CustomizeShareActivity.this.coZ_(view);
                }
            });
            ViewClickInstrumentation.clickOnView(compoundButton);
        }
    }

    public /* synthetic */ void coY_(View view) {
        mwd.e(false);
        a();
        ViewClickInstrumentation.clickOnView(view);
    }

    public /* synthetic */ void coZ_(View view) {
        this.ah.setChecked(true);
        ViewClickInstrumentation.clickOnView(view);
    }

    private void f() {
        File g;
        Intent intent = getIntent();
        if (intent == null) {
            ReleaseLogUtil.d("Share_StepDetailShareActivity", "getIntentData intent is null");
            return;
        }
        if (!(intent.getSerializableExtra("shareWaterMarkData") instanceof feh) || !(intent.getSerializableExtra("waterMarkIds") instanceof List)) {
            LogUtil.h("Share_StepDetailShareActivity", "addCardBottomView, input error");
            finish();
            return;
        }
        this.ad = (feh) intent.getSerializableExtra("shareWaterMarkData");
        int intExtra = intent.getIntExtra("downLoadId", 999);
        this.o = intExtra;
        if (intExtra == 10002 && ((g = g()) == null || !g.exists())) {
            LogUtil.h("Share_StepDetailShareActivity", "getWatermarkThumbnail no image");
            finish();
            return;
        }
        this.d = intent.getBooleanExtra("isDownloadMarkFromCloud", false);
        this.aj = intent.getStringExtra("shareSource");
        String stringExtra = intent.getStringExtra("shareModuleId");
        this.ab = stringExtra;
        if (TextUtils.isEmpty(stringExtra)) {
            this.ab = AnalyticsValue.HEALTH_SHARE_STEP_DAILY_SHARE_2100001.value();
        }
        LogUtil.a("Share_StepDetailShareActivity", "getIntentData mShareModuleId: ", this.ab);
        this.s = (List) intent.getSerializableExtra("waterMarkIds");
    }

    private void a() {
        if (this.ad == null || this.s == null) {
            LogUtil.h("Share_StepDetailShareActivity", "addCardBottomView, input is null");
            finish();
            return;
        }
        mwe mweVar = new mwe(this.ad, this.j);
        mweVar.setDoMainColor(-1);
        mweVar.setTopWidgetColor(-1);
        mweVar.setWidgetColor(-1);
        mweVar.constructLocalDefaultWatermarkViewList(this.s);
        List<EditShareCommonView> editShareCommonViewList = mweVar.getEditShareCommonViewList();
        this.w = editShareCommonViewList;
        if (koq.b(editShareCommonViewList)) {
            return;
        }
        if (!this.d) {
            this.ae.setVisibility(8);
            this.an.setVisibility(8);
        } else {
            this.ae.setVisibility(0);
            this.an.setVisibility(0);
        }
        EditShareCommonView editShareCommonView = this.w.get(0);
        editShareCommonView.refreshUi(-1, -1);
        editShareCommonView.refreshTopUi(-1);
        View view = editShareCommonView.getView();
        if (view != null) {
            coQ_(view);
        }
    }

    private void coQ_(View view) {
        this.i.removeAllViews();
        this.i.removeAllViewsInLayout();
        ViewParent parent = view.getParent();
        if (parent instanceof ViewGroup) {
            ((ViewGroup) parent).removeView(view);
        }
        this.i.addView(view);
    }

    @Override // com.huawei.pluginsocialshare.activity.DownloadInterface
    public void refreshDataMark(List<ShareDataInfo> list) {
        mwe mweVar = new mwe(this.ad, this.j);
        mweVar.constructDownloadWatermarkViewList(list);
        mweVar.setDoMainColor(-1);
        mweVar.setTopWidgetColor(-1);
        mweVar.setWidgetColor(-1);
        this.w.clear();
        for (EditShareCommonView editShareCommonView : mweVar.getEditShareCommonViewList()) {
            if (editShareCommonView != null) {
                if (editShareCommonView.getIsNeedHide()) {
                    this.k.add(Integer.valueOf(editShareCommonView.getWatermarkId()));
                } else {
                    this.w.add(editShareCommonView);
                    this.ac.put("dataID", Integer.valueOf(editShareCommonView.getWatermarkId()));
                }
            }
        }
        this.g.clear();
        e(list, this.k);
        this.g.addAll(list);
    }

    private void e(List<ShareDataInfo> list, List<Integer> list2) {
        Iterator<ShareDataInfo> it = list.iterator();
        while (it.hasNext()) {
            ShareDataInfo next = it.next();
            if (next != null && list2.contains(Integer.valueOf(next.getId()))) {
                it.remove();
            }
        }
    }

    @Override // com.huawei.pluginsocialshare.activity.DownloadInterface
    public void refreshShareLayoutNoRecommend() {
        boolean z = EnvironmentInfo.k() || !(EnvironmentInfo.k() || this.h == 0);
        if (koq.c(this.f) && z && koq.d(this.f, this.h)) {
            if ("path_type".equals(this.ai)) {
                BitmapFactory.Options options = new BitmapFactory.Options();
                options.inPreferredConfig = Bitmap.Config.ARGB_8888;
                this.b = coT_(this.f.get(this.h).getPath(), options);
            } else if (this.f.get(this.h) instanceof mvp) {
                this.b = BitmapFactory.decodeResource(this.j.getResources(), ((mvp) this.f.get(this.h)).e());
            }
        }
        Bitmap bitmap = this.b;
        if (bitmap != null) {
            this.r.setBackground(nrf.cHq_(bitmap));
        }
        if (koq.d(this.f, this.h)) {
            this.ac.put("backgroundID", Integer.valueOf(this.f.get(this.h).getId()));
        }
    }

    private Bitmap coT_(String str, BitmapFactory.Options options) {
        Bitmap cHC_ = nrf.cHC_(str, options);
        if (cHC_ != null) {
            return cHC_;
        }
        LogUtil.a("Share_StepDetailShareActivity", "decodeImageFromFile RGB_565");
        options.inPreferredConfig = Bitmap.Config.RGB_565;
        return nrf.cHC_(str, options);
    }

    private void n() {
        GridLayoutManager gridLayoutManager;
        this.x = (HealthRecycleView) findViewById(R.id.rv_backgroud);
        if ((nsn.ag(this.j) && this.f.size() > 15) || (!nsn.ag(this.j) && this.f.size() > 9)) {
            gridLayoutManager = new GridLayoutManager((Context) this.j, 2, 0, false);
        } else {
            gridLayoutManager = new GridLayoutManager((Context) this.j, 1, 0, false);
        }
        this.x.setLayoutManager(gridLayoutManager);
        EditShareAdapter editShareAdapter = new EditShareAdapter(this, this.x, this.h, this, 1);
        this.f8513a = editShareAdapter;
        editShareAdapter.c(this.f, this.h, false);
        this.x.setAdapter(this.f8513a);
        this.an.setLayoutManager(new GridLayoutManager((Context) this.j, 1, 0, false));
        EditShareAdapter editShareAdapter2 = new EditShareAdapter(this, this.an, this.am, this, 2);
        this.ag = editShareAdapter2;
        editShareAdapter2.c(this.g, this.am, false);
        this.an.setAdapter(this.ag);
    }

    private void r() {
        this.af.setLeftButtonVisibility(0);
        CustomizeShareActivity customizeShareActivity = this.j;
        if (customizeShareActivity != null) {
            this.af.setTitleBarBackgroundColor(customizeShareActivity.getResources().getColor(R.color._2131296657_res_0x7f090191));
        }
        this.af.setLeftButtonOnClickListener(new View.OnClickListener() { // from class: mts
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                CustomizeShareActivity.this.coX_(view);
            }
        });
        s();
    }

    public /* synthetic */ void coX_(View view) {
        finish();
        overridePendingTransition(0, R.anim._2130772079_res_0x7f01006f);
        ViewClickInstrumentation.clickOnView(view);
    }

    private void s() {
        if ("plan".equals(this.aj) || "threeCircle".equals(this.aj) || String.valueOf(16).equals(this.aj)) {
            this.af.setRightTextContent(getResources().getString(R.string._2130841395_res_0x7f020f33));
            this.af.setRightTextVisible(0);
            this.af.setRightTextOnClickListener(new View.OnClickListener() { // from class: mtr
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    CustomizeShareActivity.this.cpb_(view);
                }
            });
        }
    }

    public /* synthetic */ void cpb_(View view) {
        mto.d(j());
        startActivity(new Intent(this.j, (Class<?>) CustomPreviewActivity.class));
        ViewClickInstrumentation.clickOnView(view);
    }

    private fdu j() {
        fdu fduVar = (fdu) this.v.clone();
        Bitmap bGg_ = jdv.bGg_(this.r);
        if (bGg_ == null) {
            return fduVar;
        }
        Bitmap cJj_ = nrf.cJj_(bGg_, 0, mwd.cqx_(this.p), bGg_.getHeight());
        LogUtil.a("Share_StepDetailShareActivity", "onClick: screenCut: ", cJj_);
        if (cJj_ == null) {
            LogUtil.h("Share_StepDetailShareActivity", "onClick: screenCut is null!");
            return fduVar;
        }
        int cqn_ = mwd.cqn_(cJj_);
        LogUtil.a("Share_StepDetailShareActivity", "initView all medal share bitmap size =", Integer.valueOf(cqn_));
        if ((cqn_ / 1024) / 1024 >= 1) {
            String cqB_ = mwd.cqB_(this.j, "EditShareActivity_Share.webp", cJj_);
            fduVar.d(cqB_);
            fduVar.a(4);
            LogUtil.a("Share_StepDetailShareActivity", "onClick:saved= ", cqB_);
        } else {
            fduVar.awp_(cJj_);
            fduVar.a(1);
        }
        return fduVar;
    }

    private void t() {
        if (CommonUtil.bu() || Utils.l()) {
            k();
            return;
        }
        this.q = Utils.o();
        if (CommonUtil.aa(this.j) && ((!this.q && LanguageUtil.m(this.j)) || this.q)) {
            this.ai = "path_type";
            m();
            i();
        } else if (!CommonUtil.aa(this.j) && ((!this.q && LanguageUtil.m(this.j)) || this.q)) {
            nrh.b(BaseApplication.getContext(), R.string._2130841392_res_0x7f020f30);
            m();
        } else {
            k();
        }
    }

    private void m() {
        fea c2 = mvl.b().c(this.o);
        c(c2);
        if (this.d) {
            e(c2);
        }
    }

    private void a(List<ShareDataInfo> list) {
        this.f.clear();
        b();
        this.f.addAll(list);
    }

    private void b() {
        if (EnvironmentInfo.k()) {
            return;
        }
        mvp mvpVar = new mvp();
        mvpVar.a(R.mipmap.hw_health_edit_share_photo_pic);
        mvpVar.setId(-1);
        this.f.add(0, mvpVar);
    }

    private void k() {
        this.ai = "id_type";
        d(this.l);
        if (this.d) {
            o();
        }
    }

    private void d(List<Integer> list) {
        this.f.clear();
        b();
        Iterator<Integer> it = list.iterator();
        while (it.hasNext()) {
            int intValue = it.next().intValue();
            mvp mvpVar = new mvp();
            mvpVar.a(intValue);
            Integer num = mus.c.get(Integer.valueOf(intValue));
            if (num != null) {
                mvpVar.setId(num.intValue());
            }
            this.f.add(mvpVar);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void i() {
        mva mvaVar = new mva(this);
        this.z = mvaVar;
        mvaVar.cqf_(true, this.j, this.o);
    }

    @Override // com.huawei.pluginsocialshare.activity.DownloadInterface
    public void notifyShareDataChanged(fea feaVar) {
        LogUtil.a("Share_StepDetailShareActivity", "notifyShareDataChanged");
        this.ai = "path_type";
        a(feaVar);
        b(feaVar);
        updateAllFragment(true);
    }

    private void b(fea feaVar) {
        List<ShareDataInfo> e = mwd.e(feaVar.a());
        mwd.b(e);
        refreshDataMark(e);
    }

    @Override // com.huawei.pluginsocialshare.activity.DownloadInterface
    public void notifyDownloadDataFail() {
        m();
        updateAllFragment(true);
    }

    @Override // com.huawei.pluginsocialshare.activity.DownloadInterface
    public void updateAllFragment(boolean z) {
        LogUtil.a("Share_StepDetailShareActivity", "updateAllFragment enter");
        EditShareAdapter editShareAdapter = this.f8513a;
        if (editShareAdapter != null) {
            editShareAdapter.c(this.f, this.h, false);
        }
        EditShareAdapter editShareAdapter2 = this.ag;
        if (editShareAdapter2 == null || !this.d) {
            return;
        }
        editShareAdapter2.c(this.g, this.am, false);
        d(this.am);
    }

    private void a(fea feaVar) {
        List<ShareDataInfo> e = mwd.e(feaVar.d());
        mwd.b(e);
        this.f.clear();
        b();
        this.f.addAll(e);
        if (this.x.getLayoutManager() instanceof GridLayoutManager) {
            if ((nsn.ag(this.j) && this.f.size() > 15) || (!nsn.ag(this.j) && this.f.size() > 9)) {
                ((GridLayoutManager) this.x.getLayoutManager()).setSpanCount(2);
            } else {
                ((GridLayoutManager) this.x.getLayoutManager()).setSpanCount(1);
            }
        }
        this.f8513a.c(this.f, this.h, false);
    }

    @Override // com.huawei.pluginsocialshare.activity.DownloadInterface
    public void showDownloadError() {
        LogUtil.a("Share_StepDetailShareActivity", "showDownloadError");
        this.m.setVisibility(0);
    }

    public void a(int i, ShareRecycleScrollItemView shareRecycleScrollItemView) {
        this.h = i;
        if (i == 0 && !EnvironmentInfo.k()) {
            shareRecycleScrollItemView.crn_(this.j);
        } else {
            c(false);
            refreshShareLayoutNoRecommend();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void coU_(Bitmap bitmap) {
        LogUtil.a("Share_StepDetailShareActivity", "refreshBackground");
        EditShareAdapter editShareAdapter = this.f8513a;
        if (editShareAdapter != null) {
            editShareAdapter.b(this.h);
        }
        if (this.r != null) {
            this.r.setBackground(new BitmapDrawable(this.j.getResources(), bitmap));
        }
    }

    private void c(boolean z) {
        if (this.q && z) {
            this.y.setVisibility(8);
            this.n.setText(R.string._2130843902_res_0x7f0218fe);
        } else {
            this.y.setVisibility(0);
            this.n.setText(R.string.IDS_app_name_health);
        }
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i != 4 && intent == null) {
            LogUtil.h("Share_StepDetailShareActivity", "data == null and it's not camera callback");
            return;
        }
        if (i2 == -1) {
            c(true);
            if (i == 2) {
                coW_(intent);
            } else if (i == 3) {
                coS_(intent);
            } else {
                if (i != 4) {
                    return;
                }
                mud.cpC_(this.j);
            }
        }
    }

    private void coW_(final Intent intent) {
        ThreadPoolManager.d().execute(new Runnable() { // from class: com.huawei.pluginsocialshare.activity.CustomizeShareActivity.1
            @Override // java.lang.Runnable
            public void run() {
                mud.cpD_(CustomizeShareActivity.this.j, intent);
            }
        });
    }

    private void coS_(final Intent intent) {
        ThreadPoolManager.d().execute(new Runnable() { // from class: com.huawei.pluginsocialshare.activity.CustomizeShareActivity.3
            @Override // java.lang.Runnable
            public void run() {
                CustomizeShareActivity.this.coR_(intent);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void coR_(Intent intent) {
        LogUtil.a("Share_StepDetailShareActivity", "dealCropResult");
        String stringExtra = intent.getStringExtra("bitmap");
        if (stringExtra == null) {
            LogUtil.b("Share_StepDetailShareActivity", "dealCropResult:bitmapPath from intent is null!");
            return;
        }
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inPreferredConfig = Bitmap.Config.ARGB_8888;
        Bitmap coT_ = coT_(stringExtra, options);
        this.b = coT_;
        if (coT_ != null) {
            coV_(coT_);
            b(stringExtra);
        } else {
            LogUtil.h("Share_StepDetailShareActivity", "dealCropResult:bitmap is null");
        }
    }

    private void b(String str) {
        mwa.a(str);
    }

    private void e() {
        fdu fduVar = this.v;
        if (fduVar == null) {
            LogUtil.h("Share_StepDetailShareActivity", "mShareContent == null");
            return;
        }
        if (fduVar.u() == 4) {
            File file = new File(this.v.i());
            if (file.exists()) {
                LogUtil.a("Share_StepDetailShareActivity", "is delete file : ", Boolean.valueOf(file.delete()));
            }
        } else {
            Bitmap awm_ = this.v.awm_();
            if (awm_ != null && !awm_.isRecycled()) {
                awm_.recycle();
            }
        }
        if (String.valueOf(16).equals(this.aj)) {
            h();
        }
    }

    private void h() {
        File g = g();
        if (g == null || !g.exists()) {
            return;
        }
        LogUtil.a("Share_StepDetailShareActivity", "deleteWatermarkThumbnail is delete file : ", Boolean.valueOf(g.delete()));
    }

    private File g() {
        feh fehVar = this.ad;
        if (fehVar == null) {
            return null;
        }
        String c2 = fehVar.c();
        LogUtil.a("Share_StepDetailShareActivity", "deleteWatermarkThumbnail path : ", c2);
        if (TextUtils.isEmpty(c2)) {
            return null;
        }
        return new File(c2);
    }

    private void coV_(final Bitmap bitmap) {
        LogUtil.a("Share_StepDetailShareActivity", "refreshBackgroundOnUi");
        runOnUiThread(new Runnable() { // from class: com.huawei.pluginsocialshare.activity.CustomizeShareActivity.5
            @Override // java.lang.Runnable
            public void run() {
                CustomizeShareActivity.this.coU_(bitmap);
            }
        });
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        e();
        mva mvaVar = this.z;
        if (mvaVar != null) {
            mvaVar.cqc_(this.j);
            this.z.e();
            this.z = null;
        }
        ThreadPoolManager.d().execute(new Runnable() { // from class: com.huawei.pluginsocialshare.activity.CustomizeShareActivity.4
            @Override // java.lang.Runnable
            public void run() {
                mvl.b().e();
            }
        });
    }

    private void d(int i) {
        this.g.clear();
        this.g.addAll(this.ag.b());
        this.am = i;
        if (koq.d(this.w, i)) {
            EditShareCommonView editShareCommonView = this.w.get(this.am);
            editShareCommonView.refreshUi(-1, -1);
            editShareCommonView.refreshTopUi(-1);
            View view = editShareCommonView.getView();
            if (view != null) {
                coQ_(view);
                this.ac.put("dataID", Integer.valueOf(editShareCommonView.getWatermarkId()));
            }
        }
    }

    public void d() {
        if (CommonUtil.bv() || CommonUtil.as() || this.ah == null) {
            return;
        }
        if (this.ah.isChecked() == mwd.i()) {
            return;
        }
        this.e = true;
        this.ah.setChecked(mwd.i());
    }

    @Override // com.huawei.pluginsocialshare.adapter.EditShareAdapter.OnBackgroundChangeListener
    public void onBackgroundChange(int i, int i2, View view) {
        if (view instanceof ShareRecycleScrollItemView) {
            if (i == 1) {
                a(i2, (ShareRecycleScrollItemView) view);
            } else {
                if (i != 2) {
                    return;
                }
                d(i2);
            }
        }
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
        mwd.cqK_(this.r, this.j, false);
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, android.view.ContextThemeWrapper, android.content.ContextWrapper, android.content.Context
    public Resources getResources() {
        Resources resources = super.getResources();
        return !this.t ? mwd.cqG_(resources) : resources;
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onPause() {
        super.onPause();
        mwd.cqI_(getResources());
        this.t = true;
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        d();
    }
}
