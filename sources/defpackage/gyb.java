package defpackage;

import com.huawei.healthcloud.plugintrack.model.AbstractTrackSummaryData;
import com.huawei.healthcloud.plugintrack.model.ITrackSummaryDataFactory;
import com.huawei.hihealth.data.model.RelativeSportData;
import com.huawei.hihealthservice.old.model.OldToNewMotionPath;
import com.huawei.hwfoundationmodel.trackmodel.MotionPathSimplify;
import com.huawei.hwlogsmodel.LogUtil;

/* loaded from: classes4.dex */
public class gyb implements ITrackSummaryDataFactory {
    @Override // com.huawei.healthcloud.plugintrack.model.ITrackSummaryDataFactory
    public AbstractTrackSummaryData createSingleTrackSummaryData(MotionPathSimplify motionPathSimplify) {
        if (motionPathSimplify == null) {
            LogUtil.h("Track_TrackSummaryDataFactory", "The sport data is null");
            return null;
        }
        switch (motionPathSimplify.requestSportType()) {
            case 258:
                return new gxr(motionPathSimplify);
            case 259:
                return new gxl(motionPathSimplify);
            case 260:
            case 261:
            case 263:
            default:
                LogUtil.h("Track_TrackSummaryDataFactory", "the sport type is not surpport");
                return null;
            case 262:
                return new gxj(motionPathSimplify);
            case 264:
                return new gxm(motionPathSimplify);
            case OldToNewMotionPath.SPORT_TYPE_INDOOR_BIKE /* 265 */:
                return new gxk(motionPathSimplify);
            case OldToNewMotionPath.SPORT_TYPE_OPEN_AREA_SWIM /* 266 */:
                return new gxo(motionPathSimplify);
        }
    }

    @Override // com.huawei.healthcloud.plugintrack.model.ITrackSummaryDataFactory
    public AbstractTrackSummaryData createSingleTrackSummaryData(RelativeSportData relativeSportData) {
        if (relativeSportData == null) {
            LogUtil.h("Track_TrackSummaryDataFactory", "The sport data is null");
            return null;
        }
        switch (relativeSportData.getSportType()) {
            case 258:
                return new gxr(relativeSportData);
            case 259:
                return new gxl(relativeSportData);
            case 260:
            case 261:
            case 263:
            default:
                LogUtil.h("Track_TrackSummaryDataFactory", "the sport type is not support");
                return null;
            case 262:
                return new gxj(relativeSportData);
            case 264:
                return new gxm(relativeSportData);
            case OldToNewMotionPath.SPORT_TYPE_INDOOR_BIKE /* 265 */:
                return new gxk(relativeSportData);
            case OldToNewMotionPath.SPORT_TYPE_OPEN_AREA_SWIM /* 266 */:
                return new gxo(relativeSportData);
        }
    }
}
