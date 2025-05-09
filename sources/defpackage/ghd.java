package defpackage;

import android.graphics.drawable.Drawable;
import com.huawei.health.R;
import com.huawei.health.sport.model.WorkoutRecord;
import com.huawei.health.suggestion.model.fitness.FitnessAchieveInfoUseCase;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import java.util.HashSet;
import java.util.List;

/* loaded from: classes4.dex */
public class ghd {

    /* renamed from: a, reason: collision with root package name */
    private long f12802a;
    private long d;

    public ghd(long j) {
        this.f12802a = j;
    }

    public void b(long j) {
        this.d = j;
    }

    public int d(long j) {
        if (j - this.d < this.f12802a) {
            return 2;
        }
        this.d = j;
        return 1;
    }

    public static String a(int i) {
        long j = i;
        if (j == -4) {
            return "";
        }
        if (j == -2) {
            return arx.b().getString(R.string._2130846128_res_0x7f0221b0);
        }
        if (j == -3) {
            return arx.b().getString(R.string._2130846127_res_0x7f0221af);
        }
        if (i == 0) {
            return arx.b().getString(R.string._2130846126_res_0x7f0221ae);
        }
        return arx.b().getResources().getQuantityString(R.plurals._2130903356_res_0x7f03013c, i, Integer.valueOf(i));
    }

    public static FitnessAchieveInfoUseCase a(List<WorkoutRecord> list) {
        if (!koq.c(list)) {
            return null;
        }
        HashSet hashSet = new HashSet();
        int i = 0;
        double d = 0.0d;
        double d2 = 0.0d;
        for (WorkoutRecord workoutRecord : list) {
            d += workoutRecord.getDuration();
            d2 += jdl.ac(workoutRecord.startTime()) ? workoutRecord.getDuration() : 0.0d;
            i++;
            hashSet.add(nsj.g(workoutRecord.startTime()));
        }
        FitnessAchieveInfoUseCase fitnessAchieveInfoUseCase = new FitnessAchieveInfoUseCase();
        fitnessAchieveInfoUseCase.setSumExerciseTime(d(d));
        fitnessAchieveInfoUseCase.setSumDays(hashSet.size());
        fitnessAchieveInfoUseCase.setTodayTime(d(d2));
        fitnessAchieveInfoUseCase.setSumExerciseCount(i);
        fitnessAchieveInfoUseCase.setTimeStamp(System.currentTimeMillis());
        return fitnessAchieveInfoUseCase;
    }

    private static int d(double d) {
        return (int) Math.ceil(d / 60000.0d);
    }

    public static void a(HealthTextView healthTextView) {
        int c = nsn.c(BaseApplication.getContext(), 23.0f);
        int c2 = nsn.c(BaseApplication.getContext(), 12.0f);
        Drawable drawable = BaseApplication.getContext().getResources().getDrawable(R.drawable._2131427540_res_0x7f0b00d4);
        drawable.setBounds(0, 0, c, c2);
        healthTextView.setCompoundDrawables(null, null, drawable, null);
    }
}
