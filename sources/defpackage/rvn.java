package defpackage;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.text.SpannableString;
import android.text.method.LinkMovementMethod;
import android.util.Pair;
import android.util.TypedValue;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import androidx.core.content.ContextCompat;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.openalliance.ad.constant.Constants;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.base.BaseDialog;
import com.huawei.ui.commonui.button.HealthButton;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.viewpager.HealthPagerAdapter;
import com.huawei.ui.commonui.viewpager.HealthViewPager;
import com.huawei.ui.main.R$string;
import defpackage.rvn;
import health.compact.a.CommonUtil;
import health.compact.a.Utils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;

/* loaded from: classes7.dex */
public class rvn extends BaseDialog {
    private rvn(Context context, int i) {
        super(context, i);
    }

    public static class d {

        /* renamed from: a, reason: collision with root package name */
        private HealthViewPager f16931a;
        private Activity b;
        private rvn c = null;
        private Context d;
        private rvh e;

        public d(Context context, Activity activity) {
            this.d = context;
            this.b = activity;
            this.e = new rvh(context, activity, null);
        }

        public rvn b() {
            Drawable drawable;
            this.c = new rvn(this.d, R.style.CustomDialog);
            View inflate = ((LayoutInflater) this.d.getSystemService("layout_inflater")).inflate(R.layout.ove_privacy_dialog_layout, (ViewGroup) null);
            TypedValue typedValue = new TypedValue();
            this.d.getTheme().resolveAttribute(R.attr._2131099975_res_0x7f060147, typedValue, true);
            TypedArray obtainStyledAttributes = this.d.getTheme().obtainStyledAttributes(typedValue.resourceId, new int[]{R.attr._2131099820_res_0x7f0600ac, R.attr._2131099951_res_0x7f06012f, R.attr._2131100000_res_0x7f060160, R.attr._2131101300_res_0x7f060674});
            if (typedValue.resourceId != 0) {
                drawable = obtainStyledAttributes.getDrawable(2);
            } else {
                drawable = ContextCompat.getDrawable(this.d, R.drawable._2131427507_res_0x7f0b00b3);
            }
            inflate.setBackground(drawable);
            dSa_(this.c, inflate, this.d);
            HealthViewPager healthViewPager = (HealthViewPager) inflate.findViewById(R.id.viewpager);
            this.f16931a = healthViewPager;
            healthViewPager.setIsScroll(false);
            if (rvn.c() || rvn.d()) {
                this.f16931a.setAdapter(d());
            } else {
                this.f16931a.setAdapter(e());
            }
            this.c.setCancelable(false);
            obtainStyledAttributes.recycle();
            this.c.c(this.d);
            return this.c;
        }

        private HealthPagerAdapter e() {
            View inflate = LayoutInflater.from(this.d).inflate(R.layout.ove_privacy_dialog_page_one, (ViewGroup) null);
            View inflate2 = LayoutInflater.from(this.d).inflate(R.layout.ove_privacy_dialog_page_two, (ViewGroup) null);
            dRZ_(inflate, inflate2);
            dSc_(inflate, inflate2);
            HealthButton healthButton = (HealthButton) inflate2.findViewById(R.id.hw_health_agreement_dialog_disagree);
            healthButton.setText(this.d.getString(R$string.IDS_hw_show_cancel).toUpperCase(Locale.getDefault()));
            HealthButton healthButton2 = (HealthButton) inflate2.findViewById(R.id.hw_health_agreement_dialog_agree);
            healthButton2.setText(this.d.getString(R$string.IDS_hwh_privacy_revoke_auth).toUpperCase(Locale.getDefault()));
            healthButton.setOnClickListener(new View.OnClickListener() { // from class: rvn.d.3
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    d.this.c.dismiss();
                    ViewClickInstrumentation.clickOnView(view);
                }
            });
            d(healthButton2);
            healthButton2.setOnClickListener(new View.OnClickListener() { // from class: rvn.d.2
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    if (d.this.c != null) {
                        d.this.c.dismiss();
                    }
                    d.this.e.d();
                    ViewClickInstrumentation.clickOnView(view);
                }
            });
            HealthButton healthButton3 = (HealthButton) inflate.findViewById(R.id.hw_health_privacy_dialog_cancel);
            healthButton3.setText(this.d.getString(R$string.IDS_settings_button_cancal).toUpperCase(Locale.getDefault()));
            healthButton3.setOnClickListener(new View.OnClickListener() { // from class: rvn.d.5
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    if (d.this.c != null) {
                        d.this.c.dismiss();
                    }
                    ViewClickInstrumentation.clickOnView(view);
                }
            });
            HealthButton healthButton4 = (HealthButton) inflate.findViewById(R.id.hw_health_privacy_dialog_next);
            healthButton4.setText(this.d.getString(R$string.IDS_startup_next).toUpperCase(Locale.getDefault()));
            healthButton4.setOnClickListener(new View.OnClickListener() { // from class: rvn.d.1
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    d.this.f16931a.setCurrentItem(1);
                    ViewClickInstrumentation.clickOnView(view);
                }
            });
            d(healthButton4);
            ArrayList arrayList = new ArrayList(16);
            arrayList.add(inflate);
            arrayList.add(inflate2);
            return new rvl(arrayList);
        }

        private void d(HealthButton healthButton) {
            if (Utils.i()) {
                healthButton.setBackgroundResource(R.drawable._2131428850_res_0x7f0b05f2);
                healthButton.setTextColor(this.d.getColor(R.color._2131296927_res_0x7f09029f));
            }
        }

        private void dSc_(View view, View view2) {
            if (nsn.r()) {
                ((HealthTextView) view.findViewById(R.id.hw_health_service_item_all)).setTextSize(1, 26.0f);
                ((HealthTextView) view.findViewById(R.id.text_one)).setTextSize(1, 26.0f);
                ((HealthTextView) view.findViewById(R.id.text_two)).setTextSize(1, 26.0f);
                ((HealthTextView) view.findViewById(R.id.text_three)).setTextSize(1, 26.0f);
                ((HealthTextView) view.findViewById(R.id.text_four)).setTextSize(1, 26.0f);
                ((HealthTextView) view.findViewById(R.id.hw_health_privacy_eu_text_one)).setTextSize(1, 26.0f);
                ((HealthTextView) view.findViewById(R.id.hw_health_privacy_eu_text_two)).setTextSize(1, 26.0f);
                ((HealthTextView) view.findViewById(R.id.save_data_local)).setTextSize(1, 26.0f);
                ((HealthTextView) view.findViewById(R.id.device_data_upload_cloud)).setTextSize(1, 26.0f);
                ((HealthTextView) view.findViewById(R.id.map_location_info)).setTextSize(1, 26.0f);
                ((HealthTextView) view.findViewById(R.id.weather_location_info)).setTextSize(1, 26.0f);
                ((HealthTextView) view.findViewById(R.id.exit_connect_net)).setTextSize(1, 26.0f);
                ((HealthButton) view.findViewById(R.id.hw_health_privacy_dialog_cancel)).setTextSize(1, 26.0f);
                ((HealthButton) view.findViewById(R.id.hw_health_privacy_dialog_next)).setTextSize(1, 26.0f);
                ((HealthTextView) view2.findViewById(R.id.use_permission_declare)).setTextSize(1, 26.0f);
                ((HealthTextView) view2.findViewById(R.id.net_permission)).setTextSize(1, 26.0f);
                ((HealthTextView) view2.findViewById(R.id.service_permission)).setTextSize(1, 26.0f);
                ((HealthTextView) view2.findViewById(R.id.minors_use_description)).setTextSize(1, 26.0f);
                ((HealthTextView) view2.findViewById(R.id.hw_health_ove_agreement_agree_text)).setTextSize(1, 26.0f);
                ((HealthButton) view2.findViewById(R.id.hw_health_agreement_dialog_disagree)).setTextSize(1, 26.0f);
                ((HealthButton) view2.findViewById(R.id.hw_health_agreement_dialog_agree)).setTextSize(1, 26.0f);
            }
        }

        private HealthPagerAdapter d() {
            View inflate = View.inflate(this.d, R.layout.ove_privacy_dialog_page_hong_kong, null);
            HealthButton healthButton = (HealthButton) inflate.findViewById(R.id.hw_health_agreement_dialog_disagree);
            healthButton.setText(this.d.getString(R$string.IDS_hw_show_cancel).toUpperCase(Locale.getDefault()));
            HealthButton healthButton2 = (HealthButton) inflate.findViewById(R.id.hw_health_agreement_dialog_agree);
            healthButton2.setText(this.d.getString(R$string.IDS_hwh_privacy_revoke_auth).toUpperCase(Locale.getDefault()));
            healthButton.setOnClickListener(new View.OnClickListener() { // from class: rvn.d.4
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    d.this.c.dismiss();
                    ViewClickInstrumentation.clickOnView(view);
                }
            });
            healthButton2.setOnClickListener(new View.OnClickListener() { // from class: rvn.d.7
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    if (d.this.c != null) {
                        d.this.c.dismiss();
                    }
                    d.this.e.d();
                    ViewClickInstrumentation.clickOnView(view);
                }
            });
            d(healthButton2);
            dSd_(inflate);
            dSb_(inflate);
            sds.b((HealthTextView) inflate.findViewById(R.id.declare_agreement_view));
            ArrayList arrayList = new ArrayList(16);
            arrayList.add(inflate);
            return new rvl(arrayList);
        }

        private void dSb_(View view) {
            if (nsn.r()) {
                ((HealthTextView) view.findViewById(R.id.hw_health_service_item_one)).setTextSize(1, 26.0f);
                ((HealthTextView) view.findViewById(R.id.hw_health_service_item_two)).setTextSize(1, 26.0f);
                ((HealthTextView) view.findViewById(R.id.hw_health_service_item_six)).setTextSize(1, 26.0f);
                ((HealthTextView) view.findViewById(R.id.hw_health_service_item_seven)).setTextSize(1, 26.0f);
                ((HealthTextView) view.findViewById(R.id.minors_use_description)).setTextSize(1, 26.0f);
                ((HealthTextView) view.findViewById(R.id.declare_agreement_view)).setTextSize(1, 26.0f);
                ((HealthButton) view.findViewById(R.id.hw_health_agreement_dialog_disagree)).setTextSize(1, 26.0f);
                ((HealthButton) view.findViewById(R.id.hw_health_agreement_dialog_agree)).setTextSize(1, 26.0f);
            }
        }

        private void dSd_(View view) {
            ((HealthTextView) view.findViewById(R.id.hw_health_service_item_one)).setText(this.d.getString(R$string.IDS_hwh_privacy_permission_total, this.d.getResources().getString(R$string.IDS_hwh_privacy_permission_read)));
            ((HealthTextView) view.findViewById(R.id.hw_health_service_item_two)).setText(sds.dWj_());
            ((HealthTextView) view.findViewById(R.id.hw_health_service_item_six)).setText(this.d.getResources().getString(R$string.IDS_hwh_agreement_save_area, rvn.c() ? this.d.getResources().getString(R$string.IDS_hwh_agreement_russia) : this.d.getResources().getString(R$string.IDS_hwh_agreement_hong_kong)));
        }

        public void c() {
            HashMap hashMap = new HashMap(16);
            hashMap.put("click", "1");
            ixx.d().d(this.d, AnalyticsValue.HEALTH_CANCEL_AUTH_2040061.value(), hashMap, 0);
            ixx.d().c(this.d);
            final rvj rvjVar = new rvj(this.b);
            LoginInit loginInit = LoginInit.getInstance(this.d);
            if (Utils.l() || !loginInit.getIsLogined()) {
                LogUtil.a("OverseasCancelAuthDialog", "stopHealth isOverseaNoCloudVersion = true or logout");
                rvjVar.b(this.d);
                rvjVar.e(this.d);
                rvjVar.a(this.d);
                rvjVar.d(this.d);
                return;
            }
            LogUtil.a("OverseasCancelAuthDialog", "stopHealth obtainToken");
            if (!CommonUtil.aa(this.d)) {
                nrh.b(this.d, R$string.IDS_device_hygride_current_network_unavailable);
            } else {
                ThreadPoolManager.d().execute(new Runnable() { // from class: rvu
                    @Override // java.lang.Runnable
                    public final void run() {
                        rvn.d.this.e(rvjVar);
                    }
                });
            }
        }

        /* synthetic */ void e(rvj rvjVar) {
            rvjVar.c(this.d);
        }

        private void dRZ_(View view, View view2) {
            HealthTextView healthTextView = (HealthTextView) view.findViewById(R.id.hw_health_service_item_all);
            HealthTextView healthTextView2 = (HealthTextView) view.findViewById(R.id.hw_health_privacy_eu_text_two);
            healthTextView.setText(this.d.getString(R$string.IDS_hwh_privacy_permission_total, this.d.getResources().getString(R$string.IDS_hwh_privacy_ove_one)));
            healthTextView2.setMovementMethod(LinkMovementMethod.getInstance());
            healthTextView2.setHighlightColor(this.d.getResources().getColor(android.R.color.transparent));
            LinearLayout linearLayout = (LinearLayout) view.findViewById(R.id.hw_health_privacy_eu_cloud);
            HealthTextView healthTextView3 = (HealthTextView) view.findViewById(R.id.hw_health_privacy_eu_text_one);
            LinearLayout linearLayout2 = (LinearLayout) view.findViewById(R.id.hw_health_privacy_eu_no_cloud);
            if (Utils.i()) {
                linearLayout2.setVisibility(8);
                healthTextView3.setText(this.d.getString(R$string.IDS_hwh_agreement_save_area_two, this.d.getResources().getString(R$string.IDS_hwh_agreement_eu)));
            } else {
                linearLayout.setVisibility(8);
            }
            String string = this.d.getString(R$string.IDS_hwh_privacy_eu_three);
            SpannableString spannableString = new SpannableString(this.d.getString(R$string.IDS_hwh_privacy_eu_two, string));
            int indexOf = spannableString.toString().indexOf(string);
            if (indexOf != -1) {
                spannableString.setSpan(new rvq(this.d, "HealthPrivacy"), indexOf, string.length() + indexOf, 17);
            }
            healthTextView2.setText(spannableString);
            SpannableString spannableString2 = new SpannableString(this.d.getString(R$string.IDS_hw_show_setting_about_service_item));
            spannableString2.setSpan(new rvq(this.d, "HealthUserAgreement"), 0, spannableString2.toString().length(), 17);
            HealthTextView healthTextView4 = (HealthTextView) view2.findViewById(R.id.hw_health_ove_agreement_agree_text);
            healthTextView4.setMovementMethod(LinkMovementMethod.getInstance());
            healthTextView4.setHighlightColor(this.d.getResources().getColor(android.R.color.transparent));
            healthTextView4.setText(spannableString2);
        }

        private void dSa_(Dialog dialog, View view, Context context) {
            dialog.setContentView(view);
            Window window = dialog.getWindow();
            Display defaultDisplay = ((WindowManager) context.getApplicationContext().getSystemService(Constants.NATIVE_WINDOW_SUB_DIR)).getDefaultDisplay();
            WindowManager.LayoutParams attributes = window.getAttributes();
            int dimensionPixelSize = this.d.getResources().getDimensionPixelSize(R.dimen._2131362466_res_0x7f0a02a2);
            int dimensionPixelSize2 = this.d.getResources().getDimensionPixelSize(R.dimen._2131362465_res_0x7f0a02a1);
            int dimensionPixelSize3 = this.d.getResources().getDimensionPixelSize(R.dimen._2131362464_res_0x7f0a02a0);
            Pair<Integer, Integer> safeRegionWidth = BaseActivity.getSafeRegionWidth();
            attributes.width = (((defaultDisplay.getWidth() - dimensionPixelSize) - dimensionPixelSize2) - ((Integer) safeRegionWidth.first).intValue()) - ((Integer) safeRegionWidth.second).intValue();
            attributes.height = (int) (defaultDisplay.getHeight() * 0.8f);
            attributes.y = dimensionPixelSize3;
            window.setGravity(80);
            window.setAttributes(attributes);
            window.setWindowAnimations(R.style.track_dialog_anim);
        }
    }

    public void c(Context context) {
        nsn.a(context, this);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean d() {
        if (Utils.l()) {
            return false;
        }
        LoginInit loginInit = LoginInit.getInstance(BaseApplication.getContext());
        return loginInit.getIsLogined() && CommonUtil.h(loginInit.getAccountInfo(1009)) == 5;
    }

    public static boolean c() {
        if (Utils.l()) {
            return false;
        }
        LoginInit loginInit = LoginInit.getInstance(BaseApplication.getContext());
        return loginInit.getIsLogined() && CommonUtil.h(loginInit.getAccountInfo(1009)) == 8;
    }
}
