package com.huawei.ui.commonui.dialog;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.huawei.health.R;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.base.BaseDialog;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import defpackage.nsn;

/* loaded from: classes9.dex */
public class MemberServicesDialog extends BaseDialog {

    /* renamed from: a, reason: collision with root package name */
    private View f8816a;
    private HealthTextView b;
    private HealthTextView c;
    private HealthTextView d;
    private Context e;
    private ImageView i;

    public MemberServicesDialog(Context context) {
        super(context, R.style.CustomDialog);
        this.e = context;
        a();
    }

    private boolean e() {
        Context context = this.e;
        return context == null || ((Activity) context).isFinishing();
    }

    public void a() {
        Context context = this.e;
        if (context == null) {
            LogUtil.b("MemberServicesDialog", " mDialogContext is null");
            return;
        }
        View inflate = ((LayoutInflater) context.getSystemService("layout_inflater")).inflate(R.layout.common_ui_member_services, (ViewGroup) null);
        this.f8816a = inflate;
        this.d = (HealthTextView) inflate.findViewById(R.id.user_type);
        this.c = (HealthTextView) this.f8816a.findViewById(R.id.user_content);
        this.b = (HealthTextView) this.f8816a.findViewById(R.id.member_Activate);
        this.i = (ImageView) this.f8816a.findViewById(R.id.member_cancel_dialog);
        LinearLayout linearLayout = (LinearLayout) this.f8816a.findViewById(R.id.dialog_parent);
        if (nsn.t() && !nsn.p()) {
            FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) linearLayout.getLayoutParams();
            layoutParams.setMargins(0, this.e.getResources().getDimensionPixelSize(R.dimen._2131363125_res_0x7f0a0535), 0, 0);
            linearLayout.setLayoutParams(layoutParams);
        }
        if (nsn.p()) {
            this.d.setTextSize(1, 23.0f);
            this.c.setTextSize(1, 18.0f);
            this.b.setTextSize(1, 16.0f);
            FrameLayout.LayoutParams layoutParams2 = (FrameLayout.LayoutParams) linearLayout.getLayoutParams();
            layoutParams2.setMargins(0, this.e.getResources().getDimensionPixelSize(R.dimen._2131363125_res_0x7f0a0535), 0, 0);
            linearLayout.setLayoutParams(layoutParams2);
        }
        this.d.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() { // from class: com.huawei.ui.commonui.dialog.MemberServicesDialog.1
            @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
            public void onGlobalLayout() {
                int lineCount = MemberServicesDialog.this.d.getLayout().getLineCount();
                LogUtil.a("OnGlobalLayoutListener,", Integer.valueOf(lineCount));
                if (lineCount > 1) {
                    MemberServicesDialog.this.d.setTextSize(1, 16.0f);
                }
                MemberServicesDialog.this.d.getViewTreeObserver().removeOnGlobalLayoutListener(this);
            }
        });
        setContentView(this.f8816a);
    }

    @Override // com.huawei.ui.commonui.base.BaseDialog, android.app.Dialog
    public void show() {
        if (e() || isShowing()) {
            return;
        }
        super.show();
        LogUtil.a("EquityDialog", "dialog.setBackground: dialog showed");
    }
}
