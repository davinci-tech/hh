package com.huawei.openalliance.ad.activity;

import android.app.ActionBar;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toolbar;
import com.huawei.health.R;
import com.huawei.openalliance.ad.bz;
import com.huawei.openalliance.ad.ho;
import com.huawei.openalliance.ad.utils.cz;

/* loaded from: classes5.dex */
public abstract class c extends e {
    private boolean e() {
        return true;
    }

    protected int c() {
        return 0;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.huawei.openalliance.ad.activity.e, com.huawei.openalliance.ad.activity.f, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        d();
    }

    protected String b() {
        return "";
    }

    private void d() {
        ActionBar actionBar = getActionBar();
        if (actionBar == null || !e()) {
            return;
        }
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        if (c() == 0 && cz.b(b())) {
            return;
        }
        if (bz.b(this)) {
            if (cz.b(b())) {
                actionBar.setTitle(c());
                return;
            } else {
                actionBar.setTitle(b());
                return;
            }
        }
        View inflate = getLayoutInflater().inflate(R.layout.action_bar_title_layout, (ViewGroup) null);
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setCustomView(inflate);
        a(inflate);
        if (cz.b(b())) {
            ((TextView) findViewById(R.id.custom_action_bar_title)).setText(c());
        } else {
            ((TextView) findViewById(R.id.custom_action_bar_title)).setText(b());
        }
    }

    private void a(final View view) {
        try {
            FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, -2);
            final Toolbar toolbar = (Toolbar) view.getParent();
            if (toolbar != null) {
                toolbar.setLayoutParams(layoutParams);
            }
            view.post(new Runnable() { // from class: com.huawei.openalliance.ad.activity.c.1
                @Override // java.lang.Runnable
                public void run() {
                    try {
                        TypedValue typedValue = new TypedValue();
                        int max = Math.max(view.getHeight(), c.this.getTheme().resolveAttribute(android.R.attr.actionBarSize, typedValue, true) ? TypedValue.complexToDimensionPixelSize(typedValue.data, c.this.getResources().getDisplayMetrics()) : 0);
                        if (max > 0) {
                            toolbar.setMinimumHeight(max);
                        }
                    } catch (Throwable unused) {
                        ho.c("BasePureWebActivity", "set toolBar min height error.");
                    }
                }
            });
        } catch (Throwable unused) {
            ho.c("BasePureWebActivity", "setCustomToolBar error.");
        }
    }
}
