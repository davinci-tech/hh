package defpackage;

import android.text.TextUtils;
import com.huawei.haf.application.BaseApplication;
import com.huawei.health.vip.api.VipApi;
import com.huawei.health.vip.api.VipCallback;
import com.huawei.health.vip.datatypes.UserMemberInfo;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.Services;
import health.compact.a.SharedPreferenceManager;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/* loaded from: classes3.dex */
public class dxz {
    private static volatile dxz c;
    private static final Object d = new Object();

    /* renamed from: a, reason: collision with root package name */
    private UserMemberInfo f11906a;

    public static dxz d() {
        LogUtil.a("QueryMemberManager", "QueryMemberManager getInstance");
        if (c == null) {
            synchronized (d) {
                if (c == null) {
                    c = new dxz();
                }
            }
        }
        return c;
    }

    protected List<String> a(List<String> list) {
        LogUtil.a("QueryMemberManager", "getMemberInfo start");
        ArrayList arrayList = new ArrayList(3);
        String b = SharedPreferenceManager.b(BaseApplication.e(), Integer.toString(10000), "USER_VIP_INFO_KEY");
        if (TextUtils.isEmpty(b)) {
            LogUtil.a("QueryMemberManager", "getMemberInfo from sp is empty. use netWork request.");
            return b(list, arrayList);
        }
        LogUtil.a("QueryMemberManager", "getMemberInfo from sp");
        UserMemberInfo userMemberInfo = (UserMemberInfo) moj.e(b, UserMemberInfo.class);
        this.f11906a = userMemberInfo;
        if (userMemberInfo == null) {
            return b(list, arrayList);
        }
        e(list, arrayList);
        return arrayList;
    }

    private List<String> b(List<String> list, List<String> list2) {
        CountDownLatch countDownLatch = new CountDownLatch(1);
        e(list2, countDownLatch, list);
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            LogUtil.b("QueryMemberManager", "getVipMemberInfo:InterruptedException exception = ", e.getMessage());
        }
        LogUtil.a("QueryMemberManager", "getviplabel by network finish.");
        return list2;
    }

    private void d(List<String> list) {
        UserMemberInfo userMemberInfo = this.f11906a;
        if (userMemberInfo == null) {
            LogUtil.h("QueryMemberManager", "mUserMemberInfo is null.");
            return;
        }
        int memberFlag = userMemberInfo.getMemberFlag();
        long expireTime = this.f11906a.getExpireTime();
        long nowTime = this.f11906a.getNowTime();
        String str = (Double.isNaN((double) expireTime) || Double.isNaN((double) nowTime) || expireTime <= nowTime || memberFlag != 1) ? "UserMemberFlag_0" : "UserMemberFlag_1";
        LogUtil.c("QueryMemberManager", "generateMemberFlagLabel value = ", str);
        list.add(str);
    }

    private void c(List<String> list) {
        UserMemberInfo userMemberInfo = this.f11906a;
        if (userMemberInfo == null) {
            LogUtil.h("QueryMemberManager", "mUserMemberInfo is null.");
            return;
        }
        int autoRenew = userMemberInfo.getAutoRenew();
        String str = (Double.isNaN((double) autoRenew) || autoRenew != 1) ? "UserMemberType_0" : "UserMemberType_1";
        LogUtil.c("QueryMemberManager", "generateMemberTypeLabel value = ", str);
        list.add(str);
    }

    private void e(List<String> list) {
        String str;
        UserMemberInfo userMemberInfo = this.f11906a;
        if (userMemberInfo == null) {
            LogUtil.h("QueryMemberManager", "mUserMemberInfo is null.");
            return;
        }
        long expireTime = userMemberInfo.getExpireTime();
        long nowTime = this.f11906a.getNowTime();
        if (!Double.isNaN(expireTime) && !Double.isNaN(nowTime)) {
            if (expireTime < nowTime) {
                str = "UserMemberStage_0";
            } else if (expireTime <= nowTime || expireTime - nowTime > 259200000) {
                LogUtil.a("QueryMemberManager", "memberFlag value = ", Integer.valueOf(this.f11906a.getMemberFlag()));
            } else {
                str = "UserMemberStage_1";
            }
            LogUtil.c("QueryMemberManager", "generateMemberStageLabel value = ", str);
            list.add(str);
        }
        str = "";
        LogUtil.c("QueryMemberManager", "generateMemberStageLabel value = ", str);
        list.add(str);
    }

    private void e(final List<String> list, final CountDownLatch countDownLatch, final List<String> list2) {
        VipApi vipApi = (VipApi) Services.a("vip", VipApi.class);
        if (vipApi == null) {
            LogUtil.h("QueryMemberManager", "refreshVipStateData VipApi is null");
            countDownLatch.countDown();
        } else {
            vipApi.getVipInfo(new VipCallback() { // from class: dxz.1
                @Override // com.huawei.health.vip.api.VipCallback
                public void onSuccess(Object obj) {
                    if (obj == null) {
                        LogUtil.a("QueryMemberManager", "getVipMemberInfo onSuccess result is null");
                        countDownLatch.countDown();
                        return;
                    }
                    if (obj instanceof UserMemberInfo) {
                        dxz.this.f11906a = (UserMemberInfo) obj;
                        if (dxz.this.f11906a != null) {
                            LogUtil.a("QueryMemberManager", "getVipMemberInfo mUserMemberInfo = ", dxz.this.f11906a.toString());
                            dxz.this.e(list2, list);
                        } else {
                            LogUtil.h("QueryMemberManager", "getVipMemberInfo memberInfo == null ");
                            countDownLatch.countDown();
                            return;
                        }
                    }
                    countDownLatch.countDown();
                }

                @Override // com.huawei.health.vip.api.VipCallback
                public void onFailure(int i, String str) {
                    LogUtil.h("QueryMemberManager", "getVipMemberInfo errorCode=", Integer.valueOf(i), " errMsg=", str);
                    countDownLatch.countDown();
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(List<String> list, List<String> list2) {
        if (list.contains("health_sport_member_flag")) {
            d(list2);
        }
        if (list.contains("health_sport_member_stage")) {
            e(list2);
        }
        if (list.contains("health_sport_member_type")) {
            c(list2);
        }
    }
}
