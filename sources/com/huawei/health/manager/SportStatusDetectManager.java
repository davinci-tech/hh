package com.huawei.health.manager;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.amap.api.services.core.AMapException;
import com.huawei.health.manager.util.AlarmHelper;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;

/* loaded from: classes.dex */
public class SportStatusDetectManager {
    private final TriggerSportInterface b;

    /* renamed from: a, reason: collision with root package name */
    private AutoRecognizeStatus f2781a = AutoRecognizeStatus.IDLE;
    private final AlarmHelper e = new AlarmHelper();

    public SportStatusDetectManager(TriggerSportInterface triggerSportInterface) {
        this.b = triggerSportInterface;
    }

    public void c(AutoRecognizeStatus autoRecognizeStatus) {
        ReleaseLogUtil.e("Track_SportStatusDetectManager", "current status is ", autoRecognizeStatus);
        if (this.f2781a.getStatus() == autoRecognizeStatus.getStatus()) {
            ReleaseLogUtil.c("Track_SportStatusDetectManager", "status is same,not need update");
        } else {
            this.f2781a = this.f2781a.detect(autoRecognizeStatus, this.b, this.e);
        }
    }

    /* JADX WARN: Enum visitor error
    jadx.core.utils.exceptions.JadxRuntimeException: Init of enum field 'RUN' uses external variables
    	at jadx.core.dex.visitors.EnumVisitor.createEnumFieldByConstructor(EnumVisitor.java:451)
    	at jadx.core.dex.visitors.EnumVisitor.processEnumFieldByRegister(EnumVisitor.java:395)
    	at jadx.core.dex.visitors.EnumVisitor.extractEnumFieldsFromFilledArray(EnumVisitor.java:324)
    	at jadx.core.dex.visitors.EnumVisitor.extractEnumFieldsFromInsn(EnumVisitor.java:262)
    	at jadx.core.dex.visitors.EnumVisitor.convertToEnum(EnumVisitor.java:151)
    	at jadx.core.dex.visitors.EnumVisitor.visit(EnumVisitor.java:100)
     */
    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    public static class AutoRecognizeStatus implements IDetect {
        private static final /* synthetic */ AutoRecognizeStatus[] $VALUES;
        public static final AutoRecognizeStatus END_CALCULATE;
        public static final AutoRecognizeStatus FAULT_TOLERANT;
        public static final AutoRecognizeStatus HANG_START;
        public static final AutoRecognizeStatus IDLE;
        public static final AutoRecognizeStatus PRE_PAUSE;
        public static final AutoRecognizeStatus PRE_RUN;
        public static final AutoRecognizeStatus PRE_STOP;
        public static final AutoRecognizeStatus PRE_WALK;
        public static final AutoRecognizeStatus RECOGNIZE_RUN;
        public static final AutoRecognizeStatus RECOGNIZE_WALK;
        public static final AutoRecognizeStatus RUN;
        public static final AutoRecognizeStatus WALK;
        private final int mStatus;

        public static AutoRecognizeStatus valueOf(String str) {
            return (AutoRecognizeStatus) Enum.valueOf(AutoRecognizeStatus.class, str);
        }

        public static AutoRecognizeStatus[] values() {
            return (AutoRecognizeStatus[]) $VALUES.clone();
        }

        static {
            int i = 0;
            AutoRecognizeStatus autoRecognizeStatus = new AutoRecognizeStatus("RUN", i, AMapException.CODE_AMAP_CLIENT_IO_EXCEPTION) { // from class: com.huawei.health.manager.SportStatusDetectManager.AutoRecognizeStatus.1
                @Override // com.huawei.health.manager.SportStatusDetectManager.IDetect
                public AutoRecognizeStatus demote(TriggerSportInterface triggerSportInterface, AlarmHelper alarmHelper) {
                    ReleaseLogUtil.e("Track_SportStatusDetectManager", "run status upgrade enter pre stop ");
                    alarmHelper.b();
                    return stopPre(alarmHelper);
                }
            };
            RUN = autoRecognizeStatus;
            int i2 = 1;
            AutoRecognizeStatus autoRecognizeStatus2 = new AutoRecognizeStatus("WALK", i2, AMapException.CODE_AMAP_CLIENT_INVALID_PARAMETER) { // from class: com.huawei.health.manager.SportStatusDetectManager.AutoRecognizeStatus.2
                @Override // com.huawei.health.manager.SportStatusDetectManager.IDetect
                public AutoRecognizeStatus demote(TriggerSportInterface triggerSportInterface, AlarmHelper alarmHelper) {
                    ReleaseLogUtil.e("Track_SportStatusDetectManager", "walk status upgrade enter pre stop ");
                    alarmHelper.b();
                    return stopPre(alarmHelper);
                }
            };
            WALK = autoRecognizeStatus2;
            int i3 = 2;
            AutoRecognizeStatus autoRecognizeStatus3 = new AutoRecognizeStatus("RECOGNIZE_RUN", i3, TypedValues.PositionType.TYPE_DRAWPATH) { // from class: com.huawei.health.manager.SportStatusDetectManager.AutoRecognizeStatus.3
                @Override // com.huawei.health.manager.SportStatusDetectManager.IDetect
                public AutoRecognizeStatus upgrade(AutoRecognizeStatus autoRecognizeStatus4, TriggerSportInterface triggerSportInterface, AlarmHelper alarmHelper) {
                    ReleaseLogUtil.e("Track_SportStatusDetectManager", "recognize run start to pre sport ", Integer.valueOf(autoRecognizeStatus4.getStatus()));
                    alarmHelper.q();
                    alarmHelper.e();
                    alarmHelper.c();
                    if (autoRecognizeStatus4 == WALK) {
                        alarmHelper.h();
                        return PRE_WALK;
                    }
                    if (autoRecognizeStatus4 == END_CALCULATE) {
                        alarmHelper.g();
                        return alarmHelper.b(PRE_RUN);
                    }
                    alarmHelper.g();
                    return PRE_RUN;
                }

                @Override // com.huawei.health.manager.SportStatusDetectManager.IDetect
                public AutoRecognizeStatus demote(TriggerSportInterface triggerSportInterface, AlarmHelper alarmHelper) {
                    ReleaseLogUtil.e("Track_SportStatusDetectManager", "recognize run cancel to idle");
                    alarmHelper.q();
                    alarmHelper.b();
                    alarmHelper.o();
                    return HANG_START;
                }

                @Override // com.huawei.health.manager.SportStatusDetectManager.IDetect
                public AutoRecognizeStatus trans(AlarmHelper alarmHelper) {
                    ReleaseLogUtil.e("Track_SportStatusDetectManager", "recognize run trans to recognize walk");
                    alarmHelper.b();
                    alarmHelper.q();
                    return walkPre(alarmHelper);
                }

                @Override // com.huawei.health.manager.SportStatusDetectManager.IDetect
                public AutoRecognizeStatus detect(AutoRecognizeStatus autoRecognizeStatus4, TriggerSportInterface triggerSportInterface, AlarmHelper alarmHelper) {
                    if (autoRecognizeStatus4.getStatus() == RUN.getStatus() || autoRecognizeStatus4.getStatus() == END_CALCULATE.getStatus()) {
                        ReleaseLogUtil.e("Track_SportStatusDetectManager", "recognize run detect to recognize run");
                        return upgrade(autoRecognizeStatus4, triggerSportInterface, alarmHelper);
                    }
                    if (autoRecognizeStatus4.getStatus() == WALK.getStatus()) {
                        ReleaseLogUtil.e("Track_SportStatusDetectManager", "recognize run detect to recognize walk");
                        return trans(alarmHelper);
                    }
                    ReleaseLogUtil.e("Track_SportStatusDetectManager", "recognize run detect to recognize idle");
                    return demote(triggerSportInterface, alarmHelper);
                }
            };
            RECOGNIZE_RUN = autoRecognizeStatus3;
            AutoRecognizeStatus autoRecognizeStatus4 = new AutoRecognizeStatus("RECOGNIZE_WALK", 3, 501) { // from class: com.huawei.health.manager.SportStatusDetectManager.AutoRecognizeStatus.4
                @Override // com.huawei.health.manager.SportStatusDetectManager.IDetect
                public AutoRecognizeStatus upgrade(AutoRecognizeStatus autoRecognizeStatus5, TriggerSportInterface triggerSportInterface, AlarmHelper alarmHelper) {
                    ReleaseLogUtil.e("Track_SportStatusDetectManager", "recognize walk start to pre sport ", Integer.valueOf(autoRecognizeStatus5.getStatus()));
                    alarmHelper.r();
                    alarmHelper.e();
                    alarmHelper.c();
                    if (autoRecognizeStatus5.getStatus() == WALK.getStatus()) {
                        alarmHelper.h();
                        return PRE_WALK;
                    }
                    if (autoRecognizeStatus5.getStatus() == END_CALCULATE.getStatus()) {
                        alarmHelper.h();
                        return alarmHelper.b(PRE_WALK);
                    }
                    alarmHelper.g();
                    return PRE_RUN;
                }

                @Override // com.huawei.health.manager.SportStatusDetectManager.IDetect
                public AutoRecognizeStatus demote(TriggerSportInterface triggerSportInterface, AlarmHelper alarmHelper) {
                    ReleaseLogUtil.e("Track_SportStatusDetectManager", "recognize walk cancel to idle");
                    alarmHelper.b();
                    alarmHelper.r();
                    alarmHelper.o();
                    return HANG_START;
                }

                @Override // com.huawei.health.manager.SportStatusDetectManager.IDetect
                public AutoRecognizeStatus trans(AlarmHelper alarmHelper) {
                    ReleaseLogUtil.e("Track_SportStatusDetectManager", "recognize walk trans to recognize run");
                    alarmHelper.b();
                    alarmHelper.r();
                    return runPre(alarmHelper);
                }

                @Override // com.huawei.health.manager.SportStatusDetectManager.IDetect
                public AutoRecognizeStatus detect(AutoRecognizeStatus autoRecognizeStatus5, TriggerSportInterface triggerSportInterface, AlarmHelper alarmHelper) {
                    if (autoRecognizeStatus5.getStatus() == WALK.getStatus() || autoRecognizeStatus5.getStatus() == END_CALCULATE.getStatus()) {
                        ReleaseLogUtil.e("Track_SportStatusDetectManager", "recognize walk detect to recognize walk");
                        return upgrade(autoRecognizeStatus5, triggerSportInterface, alarmHelper);
                    }
                    if (autoRecognizeStatus5.getStatus() == RUN.getStatus()) {
                        ReleaseLogUtil.e("Track_SportStatusDetectManager", "recognize walk detect to recognize run");
                        return trans(alarmHelper);
                    }
                    ReleaseLogUtil.e("Track_SportStatusDetectManager", "recognize walk detect to recognize idle");
                    return demote(triggerSportInterface, alarmHelper);
                }
            };
            RECOGNIZE_WALK = autoRecognizeStatus4;
            AutoRecognizeStatus autoRecognizeStatus5 = new AutoRecognizeStatus("HANG_START", 4, i3) { // from class: com.huawei.health.manager.SportStatusDetectManager.AutoRecognizeStatus.5
                @Override // com.huawei.health.manager.SportStatusDetectManager.IDetect
                public AutoRecognizeStatus upgrade(AutoRecognizeStatus autoRecognizeStatus6, TriggerSportInterface triggerSportInterface, AlarmHelper alarmHelper) {
                    alarmHelper.b();
                    alarmHelper.s();
                    if (autoRecognizeStatus6 == WALK) {
                        alarmHelper.l();
                        ReleaseLogUtil.e("Track_SportStatusDetectManager", "HANG_START continue walk");
                        return RECOGNIZE_WALK;
                    }
                    alarmHelper.n();
                    ReleaseLogUtil.e("Track_SportStatusDetectManager", "HANG_START continue running");
                    return RECOGNIZE_RUN;
                }

                @Override // com.huawei.health.manager.SportStatusDetectManager.IDetect
                public AutoRecognizeStatus demote(TriggerSportInterface triggerSportInterface, AlarmHelper alarmHelper) {
                    ReleaseLogUtil.e("Track_SportStatusDetectManager", "hang start status detected demote to stop");
                    alarmHelper.b();
                    alarmHelper.e();
                    alarmHelper.d();
                    return IDLE;
                }

                @Override // com.huawei.health.manager.SportStatusDetectManager.IDetect
                public AutoRecognizeStatus detect(AutoRecognizeStatus autoRecognizeStatus6, TriggerSportInterface triggerSportInterface, AlarmHelper alarmHelper) {
                    ReleaseLogUtil.e("Track_SportStatusDetectManager", "hang start status detected demote ", Integer.valueOf(autoRecognizeStatus6.getStatus()));
                    if (autoRecognizeStatus6 == IDLE) {
                        return demote(triggerSportInterface, alarmHelper);
                    }
                    return upgrade(autoRecognizeStatus6, triggerSportInterface, alarmHelper);
                }
            };
            HANG_START = autoRecognizeStatus5;
            AutoRecognizeStatus autoRecognizeStatus6 = new AutoRecognizeStatus("PRE_STOP", 5, i2) { // from class: com.huawei.health.manager.SportStatusDetectManager.AutoRecognizeStatus.6
                @Override // com.huawei.health.manager.SportStatusDetectManager.IDetect
                public AutoRecognizeStatus upgrade(AutoRecognizeStatus autoRecognizeStatus7, TriggerSportInterface triggerSportInterface, AlarmHelper alarmHelper) {
                    alarmHelper.b();
                    alarmHelper.e();
                    alarmHelper.p();
                    ReleaseLogUtil.e("Track_SportStatusDetectManager", "pre stop status upgrade to stop", autoRecognizeStatus7);
                    return autoRecognizeStatus7;
                }

                @Override // com.huawei.health.manager.SportStatusDetectManager.IDetect
                public AutoRecognizeStatus demote(TriggerSportInterface triggerSportInterface, AlarmHelper alarmHelper) {
                    ReleaseLogUtil.e("Track_SportStatusDetectManager", "pre stop status detected demote to stop");
                    alarmHelper.d();
                    alarmHelper.b();
                    triggerSportInterface.endSport();
                    return IDLE;
                }

                @Override // com.huawei.health.manager.SportStatusDetectManager.IDetect
                public AutoRecognizeStatus detect(AutoRecognizeStatus autoRecognizeStatus7, TriggerSportInterface triggerSportInterface, AlarmHelper alarmHelper) {
                    ReleaseLogUtil.e("Track_SportStatusDetectManager", "pre stop status detected ", Integer.valueOf(autoRecognizeStatus7.getStatus()));
                    if (autoRecognizeStatus7 == AutoRecognizeStatus.IDLE) {
                        return demote(triggerSportInterface, alarmHelper);
                    }
                    return upgrade(autoRecognizeStatus7, triggerSportInterface, alarmHelper);
                }
            };
            PRE_STOP = autoRecognizeStatus6;
            AutoRecognizeStatus autoRecognizeStatus7 = new AutoRecognizeStatus("IDLE", 6, i) { // from class: com.huawei.health.manager.SportStatusDetectManager.AutoRecognizeStatus.7
                @Override // com.huawei.health.manager.SportStatusDetectManager.IDetect
                public AutoRecognizeStatus upgrade(AutoRecognizeStatus autoRecognizeStatus8, TriggerSportInterface triggerSportInterface, AlarmHelper alarmHelper) {
                    if (autoRecognizeStatus8 == PRE_STOP) {
                        ReleaseLogUtil.e("Track_SportStatusDetectManager", "invalid status,continue idle ");
                        alarmHelper.d();
                        alarmHelper.b();
                        return IDLE;
                    }
                    if (autoRecognizeStatus8.getStatus() == WALK.getStatus()) {
                        ReleaseLogUtil.e("Track_SportStatusDetectManager", "idle pre to walk");
                        return walkPre(alarmHelper);
                    }
                    ReleaseLogUtil.e("Track_SportStatusDetectManager", "idle pre to run");
                    return runPre(alarmHelper);
                }
            };
            IDLE = autoRecognizeStatus7;
            AutoRecognizeStatus autoRecognizeStatus8 = new AutoRecognizeStatus("FAULT_TOLERANT", 7, 1701) { // from class: com.huawei.health.manager.SportStatusDetectManager.AutoRecognizeStatus.8
                @Override // com.huawei.health.manager.SportStatusDetectManager.IDetect
                public AutoRecognizeStatus upgrade(AutoRecognizeStatus autoRecognizeStatus9, TriggerSportInterface triggerSportInterface, AlarmHelper alarmHelper) {
                    alarmHelper.b();
                    return alarmHelper.a(autoRecognizeStatus9, triggerSportInterface);
                }

                @Override // com.huawei.health.manager.SportStatusDetectManager.IDetect
                public AutoRecognizeStatus demote(TriggerSportInterface triggerSportInterface, AlarmHelper alarmHelper) {
                    ReleaseLogUtil.e("Track_SportStatusDetectManager", "fault tolerant status detected demote to stop");
                    alarmHelper.b();
                    alarmHelper.e();
                    alarmHelper.d();
                    return IDLE;
                }
            };
            FAULT_TOLERANT = autoRecognizeStatus8;
            AutoRecognizeStatus autoRecognizeStatus9 = new AutoRecognizeStatus("PRE_WALK", 8, 1501) { // from class: com.huawei.health.manager.SportStatusDetectManager.AutoRecognizeStatus.9
                @Override // com.huawei.health.manager.SportStatusDetectManager.IDetect
                public AutoRecognizeStatus upgrade(AutoRecognizeStatus autoRecognizeStatus10, TriggerSportInterface triggerSportInterface, AlarmHelper alarmHelper) {
                    ReleaseLogUtil.e("Track_SportStatusDetectManager", "pre walk status detected start to walk or running ", Integer.valueOf(autoRecognizeStatus10.getStatus()));
                    alarmHelper.d();
                    if (autoRecognizeStatus10.getStatus() == WALK.getStatus()) {
                        alarmHelper.r();
                        return alarmHelper.a(autoRecognizeStatus10, triggerSportInterface);
                    }
                    alarmHelper.b();
                    return FAULT_TOLERANT;
                }

                @Override // com.huawei.health.manager.SportStatusDetectManager.IDetect
                public AutoRecognizeStatus demote(TriggerSportInterface triggerSportInterface, AlarmHelper alarmHelper) {
                    ReleaseLogUtil.e("Track_SportStatusDetectManager", "pre walk demote now is to cancel");
                    alarmHelper.f();
                    alarmHelper.m();
                    return PRE_PAUSE;
                }

                @Override // com.huawei.health.manager.SportStatusDetectManager.IDetect
                public AutoRecognizeStatus detect(AutoRecognizeStatus autoRecognizeStatus10, TriggerSportInterface triggerSportInterface, AlarmHelper alarmHelper) {
                    ReleaseLogUtil.e("Track_SportStatusDetectManager", "pre walk status detected  ", Integer.valueOf(autoRecognizeStatus10.getStatus()));
                    if (autoRecognizeStatus10.getStatus() == WALK.getStatus()) {
                        return upgrade(autoRecognizeStatus10, triggerSportInterface, alarmHelper);
                    }
                    if (autoRecognizeStatus10.getStatus() == RUN.getStatus()) {
                        return trans(alarmHelper);
                    }
                    return demote(triggerSportInterface, alarmHelper);
                }

                @Override // com.huawei.health.manager.SportStatusDetectManager.IDetect
                public AutoRecognizeStatus trans(AlarmHelper alarmHelper) {
                    ReleaseLogUtil.e("Track_SportStatusDetectManager", "pre walk trans to pre run");
                    alarmHelper.b();
                    alarmHelper.r();
                    alarmHelper.g();
                    return PRE_RUN;
                }
            };
            PRE_WALK = autoRecognizeStatus9;
            AutoRecognizeStatus autoRecognizeStatus10 = new AutoRecognizeStatus("PRE_RUN", 9, 1502) { // from class: com.huawei.health.manager.SportStatusDetectManager.AutoRecognizeStatus.10
                @Override // com.huawei.health.manager.SportStatusDetectManager.IDetect
                public AutoRecognizeStatus upgrade(AutoRecognizeStatus autoRecognizeStatus11, TriggerSportInterface triggerSportInterface, AlarmHelper alarmHelper) {
                    ReleaseLogUtil.e("Track_SportStatusDetectManager", "pre run status detected start to walk or running ");
                    alarmHelper.d();
                    if (autoRecognizeStatus11.getStatus() == RUN.getStatus()) {
                        alarmHelper.q();
                        return alarmHelper.a(autoRecognizeStatus11, triggerSportInterface);
                    }
                    alarmHelper.b();
                    return FAULT_TOLERANT;
                }

                @Override // com.huawei.health.manager.SportStatusDetectManager.IDetect
                public AutoRecognizeStatus demote(TriggerSportInterface triggerSportInterface, AlarmHelper alarmHelper) {
                    ReleaseLogUtil.e("Track_SportStatusDetectManager", "pre run demote now is to cancel");
                    alarmHelper.f();
                    alarmHelper.m();
                    return PRE_PAUSE;
                }

                @Override // com.huawei.health.manager.SportStatusDetectManager.IDetect
                public AutoRecognizeStatus detect(AutoRecognizeStatus autoRecognizeStatus11, TriggerSportInterface triggerSportInterface, AlarmHelper alarmHelper) {
                    ReleaseLogUtil.e("Track_SportStatusDetectManager", "pre run status detected  ", Integer.valueOf(autoRecognizeStatus11.getStatus()));
                    if (autoRecognizeStatus11.getStatus() == RUN.getStatus()) {
                        return upgrade(autoRecognizeStatus11, triggerSportInterface, alarmHelper);
                    }
                    if (autoRecognizeStatus11.getStatus() == WALK.getStatus()) {
                        return trans(alarmHelper);
                    }
                    return demote(triggerSportInterface, alarmHelper);
                }

                @Override // com.huawei.health.manager.SportStatusDetectManager.IDetect
                public AutoRecognizeStatus trans(AlarmHelper alarmHelper) {
                    ReleaseLogUtil.e("Track_SportStatusDetectManager", "pre run trans to pre walk");
                    alarmHelper.b();
                    alarmHelper.q();
                    alarmHelper.h();
                    return PRE_WALK;
                }
            };
            PRE_RUN = autoRecognizeStatus10;
            AutoRecognizeStatus autoRecognizeStatus11 = new AutoRecognizeStatus("PRE_PAUSE", 10, 1303) { // from class: com.huawei.health.manager.SportStatusDetectManager.AutoRecognizeStatus.11
                @Override // com.huawei.health.manager.SportStatusDetectManager.IDetect
                public AutoRecognizeStatus demote(TriggerSportInterface triggerSportInterface, AlarmHelper alarmHelper) {
                    ReleaseLogUtil.e("Track_SportStatusDetectManager", "pre pause demote now is to cancel");
                    alarmHelper.b();
                    alarmHelper.e();
                    alarmHelper.d();
                    return IDLE;
                }

                @Override // com.huawei.health.manager.SportStatusDetectManager.IDetect
                public AutoRecognizeStatus upgrade(AutoRecognizeStatus autoRecognizeStatus12, TriggerSportInterface triggerSportInterface, AlarmHelper alarmHelper) {
                    ReleaseLogUtil.e("Track_SportStatusDetectManager", "pre pause status detected start to walk or running ");
                    alarmHelper.k();
                    alarmHelper.a();
                    if (autoRecognizeStatus12.getStatus() == RUN.getStatus()) {
                        return PRE_RUN;
                    }
                    return PRE_WALK;
                }
            };
            PRE_PAUSE = autoRecognizeStatus11;
            AutoRecognizeStatus autoRecognizeStatus12 = new AutoRecognizeStatus("END_CALCULATE", 11, 1603);
            END_CALCULATE = autoRecognizeStatus12;
            $VALUES = new AutoRecognizeStatus[]{autoRecognizeStatus, autoRecognizeStatus2, autoRecognizeStatus3, autoRecognizeStatus4, autoRecognizeStatus5, autoRecognizeStatus6, autoRecognizeStatus7, autoRecognizeStatus8, autoRecognizeStatus9, autoRecognizeStatus10, autoRecognizeStatus11, autoRecognizeStatus12};
        }

        private AutoRecognizeStatus(String str, int i, int i2) {
            this.mStatus = i2;
        }

        @Override // com.huawei.health.manager.SportStatusDetectManager.IDetect
        public int getStatus() {
            return this.mStatus;
        }

        AutoRecognizeStatus walkPre(AlarmHelper alarmHelper) {
            alarmHelper.l();
            return RECOGNIZE_WALK;
        }

        AutoRecognizeStatus runPre(AlarmHelper alarmHelper) {
            alarmHelper.n();
            return RECOGNIZE_RUN;
        }

        AutoRecognizeStatus stopPre(AlarmHelper alarmHelper) {
            alarmHelper.j();
            alarmHelper.i();
            return PRE_STOP;
        }
    }

    public interface IDetect {
        default int getStatus() {
            return 0;
        }

        default AutoRecognizeStatus upgrade(AutoRecognizeStatus autoRecognizeStatus, TriggerSportInterface triggerSportInterface, AlarmHelper alarmHelper) {
            ReleaseLogUtil.e("Track_SportStatusDetectManager", "upgrade default implement");
            return AutoRecognizeStatus.RUN;
        }

        default AutoRecognizeStatus demote(TriggerSportInterface triggerSportInterface, AlarmHelper alarmHelper) {
            ReleaseLogUtil.e("Track_SportStatusDetectManager", "demote default implement");
            return AutoRecognizeStatus.IDLE;
        }

        default AutoRecognizeStatus reset(TriggerSportInterface triggerSportInterface) {
            ReleaseLogUtil.e("Track_SportStatusDetectManager", "reset default implement");
            triggerSportInterface.endSport();
            return AutoRecognizeStatus.IDLE;
        }

        default AutoRecognizeStatus trans(AlarmHelper alarmHelper) {
            ReleaseLogUtil.e("Track_SportStatusDetectManager", "trans default implement");
            return AutoRecognizeStatus.WALK;
        }

        default AutoRecognizeStatus detect(AutoRecognizeStatus autoRecognizeStatus, TriggerSportInterface triggerSportInterface, AlarmHelper alarmHelper) {
            int status = autoRecognizeStatus.getStatus() - getStatus();
            if (status >= 100) {
                return upgrade(autoRecognizeStatus, triggerSportInterface, alarmHelper);
            }
            if (Math.abs(status) < 10) {
                return trans(alarmHelper);
            }
            return demote(triggerSportInterface, alarmHelper);
        }
    }
}
