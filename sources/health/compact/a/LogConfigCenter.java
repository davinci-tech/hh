package health.compact.a;

import com.huawei.hwlogsmodel.common.LogConfig;
import com.huawei.ui.thirdpartservice.syncdata.constants.SyncDataConstant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* loaded from: classes.dex */
public class LogConfigCenter {
    private LogConfigCenter() {
    }

    private static List<String> a() {
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(Arrays.asList("Login", "Track", "Step", "HiH", "PluginDevice", "HealthAdapter", "HWhealthLinkage", "SCUI", "UIHLH", "UIME", "PLGACHIEVE", "UIDV", "PLGLOGIN", "Opera", "Share", "Suggestion", "TimeEat", "Group", "GRS", "Dfx", SyncDataConstant.SYNC_DATA_TRACKS_EXPORT_FOLDER, "BaseModule", "ServicesApi", "EcologyDevice"));
        arrayList.addAll(Arrays.asList("BTSDK", "DMS", "Fitness", "KIDWATCH", "Notify", "OTA", "SMART", "DEVMGR", "AW70", "CORESLEEPMISSON", "WearEngine", "HWWEAR"));
        arrayList.addAll(e());
        return arrayList;
    }

    private static List<String> e() {
        return Arrays.asList("R", "QrCode", "BloodOxygen", "PersonalInfo", "Sleep", "Weight", "Bundle", "HAF", "Nps", "BTSYNC");
    }

    private static List<String> d() {
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(Arrays.asList("Login", "Track", "FitnessSug", "Dfx", SyncDataConstant.SYNC_DATA_TRACKS_EXPORT_FOLDER, "ServicesApi"));
        arrayList.addAll(Arrays.asList("HiH", "PluginDevice", "HealthAdapter", "Suggestion", "HWhealthLinkage", "SCUI", "UIHLH", "UIME", "PLGACHIEVE", "UIDV", "PLGLOGIN", "Opera", "Share", "GRS", "EcologyDevice"));
        arrayList.addAll(Arrays.asList("BTSDK", "DMS", "Fitness", "KIDWATCH", "Notify", "OTA", "SMART", "DEVMGR", "CORESLEEPMISSON"));
        arrayList.addAll(b());
        return arrayList;
    }

    private static List<String> b() {
        return Arrays.asList("R", "QrCode", "BloodOxygen", "PersonalInfo", "Sleep", "Weight", "Bundle", "HAF", "BTSYNC");
    }

    private static LogAllowListConfig c() {
        LogAllowListConfig logAllowListConfig = new LogAllowListConfig();
        logAllowListConfig.a(a());
        logAllowListConfig.b(d());
        return logAllowListConfig;
    }

    public static com.huawei.hwlogsmodel.common.LogConfig e(String str) {
        return new LogConfig.d(str).e(c()).e();
    }

    public static com.huawei.hwlogsmodel.common.LogConfig b(String str) {
        return new LogConfig.d(str).b(5).e(5242880.0d).e(c()).e();
    }

    public static com.huawei.hwlogsmodel.common.LogConfig c(String str) {
        return new LogConfig.d(str).e(c()).b(30).c(false).c(5242880.0d).e();
    }

    public static com.huawei.hwlogsmodel.common.LogConfig d(String str) {
        return new LogConfig.d(str).c("sensor").b(2).c(false).c(512000.0d).e(c()).e();
    }
}
