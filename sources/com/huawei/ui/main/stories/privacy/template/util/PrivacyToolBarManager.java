package com.huawei.ui.main.stories.privacy.template.util;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.core.content.ContextCompat;
import com.huawei.health.R;
import com.huawei.ui.commonui.toolbar.HealthToolBar;
import com.huawei.ui.main.R$string;

/* loaded from: classes7.dex */
public class PrivacyToolBarManager {

    /* renamed from: a, reason: collision with root package name */
    private OnStatusChangedListener f10426a;
    private HealthToolBar.OnSingleTapListener b;
    private HealthToolBar c;
    private int d;

    public interface OnStatusChangedListener {
        void onStatusChanged(int i);
    }

    public PrivacyToolBarManager(HealthToolBar healthToolBar, HealthToolBar.OnSingleTapListener onSingleTapListener) {
        this.c = healthToolBar;
        this.b = onSingleTapListener;
        b();
    }

    public void c(OnStatusChangedListener onStatusChangedListener) {
        this.f10426a = onStatusChangedListener;
    }

    public void b(int i) {
        if (this.c == null) {
            return;
        }
        OnStatusChangedListener onStatusChangedListener = this.f10426a;
        if (onStatusChangedListener != null) {
            onStatusChangedListener.onStatusChanged(i);
        }
        this.d = i;
        if (i == 1) {
            this.c.setVisibility(8);
            return;
        }
        if (i == 2) {
            this.c.setVisibility(0);
            c();
        } else if (i == 3) {
            d();
        } else {
            if (i != 4) {
                return;
            }
            a();
        }
    }

    public int e() {
        return this.d;
    }

    private void b() {
        HealthToolBar healthToolBar = this.c;
        if (healthToolBar == null || this.b == null) {
            return;
        }
        this.d = 1;
        View inflate = LayoutInflater.from(healthToolBar.getContext()).inflate(R.layout.hw_toolbar_bottomview, (ViewGroup) null);
        inflate.setBackgroundColor(ContextCompat.getColor(this.c.getContext(), R.color._2131297799_res_0x7f090607));
        this.c.cHc_(inflate);
        this.c.setOnSingleTapListener(this.b);
        this.c.setVisibility(8);
    }

    private void c() {
        HealthToolBar healthToolBar = this.c;
        if (healthToolBar == null) {
            return;
        }
        healthToolBar.setIconVisible(2, 8);
        this.c.setIcon(1, R.drawable._2131430270_res_0x7f0b0b7e);
        HealthToolBar healthToolBar2 = this.c;
        healthToolBar2.setIconTitle(1, healthToolBar2.getContext().getString(R$string.IDS_hw_show_healthdata_bloodsugar_delete));
        this.c.setIcon(3, R.drawable._2131430296_res_0x7f0b0b98);
        HealthToolBar healthToolBar3 = this.c;
        healthToolBar3.setIconTitle(3, healthToolBar3.getContext().getString(R$string.IDS_hw_show_healthdata_bloodsugar_select_all));
        this.c.setIconVisible(1, 0);
        this.c.setIconVisible(3, 0);
    }

    private void d() {
        HealthToolBar healthToolBar = this.c;
        if (healthToolBar == null) {
            return;
        }
        healthToolBar.setIconTitle(3, healthToolBar.getContext().getString(R$string.IDS_hw_show_healthdata_bloodsugar_select_all));
        this.c.setIcon(3, R.drawable._2131430296_res_0x7f0b0b98);
    }

    private void a() {
        HealthToolBar healthToolBar = this.c;
        if (healthToolBar == null) {
            return;
        }
        healthToolBar.setIconTitle(3, healthToolBar.getContext().getString(R$string.IDS_hw_show_healthdata_bloodsugar_unselected_all));
        this.c.setIcon(3, R.drawable._2131430281_res_0x7f0b0b89);
    }
}
