package defpackage;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.text.SpannableString;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.TextAppearanceSpan;
import android.util.SparseArray;
import android.view.View;
import androidx.core.content.ContextCompat;
import com.huawei.basefitnessadvice.model.intplan.IntDayPlan;
import com.huawei.basefitnessadvice.model.intplan.IntPlan;
import com.huawei.haf.application.BaseApplication;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwbasemgr.ResponseCallback;
import com.huawei.hwcommonmodel.utils.DateFormatUtil;
import com.huawei.ui.commonui.dialog.CustomAlertDialog;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.imageview.HealthImageView;
import com.huawei.ui.commonui.numberpicker.HealthNumberPicker;
import health.compact.a.LanguageUtil;
import health.compact.a.LogUtil;
import health.compact.a.ReleaseLogUtil;
import health.compact.a.UnitUtil;
import java.util.ArrayList;
import java.util.Calendar;

/* loaded from: classes4.dex */
public class fze {
    private static Calendar b(IntPlan intPlan) {
        Calendar calendar = Calendar.getInstance();
        if (intPlan == null) {
            return calendar;
        }
        SparseArray<IntDayPlan> aIw_ = fyw.aIw_(intPlan);
        int size = aIw_.size();
        int b = DateFormatUtil.b(System.currentTimeMillis());
        for (int i = 0; i < size; i++) {
            int keyAt = aIw_.keyAt(i);
            if (keyAt > b && ase.a(aIw_.get(keyAt)) == 2) {
                calendar.setTimeInMillis(DateFormatUtil.c(keyAt));
            }
        }
        return calendar;
    }

    public static CustomAlertDialog a(Context context, IntPlan intPlan, ResponseCallback<Object> responseCallback) {
        Calendar calendar;
        SparseArray<IntDayPlan> aIw_ = fyw.aIw_(intPlan);
        int a2 = ase.a(aIw_.get(DateFormatUtil.b(System.currentTimeMillis())));
        Context e = BaseApplication.e();
        View inflate = View.inflate(e, R.layout.dialog_plan_adjust, null);
        boolean bc = LanguageUtil.bc(e);
        Calendar calendar2 = Calendar.getInstance();
        int i = 2;
        if (a2 == 0) {
            inflate.findViewById(R.id.dialog_plan_adjust_rest_divider).setVisibility(0);
            e((HealthImageView) inflate.findViewById(R.id.dialog_plan_adjust_rest_arrow), bc);
            aIG_(inflate.findViewById(R.id.dialog_plan_adjust_rest), responseCallback, 1, fyw.aIx_(aIw_, 1) > 0 ? calendar2 : null);
            if (fyw.aIx_(aIw_, 0) > 0) {
                r4 = calendar2;
            }
        } else {
            if (a2 != 1) {
                if (a2 == 2) {
                    HealthTextView healthTextView = (HealthTextView) inflate.findViewById(R.id.dialog_plan_adjust_leave_date);
                    healthTextView.setVisibility(0);
                    calendar = b(intPlan);
                    healthTextView.setText(nsf.b(R.string._2130848814_res_0x7f022c2e, UnitUtil.c(calendar, 24)));
                    i = 3;
                    e((HealthImageView) inflate.findViewById(R.id.dialog_plan_adjust_leave_arrow), bc);
                    aIG_(inflate.findViewById(R.id.dialog_plan_adjust_leave), responseCallback, i, calendar);
                    e((HealthImageView) inflate.findViewById(R.id.dialog_plan_week_adjust_arrow), bc);
                    aIG_(inflate.findViewById(R.id.dialog_plan_week_adjust), responseCallback, 4, calendar);
                    CustomAlertDialog.Builder builder = new CustomAlertDialog.Builder(context);
                    builder.e(R.string._2130848807_res_0x7f022c27);
                    builder.cyp_(inflate).cyn_(R.string._2130841130_res_0x7f020e2a, new DialogInterface.OnClickListener() { // from class: fzj
                        @Override // android.content.DialogInterface.OnClickListener
                        public final void onClick(DialogInterface dialogInterface, int i2) {
                            fze.aIH_(dialogInterface, i2);
                        }
                    });
                    return builder.c();
                }
                ReleaseLogUtil.a("R_PlanAdjustUtil", "getAdjustDialog dayStatus ", Integer.valueOf(a2));
                return null;
            }
            inflate.findViewById(R.id.dialog_plan_adjust_train_divider).setVisibility(0);
            e((HealthImageView) inflate.findViewById(R.id.dialog_plan_adjust_train_arrow), bc);
            aIG_(inflate.findViewById(R.id.dialog_plan_adjust_train), responseCallback, 0, calendar2);
            r4 = fyw.aIx_(aIw_, 0) > 0 ? calendar2 : null;
            ((HealthImageView) inflate.findViewById(R.id.dialog_plan_adjust_train_icon)).setImageDrawable(nrz.cKl_(e, R.drawable._2131430643_res_0x7f0b0cf3));
        }
        calendar = r4;
        e((HealthImageView) inflate.findViewById(R.id.dialog_plan_adjust_leave_arrow), bc);
        aIG_(inflate.findViewById(R.id.dialog_plan_adjust_leave), responseCallback, i, calendar);
        e((HealthImageView) inflate.findViewById(R.id.dialog_plan_week_adjust_arrow), bc);
        aIG_(inflate.findViewById(R.id.dialog_plan_week_adjust), responseCallback, 4, calendar);
        CustomAlertDialog.Builder builder2 = new CustomAlertDialog.Builder(context);
        builder2.e(R.string._2130848807_res_0x7f022c27);
        builder2.cyp_(inflate).cyn_(R.string._2130841130_res_0x7f020e2a, new DialogInterface.OnClickListener() { // from class: fzj
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i2) {
                fze.aIH_(dialogInterface, i2);
            }
        });
        return builder2.c();
    }

    static /* synthetic */ void aIH_(DialogInterface dialogInterface, int i) {
        LogUtil.c("R_PlanAdjustUtil", "getAdjustDialog dialog ", dialogInterface, " which ", Integer.valueOf(i));
        ViewClickInstrumentation.clickOnDialog(dialogInterface, i);
    }

    private static void e(HealthImageView healthImageView, boolean z) {
        Drawable drawable;
        Context e = BaseApplication.e();
        if (z) {
            drawable = nrz.cKn_(e, R.drawable._2131430240_res_0x7f0b0b60);
        } else {
            drawable = ContextCompat.getDrawable(e, R.drawable._2131430240_res_0x7f0b0b60);
        }
        healthImageView.setImageDrawable(drawable);
    }

    private static void aIG_(View view, final ResponseCallback<Object> responseCallback, final int i, final Object obj) {
        view.setVisibility(0);
        view.setOnClickListener(new View.OnClickListener() { // from class: fzk
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                fze.aII_(ResponseCallback.this, i, obj, view2);
            }
        });
    }

    static /* synthetic */ void aII_(ResponseCallback responseCallback, int i, Object obj, View view) {
        if (responseCallback == null) {
            ViewClickInstrumentation.clickOnView(view);
        } else {
            responseCallback.onResponse(i, obj);
            ViewClickInstrumentation.clickOnView(view);
        }
    }

    private static String d(int i) {
        return e(i, false);
    }

    private static String e(int i, boolean z) {
        double d = i;
        return nsf.a(z ? R.plurals._2130903491_res_0x7f0301c3 : R.plurals._2130903490_res_0x7f0301c2, UnitUtil.e(d), UnitUtil.e(d, 1, 0));
    }

    private static void a(HealthTextView healthTextView, HealthTextView healthTextView2, int i) {
        if (i == 1 || i == 2) {
            String b = nsf.b(R.string._2130848818_res_0x7f022c32, nsf.h(R.string._2130845663_res_0x7f021fdf));
            SpannableString spannableString = new SpannableString(nsf.b(R.string._2130848817_res_0x7f022c31, b));
            int indexOf = spannableString.toString().indexOf(b);
            if (indexOf >= 0) {
                int length = b.length() + indexOf;
                spannableString.setSpan(new TextAppearanceSpan(BaseApplication.e(), R.style.list_text_title_second), indexOf, length, 17);
                spannableString.setSpan(new nmj(nsk.cKO_()), indexOf, length, 17);
                spannableString.setSpan(new AbsoluteSizeSpan(14, true), indexOf, length, 17);
            }
            healthTextView.setText(spannableString);
            healthTextView2.setVisibility(8);
            return;
        }
        healthTextView.setText(nsf.h(R.string._2130848835_res_0x7f022c43));
        healthTextView2.setVisibility(0);
        healthTextView2.setText(nsf.b(R.string._2130848821_res_0x7f022c35, d(5)));
    }

    private static void b(HealthTextView healthTextView, int i, String str, int i2, String str2) {
        if (i == 1) {
            healthTextView.setText(nsf.b(R.string._2130848841_res_0x7f022c49, str));
        } else if (i == 2) {
            healthTextView.setText(nsf.b(R.string._2130848844_res_0x7f022c4c, str));
        } else {
            healthTextView.setText(nsf.b(R.string._2130848819_res_0x7f022c33, d(i2), str2));
        }
    }

    private static void c(HealthTextView healthTextView, HealthTextView healthTextView2, int i, int i2, String str) {
        if (i == 1 || i == 2) {
            healthTextView.setText(nsf.h(R.string._2130848842_res_0x7f022c4a));
            healthTextView2.setText(nsf.b(R.string._2130848843_res_0x7f022c4b, e(i2, true), str));
            healthTextView.setVisibility(0);
            healthTextView2.setVisibility(0);
            return;
        }
        healthTextView.setVisibility(8);
        healthTextView2.setVisibility(8);
    }

    private static void b(HealthTextView healthTextView, int i) {
        healthTextView.setText(nsf.a(R.plurals._2130903490_res_0x7f0301c2, UnitUtil.e(i), ""));
    }

    private static SparseArray<Calendar> aIE_(IntPlan intPlan) {
        IntDayPlan intDayPlan;
        if (intPlan == null) {
            return new SparseArray<>();
        }
        SparseArray<IntDayPlan> aIw_ = fyw.aIw_(intPlan);
        int size = aIw_.size();
        int b = DateFormatUtil.b(System.currentTimeMillis());
        ArrayList arrayList = new ArrayList();
        int i = 0;
        for (int i2 = 0; i2 < size; i2++) {
            int keyAt = aIw_.keyAt(i2);
            if (keyAt >= b && (intDayPlan = aIw_.get(keyAt)) != null && intDayPlan.getInPlanActionCnt() > 0) {
                Calendar calendar = Calendar.getInstance();
                calendar.setTimeInMillis(DateFormatUtil.c(keyAt));
                arrayList.add(calendar);
            }
        }
        int min = Math.min(arrayList.size(), 5);
        SparseArray<Calendar> sparseArray = new SparseArray<>();
        while (i < min) {
            int i3 = i + 1;
            sparseArray.append(i3, (Calendar) arrayList.get(i));
            i = i3;
        }
        return sparseArray;
    }

    private static SparseArray<String> aIF_(SparseArray<Calendar> sparseArray) {
        if (sparseArray == null) {
            return new SparseArray<>();
        }
        int size = sparseArray.size();
        SparseArray<String> sparseArray2 = new SparseArray<>();
        for (int i = 0; i < size; i++) {
            sparseArray2.append(sparseArray.keyAt(i), UnitUtil.c(sparseArray.valueAt(i), 24));
        }
        return sparseArray2;
    }

    private static String[] aIC_(SparseArray<String> sparseArray) {
        int size = sparseArray.size();
        String[] strArr = new String[size];
        for (int i = 0; i < size; i++) {
            strArr[i] = UnitUtil.e(sparseArray.keyAt(i), 1, 0);
        }
        return strArr;
    }

    private static int aID_(SparseArray<Calendar> sparseArray, int i, int i2) {
        Calendar valueAt;
        int size = sparseArray.size();
        if (i != 1 && i != 2) {
            return 0;
        }
        for (int i3 = 0; i3 < size; i3++) {
            if (i3 == size - 1 || ((valueAt = sparseArray.valueAt(i3)) != null && DateFormatUtil.b(valueAt.getTimeInMillis()) >= i2)) {
                return i3;
            }
        }
        return 0;
    }

    public static void a(Context context, IntPlan intPlan, final int i, int i2, final ResponseCallback<Integer> responseCallback) {
        View inflate = View.inflate(BaseApplication.e(), R.layout.dialog_plan_adjust_leave, null);
        a((HealthTextView) inflate.findViewById(R.id.dialog_plan_adjustment_leave_title), (HealthTextView) inflate.findViewById(R.id.dialog_plan_adjust_leave_tip), i);
        double e = jdl.e(System.currentTimeMillis(), DateFormatUtil.c(i2));
        final String a2 = nsf.a(R.plurals._2130903331_res_0x7f030123, UnitUtil.e(e), UnitUtil.e(e, 1, 0));
        SparseArray<Calendar> aIE_ = aIE_(intPlan);
        final SparseArray<String> aIF_ = aIF_(aIE_);
        int aID_ = aID_(aIE_, i, i2);
        int keyAt = aIF_.keyAt(aID_);
        final HealthTextView healthTextView = (HealthTextView) inflate.findViewById(R.id.dialog_plan_adjust_leave_subtitle_top);
        final HealthTextView healthTextView2 = (HealthTextView) inflate.findViewById(R.id.dialog_plan_adjust_leave_subtitle_middle);
        final HealthTextView healthTextView3 = (HealthTextView) inflate.findViewById(R.id.dialog_plan_adjust_leave_subtitle_bottom);
        b(healthTextView, i, a2, keyAt, aIF_.valueAt(aID_));
        c(healthTextView2, healthTextView3, i, keyAt, aIF_.valueAt(aID_));
        final HealthTextView healthTextView4 = (HealthTextView) inflate.findViewById(R.id.dialog_plan_adjust_leave_unit);
        b(healthTextView4, keyAt);
        final HealthNumberPicker healthNumberPicker = (HealthNumberPicker) inflate.findViewById(R.id.dialog_plan_adjust_leave_number_picker);
        healthNumberPicker.setDisplayedValues(aIC_(aIF_));
        healthNumberPicker.setMaxValue(aIF_.size() - 1);
        healthNumberPicker.setMinValue(0);
        healthNumberPicker.setValue(aID_);
        healthNumberPicker.setOnValuePickedListener(new HealthNumberPicker.OnPickedListener() { // from class: fzh
            @Override // com.huawei.ui.commonui.numberpicker.HealthNumberPicker.OnPickedListener
            public final void onValuePicked(int i3, int i4) {
                fze.aIL_(aIF_, healthTextView, i, a2, healthTextView2, healthTextView3, healthTextView4, i3, i4);
            }
        });
        CustomAlertDialog.Builder builder = new CustomAlertDialog.Builder(context);
        builder.cyp_(inflate).cyo_(R.string._2130848401_res_0x7f022a91, new DialogInterface.OnClickListener() { // from class: fzp
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i3) {
                fze.aIM_(ResponseCallback.this, aIF_, healthNumberPicker, dialogInterface, i3);
            }
        }).cyn_(R.string._2130841130_res_0x7f020e2a, new DialogInterface.OnClickListener() { // from class: fzo
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i3) {
                fze.aIN_(dialogInterface, i3);
            }
        });
        c(builder);
    }

    static /* synthetic */ void aIL_(SparseArray sparseArray, HealthTextView healthTextView, int i, String str, HealthTextView healthTextView2, HealthTextView healthTextView3, HealthTextView healthTextView4, int i2, int i3) {
        int keyAt = sparseArray.keyAt(i3);
        b(healthTextView, i, str, keyAt, (String) sparseArray.valueAt(i3));
        c(healthTextView2, healthTextView3, i, keyAt, (String) sparseArray.valueAt(i3));
        b(healthTextView4, keyAt);
    }

    static /* synthetic */ void aIM_(ResponseCallback responseCallback, SparseArray sparseArray, HealthNumberPicker healthNumberPicker, DialogInterface dialogInterface, int i) {
        if (responseCallback == null) {
            ViewClickInstrumentation.clickOnDialog(dialogInterface, i);
        } else {
            responseCallback.onResponse(0, Integer.valueOf(sparseArray.keyAt(healthNumberPicker.getValue())));
            ViewClickInstrumentation.clickOnDialog(dialogInterface, i);
        }
    }

    static /* synthetic */ void aIN_(DialogInterface dialogInterface, int i) {
        LogUtil.c("R_PlanAdjustUtil", "showLeaveDialog dialog ", dialogInterface, " which ", Integer.valueOf(i));
        ViewClickInstrumentation.clickOnDialog(dialogInterface, i);
    }

    public static void b(Context context, final Object obj, final ResponseCallback<Object> responseCallback) {
        CustomAlertDialog.Builder builder = new CustomAlertDialog.Builder(context);
        builder.e(R.string._2130848815_res_0x7f022c2f);
        if (obj instanceof Calendar) {
            builder.c(nsf.b(R.string._2130848816_res_0x7f022c30, UnitUtil.c((Calendar) obj, 20)));
        }
        builder.cyo_(R.string._2130848401_res_0x7f022a91, new DialogInterface.OnClickListener() { // from class: fzl
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                fze.aIJ_(ResponseCallback.this, obj, dialogInterface, i);
            }
        }).cyn_(R.string._2130848812_res_0x7f022c2c, new DialogInterface.OnClickListener() { // from class: fzi
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                fze.aIK_(dialogInterface, i);
            }
        });
        c(builder);
    }

    static /* synthetic */ void aIJ_(ResponseCallback responseCallback, Object obj, DialogInterface dialogInterface, int i) {
        if (responseCallback == null) {
            ViewClickInstrumentation.clickOnDialog(dialogInterface, i);
        } else {
            responseCallback.onResponse(0, obj);
            ViewClickInstrumentation.clickOnDialog(dialogInterface, i);
        }
    }

    static /* synthetic */ void aIK_(DialogInterface dialogInterface, int i) {
        LogUtil.c("R_PlanAdjustUtil", "showCancelLeaveDialog dialog ", dialogInterface, " which ", Integer.valueOf(i));
        ViewClickInstrumentation.clickOnDialog(dialogInterface, i);
    }

    private static void c(CustomAlertDialog.Builder builder) {
        CustomAlertDialog c = builder.c();
        if (c == null || c.isShowing()) {
            return;
        }
        c.show();
    }
}
