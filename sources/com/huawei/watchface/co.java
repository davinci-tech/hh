package com.huawei.watchface;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.core.content.ContextCompat;
import com.huawei.health.R;
import com.huawei.uikit.hwprogressbar.widget.HwProgressBar;
import com.huawei.watchface.utils.HwLog;

/* loaded from: classes7.dex */
public class co extends ad {

    /* renamed from: a, reason: collision with root package name */
    private static final String f10972a = "co";
    private Context b;
    private HwProgressBar c;
    private TextView d;

    public co(Context context, int i) {
        super(context, i);
        ad.a(context, this);
        this.b = context.getApplicationContext();
    }

    @Override // android.app.Dialog
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
    }

    public static co b(Context context) {
        co coVar = new co(context, R.style.watch_face_style_saving_dialog);
        View inflate = ((LayoutInflater) context.getSystemService("layout_inflater")).inflate(R$layout.watchface_dialog_saving, (ViewGroup) null);
        coVar.d = (TextView) inflate.findViewById(R$id.tv_basic_info_check);
        HwProgressBar hwProgressBar = (HwProgressBar) inflate.findViewById(R$id.pb_basic_info);
        coVar.c = hwProgressBar;
        hwProgressBar.setLayerType(1, null);
        coVar.c.setVisibility(0);
        coVar.addContentView(inflate, new ViewGroup.LayoutParams(-1, -2));
        return coVar;
    }

    public void a(String str) {
        if (this.d == null || str == null) {
            return;
        }
        int color = ContextCompat.getColor(this.b, R$color.aar_textColorPrimary);
        float dimension = this.b.getResources().getDimension(R$dimen.dp_15);
        this.d.setTextColor(color);
        this.d.setTextSize(0, dimension);
        this.d.setText(str);
    }

    public void c() {
        show();
    }

    @Override // com.huawei.watchface.ad, android.app.Dialog, android.content.DialogInterface
    public void dismiss() {
        ad.a(this);
        HwLog.i(f10972a, "dismiss() enter.");
        super.dismiss();
        if (this.c != null) {
            this.c = null;
        }
    }
}
