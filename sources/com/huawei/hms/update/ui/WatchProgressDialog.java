package com.huawei.hms.update.ui;

import android.R;
import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.LinearLayout;
import com.huawei.hms.support.log.HMSLog;
import com.huawei.hms.utils.ResourceLoaderUtil;

/* loaded from: classes9.dex */
public class WatchProgressDialog extends Dialog {

    /* renamed from: a, reason: collision with root package name */
    private final Context f6132a;

    public WatchProgressDialog(Context context) {
        super(context, ResourceLoaderUtil.getStyleId("WatchDialog"));
        this.f6132a = context;
        a();
        b();
    }

    private void a() {
        addContentView(((LayoutInflater) this.f6132a.getSystemService("layout_inflater")).inflate(ResourceLoaderUtil.getLayoutId("hms_installer_progress_dialog_watch"), (ViewGroup) null), new LinearLayout.LayoutParams(-1, -1));
    }

    private void b() {
        Window window = getWindow();
        if (window == null) {
            HMSLog.e("WatchProgressDialog", "Dialog getWindow is null");
            return;
        }
        window.setBackgroundDrawableResource(R.color.transparent);
        window.getAttributes().width = -1;
        window.getAttributes().height = -1;
        window.getAttributes().gravity = 17;
    }
}
