package defpackage;

import com.google.gson.annotations.SerializedName;
import health.compact.a.CommonUtil;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes9.dex */
public class kui {

    /* renamed from: a, reason: collision with root package name */
    @SerializedName("aggregateType")
    private String f14601a;

    @SerializedName("constantsKey")
    private List<String> b;

    @SerializedName("dataFilter")
    private String c;

    @SerializedName("count")
    private int d;

    @SerializedName("anchor")
    private int e;

    @SerializedName("groupUnitSize")
    private int f;

    @SerializedName("groupUnitType")
    private int g;

    @SerializedName("localDate")
    private String h;

    @SerializedName("endTime")
    private long i;

    @SerializedName("deviceUuid")
    private String j;

    @SerializedName("readType")
    private int k;

    @SerializedName("startTime")
    private long l;

    @SerializedName("sortOrder")
    private int m;

    @SerializedName("subTypes")
    private List<Integer> n;

    @SerializedName("sequenceDataType")
    private int o;

    @SerializedName("dataTypes")
    private List<Integer> s;

    public long k() {
        return this.l;
    }

    public long g() {
        return this.i;
    }

    public int[] m() {
        return CommonUtil.b(this.s);
    }

    public int i() {
        return this.g;
    }

    public int a() {
        return this.d;
    }

    public int b() {
        return this.e;
    }

    public int o() {
        return this.m;
    }

    public int h() {
        return this.k;
    }

    public String j() {
        return this.j;
    }

    public List<String> e() {
        return this.b;
    }

    public int d() {
        return kuh.c(this.f14601a);
    }

    public int f() {
        return this.f;
    }

    public Map<Integer, JSONObject> c() {
        HashMap hashMap = new HashMap();
        for (Map.Entry<Integer, Integer> entry : kuf.a().c(m()).entrySet()) {
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put(kuh.f14600a, e());
                jSONObject.put(kuh.d, entry.getValue());
                hashMap.put(entry.getKey(), jSONObject);
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }
        }
        return hashMap;
    }

    public String toString() {
        return "AggregateRequest{mStartTime=" + this.l + ", mEndTime=" + this.i + ", mTypes=" + this.s + ", mLocalDate='" + this.h + "', mCount=" + this.d + ", anchor=" + this.e + ", mSortOrder=" + this.m + ", mReadType=" + this.k + ", mDeviceUuid='" + this.j + "', mConstantsKeys=" + this.b + ", mSequenceDataType=" + this.o + ", mAggregateType=" + this.f14601a + ", mGroupUnitSize=" + this.f + ", mGroupUnitType=" + this.g + ", mDataFilter='" + this.c + "', mSubTypes=" + this.n + '}';
    }
}
