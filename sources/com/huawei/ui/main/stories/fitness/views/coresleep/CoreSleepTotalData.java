package com.huawei.ui.main.stories.fitness.views.coresleep;

import android.os.Parcel;
import android.os.Parcelable;
import com.huawei.hwlogsmodel.LogUtil;
import defpackage.sdn;

/* loaded from: classes6.dex */
public class CoreSleepTotalData implements Parcelable {
    public static final Parcelable.Creator<CoreSleepTotalData> CREATOR = new Parcelable.Creator<CoreSleepTotalData>() { // from class: com.huawei.ui.main.stories.fitness.views.coresleep.CoreSleepTotalData.2
        @Override // android.os.Parcelable.Creator
        /* renamed from: dwM_, reason: merged with bridge method [inline-methods] */
        public CoreSleepTotalData createFromParcel(Parcel parcel) {
            return new CoreSleepTotalData(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: c, reason: merged with bridge method [inline-methods] */
        public CoreSleepTotalData[] newArray(int i) {
            return new CoreSleepTotalData[i];
        }
    };
    private static final String TAG = "CoreSleepTotalData";
    private int adNum0;
    private int adNum1;
    private ResultInfoArrBean resultInfoArr;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public CoreSleepTotalData() {
    }

    protected CoreSleepTotalData(Parcel parcel) {
        this.adNum0 = parcel.readInt();
        this.adNum1 = parcel.readInt();
        this.resultInfoArr = (ResultInfoArrBean) parcel.readParcelable(ResultInfoArrBean.class.getClassLoader());
    }

    public int getAdNum0() {
        return this.adNum0;
    }

    public int getAdNum1() {
        return this.adNum1;
    }

    public ResultInfoArrBean getResultInfoArr() {
        return (ResultInfoArrBean) sdn.c(this.resultInfoArr);
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.adNum0);
        parcel.writeInt(this.adNum1);
        parcel.writeParcelable(this.resultInfoArr, i);
    }

    public static class ResultInfoArrBean implements Parcelable {
        public static final Parcelable.Creator<ResultInfoArrBean> CREATOR = new Parcelable.Creator<ResultInfoArrBean>() { // from class: com.huawei.ui.main.stories.fitness.views.coresleep.CoreSleepTotalData.ResultInfoArrBean.2
            @Override // android.os.Parcelable.Creator
            /* renamed from: dwN_, reason: merged with bridge method [inline-methods] */
            public ResultInfoArrBean createFromParcel(Parcel parcel) {
                return new ResultInfoArrBean(parcel);
            }

            @Override // android.os.Parcelable.Creator
            /* renamed from: b, reason: merged with bridge method [inline-methods] */
            public ResultInfoArrBean[] newArray(int i) {
                return new ResultInfoArrBean[i];
            }
        };
        private CurrBean curr;
        private LastBean last;

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        public ResultInfoArrBean() {
        }

        protected ResultInfoArrBean(Parcel parcel) {
            this.curr = (CurrBean) parcel.readParcelable(CurrBean.class.getClassLoader());
            this.last = (LastBean) parcel.readParcelable(LastBean.class.getClassLoader());
        }

        public CurrBean getCurr() {
            return (CurrBean) sdn.c(this.curr);
        }

        public LastBean getLast() {
            return (LastBean) sdn.c(this.last);
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeParcelable(this.curr, i);
            parcel.writeParcelable(this.last, i);
        }

        public static class CurrBean implements Parcelable {
            public static final Parcelable.Creator<CurrBean> CREATOR = new Parcelable.Creator<CurrBean>() { // from class: com.huawei.ui.main.stories.fitness.views.coresleep.CoreSleepTotalData.ResultInfoArrBean.CurrBean.1
                @Override // android.os.Parcelable.Creator
                /* renamed from: dwO_, reason: merged with bridge method [inline-methods] */
                public CurrBean createFromParcel(Parcel parcel) {
                    return new CurrBean(parcel);
                }

                @Override // android.os.Parcelable.Creator
                /* renamed from: c, reason: merged with bridge method [inline-methods] */
                public CurrBean[] newArray(int i) {
                    return new CurrBean[i];
                }
            };
            private DaysBean days;
            private MaxBean max;
            private MeanBean mean;
            private MinBean min;

            @Override // android.os.Parcelable
            public int describeContents() {
                return 0;
            }

            public CurrBean() {
            }

            protected CurrBean(Parcel parcel) {
                this.days = (DaysBean) parcel.readParcelable(DaysBean.class.getClassLoader());
                this.mean = (MeanBean) parcel.readParcelable(MeanBean.class.getClassLoader());
                this.max = (MaxBean) parcel.readParcelable(MaxBean.class.getClassLoader());
                this.min = (MinBean) parcel.readParcelable(MinBean.class.getClassLoader());
            }

            public DaysBean getDays() {
                return (DaysBean) sdn.c(this.days);
            }

            public MeanBean getMean() {
                return (MeanBean) sdn.c(this.mean);
            }

            public MaxBean getMax() {
                return (MaxBean) sdn.c(this.max);
            }

            public MinBean getMin() {
                return (MinBean) sdn.c(this.min);
            }

            @Override // android.os.Parcelable
            public void writeToParcel(Parcel parcel, int i) {
                parcel.writeParcelable(this.days, i);
                parcel.writeParcelable(this.mean, i);
                parcel.writeParcelable(this.max, i);
                parcel.writeParcelable(this.min, i);
            }

            public static class DaysBean implements Parcelable {
                public static final Parcelable.Creator<DaysBean> CREATOR = new Parcelable.Creator<DaysBean>() { // from class: com.huawei.ui.main.stories.fitness.views.coresleep.CoreSleepTotalData.ResultInfoArrBean.CurrBean.DaysBean.4
                    @Override // android.os.Parcelable.Creator
                    /* renamed from: dwP_, reason: merged with bridge method [inline-methods] */
                    public DaysBean createFromParcel(Parcel parcel) {
                        return new DaysBean(parcel);
                    }

                    @Override // android.os.Parcelable.Creator
                    /* renamed from: b, reason: merged with bridge method [inline-methods] */
                    public DaysBean[] newArray(int i) {
                        return new DaysBean[i];
                    }
                };
                private int earlyWakeUp;
                private int easyAwake;
                private int irregularFallSleep;
                private int irregularWakeUp;
                private int lackSleep;
                private int lateFallSleep;
                private int lowAllSleepLateFallSleep;
                private int lowBreathQuality;
                private int lowDeepSleep;
                private int lowDeepSleepLateFallSleep;
                private int lowRemSleep;
                private int overRemLateFallSleep;
                private int overRemSleep;
                private int overSleep;
                private int upperScore;

                @Override // android.os.Parcelable
                public int describeContents() {
                    return 0;
                }

                protected DaysBean(Parcel parcel) {
                    this.upperScore = parcel.readInt();
                    this.overSleep = parcel.readInt();
                    this.lackSleep = parcel.readInt();
                    this.irregularWakeUp = parcel.readInt();
                    this.irregularFallSleep = parcel.readInt();
                    this.lateFallSleep = parcel.readInt();
                    this.lowDeepSleep = parcel.readInt();
                    this.lowBreathQuality = parcel.readInt();
                    this.overRemSleep = parcel.readInt();
                    this.lowRemSleep = parcel.readInt();
                    this.earlyWakeUp = parcel.readInt();
                    this.easyAwake = parcel.readInt();
                    this.lowDeepSleepLateFallSleep = parcel.readInt();
                    this.lowAllSleepLateFallSleep = parcel.readInt();
                    this.overRemLateFallSleep = parcel.readInt();
                }

                public int getUpperScore() {
                    return ((Integer) sdn.c(Integer.valueOf(this.upperScore))).intValue();
                }

                public int getOverSleep() {
                    return ((Integer) sdn.c(Integer.valueOf(this.overSleep))).intValue();
                }

                public int getLackSleep() {
                    return ((Integer) sdn.c(Integer.valueOf(this.lackSleep))).intValue();
                }

                public int getIrregularWakeUp() {
                    return ((Integer) sdn.c(Integer.valueOf(this.irregularWakeUp))).intValue();
                }

                public int getIrregularFallSleep() {
                    return ((Integer) sdn.c(Integer.valueOf(this.irregularFallSleep))).intValue();
                }

                public int getLateFallSleep() {
                    return ((Integer) sdn.c(Integer.valueOf(this.lateFallSleep))).intValue();
                }

                public int getLowDeepSleep() {
                    LogUtil.c(CoreSleepTotalData.TAG, "DaysBean lowDeepSleep:", Integer.valueOf(this.lowDeepSleep));
                    return ((Integer) sdn.c(Integer.valueOf(this.lowDeepSleep))).intValue();
                }

                public int getLowBreathQuality() {
                    LogUtil.c(CoreSleepTotalData.TAG, "DaysBean lowBreathQuality:", Integer.valueOf(this.lowBreathQuality));
                    return ((Integer) sdn.c(Integer.valueOf(this.lowBreathQuality))).intValue();
                }

                public int getOverREMSleep() {
                    LogUtil.c(CoreSleepTotalData.TAG, " DaysBean overRemSleep:", Integer.valueOf(this.overRemSleep));
                    return ((Integer) sdn.c(Integer.valueOf(this.overRemSleep))).intValue();
                }

                public int getLowREMSleep() {
                    LogUtil.c(CoreSleepTotalData.TAG, "DaysBean lowRemSleep:", Integer.valueOf(this.lowRemSleep));
                    return ((Integer) sdn.c(Integer.valueOf(this.lowRemSleep))).intValue();
                }

                public int getEarlyWakeUp() {
                    LogUtil.c(CoreSleepTotalData.TAG, "DaysBean earlyWakeUp:", Integer.valueOf(this.earlyWakeUp));
                    return ((Integer) sdn.c(Integer.valueOf(this.earlyWakeUp))).intValue();
                }

                public int getEasyAwake() {
                    LogUtil.c(CoreSleepTotalData.TAG, "DaysBean easyAwake:", Integer.valueOf(this.easyAwake));
                    return ((Integer) sdn.c(Integer.valueOf(this.easyAwake))).intValue();
                }

                public int getLowAllSleepLateFallSleep() {
                    LogUtil.c(CoreSleepTotalData.TAG, "DaysBean lowAllSleepLateFallSleep:", Integer.valueOf(this.lowAllSleepLateFallSleep));
                    return ((Integer) sdn.c(Integer.valueOf(this.lowAllSleepLateFallSleep))).intValue();
                }

                public int getOverREMLateFallSleep() {
                    LogUtil.c(CoreSleepTotalData.TAG, "DaysBean overRemLateFallSleep:", Integer.valueOf(this.overRemLateFallSleep));
                    return ((Integer) sdn.c(Integer.valueOf(this.overRemLateFallSleep))).intValue();
                }

                @Override // android.os.Parcelable
                public void writeToParcel(Parcel parcel, int i) {
                    LogUtil.c(CoreSleepTotalData.TAG, "DaysBean writeToParcel");
                    parcel.writeInt(this.upperScore);
                    parcel.writeInt(this.overSleep);
                    parcel.writeInt(this.lackSleep);
                    parcel.writeInt(this.irregularWakeUp);
                    parcel.writeInt(this.irregularFallSleep);
                    parcel.writeInt(this.lateFallSleep);
                    parcel.writeInt(this.lowDeepSleep);
                    parcel.writeInt(this.lowBreathQuality);
                    parcel.writeInt(this.overRemSleep);
                    parcel.writeInt(this.lowRemSleep);
                    parcel.writeInt(this.earlyWakeUp);
                    parcel.writeInt(this.easyAwake);
                    parcel.writeInt(this.lowDeepSleepLateFallSleep);
                    parcel.writeInt(this.lowAllSleepLateFallSleep);
                    parcel.writeInt(this.overRemLateFallSleep);
                    LogUtil.c(CoreSleepTotalData.TAG, "DaysBean writeToParcel");
                }
            }

            public static class MeanBean implements Parcelable {
                public static final Parcelable.Creator<MeanBean> CREATOR = new Parcelable.Creator<MeanBean>() { // from class: com.huawei.ui.main.stories.fitness.views.coresleep.CoreSleepTotalData.ResultInfoArrBean.CurrBean.MeanBean.2
                    @Override // android.os.Parcelable.Creator
                    /* renamed from: dwR_, reason: merged with bridge method [inline-methods] */
                    public MeanBean createFromParcel(Parcel parcel) {
                        return new MeanBean(parcel);
                    }

                    @Override // android.os.Parcelable.Creator
                    /* renamed from: b, reason: merged with bridge method [inline-methods] */
                    public MeanBean[] newArray(int i) {
                        return new MeanBean[i];
                    }
                };
                private int allSleepTime;
                private int awakeCnt;
                private int awakeTime;
                private int breathQuality;
                private int deepSleepPartCnt;
                private int deepSleepScale;
                private int deepSleepTime;
                private int efficiency;
                private int fallSleepTime;
                private int latency;
                private int remScale;
                private int score;
                private int snoreCnt;
                private int wakeUpTime;

                @Override // android.os.Parcelable
                public int describeContents() {
                    return 0;
                }

                public MeanBean() {
                }

                protected MeanBean(Parcel parcel) {
                    this.score = parcel.readInt();
                    this.wakeUpTime = parcel.readInt();
                    this.allSleepTime = parcel.readInt();
                    this.awakeTime = parcel.readInt();
                    this.deepSleepTime = parcel.readInt();
                    this.deepSleepPartCnt = parcel.readInt();
                    this.deepSleepScale = parcel.readInt();
                    this.remScale = parcel.readInt();
                    this.breathQuality = parcel.readInt();
                    this.fallSleepTime = parcel.readInt();
                    this.awakeCnt = parcel.readInt();
                    this.snoreCnt = parcel.readInt();
                    this.efficiency = parcel.readInt();
                    this.latency = parcel.readInt();
                }

                public int getScore() {
                    return ((Integer) sdn.c(Integer.valueOf(this.score))).intValue();
                }

                public int getWakeUpTime() {
                    return ((Integer) sdn.c(Integer.valueOf(this.wakeUpTime))).intValue();
                }

                public int getAllSleepTime() {
                    return ((Integer) sdn.c(Integer.valueOf(this.allSleepTime))).intValue();
                }

                public int getDeepSleepScale() {
                    return ((Integer) sdn.c(Integer.valueOf(this.deepSleepScale))).intValue();
                }

                public int getREMScale() {
                    return ((Integer) sdn.c(Integer.valueOf(this.remScale))).intValue();
                }

                public int getBreathQuality() {
                    return ((Integer) sdn.c(Integer.valueOf(this.breathQuality))).intValue();
                }

                public int getFallSleepTime() {
                    return ((Integer) sdn.c(Integer.valueOf(this.fallSleepTime))).intValue();
                }

                public int getAwakeCnt() {
                    return ((Integer) sdn.c(Integer.valueOf(this.awakeCnt))).intValue();
                }

                @Override // android.os.Parcelable
                public void writeToParcel(Parcel parcel, int i) {
                    parcel.writeInt(this.score);
                    parcel.writeInt(this.wakeUpTime);
                    parcel.writeInt(this.allSleepTime);
                    parcel.writeInt(this.awakeTime);
                    parcel.writeInt(this.deepSleepTime);
                    parcel.writeInt(this.deepSleepPartCnt);
                    parcel.writeInt(this.deepSleepScale);
                    parcel.writeInt(this.remScale);
                    parcel.writeInt(this.breathQuality);
                    parcel.writeInt(this.fallSleepTime);
                    parcel.writeInt(this.awakeCnt);
                    parcel.writeInt(this.snoreCnt);
                    parcel.writeInt(this.efficiency);
                    parcel.writeInt(this.latency);
                }
            }

            public static class MaxBean implements Parcelable {
                public static final Parcelable.Creator<MaxBean> CREATOR = new Parcelable.Creator<MaxBean>() { // from class: com.huawei.ui.main.stories.fitness.views.coresleep.CoreSleepTotalData.ResultInfoArrBean.CurrBean.MaxBean.1
                    @Override // android.os.Parcelable.Creator
                    /* renamed from: dwQ_, reason: merged with bridge method [inline-methods] */
                    public MaxBean createFromParcel(Parcel parcel) {
                        return new MaxBean(parcel);
                    }

                    @Override // android.os.Parcelable.Creator
                    /* renamed from: c, reason: merged with bridge method [inline-methods] */
                    public MaxBean[] newArray(int i) {
                        return new MaxBean[i];
                    }
                };
                private int allSleepTime;
                private int breathQuality;
                private int deepSleepScale;
                private int remScale;

                @Override // android.os.Parcelable
                public int describeContents() {
                    return 0;
                }

                public MaxBean() {
                }

                protected MaxBean(Parcel parcel) {
                    this.allSleepTime = parcel.readInt();
                    this.deepSleepScale = parcel.readInt();
                    this.remScale = parcel.readInt();
                    this.breathQuality = parcel.readInt();
                }

                public int getAllSleepTime() {
                    return ((Integer) sdn.c(Integer.valueOf(this.allSleepTime))).intValue();
                }

                public int getDeepSleepScale() {
                    return ((Integer) sdn.c(Integer.valueOf(this.deepSleepScale))).intValue();
                }

                public int getREMScale() {
                    return ((Integer) sdn.c(Integer.valueOf(this.remScale))).intValue();
                }

                public int getBreathQuality() {
                    return ((Integer) sdn.c(Integer.valueOf(this.breathQuality))).intValue();
                }

                @Override // android.os.Parcelable
                public void writeToParcel(Parcel parcel, int i) {
                    parcel.writeInt(this.allSleepTime);
                    parcel.writeInt(this.deepSleepScale);
                    parcel.writeInt(this.remScale);
                    parcel.writeInt(this.breathQuality);
                }
            }

            public static class MinBean implements Parcelable {
                public static final Parcelable.Creator<MinBean> CREATOR = new Parcelable.Creator<MinBean>() { // from class: com.huawei.ui.main.stories.fitness.views.coresleep.CoreSleepTotalData.ResultInfoArrBean.CurrBean.MinBean.3
                    @Override // android.os.Parcelable.Creator
                    /* renamed from: dwS_, reason: merged with bridge method [inline-methods] */
                    public MinBean createFromParcel(Parcel parcel) {
                        return new MinBean(parcel);
                    }

                    @Override // android.os.Parcelable.Creator
                    /* renamed from: c, reason: merged with bridge method [inline-methods] */
                    public MinBean[] newArray(int i) {
                        return new MinBean[i];
                    }
                };
                private int allSleepTime;
                private int breathQuality;
                private int deepSleepScale;
                private int remScale;

                @Override // android.os.Parcelable
                public int describeContents() {
                    return 0;
                }

                public MinBean() {
                }

                protected MinBean(Parcel parcel) {
                    this.allSleepTime = parcel.readInt();
                    this.deepSleepScale = parcel.readInt();
                    this.remScale = parcel.readInt();
                    this.breathQuality = parcel.readInt();
                }

                public int getAllSleepTime() {
                    LogUtil.c(CoreSleepTotalData.TAG, "allSleepTime: ", Integer.valueOf(this.allSleepTime));
                    return ((Integer) sdn.c(Integer.valueOf(this.allSleepTime))).intValue();
                }

                public int getDeepSleepScale() {
                    LogUtil.c(CoreSleepTotalData.TAG, "deepSleepScale: ", Integer.valueOf(this.deepSleepScale));
                    return ((Integer) sdn.c(Integer.valueOf(this.deepSleepScale))).intValue();
                }

                public int getREMScale() {
                    LogUtil.c(CoreSleepTotalData.TAG, "remScale: ", Integer.valueOf(this.remScale));
                    return ((Integer) sdn.c(Integer.valueOf(this.remScale))).intValue();
                }

                public int getBreathQuality() {
                    LogUtil.c(CoreSleepTotalData.TAG, "breathQuality: ", Integer.valueOf(this.breathQuality));
                    return ((Integer) sdn.c(Integer.valueOf(this.breathQuality))).intValue();
                }

                @Override // android.os.Parcelable
                public void writeToParcel(Parcel parcel, int i) {
                    LogUtil.c(CoreSleepTotalData.TAG, "writeToParcel ");
                    parcel.writeInt(this.allSleepTime);
                    parcel.writeInt(this.deepSleepScale);
                    parcel.writeInt(this.remScale);
                    parcel.writeInt(this.breathQuality);
                }
            }
        }

        public static class LastBean implements Parcelable {
            public static final Parcelable.Creator<LastBean> CREATOR = new Parcelable.Creator<LastBean>() { // from class: com.huawei.ui.main.stories.fitness.views.coresleep.CoreSleepTotalData.ResultInfoArrBean.LastBean.4
                @Override // android.os.Parcelable.Creator
                /* renamed from: dwT_, reason: merged with bridge method [inline-methods] */
                public LastBean createFromParcel(Parcel parcel) {
                    return new LastBean(parcel);
                }

                @Override // android.os.Parcelable.Creator
                /* renamed from: a, reason: merged with bridge method [inline-methods] */
                public LastBean[] newArray(int i) {
                    return new LastBean[i];
                }
            };
            private DaysBeanX days;
            private MeanBeanX mean;

            @Override // android.os.Parcelable
            public int describeContents() {
                return 0;
            }

            public LastBean() {
            }

            protected LastBean(Parcel parcel) {
                this.days = (DaysBeanX) parcel.readParcelable(DaysBeanX.class.getClassLoader());
                this.mean = (MeanBeanX) parcel.readParcelable(MeanBeanX.class.getClassLoader());
            }

            public DaysBeanX getDays() {
                return (DaysBeanX) sdn.c(this.days);
            }

            public MeanBeanX getMean() {
                return (MeanBeanX) sdn.c(this.mean);
            }

            @Override // android.os.Parcelable
            public void writeToParcel(Parcel parcel, int i) {
                parcel.writeParcelable(this.days, i);
                parcel.writeParcelable(this.mean, i);
            }

            public static class DaysBeanX implements Parcelable {
                public static final Parcelable.Creator<DaysBeanX> CREATOR = new Parcelable.Creator<DaysBeanX>() { // from class: com.huawei.ui.main.stories.fitness.views.coresleep.CoreSleepTotalData.ResultInfoArrBean.LastBean.DaysBeanX.2
                    @Override // android.os.Parcelable.Creator
                    /* renamed from: dwU_, reason: merged with bridge method [inline-methods] */
                    public DaysBeanX createFromParcel(Parcel parcel) {
                        return new DaysBeanX(parcel);
                    }

                    @Override // android.os.Parcelable.Creator
                    /* renamed from: d, reason: merged with bridge method [inline-methods] */
                    public DaysBeanX[] newArray(int i) {
                        return new DaysBeanX[i];
                    }
                };
                private int earlyWakeUp;
                private int easyAwake;
                private int irregularWakeUp;
                private int lackSleep;
                private int lateFallSleep;
                private int lowBreathQuality;
                private int lowDeepSleep;
                private int lowRemSleep;
                private int overRemSleep;
                private int overSleep;
                private int upperScore;

                @Override // android.os.Parcelable
                public int describeContents() {
                    return 0;
                }

                public DaysBeanX() {
                }

                protected DaysBeanX(Parcel parcel) {
                    this.upperScore = parcel.readInt();
                    this.overSleep = parcel.readInt();
                    this.lackSleep = parcel.readInt();
                    this.irregularWakeUp = parcel.readInt();
                    this.lateFallSleep = parcel.readInt();
                    this.lowDeepSleep = parcel.readInt();
                    this.lowBreathQuality = parcel.readInt();
                    this.overRemSleep = parcel.readInt();
                    this.lowRemSleep = parcel.readInt();
                    this.earlyWakeUp = parcel.readInt();
                    this.easyAwake = parcel.readInt();
                }

                public int getUpperScore() {
                    return ((Integer) sdn.c(Integer.valueOf(this.upperScore))).intValue();
                }

                public int getLackSleep() {
                    return ((Integer) sdn.c(Integer.valueOf(this.lackSleep))).intValue();
                }

                public int getLowDeepSleep() {
                    return ((Integer) sdn.c(Integer.valueOf(this.lowDeepSleep))).intValue();
                }

                public int getLowBreathQuality() {
                    return ((Integer) sdn.c(Integer.valueOf(this.lowBreathQuality))).intValue();
                }

                public int getOverREMSleep() {
                    return ((Integer) sdn.c(Integer.valueOf(this.overRemSleep))).intValue();
                }

                public int getEarlyWakeUp() {
                    return ((Integer) sdn.c(Integer.valueOf(this.earlyWakeUp))).intValue();
                }

                public int getEasyAwake() {
                    return ((Integer) sdn.c(Integer.valueOf(this.easyAwake))).intValue();
                }

                @Override // android.os.Parcelable
                public void writeToParcel(Parcel parcel, int i) {
                    parcel.writeInt(this.upperScore);
                    parcel.writeInt(this.overSleep);
                    parcel.writeInt(this.lackSleep);
                    parcel.writeInt(this.irregularWakeUp);
                    parcel.writeInt(this.lateFallSleep);
                    parcel.writeInt(this.lowDeepSleep);
                    parcel.writeInt(this.lowBreathQuality);
                    parcel.writeInt(this.overRemSleep);
                    parcel.writeInt(this.lowRemSleep);
                    parcel.writeInt(this.earlyWakeUp);
                    parcel.writeInt(this.easyAwake);
                }
            }

            public static class MeanBeanX implements Parcelable {
                public static final Parcelable.Creator<MeanBeanX> CREATOR = new Parcelable.Creator<MeanBeanX>() { // from class: com.huawei.ui.main.stories.fitness.views.coresleep.CoreSleepTotalData.ResultInfoArrBean.LastBean.MeanBeanX.1
                    @Override // android.os.Parcelable.Creator
                    /* renamed from: dwV_, reason: merged with bridge method [inline-methods] */
                    public MeanBeanX createFromParcel(Parcel parcel) {
                        return new MeanBeanX(parcel);
                    }

                    @Override // android.os.Parcelable.Creator
                    /* renamed from: d, reason: merged with bridge method [inline-methods] */
                    public MeanBeanX[] newArray(int i) {
                        return new MeanBeanX[i];
                    }
                };
                private int allSleepTime;
                private int awakeCnt;
                private int breathQuality;
                private int deepSleepScale;
                private int fallSleepTime;
                private int remScale;
                private int score;
                private int wakeUpTime;

                @Override // android.os.Parcelable
                public int describeContents() {
                    return 0;
                }

                public MeanBeanX() {
                }

                protected MeanBeanX(Parcel parcel) {
                    this.score = parcel.readInt();
                    this.wakeUpTime = parcel.readInt();
                    this.allSleepTime = parcel.readInt();
                    this.deepSleepScale = parcel.readInt();
                    this.remScale = parcel.readInt();
                    this.breathQuality = parcel.readInt();
                    this.fallSleepTime = parcel.readInt();
                    this.awakeCnt = parcel.readInt();
                }

                public int getScore() {
                    return ((Integer) sdn.c(Integer.valueOf(this.score))).intValue();
                }

                public int getWakeUpTime() {
                    LogUtil.c(CoreSleepTotalData.TAG, "MeanBeanX wakeUpTime: ", Integer.valueOf(this.wakeUpTime));
                    return ((Integer) sdn.c(Integer.valueOf(this.wakeUpTime))).intValue();
                }

                public int getAllSleepTime() {
                    LogUtil.c(CoreSleepTotalData.TAG, "MeanBeanX allSleepTime: ", Integer.valueOf(this.allSleepTime));
                    return ((Integer) sdn.c(Integer.valueOf(this.allSleepTime))).intValue();
                }

                public int getDeepSleepScale() {
                    LogUtil.c(CoreSleepTotalData.TAG, "MeanBeanX deepSleepScale: ", Integer.valueOf(this.deepSleepScale));
                    return ((Integer) sdn.c(Integer.valueOf(this.deepSleepScale))).intValue();
                }

                public int getBreathQuality() {
                    LogUtil.c(CoreSleepTotalData.TAG, "MeanBeanX breathQuality: ", Integer.valueOf(this.breathQuality));
                    return ((Integer) sdn.c(Integer.valueOf(this.breathQuality))).intValue();
                }

                @Override // android.os.Parcelable
                public void writeToParcel(Parcel parcel, int i) {
                    parcel.writeInt(this.score);
                    parcel.writeInt(this.wakeUpTime);
                    parcel.writeInt(this.allSleepTime);
                    parcel.writeInt(this.deepSleepScale);
                    parcel.writeInt(this.remScale);
                    parcel.writeInt(this.breathQuality);
                    parcel.writeInt(this.fallSleepTime);
                    parcel.writeInt(this.awakeCnt);
                }
            }
        }
    }
}
