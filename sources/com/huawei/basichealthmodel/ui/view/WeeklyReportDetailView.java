package com.huawei.basichealthmodel.ui.view;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.text.SpannableString;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.core.content.ContextCompat;
import com.huawei.basichealthmodel.R$plurals;
import com.huawei.basichealthmodel.R$string;
import com.huawei.basichealthmodel.callback.ShareClickListener;
import com.huawei.haf.application.BaseApplication;
import com.huawei.health.R;
import com.huawei.health.socialshare.api.SocialShareCenterApi;
import com.huawei.health.userprofilemgr.api.UserProfileMgrApi;
import com.huawei.hiai.awareness.AwarenessConstants;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwcommonmodel.utils.DateFormatUtil;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.openalliance.ad.constant.Constants;
import com.huawei.ui.commonui.R$drawable;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.reportchart.HwHealthReportLineChart;
import defpackage.ayi;
import defpackage.aza;
import defpackage.azi;
import defpackage.bdf;
import defpackage.bdj;
import defpackage.jdl;
import defpackage.koq;
import defpackage.mld;
import defpackage.nrz;
import defpackage.nsn;
import health.compact.a.CommonUtils;
import health.compact.a.LanguageUtil;
import health.compact.a.ReleaseLogUtil;
import health.compact.a.Services;
import java.util.List;

/* loaded from: classes8.dex */
public class WeeklyReportDetailView extends LinearLayout implements View.OnClickListener {

    /* renamed from: a, reason: collision with root package name */
    private int f1928a;
    private Context b;
    private HealthWeekCalendarGroupView c;
    private LinearLayout d;
    private HealthTextView e;
    private View f;
    private HealthTextView g;
    private HealthTextView h;
    private int i;
    private HwHealthReportLineChart j;
    private boolean k;
    private boolean l;
    private boolean m;
    private boolean n;
    private boolean o;
    private HealthTextView p;
    private ImageView q;
    private ShareClickListener r;
    private ImageView s;
    private ImageView t;
    private HealthTextView x;

    public WeeklyReportDetailView(Context context) {
        this(context, null);
    }

    public WeeklyReportDetailView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, -1);
    }

    public WeeklyReportDetailView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.o = false;
        this.k = false;
        this.m = false;
        this.l = false;
        this.b = context;
        lF_(context, attributeSet);
        d(context);
    }

    private void lF_(Context context, AttributeSet attributeSet) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, new int[]{R.attr._2131099861_res_0x7f0600d5, R.attr._2131100675_res_0x7f060403, R.attr._2131100961_res_0x7f060521, R.attr._2131101097_res_0x7f0605a9});
        if (obtainStyledAttributes == null) {
            return;
        }
        try {
            this.o = obtainStyledAttributes.getBoolean(2, false);
            this.k = obtainStyledAttributes.getBoolean(3, false);
            this.m = obtainStyledAttributes.getBoolean(0, false);
            this.l = obtainStyledAttributes.getBoolean(1, false);
        } catch (Resources.NotFoundException unused) {
            LogUtil.b("HealthLife_WeeklyReportDetailView", "init NotFoundException");
        }
        obtainStyledAttributes.recycle();
    }

    private void d(Context context) {
        Object systemService = context.getSystemService("layout_inflater");
        if (!(systemService instanceof LayoutInflater)) {
            LogUtil.h("HealthLife_WeeklyReportDetailView", "initView object is null or not LayoutInflater");
            return;
        }
        View inflate = ((LayoutInflater) systemService).inflate(R.layout.weekly_report_detail_layout, this);
        this.h = (HealthTextView) inflate.findViewById(R.id.weekly_report_date_tv);
        this.x = (HealthTextView) inflate.findViewById(R.id.weekly_report_num_tv);
        this.s = (ImageView) inflate.findViewById(R.id.weekly_report_share_iv);
        this.q = (ImageView) inflate.findViewById(R.id.weekly_report_share_head_icon);
        this.s.setOnClickListener(this);
        inflate.findViewById(R.id.weekly_report_detail).setOnClickListener(this);
        this.t = (ImageView) inflate.findViewById(R.id.weekly_report_tips_icon);
        this.p = (HealthTextView) inflate.findViewById(R.id.weekly_report_tips_tv);
        this.e = (HealthTextView) inflate.findViewById(R.id.weekly_report_management_tv);
        this.c = (HealthWeekCalendarGroupView) inflate.findViewById(R.id.week_calendar_group_view);
        this.f = inflate.findViewById(R.id.weekly_report_interval_view);
        this.d = (LinearLayout) inflate.findViewById(R.id.weekly_report_chart_layout);
        this.j = (HwHealthReportLineChart) inflate.findViewById(R.id.history_line_chart);
        this.g = (HealthTextView) inflate.findViewById(R.id.history_line_chart_tips_tv);
        lG_(inflate);
    }

    private void lG_(View view) {
        int i;
        if (this.k) {
            if (this.l) {
                this.s.setVisibility(8);
                this.q.setVisibility(0);
                HealthTextView healthTextView = (HealthTextView) view.findViewById(R.id.weekly_report_share_user_name_tv);
                SocialShareCenterApi socialShareCenterApi = (SocialShareCenterApi) Services.c("PluginSocialShare", SocialShareCenterApi.class);
                String shareNickName = socialShareCenterApi.getShareNickName();
                if (!TextUtils.isEmpty(shareNickName)) {
                    healthTextView.setText(shareNickName);
                    healthTextView.setVisibility(0);
                }
                socialShareCenterApi.updateShareUserView("HealthLife_WeeklyReportDetailView", this.q, healthTextView);
                return;
            }
            this.s.setVisibility(0);
            Context e = BaseApplication.e();
            ImageView imageView = this.s;
            if (LanguageUtil.bc(e)) {
                i = R$drawable.ic_health_nav_share_black_rt;
            } else {
                i = R$drawable.ic_health_nav_share_black;
            }
            imageView.setImageResource(i);
            this.q.setVisibility(8);
            return;
        }
        this.s.setVisibility(8);
        this.q.setVisibility(8);
    }

    public void b(List<ayi> list, int i, boolean z, boolean z2) {
        LogUtil.a("HealthLife_WeeklyReportDetailView", "updateWeekView", list.toString(), " ,percent", Integer.valueOf(i), " ,isCurrentWeek", Boolean.valueOf(z), " ,isFromHistoryPage", Boolean.valueOf(z2));
        if (koq.b(list)) {
            LogUtil.h("HealthLife_WeeklyReportDetailView", "initialViewData, recentWeeklyData is empty");
            return;
        }
        if (z2) {
            this.s.setVisibility(8);
            this.x.setVisibility(8);
        }
        ayi ayiVar = list.get(0);
        if (z) {
            d(ayiVar, i, true);
            return;
        }
        if (this.k && this.l) {
            this.x.setVisibility(0);
            UserProfileMgrApi userProfileMgrApi = (UserProfileMgrApi) Services.a("HWUserProfileMgr", UserProfileMgrApi.class);
            if (userProfileMgrApi == null) {
                LogUtil.h("HealthLife_WeeklyReportDetailView", "getHeadBitmap : userProfileMgrApi is null.");
                return;
            } else {
                Bitmap headBitmap = userProfileMgrApi.getHeadBitmap(this.b);
                if (headBitmap != null) {
                    this.q.setImageBitmap(headBitmap);
                }
            }
        }
        if (!z2 && list.size() > 1) {
            ayiVar = list.get(1);
        }
        d(ayiVar, i, false);
        if (!z2) {
            list.remove(list.get(0));
        }
        if (this.m && koq.c(list) && list.size() > 1) {
            LogUtil.a("HealthLife_WeeklyReportDetailView", "initViewData recentWeeklyData more than one week data");
            this.d.setVisibility(0);
            this.f.setVisibility(0);
            bdj.b(this.b, this.g, this.j, list);
            return;
        }
        LogUtil.a("HealthLife_WeeklyReportDetailView", "initViewData recentWeeklyData can not show line chart layout");
    }

    private void d(ayi ayiVar, int i, boolean z) {
        LogUtil.a("HealthLife_WeeklyReportDetailView", "updateWeekView", ayiVar.toString(), " ,percent", Integer.valueOf(i), " ,isNowWeek", Boolean.valueOf(z));
        this.p.setText(lE_(ayiVar.b(), i));
        this.c.d(ayiVar.d(), ayiVar.a(), ayiVar.i(), z);
        setWeekDate(ayiVar);
        setWeekNumber(ayiVar);
        setWeeklyReport("", false, z, ayiVar.a());
    }

    public void setWeeklyReport(String str, boolean z, boolean z2, int i) {
        if (!z2 && (z || !TextUtils.isEmpty(str))) {
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(mld.d(this.b, 86.5f), mld.d(this.b, 86.5f));
            layoutParams.setMargins(mld.d(this.b, 20.0f), mld.d(this.b, 12.0f), mld.d(this.b, 12.0f), 0);
            this.t.setLayoutParams(layoutParams);
            this.e.setVisibility(0);
            this.i = i;
            this.f1928a = CommonUtils.h(str);
            this.n = z;
            setChallengeTextAndPic(z);
            return;
        }
        this.e.setVisibility(8);
        this.t.setImageDrawable(ContextCompat.getDrawable(this.b, R.drawable._2131428419_res_0x7f0b0443));
        LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(mld.d(this.b, 75.0f), mld.d(this.b, 75.0f));
        layoutParams2.setMargins(mld.d(this.b, 20.0f), mld.d(this.b, 4.0f), mld.d(this.b, 24.0f), 0);
        this.t.setLayoutParams(layoutParams2);
    }

    private void setChallengeTextAndPic(boolean z) {
        Drawable drawable;
        Drawable drawable2;
        Drawable drawable3;
        Drawable drawable4;
        Drawable drawable5;
        Drawable drawable6;
        Context e = BaseApplication.e();
        boolean z2 = LanguageUtil.bc(e) && !LanguageUtil.bp(e);
        if (z) {
            this.e.setText(R$string.IDS_week_report_health_two);
            ImageView imageView = this.t;
            if (z2) {
                drawable6 = nrz.cKn_(this.b, R.drawable._2131428429_res_0x7f0b044d);
            } else {
                drawable6 = ContextCompat.getDrawable(this.b, R.drawable._2131428429_res_0x7f0b044d);
            }
            imageView.setImageDrawable(drawable6);
        }
        int i = this.f1928a;
        switch (i) {
            case AwarenessConstants.ERROR_INVALID_FREQUENCY_CODE /* 200002 */:
                this.e.setText(R$string.IDS_week_report_anxiety);
                ImageView imageView2 = this.t;
                if (z2) {
                    drawable = nrz.cKn_(this.b, R.drawable._2131428427_res_0x7f0b044b);
                } else {
                    drawable = ContextCompat.getDrawable(this.b, R.drawable._2131428427_res_0x7f0b044b);
                }
                imageView2.setImageDrawable(drawable);
                break;
            case AwarenessConstants.ERROR_TIMEOUT_CODE /* 200003 */:
                this.e.setText(R$string.IDS_week_report_weight);
                ImageView imageView3 = this.t;
                if (z2) {
                    drawable2 = nrz.cKn_(this.b, R.drawable._2131428432_res_0x7f0b0450);
                } else {
                    drawable2 = ContextCompat.getDrawable(this.b, R.drawable._2131428432_res_0x7f0b0450);
                }
                imageView3.setImageDrawable(drawable2);
                break;
            case AwarenessConstants.ERROR_UNKNOWN_CODE /* 200004 */:
                this.e.setText(R$string.IDS_week_report_sleep);
                ImageView imageView4 = this.t;
                if (z2) {
                    drawable3 = nrz.cKn_(this.b, R.drawable._2131428431_res_0x7f0b044f);
                } else {
                    drawable3 = ContextCompat.getDrawable(this.b, R.drawable._2131428431_res_0x7f0b044f);
                }
                imageView4.setImageDrawable(drawable3);
                break;
            case AwarenessConstants.ERROR_LIMITED_REGISTRY_CODE /* 200005 */:
                this.e.setText(R$string.IDS_week_report_immunity);
                ImageView imageView5 = this.t;
                if (z2) {
                    drawable4 = nrz.cKn_(this.b, R.drawable._2131428428_res_0x7f0b044c);
                } else {
                    drawable4 = ContextCompat.getDrawable(this.b, R.drawable._2131428428_res_0x7f0b044c);
                }
                imageView5.setImageDrawable(drawable4);
                break;
            case AwarenessConstants.ERROR_CANCEL_REGISTRY_CODE /* 200006 */:
            case AwarenessConstants.ERROR_PARAMETER_CODE /* 200007 */:
            case AwarenessConstants.ERROR_REGISTER_SAME_FENCE_CODE /* 200008 */:
                this.e.setText(R$string.IDS_week_report_blood_pressure);
                ImageView imageView6 = this.t;
                if (z2) {
                    drawable5 = nrz.cKn_(this.b, R.drawable._2131428430_res_0x7f0b044e);
                } else {
                    drawable5 = ContextCompat.getDrawable(this.b, R.drawable._2131428430_res_0x7f0b044e);
                }
                imageView6.setImageDrawable(drawable5);
                break;
            default:
                LogUtil.h("HealthLife_WeeklyReportDetailView", "setChallengeTextAndPic mChallengeId ", Integer.valueOf(i));
                break;
        }
    }

    private SpannableString lE_(int i, int i2) {
        return azi.lI_(i, i2, (i2 == 0 || i == 0) ? 1 : 2);
    }

    private void setWeekDate(ayi ayiVar) {
        int d = ayiVar.d();
        int a2 = ayiVar.a();
        StringBuilder sb = new StringBuilder();
        sb.append(bdf.d(String.valueOf(d), 4));
        sb.append(Constants.LINK);
        sb.append(bdf.d(String.valueOf(a2), 4));
        this.h.setText(sb);
    }

    private void setWeekNumber(ayi ayiVar) {
        int g = ayiVar.g();
        String quantityString = this.b.getResources().getQuantityString(R$plurals.IDS_health_model_week_time, g, Integer.valueOf(g));
        String string = this.b.getResources().getString(R$string.IDS_weekly_report_progress, quantityString, this.b.getResources().getString(R$string.IDS_processing_has_brackets));
        if (this.o) {
            this.x.setText(string);
        } else {
            this.x.setText(quantityString);
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        ReleaseLogUtil.b("HealthLife_WeeklyReportDetailView", "onClick view ", view);
        if (view == null || nsn.cLk_(view)) {
            ViewClickInstrumentation.clickOnView(view);
            return;
        }
        int id = view.getId();
        if (id == R.id.weekly_report_share_iv) {
            this.r.shareClickCallback();
        }
        if (this.e.getVisibility() == 0 && id == R.id.weekly_report_detail) {
            aza.e(4, this.n ? 1 : this.f1928a);
            azi.e(this.b, DateFormatUtil.b(jdl.c(DateFormatUtil.c(this.i), 1, 0)));
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    public void setListener(ShareClickListener shareClickListener) {
        this.r = shareClickListener;
    }
}
