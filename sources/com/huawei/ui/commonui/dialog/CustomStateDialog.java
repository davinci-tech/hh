package com.huawei.ui.commonui.dialog;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.R$string;
import com.huawei.ui.commonui.R$styleable;
import com.huawei.ui.commonui.base.BaseDialog;
import com.huawei.ui.commonui.button.HealthButton;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import defpackage.msc;
import defpackage.msj;
import health.compact.a.EzPluginManager;
import health.compact.a.utils.StringUtils;
import java.io.File;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes6.dex */
public class CustomStateDialog extends BaseDialog {
    private CustomStateDialog(Context context, int i) {
        super(context, i);
    }

    public static class Builder {

        /* renamed from: a, reason: collision with root package name */
        private List<String> f8803a;
        private List<List<String>> b;
        private Context c;
        private HealthTextView d;
        private String e;
        private int f;
        private int g = 0;
        private CustomStateDialog h;
        private ImageView i;
        private HealthButton j;
        private List<String> k;
        private HealthTextView n;
        private HealthButton o;

        public Builder(Context context) {
            this.c = context;
        }

        public Builder d(List<List<String>> list) {
            this.b = list;
            return this;
        }

        public Builder a(List<String> list) {
            this.k = list;
            return this;
        }

        public Builder b(List<String> list) {
            this.f8803a = list;
            return this;
        }

        public Builder e(String str) {
            this.e = str;
            return this;
        }

        public Builder c(int i) {
            this.f = i;
            return this;
        }

        public CustomStateDialog a() {
            LogUtil.a("CustomStateDialog", "enter create dialog");
            CustomStateDialog customStateDialog = new CustomStateDialog(this.c, R.style.CustomDialog);
            this.h = customStateDialog;
            if (this.f8803a == null) {
                return customStateDialog;
            }
            View inflate = LayoutInflater.from(this.c).inflate(R.layout.commonui_custom_state_dialog, (ViewGroup) null);
            TypedValue typedValue = new TypedValue();
            this.c.getTheme().resolveAttribute(R.attr._2131099975_res_0x7f060147, typedValue, true);
            TypedArray obtainStyledAttributes = this.c.getTheme().obtainStyledAttributes(typedValue.resourceId, R$styleable.customDialogDefinition);
            Drawable drawable = obtainStyledAttributes.getDrawable(R$styleable.customDialogDefinition_dialogBackground);
            obtainStyledAttributes.recycle();
            inflate.setBackground(drawable);
            this.h.addContentView(inflate, new ViewGroup.LayoutParams(-2, -2));
            this.i = (ImageView) inflate.findViewById(R.id.custom_state_dialog_image);
            this.n = (HealthTextView) inflate.findViewById(R.id.custom_state_dialog_title);
            this.d = (HealthTextView) inflate.findViewById(R.id.custom_state_dialog_description);
            b(this.g);
            a(this.g);
            e(this.g);
            cyJ_(inflate);
            this.h.setContentView(inflate);
            return this.h;
        }

        private void cyJ_(View view) {
            HealthButton healthButton = (HealthButton) view.findViewById(R.id.status_dialog_btn_negative);
            this.j = healthButton;
            healthButton.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.ui.commonui.dialog.CustomStateDialog.Builder.4
                @Override // android.view.View.OnClickListener
                public void onClick(View view2) {
                    Builder.this.e();
                    ViewClickInstrumentation.clickOnView(view2);
                }
            });
            HealthButton healthButton2 = (HealthButton) view.findViewById(R.id.status_dialog_btn_positive);
            this.o = healthButton2;
            healthButton2.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.ui.commonui.dialog.CustomStateDialog.Builder.1
                @Override // android.view.View.OnClickListener
                public void onClick(View view2) {
                    Builder.this.c();
                    ViewClickInstrumentation.clickOnView(view2);
                }
            });
            if (this.f8803a.size() == 1) {
                d();
            } else {
                b();
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void e() {
            if (this.h == null) {
                return;
            }
            int i = this.g - 1;
            this.g = i;
            LogUtil.a("CustomStateDialog", "on click negativeButton button, mPageIndex is: ", Integer.valueOf(i));
            int i2 = this.g;
            if (i2 < 0) {
                this.h.dismiss();
                return;
            }
            d(i2);
            if (this.g == 0) {
                this.j.setText(R$string.IDS_device_user_guide_cancel_btn);
            } else {
                this.j.setText(R$string.IDS_device_user_guide_previous_btn);
            }
            if (this.g == this.f8803a.size() - 2) {
                this.o.setText(R$string.IDS_device_user_guide_next_btn);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void c() {
            int i = this.g + 1;
            this.g = i;
            LogUtil.a("CustomStateDialog", "on click positive button, mPageIndex is: ", Integer.valueOf(i));
            int size = this.f8803a.size();
            if (size > 1 && this.g == 1) {
                this.j.setText(R$string.IDS_device_user_guide_previous_btn);
            }
            int i2 = this.g;
            if (i2 < size) {
                d(i2);
            }
            if (this.g == size - 1) {
                LogUtil.a("CustomStateDialog", "last page");
                this.o.setText(R$string.IDS_device_user_guide_complete_btn);
            }
            if (this.g >= size) {
                LogUtil.a("CustomStateDialog", "close dialog");
                CustomStateDialog customStateDialog = this.h;
                if (customStateDialog != null) {
                    customStateDialog.dismiss();
                }
            }
        }

        private void d(int i) {
            b(i);
            a(i);
            e(i);
        }

        private void d() {
            this.j.setVisibility(0);
            this.j.setText(R$string.IDS_common_notification_know_tips);
        }

        private void b() {
            this.j.setVisibility(0);
            this.o.setVisibility(0);
            this.j.setText(R$string.IDS_device_user_guide_cancel_btn);
            this.o.setText(R$string.IDS_device_user_guide_next_btn);
        }

        private void b(int i) {
            List<String> list = this.k;
            if (list == null || list.isEmpty() || i >= this.k.size()) {
                return;
            }
            if (StringUtils.g(this.k.get(i))) {
                this.n.setVisibility(8);
            } else {
                this.n.setVisibility(0);
                this.n.setText(this.k.get(i));
            }
        }

        private void a(int i) {
            List<String> list = this.f8803a;
            if (list == null || list.isEmpty() || i >= this.f8803a.size()) {
                return;
            }
            this.d.setText(this.f8803a.get(i));
        }

        private void e(int i) {
            LogUtil.a("CustomStateDialog", "enter setmImageView");
            List<List<String>> list = this.b;
            if (list == null || list.isEmpty()) {
                LogUtil.a("CustomStateDialog", "mAnimationDrawablesList is null");
                return;
            }
            if (i >= this.b.size()) {
                return;
            }
            msc c = EzPluginManager.a().c(this.e);
            if (c == null) {
                LogUtil.a("CustomStateDialog", "ezPluginInfo is null");
                return;
            }
            List<String> list2 = this.b.get(i);
            if (list2 == null || list2.isEmpty()) {
                LogUtil.a("CustomStateDialog", "image name list is null");
                return;
            }
            if (list2.size() == 1) {
                LogUtil.a("CustomStateDialog", "image name list size is 1");
                Bitmap cyI_ = cyI_(c, list2.get(0));
                if (cyI_ == null) {
                    LogUtil.a("CustomStateDialog", "don't have image bitmap");
                    return;
                } else {
                    this.i.setImageDrawable(new BitmapDrawable(cyI_));
                    return;
                }
            }
            int i2 = this.f;
            if (i2 == 0) {
                i2 = 200;
            }
            AnimationDrawable animationDrawable = new AnimationDrawable();
            Iterator<String> it = list2.iterator();
            while (it.hasNext()) {
                Bitmap cyI_2 = cyI_(c, it.next());
                if (cyI_2 != null) {
                    animationDrawable.addFrame(new BitmapDrawable(cyI_2), i2);
                }
            }
            this.i.setImageDrawable(animationDrawable);
            animationDrawable.setOneShot(false);
            animationDrawable.start();
        }

        private Bitmap cyI_(msc mscVar, String str) {
            return EzPluginManager.a().coo_(msj.a().d(mscVar.a()) + File.separator + mscVar.a() + File.separator + "img" + File.separator + str + ".png");
        }
    }
}
