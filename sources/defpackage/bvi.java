package defpackage;

import android.text.TextUtils;
import com.huawei.hwcommonmodel.utils.DateFormatUtil;
import com.huawei.hwlogsmodel.LogUtil;
import java.io.Serializable;
import java.text.ParseException;
import java.util.Comparator;

/* loaded from: classes3.dex */
public class bvi implements Comparator, Serializable {
    @Override // java.util.Comparator
    public int compare(Object obj, Object obj2) {
        if (!(obj instanceof ceb) || !(obj2 instanceof ceb)) {
            return 1;
        }
        ceb cebVar = (ceb) obj;
        ceb cebVar2 = (ceb) obj2;
        if (TextUtils.isEmpty(cebVar.s()) || TextUtils.isEmpty(cebVar2.s())) {
            return 1;
        }
        return Long.compare(c(cebVar.s()), c(cebVar2.s()));
    }

    private long c(String str) {
        try {
            return DateFormatUtil.e(str, DateFormatUtil.DateFormatType.DEFAULT_DATE_FORMAT, jdl.b()).getTime();
        } catch (ParseException e) {
            LogUtil.b("PreviewActivityComparator", "格式日期出错:" + e.getMessage());
            return 0L;
        }
    }
}
