package defpackage;

import android.content.Context;
import android.content.res.Resources;
import android.net.Uri;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.huawei.basefitnessadvice.callback.UiCallback;
import com.huawei.basefitnessadvice.model.intplan.IntPlan;
import com.huawei.haf.application.BaseApplication;
import com.huawei.health.R;
import com.huawei.health.fitnessadvice.api.FitnessAdviceApi;
import com.huawei.health.h5pro.core.H5ProLaunchOption;
import com.huawei.health.plan.api.PlanApi;
import com.huawei.hihealth.HiUserInfo;
import com.huawei.hihealth.api.HiHealthManager;
import com.huawei.hihealth.data.listener.HiCommonListener;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.operation.utils.WebViewHelp;
import com.huawei.pluginfitnessadvice.FitnessPackageInfo;
import com.huawei.ui.commonui.dialog.HealthDataInputDialog;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.numberpicker.HealthNumberPicker;
import com.huawei.ui.main.R$string;
import health.compact.a.Services;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.UnitUtil;
import health.compact.a.Utils;
import health.compact.a.util.LogUtil;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/* loaded from: classes9.dex */
public class pgk {
    public static String c(cfi cfiVar, double d, double d2) {
        return cfiVar == null ? "addRecord" : d(BaseApplication.e()) ? "startPlan" : (d <= 0.0d || d2 <= 0.0d) ? d2 > 0.0d ? doj.b(d2, Utils.o(), cfiVar.c(), cfiVar.a()) > 2 ? "todoLoseWeight" : "todoGainWeight" : "addRecord" : ((double) cfiVar.m()) > d ? "loseWeightGoal" : "gainWeightGoal";
    }

    public static void d(Context context, String str, String str2, String str3, int i) {
        LogUtil.d("VIPCard_MemberWeightUtil", "enter goToCustomRecipe");
        H5ProLaunchOption.Builder builder = new H5ProLaunchOption.Builder();
        builder.addGlobalBiParam(WebViewHelp.BI_KEY_PULL_FROM, str).addGlobalBiParam("resourceName", str2).addGlobalBiParam("resourceId", str3).addGlobalBiParam("pullOrder", String.valueOf(i + 1));
        bzs.e().loadH5ProApp(context, "com.huawei.health.h5.custom-recipe", builder);
    }

    public static void d(Context context, String str) {
        LogUtil.d("VIPCard_MemberWeightUtil", "goToAiFitnessPlan, url = ", str);
        FitnessAdviceApi fitnessAdviceApi = (FitnessAdviceApi) Services.a("PluginFitnessAdvice", FitnessAdviceApi.class);
        if (fitnessAdviceApi != null) {
            LogUtil.d("VIPCard_MemberWeightUtil", "jump to Ai fitness plan");
            fitnessAdviceApi.jumpFitnessPage(context, Uri.parse(str), null);
        }
    }

    public static void c(Context context, final IBaseResponseCallback iBaseResponseCallback) {
        if (iBaseResponseCallback == null) {
            LogUtil.c("VIPCard_MemberWeightUtil", "getWeightUserData callback is null");
        } else {
            HiHealthManager.d(context).fetchUserData(new HiCommonListener() { // from class: pgk.5
                @Override // com.huawei.hihealth.data.listener.HiCommonListener
                public void onSuccess(int i, Object obj) {
                    if (obj != null) {
                        List list = (List) obj;
                        if (koq.b(list)) {
                            LogUtil.c("VIPCard_MemberWeightUtil", "getWeightUserData onSuccess userInfos is null or size is zero");
                            IBaseResponseCallback.this.d(1, new cfi());
                            return;
                        }
                        HiUserInfo hiUserInfo = (HiUserInfo) list.get(0);
                        if (hiUserInfo == null) {
                            LogUtil.c("VIPCard_MemberWeightUtil", "getWeightUserData onSuccess, HiUserInfo is null");
                            IBaseResponseCallback.this.d(1, new cfi());
                            return;
                        }
                        int gender = hiUserInfo.getGender();
                        if (gender != 2 && gender != 0 && gender != 1) {
                            gender = -100;
                        }
                        cfi cfiVar = new cfi();
                        cfiVar.a(hiUserInfo.getAge());
                        cfiVar.a((byte) gender);
                        cfiVar.d(hiUserInfo.getHeight());
                        cfiVar.b(hiUserInfo.getWeight());
                        IBaseResponseCallback.this.d(0, cfiVar);
                        return;
                    }
                    IBaseResponseCallback.this.d(1, new cfi());
                }

                @Override // com.huawei.hihealth.data.listener.HiCommonListener
                public void onFailure(int i, Object obj) {
                    LogUtil.e("VIPCard_MemberWeightUtil", "getWeightUserData fail");
                    IBaseResponseCallback.this.d(1, new cfi());
                }
            });
        }
    }

    public static boolean d(Context context) {
        String b = SharedPreferenceManager.b(context, "intelligent_plan", "intelligent_plan_create_temp");
        return b != null && Boolean.parseBoolean(b);
    }

    public static HealthDataInputDialog.e e(cfi cfiVar) {
        HealthDataInputDialog.e eVar;
        HealthDataInputDialog.DataSetFormatter c = c();
        HealthDataInputDialog.SelectedValueProcessor b = b();
        int a2 = UnitUtil.a();
        if (a2 == 1) {
            eVar = new HealthDataInputDialog.e((int) UnitUtil.b(10.0d), (int) UnitUtil.b(251.0d), c, b);
        } else if (a2 == 3) {
            eVar = new HealthDataInputDialog.e(22, 552, c, b);
        } else {
            eVar = new HealthDataInputDialog.e(10, 251, c, b);
        }
        eVar.a(qsj.e(100.0d, false)).b(BaseApplication.e().getResources().getString(R$string.IDS_hw_show_set_weight));
        eVar.d(1);
        if (cfiVar == null) {
            eVar.c(rag.d(70.0d));
        } else if (cfiVar.m() > 0.0f) {
            eVar.c(rag.d(cfiVar.m()));
        } else if (cfiVar.c() == 0) {
            eVar.c(rag.d(50.0d));
        } else {
            eVar.c(rag.d(70.0d));
        }
        return eVar;
    }

    public static HealthDataInputDialog.e a(cfi cfiVar) {
        HealthDataInputDialog.e eVar = new HealthDataInputDialog.e(50, 250, c(), d());
        eVar.a(BaseApplication.e().getResources().getString(R$string.IDS_cm));
        eVar.b(BaseApplication.e().getResources().getString(R$string.IDS_hw_show_set_height));
        eVar.d(1);
        if (cfiVar != null) {
            LogUtil.d("VIPCard_MemberWeightUtil", "height = ", Integer.valueOf(cfiVar.d()));
            if (cfiVar.d() > 0) {
                eVar.c(d(cfiVar.d(), 50.0d));
            } else if (cfiVar.c() == 0) {
                eVar.c(d(160.0d, 50.0d));
            } else {
                eVar.c(d(170.0d, 50.0d));
            }
        } else {
            eVar.c(d(170.0d, 50.0d));
        }
        return eVar;
    }

    public static View doK_(final Context context, cfi cfiVar) {
        if (context == null) {
            return null;
        }
        View inflate = LayoutInflater.from(context).inflate(R.layout.member_open_inch_height_dialog, (ViewGroup) null);
        HealthNumberPicker healthNumberPicker = (HealthNumberPicker) inflate.findViewById(R.id.height_ft_number_picker_v9);
        String[] strArr = new String[8];
        int i = 0;
        while (i < 8) {
            int i2 = i + 1;
            strArr[i] = context.getResources().getQuantityString(R.plurals._2130903306_res_0x7f03010a, i2, Integer.valueOf(i2));
            i = i2;
        }
        healthNumberPicker.setDisplayedValues(strArr);
        healthNumberPicker.setMinValue(0);
        healthNumberPicker.setMaxValue(7);
        healthNumberPicker.setSelectorPaintColor(context.getResources().getColor(R.color._2131299373_res_0x7f090c2d));
        HealthNumberPicker healthNumberPicker2 = (HealthNumberPicker) inflate.findViewById(R.id.height_inch_number_picker_v9);
        String[] strArr2 = new String[12];
        for (int i3 = 0; i3 < 12; i3++) {
            strArr2[i3] = context.getResources().getQuantityString(R.plurals._2130903219_res_0x7f0300b3, i3, Integer.valueOf(i3));
        }
        healthNumberPicker2.setDisplayedValues(strArr2);
        healthNumberPicker2.setMinValue(0);
        healthNumberPicker2.setMaxValue(11);
        healthNumberPicker2.setSelectorPaintColor(context.getResources().getColor(R.color._2131299373_res_0x7f090c2d));
        final int[] c = c(cfiVar);
        if (cfiVar != null && cfiVar.d() > 30) {
            int[] j = UnitUtil.j(BigDecimal.valueOf(cfiVar.d()).divide(new BigDecimal(100), 2, 4).doubleValue());
            if (j[0] > 0 && j[1] >= 0) {
                c = j;
            }
        }
        healthNumberPicker.setValue(c[0] - 1);
        healthNumberPicker2.setValue(c[1]);
        final HealthTextView healthTextView = (HealthTextView) inflate.findViewById(R.id.inch_height_value);
        healthTextView.setText(a(context, c));
        healthNumberPicker.setOnValuePickedListener(new HealthNumberPicker.OnPickedListener() { // from class: pgi
            @Override // com.huawei.ui.commonui.numberpicker.HealthNumberPicker.OnPickedListener
            public final void onValuePicked(int i4, int i5) {
                pgk.d(c, healthTextView, context, i4, i5);
            }
        });
        healthNumberPicker2.setOnValuePickedListener(new HealthNumberPicker.OnPickedListener() { // from class: pgp
            @Override // com.huawei.ui.commonui.numberpicker.HealthNumberPicker.OnPickedListener
            public final void onValuePicked(int i4, int i5) {
                pgk.a(c, healthTextView, context, i4, i5);
            }
        });
        return inflate;
    }

    static /* synthetic */ void d(int[] iArr, HealthTextView healthTextView, Context context, int i, int i2) {
        iArr[0] = i2 + 1;
        healthTextView.setText(a(context, iArr));
    }

    static /* synthetic */ void a(int[] iArr, HealthTextView healthTextView, Context context, int i, int i2) {
        iArr[1] = i2;
        healthTextView.setText(a(context, iArr));
    }

    private static int[] c(cfi cfiVar) {
        return (cfiVar == null || cfiVar.c() != 0) ? new int[]{5, 7} : new int[]{5, 3};
    }

    private static CharSequence a(Context context, int[] iArr) {
        if (iArr == null || iArr.length < 2) {
            return "";
        }
        Resources resources = context.getResources();
        int i = iArr[0];
        String quantityString = resources.getQuantityString(R.plurals._2130903306_res_0x7f03010a, i, Integer.valueOf(i));
        Resources resources2 = context.getResources();
        int i2 = iArr[1];
        return c(context.getResources().getString(R$string.IDS_member_bmi_selected_height, quantityString, resources2.getQuantityString(R.plurals._2130903219_res_0x7f0300b3, i2, Integer.valueOf(i2))), String.valueOf(iArr[0]), String.valueOf(iArr[1]));
    }

    public static CharSequence c(String str, String str2, String str3) {
        int lastIndexOf;
        int indexOf;
        int applyDimension = (int) TypedValue.applyDimension(1, 30.0f, BaseApplication.e().getResources().getDisplayMetrics());
        LogUtil.d("VIPCard_MemberWeightUtil", "textSize = ", Integer.valueOf(applyDimension));
        if (TextUtils.isEmpty(str)) {
            LogUtil.d("VIPCard_MemberWeightUtil", "getTextSizeSpanString() content or highlightText is empty.");
            return str;
        }
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(str);
        if (!TextUtils.isEmpty(str2) && (indexOf = str.indexOf(str2)) >= 0) {
            spannableStringBuilder.setSpan(new AbsoluteSizeSpan(applyDimension), indexOf, str2.length() + indexOf, 34);
        }
        if (!TextUtils.isEmpty(str3) && (lastIndexOf = str.lastIndexOf(str3)) >= 0) {
            spannableStringBuilder.setSpan(new AbsoluteSizeSpan(applyDimension), lastIndexOf, str3.length() + lastIndexOf, 34);
        }
        return spannableStringBuilder;
    }

    public static int d(double d, double d2) {
        try {
            return (int) (NumberFormat.getInstance(Locale.getDefault()).parse(UnitUtil.e(BigDecimal.valueOf(d).subtract(BigDecimal.valueOf(d2)).doubleValue(), 1, 1)).doubleValue() * 10.0d);
        } catch (ParseException e) {
            LogUtil.e("VIPCard_MemberWeightUtil", "parse weight data exception", e.getMessage());
            return 0;
        }
    }

    public static double c(double d, int i) {
        return new BigDecimal(d + (i * 0.1d)).setScale(1, 4).doubleValue();
    }

    public static HealthDataInputDialog.DataSetFormatter c() {
        return new HealthDataInputDialog.DataSetFormatter() { // from class: pgr
            @Override // com.huawei.ui.commonui.dialog.HealthDataInputDialog.DataSetFormatter
            public final String format(int i) {
                String e;
                e = UnitUtil.e(i, 1, 0);
                return e;
            }
        };
    }

    public static HealthDataInputDialog.SelectedValueProcessor b() {
        return new HealthDataInputDialog.SelectedValueProcessor() { // from class: pgk.4
            @Override // com.huawei.ui.commonui.dialog.HealthDataInputDialog.SelectedValueProcessor
            public int process(int i, int i2) {
                return i;
            }

            @Override // com.huawei.ui.commonui.dialog.HealthDataInputDialog.SelectedValueProcessor
            public String format(int i) {
                return UnitUtil.e(rag.a(i), 1, 1);
            }
        };
    }

    public static HealthDataInputDialog.SelectedValueProcessor d() {
        return new HealthDataInputDialog.SelectedValueProcessor() { // from class: pgk.2
            @Override // com.huawei.ui.commonui.dialog.HealthDataInputDialog.SelectedValueProcessor
            public int process(int i, int i2) {
                return i;
            }

            @Override // com.huawei.ui.commonui.dialog.HealthDataInputDialog.SelectedValueProcessor
            public String format(int i) {
                return UnitUtil.e(pgk.c(50.0d, i), 1, 1);
            }
        };
    }

    public static void b(final IBaseResponseCallback iBaseResponseCallback) {
        if (iBaseResponseCallback == null) {
            return;
        }
        LogUtil.d("VIPCard_MemberWeightUtil", "enter getRecommendedPlanList");
        PlanApi planApi = (PlanApi) Services.a("CoursePlanService", PlanApi.class);
        if (planApi == null) {
            LogUtil.c("VIPCard_MemberWeightUtil", "refreshFitnessPlan, getRecommendPlans : planApi is null.");
            iBaseResponseCallback.d(1, new ArrayList());
        } else {
            planApi.getAllFitnessPackage(-1, new UiCallback<List<FitnessPackageInfo>>() { // from class: pgk.1
                @Override // com.huawei.basefitnessadvice.callback.UiCallback
                public void onFailure(int i, String str) {
                    LogUtil.c("VIPCard_MemberWeightUtil", "onFailure errorCode = ", Integer.valueOf(i), " errorInfo = ", str);
                    IBaseResponseCallback.this.d(1, new ArrayList());
                }

                @Override // com.huawei.basefitnessadvice.callback.UiCallback
                /* renamed from: d, reason: merged with bridge method [inline-methods] */
                public void onSuccess(List<FitnessPackageInfo> list) {
                    LogUtil.d("VIPCard_MemberWeightUtil", "onSuccess invoke, data = ", list);
                    ArrayList arrayList = new ArrayList();
                    for (FitnessPackageInfo fitnessPackageInfo : list) {
                        if (fitnessPackageInfo.getPlanType() == IntPlan.PlanType.AI_FITNESS_PLAN.getType()) {
                            arrayList.add(fitnessPackageInfo);
                        }
                    }
                    LogUtil.d("VIPCard_MemberWeightUtil", "fitnessPackageInfo = ", arrayList);
                    IBaseResponseCallback.this.d(0, arrayList);
                }
            });
        }
    }

    public static SpannableString doL_(Context context, String str, String str2) {
        if (TextUtils.isEmpty(str)) {
            return new SpannableString("");
        }
        int indexOf = str.indexOf(str2);
        if (indexOf < 0) {
            return new SpannableString(str);
        }
        SpannableString spannableString = new SpannableString(str);
        spannableString.setSpan(new ForegroundColorSpan(context.getResources().getColor(R.color._2131299373_res_0x7f090c2d)), indexOf, str2.length() + indexOf, 17);
        return spannableString;
    }

    public static void a(float f) {
        ckm ckmVar = new ckm();
        ckmVar.setBodyFatRat(0.0f);
        ckmVar.setWeight(f);
        ckmVar.setStartTime(System.currentTimeMillis());
        ckmVar.e(true);
        ckmVar.setEndTime(System.currentTimeMillis());
        dfd dfdVar = new dfd(10006);
        dfdVar.e(new IBaseResponseCallback() { // from class: pgn
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public final void d(int i, Object obj) {
                LogUtil.d("VIPCard_MemberWeightUtil", "errorCode = ", Integer.valueOf(i), " objData = ", obj);
            }
        });
        dfdVar.onDataChanged(rag.a(), ckmVar);
    }
}
