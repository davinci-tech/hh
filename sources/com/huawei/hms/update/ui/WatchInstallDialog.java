package com.huawei.hms.update.ui;

import android.R;
import android.app.Dialog;
import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.huawei.hms.support.log.HMSLog;
import com.huawei.hms.update.ui.ButtonConfig;
import com.huawei.hms.utils.ResourceLoaderUtil;
import com.huawei.openalliance.ad.constant.JsbMapKeyNames;

/* loaded from: classes9.dex */
public class WatchInstallDialog extends Dialog {

    /* renamed from: a, reason: collision with root package name */
    private final Context f6131a;
    private TextView b;
    private TextView c;
    private Button d;
    private Button e;

    public WatchInstallDialog(Context context) {
        super(context, ResourceLoaderUtil.getStyleId("WatchDialog"));
        this.f6131a = context;
        b();
    }

    private void a() {
        addContentView(((LayoutInflater) this.f6131a.getSystemService("layout_inflater")).inflate(ResourceLoaderUtil.getLayoutId("hms_installer_update_dialog_watch"), (ViewGroup) null), new LinearLayout.LayoutParams(-1, -1));
    }

    private void b() {
        a();
        d();
        c();
    }

    private void c() {
        this.b = (TextView) findViewById(ResourceLoaderUtil.getIdId("title"));
        this.c = (TextView) findViewById(ResourceLoaderUtil.getIdId("message"));
        this.d = (Button) findViewById(ResourceLoaderUtil.getIdId(JsbMapKeyNames.H5_TEXT_DOWNLOAD_INSTALL));
        this.e = (Button) findViewById(ResourceLoaderUtil.getIdId("cancel"));
        ButtonConfig createWatch = ButtonConfig.createWatch(this.f6131a);
        int color = this.f6131a.getResources().getColor(ResourceLoaderUtil.getColorId("hw_cloud_dialog_button_text_color"));
        a(this.d, createWatch, color);
        a(this.e, createWatch, color);
    }

    private void d() {
        Window window = getWindow();
        if (window == null) {
            HMSLog.e("WatchInstallDialog", "getWindow is null");
            return;
        }
        window.setBackgroundDrawableResource(R.color.transparent);
        WindowManager.LayoutParams attributes = window.getAttributes();
        attributes.width = -1;
        attributes.height = -1;
        attributes.gravity = 17;
    }

    public void setCancelResourceId(int i) {
        this.e.setText(i);
    }

    public void setCancelText(CharSequence charSequence) {
        this.e.setText(charSequence);
    }

    public void setInstallResourceId(int i) {
        this.d.setText(i);
    }

    public void setInstallText(CharSequence charSequence) {
        this.d.setText(charSequence);
    }

    public void setMessage(CharSequence charSequence) {
        this.c.setText(charSequence);
    }

    public void setOnCancelClick(View.OnClickListener onClickListener) {
        this.e.setOnClickListener(onClickListener);
    }

    public void setOnInstallClick(View.OnClickListener onClickListener) {
        this.d.setOnClickListener(onClickListener);
    }

    @Override // android.app.Dialog
    public void setTitle(int i) {
        this.b.setVisibility(0);
        this.b.setText(i);
    }

    @Override // android.app.Dialog
    public void setTitle(CharSequence charSequence) {
        this.b.setVisibility(0);
        this.b.setText(charSequence);
    }

    private void a(Button button, ButtonConfig buttonConfig, int i) {
        if (button == null) {
            return;
        }
        String str = Build.MANUFACTURER;
        if (buttonConfig.f == ButtonConfig.Level.NORMAL && (i > 0 || (str != null && str.equals("HUAWEI")))) {
            buttonConfig.c = i;
            buttonConfig.d = i;
        }
        HwDialogUtil.a(button, buttonConfig.f6083a, buttonConfig.b);
        HwDialogUtil.b(button, buttonConfig.c, buttonConfig.d);
    }
}
