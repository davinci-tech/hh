package defpackage;

import android.content.Context;
import android.graphics.Bitmap;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.dialog.CustomViewDialog;
import com.huawei.ui.commonui.dialog.NoTitleCustomAlertDialog;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.numberpicker.HealthNumberPicker;
import com.huawei.ui.main.R$string;
import defpackage.nmn;
import health.compact.a.LanguageUtil;
import health.compact.a.UnitUtil;
import health.compact.a.utils.StringUtils;
import java.util.ArrayList;
import java.util.Calendar;

/* loaded from: classes7.dex */
public class qpz {
    public static void dHr_(cfi cfiVar, HealthTextView healthTextView, ImageView imageView) {
        if (cfiVar == null || imageView == null) {
            LogUtil.a("BaseHealthViewsUtils", "setUserNameAndPhotoForList: user or headPhotoView is null");
            return;
        }
        if (healthTextView != null) {
            String h = cfiVar.h();
            if (LanguageUtil.bc(BaseApplication.getContext()) && !TextUtils.isEmpty(h) && (StringUtils.b(h) || StringUtils.d(h))) {
                healthTextView.setTextDirection(3);
            }
            healthTextView.setText(h);
        }
        imageView.setImageBitmap(null);
        imageView.setImageResource(0);
        if (cfiVar.n() == -1) {
            imageView.setImageResource(R.drawable._2131428455_res_0x7f0b0467);
            return;
        }
        if (!TextUtils.isEmpty(cfiVar.e())) {
            dHs_(cfiVar.e(), imageView, cfiVar);
        } else if (cfiVar.Ex_() == null) {
            imageView.setImageDrawable(nmn.cBh_(BaseApplication.getContext().getResources(), new nmn.c(null, cfiVar.i(), true)));
        } else {
            imageView.setImageBitmap(nmn.cBg_(cfiVar.Ex_(), true));
        }
    }

    private static void dHs_(String str, ImageView imageView, cfi cfiVar) {
        if (imageView == null || cfiVar == null) {
            LogUtil.b("BaseHealthViewsUtils", "setUserPhotoForList: headPhotoView or user is null");
            return;
        }
        if (!"default".equals(str)) {
            Bitmap cIe_ = nrf.cIe_(BaseApplication.getContext(), str);
            if (cIe_ != null) {
                imageView.setImageBitmap(nmn.cBg_(cIe_, true));
                return;
            }
            return;
        }
        imageView.setImageDrawable(nmn.cBh_(BaseApplication.getContext().getResources(), new nmn.c(null, cfiVar.i(), true)));
    }

    public static void b(Context context, int i, final IBaseResponseCallback iBaseResponseCallback) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.health_healthdata_weight_userinfo_dialog_set, (ViewGroup) null);
        final HealthNumberPicker healthNumberPicker = (HealthNumberPicker) inflate.findViewById(R.id.weight_number_picker_v9);
        ArrayList<String> c = c(context);
        healthNumberPicker.setDisplayedValues((String[]) c.toArray(new String[c.size()]));
        healthNumberPicker.setMinValue(0);
        healthNumberPicker.setMaxValue(c.size() - 1);
        if (i >= 50) {
            int i2 = i - 50;
            LogUtil.c("BaseHealthViewsUtils", "mHiUserInfo.getHeight() = ", Integer.valueOf(i2));
            healthNumberPicker.setValue(i2);
        } else {
            healthNumberPicker.setValue(110);
        }
        CustomViewDialog.Builder builder = new CustomViewDialog.Builder(context);
        builder.d(R$string.IDS_hw_show_set_height).czg_(inflate).cze_(R$string.IDS_hw_common_ui_dialog_confirm, new View.OnClickListener() { // from class: qpz.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LogUtil.a("BaseHealthViewsUtils", "get hselect =", Integer.valueOf(HealthNumberPicker.this.getValue()));
                IBaseResponseCallback iBaseResponseCallback2 = iBaseResponseCallback;
                if (iBaseResponseCallback2 == null) {
                    ViewClickInstrumentation.clickOnView(view);
                } else {
                    iBaseResponseCallback2.d(0, Integer.valueOf(HealthNumberPicker.this.getValue()));
                    ViewClickInstrumentation.clickOnView(view);
                }
            }
        }).czc_(R$string.IDS_hw_show_cancel, new View.OnClickListener() { // from class: qpz.5
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        builder.e().show();
    }

    public static void c(Context context, cfi cfiVar, int i, IBaseResponseCallback iBaseResponseCallback) {
        if (context == null) {
            LogUtil.h("showHeightInchDialog() context is null", new Object[0]);
            return;
        }
        View inflate = ((LayoutInflater) context.getSystemService("layout_inflater")).inflate(R.layout.health_healthdata_userinfo_dialog_inch_height_v9, (ViewGroup) null);
        HealthNumberPicker healthNumberPicker = (HealthNumberPicker) inflate.findViewById(R.id.height_ft_number_picker_v9);
        ((HealthTextView) inflate.findViewById(R.id.hw_health_dialog_tips)).setVisibility(8);
        String[] strArr = new String[8];
        int i2 = 0;
        while (i2 < 8) {
            StringBuilder sb = new StringBuilder();
            int i3 = i2 + 1;
            sb.append(UnitUtil.e(i3, 1, 0));
            sb.append(" ");
            sb.append(context.getString(R$string.IDS_ft));
            strArr[i2] = sb.toString();
            i2 = i3;
        }
        healthNumberPicker.setDisplayedValues(strArr);
        healthNumberPicker.setMinValue(0);
        healthNumberPicker.setMaxValue(7);
        HealthNumberPicker healthNumberPicker2 = (HealthNumberPicker) inflate.findViewById(R.id.height_inch_number_picker_v9);
        String[] strArr2 = new String[12];
        for (int i4 = 0; i4 < 12; i4++) {
            strArr2[i4] = UnitUtil.e(i4, 1, 0) + " " + context.getString(R$string.IDS_ins);
        }
        healthNumberPicker2.setDisplayedValues(strArr2);
        healthNumberPicker2.setMinValue(0);
        healthNumberPicker2.setMaxValue(11);
        int[] iArr = {5, 3};
        if (cfiVar != null && i > 30) {
            iArr = UnitUtil.j(i / 100.0d);
        }
        healthNumberPicker.setValue(iArr[0] - 1);
        healthNumberPicker2.setValue(iArr[1]);
        dHq_(context, iBaseResponseCallback, inflate, healthNumberPicker, healthNumberPicker2);
    }

    private static void dHq_(Context context, final IBaseResponseCallback iBaseResponseCallback, View view, final HealthNumberPicker healthNumberPicker, final HealthNumberPicker healthNumberPicker2) {
        String string = context.getString(R$string.IDS_hw_show_set_height);
        CustomViewDialog.Builder builder = new CustomViewDialog.Builder(context);
        builder.a(string).czg_(view).cze_(R$string.IDS_hw_common_ui_dialog_confirm, new View.OnClickListener() { // from class: qpz.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                if (IBaseResponseCallback.this != null) {
                    IBaseResponseCallback.this.d(1, new int[]{healthNumberPicker.getValue() + 1, healthNumberPicker2.getValue()});
                } else {
                    LogUtil.h("BaseHealthViewsUtils", "call back is null");
                }
                ViewClickInstrumentation.clickOnView(view2);
            }
        }).czc_(R$string.IDS_hw_show_cancel, new View.OnClickListener() { // from class: qpz.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                ViewClickInstrumentation.clickOnView(view2);
            }
        });
        builder.e().show();
    }

    private static ArrayList<String> c(Context context) {
        ArrayList<String> arrayList = new ArrayList<>(31);
        for (int i = 50; i < 251; i++) {
            arrayList.add(context.getString(R$string.IDS_hw_show_set_height_value_with_unit_cm, UnitUtil.e(i, 1, 0)));
        }
        return arrayList;
    }

    public static boolean a(int i, int i2, int i3) {
        Calendar calendar = Calendar.getInstance();
        int i4 = calendar.get(1);
        int i5 = calendar.get(2) + 1;
        int i6 = calendar.get(5);
        boolean z = i > i4;
        if (i == i4 && i2 > i5) {
            z = true;
        }
        if (i == i4 && i2 == i5 && i3 > i6) {
            return true;
        }
        return z;
    }

    public static boolean b(int i, int i2, int i3) {
        int i4 = i + 18;
        Calendar calendar = Calendar.getInstance();
        int i5 = calendar.get(1);
        int i6 = calendar.get(2);
        int i7 = calendar.get(5);
        if (i4 < i5) {
            return false;
        }
        if (i4 != i5 || i2 >= i6) {
            return (i4 == i5 && i2 == i6 && i3 + 1 <= i7) ? false : true;
        }
        return false;
    }

    public static void e(Context context) {
        if (context == null) {
            return;
        }
        NoTitleCustomAlertDialog.Builder builder = new NoTitleCustomAlertDialog.Builder(context);
        builder.e(context.getString(R$string.IDS_hwh_home_overall_target_calculation)).czC_(R$string.IDS_settings_button_ok, new View.OnClickListener() { // from class: qqc
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        builder.e().show();
    }
}
