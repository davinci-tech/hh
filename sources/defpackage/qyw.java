package defpackage;

import android.content.Context;
import android.content.DialogInterface;
import android.text.TextUtils;
import android.view.View;
import android.widget.CompoundButton;
import com.apprichtap.haptic.base.PrebakedEffectId;
import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.handler.HandlerExecutor;
import com.huawei.haf.router.AppRouter;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.R;
import com.huawei.health.device.fatscale.multiusers.MultiUsersManager;
import com.huawei.health.weight.bean.WeightTargetDifferences;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hihealth.HiUserInfo;
import com.huawei.hihealth.HiUserPreference;
import com.huawei.hihealth.api.HiHealthManager;
import com.huawei.hihealth.data.listener.HiCommonListener;
import com.huawei.hihealth.util.HiJsonUtil;
import com.huawei.hms.iap.entity.OrderStatusCode;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwbasemgr.ResponseCallback;
import com.huawei.hwbasemgr.WeightBaseResponseCallback;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.operation.OpAnalyticsUtil;
import com.huawei.operation.OperationKey;
import com.huawei.ui.commonui.base.BaseDialog;
import com.huawei.ui.commonui.checkbox.HealthCheckBox;
import com.huawei.ui.commonui.dialog.CustomAlertDialog;
import com.huawei.ui.commonui.dialog.CustomTextAlertDialog;
import com.huawei.ui.commonui.dialog.HealthDataInputDialog;
import com.huawei.ui.commonui.dialog.NoTitleCustomAlertDialog;
import com.huawei.ui.main.R$string;
import defpackage.nry;
import defpackage.nsf;
import defpackage.qry;
import defpackage.qyw;
import health.compact.a.CommonUtils;
import health.compact.a.LanguageUtil;
import health.compact.a.LogUtil;
import health.compact.a.ReleaseLogUtil;
import health.compact.a.UnitUtil;
import health.compact.a.Utils;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.atomic.AtomicInteger;

/* loaded from: classes9.dex */
public class qyw {
    public static void b(ResponseCallback<Integer> responseCallback) {
        if (responseCallback == null) {
            ReleaseLogUtil.a("HealthWeight_WeightDialogUtils", "callbackError callback is null");
        } else {
            responseCallback.onResponse(-1, -1);
        }
    }

    public static void e(ResponseCallback<Integer> responseCallback, int i) {
        if (responseCallback == null) {
            ReleaseLogUtil.a("HealthWeight_WeightDialogUtils", "callbackSuccess callback is null state ", Integer.valueOf(i));
        } else {
            responseCallback.onResponse(0, Integer.valueOf(i));
        }
    }

    private static void d(final ResponseCallback<Integer> responseCallback, final AtomicInteger atomicInteger, BaseDialog baseDialog) {
        baseDialog.show();
        baseDialog.setOnDismissListener(new DialogInterface.OnDismissListener() { // from class: qyx
            @Override // android.content.DialogInterface.OnDismissListener
            public final void onDismiss(DialogInterface dialogInterface) {
                qyw.e((ResponseCallback<Integer>) ResponseCallback.this, atomicInteger.get());
            }
        });
        e(responseCallback, 0);
    }

    public static void i(final Context context, final ResponseCallback<Integer> responseCallback) {
        LoginInit.getInstance(BaseApplication.e()).browsingToLogin(new IBaseResponseCallback() { // from class: qzi
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public final void d(int i, Object obj) {
                qyw.b(ResponseCallback.this, context, i, obj);
            }
        }, "");
    }

    static /* synthetic */ void b(final ResponseCallback responseCallback, final Context context, int i, Object obj) {
        ReleaseLogUtil.b("HealthWeight_WeightDialogUtils", "showUserInfoDialog onResponse result ", Integer.valueOf(i));
        if (i != 0) {
            b((ResponseCallback<Integer>) responseCallback);
        } else {
            MultiUsersManager.INSTANCE.getCurrentUser(new WeightBaseResponseCallback() { // from class: qyu
                @Override // com.huawei.hwbasemgr.WeightBaseResponseCallback
                public final void onResponse(int i2, Object obj2) {
                    qyw.d(ResponseCallback.this, context, i2, (cfi) obj2);
                }
            });
        }
    }

    static /* synthetic */ void d(final ResponseCallback responseCallback, final Context context, int i, cfi cfiVar) {
        LogUtil.c("HealthWeight_WeightDialogUtils", "showUserInfoDialog onResponse errorCode ", Integer.valueOf(i), " user ", cfiVar);
        if (i == 0 && cfiVar != null) {
            e((ResponseCallback<Integer>) responseCallback, -1);
        } else {
            HandlerExecutor.e(new Runnable() { // from class: qzc
                @Override // java.lang.Runnable
                public final void run() {
                    nry.d(rag.c(context), false, new IBaseResponseCallback() { // from class: qzn
                        @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                        /* renamed from: onResponse */
                        public final void d(int i2, Object obj) {
                            qyw.e(ResponseCallback.this, i2, obj);
                        }
                    }, nsf.c(R.color._2131298733_res_0x7f0909ad));
                }
            });
        }
    }

    static /* synthetic */ void e(ResponseCallback responseCallback, int i, Object obj) {
        LogUtil.c("HealthWeight_WeightDialogUtils", "showUserInfoDialog onResponse resultCode ", Integer.valueOf(i), " object ", obj);
        gsd.y();
        if (i == -1) {
            e((ResponseCallback<Integer>) responseCallback, 1);
        } else if (i == 0) {
            e((ResponseCallback<Integer>) responseCallback, 2);
        } else {
            e((ResponseCallback<Integer>) responseCallback, -1);
        }
    }

    public static void g(Context context, ResponseCallback<Integer> responseCallback) {
        final Context c = rag.c(context);
        final cfe cfeVar = new cfe();
        final HealthDataInputDialog d = new HealthDataInputDialog(c).c(nsf.c(R.color._2131299374_res_0x7f090c2e)).e(nsf.h(R$string.IDS_hwh_home_weight_guide_title)).a(nsf.h(R$string.IDS_hwh_home_weight_guide_desc)).d(d(cfeVar));
        if (LanguageUtil.g(BaseApplication.e())) {
            d.e(true);
        }
        d.c(new HealthDataInputDialog.PositiveBtnClickListener() { // from class: qzb
            @Override // com.huawei.ui.commonui.dialog.HealthDataInputDialog.PositiveBtnClickListener
            public final void onClick(List list) {
                qyw.d(c, d, cfeVar);
            }
        }).czq_(new View.OnClickListener() { // from class: qzd
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                qyw.dJi_(HealthDataInputDialog.this, view);
            }
        });
        d(responseCallback, new AtomicInteger(1), d);
        qyr.d(0);
    }

    static /* synthetic */ void dJi_(HealthDataInputDialog healthDataInputDialog, View view) {
        healthDataInputDialog.dismiss();
        qyr.d(1);
        ViewClickInstrumentation.clickOnView(view);
    }

    private static HealthDataInputDialog.e d(final cfe cfeVar) {
        HealthDataInputDialog.e a2;
        HealthDataInputDialog.DataSetFormatter dataSetFormatter = new HealthDataInputDialog.DataSetFormatter() { // from class: qzp
            @Override // com.huawei.ui.commonui.dialog.HealthDataInputDialog.DataSetFormatter
            public final String format(int i) {
                String e;
                e = UnitUtil.e(i, 1, 0);
                return e;
            }
        };
        HealthDataInputDialog.SelectedValueProcessor selectedValueProcessor = new HealthDataInputDialog.SelectedValueProcessor() { // from class: qyw.1
            @Override // com.huawei.ui.commonui.dialog.HealthDataInputDialog.SelectedValueProcessor
            public int process(int i, int i2) {
                return i;
            }

            @Override // com.huawei.ui.commonui.dialog.HealthDataInputDialog.SelectedValueProcessor
            public String format(int i) {
                double a3 = rag.a(i);
                int a4 = UnitUtil.a();
                if (a4 == 1) {
                    cfe.this.ae(UnitUtil.c(a3));
                } else if (a4 == 3) {
                    cfe.this.ae(UnitUtil.i(a3));
                } else {
                    cfe.this.ae(a3);
                }
                return UnitUtil.e(a3, 1, 1);
            }
        };
        String e = qsj.e(-1.0d, false);
        int a3 = UnitUtil.a();
        if (a3 == 1) {
            a2 = new HealthDataInputDialog.e((int) UnitUtil.b(10.0d), (int) UnitUtil.b(251.0d), dataSetFormatter, selectedValueProcessor).a(e);
        } else if (a3 == 3) {
            a2 = new HealthDataInputDialog.e(22, 552, dataSetFormatter, selectedValueProcessor).a(e);
        } else {
            a2 = new HealthDataInputDialog.e(10, 251, dataSetFormatter, selectedValueProcessor).a(e);
        }
        a2.d(1);
        a2.c(rag.d(65.0d));
        return a2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void d(final Context context, final HealthDataInputDialog healthDataInputDialog, final cfe cfeVar) {
        qyr.d(2);
        LoginInit.getInstance(BaseApplication.e()).browsingToLogin(new IBaseResponseCallback() { // from class: qyy
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public final void d(int i, Object obj) {
                qyw.d(HealthDataInputDialog.this, context, cfeVar, i, obj);
            }
        }, "");
    }

    static /* synthetic */ void d(final HealthDataInputDialog healthDataInputDialog, final Context context, final cfe cfeVar, int i, Object obj) {
        ReleaseLogUtil.b("HealthWeight_WeightDialogUtils", "showInputDialogLogin onResponse result ", Integer.valueOf(i));
        if (i != 0) {
            return;
        }
        MultiUsersManager.INSTANCE.getCurrentUser(new WeightBaseResponseCallback() { // from class: qyv
            @Override // com.huawei.hwbasemgr.WeightBaseResponseCallback
            public final void onResponse(int i2, Object obj2) {
                qyw.b(HealthDataInputDialog.this, context, cfeVar, i2, (cfi) obj2);
            }
        });
    }

    static /* synthetic */ void b(HealthDataInputDialog healthDataInputDialog, final Context context, cfe cfeVar, int i, cfi cfiVar) {
        ReleaseLogUtil.b("HealthWeight_WeightDialogUtils", "showInputDialogLogin onResponse errorCode ", Integer.valueOf(i));
        healthDataInputDialog.dismiss();
        if (i != 0 || cfiVar == null) {
            HandlerExecutor.e(new Runnable() { // from class: com.huawei.ui.main.stories.health.weight.util.WeightDialogUtils$$ExternalSyntheticLambda10
                @Override // java.lang.Runnable
                public final void run() {
                    nry.d(context, false, null, nsf.c(R.color._2131298733_res_0x7f0909ad));
                }
            });
            return;
        }
        ckm ckmVar = new ckm();
        ckmVar.setBodyFatRat(20.0f);
        ckmVar.setWeight((float) cfeVar.ax());
        ckmVar.e(true);
        long currentTimeMillis = System.currentTimeMillis();
        ckmVar.setStartTime(currentTimeMillis);
        ckmVar.setEndTime(currentTimeMillis);
        dfd dfdVar = new dfd(10006);
        dfdVar.b(cfiVar);
        dfdVar.onDataChanged(rag.a(), ckmVar);
        qyr.c(2, 4, new String[]{"weight"});
    }

    public static void d(final Context context, final ResponseCallback<Integer> responseCallback) {
        if (!TextUtils.isEmpty(rag.a(10006, "health_weight_upto_user_info_dialog")) || !Utils.i()) {
            e(responseCallback, -1);
            return;
        }
        if (HandlerExecutor.b()) {
            ThreadPoolManager.d().execute(new Runnable() { // from class: qzj
                @Override // java.lang.Runnable
                public final void run() {
                    qyw.d(context, responseCallback);
                }
            });
            return;
        }
        HiUserPreference userPreference = HiHealthManager.d(BaseApplication.e()).getUserPreference("custom.weight_auto_update_status");
        if (userPreference != null && "1".equals(userPreference.getValue())) {
            e(responseCallback, -1);
        } else {
            HandlerExecutor.e(new Runnable() { // from class: qzr
                @Override // java.lang.Runnable
                public final void run() {
                    qyw.b(context, responseCallback);
                }
            });
        }
    }

    static /* synthetic */ void b(Context context, final ResponseCallback responseCallback) {
        NoTitleCustomAlertDialog e = new NoTitleCustomAlertDialog.Builder(rag.c(context)).e(nsf.h(R$string.IDS_smartcard_auto_update_weight_content)).czE_(nsf.h(R$string.IDS_common_notification_know_tips).toUpperCase(Locale.ENGLISH), new View.OnClickListener() { // from class: qza
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                qyw.dJe_(view);
            }
        }).e();
        if (e == null) {
            ReleaseLogUtil.a("HealthWeight_WeightDialogUtils", "showAutoUpdateWeightDialog dialog is null activity ", context, " callback ", responseCallback);
            b((ResponseCallback<Integer>) responseCallback);
        } else {
            e.show();
            e.setOnDismissListener(new DialogInterface.OnDismissListener() { // from class: qyz
                @Override // android.content.DialogInterface.OnDismissListener
                public final void onDismiss(DialogInterface dialogInterface) {
                    qyw.dJf_(ResponseCallback.this, dialogInterface);
                }
            });
            e((ResponseCallback<Integer>) responseCallback, 0);
        }
    }

    static /* synthetic */ void dJe_(View view) {
        rag.c(10006, "health_weight_upto_user_info_dialog", String.valueOf(true));
        rag.c(10000, "health_weight_auto_update", "1");
        ThreadPoolManager.d().execute(new Runnable() { // from class: qzf
            @Override // java.lang.Runnable
            public final void run() {
                qyw.c();
            }
        });
        ViewClickInstrumentation.clickOnView(view);
    }

    static /* synthetic */ void c() {
        HiUserPreference hiUserPreference = new HiUserPreference();
        hiUserPreference.setKey("custom.weight_auto_update_status");
        hiUserPreference.setValue("1");
        HiHealthManager.d(BaseApplication.e()).setUserPreference(hiUserPreference);
    }

    static /* synthetic */ void dJf_(ResponseCallback responseCallback, DialogInterface dialogInterface) {
        rag.c(10006, "health_weight_upto_user_info_dialog", String.valueOf(false));
        e((ResponseCallback<Integer>) responseCallback, 1);
    }

    public static void h(final Context context, final ResponseCallback<Integer> responseCallback) {
        if (!sdk.a(Integer.toString(PrebakedEffectId.RT_HEARTBEAT), "WEIGHT_SET_GOAL_DIALOG", 2419200000L)) {
            e(responseCallback, -1);
            return;
        }
        final Context e = BaseApplication.e();
        View cKr_ = nsf.cKr_(e, R.layout.weight_set_goal_dialog, null);
        if (cKr_ == null) {
            ReleaseLogUtil.a("HealthWeight_WeightDialogUtils", "showSetGoalDialog rootView is null activity ", context, " callback ", responseCallback);
            b(responseCallback);
            return;
        }
        final AtomicInteger atomicInteger = new AtomicInteger(1);
        final CustomAlertDialog c = new CustomAlertDialog.Builder(rag.c(context)).cyp_(cKr_).c();
        cKr_.findViewById(R.id.set_goal).setOnClickListener(new View.OnClickListener() { // from class: qzh
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                qyw.dJj_(atomicInteger, e, c, context, responseCallback, view);
            }
        });
        cKr_.findViewById(R.id.next_show).setOnClickListener(new View.OnClickListener() { // from class: qzl
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                qyw.dJk_(CustomAlertDialog.this, context, responseCallback, view);
            }
        });
        if (c == null) {
            ReleaseLogUtil.a("HealthWeight_WeightDialogUtils", "showSetGoalDialog dialog is null activity ", context, " callback ", responseCallback);
            b(responseCallback);
        } else {
            d(responseCallback, atomicInteger, c);
        }
    }

    static /* synthetic */ void dJj_(AtomicInteger atomicInteger, Context context, CustomAlertDialog customAlertDialog, Context context2, ResponseCallback responseCallback, View view) {
        atomicInteger.set(2);
        AppRouter.b("/Main/WeightGoalActivity").c(context);
        if (customAlertDialog == null) {
            ReleaseLogUtil.a("HealthWeight_WeightDialogUtils", "showSetGoalDialog dialog is null view ", view, " activity ", context2, " callback ", responseCallback);
            ViewClickInstrumentation.clickOnView(view);
        } else {
            customAlertDialog.dismiss();
            ViewClickInstrumentation.clickOnView(view);
        }
    }

    static /* synthetic */ void dJk_(CustomAlertDialog customAlertDialog, Context context, ResponseCallback responseCallback, View view) {
        if (customAlertDialog == null) {
            ReleaseLogUtil.a("HealthWeight_WeightDialogUtils", "showSetGoalDialog dialog is null view ", view, " activity ", context, " callback ", responseCallback);
            ViewClickInstrumentation.clickOnView(view);
        } else {
            customAlertDialog.dismiss();
            ViewClickInstrumentation.clickOnView(view);
        }
    }

    public static void e(Context context, ResponseCallback<Integer> responseCallback) {
        final Context c = rag.c(context);
        final AtomicInteger atomicInteger = new AtomicInteger(1);
        NoTitleCustomAlertDialog e = new NoTitleCustomAlertDialog.Builder(c).e(nsf.h(R$string.IDS_hw_plugin_device_selection_click_bind_my_device_select)).czE_(nsf.h(R$string.IDS_hw_health_show_common_dialog_ok_button), new View.OnClickListener() { // from class: qzg
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                qyw.dJg_(atomicInteger, c, view);
            }
        }).czA_(nsf.h(R$string.IDS_hw_health_show_common_dialog_cancle_button), new View.OnClickListener() { // from class: qze
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ViewClickInstrumentation.clickOnView(view);
            }
        }).e();
        if (e == null) {
            ReleaseLogUtil.a("HealthWeight_WeightDialogUtils", "showBindDeviceDialog dialog is null activity ", context, " callback ", responseCallback);
            b(responseCallback);
        } else {
            d(responseCallback, atomicInteger, e);
        }
    }

    static /* synthetic */ void dJg_(AtomicInteger atomicInteger, Context context, View view) {
        atomicInteger.set(2);
        qyr.e();
        dks.e(context, "HDK_WEIGHT");
        ViewClickInstrumentation.clickOnView(view);
    }

    public static void f(final Context context, final ResponseCallback<Integer> responseCallback) {
        cfi currentUser = MultiUsersManager.INSTANCE.getCurrentUser();
        if (currentUser != null) {
            b(context, responseCallback, currentUser);
        } else {
            HiHealthManager.d(BaseApplication.e()).fetchUserData(new HiCommonListener() { // from class: qyw.5
                @Override // com.huawei.hihealth.data.listener.HiCommonListener
                public void onSuccess(int i, Object obj) {
                    OpAnalyticsUtil.getInstance().setEventOneErrorCode(OperationKey.HEALTH_APP_WEIGHT_GET_USER_INFORMATION_85070011.value(), CommonUtils.h("0"));
                    if (!koq.e(obj, HiUserInfo.class)) {
                        qyw.b(context, responseCallback, null);
                        return;
                    }
                    for (HiUserInfo hiUserInfo : (List) obj) {
                        if (hiUserInfo != null && hiUserInfo.getRelateType() == 0) {
                            qyw.b(context, responseCallback, hiUserInfo);
                            return;
                        }
                    }
                    qyw.b(context, responseCallback, null);
                }

                @Override // com.huawei.hihealth.data.listener.HiCommonListener
                public void onFailure(int i, Object obj) {
                    OpAnalyticsUtil.getInstance().setEventOneErrorCode(OperationKey.HEALTH_APP_WEIGHT_GET_USER_INFORMATION_85070011.value(), i);
                    qyw.b(context, responseCallback, null);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void b(final Context context, final ResponseCallback<Integer> responseCallback, final Object obj) {
        if (dks.a(obj) || !nry.c()) {
            e(responseCallback, -1);
            return;
        }
        if (!HandlerExecutor.b()) {
            HandlerExecutor.e(new Runnable() { // from class: qzq
                @Override // java.lang.Runnable
                public final void run() {
                    qyw.b(context, responseCallback, obj);
                }
            });
            return;
        }
        final Context c = rag.c(context);
        final AtomicInteger atomicInteger = new AtomicInteger(1);
        CustomTextAlertDialog a2 = new CustomTextAlertDialog.Builder(c).b(R$string.IDS_profile_dialog_title).d(R$string.IDS_profile_dialog_content).cyU_(R$string.IDS_profile_dialog_go_perfect, new View.OnClickListener() { // from class: qzo
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                qyw.dJo_(atomicInteger, c, view);
            }
        }).cyR_(R$string.IDS_main_btn_state_ignore, new View.OnClickListener() { // from class: qzs
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ViewClickInstrumentation.clickOnView(view);
            }
        }).a();
        if (a2 == null) {
            ReleaseLogUtil.a("HealthWeight_WeightDialogUtils", "showUserInfoDialogForMeasureStart dialog is null activity ", context, " callback ", responseCallback, " object ", obj);
            b(responseCallback);
        } else {
            a2.setCancelable(false);
            d(responseCallback, atomicInteger, a2);
            dks.c();
        }
    }

    static /* synthetic */ void dJo_(AtomicInteger atomicInteger, Context context, View view) {
        atomicInteger.set(2);
        dks.d(context, true);
        ViewClickInstrumentation.clickOnView(view);
    }

    public static void j(Context context, ResponseCallback<Integer> responseCallback) {
        if (!nry.c() || dks.a(MultiUsersManager.INSTANCE.getCurrentUser()) || nry.d("key_person_info_setting_dialog_show_count_") > 2) {
            e(responseCallback, -1);
            return;
        }
        String a2 = rag.a(OrderStatusCode.ORDER_STATE_PARAM_ERROR, "isShowDetailDialog");
        if (TextUtils.isEmpty(a2)) {
            a2 = String.valueOf(true);
        }
        if (!String.valueOf(true).equals(a2)) {
            e(responseCallback, -1);
            return;
        }
        View cKr_ = nsf.cKr_(BaseApplication.e(), R.layout.dialog_perfected_user_info, null);
        if (cKr_ == null) {
            ReleaseLogUtil.a("HealthWeight_WeightDialogUtils", "showUserInfoDialogForMeasureFinish rootView is null activity ", context, " callback ", responseCallback);
            b(responseCallback);
            return;
        }
        final Context c = rag.c(context);
        final AtomicInteger atomicInteger = new AtomicInteger(1);
        CustomAlertDialog c2 = new CustomAlertDialog.Builder(c).cyp_(cKr_).e(R$string.IDS_userInfo_dialog_title).cyo_(R$string.IDS_profile_dialog_go_perfect, new DialogInterface.OnClickListener() { // from class: qzm
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                qyw.dJl_(atomicInteger, c, dialogInterface, i);
            }
        }).cyn_(R$string.IDS_settings_button_cancal_ios_btn, new DialogInterface.OnClickListener() { // from class: qzk
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                ViewClickInstrumentation.clickOnDialog(dialogInterface, i);
            }
        }).c();
        if (c2 == null) {
            ReleaseLogUtil.a("HealthWeight_WeightDialogUtils", "showUserInfoDialogForMeasureFinish dialog is null activity ", context, " callback ", responseCallback);
            b(responseCallback);
        } else {
            c2.setCancelable(false);
            ((HealthCheckBox) cKr_.findViewById(R.id.remind_checkbox)).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.huawei.ui.main.stories.health.weight.util.WeightDialogUtils$$ExternalSyntheticLambda25
                @Override // android.widget.CompoundButton.OnCheckedChangeListener
                public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                    qyw.dJn_(compoundButton, z);
                }
            });
            d(responseCallback, atomicInteger, c2);
        }
    }

    static /* synthetic */ void dJl_(AtomicInteger atomicInteger, Context context, DialogInterface dialogInterface, int i) {
        atomicInteger.set(2);
        dks.d(context, false);
        ViewClickInstrumentation.clickOnDialog(dialogInterface, i);
    }

    public static /* synthetic */ void dJn_(CompoundButton compoundButton, boolean z) {
        rag.c(OrderStatusCode.ORDER_STATE_PARAM_ERROR, "isShowDetailDialog", String.valueOf(!z));
        ViewClickInstrumentation.clickOnView(compoundButton);
    }

    public static void c(Context context, ResponseCallback<Integer> responseCallback, float f, int i, String str) {
        if (TextUtils.isEmpty(str)) {
            ReleaseLogUtil.a("HealthWeight_WeightDialogUtils", "showGoalMotivationDialog json ", str, " activity ", context, " callback ", responseCallback, " bodyWeightDiff ", Float.valueOf(f), " progress ", Integer.valueOf(i));
            b(responseCallback);
            return;
        }
        WeightTargetDifferences weightTargetDifferences = (WeightTargetDifferences) HiJsonUtil.e(str, WeightTargetDifferences.class);
        if (weightTargetDifferences == null) {
            ReleaseLogUtil.a("HealthWeight_WeightDialogUtils", "showGoalMotivationDialog differences is null activity ", context, " callback ", responseCallback, " bodyWeightDiff ", Float.valueOf(f), " progress ", Integer.valueOf(i), " json ", str);
            b(responseCallback);
            return;
        }
        long e = weightTargetDifferences.e();
        long b = weightTargetDifferences.b();
        LogUtil.c("HealthWeight_WeightDialogUtils", "showGoalMotivationDialog beginDate ", Long.valueOf(e), " finishDate ", Long.valueOf(b), " activity ", context, " callback ", responseCallback, " bodyWeightDiff ", Float.valueOf(f), " progress ", Integer.valueOf(i), " json ", str);
        if (e <= 0 || b <= 0) {
            b(responseCallback);
            return;
        }
        qry.d dVar = new qry.d();
        dVar.d(weightTargetDifferences.d() == WeightTargetDifferences.WeightTargetType.WEIGHT_LOSS ? 2 : 1);
        dVar.d(f);
        dVar.b(i);
        qry.b(rag.c(context), e, b, dVar, responseCallback);
    }
}
