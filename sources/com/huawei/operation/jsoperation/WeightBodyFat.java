package com.huawei.operation.jsoperation;

import com.huawei.operation.jsoperation.BodyInfoBase;
import java.util.List;

/* loaded from: classes5.dex */
public class WeightBodyFat extends BodyInfoBase {
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
            private double mBasalMetabolism;
            private double mBmi;
            private int mBodyAge;
            private double mBodyFat;
            private double mBodyFatRate;
            private double mBodyScore;
            private double mBodyWeight;
            private double mBoneSalt;
            private String mExtendAttribute;
            private double mImpedance;
            private double mMoisture;
            private double mMoistureRate;
            private double mMuscleMass;
            private double mProteinRate;
            private double mVisceralFatLevel;

            public String getExtendAttribute() {
                return this.mExtendAttribute;
            }

            public void setExtendAttribute(String str) {
                this.mExtendAttribute = str;
            }

            public double getBodyFatRate() {
                return this.mBodyFatRate;
            }

            public void setBodyFatRate(int i) {
                this.mBodyFatRate = i;
            }

            public double getBodyWeight() {
                return this.mBodyWeight;
            }

            public void setBodyWeight(int i) {
                this.mBodyWeight = i;
            }

            public double getBodyFat() {
                return this.mBodyFat;
            }

            public void setBodyFat(int i) {
                this.mBodyFat = i;
            }

            public double getImpedance() {
                return this.mImpedance;
            }

            public void setImpedance(int i) {
                this.mImpedance = i;
            }

            public double getBmi() {
                return this.mBmi;
            }

            public void setBmi(double d) {
                this.mBmi = d;
            }

            public double getMuscleMass() {
                return this.mMuscleMass;
            }

            public void setMuscleMass(int i) {
                this.mMuscleMass = i;
            }

            public double getMoisture() {
                return this.mMoisture;
            }

            public void setMoisture(int i) {
                this.mMoisture = i;
            }

            public double getBoneSalt() {
                return this.mBoneSalt;
            }

            public void setBoneSalt(double d) {
                this.mBoneSalt = d;
            }

            public int getBodyAge() {
                return this.mBodyAge;
            }

            public void setBodyAge(int i) {
                this.mBodyAge = i;
            }

            public double getBodyScore() {
                return this.mBodyScore;
            }

            public void setBodyScore(int i) {
                this.mBodyScore = i;
            }

            public double getBasalMetabolism() {
                return this.mBasalMetabolism;
            }

            public void setBasalMetabolism(int i) {
                this.mBasalMetabolism = i;
            }

            public double getMoistureRate() {
                return this.mMoistureRate;
            }

            public void setMoistureRate(int i) {
                this.mMoistureRate = i;
            }

            public double getVisceralFatLevel() {
                return this.mVisceralFatLevel;
            }

            public void setVisceralFatLevel(double d) {
                this.mVisceralFatLevel = d;
            }

            public double getProteinRate() {
                return this.mProteinRate;
            }

            public void setProteinRate(int i) {
                this.mProteinRate = i;
            }
        }
    }
}
