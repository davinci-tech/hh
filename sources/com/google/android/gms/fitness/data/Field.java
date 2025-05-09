package com.google.android.gms.fitness.data;

import android.os.Parcel;
import android.os.Parcelable;
import com.amap.api.col.p0003sl.it;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.huawei.hms.network.embedded.g4;
import com.huawei.openalliance.ad.constant.JsbMapKeyNames;
import com.huawei.pluginachievement.manager.model.MedalConstants;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;

/* loaded from: classes2.dex */
public final class Field extends AbstractSafeParcelable {
    public static final int FORMAT_FLOAT = 2;
    public static final int FORMAT_INT32 = 1;
    public static final int FORMAT_MAP = 4;
    public static final int FORMAT_STRING = 3;
    public static final int MEAL_TYPE_BREAKFAST = 1;
    public static final int MEAL_TYPE_DINNER = 3;
    public static final int MEAL_TYPE_LUNCH = 2;
    public static final int MEAL_TYPE_SNACK = 4;
    public static final int MEAL_TYPE_UNKNOWN = 0;
    public static final String NUTRIENT_CALCIUM = "calcium";
    public static final String NUTRIENT_CALORIES = "calories";
    public static final String NUTRIENT_CHOLESTEROL = "cholesterol";
    public static final String NUTRIENT_DIETARY_FIBER = "dietary_fiber";
    public static final String NUTRIENT_IRON = "iron";
    public static final String NUTRIENT_MONOUNSATURATED_FAT = "fat.monounsaturated";
    public static final String NUTRIENT_POLYUNSATURATED_FAT = "fat.polyunsaturated";
    public static final String NUTRIENT_POTASSIUM = "potassium";
    public static final String NUTRIENT_PROTEIN = "protein";
    public static final String NUTRIENT_SATURATED_FAT = "fat.saturated";
    public static final String NUTRIENT_SODIUM = "sodium";
    public static final String NUTRIENT_SUGAR = "sugar";
    public static final String NUTRIENT_TOTAL_CARBS = "carbs.total";
    public static final String NUTRIENT_TOTAL_FAT = "fat.total";
    public static final String NUTRIENT_TRANS_FAT = "fat.trans";
    public static final String NUTRIENT_UNSATURATED_FAT = "fat.unsaturated";
    public static final String NUTRIENT_VITAMIN_A = "vitamin_a";
    public static final String NUTRIENT_VITAMIN_C = "vitamin_c";
    public static final int RESISTANCE_TYPE_BARBELL = 1;
    public static final int RESISTANCE_TYPE_BODY = 6;
    public static final int RESISTANCE_TYPE_CABLE = 2;
    public static final int RESISTANCE_TYPE_DUMBBELL = 3;
    public static final int RESISTANCE_TYPE_KETTLEBELL = 4;
    public static final int RESISTANCE_TYPE_MACHINE = 5;
    public static final int RESISTANCE_TYPE_UNKNOWN = 0;
    private final int format;
    private final String name;
    private final Boolean zzdd;
    public static final Field FIELD_ACTIVITY = zzd("activity");
    public static final Field FIELD_CONFIDENCE = zzf("confidence");
    public static final Field FIELD_ACTIVITY_CONFIDENCE = zzh("activity_confidence");
    public static final Field FIELD_STEPS = zzd(MedalConstants.EVENT_STEPS);
    public static final Field FIELD_STEP_LENGTH = zzf("step_length");
    public static final Field FIELD_DURATION = zzd("duration");
    private static final Field zzcg = zze("duration");
    private static final Field zzch = zzh("activity_duration");
    public static final Field zzci = zzh("activity_duration.ascending");
    public static final Field zzcj = zzh("activity_duration.descending");
    public static final Field FIELD_BPM = zzf("bpm");
    public static final Field FIELD_LATITUDE = zzf(JsbMapKeyNames.H5_LOC_LAT);
    public static final Field FIELD_LONGITUDE = zzf(JsbMapKeyNames.H5_LOC_LON);
    public static final Field FIELD_ACCURACY = zzf("accuracy");
    public static final Field FIELD_ALTITUDE = new Field("altitude", 2, true);
    public static final Field FIELD_DISTANCE = zzf("distance");
    public static final Field FIELD_HEIGHT = zzf("height");
    public static final Field FIELD_WEIGHT = zzf("weight");
    public static final Field FIELD_CIRCUMFERENCE = zzf("circumference");
    public static final Field FIELD_PERCENTAGE = zzf("percentage");
    public static final Field FIELD_SPEED = zzf("speed");
    public static final Field FIELD_RPM = zzf("rpm");
    public static final Field zzck = zzi("google.android.fitness.GoalV2");
    public static final Field zzcl = zzi("prescription_event");
    public static final Field zzcm = zzi("symptom");
    public static final Field zzcn = zzi("google.android.fitness.StrideModel");
    public static final Field zzco = zzi("google.android.fitness.Device");
    public static final Field FIELD_REVOLUTIONS = zzd("revolutions");
    public static final Field FIELD_CALORIES = zzf("calories");
    public static final Field FIELD_WATTS = zzf("watts");
    public static final Field FIELD_VOLUME = zzf("volume");
    public static final Field FIELD_MEAL_TYPE = zzd("meal_type");
    public static final Field FIELD_FOOD_ITEM = zzg("food_item");
    public static final Field FIELD_NUTRIENTS = zzh("nutrients");
    public static final Field zzcp = zzf("elevation.change");
    public static final Field zzcq = zzh("elevation.gain");
    public static final Field zzcr = zzh("elevation.loss");
    public static final Field zzcs = zzf("floors");
    public static final Field zzct = zzh("floor.gain");
    public static final Field zzcu = zzh("floor.loss");
    public static final Field FIELD_EXERCISE = zzg("exercise");
    public static final Field FIELD_REPETITIONS = zzd("repetitions");
    public static final Field FIELD_RESISTANCE = zzf("resistance");
    public static final Field FIELD_RESISTANCE_TYPE = zzd("resistance_type");
    public static final Field FIELD_NUM_SEGMENTS = zzd("num_segments");
    public static final Field FIELD_AVERAGE = zzf("average");
    public static final Field FIELD_MAX = zzf("max");
    public static final Field FIELD_MIN = zzf("min");
    public static final Field FIELD_LOW_LATITUDE = zzf("low_latitude");
    public static final Field FIELD_LOW_LONGITUDE = zzf("low_longitude");
    public static final Field FIELD_HIGH_LATITUDE = zzf("high_latitude");
    public static final Field FIELD_HIGH_LONGITUDE = zzf("high_longitude");
    public static final Field FIELD_OCCURRENCES = zzd("occurrences");
    public static final Field zzcv = zzd("sensor_type");
    public static final Field zzcw = zzd("sensor_types");
    public static final Field zzcx = new Field("timestamps", 5);
    public static final Field zzcy = zzd("sample_period");
    public static final Field zzcz = zzd("num_samples");
    public static final Field zzda = zzd("num_dimensions");
    public static final Field zzdb = new Field("sensor_values", 6);
    public static final Field FIELD_INTENSITY = zzf("intensity");
    public static final Field zzdc = zzf("probability");
    public static final Parcelable.Creator<Field> CREATOR = new zzq();

    public static final class zza {
        public static final Field zzde = Field.zzf("x");
        public static final Field zzdf = Field.zzf("y");
        public static final Field zzdg = Field.zzf("z");
        public static final Field zzdh = Field.zzj("debug_session");
        public static final Field zzdi = Field.zzj("google.android.fitness.SessionV2");
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public static Field zza(String str, int i) {
        char c;
        str.hashCode();
        switch (str.hashCode()) {
            case -2131707655:
                if (str.equals("accuracy")) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            case -2083865430:
                if (str.equals("debug_session")) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            case -2006370880:
                if (str.equals("body_temperature_measurement_location")) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            case -1992012396:
                if (str.equals("duration")) {
                    c = 3;
                    break;
                }
                c = 65535;
                break;
            case -1859447186:
                if (str.equals("blood_glucose_level")) {
                    c = 4;
                    break;
                }
                c = 65535;
                break;
            case -1743016407:
                if (str.equals("symptom")) {
                    c = 5;
                    break;
                }
                c = 65535;
                break;
            case -1655966961:
                if (str.equals("activity")) {
                    c = 6;
                    break;
                }
                c = 65535;
                break;
            case -1595712862:
                if (str.equals("cervical_dilation")) {
                    c = 7;
                    break;
                }
                c = 65535;
                break;
            case -1579612127:
                if (str.equals("floor.gain")) {
                    c = '\b';
                    break;
                }
                c = 65535;
                break;
            case -1579449403:
                if (str.equals("floor.loss")) {
                    c = '\t';
                    break;
                }
                c = 65535;
                break;
            case -1569430471:
                if (str.equals("num_segments")) {
                    c = '\n';
                    break;
                }
                c = 65535;
                break;
            case -1531570079:
                if (str.equals("elevation.change")) {
                    c = 11;
                    break;
                }
                c = 65535;
                break;
            case -1440707631:
                if (str.equals("oxygen_saturation")) {
                    c = '\f';
                    break;
                }
                c = 65535;
                break;
            case -1439978388:
                if (str.equals(JsbMapKeyNames.H5_LOC_LAT)) {
                    c = '\r';
                    break;
                }
                c = 65535;
                break;
            case -1352492506:
                if (str.equals("num_dimensions")) {
                    c = 14;
                    break;
                }
                c = 65535;
                break;
            case -1290561483:
                if (str.equals("probability")) {
                    c = 15;
                    break;
                }
                c = 65535;
                break;
            case -1271636505:
                if (str.equals("floors")) {
                    c = 16;
                    break;
                }
                c = 65535;
                break;
            case -1248595573:
                if (str.equals("supplemental_oxygen_flow_rate_average")) {
                    c = 17;
                    break;
                }
                c = 65535;
                break;
            case -1221029593:
                if (str.equals("height")) {
                    c = 18;
                    break;
                }
                c = 65535;
                break;
            case -1220952307:
                if (str.equals("blood_pressure_measurement_location")) {
                    c = 19;
                    break;
                }
                c = 65535;
                break;
            case -1133736764:
                if (str.equals("activity_duration")) {
                    c = 20;
                    break;
                }
                c = 65535;
                break;
            case -1129337776:
                if (str.equals("num_samples")) {
                    c = 21;
                    break;
                }
                c = 65535;
                break;
            case -1110756780:
                if (str.equals("food_item")) {
                    c = 22;
                    break;
                }
                c = 65535;
                break;
            case -921832806:
                if (str.equals("percentage")) {
                    c = 23;
                    break;
                }
                c = 65535;
                break;
            case -918978307:
                if (str.equals("cervical_position")) {
                    c = 24;
                    break;
                }
                c = 65535;
                break;
            case -810883302:
                if (str.equals("volume")) {
                    c = 25;
                    break;
                }
                c = 65535;
                break;
            case -803244749:
                if (str.equals("blood_pressure_systolic")) {
                    c = 26;
                    break;
                }
                c = 65535;
                break;
            case -791592328:
                if (str.equals("weight")) {
                    c = 27;
                    break;
                }
                c = 65535;
                break;
            case -631448035:
                if (str.equals("average")) {
                    c = 28;
                    break;
                }
                c = 65535;
                break;
            case -626344110:
                if (str.equals("high_longitude")) {
                    c = 29;
                    break;
                }
                c = 65535;
                break;
            case -619868540:
                if (str.equals("low_longitude")) {
                    c = 30;
                    break;
                }
                c = 65535;
                break;
            case -511934137:
                if (str.equals("sensor_values")) {
                    c = 31;
                    break;
                }
                c = 65535;
                break;
            case -494782871:
                if (str.equals("high_latitude")) {
                    c = ' ';
                    break;
                }
                c = 65535;
                break;
            case -452643911:
                if (str.equals("step_length")) {
                    c = '!';
                    break;
                }
                c = 65535;
                break;
            case -437053898:
                if (str.equals("meal_type")) {
                    c = '\"';
                    break;
                }
                c = 65535;
                break;
            case -277306353:
                if (str.equals("circumference")) {
                    c = '#';
                    break;
                }
                c = 65535;
                break;
            case -266093204:
                if (str.equals("nutrients")) {
                    c = '$';
                    break;
                }
                c = 65535;
                break;
            case -228366862:
                if (str.equals("oxygen_saturation_measurement_method")) {
                    c = '%';
                    break;
                }
                c = 65535;
                break;
            case -168965370:
                if (str.equals("calories")) {
                    c = '&';
                    break;
                }
                c = 65535;
                break;
            case -126538880:
                if (str.equals("resistance_type")) {
                    c = '\'';
                    break;
                }
                c = 65535;
                break;
            case -28590302:
                if (str.equals("ovulation_test_result")) {
                    c = g4.k;
                    break;
                }
                c = 65535;
                break;
            case 120:
                if (str.equals("x")) {
                    c = g4.l;
                    break;
                }
                c = 65535;
                break;
            case 121:
                if (str.equals("y")) {
                    c = '*';
                    break;
                }
                c = 65535;
                break;
            case 122:
                if (str.equals("z")) {
                    c = '+';
                    break;
                }
                c = 65535;
                break;
            case 97759:
                if (str.equals("bpm")) {
                    c = ',';
                    break;
                }
                c = 65535;
                break;
            case 107876:
                if (str.equals("max")) {
                    c = '-';
                    break;
                }
                c = 65535;
                break;
            case 108114:
                if (str.equals("min")) {
                    c = FilenameUtils.EXTENSION_SEPARATOR;
                    break;
                }
                c = 65535;
                break;
            case 113135:
                if (str.equals("rpm")) {
                    c = '/';
                    break;
                }
                c = 65535;
                break;
            case 66639641:
                if (str.equals("temporal_relation_to_sleep")) {
                    c = '0';
                    break;
                }
                c = 65535;
                break;
            case 109641799:
                if (str.equals("speed")) {
                    c = '1';
                    break;
                }
                c = 65535;
                break;
            case 109761319:
                if (str.equals(MedalConstants.EVENT_STEPS)) {
                    c = '2';
                    break;
                }
                c = 65535;
                break;
            case 112903913:
                if (str.equals("watts")) {
                    c = '3';
                    break;
                }
                c = 65535;
                break;
            case 120904628:
                if (str.equals("sensor_types")) {
                    c = '4';
                    break;
                }
                c = 65535;
                break;
            case 137365935:
                if (str.equals(JsbMapKeyNames.H5_LOC_LON)) {
                    c = '5';
                    break;
                }
                c = 65535;
                break;
            case 198162679:
                if (str.equals("low_latitude")) {
                    c = '6';
                    break;
                }
                c = 65535;
                break;
            case 220648413:
                if (str.equals("blood_pressure_diastolic_average")) {
                    c = '7';
                    break;
                }
                c = 65535;
                break;
            case 248891292:
                if (str.equals("blood_glucose_specimen_source")) {
                    c = '8';
                    break;
                }
                c = 65535;
                break;
            case 286612066:
                if (str.equals("activity_duration.descending")) {
                    c = '9';
                    break;
                }
                c = 65535;
                break;
            case 288459765:
                if (str.equals("distance")) {
                    c = ':';
                    break;
                }
                c = 65535;
                break;
            case 292126261:
                if (str.equals("prescription_event")) {
                    c = ';';
                    break;
                }
                c = 65535;
                break;
            case 306600408:
                if (str.equals("google.android.fitness.SessionV2")) {
                    c = '<';
                    break;
                }
                c = 65535;
                break;
            case 320627489:
                if (str.equals("cervical_mucus_texture")) {
                    c = '=';
                    break;
                }
                c = 65535;
                break;
            case 419669488:
                if (str.equals("google.android.fitness.Device")) {
                    c = '>';
                    break;
                }
                c = 65535;
                break;
            case 455965230:
                if (str.equals("activity_duration.ascending")) {
                    c = '?';
                    break;
                }
                c = 65535;
                break;
            case 475560024:
                if (str.equals("blood_pressure_systolic_max")) {
                    c = '@';
                    break;
                }
                c = 65535;
                break;
            case 475560262:
                if (str.equals("blood_pressure_systolic_min")) {
                    c = 'A';
                    break;
                }
                c = 65535;
                break;
            case 499324979:
                if (str.equals("intensity")) {
                    c = 'B';
                    break;
                }
                c = 65535;
                break;
            case 514168969:
                if (str.equals("google.android.fitness.GoalV2")) {
                    c = 'C';
                    break;
                }
                c = 65535;
                break;
            case 581888402:
                if (str.equals("cervical_mucus_amount")) {
                    c = 'D';
                    break;
                }
                c = 65535;
                break;
            case 623947695:
                if (str.equals("oxygen_saturation_average")) {
                    c = 'E';
                    break;
                }
                c = 65535;
                break;
            case 738210934:
                if (str.equals("google.android.fitness.StrideModel")) {
                    c = 'F';
                    break;
                }
                c = 65535;
                break;
            case 784486594:
                if (str.equals("occurrences")) {
                    c = 'G';
                    break;
                }
                c = 65535;
                break;
            case 811264586:
                if (str.equals("revolutions")) {
                    c = 'H';
                    break;
                }
                c = 65535;
                break;
            case 815736413:
                if (str.equals("oxygen_saturation_system")) {
                    c = 'I';
                    break;
                }
                c = 65535;
                break;
            case 829251210:
                if (str.equals("confidence")) {
                    c = 'J';
                    break;
                }
                c = 65535;
                break;
            case 833248065:
                if (str.equals("temporal_relation_to_meal")) {
                    c = 'K';
                    break;
                }
                c = 65535;
                break;
            case 883161687:
                if (str.equals("body_temperature")) {
                    c = 'L';
                    break;
                }
                c = 65535;
                break;
            case 984367650:
                if (str.equals("repetitions")) {
                    c = 'M';
                    break;
                }
                c = 65535;
                break;
            case 998412730:
                if (str.equals("activity_confidence")) {
                    c = 'N';
                    break;
                }
                c = 65535;
                break;
            case 1136011766:
                if (str.equals("sample_period")) {
                    c = 'O';
                    break;
                }
                c = 65535;
                break;
            case 1276952063:
                if (str.equals("blood_pressure_diastolic")) {
                    c = 'P';
                    break;
                }
                c = 65535;
                break;
            case 1284575222:
                if (str.equals("oxygen_saturation_max")) {
                    c = 'Q';
                    break;
                }
                c = 65535;
                break;
            case 1284575460:
                if (str.equals("oxygen_saturation_min")) {
                    c = 'R';
                    break;
                }
                c = 65535;
                break;
            case 1403812644:
                if (str.equals("blood_pressure_diastolic_max")) {
                    c = 'S';
                    break;
                }
                c = 65535;
                break;
            case 1403812882:
                if (str.equals("blood_pressure_diastolic_min")) {
                    c = 'T';
                    break;
                }
                c = 65535;
                break;
            case 1527920799:
                if (str.equals("sensor_type")) {
                    c = 'U';
                    break;
                }
                c = 65535;
                break;
            case 1708915229:
                if (str.equals("timestamps")) {
                    c = 'V';
                    break;
                }
                c = 65535;
                break;
            case 1857734768:
                if (str.equals("elevation.gain")) {
                    c = 'W';
                    break;
                }
                c = 65535;
                break;
            case 1857897492:
                if (str.equals("elevation.loss")) {
                    c = 'X';
                    break;
                }
                c = 65535;
                break;
            case 1863800889:
                if (str.equals("resistance")) {
                    c = 'Y';
                    break;
                }
                c = 65535;
                break;
            case 1880897007:
                if (str.equals("oxygen_therapy_administration_mode")) {
                    c = 'Z';
                    break;
                }
                c = 65535;
                break;
            case 1892583496:
                if (str.equals("menstrual_flow")) {
                    c = '[';
                    break;
                }
                c = 65535;
                break;
            case 1958191058:
                if (str.equals("supplemental_oxygen_flow_rate_max")) {
                    c = IOUtils.DIR_SEPARATOR_WINDOWS;
                    break;
                }
                c = 65535;
                break;
            case 1958191296:
                if (str.equals("supplemental_oxygen_flow_rate_min")) {
                    c = ']';
                    break;
                }
                c = 65535;
                break;
            case 1983072038:
                if (str.equals("body_position")) {
                    c = '^';
                    break;
                }
                c = 65535;
                break;
            case 2020153105:
                if (str.equals("blood_pressure_systolic_average")) {
                    c = '_';
                    break;
                }
                c = 65535;
                break;
            case 2036550306:
                if (str.equals("altitude")) {
                    c = '`';
                    break;
                }
                c = 65535;
                break;
            case 2056323544:
                if (str.equals("exercise")) {
                    c = 'a';
                    break;
                }
                c = 65535;
                break;
            case 2072582505:
                if (str.equals("cervical_firmness")) {
                    c = 'b';
                    break;
                }
                c = 65535;
                break;
            case 2078370221:
                if (str.equals("supplemental_oxygen_flow_rate")) {
                    c = 'c';
                    break;
                }
                c = 65535;
                break;
            default:
                c = 65535;
                break;
        }
        switch (c) {
            case 0:
                return FIELD_ACCURACY;
            case 1:
                return zza.zzdh;
            case 2:
                return HealthFields.FIELD_BODY_TEMPERATURE_MEASUREMENT_LOCATION;
            case 3:
                return FIELD_DURATION;
            case 4:
                return HealthFields.FIELD_BLOOD_GLUCOSE_LEVEL;
            case 5:
                return zzcm;
            case 6:
                return FIELD_ACTIVITY;
            case 7:
                return HealthFields.FIELD_CERVICAL_DILATION;
            case '\b':
                return zzct;
            case '\t':
                return zzcu;
            case '\n':
                return FIELD_NUM_SEGMENTS;
            case 11:
                return zzcp;
            case '\f':
                return HealthFields.FIELD_OXYGEN_SATURATION;
            case '\r':
                return FIELD_LATITUDE;
            case 14:
                return zzda;
            case 15:
                return zzdc;
            case 16:
                return zzcs;
            case 17:
                return HealthFields.FIELD_SUPPLEMENTAL_OXYGEN_FLOW_RATE_AVERAGE;
            case 18:
                return FIELD_HEIGHT;
            case 19:
                return HealthFields.FIELD_BLOOD_PRESSURE_MEASUREMENT_LOCATION;
            case 20:
                return zzch;
            case 21:
                return zzcz;
            case 22:
                return FIELD_FOOD_ITEM;
            case 23:
                return FIELD_PERCENTAGE;
            case 24:
                return HealthFields.FIELD_CERVICAL_POSITION;
            case 25:
                return FIELD_VOLUME;
            case 26:
                return HealthFields.FIELD_BLOOD_PRESSURE_SYSTOLIC;
            case 27:
                return FIELD_WEIGHT;
            case 28:
                return FIELD_AVERAGE;
            case 29:
                return FIELD_HIGH_LONGITUDE;
            case 30:
                return FIELD_LOW_LONGITUDE;
            case 31:
                return zzdb;
            case ' ':
                return FIELD_HIGH_LATITUDE;
            case '!':
                return FIELD_STEP_LENGTH;
            case '\"':
                return FIELD_MEAL_TYPE;
            case '#':
                return FIELD_CIRCUMFERENCE;
            case '$':
                return FIELD_NUTRIENTS;
            case '%':
                return HealthFields.FIELD_OXYGEN_SATURATION_MEASUREMENT_METHOD;
            case '&':
                return FIELD_CALORIES;
            case '\'':
                return FIELD_RESISTANCE_TYPE;
            case '(':
                return HealthFields.FIELD_OVULATION_TEST_RESULT;
            case ')':
                return zza.zzde;
            case '*':
                return zza.zzdf;
            case '+':
                return zza.zzdg;
            case ',':
                return FIELD_BPM;
            case '-':
                return FIELD_MAX;
            case '.':
                return FIELD_MIN;
            case '/':
                return FIELD_RPM;
            case '0':
                return HealthFields.FIELD_TEMPORAL_RELATION_TO_SLEEP;
            case '1':
                return FIELD_SPEED;
            case '2':
                return FIELD_STEPS;
            case '3':
                return FIELD_WATTS;
            case '4':
                return zzcw;
            case '5':
                return FIELD_LONGITUDE;
            case '6':
                return FIELD_LOW_LATITUDE;
            case '7':
                return HealthFields.FIELD_BLOOD_PRESSURE_DIASTOLIC_AVERAGE;
            case '8':
                return HealthFields.FIELD_BLOOD_GLUCOSE_SPECIMEN_SOURCE;
            case '9':
                return zzcj;
            case ':':
                return FIELD_DISTANCE;
            case ';':
                return zzcl;
            case '<':
                return zza.zzdi;
            case '=':
                return HealthFields.FIELD_CERVICAL_MUCUS_TEXTURE;
            case '>':
                return zzco;
            case '?':
                return zzci;
            case '@':
                return HealthFields.FIELD_BLOOD_PRESSURE_SYSTOLIC_MAX;
            case 'A':
                return HealthFields.FIELD_BLOOD_PRESSURE_SYSTOLIC_MIN;
            case 'B':
                return FIELD_INTENSITY;
            case 'C':
                return zzck;
            case 'D':
                return HealthFields.FIELD_CERVICAL_MUCUS_AMOUNT;
            case 'E':
                return HealthFields.FIELD_OXYGEN_SATURATION_AVERAGE;
            case 'F':
                return zzcn;
            case 'G':
                return FIELD_OCCURRENCES;
            case 'H':
                return FIELD_REVOLUTIONS;
            case 'I':
                return HealthFields.FIELD_OXYGEN_SATURATION_SYSTEM;
            case 'J':
                return FIELD_CONFIDENCE;
            case 'K':
                return HealthFields.FIELD_TEMPORAL_RELATION_TO_MEAL;
            case 'L':
                return HealthFields.FIELD_BODY_TEMPERATURE;
            case 'M':
                return FIELD_REPETITIONS;
            case 'N':
                return FIELD_ACTIVITY_CONFIDENCE;
            case 'O':
                return zzcy;
            case 'P':
                return HealthFields.FIELD_BLOOD_PRESSURE_DIASTOLIC;
            case 'Q':
                return HealthFields.FIELD_OXYGEN_SATURATION_MAX;
            case 'R':
                return HealthFields.FIELD_OXYGEN_SATURATION_MIN;
            case 'S':
                return HealthFields.FIELD_BLOOD_PRESSURE_DIASTOLIC_MAX;
            case 'T':
                return HealthFields.FIELD_BLOOD_PRESSURE_DIASTOLIC_MIN;
            case 'U':
                return zzcv;
            case 'V':
                return zzcx;
            case 'W':
                return zzcq;
            case 'X':
                return zzcr;
            case 'Y':
                return FIELD_RESISTANCE;
            case 'Z':
                return HealthFields.FIELD_OXYGEN_THERAPY_ADMINISTRATION_MODE;
            case '[':
                return HealthFields.FIELD_MENSTRUAL_FLOW;
            case '\\':
                return HealthFields.FIELD_SUPPLEMENTAL_OXYGEN_FLOW_RATE_MAX;
            case ']':
                return HealthFields.FIELD_SUPPLEMENTAL_OXYGEN_FLOW_RATE_MIN;
            case '^':
                return HealthFields.FIELD_BODY_POSITION;
            case '_':
                return HealthFields.FIELD_BLOOD_PRESSURE_SYSTOLIC_AVERAGE;
            case '`':
                return FIELD_ALTITUDE;
            case 'a':
                return FIELD_EXERCISE;
            case 'b':
                return HealthFields.FIELD_CERVICAL_FIRMNESS;
            case 'c':
                return HealthFields.FIELD_SUPPLEMENTAL_OXYGEN_FLOW_RATE;
            default:
                return new Field(str, i, null);
        }
    }

    private static Field zzd(String str) {
        return new Field(str, 1);
    }

    static Field zze(String str) {
        return new Field(str, 1, true);
    }

    static Field zzf(String str) {
        return new Field(str, 2);
    }

    private static Field zzg(String str) {
        return new Field(str, 3);
    }

    private static Field zzh(String str) {
        return new Field(str, 4);
    }

    private static Field zzi(String str) {
        return new Field(str, 7);
    }

    static Field zzj(String str) {
        return new Field(str, 7, true);
    }

    private Field(String str, int i) {
        this(str, i, null);
    }

    Field(String str, int i, Boolean bool) {
        this.name = (String) Preconditions.checkNotNull(str);
        this.format = i;
        this.zzdd = bool;
    }

    public final String getName() {
        return this.name;
    }

    public final int getFormat() {
        return this.format;
    }

    public final Boolean isOptional() {
        return this.zzdd;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Field)) {
            return false;
        }
        Field field = (Field) obj;
        return this.name.equals(field.name) && this.format == field.format;
    }

    public final int hashCode() {
        return this.name.hashCode();
    }

    public final String toString() {
        Object[] objArr = new Object[2];
        objArr[0] = this.name;
        objArr[1] = this.format == 1 ? "i" : it.i;
        return String.format("%s(%s)", objArr);
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 1, getName(), false);
        SafeParcelWriter.writeInt(parcel, 2, getFormat());
        SafeParcelWriter.writeBooleanObject(parcel, 3, isOptional(), false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
