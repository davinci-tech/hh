package defpackage;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Message;
import com.huawei.featureuserprofile.account.ext.IAccountDataExtMgr;
import com.huawei.featureuserprofile.common.IUserInfomationHandler;
import com.huawei.featureuserprofile.media.UserInfoMedia;
import com.huawei.featureuserprofile.util.AsyncSelectorSerialize;
import com.huawei.featureuserprofile.util.MessageHandler;
import com.huawei.hihealth.HiUserInfo;
import com.huawei.hihealth.api.HiHealthNativeApi;
import com.huawei.hihealth.data.listener.HiCommonListener;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.OpAnalyticsUtil;
import com.huawei.operation.OperationKey;
import com.huawei.up.model.UserInfomation;
import health.compact.a.BroadcastManagerUtil;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: classes3.dex */
public class bqs implements IAccountDataExtMgr, IUserInfomationHandler {
    private static final Object d = new Object();
    private b b = null;
    private UserInfomation i = null;
    private UserInfoMedia.UserInfoReader e = null;
    private UserInfoMedia.UserInfoWriter j = null;
    private boolean h = false;
    private AtomicBoolean f = new AtomicBoolean(false);

    /* renamed from: a, reason: collision with root package name */
    private BroadcastReceiver f469a = new BroadcastReceiver() { // from class: bqs.4
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, final Intent intent) {
            bqs.this.b.post(new Runnable() { // from class: bqs.4.2
                @Override // java.lang.Runnable
                public void run() {
                    tC_(intent);
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void tC_(Intent intent) {
            String action = intent.getAction();
            if (action == null) {
                LogUtil.h("AccountDataExtMgr", "onReceive hiHealthCloudSyncReceiver action is null");
                return;
            }
            LogUtil.a("AccountDataExtMgr", "onReceive hiHealthCloudSyncReceiver: ", action);
            if ("com.huawei.hihealth.action_sync".equals(action)) {
                if (intent.getIntExtra("com.huawei.hihealth.action_sync_status", -1) != 2) {
                    return;
                } else {
                    nry.a(true);
                }
            } else if ("com.huawei.hihealth.action_data_refresh".equals(action)) {
                if (intent.getIntExtra("refresh_type", -1) != 5) {
                    return;
                }
            } else {
                if (!"com.huawei.hihealth.action_sync_data".equals(action)) {
                    return;
                }
                int intExtra = intent.getIntExtra("com.huawei.hihealth.action_sync_type", -1);
                int intExtra2 = intent.getIntExtra("com.huawei.hihealth.action_sync_status", -1);
                LogUtil.a("AccountDataExtMgr", "receive HiBroadcastAction.ACTION_SYNC_DATA type: ", Integer.valueOf(intExtra), " status: ", Integer.valueOf(intExtra2));
                if (intExtra == 10028 && intExtra2 == 2) {
                    nry.a(true);
                }
            }
            bqs.this.e(bqs.this.d());
        }
    };
    private Context c = null;

    @Override // com.huawei.featureuserprofile.account.ext.IAccountDataExtMgr
    public void destroy(Context context) {
    }

    class b extends MessageHandler {
        b(Context context) {
            super(context, "AccountDataExtMgr", bqs.this);
        }
    }

    @Override // com.huawei.featureuserprofile.account.ext.IAccountDataExtMgr
    public void init(Context context) {
        b bVar = new b(context);
        this.b = bVar;
        bVar.init(context);
    }

    @Override // com.huawei.featureuserprofile.common.IUserInfomationHandler
    public void processInit(Message message) {
        if (message == null) {
            LogUtil.h("AccountDataExtMgr", "processInit msg is null");
            return;
        }
        this.c = (Context) message.obj;
        this.i = new UserInfomation();
        this.e = bsj.b(this.c).obtainReader();
        this.j = bsj.b(this.c).d(2);
        this.e.unBlock();
        this.j.unBlock();
        IntentFilter intentFilter = new IntentFilter("com.huawei.hihealth.action_sync_data");
        intentFilter.addAction("com.huawei.hihealth.action_sync");
        intentFilter.addAction("com.huawei.hihealth.action_data_refresh");
        BroadcastManagerUtil.bFA_(this.c, this.f469a, intentFilter, "com.huawei.hihealth.DEFAULT_PERMISSION", null);
        final int i = message.what;
        AsyncSelectorSerialize asyncSelectorSerialize = new AsyncSelectorSerialize(this.b) { // from class: bqs.2
            @Override // com.huawei.featureuserprofile.util.AsyncSelectorSerialize
            public void onFailed(int i2) {
                ReleaseLogUtil.e("R_PersonalInfo_AccountDataExtMgr", "processInit AccountDataExtMgr end onFailed");
                if (i2 != 1) {
                    OpAnalyticsUtil.getInstance().setEventOneErrorCode(OperationKey.HEALTH_APP_PEOPLE_INFORMATION_INIT_85070024.value(), -1);
                }
                bqs.this.b.postResult(i, -1);
            }

            @Override // com.huawei.featureuserprofile.util.AsyncSelectorSerialize
            public void onSuccess(Map map) {
                ReleaseLogUtil.e("R_PersonalInfo_AccountDataExtMgr", "processInit AccountDataExtMgr end onSuccess");
                bqs.this.h = true;
                OpAnalyticsUtil.getInstance().setEventOneErrorCode(OperationKey.HEALTH_APP_PEOPLE_INFORMATION_INIT_85070024.value(), 0);
                bqs.this.b.postResult(i, 0);
            }
        };
        d(asyncSelectorSerialize);
        e(asyncSelectorSerialize);
    }

    private void d(final AsyncSelectorSerialize asyncSelectorSerialize) {
        asyncSelectorSerialize.add(new AsyncSelectorSerialize.BaseAction() { // from class: bqs.1
            @Override // com.huawei.featureuserprofile.util.AsyncSelectorSerialize.BaseAction, com.huawei.featureuserprofile.util.AsyncSelectorSerialize.Action
            public void execute(Map map) {
                bqs.this.e.read(new UserInfoMedia.UserInfoReader.Callback() { // from class: bqs.1.5
                    @Override // com.huawei.featureuserprofile.media.UserInfoMedia.UserInfoReader.Callback
                    public void onSuccess(UserInfomation userInfomation) {
                        HashMap hashMap = new HashMap();
                        if (userInfomation == null) {
                            ReleaseLogUtil.d("R_PersonalInfo_AccountDataExtMgr", "UserInfoReader read in init success AccountDataExtMgr,obj null");
                            asyncSelectorSerialize.postError();
                        } else {
                            hashMap.put("userInfo", userInfomation);
                            asyncSelectorSerialize.next(hashMap);
                        }
                    }

                    @Override // com.huawei.featureuserprofile.media.UserInfoMedia.UserInfoReader.Callback
                    public void onFail(int i) {
                        ReleaseLogUtil.d("R_PersonalInfo_AccountDataExtMgr", "MSG_INIT_DATA_FROM_HIHEALTH Fail: ", Integer.valueOf(i));
                        asyncSelectorSerialize.postError();
                    }
                });
            }
        });
    }

    @Override // com.huawei.featureuserprofile.account.ext.IAccountDataExtMgr
    public boolean checkInit() {
        return this.b.checkInit();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public AsyncSelectorSerialize d() {
        final AsyncSelectorSerialize asyncSelectorSerialize = new AsyncSelectorSerialize(this.b) { // from class: bqs.10
            @Override // com.huawei.featureuserprofile.util.AsyncSelectorSerialize
            public void onFailed(int i) {
                LogUtil.h("AccountDataExtMgr", "ACTION_SYNC_DATA actions FAIL or data not change");
            }

            @Override // com.huawei.featureuserprofile.util.AsyncSelectorSerialize
            public void onSuccess(Map map) {
                LogUtil.h("AccountDataExtMgr", "ACTION_SYNC_DATA actions SUCCESS");
                bvz.e(bqs.this.c, "com.huawei.bone.action.FITNESS_USERINFO_UPDATED");
            }
        };
        asyncSelectorSerialize.add(new AsyncSelectorSerialize.BaseAction() { // from class: bqs.7
            @Override // com.huawei.featureuserprofile.util.AsyncSelectorSerialize.BaseAction, com.huawei.featureuserprofile.util.AsyncSelectorSerialize.Action
            public void execute(Map map) {
                bqs.this.e.read(new UserInfoMedia.UserInfoReader.Callback() { // from class: bqs.7.2
                    @Override // com.huawei.featureuserprofile.media.UserInfoMedia.UserInfoReader.Callback
                    public void onSuccess(UserInfomation userInfomation) {
                        if (userInfomation == null) {
                            LogUtil.h("AccountDataExtMgr", "UserInfoReader read in init success, obj null");
                            asyncSelectorSerialize.postError();
                            return;
                        }
                        ReleaseLogUtil.e("R_PersonalInfo_AccountDataExtMgr", "CloudSync read hihealth : success");
                        bqq.d(bqs.this.c).c(userInfomation);
                        HashMap hashMap = new HashMap(16);
                        hashMap.put("userInfo", userInfomation);
                        asyncSelectorSerialize.next(hashMap);
                    }

                    @Override // com.huawei.featureuserprofile.media.UserInfoMedia.UserInfoReader.Callback
                    public void onFail(int i) {
                        ReleaseLogUtil.d("R_PersonalInfo_AccountDataExtMgr", "ACTION_SYNC_DATA get data Fail: ", Integer.valueOf(i));
                        asyncSelectorSerialize.postError();
                    }
                });
            }
        });
        return asyncSelectorSerialize;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(final AsyncSelectorSerialize asyncSelectorSerialize) {
        asyncSelectorSerialize.add(new AsyncSelectorSerialize.BaseAction() { // from class: bqs.8
            int b = -1;

            @Override // com.huawei.featureuserprofile.util.AsyncSelectorSerialize.BaseAction, com.huawei.featureuserprofile.util.AsyncSelectorSerialize.Action
            public void execute(Map map) {
                if (map == null) {
                    LogUtil.h("AccountDataExtMgr", "processCloudSync write memory error map null AccountDataExtMgr");
                    asyncSelectorSerialize.postError();
                    return;
                }
                UserInfomation userInfomation = map.get("userInfo") instanceof UserInfomation ? (UserInfomation) map.get("userInfo") : null;
                if (userInfomation != null) {
                    synchronized (bqs.d) {
                        bqs bqsVar = bqs.this;
                        if (!bqsVar.a(bqsVar.i, userInfomation)) {
                            bqs.this.i.loadAccountExtData(userInfomation);
                            LogUtil.a("AccountDataExtMgr", "processCloudSync write memory success refresh");
                            asyncSelectorSerialize.next(null);
                            return;
                        } else {
                            this.b = 1;
                            asyncSelectorSerialize.postError();
                            return;
                        }
                    }
                }
                asyncSelectorSerialize.postError();
            }

            @Override // com.huawei.featureuserprofile.util.AsyncSelectorSerialize.BaseAction, com.huawei.featureuserprofile.util.AsyncSelectorSerialize.Action
            public int getFailedValue() {
                return this.b;
            }
        });
        asyncSelectorSerialize.run();
    }

    @Override // com.huawei.featureuserprofile.account.ext.IAccountDataExtMgr
    public UserInfomation getUserInfo() {
        UserInfomation userInfomation;
        e();
        synchronized (d) {
            userInfomation = this.i;
        }
        return userInfomation;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean a(UserInfomation userInfomation, UserInfomation userInfomation2) {
        return userInfomation.getHeight() == userInfomation2.getHeight() && userInfomation.getWeight() == userInfomation2.getWeight();
    }

    private void e() {
        b bVar;
        if (this.h || (bVar = this.b) == null || !bVar.checkInit() || !this.f.compareAndSet(false, true)) {
            return;
        }
        ReleaseLogUtil.e("R_PersonalInfo_AccountDataExtMgr", "checkInitRetry need init account extend data");
        HiHealthNativeApi.a(this.c).fetchUserData(new HiCommonListener() { // from class: bqs.9
            @Override // com.huawei.hihealth.data.listener.HiCommonListener
            public void onSuccess(int i, Object obj) {
                bqs.this.f.set(false);
                if (obj == null) {
                    ReleaseLogUtil.d("R_PersonalInfo_AccountDataExtMgr", "checkInitRetry data is null");
                    return;
                }
                ArrayList arrayList = (ArrayList) obj;
                if (koq.b(arrayList)) {
                    ReleaseLogUtil.d("R_PersonalInfo_AccountDataExtMgr", "checkInitRetry hiUserList isEmpty");
                    return;
                }
                HiUserInfo hiUserInfo = (HiUserInfo) arrayList.get(0);
                synchronized (bqs.d) {
                    bqs.this.i.loadAccountExtData(hiUserInfo);
                    bqs.this.i.transSelf2METRIC();
                }
                bqs.this.h = true;
            }

            @Override // com.huawei.hihealth.data.listener.HiCommonListener
            public void onFailure(int i, Object obj) {
                ReleaseLogUtil.d("R_PersonalInfo_AccountDataExtMgr", "checkInitRetry onFailure errorCode: ", Integer.valueOf(i), " errorMessage: ", obj);
                bqs.this.f.set(false);
            }
        });
    }

    @Override // com.huawei.featureuserprofile.account.ext.IAccountDataExtMgr
    public void setUserInfo(UserInfomation userInfomation, UserInfoMedia.UserInfoWriter.Callback callback) {
        ReleaseLogUtil.e("R_PersonalInfo_AccountDataExtMgr", "setUserInfo enter isInitSuccess: ", Boolean.valueOf(this.h));
        this.b.setUserInfo(userInfomation, callback);
    }

    @Override // com.huawei.featureuserprofile.common.IUserInfomationHandler
    public void processModifyUserInfo(Message message, final UserInfomation userInfomation, final UserInfoMedia.UserInfoWriter.Callback callback) {
        LogUtil.a("AccountDataExtMgr", "enter processModifyUserInfo");
        final int i = message.what;
        userInfomation.transSelf2METRIC();
        final AsyncSelectorSerialize asyncSelectorSerialize = new AsyncSelectorSerialize(this.b) { // from class: bqs.6
            @Override // com.huawei.featureuserprofile.util.AsyncSelectorSerialize
            public void onFailed(int i2) {
                LogUtil.h("AccountDataExtMgr", "processModifyUserInfo onFailed AccountDataExtMgr");
                bqs.this.b.postResult(i, -1);
                bsf.c(callback, -1);
            }

            @Override // com.huawei.featureuserprofile.util.AsyncSelectorSerialize
            public void onSuccess(Map map) {
                LogUtil.a("AccountDataExtMgr", "processModifyUserInfo onSuccess");
                bqs.this.b.postResult(i, 0);
                bsf.b(callback);
            }
        };
        a(userInfomation, asyncSelectorSerialize);
        e(userInfomation, asyncSelectorSerialize);
        asyncSelectorSerialize.add(new AsyncSelectorSerialize.BaseAction() { // from class: bqs.12
            @Override // com.huawei.featureuserprofile.util.AsyncSelectorSerialize.BaseAction, com.huawei.featureuserprofile.util.AsyncSelectorSerialize.Action
            public void execute(Map map) {
                UserInfomation userInfomation2 = userInfomation;
                synchronized (bqs.d) {
                    bqs.this.i.loadAccountExtData(userInfomation2);
                }
                asyncSelectorSerialize.next(null);
            }
        });
        asyncSelectorSerialize.run();
    }

    private void e(final UserInfomation userInfomation, final AsyncSelectorSerialize asyncSelectorSerialize) {
        asyncSelectorSerialize.add(new AsyncSelectorSerialize.BaseAction() { // from class: bqs.5
            @Override // com.huawei.featureuserprofile.util.AsyncSelectorSerialize.BaseAction, com.huawei.featureuserprofile.util.AsyncSelectorSerialize.Action
            public void execute(Map map) {
                HiHealthNativeApi.a(bqs.this.c).fetchUserData(new HiCommonListener() { // from class: bqs.5.2
                    @Override // com.huawei.hihealth.data.listener.HiCommonListener
                    public void onSuccess(int i, Object obj) {
                        Object[] objArr = new Object[2];
                        objArr[0] = "addFetchUserInfoAct fetchUserData onSuccess data == null is ";
                        objArr[1] = Boolean.valueOf(obj == null);
                        LogUtil.a("AccountDataExtMgr", objArr);
                        if (obj == null) {
                            asyncSelectorSerialize.postError();
                            return;
                        }
                        try {
                            List list = (List) obj;
                            if (list.size() <= 0) {
                                LogUtil.b("AccountDataExtMgr", "addFetchUserInfoAct userInfo is null");
                                asyncSelectorSerialize.postError();
                            } else {
                                HiUserInfo hiUserInfo = (HiUserInfo) list.get(0);
                                userInfomation.setSetTime(hiUserInfo.getCreateTime());
                                LogUtil.a("AccountDataExtMgr", "addFetchUserInfoAct setTime = ", Long.valueOf(hiUserInfo.getCreateTime()));
                                asyncSelectorSerialize.next(null);
                            }
                        } catch (ClassCastException unused) {
                            LogUtil.b("AccountDataExtMgr", "addFetchUserInfoAct fetchUserData data ClassCastException");
                            asyncSelectorSerialize.postError();
                        }
                    }

                    @Override // com.huawei.hihealth.data.listener.HiCommonListener
                    public void onFailure(int i, Object obj) {
                        LogUtil.a("AccountDataExtMgr", "addFetchUserInfoAct fetchUserData onFailure");
                        asyncSelectorSerialize.postError();
                    }
                });
            }
        });
    }

    private void a(final UserInfomation userInfomation, final AsyncSelectorSerialize asyncSelectorSerialize) {
        asyncSelectorSerialize.add(new AsyncSelectorSerialize.BaseAction() { // from class: bqs.3
            @Override // com.huawei.featureuserprofile.util.AsyncSelectorSerialize.BaseAction, com.huawei.featureuserprofile.util.AsyncSelectorSerialize.Action
            public void execute(Map map) {
                bqs.this.j.write(userInfomation, new UserInfoMedia.UserInfoWriter.Callback() { // from class: bqs.3.5
                    @Override // com.huawei.featureuserprofile.media.UserInfoMedia.UserInfoWriter.Callback
                    public void onSuccess() {
                        LogUtil.a("AccountDataExtMgr", "addUserInfoWriteAct write hihealth success");
                        asyncSelectorSerialize.next(null);
                    }

                    @Override // com.huawei.featureuserprofile.media.UserInfoMedia.UserInfoWriter.Callback
                    public void onFail(int i) {
                        LogUtil.h("AccountDataExtMgr", "addUserInfoWriteAct write hihealth failed");
                        asyncSelectorSerialize.postError();
                    }
                });
            }
        });
    }

    @Override // com.huawei.featureuserprofile.common.IUserInfomationHandler
    public void processDestroy() {
        this.c.unregisterReceiver(this.f469a);
    }
}
