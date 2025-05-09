package defpackage;

import com.huawei.hwlogsmodel.LogUtil;
import java.util.HashMap;

/* loaded from: classes4.dex */
public class fhf {
    private final HashMap<String, String> e = new HashMap<String, String>() { // from class: fhf.2
        private static final long serialVersionUID = -262925167266023307L;

        {
            put("local:sportservice//EcologyManager", "com.huawei.health.sportservice.manager.EcologyManager");
            put("local:sportservice//StepManager", "com.huawei.health.sportservice.manager.StepManager");
            put("local:sportservice//SkipAchieveManager", "com.huawei.health.sportservice.manager.skip.SkipAchieveManager");
            put("local:sportservice//ModelManager", "com.huawei.health.sportservice.manager.ModelManager");
            put("local:sportservice//SportStatusManager", "com.huawei.health.sportservice.manager.SportStatusManager");
            put("local:sportservice//AliveManager", "com.huawei.health.sportservice.manager.AliveManager");
            put("local:sportservice//SportDataCenter", "com.huawei.health.sportservice.dataproducer.SportDataCenter");
            put("local:sportservice//BaseIntensityManager", "com.huawei.health.sportservice.manager.BaseIntensityManager");
            put("local:sportservice//SkipVoiceManager", "com.huawei.health.sportservice.manager.skip.SkipVoiceManager");
            put("local:sportservice//SkipTargetManager", "com.huawei.health.sportservice.manager.skip.SkipTargetManager");
            put("local:sportservice//BaseTargetManager", "com.huawei.health.sportservice.manager.BaseTargetManager");
            put("local:sportservice//SportDeviceManager", "com.huawei.health.sportservice.manager.SportDeviceManager");
            put("local:sportservice//ReverseLinkageManager", "com.huawei.health.sportservice.manager.ReverseLinkageManager");
            put("local:sportservice//VoiceManager", "com.huawei.health.sportservice.manager.VoiceManager");
            put("local:sportservice//SportBleManager", "com.huawei.health.sportservice.manager.SportBleManager");
            put("local:sportservice//PointDataManager", "com.huawei.health.sportservice.manager.PointDataManager");
            put("local:sportservice//NotificationManager", "com.huawei.health.sportservice.manager.NotificationManager");
            put("local:sportservice//MusicManager", "com.huawei.health.sportservice.manager.MusicManager");
            put("local:sportservice//IntensityManager", "com.huawei.health.sportservice.manager.IntensityManager");
            put("local:sportservice//StatusDetectManager", "com.huawei.health.sportservice.manager.StatusDetectManager");
            put("local:sportservice//DistributeSportDataManager", "com.huawei.health.sportservice.manager.DistributeSportDataManager");
            put("local:sportservice//StandFlexionAchieveManager", "com.huawei.health.sportservice.manager.standflexion.StandFlexionAchieveManager");
            put("local:sportservice//StandFlexionVoiceManager", "com.huawei.health.sportservice.manager.standflexion.StandFlexionVoiceManager");
            put("local:sportservice//AerobicSportTargetManager", "com.huawei.health.sportservice.manager.AerobicSportTargetManager");
            put("local:sportservice//SupineLegRaiseAchieveManager", "com.huawei.health.sportservice.manager.supinelegraise.SupineLegRaiseAchieveManager");
            put("local:sportservice//SupineLegRaiseVoiceManager", "com.huawei.health.sportservice.manager.supinelegraise.SupineLegRaiseVoiceManager");
            put("local:sportservice//SupineLegRaiseTargetManager", "com.huawei.health.sportservice.manager.supinelegraise.SupineLegRaiseTargetManager");
            put("local:sportservice//BaseHeartRateControlManager", "com.huawei.health.sportservice.manager.heartratecontrol.BaseHeartRateControlManager");
            put("local:sportservice//TreadmillHeartRateControlManager", "com.huawei.health.sportservice.manager.heartratecontrol.TreadmillHeartRateControlManager");
            put("local:sportservice//CourseManager", "com.huawei.health.sportservice.manager.CourseManager");
            put("local:sportservice//CourseTargetManager", "com.huawei.health.sportservice.manager.CourseTargetManager");
            put("local:sportservice//LongJumpVoiceManager", "com.huawei.health.sportservice.manager.LongJumpVoiceManager");
            put("local:sportservice//BeatSoundManager", "com.huawei.health.sportservice.manager.BeatSoundManager");
            put("local:sportservice//BikeHeartRateControlManager", "com.huawei.health.sportservice.manager.heartratecontrol.BikeHeartRateControlManager");
            put("local:sportservice//CrossJumpVoiceManager", "com.huawei.health.sportservice.manager.CrossJumpVoiceManager");
            put("local:sportservice//ActionTrainVoiceManager", "com.huawei.health.sportservice.manager.aitrain.ActionTrainVoiceManager");
            put("local:sportservice//AiActionTrainTargetManager", "com.huawei.health.sportservice.manager.aitrain.AiActionTrainTargetManager");
            put("local:sportservice//AiTrainModelManager", "com.huawei.health.sportservice.manager.aitrain.AiTrainModelManager");
            put("local:sportservice//BaseSportExamVoiceManager", "com.huawei.health.sportservice.manager.BaseSportExamVoiceManager");
        }
    };
    private final HashMap<String, String> d = new HashMap<String, String>() { // from class: fhf.3
        private static final long serialVersionUID = -262925167266023307L;

        {
            put("local:sportservice//SkipGroupProducer", "com.huawei.health.sportservice.dataproducer.SkipGroupProducer");
            put("local:sportservice//SkipProducer", "com.huawei.health.sportservice.dataproducer.SkipProducer");
            put("local:sportservice//CalorieProducer", "com.huawei.health.sportservice.dataproducer.CalorieProducer");
            put("local:sportservice//ActiveCalorieProducer", "com.huawei.health.sportservice.dataproducer.ActiveCalorieProducer");
            put("local:sportservice//DistanceProducer", "com.huawei.health.sportservice.dataproducer.DistanceProducer");
            put("local:sportservice//HeartRateProducer", "com.huawei.health.sportservice.dataproducer.HeartRateProducer");
            put("local:sportservice//PaceMapProducer", "com.huawei.health.sportservice.dataproducer.PaceMapProducer");
            put("local:sportservice//SpeedProducer", "com.huawei.health.sportservice.dataproducer.SpeedProducer");
            put("local:sportservice//RunningPostureProducer", "com.huawei.health.sportservice.dataproducer.RunningPostureProducer");
            put("local:sportservice//StepProducer", "com.huawei.health.sportservice.dataproducer.StepProducer");
            put("local:sportservice//StepRateProducer", "com.huawei.health.sportservice.dataproducer.StepRateProducer");
            put("local:sportservice//TimeProducer", "com.huawei.health.sportservice.dataproducer.TimeProducer");
            put("local:sportservice//TempoProducer", "com.huawei.health.sportservice.dataproducer.TempoProducer");
            put("local:sportservice//WeightProducer", "com.huawei.health.sportservice.dataproducer.WeightProducer");
            put("local:sportservice//ActionGroupProducer", "com.huawei.health.sportservice.dataproducer.ActionGroupProducer");
            put("local:sportservice//GroupCountProducer", "com.huawei.health.sportservice.dataproducer.GroupCountProducer");
            put("local:sportservice//PowerProducer", "com.huawei.health.sportservice.dataproducer.PowerProducer");
            put("local:sportservice//PaddleProducer", "com.huawei.health.sportservice.dataproducer.PaddleProducer");
            put("local:sportservice//CadenceProducer", "com.huawei.health.sportservice.dataproducer.CadenceProducer");
            put("local:sportservice//InclinationProducer", "com.huawei.health.sportservice.dataproducer.InclinationProducer");
            put("local:sportservice//ResistanceProducer", "com.huawei.health.sportservice.dataproducer.ResistanceProducer");
            put("local:sportservice//ResistanceLevelProducer", "com.huawei.health.sportservice.dataproducer.ResistanceLevelProducer");
            put("local:sportservice//TotalCreepProducer", "com.huawei.health.sportservice.dataproducer.TotalCreepProducer");
            put("local:sportservice//IndoorDeviceInfoProducer", "com.huawei.health.sportservice.dataproducer.IndoorDeviceInfoProducer");
            put("local:sportservice//UpdateViewSignalProducer", "com.huawei.health.sportservice.dataproducer.UpdateViewSignalProducer");
            put("local:sportservice//CrossJumpProducer", "com.huawei.health.sportservice.dataproducer.CrossJumpProducer");
            put("local:sportservice//StatusCodeProducer", "com.huawei.health.sportservice.dataproducer.StatusCodeProducer");
            put("local:sportservice//ResultCodeProducer", "com.huawei.health.sportservice.dataproducer.ResultCodeProducer");
            put("local:sportservice//ValidTimesProducer", "com.huawei.health.sportservice.dataproducer.ValidTimesProducer");
            put("local:sportservice//ResultCodeMapProducer", "com.huawei.health.sportservice.dataproducer.ResultCodeMapProducer");
            put("local:sportservice//CourseDataProducer", "com.huawei.health.sportservice.dataproducer.CourseDataProducer");
        }
    };

    /* renamed from: a, reason: collision with root package name */
    private final HashMap<String, String> f12511a = new HashMap<String, String>() { // from class: fhf.5
        private static final long serialVersionUID = -262925167266023307L;

        {
            put("local:sportservice//SkipGroupSource", "com.huawei.health.sportservice.datasource.SkipGroupSource");
            put("local:sportservice//CalorieAlgorithmSource", "com.huawei.health.sportservice.datasource.CalorieAlgorithmSource");
            put("local:sportservice//ActiveCalorieAlgorithmSource", "com.huawei.health.sportservice.datasource.ActiveCalorieAlgorithmSource");
            put("local:sportservice//CalorieIndoorSource", "com.huawei.health.sportservice.datasource.CalorieIndoorSource");
            put("local:sportservice//CalorieWatchSource", "com.huawei.health.sportservice.datasource.CalorieWatchSource");
            put("local:sportservice//DistanceSource", "com.huawei.health.sportservice.datasource.DistanceSource");
            put("local:sportservice//HeartRateSource", "com.huawei.health.sportservice.datasource.HeartRateSource");
            put("local:sportservice//MultiSources", "com.huawei.health.sportservice.datasource.MultiSources");
            put("local:sportservice//PhoneStepSource", "com.huawei.health.sportservice.datasource.PhoneStepSource");
            put("local:sportservice//ResistanceLevelSource", "com.huawei.health.sportservice.datasource.ResistanceLevelSource");
            put("local:sportservice//RunningPostureSource", "com.huawei.health.sportservice.datasource.RunningPostureSource");
            put("local:sportservice//SpeedSource", "com.huawei.health.sportservice.datasource.SpeedSource");
            put("local:sportservice//StepIndoorSource", "com.huawei.health.sportservice.datasource.StepIndoorSource");
            put("local:sportservice//TimeIndoorSource", "com.huawei.health.sportservice.datasource.TimeIndoorSource");
            put("local:sportservice//WearStepSource", "com.huawei.health.sportservice.datasource.WearStepSource");
            put("local:sportservice//SensorStepSource", "com.huawei.health.sportservice.datasource.SensorStepSource");
            put("local:sportservice//TimeSelfSource", "com.huawei.health.sportservice.datasource.TimeSelfSource");
            put("local:sportservice//SkipDataAiSource", "com.huawei.health.sportservice.datasource.SkipDataAiSource");
            put("local:sportservice//SkipIndoorSource", "com.huawei.health.sportservice.datasource.SkipIndoorSource");
            put("local:sportservice//ResistanceSource", "com.huawei.health.sportservice.datasource.ResistanceSource");
            put("local:sportservice//TempoSource", "com.huawei.health.sportservice.datasource.TempoSource");
            put("local:sportservice//WeightSource", "com.huawei.health.sportservice.datasource.WeightSource");
            put("local:sportservice//ActionGroupSource", "com.huawei.health.sportservice.datasource.ActionGroupSource");
            put("local:sportservice//GroupCountSource", "com.huawei.health.sportservice.datasource.GroupCountSource");
            put("local:sportservice//PowerSource", "com.huawei.health.sportservice.datasource.PowerSource");
            put("local:sportservice//PaddleTimesSource", "com.huawei.health.sportservice.datasource.PaddleTimesSource");
            put("local:sportservice//CadenceSource", "com.huawei.health.sportservice.datasource.CadenceSource");
            put("local:sportservice//OpCodeSource", "com.huawei.health.sportservice.datasource.OpCodeSource");
            put("local:sportservice//InclinationSource", "com.huawei.health.sportservice.datasource.InclinationSource");
            put("local:sportservice//TotalCreepSource", "com.huawei.health.sportservice.datasource.TotalCreepSource");
            put("local:sportservice//IndoorDeviceInfoSource", "com.huawei.health.sportservice.datasource.IndoorDeviceInfoSource");
            put("local:sportservice//StandFlexionDataAiSource", "com.huawei.health.sportservice.datasource.StandFlexionDataAiSource");
            put("local:sportservice//SupineLegRaiseDataAiSource", "com.huawei.health.sportservice.datasource.SupineLegRaiseDataAiSource");
            put("local:sportservice//LongJumpDataAiSource", "com.huawei.health.sportservice.datasource.LongJumpDataAiSource");
            put("local:sportservice//CrossJumpDataAiSource", "com.huawei.health.sportservice.datasource.CrossJumpDataAiSource");
            put("local:sportservice//AiActionTrainDataSource", "com.huawei.health.sportservice.datasource.AiActionTrainDataSource");
            put("local:sportservice//AiTrainCalorieSource", "com.huawei.health.sportservice.datasource.AiTrainCalorieSource");
            put("local:sportservice//AiTrainActiveCalorieSource", "com.huawei.health.sportservice.datasource.AiTrainActiveCalorieSource");
            put("local:sportservice//MirrorLinkDataSource", "com.huawei.health.sportservice.datasource.MirrorLinkDataSource");
        }
    };

    public String e(String str) {
        String str2 = this.e.get(str);
        if (str2 != null) {
            return str2;
        }
        LogUtil.b("SportService_SportInitRegistry", "getManagerComponentName not found, please check your url! url = ", str);
        return "";
    }

    public String a(String str) {
        String str2 = this.d.get(str);
        if (str2 != null) {
            return str2;
        }
        LogUtil.b("SportService_SportInitRegistry", "getProducerComponentName not found, please check your url! url = ", str);
        return "";
    }

    public String c(String str) {
        String str2 = this.f12511a.get(str);
        if (str2 != null) {
            return str2;
        }
        LogUtil.b("SportService_SportInitRegistry", "getSourceComponentName not found, please check your url! url = ", str);
        return "";
    }
}
