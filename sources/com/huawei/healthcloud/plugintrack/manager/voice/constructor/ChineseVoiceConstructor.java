package com.huawei.healthcloud.plugintrack.manager.voice.constructor;

import android.text.TextUtils;
import com.huawei.health.R;
import com.huawei.health.userprofilemgr.api.UserProfileMgrApi;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.up.model.UserInfomation;
import defpackage.gso;
import defpackage.gtx;
import defpackage.gwg;
import defpackage.gxi;
import defpackage.gyn;
import defpackage.gzz;
import defpackage.hab;
import defpackage.hac;
import defpackage.had;
import defpackage.koq;
import health.compact.a.CommonUtil;
import health.compact.a.Services;
import java.math.BigDecimal;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes4.dex */
public class ChineseVoiceConstructor implements IVoiceContentConstructor, ISoundResourceConstructor {

    enum SoundData {
        GIRL_START_RIDE(R.raw._2131886280_res_0x7f1200c8, 1),
        GIRL_START_RUN(R.raw._2131886281_res_0x7f1200c9, 2),
        GIRL_START_WALK(R.raw._2131886282_res_0x7f1200ca, 3),
        GIRL_SPORT_RESTART(R.raw._2131886279_res_0x7f1200c7, 4),
        GIRL_YOU_HAVE_SPORT(R.raw._2131886283_res_0x7f1200cb, 5),
        DINGDONG(R.raw._2131886169_res_0x7f120059, 8),
        SPEND_TIME(R.raw._2131886272_res_0x7f1200c0, 9),
        COME_ON(R.raw._2131886273_res_0x7f1200c1, 10),
        SECOND(R.raw._2131886263_res_0x7f1200b7, 11),
        MINUTE(R.raw._2131886264_res_0x7f1200b8, 12),
        HOUR(R.raw._2131886266_res_0x7f1200ba, 13),
        KILOMETER(R.raw._2131886265_res_0x7f1200b9, 14),
        NEAR_BY_ONE_MILE(R.raw._2131886271_res_0x7f1200bf, 15),
        NUMBER_ONE(R.raw._2131886249_res_0x7f1200a9, 16),
        NUMBER_TWO(R.raw._2131886250_res_0x7f1200aa, 17),
        NUMBER_THREE(R.raw._2131886251_res_0x7f1200ab, 18),
        NUMBER_FOUR(R.raw._2131886252_res_0x7f1200ac, 19),
        NUMBER_FIVE(R.raw._2131886253_res_0x7f1200ad, 20),
        NUMBER_SIX(R.raw._2131886254_res_0x7f1200ae, 21),
        NUMBER_SEVEN(R.raw._2131886255_res_0x7f1200af, 22),
        NUMBER_EIGHT(R.raw._2131886256_res_0x7f1200b0, 23),
        NUMBER_NINE(R.raw._2131886257_res_0x7f1200b1, 24),
        NUMBER_TEN(R.raw._2131886258_res_0x7f1200b2, 25),
        GREAT(R.raw._2131886276_res_0x7f1200c4, 26),
        GIRL_SPORT_PAUSE(R.raw._2131886278_res_0x7f1200c6, 27),
        GIRL_SPORT_OVER(R.raw._2131886277_res_0x7f1200c5, 28),
        COMPLETED_GOALS(R.raw._2131886274_res_0x7f1200c2, 29),
        HUNDRED(R.raw._2131886259_res_0x7f1200b3, 30),
        THOUSAND(R.raw._2131886260_res_0x7f1200b4, 31),
        EXERCISE_TO_RELAX(R.raw._2131886275_res_0x7f1200c3, 34),
        TEN_THOUSAND(R.raw._2131886261_res_0x7f1200b5, 35),
        AVERAGE_SPEED(R.raw._2131886270_res_0x7f1200be, 37),
        ZERO(R.raw._2131886262_res_0x7f1200b6, 38),
        GIRL_POINT(R.raw._2131886267_res_0x7f1200bb, 39),
        GIRL_HEART_RATE(R.raw._2131886269_res_0x7f1200bd, 40),
        GIRL_HEART_RATE_UNIT(R.raw._2131886268_res_0x7f1200bc, 41),
        GIRL_NICE(R.raw._2131886219_res_0x7f12008b, 42),
        GIRL_FANTASTIC(R.raw._2131886165_res_0x7f120055, 43),
        GIRL_FINISH_HALF_MARATHON(R.raw._2131886166_res_0x7f120056, 44),
        GIRL_FINISH_MARATHON(R.raw._2131886167_res_0x7f120057, 45),
        HEART_RATE_WARNING(R.raw._2131886187_res_0x7f12006b, 46),
        HEART_RATE_UNIT_CHINESE(R.raw._2131886186_res_0x7f12006a, 47),
        CURRENT_HEART_RATE(R.raw._2131886160_res_0x7f120050, 48),
        YOU_HAVE(R.raw._2131886501_res_0x7f1201a5, 49),
        STRETCH(R.raw._2131886242_res_0x7f1200a2, 50),
        HALF_GOAL_FINISH(R.raw._2131886185_res_0x7f120069, 51),
        COUNT_DOWN_DISTANCE(R.raw._2131886153_res_0x7f120049, 52),
        COME_ON_COME_ON(R.raw._2131886147_res_0x7f120043, 53),
        VERY_NICE(R.raw._2131886292_res_0x7f1200d4, 54),
        PERSEVERE_SUCCESS(R.raw._2131886220_res_0x7f12008c, 55),
        PERSEVERE_YOU_CAN_DO_IT(R.raw._2131886221_res_0x7f12008d, 56),
        GOAL_FINISH(R.raw._2131886183_res_0x7f120067, 57),
        MORE_PERSEVERE(R.raw._2131886215_res_0x7f120087, 58),
        VERY_GOOD(R.raw._2131886291_res_0x7f1200d3, 59),
        END_POINT(R.raw._2131886164_res_0x7f120054, 60),
        METER(R.raw._2131886214_res_0x7f120086, 61),
        EMPTY(R.raw._2131886163_res_0x7f120053, 62),
        INTELLIGENT_RUNNING(R.raw._2131886206_res_0x7f12007e, 63),
        INTELLIGENT_VOICE_7(R.raw._2131886205_res_0x7f12007d, 64),
        INTELLIGENT_VOICE_4(R.raw._2131886202_res_0x7f12007a, 65),
        INTELLIGENT_VOICE_5(R.raw._2131886203_res_0x7f12007b, 66),
        INTELLIGENT_VOICE_6(R.raw._2131886204_res_0x7f12007c, 67),
        INTELLIGENT_VOICE_1(R.raw._2131886199_res_0x7f120077, 68),
        INTELLIGENT_VOICE_0(R.raw._2131886198_res_0x7f120076, 69),
        INTELLIGENT_RUNNING_1(R.raw._2131886207_res_0x7f12007f, 70),
        INTELLIGENT_VOICE_2(R.raw._2131886200_res_0x7f120078, 71),
        INTELLIGENT_VOICE_3(R.raw._2131886201_res_0x7f120079, 72),
        COUNT_DOWN_ONE(R.raw._2131886157_res_0x7f12004d, 73),
        COUNT_DOWN_TWO(R.raw._2131886159_res_0x7f12004f, 74),
        COUNT_DOWN_THREE(R.raw._2131886158_res_0x7f12004e, 75),
        FIVE_HUNDRED_END_POINT(R.raw._2131886168_res_0x7f120058, 76),
        CURRENT_PACE(R.raw._2131886284_res_0x7f1200cc, 77),
        CURRENT_SPEED(R.raw._2131886286_res_0x7f1200ce, 78),
        PER_HOUR(R.raw._2131886287_res_0x7f1200cf, 79),
        CURRENT_PACE_TESTING(R.raw._2131886285_res_0x7f1200cd, 80),
        HEART_RATE_DEVICE_NOT_BIND(R.raw._2131886288_res_0x7f1200d0, 81),
        MEASURING(R.raw._2131886289_res_0x7f1200d1, 82),
        LAST_PACE_TESTING(R.raw._2131886290_res_0x7f1200d2, 83),
        CONSUME(R.raw._2131886152_res_0x7f120048, 84),
        KILO_CALORIE(R.raw._2131886208_res_0x7f120080, 85),
        CURRENT_NOT_ENOUGH(R.raw._2131886161_res_0x7f120051, 86),
        CANNOT_GET_DATA(R.raw._2131886146_res_0x7f120042, 87),
        CONNECT_SUCCESS(R.raw._2131886151_res_0x7f120047, 88),
        START_RUN(R.raw._2131886224_res_0x7f120090, 89),
        START_RUN_1(R.raw._2131886234_res_0x7f12009a, 113),
        POSTURE_ABNORMAL(R.raw._2131886150_res_0x7f120046, 90),
        IMPACT_ALWAYS_OK(R.raw._2131886193_res_0x7f120071, 91),
        IMPACT_DOWN_A_LITTLE(R.raw._2131886194_res_0x7f120072, 92),
        IMPACT_DOWN_TO_OK(R.raw._2131886195_res_0x7f120073, 93),
        IMPACT_TOO_LARGE(R.raw._2131886196_res_0x7f120074, 94),
        IMPACT_TOO_LARGE_B(R.raw._2131886197_res_0x7f120075, 95),
        NEED_WARM_UP_1(R.raw._2131886216_res_0x7f120088, 96),
        NEED_WARM_UP_2(R.raw._2131886217_res_0x7f120089, 97),
        NEED_WARM_UP_3(R.raw._2131886218_res_0x7f12008a, 98),
        STEP_RATE_OK(R.raw._2131886238_res_0x7f12009e, 99),
        STEP_RATE_TOO_FAST(R.raw._2131886239_res_0x7f12009f, 100),
        STEP_RATE_TOO_SLOW(R.raw._2131886240_res_0x7f1200a0, 101),
        WARM_UP_NOT_ENOUGH(R.raw._2131886294_res_0x7f1200d6, 102),
        COMMON_NORMAL_ENCOURAGE_A(R.raw._2131886148_res_0x7f120044, 103),
        COMMON_NORMAL_ENCOURAGE_B(R.raw._2131886149_res_0x7f120045, 104),
        RETRY_LATER(R.raw._2131886227_res_0x7f120093, 105),
        DISTANCE_TOO_SHORT(R.raw._2131886162_res_0x7f120052, 106),
        PRIVACY_SWITCH_OFF(R.raw._2131886225_res_0x7f120091, 107),
        SENSOR_DISTANCE(R.raw._2131886232_res_0x7f120098, 108),
        MANUAL_CALIBRATION(R.raw._2131886209_res_0x7f120081, 109),
        START_WORKOUT(R.raw._2131886237_res_0x7f12009d, 110),
        COUNT_DOWN_FOUR(R.raw._2131886155_res_0x7f12004b, 111),
        COUNT_DOWN_FIVE(R.raw._2131886154_res_0x7f12004a, 112),
        COUNT_DOWN_GO(R.raw._2131886156_res_0x7f12004c, 114),
        START_HIKE(R.raw._2131886236_res_0x7f12009c, 115),
        START_CLIMB_HILL(R.raw._2131886235_res_0x7f12009b, 116);

        private int mIndex;
        private int mNameId;

        SoundData(int i, int i2) {
            this.mNameId = i;
            this.mIndex = i2;
        }

        public int getIndex() {
            return this.mIndex;
        }

        public int getNameId() {
            return this.mNameId;
        }
    }

    @Override // com.huawei.healthcloud.plugintrack.manager.voice.constructor.IVoiceContentConstructor
    public Object constructContent(int i, Object obj) {
        List<Object> c;
        LogUtil.c("Track_ChineseVoiceConstructor", "constructContent() type : ", Integer.valueOf(i));
        if (i == 9) {
            c = c(obj, 9);
        } else if (i == 11) {
            c = c(obj, 11);
        } else {
            if (i == 35) {
                return obj;
            }
            switch (i) {
                case 0:
                    c = new ArrayList<>(1);
                    c.add(Integer.valueOf(SoundData.GIRL_START_RIDE.getIndex()));
                    break;
                case 1:
                    c = new ArrayList<>(1);
                    c.add(Integer.valueOf(SoundData.GIRL_START_RUN.getIndex()));
                    break;
                case 2:
                    c = new ArrayList<>(1);
                    c.add(Integer.valueOf(SoundData.GIRL_START_WALK.getIndex()));
                    break;
                case 3:
                    c = new ArrayList<>(1);
                    c.add(Integer.valueOf(SoundData.GIRL_SPORT_PAUSE.getIndex()));
                    break;
                case 4:
                    c = new ArrayList<>(1);
                    c.add(Integer.valueOf(SoundData.GIRL_SPORT_OVER.getIndex()));
                    break;
                case 5:
                    c = new ArrayList<>(1);
                    c.add(Integer.valueOf(SoundData.GIRL_SPORT_RESTART.getIndex()));
                    break;
                case 6:
                    c = new ArrayList<>(1);
                    c.add(Integer.valueOf(SoundData.COMPLETED_GOALS.getIndex()));
                    break;
                default:
                    c = e(i, obj);
                    break;
            }
        }
        if (c.size() == 0) {
            c.add(Integer.valueOf(SoundData.EMPTY.getIndex()));
        }
        return c;
    }

    private List<Object> e(int i, Object obj) {
        switch (i) {
            case 12:
                ArrayList arrayList = new ArrayList(2);
                arrayList.add(Integer.valueOf(SoundData.GIRL_FANTASTIC.getIndex()));
                arrayList.add(Integer.valueOf(SoundData.GIRL_FINISH_MARATHON.getIndex()));
                if (obj == null) {
                    return arrayList;
                }
                a(arrayList, obj);
                return arrayList;
            case 13:
                ArrayList arrayList2 = new ArrayList(2);
                arrayList2.add(Integer.valueOf(SoundData.GIRL_NICE.getIndex()));
                arrayList2.add(Integer.valueOf(SoundData.GIRL_FINISH_HALF_MARATHON.getIndex()));
                if (obj == null) {
                    return arrayList2;
                }
                a(arrayList2, obj);
                return arrayList2;
            case 14:
                ArrayList arrayList3 = new ArrayList(1);
                arrayList3.add(Integer.valueOf(SoundData.HEART_RATE_WARNING.getIndex()));
                return arrayList3;
            case 15:
                ArrayList arrayList4 = new ArrayList(2);
                arrayList4.add(Integer.valueOf(SoundData.GIRL_SPORT_OVER.getIndex()));
                arrayList4.add(Integer.valueOf(SoundData.EXERCISE_TO_RELAX.getIndex()));
                return arrayList4;
            case 16:
                ArrayList arrayList5 = new ArrayList(2);
                arrayList5.add(Integer.valueOf(SoundData.GIRL_SPORT_OVER.getIndex()));
                arrayList5.add(Integer.valueOf(SoundData.STRETCH.getIndex()));
                return arrayList5;
            case 17:
                ArrayList arrayList6 = new ArrayList(1);
                if (obj == null) {
                    return arrayList6;
                }
                c(arrayList6, obj);
                return arrayList6;
            case 18:
            default:
                return b(i, obj);
            case 19:
                ArrayList arrayList7 = new ArrayList(1);
                arrayList7.add(Integer.valueOf(SoundData.GIRL_NICE.getIndex()));
                arrayList7.add(Integer.valueOf(SoundData.HALF_GOAL_FINISH.getIndex()));
                return arrayList7;
        }
    }

    private List<Object> b(int i, Object obj) {
        switch (i) {
            case 18:
                ArrayList arrayList = new ArrayList(1);
                if (obj == null) {
                    return arrayList;
                }
                e((List<Object>) arrayList);
                return arrayList;
            case 19:
            default:
                return d(i, obj);
            case 20:
                return b(obj);
            case 21:
                ArrayList arrayList2 = new ArrayList(1);
                c((List<Object>) arrayList2);
                return arrayList2;
            case 22:
                ArrayList arrayList3 = new ArrayList(1);
                f(arrayList3);
                return arrayList3;
            case 23:
                return e(obj);
            case 24:
                ArrayList arrayList4 = new ArrayList(1);
                arrayList4.add(Integer.valueOf(SoundData.GIRL_SPORT_OVER.getIndex()));
                arrayList4.add(Integer.valueOf(SoundData.DISTANCE_TOO_SHORT.getIndex()));
                return arrayList4;
            case 25:
                ArrayList arrayList5 = new ArrayList(1);
                arrayList5.add(Integer.valueOf(SoundData.PRIVACY_SWITCH_OFF.getIndex()));
                return arrayList5;
            case 26:
                return e(obj, 26);
            case 27:
                return e(obj, 27);
            case 28:
                return b(obj, 28);
        }
    }

    private List<Object> d(int i, Object obj) {
        switch (i) {
            case 29:
                return b(obj, 29);
            case 30:
                return d(obj);
            case 31:
                return c(obj);
            case 32:
                return a(obj);
            default:
                switch (i) {
                    case 110:
                        ArrayList arrayList = new ArrayList(1);
                        arrayList.add(Integer.valueOf(SoundData.START_WORKOUT.getIndex()));
                        return arrayList;
                    case 111:
                        ArrayList arrayList2 = new ArrayList(1);
                        arrayList2.add(Integer.valueOf(SoundData.START_HIKE.getIndex()));
                        return arrayList2;
                    case 112:
                        ArrayList arrayList3 = new ArrayList(1);
                        arrayList3.add(Integer.valueOf(SoundData.START_CLIMB_HILL.getIndex()));
                        return arrayList3;
                    default:
                        ArrayList arrayList4 = new ArrayList(1);
                        arrayList4.add(Integer.valueOf(SoundData.EMPTY.getIndex()));
                        return arrayList4;
                }
        }
    }

    private static String b(int i) {
        StringBuilder sb = new StringBuilder("N");
        if (i / 100 > 0) {
            sb.append(i);
        } else if (i / 10 > 0) {
            sb.append(0);
            sb.append(i);
        } else {
            sb.append("00");
            sb.append(i);
        }
        return sb.toString();
    }

    private void c(List<Object> list, int i, boolean z) {
        String str = z ? "C007" : "C006";
        int i2 = i / 3600;
        if (i2 > 0) {
            list.add(had.d().d(b(i2)));
            list.add(had.d().d("C011"));
        }
        int i3 = (i % 3600) / 60;
        if (i3 > 0) {
            list.add(had.d().d(b(i3)));
            list.add(had.d().d(str));
        }
        int i4 = i % 60;
        if (i4 > 0) {
            list.add(had.d().d(b(i4)));
            list.add(had.d().d("C008"));
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:31:0x00a3  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x00b3  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void d(java.util.List<java.lang.Object> r9, float r10, java.lang.String r11) {
        /*
            r8 = this;
            r0 = 1120403456(0x42c80000, float:100.0)
            float r1 = r10 * r0
            int r1 = java.lang.Math.round(r1)
            float r1 = (float) r1
            r2 = 1065353216(0x3f800000, float:1.0)
            float r1 = r1 * r2
            float r1 = r1 / r0
            double r3 = (double) r1
            double r3 = java.lang.Math.floor(r3)
            int r0 = (int) r3
            java.lang.String r1 = java.lang.Float.toString(r1)
            r3 = 46
            int r3 = r1.indexOf(r3)
            java.lang.String r4 = "Track_ChineseVoiceConstructor"
            java.lang.String r5 = "configDistanceKilometerVoice "
            r6 = 0
            if (r3 < 0) goto L3b
            int r7 = r3 + 1
            java.lang.String r1 = r1.substring(r7)
            int r7 = java.lang.Integer.parseInt(r1)     // Catch: java.lang.NumberFormatException -> L2f
            goto L3c
        L2f:
            r7 = move-exception
            java.lang.String r7 = r7.getMessage()
            java.lang.Object[] r7 = new java.lang.Object[]{r5, r7}
            com.huawei.hwlogsmodel.LogUtil.b(r4, r7)
        L3b:
            r7 = r6
        L3c:
            if (r3 < 0) goto La1
            if (r7 > 0) goto L41
            goto La1
        L41:
            r3 = 0
            int r3 = (r10 > r3 ? 1 : (r10 == r3 ? 0 : -1))
            if (r3 <= 0) goto L5a
            int r10 = (r10 > r2 ? 1 : (r10 == r2 ? 0 : -1))
            if (r10 >= 0) goto L5a
            had r10 = defpackage.had.d()
            java.lang.String r0 = b(r6)
            java.lang.String r10 = r10.d(r0)
            r9.add(r10)
            goto L5d
        L5a:
            r8.a(r9, r0)
        L5d:
            had r10 = defpackage.had.d()
            java.lang.String r0 = "C010"
            java.lang.String r10 = r10.d(r0)
            r9.add(r10)
            int r10 = r1.length()
            if (r10 == 0) goto Lb6
            int r10 = r1.length()
        L74:
            if (r6 >= r10) goto Lb6
            char r0 = r1.charAt(r6)     // Catch: java.lang.NumberFormatException -> L92
            java.lang.String r0 = java.lang.String.valueOf(r0)     // Catch: java.lang.NumberFormatException -> L92
            int r0 = java.lang.Integer.parseInt(r0)     // Catch: java.lang.NumberFormatException -> L92
            had r2 = defpackage.had.d()     // Catch: java.lang.NumberFormatException -> L92
            java.lang.String r0 = b(r0)     // Catch: java.lang.NumberFormatException -> L92
            java.lang.String r0 = r2.d(r0)     // Catch: java.lang.NumberFormatException -> L92
            r9.add(r0)     // Catch: java.lang.NumberFormatException -> L92
            goto L9e
        L92:
            r0 = move-exception
            java.lang.String r0 = r0.getMessage()
            java.lang.Object[] r0 = new java.lang.Object[]{r5, r0}
            com.huawei.hwlogsmodel.LogUtil.b(r4, r0)
        L9e:
            int r6 = r6 + 1
            goto L74
        La1:
            if (r0 != 0) goto Lb3
            had r10 = defpackage.had.d()
            java.lang.String r0 = b(r6)
            java.lang.String r10 = r10.d(r0)
            r9.add(r10)
            goto Lb6
        Lb3:
            r8.a(r9, r0)
        Lb6:
            had r10 = defpackage.had.d()
            java.lang.String r10 = r10.d(r11)
            r9.add(r10)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.healthcloud.plugintrack.manager.voice.constructor.ChineseVoiceConstructor.d(java.util.List, float, java.lang.String):void");
    }

    private void a(List<Object> list, int i) {
        int[] iArr = new int[5];
        int i2 = 0;
        while (i >= 10) {
            iArr[i2] = i % 10;
            i /= 10;
            i2++;
        }
        iArr[i2] = i;
        String[] strArr = {"C013", "C014", "C015", "C016"};
        if (i2 == 1 && iArr[1] == 1) {
            list.add(had.d().d(strArr[i2 - 1]));
            i2--;
        }
        while (i2 > 0) {
            if (iArr[i2] > 0) {
                list.add(had.d().d(b(iArr[i2])));
                list.add(had.d().d(strArr[i2 - 1]));
                i2--;
            } else if (i2 == 1 && iArr[1] == 0 && iArr[0] != 0) {
                list.add(had.d().d(b(0)));
                i2--;
            } else {
                LogUtil.b("Track_ChineseVoiceConstructor", "wrong branch");
                i2--;
            }
        }
        if (iArr[i2] > 0) {
            list.add(had.d().d(b(iArr[i2])));
        }
    }

    private List<Object> e(Object obj, int i) {
        ArrayList arrayList = new ArrayList(16);
        if (!(obj instanceof gxi)) {
            LogUtil.a("Track_ChineseVoiceConstructor", "constructSmartCoachBeforeSportVoice parameter is invalid.");
            return arrayList;
        }
        gxi gxiVar = (gxi) obj;
        String[] d = gxiVar.d();
        if (d != null && d.length > 0) {
            for (String str : d) {
                arrayList.add(had.d().d(str));
            }
        }
        d(i, arrayList, gxiVar);
        arrayList.add(had.d().d("empty_500"));
        arrayList.add(had.d().d("E208"));
        arrayList.add(had.d().d(b(gxiVar.k())));
        arrayList.add(had.d().d("C006"));
        a((List<Object>) arrayList);
        return arrayList;
    }

    private void d(int i, List<Object> list, gxi gxiVar) {
        if (i == 26) {
            list.add(had.d().d(b((int) gxiVar.i())));
        } else if (i == 27) {
            if (hab.a() == 264) {
                d(list, gxiVar.i(), "C009");
            } else {
                c(list, (int) (gxiVar.i() * 60.0f), true);
            }
        } else {
            LogUtil.h("Track_ChineseVoiceConstructor", "constructSmartCoachBeforeSportVoice wrong branch.");
        }
        list.add(had.d().d("E246"));
        if (i == 26) {
            list.add(had.d().d(b((int) gxiVar.l())));
        } else {
            if (i == 27) {
                if (hab.a() == 264) {
                    d(list, gxiVar.l(), "C009");
                    return;
                } else {
                    c(list, (int) (gxiVar.l() * 60.0f), true);
                    return;
                }
            }
            LogUtil.h("Track_ChineseVoiceConstructor", "constructSmartCoachBeforeSportVoice wrong branch.");
        }
    }

    private List<Object> b(Object obj, int i) {
        ArrayList arrayList = new ArrayList(16);
        if (!(obj instanceof gxi)) {
            LogUtil.a("Track_ChineseVoiceConstructor", "constructSmartCoachHeartRateVoice parameter is invalid.");
            return arrayList;
        }
        gxi gxiVar = (gxi) obj;
        int k = gxiVar.k();
        if (hab.c()) {
            if (k > 0) {
                arrayList.add(had.d().d("K101"));
                arrayList.add(had.d().d(b(k)));
            }
        } else if (k > 0) {
            arrayList.add(had.d().d("L232"));
            c((List<Object>) arrayList, k, true);
        }
        return a(i, arrayList, gxiVar);
    }

    private List<Object> a(int i, List<Object> list, gxi gxiVar) {
        if (i == 29) {
            list.add(had.d().d("L174"));
            list.add(had.d().d("L175"));
            return list;
        }
        String[] d = gxiVar.d();
        if (d != null && d.length > 0) {
            for (String str : d) {
                list.add(had.d().d(str));
            }
        }
        if (hab.c()) {
            int i2 = (int) gxiVar.i();
            int l = (int) gxiVar.l();
            if (i2 <= 0 || l <= 0 || i2 >= l) {
                return new ArrayList(16);
            }
            list.add(had.d().d(b(i2)));
            list.add(had.d().d("E246"));
            list.add(had.d().d(b(l)));
        } else {
            int i3 = (int) (gxiVar.i() * 60.0f);
            int l2 = (int) (gxiVar.l() * 60.0f);
            if (i3 <= 0 || l2 <= 0) {
                return new ArrayList(16);
            }
            c(list, i3, true);
            list.add(had.d().d("E246"));
            c(list, l2, true);
        }
        return list;
    }

    private List<Object> c(Object obj) {
        ArrayList arrayList = new ArrayList(16);
        if (!(obj instanceof gxi)) {
            LogUtil.a("Track_ChineseVoiceConstructor", "constructSmartCoachSpeedVoice parameter is invalid.");
            return arrayList;
        }
        gxi gxiVar = (gxi) obj;
        String[] d = gxiVar.d();
        if (d != null && d.length > 0) {
            for (String str : d) {
                arrayList.add(had.d().d(str));
            }
        }
        arrayList.add(had.d().d("L148"));
        int i = (int) gxiVar.i();
        arrayList.add(had.d().d(b(i)));
        arrayList.add(had.d().d("C009"));
        arrayList.add(had.d().d("E246"));
        int l = (int) gxiVar.l();
        arrayList.add(had.d().d(b(l)));
        arrayList.add(had.d().d("C009"));
        return (i <= 0 || l <= 0 || i >= l) ? new ArrayList(16) : arrayList;
    }

    private List<Object> d(Object obj) {
        int c;
        ArrayList arrayList = new ArrayList(16);
        if (!(obj instanceof gxi)) {
            LogUtil.a("Track_ChineseVoiceConstructor", "constructSmartCoachGoalCompletedVoice parameter is invalid.");
            return arrayList;
        }
        gxi gxiVar = (gxi) obj;
        String[] d = gxiVar.d();
        if (d != null && d.length > 0) {
            arrayList.add(had.d().d(d[0]));
        }
        arrayList.add(had.d().d("L163"));
        d(arrayList, gxiVar.a(), "C012");
        arrayList.add(had.d().d("L231"));
        c((List<Object>) arrayList, ((int) gxiVar.r()) / 1000, false);
        arrayList.add(had.d().d("L230"));
        d(arrayList, gxiVar.x(), "L229");
        if (hab.c() && (c = gxiVar.c()) > 0) {
            arrayList.add(had.d().d("K101"));
            arrayList.add(had.d().d(b(c)));
        }
        if (d != null && d.length > 1) {
            arrayList.add(had.d().d(d[1]));
        }
        return arrayList;
    }

    private List<Object> a(Object obj) {
        ArrayList arrayList = new ArrayList(16);
        if (!(obj instanceof gxi)) {
            LogUtil.a("Track_ChineseVoiceConstructor", "constructSmartCoachAfterSportVoice parameter is invalid.");
            return arrayList;
        }
        gxi gxiVar = (gxi) obj;
        arrayList.add(had.d().d("L151"));
        c((List<Object>) arrayList, ((int) gxiVar.i()) / 1000, false);
        arrayList.add(had.d().d("L150"));
        d(arrayList, gxiVar.l(), "C012");
        String[] d = gxiVar.d();
        if (d != null && d.length > 0) {
            for (String str : d) {
                arrayList.add(had.d().d(str));
            }
        }
        l(arrayList, gxiVar);
        arrayList.add(had.d().d("L161"));
        return arrayList;
    }

    private void l(List<Object> list, gxi gxiVar) {
        int k = gxiVar.k();
        if (k > 0) {
            list.add(had.d().d("L157"));
            list.add(had.d().d(b(k)));
            list.add(had.d().d("C011"));
            if (k >= 36 && k <= 53) {
                list.add(had.d().d("L158"));
                return;
            }
            if (k >= 54 && k <= 96) {
                list.add(had.d().d("L159"));
                list.add(had.d().d(b(k)));
                list.add(had.d().d("C011"));
                list.add(had.d().d("E210"));
                list.add(had.d().d("L160"));
                return;
            }
            LogUtil.h("Track_ChineseVoiceConstructor", "constructSmartCoachAfterSportVoice wrong branch.");
        }
    }

    private List<Object> c(Object obj, int i) {
        if (!(obj instanceof gxi)) {
            LogUtil.a("Track_ChineseVoiceConstructor", "constructDistanceTimeContent parameter is invalid.");
            ArrayList arrayList = new ArrayList(1);
            arrayList.add(Integer.valueOf(SoundData.EMPTY.getIndex()));
            return arrayList;
        }
        gxi gxiVar = (gxi) obj;
        ArrayList arrayList2 = new ArrayList(1);
        e(arrayList2, gxiVar);
        b((List<Object>) arrayList2, gxiVar, i);
        g(arrayList2, gxiVar);
        c((List<Object>) arrayList2, gxiVar);
        j(arrayList2, gxiVar);
        d(arrayList2, gxiVar);
        n(arrayList2, gxiVar);
        h(arrayList2, gxiVar);
        i(arrayList2, gxiVar);
        f(arrayList2, gxiVar);
        o(arrayList2, gxiVar);
        m(arrayList2, gxiVar);
        a((List<Object>) arrayList2, gxiVar);
        arrayList2.add(Integer.valueOf(SoundData.EMPTY.getIndex()));
        LogUtil.a("Track_ChineseVoiceConstructor", arrayList2);
        return arrayList2;
    }

    private void a(List<Object> list, gxi gxiVar) {
        if (gxiVar.ae()) {
            int y = gxiVar.y();
            if (y == 25) {
                list.add(Integer.valueOf(SoundData.COMMON_NORMAL_ENCOURAGE_A.getIndex()));
            } else {
                if (y != 26) {
                    return;
                }
                list.add(Integer.valueOf(SoundData.COMMON_NORMAL_ENCOURAGE_B.getIndex()));
            }
        }
    }

    private void d(List<Object> list, gxi gxiVar) {
        if (gxiVar.af()) {
            list.add(Integer.valueOf(SoundData.CONNECT_SUCCESS.getIndex()));
        }
    }

    private void n(List<Object> list, gxi gxiVar) {
        if (gxiVar.aj()) {
            list.add(Integer.valueOf(SoundData.START_RUN.getIndex()));
        }
    }

    private void h(List<Object> list, gxi gxiVar) {
        if (gxiVar.am()) {
            list.add(Integer.valueOf(SoundData.START_RUN_1.getIndex()));
        }
    }

    private void i(List<Object> list, gxi gxiVar) {
        if (gxiVar.ai()) {
            list.add(Integer.valueOf(SoundData.POSTURE_ABNORMAL.getIndex()));
        }
    }

    private void f(List<Object> list, gxi gxiVar) {
        if (gxiVar.ag()) {
            switch (gxiVar.u()) {
                case 12:
                    list.add(Integer.valueOf(SoundData.IMPACT_ALWAYS_OK.getIndex()));
                    break;
                case 13:
                    list.add(Integer.valueOf(SoundData.IMPACT_TOO_LARGE.getIndex()));
                    break;
                case 14:
                    list.add(Integer.valueOf(SoundData.IMPACT_TOO_LARGE_B.getIndex()));
                    break;
                case 15:
                    list.add(Integer.valueOf(SoundData.IMPACT_DOWN_A_LITTLE.getIndex()));
                    break;
                case 16:
                    list.add(Integer.valueOf(SoundData.IMPACT_DOWN_TO_OK.getIndex()));
                    break;
            }
        }
    }

    private void o(List<Object> list, gxi gxiVar) {
        if (gxiVar.al()) {
            switch (gxiVar.ad()) {
                case 18:
                    list.add(Integer.valueOf(SoundData.STEP_RATE_OK.getIndex()));
                    break;
                case 19:
                    list.add(Integer.valueOf(SoundData.STEP_RATE_TOO_SLOW.getIndex()));
                    break;
                case 20:
                    list.add(Integer.valueOf(SoundData.STEP_RATE_TOO_FAST.getIndex()));
                    break;
            }
        }
    }

    private void m(List<Object> list, gxi gxiVar) {
        if (gxiVar.ak()) {
            int ab = gxiVar.ab();
            if (ab == 22) {
                b(list, gxiVar);
            } else {
                if (ab != 23) {
                    return;
                }
                list.add(Integer.valueOf(SoundData.WARM_UP_NOT_ENOUGH.getIndex()));
            }
        }
    }

    private void b(List<Object> list, gxi gxiVar) {
        list.add(Integer.valueOf(SoundData.NEED_WARM_UP_1.getIndex()));
        b(list, gxiVar.ah());
        list.add(Integer.valueOf(SoundData.NEED_WARM_UP_2.getIndex()));
        b(list, gxiVar.aa());
        list.add(Integer.valueOf(SoundData.NEED_WARM_UP_3.getIndex()));
    }

    private List<Object> b(Object obj) {
        if (!(obj instanceof gxi)) {
            LogUtil.a("Track_ChineseVoiceConstructor", "constructCountDownVoiceContent parameter is invalid");
            ArrayList arrayList = new ArrayList(1);
            arrayList.add(Integer.valueOf(SoundData.EMPTY.getIndex()));
            return arrayList;
        }
        int b = ((gxi) obj).b();
        ArrayList arrayList2 = new ArrayList(1);
        if (b == 0) {
            arrayList2.add(Integer.valueOf(SoundData.COUNT_DOWN_GO.getIndex()));
        } else if (b == 1) {
            arrayList2.add(Integer.valueOf(SoundData.COUNT_DOWN_ONE.getIndex()));
        } else if (b == 2) {
            arrayList2.add(Integer.valueOf(SoundData.COUNT_DOWN_TWO.getIndex()));
        } else if (b == 3) {
            arrayList2.add(Integer.valueOf(SoundData.COUNT_DOWN_THREE.getIndex()));
        } else if (b == 4) {
            arrayList2.add(Integer.valueOf(SoundData.COUNT_DOWN_FOUR.getIndex()));
        } else if (b == 5) {
            arrayList2.add(Integer.valueOf(SoundData.COUNT_DOWN_FIVE.getIndex()));
        }
        return arrayList2;
    }

    private List<Object> e(Object obj) {
        if (!(obj instanceof gxi)) {
            LogUtil.a("Track_ChineseVoiceConstructor", "constructIndoorCalibrationVoiceContent parameter is invalid");
            ArrayList arrayList = new ArrayList(16);
            arrayList.add(Integer.valueOf(SoundData.EMPTY.getIndex()));
            return arrayList;
        }
        float a2 = ((gxi) obj).a();
        ArrayList arrayList2 = new ArrayList(16);
        arrayList2.add(Integer.valueOf(SoundData.SENSOR_DISTANCE.getIndex()));
        a(arrayList2, a2);
        arrayList2.add(Integer.valueOf(SoundData.MANUAL_CALIBRATION.getIndex()));
        return arrayList2;
    }

    private List<Object> a(List<Object> list, Object obj) {
        if (!(obj instanceof gxi)) {
            LogUtil.a("Track_ChineseVoiceConstructor", "constructDurationVoiceContent parameter is invalid");
            ArrayList arrayList = new ArrayList(1);
            arrayList.add(Integer.valueOf(SoundData.EMPTY.getIndex()));
            return arrayList;
        }
        long r = ((gxi) obj).r();
        if (r <= 0) {
            LogUtil.a("Track_ChineseVoiceConstructor", "constructDurationVoiceContent duration is less than 0");
            return list;
        }
        list.add(Integer.valueOf(SoundData.SPEND_TIME.getIndex()));
        b(list, r);
        return list;
    }

    private List<Object> c(List<Object> list, Object obj) {
        if (!(obj instanceof gxi)) {
            LogUtil.a("Track_ChineseVoiceConstructor", "constructCountDownDistanceVoiceContent parameter is invalid");
            return list;
        }
        list.add(Integer.valueOf(SoundData.COUNT_DOWN_DISTANCE.getIndex()));
        gxi gxiVar = (gxi) obj;
        float j = gxiVar.j();
        if (j <= 0.0f) {
            LogUtil.a("Track_ChineseVoiceConstructor", "constructCountDownDistanceVoiceContent duration is less than 0");
            return list;
        }
        b(list, (int) j);
        list.add(Integer.valueOf(SoundData.KILOMETER.getIndex()));
        e(list, gxiVar.b());
        return list;
    }

    private List<Object> e(List<Object> list) {
        list.add(Integer.valueOf(SoundData.FIVE_HUNDRED_END_POINT.getIndex()));
        list.add(Integer.valueOf(SoundData.END_POINT.getIndex()));
        return list;
    }

    private void e(List<Object> list, int i) {
        if (i == 1) {
            list.add(Integer.valueOf(SoundData.VERY_GOOD.getIndex()));
            return;
        }
        if (i == 2) {
            list.add(Integer.valueOf(SoundData.PERSEVERE_SUCCESS.getIndex()));
            return;
        }
        if (i == 3) {
            list.add(Integer.valueOf(SoundData.VERY_NICE.getIndex()));
            return;
        }
        if (i == 60) {
            list.add(Integer.valueOf(SoundData.COME_ON_COME_ON.getIndex()));
            return;
        }
        if (i == 70) {
            list.add(Integer.valueOf(SoundData.PERSEVERE_YOU_CAN_DO_IT.getIndex()));
        } else if (i == 80) {
            list.add(Integer.valueOf(SoundData.GREAT.getIndex()));
        } else {
            if (i != 90) {
                return;
            }
            list.add(Integer.valueOf(SoundData.MORE_PERSEVERE.getIndex()));
        }
    }

    private int c(int i) {
        return SoundData.GIRL_YOU_HAVE_SPORT.getIndex();
    }

    private void b(List<Object> list, long j) {
        LogUtil.c("Track_ChineseVoiceConstructor", "addTimeResource time:", String.valueOf(j));
        long j2 = j / 1000;
        if (e(list, j2)) {
            list.add(Integer.valueOf(SoundData.HOUR.getIndex()));
        }
        if (d(list, j2)) {
            list.add(Integer.valueOf(SoundData.MINUTE.getIndex()));
        }
        if (a(list, j2)) {
            list.add(Integer.valueOf(SoundData.SECOND.getIndex()));
        }
    }

    private void c(List<Object> list, int i) {
        if (i > 50) {
            list.add(Integer.valueOf(SoundData.PERSEVERE_SUCCESS.getIndex()));
        } else {
            list.add(Integer.valueOf(SoundData.COME_ON.getIndex()));
        }
    }

    private boolean e(List<Object> list, long j) {
        boolean z;
        int i = (int) (j / 3600);
        int i2 = (i / 1000) % 10;
        int i3 = (i / 100) % 10;
        int i4 = i / 10;
        if (i2 > 0) {
            i(list, i2);
            list.add(Integer.valueOf(SoundData.THOUSAND.getIndex()));
            z = true;
        } else {
            z = false;
        }
        if (i3 > 0) {
            i(list, i3);
            list.add(Integer.valueOf(SoundData.HUNDRED.getIndex()));
            z = true;
        }
        if (i4 > 0) {
            if (!z && i4 > 1) {
                i(list, i4);
            }
            list.add(Integer.valueOf(SoundData.NUMBER_TEN.getIndex()));
            z = true;
        }
        int i5 = i % 10;
        if (i5 <= 0) {
            return z;
        }
        i(list, i5);
        return true;
    }

    private boolean d(List<Object> list, long j) {
        boolean z;
        int i = (int) ((j / 60) % 60);
        int i2 = i / 10;
        int i3 = i % 10;
        if (i2 > 0) {
            if (i2 > 1) {
                i(list, i2);
            }
            list.add(Integer.valueOf(SoundData.NUMBER_TEN.getIndex()));
            z = true;
        } else {
            z = false;
        }
        if (i3 <= 0) {
            return z;
        }
        i(list, i3);
        return true;
    }

    private boolean a(List<Object> list, long j) {
        boolean z;
        int i = (int) (j % 60);
        int i2 = i / 10;
        int i3 = i % 10;
        if (i2 > 0) {
            if (i2 > 1) {
                i(list, i2);
            }
            list.add(Integer.valueOf(SoundData.NUMBER_TEN.getIndex()));
            z = true;
        } else {
            z = false;
        }
        if (i3 <= 0) {
            return z;
        }
        i(list, i3);
        return true;
    }

    private void b(List<Object> list, int i) {
        int[] iArr = new int[5];
        int i2 = 0;
        while (i >= 10) {
            iArr[i2] = i % 10;
            i /= 10;
            i2++;
        }
        iArr[i2] = i;
        int[] iArr2 = {SoundData.NUMBER_TEN.getIndex(), SoundData.HUNDRED.getIndex(), SoundData.THOUSAND.getIndex(), SoundData.TEN_THOUSAND.getIndex()};
        if (i2 == 1 && iArr[1] == 1) {
            list.add(Integer.valueOf(iArr2[i2 - 1]));
            i2--;
        }
        while (i2 > 0) {
            int i3 = iArr[i2];
            if (i3 > 0) {
                i(list, i3);
                list.add(Integer.valueOf(iArr2[i2 - 1]));
                i2--;
            } else if (i2 == 1 && iArr[1] == 0 && iArr[0] != 0) {
                i(list, 0);
                i2--;
            } else {
                LogUtil.b("Track_ChineseVoiceConstructor", "wrong branch");
                i2--;
            }
        }
        int i4 = iArr[i2];
        if (i4 > 0) {
            i(list, i4);
        }
    }

    private void i(List<Object> list, int i) {
        switch (i) {
            case 0:
                list.add(Integer.valueOf(SoundData.ZERO.getIndex()));
                break;
            case 1:
                list.add(Integer.valueOf(SoundData.NUMBER_ONE.getIndex()));
                break;
            case 2:
                list.add(Integer.valueOf(SoundData.NUMBER_TWO.getIndex()));
                break;
            case 3:
                list.add(Integer.valueOf(SoundData.NUMBER_THREE.getIndex()));
                break;
            case 4:
                list.add(Integer.valueOf(SoundData.NUMBER_FOUR.getIndex()));
                break;
            case 5:
                list.add(Integer.valueOf(SoundData.NUMBER_FIVE.getIndex()));
                break;
            case 6:
                list.add(Integer.valueOf(SoundData.NUMBER_SIX.getIndex()));
                break;
            case 7:
                list.add(Integer.valueOf(SoundData.NUMBER_SEVEN.getIndex()));
                break;
            case 8:
                list.add(Integer.valueOf(SoundData.NUMBER_EIGHT.getIndex()));
                break;
            case 9:
                list.add(Integer.valueOf(SoundData.NUMBER_NINE.getIndex()));
                break;
        }
    }

    @Override // com.huawei.healthcloud.plugintrack.manager.voice.constructor.ISoundResourceConstructor
    public Map<Integer, Integer> getSoundResource() {
        HashMap hashMap = new HashMap(10);
        for (SoundData soundData : SoundData.values()) {
            hashMap.put(Integer.valueOf(soundData.getIndex()), Integer.valueOf(soundData.getNameId()));
        }
        LogUtil.c("Track_ChineseVoiceConstructor", "getSoundResource() map size", Integer.valueOf(hashMap.size()));
        return hashMap;
    }

    private int a(int i) {
        return new SecureRandom().nextInt(i);
    }

    private void f(List<Object> list) {
        if (b()) {
            list.add(Integer.valueOf(SoundData.INTELLIGENT_VOICE_7.getIndex()));
            return;
        }
        switch (a(7)) {
            case 0:
                list.add(Integer.valueOf(SoundData.INTELLIGENT_VOICE_0.getIndex()));
                break;
            case 1:
                list.add(Integer.valueOf(SoundData.INTELLIGENT_VOICE_1.getIndex()));
                break;
            case 2:
                list.add(Integer.valueOf(SoundData.INTELLIGENT_VOICE_2.getIndex()));
                break;
            case 3:
                list.add(Integer.valueOf(SoundData.INTELLIGENT_VOICE_3.getIndex()));
                break;
            case 4:
                list.add(Integer.valueOf(SoundData.INTELLIGENT_VOICE_4.getIndex()));
                break;
            case 5:
                list.add(Integer.valueOf(SoundData.INTELLIGENT_VOICE_5.getIndex()));
                break;
            case 6:
                list.add(Integer.valueOf(SoundData.INTELLIGENT_VOICE_6.getIndex()));
                break;
            default:
                list.add(Integer.valueOf(SoundData.INTELLIGENT_VOICE_0.getIndex()));
                break;
        }
        hab.c(had.d().b(list, getSoundResource()));
    }

    private void c(List<Object> list) {
        if (b()) {
            list.add(Integer.valueOf(SoundData.INTELLIGENT_VOICE_7.getIndex()));
            return;
        }
        switch (a(10)) {
            case 0:
                list.add(Integer.valueOf(SoundData.INTELLIGENT_VOICE_0.getIndex()));
                break;
            case 1:
                list.add(Integer.valueOf(SoundData.INTELLIGENT_VOICE_1.getIndex()));
                break;
            case 2:
                list.add(Integer.valueOf(SoundData.INTELLIGENT_VOICE_2.getIndex()));
                break;
            case 3:
                list.add(Integer.valueOf(SoundData.INTELLIGENT_VOICE_3.getIndex()));
                break;
            case 4:
                list.add(Integer.valueOf(SoundData.INTELLIGENT_VOICE_4.getIndex()));
                break;
            case 5:
                list.add(Integer.valueOf(SoundData.INTELLIGENT_VOICE_5.getIndex()));
                break;
            case 6:
                list.add(Integer.valueOf(SoundData.INTELLIGENT_VOICE_6.getIndex()));
                break;
            case 7:
                list.add(Integer.valueOf(SoundData.INTELLIGENT_RUNNING.getIndex()));
                break;
            default:
                if (a()) {
                    list.add(Integer.valueOf(SoundData.INTELLIGENT_RUNNING_1.getIndex()));
                    break;
                } else {
                    list.add(Integer.valueOf(SoundData.INTELLIGENT_VOICE_0.getIndex()));
                    break;
                }
        }
        hab.c(had.d().b(list, getSoundResource()));
    }

    private boolean b() {
        long currentTimeMillis = System.currentTimeMillis();
        return currentTimeMillis > 1538236800000L && currentTimeMillis < 1538928000000L;
    }

    private boolean a() {
        UserInfomation userInfo = ((UserProfileMgrApi) Services.c("HWUserProfileMgr", UserProfileMgrApi.class)).getUserInfo();
        if (userInfo == null) {
            return false;
        }
        int ageOrDefaultValue = userInfo.getAgeOrDefaultValue();
        return ageOrDefaultValue > 0 && ageOrDefaultValue <= 25 && userInfo.getGenderOrDefaultValue() == 0;
    }

    private void e(List<Object> list, gxi gxiVar) {
        if (gxiVar.p() || gxiVar.s() || ((gxiVar.v() && gxiVar.g() > 0) || ((gxiVar.q() && gxiVar.c() > 0) || gxiVar.w()))) {
            list.add(Integer.valueOf(SoundData.DINGDONG.getIndex()));
        }
    }

    private void b(List<Object> list, gxi gxiVar, int i) {
        boolean p = gxiVar.p();
        boolean s = gxiVar.s();
        int n = gxiVar.n();
        float a2 = gxiVar.a();
        long r = gxiVar.r();
        int x = gxiVar.x();
        if (p || s) {
            list.add(Integer.valueOf(c(n)));
        }
        if (p) {
            if (i == 9) {
                f(list, (int) a2);
            } else {
                a(list, a2);
            }
        }
        if (p && s) {
            list.add(Integer.valueOf(SoundData.SPEND_TIME.getIndex()));
        }
        if (s) {
            b(list, r);
        }
        boolean t = gxiVar.t();
        if ((p && t) || (s && t)) {
            d(list, x);
        }
        if (gxiVar.o() && s && gxiVar.j() > 0.0f) {
            list.add(Integer.valueOf(SoundData.COUNT_DOWN_DISTANCE.getIndex()));
            b(list, (long) gxiVar.j());
            c(list, gxiVar.m());
        } else {
            if (gxiVar.o() && p && gxiVar.j() > 0.0f) {
                float j = gxiVar.j() / 1000.0f;
                list.add(Integer.valueOf(SoundData.COUNT_DOWN_DISTANCE.getIndex()));
                a(list, j);
                c(list, gxiVar.m());
                return;
            }
            LogUtil.c("Track_ChineseVoiceConstructor", "wrong branch");
        }
    }

    private void d(List<Object> list, int i) {
        if (i > 0) {
            list.add(Integer.valueOf(SoundData.CONSUME.getIndex()));
            b(list, i);
            list.add(Integer.valueOf(SoundData.KILO_CALORIE.getIndex()));
        }
    }

    private void f(List<Object> list, int i) {
        if (i > 0) {
            b(list, i);
            list.add(Integer.valueOf(SoundData.KILOMETER.getIndex()));
        }
    }

    private void a(List<Object> list, float f) {
        int floor = (int) Math.floor(f);
        String d = Double.toString(new BigDecimal(f - floor).setScale(2, 4).doubleValue());
        int indexOf = d.indexOf(46);
        int i = 0;
        if (indexOf >= 0) {
            d = d.substring(indexOf + 1);
            try {
                i = Integer.parseInt(d);
            } catch (NumberFormatException e) {
                LogUtil.a("Track_ChineseVoiceConstructor", "broadcastDecimalDistance NumberFormatException", e.getMessage());
            }
        }
        if (indexOf >= 0 && i > 0) {
            if (f > 0.0f && f < 1.0f) {
                list.add(Integer.valueOf(SoundData.ZERO.getIndex()));
            } else {
                b(list, floor);
            }
            list.add(Integer.valueOf(SoundData.GIRL_POINT.getIndex()));
            c(list, d);
        } else if (floor == 0) {
            list.add(Integer.valueOf(SoundData.ZERO.getIndex()));
        } else {
            b(list, floor);
        }
        list.add(Integer.valueOf(SoundData.KILOMETER.getIndex()));
    }

    private void c(List<Object> list, String str) {
        if (str.length() != 0) {
            int length = str.length();
            for (int i = 0; i < length; i++) {
                try {
                    i(list, Integer.parseInt(String.valueOf(str.charAt(i))));
                } catch (NumberFormatException e) {
                    LogUtil.a("Track_ChineseVoiceConstructor", "handlePointString NumberFormatException", e.getMessage());
                }
            }
        }
    }

    private void g(List<Object> list, gxi gxiVar) {
        if (gxiVar.v()) {
            long g = gxiVar.g();
            LogUtil.a("Track_ChineseVoiceConstructor", "broadcastPace lastPace");
            if (g > 0) {
                b(list, gxiVar, g);
            }
        }
    }

    private void b(List<Object> list, gxi gxiVar, long j) {
        if (j <= 1000) {
            if (gxiVar.p() && gxiVar.s() && gxiVar.v() && gxiVar.q()) {
                return;
            }
            j(list);
            return;
        }
        list.add(Integer.valueOf(SoundData.NEAR_BY_ONE_MILE.getIndex()));
        b(list, j);
        if (gxiVar.h()) {
            list.add(Integer.valueOf(SoundData.GREAT.getIndex()));
        } else {
            list.add(Integer.valueOf(SoundData.COME_ON.getIndex()));
        }
    }

    private void j(List<Object> list) {
        list.add(Integer.valueOf(SoundData.CURRENT_NOT_ENOUGH.getIndex()));
        list.add(Integer.valueOf(SoundData.CANNOT_GET_DATA.getIndex()));
    }

    private void j(List<Object> list, gxi gxiVar) {
        if (gxiVar.w()) {
            float e = gxiVar.e();
            int n = gxiVar.n();
            if (e > 0.0f) {
                e(list, gxiVar, e, n);
            } else {
                e(list, gxiVar, n);
            }
        }
    }

    private void e(List<Object> list, gxi gxiVar, int i) {
        LogUtil.a("Track_ChineseVoiceConstructor", "broadcastSpeed : announceCurrentSpeedEqualZero, mSpeed = 0");
        if (gxiVar.p() && gxiVar.s() && gxiVar.w() && gxiVar.q()) {
            return;
        }
        if (i == 264 || i == 257 || i == 258) {
            list.add(Integer.valueOf(SoundData.CURRENT_PACE_TESTING.getIndex()));
            list.add(Integer.valueOf(SoundData.RETRY_LATER.getIndex()));
        } else if (i == 259 || i == 265) {
            list.add(Integer.valueOf(SoundData.CURRENT_SPEED.getIndex()));
            list.add(Integer.valueOf(SoundData.MEASURING.getIndex()));
            list.add(Integer.valueOf(SoundData.RETRY_LATER.getIndex()));
        }
    }

    private void e(List<Object> list, gxi gxiVar, float f, int i) {
        double d;
        LogUtil.a("Track_ChineseVoiceConstructor", "broadcastSpeed : announceCurrentSpeedEqualZero, Speed > 0.0f ");
        if (i == 264 || i == 257 || i == 258) {
            list.add(Integer.valueOf(SoundData.CURRENT_PACE.getIndex()));
            b(list, ((long) (3600.0f / f)) * 1000);
        } else if (i == 259 || i == 265) {
            try {
                d = Double.valueOf(gwg.c(f, 1)).doubleValue();
            } catch (NumberFormatException unused) {
                LogUtil.h("Track_ChineseVoiceConstructor", "numberFormatException");
                d = 0.0d;
            }
            list.add(Integer.valueOf(SoundData.CURRENT_SPEED.getIndex()));
            a(list, (float) d);
            list.add(Integer.valueOf(SoundData.PER_HOUR.getIndex()));
        }
        if (gxiVar.h()) {
            list.add(Integer.valueOf(SoundData.GREAT.getIndex()));
        } else {
            list.add(Integer.valueOf(SoundData.COME_ON.getIndex()));
        }
    }

    private void c(List<Object> list, gxi gxiVar) {
        if (gxiVar.q()) {
            LogUtil.a("Track_ChineseVoiceConstructor", "broadcastHeartRate");
            int c = gxiVar.c();
            if (c > 0) {
                list.add(Integer.valueOf(SoundData.CURRENT_HEART_RATE.getIndex()));
                b(list, c);
                return;
            }
            if (!(gxiVar.p() && gxiVar.s() && ((gxiVar.w() || gxiVar.v()) && gxiVar.q())) && gxiVar.z() <= 1) {
                boolean j = gso.e().j();
                boolean l = gso.e().l();
                if (!j) {
                    list.add(Integer.valueOf(SoundData.HEART_RATE_DEVICE_NOT_BIND.getIndex()));
                } else {
                    if (!l) {
                        list.add(Integer.valueOf(SoundData.HEART_RATE_DEVICE_NOT_BIND.getIndex()));
                        return;
                    }
                    list.add(Integer.valueOf(SoundData.CURRENT_HEART_RATE.getIndex()));
                    list.add(Integer.valueOf(SoundData.MEASURING.getIndex()));
                    list.add(Integer.valueOf(SoundData.RETRY_LATER.getIndex()));
                }
            }
        }
    }

    private void a(List<Object> list) {
        if (!koq.b(list) && gzz.a() && hac.a().g() && gtx.c(BaseApplication.getContext()).al()) {
            for (int i = 0; i < 15; i++) {
                list.add(had.d().d("empty_500"));
            }
            list.add(had.d().d("L233"));
        }
    }

    private static void c(List<String> list, int i, int i2) {
        int i3 = i / 10000;
        if (i3 > 0) {
            list.add(b(i3));
            list.add("C016");
            int i4 = i % 10000;
            if (i4 == 0) {
                return;
            }
            int length = String.valueOf(i4).length();
            if (length < i2 - 1) {
                list.add(b(0));
            }
            c(list, i4, length);
            return;
        }
        int i5 = i / 1000;
        if (i5 > 0) {
            list.add(d(i5));
            list.add("C015");
            int i6 = i % 1000;
            if (i6 == 0) {
                return;
            }
            int length2 = String.valueOf(i6).length();
            if (length2 < i2 - 1) {
                list.add(b(0));
            }
            c(list, i6, length2);
            return;
        }
        int i7 = i / 100;
        if (i7 > 0) {
            list.add(d(i7));
            list.add("C014");
            int i8 = i % 100;
            if (i8 == 0) {
                return;
            }
            int length3 = String.valueOf(i8).length();
            if (length3 < i2 - 1) {
                list.add(b(0));
            }
            c(list, i8, length3);
            return;
        }
        int i9 = i / 10;
        if (i9 > 0) {
            list.add(d(i9));
            list.add("C013");
            int i10 = i % 10;
            if (i10 == 0) {
                return;
            }
            c(list, i10, 1);
            return;
        }
        list.add(d(i));
    }

    private static void b(List<String> list, float f) {
        if (koq.b(list)) {
            return;
        }
        String[] split = String.valueOf(f).split("\\.");
        if (split.length != 2) {
            LogUtil.h("Track_ChineseVoiceConstructor", "numberArray.length error");
            return;
        }
        c(list, CommonUtil.h(split[0]), split[0].length());
        if (CommonUtil.h(split[1]) > 0) {
            list.add("C010");
            b(list, split[1]);
        }
    }

    private static void b(List<String> list, String str) {
        if (koq.b(list) || TextUtils.isEmpty(str)) {
            return;
        }
        int length = str.length();
        if (length > 2) {
            length = 2;
        }
        for (int i = 0; i < length; i++) {
            list.add(d(str.charAt(i) - '0'));
        }
    }

    private static String d(int i) {
        return "N00" + i;
    }

    public static void b(List<String> list, Object obj) {
        if (obj instanceof Integer) {
            if (String.valueOf(obj).length() == 2) {
                g(list, ((Integer) obj).intValue());
                return;
            } else {
                c(list, ((Integer) obj).intValue(), String.valueOf(obj).length());
                return;
            }
        }
        if (obj instanceof Float) {
            b(list, ((Float) obj).floatValue());
        } else {
            LogUtil.a("Track_ChineseVoiceConstructor", "AssembleCommonNumberVoice no match number type");
        }
    }

    private static void g(List<String> list, int i) {
        int i2 = i / 10;
        if (i2 > 1) {
            list.add(d(i2));
            list.add("C013");
            int i3 = i % 10;
            if (i3 != 0) {
                list.add(d(i3));
                return;
            }
            return;
        }
        list.add("C013");
        int i4 = i % 10;
        if (i4 != 0) {
            list.add(d(i4));
        }
    }

    public static String[] b(List<String> list) {
        if (koq.b(list)) {
            return null;
        }
        String[] strArr = new String[list.size()];
        for (int i = 0; i < list.size(); i++) {
            strArr[i] = had.d().d(list.get(i));
        }
        return strArr;
    }

    public static String[] d(List<String> list) {
        if (koq.b(list)) {
            return null;
        }
        String[] strArr = new String[list.size()];
        for (int i = 0; i < list.size(); i++) {
            strArr[i] = "assert" + gyn.d().b(list.get(i), "mp3");
        }
        return strArr;
    }
}
