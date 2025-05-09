package defpackage;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.text.TextPaint;
import android.text.TextUtils;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.core.content.ContextCompat;
import com.apprichtap.haptic.base.PrebakedEffectId;
import com.huawei.haf.handler.HandlerExecutor;
import com.huawei.health.R;
import com.huawei.health.marketing.datatype.SingleDailyMomentContent;
import com.huawei.health.marketing.datatype.SingleGridContent;
import com.huawei.health.marketing.datatype.templates.CornerTemplate;
import com.huawei.health.servicesui.R$string;
import com.huawei.hihealth.util.HiJsonUtil;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.subheader.HealthSubHeader;
import com.huawei.ui.main.stories.soical.interactor.OperationInteractorsApi;
import health.compact.a.LanguageUtil;
import health.compact.a.Services;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.UnitUtil;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;

/* loaded from: classes3.dex */
public class eiv {
    public static void d(HealthTextView healthTextView, String str, boolean z) {
        if (healthTextView == null) {
            LogUtil.h("UiUtils", "setTitleUi titleTextView is null.");
        } else if (!TextUtils.isEmpty(str) && z) {
            healthTextView.setVisibility(0);
            healthTextView.setText(str);
        } else {
            healthTextView.setVisibility(8);
        }
    }

    public static void a(HealthTextView healthTextView, String str, boolean z, boolean z2) {
        if (healthTextView == null) {
            LogUtil.h("UiUtils", "setTitleUi titleTextView is null.");
        } else if (!TextUtils.isEmpty(str) && z) {
            healthTextView.setVisibility(0);
            healthTextView.setText(str);
        } else {
            healthTextView.setVisibility(z2 ? 4 : 8);
        }
    }

    public static void a(HealthTextView healthTextView, String str, boolean z) {
        d(healthTextView, str, z);
    }

    public static void c(HealthTextView healthTextView, String str, boolean z, boolean z2) {
        a(healthTextView, str, z, z2);
    }

    public static void d(HealthTextView healthTextView, String str, String str2) {
        String d;
        if (healthTextView == null) {
            LogUtil.h("UiUtils", "setPageAttributeUi attributeTextView is null.");
            return;
        }
        if (TextUtils.isEmpty(str)) {
            LogUtil.h("UiUtils", "setPageAttributeUi category is null.");
            healthTextView.setVisibility(8);
            return;
        }
        if (TextUtils.equals(str, "Mixed")) {
            d = d(str2);
        } else {
            d = d(str);
        }
        if (TextUtils.isEmpty(d)) {
            LogUtil.h("UiUtils", "pageAttributeText is empty.");
            healthTextView.setVisibility(8);
        } else {
            healthTextView.setVisibility(0);
            healthTextView.setText(d);
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public static String d(String str) {
        char c;
        if (TextUtils.isEmpty(str)) {
            LogUtil.h("UiUtils", "getItemCategory category is empty.");
            return null;
        }
        Resources resources = BaseApplication.getContext().getResources();
        str.hashCode();
        switch (str.hashCode()) {
            case -2053342301:
                if (str.equals("Merchandise")) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            case -1591322833:
                if (str.equals(SingleDailyMomentContent.ACTIVITY_TYPE)) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            case -677168338:
                if (str.equals("Advertisements")) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            case -658498292:
                if (str.equals(SingleDailyMomentContent.INFORMATION_TYPE)) {
                    c = 3;
                    break;
                }
                c = 65535;
                break;
            case -646160747:
                if (str.equals("Service")) {
                    c = 4;
                    break;
                }
                c = 65535;
                break;
            case 2024262715:
                if (str.equals(SingleDailyMomentContent.COURSE_TYPE)) {
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
            return resources.getString(R$string.IDS_attribute_merchandise);
        }
        if (c == 1) {
            return resources.getString(R$string.IDS_main_home_bottom_text_activity);
        }
        if (c == 2) {
            return resources.getString(R$string.IDS_messageCenter_ad_logo);
        }
        if (c == 3) {
            return resources.getString(R$string.IDS_social_information);
        }
        if (c == 4) {
            return resources.getString(R$string.IDS_hw_messagecenter_service);
        }
        if (c == 5) {
            return resources.getString(R$string.IDS_configured_page_attribute_course);
        }
        LogUtil.h("UiUtils", "category is empty.");
        return null;
    }

    public static void c(HealthTextView healthTextView, SingleGridContent singleGridContent, String str) {
        String str2;
        if (healthTextView == null || singleGridContent == null) {
            LogUtil.h("UiUtils", "setJoinNumberOrReadCount textView or object is null.");
            return;
        }
        if (TextUtils.equals(str, SingleDailyMomentContent.INFORMATION_TYPE) || TextUtils.equals(singleGridContent.getItemCategory(), SingleDailyMomentContent.INFORMATION_TYPE)) {
            LogUtil.a("UiUtils", "numOfPeople:", singleGridContent.getNumberOfPeople() + "--", singleGridContent.getTheme());
            int numberOfPeople = singleGridContent.getNumberOfPeople();
            if (!TextUtils.isEmpty(singleGridContent.getDynamicDataId()) && numberOfPeople >= 0) {
                String replace = BaseApplication.getContext().getResources().getQuantityString(R.plurals._2130903294_res_0x7f0300fe, numberOfPeople, Integer.valueOf(numberOfPeople)).replace(String.valueOf(numberOfPeople), UnitUtil.e(numberOfPeople, 1, 0));
                healthTextView.setVisibility(0);
                healthTextView.setText(replace);
                return;
            }
            healthTextView.setVisibility(8);
            return;
        }
        if (TextUtils.equals(str, SingleDailyMomentContent.ACTIVITY_TYPE) || TextUtils.equals(singleGridContent.getItemCategory(), SingleDailyMomentContent.ACTIVITY_TYPE) || TextUtils.equals(str, SingleDailyMomentContent.COURSE_TYPE) || TextUtils.equals(singleGridContent.getItemCategory(), SingleDailyMomentContent.COURSE_TYPE)) {
            LogUtil.a("UiUtils", "numOfPeople:", singleGridContent.getNumberOfPeople() + "--", singleGridContent.getTheme());
            int numberOfPeople2 = singleGridContent.getNumberOfPeople();
            if (!TextUtils.isEmpty(singleGridContent.getDynamicDataId()) && numberOfPeople2 > 0) {
                healthTextView.setVisibility(0);
                String num = Integer.toString(numberOfPeople2);
                try {
                    str2 = UnitUtil.e(numberOfPeople2, 1, 0);
                } catch (Exception e) {
                    e = e;
                    str2 = "";
                }
                try {
                    num = BaseApplication.getContext().getResources().getQuantityString(R.plurals._2130903123_res_0x7f030053, numberOfPeople2, str2);
                } catch (Exception e2) {
                    e = e2;
                    ReleaseLogUtil.c("UiUtils", "setJoinNumberOrReadCount failed, cause exception = ", e.getMessage(), ", cause = ", e.getCause(), ", numberOfPeople = ", str2, ", numOfPeople = ", Integer.valueOf(numberOfPeople2));
                    healthTextView.setText(num);
                    return;
                }
                healthTextView.setText(num);
                return;
            }
            healthTextView.setVisibility(8);
            return;
        }
        healthTextView.setVisibility(8);
    }

    public static void a(HealthTextView healthTextView, SingleGridContent singleGridContent, String str, int i) {
        if (healthTextView == null) {
            LogUtil.h("UiUtils", "setActivityStatusView activityStatusTextView is null.");
            return;
        }
        if (singleGridContent == null) {
            LogUtil.h("UiUtils", "setActivityStatusView itemObject is null.");
            return;
        }
        if (!TextUtils.equals(singleGridContent.getItemCategory(), SingleDailyMomentContent.ACTIVITY_TYPE) && !TextUtils.equals(str, SingleDailyMomentContent.ACTIVITY_TYPE)) {
            LogUtil.h("UiUtils", "setActivityStatusView not activity category.");
            return;
        }
        Context context = BaseApplication.getContext();
        String b = SharedPreferenceManager.b(context, Integer.toString(PrebakedEffectId.RT_ICE), "marketingServerCurrentTime" + i);
        OperationInteractorsApi operationInteractorsApi = (OperationInteractorsApi) Services.a("OperationBundle", OperationInteractorsApi.class);
        if (operationInteractorsApi == null) {
            LogUtil.h("UiUtils", "operationInteractorsApi = null");
            return;
        }
        LogUtil.a("UiUtils", "setActivityStatusView currentTime:", b, "--", singleGridContent.getBeginDate(), "--", singleGridContent.getEndDate(), "--", singleGridContent.getTheme(), "--id:", singleGridContent.getDynamicDataId());
        int activityStatus = operationInteractorsApi.getActivityStatus(b, singleGridContent.getBeginDate(), singleGridContent.getEndDate());
        if (activityStatus == 0) {
            healthTextView.setVisibility(0);
            healthTextView.setText(context.getResources().getString(R$string.IDS_activity_social_coming_soon));
            c(healthTextView, R.color._2131298764_res_0x7f0909cc);
        } else if (activityStatus == 1) {
            healthTextView.setVisibility(0);
            healthTextView.setText(context.getResources().getString(R$string.IDS_hwh_home_group_underway));
            c(healthTextView, R.color._2131298764_res_0x7f0909cc);
        } else if (activityStatus == -1) {
            healthTextView.setVisibility(0);
            healthTextView.setText(context.getResources().getString(R$string.IDS_activity_social_is_over));
            c(healthTextView, R.color._2131298164_res_0x7f090774);
        } else {
            healthTextView.setVisibility(8);
            LogUtil.a("UiUtils", "setActivityStatusView activity status is empty.");
        }
    }

    private static void c(HealthTextView healthTextView, int i) {
        try {
            GradientDrawable gradientDrawable = new GradientDrawable();
            gradientDrawable.setShape(0);
            gradientDrawable.setCornerRadii(LanguageUtil.bc(BaseApplication.getContext()) ? new float[]{d(16.0f), d(16.0f), 0.0f, 0.0f, d(8.0f), d(8.0f), 0.0f, 0.0f} : new float[]{0.0f, 0.0f, d(16.0f), d(16.0f), 0.0f, 0.0f, d(8.0f), d(8.0f)});
            gradientDrawable.setColor(BaseApplication.getContext().getColor(i));
            healthTextView.setBackground(gradientDrawable);
        } catch (IllegalArgumentException unused) {
            LogUtil.b("UiUtils", "IllegalArgumentException");
        }
    }

    public static int d(Context context, int i) {
        int a2 = eie.a(context) - i;
        if (!nsn.ag(BaseApplication.getActivity())) {
            return a2;
        }
        int b = (a2 - nrr.b(BaseApplication.getContext())) / 2;
        LogUtil.h("UiUtils", "getBannerImageWidth() width = ", Integer.valueOf(b));
        return b;
    }

    public static void a(HealthTextView healthTextView, boolean z, boolean z2) {
        if (healthTextView == null || !b()) {
            return;
        }
        if (z) {
            healthTextView.setVisibility(8);
            return;
        }
        if (z2) {
            healthTextView.setMaxLines(3);
        }
        if (nsn.s()) {
            healthTextView.setTextSize(1, (healthTextView.getTextSize() / healthTextView.getContext().getResources().getDisplayMetrics().scaledDensity) * 2.0f);
        }
    }

    public static boolean b() {
        return nsn.c() >= 1.25f;
    }

    public static void c(HealthTextView healthTextView, CornerTemplate cornerTemplate, float[] fArr) {
        if (cornerTemplate == null) {
            LogUtil.h("UiUtils", "content = null");
            return;
        }
        if (fArr == null) {
            LogUtil.h("UiUtils", "corner radius object is null");
            return;
        }
        if (fArr.length != 8) {
            LogUtil.h("UiUtils", "the quantity of corner params is not equal 8");
            return;
        }
        if (!b(healthTextView, cornerTemplate)) {
            LogUtil.h("UiUtils", "setCornerShow can not show");
            return;
        }
        String corner = cornerTemplate.getCorner();
        if (TextUtils.isEmpty(corner)) {
            return;
        }
        c(healthTextView, cornerTemplate, fArr, corner.length());
        healthTextView.setGravity(17);
        healthTextView.setText(cornerTemplate.getCorner());
        healthTextView.setVisibility(0);
    }

    private static void c(HealthTextView healthTextView, CornerTemplate cornerTemplate, float[] fArr, int i) {
        if (i == 1) {
            GradientDrawable gradientDrawable = new GradientDrawable();
            gradientDrawable.setShape(1);
            gradientDrawable.setSize(d(16.0f), d(16.0f));
            gradientDrawable.setColor(Color.parseColor(cornerTemplate.getCornerColor()));
            healthTextView.setBackground(gradientDrawable);
            healthTextView.setPadding(0, 0, 0, 0);
            return;
        }
        if (i >= 2) {
            try {
                GradientDrawable gradientDrawable2 = new GradientDrawable();
                gradientDrawable2.setShape(0);
                gradientDrawable2.setCornerRadii(fArr);
                gradientDrawable2.setColor(Color.parseColor(cornerTemplate.getCornerColor()));
                healthTextView.setBackground(gradientDrawable2);
                return;
            } catch (IllegalArgumentException unused) {
                LogUtil.b("UiUtils", "IllegalArgumentException");
                return;
            }
        }
        LogUtil.h("UiUtils", "setCornerMultiShow corner text length is: ", Integer.valueOf(i));
    }

    public static void a(HealthTextView healthTextView, CornerTemplate cornerTemplate) {
        if (cornerTemplate == null) {
            LogUtil.h("UiUtils", "content = null");
            return;
        }
        if (!b(healthTextView, cornerTemplate)) {
            LogUtil.h("UiUtils", "setCornerShow can not show");
            return;
        }
        try {
            float d = d(4.0f);
            GradientDrawable gradientDrawable = new GradientDrawable();
            gradientDrawable.setShape(0);
            gradientDrawable.setCornerRadii(new float[]{d, d, d, d, d, d, d, d});
            gradientDrawable.setStroke(d(1.0f), Color.parseColor(cornerTemplate.getCornerColor()));
            healthTextView.setBackground(gradientDrawable);
        } catch (IllegalArgumentException unused) {
            LogUtil.b("UiUtils", "IllegalArgumentException");
        }
        healthTextView.setGravity(17);
        healthTextView.setText(cornerTemplate.getCorner());
        healthTextView.setTextColor(Color.parseColor(cornerTemplate.getCornerColor()));
        healthTextView.setVisibility(0);
    }

    public static void d(HealthTextView healthTextView, CornerTemplate cornerTemplate, boolean z) {
        if (cornerTemplate == null) {
            LogUtil.h("UiUtils", "content = null");
            return;
        }
        LogUtil.a("UiUtils", "content: ", cornerTemplate.toString());
        if (!b(healthTextView, cornerTemplate)) {
            LogUtil.h("UiUtils", "setCornerShow can not show");
            return;
        }
        try {
            GradientDrawable gradientDrawable = new GradientDrawable();
            gradientDrawable.setShape(0);
            gradientDrawable.setCornerRadii(LanguageUtil.bc(BaseApplication.getContext()) ? z ? new float[]{d(8.0f), d(8.0f), 0.0f, 0.0f, d(8.0f), d(8.0f), 0.0f, 0.0f} : new float[]{d(16.0f), d(16.0f), 0.0f, 0.0f, d(8.0f), d(8.0f), 0.0f, 0.0f} : z ? new float[]{0.0f, 0.0f, d(8.0f), d(8.0f), 0.0f, 0.0f, d(8.0f), d(8.0f)} : new float[]{0.0f, 0.0f, d(16.0f), d(16.0f), 0.0f, 0.0f, d(8.0f), d(8.0f)});
            gradientDrawable.setColor(Color.parseColor(cornerTemplate.getCornerColor()));
            healthTextView.setBackground(gradientDrawable);
        } catch (IllegalArgumentException unused) {
            LogUtil.b("UiUtils", "IllegalArgumentException");
        }
        healthTextView.setGravity(17);
        healthTextView.setText(cornerTemplate.getCorner());
        healthTextView.setVisibility(0);
    }

    public static void alZ_(HealthTextView healthTextView, SingleGridContent singleGridContent, ImageView imageView) {
        if (singleGridContent == null || healthTextView == null || imageView == null) {
            LogUtil.h("UiUtils", "content = null");
        } else if ("1".equals(singleGridContent.getInfoType())) {
            healthTextView.setGravity(17);
            healthTextView.setText(BaseApplication.getContext().getResources().getString(R$string.IDS_sleep_series_icon));
            healthTextView.setVisibility(0);
            imageView.setVisibility(8);
        }
    }

    public static void d(HealthTextView healthTextView, CornerTemplate cornerTemplate) {
        if (!b(healthTextView, cornerTemplate)) {
            LogUtil.h("UiUtils", "setCornerStyle can not show");
            return;
        }
        try {
            healthTextView.setTextColor(Color.parseColor(cornerTemplate.getCornerColor()));
            GradientDrawable gradientDrawable = new GradientDrawable();
            gradientDrawable.setShape(0);
            gradientDrawable.setCornerRadius(d(2.0f));
            gradientDrawable.setStroke(2, Color.parseColor(cornerTemplate.getCornerColor()));
            healthTextView.setBackground(gradientDrawable);
        } catch (IllegalArgumentException unused) {
            LogUtil.b("UiUtils", "IllegalArgumentException");
        }
        healthTextView.setGravity(17);
        healthTextView.setText(cornerTemplate.getCorner());
        healthTextView.setVisibility(0);
    }

    public static void c(HealthTextView healthTextView, CornerTemplate cornerTemplate, int i, int i2, int i3, int i4) {
        if (cornerTemplate == null) {
            LogUtil.h("UiUtils", "content = null");
            return;
        }
        if (!b(healthTextView, cornerTemplate)) {
            LogUtil.h("UiUtils", "setCornerShow can not show");
            return;
        }
        try {
            GradientDrawable gradientDrawable = new GradientDrawable();
            gradientDrawable.setShape(0);
            float f = i;
            float f2 = i2;
            float f3 = i3;
            float f4 = i4;
            gradientDrawable.setCornerRadii(new float[]{d(f), d(f), d(f2), d(f2), d(f3), d(f3), d(f4), d(f4)});
            gradientDrawable.setColor(Color.parseColor(cornerTemplate.getCornerColor()));
            healthTextView.setBackground(gradientDrawable);
        } catch (IllegalArgumentException unused) {
            LogUtil.b("UiUtils", "IllegalArgumentException");
        }
        healthTextView.setGravity(17);
        healthTextView.setText(cornerTemplate.getCorner());
        healthTextView.setVisibility(0);
    }

    private static boolean b(HealthTextView healthTextView, CornerTemplate cornerTemplate) {
        if (healthTextView == null) {
            LogUtil.h("UiUtils", "textView = null");
            return false;
        }
        if (cornerTemplate.getCornerVisibility() && !TextUtils.isEmpty(cornerTemplate.getCorner()) && !TextUtils.isEmpty(cornerTemplate.getCornerColor())) {
            return true;
        }
        healthTextView.setVisibility(8);
        return false;
    }

    public static int d(float f) {
        return nsn.c(BaseApplication.getContext(), f);
    }

    public static void e(HealthTextView healthTextView, int i) {
        if (healthTextView == null || i <= 0) {
            LogUtil.h("UiUtils", "moveCornerMark failed");
            return;
        }
        TextPaint paint = healthTextView.getPaint();
        CharSequence text = healthTextView.getText();
        LogUtil.h("UiUtils", "corner mark text: ", text);
        if (TextUtils.isEmpty(text)) {
            return;
        }
        int measureText = (int) paint.measureText(text.toString());
        LogUtil.b("UiUtils", "corner mark image width and text width: ", Integer.valueOf(i), ",", Integer.valueOf(measureText));
        c(healthTextView, text, i / 2, healthTextView.getPaddingLeft() + healthTextView.getPaddingRight() + measureText);
    }

    private static void c(HealthTextView healthTextView, CharSequence charSequence, int i, int i2) {
        if (d(charSequence)) {
            c(healthTextView, i, i2);
        } else {
            a(healthTextView, i, i2);
        }
    }

    private static void a(final HealthTextView healthTextView, int i, int i2) {
        final int i3;
        int left = healthTextView.getLeft() + i;
        int left2 = healthTextView.getLeft() + (i2 / 2);
        if (left2 < left) {
            if (left2 < left && left2 >= healthTextView.getLeft()) {
                i3 = Math.abs(left - left2);
                LogUtil.c("UiUtils", "corner mark text moved width: ", Integer.valueOf(i3));
                HandlerExecutor.a(new Runnable() { // from class: eiv.1
                    @Override // java.lang.Runnable
                    public void run() {
                        HealthTextView healthTextView2 = HealthTextView.this;
                        healthTextView2.setLeft(healthTextView2.getLeft() + i3);
                        HealthTextView healthTextView3 = HealthTextView.this;
                        healthTextView3.setRight(healthTextView3.getRight() + i3);
                    }
                });
            }
            LogUtil.h("UiUtils", "get textMoveWidth error");
        }
        i3 = 0;
        LogUtil.c("UiUtils", "corner mark text moved width: ", Integer.valueOf(i3));
        HandlerExecutor.a(new Runnable() { // from class: eiv.1
            @Override // java.lang.Runnable
            public void run() {
                HealthTextView healthTextView2 = HealthTextView.this;
                healthTextView2.setLeft(healthTextView2.getLeft() + i3);
                HealthTextView healthTextView3 = HealthTextView.this;
                healthTextView3.setRight(healthTextView3.getRight() + i3);
            }
        });
    }

    private static void c(final HealthTextView healthTextView, int i, int i2) {
        final int abs = Math.abs((i - i2) - (i2 / 2));
        LogUtil.c("UiUtils", "corner mark text moved width: ", Integer.valueOf(abs));
        HandlerExecutor.a(new Runnable() { // from class: eiv.3
            @Override // java.lang.Runnable
            public void run() {
                HealthTextView healthTextView2 = HealthTextView.this;
                healthTextView2.setLeft(healthTextView2.getLeft() + abs);
                HealthTextView healthTextView3 = HealthTextView.this;
                healthTextView3.setRight(healthTextView3.getRight() + abs);
            }
        });
    }

    private static boolean d(CharSequence charSequence) {
        return !TextUtils.isEmpty(charSequence) && charSequence.length() == 1;
    }

    public static void e(boolean z, HealthSubHeader healthSubHeader) {
        if (!z) {
            ViewGroup.LayoutParams layoutParams = healthSubHeader.getLayoutParams();
            if (layoutParams != null) {
                healthSubHeader.setVisibility(4);
                layoutParams.height = BaseApplication.getContext().getResources().getDimensionPixelSize(R.dimen._2131362573_res_0x7f0a030d);
                return;
            }
            return;
        }
        healthSubHeader.setVisibility(0);
    }

    public static Drawable alY_() {
        int i = nsn.v(BaseApplication.getContext()) ? R.drawable._2131430340_res_0x7f0b0bc4 : R.drawable._2131430339_res_0x7f0b0bc3;
        if (LanguageUtil.bc(BaseApplication.getContext())) {
            return nrz.cKn_(BaseApplication.getContext(), i);
        }
        return ContextCompat.getDrawable(BaseApplication.getContext(), i);
    }

    public static CharSequence a(SingleGridContent singleGridContent) {
        if (singleGridContent == null) {
            ReleaseLogUtil.d("UiUtils", "getContentDescription singleGridContent is null");
            return "";
        }
        String theme = singleGridContent.getTheme();
        String trim = theme != null ? theme.trim() : "";
        String description = singleGridContent.getDescription();
        if (description != null) {
            String trim2 = description.trim();
            if (!TextUtils.isEmpty(trim) && !TextUtils.isEmpty(trim2)) {
                trim = nsf.b(R$string.accessibility_theme_description, trim, trim2);
            }
        }
        String str = trim;
        if (TextUtils.isEmpty(str)) {
            String itemCategory = singleGridContent.getItemCategory();
            String d = d(itemCategory);
            LogUtil.h("UiUtils", "getContentDescription contentDescription ", str, " itemCategory ", itemCategory, " itemCategoryText ", d);
            str = TextUtils.isEmpty(d) ? nsf.h(R$string.IDS_configured_page_attribute_course) : d;
        }
        if (jcf.c()) {
            LogUtil.a("UiUtils", "getContentDescription singleGridContent ", HiJsonUtil.e(singleGridContent));
        }
        return str;
    }
}
