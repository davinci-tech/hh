package defpackage;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import com.huawei.health.R;
import com.huawei.health.ecologydevice.callback.IdialogButtonClickCallback;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.dialog.CustomTextAlertDialog;
import com.huawei.ui.commonui.dialog.CustomViewDialog;
import com.huawei.ui.commonui.dialog.NoTitleCustomAlertDialog;
import java.util.Locale;
import java.util.Objects;

/* loaded from: classes3.dex */
public class dif {
    public static void e(Context context) {
        LogUtil.a("DialogUtils", "showNotSupportMeasureDialog() enter");
        NoTitleCustomAlertDialog.Builder builder = new NoTitleCustomAlertDialog.Builder(context);
        builder.e(R.string.IDS_device_wifi_measure_not_support_prompt_msg).czC_(R.string.IDS_device_measureactivity_result_confirm, new View.OnClickListener() { // from class: dio
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        NoTitleCustomAlertDialog e = builder.e();
        e.setCancelable(false);
        e.show();
    }

    public static void Vq_(Context context, View view, final IBaseResponseCallback iBaseResponseCallback) {
        if (context == null || view == null) {
            iBaseResponseCallback.d(-1, "show dialog fail");
            return;
        }
        Vo_(view);
        CustomViewDialog.Builder builder = new CustomViewDialog.Builder(context);
        builder.d(R.string.IDS_device_wifi_user_permission_dialog_title);
        builder.czg_(view);
        builder.czf_(context.getString(R.string._2130841555_res_0x7f020fd3).toUpperCase(Locale.getDefault()), new View.OnClickListener() { // from class: dik
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                dif.Vm_(IBaseResponseCallback.this, view2);
            }
        });
        builder.czd_(context.getString(R.string._2130841130_res_0x7f020e2a).toUpperCase(Locale.getDefault()), new View.OnClickListener() { // from class: dil
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                dif.Vn_(IBaseResponseCallback.this, view2);
            }
        });
        CustomViewDialog e = builder.e();
        e.setCancelable(false);
        e.show();
    }

    static /* synthetic */ void Vm_(IBaseResponseCallback iBaseResponseCallback, View view) {
        iBaseResponseCallback.d(0, "click position button");
        ViewClickInstrumentation.clickOnView(view);
    }

    static /* synthetic */ void Vn_(IBaseResponseCallback iBaseResponseCallback, View view) {
        iBaseResponseCallback.d(-1, "click negative button");
        ViewClickInstrumentation.clickOnView(view);
    }

    private static void Vo_(final View view) {
        if (view == null) {
            LogUtil.h("DialogUtils", "onDialogSizeChanged, dialogView is null");
        } else {
            view.post(new Runnable() { // from class: dii
                @Override // java.lang.Runnable
                public final void run() {
                    dif.Vf_(view);
                }
            });
        }
    }

    static /* synthetic */ void Vf_(View view) {
        int measuredHeight = view.getMeasuredHeight();
        int d = (((int) (nrs.d(BaseApplication.getContext()) * 0.8f)) - view.getRootView().findViewById(R.id.custom_view_dialog_title_layout).getHeight()) - view.getRootView().findViewById(R.id.dialog_linearlayout1).getHeight();
        if (measuredHeight > d) {
            ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
            layoutParams.height = d;
            view.setLayoutParams(layoutParams);
            view.requestLayout();
        }
    }

    public static void Vp_(Activity activity, boolean z, final IdialogButtonClickCallback idialogButtonClickCallback) {
        CustomTextAlertDialog customTextAlertDialog;
        if (activity == null || !z) {
            customTextAlertDialog = null;
        } else {
            CustomTextAlertDialog.Builder d = new CustomTextAlertDialog.Builder(activity).b(R.string.IDS_service_area_notice_title).d(R.string._2130842677_res_0x7f021435);
            String string = activity.getString(R.string._2130841130_res_0x7f020e2a);
            Objects.requireNonNull(idialogButtonClickCallback);
            CustomTextAlertDialog.Builder cyS_ = d.cyS_(string, new View.OnClickListener() { // from class: dim
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    dif.Vd_(IdialogButtonClickCallback.this, view);
                }
            });
            String string2 = activity.getString(R.string._2130841555_res_0x7f020fd3);
            Objects.requireNonNull(idialogButtonClickCallback);
            customTextAlertDialog = cyS_.cyV_(string2, new View.OnClickListener() { // from class: dit
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    dif.Ve_(IdialogButtonClickCallback.this, view);
                }
            }).a();
        }
        if (customTextAlertDialog != null) {
            customTextAlertDialog.setCancelable(false);
            if (customTextAlertDialog.isShowing()) {
                return;
            }
            customTextAlertDialog.show();
        }
    }

    public static void c(Context context, final IdialogButtonClickCallback idialogButtonClickCallback) {
        if (context == null) {
            return;
        }
        NoTitleCustomAlertDialog e = new NoTitleCustomAlertDialog.Builder(context).e(nsn.ae(context) ? R.string._2130844450_res_0x7f021b22 : R.string._2130844110_res_0x7f0219ce).czz_(R.string._2130841130_res_0x7f020e2a, new View.OnClickListener() { // from class: dip
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                dif.Vg_(IdialogButtonClickCallback.this, view);
            }
        }).czC_(R.string._2130842176_res_0x7f021240, new View.OnClickListener() { // from class: diq
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                dif.Vh_(IdialogButtonClickCallback.this, view);
            }
        }).e();
        e.setCancelable(false);
        e.show();
    }

    static /* synthetic */ void Vg_(IdialogButtonClickCallback idialogButtonClickCallback, View view) {
        if (idialogButtonClickCallback != null) {
            idialogButtonClickCallback.onCancelClick(view);
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    static /* synthetic */ void Vh_(IdialogButtonClickCallback idialogButtonClickCallback, View view) {
        if (idialogButtonClickCallback != null) {
            idialogButtonClickCallback.onClick(view);
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    public static void b() {
        LogUtil.a("DialogUtils", "showUnBindDeviceDialog in");
        NoTitleCustomAlertDialog.Builder builder = new NoTitleCustomAlertDialog.Builder(BaseApplication.getContext());
        builder.e(R.string.IDS_device_wifi_release_auth_msg);
        builder.czC_(R.string.IDS_device_measureactivity_result_confirm, new View.OnClickListener() { // from class: dig
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                dif.Vj_(view);
            }
        });
        NoTitleCustomAlertDialog e = builder.e();
        e.setCancelable(false);
        e.show();
    }

    static /* synthetic */ void Vj_(View view) {
        BaseApplication.getActivity().finish();
        ViewClickInstrumentation.clickOnView(view);
    }

    public static void e(Context context, final IdialogButtonClickCallback idialogButtonClickCallback) {
        if (context == null) {
            LogUtil.h("DialogUtils", "showUnbindThirdPartyDeviceDilog context is null");
            return;
        }
        CustomTextAlertDialog a2 = new CustomTextAlertDialog.Builder(context).b(R.string.IDS_hw_health_wear_connect_device_unpair_button).d(R.string.IDS_unbind_device_wear_home).cyU_(R.string.IDS_hw_health_wear_connect_device_unpair_button, new View.OnClickListener() { // from class: dir
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                dif.Vk_(IdialogButtonClickCallback.this, view);
            }
        }).cyR_(R.string._2130841130_res_0x7f020e2a, new View.OnClickListener() { // from class: din
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                dif.Vl_(IdialogButtonClickCallback.this, view);
            }
        }).a();
        a2.setCancelable(false);
        a2.show();
    }

    static /* synthetic */ void Vk_(IdialogButtonClickCallback idialogButtonClickCallback, View view) {
        if (idialogButtonClickCallback != null) {
            idialogButtonClickCallback.onClick(view);
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    static /* synthetic */ void Vl_(IdialogButtonClickCallback idialogButtonClickCallback, View view) {
        if (idialogButtonClickCallback != null) {
            idialogButtonClickCallback.onCancelClick(view);
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    static void Ve_(IdialogButtonClickCallback idialogButtonClickCallback, View view) {
        idialogButtonClickCallback.onClick(view);
        ViewClickInstrumentation.clickOnView(view);
    }

    static void Vd_(IdialogButtonClickCallback idialogButtonClickCallback, View view) {
        idialogButtonClickCallback.onCancelClick(view);
        ViewClickInstrumentation.clickOnView(view);
    }
}
