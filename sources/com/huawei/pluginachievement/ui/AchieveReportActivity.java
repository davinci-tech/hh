package com.huawei.pluginachievement.ui;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Pair;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.huawei.haf.handler.BaseHandler;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.R;
import com.huawei.health.h5pro.core.H5ProLaunchOption;
import com.huawei.health.h5pro.utils.GeneralUtil;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwcommonmodel.utils.PermissionUtil;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.openalliance.ad.constant.Constants;
import com.huawei.pluginachievement.impl.AchieveObserver;
import com.huawei.pluginachievement.manager.model.AchieveInfo;
import com.huawei.pluginachievement.manager.model.ParsedFieldTag;
import com.huawei.pluginachievement.manager.model.PersonalData;
import com.huawei.pluginachievement.manager.model.SingleDayRecord;
import com.huawei.pluginachievement.manager.model.TotalRecord;
import com.huawei.pluginachievement.manager.model.TrackData;
import com.huawei.pluginachievement.manager.model.UserAchieveWrapper;
import com.huawei.pluginachievement.ui.AchieveReportActivity;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.divider.HealthDivider;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.scrollview.HealthScrollView;
import com.huawei.ui.commonui.titlebar.CustomTitleBar;
import com.huawei.ui.commonui.utils.CustomPermissionAction;
import defpackage.bzs;
import defpackage.gvv;
import defpackage.mcx;
import defpackage.mcz;
import defpackage.mdn;
import defpackage.mef;
import defpackage.meh;
import defpackage.mer;
import defpackage.mfc;
import defpackage.mfe;
import defpackage.mfm;
import defpackage.mfp;
import defpackage.mka;
import defpackage.mkc;
import defpackage.mke;
import defpackage.mkw;
import defpackage.mle;
import defpackage.mlf;
import defpackage.mlg;
import defpackage.mll;
import defpackage.nrh;
import defpackage.nrz;
import defpackage.nsf;
import defpackage.nsn;
import health.compact.a.CommonUtil;
import health.compact.a.LanguageUtil;
import health.compact.a.UnitUtil;
import health.compact.a.Utils;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes9.dex */
public class AchieveReportActivity extends BaseActivity implements AchieveObserver {
    private Context b;
    private boolean d;
    private CustomTitleBar e;
    private SingleDayRecord g;
    private meh h;
    private TotalRecord k;
    private HealthTextView l;
    private HealthTextView m;
    private HealthScrollView n;
    private HealthTextView o;
    private HealthTextView p;
    private HealthTextView q;
    private HealthTextView r;
    private HealthTextView s;
    private HealthTextView t;
    private boolean i = false;
    private Map<Integer, Pair<Long, Long>> f = new HashMap(0);

    /* renamed from: a, reason: collision with root package name */
    private HashMap<Integer, BitmapDrawable> f8407a = new HashMap<>(0);
    private Runnable j = new b(this);
    private final Handler c = new a(this);

    private int c(int i) {
        return i == R.id.report_detail_bestNpe ? 16 : 0;
    }

    private boolean s() {
        return false;
    }

    static class a extends BaseHandler<AchieveReportActivity> {
        a(AchieveReportActivity achieveReportActivity) {
            super(achieveReportActivity);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.huawei.haf.handler.BaseHandler
        /* renamed from: chZ_, reason: merged with bridge method [inline-methods] */
        public void handleMessageWhenReferenceNotNull(AchieveReportActivity achieveReportActivity, Message message) {
            int i = message.what;
            if (i == 0) {
                Object obj = message.obj;
                if (obj instanceof UserAchieveWrapper) {
                    achieveReportActivity.a((UserAchieveWrapper) obj);
                    return;
                }
                return;
            }
            if (i != 1) {
                if (i != 2) {
                    return;
                }
                mlg.e(achieveReportActivity);
            } else {
                nrh.d(BaseApplication.getContext(), message.obj + "");
                LogUtil.a("PLGACHIEVE_AchieveReportActivity", "ERROR_TIP :", message.obj);
            }
        }
    }

    static class b implements Runnable {
        private final WeakReference<AchieveReportActivity> e;

        b(AchieveReportActivity achieveReportActivity) {
            this.e = new WeakReference<>(achieveReportActivity);
        }

        @Override // java.lang.Runnable
        public void run() {
            AchieveReportActivity achieveReportActivity = this.e.get();
            if (achieveReportActivity == null || achieveReportActivity.h == null) {
                return;
            }
            mcz d = achieveReportActivity.h.d(1, new HashMap(4));
            if (d instanceof TotalRecord) {
                achieveReportActivity.k = (TotalRecord) d;
            }
            mcz d2 = achieveReportActivity.h.d(2, new HashMap(4));
            if (d2 instanceof SingleDayRecord) {
                achieveReportActivity.g = (SingleDayRecord) d2;
            }
            if (achieveReportActivity.d) {
                achieveReportActivity.a();
            }
            achieveReportActivity.c.sendMessage(achieveReportActivity.c.obtainMessage(0, new UserAchieveWrapper(0, null, null, achieveReportActivity.g, achieveReportActivity.k)));
        }
    }

    static class c implements Runnable {
        private TrackData b;
        private final WeakReference<AchieveReportActivity> d;

        c(AchieveReportActivity achieveReportActivity, TrackData trackData) {
            this.d = new WeakReference<>(achieveReportActivity);
            this.b = trackData;
        }

        @Override // java.lang.Runnable
        public void run() {
            AchieveReportActivity achieveReportActivity = this.d.get();
            if (achieveReportActivity == null || achieveReportActivity.h == null) {
                return;
            }
            mfe.a(this.b, achieveReportActivity.h, achieveReportActivity.b);
        }
    }

    private Drawable chV_(int i) {
        BitmapDrawable bitmapDrawable = null;
        if (!LanguageUtil.bc(this.b)) {
            return null;
        }
        HashMap<Integer, BitmapDrawable> hashMap = this.f8407a;
        if (hashMap != null) {
            BitmapDrawable bitmapDrawable2 = hashMap.get(Integer.valueOf(i));
            if (bitmapDrawable2 != null) {
                return bitmapDrawable2;
            }
            bitmapDrawable = nrz.cKn_(this.b, i);
            if (bitmapDrawable != null) {
                this.f8407a.put(Integer.valueOf(i), bitmapDrawable);
            }
        }
        return bitmapDrawable;
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (nsn.s()) {
            setContentView(R.layout.activity_report_main_three_fold_fonts);
        } else {
            setContentView(R.layout.activity_report_main);
        }
        clearBackgroundDrawable();
        this.b = this;
        m();
        e();
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(UserAchieveWrapper userAchieveWrapper) {
        TotalRecord acquireTotalRecord = userAchieveWrapper.acquireTotalRecord();
        SingleDayRecord acquireSingleDayRecord = userAchieveWrapper.acquireSingleDayRecord();
        e(acquireTotalRecord);
        d(acquireSingleDayRecord);
    }

    private void e(TotalRecord totalRecord) {
        if (totalRecord == null) {
            totalRecord = mll.a();
        }
        this.m.setText(mll.c(this.b, totalRecord));
        this.r.setText(mll.d(this.b, totalRecord), TextView.BufferType.SPANNABLE);
        this.t.setText(mll.g(this.b, totalRecord));
        if (mlg.d()) {
            this.q.setText(mll.i(this.b, totalRecord));
        } else {
            this.q.setText(mll.e(this.b, totalRecord));
        }
        this.s.setText(mll.j(this.b, totalRecord));
        this.p.setText(mll.h(this.b, totalRecord));
        this.l.setText(mll.a(this.b, totalRecord));
        this.o.setText(mll.b(this.b, totalRecord));
        HealthScrollView healthScrollView = this.n;
        if (healthScrollView != null) {
            a(healthScrollView, R.id.report_detail_totaldays, R.id.content_value).setText(mll.d(this.b, totalRecord), TextView.BufferType.SPANNABLE);
            a(this.n, R.id.report_detail_totaldays, R.id.content_desc).setText(mll.c(this.b, totalRecord));
            a(this.n, R.id.report_detail_totalWalkDistance, R.id.content_value).setText(mll.g(this.b, totalRecord));
            if (mlg.d()) {
                a(this.n, R.id.report_detail_totalWalkDistance, R.id.content_desc).setText(mll.i(this.b, totalRecord));
            } else {
                a(this.n, R.id.report_detail_totalWalkDistance, R.id.content_desc).setText(mll.e(this.b, totalRecord));
            }
            a(this.n, R.id.report_detail_totalsteps, R.id.content_value).setText(mll.j(this.b, totalRecord));
            a(this.n, R.id.report_detail_totalsteps, R.id.content_desc).setText(mll.h(this.b, totalRecord));
            a(this.n, R.id.report_detail_totalcal, R.id.content_value).setText(mll.a(this.b, totalRecord));
            a(this.n, R.id.report_detail_totalcal, R.id.content_desc).setText(mll.b(this.b, totalRecord));
        }
    }

    private void e() {
        this.h = meh.c(getApplicationContext());
        LogUtil.a("PLGACHIEVE_AchieveReportActivity", "getData()");
        this.h.b((AchieveObserver) this);
        this.d = true;
        ThreadPoolManager.d().execute(this.j);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a() {
        LogUtil.a("PLGACHIEVE_AchieveReportActivity", "enter doRefreshInfo():");
        meh mehVar = this.h;
        if (mehVar != null) {
            mcz d = mehVar.d(5, new HashMap(4));
            Map<String, String> e = e(d instanceof AchieveInfo ? (AchieveInfo) d : null);
            e.put("countryCode", LoginInit.getInstance(this.b).getAccountInfo(1010));
            this.h.a(0, e);
        }
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onPause() {
        meh mehVar = this.h;
        if (mehVar != null) {
            mehVar.c((AchieveObserver) this);
        }
        super.onPause();
    }

    private Map<String, String> e(AchieveInfo achieveInfo) {
        if (achieveInfo == null) {
            return new HashMap(0);
        }
        HashMap hashMap = new HashMap(4);
        hashMap.put("timestamp", String.valueOf(achieveInfo.getSyncTimestamp()));
        return hashMap;
    }

    private void m() {
        Typeface.create(Constants.FONT, 0);
        o();
        b();
        k();
        c();
        h();
        n();
        f();
        g();
        l();
        j();
    }

    private void j() {
        HealthScrollView healthScrollView = (HealthScrollView) mfm.cgL_(this, R.id.report_scrollview);
        healthScrollView.setScrollViewVerticalDirectionEvent(true);
        healthScrollView.setLayerType(1, null);
    }

    private void l() {
        CustomTitleBar customTitleBar = (CustomTitleBar) mfm.cgL_(this, R.id.title_layout);
        this.e = customTitleBar;
        customTitleBar.setRightButtonVisibility(0);
        if (LanguageUtil.bc(this.b)) {
            this.e.setRightButtonDrawable(nrz.cKn_(this.b, R.drawable._2131430035_res_0x7f0b0a93), nsf.h(R.string._2130850657_res_0x7f023361));
        } else {
            this.e.setRightButtonDrawable(BaseApplication.getContext().getResources().getDrawable(R.drawable._2131430035_res_0x7f0b0a93), nsf.h(R.string._2130850657_res_0x7f023361));
        }
        this.e.setRightButtonOnClickListener(new View.OnClickListener() { // from class: mja
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                AchieveReportActivity.this.chY_(view);
            }
        });
    }

    public /* synthetic */ void chY_(View view) {
        LogUtil.a("PLGACHIEVE_AchieveReportActivity", "initTitleBar onClick share information");
        if (PermissionUtil.c()) {
            q();
            ViewClickInstrumentation.clickOnView(view);
        } else {
            PermissionUtil.b(this.b, PermissionUtil.PermissionType.STORAGE, new CustomPermissionAction(this.b) { // from class: com.huawei.pluginachievement.ui.AchieveReportActivity.2
                @Override // com.huawei.hwcommonmodel.utils.permission.PermissionsResultAction
                public void onGranted() {
                    AchieveReportActivity.this.q();
                }
            });
            ViewClickInstrumentation.clickOnView(view);
        }
    }

    @Override // com.huawei.pluginachievement.impl.AchieveObserver
    public void onDataChanged(int i, UserAchieveWrapper userAchieveWrapper) {
        if (i == -1) {
            LogUtil.h("PLGACHIEVE_AchieveReportActivity", "onDataChanged error=", Integer.valueOf(i));
            if (CommonUtil.bu()) {
                return;
            }
            String string = getString(R.string._2130841551_res_0x7f020fcf);
            Handler handler = this.c;
            handler.sendMessage(handler.obtainMessage(1, string));
            return;
        }
        if (userAchieveWrapper != null && userAchieveWrapper.getContentType() == 0) {
            this.d = false;
            ThreadPoolManager.d().execute(this.j);
        }
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        meh mehVar = this.h;
        if (mehVar != null) {
            mehVar.c((AchieveObserver) this);
        }
        Handler handler = this.c;
        if (handler != null) {
            handler.removeMessages(0);
            this.c.removeMessages(2);
        }
        if (this.j != null) {
            ThreadPoolManager.d().a(this.j);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void q() {
        Bitmap cgv_;
        LogUtil.a("PLGACHIEVE_AchieveReportActivity", "share enter");
        if (!mcx.d(this)) {
            LogUtil.a("PLGACHIEVE_AchieveReportActivity", "isNetworkAvailable error");
            this.c.sendEmptyMessage(2);
            return;
        }
        if (this.n == null) {
            this.i = true;
            HealthScrollView healthScrollView = (HealthScrollView) ((ViewStub) findViewById(R.id.share_content_layout)).inflate();
            this.n = healthScrollView;
            HealthTextView healthTextView = (HealthTextView) mfm.cgM_(healthScrollView, R.id.report_share_date);
            String b2 = mlg.b(String.valueOf(System.currentTimeMillis()));
            if (healthTextView != null) {
                healthTextView.setText(b2);
            }
            HealthTextView healthTextView2 = (HealthTextView) mfm.cgM_(this.n, R.id.report_share_date_no);
            if (healthTextView2 != null) {
                healthTextView2.setText(b2);
            }
            ImageView imageView = (ImageView) mfm.cgL_(this, R.id.report_share_head);
            ((HealthTextView) mfm.cgL_(this, R.id.report_share_nick)).setText(mcx.b());
            String c2 = mcx.c(getApplicationContext());
            String c3 = CommonUtil.c(c2);
            if (GeneralUtil.isSafePath(c2) && !TextUtils.isEmpty(c3) && (cgv_ = mfc.cgv_(this, c3)) != null) {
                imageView.setImageBitmap(cgv_);
            }
            t();
            o();
            i();
            k();
            c();
            h();
            n();
            f();
            g();
            e(this.k);
            d(this.g);
        }
        View cgM_ = mfm.cgM_(this.n, R.id.share_top_rlayout);
        View cgM_2 = mfm.cgM_(this.n, R.id.share_top_rlayout_no);
        if (mcx.e()) {
            cgM_.setVisibility(0);
            cgM_2.setVisibility(8);
        } else {
            ViewGroup.LayoutParams layoutParams = cgM_2.getLayoutParams();
            layoutParams.height = (nsn.n() * 313) / 720;
            cgM_2.setLayoutParams(layoutParams);
            cgM_.setVisibility(8);
            cgM_2.setVisibility(0);
        }
        this.n.post(new Runnable() { // from class: mix
            @Override // java.lang.Runnable
            public final void run() {
                AchieveReportActivity.this.d();
            }
        });
    }

    public /* synthetic */ void d() {
        Bitmap cgK_ = mfp.cgK_(this.n);
        if (cgK_ == null) {
            LogUtil.a("PLGACHIEVE_AchieveReportActivity", "share bmpShare == null ");
        } else {
            mcx.cfN_(this.b, cgK_, AnalyticsValue.SUCCESSES_SHARE_1100014.value(), null);
        }
        LogUtil.a("PLGACHIEVE_AchieveReportActivity", "share end");
    }

    private void t() {
        ImageView imageView = (ImageView) mfm.cgL_(this, R.id.vip_image);
        if (mkw.e()) {
            imageView.setVisibility(0);
        } else {
            imageView.setVisibility(8);
        }
    }

    private void k() {
        String string = this.b.getString(R.string._2130840707_res_0x7f020c83);
        String string2 = this.b.getString(R.string._2130840709_res_0x7f020c85);
        String string3 = this.b.getString(R.string._2130847439_res_0x7f0226cf);
        String string4 = this.b.getString(R.string._2130840708_res_0x7f020c84);
        if (!this.i) {
            e(R.id.report_detail_totaldays, R.id.content_title).setText(string);
            this.r = e(R.id.report_detail_totaldays, R.id.content_value);
            this.m = e(R.id.report_detail_totaldays, R.id.content_desc);
            e(R.id.report_detail_totalsteps, R.id.content_title).setText(string2);
            this.s = e(R.id.report_detail_totalsteps, R.id.content_value);
            this.p = e(R.id.report_detail_totalsteps, R.id.content_desc);
            e(R.id.report_detail_totalcal, R.id.content_title).setText(string3);
            this.l = e(R.id.report_detail_totalcal, R.id.content_value);
            this.o = e(R.id.report_detail_totalcal, R.id.content_desc);
            e(R.id.report_detail_totalWalkDistance, R.id.content_title).setText(string4);
            this.t = e(R.id.report_detail_totalWalkDistance, R.id.content_value);
            this.q = e(R.id.report_detail_totalWalkDistance, R.id.content_desc);
            a(R.id.report_detail_totalWalkDistance, R.id.data_line).setVisibility(8);
            return;
        }
        a(this.n, R.id.report_detail_totaldays, R.id.content_title).setText(string);
        a(this.n, R.id.report_detail_totalsteps, R.id.content_title).setText(string2);
        a(this.n, R.id.report_detail_totalcal, R.id.content_title).setText(string3);
        a(this.n, R.id.report_detail_totalWalkDistance, R.id.content_title).setText(string4);
        c(this.n, R.id.report_detail_totalWalkDistance, R.id.data_line).setVisibility(8);
    }

    private void o() {
        String string = this.b.getString(R.string._2130840714_res_0x7f020c8a);
        String string2 = this.b.getString(R.string._2130840809_res_0x7f020ce9);
        String string3 = this.b.getString(R.string._2130840811_res_0x7f020ceb);
        String string4 = this.b.getString(R.string._2130840810_res_0x7f020cea);
        String string5 = this.b.getString(R.string._2130840992_res_0x7f020da0);
        String string6 = this.b.getString(R.string._2130841074_res_0x7f020df2);
        if (!this.i) {
            ((HealthTextView) findViewById(R.id.report_detail_totalDataTitle)).setText(string);
            ((HealthTextView) findViewById(R.id.report_detail_stepTitle)).setText(string2);
            ((HealthTextView) findViewById(R.id.report_detail_runTitle)).setText(string3);
            ((HealthTextView) findViewById(R.id.report_detail_cylceTitle)).setText(string4);
            ((HealthTextView) findViewById(R.id.report_detail_ropeTitle)).setText(string5);
            ((HealthTextView) findViewById(R.id.report_detail_npeTitle)).setText(string6);
            if (Utils.o()) {
                findViewById(R.id.report_detail_cylceTitle_splitter).setVisibility(8);
                findViewById(R.id.report_detail_ropeTitle_splitter).setVisibility(8);
                a(R.id.report_detail_runBestPace, R.id.data_line).setVisibility(8);
                return;
            }
            return;
        }
        ((HealthTextView) this.n.findViewById(R.id.report_detail_totalDataTitle)).setText(string);
        ((HealthTextView) this.n.findViewById(R.id.report_detail_stepTitle)).setText(string2);
        ((HealthTextView) this.n.findViewById(R.id.report_detail_runTitle)).setText(string3);
        ((HealthTextView) this.n.findViewById(R.id.report_detail_cylceTitle)).setText(string4);
        ((HealthTextView) this.n.findViewById(R.id.report_detail_ropeTitle)).setText(string5);
        ((HealthTextView) this.n.findViewById(R.id.report_detail_npeTitle)).setText(string6);
    }

    private void h() {
        if (!this.i) {
            e(R.id.report_detail_runBestDistance, R.id.content_title).setText(this.b.getString(R.string._2130840803_res_0x7f020ce3));
            e(R.id.report_detail_runBestPace, R.id.content_title).setText(this.b.getString(R.string._2130840741_res_0x7f020ca5));
            e(R.id.report_detail_PB3KMBestPace, R.id.content_title).setText(mlg.d(5, this.b));
            e(R.id.report_detail_PB5KMBestPace, R.id.content_title).setText(mlg.d(6, this.b));
            e(R.id.report_detail_PB10KMBestPace, R.id.content_title).setText(mlg.d(7, this.b));
            e(R.id.report_detail_PBHMBestPace, R.id.content_title).setText(this.b.getString(R.string._2130840807_res_0x7f020ce7));
            e(R.id.report_detail_PBFMBestPace, R.id.content_title).setText(this.b.getString(R.string._2130840808_res_0x7f020ce8));
            a(R.id.report_detail_PBFMBestPace, R.id.data_line).setVisibility(8);
            return;
        }
        a(this.n, R.id.report_detail_runBestDistance, R.id.content_title).setText(this.b.getString(R.string._2130840803_res_0x7f020ce3));
        a(this.n, R.id.report_detail_runBestPace, R.id.content_title).setText(this.b.getString(R.string._2130840741_res_0x7f020ca5));
        a(this.n, R.id.report_detail_PB3KMBestPace, R.id.content_title).setText(mlg.d(5, this.b));
        a(this.n, R.id.report_detail_PB5KMBestPace, R.id.content_title).setText(mlg.d(6, this.b));
        a(this.n, R.id.report_detail_PB10KMBestPace, R.id.content_title).setText(mlg.d(7, this.b));
        a(this.n, R.id.report_detail_PBHMBestPace, R.id.content_title).setText(this.b.getString(R.string._2130840807_res_0x7f020ce7));
        a(this.n, R.id.report_detail_PBFMBestPace, R.id.content_title).setText(this.b.getString(R.string._2130840808_res_0x7f020ce8));
        c(this.n, R.id.report_detail_PBFMBestPace, R.id.data_line).setVisibility(8);
    }

    private void d(SingleDayRecord singleDayRecord) {
        c(R.id.report_detail_PB3KMBestPace, singleDayRecord);
        c(R.id.report_detail_PB5KMBestPace, singleDayRecord);
        c(R.id.report_detail_PB10KMBestPace, singleDayRecord);
        c(R.id.report_detail_PBHMBestPace, singleDayRecord);
        c(R.id.report_detail_PBFMBestPace, singleDayRecord);
        c(R.id.report_detail_cylceBestDistance, singleDayRecord);
        c(R.id.report_detail_cylceBestPace, singleDayRecord);
        c(R.id.report_detail_stepBestDistance, singleDayRecord);
        c(R.id.report_detail_singleDayMoststeps, singleDayRecord);
        c(R.id.report_detail_runBestDistance, singleDayRecord);
        c(R.id.report_detail_runBestPace, singleDayRecord);
        c(R.id.report_detail_ropeBestRopeSingCount, singleDayRecord);
        c(R.id.report_detail_ropeBestRopeContinuousCount, singleDayRecord);
        c(R.id.report_detail_ropeBestRopeSpeed, singleDayRecord);
        c(R.id.report_detail_ropeBestRopeDuration, singleDayRecord);
        c(R.id.report_detail_bestNpe, singleDayRecord);
    }

    private void b() {
        chX_(chR_(R.id.report_detail_totaldays, R.id.content_icon), R.mipmap._2131820878_res_0x7f11014e);
        chX_(chR_(R.id.report_detail_totalsteps, R.id.content_icon), R.mipmap._2131821124_res_0x7f110244);
        chX_(chR_(R.id.report_detail_totalcal, R.id.content_icon), R.mipmap._2131820934_res_0x7f110186);
        chX_(chR_(R.id.report_detail_totalWalkDistance, R.id.content_icon), R.mipmap._2131820881_res_0x7f110151);
        chX_(chR_(R.id.report_detail_stepBestDistance, R.id.content_icon), R.mipmap._2131821138_res_0x7f110252);
        chX_(chR_(R.id.report_detail_singleDayMoststeps, R.id.content_icon), R.mipmap._2131821124_res_0x7f110244);
        chX_(chR_(R.id.report_detail_cylceBestDistance, R.id.content_icon), R.mipmap._2131821066_res_0x7f11020a);
        chX_(chR_(R.id.report_detail_cylceBestPace, R.id.content_icon), R.mipmap._2131821123_res_0x7f110243);
        chX_(chR_(R.id.report_detail_runBestDistance, R.id.content_icon), R.mipmap._2131821079_res_0x7f110217);
        chX_(chR_(R.id.report_detail_runBestPace, R.id.content_icon), R.mipmap._2131821123_res_0x7f110243);
        chX_(chR_(R.id.report_detail_ropeBestRopeSingCount, R.id.content_icon), R.drawable._2131430348_res_0x7f0b0bcc);
        chX_(chR_(R.id.report_detail_ropeBestRopeContinuousCount, R.id.content_icon), R.drawable._2131430348_res_0x7f0b0bcc);
        chX_(chR_(R.id.report_detail_ropeBestRopeSpeed, R.id.content_icon), R.drawable._2131430357_res_0x7f0b0bd5);
        chX_(chR_(R.id.report_detail_ropeBestRopeDuration, R.id.content_icon), R.drawable._2131430358_res_0x7f0b0bd6);
        chR_(R.id.report_detail_PB3KMBestPace, R.id.content_icon).setBackgroundResource(R.mipmap._2131821084_res_0x7f11021c);
        chR_(R.id.report_detail_PB5KMBestPace, R.id.content_icon).setBackgroundResource(R.mipmap._2131821085_res_0x7f11021d);
        chR_(R.id.report_detail_PB10KMBestPace, R.id.content_icon).setBackgroundResource(R.mipmap._2131821080_res_0x7f110218);
        chR_(R.id.report_detail_PBHMBestPace, R.id.content_icon).setBackgroundResource(R.mipmap._2131821091_res_0x7f110223);
        chR_(R.id.report_detail_PBFMBestPace, R.id.content_icon).setBackgroundResource(R.mipmap._2131821089_res_0x7f110221);
        chR_(R.id.report_detail_bestNpe, R.id.content_icon).setBackgroundResource(R.mipmap._2131821131_res_0x7f11024b);
    }

    private void i() {
        chX_(chT_(this.n, R.id.report_detail_totaldays, R.id.content_icon), R.mipmap._2131820878_res_0x7f11014e);
        chX_(chT_(this.n, R.id.report_detail_totalsteps, R.id.content_icon), R.mipmap._2131821124_res_0x7f110244);
        chX_(chT_(this.n, R.id.report_detail_totalcal, R.id.content_icon), R.mipmap._2131820934_res_0x7f110186);
        chX_(chT_(this.n, R.id.report_detail_totalWalkDistance, R.id.content_icon), R.mipmap._2131820881_res_0x7f110151);
        chX_(chT_(this.n, R.id.report_detail_stepBestDistance, R.id.content_icon), R.mipmap._2131821138_res_0x7f110252);
        chX_(chT_(this.n, R.id.report_detail_singleDayMoststeps, R.id.content_icon), R.mipmap._2131821124_res_0x7f110244);
        chX_(chT_(this.n, R.id.report_detail_cylceBestDistance, R.id.content_icon), R.mipmap._2131821066_res_0x7f11020a);
        chX_(chT_(this.n, R.id.report_detail_cylceBestPace, R.id.content_icon), R.mipmap._2131821123_res_0x7f110243);
        chX_(chT_(this.n, R.id.report_detail_runBestDistance, R.id.content_icon), R.mipmap._2131821079_res_0x7f110217);
        chX_(chT_(this.n, R.id.report_detail_runBestPace, R.id.content_icon), R.mipmap._2131821123_res_0x7f110243);
        chX_(chT_(this.n, R.id.report_detail_ropeBestRopeSingCount, R.id.content_icon), R.drawable._2131430348_res_0x7f0b0bcc);
        chX_(chT_(this.n, R.id.report_detail_ropeBestRopeContinuousCount, R.id.content_icon), R.drawable._2131430348_res_0x7f0b0bcc);
        chX_(chT_(this.n, R.id.report_detail_ropeBestRopeSpeed, R.id.content_icon), R.drawable._2131430357_res_0x7f0b0bd5);
        chX_(chT_(this.n, R.id.report_detail_ropeBestRopeDuration, R.id.content_icon), R.drawable._2131430358_res_0x7f0b0bd6);
        chT_(this.n, R.id.report_detail_PB3KMBestPace, R.id.content_icon).setBackgroundResource(R.mipmap._2131821084_res_0x7f11021c);
        chT_(this.n, R.id.report_detail_PB5KMBestPace, R.id.content_icon).setBackgroundResource(R.mipmap._2131821085_res_0x7f11021d);
        chT_(this.n, R.id.report_detail_PB10KMBestPace, R.id.content_icon).setBackgroundResource(R.mipmap._2131821080_res_0x7f110218);
        chT_(this.n, R.id.report_detail_PBHMBestPace, R.id.content_icon).setBackgroundResource(R.mipmap._2131821091_res_0x7f110223);
        chT_(this.n, R.id.report_detail_PBFMBestPace, R.id.content_icon).setBackgroundResource(R.mipmap._2131821089_res_0x7f110221);
        chT_(this.n, R.id.report_detail_bestNpe, R.id.content_icon).setBackgroundResource(R.mipmap._2131821131_res_0x7f11024b);
    }

    private void chX_(ImageView imageView, int i) {
        Drawable chV_ = chV_(i);
        if (chV_ != null) {
            imageView.setBackground(chV_);
        } else {
            imageView.setBackgroundResource(i);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int b(int i) {
        if (i == R.id.report_detail_stepBestDistance) {
            return 1;
        }
        if (i == R.id.report_detail_singleDayMoststeps) {
            return 2;
        }
        if (i == R.id.report_detail_cylceBestDistance) {
            return 10;
        }
        if (i == R.id.report_detail_cylceBestPace) {
            return 11;
        }
        if (i == R.id.report_detail_runBestDistance) {
            return 3;
        }
        if (i == R.id.report_detail_runBestPace) {
            return 4;
        }
        if (i == R.id.report_detail_PB3KMBestPace) {
            return 5;
        }
        if (i == R.id.report_detail_PB5KMBestPace) {
            return 6;
        }
        if (i == R.id.report_detail_PB10KMBestPace) {
            return 7;
        }
        if (i == R.id.report_detail_PBHMBestPace) {
            return 8;
        }
        if (i == R.id.report_detail_PBFMBestPace) {
            return 9;
        }
        if (i == R.id.report_detail_ropeBestRopeSingCount) {
            return 12;
        }
        if (i == R.id.report_detail_ropeBestRopeContinuousCount) {
            return 13;
        }
        if (i == R.id.report_detail_ropeBestRopeSpeed) {
            return 14;
        }
        if (i == R.id.report_detail_ropeBestRopeDuration) {
            return 15;
        }
        return c(i);
    }

    private void c() {
        String string = this.b.getString(R.string._2130840804_res_0x7f020ce4);
        String string2 = this.b.getString(R.string._2130840805_res_0x7f020ce5);
        if (!this.i) {
            e(R.id.report_detail_cylceBestDistance, R.id.content_title).setText(string);
            e(R.id.report_detail_cylceBestPace, R.id.content_title).setText(string2);
            a(R.id.report_detail_cylceBestPace, R.id.data_line).setVisibility(8);
        } else {
            a(this.n, R.id.report_detail_cylceBestDistance, R.id.content_title).setText(string);
            a(this.n, R.id.report_detail_cylceBestPace, R.id.content_title).setText(string2);
            c(this.n, R.id.report_detail_cylceBestPace, R.id.data_line).setVisibility(8);
        }
    }

    private void f() {
        String string = this.b.getString(R.string._2130840993_res_0x7f020da1);
        String string2 = this.b.getString(R.string._2130840994_res_0x7f020da2);
        String string3 = this.b.getString(R.string._2130840995_res_0x7f020da3);
        String string4 = this.b.getString(R.string._2130840996_res_0x7f020da4);
        if (!this.i) {
            e(R.id.report_detail_ropeBestRopeSingCount, R.id.content_title).setText(string);
            chR_(R.id.report_detail_ropeBestRopeSingCount, R.id.record_arrow).setVisibility(8);
            e(R.id.report_detail_ropeBestRopeContinuousCount, R.id.content_title).setText(string2);
            chR_(R.id.report_detail_ropeBestRopeContinuousCount, R.id.record_arrow).setVisibility(8);
            e(R.id.report_detail_ropeBestRopeSpeed, R.id.content_title).setText(string3);
            chR_(R.id.report_detail_ropeBestRopeSpeed, R.id.record_arrow).setVisibility(8);
            e(R.id.report_detail_ropeBestRopeDuration, R.id.content_title).setText(string4);
            chR_(R.id.report_detail_ropeBestRopeDuration, R.id.record_arrow).setVisibility(8);
            a(R.id.report_detail_ropeBestRopeDuration, R.id.data_line).setVisibility(8);
            return;
        }
        a(this.n, R.id.report_detail_ropeBestRopeSingCount, R.id.content_title).setText(string);
        a(this.n, R.id.report_detail_ropeBestRopeContinuousCount, R.id.content_title).setText(string2);
        a(this.n, R.id.report_detail_ropeBestRopeSpeed, R.id.content_title).setText(string3);
        a(this.n, R.id.report_detail_ropeBestRopeDuration, R.id.content_title).setText(string4);
        c(this.n, R.id.report_detail_ropeBestRopeDuration, R.id.data_line).setVisibility(8);
    }

    private void g() {
        if (s()) {
            return;
        }
        String string = this.b.getString(R.string._2130841075_res_0x7f020df3);
        if (!this.i) {
            e(R.id.report_detail_bestNpe, R.id.content_title).setText(string);
            a(R.id.report_detail_bestNpe, R.id.data_line).setVisibility(8);
        } else {
            a(this.n, R.id.report_detail_bestNpe, R.id.content_title).setText(string);
            c(this.n, R.id.report_detail_bestNpe, R.id.data_line).setVisibility(8);
        }
    }

    private void n() {
        if (!this.i) {
            e(R.id.report_detail_stepBestDistance, R.id.content_title).setText(this.b.getString(R.string._2130840802_res_0x7f020ce2));
            e(R.id.report_detail_singleDayMoststeps, R.id.content_title).setText(this.b.getString(R.string._2130840715_res_0x7f020c8b));
            a(R.id.report_detail_singleDayMoststeps, R.id.data_line).setVisibility(8);
            chR_(R.id.report_detail_singleDayMoststeps, R.id.record_arrow).setVisibility(8);
            return;
        }
        a(this.n, R.id.report_detail_stepBestDistance, R.id.content_title).setText(this.b.getString(R.string._2130840802_res_0x7f020ce2));
        a(this.n, R.id.report_detail_singleDayMoststeps, R.id.content_title).setText(this.b.getString(R.string._2130840715_res_0x7f020c8b));
        c(this.n, R.id.report_detail_singleDayMoststeps, R.id.data_line).setVisibility(8);
    }

    private void e(int i) {
        e(i, R.id.content_value).setVisibility(8);
        e(i, R.id.content_title).setTextColor(getResources().getColor(R.color._2131299236_res_0x7f090ba4));
        e(i, R.id.content_title).setTextSize(0, getResources().getDimension(R.dimen._2131365061_res_0x7f0a0cc5));
        chS_(i, R.id.record_data_layout).setVisibility(8);
    }

    private void d(int i) {
        e(i, R.id.content_value).setVisibility(0);
        e(i, R.id.content_title).setTextColor(getResources().getColor(R.color._2131299236_res_0x7f090ba4));
        e(i, R.id.content_title).setTextSize(0, getResources().getDimension(R.dimen._2131365061_res_0x7f0a0cc5));
        chS_(i, R.id.record_data_layout).setVisibility(0);
        if (mle.a(this.b)) {
            chR_(i, R.id.record_arrow).setImageResource(R.drawable._2131427841_res_0x7f0b0201);
        }
        if (CommonUtil.bu()) {
            chR_(i, R.id.record_arrow).setVisibility(8);
        }
    }

    private void b(int i, int i2, String str) {
        Pair<Long, Long> chW_ = chW_(str, i2);
        b(i2, ((Long) chW_.first).longValue(), ((Long) chW_.second).longValue());
        if (mlg.g(i2)) {
            c(i, i2, str);
            return;
        }
        if (mlg.f(i2)) {
            a(i, i2, str);
            return;
        }
        if (mlg.i(i2)) {
            c(i, str);
        } else if (mlg.h(i2)) {
            e(i, i2, str);
        } else {
            d(i, i2, str);
        }
    }

    private void d(int i, int i2, String str) {
        if (mlg.j(i2)) {
            b(i, str);
        } else {
            e(i);
            i(i);
        }
    }

    private void c(int i, mka mkaVar) {
        if (mkaVar == null) {
            LogUtil.h("PLGACHIEVE_AchieveReportActivity", "dealMedalOnException bestMotion == null!");
            return;
        }
        if (i == 3) {
            TrackData trackData = new TrackData();
            trackData.saveType(258);
            trackData.saveDistance(mkaVar.b());
            trackData.saveTrackTime(mkaVar.e());
            LogUtil.a("PLGACHIEVE_AchieveReportActivity", "dealMedalOnException run report = ", Long.valueOf(mkaVar.b()));
            e(trackData);
            return;
        }
        if (i == 10) {
            TrackData trackData2 = new TrackData();
            trackData2.saveType(259);
            trackData2.saveDistance(mkaVar.b());
            trackData2.saveTrackTime(mkaVar.e());
            LogUtil.a("PLGACHIEVE_AchieveReportActivity", "dealMedalOnException cycle report = ", Long.valueOf(mkaVar.b()));
            e(trackData2);
            return;
        }
        LogUtil.h("PLGACHIEVE_AchieveReportActivity", "not deal");
    }

    private void e(TrackData trackData) {
        ThreadPoolManager.d().execute(new c(this, trackData));
    }

    private void c(int i, int i2, String str) {
        mka c2 = mlg.c(str);
        if (c2 != null) {
            c(i, i2);
            e(i, R.id.content_desc).setVisibility(8);
            d(i);
            String b2 = mlf.b(c2.b() * 1.0d, this.b);
            String b3 = mlg.b(String.valueOf(c2.e()));
            e(i, R.id.content_value).setText(b2);
            e(i, R.id.record_data).setText(b3);
            c(i, b2, b3, null);
            c(i2, c2);
            return;
        }
        e(i);
        i(i);
    }

    private void a(int i, int i2, String str) {
        String valueOf;
        mkc d = mlg.d(str);
        if (d != null) {
            c(i, i2);
            e(i, R.id.content_desc).setVisibility(8);
            d(i);
            if (4 == i2) {
                valueOf = gvv.a((float) d.b());
            } else {
                valueOf = String.valueOf(mlg.d((int) (d.b() + 0.5d)));
            }
            String b2 = mlg.b(String.valueOf(d.e()));
            if (mlg.e(i2)) {
                String string = this.b.getString(R.string._2130844078_res_0x7f0219ae);
                String a2 = mlg.a(d.b());
                if (UnitUtil.h()) {
                    a2 = UnitUtil.e(UnitUtil.e(mef.d(a2), 3), 1, 2);
                    string = this.b.getString(R.string._2130844079_res_0x7f0219af);
                    e(i, R.id.content_unit).setText(string);
                }
                e(i, R.id.content_value).setText(a2);
                e(i, R.id.content_unit).setVisibility(0);
                c(i, a2, b2, string);
            } else {
                e(i, R.id.content_value).setText(valueOf);
                c(i, valueOf, b2, null);
            }
            e(i, R.id.record_data).setText(b2);
            return;
        }
        e(i);
        i(i);
    }

    private void c(int i, String str) {
        mke h = mlg.h(str);
        if (h != null) {
            e(i, R.id.content_desc).setVisibility(8);
            String a2 = mlf.a(this.b, h.a());
            String a3 = mlg.a(String.valueOf(h.d()));
            d(i);
            e(i, R.id.content_value).setText(mlf.a(this.b, h.a()));
            e(i, R.id.record_data).setText(mlg.a(String.valueOf(h.d())));
            c(i, a2, a3, null);
            return;
        }
        e(i);
        i(i);
    }

    private void e(int i, int i2, String str) {
        mka c2 = mlg.c(str);
        if (c2 != null) {
            e(i, R.id.content_desc).setVisibility(8);
            d(i);
            String d = mlf.d(c2.b(), i2, this.b);
            String b2 = mlg.b(String.valueOf(c2.e()));
            e(i, R.id.content_value).setText(d);
            e(i, R.id.record_data).setText(b2);
            c(i, d, b2, null);
            return;
        }
        e(i);
        i(i);
        j(i2);
    }

    private void b(int i, String str) {
        if (s() || TextUtils.isEmpty(str)) {
            return;
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            double a2 = mdn.a(ParsedFieldTag.NPES_TOTAL_SCORE, jSONObject);
            long d = mdn.d(ParsedFieldTag.NPES_SPORT_TIME, jSONObject);
            String b2 = mdn.b(ParsedFieldTag.NPES_TEAM_TYPE, jSONObject);
            String b3 = mdn.b(ParsedFieldTag.NPES_RESULT_ID, jSONObject);
            if (mlg.d(a2, 0.0d) == 0 && d == 0) {
                e(i);
                i(i);
                j(16);
                return;
            }
            d(i, a2, d, b2, b3);
        } catch (JSONException e) {
            LogUtil.b("PLGACHIEVE_AchieveReportActivity", "initNpeRecordView Exception:", e.getMessage());
        }
    }

    private void d(int i, double d, long j, final String str, final String str2) {
        String quantityString = getResources().getQuantityString(R.plurals._2130903221_res_0x7f0300b5, (int) d, UnitUtil.e(d, 1, 0));
        e(i, R.id.content_value).setText(quantityString);
        String b2 = mlg.b(String.valueOf(j));
        e(i, R.id.record_data).setText(b2);
        e(i, R.id.content_desc).setVisibility(8);
        d(i);
        c(i, quantityString, b2, null);
        if (CommonUtil.bu()) {
            return;
        }
        findViewById(i).setBackgroundResource(R.drawable._2131427493_res_0x7f0b00a5);
        findViewById(i).setOnClickListener(new View.OnClickListener() { // from class: com.huawei.pluginachievement.ui.AchieveReportActivity.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                bzs.e().loadH5ProApp(AchieveReportActivity.this.b, "com.huawei.health.h5.physical-fitness-test", new H5ProLaunchOption.Builder().addPath("#/testReport?from=4&teamType=" + str + "&resultId=" + str2));
                ViewClickInstrumentation.clickOnView(view);
            }
        });
    }

    private void c(final int i, final int i2) {
        if (CommonUtil.bu()) {
            return;
        }
        findViewById(i).setBackgroundResource(R.drawable._2131427493_res_0x7f0b00a5);
        findViewById(i).setOnClickListener(new View.OnClickListener() { // from class: com.huawei.pluginachievement.ui.AchieveReportActivity.5
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (!mer.e(500)) {
                    int b2 = AchieveReportActivity.this.b(i);
                    String b3 = mlg.b(i2, AchieveReportActivity.this.g);
                    Intent intent = new Intent();
                    intent.setClassName(AchieveReportActivity.this.b, PersonalData.CLASS_NAME_PERSONAL_REPORT_ADVANCED);
                    intent.putExtra("dialogType", b2);
                    intent.putExtra("value", b3);
                    AchieveReportActivity.this.b.startActivity(intent);
                    ViewClickInstrumentation.clickOnView(view);
                    return;
                }
                LogUtil.h("PLGACHIEVE_AchieveReportActivity", "setClickEvent enter isFastClick!");
                ViewClickInstrumentation.clickOnView(view);
            }
        });
    }

    private void c(int i, SingleDayRecord singleDayRecord) {
        int b2 = b(i);
        String b3 = mlg.b(b2, singleDayRecord);
        if (Utils.o() && Utils.i()) {
            c(i, b2, singleDayRecord);
        } else {
            if (!TextUtils.isEmpty(b3)) {
                b(i, b2, b3);
                return;
            }
            e(i);
            i(i);
            j(b2);
        }
    }

    private void c(int i, int i2, SingleDayRecord singleDayRecord) {
        if (mlg.a(i2)) {
            a(i, i2, singleDayRecord);
            return;
        }
        if (mlg.h(i2)) {
            e(i, i2, mlg.b(i2, singleDayRecord));
            return;
        }
        findViewById(i).setVisibility(8);
        findViewById(R.id.report_detail_cylceTitle).setVisibility(8);
        findViewById(R.id.report_detail_npeTitle).setVisibility(8);
        i(i);
        j(i2);
    }

    private void a(int i, int i2, SingleDayRecord singleDayRecord) {
        String d;
        String a2;
        if (singleDayRecord == null) {
            i(i, i2);
            return;
        }
        e(i, R.id.content_desc).setVisibility(8);
        if (i2 == 2) {
            if (singleDayRecord.getSteps() > 0) {
                d(i);
                d = mlf.a(this.b, singleDayRecord.getSteps());
                a2 = mlg.a(singleDayRecord.getStepsDate());
            }
            d = "";
            a2 = "";
        } else if (i2 == 3) {
            if (singleDayRecord.acquireDistance() > 0.0d) {
                d(i);
                chR_(i, R.id.record_arrow).setVisibility(8);
                d = mlf.b(singleDayRecord.acquireDistance(), this.b);
                a2 = mlg.a(singleDayRecord.getDistanceDate());
            }
            d = "";
            a2 = "";
        } else {
            if (i2 == 4) {
                if (singleDayRecord.acquireMatchSpeed() > 0) {
                    d(i);
                    chR_(i, R.id.record_arrow).setVisibility(8);
                    d = mlg.d(singleDayRecord.acquireMatchSpeed());
                    a2 = mlg.a(singleDayRecord.getMatchSpeedDate());
                }
            } else {
                LogUtil.a("PLGACHIEVE_AchieveReportActivity", "initOverSea key not matching");
            }
            d = "";
            a2 = "";
        }
        if (!TextUtils.isEmpty(d) && !TextUtils.isEmpty(d)) {
            e(i, R.id.content_value).setText(d);
            e(i, R.id.record_data).setText(a2);
            c(i, d, a2, null);
        } else {
            e(i);
            i(i);
            if (i2 != 4) {
                j(i2);
            }
        }
    }

    private void i(int i, int i2) {
        e(i);
        i(i);
        j(i2);
    }

    private void c(int i, String str, String str2, String str3) {
        HealthScrollView healthScrollView = this.n;
        if (healthScrollView != null) {
            chU_(healthScrollView, i, R.id.record_data_layout).setVisibility(0);
            a(this.n, i, R.id.content_desc).setVisibility(8);
            a(this.n, i, R.id.content_value).setText(str);
            a(this.n, i, R.id.record_data).setText(str2);
            chT_(this.n, i, R.id.record_arrow).setVisibility(8);
            if (str3 != null) {
                a(this.n, i, R.id.content_unit).setVisibility(0);
            }
        }
    }

    private void i(int i) {
        HealthScrollView healthScrollView = this.n;
        if (healthScrollView != null) {
            healthScrollView.findViewById(i).setVisibility(8);
        }
    }

    private void j(int i) {
        HealthScrollView healthScrollView = this.n;
        if (healthScrollView != null) {
            if (i == 10) {
                healthScrollView.findViewById(R.id.report_detail_cylceTitle).setVisibility(8);
                return;
            }
            if (i == 3) {
                healthScrollView.findViewById(R.id.report_detail_runTitle).setVisibility(8);
                return;
            }
            if (i == 2) {
                healthScrollView.findViewById(R.id.report_detail_stepTitle).setVisibility(8);
            } else if (i == 12) {
                healthScrollView.findViewById(R.id.report_detail_ropeTitle).setVisibility(8);
            } else {
                g(i);
            }
        }
    }

    private void g(int i) {
        if (i == 16) {
            this.n.findViewById(R.id.report_detail_npeTitle).setVisibility(8);
        } else {
            LogUtil.c("PLGACHIEVE_AchieveReportActivity", "initShareModuleView key:", Integer.valueOf(i));
        }
    }

    private HealthTextView a(HealthScrollView healthScrollView, int i, int i2) {
        return (HealthTextView) mfm.cgM_(healthScrollView, i).findViewById(i2);
    }

    private HealthDivider c(HealthScrollView healthScrollView, int i, int i2) {
        return (HealthDivider) mfm.cgM_(healthScrollView, i).findViewById(i2);
    }

    private ImageView chT_(HealthScrollView healthScrollView, int i, int i2) {
        return (ImageView) mfm.cgM_(healthScrollView, i).findViewById(i2);
    }

    private LinearLayout chU_(HealthScrollView healthScrollView, int i, int i2) {
        return (LinearLayout) mfm.cgM_(healthScrollView, i).findViewById(i2);
    }

    private HealthTextView e(int i, int i2) {
        return (HealthTextView) mfm.cgL_(this, i).findViewById(i2);
    }

    private HealthDivider a(int i, int i2) {
        return (HealthDivider) mfm.cgL_(this, i).findViewById(i2);
    }

    private ImageView chR_(int i, int i2) {
        return (ImageView) mfm.cgL_(this, i).findViewById(i2);
    }

    private LinearLayout chS_(int i, int i2) {
        return (LinearLayout) mfm.cgL_(this, i).findViewById(i2);
    }

    private void b(int i, long j, long j2) {
        if (j2 <= j) {
            j2 = 1 + j;
        }
        Pair<Long, Long> pair = new Pair<>(Long.valueOf(j), Long.valueOf(j2));
        if (this.f == null) {
            this.f = new HashMap(4);
        }
        this.f.put(Integer.valueOf(i), pair);
    }

    private Pair<Long, Long> chW_(String str, int i) {
        long j = 0;
        Pair<Long, Long> pair = new Pair<>(0L, 0L);
        try {
            JSONArray jSONArray = new JSONArray(str);
            long j2 = 0;
            long j3 = 0;
            double d = 0.0d;
            for (int i2 = 0; i2 < jSONArray.length(); i2++) {
                JSONObject jSONObject = jSONArray.getJSONObject(i2);
                if (mlg.g(i)) {
                    long d2 = mdn.d("value", jSONObject);
                    if (d2 > j3) {
                        j = mdn.d("startTime", jSONObject);
                        j2 = mdn.d("endTime", jSONObject);
                        LogUtil.a("PLGACHIEVE_AchieveReportActivity", "value=", Long.valueOf(d2), "startTime=", Long.valueOf(j), "endTime=", Long.valueOf(j2));
                        j3 = d2;
                    }
                } else if (!mlg.f(i)) {
                    LogUtil.c("PLGACHIEVE_AchieveReportActivity", "getStartTimeAndEndTime key", Integer.valueOf(i));
                } else {
                    double a2 = mdn.a("value", jSONObject);
                    if (d == 0.0d) {
                        j = mdn.d("startTime", jSONObject);
                        j2 = mdn.d("endTime", jSONObject);
                        d = a2;
                    }
                    if (a2 <= d) {
                        j = mdn.d("startTime", jSONObject);
                        j2 = mdn.d("endTime", jSONObject);
                        d = a2;
                    }
                }
            }
            return new Pair<>(Long.valueOf(j), Long.valueOf(j2));
        } catch (JSONException e) {
            LogUtil.b("PLGACHIEVE_AchieveReportActivity", "getStartTimeAndEndTime Exception:", e.getMessage());
            return pair;
        }
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }
}
