package com.huawei.openalliance.ad;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.operation.beans.TitleBean;
import com.tencent.connect.common.Constants;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes5.dex */
public class tl {

    /* renamed from: a, reason: collision with root package name */
    private static final Map<String, String> f7536a = new HashMap<String, String>() { // from class: com.huawei.openalliance.ad.tl.1
        {
            put("WX", "com.tencent.mm");
            put(Constants.SOURCE_QQ, "com.tencent.mobileqq");
            put("WB", "com.sina.weibo");
            put("weLink", com.huawei.openalliance.ad.constant.Constants.WELINK_PKG_NAME);
        }
    };
    private static final Map<String, Integer> b = new HashMap<String, Integer>() { // from class: com.huawei.openalliance.ad.tl.2
        {
            put("WX", Integer.valueOf(R.string._2130851157_res_0x7f023555));
            put(Constants.SOURCE_QQ, Integer.valueOf(R.string._2130851153_res_0x7f023551));
        }
    };
    private Dialog c;
    private View d;
    private final Activity e;
    private final ts f;
    private List<String> g = new ArrayList();
    private Map<String, String> h = new HashMap();
    private com.huawei.openalliance.ad.analysis.c i;

    public void b() {
        Dialog dialog = this.c;
        if (dialog == null || !dialog.isShowing()) {
            return;
        }
        this.c.dismiss();
    }

    public void a(DialogInterface.OnDismissListener onDismissListener) {
        Dialog dialog = this.c;
        if (dialog != null) {
            dialog.setOnDismissListener(onDismissListener);
        }
    }

    public void a() {
        Dialog dialog;
        if (this.e == null || this.f == null || (dialog = this.c) == null) {
            ho.a("PPSShareDialog", "context,nativeAd or dialog is empty");
        } else {
            dialog.show();
        }
    }

    private void f() {
        a(R.id.share_wx, "WX", 1);
        a(R.id.share_wx_moments, "WX", 2);
        a(R.id.share_qq, Constants.SOURCE_QQ, 1);
        a(R.id.share_qq_qzone, Constants.SOURCE_QQ, 2);
        a(R.id.share_weibo, "WB", -1);
        a(R.id.share_weLink, "weLink", -1);
        a(R.id.share_more, TitleBean.RIGHT_BTN_TYPE_MORE, -1);
        if (ho.a()) {
            ho.a("PPSShareDialog", "initShareButton end");
        }
    }

    private void e() {
        this.c = new Dialog(this.e, R.style.HIAD_share_dialog);
        View inflate = LayoutInflater.from(this.e).inflate(R.layout.bottom_share_layout, (ViewGroup) null);
        this.d = inflate;
        this.c.setContentView(inflate);
        this.c.setCanceledOnTouchOutside(true);
        this.c.setCancelable(true);
        Window window = this.c.getWindow();
        if (window != null) {
            WindowManager.LayoutParams attributes = window.getAttributes();
            window.setDimAmount(0.0f);
            attributes.gravity = 80;
            attributes.width = this.e.getResources().getDisplayMetrics().widthPixels;
        }
    }

    private boolean d(String str) {
        if (com.huawei.openalliance.ad.utils.i.a(this.e, f7536a.get(str))) {
            return true;
        }
        if (!ho.a()) {
            return false;
        }
        ho.a("PPSShareDialog", "checkForInstall : %s filed", str);
        return false;
    }

    private void d() {
        if (ho.a()) {
            ho.a("PPSShareDialog", "initDialog");
        }
        if (this.e == null) {
            if (ho.a()) {
                ho.a("PPSShareDialog", "initDialog filed : context is null");
            }
        } else {
            e();
            this.h = fh.b(this.e.getApplicationContext()).an();
            f();
            this.i = new com.huawei.openalliance.ad.analysis.c(this.e.getApplicationContext());
        }
    }

    private boolean c(String str) {
        if (tt.a(str)) {
            return true;
        }
        if (!ho.a()) {
            return false;
        }
        ho.a("PPSShareDialog", "checkForDependencies : %s filed", str);
        return false;
    }

    private boolean b(String str) {
        if (this.h.get(str) != null) {
            return c(str);
        }
        if (ho.a()) {
            ho.a("PPSShareDialog", "checkForAppId : %s filed", str);
        }
        return false;
    }

    private boolean a(final String str, View view) {
        if (!b(str)) {
            view.setVisibility(8);
            return true;
        }
        if (d(str)) {
            return false;
        }
        a(str);
        view.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.openalliance.ad.tl.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                Toast.makeText(tl.this.e, tl.this.e.getResources().getString(R.string._2130851151_res_0x7f02354f, tl.this.e.getResources().getString(((Integer) tl.b.get(str)).intValue())), 0).show();
                ho.a("PPSShareDialog", "click %s share : app not support", str);
                ViewClickInstrumentation.clickOnView(view2);
            }
        });
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:7:0x002b  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void a(java.lang.String r4, int r5) {
        /*
            r3 = this;
            com.huawei.openalliance.ad.tu r0 = new com.huawei.openalliance.ad.tu
            r0.<init>()
            java.util.Map<java.lang.String, java.lang.String> r1 = r3.h
            java.lang.Object r1 = r1.get(r4)
            java.lang.String r1 = (java.lang.String) r1
            r0.a(r1)
            r1 = 1
            if (r1 != r5) goto L18
            java.lang.Boolean r5 = java.lang.Boolean.valueOf(r1)
            goto L20
        L18:
            r1 = 2
            if (r1 != r5) goto L23
            r5 = 0
            java.lang.Boolean r5 = java.lang.Boolean.valueOf(r5)
        L20:
            r0.a(r5)
        L23:
            java.lang.String r5 = "more"
            boolean r5 = r5.equals(r4)
            if (r5 == 0) goto L30
            java.util.List<java.lang.String> r5 = r3.g
            r0.a(r5)
        L30:
            r3.b()
            com.huawei.openalliance.ad.ts r5 = r3.f
            java.lang.String r5 = r5.a()
            if (r5 == 0) goto L43
            java.lang.String r1 = "http"
            boolean r5 = r5.startsWith(r1)
            if (r5 != 0) goto L52
        L43:
            com.huawei.openalliance.ad.ts r5 = r3.f
            java.util.Map<java.lang.String, java.lang.String> r1 = r3.h
            java.lang.String r2 = "defImg"
            java.lang.Object r1 = r1.get(r2)
            java.lang.String r1 = (java.lang.String) r1
            r5.a(r1)
        L52:
            android.app.Activity r5 = r3.e
            com.huawei.openalliance.ad.ts r1 = r3.f
            com.huawei.openalliance.ad.tt.a(r4, r5, r1, r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.openalliance.ad.tl.a(java.lang.String, int):void");
    }

    private void a(String str) {
        String str2 = f7536a.get(str);
        if (this.g.contains(str2)) {
            return;
        }
        this.g.add(str2);
    }

    private void a(int i, final String str, final int i2) {
        View findViewById = this.d.findViewById(i);
        if (Constants.SOURCE_QQ.equals(str) || "WX".equals(str)) {
            if (a(str, findViewById)) {
                return;
            }
        } else if ("WB".equals(str)) {
            if (!b(str) || !d(str)) {
                findViewById.setVisibility(8);
                return;
            }
        } else if ("weLink".equals(str) && (!c(str) || !d(str))) {
            findViewById.setVisibility(8);
            return;
        }
        a(str);
        findViewById.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.openalliance.ad.tl.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                tl.this.a(str, i2);
                tl.this.i.a(str, tl.this.f.f());
                ho.a("PPSShareDialog", "click %s share", str);
                ViewClickInstrumentation.clickOnView(view);
            }
        });
    }

    public tl(Activity activity, ts tsVar) {
        this.e = activity;
        this.f = tsVar;
        d();
    }
}
