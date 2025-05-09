package com.huawei.hihealthservice.sync.syncdata;

/* loaded from: classes8.dex */
public class HiSyncWeightData {
    private Integer age;
    private Double basalMetabolism;
    private Double bmi;
    private Integer bodyAge;
    private Double bodyFat;
    private Double bodyFatRate;
    private Double bodyScore;
    private Double bodyShape;
    private Integer bodySize;
    private Double bodyWeight;
    private Double boneSalt;
    private Integer conflictFlag;
    private String conflictUserInfo;
    private String extendAttribute;
    private Double fatBalance;
    private Integer gender;
    private Double healthyFatRate;
    private Double healthyWeight;
    private Integer heartRate;
    private Integer height;
    private Double impedance;
    private Double leftArmFatMass;
    private Double leftArmMuscleMass;
    private Double leftLegFatMass;
    private Double leftLegMuscleMass;
    private Double lfrfHfImpedance;
    private Double lfrfImpedance;
    private Double lhlfHfImpedance;
    private Double lhlfImpedance;
    private Double lhrfHfImpedance;
    private Double lhrfImpedance;
    private Double lhrhHfImpedance;
    private Double lhrhImpedance;
    private Double moisture;
    private Double moistureRate;
    private Double muscleBalance;
    private Double muscleMass;
    private Integer pole;
    private Integer pressure;
    private Double proteinRate;
    private Double proteinValue;
    private Double rasm;
    private Double rhlfHfImpedance;
    private Double rhlfImpedance;
    private Double rhrfHfImpedance;
    private Double rhrfImpedance;
    private Double rightArmFatMass;
    private Double rightArmMuscleMass;
    private Double rightLegFatMass;
    private Double rightLegMuscleMass;
    private Double skeletalMusclelMass;
    private Double trunkFatMass;
    private Double trunkMuscleMass;
    private Double visceralFatLevel;
    private Double waistHipRatio;
    private Double waistHipRatioUser;

    public void setUserid(String str) {
        this.extendAttribute = str;
    }

    public String getUserId() {
        return this.extendAttribute;
    }

    public void setWeight(double d) {
        this.bodyWeight = Double.valueOf(d);
    }

    public Double getWeight() {
        return this.bodyWeight;
    }

    public void setBodyFat(double d) {
        this.bodyFat = Double.valueOf(d);
    }

    public Double getBodyFat() {
        return this.bodyFat;
    }

    public void setBodyFatRate(Double d) {
        this.bodyFatRate = d;
    }

    public Double getBodyFatRate() {
        return this.bodyFatRate;
    }

    public void setImpedance(Double d) {
        this.impedance = d;
    }

    public Double getImpedance() {
        return this.impedance;
    }

    public void setBMI(Double d) {
        this.bmi = d;
    }

    public Double getBMI() {
        return this.bmi;
    }

    public void setMuscles(Double d) {
        this.muscleMass = d;
    }

    public Double getMuscles() {
        return this.muscleMass;
    }

    public void setBMR(Double d) {
        this.basalMetabolism = d;
    }

    public Double getBMR() {
        return this.basalMetabolism;
    }

    public void setWater(Double d) {
        this.moisture = d;
    }

    public Double getWater() {
        return this.moisture;
    }

    public void setMoistureRate(Double d) {
        this.moistureRate = d;
    }

    public Double getMoistureRate() {
        return this.moistureRate;
    }

    public void setFatLevel(Double d) {
        this.visceralFatLevel = d;
    }

    public Double getFatLevel() {
        return this.visceralFatLevel;
    }

    public void setBoneMineral(Double d) {
        this.boneSalt = d;
    }

    public Double getBoneMineral() {
        return this.boneSalt;
    }

    public void setProtein(Double d) {
        this.proteinRate = d;
    }

    public Double getProtein() {
        return this.proteinRate;
    }

    public void setBodyScore(double d) {
        this.bodyScore = Double.valueOf(d);
    }

    public Double getBodyScore() {
        return this.bodyScore;
    }

    public void setBodyAge(Integer num) {
        this.bodyAge = num;
    }

    public Integer getBodyAge() {
        return this.bodyAge;
    }

    public Integer getPole() {
        return this.pole;
    }

    public void setPole(Integer num) {
        this.pole = num;
    }

    public Double getLhrhImpedance() {
        return this.lhrhImpedance;
    }

    public void setLhrhImpedance(Double d) {
        this.lhrhImpedance = d;
    }

    public Double getLhlfImpedance() {
        return this.lhlfImpedance;
    }

    public void setLhlfImpedance(Double d) {
        this.lhlfImpedance = d;
    }

    public Double getLhrfImpedance() {
        return this.lhrfImpedance;
    }

    public void setLhrfImpedance(Double d) {
        this.lhrfImpedance = d;
    }

    public Double getRhlfImpedance() {
        return this.rhlfImpedance;
    }

    public void setRhlfImpedance(Double d) {
        this.rhlfImpedance = d;
    }

    public Double getRhrfImpedance() {
        return this.rhrfImpedance;
    }

    public void setRhrfImpedance(Double d) {
        this.rhrfImpedance = d;
    }

    public Double getLfrfImpedance() {
        return this.lfrfImpedance;
    }

    public void setLfrfImpedance(Double d) {
        this.lfrfImpedance = d;
    }

    public Double getLeftArmMuscleMass() {
        return this.leftArmMuscleMass;
    }

    public void setLeftArmMuscleMass(Double d) {
        this.leftArmMuscleMass = d;
    }

    public Double getRightArmMuscleMass() {
        return this.rightArmMuscleMass;
    }

    public void setRightArmMuscleMass(Double d) {
        this.rightArmMuscleMass = d;
    }

    public Double getLeftLegMuscleMass() {
        return this.leftLegMuscleMass;
    }

    public void setLeftLegMuscleMass(Double d) {
        this.leftLegMuscleMass = d;
    }

    public Double getRightLegMuscleMass() {
        return this.rightLegMuscleMass;
    }

    public void setRightLegMuscleMass(Double d) {
        this.rightLegMuscleMass = d;
    }

    public Double getTrunkMuscleMass() {
        return this.trunkMuscleMass;
    }

    public void setTrunkMuscleMass(Double d) {
        this.trunkMuscleMass = d;
    }

    public Double getLeftArmFatMass() {
        return this.leftArmFatMass;
    }

    public void setLeftArmFatMass(Double d) {
        this.leftArmFatMass = d;
    }

    public Double getRightArmFatMass() {
        return this.rightArmFatMass;
    }

    public void setRightArmFatMass(Double d) {
        this.rightArmFatMass = d;
    }

    public Double getLeftLegFatMass() {
        return this.leftLegFatMass;
    }

    public void setLeftLegFatMass(Double d) {
        this.leftLegFatMass = d;
    }

    public Double getRightLegFatMass() {
        return this.rightLegFatMass;
    }

    public void setRightLegFatMass(Double d) {
        this.rightLegFatMass = d;
    }

    public Double getTrunkFatMass() {
        return this.trunkFatMass;
    }

    public void setTrunkFatMass(Double d) {
        this.trunkFatMass = d;
    }

    public Double getRasm() {
        return this.rasm;
    }

    public void setRasm(Double d) {
        this.rasm = d;
    }

    public Double getWaistHipRatio() {
        return this.waistHipRatio;
    }

    public void setWaistHipRatio(Double d) {
        this.waistHipRatio = d;
    }

    public Double getWaistHipRatioUser() {
        return this.waistHipRatioUser;
    }

    public void setWaistHipRatioUser(Double d) {
        this.waistHipRatioUser = d;
    }

    public Double getBodyShape() {
        return this.bodyShape;
    }

    public void setBodyShape(Double d) {
        this.bodyShape = d;
    }

    public Double getFatBalance() {
        return this.fatBalance;
    }

    public void setFatBalance(Double d) {
        this.fatBalance = d;
    }

    public Double getMuscleBalance() {
        return this.muscleBalance;
    }

    public void setMuscleBalance(Double d) {
        this.muscleBalance = d;
    }

    public Double getLhrhHfImpedance() {
        return this.lhrhHfImpedance;
    }

    public void setLhrhHfImpedance(Double d) {
        this.lhrhHfImpedance = d;
    }

    public Double getLhlfHfImpedance() {
        return this.lhlfHfImpedance;
    }

    public void setLhlfHfImpedance(Double d) {
        this.lhlfHfImpedance = d;
    }

    public Double getLhrfHfImpedance() {
        return this.lhrfHfImpedance;
    }

    public void setLhrfHfImpedance(Double d) {
        this.lhrfHfImpedance = d;
    }

    public Double getRhlfHfImpedance() {
        return this.rhlfHfImpedance;
    }

    public void setRhlfHfImpedance(Double d) {
        this.rhlfHfImpedance = d;
    }

    public Double getRhrfHfImpedance() {
        return this.rhrfHfImpedance;
    }

    public void setRhrfHfImpedance(Double d) {
        this.rhrfHfImpedance = d;
    }

    public Double getLfrfHfImpedance() {
        return this.lfrfHfImpedance;
    }

    public void setLfrfHfImpedance(Double d) {
        this.lfrfHfImpedance = d;
    }

    public String toString() {
        return "userid=" + this.extendAttribute + ",weight=" + this.bodyWeight + ",bodyFat=" + this.bodyFat + ",bodyFatRate=" + this.bodyFatRate + ",impedance=" + this.impedance + ",BMI=" + this.bmi + ",muscles=" + this.muscleMass + ",BMR=" + this.basalMetabolism + ",moisture=" + this.moisture + ",moistureRate=" + this.moistureRate + ",fatLevel=" + this.visceralFatLevel + ",boneSalt=" + this.boneSalt + ",proteinRate=" + this.proteinRate + ",bodyAge=" + this.bodyAge + ",bodyScore=" + this.bodyScore;
    }

    public Integer getHeartRate() {
        return this.heartRate;
    }

    public void setHeartRate(Integer num) {
        this.heartRate = num;
    }

    public Integer getPressure() {
        return this.pressure;
    }

    public void setPressure(Integer num) {
        this.pressure = num;
    }

    public Double getSkeletalMusclelMass() {
        return this.skeletalMusclelMass;
    }

    public void setSkeletalMusclelMass(Double d) {
        this.skeletalMusclelMass = d;
    }

    public Integer getGender() {
        return this.gender;
    }

    public void setGender(Integer num) {
        this.gender = num;
    }

    public Integer getAge() {
        return this.age;
    }

    public void setAge(Integer num) {
        this.age = num;
    }

    public Integer getHeight() {
        return this.height;
    }

    public void setHeight(Integer num) {
        this.height = num;
    }

    public Double getHealthyWeight() {
        return this.healthyWeight;
    }

    public void setHealthyWeight(Double d) {
        this.healthyWeight = d;
    }

    public Double getHealthyFatRate() {
        return this.healthyFatRate;
    }

    public void setHealthyFatRate(Double d) {
        this.healthyFatRate = d;
    }

    public Integer getBodySize() {
        return this.bodySize;
    }

    public void setBodySize(Integer num) {
        this.bodySize = num;
    }

    public void setConflictFlag(Integer num) {
        this.conflictFlag = num;
    }

    public Integer getConflictFlag() {
        return this.conflictFlag;
    }

    public void setConflictUserInfo(String str) {
        this.conflictUserInfo = str;
    }

    public String getConflictUserInfo() {
        return this.conflictUserInfo;
    }

    public void setProteinValue(Double d) {
        this.proteinValue = d;
    }

    public Double getProteinValue() {
        return this.proteinValue;
    }
}
