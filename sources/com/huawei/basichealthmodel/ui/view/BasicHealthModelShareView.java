package com.huawei.basichealthmodel.ui.view;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.TypefaceSpan;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.huawei.basichealthmodel.R$string;
import com.huawei.haf.application.BaseApplication;
import com.huawei.health.R;
import com.huawei.health.socialshare.api.SocialShareCenterApi;
import com.huawei.hwcommonmodel.utils.DateFormatUtil;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import defpackage.azi;
import defpackage.bdf;
import defpackage.nrf;
import health.compact.a.LanguageUtil;
import health.compact.a.Services;
import health.compact.a.Utils;
import health.compact.a.utils.StringUtils;

/* loaded from: classes8.dex */
public class BasicHealthModelShareView extends FrameLayout {

    /* renamed from: a, reason: collision with root package name */
    protected final Context f1921a;
    protected final Resources b;
    protected HealthTextView c;
    protected TextView d;
    protected LinearLayout e;
    protected HealthTextView f;
    protected ImageView g;
    protected FrameLayout h;
    protected ConstraintLayout i;
    protected HealthTextView j;
    protected HealthTextView l;

    public BasicHealthModelShareView(Context context) {
        super(context);
        Context e = BaseApplication.e();
        this.f1921a = e;
        this.b = e.getResources();
        d(context);
    }

    public BasicHealthModelShareView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        Context e = BaseApplication.e();
        this.f1921a = e;
        this.b = e.getResources();
        d(context);
    }

    public BasicHealthModelShareView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        Context e = BaseApplication.e();
        this.f1921a = e;
        this.b = e.getResources();
        d(context);
    }

    private void d(Context context) {
        View inflate = View.inflate(context, R.layout.health_model_share_layout, this);
        this.e = (LinearLayout) inflate.findViewById(R.id.health_model_share_ll);
        this.h = (FrameLayout) inflate.findViewById(R.id.health_model_task_share_bg);
        this.i = (ConstraintLayout) findViewById(R.id.health_model_share_bg);
        this.g = (ImageView) findViewById(R.id.health_model_share_head_icon);
        this.l = (HealthTextView) findViewById(R.id.health_model_share_user_name);
        HealthTextView healthTextView = (HealthTextView) findViewById(R.id.health_model_share_date);
        this.j = healthTextView;
        azi.e(healthTextView, R.dimen._2131362869_res_0x7f0a0435, 1.2f);
        HealthTextView healthTextView2 = (HealthTextView) findViewById(R.id.health_model_share_join);
        this.c = healthTextView2;
        azi.e(healthTextView2, R.dimen._2131362906_res_0x7f0a045a, 1.2f);
        this.f = (HealthTextView) findViewById(R.id.health_model_share_people);
        this.d = (TextView) findViewById(R.id.health_model_share_benefit);
        c();
    }

    private void c() {
        LoginInit loginInit = LoginInit.getInstance(this.f1921a);
        Bitmap cIe_ = nrf.cIe_(this.f1921a, loginInit.getHeadImagePath());
        if (cIe_ == null) {
            this.g.setImageResource(R.mipmap._2131821050_res_0x7f1101fa);
        } else {
            this.g.setImageBitmap(cIe_);
        }
        this.g.setVisibility(Utils.i() ? 0 : 8);
        String accountInfo = loginInit.getAccountInfo(1002);
        if (!TextUtils.isEmpty(accountInfo)) {
            if (LanguageUtil.bc(this.f1921a) && (StringUtils.b(accountInfo) || StringUtils.d(accountInfo))) {
                this.l.setTextDirection(3);
            }
            this.l.setText(accountInfo);
        } else {
            this.l.setText(R$string.IDS_share_nick_default_name);
        }
        this.j.setText(bdf.d(String.valueOf(DateFormatUtil.b(System.currentTimeMillis())), 4));
        e();
    }

    private void e() {
        ((SocialShareCenterApi) Services.c("PluginSocialShare", SocialShareCenterApi.class)).updateShareUserView("HealthLife_BasicHealthModelShareView", this.g, this.l, this.j);
    }

    public void d() {
        e();
        Bitmap cHO_ = nrf.cHO_(this.e);
        if (cHO_ == null) {
            LogUtil.h("HealthLife_BasicHealthModelShareView", "shareReport bitmap is null");
        } else if (cHO_.isRecycled()) {
            LogUtil.h("HealthLife_BasicHealthModelShareView", "shareReport bitmap is recycled");
        } else {
            bdf.nW_(getContext(), cHO_, 1);
        }
    }

    public void setBackground(Bitmap bitmap) {
        this.h.setBackground(new BitmapDrawable(this.b, ly_(bitmap)));
    }

    protected Bitmap ly_(Bitmap bitmap) {
        int width = this.e.getWidth();
        int height = this.e.getHeight();
        Bitmap cJJ_ = nrf.cJJ_(bitmap, width, height);
        int min = Math.min(width, cJJ_.getWidth());
        return nrf.cHv_(cJJ_, min, Math.min(height, (min * height) / width), 3);
    }

    protected CharSequence d(String str, String str2, String str3) {
        return a(str, str2, str3, true);
    }

    protected CharSequence a(String str, String str2, String str3, boolean z) {
        SpannableString spannableString = new SpannableString(str);
        if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2) && !TextUtils.isEmpty(str3) && str.contains(str2) && str.contains(str3)) {
            int indexOf = str.indexOf(str3);
            int length = str3.length() + indexOf;
            spannableString.setSpan(new AbsoluteSizeSpan(40, true), indexOf, length, 17);
            spannableString.setSpan(new TypefaceSpan(this.b.getString(com.huawei.ui.commonui.R$string.textFontFamilyRegular)), indexOf, length, 17);
            if (z) {
                int indexOf2 = str.indexOf(str2);
                spannableString.setSpan(new AbsoluteSizeSpan(20, true), indexOf2, str2.length() + indexOf2, 17);
            }
        }
        return spannableString;
    }
}
