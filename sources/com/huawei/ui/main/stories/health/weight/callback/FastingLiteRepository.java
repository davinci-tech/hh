package com.huawei.ui.main.stories.health.weight.callback;

import android.os.Looper;
import android.text.TextUtils;
import com.google.gson.Gson;
import com.google.gson.JsonParseException;
import com.huawei.haf.common.exception.ExceptionUtils;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.h5pro.service.anotation.H5ProMethod;
import com.huawei.health.h5pro.service.anotation.H5ProService;
import com.huawei.health.weight.WeightApi;
import com.huawei.hihealth.HiDataInsertOption;
import com.huawei.hihealth.HiDataReadOption;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.HiUserPreference;
import com.huawei.hihealth.api.HiHealthManager;
import com.huawei.hihealth.api.HiHealthNativeApi;
import com.huawei.hihealth.data.listener.HiDataOperateListener;
import com.huawei.hihealth.dictionary.utils.DicDataTypeUtil;
import com.huawei.hihealth.util.HiJsonUtil;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.main.stories.health.weight.callback.FastingLiteCbk;
import com.huawei.ui.main.stories.health.weight.callback.FastingLiteRepository;
import defpackage.koq;
import defpackage.qlc;
import defpackage.qlh;
import defpackage.qui;
import defpackage.quq;
import defpackage.quv;
import defpackage.qva;
import defpackage.qve;
import defpackage.qvf;
import defpackage.rag;
import defpackage.sdk;
import health.compact.a.DeviceUtil;
import health.compact.a.Services;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.function.ToIntFunction;
import org.json.JSONException;
import org.json.JSONObject;

@H5ProService(name = "FastingLite")
/* loaded from: classes.dex */
public class FastingLiteRepository {
    private static final int AWAIT_READ_TIME_OUT = 5000;
    private static final int ERROR_CODE_FAIL = -1;
    private static final int ERROR_CODE_SUCCESS = 0;
    private static final String FASTING_LITE_CURRENT_TASK_KAY = "currentTask";
    private static final String FASTING_LITE_STATUS_KEY = "status";
    private static final String FASTING_LITE_STATUS_NOT_JOIN = "0";
    private static final String FASTING_LITE_STATUS_VERSION_ERROR = "1";
    private static final long PRECISION = 2;
    private static final String RELEASE_TAG = "R_FastingLiteRepository";
    private static final long SECOND_TO_MILLISECOND = 1000;
    private static final String TAG = "FastingLiteRepository";

    private FastingLiteRepository() {
    }

    @H5ProMethod(name = "addFastingLiteAppSetting")
    public static void addFastingLiteAppSetting(qui quiVar, FastingLiteCbk<Object> fastingLiteCbk) {
        updateFastingLiteAppSetting(quiVar, fastingLiteCbk);
    }

    @H5ProMethod(name = "getFastingLiteAppSetting")
    public static void getFastingLiteAppSetting(FastingLiteCbk<qui> fastingLiteCbk) {
        if (fastingLiteCbk == null) {
            LogUtil.h(TAG, "getFastingLiteAppSetting callback is null");
        } else {
            fastingLiteCbk.onSuccess(qve.e("custom.weight_fasting_lite_setting"));
        }
    }

    @H5ProMethod(name = "updateFastingLiteAppSetting")
    public static void updateFastingLiteAppSetting(qui quiVar, FastingLiteCbk<Object> fastingLiteCbk) {
        if (quiVar == null) {
            LogUtil.h(TAG, "updateFastingLiteAppSetting setting is null");
            return;
        }
        LogUtil.a(TAG, "updateFastingLiteAppSetting setting:", quiVar.toString());
        HiUserPreference hiUserPreference = new HiUserPreference();
        hiUserPreference.setKey("custom.weight_fasting_lite_setting");
        String e = HiJsonUtil.e(quiVar);
        hiUserPreference.setValue(e);
        boolean userPreference = HiHealthManager.d(BaseApplication.getContext()).setUserPreference(hiUserPreference);
        if (userPreference) {
            rag.c("custom.weight_fasting_lite_setting", e);
        }
        LogUtil.a(TAG, "updateFastingLiteAppSetting isSuccess = ", Boolean.valueOf(userPreference));
        if (quiVar.d() != null && quiVar.d().b() != null && quiVar.d().b().j() == 1) {
            qve.a("custom.weight_fasting_lite_current_task");
        }
        if (quiVar.c()) {
            saveCurrentTask(qlc.b().a());
        }
        if (fastingLiteCbk == null) {
            LogUtil.h(TAG, "updateFastingLiteAppSetting fastingLiteCallback is null");
        } else if (userPreference) {
            fastingLiteCbk.onSuccess(0);
        } else {
            fastingLiteCbk.onFailure(-1, "updateFastingLiteAppSetting unknown");
        }
    }

    @H5ProMethod(name = "setFastingLitePlan")
    public static void setFastingLitePlan(qui quiVar, FastingLiteCbk<Object> fastingLiteCbk) {
        if (quiVar == null) {
            LogUtil.h(TAG, "setFastingLitePlan setting is null");
            return;
        }
        LogUtil.a(TAG, "setFastingLitePlan setting: ", quiVar.toString());
        sdk.c().b(quiVar.c() && quiVar.a());
        qlc.b().d(quiVar);
        HiUserPreference hiUserPreference = new HiUserPreference();
        hiUserPreference.setKey("custom.weight_fasting_lite_plan");
        String e = HiJsonUtil.e(quiVar);
        hiUserPreference.setValue(e);
        boolean userPreference = HiHealthManager.d(BaseApplication.getContext()).setUserPreference(hiUserPreference);
        if (userPreference) {
            rag.c("custom.weight_fasting_lite_plan", e);
        }
        LogUtil.a(TAG, "setFastingLitePlan isSuccess = ", Boolean.valueOf(userPreference));
        if (userPreference && DeviceUtil.a()) {
            quq.a().c("H5_modify", null);
        }
        if (fastingLiteCbk == null) {
            LogUtil.h(TAG, "setFastingLitePlan fastingLiteCallback is null");
        } else if (userPreference) {
            fastingLiteCbk.onSuccess(0);
        } else {
            fastingLiteCbk.onFailure(-1, "setFastingLitePlan unknown");
        }
    }

    @H5ProMethod(name = "getFastingLitePlan")
    public static void getFastingLitePlan(FastingLiteCbk<qui> fastingLiteCbk) {
        if (fastingLiteCbk == null) {
            LogUtil.h(TAG, "getFastingLiteAppSetting callback is null");
        } else {
            fastingLiteCbk.onSuccess(qve.c());
        }
    }

    @H5ProMethod(name = "addFastingLiteMode")
    public static void addFastingLiteMode(quv quvVar, FastingLiteCbk<Object> fastingLiteCbk) {
        updateFastingLiteMode(quvVar, fastingLiteCbk);
    }

    @H5ProMethod(name = "getFastingLiteModes")
    public static void getFastingLiteModes(FastingLiteCbk<quv[]> fastingLiteCbk) {
        if (fastingLiteCbk == null) {
            LogUtil.h(TAG, "getFastingLiteModes callback is null");
            return;
        }
        quv[] fastingModes = getFastingModes();
        if (fastingModes == null || fastingModes.length == 0) {
            LogUtil.h(TAG, "getFastingLiteModes modes is empty");
        }
        fastingLiteCbk.onSuccess(fastingModes);
    }

    /* JADX WARN: Code restructure failed: missing block: B:22:0x004d, code lost:
    
        if (r5 == false) goto L19;
     */
    @com.huawei.health.h5pro.service.anotation.H5ProMethod(name = "updateFastingLiteMode")
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static void updateFastingLiteMode(defpackage.quv r8, com.huawei.ui.main.stories.health.weight.callback.FastingLiteCbk<java.lang.Object> r9) {
        /*
            java.lang.String r0 = "FastingLiteRepository"
            if (r8 != 0) goto Lf
            java.lang.String r8 = "updateFastingLiteMode mode is null"
            java.lang.Object[] r8 = new java.lang.Object[]{r8}
            com.huawei.hwlogsmodel.LogUtil.b(r0, r8)
            return
        Lf:
            java.lang.String r1 = "updateFastingLiteMode mode:"
            java.lang.String r2 = r8.toString()
            java.lang.Object[] r1 = new java.lang.Object[]{r1, r2}
            com.huawei.hwlogsmodel.LogUtil.a(r0, r1)
            java.util.ArrayList r1 = new java.util.ArrayList
            r1.<init>()
            quv[] r2 = getFastingModes()
            r3 = 0
            boolean r4 = defpackage.koq.e(r2, r3)
            if (r4 == 0) goto L4f
            r4 = r3
            r5 = r4
        L2f:
            int r6 = r2.length
            if (r4 >= r6) goto L46
            r6 = r2[r4]
            if (r6 == 0) goto L43
            int r6 = r6.c()
            int r7 = r8.c()
            if (r6 != r7) goto L43
            r2[r4] = r8
            r5 = 1
        L43:
            int r4 = r4 + 1
            goto L2f
        L46:
            java.util.List r2 = java.util.Arrays.asList(r2)
            r1.addAll(r2)
            if (r5 != 0) goto L52
        L4f:
            r1.add(r8)
        L52:
            com.huawei.hihealth.HiUserPreference r8 = new com.huawei.hihealth.HiUserPreference
            r8.<init>()
            java.lang.String r2 = "custom.weight_fasting_mode"
            r8.setKey(r2)
            com.google.gson.Gson r2 = new com.google.gson.Gson
            r2.<init>()
            java.lang.String r1 = r2.toJson(r1)
            r8.setValue(r1)
            android.content.Context r1 = com.huawei.hwcommonmodel.application.BaseApplication.getContext()
            com.huawei.hihealth.api.HiHealthApi r1 = com.huawei.hihealth.api.HiHealthManager.d(r1)
            boolean r8 = r1.setUserPreference(r8)
            if (r9 != 0) goto L81
            java.lang.String r8 = "updateFastingLiteMode fastingLiteCallback is null"
            java.lang.Object[] r8 = new java.lang.Object[]{r8}
            com.huawei.hwlogsmodel.LogUtil.h(r0, r8)
            return
        L81:
            if (r8 == 0) goto L8b
            java.lang.Integer r8 = java.lang.Integer.valueOf(r3)
            r9.onSuccess(r8)
            goto L91
        L8b:
            r8 = -1
            java.lang.String r0 = "addFastingLiteMode unknow error"
            r9.onFailure(r8, r0)
        L91:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.ui.main.stories.health.weight.callback.FastingLiteRepository.updateFastingLiteMode(quv, com.huawei.ui.main.stories.health.weight.callback.FastingLiteCbk):void");
    }

    @H5ProMethod(name = "getFastingLiteTasks")
    public static void getFastingLiteTasks(FastingLiteCbk<qva[]> fastingLiteCbk) {
        if (fastingLiteCbk == null) {
            LogUtil.h(TAG, "getFastingLiteTasks fastingLiteCallback is null");
        } else {
            getFastingLiteTasks(fastingLiteCbk, true);
        }
    }

    public static void getFastingLiteTasks(final FastingLiteCbk<qva[]> fastingLiteCbk, final boolean z) {
        int[] iArr = {DicDataTypeUtil.DataType.LIGHT_FASTING.value()};
        HiDataReadOption hiDataReadOption = new HiDataReadOption();
        hiDataReadOption.setType(iArr);
        hiDataReadOption.setSortOrder(1);
        hiDataReadOption.setStartTime(0L);
        hiDataReadOption.setEndTime(System.currentTimeMillis());
        qve.a(hiDataReadOption, new FastingLiteCbk<List<qva>>() { // from class: com.huawei.ui.main.stories.health.weight.callback.FastingLiteRepository.3
            @Override // com.huawei.ui.main.stories.health.weight.callback.FastingLiteCbk
            public void onFailure(int i, String str) {
            }

            @Override // com.huawei.ui.main.stories.health.weight.callback.FastingLiteCbk
            /* renamed from: b, reason: merged with bridge method [inline-methods] */
            public void onSuccess(List<qva> list) {
                if (!koq.b(list) && list.get(0).b() <= 0) {
                    list.remove(0);
                }
                if (z) {
                    FastingLiteRepository.readFastingLitePhaseForRecord(list);
                }
                qlc.b().e(list);
                LogUtil.a(FastingLiteRepository.TAG, "record num ", Integer.valueOf(list.size()), ", data ", list.toString());
                qva[] qvaVarArr = new qva[list.size()];
                Iterator<qva> it = list.iterator();
                while (it.hasNext()) {
                    it.next().h();
                }
                fastingLiteCbk.onSuccess((qva[]) list.toArray(qvaVarArr));
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void readFastingLitePhaseForRecord(List<qva> list) {
        if (koq.b(list)) {
            LogUtil.h(TAG, "readFastingLitePhaseForRecord records is empty");
            return;
        }
        final ArrayList<qva> arrayList = new ArrayList();
        for (qva qvaVar : list) {
            if (koq.b(qvaVar.e())) {
                arrayList.add(qvaVar);
            }
        }
        if (koq.b(arrayList)) {
            LogUtil.h(TAG, "readFastingLitePhaseForRecord newRecords is empty");
            return;
        }
        long d = ((qva) arrayList.get(arrayList.size() - 1)).d();
        long b = ((qva) arrayList.get(0)).b();
        for (qva qvaVar2 : arrayList) {
            if (qvaVar2.b() > b) {
                b = qvaVar2.b();
            }
        }
        long currentTimeMillis = System.currentTimeMillis();
        if (currentTimeMillis > b) {
            b = currentTimeMillis;
        }
        LogUtil.a(TAG, "minStartTime is :", Long.valueOf(d), "maxEndTime is :", Long.valueOf(b));
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        qve.e(d, b, false, new FastingLiteCbk<List<qvf.d>>() { // from class: com.huawei.ui.main.stories.health.weight.callback.FastingLiteRepository.2
            @Override // com.huawei.ui.main.stories.health.weight.callback.FastingLiteCbk
            /* renamed from: d, reason: merged with bridge method [inline-methods] */
            public void onSuccess(List<qvf.d> list2) {
                FastingLiteRepository.fillPhasesToRecord(arrayList, list2);
                countDownLatch.countDown();
            }

            @Override // com.huawei.ui.main.stories.health.weight.callback.FastingLiteCbk
            public void onFailure(int i, String str) {
                LogUtil.b(FastingLiteRepository.TAG, "readFastingLitePhase onFailure, ", str);
                countDownLatch.countDown();
            }
        });
        try {
            LogUtil.a(TAG, "countDownLatch await result isReached:", Boolean.valueOf(countDownLatch.await(5000L, TimeUnit.MILLISECONDS)));
        } catch (InterruptedException unused) {
            LogUtil.b(TAG, "interrupted while waiting for records data");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void fillPhasesToRecord(List<qva> list, List<qvf.d> list2) {
        if (koq.b(list) || koq.b(list2)) {
            LogUtil.h(TAG, "fillPhasesToRecord records is empty or windows is empty");
            return;
        }
        HashMap hashMap = new HashMap();
        for (qvf.d dVar : list2) {
            ArrayList arrayList = hashMap.containsKey(Long.valueOf(dVar.b())) ? (ArrayList) hashMap.get(Long.valueOf(dVar.b())) : null;
            if (arrayList == null) {
                ArrayList arrayList2 = new ArrayList();
                hashMap.put(Long.valueOf(dVar.b()), arrayList2);
                arrayList2.add(dVar);
            } else if (judgeValidWindow(arrayList, dVar)) {
                arrayList.add(dVar);
            } else {
                LogUtil.h(TAG, "fillPhasesToRecord currentWindow is invalid");
            }
        }
        for (qva qvaVar : list) {
            if (hashMap.containsKey(Long.valueOf(qvaVar.d()))) {
                qvaVar.c((List) hashMap.get(Long.valueOf(qvaVar.d())));
            } else {
                LogUtil.h(TAG, "fillPhasesToRecord windows is empty");
            }
        }
    }

    private static boolean judgeValidWindow(ArrayList<qvf.d> arrayList, qvf.d dVar) {
        return koq.b(arrayList) || arrayList.get(arrayList.size() - 1).c() <= dVar.f();
    }

    @H5ProMethod(name = "attachNoteForFastingLiteTask")
    public static void attachNoteForFastingLiteTask(long j, String str, FastingLiteCbk<Object> fastingLiteCbk) {
        if (fastingLiteCbk == null) {
            LogUtil.h(TAG, "attachNoteForFastingLiteTask callback is null", Long.valueOf(j));
            return;
        }
        LogUtil.a(TAG, "attachNoteForFastingLiteTask startTime ", Long.valueOf(j), " note ", str);
        qvf a2 = qlc.b().a();
        if (a2 != null && Math.abs((a2.m() / 1000) - j) < 2) {
            LogUtil.h(TAG, "attachNoteForFastingLiteTask to current task");
            qlc.b().e(str);
            return;
        }
        int[] iArr = {DicDataTypeUtil.DataType.LIGHT_FASTING.value()};
        HiDataReadOption hiDataReadOption = new HiDataReadOption();
        hiDataReadOption.setType(iArr);
        hiDataReadOption.setSortOrder(1);
        hiDataReadOption.setStartTime((j - 2) * 1000);
        hiDataReadOption.setEndTime((j + 2) * 1000);
        hiDataReadOption.setCount(1);
        qve.a(hiDataReadOption, new AnonymousClass4(fastingLiteCbk, str));
    }

    /* renamed from: com.huawei.ui.main.stories.health.weight.callback.FastingLiteRepository$4, reason: invalid class name */
    public class AnonymousClass4 implements FastingLiteCbk<List<qva>> {
        final /* synthetic */ FastingLiteCbk b;
        final /* synthetic */ String c;

        @Override // com.huawei.ui.main.stories.health.weight.callback.FastingLiteCbk
        public void onFailure(int i, String str) {
        }

        AnonymousClass4(FastingLiteCbk fastingLiteCbk, String str) {
            this.b = fastingLiteCbk;
            this.c = str;
        }

        @Override // com.huawei.ui.main.stories.health.weight.callback.FastingLiteCbk
        /* renamed from: d, reason: merged with bridge method [inline-methods] */
        public void onSuccess(List<qva> list) {
            if (koq.b(list)) {
                this.b.onFailure(-1, "records is empty");
                return;
            }
            qva qvaVar = list.get(0);
            qvaVar.e(this.c);
            final FastingLiteCbk fastingLiteCbk = this.b;
            FastingLiteRepository.saveFastingLiteRecord(qvaVar, new IBaseResponseCallback() { // from class: qun
                @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                /* renamed from: onResponse */
                public final void d(int i, Object obj) {
                    FastingLiteRepository.AnonymousClass4.b(FastingLiteCbk.this, i, obj);
                }
            });
        }

        public static /* synthetic */ void b(FastingLiteCbk fastingLiteCbk, int i, Object obj) {
            if (i == 0) {
                fastingLiteCbk.onSuccess(new Object());
            } else {
                LogUtil.h(FastingLiteRepository.TAG, "save record fail");
                fastingLiteCbk.onFailure(-1, "save record fail");
            }
        }
    }

    public static void saveFastingLiteRecord(qva qvaVar, final IBaseResponseCallback iBaseResponseCallback) {
        if (qvaVar == null) {
            LogUtil.h(TAG, "saveFastingLiteTask is null");
            if (iBaseResponseCallback != null) {
                iBaseResponseCallback.d(-1, "record is null");
                return;
            }
            return;
        }
        HiHealthData hiHealthData = new HiHealthData();
        hiHealthData.setType(DicDataTypeUtil.DataType.LIGHT_FASTING_SET.value());
        hiHealthData.setStartTime(qvaVar.d());
        hiHealthData.setEndTime(qvaVar.b());
        HashMap hashMap = new HashMap();
        hashMap.put(Integer.valueOf(DicDataTypeUtil.DataType.LIGHT_FASTING.value()), Double.valueOf(qvaVar.b() - qvaVar.d()));
        hiHealthData.setFieldsValue(HiJsonUtil.e(hashMap));
        HashMap hashMap2 = new HashMap();
        hashMap2.put(Integer.valueOf(DicDataTypeUtil.DataType.LIGHT_FASTING.value()), new Gson().toJson(qvaVar));
        hiHealthData.setFieldsMetaData(HiJsonUtil.e(hashMap2));
        hiHealthData.setDeviceUuid("-1");
        HiDataInsertOption hiDataInsertOption = new HiDataInsertOption();
        hiDataInsertOption.addData(hiHealthData);
        HiHealthNativeApi.a(com.huawei.haf.application.BaseApplication.e()).insertHiHealthData(hiDataInsertOption, new HiDataOperateListener() { // from class: que
            @Override // com.huawei.hihealth.data.listener.HiDataOperateListener
            public final void onResult(int i, Object obj) {
                FastingLiteRepository.lambda$saveFastingLiteRecord$0(IBaseResponseCallback.this, i, obj);
            }
        });
    }

    public static /* synthetic */ void lambda$saveFastingLiteRecord$0(IBaseResponseCallback iBaseResponseCallback, int i, Object obj) {
        LogUtil.a(TAG, "insert task record ", Integer.valueOf(i));
        if (iBaseResponseCallback != null) {
            iBaseResponseCallback.d(i, obj);
        }
    }

    public static void saveCurrentTask(qvf qvfVar) {
        saveCurrentTask(qvfVar, null);
    }

    public static void saveCurrentTask(final qvf qvfVar, final FastingLiteCbk<Object> fastingLiteCbk) {
        if (qvfVar == null) {
            LogUtil.h(TAG, "can not save empty taskJson");
        } else if (Looper.getMainLooper() == Looper.myLooper()) {
            ThreadPoolManager.d().execute(new Runnable() { // from class: qug
                @Override // java.lang.Runnable
                public final void run() {
                    FastingLiteRepository.saveCurrentTask(qvf.this, fastingLiteCbk);
                }
            });
        } else {
            qve.c(qvfVar.d());
            qve.c(qvfVar, new IBaseResponseCallback() { // from class: qum
                @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                /* renamed from: onResponse */
                public final void d(int i, Object obj) {
                    FastingLiteRepository.lambda$saveCurrentTask$2(FastingLiteCbk.this, i, obj);
                }
            });
        }
    }

    public static /* synthetic */ void lambda$saveCurrentTask$2(FastingLiteCbk fastingLiteCbk, int i, Object obj) {
        LogUtil.a(TAG, "saveFastingLitePhaseList errorCode= ", Integer.valueOf(i));
        if (fastingLiteCbk != null) {
            ReleaseLogUtil.e(RELEASE_TAG, "saveCurrentTask called by H5, cbk is not null");
            fastingLiteCbk.onSuccess(0);
        }
    }

    private static quv[] getFastingModes() {
        quv[] quvVarArr;
        HiUserPreference userPreference = HiHealthManager.d(BaseApplication.getContext()).getUserPreference("custom.weight_fasting_mode");
        quv[] quvVarArr2 = new quv[0];
        if (userPreference == null || TextUtils.isEmpty(userPreference.getValue())) {
            return quvVarArr2;
        }
        try {
            quvVarArr = (quv[]) HiJsonUtil.e(userPreference.getValue(), quv[].class);
        } catch (JsonParseException unused) {
        }
        try {
            Arrays.sort(quvVarArr, Comparator.comparingInt(new ToIntFunction() { // from class: quf
                @Override // java.util.function.ToIntFunction
                public final int applyAsInt(Object obj) {
                    return ((quv) obj).c();
                }
            }));
            return quvVarArr;
        } catch (JsonParseException unused2) {
            quvVarArr2 = quvVarArr;
            LogUtil.b(TAG, "get fasting lite mode fail");
            return quvVarArr2;
        }
    }

    @H5ProMethod(name = "isCanChangeToNextState")
    public static void isCanChangeToNextState(FastingLiteCbk<Object> fastingLiteCbk) {
        ReleaseLogUtil.e(RELEASE_TAG, "enter isCanChangeToNextState");
        fastingLiteCbk.onSuccess(Boolean.valueOf(qlc.b().e()));
    }

    @H5ProMethod(name = "resetCurrentStartTimeAndPost")
    public static void resetCurrentStartTimeAndPost(long j, FastingLiteCbk<Object> fastingLiteCbk) {
        ReleaseLogUtil.e(RELEASE_TAG, "enter resetCurrentStartTimeAndPost");
        qlc.b().a(j);
        fastingLiteCbk.onSuccess(0);
    }

    @H5ProMethod(name = "changeToNextState")
    public static void changeToNextState(FastingLiteCbk<Object> fastingLiteCbk) {
        ReleaseLogUtil.e(RELEASE_TAG, "enter changeToNextState");
        qlc.b().c(fastingLiteCbk);
    }

    @H5ProMethod(name = "getCurrentFastingLiteTask")
    public static void getCurrentFastingLiteTask(final FastingLiteCbk<Object> fastingLiteCbk) {
        ReleaseLogUtil.e(RELEASE_TAG, "getCurrentFastingLiteTask");
        ThreadPoolManager.d().execute(new Runnable() { // from class: com.huawei.ui.main.stories.health.weight.callback.FastingLiteRepository.1
            @Override // java.lang.Runnable
            public void run() {
                FastingLiteRepository.getCurrentStatusAndTask(FastingLiteCbk.this);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void getCurrentStatusAndTask(FastingLiteCbk<Object> fastingLiteCbk) {
        WeightApi weightApi = (WeightApi) Services.a("Main", WeightApi.class);
        if (weightApi == null) {
            ReleaseLogUtil.d(RELEASE_TAG, "weightApi is null");
            fastingLiteCbk.onFailure(-1, "weightApi is null");
            return;
        }
        JSONObject jSONObject = new JSONObject();
        String fastingLiteStatus = weightApi.getFastingLiteStatus();
        try {
            jSONObject.put("status", fastingLiteStatus);
            ReleaseLogUtil.e(RELEASE_TAG, "getFastingLiteStatus: ", fastingLiteStatus);
            if (!"0".equals(fastingLiteStatus) && !"1".equals(fastingLiteStatus)) {
                qvf a2 = qlc.b().a();
                if (a2 != null) {
                    qlc.b().h();
                }
                LogUtil.a(TAG, "after update: ", a2);
                jSONObject.put(FASTING_LITE_CURRENT_TASK_KAY, HiJsonUtil.e(a2));
                fastingLiteCbk.onSuccess(jSONObject);
            }
            ReleaseLogUtil.e(RELEASE_TAG, "fastingLite not join or version error");
            jSONObject.put(FASTING_LITE_CURRENT_TASK_KAY, (Object) null);
            fastingLiteCbk.onSuccess(jSONObject);
        } catch (JSONException e) {
            ReleaseLogUtil.d(RELEASE_TAG, "JSONException: ", ExceptionUtils.d(e));
            fastingLiteCbk.onFailure(-1, "catch JSONException");
        }
    }

    @H5ProMethod(name = "getPreStartTime")
    public static void getPreStartTime(FastingLiteCbk<Object> fastingLiteCbk) {
        ReleaseLogUtil.e(RELEASE_TAG, "getPreStartTime");
        qlh g = qlc.b().g();
        if (g == null) {
            ReleaseLogUtil.d(RELEASE_TAG, "FastingLiteViewBean is null");
            fastingLiteCbk.onFailure(-1, "FastingLiteViewBean is null");
        } else {
            fastingLiteCbk.onSuccess(Long.valueOf(g.d()));
        }
    }
}
