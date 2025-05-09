package com.huawei.operation.jsoperation;

import com.huawei.operation.jsoperation.BodyInfoBase;
import java.util.List;

/* loaded from: classes5.dex */
public class BloodSugar extends BodyInfoBase {
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
            private double mBloodSugarAfterBreakfast;
            private double mBloodSugarAfterDinner;
            private double mBloodSugarAfterLunch;
            private double mBloodSugarBeforeBreakfast;
            private double mBloodSugarBeforeDawn;
            private double mBloodSugarBeforeDinner;
            private double mBloodSugarBeforeLunch;
            private double mBloodSugarBeforeSleep;

            public double getBloodSugarAfterLunch() {
                return this.mBloodSugarAfterLunch;
            }

            public void setBloodSugarAfterLunch(double d) {
                this.mBloodSugarAfterLunch = d;
            }

            public double getBloodSugarBeforeDinner() {
                return this.mBloodSugarBeforeDinner;
            }

            public void setBloodSugarBeforeDinner(double d) {
                this.mBloodSugarBeforeDinner = d;
            }

            public double getBloodSugarAfterDinner() {
                return this.mBloodSugarAfterDinner;
            }

            public void setBloodSugarAfterDinner(double d) {
                this.mBloodSugarAfterDinner = d;
            }

            public double getBloodSugarBeforeSleep() {
                return this.mBloodSugarBeforeSleep;
            }

            public void setBloodSugarBeforeSleep(double d) {
                this.mBloodSugarBeforeSleep = d;
            }

            public double getBloodSugarBeforeDawn() {
                return this.mBloodSugarBeforeDawn;
            }

            public void setBloodSugarBeforeDawn(double d) {
                this.mBloodSugarBeforeDawn = d;
            }

            public double getBloodSugarBeforeBreakfast() {
                return this.mBloodSugarBeforeBreakfast;
            }

            public void setBloodSugarBeforeBreakfast(double d) {
                this.mBloodSugarBeforeBreakfast = d;
            }

            public double getBloodSugarAfterBreakfast() {
                return this.mBloodSugarAfterBreakfast;
            }

            public void setBloodSugarAfterBreakfast(double d) {
                this.mBloodSugarAfterBreakfast = d;
            }

            public double getBloodSugarBeforeLunch() {
                return this.mBloodSugarBeforeLunch;
            }

            public void setBloodSugarBeforeLunch(double d) {
                this.mBloodSugarBeforeLunch = d;
            }
        }
    }
}
