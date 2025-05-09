package defpackage;

import android.widget.TextView;
import com.huawei.hwlogsmodel.LogUtil;
import java.util.Iterator;
import java.util.Map;

/* loaded from: classes8.dex */
public class fph {
    public static void aCC_(int i, TextView textView, Map<Integer, fqz> map, int i2) {
        if (textView == null || map == null) {
            LogUtil.h("Suggestion_SrtShowHelper", "showSrt() params is null");
            return;
        }
        if (i > i2) {
            textView.setVisibility(8);
            return;
        }
        Iterator<Map.Entry<Integer, fqz>> it = map.entrySet().iterator();
        while (it.hasNext()) {
            fqz value = it.next().getValue();
            if (i > value.c() && i < value.d()) {
                textView.setText(value.a());
                return;
            }
        }
    }
}
