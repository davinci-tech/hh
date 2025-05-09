package com.huawei.ui.homehealth.stepscard;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.SparseArray;
import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.handler.HandlerExecutor;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwcommonmodel.utils.DateFormatUtil;
import com.huawei.threecircle.api.ThreeCircleApi;
import com.huawei.ui.commonui.dialog.AchieveDialogFactory;
import com.huawei.ui.commonui.dialog.BaseAchieveDialog;
import com.huawei.ui.commonui.dialog.DialogResourcesListener;
import com.huawei.ui.homehealth.stepscard.AchieveTaskHandler;
import defpackage.ixx;
import defpackage.jdl;
import defpackage.njh;
import defpackage.nru;
import defpackage.ovg;
import health.compact.a.CommonUtil;
import health.compact.a.LogUtil;
import health.compact.a.ReleaseLogUtil;
import health.compact.a.Services;
import health.compact.a.SharedPreferenceManager;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.TimeZone;

/* loaded from: classes6.dex */
public class AchieveTaskHandler {
    private WeakReference<ovg> e;
    private final SparseArray<Long> b = new SparseArray<>();
    private final SparseArray<WeakReference<BaseAchieveDialog>> d = new SparseArray<>();
    private Handler c = new Handler(Looper.getMainLooper()) { // from class: com.huawei.ui.homehealth.stepscard.AchieveTaskHandler.5
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            super.handleMessage(message);
            AchieveTaskHandler.this.dhw_(message);
        }
    };

    /* JADX INFO: Access modifiers changed from: private */
    public void dhw_(Message message) {
        AchieveAniType typeOf = AchieveAniType.typeOf(message.what);
        if (typeOf == null) {
            ReleaseLogUtil.b("SCUI_AchieveDialogHandler", "processTaskMsg failed with wrong type=", Integer.valueOf(message.what));
            return;
        }
        Bundle bundle = message.obj instanceof Bundle ? (Bundle) message.obj : new Bundle();
        long j = bundle.getLong("task_time", System.currentTimeMillis());
        if (typeOf.mAchieveStrKey != null && typeOf.mAchieveStrKey.length() != 0) {
            bundle.putString("achieveText", a(typeOf.mAchieveStrKey));
        }
        Activity wa_ = BaseApplication.wa_();
        if (wa_ == null || !wa_.getLocalClassName().contains("MainActivity")) {
            LogUtil.a("SCUI_AchieveDialogHandler", "doTask mainActivity is not show");
            c(typeOf, j);
            return;
        }
        BaseAchieveDialog dhx_ = dhx_(typeOf, bundle, j);
        if (dhx_ == null) {
            c(typeOf, j);
            ReleaseLogUtil.b("SCUI_AchieveDialogHandler", "processTaskMsg failed with dialog is null type=", typeOf);
            return;
        }
        ovg ovgVar = this.e.get();
        if (ovgVar != null) {
            dhx_.setGotoDetailBtnClickListener(ovgVar.diu_(typeOf));
        }
        dhx_.show();
        this.d.put(typeOf.mType, new WeakReference<>(dhx_));
        ReleaseLogUtil.b("SCUI_AchieveDialogHandler", " show dialog type is:", Integer.valueOf(typeOf.mType));
        a(typeOf.mType);
    }

    private BaseAchieveDialog dhx_(final AchieveAniType achieveAniType, Bundle bundle, final long j) {
        ovg ovgVar = this.e.get();
        if (ovgVar == null) {
            LogUtil.a("SCUI_AchieveDialogHandler", "getAchieveDialog cardData == null");
            return null;
        }
        Context context = ovgVar.mContext;
        if (context == null) {
            LogUtil.a("SCUI_AchieveDialogHandler", "getAchieveDialog context == null");
            return null;
        }
        return AchieveDialogFactory.c().cxI_(context, achieveAniType.mDialogType, bundle, new DialogResourcesListener() { // from class: com.huawei.ui.homehealth.stepscard.AchieveTaskHandler.3
            @Override // com.huawei.ui.commonui.dialog.DialogResourcesListener
            public void onResourcesDownloadFailed() {
                AchieveTaskHandler.this.c(achieveAniType, j);
            }

            @Override // com.huawei.ui.commonui.dialog.DialogResourcesListener
            public void onShown() {
                AchieveTaskHandler.this.b(achieveAniType, j);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(AchieveAniType achieveAniType, long j) {
        ReleaseLogUtil.a("SCUI_AchieveDialogHandler", " taskFailedProcess. pls check,dialog type", Integer.valueOf(achieveAniType.mType), " dialog create time:", Long.valueOf(j));
        this.b.remove(achieveAniType.mType);
        this.d.remove(achieveAniType.mType);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(AchieveAniType achieveAniType, long j) {
        ReleaseLogUtil.a("SCUI_AchieveDialogHandler", " taskSuccessProcess. dialog type:", Integer.valueOf(achieveAniType.mType), " dialog create time:", Long.valueOf(j));
        this.b.put(achieveAniType.mType, Long.valueOf(j));
        c(achieveAniType.mSpKey, j);
    }

    public void c(ovg ovgVar) {
        this.e = new WeakReference<>(ovgVar);
    }

    /* renamed from: dhz_, reason: merged with bridge method [inline-methods] */
    public void dhA_(final AchieveAniType achieveAniType, final Bundle bundle) {
        if (achieveAniType == null) {
            ReleaseLogUtil.b("SCUI_AchieveDialogHandler", " addAchieveTask  failed type is null.");
            return;
        }
        if (!HandlerExecutor.c()) {
            HandlerExecutor.a(new Runnable() { // from class: oub
                @Override // java.lang.Runnable
                public final void run() {
                    AchieveTaskHandler.this.dhA_(achieveAniType, bundle);
                }
            });
            return;
        }
        if (e(achieveAniType)) {
            Bundle bundle2 = bundle == null ? new Bundle() : bundle;
            long currentTimeMillis = System.currentTimeMillis();
            bundle2.putLong("task_time", currentTimeMillis);
            this.b.put(achieveAniType.mType, Long.valueOf(currentTimeMillis));
            ReleaseLogUtil.b("SCUI_AchieveDialogHandler", " addAchieveTask success. type=", achieveAniType, " time=", Long.valueOf(currentTimeMillis));
            if (AchieveAniType.THREE_CIRCLE.equals(achieveAniType) || AchieveAniType.SINGLE_CIRCLE_WALK.equals(achieveAniType)) {
                if (!b()) {
                    dhy_(achieveAniType, bundle);
                    return;
                }
                ArrayList arrayList = new ArrayList(1);
                arrayList.add("900200011");
                njh.c(arrayList, new a(achieveAniType, bundle));
                return;
            }
            dhy_(achieveAniType, bundle);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void dhy_(AchieveAniType achieveAniType, Bundle bundle) {
        Message obtainMessage = this.c.obtainMessage(achieveAniType.mType);
        obtainMessage.obj = bundle;
        if (AchieveAniType.isSubType(achieveAniType)) {
            this.c.sendMessageDelayed(obtainMessage, 1000L);
        } else {
            d(achieveAniType);
            this.c.sendMessage(obtainMessage);
        }
    }

    private void d(AchieveAniType achieveAniType) {
        List<AchieveAniType> subTypes = achieveAniType.getSubTypes();
        ArrayList arrayList = new ArrayList();
        for (AchieveAniType achieveAniType2 : subTypes) {
            this.c.removeMessages(achieveAniType2.mType);
            this.b.remove(achieveAniType2.mType);
            arrayList.add(Integer.valueOf(achieveAniType2.mType));
        }
        ReleaseLogUtil.b("SCUI_AchieveDialogHandler", " removeSubTypeTask success. superType=", achieveAniType, " subTypes=", arrayList);
    }

    public boolean e(AchieveAniType achieveAniType) {
        if (achieveAniType == null) {
            ReleaseLogUtil.b("SCUI_AchieveDialogHandler", " hasAddedAchieveTask failed. type is null.");
            return false;
        }
        AchieveAniType superType = achieveAniType.getSuperType();
        long b = superType != null ? b(superType) : 0L;
        long currentTimeMillis = System.currentTimeMillis();
        long max = Math.max(b, b(achieveAniType));
        return currentTimeMillis > max && !jdl.d(currentTimeMillis, max);
    }

    private long b(AchieveAniType achieveAniType) {
        long longValue = this.b.get(achieveAniType.mType, -1L).longValue();
        if (longValue != -1) {
            return longValue;
        }
        long c = c(achieveAniType.mSpKey);
        this.b.put(achieveAniType.mType, Long.valueOf(c));
        return c;
    }

    private long c(String str) {
        if (str.equals(AchieveAniType.THREE_LEAF_ACTIVE.mSpKey) || str.equals(AchieveAniType.THREE_LEAF_PERFECT.mSpKey)) {
            String b = SharedPreferenceManager.b(BaseApplication.e(), Integer.toString(10000), str);
            ReleaseLogUtil.b("SCUI_AchieveDialogHandler", "getLatestTaskDoneTime key:", str, " time:", b);
            long g = CommonUtil.g(b);
            if (g != 0) {
                c(str, g);
            }
        }
        long b2 = SharedPreferenceManager.b(Integer.toString(10000), str, 0L);
        ReleaseLogUtil.b("SCUI_AchieveDialogHandler", "getLatestTaskDoneTime key:", str, " time:", Long.valueOf(b2));
        return b2;
    }

    private void c(String str, long j) {
        ReleaseLogUtil.b("SCUI_AchieveDialogHandler", "setLatestTaskDoneTime key:", str, " time:", Long.valueOf(j));
        SharedPreferenceManager.e(Integer.toString(10000), str, j);
    }

    private boolean b() {
        long c = DateFormatUtil.c(SharedPreferenceManager.a("threeCircleSp", "hw.sport.threecircle", 0), TimeZone.getDefault());
        long b = SharedPreferenceManager.b("threeCircleSp", "devicesConnectTime", 0L);
        long currentTimeMillis = System.currentTimeMillis();
        if (c > currentTimeMillis || b > currentTimeMillis) {
            LogUtil.e("SCUI_AchieveDialogHandler", "connect in future with ", Long.valueOf(c), ":", Long.valueOf(b));
            return false;
        }
        long d = jdl.d(currentTimeMillis, -2);
        return c > d || b > d;
    }

    private String a(String str) {
        final ThreeCircleApi threeCircleApi = (ThreeCircleApi) Services.c("DailyActivity", ThreeCircleApi.class);
        String promptMessage = threeCircleApi.getPromptMessage(threeCircleApi.queryRules("TodayAchievement", str), new HashMap());
        LogUtil.c("SCUI_AchieveDialogHandler", "getAchieveString achieveType=", str, " promptMessage=", promptMessage);
        ThreadPoolManager.d().execute(new Runnable() { // from class: ouc
            @Override // java.lang.Runnable
            public final void run() {
                ThreeCircleApi.this.checkUpdateForThreeCircle();
            }
        });
        return promptMessage;
    }

    private void a(int i) {
        HashMap hashMap = new HashMap(2);
        hashMap.put("click", 1);
        hashMap.put("type", Integer.valueOf(i));
        ixx.d().d(BaseApplication.e(), AnalyticsValue.HEALTH_MODE_COMPLETE_2010118.value(), hashMap, 0);
    }

    public void dhB_(Configuration configuration) {
        BaseAchieveDialog baseAchieveDialog;
        for (int i = 0; i < this.d.size(); i++) {
            WeakReference<BaseAchieveDialog> valueAt = this.d.valueAt(i);
            if (valueAt != null && (baseAchieveDialog = valueAt.get()) != null) {
                baseAchieveDialog.onConfigurationChanged(configuration);
            }
        }
    }

    public enum AchieveAniType {
        THREE_LEAF_ACTIVE(1, "vibrancy_today", 2, AchieveDialogFactory.DialogType.THREE_LEAF_ACTIVE),
        THREE_LEAF_PERFECT(2, "perfect_today", 0, AchieveDialogFactory.DialogType.THREE_LEAF_PERFECT),
        THREE_CIRCLE(3, "achieved_show_time", 0, AchieveDialogFactory.DialogType.THREE_CIRCLE, "DoneAll"),
        SINGLE_CIRCLE_WALK(4, "step_achieved_show_time", 0, AchieveDialogFactory.DialogType.SINGLE_CIRCLE_WALK),
        SINGLE_CIRCLE_INTENSITY(5, "intensity_achieved_show_time", 3, AchieveDialogFactory.DialogType.SINGLE_CIRCLE_INTENSITY, "DoneIntensity"),
        SINGLE_CIRCLE_ACTIVE(6, "active_achieved_show_time", 3, AchieveDialogFactory.DialogType.SINGLE_CIRCLE_ACTIVE, "DoneActiveH"),
        SINGLE_CIRCLE_CALORIE(7, "heat_achieved_show_time", 3, AchieveDialogFactory.DialogType.SINGLE_CIRCLE_CALORIE, "DoneCalorie");

        private String mAchieveStrKey;
        private AchieveDialogFactory.DialogType mDialogType;
        private String mSpKey;
        private int mSuperType;
        private int mType;

        AchieveAniType(int i, String str, int i2, AchieveDialogFactory.DialogType dialogType) {
            this.mType = i;
            this.mSpKey = str;
            this.mSuperType = i2;
            this.mDialogType = dialogType;
        }

        AchieveAniType(int i, String str, int i2, AchieveDialogFactory.DialogType dialogType, String str2) {
            this.mType = i;
            this.mSpKey = str;
            this.mSuperType = i2;
            this.mDialogType = dialogType;
            this.mAchieveStrKey = str2;
        }

        public List<AchieveAniType> getSubTypes() {
            ArrayList arrayList = new ArrayList();
            for (AchieveAniType achieveAniType : values()) {
                if (achieveAniType.mSuperType == this.mType) {
                    arrayList.add(achieveAniType);
                }
            }
            return arrayList;
        }

        public AchieveAniType getSuperType() {
            return typeOf(this.mSuperType);
        }

        public static AchieveAniType typeOf(int i) {
            for (AchieveAniType achieveAniType : values()) {
                if (achieveAniType.mType == i) {
                    return achieveAniType;
                }
            }
            return null;
        }

        public static boolean isSubType(AchieveAniType achieveAniType) {
            return (achieveAniType == null || achieveAniType.mSuperType == 0) ? false : true;
        }
    }

    static class a implements IBaseResponseCallback {
        private final Bundle b;
        private final AchieveAniType c;
        private final WeakReference<AchieveTaskHandler> e;

        private a(AchieveTaskHandler achieveTaskHandler, AchieveAniType achieveAniType, Bundle bundle) {
            this.e = new WeakReference<>(achieveTaskHandler);
            this.c = achieveAniType;
            this.b = bundle;
        }

        @Override // com.huawei.hwbasemgr.IBaseResponseCallback
        /* renamed from: onResponse */
        public void d(int i, Object obj) {
            AchieveTaskHandler achieveTaskHandler = this.e.get();
            if (achieveTaskHandler == null) {
                LogUtil.a("SCUI_AchieveDialogHandler", "onResponse: achieveTaskHandler is null");
                return;
            }
            if (!(obj instanceof HashMap)) {
                LogUtil.a("SCUI_AchieveDialogHandler", "onResponse: objData is not instanceof HashMap");
                return;
            }
            String b = nru.b((HashMap) obj, "900200011", "1");
            LogUtil.c("SCUI_AchieveDialogHandler", "remind switch is ", b);
            if ("0".equals(b)) {
                achieveTaskHandler.dhy_(this.c, this.b);
            } else {
                achieveTaskHandler.b(this.c, System.currentTimeMillis());
            }
        }
    }
}
