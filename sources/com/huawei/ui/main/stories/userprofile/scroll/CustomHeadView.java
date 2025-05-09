package com.huawei.ui.main.stories.userprofile.scroll;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ViewFlipper;
import androidx.core.content.ContextCompat;
import com.apprichtap.haptic.base.PrebakedEffectId;
import com.huawei.haf.router.AppRouter;
import com.huawei.health.R;
import com.huawei.health.vip.datatypes.UserMemberInfo;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwcommonmodel.utils.DateFormatUtil;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.trade.datatype.DeviceBenefits;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.button.HealthButton;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.main.R$string;
import com.huawei.up.model.UserInfomation;
import defpackage.dqj;
import defpackage.eil;
import defpackage.gpn;
import defpackage.gpo;
import defpackage.ixx;
import defpackage.izx;
import defpackage.jcf;
import defpackage.koq;
import defpackage.mct;
import defpackage.mcv;
import defpackage.mcx;
import defpackage.mlc;
import defpackage.nkx;
import defpackage.nrf;
import defpackage.nrt;
import defpackage.nsf;
import defpackage.nsj;
import defpackage.nsn;
import defpackage.sas;
import defpackage.sbc;
import health.compact.a.CommonUtil;
import health.compact.a.LanguageUtil;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.UnitUtil;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.IllegalFormatConversionException;
import java.util.Iterator;
import java.util.List;
import java.util.MissingFormatArgumentException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/* loaded from: classes7.dex */
public class CustomHeadView extends LinearLayout implements View.OnClickListener {

    /* renamed from: a, reason: collision with root package name */
    private HealthTextView f10533a;
    private ImageView aa;
    private ViewFlipper ab;
    private HealthTextView ac;
    private ImageView ad;
    private HealthTextView ai;
    private Context b;
    private int c;
    private LinearLayout d;
    private HealthButton e;
    private boolean f;
    private ImageView g;
    private HealthTextView h;
    private HealthTextView i;
    private View j;
    private HealthTextView k;
    private PersonalLevelProgress l;
    private HealthTextView m;
    private HealthTextView n;
    private HealthTextView o;
    private ExecutorService p;
    private LinearLayout q;
    private View r;
    private View s;
    private View t;
    private ImageView u;
    private ImageView v;
    private HealthTextView w;
    private String x;
    private HealthTextView y;
    private FrameLayout z;

    public CustomHeadView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.g = null;
        this.b = null;
        this.c = -1;
        this.f = false;
        d(context);
    }

    private void d(Context context) {
        this.b = context;
        dVB_(LayoutInflater.from(context).inflate(R.layout.personal_center_head_info_card, this));
    }

    private void dVB_(View view) {
        this.d = (LinearLayout) view.findViewById(R.id.gone_layout);
        this.g = (ImageView) view.findViewById(R.id.account_head_picture);
        if (nrt.a(this.b) || (BaseActivity.isFlyme() && nsn.v(this.b))) {
            this.g.getBackground().setAlpha(51);
        }
        this.f10533a = (HealthTextView) view.findViewById(R.id.account_nickname);
        this.q = (LinearLayout) view.findViewById(R.id.level_progress_layout);
        this.o = (HealthTextView) view.findViewById(R.id.personal_level_desc);
        this.k = (HealthTextView) view.findViewById(R.id.personal_level_growth_cn);
        this.m = (HealthTextView) view.findViewById(R.id.personal_level_growth_other);
        this.l = (PersonalLevelProgress) view.findViewById(R.id.personal_level_progress);
        this.j = findViewById(R.id.user_profile_mycal_layout);
        this.r = findViewById(R.id.rank_view);
        this.s = findViewById(R.id.rank_divider);
        this.y = (HealthTextView) findViewById(R.id.user_profile_mycal_num);
        this.h = (HealthTextView) findViewById(R.id.kaka_to_be_collected_text);
        this.w = (HealthTextView) findViewById(R.id.user_profile_user_rank_text);
        this.u = (ImageView) findViewById(R.id.user_profile_user_rank_img);
        this.v = (ImageView) findViewById(R.id.rank_red_point);
        this.z = (FrameLayout) findViewById(R.id.vip_layout);
        this.aa = (ImageView) findViewById(R.id.vip_card_wavy_image);
        this.ad = (ImageView) findViewById(R.id.vip_state_icon);
        this.ai = (HealthTextView) findViewById(R.id.vip_text);
        this.ac = (HealthTextView) findViewById(R.id.vip_description);
        this.ab = (ViewFlipper) findViewById(R.id.vip_inbox_layout);
        this.t = findViewById(R.id.vip_top_stroke);
        if (nsn.r()) {
            ((HealthButton) findViewById(R.id.goto_vip_center)).setVisibility(8);
            HealthButton healthButton = (HealthButton) findViewById(R.id.goto_vip_center_large);
            this.e = healthButton;
            healthButton.setVisibility(0);
            nsn.b(this.ai);
        } else {
            ((HealthButton) findViewById(R.id.goto_vip_center_large)).setVisibility(8);
            this.e = (HealthButton) findViewById(R.id.goto_vip_center);
        }
        this.i = (HealthTextView) findViewById(R.id.kaka_content_text);
        this.n = (HealthTextView) findViewById(R.id.level_content_text);
        if (!gpn.d() || CommonUtil.bu()) {
            this.z.setVisibility(8);
            this.ad.setVisibility(8);
        }
        this.ac.setText(this.b.getString(R$string.IDS_user_member_not_vip_tip));
        e();
    }

    public void setOnClickListener(Context context, View.OnClickListener onClickListener) {
        this.b = context;
        this.f = LoginInit.getInstance(context).isBrowseMode();
        Context context2 = this.b;
        if (context2 instanceof BaseActivity) {
            if (CommonUtil.z(context2)) {
                this.g.setOnClickListener(nkx.cwZ_(onClickListener, (BaseActivity) this.b, true, ""));
                this.f10533a.setOnClickListener(nkx.cwZ_(onClickListener, (BaseActivity) this.b, true, ""));
            } else {
                this.g.setOnClickListener(onClickListener);
                this.f10533a.setOnClickListener(onClickListener);
            }
            this.j.setOnClickListener(nkx.cwZ_(this, (BaseActivity) this.b, true, ""));
            this.r.setOnClickListener(nkx.cwZ_(this, (BaseActivity) this.b, true, ""));
            this.ad.setOnClickListener(nkx.cwZ_(this, (BaseActivity) this.b, true, ""));
            this.e.setOnClickListener(nkx.cwZ_(this, (BaseActivity) this.b, true, ""));
            this.z.setOnClickListener(nkx.cwZ_(this, (BaseActivity) this.b, true, ""));
            this.q.setOnClickListener(nkx.cwZ_(this, (BaseActivity) this.b, true, ""));
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (nsn.cLk_(view)) {
            LogUtil.a("CustomHeadView", "click too fast");
            ViewClickInstrumentation.clickOnView(view);
            return;
        }
        if (view == this.r || view == this.q) {
            sas.a(this.b, 1);
        } else if (view == this.j) {
            sas.e(this.b);
            mcv.d(this.b).b(this.b);
            int visibility = this.h.getVisibility();
            HashMap hashMap = new HashMap();
            hashMap.put("click", 1);
            hashMap.put("status", Integer.valueOf(visibility != 0 ? 0 : 1));
            ixx.d().d(BaseApplication.getContext(), AnalyticsValue.HEALTH_MINE_MY_KAKA_2040011.value(), hashMap, 0);
            HashMap hashMap2 = new HashMap();
            hashMap2.put("from", "0");
            ixx.d().d(BaseApplication.getContext(), AnalyticsValue.SUCCESSES_KAKA_1100007.value(), hashMap2, 0);
        } else {
            FrameLayout frameLayout = this.z;
            if (view == frameLayout || view == this.e || view == this.ad) {
                if (view == frameLayout && this.ab.getVisibility() == 0) {
                    ViewClickInstrumentation.clickOnView(view);
                    return;
                }
                a(view == this.ad ? 2 : 1);
            } else {
                LogUtil.h("CustomHeadView", "unknow click");
            }
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    public void setHeadImageByResourceId(int i) {
        this.g.setImageResource(i);
    }

    public void setHeadImageByBitmap(Bitmap bitmap) {
        this.g.setImageBitmap(bitmap);
    }

    public void setHeadPictureView(UserInfomation userInfomation) {
        String a2 = sbc.a(userInfomation);
        if (!TextUtils.isEmpty(a2) && !"default".equals(a2)) {
            Bitmap cIe_ = nrf.cIe_(this.b, a2);
            if (cIe_ != null) {
                this.g.setImageBitmap(cIe_);
                return;
            } else {
                a(userInfomation);
                LogUtil.a("CustomHeadView", "setHeadPictureView() btimap is null");
                return;
            }
        }
        LogUtil.a("CustomHeadView", "setHeadPictureView()! headImgPath is null! ");
        setHeadImageByResourceId(R.mipmap._2131821050_res_0x7f1101fa);
    }

    public void setAccountNickName(String str) {
        LogUtil.a("CustomHeadView", "setAccountNickName name:", str);
        if (TextUtils.isEmpty(str) || TextUtils.equals(this.x, str)) {
            return;
        }
        this.x = str;
        this.f10533a.setText(str);
    }

    public void b(int i, boolean z) {
        int h = mlc.h(i);
        LogUtil.a("CustomHeadView", "enter updateUserRank(): level = ", Integer.valueOf(i), ", levelType = ", Integer.valueOf(h));
        try {
            int min = Math.min(20, i);
            this.w.setText(this.b.getResources().getString(R$string.IDS_plugin_achievement_level_value_string, Integer.valueOf(min)));
            jcf.bEz_(this.r, nsf.b(R$string.accessibility_level, Integer.valueOf(min)));
        } catch (IllegalFormatConversionException | MissingFormatArgumentException unused) {
            LogUtil.b("CustomHeadView", "string format exception");
        }
        this.u.setImageResource(mlc.d(h));
        this.v.setVisibility(z ? 0 : 8);
        this.o.setText(mlc.b(h));
        c(i);
    }

    /* JADX WARN: Can't wrap try/catch for region: R(15:0|1|(2:3|(11:5|6|(1:28)(1:12)|13|14|15|(1:17)(1:25)|18|(1:20)(1:24)|21|22))|29|6|(1:8)|28|13|14|15|(0)(0)|18|(0)(0)|21|22) */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x00b8, code lost:
    
        com.huawei.hwlogsmodel.LogUtil.b("CustomHeadView", "string format exception");
     */
    /* JADX WARN: Removed duplicated region for block: B:17:0x00cb  */
    /* JADX WARN: Removed duplicated region for block: B:20:0x00e3  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x00eb  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x00d6  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void c(int r11) {
        /*
            Method dump skipped, instructions count: 257
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.ui.main.stories.userprofile.scroll.CustomHeadView.c(int):void");
    }

    public void b(int i) {
        this.h.setVisibility(i);
    }

    public void b(String str) {
        this.y.setText(str);
        HealthTextView healthTextView = this.h;
        if (healthTextView != null && healthTextView.getVisibility() == 0) {
            CharSequence text = this.h.getText();
            if (!TextUtils.isEmpty(text)) {
                str = str + "," + ((Object) text);
            }
        }
        jcf.bEz_(this.j, str + "," + nsf.h(R$string.IDS_hwh_me_achieve_kaka));
    }

    public void e(int i) {
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.head_layout);
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) linearLayout.getLayoutParams();
        if (i == 0) {
            layoutParams.topMargin = this.b.getResources().getDimensionPixelOffset(R.dimen._2131363094_res_0x7f0a0516);
        } else {
            layoutParams.topMargin = 0;
        }
        linearLayout.setLayoutParams(layoutParams);
        this.d.setVisibility(i);
        this.q.setVisibility(i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void dVC_(final Bitmap bitmap) {
        this.f10533a.post(new Runnable() { // from class: com.huawei.ui.main.stories.userprofile.scroll.CustomHeadView.5
            @Override // java.lang.Runnable
            public void run() {
                Bitmap dVD_ = CustomHeadView.this.dVD_(bitmap, 200);
                if (dVD_ == null) {
                    LogUtil.h("CustomHeadView", "resize triggered bitmap fail!");
                } else {
                    CustomHeadView.this.g.setImageBitmap(nrf.cHX_(dVD_));
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Bitmap dVD_(Bitmap bitmap, int i) {
        Matrix matrix = new Matrix();
        matrix.setScale(1.0f, 1.0f);
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        if (width >= i || height >= i) {
            matrix.setScale(0.5f, 0.5f);
            width /= 2;
            height /= 2;
        }
        return Bitmap.createBitmap(bitmap, 0, 0, width, height, matrix, true);
    }

    private void a(UserInfomation userInfomation) {
        String b2 = sbc.b(userInfomation);
        if (b2 == null) {
            LogUtil.h("CustomHeadView", "it's so terrible, as missing the headImage url, we can do nothing!");
            return;
        }
        if (this.p == null) {
            this.p = Executors.newSingleThreadExecutor();
        }
        ExecutorService executorService = this.p;
        if (executorService.submit(new b(b2, executorService, this)).isDone()) {
            LogUtil.a("CustomHeadView", "ImgRequestRunnable submit isDone");
        }
    }

    private void e() {
        if (LanguageUtil.ar(this.b)) {
            this.i.setTextSize(0, nsn.c(this.b, 12.0f));
            this.n.setTextSize(0, nsn.c(this.b, 12.0f));
        }
    }

    static class b implements Runnable {

        /* renamed from: a, reason: collision with root package name */
        private ExecutorService f10534a;
        private final WeakReference<CustomHeadView> b;
        private String d;

        b(String str, ExecutorService executorService, CustomHeadView customHeadView) {
            this.d = str;
            this.f10534a = executorService;
            this.b = new WeakReference<>(customHeadView);
        }

        @Override // java.lang.Runnable
        public void run() {
            CustomHeadView customHeadView = this.b.get();
            if (customHeadView == null) {
                return;
            }
            Bitmap cHT_ = nrf.cHT_(customHeadView.b, this.d);
            if (cHT_ != null) {
                customHeadView.dVC_(cHT_);
                LogUtil.a("CustomHeadView", "get remote head img success send message to update ui");
                if (this.f10534a.submit(new d(customHeadView.b, cHT_)).isDone()) {
                    LogUtil.a("CustomHeadView", "ImgSaveRunnable submit isDone");
                    return;
                }
                return;
            }
            LogUtil.h("CustomHeadView", "it's overwhelmingly horrible, we can even get the head image from nowhere!");
        }
    }

    static class d implements Runnable {
        private Bitmap d;
        private final WeakReference<Context> e;

        d(Context context, Bitmap bitmap) {
            this.e = new WeakReference<>(context);
            this.d = bitmap;
        }

        private boolean a() {
            Context context = this.e.get();
            if (context == null) {
                return false;
            }
            String accountInfo = LoginInit.getInstance(context).getAccountInfo(1011);
            if (TextUtils.isEmpty(accountInfo)) {
                LogUtil.h("CustomHeadView", "it's strange that we get img bitmap, unkowning the userid ");
                return false;
            }
            if (izx.bEe_(context, accountInfo, this.d) != null) {
                return true;
            }
            LogUtil.b("CustomHeadView", "triggered request success but keeping behavior fail!");
            return false;
        }

        @Override // java.lang.Runnable
        public void run() {
            LogUtil.a("CustomHeadView", "enter triggred img to save runnable");
            LogUtil.a("CustomHeadView", "Finally,the triggered header img is cached successfully? ", Boolean.valueOf(a()));
        }
    }

    public boolean c() {
        String b2 = mct.b(this.b, "kakaLastCheckInTime");
        LogUtil.a("CustomHeadView", "lastTime = ", b2);
        if (TextUtils.isEmpty(b2)) {
            return true;
        }
        return mcx.d(b2);
    }

    public void e(boolean z, boolean z2) {
        if (this.z == null || this.s == null) {
            LogUtil.h("CustomHeadView", "showItems page not init");
            return;
        }
        int i = z ? 8 : 0;
        this.j.setVisibility(i);
        this.i.setVisibility(i);
        this.r.setVisibility(0);
        this.s.setVisibility(i);
        if (!z2 || CommonUtil.bu() || z) {
            this.z.setVisibility(8);
            this.ad.setVisibility(8);
        } else {
            this.z.setVisibility(0);
            this.ad.setVisibility(0);
        }
    }

    public boolean c(UserMemberInfo userMemberInfo) {
        boolean isBrowseMode = LoginInit.getInstance(this.b).isBrowseMode();
        this.f = isBrowseMode;
        if (isBrowseMode) {
            LogUtil.h("CustomHeadView", "updateVipInfo is browseMode");
            a((List<DeviceBenefits>) null);
        }
        String string = this.b.getString(R$string.IDS_user_member_not_vip_tip);
        String string2 = this.b.getString(R$string.IDS_user_vip_learn_privilege);
        boolean z = false;
        if (userMemberInfo != null) {
            LogUtil.a("CustomHeadView", "mUserMemberInfo type = ", Integer.valueOf(userMemberInfo.getMemberFlag()));
            this.c = userMemberInfo.getMemberFlag();
            int memberFlag = userMemberInfo.getMemberFlag();
            if (memberFlag == -1) {
                string = this.b.getString(R$string.IDS_user_member_not_vip_tip);
                string2 = this.b.getString(R$string.IDS_user_vip_learn_privilege);
            } else if (memberFlag != 1) {
                LogUtil.h("CustomHeadView", "mUserMemberInfo error type");
            } else if (userMemberInfo.getExpireTime() >= userMemberInfo.getNowTime()) {
                string = a(userMemberInfo);
                string2 = this.b.getString(R$string.IDS_user_vip_center);
                z = true;
            } else {
                string = this.b.getString(R$string.IDS_user_vip_expired);
                this.c = 3;
                string2 = this.b.getString(R$string.IDS_user_vip_renew);
            }
        } else {
            LogUtil.h("CustomHeadView", "mUserMemberInfo is null");
        }
        b(string, z, string2);
        String accountInfo = LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1011);
        SharedPreferenceManager.e(Integer.toString(PrebakedEffectId.RT_AWARD), accountInfo + "key_is_vip", z);
        return z;
    }

    private void b(String str, boolean z, String str2) {
        this.ac.setText(str);
        this.e.setText(str2);
        if (z) {
            this.ai.setTextColor(ContextCompat.getColor(this.b, R.color._2131299312_res_0x7f090bf0));
            this.ac.setTextColor(ContextCompat.getColor(this.b, R.color._2131299312_res_0x7f090bf0));
            this.ad.setImageResource(R.mipmap._2131821006_res_0x7f1101ce);
            jcf.bEz_(this.ad, nsf.h(R$string.accessibility_is_vip));
            dVA_(this.aa, R.mipmap._2131821430_res_0x7f110376);
            this.z.setBackgroundResource(R.drawable._2131430974_res_0x7f0b0e3e);
            this.t.setVisibility(0);
            return;
        }
        this.ai.setTextColor(ContextCompat.getColor(this.b, R.color._2131298788_res_0x7f0909e4));
        this.ac.setTextColor(ContextCompat.getColor(this.b, R.color._2131296921_res_0x7f090299));
        this.ad.setImageResource(R.mipmap._2131820988_res_0x7f1101bc);
        jcf.bEz_(this.ad, nsf.h(R$string.accessibility_is_not_vip));
        dVA_(this.aa, R.mipmap._2131821429_res_0x7f110375);
        this.z.setBackgroundResource(R.drawable._2131430973_res_0x7f0b0e3d);
        this.t.setVisibility(8);
    }

    private void dVA_(ImageView imageView, int i) {
        if (LanguageUtil.bc(this.b)) {
            Drawable drawable = ContextCompat.getDrawable(this.b, i);
            if (drawable != null) {
                drawable.setAutoMirrored(true);
            }
            imageView.setImageDrawable(drawable);
            return;
        }
        imageView.setImageResource(i);
    }

    private String a(UserMemberInfo userMemberInfo) {
        String string;
        int b2 = eil.b(userMemberInfo.getNowTime(), userMemberInfo.getExpireTime());
        LogUtil.a("CustomHeadView", "getVipDescContent getAutoRenew = ", Integer.valueOf(userMemberInfo.getAutoRenew()));
        LogUtil.a("CustomHeadView", "getVipDescContent remainDay = ", Integer.valueOf(b2));
        if (userMemberInfo.getAutoRenew() == 1 || b2 > 7) {
            string = this.b.getString(R$string.IDS_user_member_vip, nsj.b(userMemberInfo.getExpireTime()));
        } else if (b2 >= 1) {
            string = this.b.getResources().getQuantityString(R.plurals._2130903326_res_0x7f03011e, b2, UnitUtil.e(b2, 1, 0));
        } else {
            string = this.b.getString(R$string.IDS_vip_des_will_expire_today, DateFormatUtil.b(userMemberInfo.getExpireTime(), DateFormatUtil.DateFormatType.DATE_FORMAT_HOUR_MINUTE));
        }
        LogUtil.a("CustomHeadView", "getVipDescContent = ", string);
        return string;
    }

    private void a(int i) {
        if (this.f) {
            LogUtil.h("CustomHeadView", "is browseMode");
            this.f = LoginInit.getInstance(this.b).isBrowseMode();
        } else if (gpo.b()) {
            dqj.e(this.ab.getVisibility() == 0);
            dqj.e(i);
            AppRouter.b("/home/main").e("openDiscover", true).c("from", i).c(this.b);
        } else {
            gpn.c(this.b, "from=" + i);
        }
    }

    public void a(List<DeviceBenefits> list) {
        if (this.f && list != null) {
            LogUtil.h("CustomHeadView", "refreshVipDescriptionInfo is browseMode");
            list.clear();
        }
        LogUtil.a("CustomHeadView", "refreshVipDescriptionInfo");
        if (koq.c(list)) {
            LogUtil.a("CustomHeadView", "refreshVipDescriptionInfo mDeviceBenefitInfos = ", Integer.valueOf(list.size()));
            List<LinearLayout> d2 = gpn.d(this.b, list, true);
            LogUtil.a("CustomHeadView", "refreshVipDescriptionInfo deviceBenefitViewList = ", Integer.valueOf(d2.size()));
            if (koq.c(d2)) {
                d(d2);
                this.ac.setVisibility(8);
                this.ab.setVisibility(0);
                return;
            } else {
                this.ab.setVisibility(8);
                this.ac.setVisibility(0);
                return;
            }
        }
        this.ab.setVisibility(8);
        this.ac.setVisibility(0);
    }

    private void d(List<LinearLayout> list) {
        this.ab.stopFlipping();
        this.ab.removeAllViews();
        Iterator<LinearLayout> it = list.iterator();
        while (it.hasNext()) {
            this.ab.addView(it.next());
        }
        if (list.size() > 1) {
            this.ab.startFlipping();
        }
    }
}
