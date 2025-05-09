package defpackage;

import android.content.Context;
import android.os.Message;
import com.huawei.featureuserprofile.account.data.IAccountDataMgr;
import com.huawei.featureuserprofile.common.IUserInfomationHandler;
import com.huawei.featureuserprofile.media.UserInfoMedia;
import com.huawei.featureuserprofile.util.AsyncSelectorSerialize;
import com.huawei.featureuserprofile.util.MessageHandler;
import com.huawei.hihealth.HiUserInfo;
import com.huawei.hihealth.api.HiHealthNativeApi;
import com.huawei.hihealth.data.listener.HiCommonListener;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.up.model.UserInfomation;
import health.compact.a.UnitUtil;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: classes3.dex */
public class bqr implements IAccountDataMgr, IUserInfomationHandler {
    private static final Object c = new Object();
    private b e = null;
    private UserInfomation h = null;
    private UserInfoMedia.UserInfoReader d = null;

    /* renamed from: a, reason: collision with root package name */
    private UserInfoMedia.UserInfoWriter f464a = null;
    private boolean i = false;
    private Context b = null;
    private AtomicBoolean g = new AtomicBoolean(false);

    class b extends MessageHandler {
        b(Context context) {
            super(context, "AccountDataMgrByNotAllowLoginArea", bqr.this);
        }
    }

    @Override // com.huawei.featureuserprofile.account.data.IAccountDataMgr
    public void init(Context context) {
        b bVar = new b(context);
        this.e = bVar;
        bVar.init(context);
    }

    @Override // com.huawei.featureuserprofile.common.IUserInfomationHandler
    public void processInit(Message message) {
        if (message == null) {
            LogUtil.h("AccountDataMgrByNotAllowLoginArea", "processInit msg is null");
            return;
        }
        this.b = (Context) message.obj;
        if (UnitUtil.h()) {
            this.h = new UserInfomation(1);
        } else {
            this.h = new UserInfomation(0);
        }
        this.d = bsj.b(this.b).obtainReader();
        this.f464a = bsj.b(this.b).d(1);
        this.d.unBlock();
        this.f464a.unBlock();
        final int i = message.what;
        AsyncSelectorSerialize asyncSelectorSerialize = new AsyncSelectorSerialize(this.e) { // from class: bqr.1
            @Override // com.huawei.featureuserprofile.util.AsyncSelectorSerialize
            public void onFailed(int i2) {
                ReleaseLogUtil.e("R_PersonalInfo_AccountDataMgrByNotAllowLoginArea", "processInit(AccountDataMgrByNotAllowLoginArea) end onFailed");
                bqr.this.e.postResult(i, -1);
            }

            @Override // com.huawei.featureuserprofile.util.AsyncSelectorSerialize
            public void onSuccess(Map map) {
                ReleaseLogUtil.e("R_PersonalInfo_AccountDataMgrByNotAllowLoginArea", "processInit(AccountDataMgrByNotAllowLoginArea) end onSuccess");
                bqr.this.i = true;
                bqr.this.e.postResult(i, 0);
            }
        };
        a(asyncSelectorSerialize);
        asyncSelectorSerialize.run();
    }

    private void a(final AsyncSelectorSerialize asyncSelectorSerialize) {
        asyncSelectorSerialize.add(new AsyncSelectorSerialize.BaseAction() { // from class: bqr.5
            @Override // com.huawei.featureuserprofile.util.AsyncSelectorSerialize.BaseAction, com.huawei.featureuserprofile.util.AsyncSelectorSerialize.Action
            public void execute(Map map) {
                bqr.this.d.read(new UserInfoMedia.UserInfoReader.Callback() { // from class: bqr.5.2
                    @Override // com.huawei.featureuserprofile.media.UserInfoMedia.UserInfoReader.Callback
                    public void onSuccess(UserInfomation userInfomation) {
                        HashMap hashMap = new HashMap();
                        if (userInfomation == null) {
                            ReleaseLogUtil.d("R_PersonalInfo_AccountDataMgrByNotAllowLoginArea", "UserInfoReader read in init success (AccountDataMgrByNotAllowLoginArea) ,obj null");
                            asyncSelectorSerialize.next(null);
                        } else {
                            hashMap.put("userInfo", userInfomation);
                            asyncSelectorSerialize.next(hashMap);
                        }
                    }

                    @Override // com.huawei.featureuserprofile.media.UserInfoMedia.UserInfoReader.Callback
                    public void onFail(int i) {
                        ReleaseLogUtil.d("R_PersonalInfo_AccountDataMgrByNotAllowLoginArea", "MSG_INIT_DATA_FROM_HIHEALTH Fail:", Integer.valueOf(i));
                        asyncSelectorSerialize.postError();
                    }
                });
            }
        });
        asyncSelectorSerialize.add(new AsyncSelectorSerialize.BaseAction() { // from class: bqr.2
            @Override // com.huawei.featureuserprofile.util.AsyncSelectorSerialize.BaseAction, com.huawei.featureuserprofile.util.AsyncSelectorSerialize.Action
            public void execute(Map map) {
                if (map == null) {
                    LogUtil.h("AccountDataMgrByNotAllowLoginArea", "addAsyncSelectorSerializeAct write memory error arg null (AccountDataMgrByNotAllowLoginArea)");
                    asyncSelectorSerialize.postError();
                    return;
                }
                UserInfomation userInfomation = map.get("userInfo") instanceof UserInfomation ? (UserInfomation) map.get("userInfo") : null;
                if (userInfomation != null) {
                    synchronized (bqr.c) {
                        bqr.this.h.loadAccountData(userInfomation);
                        LogUtil.c("AccountDataMgrByNotAllowLoginArea", "addAsyncSelectorSerializeAct write memory(refresh):", bqr.this.h.toString());
                    }
                    asyncSelectorSerialize.next(null);
                    return;
                }
                asyncSelectorSerialize.postError();
            }
        });
    }

    @Override // com.huawei.featureuserprofile.account.data.IAccountDataMgr
    public boolean checkInit() {
        b bVar = this.e;
        if (bVar == null) {
            LogUtil.h("AccountDataMgrByNotAllowLoginArea", "checkInit mHandler is null.");
            return false;
        }
        return bVar.checkInit();
    }

    @Override // com.huawei.featureuserprofile.account.data.IAccountDataMgr
    public UserInfomation getUserInfo() {
        UserInfomation userInfomation;
        a();
        synchronized (c) {
            userInfomation = this.h;
        }
        return userInfomation;
    }

    private void a() {
        b bVar;
        if (this.i || (bVar = this.e) == null || !bVar.checkInit() || !this.g.compareAndSet(false, true)) {
            return;
        }
        ReleaseLogUtil.e("R_PersonalInfo_AccountDataMgrByNotAllowLoginArea", "checkInitRetry need init account data");
        HiHealthNativeApi.a(this.b).fetchUserData(new HiCommonListener() { // from class: bqr.3
            @Override // com.huawei.hihealth.data.listener.HiCommonListener
            public void onSuccess(int i, Object obj) {
                bqr.this.g.set(false);
                if (obj == null) {
                    ReleaseLogUtil.d("R_PersonalInfo_AccountDataMgrByNotAllowLoginArea", "checkInitRetry data is null");
                    return;
                }
                ArrayList arrayList = (ArrayList) obj;
                if (koq.b(arrayList)) {
                    ReleaseLogUtil.d("R_PersonalInfo_AccountDataMgrByNotAllowLoginArea", "checkInitRetry hiUserList isEmpty");
                    return;
                }
                HiUserInfo hiUserInfo = (HiUserInfo) arrayList.get(0);
                synchronized (bqr.c) {
                    bqr.this.h.loadAccountData(hiUserInfo);
                    bqr.this.h.setPicPath(bvx.a());
                }
                bqr.this.i = true;
            }

            @Override // com.huawei.hihealth.data.listener.HiCommonListener
            public void onFailure(int i, Object obj) {
                ReleaseLogUtil.d("R_PersonalInfo_AccountDataMgrByNotAllowLoginArea", "checkInitRetry onFailure errorCode:", Integer.valueOf(i), " errorMessage:", obj);
                bqr.this.g.set(false);
            }
        });
    }

    @Override // com.huawei.featureuserprofile.account.data.IAccountDataMgr
    public void refreshAccountDataCache(UserInfomation userInfomation) {
        if (userInfomation == null) {
            LogUtil.h("AccountDataMgrByNotAllowLoginArea", "refreshAccountDataCache userInfo is null");
            return;
        }
        synchronized (c) {
            UserInfomation userInfomation2 = this.h;
            if (userInfomation2 == null) {
                this.h = userInfomation.copyFrom();
            } else {
                userInfomation2.loadAccountData(userInfomation);
            }
        }
        LogUtil.a("AccountDataMgrByNotAllowLoginArea", "refreshAccountDataCache end");
    }

    @Override // com.huawei.featureuserprofile.account.data.IAccountDataMgr
    public void setUserInfo(UserInfomation userInfomation, UserInfoMedia.UserInfoWriter.Callback callback) {
        ReleaseLogUtil.e("R_PersonalInfo_AccountDataMgrByNotAllowLoginArea", "setUserInfo enter isInitSuccess = ", Boolean.valueOf(this.i));
        this.e.setUserInfo(userInfomation, callback);
    }

    @Override // com.huawei.featureuserprofile.account.data.IAccountDataMgr
    public void sync() {
        LogUtil.h("AccountDataMgrByNotAllowLoginArea", "sync():", "NotAllowLoginArea cant sync with Account");
    }

    @Override // com.huawei.featureuserprofile.account.data.IAccountDataMgr
    public void sync(UserInfoMedia.UserInfoReader.Callback callback) {
        LogUtil.h("AccountDataMgrByNotAllowLoginArea", "sync(UserInfoMedia.UserInfoReader.Callback callback):", "NotAllowLoginArea cant sync with Account");
    }

    @Override // com.huawei.featureuserprofile.common.IUserInfomationHandler
    public void processModifyUserInfo(Message message, final UserInfomation userInfomation, final UserInfoMedia.UserInfoWriter.Callback callback) {
        if (message == null) {
            LogUtil.h("AccountDataMgrByNotAllowLoginArea", "processModifyUserInfo msg is null");
            return;
        }
        final int i = message.what;
        final AsyncSelectorSerialize asyncSelectorSerialize = new AsyncSelectorSerialize(this.e) { // from class: bqr.4
            @Override // com.huawei.featureuserprofile.util.AsyncSelectorSerialize
            public void onFailed(int i2) {
                bqr.this.e.postResult(i, -1);
                bsf.c(callback, -1);
            }

            @Override // com.huawei.featureuserprofile.util.AsyncSelectorSerialize
            public void onSuccess(Map map) {
                bqr.this.e.postResult(i, 0);
                bsf.b(callback);
            }
        };
        asyncSelectorSerialize.add(new AsyncSelectorSerialize.BaseAction() { // from class: bqr.8
            @Override // com.huawei.featureuserprofile.util.AsyncSelectorSerialize.BaseAction, com.huawei.featureuserprofile.util.AsyncSelectorSerialize.Action
            public void execute(Map map) {
                bqr.this.f464a.write(userInfomation, new UserInfoMedia.UserInfoWriter.Callback() { // from class: bqr.8.1
                    @Override // com.huawei.featureuserprofile.media.UserInfoMedia.UserInfoWriter.Callback
                    public void onSuccess() {
                        LogUtil.c("AccountDataMgrByNotAllowLoginArea", "processModifyUserInfo write hihealth success:", userInfomation.toString());
                        asyncSelectorSerialize.next(null);
                    }

                    @Override // com.huawei.featureuserprofile.media.UserInfoMedia.UserInfoWriter.Callback
                    public void onFail(int i2) {
                        LogUtil.c("AccountDataMgrByNotAllowLoginArea", "processModifyUserInfo write hihealth fail:", userInfomation.toString());
                        asyncSelectorSerialize.postError();
                    }
                });
            }
        });
        asyncSelectorSerialize.add(new AsyncSelectorSerialize.BaseAction() { // from class: bqr.9
            @Override // com.huawei.featureuserprofile.util.AsyncSelectorSerialize.BaseAction, com.huawei.featureuserprofile.util.AsyncSelectorSerialize.Action
            public void execute(Map map) {
                UserInfomation userInfomation2 = userInfomation;
                synchronized (bqr.c) {
                    bqr.this.h.loadAccountData(userInfomation2);
                    LogUtil.c("AccountDataMgrByNotAllowLoginArea", "processModifyUserInfo write memory(refresh):", bqr.this.h.toString());
                }
                asyncSelectorSerialize.next(null);
            }
        });
        asyncSelectorSerialize.run();
    }

    @Override // com.huawei.featureuserprofile.account.data.IAccountDataMgr
    public void destroy() {
        LogUtil.h("AccountDataMgrByNotAllowLoginArea", "destroy to enter");
    }

    @Override // com.huawei.featureuserprofile.common.IUserInfomationHandler
    public void processDestroy() {
        LogUtil.a("AccountDataMgrByNotAllowLoginArea", "processDestroy");
    }
}
