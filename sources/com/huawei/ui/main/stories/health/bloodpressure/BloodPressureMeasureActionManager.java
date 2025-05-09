package com.huawei.ui.main.stories.health.bloodpressure;

import android.content.Context;
import android.content.DialogInterface;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import androidx.core.content.ContextCompat;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.ui.commonui.R$color;
import com.huawei.ui.commonui.dialog.CustomAlertDialog;
import com.huawei.ui.commonui.edittext.HealthErrorTipTextLayout;
import com.huawei.ui.commonui.flowlayout.HealthFlowLayout;
import com.huawei.ui.main.R$string;
import com.huawei.ui.main.stories.health.bloodpressure.BloodPressureMeasureActionManager;
import com.huawei.uikit.phone.hwedittext.widget.HwIconTextLayout;
import defpackage.koq;
import defpackage.nmk;
import defpackage.nrh;
import defpackage.qkv;
import health.compact.a.LanguageUtil;
import health.compact.a.UnitUtil;
import health.compact.a.util.LogUtil;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes6.dex */
public class BloodPressureMeasureActionManager {
    private HealthFlowLayout c;
    private final Context d;
    private CustomActionChangeListener f;
    private boolean h;
    private int i;
    private int b = 1;
    private int e = -1;

    /* renamed from: a, reason: collision with root package name */
    private String f10147a = "";
    private List<qkv> j = new ArrayList(0);

    public interface CustomActionChangeListener {
        void onAddAction(String str);
    }

    public BloodPressureMeasureActionManager(Context context) {
        this.d = context;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void f() {
        View inflate = LayoutInflater.from(this.d).inflate(R.layout.item_blood_pressure_custom_action, (ViewGroup) null);
        HealthErrorTipTextLayout healthErrorTipTextLayout = (HealthErrorTipTextLayout) inflate.findViewById(R.id.error_tip_bubble);
        final EditText editText = healthErrorTipTextLayout.getEditText();
        dDt_(editText, healthErrorTipTextLayout);
        CustomAlertDialog.Builder cyn_ = new CustomAlertDialog.Builder(this.d).cyp_(inflate).e(R$string.IDS_custom).cyo_(R$string.IDS_settings_button_ok, new DialogInterface.OnClickListener() { // from class: qgv
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                BloodPressureMeasureActionManager.this.dDx_(editText, dialogInterface, i);
            }
        }).cyn_(R$string.IDS_settings_button_cancal, new DialogInterface.OnClickListener() { // from class: qgw
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                ViewClickInstrumentation.clickOnDialog(dialogInterface, i);
            }
        });
        CustomAlertDialog c = cyn_.c();
        cyn_.b().setTextColor(this.d.getColor(R.color._2131296532_res_0x7f090114));
        cyn_.d().setTextColor(this.d.getColor(R.color._2131296532_res_0x7f090114));
        c.setCanceledOnTouchOutside(false);
        c.setOnDismissListener(new DialogInterface.OnDismissListener() { // from class: qgz
            @Override // android.content.DialogInterface.OnDismissListener
            public final void onDismiss(DialogInterface dialogInterface) {
                BloodPressureMeasureActionManager.this.dDy_(editText, dialogInterface);
            }
        });
        c.setOnShowListener(new DialogInterface.OnShowListener() { // from class: qgy
            @Override // android.content.DialogInterface.OnShowListener
            public final void onShow(DialogInterface dialogInterface) {
                BloodPressureMeasureActionManager.this.dDA_(editText, dialogInterface);
            }
        });
        c.show();
    }

    public /* synthetic */ void dDx_(EditText editText, DialogInterface dialogInterface, int i) {
        String trim = editText.getText().toString().trim();
        if (!TextUtils.isEmpty(trim)) {
            a(trim);
        }
        ViewClickInstrumentation.clickOnDialog(dialogInterface, i);
    }

    public /* synthetic */ void dDy_(EditText editText, DialogInterface dialogInterface) {
        ((InputMethodManager) this.d.getSystemService(InputMethodManager.class)).hideSoftInputFromWindow(editText.getWindowToken(), 2);
        editText.clearFocus();
    }

    public /* synthetic */ void dDA_(final EditText editText, DialogInterface dialogInterface) {
        editText.postDelayed(new Runnable() { // from class: qha
            @Override // java.lang.Runnable
            public final void run() {
                BloodPressureMeasureActionManager.this.dDz_(editText);
            }
        }, 100L);
    }

    public /* synthetic */ void dDz_(EditText editText) {
        editText.requestFocus();
        ((InputMethodManager) this.d.getSystemService(InputMethodManager.class)).showSoftInput(editText, 0);
    }

    private void dDt_(final EditText editText, final HealthErrorTipTextLayout healthErrorTipTextLayout) {
        final String string = this.d.getString(R$string.IDS_custom_remind_1);
        final HwIconTextLayout hwIconTextLayout = (HwIconTextLayout) healthErrorTipTextLayout.findViewById(R.id.icon_text_bubble);
        hwIconTextLayout.setOnIconClickListener(new View.OnClickListener() { // from class: qhb
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                BloodPressureMeasureActionManager.dDu_(editText, view);
            }
        });
        editText.addTextChangedListener(new TextWatcher() { // from class: com.huawei.ui.main.stories.health.bloodpressure.BloodPressureMeasureActionManager.5
            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable editable) {
                editText.removeTextChangedListener(this);
                int i = LanguageUtil.h(BaseApplication.getContext()) ? 12 : 16;
                String obj = editable.toString();
                String str = obj.getBytes(StandardCharsets.UTF_8).length <= i ? obj : BloodPressureMeasureActionManager.this.f10147a;
                BloodPressureMeasureActionManager.this.f10147a = str;
                if (!obj.equals(str)) {
                    editText.setText(str);
                    editText.setSelection(str.length());
                    healthErrorTipTextLayout.setError(string);
                } else {
                    healthErrorTipTextLayout.setError("");
                }
                if (!TextUtils.isEmpty(obj)) {
                    hwIconTextLayout.setIcon(ContextCompat.getDrawable(BloodPressureMeasureActionManager.this.d, R.drawable._2131429705_res_0x7f0b0949));
                } else {
                    hwIconTextLayout.setIcon(null);
                }
                editText.addTextChangedListener(this);
            }
        });
    }

    public static /* synthetic */ void dDu_(EditText editText, View view) {
        editText.setText("");
        ViewClickInstrumentation.clickOnView(view);
    }

    private void a(List<qkv> list) {
        ArrayList<nmk> d = d(list);
        this.c.e(d, false);
        for (int i = 0; i < d.size(); i++) {
            dDw_(this.c.getChildAt(i), i);
        }
    }

    private void dDw_(View view, final int i) {
        view.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.ui.main.stories.health.bloodpressure.BloodPressureMeasureActionManager.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                if (BloodPressureMeasureActionManager.this.h) {
                    if (i == BloodPressureMeasureActionManager.this.j.size()) {
                        BloodPressureMeasureActionManager.this.f();
                        ViewClickInstrumentation.clickOnView(view2);
                        return;
                    }
                    BloodPressureMeasureActionManager.this.c(i);
                    if (koq.b(BloodPressureMeasureActionManager.this.j, i)) {
                        LogUtil.d("MeasureActionManager", "setItemBackground isOutOfBounds");
                        ViewClickInstrumentation.clickOnView(view2);
                        return;
                    } else {
                        if (!(view2 instanceof TextView)) {
                            LogUtil.c("MeasureActionManager", "view is not TextView");
                            ViewClickInstrumentation.clickOnView(view2);
                            return;
                        }
                        TextView textView = (TextView) view2;
                        if (((qkv) BloodPressureMeasureActionManager.this.j.get(i)).i()) {
                            textView.setBackgroundResource(R.drawable._2131427611_res_0x7f0b011b);
                            textView.setTextColor(ContextCompat.getColor(BloodPressureMeasureActionManager.this.d, R$color.textColorPrimaryInverse));
                        } else {
                            textView.setBackgroundResource(R.drawable._2131427610_res_0x7f0b011a);
                            textView.setTextColor(ContextCompat.getColor(BloodPressureMeasureActionManager.this.d, R$color.textColorSecondary));
                        }
                    }
                }
                ViewClickInstrumentation.clickOnView(view2);
            }
        });
    }

    private ArrayList<nmk> d(List<qkv> list) {
        ArrayList<nmk> arrayList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            qkv qkvVar = list.get(i);
            nmk nmkVar = new nmk(i, qkvVar.d(), 1);
            nmkVar.d(qkvVar.i());
            arrayList.add(nmkVar);
        }
        if (this.b == 1 && this.h) {
            arrayList.add(new nmk(arrayList.size(), "+ " + this.d.getString(R$string.IDS_custom), 1));
        }
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(int i) {
        if (koq.b(this.j, i)) {
            LogUtil.d("MeasureActionManager", "clickActionSelected isOutOfBounds");
            return;
        }
        qkv qkvVar = this.j.get(i);
        if (e(qkvVar)) {
            return;
        }
        if (koq.d(this.j, this.e) && this.j.get(this.e).i()) {
            this.j.get(this.e).c(false);
            e();
        }
        if (qkvVar.i()) {
            a(qkvVar);
        } else {
            c(qkvVar);
        }
    }

    private boolean e(qkv qkvVar) {
        if (!qkvVar.e()) {
            return false;
        }
        if (qkvVar.i()) {
            return true;
        }
        j();
        return true;
    }

    private void a(qkv qkvVar) {
        e();
        if (d()) {
            b();
        } else {
            qkvVar.c(!qkvVar.i());
        }
    }

    private void c(qkv qkvVar) {
        b();
        if (d()) {
            e();
            String e = UnitUtil.e(10.0d, 1, 0);
            Context context = this.d;
            nrh.d(context, context.getString(R$string.IDS_custom_max_remind, e));
            return;
        }
        qkvVar.c(!qkvVar.i());
    }

    private boolean d() {
        int i = this.i;
        return i < 0 || i > 10;
    }

    private void e() {
        this.i--;
    }

    private void b() {
        this.i++;
    }

    private void i() {
        this.i = 0;
    }

    private void j() {
        i();
        for (qkv qkvVar : this.j) {
            if (qkvVar.e()) {
                qkvVar.c(true);
                b();
            } else {
                qkvVar.c(false);
            }
        }
    }

    private void a(String str) {
        for (int i = 0; i < this.j.size(); i++) {
            qkv qkvVar = this.j.get(i);
            if (qkvVar.d().equals(str)) {
                if (qkvVar.i()) {
                    return;
                }
                c(i);
                a(this.j);
                return;
            }
        }
        CustomActionChangeListener customActionChangeListener = this.f;
        if (customActionChangeListener != null) {
            customActionChangeListener.onAddAction(str);
        }
        qkv qkvVar2 = new qkv(str);
        qkvVar2.b(true);
        this.j.add(qkvVar2);
        a();
        a(this.j);
    }

    /* JADX WARN: Code restructure failed: missing block: B:10:0x0027, code lost:
    
        if ((r4.j.size() - r1) < 30) goto L13;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void a() {
        /*
            r4 = this;
            r0 = 0
            r1 = r0
        L2:
            java.util.List<qkv> r2 = r4.j
            int r2 = r2.size()
            if (r1 >= r2) goto L2d
            java.util.List<qkv> r2 = r4.j
            java.lang.Object r2 = r2.get(r1)
            qkv r2 = (defpackage.qkv) r2
            boolean r3 = r2.a()
            if (r3 != 0) goto L2a
            boolean r2 = r2.b()
            if (r2 == 0) goto L2a
            java.util.List<qkv> r2 = r4.j
            int r2 = r2.size()
            int r2 = r2 - r1
            r1 = 30
            if (r2 >= r1) goto L31
            goto L2d
        L2a:
            int r1 = r1 + 1
            goto L2
        L2d:
            boolean r1 = r4.h
            if (r1 != 0) goto L34
        L31:
            r4.b = r0
            goto L37
        L34:
            r0 = 1
            r4.b = r0
        L37:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.ui.main.stories.health.bloodpressure.BloodPressureMeasureActionManager.a():void");
    }

    public void e(HealthFlowLayout healthFlowLayout) {
        this.c = healthFlowLayout;
        this.c.setTextViewBuilder(new BloodPressureTagTextViewBuilder(BaseApplication.getContext()));
    }

    public void c(List<qkv> list) {
        if (list == null) {
            return;
        }
        this.j = new ArrayList(list.size());
        i();
        int i = 0;
        for (int i2 = 0; i2 < list.size(); i2++) {
            qkv qkvVar = list.get(i2);
            if (qkvVar.f()) {
                if (qkvVar.i()) {
                    b();
                }
                if (qkvVar.e()) {
                    this.e = i;
                }
                this.j.add(qkvVar);
                i++;
            }
        }
        a();
        a(this.j);
    }

    public List<qkv> c() {
        return this.j;
    }

    public void b(boolean z) {
        this.h = z;
        a();
        a(this.j);
    }

    public void e(CustomActionChangeListener customActionChangeListener) {
        this.f = customActionChangeListener;
    }
}
