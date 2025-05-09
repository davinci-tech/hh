package defpackage;

import com.huawei.healthcloud.plugintrack.trackanimation.preprocess.realtimedata.RealTimeDataParser;
import com.huawei.hwlogsmodel.LogUtil;
import java.util.ArrayList;
import java.util.TreeMap;

/* loaded from: classes4.dex */
public class hbi extends RealTimeDataParser {
    @Override // com.huawei.healthcloud.plugintrack.trackanimation.preprocess.realtimedata.RealTimeDataParser
    public boolean init() {
        if (this.mMotionPath == null || this.mMotionPath.requestAltitudeList() == null || this.mMotionPath.requestAltitudeList().size() <= 0) {
            LogUtil.h("Track_HeartRateParser", "input data is null");
            return false;
        }
        if (this.mStartTime >= this.mEndTime) {
            LogUtil.h("Track_HeartRateParser", "timeStamp is error");
            return false;
        }
        if (this.mMotionPath.requestLbsDataMap() == null || this.mMotionPath.requestLbsDataMap().size() <= 0) {
            LogUtil.h("Track_HeartRateParser", "lbs data is null");
            return false;
        }
        this.mTargetSize = this.mMotionPath.requestLbsDataMap().size();
        ArrayList<knz> requestAltitudeList = this.mMotionPath.requestAltitudeList();
        this.mRealTimeData = new TreeMap();
        for (knz knzVar : requestAltitudeList) {
            if (knzVar.acquireTime() > this.mEndTime || knzVar.acquireTime() < this.mStartTime) {
                LogUtil.h("Track_HeartRateParser", "current stamp is error");
            } else {
                int acquireTime = (int) ((((knzVar.acquireTime() - this.mStartTime) * 1.0d) / (this.mEndTime - this.mStartTime)) * (this.mTargetSize - 1));
                this.mRealTimeData.put(Integer.valueOf(acquireTime), Double.valueOf(knzVar.c()));
                if (knzVar.c() > this.mMaxData.a().doubleValue()) {
                    this.mMaxData.a(Integer.valueOf(acquireTime));
                    this.mMaxData.e(Double.valueOf(knzVar.c()));
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
