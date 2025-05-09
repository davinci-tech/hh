package defpackage;

import com.huawei.health.baseapi.pluginachievement.baseapi.AchieveDeviceApi;
import com.huawei.health.userprofilemgr.model.BaseResponseCallback;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.pluginachievement.medal.model.TrackData;
import java.util.ArrayList;

/* loaded from: classes3.dex */
public class bzv implements AchieveDeviceApi {

    /* renamed from: a, reason: collision with root package name */
    private AchieveDeviceApi f575a;

    static class e {
        public static final bzv e = new bzv();
    }

    private bzv() {
        try {
            Object newInstance = Class.forName("mxo").newInstance();
            if (newInstance instanceof AchieveDeviceApi) {
                this.f575a = (AchieveDeviceApi) newInstance;
                LogUtil.a("PLGACHIEVE_AchieveDeviceProxy", "init AchieveDeviceProxy ok");
            } else {
                LogUtil.h("PLGACHIEVE_AchieveDeviceProxy", "init AchieveDeviceProxy no");
            }
        } catch (ClassNotFoundException unused) {
            LogUtil.b("PLGACHIEVE_AchieveDeviceProxy", "ClassNotFoundException");
        } catch (Exception unused2) {
            LogUtil.b("PLGACHIEVE_AchieveDeviceProxy", "Exception");
        }
    }

    public static bzv a() {
        return e.e;
    }

    @Override // com.huawei.health.baseapi.pluginachievement.baseapi.AchieveDeviceApi
    public void pingDevice(String str) {
        AchieveDeviceApi achieveDeviceApi = this.f575a;
        if (achieveDeviceApi != null) {
            achieveDeviceApi.pingDevice(str);
        }
    }

    @Override // com.huawei.health.baseapi.pluginachievement.baseapi.AchieveDeviceApi
    public void syncInit() {
        AchieveDeviceApi achieveDeviceApi = this.f575a;
        if (achieveDeviceApi != null) {
            achieveDeviceApi.syncInit();
        }
    }

    @Override // com.huawei.health.baseapi.pluginachievement.baseapi.AchieveDeviceApi
    public void sendMessageToDevice(int i) {
        AchieveDeviceApi achieveDeviceApi = this.f575a;
        if (achieveDeviceApi != null) {
            achieveDeviceApi.sendMessageToDevice(i);
        }
    }

    @Override // com.huawei.health.baseapi.pluginachievement.baseapi.AchieveDeviceApi
    public void sendMessageToDevice(int i, String str, String str2, BaseResponseCallback<Boolean> baseResponseCallback) {
        AchieveDeviceApi achieveDeviceApi = this.f575a;
        if (achieveDeviceApi != null) {
            achieveDeviceApi.sendMessageToDevice(i, str, str2, baseResponseCallback);
        }
    }

    @Override // com.huawei.health.baseapi.pluginachievement.baseapi.AchieveDeviceApi
    public void sendMessageToDeviceForPackage(int i) {
        AchieveDeviceApi achieveDeviceApi = this.f575a;
        if (achieveDeviceApi != null) {
            achieveDeviceApi.sendMessageToDeviceForPackage(i);
        }
    }

    @Override // com.huawei.health.baseapi.pluginachievement.baseapi.AchieveDeviceApi
    public void dealAchieveTrackData(ArrayList<TrackData> arrayList) {
        AchieveDeviceApi achieveDeviceApi = this.f575a;
        if (achieveDeviceApi != null) {
            achieveDeviceApi.dealAchieveTrackData(arrayList);
        }
    }
}
