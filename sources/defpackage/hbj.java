package defpackage;

import com.huawei.healthcloud.plugintrack.trackanimation.preprocess.realtimedata.RealTimeDataParser;
import com.huawei.hwfoundationmodel.trackmodel.StepRateData;
import com.huawei.hwlogsmodel.LogUtil;
import java.util.ArrayList;
import java.util.TreeMap;

/* loaded from: classes4.dex */
public class hbj extends RealTimeDataParser {
    @Override // com.huawei.healthcloud.plugintrack.trackanimation.preprocess.realtimedata.RealTimeDataParser
    public boolean init() {
        if (this.mMotionPath == null || this.mMotionPath.requestStepRateList() == null || this.mMotionPath.requestStepRateList().size() <= 0) {
            LogUtil.h("Track_StepRateParser", "input data is null");
            return false;
        }
        if (this.mStartTime >= this.mEndTime) {
            LogUtil.h("Track_StepRateParser", "timeStamp is error");
            return false;
        }
        if (this.mMotionPath.requestLbsDataMap() == null || this.mMotionPath.requestLbsDataMap().size() <= 0) {
            LogUtil.h("Track_StepRateParser", "lbs data is null");
            return false;
        }
        this.mTargetSize = this.mMotionPath.requestLbsDataMap().size();
        ArrayList<StepRateData> requestStepRateList = this.mMotionPath.requestStepRateList();
        this.mRealTimeData = new TreeMap();
        for (StepRateData stepRateData : requestStepRateList) {
            if (stepRateData.acquireTime() > this.mEndTime || stepRateData.acquireTime() < this.mStartTime) {
                LogUtil.h("Track_StepRateParser", "current stamp is error");
            } else {
                int acquireTime = (int) ((((stepRateData.acquireTime() - this.mStartTime) * 1.0d) / (this.mEndTime - this.mStartTime)) * (this.mTargetSize - 1));
                this.mRealTimeData.put(Integer.valueOf(acquireTime), Double.valueOf(stepRateData.acquireStepRate()));
                if (stepRateData.acquireStepRate() > this.mMaxData.a().doubleValue()) {
                    this.mMaxData.a(Integer.valueOf(acquireTime));
                    this.mMaxData.e(Double.valueOf(stepRateData.acquireStepRate()));
                }
            }
        }
        if (this.mRealTimeData.size() <= 0) {
            return false;
        }
        this.mTargetIndex = 0;
        return true;
    }
}
