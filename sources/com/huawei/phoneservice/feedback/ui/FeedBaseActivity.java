package com.huawei.phoneservice.feedback.ui;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.format.Formatter;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.openalliance.ad.constant.OsType;
import com.huawei.phoneservice.faq.base.util.i;
import com.huawei.phoneservice.faq.base.widget.d;
import com.huawei.uikit.hwresources.R$dimen;

/* loaded from: classes5.dex */
public abstract class FeedBaseActivity extends FeedbackAbstractBaseActivity {
    protected ProgressDialog c;
    protected AlertDialog d;

    public TextView u() {
        return (TextView) getWindow().findViewById(Resources.getSystem().getIdentifier("action_bar_title", "id", OsType.ANDROID));
    }

    public void t() {
        ProgressDialog progressDialog;
        if (isFinishing() || isDestroyed() || (progressDialog = this.c) == null || !progressDialog.isShowing()) {
            return;
        }
        this.c.dismiss();
        this.c = null;
    }

    public void showAlertDialog(View view) {
        a(view, true);
    }

    @Override // android.app.Activity
    public void setTitle(CharSequence charSequence) {
        super.setTitle(charSequence);
        ActionBar actionBar = getActionBar();
        if (actionBar != null) {
            d.cdD_(actionBar, charSequence);
        }
    }

    public void s() {
        try {
            AlertDialog alertDialog = this.d;
            if (alertDialog == null || !alertDialog.isShowing()) {
                return;
            }
            this.d.dismiss();
            this.d = null;
        } catch (Exception e2) {
            i.c("FeedBaseActivity", e2.getMessage());
        }
    }

    @Override // com.huawei.phoneservice.feedback.ui.FeedbackAbstractBaseActivity
    protected int r() {
        return R$dimen.emui_dimens_max_start;
    }

    @Override // com.huawei.phoneservice.feedback.ui.FeedbackAbstractBaseActivity
    protected int p() {
        return R$dimen.padding_m;
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        setTitle(getTitle());
        super.onResume();
    }

    @Override // com.huawei.phoneservice.feedback.ui.FeedbackAbstractBaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        t();
        s();
        super.onDestroy();
    }

    @Override // com.huawei.phoneservice.feedback.ui.FeedbackAbstractBaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        ActionBar actionBar = getActionBar();
        if (actionBar != null) {
            try {
                d.cdB_(actionBar, true, new e());
                d.cdC_(actionBar, true);
                TextView u = u();
                if (u != null) {
                    d.cdA_(actionBar, u);
                }
                actionBar.setHomeActionContentDescription(R.string._2130850849_res_0x7f023421);
            } catch (Exception e2) {
                i.c("FeedBaseActivity", e2.getMessage());
            }
        }
    }

    public void a(String str, String str2, String str3, View.OnClickListener onClickListener, View.OnClickListener onClickListener2) {
        View inflate = getLayoutInflater().inflate(R.layout.feedback_sdk_dialog_upload_prompt, (ViewGroup) null);
        if (inflate == null) {
            return;
        }
        TextView textView = (TextView) inflate.findViewById(R.id.txtContent);
        TextView textView2 = (TextView) inflate.findViewById(R.id.btnYes);
        TextView textView3 = (TextView) inflate.findViewById(R.id.btnNo);
        if (!TextUtils.isEmpty(str)) {
            textView.setText(str);
        }
        if (!TextUtils.isEmpty(str2)) {
            textView2.setText(str2);
        }
        if (!TextUtils.isEmpty(str3)) {
            textView3.setText(str3);
        }
        if (onClickListener != null) {
            textView2.setOnClickListener(onClickListener);
        }
        if (onClickListener2 != null) {
            textView3.setOnClickListener(onClickListener2);
        }
        a(inflate, false);
    }

    protected void a(View view, boolean z) {
        Window window;
        if (isFinishing()) {
            return;
        }
        AlertDialog alertDialog = this.d;
        if (alertDialog == null || !alertDialog.isShowing()) {
            this.d = null;
            AlertDialog create = new AlertDialog.Builder(this).create();
            this.d = create;
            create.setCanceledOnTouchOutside(z);
            this.d.setCancelable(z);
            AlertDialog alertDialog2 = this.d;
            if (alertDialog2 == null || alertDialog2.isShowing()) {
                return;
            }
            this.d.show();
            this.d.setContentView(view);
            if (this.f8283a && (window = this.d.getWindow()) != null) {
                WindowManager.LayoutParams attributes = window.getAttributes();
                window.setGravity(80);
                attributes.y = 50;
                attributes.dimAmount = 0.2f;
                window.setAttributes(attributes);
            }
        }
    }

    static class a implements DialogInterface.OnClickListener {
        @Override // android.content.DialogInterface.OnClickListener
        public void onClick(DialogInterface dialogInterface, int i) {
            dialogInterface.dismiss();
            ViewClickInstrumentation.clickOnDialog(dialogInterface, i);
        }

        /* synthetic */ a(e eVar) {
            this();
        }

        private a() {
        }
    }

    public void a(Activity activity, String str, String str2, CharSequence[] charSequenceArr, int i, DialogInterface.OnClickListener onClickListener) {
        if (isFinishing()) {
            return;
        }
        AlertDialog alertDialog = this.d;
        if (alertDialog == null || !alertDialog.isShowing()) {
            e eVar = null;
            if (!this.f8283a) {
                AlertDialog create = new AlertDialog.Builder(activity).setTitle(str).setCancelable(false).setNegativeButton(str2, new a(eVar)).setSingleChoiceItems(charSequenceArr, i, onClickListener).create();
                this.d = create;
                create.setCanceledOnTouchOutside(true);
                com.huawei.phoneservice.faq.base.util.a.cdd_(this.d);
                return;
            }
            AlertDialog create2 = new AlertDialog.Builder(activity).create();
            this.d = create2;
            create2.setCanceledOnTouchOutside(false);
            AlertDialog alertDialog2 = this.d;
            if (alertDialog2 == null || alertDialog2.isShowing()) {
                return;
            }
            this.d.show();
            View inflate = getLayoutInflater().inflate(R.layout.feedback_sdk_dialog_list, (ViewGroup) null);
            ((TextView) inflate.findViewById(R.id.title)).setText(str);
            TextView textView = (TextView) inflate.findViewById(R.id.cancel);
            textView.setText(str2);
            textView.setOnClickListener(new c());
            a(inflate, charSequenceArr, i, onClickListener);
        }
    }

    class b implements AdapterView.OnItemClickListener {
        final /* synthetic */ DialogInterface.OnClickListener d;

        @Override // android.widget.AdapterView.OnItemClickListener
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
            DialogInterface.OnClickListener onClickListener = this.d;
            if (onClickListener != null) {
                onClickListener.onClick(FeedBaseActivity.this.d, i);
            }
            ViewClickInstrumentation.clickOnListView(adapterView, view, i);
        }

        b(DialogInterface.OnClickListener onClickListener) {
            this.d = onClickListener;
        }
    }

    class c implements View.OnClickListener {
        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            FeedBaseActivity.this.s();
            ViewClickInstrumentation.clickOnView(view);
        }

        c() {
        }
    }

    class e implements View.OnClickListener {
        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            FeedBaseActivity.this.onBackPressed(view);
            ViewClickInstrumentation.clickOnView(view);
        }

        e() {
        }
    }

    public void a(long j, View.OnClickListener onClickListener, View.OnClickListener onClickListener2) {
        View inflate = getLayoutInflater().inflate(R.layout.feedback_sdk_dialog_upload_flow, (ViewGroup) null);
        if (inflate == null) {
            return;
        }
        TextView textView = (TextView) inflate.findViewById(R.id.txtContent);
        TextView textView2 = (TextView) inflate.findViewById(R.id.btnYes);
        TextView textView3 = (TextView) inflate.findViewById(R.id.btnNo);
        if (textView == null || textView2 == null || textView3 == null) {
            return;
        }
        textView.setText("CN".equals(com.huawei.phoneservice.feedbackcommon.utils.b.h().getSdk("country")) ? getResources().getString(R.string._2130850940_res_0x7f02347c, Formatter.formatFileSize(this, j)) : getString(R.string._2130850938_res_0x7f02347a, new Object[]{Formatter.formatFileSize(this, j)}));
        if (onClickListener != null) {
            textView2.setOnClickListener(onClickListener);
        }
        if (onClickListener2 != null) {
            textView3.setOnClickListener(onClickListener2);
        }
        showAlertDialog(inflate);
    }

    private void a(View view, CharSequence[] charSequenceArr, int i, DialogInterface.OnClickListener onClickListener) {
        ListView listView = (ListView) view.findViewById(R.id.list);
        listView.setAdapter((ListAdapter) new com.huawei.phoneservice.feedback.adapter.e(charSequenceArr, i));
        listView.setOnItemClickListener(new b(onClickListener));
        this.d.setContentView(view);
        Window window = this.d.getWindow();
        if (window == null) {
            return;
        }
        WindowManager.LayoutParams attributes = window.getAttributes();
        window.setGravity(80);
        attributes.y = 50;
        attributes.dimAmount = 0.2f;
        window.setAttributes(attributes);
    }
}
