package defpackage;

import com.amap.api.location.AMapLocation;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.data.model.HiTrackMetaData;
import com.huawei.hwfoundationmodel.trackmodel.MotionPathSimplify;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.utils.Constants;

/* loaded from: classes5.dex */
public class kwz {
    public static String e(HiHealthData hiHealthData, MotionPathSimplify motionPathSimplify) {
        String sequenceFileUrl = hiHealthData.getSequenceFileUrl();
        if (sequenceFileUrl == null) {
            return null;
        }
        HiTrackMetaData hiTrackMetaData = (HiTrackMetaData) nrv.b(hiHealthData.getMetaData(), HiTrackMetaData.class);
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
            LogUtil.c("Track_SportDataConvertUtil", "should not enter this branch,do not set");
        }
        c(motionPathSimplify, hiTrackMetaData);
        return sequenceFileUrl;
    }

    private static void c(MotionPathSimplify motionPathSimplify, HiTrackMetaData hiTrackMetaData) {
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
        e(motionPathSimplify, hiTrackMetaData);
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
        c(hiTrackMetaData, motionPathSimplify);
    }

    private static void e(MotionPathSimplify motionPathSimplify, HiTrackMetaData hiTrackMetaData) {
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
        HiTrackMetaData hiTrackMetaData = (HiTrackMetaData) nrv.b(hiHealthData.getMetaData(), HiTrackMetaData.class);
        MotionPathSimplify a2 = a(hiHealthData, hiTrackMetaData);
        if ("AMAP".equals(hiTrackMetaData.getVendor())) {
            a2.saveMapType(1);
            if (hiTrackMetaData.getIsNewCoordinate()) {
                a2.saveMapCoordinate(hiTrackMetaData.getCoordinate());
            } else {
                a2.saveMapCoordinate(AMapLocation.COORD_TYPE_GCJ02);
            }
        } else if ("GOOGLE".equals(hiTrackMetaData.getVendor())) {
            a2.saveMapType(2);
            if (hiTrackMetaData.getIsNewCoordinate()) {
                a2.saveMapCoordinate(hiTrackMetaData.getCoordinate());
            } else {
                a2.saveMapCoordinate(AMapLocation.COORD_TYPE_WGS84);
            }
        } else if (Constants.HMS.equals(hiTrackMetaData.getVendor())) {
            a2.saveMapType(3);
            a2.saveMapCoordinate(hiTrackMetaData.getCoordinate());
        } else {
            LogUtil.c("Track_SportDataConvertUtil", "should not enter this branch,do not set");
        }
        a2.saveIsNewCoordinate(hiTrackMetaData.getIsNewCoordinate());
        a2.saveFreeMotion(hiTrackMetaData.getIsFreeMotion());
        a2.saveSportDataSource(hiTrackMetaData.getSportDataSource());
        a2.saveChiefSportDataType(hiTrackMetaData.getChiefSportDataType());
        a2.saveHasTrackPoint(hiTrackMetaData.getHasTrackPoint());
        a2.saveAbnormalTrack(hiTrackMetaData.getAbnormalTrack());
        a2.saveSwolfBase(hiTrackMetaData.getSwolfBase());
        a2.saveBritishSwolfBase(hiTrackMetaData.getBritishSwolfBase());
        a2.saveMaxAltitude(hiTrackMetaData.getMaxAlti());
        a2.saveMinAltitude(hiTrackMetaData.getMinAlti());
        a2.saveTotalDescent(hiTrackMetaData.getTotalDescent());
        a2.saveSwimSegments(hiTrackMetaData.getSwimSegments());
        a2.saveBritishSwimSegments(hiTrackMetaData.getBritishSwimSegments());
        a2.saveDuplicated(hiTrackMetaData.getDuplicated());
        a2.saveHeartRateZoneType(hiTrackMetaData.getHeartrateZoneType());
        a2.saveRunCourseId(hiTrackMetaData.getRuncourseId());
        e(a2, hiTrackMetaData);
        a2.saveExtendDataMap(hiTrackMetaData.getExtendTrackMap());
        a2.saveChildSportItems(hiTrackMetaData.getChildSportItems());
        a2.saveFatherSportItem(hiTrackMetaData.getFatherSportItem());
        a2.saveMaxSpo2(hiTrackMetaData.getMaxSpo2());
        a2.saveMinSpo2(hiTrackMetaData.getMinSpo2());
        return a2;
    }

    private static MotionPathSimplify a(HiHealthData hiHealthData, HiTrackMetaData hiTrackMetaData) {
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
        c(hiTrackMetaData, motionPathSimplify);
        return motionPathSimplify;
    }

    private static void c(HiTrackMetaData hiTrackMetaData, MotionPathSimplify motionPathSimplify) {
        motionPathSimplify.saveAvgPace(hiTrackMetaData.getAvgPace());
        motionPathSimplify.saveBestPace(hiTrackMetaData.getBestPace());
        motionPathSimplify.savePaceMap(hiTrackMetaData.getPaceMap());
        motionPathSimplify.saveBritishPaceMap(hiTrackMetaData.getBritishPaceMap());
        motionPathSimplify.savePartTimeMap(hiTrackMetaData.getPartTimeMap());
        motionPathSimplify.saveBritishPartTimeMap(hiTrackMetaData.getBritishPartTimeMap());
        motionPathSimplify.saveCreepingWave(hiTrackMetaData.getCreepingWave());
    }
}
