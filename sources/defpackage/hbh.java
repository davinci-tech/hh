package defpackage;

import com.huawei.healthcloud.plugintrack.trackanimation.preprocess.realtimedata.RealTimeDataParser;
import com.huawei.hwfoundationmodel.trackmodel.HeartRateData;
import com.huawei.hwlogsmodel.LogUtil;
import java.util.Iterator;
import java.util.TreeMap;
import java.util.concurrent.CopyOnWriteArrayList;

/* loaded from: classes4.dex */
public class hbh extends RealTimeDataParser {
    @Override // com.huawei.healthcloud.plugintrack.trackanimation.preprocess.realtimedata.RealTimeDataParser
    public boolean init() {
        if (this.mMotionPath == null || this.mMotionPath.requestHeartRateList() == null || this.mMotionPath.requestHeartRateList().size() <= 0) {
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
        CopyOnWriteArrayList copyOnWriteArrayList = new CopyOnWriteArrayList(this.mMotionPath.requestHeartRateList());
        this.mRealTimeData = new TreeMap();
        Iterator it = copyOnWriteArrayList.iterator();
        while (it.hasNext()) {
            HeartRateData heartRateData = (HeartRateData) it.next();
            if (heartRateData.acquireTime() > this.mEndTime || heartRateData.acquireTime() < this.mStartTime) {
                LogUtil.h("Track_HeartRateParser", "current stamp is error");
            } else {
                int acquireTime = (int) ((((heartRateData.acquireTime() - this.mStartTime) * 1.0d) / (this.mEndTime - this.mStartTime)) * (this.mTargetSize - 1));
                this.mRealTimeData.put(Integer.valueOf(acquireTime), Double.valueOf(heartRateData.acquireHeartRate()));
                if (heartRateData.acquireHeartRate() > this.mMaxData.a().doubleValue()) {
                    this.mMaxData.a(Integer.valueOf(acquireTime));
                    this.mMaxData.e(Double.valueOf(heartRateData.acquireHeartRate()));
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
