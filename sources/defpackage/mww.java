package defpackage;

import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.pluginsport.helper.AbsAiModelConfigHelper;
import java.util.HashSet;
import java.util.Set;

/* loaded from: classes.dex */
public class mww extends AbsAiModelConfigHelper {
    private static volatile mww d;
    private static final Set<String> c = new HashSet<String>() { // from class: mww.2
        {
            add("Xiaomi");
            add("HUAWEI");
            add("HONOR");
            add("Redmi");
        }
    };
    private static final Set<String> e = new HashSet<String>() { // from class: mww.5
        {
            add("lahaina");
            add("msmnile");
            add("kona");
            add("lito");
        }
    };

    private mww() {
        super("aiRopeSkippingConfig");
    }

    public static mww d() {
        if (d == null) {
            synchronized (mww.class) {
                if (d == null) {
                    d = new mww();
                }
            }
        }
        return d;
    }

    @Override // com.huawei.pluginsport.helper.AbsAiModelConfigHelper
    public void initDefaultConfig() {
        LogUtil.h("AiRopeSkippingConfigHelper", "use the default configuration.");
        setQuaSupportBrand(c);
        setQuaSupportCpuPlatform(e);
    }

    @Override // com.huawei.pluginsport.helper.AbsAiModelConfigHelper
    public int getModelType() {
        return super.getModelType();
    }
}
