package defpackage;

import android.content.Context;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.style.ForegroundColorSpan;
import android.text.style.TypefaceSpan;
import android.view.View;
import androidx.core.content.ContextCompat;
import com.huawei.health.R;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.openalliance.ad.constant.Constants;
import com.huawei.operation.OpAnalyticsUtil;
import com.huawei.operation.OperationKey;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.main.R$string;
import health.compact.a.LogAnonymous;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.Utils;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes7.dex */
public class sds {
    public static void dWk_(Context context, View view) {
        if (context == null || view == null) {
            LogUtil.h("TermsUtil", "putAgreementText param is null");
            return;
        }
        HealthTextView healthTextView = (HealthTextView) view.findViewById(R.id.hw_health_service_item_one);
        HealthTextView healthTextView2 = (HealthTextView) view.findViewById(R.id.hw_health_service_item_two);
        HealthTextView healthTextView3 = (HealthTextView) view.findViewById(R.id.hw_health_service_item_three);
        HealthTextView healthTextView4 = (HealthTextView) view.findViewById(R.id.hw_health_service_item_four);
        HealthTextView healthTextView5 = (HealthTextView) view.findViewById(R.id.hw_health_service_item_five);
        HealthTextView healthTextView6 = (HealthTextView) view.findViewById(R.id.hw_health_service_item_six);
        HealthTextView healthTextView7 = (HealthTextView) view.findViewById(R.id.hw_health_service_item_seven);
        HealthTextView healthTextView8 = (HealthTextView) view.findViewById(R.id.hw_health_service_item_eight);
        HealthTextView healthTextView9 = (HealthTextView) view.findViewById(R.id.hw_health_service_item_nine);
        healthTextView3.getPaint().setFakeBoldText(true);
        healthTextView4.getPaint().setFakeBoldText(true);
        a(context, healthTextView8, healthTextView9);
        healthTextView.setText(dWj_());
        healthTextView2.setText(context.getString(R$string.IDS_hwh_agreement_public_two));
        healthTextView3.setText(context.getString(R$string.IDS_hwh_agreement_public_three));
        healthTextView4.setText(context.getString(R$string.IDS_hwh_agreement_oversea_one));
        healthTextView5.setText(context.getString(R$string.IDS_hwh_agreement_public_four));
        if (!Utils.o()) {
            healthTextView4.setText(context.getString(R$string.IDS_hwh_agreement_china_one));
            healthTextView6.setText(context.getString(R$string.IDS_hwh_agreement_china_two));
            healthTextView7.setText(context.getString(R$string.IDS_hwh_agreement_china_three));
            healthTextView8.setText(context.getString(R$string.IDS_hwh_agreement_china_four));
            healthTextView9.setVisibility(8);
        }
        if (Utils.o() && Utils.i()) {
            healthTextView6.setText(context.getString(R$string.IDS_hwh_agreement_europe_one));
            healthTextView7.setVisibility(8);
        }
        if (Utils.i()) {
            return;
        }
        healthTextView6.setText(context.getString(R$string.IDS_hwh_agreement_other_one));
        healthTextView7.setVisibility(8);
    }

    public static SpannableString dWj_() {
        Context context = BaseApplication.getContext();
        String string = context.getString(R$string.IDS_sentences_network);
        String string2 = context.getString(R$string.IDS_sentences_location);
        String string3 = context.getString(R$string.IDS_sentences_camera);
        String string4 = context.getString(R$string.IDS_sentences_microphone);
        String string5 = context.getString(R$string.IDS_sentences_contacts);
        String string6 = context.getString(R$string.IDS_sentences_call_log);
        String string7 = context.getString(R$string.IDS_sentences_storage);
        String string8 = context.getString(R$string.IDS_sentences_phone);
        String string9 = context.getString(R$string.IDS_sentences_short_message);
        String string10 = context.getString(R$string.IDS_sentences_calendar);
        String string11 = context.getString(R$string.IDS_sentences_physical_activity);
        SpannableString spannableString = new SpannableString(context.getString(R$string.IDS_privacy_notice_service_new_2024_3, string, string2, string3, string4, string5, string6, string7, string8, string9, string10, string11));
        dWm_(spannableString, string);
        dWm_(spannableString, string2);
        dWm_(spannableString, string3);
        dWm_(spannableString, string4);
        dWm_(spannableString, string5);
        dWm_(spannableString, string6);
        dWm_(spannableString, string7);
        dWl_(spannableString, string8);
        dWm_(spannableString, string9);
        dWm_(spannableString, string10);
        dWm_(spannableString, string11);
        return spannableString;
    }

    public static boolean c(String str) {
        int optInt;
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        try {
            optInt = new JSONObject(str).optInt("errorCode");
        } catch (JSONException e) {
            LogUtil.b("TermsUtil", "isPrivacySuccess JSONException, ", LogAnonymous.b((Object) e));
        }
        if (optInt != 0) {
            LogUtil.h("TermsUtil", "isPrivacySuccess errorCode:", Integer.valueOf(optInt));
            OpAnalyticsUtil.getInstance().setEventOneErrorCode(OperationKey.HEALTH_APP_AGREEMENT_SIGNING_85070027.value(), optInt);
            return false;
        }
        OpAnalyticsUtil.getInstance().setEventOneErrorCode(OperationKey.HEALTH_APP_AGREEMENT_SIGNING_85070027.value(), 0);
        return true;
    }

    public static void dWm_(SpannableString spannableString, String str) {
        int indexOf;
        if (spannableString == null || TextUtils.isEmpty(str) || (indexOf = spannableString.toString().indexOf(str)) == -1) {
            return;
        }
        spannableString.setSpan(new TypefaceSpan(Constants.FONT), indexOf, str.length() + indexOf, 33);
        spannableString.setSpan(new ForegroundColorSpan(ContextCompat.getColor(BaseApplication.getContext(), R.color._2131299236_res_0x7f090ba4)), indexOf, str.length() + indexOf, 33);
    }

    public static void dWl_(SpannableString spannableString, String str) {
        int lastIndexOf;
        if (spannableString == null || TextUtils.isEmpty(str) || (lastIndexOf = spannableString.toString().lastIndexOf(str)) == -1) {
            return;
        }
        spannableString.setSpan(new TypefaceSpan(Constants.FONT), lastIndexOf, str.length() + lastIndexOf, 33);
        spannableString.setSpan(new ForegroundColorSpan(ContextCompat.getColor(BaseApplication.getContext(), R.color._2131299236_res_0x7f090ba4)), lastIndexOf, str.length() + lastIndexOf, 33);
    }

    private static void a(Context context, HealthTextView healthTextView, HealthTextView healthTextView2) {
        if (Utils.o()) {
            healthTextView.setMovementMethod(LinkMovementMethod.getInstance());
            healthTextView.setHighlightColor(context.getResources().getColor(android.R.color.transparent));
            healthTextView2.setMovementMethod(LinkMovementMethod.getInstance());
            healthTextView2.setHighlightColor(context.getResources().getColor(android.R.color.transparent));
            String string = context.getString(R$string.IDS_hw_show_setting_about_service_item);
            String trim = context.getString(R$string.IDS_hwh_privacy_notice_title).trim();
            SpannableString spannableString = new SpannableString(context.getString(R$string.IDS_hwh_agreement_europe_and_other_two, trim));
            int indexOf = spannableString.toString().indexOf(trim);
            if (indexOf >= 0) {
                rvq rvqVar = new rvq(context, "HealthPrivacy");
                spannableString.setSpan(new TypefaceSpan(Constants.FONT), indexOf, trim.length() + indexOf, 33);
                spannableString.setSpan(rvqVar, indexOf, trim.length() + indexOf, 17);
            }
            healthTextView.setText(spannableString);
            SpannableString spannableString2 = new SpannableString(context.getString(R$string.IDS_hwh_agreement_europe_and_other_three, string));
            int indexOf2 = spannableString2.toString().indexOf(string);
            if (indexOf2 >= 0) {
                rvq rvqVar2 = new rvq(context, "HealthUserAgreement");
                spannableString2.setSpan(new TypefaceSpan(Constants.FONT), indexOf2, string.length() + indexOf2, 33);
                spannableString2.setSpan(rvqVar2, indexOf2, string.length() + indexOf2, 17);
            }
            healthTextView2.setText(spannableString2);
        }
    }

    public static void b(HealthTextView healthTextView) {
        if (healthTextView == null) {
            LogUtil.h("TermsUtil", "clickableText textView is null");
            return;
        }
        Context context = healthTextView.getContext();
        String string = context.getString(R$string.IDS_hwh_about_privacy_notice);
        String string2 = context.getString(R$string.IDS_hw_show_setting_about_service_item);
        String string3 = context.getString(R$string.IDS_hwh_me_about_privacy_agreement, string2, context.getString(R$string.IDS_hw_about_service_and), string);
        SpannableString spannableString = new SpannableString(string3);
        int length = string2.length();
        spannableString.setSpan(new rvq(context, "HealthUserAgreement"), 0, length, 33);
        spannableString.setSpan(new TypefaceSpan(Constants.FONT), 0, length, 33);
        int length2 = string3.length() - string.length();
        int length3 = spannableString.toString().length();
        spannableString.setSpan(new rvq(context, "HealthPrivacy"), length2, length3, 33);
        spannableString.setSpan(new TypefaceSpan(Constants.FONT), length2, length3, 33);
        healthTextView.setMovementMethod(LinkMovementMethod.getInstance());
        healthTextView.setHighlightColor(ContextCompat.getColor(context, android.R.color.transparent));
        healthTextView.setText(spannableString);
    }

    public static String a() {
        return BaseApplication.getContext().getString(R$string.IDS_hw_health_rights, 2014, 2025);
    }

    public static void e() {
        LogUtil.a("TermsUtil", "enter initUserShareStatus");
        Context context = BaseApplication.getContext();
        gmz d = gmz.d();
        if (TextUtils.isEmpty(knx.a())) {
            if (String.valueOf(0).equals(SharedPreferenceManager.b(context, Integer.toString(10000), "ove_bi_dialog_first_in"))) {
                d.c(11, false, (String) null, (IBaseResponseCallback) null);
                return;
            } else {
                LogUtil.a("TermsUtil", "overseaBiFirstIn is not zero");
                d.c(11, !LoginInit.getInstance(context).isKidAccount(), (String) null, (IBaseResponseCallback) null);
                return;
            }
        }
        d.c(11, String.valueOf(true).equals(knx.a()), (String) null, (IBaseResponseCallback) null);
    }
}
