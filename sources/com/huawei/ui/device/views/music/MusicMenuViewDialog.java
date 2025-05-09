package com.huawei.ui.device.views.music;

import android.content.Context;
import android.text.InputFilter;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.base.BaseDialog;
import com.huawei.ui.commonui.button.HealthButton;
import com.huawei.ui.commonui.edittext.HealthErrorTipTextLayout;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Pattern;

/* loaded from: classes6.dex */
public class MusicMenuViewDialog extends BaseDialog {
    public MusicMenuViewDialog(Context context, int i) {
        super(context, i);
    }

    public static class Builder {
        private Context f;
        private View.OnClickListener h;

        /* renamed from: a, reason: collision with root package name */
        MusicMenuViewDialog f9321a = null;
        HealthButton d = null;
        EditText b = null;
        HealthTextView c = null;
        HealthErrorTipTextLayout e = null;
        private InputFilter i = new InputFilter() { // from class: com.huawei.ui.device.views.music.MusicMenuViewDialog.Builder.4
            @Override // android.text.InputFilter
            public CharSequence filter(CharSequence charSequence, int i, int i2, Spanned spanned, int i3, int i4) {
                boolean z;
                do {
                    try {
                        z = new SpannableStringBuilder(spanned).replace(i3, i4, charSequence.subSequence(i, i2)).toString().getBytes("utf-8").length > 30;
                        if (z) {
                            i2--;
                            charSequence = charSequence.subSequence(i, i2);
                        }
                    } catch (UnsupportedEncodingException unused) {
                        LogUtil.b("MusicMenuViewDialog", "filter UnsupportedEncodingException");
                    }
                } while (z);
                return charSequence;
            }
        };
        private InputFilter j = new InputFilter() { // from class: com.huawei.ui.device.views.music.MusicMenuViewDialog.Builder.2
            Pattern c = Pattern.compile("[🀀-🏿]|[🐀-\u1f7ff]|[☀-⟿]", 66);

            @Override // android.text.InputFilter
            public CharSequence filter(CharSequence charSequence, int i, int i2, Spanned spanned, int i3, int i4) {
                if (!this.c.matcher(charSequence).find()) {
                    return null;
                }
                LogUtil.c("MusicMenuViewDialog", "can not insert emoji");
                return "";
            }
        };

        public Builder(Context context) {
            this.f = context;
        }

        public Builder cVB_(View.OnClickListener onClickListener) {
            this.h = onClickListener;
            return this;
        }

        public HealthTextView d() {
            return this.c;
        }

        public EditText cVA_() {
            return this.b;
        }

        public HealthErrorTipTextLayout a() {
            return this.e;
        }

        public MusicMenuViewDialog b() {
            Object systemService = this.f.getSystemService("layout_inflater");
            if (!(systemService instanceof LayoutInflater)) {
                LogUtil.a("MusicMenuViewDialog", "object is not instanceof Inflater");
                return null;
            }
            this.f9321a = new MusicMenuViewDialog(this.f, R.style.TrackDialog);
            View inflate = ((LayoutInflater) systemService).inflate(R.layout.music_create_menu_dialog, (ViewGroup) null);
            this.e = (HealthErrorTipTextLayout) inflate.findViewById(R.id.error_tip_text_layout);
            this.b = (EditText) inflate.findViewById(R.id.edit);
            this.c = (HealthTextView) inflate.findViewById(R.id.dialog_title);
            this.b.setHint(R.string._2130843221_res_0x7f021655);
            this.b.setFilters(new InputFilter[]{this.i});
            cVz_(this.b);
            ((HealthButton) inflate.findViewById(R.id.cancel)).setOnClickListener(new d());
            HealthButton healthButton = (HealthButton) inflate.findViewById(R.id.ok);
            this.d = healthButton;
            healthButton.setEnabled(false);
            this.d.setOnClickListener(new c());
            this.f9321a.addContentView(inflate, new ViewGroup.LayoutParams(-1, -2));
            this.f9321a.setContentView(inflate);
            return this.f9321a;
        }

        public void c(boolean z) {
            HealthButton healthButton = this.d;
            if (healthButton != null) {
                healthButton.setEnabled(z);
            }
        }

        class c implements View.OnClickListener {
            c() {
            }

            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (Builder.this.f9321a != null) {
                    Builder.this.f9321a.dismiss();
                }
                if (Builder.this.h != null) {
                    Builder.this.h.onClick(view);
                } else {
                    LogUtil.c("MusicMenuViewDialog", "mPositiveButtonClickListener is null in CustomViewDialog");
                }
                ViewClickInstrumentation.clickOnView(view);
            }
        }

        class d implements View.OnClickListener {
            d() {
            }

            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (Builder.this.f9321a != null) {
                    Builder.this.f9321a.dismiss();
                }
                ViewClickInstrumentation.clickOnView(view);
            }
        }

        private void cVz_(EditText editText) {
            ArrayList arrayList = new ArrayList(Arrays.asList(editText.getFilters()));
            arrayList.add(this.j);
            editText.setFilters((InputFilter[]) arrayList.toArray(new InputFilter[arrayList.size()]));
        }
    }

    @Override // com.huawei.ui.commonui.base.BaseDialog, android.app.Dialog
    public void show() {
        Window window = getWindow();
        window.setGravity(80);
        window.setDimAmount(0.2f);
        WindowManager.LayoutParams attributes = window.getAttributes();
        window.getDecorView().setPadding(0, 0, 0, 0);
        attributes.width = -1;
        attributes.height = -2;
        window.setAttributes(attributes);
        window.setWindowAnimations(R.style.track_dialog_anim);
        super.show();
    }
}
