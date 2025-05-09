package defpackage;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.Pair;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.openalliance.ad.constant.Constants;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.base.BaseDialog;
import com.huawei.ui.commonui.button.HealthButton;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.numberpicker.HealthNumberPicker;

/* loaded from: classes7.dex */
public class rvg extends BaseDialog {
    public rvg(Context context, int i) {
        super(context, i);
    }

    public static class a {
        private Context b;
        private HealthTextView c;
        private int d;
        private Handler e;
        private int i;
        private HealthNumberPicker f = null;

        /* renamed from: a, reason: collision with root package name */
        private String[] f16926a = null;

        public a(Context context, Handler handler, int i) {
            this.b = context;
            this.e = handler;
            this.d = i;
        }

        public rvg c(final int[] iArr, Integer num) {
            if (iArr == null || iArr.length == 0) {
                return null;
            }
            this.f16926a = new String[iArr.length];
            this.i = num.intValue();
            b(iArr);
            LayoutInflater layoutInflater = (LayoutInflater) this.b.getSystemService("layout_inflater");
            final rvg rvgVar = new rvg(this.b, R.style.heart_rate_dialog);
            View inflate = layoutInflater.inflate(R.layout.dialog_heart_rate_zone_setting_upper_limit, (ViewGroup) null);
            this.f = (HealthNumberPicker) inflate.findViewById(R.id.wheelviewselayout);
            this.c = (HealthTextView) inflate.findViewById(R.id.heart_picker_title);
            dRB_(iArr, inflate, rvgVar);
            ((HealthButton) inflate.findViewById(R.id.btn_ok)).setOnClickListener(new View.OnClickListener() { // from class: rvg.a.2
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    if (rvgVar != null) {
                        Message obtain = Message.obtain();
                        obtain.what = a.this.d;
                        int value = a.this.f.getValue();
                        if (value >= 0) {
                            int[] iArr2 = iArr;
                            if (value < iArr2.length) {
                                obtain.arg1 = iArr2[value];
                            }
                        }
                        a.this.e.sendMessage(obtain);
                        rvgVar.dismiss();
                    }
                    ViewClickInstrumentation.clickOnView(view);
                }
            });
            rvgVar.addContentView(inflate, new ViewGroup.LayoutParams(-1, -2));
            rvgVar.setContentView(inflate);
            Window window = rvgVar.getWindow();
            window.setGravity(80);
            WindowManager.LayoutParams attributes = window.getAttributes();
            Display defaultDisplay = ((WindowManager) this.b.getSystemService(Constants.NATIVE_WINDOW_SUB_DIR)).getDefaultDisplay();
            attributes.y = this.b.getResources().getDimensionPixelSize(R.dimen._2131362464_res_0x7f0a02a0);
            int dimensionPixelSize = this.b.getResources().getDimensionPixelSize(R.dimen._2131362466_res_0x7f0a02a2);
            int dimensionPixelSize2 = this.b.getResources().getDimensionPixelSize(R.dimen._2131362465_res_0x7f0a02a1);
            Pair<Integer, Integer> safeRegionWidth = BaseActivity.getSafeRegionWidth();
            attributes.width = (((defaultDisplay.getWidth() - dimensionPixelSize) - dimensionPixelSize2) - ((Integer) safeRegionWidth.first).intValue()) - ((Integer) safeRegionWidth.second).intValue();
            window.setAttributes(attributes);
            window.setWindowAnimations(R.style.track_dialog_anim);
            return rvgVar;
        }

        private void dRB_(int[] iArr, View view, final rvg rvgVar) {
            this.f.setDisplayedValues(this.f16926a);
            this.f.setMinValue(0);
            this.f.setMaxValue(iArr.length - 1);
            this.f.setWrapSelectorWheel(false);
            this.f.setValue(d(this.i, iArr));
            ((HealthButton) view.findViewById(R.id.btn_cancel)).setOnClickListener(new View.OnClickListener() { // from class: rvg.a.3
                @Override // android.view.View.OnClickListener
                public void onClick(View view2) {
                    rvgVar.dismiss();
                    ViewClickInstrumentation.clickOnView(view2);
                }
            });
        }

        private void b(int[] iArr) {
            if (iArr == null || this.b == null) {
                return;
            }
            for (int i = 0; i < iArr.length; i++) {
                this.f16926a[i] = this.b.getResources().getQuantityString(R.plurals._2130903303_res_0x7f030107, 1, Integer.valueOf(iArr[i]));
            }
        }

        private int d(int i, int[] iArr) {
            if (iArr == null) {
                return 2;
            }
            int length = iArr.length / 2;
            for (int i2 = 0; i2 < iArr.length; i2++) {
                if (iArr[i2] == i) {
                    return i2;
                }
            }
            return length;
        }

        public void a(String str) {
            this.c.setText(str);
        }
    }
}
