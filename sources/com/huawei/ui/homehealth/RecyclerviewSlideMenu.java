package com.huawei.ui.homehealth;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import com.huawei.health.R;

/* loaded from: classes6.dex */
public class RecyclerviewSlideMenu extends LinearLayout {
    private Context b;

    public RecyclerviewSlideMenu(Context context) {
        super(context);
        this.b = context;
        d();
    }

    private void d() {
        Object systemService = this.b.getSystemService("layout_inflater");
        if (systemService instanceof LayoutInflater) {
            ((LayoutInflater) systemService).inflate(R.layout.recyclerview_slide_menu_layout, this);
        }
    }
}
