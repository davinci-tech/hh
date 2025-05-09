package defpackage;

import android.text.TextUtils;
import com.huawei.haf.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.utils.PhoneInfoUtils;
import com.huawei.pluginsport.helper.AbsAiModelConfigHelper;
import health.compact.a.CommonUtil;
import health.compact.a.HarmonyBuild;
import health.compact.a.ReleaseLogUtil;
import java.util.HashSet;
import java.util.Set;

/* loaded from: classes6.dex */
public class mwt extends AbsAiModelConfigHelper {
    private static volatile mwt b;
    private static final Set<String> d = new HashSet<String>() { // from class: mwt.3
        {
            add("HUAWEI");
        }
    };
    private static final Set<String> e = new HashSet<String>() { // from class: mwt.5
        {
            add("lahaina");
            add("kona");
            add("taro");
        }
    };
    private static final Set<String> c = new HashSet<String>() { // from class: mwt.1
        {
            add("kirin810");
        }
    };

    /* renamed from: a, reason: collision with root package name */
    private static final Set<String> f15224a = new HashSet<String>() { // from class: mwt.2
        {
            add("OCE-AN10");
            add("NOH-AL00");
            add("NOH-AL10");
            add("NOH-AN00");
            add("NOH-AN01");
            add("NOP-AN00");
            add("TAS-AL00");
            add("TAS-AN00");
            add("LIO-AL00");
            add("LIO-AN00");
            add("LIO-AN00P");
            add("ANA-AL00");
            add("ANA-AN00");
            add("ANA-TN00");
            add("ELS-AN00");
            add("ELS-TN00");
            add("ELS-AN10");
            add("ELS-TN10");
        }
    };

    private mwt() {
        super("aiFitnessSportConfig");
    }

    public static mwt d() {
        if (b == null) {
            synchronized (mwt.class) {
                if (b == null) {
                    b = new mwt();
                }
            }
        }
        return b;
    }

    @Override // com.huawei.pluginsport.helper.AbsAiModelConfigHelper
    public int getQuaModelType() {
        return getAiModel().a() ? -1 : 2;
    }

    @Override // com.huawei.pluginsport.helper.AbsAiModelConfigHelper
    public int getKirModelType(int i) {
        return (getAiModel().a() || c() || i < 300) ? -1 : 3;
    }

    @Override // com.huawei.pluginsport.helper.AbsAiModelConfigHelper
    public void initDefaultConfig() {
        LogUtil.h("AiFitnessSportConfigHelper", "use the default configuration.");
        setQuaSupportBrand(d);
        setQuaSupportCpuPlatform(e);
        setDefaultDeviceModel(f15224a);
    }

    @Override // com.huawei.pluginsport.helper.AbsAiModelConfigHelper
    public int getModelType() {
        if (!e() || nsn.ae(BaseApplication.e())) {
            return -1;
        }
        if (CommonUtil.bv()) {
            String deviceModel = PhoneInfoUtils.getDeviceModel();
            ReleaseLogUtil.b("AiFitnessSportConfigHelper", "currentModel: ", deviceModel);
            if (!getDeviceModel().contains(deviceModel.toUpperCase())) {
                return -1;
            }
            LogUtil.a("AiFitnessSportConfigHelper", "DeviceModel contains ", deviceModel);
            return super.getModelType();
        }
        return super.getModelType();
    }

    private boolean e() {
        if (!HarmonyBuild.d) {
            LogUtil.h("AiFitnessSportConfigHelper", "is not harmonyOS, not support");
            return false;
        }
        String str = HarmonyBuild.b;
        int modelType = super.getModelType();
        LogUtil.a("AiFitnessSportConfigHelper", "harmonyVersion = ", str, ", modelType = ", Integer.valueOf(modelType));
        if (!str.startsWith("2") || modelType != 2) {
            return true;
        }
        LogUtil.h("AiFitnessSportConfigHelper", "is harmonyOS 2 with Qua, not support");
        return false;
    }

    private boolean c() {
        String a2 = CommonUtil.a();
        if (TextUtils.isEmpty(a2)) {
            ReleaseLogUtil.a("AiFitnessSportConfigHelper", "cannot get cpuPlatform for kirin");
            return true;
        }
        LogUtil.a("AiFitnessSportConfigHelper", "isInBlockCpuList kirinCpuPlatform ", a2);
        boolean contains = c.contains(a2);
        ReleaseLogUtil.a("AiFitnessSportConfigHelper", "isInBlockCpuList isNotSupportCpu ", Boolean.valueOf(contains));
        return contains;
    }
}
