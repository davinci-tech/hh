package defpackage;

import com.amap.api.location.AMapLocation;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.data.model.HiTrackMetaData;
import com.huawei.hwfoundationmodel.trackmodel.MotionPathSimplify;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.utils.Constants;

/* loaded from: classes5.dex */
public class kpt {
    public static String a(HiHealthData hiHealthData, MotionPathSimplify motionPathSimplify) {
        String sequenceFileUrl = hiHealthData.getSequenceFileUrl();
        if (sequenceFileUrl == null) {
            return null;
        }
        HiTrackMetaData hiTrackMetaData = (HiTrackMetaData) kps.e(hiHealthData.getMetaData(), HiTrackMetaData.class);
        d(hiHealthData, motionPathSimplify, hiTrackMetaData);
        if ("AMAP".equals(hiTrackMetaData.getVendor())) {
            motionPathSimplify.saveMapType(1);
            if (hiTrackMetaData.getIsNewCoordinate()) {
                motionPathSimplify.saveMapCoordinate(hiTrackMetaData.getCoordinate());
            } else {
                motionPathSimplify.saveMapCoordinate(AMapLocation.COORD_TYPE_GCJ02);
            }
        } else if ("GOOGLE".equals(hiTrackMetaData.getVendor())) {
            motionPathSimplify.saveMapType(2);
            if (hiTrackMetaData.getIsNewCoordinate()) {
                motionPathSimplify.saveMapCoordinate(hiTrackMetaData.getCoordinate());
            } else {
                motionPathSimplify.saveMapCoordinate(AMapLocation.COORD_TYPE_WGS84);
            }
        } else if (Constants.HMS.equals(hiTrackMetaData.getVendor())) {
            motionPathSimplify.saveMapType(3);
            motionPathSimplify.saveMapCoordinate(hiTrackMetaData.getCoordinate());
        } else {
            LogUtil.c("Track_PluginUtil", "should not enter this branch,do not set");
        }
        a(motionPathSimplify, hiTrackMetaData);
        return sequenceFileUrl;
    }

    private static void a(MotionPathSimplify motionPathSimplify, HiTrackMetaData hiTrackMetaData) {
        motionPathSimplify.saveIsNewCoordinate(hiTrackMetaData.getIsNewCoordinate());
        motionPathSimplify.saveSwimSegments(hiTrackMetaData.getSwimSegments());
        motionPathSimplify.saveBritishSwimSegments(hiTrackMetaData.getBritishSwimSegments());
        motionPathSimplify.saveSwolfBase(hiTrackMetaData.getSwolfBase());
        motionPathSimplify.saveBritishSwolfBase(hiTrackMetaData.getBritishSwolfBase());
        motionPathSimplify.saveMaxAltitude(hiTrackMetaData.getMaxAlti());
        motionPathSimplify.saveMinAltitude(hiTrackMetaData.getMinAlti());
        motionPathSimplify.saveTotalDescent(hiTrackMetaData.getTotalDescent());
        motionPathSimplify.saveFreeMotion(hiTrackMetaData.getIsFreeMotion());
        motionPathSimplify.saveSportDataSource(hiTrackMetaData.getSportDataSource());
        motionPathSimplify.saveChiefSportDataType(hiTrackMetaData.getChiefSportDataType());
        motionPathSimplify.saveHasTrackPoint(hiTrackMetaData.getHasTrackPoint());
        motionPathSimplify.saveAbnormalTrack(hiTrackMetaData.getAbnormalTrack());
        d(motionPathSimplify, hiTrackMetaData);
        motionPathSimplify.saveExtendDataMap(hiTrackMetaData.getExtendTrackMap());
        motionPathSimplify.saveDuplicated(hiTrackMetaData.getDuplicated());
        motionPathSimplify.saveHeartRateZoneType(hiTrackMetaData.getHeartrateZoneType());
        motionPathSimplify.saveRunCourseId(hiTrackMetaData.getRuncourseId());
        motionPathSimplify.saveChildSportItems(hiTrackMetaData.getChildSportItems());
        motionPathSimplify.saveFatherSportItem(hiTrackMetaData.getFatherSportItem());
        motionPathSimplify.saveMaxSpo2(hiTrackMetaData.getMaxSpo2());
        motionPathSimplify.saveMinSpo2(hiTrackMetaData.getMinSpo2());
    }

    private static void d(HiHealthData hiHealthData, MotionPathSimplify motionPathSimplify, HiTrackMetaData hiTrackMetaData) {
        motionPathSimplify.saveTrackType(hiTrackMetaData.getTrackType());
        motionPathSimplify.saveTotalTime(hiTrackMetaData.getTotalTime());
        motionPathSimplify.saveTotalDistance(hiTrackMetaData.getTotalDistance());
        motionPathSimplify.saveAvgHeartRate(hiTrackMetaData.getAvgHeartRate());
        motionPathSimplify.saveAvgStepRate(hiTrackMetaData.getAvgStepRate());
        motionPathSimplify.saveTotalCalories(hiTrackMetaData.getTotalCalories());
        motionPathSimplify.saveSportId(hiTrackMetaData.getSportId());
        motionPathSimplify.saveSportType(hiTrackMetaData.getSportType());
        motionPathSimplify.saveTotalSteps(hiTrackMetaData.getTotalSteps());
        motionPathSimplify.saveBestStepRate(hiTrackMetaData.getBestStepRate());
        motionPathSimplify.saveMaxHeartRate(hiTrackMetaData.getMaxHeartRate());
        motionPathSimplify.saveEndTime(hiHealthData.getEndTime());
        motionPathSimplify.saveStartTime(hiHealthData.getStartTime());
        motionPathSimplify.saveSportData(hiTrackMetaData.getWearSportData());
        b(hiTrackMetaData, motionPathSimplify);
    }

    private static void d(MotionPathSimplify motionPathSimplify, HiTrackMetaData hiTrackMetaData) {
        motionPathSimplify.saveAverageHangTime(hiTrackMetaData.requestAverageHangTime());
        motionPathSimplify.saveGroundHangTimeRate(hiTrackMetaData.requestGroundHangTimeRate());
        motionPathSimplify.saveAvgGroundContactTime(hiTrackMetaData.getAvgGroundContactTime());
        motionPathSimplify.saveAvgGroundImpactAcceleration(hiTrackMetaData.getAvgGroundImpactAcceleration());
        motionPathSimplify.saveAvgEversionExcursion(hiTrackMetaData.getAvgEversionExcursion());
        motionPathSimplify.saveAvgSwingAngle(hiTrackMetaData.getAvgSwingAngle());
        motionPathSimplify.saveAvgForeFootStrikePattern(hiTrackMetaData.getAvgForeFootStrikePattern());
        motionPathSimplify.saveAvgWholeFootStrikePattern(hiTrackMetaData.getAvgWholeFootStrikePattern());
        motionPathSimplify.saveAvgHindFootStrikePattern(hiTrackMetaData.getAvgHindFootStrikePattern());
    }

    public static MotionPathSimplify e(HiHealthData hiHealthData) {
        HiTrackMetaData hiTrackMetaData = (HiTrackMetaData) kps.e(hiHealthData.getMetaData(), HiTrackMetaData.class);
        MotionPathSimplify d = d(hiHealthData, hiTrackMetaData);
        if ("AMAP".equals(hiTrackMetaData.getVendor())) {
            d.saveMapType(1);
            if (hiTrackMetaData.getIsNewCoordinate()) {
                d.saveMapCoordinate(hiTrackMetaData.getCoordinate());
            } else {
                d.saveMapCoordinate(AMapLocation.COORD_TYPE_GCJ02);
            }
        } else if ("GOOGLE".equals(hiTrackMetaData.getVendor())) {
            d.saveMapType(2);
            if (hiTrackMetaData.getIsNewCoordinate()) {
                d.saveMapCoordinate(hiTrackMetaData.getCoordinate());
            } else {
                d.saveMapCoordinate(AMapLocation.COORD_TYPE_WGS84);
            }
        } else if (Constants.HMS.equals(hiTrackMetaData.getVendor())) {
            d.saveMapType(3);
            d.saveMapCoordinate(hiTrackMetaData.getCoordinate());
        } else {
            LogUtil.c("Track_PluginUtil", "should not enter this branch,do not set");
        }
        d.saveIsNewCoordinate(hiTrackMetaData.getIsNewCoordinate());
        d.saveFreeMotion(hiTrackMetaData.getIsFreeMotion());
        d.saveSportDataSource(hiTrackMetaData.getSportDataSource());
        d.saveChiefSportDataType(hiTrackMetaData.getChiefSportDataType());
        d.saveHasTrackPoint(hiTrackMetaData.getHasTrackPoint());
        d.saveAbnormalTrack(hiTrackMetaData.getAbnormalTrack());
        d.saveSwolfBase(hiTrackMetaData.getSwolfBase());
        d.saveBritishSwolfBase(hiTrackMetaData.getBritishSwolfBase());
        d.saveMaxAltitude(hiTrackMetaData.getMaxAlti());
        d.saveMinAltitude(hiTrackMetaData.getMinAlti());
        d.saveTotalDescent(hiTrackMetaData.getTotalDescent());
        d.saveSwimSegments(hiTrackMetaData.getSwimSegments());
        d.saveBritishSwimSegments(hiTrackMetaData.getBritishSwimSegments());
        d.saveDuplicated(hiTrackMetaData.getDuplicated());
        d.saveHeartRateZoneType(hiTrackMetaData.getHeartrateZoneType());
        d.saveRunCourseId(hiTrackMetaData.getRuncourseId());
        d(d, hiTrackMetaData);
        d.saveExtendDataMap(hiTrackMetaData.getExtendTrackMap());
        d.saveChildSportItems(hiTrackMetaData.getChildSportItems());
        d.saveFatherSportItem(hiTrackMetaData.getFatherSportItem());
        d.saveMaxSpo2(hiTrackMetaData.getMaxSpo2());
        d.saveMinSpo2(hiTrackMetaData.getMinSpo2());
        return d;
    }

    private static MotionPathSimplify d(HiHealthData hiHealthData, HiTrackMetaData hiTrackMetaData) {
        MotionPathSimplify motionPathSimplify = new MotionPathSimplify();
        motionPathSimplify.saveTrackType(hiTrackMetaData.getTrackType());
        motionPathSimplify.saveTotalTime(hiTrackMetaData.getTotalTime());
        motionPathSimplify.saveTotalDistance(hiTrackMetaData.getTotalDistance());
        motionPathSimplify.saveAvgHeartRate(hiTrackMetaData.getAvgHeartRate());
        motionPathSimplify.saveAvgStepRate(hiTrackMetaData.getAvgStepRate());
        motionPathSimplify.saveTotalCalories(hiTrackMetaData.getTotalCalories());
        motionPathSimplify.saveSportId(hiTrackMetaData.getSportId());
        motionPathSimplify.saveSportType(hiTrackMetaData.getSportType());
        motionPathSimplify.saveTotalSteps(hiTrackMetaData.getTotalSteps());
        motionPathSimplify.saveBestStepRate(hiTrackMetaData.getBestStepRate());
        motionPathSimplify.saveMaxHeartRate(hiTrackMetaData.getMaxHeartRate());
        motionPathSimplify.saveEndTime(hiHealthData.getEndTime());
        motionPathSimplify.saveStartTime(hiHealthData.getStartTime());
        motionPathSimplify.saveSportData(hiTrackMetaData.getWearSportData());
        b(hiTrackMetaData, motionPathSimplify);
        return motionPathSimplify;
    }

    private static void b(HiTrackMetaData hiTrackMetaData, MotionPathSimplify motionPathSimplify) {
        motionPathSimplify.saveAvgPace(hiTrackMetaData.getAvgPace());
        motionPathSimplify.saveBestPace(hiTrackMetaData.getBestPace());
        motionPathSimplify.savePaceMap(hiTrackMetaData.getPaceMap());
        motionPathSimplify.saveBritishPaceMap(hiTrackMetaData.getBritishPaceMap());
        motionPathSimplify.savePartTimeMap(hiTrackMetaData.getPartTimeMap());
        motionPathSimplify.saveBritishPartTimeMap(hiTrackMetaData.getBritishPartTimeMap());
        motionPathSimplify.saveCreepingWave(hiTrackMetaData.getCreepingWave());
    }
}
