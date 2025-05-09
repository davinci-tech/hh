package defpackage;

import android.content.Context;
import android.text.SpannableString;
import android.text.method.LinkMovementMethod;
import android.text.style.TypefaceSpan;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.utils.Constants;
import com.huawei.ui.commonui.checkbox.HealthCheckBox;
import com.huawei.ui.commonui.dialog.CustomTextAlertDialog;
import com.huawei.ui.commonui.dialog.CustomViewDialog;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.main.R$string;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.StorageParams;
import java.util.Locale;

/* loaded from: classes7.dex */
public class rwa {

    /* renamed from: a, reason: collision with root package name */
    private HealthTextView f16938a;
    private CustomTextAlertDialog b;
    private HealthCheckBox c;
    private LinearLayout d;
    private HealthTextView e;
    private HealthTextView f;
    private HealthTextView g;
    private HealthTextView h;
    private HealthTextView i;
    private HealthTextView j;
    private HealthTextView k;
    private HealthTextView l;
    private HealthTextView m;
    private HealthCheckBox n;
    private HealthTextView o;
    private CustomViewDialog r;

    public void e(final Context context) {
        if (context == null) {
            return;
        }
        View inflate = View.inflate(context, R.layout.vmall_services_custom_view_dialog, null);
        sds.dWk_(context, inflate);
        dSk_(context, inflate);
        b();
        dSj_(context, inflate);
        CustomViewDialog e = new CustomViewDialog.Builder(context).a(context.getString(R$string.IDS_hwh_vmall_privacy_change_notice)).czh_(inflate, 24, 0).czd_(context.getString(R$string.IDS_hw_show_cancel), new View.OnClickListener() { // from class: rwa.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LogUtil.c("VmallPrivacyNotice", "negative button clicked");
                ViewClickInstrumentation.clickOnView(view);
            }
        }).czf_(context.getString(R$string.IDS_hwh_privacy_revoke_auth).toUpperCase(Locale.ENGLISH), new View.OnClickListener() { // from class: rwa.5
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                SharedPreferenceManager.e(context, "socialsharedpreference", Constants.IS_SIGN_VMALL_ARG, Boolean.toString(false), (StorageParams) null);
                LogUtil.a("VmallPrivacyNotice", "user cancel sign vmall agr IS_SIGN_VMALL_ARG = false");
                CustomTextAlertDialog.Builder cyU_ = new CustomTextAlertDialog.Builder(context).b(R$string.IDS_settings_restore_factory_settings_dialog_title).d(R$string.IDS_hwh_vmall_revoke_auth_notice).cyU_(R$string.IDS_settings_button_ok, new View.OnClickListener() { // from class: rwa.5.3
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view2) {
                        LogUtil.c("VmallPrivacyNotice", "positive button clicked");
                        ViewClickInstrumentation.clickOnView(view2);
                    }
                });
                rwa.this.b = cyU_.a();
                rwa.this.b.setCancelable(false);
                if (!rwa.this.b.isShowing()) {
                    rwa.this.b.show();
                }
                ViewClickInstrumentation.clickOnView(view);
            }
        }).e();
        this.r = e;
        e.setCancelable(false);
        this.r.show();
    }

    private void dSk_(Context context, View view) {
        if (context == null || view == null) {
            return;
        }
        LinearLayout linearLayout = (LinearLayout) view.findViewById(R.id.hw_health_agree_dialog_recommend_layout);
        this.d = linearLayout;
        linearLayout.setVisibility(8);
        this.f = (HealthTextView) view.findViewById(R.id.hw_health_service_item_one);
        this.h = (HealthTextView) view.findViewById(R.id.hw_health_service_item_two);
        this.i = (HealthTextView) view.findViewById(R.id.hw_health_service_item_three);
        this.f16938a = (HealthTextView) view.findViewById(R.id.hw_health_service_item_four);
        this.k = (HealthTextView) view.findViewById(R.id.hw_health_service_item_five);
        this.o = (HealthTextView) view.findViewById(R.id.hw_health_service_item_six);
        this.e = (HealthTextView) view.findViewById(R.id.hw_health_service_item_seven);
        this.l = (HealthTextView) view.findViewById(R.id.hw_health_service_item_eight);
        this.j = (HealthTextView) view.findViewById(R.id.hw_health_service_item_nine);
        this.g = (HealthTextView) view.findViewById(R.id.hw_health_service_item_ten);
        this.f.setText(context.getString(R$string.IDS_hwh_vmall_agreement_public_one));
        this.h.setText(context.getString(R$string.IDS_hwh_vmall_agreement_public_two));
        this.i.setText(context.getString(R$string.IDS_hwh_vmall_agreement_public_three));
        this.f16938a.setText(context.getString(R$string.IDS_hwh_vmall_agreement_public_four));
        this.k.setText(context.getString(R$string.IDS_hwh_vmall_agreement_public_five));
        this.o.setText(context.getString(R$string.IDS_hwh_vmall_agreement_public_six));
        this.e.setText(context.getString(R$string.IDS_hwh_vmall_agreement_public_seven));
        this.l.setText(context.getString(R$string.IDS_hwh_vmall_agreement_server_info));
        this.j.setText(context.getString(R$string.IDS_hwh_vmall_agreement_background));
        this.g.setText(context.getString(R$string.IDS_hwh_vmall_unagreement));
        this.n = (HealthCheckBox) view.findViewById(R.id.hw_health_agree_dialog_user_plan_box);
        this.c = (HealthCheckBox) view.findViewById(R.id.hw_health_agree_dialog_recommend_box);
        this.n.setChecked(knx.e());
        this.c.setChecked(!"0".equals(SharedPreferenceManager.b(context, Integer.toString(10000), "health_product_recommend")));
    }

    private void b() {
        this.n.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: rwa.4
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                gmz.d().c(11, z, (String) null, (IBaseResponseCallback) null);
                ViewClickInstrumentation.clickOnView(compoundButton);
            }
        });
        this.c.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: rwa.1
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                LogUtil.c("VmallPrivacyNotice", "handleLayout onCheckedChanged");
                ViewClickInstrumentation.clickOnView(compoundButton);
            }
        });
    }

    private void dSj_(Context context, View view) {
        HealthTextView healthTextView = (HealthTextView) view.findViewById(R.id.hw_health_service_item_ele);
        this.m = healthTextView;
        healthTextView.setMovementMethod(LinkMovementMethod.getInstance());
        this.m.setHighlightColor(context.getResources().getColor(android.R.color.transparent));
        String string = context.getString(R$string.IDS_hwh_vmall_use_agreement);
        String trim = context.getString(R$string.IDS_hwh_vmall_privacy_statement).trim();
        SpannableString spannableString = new SpannableString(context.getString(R$string.IDS_hwh_vmall_user_agreement, string, trim));
        int indexOf = spannableString.toString().indexOf(string);
        if (indexOf >= 0) {
            rvq rvqVar = new rvq(context, "VmallUserAgreement");
            spannableString.setSpan(new TypefaceSpan(com.huawei.openalliance.ad.constant.Constants.FONT), indexOf, string.length() + indexOf, 33);
            spannableString.setSpan(rvqVar, indexOf, string.length() + indexOf, 17);
        }
        int indexOf2 = spannableString.toString().indexOf(trim);
        if (indexOf2 >= 0) {
            rvq rvqVar2 = new rvq(context, "VmallPrivacy");
            spannableString.setSpan(new TypefaceSpan(com.huawei.openalliance.ad.constant.Constants.FONT), indexOf2, trim.length() + indexOf2, 33);
            spannableString.setSpan(rvqVar2, indexOf2, trim.length() + indexOf2, 17);
        }
        this.m.setText(spannableString);
    }
}
