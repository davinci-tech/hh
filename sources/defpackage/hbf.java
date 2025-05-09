package defpackage;

import com.huawei.healthcloud.plugintrack.trackanimation.preprocess.realtimedata.RealTimeDataParser;
import com.huawei.hwlogsmodel.LogUtil;
import java.util.List;
import java.util.TreeMap;

/* loaded from: classes4.dex */
public class hbf extends RealTimeDataParser {
    private long c = 0;

    @Override // com.huawei.healthcloud.plugintrack.trackanimation.preprocess.realtimedata.RealTimeDataParser
    public boolean init() {
        if (this.mMotionPath == null || this.mMotionPath.requestRealTimeSpeedList() == null || this.mMotionPath.requestRealTimeSpeedList().size() <= 0) {
            LogUtil.h("Track_SpeedParser", "input data is null");
            return false;
        }
        if (this.mStartTime >= this.mEndTime) {
            LogUtil.h("Track_SpeedParser", "timeStamp is error");
            return false;
        }
        if (this.mMotionPath.requestLbsDataMap() == null || this.mMotionPath.requestLbsDataMap().size() <= 0) {
            LogUtil.h("Track_SpeedParser", "lbs data is null");
            return false;
        }
        this.mTargetSize = this.mMotionPath.requestLbsDataMap().size();
        List<koi> requestRealTimeSpeedList = this.mMotionPath.requestRealTimeSpeedList();
        this.mRealTimeData = new TreeMap();
        this.c = (this.mTotalTime / 1000) + 1;
        for (koi koiVar : requestRealTimeSpeedList) {
            if (koiVar.acquireTime() > this.c) {
                LogUtil.h("Track_SpeedParser", "current stamp is error");
            } else {
                int acquireTime = (int) (((koiVar.acquireTime() * 1.0d) / this.c) * (this.mTargetSize - 1));
                this.mRealTimeData.put(Integer.valueOf(acquireTime), Double.valueOf(koiVar.e()));
                if (koiVar.e() > this.mMaxData.a().doubleValue()) {
                    this.mMaxData.a(Integer.valueOf(acquireTime));
                    this.mMaxData.e(Double.valueOf(koiVar.e()));
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
