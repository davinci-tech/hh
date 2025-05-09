package com.huawei.healthcloud.plugintrack.model;

import com.huawei.hihealth.data.model.RelativeSportData;
import com.huawei.hwfoundationmodel.trackmodel.MotionPathSimplify;

/* loaded from: classes4.dex */
public interface ITrackSummaryDataFactory {
    AbstractTrackSummaryData createSingleTrackSummaryData(RelativeSportData relativeSportData);

    AbstractTrackSummaryData createSingleTrackSummaryData(MotionPathSimplify motionPathSimplify);
}
