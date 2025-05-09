package defpackage;

import com.huawei.hmf.md.bootstrap.AboutModuleBootstrap;
import com.huawei.hmf.md.bootstrap.BaseSportModelModuleBootstrap;
import com.huawei.hmf.md.bootstrap.BasicHealthModelModuleBootstrap;
import com.huawei.hmf.md.bootstrap.BreathTrainServiceModuleBootstrap;
import com.huawei.hmf.md.bootstrap.ConfiguredPageModuleBootstrap;
import com.huawei.hmf.md.bootstrap.CoursePlanServiceModuleBootstrap;
import com.huawei.hmf.md.bootstrap.DailyActivityModuleBootstrap;
import com.huawei.hmf.md.bootstrap.DeviceModuleBootstrap;
import com.huawei.hmf.md.bootstrap.DevicePairJsModuleBootstrap;
import com.huawei.hmf.md.bootstrap.DevicePairPermissionsJsModuleBootstrap;
import com.huawei.hmf.md.bootstrap.DistributedServiceModuleBootstrap;
import com.huawei.hmf.md.bootstrap.DownloadDeviceResourceModuleBootstrap;
import com.huawei.hmf.md.bootstrap.EcologyDeviceModuleBootstrap;
import com.huawei.hmf.md.bootstrap.FeatureDataOpenModuleBootstrap;
import com.huawei.hmf.md.bootstrap.FeatureMarketingModuleBootstrap;
import com.huawei.hmf.md.bootstrap.HWHealthDataMgrModuleBootstrap;
import com.huawei.hmf.md.bootstrap.HWSmartInteractMgrModuleBootstrap;
import com.huawei.hmf.md.bootstrap.HWUserLabelMgrModuleBootstrap;
import com.huawei.hmf.md.bootstrap.HWUserProfileMgrModuleBootstrap;
import com.huawei.hmf.md.bootstrap.HWVersionMgrModuleBootstrap;
import com.huawei.hmf.md.bootstrap.HWhealthLinkageModuleBootstrap;
import com.huawei.hmf.md.bootstrap.HealthAlgorithmModuleModuleBootstrap;
import com.huawei.hmf.md.bootstrap.HealthHeadLinesModuleBootstrap;
import com.huawei.hmf.md.bootstrap.HealthKitModuleBootstrap;
import com.huawei.hmf.md.bootstrap.HomeHealthModuleBootstrap;
import com.huawei.hmf.md.bootstrap.HuaweiHealthModuleBootstrap;
import com.huawei.hmf.md.bootstrap.HwAdapterWearMgrModuleBootstrap;
import com.huawei.hmf.md.bootstrap.HwAdpaterHealthMgrModuleBootstrap;
import com.huawei.hmf.md.bootstrap.LinkagePlatformModuleBootstrap;
import com.huawei.hmf.md.bootstrap.MainModuleBootstrap;
import com.huawei.hmf.md.bootstrap.MenstrualPredictModuleBootstrap;
import com.huawei.hmf.md.bootstrap.Module_Track_Post_Process_ServiceModuleBootstrap;
import com.huawei.hmf.md.bootstrap.OperationBundleModuleBootstrap;
import com.huawei.hmf.md.bootstrap.PluginAchievementModuleBootstrap;
import com.huawei.hmf.md.bootstrap.PluginDeviceModuleBootstrap;
import com.huawei.hmf.md.bootstrap.PluginDeviceOpenModuleModuleBootstrap;
import com.huawei.hmf.md.bootstrap.PluginFitnessAdviceModuleBootstrap;
import com.huawei.hmf.md.bootstrap.PluginHealthZoneModuleBootstrap;
import com.huawei.hmf.md.bootstrap.PluginLinkIndoorEquipModuleBootstrap;
import com.huawei.hmf.md.bootstrap.PluginMessageCenterModuleBootstrap;
import com.huawei.hmf.md.bootstrap.PluginOperationModuleBootstrap;
import com.huawei.hmf.md.bootstrap.PluginSocialShareModuleBootstrap;
import com.huawei.hmf.md.bootstrap.PluginSportTrackModuleBootstrap;
import com.huawei.hmf.md.bootstrap.SleepLabelAlgorithmModuleBootstrap;
import com.huawei.hmf.md.bootstrap.SportServiceModuleBootstrap;
import com.huawei.hmf.md.bootstrap.StressGameModuleBootstrap;
import com.huawei.hmf.md.bootstrap.SyncApiManagerModuleBootstrap;
import com.huawei.hmf.md.bootstrap.TrackFeatureExtractionAlgorithmServiceModuleBootstrap;
import com.huawei.hmf.md.bootstrap.TradeServiceModuleBootstrap;
import com.huawei.hmf.md.bootstrap.WatchFaceApiManagerModuleBootstrap;
import com.huawei.hmf.md.bootstrap.module_routeModuleBootstrap;
import com.huawei.hmf.md.bootstrap.vipModuleBootstrap;
import com.huawei.hmf.repository.ModuleRegistryBase;
import com.huawei.hmf.repository.Repository;
import java.util.Map;

/* loaded from: classes3.dex */
public class byg extends ModuleRegistryBase {
    @Override // com.huawei.hmf.repository.ModuleRegistryBase
    public void registerRemoteModule(Map map) {
    }

    @Override // com.huawei.hmf.repository.ModuleRegistryBase
    public void register(Repository repository) {
        PluginFitnessAdviceModuleBootstrap.register(repository);
        HealthHeadLinesModuleBootstrap.register(repository);
        HomeHealthModuleBootstrap.register(repository);
        DevicePairPermissionsJsModuleBootstrap.register(repository);
        DeviceModuleBootstrap.register(repository);
        DevicePairJsModuleBootstrap.register(repository);
        MenstrualPredictModuleBootstrap.register(repository);
        PluginHealthZoneModuleBootstrap.register(repository);
        FeatureDataOpenModuleBootstrap.register(repository);
        AboutModuleBootstrap.register(repository);
        ConfiguredPageModuleBootstrap.register(repository);
        MainModuleBootstrap.register(repository);
        HWSmartInteractMgrModuleBootstrap.register(repository);
        SportServiceModuleBootstrap.register(repository);
        StressGameModuleBootstrap.register(repository);
        HWhealthLinkageModuleBootstrap.register(repository);
        LinkagePlatformModuleBootstrap.register(repository);
        HwAdpaterHealthMgrModuleBootstrap.register(repository);
        PluginLinkIndoorEquipModuleBootstrap.register(repository);
        PluginDeviceModuleBootstrap.register(repository);
        PluginDeviceOpenModuleModuleBootstrap.register(repository);
        EcologyDeviceModuleBootstrap.register(repository);
        CoursePlanServiceModuleBootstrap.register(repository);
        OperationBundleModuleBootstrap.register(repository);
        PluginOperationModuleBootstrap.register(repository);
        TradeServiceModuleBootstrap.register(repository);
        vipModuleBootstrap.register(repository);
        DailyActivityModuleBootstrap.register(repository);
        PluginAchievementModuleBootstrap.register(repository);
        HwAdapterWearMgrModuleBootstrap.register(repository);
        HWHealthDataMgrModuleBootstrap.register(repository);
        PluginSportTrackModuleBootstrap.register(repository);
        HWUserProfileMgrModuleBootstrap.register(repository);
        module_routeModuleBootstrap.register(repository);
        SleepLabelAlgorithmModuleBootstrap.register(repository);
        HealthAlgorithmModuleModuleBootstrap.register(repository);
        DownloadDeviceResourceModuleBootstrap.register(repository);
        SyncApiManagerModuleBootstrap.register(repository);
        WatchFaceApiManagerModuleBootstrap.register(repository);
        BaseSportModelModuleBootstrap.register(repository);
        PluginMessageCenterModuleBootstrap.register(repository);
        BasicHealthModelModuleBootstrap.register(repository);
        PluginSocialShareModuleBootstrap.register(repository);
        HWUserLabelMgrModuleBootstrap.register(repository);
        FeatureMarketingModuleBootstrap.register(repository);
        DistributedServiceModuleBootstrap.register(repository);
        BreathTrainServiceModuleBootstrap.register(repository);
        TrackFeatureExtractionAlgorithmServiceModuleBootstrap.register(repository);
        HealthKitModuleBootstrap.register(repository);
        HWVersionMgrModuleBootstrap.register(repository);
        Module_Track_Post_Process_ServiceModuleBootstrap.register(repository);
        HuaweiHealthModuleBootstrap.register(repository);
    }
}
