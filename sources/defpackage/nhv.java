package defpackage;

import com.huawei.haf.common.exception.ExceptionUtils;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes6.dex */
public class nhv {

    /* renamed from: a, reason: collision with root package name */
    private int f15292a;
    private final String b;
    private int c;
    private long d;
    private int e;
    private long g;

    public nhv(JSONObject jSONObject, String str) {
        if (jSONObject.has("fallAsleepTime")) {
            d(jSONObject);
        }
        if (jSONObject.has("wakeupTime")) {
            c(jSONObject);
        }
        if (jSONObject.has("sleepDataQuality")) {
            try {
                int i = jSONObject.getInt("sleepDataQuality");
                this.e = i;
                ReleaseLogUtil.e("BTSYNC_CoreSleep_MetaDataCoreField", " mSleepDataQuality is ", Integer.valueOf(i));
            } catch (JSONException e) {
                ReleaseLogUtil.c("BTSYNC_CoreSleep_MetaDataCoreField", " get mSleepDataQuality error: ", ExceptionUtils.d(e));
            }
        }
        if (jSONObject.has("sleepScore")) {
            try {
                this.c = jSONObject.getInt("sleepScore");
            } catch (JSONException e2) {
                ReleaseLogUtil.c("BTSYNC_CoreSleep_MetaDataCoreField", " get mScore error: ", ExceptionUtils.d(e2));
            }
        }
        if (jSONObject.has("validData")) {
            try {
                this.f15292a = jSONObject.getInt("validData");
            } catch (JSONException e3) {
                ReleaseLogUtil.c("BTSYNC_CoreSleep_MetaDataCoreField", " get mValidData error: ", ExceptionUtils.d(e3));
            }
        }
        this.b = str;
        ReleaseLogUtil.e("BTSYNC_CoreSleep_MetaDataCoreField", "sleepDataQuality is ", Integer.valueOf(this.e), ", validData is ", Integer.valueOf(this.f15292a));
        LogUtil.a("MetaDataCoreField", "fallAsleepTime is ", Long.valueOf(this.d), ", wakeupTime is ", Long.valueOf(this.g), ", score is ", Integer.valueOf(this.c), ", mDeviceUuid is ", str);
    }

    private void d(JSONObject jSONObject) {
        try {
            long j = jSONObject.getLong("fallAsleepTime");
            if (j % 60 != 0) {
                ReleaseLogUtil.d("BTSYNC_CoreSleep_MetaDataCoreField", "fallAsleepTime not whole minute");
                j = (j / 60) * 60;
            }
            this.d = j * 1000;
        } catch (JSONException e) {
            ReleaseLogUtil.c("BTSYNC_CoreSleep_MetaDataCoreField", "get mFallAsleepTime error: ", ExceptionUtils.d(e));
        }
    }

    private void c(JSONObject jSONObject) {
        try {
            long j = jSONObject.getLong("wakeupTime");
            if (j % 60 != 0) {
                ReleaseLogUtil.d("BTSYNC_CoreSleep_MetaDataCoreField", "mWakeupTime not whole minute");
                j = (j / 60) * 60;
            }
            this.g = j * 1000;
        } catch (JSONException e) {
            ReleaseLogUtil.c("BTSYNC_CoreSleep_MetaDataCoreField", " get mWakeupTime error: ", ExceptionUtils.d(e));
        }
    }

    public long e() {
        return this.d;
    }

    public long c() {
        return this.g;
    }

    public int d() {
        return this.c;
    }

    public int b() {
        return this.f15292a;
    }

    public String a() {
        return this.b;
    }
}
