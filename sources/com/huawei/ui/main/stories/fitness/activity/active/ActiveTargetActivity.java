package com.huawei.ui.main.stories.fitness.activity.active;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Message;
import android.view.View;
import android.widget.LinearLayout;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import com.google.android.gms.common.util.ArrayUtils;
import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.handler.BaseHandler;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.haf.router.AppRouterUtils;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.dialog.CustomAlertDialog;
import com.huawei.ui.commonui.divider.HealthDivider;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.imageview.HealthImageView;
import com.huawei.ui.commonui.numberpicker.HealthNumberPicker;
import com.huawei.ui.commonui.titlebar.CustomTitleBar;
import com.huawei.ui.main.R$string;
import com.huawei.ui.main.stories.fitness.activity.active.ActiveTargetActivity;
import defpackage.gnm;
import defpackage.nip;
import defpackage.nir;
import defpackage.niw;
import defpackage.nrh;
import defpackage.nsn;
import defpackage.pxp;
import health.compact.a.CommonUtil;
import health.compact.a.LanguageUtil;
import health.compact.a.UnitUtil;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.function.ToIntFunction;

/* loaded from: classes6.dex */
public class ActiveTargetActivity extends BaseActivity implements View.OnClickListener {
    private static final int b = 2130903373;
    private static final int c = 2130903372;
    private static final int i = 2130903374;
    private HealthTextView aa;
    private HealthTextView ab;
    private int ac;
    private HealthImageView ad;
    private ConstraintLayout ae;
    private ConstraintLayout af;
    private int ag;
    private ConstraintLayout ah;
    private LinearLayout ai;
    private HealthTextView aj;
    private HealthImageView ak;
    private int al;
    private HealthTextView am;
    private int an;
    private HealthTextView g;
    private int k;
    private HealthImageView l;
    private HealthTextView m;
    private HealthTextView n;
    private HealthTextView o;
    private int p;
    private HealthDivider q;
    private int r;
    private HealthImageView s;
    private int t;
    private HealthTextView u;
    private HealthTextView v;
    private int w;
    private HealthTextView x;
    private b y = new b(this);
    private ConstraintLayout z;
    private static final int j = R$string.IDS_setting_active_caloric;
    private static final int f = R$string.IDS_setting_active_workout;
    private static final int h = R$string.IDS_setting_active_hours;

    /* renamed from: a, reason: collision with root package name */
    private static final int[] f9722a = e();
    private static final int[] d = {10, 15, 20, 25, 30, 35, 40, 45, 50, 55, 60, 65, 70, 75, 80, 85, 90};
    private static final int[] e = {6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16};

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes2.dex */
    public @interface PickerType {
    }

    private static int[] e() {
        ArrayList arrayList = new ArrayList();
        for (int i2 = 50; i2 <= 5000; i2 += 10) {
            arrayList.add(Integer.valueOf(i2));
        }
        int[] iArr = new int[arrayList.size()];
        return arrayList.stream().mapToInt(new ToIntFunction() { // from class: pha
            @Override // java.util.function.ToIntFunction
            public final int applyAsInt(Object obj) {
                int intValue;
                intValue = ((Integer) obj).intValue();
                return intValue;
            }
        }).toArray();
    }

    public static void b(Context context, int i2) {
        b(context, i2, 0);
    }

    public static void e(Context context, int i2, int i3) {
        b(context, i2, i3);
    }

    private static void b(Context context, int i2, int i3) {
        Intent intent = new Intent(BaseApplication.e(), (Class<?>) ActiveTargetActivity.class);
        intent.putExtra("from", i2);
        if (i3 > 0) {
            intent.putExtra("key_picker_view", i3);
        }
        gnm.aPB_(context, intent);
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_active_target);
        f();
        a();
        g();
    }

    private void f() {
        ((CustomTitleBar) findViewById(R.id.ctb_active_target_title)).setTitleBarBackgroundColor(ContextCompat.getColor(this, R.color._2131296971_res_0x7f0902cb));
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.layout_suggestion_caloric);
        this.ai = linearLayout;
        linearLayout.setVisibility(8);
        ConstraintLayout constraintLayout = (ConstraintLayout) findViewById(R.id.layout_suggestion_caloric_target);
        this.af = constraintLayout;
        this.am = (HealthTextView) constraintLayout.findViewById(R.id.htv_item_left_text);
        this.aj = (HealthTextView) this.af.findViewById(R.id.htv_item_right_text);
        this.ak = (HealthImageView) this.af.findViewById(R.id.hiv_right_arrow);
        this.g = (HealthTextView) findViewById(R.id.htv_caloric_suggestion);
        this.m = (HealthTextView) findViewById(R.id.htv_caloric_suggestion_change);
        ConstraintLayout constraintLayout2 = (ConstraintLayout) findViewById(R.id.layout_caloric_target);
        this.ae = constraintLayout2;
        this.v = (HealthTextView) constraintLayout2.findViewById(R.id.htv_item_left_text);
        this.x = (HealthTextView) this.ae.findViewById(R.id.htv_item_right_text);
        this.s = (HealthImageView) this.ae.findViewById(R.id.hiv_right_arrow);
        this.q = (HealthDivider) findViewById(R.id.caloric_divider);
        ConstraintLayout constraintLayout3 = (ConstraintLayout) findViewById(R.id.layout_intensity_target);
        this.ah = constraintLayout3;
        this.aa = (HealthTextView) constraintLayout3.findViewById(R.id.htv_item_left_text);
        this.ab = (HealthTextView) this.ah.findViewById(R.id.htv_item_right_text);
        this.ad = (HealthImageView) this.ah.findViewById(R.id.hiv_right_arrow);
        ConstraintLayout constraintLayout4 = (ConstraintLayout) findViewById(R.id.layout_active_hours_target);
        this.z = constraintLayout4;
        this.o = (HealthTextView) constraintLayout4.findViewById(R.id.htv_item_left_text);
        this.n = (HealthTextView) this.z.findViewById(R.id.htv_item_right_text);
        this.l = (HealthImageView) this.z.findViewById(R.id.hiv_right_arrow);
    }

    private void a() {
        Intent intent = getIntent();
        if (intent != null) {
            this.w = intent.getIntExtra("from", -1);
            this.ag = intent.getIntExtra("key_picker_view", 0);
            Uri zs_ = AppRouterUtils.zs_(intent);
            if (zs_ != null) {
                this.k = CommonUtil.h(zs_.getQueryParameter("caloricTarget"));
                this.p = CommonUtil.h(zs_.getQueryParameter("intensityTarget"));
                this.t = CommonUtil.h(zs_.getQueryParameter("standTarget"));
                j();
            }
        }
        i();
        h();
    }

    private void g() {
        this.af.setOnClickListener(this);
        this.m.setOnClickListener(this);
        this.ae.setOnClickListener(this);
        this.ah.setOnClickListener(this);
        this.z.setOnClickListener(this);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (nsn.a(500)) {
            LogUtil.h("SCUI_ActiveTargetActivity", "click too fast");
            ViewClickInstrumentation.clickOnView(view);
            return;
        }
        if (view == this.ae) {
            LogUtil.a("SCUI_ActiveTargetActivity", "onClick click view mLayoutCaloricTarget");
            b(c, f9722a, j, this.x);
        } else if (view == this.af) {
            LogUtil.a("SCUI_ActiveTargetActivity", "onClick click view mLayoutSuggestionCaloricTarget");
            b(c, f9722a, j, this.aj);
        } else if (view == this.ah) {
            LogUtil.a("SCUI_ActiveTargetActivity", "onClick click view mLayoutIntensityTarget");
            b(b, d, f, this.ab);
        } else if (view == this.z) {
            LogUtil.a("SCUI_ActiveTargetActivity", "onClick click view mLayoutActiveHours");
            b(i, e, h, this.n);
        } else if (view == this.m) {
            LogUtil.a("SCUI_ActiveTargetActivity", "onClick click view mActiveCaloricSuggestionChange");
            b(this.an);
            d();
            b("900200007", this.an);
            e(4, this.an);
            nrh.d(BaseApplication.e(), getResources().getString(R$string.IDS_change_success));
        } else {
            LogUtil.a("SCUI_ActiveTargetActivity", "onClick click view else");
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    private void b(int i2, int[] iArr, int i3, HealthTextView healthTextView) {
        this.u = healthTextView;
        c(iArr, b(c(i2), iArr), i3, i2);
        d(i2);
    }

    private int c(int i2) {
        if (i2 == b) {
            return this.ac;
        }
        if (i2 == i) {
            return this.al;
        }
        return this.ai.getVisibility() == 0 ? this.an : this.r;
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        ArrayList arrayList = new ArrayList(3);
        arrayList.add("900200007");
        arrayList.add("900200008");
        arrayList.add("900200009");
        nip.a(arrayList, new d());
    }

    private void j() {
        LogUtil.a("SCUI_ActiveTargetActivity", "setTargetValue mActiveCaloricValue is ", Integer.valueOf(this.k), ", mActiveIntensityValue is ", Integer.valueOf(this.p), ", mActiveStandValue is ", Integer.valueOf(this.t));
        if (ArrayUtils.contains(f9722a, this.k)) {
            b("900200007", this.k);
        }
        if (ArrayUtils.contains(d, this.p)) {
            b("900200008", this.p);
        }
        if (ArrayUtils.contains(e, this.t)) {
            b("900200009", this.t);
        }
    }

    private void m() {
        LogUtil.a("SCUI_ActiveTargetActivity", "showTargetPickerView mShowTarget ", Integer.valueOf(this.ag));
        int i2 = this.ag;
        if (i2 == 2) {
            b(b, d, f, this.ab);
        } else if (i2 == 3) {
            b(i, e, h, this.n);
        } else if (i2 == 4) {
            b(c, f9722a, j, this.x);
        }
        this.ag = 0;
    }

    private void i() {
        this.am.setText(getString(R$string.IDS_active_caloric));
        this.v.setText(getString(R$string.IDS_active_caloric));
        b(270);
        this.aa.setText(getString(R$string.IDS_active_workout));
        this.ab.setText(getResources().getQuantityString(b, 25, e(25)));
        this.o.setText(getString(R$string.IDS_active_hours));
        this.n.setText(getResources().getQuantityString(i, 12, e(12)));
    }

    private String e(int i2) {
        return UnitUtil.e(i2, 1, 0);
    }

    private void h() {
        if (LanguageUtil.bc(this)) {
            Drawable drawable = getResources().getDrawable(R.drawable._2131427841_res_0x7f0b0201);
            this.ak.setBackground(drawable);
            this.s.setBackground(drawable);
            this.ad.setBackground(drawable);
            this.l.setBackground(drawable);
            return;
        }
        Drawable drawable2 = getResources().getDrawable(R.drawable._2131427842_res_0x7f0b0202);
        this.ak.setBackground(drawable2);
        this.s.setBackground(drawable2);
        this.ad.setBackground(drawable2);
        this.l.setBackground(drawable2);
    }

    private String[] c(int i2, int[] iArr) {
        int length = iArr.length;
        String[] strArr = new String[length];
        for (int i3 = 0; i3 < length; i3++) {
            Resources resources = getResources();
            int i4 = iArr[i3];
            strArr[i3] = resources.getQuantityString(i2, i4, e(i4));
        }
        return strArr;
    }

    private int b(int i2, int[] iArr) {
        int i3 = 0;
        int i4 = 0;
        while (true) {
            if (i4 >= iArr.length - 1) {
                break;
            }
            if (i2 > iArr[i4]) {
                int i5 = i4 + 1;
                if (i2 <= iArr[i5]) {
                    i3 = i5;
                    break;
                }
            }
            i4++;
        }
        LogUtil.a("SCUI_ActiveTargetActivity", "getDefaultPosition position is ", Integer.valueOf(i3));
        return i3;
    }

    private void c(final int[] iArr, int i2, final int i3, final int i4) {
        final HealthNumberPicker healthNumberPicker = new HealthNumberPicker(this);
        final String[] c2 = c(i4, iArr);
        healthNumberPicker.setDisplayedValues(c2);
        healthNumberPicker.setMaxValue(iArr.length - 1);
        healthNumberPicker.setMinValue(0);
        healthNumberPicker.setValue(i2);
        healthNumberPicker.setWrapSelectorWheel(true);
        healthNumberPicker.setSelectionDivider(ContextCompat.getDrawable(this, R.drawable._2131430934_res_0x7f0b0e16));
        CustomAlertDialog.Builder builder = new CustomAlertDialog.Builder(this);
        builder.e(i3).cyp_(healthNumberPicker).cyo_(R$string.IDS_hw_common_ui_dialog_confirm, new DialogInterface.OnClickListener() { // from class: pgv
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i5) {
                ActiveTargetActivity.this.dpx_(healthNumberPicker, iArr, c2, i4, i3, dialogInterface, i5);
            }
        }).cyn_(R$string.IDS_hw_common_ui_dialog_cancel, new DialogInterface.OnClickListener() { // from class: pgs
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i5) {
                ActiveTargetActivity.dpw_(dialogInterface, i5);
            }
        });
        CustomAlertDialog c3 = builder.c();
        c3.setCancelable(false);
        c3.show();
    }

    public /* synthetic */ void dpx_(HealthNumberPicker healthNumberPicker, int[] iArr, String[] strArr, int i2, int i3, DialogInterface dialogInterface, int i4) {
        LogUtil.a("SCUI_ActiveTargetActivity", "showPickerView click positive button");
        int value = healthNumberPicker.getValue();
        if (value < 0 || value > iArr.length - 1) {
            LogUtil.b("SCUI_ActiveTargetActivity", "showPickerView index out of bounds");
            ViewClickInstrumentation.clickOnDialog(dialogInterface, i4);
        } else {
            e(strArr[value].trim());
            if (iArr[value] != c(i2)) {
                d(i3, healthNumberPicker.getValue());
            }
            ViewClickInstrumentation.clickOnDialog(dialogInterface, i4);
        }
    }

    public static /* synthetic */ void dpw_(DialogInterface dialogInterface, int i2) {
        LogUtil.a("SCUI_ActiveTargetActivity", "showPickerView click negative button");
        ViewClickInstrumentation.clickOnDialog(dialogInterface, i2);
    }

    private void e(String str) {
        HealthTextView healthTextView = this.aj;
        HealthTextView healthTextView2 = this.u;
        if (healthTextView == healthTextView2) {
            this.x.setText(str);
            d();
        } else {
            healthTextView2.setText(str);
        }
    }

    private void b(int i2) {
        HealthTextView healthTextView = this.x;
        Resources resources = getResources();
        int i3 = c;
        healthTextView.setText(resources.getQuantityString(i3, i2, e(i2)));
        this.aj.setText(getResources().getQuantityString(i3, i2, e(i2)));
    }

    private void a(int i2, HealthTextView healthTextView, int i3, int i4) {
        if (i2 > 0) {
            healthTextView.setText(getResources().getQuantityString(i3, i2, e(i2)));
        } else {
            healthTextView.setText(getResources().getQuantityString(i3, i4, e(i4)));
        }
    }

    private void d(int i2, int i3) {
        if (i2 == j) {
            int[] iArr = f9722a;
            b("900200007", iArr[i3]);
            e(4, iArr[i3]);
        } else if (i2 == f) {
            int[] iArr2 = d;
            b("900200008", iArr2[i3]);
            e(2, iArr2[i3]);
        } else if (i2 == h) {
            int[] iArr3 = e;
            b("900200009", iArr3[i3]);
            e(3, iArr3[i3]);
        } else {
            LogUtil.h("SCUI_ActiveTargetActivity", "saveTargetDataByTitleId else");
        }
        nrh.d(BaseApplication.e(), getResources().getString(R$string.IDS_change_success));
    }

    private void b(String str, int i2) {
        LogUtil.a("SCUI_ActiveTargetActivity", "saveGoalValueToUserPreference key is ", str, " goalValue is ", Integer.valueOf(i2));
        if ("900200007".equals(str)) {
            i2 *= 1000;
        }
        nip.e(str, i2);
    }

    static class b extends BaseHandler<ActiveTargetActivity> {
        b(ActiveTargetActivity activeTargetActivity) {
            super(activeTargetActivity);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.huawei.haf.handler.BaseHandler
        /* renamed from: dpy_, reason: merged with bridge method [inline-methods] */
        public void handleMessageWhenReferenceNotNull(ActiveTargetActivity activeTargetActivity, Message message) {
            if (message.what == 101) {
                Object obj = message.obj;
                if (obj instanceof HashMap) {
                    activeTargetActivity.a((HashMap) obj);
                    activeTargetActivity.c();
                    return;
                }
                return;
            }
            if (message.what == 102) {
                Object obj2 = message.obj;
                if (obj2 instanceof HashMap) {
                    activeTargetActivity.c((HashMap<String, nir>) obj2);
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c() {
        ArrayList arrayList = new ArrayList(1);
        arrayList.add("900200007");
        niw.b(arrayList, 1, new c());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(HashMap<String, nir> hashMap) {
        if (hashMap.containsKey("900200007")) {
            int e2 = hashMap.get("900200007").e() / 1000;
            this.an = e2;
            if (e2 <= 0 || this.r == e2) {
                return;
            }
            b();
            this.ai.setVisibility(0);
            this.ae.setVisibility(8);
            this.q.setVisibility(8);
            Resources resources = getResources();
            int i2 = c;
            int i3 = this.r;
            String quantityString = resources.getQuantityString(i2, i3, e(i3));
            Resources resources2 = getResources();
            int i4 = this.an;
            this.g.setText(getString(R$string.IDS_caloric_suggestion, new Object[]{quantityString, resources2.getQuantityString(R.plurals._2130903378_res_0x7f030152, i4, e(i4))}));
            this.m.setText(getString(R$string.IDS_modify_now).toUpperCase(Locale.ROOT));
            pxp.b(1);
        }
    }

    private void b() {
        Intent intent = getIntent();
        if (intent == null || intent.getIntExtra("key_picker_view", 0) != 4) {
            return;
        }
        this.u = this.aj;
    }

    private void d() {
        this.ai.setVisibility(8);
        this.ae.setVisibility(0);
        this.q.setVisibility(0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(HashMap<String, Integer> hashMap) {
        this.r = nip.e(hashMap, "900200007", 270000) / 1000;
        int i2 = ArrayUtils.contains(f9722a, this.k) ? this.k : this.r;
        this.r = i2;
        b(i2);
        this.ac = nip.e(hashMap, "900200008", 25);
        int i3 = ArrayUtils.contains(d, this.p) ? this.p : this.ac;
        this.ac = i3;
        a(i3, this.ab, b, 25);
        this.al = nip.e(hashMap, "900200009", 12);
        int i4 = ArrayUtils.contains(e, this.t) ? this.t : this.al;
        this.al = i4;
        a(i4, this.n, i, 12);
        LogUtil.a("SCUI_ActiveTargetActivity", "showTargetContent: stepGoalValue = ", Integer.valueOf(this.r), " , intensityGoalValue = ", Integer.valueOf(this.ac), " , standGoalValue = ", Integer.valueOf(this.al));
        m();
        this.k = 0;
        this.p = 0;
        this.t = 0;
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        b bVar = this.y;
        if (bVar != null) {
            bVar.removeCallbacksAndMessages(null);
            this.y = null;
        }
    }

    private void d(int i2) {
        int i3;
        if (i2 == b) {
            i3 = 2;
        } else {
            i3 = i2 == i ? 3 : 4;
        }
        if (i3 != 4 || (this.ag > 0 && this.an <= 0)) {
            pxp.d(this.w, i3);
            return;
        }
        int i4 = this.w;
        int i5 = this.an;
        pxp.e(i4, i3, i5 > 0 && i5 != this.r);
    }

    private int d(int i2, int i3, int i4) {
        return pxp.d(i2, i3, i4, this.an);
    }

    private void e(int i2, int i3) {
        LogUtil.a("SCUI_ActiveTargetActivity", "save goal type is ", Integer.valueOf(i2), " value is ", Integer.valueOf(i3));
        c(i2, i3);
        if (i2 == 2) {
            this.ac = i3;
        } else if (i2 == 3) {
            this.al = i3;
        } else {
            this.an = 0;
            this.r = i3;
        }
    }

    private void c(int i2, int i3) {
        int d2;
        if (i2 == 2) {
            d2 = d(this.ac, i3, i2);
        } else if (i2 == 3) {
            d2 = d(this.al, i3, i2);
        } else {
            d2 = d(this.r, i3, i2);
        }
        pxp.c(i2, this.w, d2, i3);
    }

    static class d implements IBaseResponseCallback {
        private WeakReference<ActiveTargetActivity> b;

        private d(ActiveTargetActivity activeTargetActivity) {
            this.b = new WeakReference<>(activeTargetActivity);
        }

        @Override // com.huawei.hwbasemgr.IBaseResponseCallback
        /* renamed from: onResponse */
        public void d(int i, Object obj) {
            Object[] objArr = new Object[2];
            objArr[0] = "onResponse: ";
            objArr[1] = obj == null ? null : obj.toString();
            ReleaseLogUtil.e("SCUI_ActiveTargetActivity", objArr);
            ActiveTargetActivity activeTargetActivity = this.b.get();
            if (activeTargetActivity != null) {
                b bVar = activeTargetActivity.y;
                if (!(obj instanceof HashMap)) {
                    LogUtil.h("SCUI_ActiveTargetActivity", "onResponse: objData is not instanceof HashMap");
                    return;
                }
                if (bVar == null) {
                    ReleaseLogUtil.c("SCUI_ActiveTargetActivity", "handler is null");
                    return;
                }
                Message obtain = Message.obtain();
                obtain.what = 101;
                obtain.obj = (HashMap) obj;
                bVar.sendMessage(obtain);
                return;
            }
            LogUtil.h("SCUI_ActiveTargetActivity", "onResponse: activity is null");
        }
    }

    static class c implements IBaseResponseCallback {
        private final WeakReference<ActiveTargetActivity> b;

        private c(ActiveTargetActivity activeTargetActivity) {
            this.b = new WeakReference<>(activeTargetActivity);
        }

        @Override // com.huawei.hwbasemgr.IBaseResponseCallback
        /* renamed from: onResponse */
        public void d(int i, Object obj) {
            ActiveTargetActivity activeTargetActivity = this.b.get();
            if (activeTargetActivity == null) {
                LogUtil.h("SCUI_ActiveTargetActivity", "CalorieRecommendDataCallback, onResponse: activity is null");
                return;
            }
            if (obj instanceof HashMap) {
                if (activeTargetActivity.y == null) {
                    ReleaseLogUtil.c("SCUI_ActiveTargetActivity", "handler is null");
                    return;
                }
                Message obtain = Message.obtain();
                obtain.what = 102;
                obtain.obj = (HashMap) obj;
                activeTargetActivity.y.sendMessage(obtain);
                return;
            }
            LogUtil.h("SCUI_ActiveTargetActivity", "CalorieRecommendDataCallback, onResponse: objData is not instanceof HashMap");
        }
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }
}
