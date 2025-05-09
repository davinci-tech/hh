package com.huawei.health.device.ui.dialog;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.res.Resources;
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
import com.huawei.health.device.model.HealthDevice;
import com.huawei.health.device.ui.dialog.CustomDeviceSelectDialog;
import com.huawei.health.device.ui.dialog.DeviceCheckAdapter;
import com.huawei.health.ecologydevice.manager.ResourceManager;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.base.BaseDialog;
import com.huawei.ui.commonui.button.HealthButton;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.recycleview.HealthRecycleView;
import defpackage.ceo;
import defpackage.cjv;
import defpackage.cke;
import defpackage.cpp;
import defpackage.dbp;
import defpackage.dcz;
import defpackage.nsn;
import java.util.ArrayList;
import java.util.Iterator;

/* loaded from: classes3.dex */
public class CustomDeviceSelectDialog extends BaseDialog {
    private CustomDeviceSelectDialog(Context context, int i) {
        super(context, i);
    }

    public static class Builder {

        /* renamed from: a, reason: collision with root package name */
        private DeviceCheckAdapter f2234a;
        private HealthDevice.HealthDeviceKind b;
        private final ArrayList<cjv> c = new ArrayList<>(16);
        private CustomDeviceSelectDialog d;
        private final Context e;
        private LinearLayout f;
        private LinearLayout g;
        private cjv h;
        private RelativeLayout i;
        private HealthRecycleView j;
        private DeviceCheckAdapter.OnItemClickListener k;
        private View.OnClickListener l;
        private String m;
        private HealthButton n;
        private HealthButton o;
        private String q;
        private String r;
        private String s;

        public Builder(Context context) {
            this.e = context;
        }

        private String d(int i) {
            try {
                return String.valueOf(this.e.getText(i));
            } catch (Resources.NotFoundException unused) {
                LogUtil.b("CustomTextAlertDialog", "Resources NotFound");
                return "";
            }
        }

        public Builder b(int i) {
            LogUtil.c("CustomTextAlertDialog", "setTitle: ", Integer.valueOf(i));
            this.r = d(i);
            return this;
        }

        public Builder e(HealthDevice.HealthDeviceKind healthDeviceKind) {
            LogUtil.c("CustomTextAlertDialog", "deviceKindId: ", healthDeviceKind);
            this.b = healthDeviceKind;
            return this;
        }

        public Builder d(int i, DeviceCheckAdapter.OnItemClickListener onItemClickListener) {
            this.q = d(i);
            this.k = onItemClickListener;
            return this;
        }

        public Builder Hx_(int i, View.OnClickListener onClickListener) {
            this.m = d(i);
            this.l = onClickListener;
            return this;
        }

        public CustomDeviceSelectDialog e() {
            Drawable drawable;
            int dimensionPixelSize;
            this.d = new CustomDeviceSelectDialog(this.e, R.style.CustomDialog);
            Object systemService = this.e.getSystemService("layout_inflater");
            if (systemService instanceof LayoutInflater) {
                View inflate = ((LayoutInflater) systemService).inflate(R.layout.device_custom_select_dialog, (ViewGroup) null);
                TypedValue typedValue = new TypedValue();
                this.e.getTheme().resolveAttribute(R.attr._2131099975_res_0x7f060147, typedValue, true);
                if (typedValue.resourceId != 0) {
                    TypedArray obtainStyledAttributes = this.e.getTheme().obtainStyledAttributes(typedValue.resourceId, new int[]{R.attr._2131099820_res_0x7f0600ac, R.attr._2131099951_res_0x7f06012f, R.attr._2131100000_res_0x7f060160, R.attr._2131101300_res_0x7f060674});
                    drawable = obtainStyledAttributes.getDrawable(2);
                    TypedValue typedValue2 = new TypedValue();
                    TypedValue typedValue3 = new TypedValue();
                    obtainStyledAttributes.getValue(3, typedValue2);
                    obtainStyledAttributes.getValue(1, typedValue3);
                    dimensionPixelSize = nsn.c(this.e, (int) TypedValue.complexToFloat(typedValue2.data));
                    obtainStyledAttributes.recycle();
                } else {
                    drawable = ContextCompat.getDrawable(this.e, R.drawable._2131427507_res_0x7f0b00b3);
                    dimensionPixelSize = this.e.getResources().getDimensionPixelSize(R.dimen._2131362341_res_0x7f0a0225);
                }
                inflate.setBackground(drawable);
                Hv_(inflate, dimensionPixelSize);
                Hu_(inflate);
                Ht_(inflate);
                this.d.setContentView(inflate);
            }
            return this.d;
        }

        private void Hv_(View view, int i) {
            HealthTextView healthTextView = (HealthTextView) view.findViewById(R.id.custom_dailog_title);
            healthTextView.setTextSize(0, i);
            healthTextView.setText(this.r);
        }

        private void Ht_(View view) {
            this.i = (RelativeLayout) view.findViewById(R.id.dialog_linearlayout1);
            this.f = (LinearLayout) view.findViewById(R.id.dialog_linearlayout2);
            this.n = (HealthButton) this.i.findViewById(R.id.dialog_btn_negative);
            HealthButton healthButton = (HealthButton) this.i.findViewById(R.id.dialog_btn_positive);
            this.o = healthButton;
            healthButton.setTextAppearance(R.style.health_button_style_emphasize);
            this.o.setBackground(ContextCompat.getDrawable(this.e, R.drawable.button_background_emphasize));
            this.i.setVisibility(0);
            this.f.setVisibility(8);
            this.o.setText(this.q);
            this.o.setAllCaps(true);
            if (this.k != null) {
                this.o.setOnClickListener(new a());
            }
            this.n.setText(this.m);
            this.n.setAllCaps(true);
            if (this.l != null) {
                this.n.setOnClickListener(new d());
            }
        }

        private void Hu_(View view) {
            ArrayList<ContentValues> d2;
            if (!TextUtils.isEmpty(this.s)) {
                d2 = ceo.d().a(this.s);
            } else if (this.b == null) {
                d2 = ceo.d().f();
            } else {
                d2 = ceo.d().d(this.b);
            }
            Iterator<ContentValues> it = d2.iterator();
            while (it.hasNext()) {
                ContentValues next = it.next();
                String asString = next.getAsString("productId");
                String asString2 = next.getAsString("uniqueId");
                if (TextUtils.isEmpty(asString) || TextUtils.isEmpty(asString2)) {
                    LogUtil.h("CustomTextAlertDialog", "initDeviceList : productId or deviceIdentify is empty");
                } else {
                    dcz d3 = ResourceManager.e().d(asString);
                    if (d3 != null && !d3.n().d().trim().isEmpty()) {
                        cjv b = dbp.b(d3);
                        b.FU_(next);
                        b.a(new cke("deviceUsedTime").b(cpp.a(), next.getAsString("uniqueId")));
                        this.c.add(b);
                    }
                }
            }
            this.j = (HealthRecycleView) view.findViewById(R.id.rc_card_device_list);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this.e);
            linearLayoutManager.setOrientation(1);
            this.j.setLayoutManager(linearLayoutManager);
            Hw_(view);
            DeviceCheckAdapter deviceCheckAdapter = new DeviceCheckAdapter(this.e, this.c, new DeviceCheckAdapter.OnItemClickListener() { // from class: ckz
                @Override // com.huawei.health.device.ui.dialog.DeviceCheckAdapter.OnItemClickListener
                public final void onItemClick(cjv cjvVar) {
                    CustomDeviceSelectDialog.Builder.this.c(cjvVar);
                }
            });
            this.f2234a = deviceCheckAdapter;
            this.j.setAdapter(deviceCheckAdapter);
        }

        public /* synthetic */ void c(cjv cjvVar) {
            this.h = cjvVar;
        }

        private void Hw_(View view) {
            int height;
            int size = this.c.size();
            Context context = this.e;
            if (context == null || size <= 0 || !(context instanceof Activity) || nsn.c(this.e, (size * 90) + 120) <= (height = (int) (((Activity) context).getWindowManager().getDefaultDisplay().getHeight() * 0.8f))) {
                return;
            }
            this.g = (LinearLayout) view.findViewById(R.id.rc_card_device_linear);
            this.g.setLayoutParams(new LinearLayout.LayoutParams(-1, height - nsn.c(this.e, 120.0f)));
        }

        class a implements View.OnClickListener {
            a() {
            }

            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (Builder.this.d != null) {
                    Builder.this.d.dismiss();
                }
                if (Builder.this.k != null) {
                    Builder.this.k.onItemClick(Builder.this.h);
                } else {
                    LogUtil.c("CustomTextAlertDialog", "positiveButtonClickListener is null");
                }
                ViewClickInstrumentation.clickOnView(view);
            }
        }

        class d implements View.OnClickListener {
            d() {
            }

            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (Builder.this.d != null) {
                    Builder.this.d.dismiss();
                }
                if (Builder.this.l != null) {
                    Builder.this.l.onClick(view);
                }
                ViewClickInstrumentation.clickOnView(view);
            }
        }
    }
}
