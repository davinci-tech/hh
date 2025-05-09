package defpackage;

import android.content.Context;
import android.text.TextUtils;
import com.careforeyou.library.bean.CsFatScale;
import com.careforeyou.library.bean.RoleInfo;
import com.careforeyou.library.bean.WeightEntity;
import com.careforeyou.library.enums.Protocal_Type;
import com.careforeyou.library.utils.PrefsUtil;
import com.xshq.spring.utils.CouStru;
import java.text.DecimalFormatSymbols;
import java.util.Date;
import org.apache.commons.io.FilenameUtils;

/* loaded from: classes2.dex */
public class nj extends PrefsUtil {
    private static volatile nj c;
    private Context d;

    public static nj a(Context context) {
        if (c == null) {
            synchronized (nj.class) {
                if (c == null) {
                    c = new nj(context);
                }
            }
        }
        return c;
    }

    private nj(Context context) {
        super(context);
        this.d = context;
    }

    public WeightEntity c(CsFatScale csFatScale) {
        WeightEntity weightEntity = new WeightEntity();
        weightEntity.setWeight((float) csFatScale.getWeight());
        weightEntity.setWeight_time(of.a());
        float axunge = (float) csFatScale.getAxunge();
        if (csFatScale.getDeviceType() == 0 && csFatScale.getImpedance() == 0.0f && axunge == 0.0f) {
            axunge = -1.0f;
        }
        weightEntity.setAxunge(axunge);
        weightEntity.setBody_age((float) csFatScale.getAge());
        weightEntity.setBone((float) csFatScale.getBone());
        weightEntity.setMetabolism((float) csFatScale.getBmrC());
        weightEntity.setMuscle((float) csFatScale.getMuscle());
        weightEntity.setViscera((float) csFatScale.getVisceral_fat());
        weightEntity.setWater((float) csFatScale.getWater());
        weightEntity.setR1(csFatScale.getImpedance());
        weightEntity.setRn8(csFatScale.getRemark());
        weightEntity.setScaleweight(csFatScale.getScaleWeightC());
        weightEntity.setHeartRateMeasuringType(csFatScale.getHeartRateMeasuringType());
        weightEntity.setInsRateMeasuringType(csFatScale.getImpedanceMeasuringType());
        return weightEntity;
    }

    public WeightEntity d(float f, String str, float f2, Date date, int i) {
        WeightEntity weightEntity = new WeightEntity();
        weightEntity.setWeight(f);
        weightEntity.setScaleweight(str);
        weightEntity.setWeight_time(of.b(date == null ? new Date().getTime() : date.getTime()));
        weightEntity.setR1(f2);
        return weightEntity;
    }

    public void e(WeightEntity weightEntity, RoleInfo roleInfo, nk nkVar) {
        try {
            oa.e("WeighDataParser", "++fillFatWithSmoothImpedance+" + nkVar.b() + ",productId:" + nkVar.d());
            if (nkVar.b() != Protocal_Type.XIANGSHAN && nkVar.d() != 843465809) {
                weightEntity.setBmi(c(roleInfo.getHeight(), weightEntity.getWeight()));
                if (weightEntity.getR1() == 0.0f) {
                    d(weightEntity);
                } else {
                    if (weightEntity.getWeight() >= 20.0f && weightEntity.getBmi() >= 5.0f) {
                        int sex = roleInfo.getSex();
                        int age = roleInfo.getAge();
                        double weight = weightEntity.getWeight();
                        float height = roleInfo.getHeight();
                        if (age > 5) {
                            float f = (float) weight;
                            byte b = (byte) sex;
                            ne neVar = new ne(height, f, b, age, weightEntity.getR1());
                            weightEntity.setAxunge(neVar.e());
                            weightEntity.setViscera(neVar.k());
                            weightEntity.setWater(neVar.o());
                            weightEntity.setBone(neVar.i());
                            weightEntity.setMuscle(neVar.e(0.0f));
                            weightEntity.setMetabolism(neVar.a());
                            weightEntity.setBody_age(neVar.d());
                            weightEntity.setScore((int) neVar.h());
                            weightEntity.setBw(neVar.b());
                            weightEntity.setProtein(ne.a(f, b, weightEntity.getAxunge(), weightEntity.getWater()));
                            float weight2 = (weightEntity.getWeight() * weightEntity.getMuscle()) / 100.0f;
                            int b2 = b(this.d, roleInfo, weightEntity);
                            float weight3 = weightEntity.getWeight();
                            float axunge = weightEntity.getAxunge() / 100.0f;
                            int e = ne.e(weightEntity.getRn8());
                            weightEntity.setMuscleWeight(weight2);
                            weightEntity.setBodyLeve(d(b2));
                            weightEntity.setThinWeight(weight3 * (1.0f - axunge));
                            weightEntity.setHeart(e);
                        } else {
                            d(weightEntity);
                        }
                    }
                    weightEntity.setR1(0.0f);
                    weightEntity.setRn8("");
                    d(weightEntity);
                }
            }
            d(weightEntity, roleInfo, nkVar);
        } catch (RuntimeException e2) {
            throw e2;
        } catch (Exception e3) {
            e3.printStackTrace();
        }
    }

    private void d(WeightEntity weightEntity, RoleInfo roleInfo, nk nkVar) {
        double d;
        String str;
        boolean z;
        double d2;
        double aCBodyHydro;
        double d3;
        int sex = roleInfo.getSex();
        int age = roleInfo.getAge();
        float height = roleInfo.getHeight();
        double weight = weightEntity.getWeight();
        int r1 = (int) weightEntity.getR1();
        oa.e("WeighDataParser", "weight:" + weight + "，gender:" + sex + "，age:" + age + "，height:" + height + "，bodyImpedance:" + r1);
        double d4 = (weight * 10000.0d) / ((double) (height * height));
        if (age >= 10 && age <= 100) {
            double d5 = height;
            if (d5 >= 100.0d && d5 <= 250.0d && weight >= 30.0d && weight <= 200.0d) {
                boolean z2 = (TextUtils.isEmpty(nkVar.c()) || !nkVar.c().endsWith("A")) && nkVar.d() != 843465809;
                oa.e("WeighDataParser", "+useDC+" + z2);
                if (z2) {
                    d = d5;
                    str = "数据无效，测量脂肪失败";
                    z = z2;
                    d2 = d4;
                    double dCBodyFat = CouStru.getDCBodyFat(sex, age, d5, weight, r1);
                    d3 = dCBodyFat;
                    aCBodyHydro = CouStru.getDCBodyHydro(sex, dCBodyFat);
                } else {
                    d = d5;
                    str = "数据无效，测量脂肪失败";
                    z = z2;
                    d2 = d4;
                    double d6 = r1;
                    double aCBodyFat = CouStru.getACBodyFat(sex, age, d5, weight, d6);
                    aCBodyHydro = CouStru.getACBodyHydro(sex, d, weight, d6);
                    d3 = aCBodyFat;
                }
                oa.e("WeighDataParser", "bodyFat:" + d3 + "，bodyHydro:" + aCBodyHydro);
                weightEntity.setBmi((float) d2);
                if (d3 < 4.0d || d3 > 75.0d || aCBodyHydro < 0.0d) {
                    oa.e("WeighDataParser", str);
                    d(weightEntity);
                    return;
                }
                double d7 = d;
                double d8 = aCBodyHydro;
                double d9 = d3;
                double aCBodyVisceralFat = CouStru.getACBodyVisceralFat(sex, age, d7, weight, r1, z);
                double aCBodyMuscle = CouStru.getACBodyMuscle(sex, d9);
                double aCBodyBone = (CouStru.getACBodyBone(sex, d9) * weight) / 100.0d;
                double bodyBMR = CouStru.getBodyBMR(sex, age, d7, weight);
                double aCBodyBoneMuscle = CouStru.getACBodyBoneMuscle(sex, d8, d9);
                double aCBodyProtein = CouStru.getACBodyProtein(d8, aCBodyMuscle, d9);
                double aCBodyAge = CouStru.getACBodyAge(sex, age, d9, aCBodyMuscle, aCBodyVisceralFat, 0, z);
                double aCBodyHealth = CouStru.getACBodyHealth(sex, d9);
                double aCBodyFigure = CouStru.getACBodyFigure(sex, age, d, weight, d9, z);
                weightEntity.setAxunge((float) d9);
                weightEntity.setViscera((float) aCBodyVisceralFat);
                weightEntity.setWater((float) d8);
                weightEntity.setBone((float) aCBodyBone);
                weightEntity.setMuscle((float) aCBodyMuscle);
                weightEntity.setMetabolism((float) bodyBMR);
                weightEntity.setBody_age((float) aCBodyAge);
                weightEntity.setScore((int) aCBodyHealth);
                weightEntity.setBw((float) (((d * 22.0d) * d) / 10000.0d));
                weightEntity.setProtein((float) aCBodyProtein);
                weightEntity.setMuscleWeight((float) ((weight * aCBodyBoneMuscle) / 100.0d));
                weightEntity.setBodyLeve(d((int) aCBodyFigure));
                weightEntity.setThinWeight((float) (((100.0d - d9) * weight) / 100.0d));
                weightEntity.setHeart(ne.e(weightEntity.getRn8()));
                return;
            }
        }
        oa.e("WeighDataParser", "数据无效，测量脂肪失败");
        d(weightEntity);
    }

    private void d(WeightEntity weightEntity) {
        weightEntity.setAxunge(0.0f);
        weightEntity.setViscera(0.0f);
        weightEntity.setWater(0.0f);
        weightEntity.setBone(0.0f);
        weightEntity.setMuscle(0.0f);
        weightEntity.setMetabolism(0.0f);
        weightEntity.setBody_age(0.0f);
        weightEntity.setScore(0);
        weightEntity.setBw(0.0f);
        weightEntity.setMuscleWeight(0.0f);
        weightEntity.setProtein(0.0f);
        weightEntity.setBodyLeve("");
        weightEntity.setThinWeight(0.0f);
        weightEntity.setHeart(0);
    }

    public static float c(int i, double d) {
        if (i == 0 || ((int) d) == 0) {
            return 0.0f;
        }
        new DecimalFormatSymbols().setDecimalSeparator(FilenameUtils.EXTENSION_SEPARATOR);
        double d2 = i / 100.0d;
        return oe.b((float) (d / (d2 * d2)));
    }

    public static int b(Context context, RoleInfo roleInfo, WeightEntity weightEntity) {
        float a2;
        int age = roleInfo.getAge();
        ne neVar = new ne(roleInfo.getHeight(), weightEntity.getWeight(), (byte) roleInfo.getSex(), age, weightEntity.getR1());
        if (weightEntity.getR1() > 0.0f && age > 5) {
            a2 = neVar.l();
        } else {
            if (weightEntity.getAxunge() <= 0.0f) {
                return -1;
            }
            a2 = ne.a(neVar.j(), weightEntity.getWeight(), neVar.g(), age, weightEntity.getAxunge());
        }
        return ((int) a2) + 1;
    }

    private String d(int i) {
        if (i == 1) {
            return "消瘦";
        }
        return i == 2 ? "标准" : i == 3 ? "隐形肥胖" : i == 4 ? "肌肉型肥胖" : "肥胖";
    }
}
