package com.huawei.ui.commonui.dialog;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.core.content.ContextCompat;
import com.huawei.health.R;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.R$color;
import com.huawei.ui.commonui.base.BaseDialog;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.progressbar.HealthProgressBar;

/* loaded from: classes6.dex */
public class CommonDialog21 extends BaseDialog {

    /* renamed from: a, reason: collision with root package name */
    private Context f8794a;
    private HealthProgressBar c;
    private HealthTextView d;

    public CommonDialog21(Context context) {
        super(context);
        this.f8794a = context.getApplicationContext();
    }

    public CommonDialog21(Context context, int i) {
        super(context, i);
        this.f8794a = context.getApplicationContext();
    }

    @Override // android.app.Dialog
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
    }

    public static CommonDialog21 a(Context context) {
        return e(context, R.style.common_dialog21);
    }

    public static CommonDialog21 d(Context context, int i) {
        return e(context, i);
    }

    private static CommonDialog21 e(Context context, int i) {
        CommonDialog21 commonDialog21 = new CommonDialog21(context, i);
        View inflate = ((LayoutInflater) context.getSystemService("layout_inflater")).inflate(R.layout.commonui_loading21, (ViewGroup) null);
        commonDialog21.d = (HealthTextView) inflate.findViewById(R.id.BasicInfo_check_textView);
        HealthProgressBar healthProgressBar = (HealthProgressBar) inflate.findViewById(R.id.BasicInfo_check_img);
        commonDialog21.c = healthProgressBar;
        healthProgressBar.setLayerType(1, null);
        commonDialog21.c.setVisibility(0);
        commonDialog21.addContentView(inflate, new ViewGroup.LayoutParams(-1, -2));
        return commonDialog21;
    }

    public void e(String str) {
        if (this.d == null || str == null) {
            return;
        }
        int color = ContextCompat.getColor(this.f8794a, R$color.textColorPrimary);
        float dimension = this.f8794a.getResources().getDimension(R.dimen._2131362372_res_0x7f0a0244);
        this.d.setTextColor(color);
        this.d.setTextSize(0, dimension);
        this.d.setText(str);
    }

    public void a() {
        show();
    }

    @Override // com.huawei.ui.commonui.base.BaseDialog, android.app.Dialog, android.content.DialogInterface
    public void dismiss() {
        LogUtil.a("CommonDialog21", "enter dismiss");
        super.dismiss();
        if (this.c != null) {
            this.c = null;
        }
    }
}
