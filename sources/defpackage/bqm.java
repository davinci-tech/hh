package defpackage;

import android.content.Context;
import android.os.Message;
import android.text.TextUtils;
import com.huawei.featureuserprofile.account.data.IAccountDataMgr;
import com.huawei.featureuserprofile.common.IUserInfomationHandler;
import com.huawei.featureuserprofile.media.UserInfoMedia;
import com.huawei.featureuserprofile.util.AsyncSelectorSerialize;
import com.huawei.featureuserprofile.util.MessageHandler;
import com.huawei.featureuserprofile.util.RunnableEx;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.userprofilemgr.model.BaseResponseCallback;
import com.huawei.hihealth.HiUserInfo;
import com.huawei.hihealth.api.HiHealthNativeApi;
import com.huawei.hihealth.data.listener.HiCommonListener;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.operation.OpAnalyticsUtil;
import com.huawei.operation.OperationKey;
import com.huawei.up.model.UserInfomation;
import defpackage.bqm;
import health.compact.a.CommonUtil;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: classes3.dex */
public class bqm implements IAccountDataMgr, IUserInfomationHandler {
    private static final Object c = new Object();
    private boolean h = false;
    private UserInfomation o = null;

    /* renamed from: a, reason: collision with root package name */
    private d f450a = null;
    private Context e = null;
    private UserInfoMedia.UserInfoReader d = null;
    private UserInfoMedia.UserInfoReader b = null;
    private UserInfoMedia.UserInfoWriter j = null;
    private boolean g = false;
    private boolean i = false;
    private AtomicBoolean n = new AtomicBoolean(false);
    private AtomicBoolean f = new AtomicBoolean(false);
    private bwd l = new bwd();

    class d extends MessageHandler {
        d(Context context) {
            super(context, "AccountDataMgrByAllowLoginArea", bqm.this);
            addRunningMsg(5, new AnonymousClass2(bqm.this));
            addRunningMsg(8, new AnonymousClass4(bqm.this));
            addRunningMsg(9, null);
            addRunningMsg(6, new AnonymousClass1(bqm.this));
            addRunningMsg(7, new AnonymousClass5(bqm.this));
        }

        /* renamed from: bqm$d$2, reason: invalid class name */
        class AnonymousClass2 extends RunnableEx {

            /* renamed from: a, reason: collision with root package name */
            final /* synthetic */ bqm f460a;

            AnonymousClass2(bqm bqmVar) {
                this.f460a = bqmVar;
            }

            @Override // com.huawei.featureuserprofile.util.RunnableEx
            public void run(Message message) {
                if (bqm.this.n.get()) {
                    ReleaseLogUtil.d("R_PersonalInfo_AccountDataMgrByAllowLoginArea", "isSyncing");
                } else {
                    ThreadPoolManager.d().c("AccountDataMgrByAllowLoginArea", new Runnable() { // from class: bqo
                        @Override // java.lang.Runnable
                        public final void run() {
                            bqm.d.AnonymousClass2.this.a();
                        }
                    });
                }
            }

            /* synthetic */ void a() {
                bqm.this.b();
            }
        }

        /* renamed from: bqm$d$4, reason: invalid class name */
        class AnonymousClass4 extends RunnableEx {
            final /* synthetic */ bqm e;

            AnonymousClass4(bqm bqmVar) {
                this.e = bqmVar;
            }

            /* synthetic */ void c() {
                bqm.this.e();
            }

            @Override // com.huawei.featureuserprofile.util.RunnableEx
            public void run(Message message) {
                ThreadPoolManager.d().c("AccountDataMgrByAllowLoginArea", new Runnable() { // from class: bqn
                    @Override // java.lang.Runnable
                    public final void run() {
                        bqm.d.AnonymousClass4.this.c();
                    }
                });
            }
        }

        /* renamed from: bqm$d$1, reason: invalid class name */
        class AnonymousClass1 extends RunnableEx {
            final /* synthetic */ bqm b;

            AnonymousClass1(bqm bqmVar) {
                this.b = bqmVar;
            }

            @Override // com.huawei.featureuserprofile.util.RunnableEx
            public void run(Message message) {
                final int intValue = ((Integer) message.obj).intValue();
                ThreadPoolManager.d().c("AccountDataMgrByAllowLoginArea", new Runnable() { // from class: bqp
                    @Override // java.lang.Runnable
                    public final void run() {
                        bqm.d.AnonymousClass1.this.c(intValue);
                    }
                });
            }

            /* synthetic */ void c(int i) {
                UserInfomation userInfomation;
                bqm.this.n.set(false);
                synchronized (bqm.c) {
                    userInfomation = bqm.this.o;
                }
                bqm.this.l.e(i, userInfomation);
            }
        }

        /* renamed from: bqm$d$5, reason: invalid class name */
        class AnonymousClass5 extends RunnableEx {

            /* renamed from: a, reason: collision with root package name */
            final /* synthetic */ bqm f461a;

            AnonymousClass5(bqm bqmVar) {
                this.f461a = bqmVar;
            }

            @Override // com.huawei.featureuserprofile.util.RunnableEx
            public void run(Message message) {
                final UserInfoMedia.UserInfoReader.Callback callback = (UserInfoMedia.UserInfoReader.Callback) message.obj;
                ThreadPoolManager.d().c("AccountDataMgrByAllowLoginArea", new Runnable() { // from class: bqu
                    @Override // java.lang.Runnable
                    public final void run() {
                        bqm.d.AnonymousClass5.this.e(callback);
                    }
                });
            }

            /* synthetic */ void e(UserInfoMedia.UserInfoReader.Callback callback) {
                bqm.this.l.a(callback);
            }
        }
    }

    @Override // com.huawei.featureuserprofile.account.data.IAccountDataMgr
    public void init(Context context) {
        LogUtil.c("AccountDataMgrByAllowLoginArea", "init");
        d dVar = new d(context);
        this.f450a = dVar;
        dVar.init(context);
    }

    @Override // com.huawei.featureuserprofile.common.IUserInfomationHandler
    public void processInit(Message message) {
        if (message == null) {
            LogUtil.h("AccountDataMgrByAllowLoginArea", "processInit msg is null");
            return;
        }
        LogUtil.a("AccountDataMgrByAllowLoginArea", "processInit what:", Integer.valueOf(message.what));
        this.h = LoginInit.getInstance(this.e).isLoginedByWear();
        UserInfomation userInfomation = new UserInfomation();
        this.o = userInfomation;
        userInfomation.setPicPath("default");
        if (message.obj instanceof Context) {
            Context context = (Context) message.obj;
            this.e = context;
            this.d = bsg.b(context).obtainReader();
            this.b = bsj.b(context).obtainReader();
            this.j = bsj.b(context).d(1);
            this.d.unBlock();
            this.b.unBlock();
            this.j.unBlock();
        }
        final int i = message.what;
        AsyncSelectorSerialize asyncSelectorSerialize = new AsyncSelectorSerialize(this.f450a) { // from class: bqm.4
            @Override // com.huawei.featureuserprofile.util.AsyncSelectorSerialize
            public void onFailed(int i2) {
                ReleaseLogUtil.d("R_PersonalInfo_AccountDataMgrByAllowLoginArea", "processInit end onFailed");
                OpAnalyticsUtil.getInstance().setEventOneErrorCode(OperationKey.HEALTH_APP_PEOPLE_INFORMATION_INIT_85070024.value(), -1);
                bqm.this.f450a.postResult(i, -1);
                bqm.this.f450a.obtainMessage(5).sendToTarget();
            }

            @Override // com.huawei.featureuserprofile.util.AsyncSelectorSerialize
            public void onSuccess(Map map) {
                ReleaseLogUtil.e("R_PersonalInfo_AccountDataMgrByAllowLoginArea", "processInit end onSuccess");
                bqm.this.g = true;
                OpAnalyticsUtil.getInstance().setEventOneErrorCode(OperationKey.HEALTH_APP_PEOPLE_INFORMATION_INIT_85070024.value(), 0);
                bqm.this.f450a.postResult(i, 0);
                bqm.this.f450a.obtainMessage(5).sendToTarget();
            }
        };
        b(asyncSelectorSerialize);
        asyncSelectorSerialize.run();
    }

    private void b(final AsyncSelectorSerialize asyncSelectorSerialize) {
        asyncSelectorSerialize.add(new AsyncSelectorSerialize.BaseAction() { // from class: bqm.6
            @Override // com.huawei.featureuserprofile.util.AsyncSelectorSerialize.BaseAction, com.huawei.featureuserprofile.util.AsyncSelectorSerialize.Action
            public void execute(Map map) {
                bqm.this.b.read(new UserInfoMedia.UserInfoReader.Callback() { // from class: bqm.6.5
                    @Override // com.huawei.featureuserprofile.media.UserInfoMedia.UserInfoReader.Callback
                    public void onSuccess(UserInfomation userInfomation) {
                        HashMap hashMap = new HashMap();
                        if (userInfomation == null) {
                            LogUtil.h("AccountDataMgrByAllowLoginArea", "addProcessInitAction init from HiHealth error,obj null");
                            asyncSelectorSerialize.postError();
                        } else {
                            LogUtil.c("AccountDataMgrByAllowLoginArea", "addProcessInitAction init from HiHealth success:", userInfomation.toString());
                            hashMap.put("userInfo", userInfomation);
                            asyncSelectorSerialize.next(hashMap);
                        }
                    }

                    @Override // com.huawei.featureuserprofile.media.UserInfoMedia.UserInfoReader.Callback
                    public void onFail(int i) {
                        ReleaseLogUtil.d("R_PersonalInfo_AccountDataMgrByAllowLoginArea", "addProcessInitAction init from HiHealth failed:", Integer.valueOf(i));
                        asyncSelectorSerialize.postError();
                    }
                });
            }
        });
        asyncSelectorSerialize.add(new AsyncSelectorSerialize.BaseAction() { // from class: bqm.8
            @Override // com.huawei.featureuserprofile.util.AsyncSelectorSerialize.BaseAction, com.huawei.featureuserprofile.util.AsyncSelectorSerialize.Action
            public void execute(Map map) {
                if (map == null) {
                    LogUtil.h("AccountDataMgrByAllowLoginArea", "addProcessInitAction write memory error arg null");
                    asyncSelectorSerialize.postError();
                    return;
                }
                UserInfomation userInfomation = (UserInfomation) map.get("userInfo");
                if (userInfomation != null) {
                    synchronized (bqm.c) {
                        bqm.this.o.loadAccountData(userInfomation);
                        LogUtil.c("AccountDataMgrByAllowLoginArea", "addProcessInitAction write memory success(refresh):", bqm.this.o.toString());
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
        if (this.f450a == null) {
            LogUtil.h("AccountDataMgrByAllowLoginArea", "checkInit mHandler is null.");
            return false;
        }
        LogUtil.a("AccountDataMgrByAllowLoginArea", "checkInit mHandler is not null.");
        return this.f450a.checkInit();
    }

    @Override // com.huawei.featureuserprofile.account.data.IAccountDataMgr
    public void refreshAccountDataCache(UserInfomation userInfomation) {
        if (userInfomation == null) {
            LogUtil.h("AccountDataMgrByAllowLoginArea", "refreshAccountDataCache userInfo is null");
            return;
        }
        synchronized (c) {
            UserInfomation userInfomation2 = this.o;
            if (userInfomation2 == null) {
                this.o = userInfomation.copyFrom();
            } else {
                userInfomation2.loadAccountData(userInfomation);
            }
        }
        LogUtil.a("AccountDataMgrByAllowLoginArea", "refreshAccountDataCache end");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b() {
        UserInfomation copyFrom;
        if (this.h) {
            LogUtil.h("AccountDataMgrByAllowLoginArea", "k scence read account error");
            return;
        }
        this.n.set(true);
        synchronized (c) {
            copyFrom = this.o.copyFrom();
        }
        AsyncSelectorSerialize asyncSelectorSerialize = new AsyncSelectorSerialize(this.f450a) { // from class: bqm.9
            @Override // com.huawei.featureuserprofile.util.AsyncSelectorSerialize
            public void onFailed(int i) {
                ReleaseLogUtil.d("R_PersonalInfo_AccountDataMgrByAllowLoginArea", "processSync onFail or data no change");
                bqm.this.f450a.obtainMessage(6, -1).sendToTarget();
                bqm.this.f450a.obtainMessage(8).sendToTarget();
            }

            @Override // com.huawei.featureuserprofile.util.AsyncSelectorSerialize
            public void onSuccess(Map map) {
                ReleaseLogUtil.e("R_PersonalInfo_AccountDataMgrByAllowLoginArea", "processSync onSuccess");
                bqm.this.i = true;
                bqm.this.f450a.obtainMessage(6, 0).sendToTarget();
                bqm.this.f450a.obtainMessage(8).sendToTarget();
                bvz.e(bqm.this.e, "com.huawei.bone.action.FITNESS_USERINFO_UPDATED");
            }
        };
        LoginInit.getInstance(this.e);
        boolean shouldLogin = LoginInit.shouldLogin();
        LogUtil.a("AccountDataMgrByAllowLoginArea", "shouldLogin: ", Boolean.valueOf(shouldLogin));
        if (!CommonUtil.bu() && shouldLogin) {
            d(asyncSelectorSerialize);
        }
        e(asyncSelectorSerialize);
        b(copyFrom, asyncSelectorSerialize);
        asyncSelectorSerialize.run();
    }

    private void d(final AsyncSelectorSerialize asyncSelectorSerialize) {
        asyncSelectorSerialize.add(new AsyncSelectorSerialize.BaseAction() { // from class: bqm.7
            @Override // com.huawei.featureuserprofile.util.AsyncSelectorSerialize.BaseAction, com.huawei.featureuserprofile.util.AsyncSelectorSerialize.Action
            public void execute(Map map) {
                bqm.this.d.read(new UserInfoMedia.UserInfoReader.Callback() { // from class: bqm.7.3
                    @Override // com.huawei.featureuserprofile.media.UserInfoMedia.UserInfoReader.Callback
                    public void onSuccess(UserInfomation userInfomation) {
                        ReleaseLogUtil.e("R_PersonalInfo_AccountDataMgrByAllowLoginArea", "addProcessReadAction AccountReader read onSuccess");
                        if (userInfomation != null) {
                            LogUtil.c("AccountDataMgrByAllowLoginArea", "addProcessReadAction AccountReader read:", userInfomation.toString());
                        }
                        HashMap hashMap = new HashMap();
                        hashMap.put("userInfo", userInfomation);
                        asyncSelectorSerialize.next(hashMap);
                    }

                    @Override // com.huawei.featureuserprofile.media.UserInfoMedia.UserInfoReader.Callback
                    public void onFail(int i) {
                        ReleaseLogUtil.d("R_PersonalInfo_AccountDataMgrByAllowLoginArea", "addProcessReadAction AccountReader read onFail");
                        asyncSelectorSerialize.postError();
                    }
                });
            }
        });
    }

    private void b(final UserInfomation userInfomation, final AsyncSelectorSerialize asyncSelectorSerialize) {
        asyncSelectorSerialize.add(new AsyncSelectorSerialize.BaseAction() { // from class: bqm.10
            @Override // com.huawei.featureuserprofile.util.AsyncSelectorSerialize.BaseAction, com.huawei.featureuserprofile.util.AsyncSelectorSerialize.Action
            public void execute(Map map) {
                UserInfomation copyFrom;
                if (map == null) {
                    ReleaseLogUtil.e("R_PersonalInfo_AccountDataMgrByAllowLoginArea", "addProcessHeadImageSyncAction arg is null");
                    asyncSelectorSerialize.postError();
                    return;
                }
                UserInfomation userInfomation2 = (UserInfomation) map.get("userInfo");
                synchronized (bqm.c) {
                    if (TextUtils.isEmpty(userInfomation2.getPortraitUrl())) {
                        bqm.this.o.setPortraitUrl("");
                        bvx.d("");
                    }
                    bqm.this.o.loadAccountData(userInfomation2);
                    copyFrom = bqm.this.o.copyFrom();
                    LogUtil.c("AccountDataMgrByAllowLoginArea", "addProcessHeadImageSyncAction write memory success(refresh):", bqm.this.o.toString());
                }
                if (bqm.this.c(userInfomation, copyFrom)) {
                    bqm.this.i = true;
                    ReleaseLogUtil.e("R_PersonalInfo_AccountDataMgrByAllowLoginArea", "addProcessHeadImageSyncAction isAccountDataEquals");
                    asyncSelectorSerialize.postError();
                    return;
                }
                asyncSelectorSerialize.next(null);
            }
        });
    }

    private void e(final AsyncSelectorSerialize asyncSelectorSerialize) {
        asyncSelectorSerialize.add(new AsyncSelectorSerialize.BaseAction() { // from class: bqm.15
            @Override // com.huawei.featureuserprofile.util.AsyncSelectorSerialize.BaseAction, com.huawei.featureuserprofile.util.AsyncSelectorSerialize.Action
            public void execute(Map map) {
                if (map == null) {
                    ReleaseLogUtil.e("R_PersonalInfo_AccountDataMgrByAllowLoginArea", "addProcessGenderSyncAction arg is null");
                    asyncSelectorSerialize.postError();
                    return;
                }
                if (map.get("userInfo") instanceof UserInfomation) {
                    final UserInfomation userInfomation = (UserInfomation) map.get("userInfo");
                    synchronized (bqm.c) {
                        bqm.this.o.setBirthdayStatus(userInfomation.getBirthdayStatus());
                        LogUtil.c("AccountDataMgrByAllowLoginArea", "addProcessGenderSyncAction write memory success(refresh) only birthdayStatus:", bqm.this.o.getBirthdayStatus());
                    }
                    if (userInfomation.getGender() == -1) {
                        ReleaseLogUtil.d("R_PersonalInfo_AccountDataMgrByAllowLoginArea", "account's gender is unknown, try to use hihealth gender");
                        synchronized (bqm.c) {
                            userInfomation.setGender(Integer.valueOf(bqm.this.o.getGender()));
                        }
                    }
                    bqm.this.j.write(userInfomation, new UserInfoMedia.UserInfoWriter.Callback() { // from class: bqm.15.2
                        @Override // com.huawei.featureuserprofile.media.UserInfoMedia.UserInfoWriter.Callback
                        public void onSuccess() {
                            LogUtil.c("AccountDataMgrByAllowLoginArea", "addProcessGenderSyncAction write hihealth success:", userInfomation.toString());
                            HashMap hashMap = new HashMap();
                            hashMap.put("userInfo", userInfomation);
                            asyncSelectorSerialize.next(hashMap);
                        }

                        @Override // com.huawei.featureuserprofile.media.UserInfoMedia.UserInfoWriter.Callback
                        public void onFail(int i) {
                            LogUtil.c("AccountDataMgrByAllowLoginArea", "addProcessGenderSyncAction write hihealth fail:", userInfomation.toString());
                            asyncSelectorSerialize.postError();
                        }
                    });
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean c(UserInfomation userInfomation, UserInfomation userInfomation2) {
        if (userInfomation == null && userInfomation2 == null) {
            LogUtil.a("AccountDataMgrByAllowLoginArea", "userInfo1 and userInfo2 null,equals");
            return true;
        }
        if (userInfomation == null || userInfomation2 == null) {
            LogUtil.a("AccountDataMgrByAllowLoginArea", "userInfo1 or userInfo2 null,no equals");
            return false;
        }
        boolean equals = TextUtils.equals(userInfomation.getName(), userInfomation2.getName());
        if (!TextUtils.equals(userInfomation.getLanguageCode(), userInfomation2.getLanguageCode())) {
            equals = false;
        }
        if (!TextUtils.equals(userInfomation.getBirthday(), userInfomation2.getBirthday())) {
            equals = false;
        }
        if (userInfomation.getGender() != userInfomation2.getGender()) {
            equals = false;
        }
        if (TextUtils.equals(userInfomation.getPortraitUrl(), userInfomation2.getPortraitUrl())) {
            return equals;
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e() {
        String portraitUrl;
        synchronized (c) {
            portraitUrl = this.o.getPortraitUrl();
        }
        if (TextUtils.isEmpty(portraitUrl)) {
            LogUtil.h("AccountDataMgrByAllowLoginArea", "userInfo portraitUrl empty,force default");
            return;
        }
        if (bvx.e(portraitUrl)) {
            LogUtil.h("AccountDataMgrByAllowLoginArea", "userInfo picPath validate,return");
            this.f450a.obtainMessage(9, -1).sendToTarget();
        } else {
            AsyncSelectorSerialize asyncSelectorSerialize = new AsyncSelectorSerialize(this.f450a) { // from class: bqm.14
                @Override // com.huawei.featureuserprofile.util.AsyncSelectorSerialize
                public void onFailed(int i) {
                    bqm.this.f450a.obtainMessage(9, -1).sendToTarget();
                }

                @Override // com.huawei.featureuserprofile.util.AsyncSelectorSerialize
                public void onSuccess(Map map) {
                    bqm.this.f450a.obtainMessage(9, 0).sendToTarget();
                }
            };
            e(portraitUrl, asyncSelectorSerialize);
            asyncSelectorSerialize.run();
        }
    }

    private void e(final String str, final AsyncSelectorSerialize asyncSelectorSerialize) {
        asyncSelectorSerialize.add(new AsyncSelectorSerialize.BaseAction() { // from class: bqm.13
            @Override // com.huawei.featureuserprofile.util.AsyncSelectorSerialize.BaseAction, com.huawei.featureuserprofile.util.AsyncSelectorSerialize.Action
            public void execute(Map map) {
                bvx.b(bqm.this.e, str, new BaseResponseCallback<String>() { // from class: bqm.13.5
                    @Override // com.huawei.health.userprofilemgr.model.BaseResponseCallback
                    /* renamed from: b, reason: merged with bridge method [inline-methods] */
                    public void onResponse(int i, String str2) {
                        if (i != 0) {
                            asyncSelectorSerialize.postError();
                            return;
                        }
                        bvx.b(str);
                        bvx.d(str2);
                        HashMap hashMap = new HashMap();
                        hashMap.put("newPath", str2);
                        LogUtil.a("AccountDataMgrByAllowLoginArea", "downloadHeadPic newPath:", str2);
                        asyncSelectorSerialize.next(hashMap);
                    }
                });
            }
        });
        asyncSelectorSerialize.add(new AsyncSelectorSerialize.BaseAction() { // from class: bqm.1
            @Override // com.huawei.featureuserprofile.util.AsyncSelectorSerialize.BaseAction, com.huawei.featureuserprofile.util.AsyncSelectorSerialize.Action
            public void execute(Map map) {
                if (map.get("newPath") instanceof String) {
                    String str2 = (String) map.get("newPath");
                    synchronized (bqm.c) {
                        bqm.this.o.setPicPath(str2);
                        LogUtil.c("AccountDataMgrByAllowLoginArea", "addHeadPicAsyncSelectorSerializeAction write memory(refresh):", bqm.this.o.toString());
                    }
                }
                asyncSelectorSerialize.next(null);
            }
        });
    }

    @Override // com.huawei.featureuserprofile.account.data.IAccountDataMgr
    public UserInfomation getUserInfo() {
        UserInfomation userInfomation;
        d();
        synchronized (c) {
            userInfomation = this.o;
        }
        return userInfomation;
    }

    private void d() {
        if (this.g) {
            if (this.h || CommonUtil.bu() || this.i) {
                LogUtil.a("AccountDataMgrByAllowLoginArea", "mIsSyncSuccess: ", Boolean.valueOf(this.i));
                return;
            } else {
                if (this.n.get()) {
                    return;
                }
                ReleaseLogUtil.e("R_PersonalInfo_AccountDataMgrByAllowLoginArea", "checkInitRetry need sync account data");
                sync();
                glz.b("AccountDataMgrByAllowLoginArea");
                return;
            }
        }
        d dVar = this.f450a;
        if (dVar != null && dVar.checkInit() && this.f.compareAndSet(false, true)) {
            ReleaseLogUtil.e("R_PersonalInfo_AccountDataMgrByAllowLoginArea", "checkInitRetry need init account data");
            HiHealthNativeApi.a(this.e).fetchUserData(new HiCommonListener() { // from class: bqm.2
                @Override // com.huawei.hihealth.data.listener.HiCommonListener
                public void onSuccess(int i, Object obj) {
                    bqm.this.f.set(false);
                    if (obj == null) {
                        ReleaseLogUtil.d("R_PersonalInfo_AccountDataMgrByAllowLoginArea", "checkInitRetry data is null");
                        return;
                    }
                    ArrayList arrayList = (ArrayList) obj;
                    if (koq.b(arrayList)) {
                        ReleaseLogUtil.d("R_PersonalInfo_AccountDataMgrByAllowLoginArea", "checkInitRetry hiUserList isEmpty");
                        return;
                    }
                    HiUserInfo hiUserInfo = (HiUserInfo) arrayList.get(0);
                    synchronized (bqm.c) {
                        bqm.this.o.loadAccountData(hiUserInfo);
                        if (TextUtils.isEmpty(bqm.this.o.getPicPath()) || "default".equals(bqm.this.o.getPicPath())) {
                            bqm.this.o.setPicPath(bvx.a());
                        }
                    }
                    bqm.this.g = true;
                }

                @Override // com.huawei.hihealth.data.listener.HiCommonListener
                public void onFailure(int i, Object obj) {
                    ReleaseLogUtil.d("R_PersonalInfo_AccountDataMgrByAllowLoginArea", "checkInitRetry onFailure errorCode:", Integer.valueOf(i), " errorMessage:", obj);
                    bqm.this.f.set(false);
                }
            });
        }
    }

    @Override // com.huawei.featureuserprofile.account.data.IAccountDataMgr
    public void sync(UserInfoMedia.UserInfoReader.Callback callback) {
        if (callback == null) {
            LogUtil.h("AccountDataMgrByAllowLoginArea", "not allow get data by account with no callback");
        } else {
            if (this.h) {
                LogUtil.h("AccountDataMgrByAllowLoginArea", "k scence read account error");
                return;
            }
            LogUtil.a("AccountDataMgrByAllowLoginArea", "Synchronizing Account Information");
            this.f450a.obtainMessage(5).sendToTarget();
            this.f450a.obtainMessage(7, callback).sendToTarget();
        }
    }

    @Override // com.huawei.featureuserprofile.account.data.IAccountDataMgr
    public void sync() {
        if (this.h) {
            LogUtil.h("AccountDataMgrByAllowLoginArea", "k scence read account error");
        } else {
            LogUtil.a("AccountDataMgrByAllowLoginArea", "Synchronize with the account cloud");
            this.f450a.obtainMessage(5).sendToTarget();
        }
    }

    @Override // com.huawei.featureuserprofile.account.data.IAccountDataMgr
    public void setUserInfo(UserInfomation userInfomation, UserInfoMedia.UserInfoWriter.Callback callback) {
        ReleaseLogUtil.e("R_PersonalInfo_AccountDataMgrByAllowLoginArea", "setUserInfo enter isInitSuccess = ", Boolean.valueOf(this.g), ", accountInfo isSyncSuccess = ", Boolean.valueOf(this.i));
        d dVar = this.f450a;
        if (dVar == null) {
            return;
        }
        dVar.setUserInfo(userInfomation, callback);
    }

    @Override // com.huawei.featureuserprofile.common.IUserInfomationHandler
    public void processModifyUserInfo(Message message, final UserInfomation userInfomation, final UserInfoMedia.UserInfoWriter.Callback callback) {
        if (message == null) {
            LogUtil.h("AccountDataMgrByAllowLoginArea", "processModifyUserInfo msg is null");
            return;
        }
        final int i = message.what;
        final AsyncSelectorSerialize asyncSelectorSerialize = new AsyncSelectorSerialize(this.f450a) { // from class: bqm.3
            @Override // com.huawei.featureuserprofile.util.AsyncSelectorSerialize
            public void onFailed(int i2) {
                LogUtil.h("AccountDataMgrByAllowLoginArea", "processModifyUserInfo onFailed (AccountDataMgrByAllowLoginArea)");
                bqm.this.f450a.postResult(i, -1);
                bsf.c(callback, -1);
            }

            @Override // com.huawei.featureuserprofile.util.AsyncSelectorSerialize
            public void onSuccess(Map map) {
                LogUtil.a("AccountDataMgrByAllowLoginArea", "processModifyUserInfo onSuccess");
                bqm.this.f450a.postResult(i, 0);
                bsf.b(callback);
            }
        };
        asyncSelectorSerialize.add(new AsyncSelectorSerialize.BaseAction() { // from class: bqm.5
            @Override // com.huawei.featureuserprofile.util.AsyncSelectorSerialize.BaseAction, com.huawei.featureuserprofile.util.AsyncSelectorSerialize.Action
            public void execute(Map map) {
                synchronized (bqm.c) {
                    bqm.this.o.loadAccountData(userInfomation);
                    LogUtil.c("AccountDataMgrByAllowLoginArea", "processModifyUserInfo write memory success(refresh):", bqm.this.o.toString());
                }
                asyncSelectorSerialize.next(null);
            }
        });
        asyncSelectorSerialize.run();
    }

    @Override // com.huawei.featureuserprofile.account.data.IAccountDataMgr
    public void destroy() {
        LogUtil.h("AccountDataMgrByAllowLoginArea", "destroy to enter");
    }

    @Override // com.huawei.featureuserprofile.common.IUserInfomationHandler
    public void processDestroy() {
        this.l.d();
        for (int i = 5; i < 10; i++) {
            this.f450a.removeMessages(i);
        }
    }
}
