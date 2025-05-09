package com.huawei.pluginoperation.util;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.webkit.JavascriptInterface;
import com.huawei.health.h5pro.jsbridge.base.JsModuleBase;
import com.huawei.health.h5pro.utils.LogUtil;
import com.huawei.health.h5pro.vengine.H5ProInstance;
import com.huawei.health.h5pro.vengine.H5ProJsCbkInvoker;
import com.huawei.hmf.tasks.OnFailureListener;
import com.huawei.hmf.tasks.OnSuccessListener;
import com.huawei.hmf.tasks.Task;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.pluginoperation.util.DietKakaUtil;
import defpackage.bzw;
import defpackage.gib;
import defpackage.koq;
import defpackage.mde;
import defpackage.mdf;
import defpackage.mdg;
import defpackage.quh;
import defpackage.qul;
import health.compact.a.CommonUtil;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes.dex */
public class DietKakaUtil extends JsModuleBase {
    private static final String BREAKFAST_TASK_ID = "3509119";
    private static final String DINNER_TASK_ID = "3793654";
    private static final String FULL_MEAL_TASK_ID = "3904161";
    private static final String LAUNCH_TASK_ID = "3658543";
    private static final String TAG = "KAKA_DietKakaUtil";
    private static final int TASK_STATUS_FAILURE = 0;
    private static final int TASK_STATUS_REPEATS = 1;
    private static final int TASK_STATUS_SUCCESS = 2;
    private Context mContext;
    private H5ProJsCbkInvoker<Object> mJsCbkInvoker;

    /* loaded from: classes6.dex */
    public interface DietKakaTaskCheckCallback {
        void onFailure(String str);

        void onSuccess(mdf mdfVar);
    }

    /* loaded from: classes6.dex */
    public interface DietKakaTaskUpdateCallback {
        void onFailure(String str);

        void onSuccess(mde mdeVar);
    }

    @Override // com.huawei.health.h5pro.jsbridge.base.JsModuleBase
    public void onMount(Context context, H5ProInstance h5ProInstance) {
        this.mContext = context;
        this.mJsCbkInvoker = h5ProInstance.getJsCbkInvoker();
    }

    @JavascriptInterface
    public void updateDietKakaTask(final long j, String str) {
        LogUtil.i(TAG, "updateDietKakaTask start");
        dietKakaTaskCheck(str, new DietKakaTaskCheckCallback() { // from class: com.huawei.pluginoperation.util.DietKakaUtil.3
            @Override // com.huawei.pluginoperation.util.DietKakaUtil.DietKakaTaskCheckCallback
            public void onFailure(String str2) {
                LogUtil.i(DietKakaUtil.TAG, "updateDietKakaTask onFailure");
                DietKakaUtil.this.mJsCbkInvoker.onFailure(0, str2, j);
            }

            @Override // com.huawei.pluginoperation.util.DietKakaUtil.DietKakaTaskCheckCallback
            public void onSuccess(mdf mdfVar) {
                LogUtil.i(DietKakaUtil.TAG, "updateDietKakaTask onSuccess");
                if (TextUtils.equals(mdfVar.p(), String.valueOf(2))) {
                    DietKakaUtil.this.mJsCbkInvoker.onSuccess(1, j);
                } else {
                    DietKakaUtil.updateDietKakaTask(DietKakaUtil.this.mContext, mdfVar.h(), new DietKakaTaskUpdateCallback() { // from class: com.huawei.pluginoperation.util.DietKakaUtil.3.1
                        @Override // com.huawei.pluginoperation.util.DietKakaUtil.DietKakaTaskUpdateCallback
                        public void onFailure(String str2) {
                            LogUtil.i(DietKakaUtil.TAG, "updateDietKakaTask onSuccess onFailure");
                            DietKakaUtil.this.mJsCbkInvoker.onFailure(0, str2, j);
                        }

                        @Override // com.huawei.pluginoperation.util.DietKakaUtil.DietKakaTaskUpdateCallback
                        public void onSuccess(mde mdeVar) {
                            LogUtil.i(DietKakaUtil.TAG, "updateDietKakaTask onSuccess onSuccess");
                            DietKakaUtil.this.mJsCbkInvoker.onSuccess(2, j);
                        }
                    });
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void updateDietKakaTask(Context context, String str, final DietKakaTaskUpdateCallback dietKakaTaskUpdateCallback) {
        LogUtil.i(TAG, "inner updateDietKakaTask start");
        mdg mdgVar = new mdg();
        mdgVar.b(str);
        mdgVar.e(2);
        mdgVar.b(0);
        mdgVar.d(CommonUtil.am());
        ArrayList arrayList = new ArrayList(1);
        arrayList.add(mdgVar);
        Task<mde> updateKakaTasks = bzw.e().updateKakaTasks(context, arrayList);
        final String str2 = "Failed to update the task.";
        if (updateKakaTasks == null) {
            dietKakaTaskUpdateCallback.onFailure("Failed to update the task.");
        } else {
            updateKakaTasks.addOnSuccessListener(new OnSuccessListener() { // from class: mtf
                @Override // com.huawei.hmf.tasks.OnSuccessListener
                public final void onSuccess(Object obj) {
                    DietKakaUtil.lambda$updateDietKakaTask$0(DietKakaUtil.DietKakaTaskUpdateCallback.this, str2, (mde) obj);
                }
            }).addOnFailureListener(new OnFailureListener() { // from class: mti
                @Override // com.huawei.hmf.tasks.OnFailureListener
                public final void onFailure(Exception exc) {
                    DietKakaUtil.DietKakaTaskUpdateCallback.this.onFailure(str2);
                }
            });
        }
    }

    public static /* synthetic */ void lambda$updateDietKakaTask$0(DietKakaTaskUpdateCallback dietKakaTaskUpdateCallback, String str, mde mdeVar) {
        if (mdeVar == null) {
            dietKakaTaskUpdateCallback.onFailure(str);
        } else if (TextUtils.equals(mdeVar.d(), "0")) {
            dietKakaTaskUpdateCallback.onSuccess(mdeVar);
        } else {
            dietKakaTaskUpdateCallback.onFailure(str);
        }
    }

    public static void completeMeal(final Context context, quh quhVar, qul qulVar) {
        final boolean z;
        LogUtil.i(TAG, "start complete meal kaka, dietRecord=" + quhVar.toString() + ", meal=" + qulVar.toString());
        if (gib.b(qulVar.d()) != gib.b(System.currentTimeMillis())) {
            Log.w(TAG, "completeMealKaka failed, meal's time is " + gib.j(qulVar.d()));
            return;
        }
        int h = qulVar.h();
        final String str = h != 10 ? h != 20 ? h != 30 ? "" : DINNER_TASK_ID : LAUNCH_TASK_ID : BREAKFAST_TASK_ID;
        HashSet hashSet = new HashSet();
        Iterator<qul> it = quhVar.a().iterator();
        while (it.hasNext()) {
            hashSet.add(Integer.valueOf(it.next().h()));
        }
        HashSet hashSet2 = new HashSet(Arrays.asList(10, 20, 30));
        if (hashSet.containsAll(hashSet2)) {
            z = false;
        } else {
            hashSet.add(Integer.valueOf(qulVar.h()));
            z = hashSet.containsAll(hashSet2);
        }
        dietKakaTaskCheck(str, new DietKakaTaskCheckCallback() { // from class: com.huawei.pluginoperation.util.DietKakaUtil.2
            @Override // com.huawei.pluginoperation.util.DietKakaUtil.DietKakaTaskCheckCallback
            public void onFailure(String str2) {
                LogUtil.w(DietKakaUtil.TAG, "completeMealKaka meal task has done!");
            }

            @Override // com.huawei.pluginoperation.util.DietKakaUtil.DietKakaTaskCheckCallback
            public void onSuccess(mdf mdfVar) {
                LogUtil.i(DietKakaUtil.TAG, "completeMealKaka meal task can complete!");
                DietKakaTaskUpdateCallback dietKakaTaskUpdateCallback = new DietKakaTaskUpdateCallback() { // from class: com.huawei.pluginoperation.util.DietKakaUtil.2.5
                    @Override // com.huawei.pluginoperation.util.DietKakaUtil.DietKakaTaskUpdateCallback
                    public void onFailure(String str2) {
                        LogUtil.e(DietKakaUtil.TAG, "completeMealKaka complete task error, msg=" + str2);
                    }

                    @Override // com.huawei.pluginoperation.util.DietKakaUtil.DietKakaTaskUpdateCallback
                    public void onSuccess(mde mdeVar) {
                        LogUtil.i(DietKakaUtil.TAG, "completeMealKaka complete task success, " + mdeVar.e());
                    }
                };
                DietKakaUtil.updateDietKakaTask(context, str, dietKakaTaskUpdateCallback);
                if (z) {
                    DietKakaUtil.updateDietKakaTask(context, DietKakaUtil.FULL_MEAL_TASK_ID, dietKakaTaskUpdateCallback);
                }
            }
        });
    }

    private static void dietKakaTaskCheck(final String str, final DietKakaTaskCheckCallback dietKakaTaskCheckCallback) {
        LogUtil.i(TAG, "dietKakaTaskCheck get start");
        Task<List<mdf>> taskInfoList = bzw.e().getTaskInfoList(BaseApplication.getContext(), 2);
        if (taskInfoList == null) {
            dietKakaTaskCheckCallback.onFailure("No task of this type exists.");
        } else {
            taskInfoList.addOnSuccessListener(new OnSuccessListener() { // from class: mtc
                @Override // com.huawei.hmf.tasks.OnSuccessListener
                public final void onSuccess(Object obj) {
                    DietKakaUtil.dietKakaTaskCheck(str, (List) obj, dietKakaTaskCheckCallback);
                }
            }).addOnFailureListener(new OnFailureListener() { // from class: mtg
                @Override // com.huawei.hmf.tasks.OnFailureListener
                public final void onFailure(Exception exc) {
                    DietKakaUtil.DietKakaTaskCheckCallback.this.onFailure("Failed to query the task.");
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void dietKakaTaskCheck(String str, List<mdf> list, DietKakaTaskCheckCallback dietKakaTaskCheckCallback) {
        LogUtil.i(TAG, "dietKakaTaskCheck Check start");
        if (koq.b(list)) {
            dietKakaTaskCheckCallback.onFailure("The task list is empty.");
            return;
        }
        for (mdf mdfVar : list) {
            if (TextUtils.equals(str, mdfVar.h())) {
                LogUtil.i(TAG, "dietKakaTaskCheck Check true");
                dietKakaTaskCheckCallback.onSuccess(mdfVar);
                return;
            }
        }
        dietKakaTaskCheckCallback.onFailure("Failed to match the task.");
    }
}
