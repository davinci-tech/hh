package defpackage;

import com.huawei.hihealth.HiHealthData;
import com.huawei.hwlogsmodel.LogUtil;
import java.util.List;

/* loaded from: classes7.dex */
public class iiw {

    /* renamed from: a, reason: collision with root package name */
    private ihe f13389a;

    private iiw() {
        this.f13389a = ihe.b();
    }

    static class d {
        private static final iiw b = new iiw();
    }

    public static iiw e() {
        return d.b;
    }

    public List<HiHealthData> c(int i, List<Integer> list, String str) {
        int size = list.size();
        StringBuffer stringBuffer = new StringBuffer((size * 2) + 32);
        String[] strArr = new String[size + 1];
        stringBuffer.append("type_id =? ");
        strArr[0] = Long.toString(i);
        iil.a("client_id", list, stringBuffer, strArr, 1);
        LogUtil.c("Debug_DataRealTimeManager", "queryRealTimeData() sbSelector = ", stringBuffer);
        return iih.bAP_(this.f13389a.query(stringBuffer.toString(), strArr, null, null, "start_time DESC "), str);
    }
}
