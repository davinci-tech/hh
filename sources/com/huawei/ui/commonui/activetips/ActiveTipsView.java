package com.huawei.ui.commonui.activetips;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.huawei.health.R;
import defpackage.nsk;

/* loaded from: classes6.dex */
public class ActiveTipsView extends RelativeLayout {

    /* renamed from: a, reason: collision with root package name */
    private View f8755a;
    private Context b;
    private TextView c;
    private TextView e;

    public ActiveTipsView(Context context) {
        super(context);
        this.b = context;
        a();
    }

    public ActiveTipsView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.b = context;
        a();
    }

    public ActiveTipsView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.b = context;
        a();
    }

    private void a() {
        View inflate = LayoutInflater.from(this.b).inflate(R.layout.active_tips, this);
        this.f8755a = inflate;
        if (inflate == null) {
            return;
        }
        this.c = (TextView) inflate.findViewById(R.id.active_tips_text);
        this.e = (TextView) this.f8755a.findViewById(R.id.goto_active_center);
        Typeface cKN_ = nsk.cKN_();
        this.c.setTypeface(cKN_);
        this.e.setTypeface(cKN_);
    }

    public void setActiveBtText(String str) {
        this.e.setText(str);
    }

    public void setTipsText(String str) {
        this.c.setText(str);
    }

    public void setClickActiveListener(View.OnClickListener onClickListener) {
        this.e.setOnClickListener(onClickListener);
    }

    public void setClickBannerListener(View.OnClickListener onClickListener) {
        this.f8755a.setOnClickListener(onClickListener);
    }
}
