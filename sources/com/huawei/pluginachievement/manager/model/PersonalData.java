package com.huawei.pluginachievement.manager.model;

/* loaded from: classes6.dex */
public class PersonalData {
    public static final String CLASS_NAME_ACHIEVE_MONTH_REPORT_ACTIVITY = "com.huawei.pluginachievement.ui.report.AchieveMonthReportActivity";
    public static final String CLASS_NAME_ACHIEVE_WEEK_REPORT_ACTIVITY = "com.huawei.pluginachievement.ui.report.AchieveWeekReportActivity";
    public static final String CLASS_NAME_PERSONAL_HISTORY_BEST = "com.huawei.ui.main.stories.fitness.activity.step.FitnessStepDetailActivity";
    public static final String CLASS_NAME_PERSONAL_KAKA = "com.huawei.pluginachievement.ui.AchieveKaKaActivity";
    public static final String CLASS_NAME_PERSONAL_KAKA_DETAIL = "com.huawei.pluginachievement.ui.kakatask.AchieveKaKaDetailActivity";
    public static final String CLASS_NAME_PERSONAL_KAKA_EXCHANGE_POINT = "com.huawei.pluginachievement.ui.AchieveKakaExchangeActivity";
    public static final String CLASS_NAME_PERSONAL_KAKA_EXCHANGE_POINT_RULE = "com.huawei.pluginachievement.ui.AchieveKakaExchangeRuleActivity";
    public static final String CLASS_NAME_PERSONAL_LEVEL = "com.huawei.pluginachievement.ui.AchieveLevelMainActivity";
    public static final String CLASS_NAME_PERSONAL_LEVEL_DIALOG = "com.huawei.pluginachievement.ui.level.AchieveLevelMessageDialog";
    public static final String CLASS_NAME_PERSONAL_LEVEL_RECORD = "com.huawei.pluginachievement.ui.AchieveLevelRecordActivity";
    public static final String CLASS_NAME_PERSONAL_LEVEL_RULE = "com.huawei.pluginachievement.ui.level.AchieveLevelRuleActivity";
    public static final String CLASS_NAME_PERSONAL_LEVEL_RULE_CN = "com.huawei.pluginachievement.ui.AchieveLevelRuleCNActivity";
    public static final String CLASS_NAME_PERSONAL_LOTTERY_SHARE = "com.huawei.pluginachievement.ui.AchieveLotteryShareActivity";
    public static final String CLASS_NAME_PERSONAL_MEDAL_DETAIL = "com.huawei.pluginachievement.ui.AchieveMedalDetailActivity";
    public static final String CLASS_NAME_PERSONAL_MEDAL_MESSAGE_DIALOG = "com.huawei.pluginachievement.ui.AchieveMedalMessageDialog";
    public static final String CLASS_NAME_PERSONAL_NEW_LEVEL = "com.huawei.pluginachievement.ui.AchieveNewLevelMainActivity";
    public static final String CLASS_NAME_PERSONAL_NEW_MEDALS = "com.huawei.pluginachievement.ui.AchieveMedalNewActivity";
    public static final String CLASS_NAME_PERSONAL_REPORT = "com.huawei.pluginachievement.ui.AchieveReportActivity";
    public static final String CLASS_NAME_PERSONAL_REPORT_ADVANCED = "com.huawei.pluginachievement.ui.AchieveReportAdvanceActivity";
    public static final String CLASS_NAME_PERSONAL_REPORT_MESSAGE_DIALOG = "com.huawei.pluginachievement.ui.report.AchieveReportDialog";
    public static final String CLASS_NAME_PERSONAL_YEAR_REPORT = "com.huawei.pluginachievement.ui.report.ReportYearActivity";
    public static final String CLASS_NAME_SPORT_MEDAL_VIEW = "com.huawei.pluginachievement.ui.SportMedalDialog";
    public static final String CLASS_NAME_WEEK_AND_MONTH = "com.huawei.pluginachievement.ui.report.WeekAndMonthActivity";
    private int bestDaySteps;
    private String medals;
    private int personalLevel;
    private String personalLevelDesc;
    private double stepRanking;
    private int sumDays;
    private int sumKakaNum;
    private double sumSteps;

    private PersonalData() {
        this.personalLevelDesc = "";
        this.medals = "";
    }

    public int getPersonalLevel() {
        return this.personalLevel;
    }

    public int getSumDays() {
        return this.sumDays;
    }

    public double getSumSteps() {
        return this.sumSteps;
    }

    public int getBestDaySteps() {
        return this.bestDaySteps;
    }

    public double getStepRanking() {
        return this.stepRanking;
    }

    public int getSumKakaNum() {
        return this.sumKakaNum;
    }

    public String getMedals() {
        return this.medals;
    }

    public String getPersonalLevelDesc() {
        return this.personalLevelDesc;
    }

    public static Builder builder() {
        return new Builder();
    }

    public String toString() {
        return "PersonalData{personalLevel='" + this.personalLevel + "', personalLevelDesc=" + this.personalLevelDesc + ", sumDays=" + this.sumDays + ", sumSteps=" + this.sumSteps + ", bestDaySteps=" + this.bestDaySteps + ", stepRanking=" + this.stepRanking + ", sumKakaNum=" + this.sumKakaNum + ", medals=" + this.medals + '}';
    }

    public static class Builder {
        private int bestDaySteps;
        private String medals;
        private int personalLevel;
        private String personalLevelDesc;
        private double stepRanking;
        private int sumDays;
        private int sumKakaNum;
        private double sumSteps;

        public Builder withPersonalLevel(int i) {
            this.personalLevel = i;
            return this;
        }

        public Builder withSumDays(int i) {
            this.sumDays = i;
            return this;
        }

        public Builder withSumSteps(double d) {
            this.sumSteps = d;
            return this;
        }

        public Builder withStepRanking(double d) {
            this.stepRanking = d;
            return this;
        }

        public Builder withBestDaySteps(int i) {
            this.bestDaySteps = i;
            return this;
        }

        public Builder withSumKakaNum(int i) {
            this.sumKakaNum = i;
            return this;
        }

        public Builder withMedals(String str) {
            this.medals = str;
            return this;
        }

        public Builder withPersonalLevelDesc(String str) {
            this.personalLevelDesc = str;
            return this;
        }

        public PersonalData build() {
            PersonalData personalData = new PersonalData();
            personalData.medals = this.medals;
            personalData.personalLevel = this.personalLevel;
            personalData.personalLevelDesc = this.personalLevelDesc;
            personalData.stepRanking = this.stepRanking;
            personalData.sumKakaNum = this.sumKakaNum;
            personalData.sumDays = this.sumDays;
            personalData.sumSteps = this.sumSteps;
            personalData.bestDaySteps = this.bestDaySteps;
            return personalData;
        }
    }
}
