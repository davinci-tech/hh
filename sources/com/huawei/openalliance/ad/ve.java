package com.huawei.openalliance.ad;

import android.widget.CompoundButton;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;

/* loaded from: classes9.dex */
public class ve implements CompoundButton.OnCheckedChangeListener {

    /* renamed from: a, reason: collision with root package name */
    private CompoundButton.OnCheckedChangeListener f7771a;
    private boolean b = false;

    @Override // android.widget.CompoundButton.OnCheckedChangeListener
    public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
        if (this.b) {
            this.f7771a.onCheckedChanged(compoundButton, z);
        } else {
            ho.b("OAIDOnCheckedChangeListener", "not click able");
        }
        ViewClickInstrumentation.clickOnView(compoundButton);
    }

    public void a(boolean z) {
        this.b = z;
    }

    public ve(CompoundButton.OnCheckedChangeListener onCheckedChangeListener) {
        this.f7771a = onCheckedChangeListener;
    }
}
