package com.huawei.healthcloud.plugintrack.util;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.view.View;
import androidx.core.content.ContextCompat;
import com.huawei.haf.application.BaseApplication;
import com.huawei.health.R;
import com.huawei.health.userprofilemgr.api.UserProfileMgrApi;
import com.huawei.healthcloud.plugintrack.util.AiSportExamDialogUtils;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.button.HealthButton;
import com.huawei.ui.commonui.dialog.CustomAlertDialog;
import com.huawei.ui.commonui.dialog.HealthButtonBarLayout;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.scrollview.ScrollScaleView;
import com.huawei.up.model.UserInfomation;
import defpackage.gvv;
import defpackage.koq;
import health.compact.a.Services;
import health.compact.a.UnitUtil;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

/* loaded from: classes4.dex */
public class AiSportExamDialogUtils {

    public interface DialogResultCallback {
        void executeResult(float f);
    }

    public static void a(final Context context, final DialogResultCallback dialogResultCallback) {
        if (context == null || dialogResultCallback == null) {
            LogUtil.h("Track_DialogUtils", "showSetHeightDialog context == null || resultCallback == null");
            return;
        }
        View inflate = View.inflate(context, R.layout.dialog_sport_exam_height, null);
        final AtomicReference<Float> bnJ_ = bnJ_(context, inflate, c());
        CustomAlertDialog.Builder builder = new CustomAlertDialog.Builder(context);
        builder.e(R.string._2130840185_res_0x7f020a79).cyp_(inflate).cyo_(R.string._2130841395_res_0x7f020f33, new DialogInterface.OnClickListener() { // from class: hos
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                AiSportExamDialogUtils.bnN_(AiSportExamDialogUtils.DialogResultCallback.this, bnJ_, dialogInterface, i);
            }
        }).cyn_(R.string._2130845109_res_0x7f021db5, new DialogInterface.OnClickListener() { // from class: hot
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                AiSportExamDialogUtils.bnO_(AiSportExamDialogUtils.DialogResultCallback.this, context, dialogInterface, i);
            }
        });
        CustomAlertDialog c = builder.c();
        HealthButton healthButton = (HealthButton) c.findViewById(R.id.dialog_btn_positive);
        healthButton.setTextColor(ContextCompat.getColor(context, R.color._2131298273_res_0x7f0907e1));
        healthButton.setBackgroundResource(R.drawable.button_background_emphasize);
        ((HealthButtonBarLayout) c.findViewById(R.id.button_bar)).setDividerDrawable(ContextCompat.getDrawable(context, R.drawable._2131427926_res_0x7f0b0256));
        c.setCancelable(false);
        c.show();
    }

    public static /* synthetic */ void bnN_(DialogResultCallback dialogResultCallback, AtomicReference atomicReference, DialogInterface dialogInterface, int i) {
        dialogResultCallback.executeResult(((Float) atomicReference.get()).floatValue());
        ViewClickInstrumentation.clickOnDialog(dialogInterface, i);
    }

    public static /* synthetic */ void bnO_(DialogResultCallback dialogResultCallback, Context context, DialogInterface dialogInterface, int i) {
        dialogResultCallback.executeResult(0.0f);
        c(context);
        ViewClickInstrumentation.clickOnDialog(dialogInterface, i);
    }

    private static float c() {
        UserProfileMgrApi userProfileMgrApi = (UserProfileMgrApi) Services.a("HWUserProfileMgr", UserProfileMgrApi.class);
        if (userProfileMgrApi == null) {
            LogUtil.h("Track_DialogUtils", "showSetHeightDialog userProfileMgrApi is null");
            return 170.0f;
        }
        UserInfomation userInfo = userProfileMgrApi.getUserInfo();
        if (userInfo == null) {
            LogUtil.h("Track_DialogUtils", "showSetHeightDialog userInfo is null");
            return 170.0f;
        }
        float height = userInfo.getHeight();
        if (gvv.b(height)) {
            return 170.0f;
        }
        return height;
    }

    private static AtomicReference<Float> bnJ_(Context context, View view, float f) {
        ScrollScaleView scrollScaleView = (ScrollScaleView) view.findViewById(R.id.height_scale);
        final HealthTextView healthTextView = (HealthTextView) view.findViewById(R.id.height_text);
        ArrayList arrayList = new ArrayList();
        for (int i = 50; i <= 250; i++) {
            arrayList.add(UnitUtil.e(i, 1, 0));
        }
        scrollScaleView.setData(arrayList, 10, 40);
        scrollScaleView.setCustomStartColor(ContextCompat.getColor(context, R.color._2131296927_res_0x7f09029f));
        scrollScaleView.setSelectedPosition(a(f, 50));
        healthTextView.setText(UnitUtil.e(f, 1, 1));
        final AtomicReference<Float> atomicReference = new AtomicReference<>(Float.valueOf(f));
        scrollScaleView.setOnSelectedListener(new ScrollScaleView.OnSelectedListener() { // from class: hor
            @Override // com.huawei.ui.commonui.scrollview.ScrollScaleView.OnSelectedListener
            public final void onSelected(List list, int i2) {
                AiSportExamDialogUtils.a(HealthTextView.this, 50, (AtomicReference<Float>) atomicReference, i2);
            }
        });
        return atomicReference;
    }

    private static void c(Context context) {
        if (!(context instanceof Activity)) {
            context = BaseApplication.wa_();
        }
        if (context instanceof Activity) {
            ((Activity) context).finish();
        }
    }

    private static int a(float f, int i) {
        return ((int) BigDecimal.valueOf(f).subtract(BigDecimal.valueOf(i)).floatValue()) * 10;
    }

    public static void d(Context context, List<Integer> list, final DialogResultCallback dialogResultCallback) {
        if (dialogResultCallback == null) {
            LogUtil.h("Track_DialogUtils", "resultCallback context == null");
            return;
        }
        ArrayList arrayList = new ArrayList(list);
        for (int i = 0; i <= 2; i++) {
            if (koq.b(arrayList, i)) {
                arrayList.add(0);
            }
        }
        if (context == null) {
            LogUtil.h("Track_DialogUtils", "showScoreConfirmDialog context == null");
            dialogResultCallback.executeResult(0.0f);
            return;
        }
        int intValue = ((Integer) Collections.max(arrayList)).intValue();
        View inflate = View.inflate(context, R.layout.dialog_sport_exam_jump_distance, null);
        bnL_(inflate, arrayList);
        final AtomicReference<Float> bnK_ = bnK_(context, inflate, intValue);
        CustomAlertDialog.Builder builder = new CustomAlertDialog.Builder(context);
        builder.b().setBackgroundResource(R.drawable.button_background_emphasize);
        builder.b().setTextColor(ContextCompat.getColor(context, R.color._2131299238_res_0x7f090ba6));
        builder.e(R.string._2130840188_res_0x7f020a7c).cyp_(inflate).cyo_(R.string._2130841395_res_0x7f020f33, new DialogInterface.OnClickListener() { // from class: hom
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i2) {
                AiSportExamDialogUtils.bnM_(AiSportExamDialogUtils.DialogResultCallback.this, bnK_, dialogInterface, i2);
            }
        });
        CustomAlertDialog c = builder.c();
        c.setCancelable(false);
        c.show();
    }

    public static /* synthetic */ void bnM_(DialogResultCallback dialogResultCallback, AtomicReference atomicReference, DialogInterface dialogInterface, int i) {
        dialogResultCallback.executeResult(((Float) atomicReference.get()).floatValue());
        ViewClickInstrumentation.clickOnDialog(dialogInterface, i);
    }

    private static void bnL_(View view, List<Integer> list) {
        e(list.get(0).intValue(), (HealthTextView) view.findViewById(R.id.first_jump_score));
        e(list.get(1).intValue(), (HealthTextView) view.findViewById(R.id.second_jump_score));
        e(list.get(2).intValue(), (HealthTextView) view.findViewById(R.id.third_jump_score));
    }

    private static void e(float f, HealthTextView healthTextView) {
        healthTextView.setText(BaseApplication.e().getResources().getQuantityString(R.plurals._2130903214_res_0x7f0300ae, (int) f, UnitUtil.e(f, 1, 1)));
    }

    private static AtomicReference<Float> bnK_(Context context, View view, int i) {
        ScrollScaleView scrollScaleView = (ScrollScaleView) view.findViewById(R.id.distance_scale);
        final HealthTextView healthTextView = (HealthTextView) view.findViewById(R.id.best_distance_text);
        healthTextView.setText(UnitUtil.e(i, 1, 1));
        ArrayList arrayList = new ArrayList();
        final int i2 = i - 10;
        for (int i3 = i2; i3 <= i + 10; i3++) {
            arrayList.add(UnitUtil.e(i3, 1, 0));
        }
        scrollScaleView.setData(arrayList, 10, 40);
        scrollScaleView.setCustomStartColor(ContextCompat.getColor(context, R.color._2131296927_res_0x7f09029f));
        float f = i;
        scrollScaleView.setSelectedPosition(a(f, i2));
        final AtomicReference<Float> atomicReference = new AtomicReference<>(Float.valueOf(f));
        scrollScaleView.setOnSelectedListener(new ScrollScaleView.OnSelectedListener() { // from class: hou
            @Override // com.huawei.ui.commonui.scrollview.ScrollScaleView.OnSelectedListener
            public final void onSelected(List list, int i4) {
                AiSportExamDialogUtils.a(HealthTextView.this, i2, (AtomicReference<Float>) atomicReference, i4);
            }
        });
        return atomicReference;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void a(HealthTextView healthTextView, int i, AtomicReference<Float> atomicReference, int i2) {
        atomicReference.set(Float.valueOf(new BigDecimal(i + (i2 * 0.1f)).setScale(1, 4).floatValue()));
        if (healthTextView != null) {
            healthTextView.setText(UnitUtil.e(r4.floatValue(), 1, 1));
        }
    }
}
