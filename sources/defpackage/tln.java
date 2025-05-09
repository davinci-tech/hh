package defpackage;

import android.R;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.wear.wallet.ui.dialog.DialogContentView;
import com.huawei.wear.wallet.ui.dialog.HwDialogInterface;
import com.huawei.wear.wallet.ui.dialog.WidgetBuilder;

/* loaded from: classes9.dex */
public class tln extends Dialog implements HwDialogInterface {

    /* renamed from: a, reason: collision with root package name */
    private boolean f17265a;
    private boolean b;
    private boolean c;
    private boolean d;
    private RelativeLayout e;
    private Context f;
    private LinearLayout g;
    private LayoutInflater h;
    private View i;
    private TextView j;
    private TextView k;
    private ImageView l;
    private LinearLayout m;
    private DialogContentView n;
    private TextView o;
    private TextView r;
    private LinearLayout s;

    protected double c() {
        return 0.2d;
    }

    public tln(Context context, int i) {
        super(context, i);
        this.d = false;
        this.b = false;
        this.f17265a = false;
        this.c = false;
        this.f = context;
        this.h = (LayoutInflater) context.getSystemService("layout_inflater");
        c(this.c);
    }

    @Override // android.app.Dialog
    protected void onStart() {
        super.onStart();
        getWindow().setBackgroundDrawableResource(R.color.transparent);
    }

    private void c(boolean z) {
        requestWindowFeature(1);
        setContentView(com.huawei.health.R.layout.cp3_hw_dialog_custom_layout_walet);
        this.n = (DialogContentView) findViewById(com.huawei.health.R.id.dialog_layout);
        this.r = (TextView) findViewById(com.huawei.health.R.id.title);
        this.s = (LinearLayout) findViewById(com.huawei.health.R.id.titleView);
        this.g = (LinearLayout) findViewById(com.huawei.health.R.id.content);
        this.m = (LinearLayout) findViewById(com.huawei.health.R.id.content_list);
        this.j = (TextView) findViewById(com.huawei.health.R.id.msg);
        this.e = (RelativeLayout) findViewById(com.huawei.health.R.id.button_layout);
        this.o = (TextView) findViewById(com.huawei.health.R.id.ok);
        this.k = (TextView) findViewById(com.huawei.health.R.id.cancel);
        this.i = findViewById(com.huawei.health.R.id.dialog_bottom_line);
    }

    @Override // com.huawei.wear.wallet.ui.dialog.HwDialogInterface
    public HwDialogInterface setPositiveButton(CharSequence charSequence, final DialogInterface.OnClickListener onClickListener) {
        ImageView imageView = this.l;
        if (imageView != null) {
            imageView.setVisibility(0);
        }
        this.o.setText(charSequence);
        this.e.setVisibility(0);
        this.o.setVisibility(0);
        this.o.setOnClickListener(new View.OnClickListener() { // from class: tln.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                tlk.faS_(tln.this.d, tln.this);
                DialogInterface.OnClickListener onClickListener2 = onClickListener;
                if (onClickListener2 != null) {
                    onClickListener2.onClick(tln.this, -1);
                }
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        return this;
    }

    @Override // android.app.Dialog, android.view.Window.Callback
    public void onContentChanged() {
        super.onContentChanged();
        tlk.faV_(this, getContext().getResources().getDimensionPixelSize(com.huawei.health.R.dimen._2131362022_res_0x7f0a00e6), 0.98d, this.c);
    }

    @Override // com.huawei.wear.wallet.ui.dialog.HwDialogInterface
    public HwDialogInterface setPositiveButton(int i, DialogInterface.OnClickListener onClickListener) {
        return setPositiveButton(this.f.getResources().getString(i), onClickListener);
    }

    @Override // com.huawei.wear.wallet.ui.dialog.HwDialogInterface
    public HwDialogInterface setNegativeButton(CharSequence charSequence, final DialogInterface.OnClickListener onClickListener) {
        ImageView imageView = this.l;
        if (imageView != null) {
            imageView.setVisibility(0);
        }
        this.k.setText(charSequence);
        this.e.setVisibility(0);
        this.k.setVisibility(0);
        this.k.setOnClickListener(new View.OnClickListener() { // from class: tln.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                tlk.faS_(tln.this.d, tln.this);
                DialogInterface.OnClickListener onClickListener2 = onClickListener;
                if (onClickListener2 != null) {
                    onClickListener2.onClick(tln.this, -2);
                }
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        return this;
    }

    @Override // com.huawei.wear.wallet.ui.dialog.HwDialogInterface
    public HwDialogInterface setNegativeButton(int i, DialogInterface.OnClickListener onClickListener) {
        return setNegativeButton(this.f.getResources().getString(i), onClickListener);
    }

    @Override // android.app.Dialog
    public void setTitle(int i) {
        tlk.faY_(this.r, this.f.getResources().getString(i));
    }

    @Override // com.huawei.wear.wallet.ui.dialog.HwDialogInterface
    public HwDialogInterface setMessage(String str) {
        TextView textView = this.j;
        if (textView != null) {
            textView.setVisibility(0);
            this.j.setText(str);
        }
        return this;
    }

    @Override // com.huawei.wear.wallet.ui.dialog.HwDialogInterface
    public HwDialogInterface setMessage(int i) {
        return setMessage(this.f.getResources().getString(i));
    }

    @Override // android.app.Dialog
    public void setOnKeyListener(DialogInterface.OnKeyListener onKeyListener) {
        super.setOnKeyListener(onKeyListener);
    }

    @Override // android.app.Dialog
    public void setOnDismissListener(DialogInterface.OnDismissListener onDismissListener) {
        super.setOnDismissListener(onDismissListener);
    }

    @Override // android.app.Dialog
    public void setOnShowListener(DialogInterface.OnShowListener onShowListener) {
        super.setOnShowListener(onShowListener);
    }

    @Override // android.app.Dialog
    public void setOnCancelListener(DialogInterface.OnCancelListener onCancelListener) {
        super.setOnCancelListener(onCancelListener);
    }

    @Override // android.app.Dialog, com.huawei.wear.wallet.ui.dialog.HwDialogInterface
    public void setCancelable(boolean z) {
        super.setCancelable(z);
    }

    @Override // android.app.Dialog, com.huawei.wear.wallet.ui.dialog.HwDialogInterface
    public void setCanceledOnTouchOutside(boolean z) {
        super.setCanceledOnTouchOutside(z);
    }

    @Override // android.app.Dialog, android.view.Window.Callback
    public boolean onSearchRequested() {
        if (this.b) {
            return this.f17265a;
        }
        return super.onSearchRequested();
    }

    @Override // android.app.Dialog, com.huawei.wear.wallet.ui.dialog.HwDialogInterface
    public void show() {
        stq.b("HwDialogCustom", "dialog show");
        tlk.faT_(this.i, this.o, this.k);
        tlk.faX_(this.j, this.r, this.f, com.huawei.health.R.dimen._2131362467_res_0x7f0a02a3);
        if (!WidgetBuilder.a(this.f)) {
            getWindow().getWindowManager().getDefaultDisplay().getMetrics(new DisplayMetrics());
            int c = (int) (r0.heightPixels * c());
            FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) this.n.getLayoutParams();
            if (tls.a(this.f) == 1) {
                layoutParams.setMargins(0, 0, 0, 0);
            } else {
                layoutParams.setMargins(0, c, 0, 0);
            }
            layoutParams.gravity = 1;
            this.n.setLayoutParams(layoutParams);
        }
        tlk.faU_(this.f, this.n);
        try {
            super.show();
        } catch (Exception unused) {
            stq.e("HwDialogCustom", "showDialog failed Exception");
        }
    }

    @Override // android.app.Dialog, android.content.DialogInterface
    public void dismiss() {
        try {
            super.dismiss();
        } catch (Exception unused) {
            stq.e("HwDialogCustom", "dismiss failed Exception");
        }
    }

    @Override // android.app.Dialog, android.content.DialogInterface
    public void cancel() {
        try {
            super.cancel();
        } catch (Exception unused) {
            stq.e("HwDialogCustom", "cancel failed Exception");
        }
    }

    @Override // android.app.Dialog
    public void hide() {
        try {
            super.hide();
        } catch (Exception unused) {
            stq.e("HwDialogCustom", "hide failed Exception");
        }
    }
}
