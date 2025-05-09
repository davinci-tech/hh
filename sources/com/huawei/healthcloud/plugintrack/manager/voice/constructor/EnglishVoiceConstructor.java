package com.huawei.healthcloud.plugintrack.manager.voice.constructor;

import android.util.ArrayMap;
import com.huawei.health.R;
import com.huawei.hwlogsmodel.LogUtil;
import defpackage.gxi;
import health.compact.a.LogAnonymous;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes4.dex */
public class EnglishVoiceConstructor implements IVoiceContentConstructor, ISoundResourceConstructor {
    private Map<Integer, Integer> d;

    public EnglishVoiceConstructor() {
        ArrayMap arrayMap = new ArrayMap();
        this.d = arrayMap;
        arrayMap.put(0, Integer.valueOf(SoundData.NUM_0.getIndex()));
        this.d.put(1, Integer.valueOf(SoundData.NUM_01.getIndex()));
        this.d.put(2, Integer.valueOf(SoundData.NUM_02.getIndex()));
        this.d.put(3, Integer.valueOf(SoundData.NUM_03.getIndex()));
        this.d.put(4, Integer.valueOf(SoundData.NUM_04.getIndex()));
        this.d.put(5, Integer.valueOf(SoundData.NUM_05.getIndex()));
        this.d.put(6, Integer.valueOf(SoundData.NUM_06.getIndex()));
        this.d.put(7, Integer.valueOf(SoundData.NUM_07.getIndex()));
        this.d.put(8, Integer.valueOf(SoundData.NUM_08.getIndex()));
        this.d.put(9, Integer.valueOf(SoundData.NUM_09.getIndex()));
        this.d.put(10, Integer.valueOf(SoundData.NUM_10.getIndex()));
        this.d.put(11, Integer.valueOf(SoundData.NUM_11.getIndex()));
        this.d.put(12, Integer.valueOf(SoundData.NUM_12.getIndex()));
        this.d.put(13, Integer.valueOf(SoundData.NUM_13.getIndex()));
        this.d.put(14, Integer.valueOf(SoundData.NUM_14.getIndex()));
        this.d.put(15, Integer.valueOf(SoundData.NUM_15.getIndex()));
        this.d.put(16, Integer.valueOf(SoundData.NUM_16.getIndex()));
        this.d.put(17, Integer.valueOf(SoundData.NUM_17.getIndex()));
        this.d.put(18, Integer.valueOf(SoundData.NUM_18.getIndex()));
        this.d.put(19, Integer.valueOf(SoundData.NUM_19.getIndex()));
        this.d.put(20, Integer.valueOf(SoundData.NUM_20.getIndex()));
        this.d.put(30, Integer.valueOf(SoundData.NUM_30.getIndex()));
        this.d.put(40, Integer.valueOf(SoundData.NUM_40.getIndex()));
        this.d.put(50, Integer.valueOf(SoundData.NUM_50.getIndex()));
        this.d.put(60, Integer.valueOf(SoundData.NUM_60.getIndex()));
        this.d.put(70, Integer.valueOf(SoundData.NUM_70.getIndex()));
        this.d.put(80, Integer.valueOf(SoundData.NUM_80.getIndex()));
        this.d.put(90, Integer.valueOf(SoundData.NUM_90.getIndex()));
        this.d.put(100, Integer.valueOf(SoundData.NUM_100.getIndex()));
        this.d.put(1000, Integer.valueOf(SoundData.NUM_1000.getIndex()));
    }

    enum SoundData {
        DINGDONG(R.raw._2131886169_res_0x7f120059, 1),
        SPORT_RESTART(R.raw._2131886137_res_0x7f120039, 2),
        SPORT_START(R.raw._2131886138_res_0x7f12003a, 3),
        DISTANCE(R.raw._2131886089_res_0x7f120009, 4),
        FAST(R.raw._2131886090_res_0x7f12000a, 5),
        MINUTE(R.raw._2131886100_res_0x7f120014, 6),
        MINUTES(R.raw._2131886101_res_0x7f120015, 7),
        SECOND(R.raw._2131886133_res_0x7f120035, 8),
        SECONDS(R.raw._2131886134_res_0x7f120036, 9),
        KILOMETER(R.raw._2131886095_res_0x7f12000f, 10),
        KILOMETERS(R.raw._2131886096_res_0x7f120010, 11),
        KILOMETERS_PER_HOUR(R.raw._2131886097_res_0x7f120011, 12),
        LAST_KILOMETER_PACE(R.raw._2131886098_res_0x7f120012, 13),
        PACE(R.raw._2131886131_res_0x7f120033, 14),
        NUM_01(R.raw._2131886102_res_0x7f120016, 15),
        NUM_02(R.raw._2131886103_res_0x7f120017, 16),
        NUM_03(R.raw._2131886104_res_0x7f120018, 17),
        NUM_04(R.raw._2131886105_res_0x7f120019, 18),
        NUM_05(R.raw._2131886106_res_0x7f12001a, 19),
        NUM_06(R.raw._2131886107_res_0x7f12001b, 20),
        NUM_07(R.raw._2131886108_res_0x7f12001c, 21),
        NUM_08(R.raw._2131886109_res_0x7f12001d, 22),
        NUM_09(R.raw._2131886110_res_0x7f12001e, 23),
        NUM_10(R.raw._2131886111_res_0x7f12001f, 24),
        NUM_11(R.raw._2131886114_res_0x7f120022, 25),
        NUM_12(R.raw._2131886115_res_0x7f120023, 26),
        NUM_13(R.raw._2131886116_res_0x7f120024, 27),
        NUM_14(R.raw._2131886117_res_0x7f120025, 28),
        NUM_15(R.raw._2131886118_res_0x7f120026, 29),
        NUM_16(R.raw._2131886119_res_0x7f120027, 30),
        NUM_17(R.raw._2131886120_res_0x7f120028, 31),
        NUM_18(R.raw._2131886121_res_0x7f120029, 32),
        NUM_19(R.raw._2131886122_res_0x7f12002a, 33),
        NUM_20(R.raw._2131886123_res_0x7f12002b, 34),
        NUM_30(R.raw._2131886124_res_0x7f12002c, 35),
        NUM_40(R.raw._2131886125_res_0x7f12002d, 36),
        NUM_50(R.raw._2131886126_res_0x7f12002e, 37),
        NUM_60(R.raw._2131886127_res_0x7f12002f, 38),
        NUM_70(R.raw._2131886128_res_0x7f120030, 39),
        NUM_80(R.raw._2131886129_res_0x7f120031, 40),
        NUM_90(R.raw._2131886130_res_0x7f120032, 41),
        NUM_100(R.raw._2131886112_res_0x7f120020, 42),
        TOTAL_DISTANCE(R.raw._2131886140_res_0x7f12003c, 43),
        TOTAL_TIME(R.raw._2131886141_res_0x7f12003d, 44),
        TIME(R.raw._2131886139_res_0x7f12003b, 45),
        POINT(R.raw._2131886132_res_0x7f120034, 46),
        COME_ON(R.raw._2131886088_res_0x7f120008, 47),
        LET_RELAX(R.raw._2131886099_res_0x7f120013, 48),
        HOUR(R.raw._2131886093_res_0x7f12000d, 49),
        HOURS(R.raw._2131886094_res_0x7f12000e, 50),
        SPORT_OVER(R.raw._2131886135_res_0x7f120037, 53),
        SPORT_PAUSE(R.raw._2131886136_res_0x7f120038, 54),
        SPORT_STOPPED(R.raw._2131886085_res_0x7f120005, 55),
        FINISHED(R.raw._2131886091_res_0x7f12000b, 56),
        YOUR_TARGET(R.raw._2131886142_res_0x7f12003e, 57),
        NUM_0(R.raw._2131886143_res_0x7f12003f, 58),
        BOY_HEART_RATE(R.raw._2131886092_res_0x7f12000c, 59),
        BOY_HEART_RATE_UNIT(R.raw._2131886087_res_0x7f120007, 60),
        BOY_AND(R.raw._2131886086_res_0x7f120006, 61),
        EMPTY(R.raw._2131886163_res_0x7f120053, 62),
        NUM_1000(R.raw._2131886113_res_0x7f120021, 63);

        private int mIndex;
        private int mNameId;

        SoundData(int i, int i2) {
            this.mNameId = i;
            this.mIndex = i2;
        }

        public int getNameId() {
            return this.mNameId;
        }

        public int getIndex() {
            return this.mIndex;
        }
    }

    @Override // com.huawei.healthcloud.plugintrack.manager.voice.constructor.IVoiceContentConstructor
    public Object constructContent(int i, Object obj) {
        LogUtil.c("Track_EnglishVoiceConstructor", "constructContent() type : ", Integer.valueOf(i));
        if (i == 9) {
            return b(obj);
        }
        if (i == 11) {
            return e(obj);
        }
        if (i == 33) {
            return c(obj);
        }
        if (i == 35) {
            return obj;
        }
        switch (i) {
            case 0:
            case 1:
            case 2:
                break;
            case 3:
                ArrayList arrayList = new ArrayList(1);
                arrayList.add(Integer.valueOf(SoundData.SPORT_PAUSE.getIndex()));
                return arrayList;
            case 4:
                ArrayList arrayList2 = new ArrayList(1);
                arrayList2.add(Integer.valueOf(SoundData.SPORT_STOPPED.getIndex()));
                return arrayList2;
            case 5:
                ArrayList arrayList3 = new ArrayList(1);
                arrayList3.add(Integer.valueOf(SoundData.SPORT_RESTART.getIndex()));
                return arrayList3;
            case 6:
                ArrayList arrayList4 = new ArrayList(2);
                arrayList4.add(Integer.valueOf(SoundData.YOUR_TARGET.getIndex()));
                arrayList4.add(Integer.valueOf(SoundData.FINISHED.getIndex()));
                return arrayList4;
            default:
                switch (i) {
                    case 110:
                    case 111:
                    case 112:
                        break;
                    default:
                        ArrayList arrayList5 = new ArrayList(1);
                        arrayList5.add(Integer.valueOf(SoundData.EMPTY.getIndex()));
                        return arrayList5;
                }
        }
        ArrayList arrayList6 = new ArrayList(1);
        arrayList6.add(Integer.valueOf(SoundData.SPORT_START.getIndex()));
        return arrayList6;
    }

    @Override // com.huawei.healthcloud.plugintrack.manager.voice.constructor.ISoundResourceConstructor
    public Map<Integer, Integer> getSoundResource() {
        HashMap hashMap = new HashMap(SoundData.values().length);
        for (SoundData soundData : SoundData.values()) {
            hashMap.put(Integer.valueOf(soundData.getIndex()), Integer.valueOf(soundData.getNameId()));
        }
        LogUtil.c("Track_EnglishVoiceConstructor", "getSoundResource() map size", Integer.valueOf(hashMap.size()));
        return hashMap;
    }

    private List<Integer> c(Object obj) {
        if (!(obj instanceof gxi)) {
            LogUtil.a("Track_EnglishVoiceConstructor", "constructDistanceTimeContent parameter is invalid");
            ArrayList arrayList = new ArrayList(1);
            arrayList.add(Integer.valueOf(SoundData.EMPTY.getIndex()));
            return arrayList;
        }
        int f = ((gxi) obj).f();
        ArrayList arrayList2 = new ArrayList(10);
        if (f > 0) {
            LogUtil.a("Track_EnglishVoiceConstructor", "construct skip times:", Integer.valueOf(f));
            e((List<Integer>) arrayList2, f);
        }
        return arrayList2;
    }

    private List<Integer> b(Object obj) {
        if (!(obj instanceof gxi)) {
            LogUtil.a("Track_EnglishVoiceConstructor", "constructDistanceTimeContent parameter is invalid");
            ArrayList arrayList = new ArrayList(1);
            arrayList.add(Integer.valueOf(SoundData.EMPTY.getIndex()));
            return arrayList;
        }
        gxi gxiVar = (gxi) obj;
        int round = Math.round(gxiVar.a());
        ArrayList arrayList2 = new ArrayList(10);
        arrayList2.add(Integer.valueOf(SoundData.DINGDONG.getIndex()));
        arrayList2.add(Integer.valueOf(SoundData.TOTAL_DISTANCE.getIndex()));
        if (round > 0) {
            LogUtil.a("Track_EnglishVoiceConstructor", "constructDistanceTimeContent total distance:", LogAnonymous.b(round));
            e((List<Integer>) arrayList2, round);
        }
        if (round > 1) {
            arrayList2.add(Integer.valueOf(SoundData.KILOMETERS.getIndex()));
        } else if (round > 0) {
            arrayList2.add(Integer.valueOf(SoundData.KILOMETER.getIndex()));
        } else {
            LogUtil.h("Track_EnglishVoiceConstructor", "currentKilometer <= 0");
        }
        arrayList2.add(Integer.valueOf(SoundData.TOTAL_TIME.getIndex()));
        long r = gxiVar.r();
        LogUtil.a("Track_EnglishVoiceConstructor", "constructDistanceTimeContent total TIME:", LogAnonymous.d(r), ", count kilo:", LogAnonymous.b(round));
        e((List<Integer>) arrayList2, r);
        long g = gxiVar.g();
        if (g > 0) {
            arrayList2.add(Integer.valueOf(SoundData.LAST_KILOMETER_PACE.getIndex()));
            LogUtil.a("Track_EnglishVoiceConstructor", "constructDistanceTimeContent last kilo TIME:", String.valueOf(LogAnonymous.d(g)));
            e((List<Integer>) arrayList2, g);
        }
        int c = gxiVar.c();
        if (c > 0) {
            arrayList2.add(Integer.valueOf(SoundData.BOY_HEART_RATE.getIndex()));
            e((List<Integer>) arrayList2, c);
            arrayList2.add(Integer.valueOf(SoundData.BOY_HEART_RATE_UNIT.getIndex()));
        }
        return arrayList2;
    }

    private List<Integer> e(Object obj) {
        if (!(obj instanceof gxi)) {
            LogUtil.a("Track_EnglishVoiceConstructor", "constructSportStateVoiceContent parameter is invalid");
            ArrayList arrayList = new ArrayList(1);
            arrayList.add(Integer.valueOf(SoundData.EMPTY.getIndex()));
            return arrayList;
        }
        gxi gxiVar = (gxi) obj;
        float a2 = gxiVar.a();
        ArrayList arrayList2 = new ArrayList(10);
        arrayList2.add(Integer.valueOf(SoundData.DINGDONG.getIndex()));
        arrayList2.add(Integer.valueOf(SoundData.TOTAL_DISTANCE.getIndex()));
        b(gxiVar, a2, arrayList2, (int) Math.floor(a2), new BigDecimal(a2 - r5).setScale(2, 4).doubleValue());
        return arrayList2;
    }

    private void b(gxi gxiVar, float f, List<Integer> list, int i, double d) {
        d(f, list, i, d);
        if (f > 1.0f) {
            list.add(Integer.valueOf(SoundData.KILOMETERS.getIndex()));
        } else if (f >= 0.0f) {
            list.add(Integer.valueOf(SoundData.KILOMETER.getIndex()));
        } else {
            LogUtil.h("Track_EnglishVoiceConstructor", "currentKilometer < 0");
        }
        list.add(Integer.valueOf(SoundData.TOTAL_TIME.getIndex()));
        e(list, gxiVar.r());
        d(gxiVar.g(), list);
        int c = gxiVar.c();
        if (c > 0) {
            list.add(Integer.valueOf(SoundData.BOY_HEART_RATE.getIndex()));
            e(list, c);
            list.add(Integer.valueOf(SoundData.BOY_HEART_RATE_UNIT.getIndex()));
        }
        LogUtil.a("Track_EnglishVoiceConstructor", list);
    }

    private void d(float f, List<Integer> list, int i, double d) {
        String d2 = Double.toString(d);
        int indexOf = d2.indexOf(46);
        int i2 = 0;
        if (indexOf >= 0) {
            d2 = d2.substring(indexOf + 1);
            try {
                i2 = Integer.parseInt(d2);
            } catch (NumberFormatException e) {
                LogUtil.a("Track_EnglishVoiceConstructor", "handleDistanceVoice NumberFormatException", e.getMessage());
            }
        }
        if (indexOf < 0 || i2 <= 0) {
            if (i == 0) {
                list.add(Integer.valueOf(SoundData.NUM_0.getIndex()));
                return;
            } else {
                e(list, i);
                return;
            }
        }
        if (f > 0.0f && f < 1.0f) {
            list.add(Integer.valueOf(SoundData.NUM_0.getIndex()));
        } else {
            e(list, i);
        }
        list.add(Integer.valueOf(SoundData.POINT.getIndex()));
        e(list, d2);
    }

    private void e(List<Integer> list, String str) {
        if (str.length() != 0) {
            int length = str.length();
            for (int i = 0; i < length; i++) {
                try {
                    c(list, Integer.parseInt(String.valueOf(str.charAt(i))));
                } catch (NumberFormatException e) {
                    LogUtil.a("Track_EnglishVoiceConstructor", "handlePointString NumberFormatException", e.getMessage());
                }
            }
        }
    }

    private void d(long j, List<Integer> list) {
        if (j > 0) {
            list.add(Integer.valueOf(SoundData.LAST_KILOMETER_PACE.getIndex()));
            e(list, j);
        }
    }

    private void e(List<Integer> list, long j) {
        long j2 = j / 1000;
        LogUtil.c("Track_EnglishVoiceConstructor", "addTimeResource TIME:", String.valueOf(j2));
        float f = j2;
        e(list, f);
        a(list, f);
        c(list, f);
    }

    private void e(List<Integer> list, float f) {
        int i = (int) ((f / 3600.0f) % 100.0f);
        int i2 = (i / 100) % 10;
        int i3 = i / 10;
        int i4 = i % 10;
        if (i2 > 0) {
            c(list, i2);
            list.add(Integer.valueOf(SoundData.NUM_100.getIndex()));
        }
        if (i3 > 1) {
            c(list, i3 * 10);
        } else if (i3 > 0) {
            c(list, (i3 * 10) + i4);
        } else {
            LogUtil.h("Track_EnglishVoiceConstructor", "hourDecadeValue <= 0");
        }
        if (i3 != 1 && i4 > 0) {
            c(list, i4);
        }
        if (i > 1) {
            list.add(Integer.valueOf(SoundData.HOURS.getIndex()));
        } else if (i > 0) {
            list.add(Integer.valueOf(SoundData.HOUR.getIndex()));
        } else {
            LogUtil.h("Track_EnglishVoiceConstructor", "hour <= 0");
        }
    }

    private void a(List<Integer> list, float f) {
        int i = (int) ((f / 60.0f) % 60.0f);
        int i2 = i / 10;
        int i3 = i % 10;
        LogUtil.a("Track_EnglishVoiceConstructor", "convertTimeToSpeakListEng ", " minuteDecadeValue:", Integer.valueOf(i2), " minuteUnitValue:", Integer.valueOf(i3));
        if (i2 > 1) {
            c(list, i2 * 10);
        } else if (i2 > 0) {
            c(list, (i2 * 10) + i3);
        } else {
            LogUtil.h("Track_EnglishVoiceConstructor", "minuteDecadeValue <= 0");
        }
        if (i2 != 1 && i3 > 0) {
            c(list, i3);
        }
        if (i > 1) {
            list.add(Integer.valueOf(SoundData.MINUTES.getIndex()));
        } else if (i > 0) {
            list.add(Integer.valueOf(SoundData.MINUTE.getIndex()));
        } else {
            LogUtil.h("Track_EnglishVoiceConstructor", "minute <= 0");
        }
    }

    private void c(List<Integer> list, float f) {
        int i = (int) (f % 60.0f);
        int i2 = i / 10;
        int i3 = i % 10;
        LogUtil.a("Track_EnglishVoiceConstructor", "convertTimeToSpeakListEng ", " secondDecadeValue:", Integer.valueOf(i2), " secondUnitValue:", Integer.valueOf(i3));
        if (i2 > 1) {
            c(list, i2 * 10);
        } else if (i2 > 0) {
            c(list, (i2 * 10) + i3);
        } else {
            LogUtil.h("Track_EnglishVoiceConstructor", "secondDecadeValue <= 0");
        }
        if (i2 != 1 && i3 > 0) {
            c(list, i3);
        }
        if (i > 1) {
            list.add(Integer.valueOf(SoundData.SECONDS.getIndex()));
        } else if (i > 0) {
            list.add(Integer.valueOf(SoundData.SECOND.getIndex()));
        } else {
            LogUtil.h("Track_EnglishVoiceConstructor", "second <= 0");
        }
    }

    private void e(List<Integer> list, int i) {
        int i2;
        int i3;
        int i4;
        int i5;
        int i6;
        int i7;
        int[] iArr = new int[5];
        int i8 = i;
        int i9 = 0;
        while (i8 >= 10) {
            iArr[i9] = i8 % 10;
            i8 /= 10;
            i9++;
        }
        if (i9 >= 5) {
            LogUtil.h("Track_EnglishVoiceConstructor", "numValue is out of ", Integer.valueOf(i));
            return;
        }
        iArr[i9] = i8;
        while (i9 >= 0) {
            if (i9 != 4 || (i7 = iArr[i9]) <= 0) {
                if (i9 != 3 || (i6 = iArr[i9]) <= 0) {
                    if (i9 == 2 && (i5 = iArr[i9]) > 0) {
                        c(list, i5);
                        c(list, 100);
                        if (iArr[1] == 0 && iArr[0] == 0) {
                            return;
                        } else {
                            list.add(Integer.valueOf(SoundData.BOY_AND.getIndex()));
                        }
                    } else if (i9 == 1 && (i4 = iArr[1]) == 1) {
                        c(list, (i4 * 10) + iArr[0]);
                    } else if (i9 == 1 && (i3 = iArr[i9]) > 1) {
                        c(list, i3 * 10);
                    } else if (iArr[1] != 1 && (i2 = iArr[i9]) > 0) {
                        c(list, i2);
                    }
                } else if (b(list, iArr, i6)) {
                    return;
                }
                i9--;
            } else if (b(list, iArr, (i7 * 10) + iArr[i9 - 1])) {
                return;
            } else {
                i9 = 2;
            }
        }
    }

    private boolean b(List<Integer> list, int[] iArr, int i) {
        c(list, i);
        c(list, 1000);
        if (iArr[2] == 0 && iArr[1] == 0 && iArr[0] == 0) {
            return true;
        }
        list.add(Integer.valueOf(SoundData.BOY_AND.getIndex()));
        return false;
    }

    private void c(List<Integer> list, int i) {
        list.add(this.d.get(Integer.valueOf(i)));
    }
}
