package defpackage;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import com.huawei.health.R;
import com.huawei.health.servicesui.R$string;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hms.support.api.entity.common.CommonConstant;
import com.huawei.hms.utils.HMSPackageManager;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.huaweilogin.HuaweiLoginManager;
import com.huawei.ui.commonui.datepicker.HealthDatePickerDialog;
import com.huawei.ui.commonui.dialog.CustomAlertDialog;
import com.huawei.ui.commonui.dialog.CustomViewDialog;
import com.huawei.ui.commonui.dialog.NoTitleCustomAlertDialog;
import com.huawei.ui.commonui.divider.HealthDivider;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.numberpicker.HealthNumberPicker;
import com.huawei.ui.commonui.radiobutton.HealthRadioButton;
import com.huawei.up.model.UserInfomation;
import health.compact.a.CommonUtil;
import health.compact.a.UnitUtil;
import health.compact.a.Utils;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;

/* loaded from: classes4.dex */
public class gmc {
    private static int e(int i) {
        if (i == 0) {
            return 1;
        }
        if (1 == i) {
            return 0;
        }
        return i;
    }

    public static void aPd_(Activity activity, UserInfomation userInfomation, IBaseResponseCallback iBaseResponseCallback) {
        aPe_(activity, userInfomation, iBaseResponseCallback, false);
    }

    public static void aPe_(Activity activity, final UserInfomation userInfomation, final IBaseResponseCallback iBaseResponseCallback, boolean z) {
        if (activity == null) {
            LogUtil.h("UIME_UserInfoActivityUtil", "activity param null");
            return;
        }
        View inflate = activity.getLayoutInflater().inflate(R.layout.health_healthdata_userinfo_dialog_set, (ViewGroup) null);
        HealthTextView healthTextView = (HealthTextView) inflate.findViewById(R.id.hw_health_dialog_tips_userinfo);
        final HealthNumberPicker healthNumberPicker = (HealthNumberPicker) inflate.findViewById(R.id.userinfo_number_picker_v9);
        healthTextView.setText(activity.getString(R$string.IDS_hwh_user_data_weight_tips));
        ArrayList<String> c = c(z);
        healthNumberPicker.setDisplayedValues((String[]) c.toArray(new String[c.size()]));
        healthNumberPicker.setMinValue(0);
        healthNumberPicker.setMaxValue(c.size() - 1);
        if (z) {
            e(userInfomation, healthNumberPicker);
        } else {
            d(userInfomation, healthNumberPicker, UnitUtil.h());
        }
        CustomAlertDialog.Builder builder = new CustomAlertDialog.Builder(activity);
        builder.a(activity.getString(R$string.IDS_hw_health_show_healthdata_weight)).cyp_(inflate).cyo_(R$string.IDS_hw_common_ui_dialog_confirm, new DialogInterface.OnClickListener() { // from class: gmj
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                gmc.aOX_(IBaseResponseCallback.this, userInfomation, healthNumberPicker, dialogInterface, i);
            }
        }).cyn_(R$string.IDS_hw_show_cancel, new DialogInterface.OnClickListener() { // from class: gml
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                ViewClickInstrumentation.clickOnDialog(dialogInterface, i);
            }
        });
        builder.c().show();
    }

    static /* synthetic */ void aOX_(IBaseResponseCallback iBaseResponseCallback, UserInfomation userInfomation, HealthNumberPicker healthNumberPicker, DialogInterface dialogInterface, int i) {
        if (iBaseResponseCallback == null) {
            LogUtil.h("UIME_UserInfoActivityUtil", "callback is null");
            ViewClickInstrumentation.clickOnDialog(dialogInterface, i);
        } else if (a()) {
            iBaseResponseCallback.d(-1, null);
            ViewClickInstrumentation.clickOnDialog(dialogInterface, i);
        } else {
            userInfomation.setWeight(Float.valueOf(healthNumberPicker.getValue() / 10.0f).floatValue());
            iBaseResponseCallback.d(1, userInfomation);
            ViewClickInstrumentation.clickOnDialog(dialogInterface, i);
        }
    }

    private static void e(UserInfomation userInfomation, HealthNumberPicker healthNumberPicker) {
        int a2 = UnitUtil.a();
        if (a2 != 1) {
            d(userInfomation, healthNumberPicker, a2 == 3);
        } else if (userInfomation != null && Math.round(UnitUtil.b(userInfomation.getWeight())) >= UnitUtil.b(10.0d)) {
            LogUtil.c("UIME_UserInfoActivityUtil", "CATTY userInfomation.getWeight()=", Float.valueOf(userInfomation.getWeight()));
            healthNumberPicker.setValue((int) (Math.round(UnitUtil.b(userInfomation.getWeight())) - UnitUtil.b(10.0d)));
        } else {
            healthNumberPicker.setValue((int) UnitUtil.b(70.0d));
        }
    }

    private static void d(UserInfomation userInfomation, HealthNumberPicker healthNumberPicker, boolean z) {
        if (z) {
            if (userInfomation != null && ((int) Math.round(UnitUtil.h(userInfomation.getWeight()))) >= 22) {
                LogUtil.c("UIME_UserInfoActivityUtil", "LB userInfomation.getWeight()=", Float.valueOf(userInfomation.getWeight()));
                healthNumberPicker.setValue(((int) Math.round(UnitUtil.h(userInfomation.getWeight() * 10.0f))) - 220);
                return;
            } else {
                healthNumberPicker.setValue((int) UnitUtil.h(700.0d));
                return;
            }
        }
        if (userInfomation != null && Math.round(userInfomation.getWeight()) >= 10) {
            LogUtil.c("UIME_UserInfoActivityUtil", "userInfomation.getWeight()=", Float.valueOf(userInfomation.getWeight()));
            healthNumberPicker.setValue(Math.round(userInfomation.getWeight() * 10.0f) - 100);
        } else {
            healthNumberPicker.setValue(700);
        }
    }

    public static void aPb_(Activity activity, final UserInfomation userInfomation, final IBaseResponseCallback iBaseResponseCallback) {
        if (activity == null) {
            LogUtil.h("UIME_UserInfoActivityUtil", "showHeightDialog activity param is null");
            return;
        }
        if (UnitUtil.h()) {
            aPc_(activity, userInfomation, iBaseResponseCallback);
            return;
        }
        View inflate = activity.getLayoutInflater().inflate(R.layout.health_healthdata_userinfo_dialog_set, (ViewGroup) null);
        HealthTextView healthTextView = (HealthTextView) inflate.findViewById(R.id.hw_health_dialog_tips_userinfo);
        final HealthNumberPicker healthNumberPicker = (HealthNumberPicker) inflate.findViewById(R.id.userinfo_number_picker_v9);
        healthTextView.setText(activity.getString(R$string.IDS_hwh_user_data_height_tips));
        ArrayList<String> d = d();
        healthNumberPicker.setDisplayedValues((String[]) d.toArray(new String[d.size()]));
        healthNumberPicker.setMinValue(0);
        healthNumberPicker.setMaxValue(d.size() - 1);
        if (userInfomation != null && userInfomation.getHeight() >= 50) {
            LogUtil.c("UIME_UserInfoActivityUtil", "userInfomation.getHeight()=", Integer.valueOf(userInfomation.getHeight()));
            healthNumberPicker.setValue(userInfomation.getHeight() - 50);
        } else {
            healthNumberPicker.setValue(110);
        }
        CustomAlertDialog.Builder builder = new CustomAlertDialog.Builder(activity);
        builder.a(activity.getString(R$string.IDS_hw_show_set_height)).cyp_(inflate).cyo_(R$string.IDS_hw_common_ui_dialog_confirm, new DialogInterface.OnClickListener() { // from class: gmb
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                gmc.aOT_(IBaseResponseCallback.this, userInfomation, healthNumberPicker, dialogInterface, i);
            }
        }).cyn_(R$string.IDS_hw_show_cancel, new DialogInterface.OnClickListener() { // from class: gmd
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                ViewClickInstrumentation.clickOnDialog(dialogInterface, i);
            }
        });
        builder.c().show();
    }

    static /* synthetic */ void aOT_(IBaseResponseCallback iBaseResponseCallback, UserInfomation userInfomation, HealthNumberPicker healthNumberPicker, DialogInterface dialogInterface, int i) {
        if (iBaseResponseCallback == null) {
            LogUtil.h("UIME_UserInfoActivityUtil", "callback is null");
            ViewClickInstrumentation.clickOnDialog(dialogInterface, i);
        } else if (a()) {
            iBaseResponseCallback.d(-1, null);
            ViewClickInstrumentation.clickOnDialog(dialogInterface, i);
        } else {
            userInfomation.setHeight(Integer.valueOf(healthNumberPicker.getValue()).intValue());
            iBaseResponseCallback.d(0, userInfomation);
            ViewClickInstrumentation.clickOnDialog(dialogInterface, i);
        }
    }

    private static void aPc_(Activity activity, final UserInfomation userInfomation, final IBaseResponseCallback iBaseResponseCallback) {
        View inflate = activity.getLayoutInflater().inflate(R.layout.health_healthdata_userinfo_dialog_inch_height_v9, (ViewGroup) null);
        final HealthNumberPicker healthNumberPicker = (HealthNumberPicker) inflate.findViewById(R.id.height_ft_number_picker_v9);
        String[] strArr = new String[8];
        int i = 0;
        while (i < 8) {
            StringBuilder sb = new StringBuilder();
            int i2 = i + 1;
            sb.append(UnitUtil.e(i2, 1, 0));
            sb.append(" ");
            sb.append(activity.getResources().getString(R$string.IDS_ft));
            strArr[i] = sb.toString();
            i = i2;
        }
        healthNumberPicker.setDisplayedValues(strArr);
        healthNumberPicker.setMinValue(0);
        healthNumberPicker.setMaxValue(7);
        final HealthNumberPicker healthNumberPicker2 = (HealthNumberPicker) inflate.findViewById(R.id.height_inch_number_picker_v9);
        String[] strArr2 = new String[12];
        for (int i3 = 0; i3 < 12; i3++) {
            strArr2[i3] = UnitUtil.e(i3, 1, 0) + " " + activity.getResources().getString(R$string.IDS_ins);
        }
        healthNumberPicker2.setDisplayedValues(strArr2);
        healthNumberPicker2.setMinValue(0);
        healthNumberPicker2.setMaxValue(11);
        int[] iArr = {5, 7};
        if (userInfomation != null && userInfomation.getHeight() > 30) {
            int[] j = UnitUtil.j(new BigDecimal(userInfomation.getHeight()).divide(new BigDecimal(100), 2, 4).doubleValue());
            if (j[0] > 0 && j[1] >= 0) {
                iArr = j;
            }
        }
        healthNumberPicker.setValue(iArr[0] - 1);
        healthNumberPicker2.setValue(iArr[1]);
        CustomViewDialog.Builder builder = new CustomViewDialog.Builder(activity);
        builder.a(activity.getString(R$string.IDS_hw_show_set_height)).czg_(inflate).cze_(R$string.IDS_hw_common_ui_dialog_confirm, new View.OnClickListener() { // from class: gma
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                gmc.aOV_(UserInfomation.this, iBaseResponseCallback, healthNumberPicker, healthNumberPicker2, view);
            }
        }).czc_(R$string.IDS_hw_show_cancel, new View.OnClickListener() { // from class: gmh
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        builder.e().show();
    }

    static /* synthetic */ void aOV_(UserInfomation userInfomation, IBaseResponseCallback iBaseResponseCallback, HealthNumberPicker healthNumberPicker, HealthNumberPicker healthNumberPicker2, View view) {
        d(userInfomation, iBaseResponseCallback, healthNumberPicker, healthNumberPicker2);
        ViewClickInstrumentation.clickOnView(view);
    }

    private static void d(UserInfomation userInfomation, IBaseResponseCallback iBaseResponseCallback, HealthNumberPicker healthNumberPicker, HealthNumberPicker healthNumberPicker2) {
        if (iBaseResponseCallback == null) {
            LogUtil.h("UIME_UserInfoActivityUtil", "handlePositiveEvent callback is null");
            return;
        }
        if (a()) {
            iBaseResponseCallback.d(-1, null);
            return;
        }
        userInfomation.setHeight((int) Math.rint((UnitUtil.d(healthNumberPicker.getValue() + 1, 1) * 100.0d) + UnitUtil.d(healthNumberPicker2.getValue(), 0)));
        iBaseResponseCallback.d(0, userInfomation);
    }

    public static boolean a() {
        if (CommonUtil.bu() || !Utils.i()) {
            return false;
        }
        return !CommonUtil.aa(BaseApplication.getContext());
    }

    private static ArrayList<String> c(boolean z) {
        Context context = BaseApplication.getContext();
        ArrayList<String> arrayList = new ArrayList<>();
        if (z) {
            b(context, arrayList);
        } else {
            c(context, arrayList, UnitUtil.h());
        }
        return arrayList;
    }

    private static void b(Context context, ArrayList<String> arrayList) {
        int a2 = UnitUtil.a();
        if (a2 == 1) {
            int b = (int) UnitUtil.b(10.0d);
            while (true) {
                double d = b;
                if (d > UnitUtil.b(250.0d)) {
                    return;
                }
                arrayList.add(context.getResources().getQuantityString(R.plurals._2130903105_res_0x7f030041, UnitUtil.e(d), UnitUtil.e(d, 1, 0)));
                b++;
            }
        } else {
            c(context, arrayList, a2 == 3);
        }
    }

    private static void c(Context context, ArrayList<String> arrayList, boolean z) {
        if (z) {
            for (double d = 22.0d; d <= 552.0d; d += 0.1d) {
                arrayList.add(context.getResources().getString(R$string.IDS_hw_show_set_weightvalue_with_unit_lb, UnitUtil.e(d, 1, 1)));
            }
            return;
        }
        for (double d2 = 10.0d; d2 <= 250.0d; d2 += 0.1d) {
            arrayList.add(context.getResources().getString(R$string.IDS_hw_show_set_weightvalue_with_unit_kg, UnitUtil.e(d2, 1, 1)));
        }
    }

    private static ArrayList<String> d() {
        Context context = BaseApplication.getContext();
        ArrayList<String> arrayList = new ArrayList<>();
        for (int i = 50; i <= 250; i++) {
            arrayList.add(context.getResources().getString(R$string.IDS_hw_show_set_height_value_with_unit_cm, UnitUtil.e(i, 1, 0)));
        }
        return arrayList;
    }

    public static void aOZ_(Activity activity, int i, int i2, int i3, final IBaseResponseCallback iBaseResponseCallback) {
        if (activity == null) {
            LogUtil.h("UIME_UserInfoActivityUtil", "activity param null");
            return;
        }
        if (i <= 0) {
            i = 1992;
            i2 = 0;
            i3 = 1;
        }
        HealthDatePickerDialog healthDatePickerDialog = new HealthDatePickerDialog(activity, new HealthDatePickerDialog.DateSelectedListener() { // from class: gmm
            @Override // com.huawei.ui.commonui.datepicker.HealthDatePickerDialog.DateSelectedListener
            public final void onDateSelected(int i4, int i5, int i6) {
                gmc.b(IBaseResponseCallback.this, i4, i5, i6);
            }
        }, new GregorianCalendar(i, i2, i3));
        int i4 = Calendar.getInstance().get(1);
        healthDatePickerDialog.e(i4 - 150, i4);
        healthDatePickerDialog.show();
    }

    static /* synthetic */ void b(IBaseResponseCallback iBaseResponseCallback, int i, int i2, int i3) {
        if (iBaseResponseCallback == null) {
            LogUtil.h("UIME_UserInfoActivityUtil", "callback is null");
            return;
        }
        if (a()) {
            iBaseResponseCallback.d(-1, null);
            return;
        }
        String str = i + String.format(Locale.ENGLISH, "%02d", Integer.valueOf(i2 + 1)) + String.format(Locale.ENGLISH, "%02d", Integer.valueOf(i3));
        UserInfomation userInfomation = new UserInfomation(0);
        userInfomation.setBirthday(str);
        iBaseResponseCallback.d(3, userInfomation);
    }

    public static void aPa_(Activity activity, int i, UserInfomation userInfomation, IBaseResponseCallback iBaseResponseCallback) {
        if (activity == null) {
            LogUtil.h("UIME_UserInfoActivityUtil", "activity param null");
            return;
        }
        if (activity.getSystemService("layout_inflater") instanceof LayoutInflater) {
            View inflate = ((LayoutInflater) activity.getSystemService("layout_inflater")).inflate(R.layout.hw_show_settings_gender_view, (ViewGroup) null);
            CustomViewDialog.Builder builder = new CustomViewDialog.Builder(activity);
            builder.a(activity.getString(R$string.IDS_hw_show_set_gender)).czg_(inflate).czc_(R$string.IDS_hw_show_cancel, new View.OnClickListener() { // from class: gmi
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    ViewClickInstrumentation.clickOnView(view);
                }
            });
            CustomViewDialog e = builder.e();
            if (iBaseResponseCallback == null) {
                LogUtil.h("UIME_UserInfoActivityUtil", "callback is null");
            } else if (a()) {
                iBaseResponseCallback.d(-1, null);
            } else {
                aOO_(inflate, i, e, userInfomation, iBaseResponseCallback);
                e.show();
            }
        }
    }

    private static void aOO_(View view, final int i, final CustomViewDialog customViewDialog, final UserInfomation userInfomation, final IBaseResponseCallback iBaseResponseCallback) {
        LogUtil.a("UIME_UserInfoActivityUtil", "initializeGenderDialogLayout()");
        final HealthRadioButton healthRadioButton = (HealthRadioButton) view.findViewById(R.id.settings_gender_imgview1);
        final HealthRadioButton healthRadioButton2 = (HealthRadioButton) view.findViewById(R.id.settings_gender_imgview2);
        final HealthRadioButton healthRadioButton3 = (HealthRadioButton) view.findViewById(R.id.settings_gender_secrect);
        RelativeLayout relativeLayout = (RelativeLayout) view.findViewById(R.id.settings_gender_view_layout1);
        RelativeLayout relativeLayout2 = (RelativeLayout) view.findViewById(R.id.settings_gender_view_layout2);
        RelativeLayout relativeLayout3 = (RelativeLayout) view.findViewById(R.id.settings_gender_view_layout_secrect);
        HealthDivider healthDivider = (HealthDivider) view.findViewById(R.id.gender_picker_divide_line2);
        if (i == 19) {
            healthDivider.setVisibility(8);
            relativeLayout3.setVisibility(8);
        } else {
            healthDivider.setVisibility(0);
            relativeLayout3.setVisibility(0);
        }
        c(e(userInfomation.getGender()), healthRadioButton, healthRadioButton2, healthRadioButton3, i);
        relativeLayout.setOnClickListener(new View.OnClickListener() { // from class: gmf
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                gmc.aOQ_(HealthRadioButton.this, healthRadioButton2, healthRadioButton3, userInfomation, iBaseResponseCallback, i, customViewDialog, view2);
            }
        });
        relativeLayout2.setOnClickListener(new View.OnClickListener() { // from class: gmg
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                gmc.aOR_(HealthRadioButton.this, healthRadioButton2, healthRadioButton3, userInfomation, iBaseResponseCallback, i, customViewDialog, view2);
            }
        });
        relativeLayout3.setOnClickListener(new View.OnClickListener() { // from class: gme
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                gmc.aOP_(HealthRadioButton.this, healthRadioButton2, healthRadioButton3, userInfomation, iBaseResponseCallback, i, customViewDialog, view2);
            }
        });
    }

    static /* synthetic */ void aOQ_(HealthRadioButton healthRadioButton, HealthRadioButton healthRadioButton2, HealthRadioButton healthRadioButton3, UserInfomation userInfomation, IBaseResponseCallback iBaseResponseCallback, int i, CustomViewDialog customViewDialog, View view) {
        healthRadioButton.setChecked(true);
        healthRadioButton2.setChecked(false);
        healthRadioButton3.setChecked(false);
        userInfomation.setGender(1);
        iBaseResponseCallback.d(i, userInfomation);
        customViewDialog.dismiss();
        ViewClickInstrumentation.clickOnView(view);
    }

    static /* synthetic */ void aOR_(HealthRadioButton healthRadioButton, HealthRadioButton healthRadioButton2, HealthRadioButton healthRadioButton3, UserInfomation userInfomation, IBaseResponseCallback iBaseResponseCallback, int i, CustomViewDialog customViewDialog, View view) {
        healthRadioButton.setChecked(false);
        healthRadioButton2.setChecked(true);
        healthRadioButton3.setChecked(false);
        userInfomation.setGender(0);
        iBaseResponseCallback.d(i, userInfomation);
        customViewDialog.dismiss();
        ViewClickInstrumentation.clickOnView(view);
    }

    static /* synthetic */ void aOP_(HealthRadioButton healthRadioButton, HealthRadioButton healthRadioButton2, HealthRadioButton healthRadioButton3, UserInfomation userInfomation, IBaseResponseCallback iBaseResponseCallback, int i, CustomViewDialog customViewDialog, View view) {
        healthRadioButton.setChecked(false);
        healthRadioButton2.setChecked(false);
        healthRadioButton3.setChecked(true);
        userInfomation.setGender(2);
        iBaseResponseCallback.d(i, userInfomation);
        customViewDialog.dismiss();
        ViewClickInstrumentation.clickOnView(view);
    }

    private static void c(int i, HealthRadioButton healthRadioButton, HealthRadioButton healthRadioButton2, HealthRadioButton healthRadioButton3, int i2) {
        if (i == 0) {
            healthRadioButton.setChecked(false);
            healthRadioButton2.setChecked(true);
            healthRadioButton3.setChecked(false);
            return;
        }
        if (i == 1) {
            healthRadioButton.setChecked(true);
            healthRadioButton2.setChecked(false);
            healthRadioButton3.setChecked(false);
        } else if (i == 2) {
            healthRadioButton.setChecked(false);
            healthRadioButton2.setChecked(false);
            healthRadioButton3.setChecked(true);
        } else if (i2 == 19) {
            healthRadioButton.setChecked(false);
            healthRadioButton2.setChecked(false);
        } else {
            healthRadioButton.setChecked(true);
            healthRadioButton2.setChecked(false);
            healthRadioButton3.setChecked(false);
        }
    }

    public static String b(Context context, int i) {
        String string = context.getResources().getString(R$string.IDS_hw_me_userinfo_not_set);
        if (i <= 0) {
            LogUtil.h("UIME_UserInfoActivityUtil", "height invalid");
            return string;
        }
        if (UnitUtil.h()) {
            LogUtil.a("UIME_UserInfoActivityUtil", "enter UNIT_IMPERIAL");
            int[] iArr = {5, 7};
            if (i > 30) {
                iArr = UnitUtil.j(i / 100.0d);
            }
            return UnitUtil.e(iArr[0], 1, 0) + " " + context.getResources().getString(R$string.IDS_ft) + " " + UnitUtil.e(iArr[1], 1, 0) + " " + context.getResources().getString(R$string.IDS_ins);
        }
        LogUtil.a("UIME_UserInfoActivityUtil", "enter UNIT_CM_KG");
        return context.getResources().getString(R$string.IDS_hw_show_set_height_value_with_unit_cm, UnitUtil.e(i, 1, 0));
    }

    public static String d(Context context, float f) {
        String string = context.getResources().getString(R$string.IDS_hw_me_userinfo_not_set);
        if (f <= 0.0f) {
            return string;
        }
        if (UnitUtil.h()) {
            LogUtil.a("UIME_UserInfoActivityUtil", "enter UNIT_IMPERIAL");
            return context.getResources().getString(R$string.IDS_hw_show_set_weightvalue_with_unit_lb, UnitUtil.e(UnitUtil.h(f), 1, 1));
        }
        LogUtil.a("UIME_UserInfoActivityUtil", "enter UNIT_CM_KG");
        return context.getResources().getString(R$string.IDS_hw_show_set_weightvalue_with_unit_kg, UnitUtil.e(CommonUtil.a(String.valueOf(f)), 1, 1));
    }

    public static String c(Context context, String str) {
        String string = context.getResources().getString(R$string.IDS_hw_me_userinfo_not_set);
        try {
            int parseInt = Integer.parseInt(str);
            if (parseInt <= 0) {
                return string;
            }
            Calendar calendar = Calendar.getInstance();
            calendar.set(parseInt / 10000, ((parseInt % 10000) / 100) - 1, parseInt % 100);
            return UnitUtil.a(calendar.getTime(), 20);
        } catch (NumberFormatException unused) {
            LogUtil.h("UIME_UserInfoActivityUtil", "parse birthday error");
            return string;
        }
    }

    public static void a(Context context) {
        nrh.b(context, R$string.IDS_network_connect_error);
    }

    public static void aPf_(Activity activity) {
        if (activity == null) {
            LogUtil.h("UIME_UserInfoActivityUtil", "activity param null");
            return;
        }
        if (!HuaweiLoginManager.checkIsInstallHuaweiAccount(activity)) {
            LogUtil.h("UIME_UserInfoActivityUtil", "checkIsInstallHuaweiAccount is false");
            return;
        }
        try {
            Intent intent = new Intent(CommonConstant.ACTION.HWID_SCHEME_URL, Uri.parse("hwid://com.huawei.hwid/AccountDetail"));
            intent.setPackage(HMSPackageManager.getInstance(activity).getHMSPackageName());
            activity.startActivityForResult(intent, 0);
        } catch (ActivityNotFoundException unused) {
            LogUtil.b("UIME_UserInfoActivityUtil", "HMS Not Installed");
        } catch (IllegalArgumentException e) {
            LogUtil.b("UIME_UserInfoActivityUtil", "exception getClassName:" + e.getClass().getSimpleName());
        }
    }

    public static void d(final Context context) {
        NoTitleCustomAlertDialog e = new NoTitleCustomAlertDialog.Builder(context).e(R$string.IDS_emergency_dialog_message).czz_(R$string.IDS_settings_button_cancal_ios_btn, new View.OnClickListener() { // from class: gmc.5
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LogUtil.a("UIME_UserInfoActivityUtil", "showEmergencyChangeDialog cancel click");
                ViewClickInstrumentation.clickOnView(view);
            }
        }).czC_(R$string.IDS_hw_watchface_go_hms_install, new View.OnClickListener() { // from class: gmc.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LogUtil.a("UIME_UserInfoActivityUtil", "showEmergencyChangeDialog ok click");
                gmc.b(context);
                ViewClickInstrumentation.clickOnView(view);
            }
        }).e();
        e.setCancelable(false);
        e.show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void b(Context context) {
        LogUtil.a("UIME_UserInfoActivityUtil", "enterEditEmergencyContact enter");
        Intent intent = new Intent();
        try {
            intent.setClassName("com.android.emergency", "com.android.emergency.view.ViewInfoActivity");
            context.startActivity(intent);
        } catch (ActivityNotFoundException unused) {
            LogUtil.b("UIME_UserInfoActivityUtil", "enterEditEmergencyContact Emui110 activityNotFoundException SOS");
        }
    }
}
