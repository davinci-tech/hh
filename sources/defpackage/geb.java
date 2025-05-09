package defpackage;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.huawei.health.suggestion.model.fitness.FitnessAchieveInfoUseCase;
import com.huawei.hihealth.HiHealthData;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/* loaded from: classes8.dex */
public class geb {

    /* renamed from: a, reason: collision with root package name */
    @SerializedName("tahitiPicture")
    @Expose
    private String f12778a;

    @SerializedName("gridContents")
    @Expose
    private ArrayList<gdx> b;

    @SerializedName("buttonPicture")
    @Expose
    private String c;

    @SerializedName("picture")
    @Expose
    private String e;

    @Expose
    private gee g = new gee();
    private List<HiHealthData> j = new ArrayList();

    @Expose
    private FitnessAchieveInfoUseCase d = new FitnessAchieveInfoUseCase();

    public gee d() {
        return this.g;
    }

    public void c(gee geeVar) {
        this.g = geeVar;
    }

    public ArrayList<gdx> c() {
        ArrayList<gdx> arrayList = this.b;
        return arrayList != null ? arrayList : new ArrayList<>();
    }

    public List<HiHealthData> e() {
        return this.j;
    }

    public void a(List<HiHealthData> list) {
        this.j.clear();
        this.j.addAll(list);
    }

    public FitnessAchieveInfoUseCase a() {
        return this.d;
    }

    public void e(FitnessAchieveInfoUseCase fitnessAchieveInfoUseCase) {
        this.d = fitnessAchieveInfoUseCase;
    }

    public List<String> b() {
        return new ArrayList(Arrays.asList(this.e, this.f12778a));
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        geb gebVar = (geb) obj;
        return Objects.equals(this.c, gebVar.c) && Objects.equals(this.e, gebVar.e) && Objects.equals(this.f12778a, gebVar.f12778a) && Objects.equals(this.b, gebVar.b) && Objects.equals(this.g, gebVar.g) && Objects.equals(this.d, gebVar.d);
    }

    public int hashCode() {
        return Objects.hash(this.c, this.e, this.f12778a, this.b, this.g, this.d);
    }

    public String toString() {
        return "AchieveBean{mBtnPicture='" + this.c + "', mBackground='" + this.e + "', mBackgroundTahiti='" + this.f12778a + "', mAchieveBannerBeans=" + this.b + ", mData=" + this.g + ", mHealthData=" + this.j + ", mAchieveInfoUseCase=" + this.d + '}';
    }
}
