package defpackage;

import android.content.Context;
import android.database.sqlite.SQLiteException;
import android.text.TextUtils;
import com.huawei.hwcloudmodel.callback.GrsQueryCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.operation.OpAnalyticsConstants;
import com.huawei.operation.OpAnalyticsUtil;
import com.huawei.operation.OperationKey;
import com.huawei.operation.utils.CloudParamKeys;
import com.huawei.pluginachievement.connectivity.config.AUserProfile;
import com.huawei.pluginachievement.connectivity.https.HttpResCallBack;
import com.huawei.pluginachievement.impl.AchieveCallback;
import com.huawei.pluginachievement.manager.model.AchieveUserLevelInfo;
import com.huawei.pluginachievement.manager.model.EventRecord;
import com.huawei.pluginachievement.manager.model.MedalConfigInfo;
import com.huawei.pluginachievement.manager.model.ParsedFieldTag;
import com.huawei.pluginachievement.manager.model.ResultCode;
import com.huawei.pluginachievement.manager.model.TotalRecord;
import com.huawei.pluginachievement.manager.model.UserAchieveWrapper;
import com.huawei.pluginachievement.manager.service.AchievePersonalDataObserver;
import health.compact.a.CommonUtil;
import health.compact.a.DataBaseHelper;
import health.compact.a.GRSManager;
import health.compact.a.HiDateUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import net.zetetic.database.sqlcipher.SQLiteDatabase;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes6.dex */
public class mdo {
    private static volatile mdo c;

    /* renamed from: a, reason: collision with root package name */
    private String f14898a = "";
    private Context d;

    private mdo(Context context) {
        this.d = context.getApplicationContext();
    }

    public static mdo d(Context context) {
        if (context == null) {
            return null;
        }
        if (c == null) {
            synchronized (mdo.class) {
                if (c == null) {
                    c = new mdo(context);
                }
            }
        }
        return c;
    }

    public void e(int i, AUserProfile aUserProfile, JSONObject jSONObject, int i2, AchieveCallback achieveCallback) {
        String str;
        LogUtil.a("PLGACHIEVE_AchievePuller", "pullData: enter -> contentType = ", Integer.valueOf(i), " iVersion = ", Integer.valueOf(i2));
        if (aUserProfile == null || jSONObject == null) {
            return;
        }
        this.f14898a = aUserProfile.getHuid();
        if (TextUtils.isEmpty(aUserProfile.getToken()) || TextUtils.isEmpty(this.f14898a)) {
            LogUtil.h("PLGACHIEVE_AchievePuller", "pullData: Token or huid is empty!");
            return;
        }
        try {
            if (i == 0 || i == 4 || i == 8) {
                if (!TextUtils.isEmpty(this.f14898a)) {
                    if (TextUtils.isEmpty(aUserProfile.getAppVersion())) {
                    }
                }
                LogUtil.h("PLGACHIEVE_AchievePuller", "pullData: Huid or version is empty!");
                return;
            } else if (i == 11 || i == 12) {
                jSONObject.put("timeZone", HiDateUtil.d((String) null));
                jSONObject.put(ParsedFieldTag.TASK_RULE_VERSION, "1.0.0.10");
                LogUtil.a("PLGACHIEVE_AchievePuller", "pullData: kaka rule version = ", "1.0.0.10");
                if (i == 11 && !jSONObject.has(ParsedFieldTag.KAKA_TASK_SCENARIO)) {
                    jSONObject.put(ParsedFieldTag.KAKA_TASK_SCENARIO, -1);
                }
            } else if (jSONObject.has(ParsedFieldTag.MEDAL_ID)) {
                str = jSONObject.getString(ParsedFieldTag.MEDAL_ID);
                c(i, aUserProfile, str, mds.a(aUserProfile, jSONObject, i2), achieveCallback);
            }
            str = null;
            c(i, aUserProfile, str, mds.a(aUserProfile, jSONObject, i2), achieveCallback);
        } catch (JSONException e) {
            LogUtil.b("PLGACHIEVE_AchievePuller", "pullData: exception -> ", e.getMessage());
        }
    }

    private void c(final int i, AUserProfile aUserProfile, final String str, final JSONObject jSONObject, final AchieveCallback achieveCallback) {
        String str2;
        if (i == 10) {
            try {
                jSONObject.put(CloudParamKeys.CLIENT_TYPE, eil.a());
            } catch (JSONException e) {
                LogUtil.b("PLGACHIEVE_AchievePuller", "pullData(requestData): exception -> ", e.getMessage());
            }
            str2 = "activityUrl";
        } else {
            str2 = "achievementUrl";
        }
        final HashMap<String, String> c2 = mds.c(aUserProfile);
        GRSManager.a(this.d).e(str2, new GrsQueryCallback() { // from class: mdo.1
            @Override // com.huawei.hwcloudmodel.callback.GrsQueryCallback
            public void onCallBackSuccess(String str3) {
                LogUtil.a("PLGACHIEVE_AchievePuller", "pullData(requestData): GET KEY SUCCESS -> contentType = " + i);
                String str4 = str3 + mds.d(i);
                if (TextUtils.isEmpty(str4)) {
                    return;
                }
                LogUtil.c("PLGACHIEVE_AchievePuller", "pullData(requestData): url = ", str4);
                final long currentTimeMillis = System.currentTimeMillis();
                LogUtil.a("PLGACHIEVE_AchievePuller", "pullData(requestData): ret = ", Integer.valueOf(mdk.c(str4, jSONObject, c2, new HttpResCallBack() { // from class: mdo.1.2
                    @Override // com.huawei.pluginachievement.connectivity.https.HttpResCallBack
                    public void onFinished(int i2, String str5) {
                        LogUtil.a("PLGACHIEVE_AchievePuller", "pullData(requestData): onFinished -> resCode = ", Integer.valueOf(i2), " contentType = ", Integer.valueOf(i));
                        if (achieveCallback != null) {
                            achieveCallback.onResponse(i2, str5);
                        }
                        mdo.this.d(str5);
                        mdo.this.b(i2, str5, i);
                        if (i == 9 && str != null) {
                            mdo.this.d(i2, str);
                        } else if (i == 7) {
                            mdo.this.a(i2);
                        } else {
                            LogUtil.a("PLGACHIEVE_AchievePuller", "pullData(requestData): onCallBackSuccess contentType");
                        }
                        mdo.this.a(i2, str5, i);
                        mdo.this.e(i2, currentTimeMillis, System.currentTimeMillis());
                    }
                })));
            }

            @Override // com.huawei.hwcloudmodel.callback.GrsQueryCallback
            public void onCallBackFail(int i2) {
                LogUtil.h("PLGACHIEVE_AchievePuller", "pullData(requestData): onCallBackFail -> index = ", Integer.valueOf(i2));
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(int i, long j, long j2) {
        String str = 200 == i ? "0" : "1";
        long j3 = j2 - j;
        if (!str.equals("0") || j3 >= 5000) {
            LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap<>(4);
            linkedHashMap.put("module", "2");
            linkedHashMap.put("status", str);
            linkedHashMap.put(OpAnalyticsConstants.DELAY, String.valueOf(j3));
            linkedHashMap.put(OpAnalyticsConstants.ERROR_CODE, String.valueOf(i));
            OpAnalyticsUtil.getInstance().setEvent2nd(OperationKey.HEALTH_APP_REQUEST_MODULE_85030001.value(), linkedHashMap);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(int i, String str, int i2) {
        UserAchieveWrapper userAchieveWrapper;
        if (8 == i2) {
            return;
        }
        if (i == 200) {
            if (mdp.e(i2)) {
                mee.b(this.d).c(i, mdp.c(i2, str));
                return;
            }
            userAchieveWrapper = mdp.e(i2, str);
        } else if (i == -1) {
            mee.b(this.d).c(i, new UserAchieveWrapper(i2));
            return;
        } else {
            LogUtil.h("PLGACHIEVE_AchievePuller", "processData resCode = ", Integer.valueOf(i));
            userAchieveWrapper = null;
        }
        if (userAchieveWrapper == null) {
            return;
        }
        String resultCode = userAchieveWrapper.getResultCode();
        LogUtil.a("PLGACHIEVE_AchievePuller", "processData resultCode:", resultCode);
        if (14 == i2) {
            mds.a(userAchieveWrapper, resultCode, this.d);
        }
        c(i2, resultCode);
        if (10 == i2 && "0".equals(resultCode)) {
            LogUtil.a("PLGACHIEVE_AchievePuller", "processData GET_ACTIVITY_INFO:", resultCode);
            mee.b(this.d).c(i, userAchieveWrapper);
        } else if (mds.e(userAchieveWrapper, resultCode)) {
            mee.b(this.d).c(i, userAchieveWrapper);
        } else if (i2 == 18) {
            mee.b(this.d).c(i, userAchieveWrapper);
        } else {
            c(userAchieveWrapper.toList());
            mee.b(this.d).c(i, userAchieveWrapper);
        }
    }

    private void c(List<mcz> list) {
        if (list == null || list.isEmpty()) {
            LogUtil.h("PLGACHIEVE_AchievePuller", "initUserType");
            return;
        }
        for (mcz mczVar : list) {
            if (mczVar != null) {
                mczVar.setHuid(this.f14898a);
                mee.b(this.d).b(mczVar);
                if (mczVar instanceof TotalRecord) {
                    AchievePersonalDataObserver.a(((TotalRecord) mczVar).getDays());
                }
            }
        }
    }

    private void c(int i, String str) {
        if (i == 0 && ResultCode.CODE_NEVER_SYNC.equals(str)) {
            LogUtil.a("PLGACHIEVE_AchievePuller", "processData GET_ACTIVITY_INFO:", str, " set the new user");
            String accountInfo = LoginInit.getInstance(this.d).getAccountInfo(1011);
            if (TextUtils.isEmpty(accountInfo)) {
                return;
            }
            AchievePersonalDataObserver.a(this.d, "NEWBIE" + accountInfo, "done");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(int i) {
        if (i == 200) {
            mct.b(this.d, "_uploadMedal", "");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(int i, String str) {
        if (i == 200) {
            EventRecord eventRecord = new EventRecord();
            eventRecord.saveMedalID(str);
            mee.b(this.d).d(eventRecord);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(int i, String str, int i2) {
        if (mds.b(i2)) {
            return;
        }
        LogUtil.c("PLGACHIEVE_AchievePuller", "enter processMedalBasicData ");
        ArrayList<UserAchieveWrapper> arrayList = new ArrayList<>(16);
        List<mdf> arrayList2 = new ArrayList<>(4);
        if (CommonUtil.bu()) {
            str = mfi.d(i2, str);
        }
        if (CommonUtil.bu() || i == 200) {
            arrayList = mdp.c(i2, str, this.d);
            if (i2 == 17 || i2 == 11) {
                arrayList2 = b((List<UserAchieveWrapper>) arrayList);
            }
        }
        if (i2 == 0 && !TextUtils.isEmpty(mct.b(this.d, "medal"))) {
            mct.b(this.d, "personal", "1");
        }
        UserAchieveWrapper userAchieveWrapper = new UserAchieveWrapper(8);
        UserAchieveWrapper userAchieveWrapper2 = new UserAchieveWrapper(11);
        UserAchieveWrapper userAchieveWrapper3 = new UserAchieveWrapper(13);
        if (i2 != 17) {
            a(arrayList, userAchieveWrapper, userAchieveWrapper3, i2);
        }
        if (i2 == 8) {
            mct.b(this.d, "medal", "1");
            mee.b(this.d).c(i, userAchieveWrapper);
            return;
        }
        if (i2 == 11) {
            userAchieveWrapper2.setHealthLifeKakaTaskInfoList(arrayList2);
            mee.b(this.d).c(i, userAchieveWrapper2);
        } else if (i2 == 17) {
            UserAchieveWrapper userAchieveWrapper4 = new UserAchieveWrapper(17);
            userAchieveWrapper4.setHealthLifeKakaTaskInfoList(arrayList2);
            mee.b(this.d).c(i, userAchieveWrapper4);
        } else if (i2 == 13) {
            mee.b(this.d).c(i, userAchieveWrapper3);
        } else {
            LogUtil.a("PLGACHIEVE_AchievePuller", "processMedalBasicData not refresh Observer");
        }
    }

    private void a(ArrayList<UserAchieveWrapper> arrayList, UserAchieveWrapper userAchieveWrapper, UserAchieveWrapper userAchieveWrapper2, int i) {
        if (mea.b(BaseApplication.getContext()) == null) {
            LogUtil.h("PLGACHIEVE_AchievePuller", "processMedalData achieveDBManager not init");
            return;
        }
        SQLiteDatabase c2 = DataBaseHelper.c(String.valueOf(mds.b())).c();
        boolean z = false;
        if (c2 != null) {
            try {
                try {
                    c2.beginTransaction();
                    d(arrayList, userAchieveWrapper, userAchieveWrapper2);
                    c2.setTransactionSuccessful();
                    try {
                        c2.endTransaction();
                        z = true;
                    } catch (SQLiteException | IllegalStateException e) {
                        LogUtil.b("PLGACHIEVE_AchievePuller", "endTransaction IllegalStateException", e.getMessage());
                    }
                } finally {
                    try {
                        c2.endTransaction();
                    } catch (SQLiteException | IllegalStateException e2) {
                        LogUtil.b("PLGACHIEVE_AchievePuller", "endTransaction IllegalStateException", e2.getMessage());
                    }
                }
            } catch (SQLiteException | IllegalStateException e3) {
                LogUtil.b("PLGACHIEVE_AchievePuller", "IllegalStateException", e3.getMessage());
            }
        }
        if (i == 11 && z && b(arrayList)) {
            mct.b(BaseApplication.getContext(), "kakaSyncDate", String.valueOf(System.currentTimeMillis()));
            LogUtil.a("PLGACHIEVE_AchievePuller", "refreshKakaSyncDate updata kakaSyncDate ", Long.valueOf(System.currentTimeMillis()));
        }
    }

    private boolean b(ArrayList<UserAchieveWrapper> arrayList) {
        if (koq.b(arrayList)) {
            return false;
        }
        boolean z = arrayList.size() != 1 || "0".equals(arrayList.get(0).getResultCode());
        LogUtil.a("PLGACHIEVE_AchievePuller", "refreshKakaSyncDate isResultCodeOk ", Boolean.valueOf(z));
        return z;
    }

    private void d(ArrayList<UserAchieveWrapper> arrayList, UserAchieveWrapper userAchieveWrapper, UserAchieveWrapper userAchieveWrapper2) {
        Iterator<UserAchieveWrapper> it = arrayList.iterator();
        while (it.hasNext()) {
            UserAchieveWrapper next = it.next();
            if (next != null) {
                for (mcz mczVar : next.toList()) {
                    if (mczVar != null) {
                        mczVar.setHuid(this.f14898a);
                        mee.b(this.d).b(mczVar);
                        if (mczVar instanceof MedalConfigInfo) {
                            MedalConfigInfo medalConfigInfo = (MedalConfigInfo) mczVar;
                            if (!TextUtils.isEmpty(medalConfigInfo.acquireMedalType()) && mlb.o(medalConfigInfo.acquireMedalType())) {
                                userAchieveWrapper.saveMedalConfigInfo(medalConfigInfo);
                            }
                        }
                        if (mczVar instanceof AchieveUserLevelInfo) {
                            userAchieveWrapper2.saveAchieveUserLevelInfo((AchieveUserLevelInfo) mczVar);
                        }
                    }
                }
            }
        }
    }

    private List<mdf> b(List<UserAchieveWrapper> list) {
        ArrayList arrayList = new ArrayList();
        if (!koq.b(list)) {
            for (UserAchieveWrapper userAchieveWrapper : list) {
                if (userAchieveWrapper != null && userAchieveWrapper.acquireKakaTaskInfo() != null) {
                    arrayList.add(userAchieveWrapper.acquireKakaTaskInfo());
                }
            }
        }
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(String str) {
        if (TextUtils.isEmpty(str) || CommonUtil.bu()) {
            LogUtil.h("PLGACHIEVE_AchievePuller", "checkResultCode resultJson is empty");
            return;
        }
        try {
            int optInt = new JSONObject(str).optInt("resultCode");
            if (optInt == 1002 || optInt == 1004) {
                LogUtil.a("PLGACHIEVE_AchievePuller", "checkResultCode resultCode = ", Integer.valueOf(optInt));
                LoginInit.tryLoginWhenTokenInvalid();
            }
        } catch (JSONException unused) {
            LogUtil.b("PLGACHIEVE_AchievePuller", "checkResultCode JSONException");
        }
    }
}
