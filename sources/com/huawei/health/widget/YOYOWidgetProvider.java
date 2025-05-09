package com.huawei.health.widget;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BadParcelableException;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.SparseArray;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.hihonor.assistant.cardmgrsdk.CardMgrSdkConst;
import com.hihonor.assistant.cardmgrsdk.model.CardDisplayRequestArg;
import com.hihonor.assistant.cardmgrsdk.model.DisplayResult;
import com.hihonor.assistant.cardmgrsdk.model.YOYOWidgetMsg;
import com.huawei.health.arkuix.utils.ArkUIXConstants;
import com.huawei.health.sport.model.WorkoutRecord;
import com.huawei.hihealth.HiDataReadOption;
import com.huawei.hihealth.HiHealthClient;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.api.HiHealthNativeApi;
import com.huawei.hihealth.data.listener.FitnessListener;
import com.huawei.hihealth.data.listener.HiDataReadResultListener;
import com.huawei.hihealth.data.listener.HiSubscribeListener;
import com.huawei.hihealth.data.listener.HiUnSubscribeListener;
import com.huawei.hihealth.data.model.HiTrackMetaData;
import com.huawei.hihealth.util.HiJsonUtil;
import com.huawei.hihealth.util.WidgetHelperUtils;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import defpackage.gnn;
import defpackage.knu;
import defpackage.koq;
import defpackage.kor;
import defpackage.uj;
import defpackage.un;
import health.compact.a.BroadcastManagerUtil;
import health.compact.a.CommonUtil;
import health.compact.a.HiCommonUtil;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.StorageParams;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class YOYOWidgetProvider extends AppWidgetProvider {
    private static a b;
    private static List<Integer> g = new ArrayList();
    private static List<Integer> f = null;

    /* renamed from: a, reason: collision with root package name */
    private static boolean f3509a = false;
    private static volatile boolean c = false;
    private static boolean e = false;
    private static boolean d = false;

    @Override // android.appwidget.AppWidgetProvider
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] iArr) {
        Bundle bundle;
        super.onUpdate(context, appWidgetManager, iArr);
        for (int i : iArr) {
            try {
                bundle = appWidgetManager.getAppWidgetOptions(i);
            } catch (BadParcelableException unused) {
                LogUtil.b("HonorYoyoWidgetProvider", "catch BadParcelableException onUpdate");
                bundle = null;
            }
            aSZ_(bundle);
        }
    }

    private void aSZ_(Bundle bundle) {
        if (bundle == null) {
            LogUtil.b("HonorYoyoWidgetProvider", "appWidgetOptions is null");
            return;
        }
        Optional<YOYOWidgetMsg> cS_ = new un().cS_(bundle);
        if (Build.VERSION.SDK_INT >= 30) {
            cS_.ifPresent(new Consumer<YOYOWidgetMsg>() { // from class: com.huawei.health.widget.YOYOWidgetProvider.4
                /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
                @Override // java.util.function.Consumer
                /* renamed from: b, reason: merged with bridge method [inline-methods] */
                public void accept(YOYOWidgetMsg yOYOWidgetMsg) {
                    char c2;
                    String business = yOYOWidgetMsg.getBusiness();
                    String scene = yOYOWidgetMsg.getScene();
                    String type = yOYOWidgetMsg.getType();
                    LogUtil.a("HonorYoyoWidgetProvider", "business:" + business + ",scene:" + scene + ",type:" + type + ",extras: " + yOYOWidgetMsg.getExtras());
                    if (scene.equals(CardMgrSdkConst.MSGScene.SHOWCARD)) {
                        LogUtil.a("HonorYoyoWidgetProvider", "Display Card");
                        return;
                    }
                    type.hashCode();
                    switch (type.hashCode()) {
                        case -1288023416:
                            if (type.equals(CardMgrSdkConst.BroadcastAction.UI_CHANGED_ACTION)) {
                                c2 = 0;
                                break;
                            }
                            c2 = 65535;
                            break;
                        case -658634884:
                            if (type.equals(CardMgrSdkConst.BroadcastAction.CARDSTACK_FAILED_ACTION)) {
                                c2 = 1;
                                break;
                            }
                            c2 = 65535;
                            break;
                        case -268976946:
                            if (type.equals(CardMgrSdkConst.BroadcastAction.LOCALE_CHANGED_ACTION)) {
                                c2 = 2;
                                break;
                            }
                            c2 = 65535;
                            break;
                        case 1844526859:
                            if (type.equals(CardMgrSdkConst.BroadcastAction.CARD_APK_UPDATE_ACTION)) {
                                c2 = 3;
                                break;
                            }
                            c2 = 65535;
                            break;
                        default:
                            c2 = 65535;
                            break;
                    }
                    if (c2 == 0) {
                        LogUtil.a("HonorYoyoWidgetProvider", "UI_CHANGED_ACTION");
                        return;
                    }
                    if (c2 == 1) {
                        LogUtil.a("HonorYoyoWidgetProvider", "CARDSTACK_FAILED_ACTION");
                        YOYOWidgetProvider.a();
                    } else if (c2 == 2) {
                        LogUtil.a("HonorYoyoWidgetProvider", "LOCALE_CHANGED_ACTION");
                        YOYOWidgetProvider.a();
                    } else {
                        if (c2 != 3) {
                            return;
                        }
                        LogUtil.a("HonorYoyoWidgetProvider", "CARD_APK_UPDATE_ACTION");
                        YOYOWidgetProvider.a();
                    }
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void a() {
        d = true;
        i(-1L);
        a(-1L);
        e(-1L);
        d = false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void i(long j) {
        try {
            String b2 = SharedPreferenceManager.b(BaseApplication.getContext(), "movement", "DISPLAYREQUESTARG_WALK_MOVEMENT");
            String b3 = SharedPreferenceManager.b(BaseApplication.getContext(), "movement", "DISPLAYREQUESTARG_WALK_MOVEMENT_JSON");
            long b4 = SharedPreferenceManager.b("movement", "DISPLAYREQUESTARG_WALK_MOVEMENT_STARTTIME", 0L);
            long b5 = SharedPreferenceManager.b("movement", "DISPLAYREQUESTARG_WALK_MOVEMENT_ENDTTIME", 0L);
            HiTrackMetaData hiTrackMetaData = (HiTrackMetaData) new Gson().fromJson(b2, HiTrackMetaData.class);
            LogUtil.a("HonorYoyoWidgetProvider", "systemEventsRemoveWalkMoveMentCard: ", "movementStartTime: ", Long.valueOf(b4), "movementEndTime: ", Long.valueOf(b5), "displayRequestArgmovement: ", b2, "movementData: ", b3);
            if (hiTrackMetaData == null || TextUtils.isEmpty(b3) || b4 == 0 || b5 == 0) {
                return;
            }
            if (j == -1 || j == b5) {
                d(b3, hiTrackMetaData, b4, b5);
            }
        } catch (JsonSyntaxException unused) {
            LogUtil.b("HonorYoyoWidgetProvider", "systemEventsRemoveWalkMoveMentCard JsonSyntaxException");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void a(long j) {
        try {
            String b2 = SharedPreferenceManager.b(BaseApplication.getContext(), "movement", "DISPLAYREQUESTARG_RUN_MOVEMENT");
            String b3 = SharedPreferenceManager.b(BaseApplication.getContext(), "movement", "DISPLAYREQUESTARG_RUN_MOVEMENT_JSON");
            long b4 = SharedPreferenceManager.b("movement", "DISPLAYREQUESTARG_RUN_MOVEMENT_STARTTIME", 0L);
            long b5 = SharedPreferenceManager.b("movement", "DISPLAYREQUESTARG_RUN_MOVEMENT_ENDTTIME", 0L);
            HiTrackMetaData hiTrackMetaData = (HiTrackMetaData) new Gson().fromJson(b2, HiTrackMetaData.class);
            LogUtil.a("HonorYoyoWidgetProvider", "systemEventsRemoveRunMoveMentCard: ", "movementStartTime: ", Long.valueOf(b4), "movementEndTime: ", Long.valueOf(b5), "displayRequestArgmovement: ", b2, "movementData: ", b3);
            if (hiTrackMetaData == null || TextUtils.isEmpty(b3) || b4 == 0 || b5 == 0) {
                return;
            }
            if (j == -1 || j == b5) {
                d(b3, hiTrackMetaData, b4, b5);
            }
        } catch (JsonSyntaxException unused) {
            LogUtil.b("HonorYoyoWidgetProvider", "systemEventsRemoveRunMoveMentCard JsonSyntaxException");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void e(long j) {
        try {
            WorkoutRecord workoutRecord = (WorkoutRecord) new Gson().fromJson(SharedPreferenceManager.b(BaseApplication.getContext(), "fitness", "DISPLAYREQUESTARG_FITNESS"), WorkoutRecord.class);
            if (workoutRecord != null) {
                LogUtil.a("HonorYoyoWidgetProvider", "lastWorkoutRecord: ", workoutRecord.toString());
                if (j == -1 || j == workoutRecord.acquireExerciseTime()) {
                    d(workoutRecord);
                }
            }
        } catch (JsonSyntaxException unused) {
            LogUtil.b("HonorYoyoWidgetProvider", "systemEventsRemoveFitnessCard JsonSyntaxException");
        }
    }

    /* loaded from: classes4.dex */
    static class a extends BroadcastReceiver {
        private a() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (intent == null) {
                LogUtil.b("HonorYoyoWidgetProvider", "mAccountDataSwitchReceiver intent is null");
                return;
            }
            try {
                if ("com.huawei.hihealth.action_account_login_datas_switch_finish".equals(intent.getAction())) {
                    int intExtra = intent.getIntExtra("account_switch_status", 0);
                    LogUtil.a("HonorYoyoWidgetProvider", "mAccountDataSwitchReceiver onReceive,", Integer.valueOf(intExtra));
                    if (intExtra == 0 && !YOYOWidgetProvider.f3509a) {
                        HiHealthNativeApi.a(context).subscribeHiHealthData(YOYOWidgetProvider.g, new b());
                    }
                }
                if ("com.huawei.bone.action.UNITSWITCH".equals(intent.getAction())) {
                    LogUtil.a("HonorYoyoWidgetProvider", "UNITSWITCH");
                    YOYOWidgetProvider.a();
                }
                if ("com.huawei.plugin.account.logout".equals(intent.getAction())) {
                    LogUtil.a("HonorYoyoWidgetProvider", "ACTION_LOGOUT_SUCCESSFUL");
                    boolean unused = YOYOWidgetProvider.e = true;
                    YOYOWidgetProvider.i(-1L);
                    YOYOWidgetProvider.a(-1L);
                    YOYOWidgetProvider.e(-1L);
                    boolean unused2 = YOYOWidgetProvider.e = false;
                }
                if ("com.huawei.bone.action.REMOVECARD".equals(intent.getAction())) {
                    String stringExtra = intent.getStringExtra("type");
                    long longExtra = intent.getLongExtra("EndTime", 0L);
                    boolean unused3 = YOYOWidgetProvider.e = true;
                    LogUtil.a("HonorYoyoWidgetProvider", "YOYO_REMOVECARD type: ", stringExtra, "mEndTime:", Long.valueOf(longExtra));
                    if ("movement".equals(stringExtra)) {
                        YOYOWidgetProvider.i(longExtra);
                    }
                    if ("movement_run".equals(stringExtra)) {
                        YOYOWidgetProvider.a(longExtra);
                    }
                    if ("fitness".equals(stringExtra)) {
                        YOYOWidgetProvider.e(longExtra);
                    }
                    boolean unused4 = YOYOWidgetProvider.e = false;
                }
            } catch (BadParcelableException unused5) {
                LogUtil.b("HonorYoyoWidgetProvider", "catch BadParcelableException");
            }
        }
    }

    public static void d(Context context) {
        if (!gnn.d()) {
            LogUtil.a("HonorYoyoWidgetProvider", "registerSportData isAccessYoyoCard");
            return;
        }
        LogUtil.a("HonorYoyoWidgetProvider", "registerSportData");
        g.add(4);
        HiHealthNativeApi.a(context).subscribeHiHealthData(g, new b());
        b = new a();
        IntentFilter intentFilter = new IntentFilter("com.huawei.hihealth.action_account_login_datas_switch_finish");
        intentFilter.addAction("com.huawei.hihealth.action_account_login_datas_switch_finish");
        intentFilter.addAction("com.huawei.bone.action.UNITSWITCH");
        intentFilter.addAction("com.huawei.plugin.account.logout");
        intentFilter.addAction("com.huawei.bone.action.REMOVECARD");
        BroadcastManagerUtil.bFA_(context, b, intentFilter, "com.huawei.hihealth.DEFAULT_PERMISSION", null);
        c = true;
        WidgetHelperUtils.e().e(new FitnessListener() { // from class: com.huawei.health.widget.YOYOWidgetProvider.3
            @Override // com.huawei.hihealth.data.listener.FitnessListener
            public void onResult(String str) {
                if (gnn.d()) {
                    YOYOWidgetProvider.e(str);
                } else {
                    LogUtil.a("HonorYoyoWidgetProvider", "registListener isAccessYoyoCard");
                }
            }
        });
    }

    public static void b(Context context) {
        if (b != null && c) {
            LogUtil.h("HonorYoyoWidgetProvider", "unregisterReceiver AccountStatusReceiver");
            try {
                BaseApplication.getContext().unregisterReceiver(b);
            } catch (IllegalArgumentException unused) {
                LogUtil.b("HonorYoyoWidgetProvider", "unregisterSportData IllegalArgumentException");
            }
            b = null;
            c = false;
        }
        if (koq.b(f)) {
            LogUtil.h("HonorYoyoWidgetProvider", "unregisterSportData sSportDataList listener has unregister.");
        } else {
            HiHealthNativeApi.a(context).unSubscribeHiHealthData(f, new d("HonorYoyoWidgetProvider", "unSubscribeDataChange, isSuccess:"));
        }
    }

    /* loaded from: classes4.dex */
    static class b implements HiSubscribeListener {
        b() {
        }

        @Override // com.huawei.hihealth.data.listener.HiSubscribeListener
        public void onResult(List<Integer> list, List<Integer> list2) {
            if (koq.c(list)) {
                LogUtil.a("HonorYoyoWidgetProvider", "onResult successList= ", list, "failList= ", list2);
                boolean unused = YOYOWidgetProvider.f3509a = true;
                List unused2 = YOYOWidgetProvider.f = list;
            } else {
                LogUtil.b("HonorYoyoWidgetProvider", "onResult SportHiSubscribeListener fail.");
                boolean unused3 = YOYOWidgetProvider.f3509a = false;
            }
        }

        @Override // com.huawei.hihealth.data.listener.HiSubscribeListener
        public void onChange(int i, HiHealthClient hiHealthClient, String str, HiHealthData hiHealthData, long j) {
            LogUtil.a("HonorYoyoWidgetProvider", "SportHiSubscribeListener onChange type= ", i + " changeKey= " + str);
            if (!gnn.d()) {
                LogUtil.a("HonorYoyoWidgetProvider", "onChange isAccessYoyoCard");
            } else if (i == 4 && ArkUIXConstants.INSERT.equals(str)) {
                YOYOWidgetProvider.b();
            }
        }
    }

    /* loaded from: classes4.dex */
    public static class d implements HiUnSubscribeListener {

        /* renamed from: a, reason: collision with root package name */
        private String f3511a;
        private String e;

        public d(String str, String str2) {
            this.e = str;
            this.f3511a = str2;
        }

        @Override // com.huawei.hihealth.data.listener.HiUnSubscribeListener
        public void onResult(boolean z) {
            if (z) {
                boolean unused = YOYOWidgetProvider.f3509a = false;
            }
            LogUtil.a(this.e, this.f3511a, Boolean.valueOf(z));
        }
    }

    public static void b() {
        LogUtil.a("HonorYoyoWidgetProvider", "queryLastSportData");
        HiDataReadOption hiDataReadOption = new HiDataReadOption();
        hiDataReadOption.setTimeInterval(System.currentTimeMillis() - 1800000, System.currentTimeMillis());
        hiDataReadOption.setType(new int[]{30004});
        hiDataReadOption.setCount(1);
        hiDataReadOption.setSortOrder(1);
        HiHealthNativeApi.a(BaseApplication.getContext()).readHiHealthData(hiDataReadOption, new HiDataReadResultListener() { // from class: com.huawei.health.widget.YOYOWidgetProvider.2
            @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
            public void onResultIntent(int i, Object obj, int i2, int i3) {
            }

            @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
            public void onResult(Object obj, int i, int i2) {
                LogUtil.a("HonorYoyoWidgetProvider", "queryLastHistoryData onResult errorCode:", Integer.valueOf(i));
                if (!(obj instanceof SparseArray)) {
                    LogUtil.h("HonorYoyoWidgetProvider", "queryLastSportData error result data");
                    return;
                }
                SparseArray sparseArray = (SparseArray) obj;
                if (sparseArray.size() <= 0) {
                    LogUtil.h("HonorYoyoWidgetProvider", "queryLastSportData onResult obj not instanceof List data: ", sparseArray);
                    return;
                }
                Object obj2 = sparseArray.get(30004);
                LogUtil.a("HonorYoyoWidgetProvider", "queryLastSportData obj=", obj2);
                if (obj2 instanceof List) {
                    YOYOWidgetProvider.b((List<HiHealthData>) obj2);
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void e(String str) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.h("HonorYoyoWidgetProvider", "convertLastFitnessData value is null");
            return;
        }
        try {
            WorkoutRecord workoutRecord = (WorkoutRecord) HiJsonUtil.e(CommonUtil.z(str), WorkoutRecord.class);
            LogUtil.a("HonorYoyoWidgetProvider", "convertLastFitnessData lastWorkoutRecord=", workoutRecord.toString());
            long currentTimeMillis = System.currentTimeMillis() - workoutRecord.acquireExerciseTime();
            LogUtil.a("HonorYoyoWidgetProvider", "Fitness Validity Period: ", Long.valueOf(currentTimeMillis));
            if (workoutRecord.getSportRecordType() == 1) {
                LogUtil.a("HonorYoyoWidgetProvider", "convertLastFitnessData getSportRecordType: ", 1);
            } else if (currentTimeMillis <= 900000) {
                LogUtil.a("HonorYoyoWidgetProvider", "Validity Period");
                d(workoutRecord);
            } else {
                LogUtil.a("HonorYoyoWidgetProvider", "Expired Validity Period");
            }
        } catch (JsonSyntaxException e2) {
            LogUtil.b("HonorYoyoWidgetProvider", "convertLastFitnessData JsonSyntaxException: ", e2.getMessage());
        }
    }

    private static void d(WorkoutRecord workoutRecord) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("workout_record", new Gson().toJson(workoutRecord));
            CardDisplayRequestArg a2 = a(workoutRecord, "Fitness", jSONObject.toString());
            SharedPreferenceManager.e(BaseApplication.getContext(), "fitness", "DISPLAYREQUESTARG_FITNESS", e ? null : new Gson().toJson(workoutRecord), (StorageParams) null);
            LogUtil.a("HonorYoyoWidgetProvider", "SharedPreferenceManager:", new Gson().toJson(workoutRecord));
            d(a2);
        } catch (JSONException unused) {
            LogUtil.b("HonorYoyoWidgetProvider", "lastWorkoutRecord fail");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void b(List<HiHealthData> list) {
        if (HiCommonUtil.d(list)) {
            LogUtil.b("HonorYoyoWidgetProvider", "list is isEmpty");
            return;
        }
        long endTime = list.get(0).getEndTime();
        if (endTime == 0) {
            LogUtil.b("HonorYoyoWidgetProvider", "endTime is zero");
        } else if (System.currentTimeMillis() - endTime <= 900000) {
            a(list);
        }
    }

    private static void a(List<HiHealthData> list) {
        final HiTrackMetaData d2 = gnn.d(list.get(0));
        if (d2 == null) {
            LogUtil.b("HonorYoyoWidgetProvider", "metaData is null");
        } else {
            if (d2.getAbnormalTrack() != 0) {
                LogUtil.b("HonorYoyoWidgetProvider", "Movement abnormality record");
                return;
            }
            final long startTime = list.get(0).getStartTime();
            final long endTime = list.get(0).getEndTime();
            kor.a().d(startTime, endTime, new IBaseResponseCallback() { // from class: com.huawei.health.widget.YOYOWidgetProvider.5
                @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                /* renamed from: onResponse */
                public void d(int i, Object obj) {
                    LogUtil.a("HonorYoyoWidgetProvider", "onResponse code: ", Integer.valueOf(i));
                    if (i == 0 && (obj instanceof knu)) {
                        knu knuVar = (knu) obj;
                        LogUtil.c("HonorYoyoWidgetProvider", "strPathDetail: ", knuVar.d(), "pathMotionPath: ", knuVar.b());
                        JSONObject jSONObject = new JSONObject();
                        try {
                            jSONObject.put("contentpath", knuVar.d());
                            jSONObject.put("simplifyDatakey", new Gson().toJson(knuVar.b()));
                        } catch (JSONException unused) {
                            LogUtil.b("HonorYoyoWidgetProvider", "displayCard is errors");
                        }
                        YOYOWidgetProvider.d(jSONObject.toString(), HiTrackMetaData.this, startTime, endTime);
                    }
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void d(String str, HiTrackMetaData hiTrackMetaData, long j, long j2) {
        LogUtil.a("HonorYoyoWidgetProvider", "motionData startTime: ", Long.valueOf(j));
        if (hiTrackMetaData.getSportType() == 257 || hiTrackMetaData.getSportType() == 281) {
            SharedPreferenceManager.e(BaseApplication.getContext(), "movement", "DISPLAYREQUESTARG_WALK_MOVEMENT", new Gson().toJson(hiTrackMetaData), (StorageParams) null);
            SharedPreferenceManager.e(BaseApplication.getContext(), "movement", "DISPLAYREQUESTARG_WALK_MOVEMENT_JSON", str, (StorageParams) null);
            SharedPreferenceManager.e("movement", "DISPLAYREQUESTARG_WALK_MOVEMENT_STARTTIME", j);
            SharedPreferenceManager.e("movement", "DISPLAYREQUESTARG_WALK_MOVEMENT_ENDTTIME", e ? 0L : j2);
            CardDisplayRequestArg d2 = d(257, hiTrackMetaData, j, "walk", str);
            d(d2);
            LogUtil.a("HonorYoyoWidgetProvider", "walkOperation: ", Integer.valueOf(d2.getOperation()));
        }
        if (hiTrackMetaData.getSportType() == 258 || hiTrackMetaData.getSportType() == 264) {
            LogUtil.a("HonorYoyoWidgetProvider", "SPORT_TYPE_RUN=", Integer.valueOf(hiTrackMetaData.getSportType()));
            SharedPreferenceManager.e(BaseApplication.getContext(), "movement", "DISPLAYREQUESTARG_RUN_MOVEMENT", new Gson().toJson(hiTrackMetaData), (StorageParams) null);
            SharedPreferenceManager.e(BaseApplication.getContext(), "movement", "DISPLAYREQUESTARG_RUN_MOVEMENT_JSON", str, (StorageParams) null);
            SharedPreferenceManager.e("movement", "DISPLAYREQUESTARG_RUN_MOVEMENT_STARTTIME", j);
            SharedPreferenceManager.e("movement", "DISPLAYREQUESTARG_RUN_MOVEMENT_ENDTTIME", e ? 0L : j2);
            CardDisplayRequestArg d3 = d(258, hiTrackMetaData, j, "running", str);
            d(d3);
            LogUtil.a("HonorYoyoWidgetProvider", "runOperation: ", Integer.valueOf(d3.getOperation()));
        }
    }

    private static CardDisplayRequestArg d(int i, HiTrackMetaData hiTrackMetaData, long j, String str, String str2) {
        uj.b().d(BaseApplication.getContext());
        CardDisplayRequestArg d2 = gnn.d(i, hiTrackMetaData, j, str, str2);
        LogUtil.a("HonorYoyoWidgetProvider", "requestArg displayMovementCard: ", d2.toString());
        return d2;
    }

    private static CardDisplayRequestArg a(WorkoutRecord workoutRecord, String str, String str2) {
        uj.b().d(BaseApplication.getContext());
        CardDisplayRequestArg e2 = gnn.e(259, workoutRecord, str, str2);
        LogUtil.a("HonorYoyoWidgetProvider", "requestArg displayFitnessCard: ", e2.toString());
        return e2;
    }

    private static void d(CardDisplayRequestArg cardDisplayRequestArg) {
        Optional<DisplayResult> b2;
        int i;
        int d2 = uj.b().d(cardDisplayRequestArg);
        LogUtil.a("HonorYoyoWidgetProvider", "queryCardInfo： ", Integer.valueOf(d2));
        if (e) {
            LogUtil.a("HonorYoyoWidgetProvider", "sIsRemoveCard");
            b2 = uj.b().a(cardDisplayRequestArg);
        } else if (d) {
            b2 = d2 != 4 ? uj.b().b(cardDisplayRequestArg) : null;
        } else if (d2 == 4) {
            b2 = uj.b().e(cardDisplayRequestArg);
        } else {
            b2 = uj.b().b(cardDisplayRequestArg);
        }
        if (Build.VERSION.SDK_INT < 30 || b2 == null || !b2.isPresent()) {
            i = 10000;
        } else {
            i = b2.get().getResultCode();
            LogUtil.a("HonorYoyoWidgetProvider", "displayResult： ", b2.toString(), " resultCode: ", Integer.valueOf(i));
        }
        LogUtil.a("HonorYoyoWidgetProvider", "resultCode： ", Integer.valueOf(i));
    }
}
