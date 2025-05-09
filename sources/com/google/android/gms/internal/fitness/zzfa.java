package com.google.android.gms.internal.fitness;

import com.google.android.gms.fitness.FitnessActivities;

/* loaded from: classes2.dex */
public final class zzfa {
    private static final String[] zzjb = {"in_vehicle", FitnessActivities.BIKING, "on_foot", "still", "unknown", "tilting", "exiting_vehicle", "walking", "running", "aerobics", "badminton", "baseball", "basketball", "biathlon", FitnessActivities.BIKING_HAND, FitnessActivities.BIKING_MOUNTAIN, FitnessActivities.BIKING_ROAD, FitnessActivities.BIKING_SPINNING, FitnessActivities.BIKING_STATIONARY, FitnessActivities.BIKING_UTILITY, "boxing", "calisthenics", "circuit_training", "cricket", "dancing", "elliptical", "fencing", "football.american", "football.australian", "football.soccer", FitnessActivities.FRISBEE_DISC, "gardening", FitnessActivities.GOLF, "gymnastics", "handball", "hiking", "hockey", FitnessActivities.HORSEBACK_RIDING, "housework", FitnessActivities.JUMP_ROPE, "kayaking", "kettlebell_training", "kickboxing", "kitesurfing", "martial_arts", "meditation", "martial_arts.mixed", "p90x", "paragliding", "pilates", "polo", "racquetball", "rock_climbing", "rowing", "rowing.machine", "rugby", FitnessActivities.RUNNING_JOGGING, FitnessActivities.RUNNING_SAND, FitnessActivities.RUNNING_TREADMILL, "sailing", "scuba_diving", "skateboarding", "skating", FitnessActivities.SKATING_CROSS, FitnessActivities.SKATING_INLINE, "skiing", FitnessActivities.SKIING_BACK_COUNTRY, FitnessActivities.SKIING_CROSS_COUNTRY, FitnessActivities.SKIING_DOWNHILL, FitnessActivities.SKIING_KITE, FitnessActivities.SKIING_ROLLER, "sledding", "sleep", "snowboarding", "snowmobile", "snowshoeing", "squash", "stair_climbing", "stair_climbing.machine", "standup_paddleboarding", "strength_training", "surfing", "swimming", "swimming.pool", "swimming.open_water", "table_tennis", "team_sports", "tennis", "treadmill", "volleyball", FitnessActivities.VOLLEYBALL_BEACH, FitnessActivities.VOLLEYBALL_INDOOR, "wakeboarding", FitnessActivities.WALKING_FITNESS, FitnessActivities.WALKING_NORDIC, FitnessActivities.WALKING_TREADMILL, "water_polo", "weightlifting", "wheelchair", "windsurfing", "yoga", "zumba", "diving", "ergometer", "ice_skating", FitnessActivities.SKATING_INDOOR, "curling", FitnessActivities.KICK_SCOOTER, "other", "sleep.light", "sleep.deep", "sleep.rem", "sleep.awake", "crossfit", "interval_training.high_intensity", "interval_training", FitnessActivities.WALKING_STROLLER, "elevator", "escalator", "archery", "softball", "flossing"};

    public static int zzl(String str) {
        int i = 0;
        while (true) {
            String[] strArr = zzjb;
            if (i >= strArr.length) {
                return 4;
            }
            if (strArr[i].equals(str)) {
                return i;
            }
            i++;
        }
    }

    public static String getName(int i) {
        String str;
        if (i >= 0) {
            String[] strArr = zzjb;
            return (i < strArr.length && (str = strArr[i]) != null) ? str : "unknown";
        }
        return "unknown";
    }

    public static String getMimeType(String str) {
        String valueOf = String.valueOf(str);
        return valueOf.length() != 0 ? FitnessActivities.MIME_TYPE_PREFIX.concat(valueOf) : new String(FitnessActivities.MIME_TYPE_PREFIX);
    }
}
