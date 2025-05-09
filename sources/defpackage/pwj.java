package defpackage;

import android.content.Context;
import android.content.DialogInterface;
import com.huawei.haf.application.BaseApplication;
import com.huawei.health.userprofilemgr.model.BaseResponseCallback;
import com.huawei.healthcloud.plugintrack.callback.CommonSingleCallback;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hihealth.dictionary.utils.DicDataTypeUtil;
import com.huawei.hihealth.util.HiJsonUtil;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.base.CommonUiBaseResponse;
import com.huawei.ui.commonui.dialog.CustomAlertDialog;
import com.huawei.ui.main.R$string;
import com.huawei.ui.main.stories.health.views.FitnessDataOriginView;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/* loaded from: classes6.dex */
public class pwj {
    private static volatile pwj c;
    private static final Object d = new Object();
    private CustomAlertDialog e;
    private boolean f;
    private volatile List<pwb> g = new ArrayList();

    /* renamed from: a, reason: collision with root package name */
    private List<Integer> f16300a = Arrays.asList(2, 7, 3, 4, 5, Integer.valueOf(DicDataTypeUtil.DataType.ACTIVE_HOUR_IS_ACTIVE.value()));
    private List<CommonSingleCallback<Boolean>> b = new ArrayList();

    private pwj() {
    }

    public static pwj e() {
        if (c == null) {
            synchronized (pwj.class) {
                if (c == null) {
                    c = new pwj();
                }
            }
        }
        return c;
    }

    public void c() {
        boolean a2 = niv.a(BaseApplication.e());
        this.f = a2;
        if (a2) {
            i();
        } else {
            f();
        }
    }

    private void f() {
        pwm.a().d(this.f16300a, System.currentTimeMillis(), new c(this));
    }

    private void i() {
        pwm.a().a(jdl.t(System.currentTimeMillis()), new CommonUiBaseResponse() { // from class: pwk
            @Override // com.huawei.ui.commonui.base.CommonUiBaseResponse
            public final void onResponse(int i, Object obj) {
                pwj.this.a(i, obj);
            }
        });
    }

    /* synthetic */ void a(int i, Object obj) {
        if (i != 0) {
            j();
            d(false);
            return;
        }
        if (!koq.e(obj, pwb.class)) {
            LogUtil.h("Suggestion_FitnessOriginDataHelper", "msg.obj not instanceof List");
            j();
            d(false);
        } else if (!(obj instanceof List)) {
            j();
            d(false);
        } else {
            this.g = (List) obj;
            LogUtil.a("Suggestion_FitnessOriginDataHelper", "queryFitnessOriginData: size is ", HiJsonUtil.e(this.g));
            d(true);
        }
    }

    public void j() {
        if (this.g == null) {
            this.g = new ArrayList();
        } else {
            this.g.clear();
        }
    }

    public int b() {
        if (koq.b(this.g)) {
            return 0;
        }
        return this.g.size();
    }

    public List<pwb> d() {
        return this.g;
    }

    public boolean a() {
        return b() > 1;
    }

    public void a(Context context, int i) {
        CustomAlertDialog customAlertDialog = this.e;
        if (customAlertDialog == null || !customAlertDialog.isShowing()) {
            FitnessDataOriginView fitnessDataOriginView = new FitnessDataOriginView(context);
            fitnessDataOriginView.setIsOnlyStepSource(this.f);
            fitnessDataOriginView.setFromType(i);
            fitnessDataOriginView.setmListdata(this.g);
            CustomAlertDialog.Builder builder = new CustomAlertDialog.Builder(context);
            builder.a(context.getString(R$string.IDS_device_data_source)).cyp_(fitnessDataOriginView).cyo_(R$string.IDS_common_notification_know_tips, new DialogInterface.OnClickListener() { // from class: pwi
                @Override // android.content.DialogInterface.OnClickListener
                public final void onClick(DialogInterface dialogInterface, int i2) {
                    pwj.dux_(dialogInterface, i2);
                }
            });
            CustomAlertDialog c2 = builder.c();
            this.e = c2;
            c2.show();
            b(context, i);
        }
    }

    static /* synthetic */ void dux_(DialogInterface dialogInterface, int i) {
        LogUtil.a("Suggestion_FitnessOriginDataHelper", "showFitnessDataOriginDialog() PositiveButton onClick.");
        ViewClickInstrumentation.clickOnDialog(dialogInterface, i);
    }

    public void d(CommonSingleCallback<Boolean> commonSingleCallback) {
        if (commonSingleCallback != null) {
            synchronized (d) {
                this.b.add(commonSingleCallback);
            }
        }
    }

    public void b(CommonSingleCallback<Boolean> commonSingleCallback) {
        synchronized (d) {
            if (this.b.contains(commonSingleCallback)) {
                this.b.remove(commonSingleCallback);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(boolean z) {
        List<CommonSingleCallback> synchronizedList = Collections.synchronizedList(this.b);
        synchronized (d) {
            for (CommonSingleCallback commonSingleCallback : synchronizedList) {
                if (commonSingleCallback != null) {
                    commonSingleCallback.callback(Boolean.valueOf(z));
                }
            }
        }
    }

    private void b(Context context, int i) {
        HashMap hashMap = new HashMap(16);
        hashMap.put("click", 1);
        hashMap.put("from", Integer.valueOf(i));
        ixx.d().d(context, AnalyticsValue.HEALTH_HOME_DATA_ORIGIN_2010068.value(), hashMap, 0);
    }

    static class c implements BaseResponseCallback<List<pwb>> {

        /* renamed from: a, reason: collision with root package name */
        private final WeakReference<pwj> f16301a;

        c(pwj pwjVar) {
            this.f16301a = new WeakReference<>(pwjVar);
        }

        @Override // com.huawei.health.userprofilemgr.model.BaseResponseCallback
        /* renamed from: e, reason: merged with bridge method [inline-methods] */
        public void onResponse(int i, List<pwb> list) {
            pwj pwjVar = this.f16301a.get();
            if (pwjVar == null) {
                ReleaseLogUtil.c("Suggestion_FitnessOriginDataHelper", "helper is null");
                return;
            }
            pwjVar.j();
            if (koq.c(list)) {
                pwjVar.g = list;
            }
            Object[] objArr = new Object[4];
            objArr[0] = "queryStepSourceDataByTypes: queryTypes =  ";
            objArr[1] = HiJsonUtil.e(pwjVar.f16300a);
            objArr[2] = "result size is ";
            objArr[3] = koq.b(list) ? "empty" : Integer.valueOf(list.size());
            LogUtil.a("Suggestion_FitnessOriginDataHelper", objArr);
            pwjVar.d(koq.c(list));
        }
    }
}
