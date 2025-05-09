package defpackage;

import com.huawei.ui.main.stories.privacy.template.model.bean.PrivacyDataModel;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes7.dex */
public class rru {
    private static volatile rru c;
    private static final Object d = new Object();
    private Map<String, List<PrivacyDataModel>> b = new LinkedHashMap(10);

    public static rru d() {
        if (c == null) {
            synchronized (d) {
                if (c == null) {
                    c = new rru();
                }
            }
        }
        return c;
    }

    public List<PrivacyDataModel> e(String str) {
        return this.b.get(str);
    }

    public Map<String, List<PrivacyDataModel>> c() {
        return this.b;
    }

    public void b(String str, PrivacyDataModel privacyDataModel) {
        if (this.b.containsKey(str)) {
            this.b.get(str).add(privacyDataModel);
            return;
        }
        ArrayList arrayList = new ArrayList(10);
        arrayList.add(privacyDataModel);
        this.b.put(str, arrayList);
    }

    public void b() {
        this.b.clear();
    }
}
