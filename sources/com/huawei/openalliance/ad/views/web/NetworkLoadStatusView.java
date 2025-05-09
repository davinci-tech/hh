package com.huawei.openalliance.ad.views.web;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.openalliance.ad.ho;
import com.huawei.openalliance.ad.utils.x;

/* loaded from: classes9.dex */
public class NetworkLoadStatusView extends RelativeLayout {

    /* renamed from: a, reason: collision with root package name */
    String f8179a;
    public View.OnClickListener b;
    private int c;
    private a d;
    private ImageView e;
    private TextView f;
    private Button g;

    public interface a {
        void onClick(View view);
    }

    public void setState(int i) {
        int i2;
        ho.a("NetworkLoadStatusView", "setState:%d", Integer.valueOf(i));
        this.c = i;
        if (i == -2) {
            c();
        } else {
            if (i != -1) {
                if (i != 0) {
                    return;
                }
                i2 = 0;
                setChildViewVisibility(i2);
            }
            b();
        }
        i2 = 8;
        setChildViewVisibility(i2);
    }

    public void setOnEmptyClickListener(a aVar) {
        this.d = aVar;
    }

    public void setErrorText(String str) {
        this.f8179a = str;
    }

    public int getCurrentState() {
        return this.c;
    }

    private void setChildViewVisibility(int i) {
        int childCount = getChildCount();
        for (int i2 = 0; i2 < childCount; i2++) {
            View childAt = getChildAt(i2);
            if (childAt.getId() == R.id.status_layout_main) {
                childAt.setVisibility(i == 0 ? 8 : 0);
            } else {
                childAt.setVisibility(i);
            }
        }
    }

    private void c() {
        ho.a("NetworkLoadStatusView", "displayNotNetwork");
        this.c = -2;
        this.e.setVisibility(0);
        this.f.setVisibility(0);
        this.g.setVisibility(0);
        this.g.setOnClickListener(this.b);
    }

    private void b() {
        ho.a("NetworkLoadStatusView", "displayError");
        this.c = -1;
        this.e.setVisibility(0);
        this.f.setVisibility(0);
        this.f.setText(this.f8179a);
        this.g.setVisibility(8);
    }

    private void a() {
        ImageView imageView;
        int i;
        if (getChildCount() > 1) {
            throw new IllegalStateException("StatusView can host only one direct child");
        }
        View inflate = LayoutInflater.from(getContext()).inflate(R.layout.webview_status_view, this);
        this.e = (ImageView) inflate.findViewById(R.id.nonwifi);
        if (x.c()) {
            imageView = this.e;
            i = R.drawable.opendevice_ic_wlan_emui10;
        } else {
            imageView = this.e;
            i = R.drawable.opendevice_ic_wlan;
        }
        imageView.setImageResource(i);
        this.f = (TextView) inflate.findViewById(R.id.network_tip);
        this.g = (Button) inflate.findViewById(R.id.privacy_set_network);
        inflate.setOnClickListener(this.b);
    }

    public NetworkLoadStatusView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.c = 1;
        this.b = new View.OnClickListener() { // from class: com.huawei.openalliance.ad.views.web.NetworkLoadStatusView.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (NetworkLoadStatusView.this.d == null) {
                    ViewClickInstrumentation.clickOnView(view);
                } else {
                    NetworkLoadStatusView.this.d.onClick(view);
                    ViewClickInstrumentation.clickOnView(view);
                }
            }
        };
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, new int[]{R.attr._2131100892_res_0x7f0604dc, R.attr._2131101089_res_0x7f0605a1, R.attr._2131101384_res_0x7f0606c8});
        try {
            this.f8179a = obtainStyledAttributes.getString(0);
            obtainStyledAttributes.recycle();
            a();
        } catch (Throwable th) {
            obtainStyledAttributes.recycle();
            throw th;
        }
    }

    public NetworkLoadStatusView(Context context) {
        super(context);
        this.c = 1;
        this.b = new View.OnClickListener() { // from class: com.huawei.openalliance.ad.views.web.NetworkLoadStatusView.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (NetworkLoadStatusView.this.d == null) {
                    ViewClickInstrumentation.clickOnView(view);
                } else {
                    NetworkLoadStatusView.this.d.onClick(view);
                    ViewClickInstrumentation.clickOnView(view);
                }
            }
        };
        a();
    }
}
