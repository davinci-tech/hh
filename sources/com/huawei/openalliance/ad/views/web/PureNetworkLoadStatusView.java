package com.huawei.openalliance.ad.views.web;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.openalliance.ad.ho;
import com.huawei.openalliance.ad.utils.x;

/* loaded from: classes5.dex */
public class PureNetworkLoadStatusView extends RelativeLayout {

    /* renamed from: a, reason: collision with root package name */
    String f8181a;
    public View.OnClickListener b;
    private int c;
    private b d;
    private ImageView e;
    private TextView f;
    private Button g;
    private boolean h;
    private boolean i;
    private a j;
    private Integer k;
    private Integer l;

    public interface a {
        void a(PureNetworkLoadStatusView pureNetworkLoadStatusView);
    }

    public interface b {
        void a(View view);
    }

    public void setState(int i) {
        int i2;
        ho.a("PureNetworkLoadStatusView", "setState:%s", Integer.valueOf(i));
        this.c = i;
        if (i == -2) {
            c();
        } else {
            if (i != -1) {
                if (i == 0 || i == 1) {
                    i2 = 0;
                    setChildViewVisibility(i2);
                }
                return;
            }
            b();
        }
        i2 = 8;
        setChildViewVisibility(i2);
    }

    public void setOnEmptyClickListener(b bVar) {
        this.d = bVar;
    }

    public void setOnConfigurationChangedListener(a aVar) {
        this.j = aVar;
    }

    public void setErrorText(String str) {
        this.f8181a = str;
    }

    @Override // android.view.View
    protected void onConfigurationChanged(Configuration configuration) {
        ho.a("PureNetworkLoadStatusView", "onConfigurationChanged");
        if (this.c == 0) {
            return;
        }
        removeAllViews();
        a();
        a aVar = this.j;
        if (aVar != null) {
            aVar.a(this);
        }
        setState(this.c);
    }

    public int getCurrentState() {
        return this.c;
    }

    private void setIconRes(boolean z) {
        ImageView imageView;
        int i;
        if (this.h) {
            this.e.setImageResource(R.drawable._2131428578_res_0x7f0b04e2);
            return;
        }
        if (x.c()) {
            imageView = this.e;
            i = z ? R.drawable.opendevice_ic_search_network_emui10 : R.drawable.opendevice_ic_loading_failed_emui10;
        } else {
            imageView = this.e;
            i = z ? R.drawable.opendevice_ic_search_network : R.drawable.opendevice_ic_loading_failed;
        }
        imageView.setImageResource(i);
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

    private void setButtonOnDeviceReDraw(boolean z) {
        float f;
        int i;
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) this.g.getLayoutParams();
        if (this.i && z) {
            f = 0.5f;
        } else {
            if (this.h) {
                i = -2;
                layoutParams.width = i;
                this.g.setLayoutParams(layoutParams);
            }
            f = 0.33f;
        }
        layoutParams.weight = f;
        i = 0;
        layoutParams.width = i;
        this.g.setLayoutParams(layoutParams);
    }

    private void c() {
        Button button;
        ho.a("PureNetworkLoadStatusView", "displayNotNetwork");
        this.c = -2;
        setIconRes(true);
        int i = 0;
        this.e.setVisibility(0);
        this.f.setText(this.f8181a);
        this.f.setVisibility(0);
        if (this.h) {
            button = this.g;
            i = 8;
        } else {
            this.g.setOnClickListener(this.b);
            this.g.requestFocus();
            button = this.g;
        }
        button.setVisibility(i);
    }

    private int c(View view) {
        if (this.l == null) {
            this.l = Integer.valueOf(a(view, 0.5f));
        }
        return this.l.intValue();
    }

    private void b() {
        ho.a("PureNetworkLoadStatusView", "displayError");
        this.c = -1;
        setIconRes(false);
        this.e.setVisibility(0);
        this.f.setText(getResources().getString(this.h ? R.string._2130851111_res_0x7f023527 : R.string._2130851120_res_0x7f023530));
        this.f.setVisibility(0);
        this.g.setVisibility(8);
    }

    private int b(View view) {
        if (this.k == null) {
            this.k = Integer.valueOf(a(view, 0.4f));
        }
        return this.k.intValue();
    }

    private void a(View view, boolean z) {
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) this.e.getLayoutParams();
        layoutParams.addRule(10);
        layoutParams.topMargin = z ? b(view) : c(view);
        this.e.setLayoutParams(layoutParams);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code restructure failed: missing block: B:4:0x0011, code lost:
    
        if (r2.i == false) goto L7;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void a(android.view.View r3) {
        /*
            r2 = this;
            android.content.res.Resources r0 = r2.getResources()
            android.content.res.Configuration r0 = r0.getConfiguration()
            int r0 = r0.orientation
            r1 = 1
            if (r0 != r1) goto Le
            goto L13
        Le:
            boolean r0 = r2.i
            r1 = 0
            if (r0 != 0) goto L16
        L13:
            r2.a(r3, r1)
        L16:
            r2.setButtonOnDeviceReDraw(r1)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.openalliance.ad.views.web.PureNetworkLoadStatusView.a(android.view.View):void");
    }

    private void a() {
        ho.a("PureNetworkLoadStatusView", "initView");
        if (getChildCount() > 1) {
            throw new IllegalStateException("StatusView can host only one direct child");
        }
        Context context = getContext();
        boolean o = x.o(context);
        this.i = o;
        this.h = !o && x.j(context);
        final View inflate = LayoutInflater.from(context).inflate(R.layout.pure_webview_status_view, this);
        this.e = (ImageView) inflate.findViewById(R.id.error_icon);
        this.f = (TextView) inflate.findViewById(R.id.error_msg);
        this.g = (Button) inflate.findViewById(R.id.privacy_set_network);
        inflate.setOnClickListener(this.b);
        inflate.post(new Runnable() { // from class: com.huawei.openalliance.ad.views.web.PureNetworkLoadStatusView.1
            @Override // java.lang.Runnable
            public void run() {
                PureNetworkLoadStatusView.this.a(inflate);
            }
        });
    }

    private int a(View view, float f) {
        int height = ((((int) (r0.heightPixels * f)) - getContext().getResources().getDisplayMetrics().heightPixels) + view.getHeight()) - (this.e.getLayoutParams().height / 2);
        ho.a("PureNetworkLoadStatusView", "topMargin:%s", Integer.valueOf(height));
        return height;
    }

    public PureNetworkLoadStatusView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.c = 1;
        this.b = new View.OnClickListener() { // from class: com.huawei.openalliance.ad.views.web.PureNetworkLoadStatusView.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (PureNetworkLoadStatusView.this.d == null) {
                    ViewClickInstrumentation.clickOnView(view);
                } else {
                    PureNetworkLoadStatusView.this.d.a(view);
                    ViewClickInstrumentation.clickOnView(view);
                }
            }
        };
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, new int[]{R.attr._2131100892_res_0x7f0604dc, R.attr._2131101089_res_0x7f0605a1, R.attr._2131101384_res_0x7f0606c8});
        try {
            this.f8181a = obtainStyledAttributes.getString(0);
            obtainStyledAttributes.recycle();
            a();
        } catch (Throwable th) {
            obtainStyledAttributes.recycle();
            throw th;
        }
    }

    public PureNetworkLoadStatusView(Context context) {
        super(context);
        this.c = 1;
        this.b = new View.OnClickListener() { // from class: com.huawei.openalliance.ad.views.web.PureNetworkLoadStatusView.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (PureNetworkLoadStatusView.this.d == null) {
                    ViewClickInstrumentation.clickOnView(view);
                } else {
                    PureNetworkLoadStatusView.this.d.a(view);
                    ViewClickInstrumentation.clickOnView(view);
                }
            }
        };
        a();
    }
}
