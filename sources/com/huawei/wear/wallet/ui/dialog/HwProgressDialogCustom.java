package com.huawei.wear.wallet.ui.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.huawei.health.R;
import defpackage.tlk;
import defpackage.tlm;
import java.text.NumberFormat;

/* loaded from: classes9.dex */
public class HwProgressDialogCustom extends Dialog implements HwProgressDialogInterface, ConfigChangeListener {

    /* renamed from: a, reason: collision with root package name */
    private LinearLayout f11217a;
    private boolean b;
    private boolean c;
    private int d;
    private int e;
    private Context f;
    private int g;
    private Drawable h;
    private int i;
    private boolean j;
    private TextView k;
    private int l;
    private CharSequence m;
    private ProgressBar n;
    private LayoutInflater o;
    private NumberFormat p;
    private Drawable q;
    private int r;
    private String s;
    private int t;
    private LinearLayout u;
    private int v;
    private Handler y;

    public HwProgressDialogCustom(Context context) {
        super(context);
        this.c = false;
        this.b = false;
        this.r = 0;
        this.f = context;
        a();
    }

    public HwProgressDialogCustom(Context context, int i) {
        super(context, i);
        this.c = false;
        this.b = false;
        this.r = 0;
        this.f = context;
        a();
    }

    private void a() {
        Log.i("HwProgressDialog", "init: ");
        this.o = (LayoutInflater) this.f.getSystemService("layout_inflater");
        requestWindowFeature(1);
        e();
        c();
    }

    private void e() {
        try {
            setContentView(R.layout.oversea_custom_dialog_layout);
            this.u = (LinearLayout) findViewById(R.id.dialog_layout);
            this.f11217a = (LinearLayout) findViewById(R.id.content);
        } catch (RuntimeException unused) {
            Log.e("HwProgressDialog", "initView NumberFormatException");
        }
    }

    @Override // com.huawei.wear.wallet.ui.dialog.ConfigChangeListener
    public void onConfigurationChanged(Configuration configuration) {
        if (this.d == configuration.screenWidthDp && this.e == configuration.screenHeightDp) {
            return;
        }
        this.d = configuration.screenWidthDp;
        this.e = configuration.screenHeightDp;
        tlk.faW_(this, getContext().getResources().getDimensionPixelSize(R.dimen._2131362022_res_0x7f0a00e6), 0.98d);
    }

    @Override // android.app.Dialog
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Log.i("HwProgressDialog", "onCreate ");
        d();
    }

    @Override // android.app.Dialog
    public void onStart() {
        super.onStart();
        this.j = true;
        getWindow().setBackgroundDrawableResource(android.R.color.transparent);
    }

    @Override // android.app.Dialog
    protected void onStop() {
        super.onStop();
        this.j = false;
    }

    @Override // android.app.Dialog, android.view.Window.Callback
    public void onContentChanged() {
        super.onContentChanged();
        tlk.faW_(this, getContext().getResources().getDimensionPixelSize(R.dimen._2131362022_res_0x7f0a00e6), 0.98d);
    }

    private void d() {
        View inflate = this.o.inflate(R.layout.oversea_custom_progress_dlg, (ViewGroup) null);
        this.n = (ProgressBar) inflate.findViewById(R.id.progress);
        this.k = (TextView) inflate.findViewById(R.id.message);
        setView(inflate);
        tlm.b(this, this.l, this.t, this.v, this.g);
        tlm.fbe_(this, this.i, this.q, this.h, this.m);
        tlm.fbd_(this.r, this.y);
    }

    private void c() {
        this.s = "%1d/%2d";
        NumberFormat percentInstance = NumberFormat.getPercentInstance();
        this.p = percentInstance;
        percentInstance.setMaximumFractionDigits(0);
    }

    @Override // android.app.Dialog
    public void setTitle(int i) {
        setTitle(this.f.getResources().getString(i));
    }

    @Override // com.huawei.wear.wallet.ui.dialog.HwProgressDialogInterface
    public void setMessage(CharSequence charSequence) {
        if (this.n != null) {
            tlm.faZ_(this.k, charSequence);
        } else {
            this.m = charSequence;
        }
    }

    @Override // com.huawei.wear.wallet.ui.dialog.HwProgressDialogInterface
    public void setProgressStyle(int i) {
        Log.i("HwProgressDialog", "setProgressStyle ");
        this.r = i;
    }

    @Override // com.huawei.wear.wallet.ui.dialog.HwProgressDialogInterface
    public void setProgressNumberFormat(String str) {
        Log.i("HwProgressDialog", "setProgressNumberFormat ");
        this.s = str;
        tlm.fbd_(this.r, this.y);
    }

    @Override // com.huawei.wear.wallet.ui.dialog.HwProgressDialogInterface
    public void setProgressPercentFormat(NumberFormat numberFormat) {
        this.p = numberFormat;
        tlm.fbd_(this.r, this.y);
    }

    @Override // com.huawei.wear.wallet.ui.dialog.HwProgressDialogInterface
    public int getProgress() {
        return tlm.fbb_(this.n, this.t);
    }

    @Override // com.huawei.wear.wallet.ui.dialog.HwProgressDialogInterface
    public void setProgress(int i) {
        if (this.j) {
            Log.i("HwProgressDialog", "setProgress ");
            this.n.setProgress(i);
            tlm.fbd_(this.r, this.y);
            return;
        }
        this.t = i;
    }

    @Override // com.huawei.wear.wallet.ui.dialog.HwProgressDialogInterface
    public int getSecondaryProgress() {
        return tlm.fbc_(this.n, this.v);
    }

    @Override // com.huawei.wear.wallet.ui.dialog.HwProgressDialogInterface
    public void setSecondaryProgress(int i) {
        ProgressBar progressBar = this.n;
        if (progressBar != null) {
            progressBar.setSecondaryProgress(i);
            tlm.fbd_(this.r, this.y);
        } else {
            this.v = i;
        }
    }

    @Override // com.huawei.wear.wallet.ui.dialog.HwProgressDialogInterface
    public int getMax() {
        return tlm.fba_(this.n, this.l);
    }

    @Override // com.huawei.wear.wallet.ui.dialog.HwProgressDialogInterface
    public void setMax(int i) {
        ProgressBar progressBar = this.n;
        if (progressBar != null) {
            progressBar.setMax(i);
            tlm.fbd_(this.r, this.y);
        } else {
            this.l = i;
        }
    }

    @Override // com.huawei.wear.wallet.ui.dialog.HwProgressDialogInterface
    public void incrementProgressBy(int i) {
        ProgressBar progressBar = this.n;
        if (progressBar != null) {
            progressBar.incrementProgressBy(i);
            tlm.fbd_(this.r, this.y);
        } else {
            this.g += i;
        }
    }

    @Override // com.huawei.wear.wallet.ui.dialog.HwProgressDialogInterface
    public void incrementSecondaryProgressBy(int i) {
        ProgressBar progressBar = this.n;
        if (progressBar != null) {
            progressBar.incrementSecondaryProgressBy(i);
            tlm.fbd_(this.r, this.y);
        } else {
            this.i += i;
        }
    }

    @Override // com.huawei.wear.wallet.ui.dialog.HwProgressDialogInterface
    public void setProgressDrawable(Drawable drawable) {
        ProgressBar progressBar = this.n;
        if (progressBar != null) {
            progressBar.setProgressDrawable(drawable);
        } else {
            this.q = drawable;
        }
    }

    @Override // com.huawei.wear.wallet.ui.dialog.HwProgressDialogInterface
    public void setIndeterminateDrawable(Drawable drawable) {
        ProgressBar progressBar = this.n;
        if (progressBar != null) {
            progressBar.setIndeterminateDrawable(drawable);
        } else {
            this.h = drawable;
        }
    }

    @Override // com.huawei.wear.wallet.ui.dialog.HwProgressDialogInterface
    public void setView(View view) {
        tlm.fbf_(this.u, this.f11217a, view);
    }

    @Override // android.app.Dialog, android.content.DialogInterface, com.huawei.wear.wallet.ui.dialog.HwProgressDialogInterface
    public void dismiss() {
        try {
            super.dismiss();
        } catch (Exception unused) {
            Log.e("HwProgressDialog", "dismiss failed Exception");
        }
    }

    @Override // android.app.Dialog, com.huawei.wear.wallet.ui.dialog.HwProgressDialogInterface
    public void show() {
        try {
            super.show();
        } catch (Exception unused) {
            Log.e("HwProgressDialog", "showDialog failed Exception");
        }
    }

    @Override // android.app.Dialog, com.huawei.wear.wallet.ui.dialog.HwProgressDialogInterface
    public Window getWindow() {
        return super.getWindow();
    }

    @Override // android.app.Dialog, android.view.Window.Callback, com.huawei.wear.wallet.ui.dialog.HwProgressDialogInterface
    public boolean onSearchRequested() {
        if (this.c) {
            return this.b;
        }
        return super.onSearchRequested();
    }

    @Override // android.app.Dialog, android.content.DialogInterface, com.huawei.wear.wallet.ui.dialog.HwProgressDialogInterface
    public void cancel() {
        try {
            super.cancel();
        } catch (Exception unused) {
            Log.e("HwProgressDialog", "cancel failed Exception");
        }
    }

    @Override // com.huawei.wear.wallet.ui.dialog.HwProgressDialogInterface
    public void setSearchRequestedReturn(boolean z) {
        this.c = true;
        this.b = z;
    }
}
