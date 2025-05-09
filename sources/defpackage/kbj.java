package defpackage;

import com.huawei.haf.application.BaseApplication;
import com.huawei.hwlogsmodel.common.LogConfig;
import health.compact.a.ProcessUtil;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/* loaded from: classes5.dex */
public class kbj {
    public static final String b = LogConfig.o();
    public static final String c = BaseApplication.d();

    /* renamed from: a, reason: collision with root package name */
    public static final String f14253a = "dfx_log_" + BaseApplication.a();
    public static final String j = ProcessUtil.b("_PhoneService");
    public static final String d = ProcessUtil.b("_DaemonService");
    public static final List<String> e = Collections.unmodifiableList(Arrays.asList(com.huawei.hwcommonmodel.application.BaseApplication.getAppPackage(), "Fortuna", "Talos", "ELA", "Crius", "Terra", "Andes", "Latona", "Diana", "Minos", "Hebe", "Fides", "Janus", "AW70", "TerraB09"));
}
