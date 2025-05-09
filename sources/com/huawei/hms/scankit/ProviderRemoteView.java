package com.huawei.hms.scankit;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import com.huawei.health.R;

/* loaded from: classes9.dex */
public class ProviderRemoteView extends LinearLayout {
    public ProviderRemoteView(Context context, boolean z, boolean z2) {
        super(context);
        a(z, z2);
    }

    private void a(boolean z, boolean z2) {
        if (z) {
            LayoutInflater.from(getContext()).inflate(R.layout.scankit_zxl_capture_customed, this);
        } else if (z2) {
            LayoutInflater.from(getContext()).inflate(R.layout.scankit_zxl_capture, this);
        } else {
            LayoutInflater.from(getContext()).inflate(R.layout.scankit_zxl_capture_new, this);
        }
    }

    public ProviderRemoteView(Context context, boolean z) {
        this(context, z, true);
    }
}
