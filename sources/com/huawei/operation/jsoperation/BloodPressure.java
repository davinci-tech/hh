package com.huawei.operation.jsoperation;

import com.huawei.operation.jsoperation.BodyInfoBase;
import java.util.List;

/* loaded from: classes5.dex */
public class BloodPressure extends BodyInfoBase {
    private List<SamplePointsBean> mSamplePoints;

    public List<SamplePointsBean> getSamplePoints() {
        return this.mSamplePoints;
    }

    public void setSamplePoints(List<SamplePointsBean> list) {
        this.mSamplePoints = list;
    }

    public static class SamplePointsBean extends BodyInfoBase.SamplePointsBeanBase {
        private ValueBean mValue;

        public ValueBean getValue() {
            return this.mValue;
        }

        public void setValue(ValueBean valueBean) {
            this.mValue = valueBean;
        }

        public static class ValueBean {
            private double mBloodPressureDiastolic;
            private double mBloodPressureSystolic;
            private double mDataPointDynamicHeartRate;

            public double getBloodPressureDiastolic() {
                return this.mBloodPressureDiastolic;
            }

            public void setBloodPressureDiastolic(int i) {
                this.mBloodPressureDiastolic = i;
            }

            public double getBloodPressureSystolic() {
                return this.mBloodPressureSystolic;
            }

            public void setBloodPressureSystolic(int i) {
                this.mBloodPressureSystolic = i;
            }

            public double getDataPointDynamicHeartRate() {
                return this.mDataPointDynamicHeartRate;
            }

            public void setDataPointDynamicHeartRate(int i) {
                this.mDataPointDynamicHeartRate = i;
            }
        }
    }
}
