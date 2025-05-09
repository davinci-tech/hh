package defpackage;

import com.google.gson.annotations.SerializedName;
import com.huawei.openalliance.ad.db.bean.EventMonitorRecord;
import java.util.Map;

/* loaded from: classes7.dex */
class qur {

    @SerializedName(EventMonitorRecord.EVENT_ID)
    private String d;

    @SerializedName("biMap")
    private Map<String, Object> e;

    qur() {
    }

    public String c() {
        return this.d;
    }

    public Map<String, Object> a() {
        return this.e;
    }

    public String toString() {
        return "BiMetaData{mEventId='" + this.d + "', mBiMap=" + this.e + '}';
    }
}
