package defpackage;

import com.google.gson.annotations.SerializedName;
import com.huawei.hwdevice.phoneprocess.mgr.exercise.HwExerciseConstants;
import java.util.Objects;

/* loaded from: classes6.dex */
public class omi {

    @SerializedName("onlineDate")
    private String c;

    @SerializedName(HwExerciseConstants.METHOD_PARAM_WORKOUT_ID)
    private String d;

    public String c() {
        return this.c;
    }

    public String toString() {
        return "TimeDaoBean{workoutId='" + this.d + "', onlineDate='" + this.c + "'}";
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        omi omiVar = (omi) obj;
        return Objects.equals(this.d, omiVar.d) && Objects.equals(this.c, omiVar.c);
    }

    public int hashCode() {
        return Objects.hash(this.d, this.c);
    }
}
