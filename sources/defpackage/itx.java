package defpackage;

import android.content.Context;
import android.content.Intent;
import com.apprichtap.haptic.base.PrebakedEffectId;
import com.huawei.hihealth.HiGoalInfo;
import com.huawei.hihealth.HiUserInfo;
import com.huawei.hihealth.HiUserPreference;
import com.huawei.hihealth.util.HiBroadcastUtil;
import com.huawei.hihealthservice.sync.syncdata.HiSyncBase;
import com.huawei.hwcloudmodel.model.userprofile.GetUserProfileReq;
import com.huawei.hwcloudmodel.model.userprofile.GetUserProfileRsp;
import com.huawei.hwcloudmodel.model.userprofile.SetUserProfileReq;
import com.huawei.hwcloudmodel.model.userprofile.UserBasicInfo;
import com.huawei.hwcloudmodel.model.userprofile.UserGoalsInfo;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.CompileParameterUtil;
import health.compact.a.HiCommonUtil;
import health.compact.a.LocalBroadcast;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: classes7.dex */
public class itx implements HiSyncBase {
    private static ArrayList<String> b = new ArrayList<>(2);
    private static ArrayList<Integer> e = new ArrayList<>(1);

    /* renamed from: a, reason: collision with root package name */
    private Context f13608a;
    private int c;
    private jbs d;
    private isx g;
    private ijz h;
    private List<Integer> i;

    @Override // com.huawei.hihealthservice.sync.syncdata.HiSyncBase
    public void pullDataByTime(long j, long j2) throws iut {
    }

    static {
        b.add("custom.start_weight_base");
        b.add("custom.weight_goal_daily_diff");
        e.add(5);
    }

    public itx(Context context, int i) {
        LogUtil.c("HiH_HiSyncUserData", "HiSyncUserData create");
        this.f13608a = context.getApplicationContext();
        this.c = i;
        i();
    }

    private void i() {
        this.h = ijz.c();
        this.g = new isx();
        this.d = jbs.a(this.f13608a);
        this.i = new ArrayList(10);
    }

    private HiUserInfo g() {
        HiUserInfo c = this.h.c(this.c, 0);
        if (c == null) {
            LogUtil.h("HiH_HiSyncUserData", "uploadUserBasic no userInfo get");
        }
        return c;
    }

    private List<HiGoalInfo> a() {
        return ijg.d().b(this.c, 0);
    }

    private void e(List<UserGoalsInfo> list) {
        List<HiGoalInfo> b2 = this.g.b(list);
        if (b2 == null || b2.isEmpty()) {
            LogUtil.h("HiH_HiSyncUserData", "saveUserGoalsToDB no userGoalsInfos can change to hiGoalInfos");
            return;
        }
        for (HiGoalInfo hiGoalInfo : b2) {
            hiGoalInfo.setOwnerId(this.c);
            ijg.d().d(hiGoalInfo, 1);
        }
        ivg.c().a(101, "HiSyncUserData", new ikv(this.f13608a.getPackageName()));
    }

    private void c(UserBasicInfo userBasicInfo) {
        HiUserInfo e2 = this.g.e(userBasicInfo);
        if (e2 == null) {
            LogUtil.h("HiH_HiSyncUserData", "saveUserInfoToDB no userBasicInfo can change to hiUserInfo");
            return;
        }
        if (this.h.d(e2, this.c, 1) > 0) {
            HiBroadcastUtil.d(this.f13608a, PrebakedEffectId.RT_JUMP, 2);
            HiBroadcastUtil.d(this.f13608a, 5);
            ivg.c().a(100, "HiSyncUserData", new ikv(this.f13608a.getPackageName()));
        }
        LogUtil.a("HiH_HiSyncUserData", "saveUserInfoToDB setIfUserFirstSync false");
        ism.f().a(Integer.toString(this.c), false);
    }

    private void c(Map<String, String> map) {
        List<HiUserPreference> e2 = this.g.e(map);
        if (e2 == null || e2.isEmpty()) {
            return;
        }
        for (HiUserPreference hiUserPreference : e2) {
            hiUserPreference.setUserId(this.c);
            String key = hiUserPreference.getKey();
            HiUserPreference a2 = ijy.c(this.f13608a).a(this.c, key);
            if (a2 == null || a2.getSyncStatus() != 0) {
                boolean b2 = ijy.c(this.f13608a).b(hiUserPreference);
                if ("custom.wear_common_setting".equals(key)) {
                    LogUtil.a("HiH_HiSyncUserData", "cloud wear setting is ", hiUserPreference, ", isChange=", Boolean.valueOf(b2), ", custom list size=", Integer.valueOf(e2.size()));
                }
            }
        }
        HiBroadcastUtil.d(this.f13608a, 7);
        ivg.c().a(102, "HiSyncUserData", new ikv(this.f13608a.getPackageName()));
    }

    private List<HiUserPreference> d() {
        return ijy.c(this.f13608a).d(this.c, 0);
    }

    private void e() throws iut {
        boolean d = ism.f().d(String.valueOf(this.c));
        if (!ism.o() && !iuz.f()) {
            LogUtil.h("HiH_HiSyncUserData", "downLoadUserData() userPrivacy switch is closed ,can not download right now!");
            if (ism.c()) {
                return;
            }
            e(d);
            return;
        }
        b();
        GetUserProfileReq getUserProfileReq = new GetUserProfileReq();
        getUserProfileReq.setProfileType(this.i);
        GetUserProfileRsp c = this.d.c(getUserProfileReq);
        if (c == null) {
            LogUtil.h("HiH_HiSyncUserData", "downLoadUserData rsp is null");
            e(d);
            return;
        }
        int intValue = c.getResultCode().intValue();
        if (intValue == 0) {
            ism.f().c(String.valueOf(this.c), false);
            c(c.getBasic());
            e(c.getGoals());
            c(c.getCustomDefine());
            return;
        }
        LogUtil.b("HiH_HiSyncUserData", "downLoadUserData fail. error code is ", Integer.valueOf(intValue));
        e(d);
        if (intValue == 30001) {
            ism.f().a(Integer.toString(this.c), false);
        }
    }

    private void e(boolean z) {
        GetUserProfileReq getUserProfileReq = new GetUserProfileReq();
        ArrayList arrayList = new ArrayList(2);
        arrayList.add(99);
        arrayList.add(2);
        getUserProfileReq.setProfileType(arrayList);
        GetUserProfileRsp c = this.d.c(getUserProfileReq);
        try {
            if (!ius.a(c, false)) {
                LogUtil.h("HiH_HiSyncUserData", "downloadHealthSettingInfo rsp is fail");
                return;
            }
            ism.f().c(String.valueOf(this.c), false);
            c(c.getCustomDefine());
            e(c.getGoals());
        } catch (iut e2) {
            ReleaseLogUtil.d("HiH_HiSyncUserData", "downloadHealthSettingInfo rsp is error, e= ", e2.getMessage());
            if (c != null && 30007 == c.getResultCode().intValue() && z) {
                ism.f().c(String.valueOf(this.c), false);
            }
        }
    }

    private void j() throws iut {
        boolean z;
        SetUserProfileReq setUserProfileReq = new SetUserProfileReq();
        HiUserInfo g = g();
        UserBasicInfo d = this.g.d(g);
        boolean z2 = true;
        if (d != null) {
            setUserProfileReq.setBasic(d);
            z = true;
        } else {
            z = false;
        }
        List<HiGoalInfo> a2 = a();
        List<UserGoalsInfo> a3 = this.g.a(a2);
        if (a3 == null || a3.isEmpty()) {
            z2 = z;
        } else {
            setUserProfileReq.setGoals(a3);
        }
        List<HiUserPreference> d2 = d();
        Map<String, String> c = this.g.c(d2);
        if (c != null && !c.isEmpty()) {
            setUserProfileReq.setCustomDefine(c);
        } else if (!z2) {
            LogUtil.h("HiH_HiSyncUserData", "uploadUserData nothing to pushData");
            return;
        }
        if (!ius.a(this.d.d(setUserProfileReq), false)) {
            LogUtil.h("HiH_HiSyncUserData", "uploadUserData rsp error");
            return;
        }
        if (d != null) {
            a(g);
            if (CompileParameterUtil.a("SUPPORT_WIFI_WEIGHT_DEVICE", false)) {
                LogUtil.a("HiH_HiSyncUserData", "setwifiuserinfo by HiSyncUserData");
                Intent intent = new Intent();
                intent.setAction("com.huawei.health.action.ACTION_WIFI_USERINFO_ACTION");
                intent.setPackage(this.f13608a.getPackageName());
                this.f13608a.sendBroadcast(intent, LocalBroadcast.c);
            }
        }
        if (a3 != null && !a3.isEmpty()) {
            b(a2);
        }
        if (c == null || c.isEmpty()) {
            return;
        }
        d(d2);
    }

    private void a(HiUserInfo hiUserInfo) {
        this.h.d(hiUserInfo, this.c, 1);
    }

    private void b(List<HiGoalInfo> list) {
        for (HiGoalInfo hiGoalInfo : list) {
            if (hiGoalInfo != null) {
                ijg.d().c(this.c, hiGoalInfo.getGoalType(), 1);
            }
        }
    }

    private void d(List<HiUserPreference> list) {
        for (HiUserPreference hiUserPreference : list) {
            HiUserPreference a2 = ijy.c(this.f13608a).a(hiUserPreference.getUserId(), hiUserPreference.getKey());
            if (a2 != null && a2.getModifiedTime() == hiUserPreference.getModifiedTime()) {
                ijy.c(this.f13608a).a(hiUserPreference, 1);
            }
        }
    }

    private void b() {
        this.i.add(1);
        this.i.add(2);
        this.i.add(99);
    }

    @Override // com.huawei.hihealthservice.sync.syncdata.HiSyncBase
    public void pullDataByVersion() throws iut {
        LogUtil.a("HiH_HiSyncUserData", "downLoad() begin !");
        e();
        LogUtil.a("HiH_HiSyncUserData", "downLoad() end !");
    }

    @Override // com.huawei.hihealthservice.sync.syncdata.HiSyncBase
    public void pushData() throws iut {
        LogUtil.a("HiH_HiSyncUserData", "upLoad() begin !");
        if (!ism.o()) {
            LogUtil.h("HiH_HiSyncUserData", "pushData() userPrivacy switch is closed ,can not pushData right now ,push end!");
            if (!ism.c() && !ism.f().d(Integer.toString(this.c))) {
                h();
                return;
            } else {
                f();
                return;
            }
        }
        if (ism.f().c(Integer.toString(this.c))) {
            LogUtil.c("HiH_HiSyncUserData", "first user sync do not upload userData, who is ", Integer.valueOf(this.c));
            if (!ism.f().d(Integer.toString(this.c))) {
                h();
            }
        } else {
            j();
        }
        LogUtil.a("HiH_HiSyncUserData", "upLoad() end !");
    }

    private void f() {
        boolean z;
        SetUserProfileReq setUserProfileReq = new SetUserProfileReq();
        List<HiUserPreference> d = d();
        if (!HiCommonUtil.d(d)) {
            Iterator<HiUserPreference> it = d.iterator();
            while (it.hasNext()) {
                if (!b.contains(it.next().getKey())) {
                    it.remove();
                }
            }
        }
        Map<String, String> c = this.g.c(d);
        if (c == null || c.isEmpty()) {
            z = false;
        } else {
            setUserProfileReq.setCustomDefine(c);
            z = true;
        }
        List<HiGoalInfo> a2 = a();
        if (!HiCommonUtil.d(a2)) {
            Iterator<HiGoalInfo> it2 = a2.iterator();
            while (it2.hasNext()) {
                if (!e.contains(Integer.valueOf(it2.next().getGoalType()))) {
                    it2.remove();
                }
            }
        }
        List<UserGoalsInfo> a3 = this.g.a(a2);
        if (a3 != null && !a3.isEmpty()) {
            setUserProfileReq.setGoals(a3);
        } else if (!z) {
            LogUtil.h("HiH_HiSyncUserData", "uploadWhiteCustom nothing to pushData");
            return;
        }
        try {
            if (!ius.a(this.d.d(setUserProfileReq), false)) {
                LogUtil.h("HiH_HiSyncUserData", "uploadWhiteCustom rsp fail");
                return;
            }
            if (c != null && !c.isEmpty()) {
                d(d);
            }
            if (a3 == null || a3.isEmpty()) {
                return;
            }
            b(a2);
        } catch (iut e2) {
            ReleaseLogUtil.d("HiH_HiSyncUserData", "uploadWhiteCustom rsp error e=", e2.getMessage());
        }
    }

    private void h() {
        boolean z;
        SetUserProfileReq setUserProfileReq = new SetUserProfileReq();
        List<HiUserPreference> d = d();
        Map<String, String> c = this.g.c(d);
        if (c == null || c.isEmpty()) {
            z = false;
        } else {
            setUserProfileReq.setCustomDefine(c);
            z = true;
        }
        List<HiGoalInfo> a2 = a();
        List<UserGoalsInfo> a3 = this.g.a(a2);
        if (a3 != null && !a3.isEmpty()) {
            setUserProfileReq.setGoals(a3);
        } else if (!z) {
            LogUtil.h("HiH_HiSyncUserData", "uploadHealthSettingInfo nothing to pushData");
            return;
        }
        try {
            if (!ius.a(this.d.d(setUserProfileReq), false)) {
                LogUtil.h("HiH_HiSyncUserData", "uploadHealthSettingInfo rsp fail");
                return;
            }
            if (c != null && !c.isEmpty()) {
                d(d);
            }
            if (a3 == null || a3.isEmpty()) {
                return;
            }
            b(a2);
        } catch (iut e2) {
            ReleaseLogUtil.d("HiH_HiSyncUserData", "uploadHealthSettingInfo rsp error e=", e2.getMessage());
        }
    }

    public boolean e(HiUserInfo hiUserInfo) throws iut {
        LogUtil.a("HiH_HiSyncUserData", "start upLoadBasicInfoOnly");
        if (!ism.o()) {
            LogUtil.h("HiH_HiSyncUserData", "upLoadBasicInfoOnly() userPrivacy switch is closed ,can not pushData right now ,push end!");
            return false;
        }
        UserBasicInfo d = this.g.d(hiUserInfo);
        if (d == null) {
            return false;
        }
        SetUserProfileReq setUserProfileReq = new SetUserProfileReq();
        setUserProfileReq.setBasic(d);
        if (ius.a(this.d.d(setUserProfileReq), false)) {
            HiBroadcastUtil.d(this.f13608a, 10005, 2);
            a(hiUserInfo);
            return true;
        }
        HiBroadcastUtil.d(this.f13608a, 10005, 3);
        return false;
    }

    public boolean c() throws iut {
        LogUtil.a("HiH_HiSyncUserData", "start downLoadBasicInfoOnly");
        GetUserProfileReq getUserProfileReq = new GetUserProfileReq();
        this.i.add(1);
        getUserProfileReq.setProfileType(this.i);
        GetUserProfileRsp c = this.d.c(getUserProfileReq);
        if (ius.a(c, false)) {
            c(c.getBasic());
            return true;
        }
        HiBroadcastUtil.d(this.f13608a, PrebakedEffectId.RT_JUMP, 3);
        return false;
    }

    public String toString() {
        return "--HiSyncUserData{}";
    }
}
