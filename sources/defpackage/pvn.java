package defpackage;

import android.app.Activity;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import com.huawei.haf.application.BaseApplication;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwcommonmodel.utils.DateFormatUtil;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.threecircle.bean.AchieveAniType;
import com.huawei.ui.commonui.dialog.AchieveDialogFactory;
import com.huawei.ui.commonui.dialog.BaseAchieveDialog;
import com.huawei.ui.commonui.dialog.DialogResourcesListener;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.io.Serializable;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.TimeZone;

/* loaded from: classes6.dex */
public class pvn {

    /* renamed from: a, reason: collision with root package name */
    private long f16274a = System.currentTimeMillis();
    private BaseAchieveDialog d;
    private final Activity e;

    public pvn(Activity activity) {
        this.e = activity;
    }

    public void b(long j) {
        this.f16274a = j;
    }

    public void e(int i, int i2) {
        if (i2 < i) {
            LogUtil.a("SCUI_WalkAchieveDialogHelper", "putSingleAchieveDialog currentValue < goalValue");
            return;
        }
        long b = SharedPreferenceManager.b(Integer.toString(10000), AchieveAniType.SINGLE_CIRCLE_WALK.mSpKey, 0L);
        LogUtil.a("SCUI_WalkAchieveDialogHelper", "showWalkAchieveDialog showWalkAchieveDialog:", Long.valueOf(b), "mCurrentTime:", Long.valueOf(this.f16274a));
        if (!jdl.d(System.currentTimeMillis(), this.f16274a)) {
            LogUtil.a("SCUI_WalkAchieveDialogHelper", "showWalkAchieveDialog isSameDay");
            return;
        }
        if (jdl.d(System.currentTimeMillis(), b)) {
            LogUtil.a("SCUI_WalkAchieveDialogHelper", "showWalkAchieveDialog isSameDay");
            return;
        }
        Bundle bundle = new Bundle();
        bundle.putSerializable("three_circle_callback", e(i2));
        bundle.putInt("target_value", i);
        bundle.putInt("achieve_user_value", i2);
        if (!e()) {
            duo_(bundle);
            return;
        }
        ArrayList arrayList = new ArrayList(1);
        arrayList.add("900200011");
        njh.c(arrayList, new d(bundle));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void duo_(Bundle bundle) {
        if (this.d == null) {
            this.d = dum_(bundle, System.currentTimeMillis());
        }
        BaseAchieveDialog baseAchieveDialog = this.d;
        if (baseAchieveDialog != null) {
            baseAchieveDialog.show();
            this.d.setGotoDetailBtnClickListener(new View.OnClickListener() { // from class: pvl
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    pvn.dun_(view);
                }
            });
        }
        c();
    }

    static /* synthetic */ void dun_(View view) {
        pxp.d(5);
        ViewClickInstrumentation.clickOnView(view);
    }

    public void dup_(Configuration configuration) {
        BaseAchieveDialog baseAchieveDialog = this.d;
        if (baseAchieveDialog != null) {
            baseAchieveDialog.onConfigurationChanged(configuration);
        }
    }

    private boolean e() {
        long c = DateFormatUtil.c(SharedPreferenceManager.a("threeCircleSp", "hw.sport.threecircle", 0), TimeZone.getDefault());
        long b = SharedPreferenceManager.b("threeCircleSp", "devicesConnectTime", 0L);
        long currentTimeMillis = System.currentTimeMillis();
        if (c > currentTimeMillis || b > currentTimeMillis) {
            health.compact.a.LogUtil.e("SCUI_WalkAchieveDialogHelper", "connect in future with ", Long.valueOf(c), ":", Long.valueOf(b));
            return false;
        }
        long d2 = jdl.d(currentTimeMillis, -2);
        return c > d2 || b > d2;
    }

    private void c() {
        HashMap hashMap = new HashMap(2);
        hashMap.put("click", 1);
        hashMap.put("type", AchieveAniType.SINGLE_CIRCLE_WALK);
        ixx.d().d(BaseApplication.e(), AnalyticsValue.HEALTH_MODE_COMPLETE_2010118.value(), hashMap, 0);
    }

    private BaseAchieveDialog dum_(Bundle bundle, final long j) {
        return AchieveDialogFactory.c().cxI_(this.e, AchieveAniType.SINGLE_CIRCLE_WALK.mDialogType, bundle, new DialogResourcesListener() { // from class: pvn.5
            @Override // com.huawei.ui.commonui.dialog.DialogResourcesListener
            public void onResourcesDownloadFailed() {
            }

            @Override // com.huawei.ui.commonui.dialog.DialogResourcesListener
            public void onShown() {
                ReleaseLogUtil.e("SCUI_WalkAchieveDialogHelper", "setLatestTaskDoneTime key:", AchieveAniType.SINGLE_CIRCLE_WALK.mSpKey, " time:", Long.valueOf(j));
                SharedPreferenceManager.e(Integer.toString(10000), AchieveAniType.SINGLE_CIRCLE_WALK.mSpKey, j);
            }
        });
    }

    private Serializable e(int i) {
        return new pvs(i);
    }

    static /* synthetic */ void a(int i, boolean z) {
        ReleaseLogUtil.e("SCUI_WalkAchieveDialogHelper", "Go share dealStepData.");
        pvk.a(i, System.currentTimeMillis());
    }

    static class d implements IBaseResponseCallback {
        private final Bundle c;
        private final WeakReference<pvn> e;

        private d(pvn pvnVar, Bundle bundle) {
            this.e = new WeakReference<>(pvnVar);
            this.c = bundle;
        }

        @Override // com.huawei.hwbasemgr.IBaseResponseCallback
        /* renamed from: onResponse */
        public void d(int i, Object obj) {
            pvn pvnVar = this.e.get();
            if (pvnVar == null) {
                health.compact.a.LogUtil.a("SCUI_WalkAchieveDialogHelper", "onResponse: achieveHandler is null");
                return;
            }
            if (!(obj instanceof HashMap)) {
                LogUtil.h("SCUI_WalkAchieveDialogHelper", "onResponse: objData is not instanceof HashMap");
                return;
            }
            String b = nru.b((HashMap) obj, "900200011", "1");
            ReleaseLogUtil.e("SCUI_WalkAchieveDialogHelper", "remind switch is ", b);
            if ("0".equals(b)) {
                pvnVar.duo_(this.c);
            } else {
                SharedPreferenceManager.e(Integer.toString(10000), AchieveAniType.SINGLE_CIRCLE_WALK.mSpKey, System.currentTimeMillis());
            }
        }
    }
}
