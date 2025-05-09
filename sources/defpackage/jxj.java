package defpackage;

import android.text.TextUtils;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.CommonUtil;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes5.dex */
public class jxj {
    public static jxs d(cwe cweVar) {
        jxs jxsVar = new jxs();
        if (cweVar == null) {
            LogUtil.h("BasicUnTlvData", "tlvFather is null");
            return jxsVar;
        }
        ArrayList arrayList = new ArrayList(16);
        List<cwe> a2 = cweVar.a();
        for (int i = 0; i < a2.size(); i++) {
            List<cwd> e = a2.get(i).e();
            for (int i2 = 0; i2 < e.size(); i2++) {
                String c = e.get(i2).c();
                String e2 = e.get(i2).e();
                if (!TextUtils.isEmpty(c) && !TextUtils.isEmpty(e2)) {
                    int w = CommonUtil.w(e2);
                    if (w == 2) {
                        jxsVar.a(CommonUtil.w(c));
                    } else if (w == 5) {
                        arrayList.add(Integer.valueOf(CommonUtil.w(c)));
                    } else {
                        LogUtil.h("BasicUnTlvData", "unTlvGetFrameCountByTime default");
                    }
                }
            }
        }
        jxsVar.c(arrayList);
        return jxsVar;
    }

    public static jxo b(cwe cweVar) {
        return jxm.c(cweVar);
    }
}
