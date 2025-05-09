package com.huawei.health.ecologydevice.ui.dialog;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.huawei.health.R;
import com.huawei.health.ecologydevice.ui.dialog.SelectAdapter;
import com.huawei.health.ecologydevice.ui.dialog.SelectDialog;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.base.BaseDialog;
import com.huawei.ui.commonui.button.HealthButton;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.recycleview.HealthRecycleView;
import defpackage.koq;
import defpackage.nsn;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes3.dex */
public class SelectDialog extends BaseDialog {
    private SelectDialog(Context context, int i) {
        super(context, i);
    }

    public static class Builder {

        /* renamed from: a, reason: collision with root package name */
        private LinearLayout f2316a;
        private LinearLayout b;
        private HealthRecycleView c;
        private final Context d;
        private SelectDialog e;
        private RelativeLayout f;
        private String g;
        private HealthButton h;
        private View.OnClickListener i;
        private HealthButton j;
        private SelectAdapter k;
        private String l;
        private SelectAdapter.OnItemClickListener m;
        private String n;
        private List<Map.Entry<String, String>> o = new ArrayList(16);
        private String q;

        public Builder(Context context) {
            this.d = context;
        }

        public Builder c(LinkedHashMap<String, String> linkedHashMap) {
            if (linkedHashMap == null) {
                return this;
            }
            ArrayList arrayList = new ArrayList(linkedHashMap.entrySet());
            if (koq.c(arrayList)) {
                this.o.addAll(arrayList);
                this.l = this.o.get(0).getKey();
            }
            return this;
        }

        public Builder c(int i, SelectAdapter.OnItemClickListener onItemClickListener) {
            this.n = this.d.getResources().getString(i);
            this.m = onItemClickListener;
            return this;
        }

        public Builder Uf_(int i, View.OnClickListener onClickListener) {
            this.g = this.d.getResources().getString(i);
            this.i = onClickListener;
            return this;
        }

        public SelectDialog d() {
            Drawable drawable;
            int dimensionPixelSize;
            this.e = new SelectDialog(this.d, R.style.CustomDialog);
            Object systemService = this.d.getSystemService("layout_inflater");
            if (systemService instanceof LayoutInflater) {
                View inflate = ((LayoutInflater) systemService).inflate(R.layout.dialog_custom_select, (ViewGroup) null);
                TypedValue typedValue = new TypedValue();
                this.d.getTheme().resolveAttribute(R.attr._2131099975_res_0x7f060147, typedValue, true);
                if (typedValue.resourceId != 0) {
                    TypedArray obtainStyledAttributes = this.d.getTheme().obtainStyledAttributes(typedValue.resourceId, new int[]{R.attr._2131099820_res_0x7f0600ac, R.attr._2131099951_res_0x7f06012f, R.attr._2131100000_res_0x7f060160, R.attr._2131101300_res_0x7f060674});
                    drawable = obtainStyledAttributes.getDrawable(2);
                    TypedValue typedValue2 = new TypedValue();
                    TypedValue typedValue3 = new TypedValue();
                    obtainStyledAttributes.getValue(3, typedValue2);
                    obtainStyledAttributes.getValue(1, typedValue3);
                    dimensionPixelSize = nsn.c(this.d, (int) TypedValue.complexToFloat(typedValue2.data));
                    obtainStyledAttributes.recycle();
                } else {
                    drawable = ContextCompat.getDrawable(this.d, R.drawable._2131427507_res_0x7f0b00b3);
                    dimensionPixelSize = this.d.getResources().getDimensionPixelSize(R.dimen._2131362341_res_0x7f0a0225);
                }
                inflate.setBackground(drawable);
                Ud_(inflate, dimensionPixelSize);
                Ub_(inflate);
                Uc_(inflate);
                this.e.setContentView(inflate);
            }
            return this.e;
        }

        private void Ud_(View view, int i) {
            HealthTextView healthTextView = (HealthTextView) view.findViewById(R.id.custom_dailog_title);
            if (TextUtils.isEmpty(this.q)) {
                healthTextView.setVisibility(8);
            } else {
                healthTextView.setTextSize(0, i);
                healthTextView.setText(this.q);
            }
        }

        private void Uc_(View view) {
            this.f = (RelativeLayout) view.findViewById(R.id.dialog_linearlayout1);
            this.b = (LinearLayout) view.findViewById(R.id.dialog_linearlayout2);
            this.j = (HealthButton) this.f.findViewById(R.id.dialog_btn_negative);
            this.h = (HealthButton) this.f.findViewById(R.id.dialog_btn_positive);
            this.f.setVisibility(0);
            this.b.setVisibility(8);
            this.h.setText(this.n);
            this.h.setAllCaps(true);
            if (this.m != null) {
                this.h.setOnClickListener(new a());
            }
            this.j.setText(this.g);
            this.j.setAllCaps(true);
            if (this.i != null) {
                this.j.setOnClickListener(new c());
            }
        }

        private void Ub_(View view) {
            this.c = (HealthRecycleView) view.findViewById(R.id.rc_card_list);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this.d);
            linearLayoutManager.setOrientation(1);
            this.c.setLayoutManager(linearLayoutManager);
            Ue_(view);
            SelectAdapter selectAdapter = new SelectAdapter(this.d, this.o, new SelectAdapter.OnItemClickListener() { // from class: dfk
                @Override // com.huawei.health.ecologydevice.ui.dialog.SelectAdapter.OnItemClickListener
                public final void onItemClick(String str) {
                    SelectDialog.Builder.this.e(str);
                }
            });
            this.k = selectAdapter;
            this.c.setAdapter(selectAdapter);
        }

        public /* synthetic */ void e(String str) {
            this.l = str;
        }

        private void Ue_(View view) {
            int height;
            int size = this.o.size();
            Context context = this.d;
            if (context == null || size == 0 || !(context instanceof Activity) || nsn.c(this.d, (size * 60) + 120) <= (height = (int) (((Activity) context).getWindowManager().getDefaultDisplay().getHeight() * 0.8f))) {
                return;
            }
            this.f2316a = (LinearLayout) view.findViewById(R.id.rc_card_linear);
            this.f2316a.setLayoutParams(new LinearLayout.LayoutParams(-1, height - nsn.c(this.d, 120.0f)));
        }

        class a implements View.OnClickListener {
            a() {
            }

            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (Builder.this.e != null) {
                    Builder.this.e.dismiss();
                }
                if (Builder.this.m != null) {
                    Builder.this.m.onItemClick(Builder.this.l);
                } else {
                    LogUtil.c("SelectDialog", "positiveButtonClickListener is null");
                }
                ViewClickInstrumentation.clickOnView(view);
            }
        }

        class c implements View.OnClickListener {
            c() {
            }

            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (Builder.this.e != null) {
                    Builder.this.e.dismiss();
                }
                if (Builder.this.i != null) {
                    Builder.this.i.onClick(view);
                }
                ViewClickInstrumentation.clickOnView(view);
            }
        }
    }
}
