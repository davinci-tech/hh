package defpackage;

import android.util.LruCache;
import com.huawei.hihealth.HiHealthData;
import health.compact.a.HiCommonUtil;

/* loaded from: classes7.dex */
public class ikt {

    /* renamed from: a, reason: collision with root package name */
    private LruCache<String, String> f13417a;

    private ikt() {
        this.f13417a = new LruCache<>(20);
    }

    static class a {
        private static final ikt d = new ikt();
    }

    public static ikt e() {
        return a.d;
    }

    public void a(HiHealthData hiHealthData) {
        if (hiHealthData == null) {
            return;
        }
        String c = c(hiHealthData);
        String str = this.f13417a.get(c);
        if (str == null) {
            str = "";
        }
        this.f13417a.put(c, str + hiHealthData.getSequenceData());
    }

    public String d(HiHealthData hiHealthData) {
        if (hiHealthData == null) {
            return "";
        }
        String c = c(hiHealthData);
        String str = this.f13417a.get(c);
        if (HiCommonUtil.b(str)) {
            return hiHealthData.getSequenceData();
        }
        this.f13417a.remove(c);
        return str + hiHealthData.getSequenceData();
    }

    private String c(HiHealthData hiHealthData) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(hiHealthData.getStartTime()).append("_").append(hiHealthData.getEndTime()).append("_").append(hiHealthData.getType()).append("_").append(hiHealthData.getClientId()).append("_").append(hiHealthData.getDataId());
        return stringBuffer.toString();
    }
}
