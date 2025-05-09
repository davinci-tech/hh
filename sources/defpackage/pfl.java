package defpackage;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.text.TextUtils;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import com.apprichtap.haptic.base.PrebakedEffectId;
import com.huawei.health.R;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.main.R$string;
import com.huawei.ui.main.stories.soical.interactor.OperationInteractorsApi;
import health.compact.a.CommonUtil;
import health.compact.a.Services;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.UnitUtil;

/* loaded from: classes6.dex */
public class pfl {
    public static void d(HealthTextView healthTextView, String str, int i) {
        if (healthTextView == null) {
            LogUtil.h("ConfiguredPage_UiUtil", "setTitleUi titleTextView is null.");
        } else if (!TextUtils.isEmpty(str) && i == 1) {
            healthTextView.setVisibility(0);
            healthTextView.setText(str);
        } else {
            healthTextView.setVisibility(8);
        }
    }

    public static void c(HealthTextView healthTextView, String str, int i) {
        d(healthTextView, str, i);
    }

    public static void d(HealthTextView healthTextView, cdu cduVar, int i) {
        if (healthTextView == null || cduVar == null) {
            LogUtil.h("ConfiguredPage_UiUtil", "setJoinNumberOrReadCount textView or itemObject is null.");
            return;
        }
        String u = cduVar.u();
        if (!TextUtils.isEmpty(u)) {
            b(healthTextView, cduVar, u);
        } else {
            b(healthTextView, cduVar, i);
        }
    }

    private static void b(HealthTextView healthTextView, cdu cduVar, String str) {
        if (healthTextView == null || cduVar == null) {
            LogUtil.h("ConfiguredPage_UiUtil", "setNumberViewByPageAttributeKey textView or itemObject is null.");
            return;
        }
        if ("activity".equals(str)) {
            b(healthTextView, cduVar);
        } else if ("knowledge".equals(str)) {
            a(healthTextView, cduVar);
        } else {
            healthTextView.setVisibility(8);
        }
    }

    private static void b(HealthTextView healthTextView, cdu cduVar, int i) {
        if (healthTextView == null || cduVar == null) {
            LogUtil.h("ConfiguredPage_UiUtil", "setNumberViewByPageAttribute textView or itemObject is null.");
        } else {
            if (a(healthTextView, cduVar, i)) {
                return;
            }
            b(healthTextView, cduVar);
        }
    }

    private static boolean a(HealthTextView healthTextView, cdu cduVar, int i) {
        if (healthTextView == null || cduVar == null) {
            LogUtil.h("ConfiguredPage_UiUtil", "isViewVisibleByPageAttribute textView or itemObject is null.");
            return true;
        }
        String a2 = a(cduVar);
        if (TextUtils.isEmpty(a2)) {
            if (i == 3) {
                return false;
            }
            healthTextView.setVisibility(8);
            return true;
        }
        if ("活动".equals(a2)) {
            return false;
        }
        healthTextView.setVisibility(8);
        return true;
    }

    private static void a(HealthTextView healthTextView, cdu cduVar) {
        if (healthTextView == null || cduVar == null) {
            LogUtil.h("ConfiguredPage_UiUtil", "setReadCount readTextView or itemObject is null.");
            return;
        }
        int x = cduVar.x();
        if (x >= 0) {
            String replace = BaseApplication.getContext().getResources().getQuantityString(R.plurals._2130903294_res_0x7f0300fe, x, Integer.valueOf(x)).replace(String.valueOf(x), UnitUtil.e(x, 1, 0));
            healthTextView.setVisibility(0);
            healthTextView.setText(replace);
            return;
        }
        healthTextView.setVisibility(8);
    }

    private static void b(HealthTextView healthTextView, cdu cduVar) {
        String k = cduVar.k();
        if (TextUtils.isEmpty(k)) {
            LogUtil.h("ConfiguredPage_UiUtil", "setJoinNumber numOfPeopleString is empty.");
            healthTextView.setVisibility(8);
            return;
        }
        int m = CommonUtil.m(BaseApplication.getContext(), k);
        if (m >= 0) {
            healthTextView.setVisibility(0);
            healthTextView.setText(BaseApplication.getContext().getResources().getString(R$string.IDS_activity_social_people_attended, UnitUtil.e(m, 1, 0)));
        } else {
            healthTextView.setVisibility(8);
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public static void d(HealthTextView healthTextView, cdu cduVar) {
        char c;
        String string;
        if (healthTextView == null || cduVar == null) {
            LogUtil.h("ConfiguredPage_UiUtil", "setPageAttributeUi attributeTextView or object is null.");
            return;
        }
        String u = cduVar.u();
        if (TextUtils.isEmpty(u)) {
            LogUtil.h("ConfiguredPage_UiUtil", "setPageAttributeUi pageAttributeKey is empty.");
            c(healthTextView, cduVar);
            return;
        }
        Resources resources = BaseApplication.getContext().getResources();
        u.hashCode();
        switch (u.hashCode()) {
            case -2060462300:
                if (u.equals("advertising")) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            case -1655966961:
                if (u.equals("activity")) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            case -1354571749:
                if (u.equals("course")) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            case 103771895:
                if (u.equals("medal")) {
                    c = 3;
                    break;
                }
                c = 65535;
                break;
            case 1549887614:
                if (u.equals("knowledge")) {
                    c = 4;
                    break;
                }
                c = 65535;
                break;
            case 1984153269:
                if (u.equals("service")) {
                    c = 5;
                    break;
                }
                c = 65535;
                break;
            default:
                c = 65535;
                break;
        }
        if (c == 0) {
            string = resources.getString(R$string.IDS_messageCenter_ad_logo);
        } else if (c == 1) {
            string = resources.getString(R$string.IDS_main_home_bottom_text_activity);
        } else if (c == 2) {
            string = resources.getString(R$string.IDS_configured_page_attribute_course);
        } else if (c == 3) {
            string = resources.getString(R$string.IDS_clear_medal_data_cache);
        } else if (c == 4) {
            string = resources.getString(R$string.IDS_configured_page_attribute_knowledge);
        } else if (c == 5) {
            string = resources.getString(R$string.IDS_hw_messagecenter_service);
        } else {
            string = cduVar.w();
            if (!TextUtils.isEmpty(string)) {
                string = string.trim();
            }
        }
        if (TextUtils.isEmpty(string)) {
            LogUtil.h("ConfiguredPage_UiUtil", "setPageAttributeUi pageAttributeText is empty.");
            healthTextView.setVisibility(8);
        } else {
            healthTextView.setVisibility(0);
            healthTextView.setText(string);
        }
    }

    private static void c(HealthTextView healthTextView, cdu cduVar) {
        if (healthTextView == null || cduVar == null) {
            LogUtil.h("ConfiguredPage_UiUtil", "setAttributeViewByPageAttribute attributeTextView or object is null.");
            return;
        }
        String a2 = a(cduVar);
        if (TextUtils.isEmpty(a2)) {
            LogUtil.h("ConfiguredPage_UiUtil", "setAttributeViewByPageAttribute pageAttribute is empty.");
            healthTextView.setVisibility(8);
        } else {
            healthTextView.setVisibility(0);
            healthTextView.setText(a2);
        }
    }

    public static void c(HealthTextView healthTextView, cdu cduVar, int i, int i2) {
        if (healthTextView == null || cduVar == null) {
            LogUtil.h("ConfiguredPage_UiUtil", "setHuaweiActivityStatusUi activityStatusTextView or itemObject is null.");
            return;
        }
        String u = cduVar.u();
        if (TextUtils.isEmpty(u)) {
            if (a(healthTextView, cduVar, i2)) {
                return;
            }
        } else if (!"activity".equals(u)) {
            healthTextView.setVisibility(8);
            return;
        }
        c(healthTextView, cduVar, i);
    }

    private static void c(HealthTextView healthTextView, cdu cduVar, int i) {
        Context context = BaseApplication.getContext();
        String b = SharedPreferenceManager.b(context, Integer.toString(PrebakedEffectId.RT_HEARTBEAT), "configuredPageActivityServerCurrentTime" + i);
        OperationInteractorsApi operationInteractorsApi = (OperationInteractorsApi) Services.a("OperationBundle", OperationInteractorsApi.class);
        if (operationInteractorsApi == null) {
            LogUtil.h("ConfiguredPage_UiUtil", "operationInteractorsApi = null");
            return;
        }
        int activityStatus = operationInteractorsApi.getActivityStatus(b, cduVar.c(), cduVar.h());
        if (activityStatus == 0) {
            healthTextView.setVisibility(0);
            healthTextView.setText(context.getResources().getString(R$string.IDS_activity_social_coming_soon));
            healthTextView.setBackground(context.getResources().getDrawable(R.drawable._2131427525_res_0x7f0b00c5));
        } else if (activityStatus == 1) {
            healthTextView.setVisibility(0);
            healthTextView.setText(context.getResources().getString(R$string.IDS_hwh_home_group_underway));
            healthTextView.setBackground(context.getResources().getDrawable(R.drawable._2131427526_res_0x7f0b00c6));
        } else if (activityStatus == -1) {
            healthTextView.setVisibility(0);
            healthTextView.setText(context.getResources().getString(R$string.IDS_activity_social_is_over));
            healthTextView.setBackground(context.getResources().getDrawable(R.drawable._2131427523_res_0x7f0b00c3));
        } else {
            healthTextView.setVisibility(8);
            LogUtil.a("ConfiguredPage_UiUtil", "setHuaweiActivityStatusUi activity status is empty.");
        }
    }

    private static String a(cdu cduVar) {
        if (cduVar == null) {
            LogUtil.h("ConfiguredPage_UiUtil", "getPageAttribute itemObject is null.");
            return "";
        }
        String y = cduVar.y();
        return !TextUtils.isEmpty(y) ? y.trim() : "";
    }

    public static void dou_(HealthTextView healthTextView, LinearLayout linearLayout, HealthTextView healthTextView2, cdu cduVar, int i) {
        if (healthTextView == null || linearLayout == null || healthTextView2 == null) {
            LogUtil.h("ConfiguredPage_UiUtil", "setCommonViewTitle titleTextView or textAreaLayout or textAreaTitleView is null.");
            return;
        }
        if (cduVar == null) {
            LogUtil.h("ConfiguredPage_UiUtil", "setCommonViewTitle itemObject is null.");
            return;
        }
        Context context = BaseApplication.getContext();
        String o = cduVar.o();
        int i2 = cduVar.i();
        ViewGroup.LayoutParams layoutParams = healthTextView.getLayoutParams();
        RelativeLayout.LayoutParams layoutParams2 = layoutParams instanceof RelativeLayout.LayoutParams ? (RelativeLayout.LayoutParams) layoutParams : null;
        if (i == 1) {
            linearLayout.setVisibility(8);
            d(healthTextView, o, i2);
            dow_(context, healthTextView, layoutParams2);
        } else {
            if (i == 2) {
                linearLayout.setVisibility(8);
                d(healthTextView, o, i2);
                dov_(context, healthTextView, layoutParams2);
                return;
            }
            healthTextView.setVisibility(8);
            if (!TextUtils.isEmpty(o) && i2 == 1) {
                linearLayout.setVisibility(0);
                healthTextView2.setText(o);
            } else {
                linearLayout.setVisibility(8);
            }
        }
    }

    private static void dov_(Context context, HealthTextView healthTextView, RelativeLayout.LayoutParams layoutParams) {
        if (context == null || healthTextView == null || layoutParams == null) {
            LogUtil.h("ConfiguredPage_UiUtil", "setTextInnerCenterTitle context or titleTextView or titleParams is null.");
            return;
        }
        layoutParams.removeRule(12);
        layoutParams.removeRule(20);
        layoutParams.addRule(15);
        layoutParams.setMarginStart(nrr.e(context, 12.0f));
        healthTextView.setTextSize(0, context.getResources().getDimensionPixelSize(R.dimen._2131365064_res_0x7f0a0cc8));
        healthTextView.setTextColor(context.getResources().getColor(R.color._2131299236_res_0x7f090ba4));
        healthTextView.setTypeface(Typeface.create(context.getResources().getString(R$string.textFontFamilyMedium), 0));
        healthTextView.setLayoutParams(layoutParams);
    }

    private static void dow_(Context context, HealthTextView healthTextView, RelativeLayout.LayoutParams layoutParams) {
        if (context == null || healthTextView == null || layoutParams == null) {
            LogUtil.h("ConfiguredPage_UiUtil", "setTextInnerImageTitle context or titleTextView or titleParams is null.");
            return;
        }
        layoutParams.removeRule(15);
        layoutParams.addRule(12);
        layoutParams.addRule(20);
        layoutParams.setMarginStart(nrr.e(context, 16.0f));
        layoutParams.bottomMargin = nrr.e(context, 8.0f);
        healthTextView.setTextSize(0, context.getResources().getDimensionPixelSize(R.dimen._2131365081_res_0x7f0a0cd9));
        healthTextView.setTextColor(context.getResources().getColor(R.color._2131297005_res_0x7f0902ed));
        healthTextView.setTypeface(Typeface.create(context.getResources().getString(R$string.textFontFamilyMedium), 0));
        healthTextView.setLayoutParams(layoutParams);
    }
}
