package com.huawei.health.knit.section.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import java.util.Map;

/* loaded from: classes3.dex */
public class BaseBiItemView extends FrameLayout {
    protected Context c;
    protected Map<String, Object> d;

    public void e() {
    }

    public BaseBiItemView(Context context, int i) {
        super(context);
        this.c = context;
        c(context, i);
    }

    private void c(Context context, int i) {
        LayoutInflater.from(context).inflate(i, (ViewGroup) this, true);
    }

    public void b(Map<String, Object> map) {
        this.d = map;
    }
}
