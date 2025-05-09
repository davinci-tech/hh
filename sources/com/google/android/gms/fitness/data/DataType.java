package com.google.android.gms.fitness.data;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.collection.ArraySet;
import com.google.android.gms.common.Scopes;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.fitness.data.Field;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Set;

/* loaded from: classes2.dex */
public final class DataType extends AbstractSafeParcelable implements ReflectedParcelable {
    public static final DataType AGGREGATE_ACTIVITY_SUMMARY;
    public static final DataType AGGREGATE_BASAL_METABOLIC_RATE_SUMMARY;
    public static final DataType AGGREGATE_BODY_FAT_PERCENTAGE_SUMMARY;

    @Deprecated
    public static final DataType AGGREGATE_CALORIES_CONSUMED;
    public static final DataType AGGREGATE_CALORIES_EXPENDED;
    public static final DataType AGGREGATE_DISTANCE_DELTA;
    public static final DataType AGGREGATE_HEART_POINTS;
    public static final DataType AGGREGATE_HEART_RATE_SUMMARY;
    public static final DataType AGGREGATE_HEIGHT_SUMMARY;
    public static final DataType AGGREGATE_HYDRATION;

    @Deprecated
    public static final Set<DataType> AGGREGATE_INPUT_TYPES;
    public static final DataType AGGREGATE_LOCATION_BOUNDING_BOX;
    public static final DataType AGGREGATE_MOVE_MINUTES;
    public static final DataType AGGREGATE_NUTRITION_SUMMARY;
    public static final DataType AGGREGATE_POWER_SUMMARY;
    public static final DataType AGGREGATE_SPEED_SUMMARY;
    public static final DataType AGGREGATE_STEP_COUNT_DELTA;
    public static final DataType AGGREGATE_WEIGHT_SUMMARY;
    public static final Parcelable.Creator<DataType> CREATOR;
    public static final String MIME_TYPE_PREFIX = "vnd.google.fitness.data_type/";

    @Deprecated
    public static final DataType TYPE_ACTIVITY_SAMPLE;
    public static final DataType TYPE_ACTIVITY_SAMPLES;
    public static final DataType TYPE_ACTIVITY_SEGMENT;
    public static final DataType TYPE_BASAL_METABOLIC_RATE;
    public static final DataType TYPE_BODY_FAT_PERCENTAGE;

    @Deprecated
    public static final DataType TYPE_CALORIES_CONSUMED;
    public static final DataType TYPE_CALORIES_EXPENDED;
    public static final DataType TYPE_CYCLING_PEDALING_CADENCE;
    public static final DataType TYPE_CYCLING_PEDALING_CUMULATIVE;
    public static final DataType TYPE_CYCLING_WHEEL_REVOLUTION;
    public static final DataType TYPE_CYCLING_WHEEL_RPM;
    public static final DataType TYPE_DISTANCE_CUMULATIVE;
    public static final DataType TYPE_DISTANCE_DELTA;
    public static final DataType TYPE_HEART_POINTS;
    public static final DataType TYPE_HEART_RATE_BPM;
    public static final DataType TYPE_HEIGHT;
    public static final DataType TYPE_HYDRATION;
    public static final DataType TYPE_LOCATION_SAMPLE;
    public static final DataType TYPE_LOCATION_TRACK;
    public static final DataType TYPE_MOVE_MINUTES;
    public static final DataType TYPE_NUTRITION;
    public static final DataType TYPE_POWER_SAMPLE;
    public static final DataType TYPE_SPEED;
    public static final DataType TYPE_STEP_COUNT_CADENCE;
    public static final DataType TYPE_STEP_COUNT_CUMULATIVE;
    public static final DataType TYPE_STEP_COUNT_DELTA;
    public static final DataType TYPE_WEIGHT;
    public static final DataType TYPE_WORKOUT_EXERCISE;
    public static final DataType zzbc;
    public static final DataType zzbd;
    public static final DataType zzbe;
    public static final DataType zzbf;
    public static final DataType zzbg;
    public static final DataType zzbh;
    public static final DataType zzbi;
    public static final DataType zzbj;
    public static final DataType zzbk;
    public static final DataType zzbl;
    public static final DataType zzbm;
    public static final DataType zzbn;
    public static final DataType zzbo;
    public static final DataType zzbp;
    public static final DataType zzbq;
    public static final DataType zzbr;
    private final String name;
    private final List<Field> zzbs;
    private final String zzbt;
    private final String zzbu;

    public static final class zza {
        public static final DataType zzbv = new DataType("com.google.internal.session.debug", Scopes.FITNESS_ACTIVITY_READ, Scopes.FITNESS_ACTIVITY_READ_WRITE, Field.zza.zzdh);
        public static final DataType zzbw = new DataType("com.google.internal.session.v2", Scopes.FITNESS_ACTIVITY_READ, Scopes.FITNESS_ACTIVITY_READ_WRITE, Field.zza.zzdi);
    }

    public static List<DataType> getAggregatesForInput(DataType dataType) {
        List<DataType> list = com.google.android.gms.fitness.data.zza.zzac.get(dataType);
        return list == null ? Collections.emptyList() : Collections.unmodifiableList(list);
    }

    private DataType(String str, Field... fieldArr) {
        this(str, (List<Field>) Arrays.asList(fieldArr), (String) null, (String) null);
    }

    public DataType(String str, String str2, String str3, Field... fieldArr) {
        this(str, (List<Field>) Arrays.asList(fieldArr), str2, str3);
    }

    DataType(String str, List<Field> list, String str2, String str3) {
        this.name = str;
        this.zzbs = Collections.unmodifiableList(list);
        this.zzbt = str2;
        this.zzbu = str3;
    }

    public static String getMimeType(DataType dataType) {
        String valueOf = String.valueOf(dataType.getName());
        return valueOf.length() != 0 ? MIME_TYPE_PREFIX.concat(valueOf) : new String(MIME_TYPE_PREFIX);
    }

    public final String getName() {
        return this.name;
    }

    public final List<Field> getFields() {
        return this.zzbs;
    }

    public final String zzk() {
        return this.zzbt;
    }

    public final String zzl() {
        return this.zzbu;
    }

    public final int indexOf(Field field) {
        int indexOf = this.zzbs.indexOf(field);
        Preconditions.checkArgument(indexOf >= 0, "%s not a field of %s", field, this);
        return indexOf;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof DataType)) {
            return false;
        }
        DataType dataType = (DataType) obj;
        return this.name.equals(dataType.name) && this.zzbs.equals(dataType.zzbs);
    }

    public final int hashCode() {
        return this.name.hashCode();
    }

    public final String toString() {
        return String.format("DataType{%s%s}", this.name, this.zzbs);
    }

    public final String zzm() {
        return this.name.startsWith("com.google.") ? this.name.substring(11) : this.name;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 1, getName(), false);
        SafeParcelWriter.writeTypedList(parcel, 2, getFields(), false);
        SafeParcelWriter.writeString(parcel, 3, this.zzbt, false);
        SafeParcelWriter.writeString(parcel, 4, this.zzbu, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    static {
        DataType dataType = new DataType("com.google.step_count.delta", Scopes.FITNESS_ACTIVITY_READ, Scopes.FITNESS_ACTIVITY_READ_WRITE, Field.FIELD_STEPS);
        TYPE_STEP_COUNT_DELTA = dataType;
        TYPE_STEP_COUNT_CUMULATIVE = new DataType("com.google.step_count.cumulative", Scopes.FITNESS_ACTIVITY_READ, Scopes.FITNESS_ACTIVITY_READ_WRITE, Field.FIELD_STEPS);
        zzbc = new DataType("com.google.step_length", Scopes.FITNESS_ACTIVITY_READ, Scopes.FITNESS_ACTIVITY_READ_WRITE, Field.FIELD_STEP_LENGTH);
        TYPE_STEP_COUNT_CADENCE = new DataType("com.google.step_count.cadence", Scopes.FITNESS_ACTIVITY_READ, Scopes.FITNESS_ACTIVITY_READ_WRITE, Field.FIELD_RPM);
        zzbd = new DataType("com.google.internal.goal", Field.zzck);
        zzbe = new DataType("com.google.internal.prescription_event", Field.zzcl);
        zzbf = new DataType("com.google.internal.symptom", Field.zzcm);
        zzbg = new DataType("com.google.stride_model", Scopes.FITNESS_ACTIVITY_READ, Scopes.FITNESS_ACTIVITY_READ_WRITE, Field.zzcn);
        DataType dataType2 = new DataType("com.google.activity.segment", Scopes.FITNESS_ACTIVITY_READ, Scopes.FITNESS_ACTIVITY_READ_WRITE, Field.FIELD_ACTIVITY);
        TYPE_ACTIVITY_SEGMENT = dataType2;
        DataType dataType3 = new DataType("com.google.floor_change", Scopes.FITNESS_ACTIVITY_READ, Scopes.FITNESS_ACTIVITY_READ_WRITE, Field.FIELD_ACTIVITY, Field.FIELD_CONFIDENCE, Field.zzcp, Field.zzcs);
        zzbh = dataType3;
        DataType dataType4 = new DataType("com.google.calories.consumed", Scopes.FITNESS_ACTIVITY_READ, Scopes.FITNESS_ACTIVITY_READ_WRITE, Field.FIELD_CALORIES);
        TYPE_CALORIES_CONSUMED = dataType4;
        DataType dataType5 = new DataType("com.google.calories.expended", Scopes.FITNESS_ACTIVITY_READ, Scopes.FITNESS_ACTIVITY_READ_WRITE, Field.FIELD_CALORIES);
        TYPE_CALORIES_EXPENDED = dataType5;
        DataType dataType6 = new DataType("com.google.calories.bmr", Scopes.FITNESS_ACTIVITY_READ, Scopes.FITNESS_ACTIVITY_READ_WRITE, Field.FIELD_CALORIES);
        TYPE_BASAL_METABOLIC_RATE = dataType6;
        DataType dataType7 = new DataType("com.google.power.sample", Scopes.FITNESS_ACTIVITY_READ, Scopes.FITNESS_ACTIVITY_READ_WRITE, Field.FIELD_WATTS);
        TYPE_POWER_SAMPLE = dataType7;
        TYPE_ACTIVITY_SAMPLE = new DataType("com.google.activity.sample", Scopes.FITNESS_ACTIVITY_READ, Scopes.FITNESS_ACTIVITY_READ_WRITE, Field.FIELD_ACTIVITY, Field.FIELD_CONFIDENCE);
        TYPE_ACTIVITY_SAMPLES = new DataType("com.google.activity.samples", Scopes.FITNESS_ACTIVITY_READ, Scopes.FITNESS_ACTIVITY_READ_WRITE, Field.FIELD_ACTIVITY_CONFIDENCE);
        zzbi = new DataType("com.google.accelerometer", Scopes.FITNESS_ACTIVITY_READ, Scopes.FITNESS_ACTIVITY_READ_WRITE, Field.zza.zzde, Field.zza.zzdf, Field.zza.zzdg);
        zzbj = new DataType("com.google.sensor.events", Scopes.FITNESS_ACTIVITY_READ, Scopes.FITNESS_ACTIVITY_READ_WRITE, Field.zzcv, Field.zzcx, Field.zzdb);
        zzbk = new DataType("com.google.sensor.const_rate_events", Scopes.FITNESS_ACTIVITY_READ, Scopes.FITNESS_ACTIVITY_READ_WRITE, Field.zzcw, Field.zzcy, Field.zzcz, Field.zzda, Field.zzdb);
        DataType dataType8 = new DataType("com.google.heart_rate.bpm", Scopes.FITNESS_BODY_READ, Scopes.FITNESS_BODY_READ, Field.FIELD_BPM);
        TYPE_HEART_RATE_BPM = dataType8;
        DataType dataType9 = new DataType("com.google.location.sample", Scopes.FITNESS_LOCATION_READ, Scopes.FITNESS_LOCATION_READ_WRITE, Field.FIELD_LATITUDE, Field.FIELD_LONGITUDE, Field.FIELD_ACCURACY, Field.FIELD_ALTITUDE);
        TYPE_LOCATION_SAMPLE = dataType9;
        TYPE_LOCATION_TRACK = new DataType("com.google.location.track", Scopes.FITNESS_LOCATION_READ, Scopes.FITNESS_LOCATION_READ_WRITE, Field.FIELD_LATITUDE, Field.FIELD_LONGITUDE, Field.FIELD_ACCURACY, Field.FIELD_ALTITUDE);
        DataType dataType10 = new DataType("com.google.distance.delta", Scopes.FITNESS_LOCATION_READ, Scopes.FITNESS_LOCATION_READ_WRITE, Field.FIELD_DISTANCE);
        TYPE_DISTANCE_DELTA = dataType10;
        TYPE_DISTANCE_CUMULATIVE = new DataType("com.google.distance.cumulative", Scopes.FITNESS_LOCATION_READ, Scopes.FITNESS_LOCATION_READ_WRITE, Field.FIELD_DISTANCE);
        DataType dataType11 = new DataType("com.google.speed", Scopes.FITNESS_LOCATION_READ, Scopes.FITNESS_LOCATION_READ_WRITE, Field.FIELD_SPEED);
        TYPE_SPEED = dataType11;
        TYPE_CYCLING_WHEEL_REVOLUTION = new DataType("com.google.cycling.wheel_revolution.cumulative", Scopes.FITNESS_LOCATION_READ, Scopes.FITNESS_LOCATION_READ_WRITE, Field.FIELD_REVOLUTIONS);
        TYPE_CYCLING_WHEEL_RPM = new DataType("com.google.cycling.wheel_revolution.rpm", Scopes.FITNESS_LOCATION_READ, Scopes.FITNESS_LOCATION_READ_WRITE, Field.FIELD_RPM);
        TYPE_CYCLING_PEDALING_CUMULATIVE = new DataType("com.google.cycling.pedaling.cumulative", Scopes.FITNESS_ACTIVITY_READ, Scopes.FITNESS_ACTIVITY_READ_WRITE, Field.FIELD_REVOLUTIONS);
        TYPE_CYCLING_PEDALING_CADENCE = new DataType("com.google.cycling.pedaling.cadence", Scopes.FITNESS_ACTIVITY_READ, Scopes.FITNESS_ACTIVITY_READ_WRITE, Field.FIELD_RPM);
        TYPE_HEIGHT = new DataType("com.google.height", Scopes.FITNESS_BODY_READ, Scopes.FITNESS_BODY_READ_WRITE, Field.FIELD_HEIGHT);
        DataType dataType12 = new DataType("com.google.weight", Scopes.FITNESS_BODY_READ, Scopes.FITNESS_BODY_READ_WRITE, Field.FIELD_WEIGHT);
        TYPE_WEIGHT = dataType12;
        DataType dataType13 = new DataType("com.google.body.fat.percentage", Scopes.FITNESS_BODY_READ, Scopes.FITNESS_BODY_READ_WRITE, Field.FIELD_PERCENTAGE);
        TYPE_BODY_FAT_PERCENTAGE = dataType13;
        DataType dataType14 = new DataType("com.google.body.waist.circumference", Scopes.FITNESS_BODY_READ, Scopes.FITNESS_BODY_READ_WRITE, Field.FIELD_CIRCUMFERENCE);
        zzbl = dataType14;
        DataType dataType15 = new DataType("com.google.body.hip.circumference", Scopes.FITNESS_BODY_READ, Scopes.FITNESS_BODY_READ_WRITE, Field.FIELD_CIRCUMFERENCE);
        zzbm = dataType15;
        DataType dataType16 = new DataType("com.google.nutrition", Scopes.FITNESS_NUTRITION_READ, Scopes.FITNESS_NUTRITION_READ_WRITE, Field.FIELD_NUTRIENTS, Field.FIELD_MEAL_TYPE, Field.FIELD_FOOD_ITEM);
        TYPE_NUTRITION = dataType16;
        DataType dataType17 = new DataType("com.google.hydration", Scopes.FITNESS_NUTRITION_READ, Scopes.FITNESS_NUTRITION_READ_WRITE, Field.FIELD_VOLUME);
        TYPE_HYDRATION = dataType17;
        TYPE_WORKOUT_EXERCISE = new DataType("com.google.activity.exercise", Scopes.FITNESS_ACTIVITY_READ, Scopes.FITNESS_ACTIVITY_READ_WRITE, Field.FIELD_EXERCISE, Field.FIELD_REPETITIONS, Field.FIELD_DURATION, Field.FIELD_RESISTANCE_TYPE, Field.FIELD_RESISTANCE);
        DataType dataType18 = new DataType("com.google.active_minutes", Scopes.FITNESS_ACTIVITY_READ, Scopes.FITNESS_ACTIVITY_READ_WRITE, Field.FIELD_DURATION);
        TYPE_MOVE_MINUTES = dataType18;
        AGGREGATE_MOVE_MINUTES = dataType18;
        zzbn = new DataType("com.google.device_on_body", Field.zzdc);
        zzbo = new DataType("com.google.internal.primary_device", Field.zzco);
        AGGREGATE_ACTIVITY_SUMMARY = new DataType("com.google.activity.summary", Scopes.FITNESS_ACTIVITY_READ, Scopes.FITNESS_ACTIVITY_READ_WRITE, Field.FIELD_ACTIVITY, Field.FIELD_DURATION, Field.FIELD_NUM_SEGMENTS);
        zzbp = new DataType("com.google.floor_change.summary", Scopes.FITNESS_ACTIVITY_READ, Scopes.FITNESS_ACTIVITY_READ_WRITE, Field.zzci, Field.zzcj, Field.zzcq, Field.zzcr, Field.zzct, Field.zzcu);
        AGGREGATE_BASAL_METABOLIC_RATE_SUMMARY = new DataType("com.google.calories.bmr.summary", Scopes.FITNESS_BODY_READ, Scopes.FITNESS_BODY_READ_WRITE, Field.FIELD_AVERAGE, Field.FIELD_MAX, Field.FIELD_MIN);
        AGGREGATE_STEP_COUNT_DELTA = dataType;
        AGGREGATE_DISTANCE_DELTA = dataType10;
        AGGREGATE_CALORIES_CONSUMED = dataType4;
        AGGREGATE_CALORIES_EXPENDED = dataType5;
        TYPE_HEART_POINTS = new DataType("com.google.heart_minutes", Scopes.FITNESS_ACTIVITY_READ, Scopes.FITNESS_ACTIVITY_READ_WRITE, Field.FIELD_INTENSITY);
        AGGREGATE_HEART_POINTS = new DataType("com.google.heart_minutes.summary", Scopes.FITNESS_ACTIVITY_READ, Scopes.FITNESS_ACTIVITY_READ_WRITE, Field.FIELD_INTENSITY, Field.FIELD_DURATION);
        AGGREGATE_HEART_RATE_SUMMARY = new DataType("com.google.heart_rate.summary", Scopes.FITNESS_BODY_READ, Scopes.FITNESS_BODY_READ_WRITE, Field.FIELD_AVERAGE, Field.FIELD_MAX, Field.FIELD_MIN);
        AGGREGATE_LOCATION_BOUNDING_BOX = new DataType("com.google.location.bounding_box", Scopes.FITNESS_LOCATION_READ, Scopes.FITNESS_LOCATION_READ_WRITE, Field.FIELD_LOW_LATITUDE, Field.FIELD_LOW_LONGITUDE, Field.FIELD_HIGH_LATITUDE, Field.FIELD_HIGH_LONGITUDE);
        AGGREGATE_POWER_SUMMARY = new DataType("com.google.power.summary", Scopes.FITNESS_ACTIVITY_READ, Scopes.FITNESS_ACTIVITY_READ_WRITE, Field.FIELD_AVERAGE, Field.FIELD_MAX, Field.FIELD_MIN);
        AGGREGATE_SPEED_SUMMARY = new DataType("com.google.speed.summary", Scopes.FITNESS_LOCATION_READ, Scopes.FITNESS_LOCATION_READ_WRITE, Field.FIELD_AVERAGE, Field.FIELD_MAX, Field.FIELD_MIN);
        AGGREGATE_BODY_FAT_PERCENTAGE_SUMMARY = new DataType("com.google.body.fat.percentage.summary", Scopes.FITNESS_BODY_READ, Scopes.FITNESS_BODY_READ_WRITE, Field.FIELD_AVERAGE, Field.FIELD_MAX, Field.FIELD_MIN);
        zzbq = new DataType("com.google.body.hip.circumference.summary", Scopes.FITNESS_BODY_READ, Scopes.FITNESS_BODY_READ_WRITE, Field.FIELD_AVERAGE, Field.FIELD_MAX, Field.FIELD_MIN);
        zzbr = new DataType("com.google.body.waist.circumference.summary", Scopes.FITNESS_BODY_READ, Scopes.FITNESS_BODY_READ_WRITE, Field.FIELD_AVERAGE, Field.FIELD_MAX, Field.FIELD_MIN);
        AGGREGATE_WEIGHT_SUMMARY = new DataType("com.google.weight.summary", Scopes.FITNESS_BODY_READ, Scopes.FITNESS_BODY_READ_WRITE, Field.FIELD_AVERAGE, Field.FIELD_MAX, Field.FIELD_MIN);
        AGGREGATE_HEIGHT_SUMMARY = new DataType("com.google.height.summary", Scopes.FITNESS_BODY_READ, Scopes.FITNESS_BODY_READ_WRITE, Field.FIELD_AVERAGE, Field.FIELD_MAX, Field.FIELD_MIN);
        AGGREGATE_NUTRITION_SUMMARY = new DataType("com.google.nutrition.summary", Scopes.FITNESS_NUTRITION_READ, Scopes.FITNESS_NUTRITION_READ_WRITE, Field.FIELD_NUTRIENTS, Field.FIELD_MEAL_TYPE);
        AGGREGATE_HYDRATION = dataType17;
        ArraySet arraySet = new ArraySet();
        AGGREGATE_INPUT_TYPES = arraySet;
        arraySet.add(dataType2);
        arraySet.add(dataType6);
        arraySet.add(dataType13);
        arraySet.add(dataType15);
        arraySet.add(dataType14);
        arraySet.add(dataType4);
        arraySet.add(dataType5);
        arraySet.add(dataType10);
        arraySet.add(dataType3);
        arraySet.add(dataType9);
        arraySet.add(dataType16);
        arraySet.add(dataType17);
        arraySet.add(dataType8);
        arraySet.add(dataType7);
        arraySet.add(dataType11);
        arraySet.add(dataType);
        arraySet.add(dataType12);
        CREATOR = new zzl();
    }
}
