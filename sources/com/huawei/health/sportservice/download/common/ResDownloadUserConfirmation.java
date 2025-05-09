package com.huawei.health.sportservice.download.common;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.bundle.AppBundle;
import com.huawei.haf.handler.HandlerExecutor;
import com.huawei.health.R;
import com.huawei.health.sportservice.download.common.ResDownloadUserConfirmation;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.ui.commonui.R$string;
import com.huawei.ui.commonui.dialog.CustomProgressDialog;
import com.huawei.ui.commonui.dialog.CustomViewDialog;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import defpackage.koq;
import defpackage.nsn;
import health.compact.a.LogUtil;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/* loaded from: classes7.dex */
public class ResDownloadUserConfirmation {

    /* renamed from: a, reason: collision with root package name */
    private Context f3006a;
    private CustomProgressDialog b;
    private CustomProgressDialog.Builder c;

    public ResDownloadUserConfirmation(Context context) {
        this.f3006a = context;
    }

    public void axI_(final View.OnClickListener onClickListener, List<String> list, long j, final List<View.OnClickListener> list2, final List<View.OnClickListener> list3) {
        Activity axE_ = axE_(this.f3006a);
        boolean a2 = a(axE_, list);
        LogUtil.c("SportService_ResDownloadUserConfirmation", "notifyShowConfirmDownloadDialog", Boolean.valueOf(a2));
        CustomViewDialog.Builder builder = new CustomViewDialog.Builder(axE_);
        builder.a(axE_.getString(R.string.IDS_service_area_notice_title));
        builder.czg_(axH_(axE_, j, list));
        builder.czc_(R.string._2130841130_res_0x7f020e2a, new View.OnClickListener() { // from class: com.huawei.health.sportservice.download.common.ResDownloadUserConfirmation.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LogUtil.c("SportService_ResDownloadUserConfirmation", "click cancel show button");
                onClickListener.onClick(view);
                for (View.OnClickListener onClickListener2 : list3) {
                    if (onClickListener2 != null) {
                        onClickListener2.onClick(view);
                    }
                }
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        builder.cze_(a2 ? R.string._2130844008_res_0x7f021968 : R.string.IDS_bundle_download_button, new View.OnClickListener() { // from class: com.huawei.health.sportservice.download.common.ResDownloadUserConfirmation.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LogUtil.c("SportService_ResDownloadUserConfirmation", "click confirm view");
                ResDownloadUserConfirmation.this.b(list3);
                for (View.OnClickListener onClickListener2 : list2) {
                    if (onClickListener2 != null) {
                        onClickListener2.onClick(view);
                    }
                }
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        CustomViewDialog e = builder.e();
        e.setCancelable(false);
        e.show();
        LogUtil.c("SportService_ResDownloadUserConfirmation", "notifyShowConfirmDownloadDialog");
    }

    public static Activity axE_(Context context) {
        Activity wa_;
        if (context instanceof Activity) {
            LogUtil.c("SportService_ResDownloadUserConfirmation", "checkContext context instanceof Activity");
            wa_ = (Activity) context;
        } else {
            LogUtil.c("SportService_ResDownloadUserConfirmation", "checkContext context is not Activity");
            wa_ = BaseApplication.wa_();
        }
        LogUtil.c("SportService_ResDownloadUserConfirmation", "checkContext mActivity.getLocalClassName() = ", wa_.getLocalClassName());
        return wa_;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(final List<View.OnClickListener> list) {
        if (this.c == null || this.b == null) {
            LogUtil.c("SportService_ResDownloadUserConfirmation", "createProgress");
            CustomProgressDialog.Builder builder = new CustomProgressDialog.Builder(this.f3006a);
            this.c = builder;
            builder.d(this.f3006a.getString(R.string._2130841544_res_0x7f020fc8)).cyH_(new View.OnClickListener() { // from class: com.huawei.health.sportservice.download.common.ResDownloadUserConfirmation.5
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    LogUtil.c("SportService_ResDownloadUserConfirmation", "progress dialog cancel");
                    for (View.OnClickListener onClickListener : list) {
                        if (onClickListener != null) {
                            onClickListener.onClick(view);
                        }
                    }
                    ViewClickInstrumentation.clickOnView(view);
                }
            });
            CustomProgressDialog e = this.c.e();
            this.b = e;
            e.setCanceledOnTouchOutside(false);
            this.c.d(1);
        }
    }

    private boolean a(Context context, List<String> list) {
        if (koq.b(list)) {
            LogUtil.a("SportService_ResDownloadUserConfirmation", "moduleNames is empty");
            return false;
        }
        Set<String> updateModules = AppBundle.c().getUpdateModules(this.f3006a, true);
        Iterator<String> it = list.iterator();
        while (it.hasNext()) {
            if (updateModules.contains(it.next())) {
                return true;
            }
        }
        return false;
    }

    public void d(final String str, final int i, final List<View.OnClickListener> list) {
        HandlerExecutor.e(new Runnable() { // from class: fhh
            @Override // java.lang.Runnable
            public final void run() {
                ResDownloadUserConfirmation.this.d(list, i, str);
            }
        });
    }

    public /* synthetic */ void d(List list, int i, String str) {
        b(list);
        CustomProgressDialog.Builder builder = this.c;
        CustomProgressDialog customProgressDialog = this.b;
        if (builder == null || customProgressDialog == null) {
            LogUtil.a("SportService_ResDownloadUserConfirmation", "builder == null || dialog == null", Integer.valueOf(i));
            return;
        }
        if (i > 0) {
            builder.d(i);
        }
        if (!TextUtils.isEmpty(str)) {
            builder.d(str);
        }
        if (customProgressDialog.isShowing()) {
            return;
        }
        customProgressDialog.show();
    }

    public void b() {
        CustomProgressDialog customProgressDialog = this.b;
        this.b = null;
        this.c = null;
        Object[] objArr = new Object[2];
        objArr[0] = "closeProgressDialog ";
        objArr[1] = Boolean.valueOf(customProgressDialog != null);
        LogUtil.c("SportService_ResDownloadUserConfirmation", objArr);
        if (customProgressDialog != null) {
            customProgressDialog.dismiss();
        }
    }

    public void axJ_(String str, final View.OnClickListener onClickListener, final View.OnClickListener onClickListener2) {
        Activity axE_ = axE_(this.f3006a);
        View inflate = LayoutInflater.from(this.f3006a).inflate(R.layout.activity_bundle_ask_dialog, (ViewGroup) null);
        inflate.findViewById(R.id.bundle_ask_dialog_list).setVisibility(8);
        ((HealthTextView) inflate.findViewById(R.id.bundle_ask_dialog_content)).setText(str);
        CustomViewDialog.Builder builder = new CustomViewDialog.Builder(axE_);
        builder.d(R$string.IDS_service_area_notice_title);
        builder.czg_(inflate);
        builder.czc_(R.string._2130841130_res_0x7f020e2a, new View.OnClickListener() { // from class: com.huawei.health.sportservice.download.common.ResDownloadUserConfirmation.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LogUtil.c("SportService_ResDownloadUserConfirmation", "showErrorDialog cancel show button");
                View.OnClickListener onClickListener3 = onClickListener;
                if (onClickListener3 != null) {
                    onClickListener3.onClick(view);
                }
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        builder.cze_(R.string._2130841467_res_0x7f020f7b, new View.OnClickListener() { // from class: com.huawei.health.sportservice.download.common.ResDownloadUserConfirmation.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LogUtil.c("SportService_ResDownloadUserConfirmation", "showErrorDialog confirm ");
                View.OnClickListener onClickListener3 = onClickListener2;
                if (onClickListener3 != null) {
                    onClickListener3.onClick(view);
                }
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        CustomViewDialog e = builder.e();
        e.setCancelable(false);
        e.show();
    }

    private View axH_(Context context, long j, List<String> list) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.activity_bundle_ask_dialog, (ViewGroup) null);
        axF_(context, inflate, j);
        axG_(context, inflate, list);
        return inflate;
    }

    private void axG_(Context context, View view, List<String> list) {
        ((ListView) view.findViewById(R.id.bundle_ask_dialog_list)).setAdapter((ListAdapter) new b(context, R.layout.activity_bundle_ask_dialog_list_item, R.id.bundle_dialog_list_item, list));
    }

    static class b extends ArrayAdapter<String> {
        @Override // android.widget.BaseAdapter, android.widget.ListAdapter
        public boolean areAllItemsEnabled() {
            return false;
        }

        @Override // android.widget.BaseAdapter, android.widget.ListAdapter
        public boolean isEnabled(int i) {
            return false;
        }

        b(Context context, int i, int i2, List<String> list) {
            super(context, i, i2, list);
        }

        @Override // android.widget.ArrayAdapter, android.widget.Adapter
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public String getItem(int i) {
            return AppBundle.c().getModuleTitle(getContext(), (String) super.getItem(i));
        }
    }

    private void axF_(Context context, View view, long j) {
        ((HealthTextView) view.findViewById(R.id.bundle_ask_dialog_content)).setText(context.getString(R.string._2130846021_res_0x7f022145, nsn.b(context, j)));
    }
}
