package defpackage;

import com.huawei.health.device.open.data.model.HealthData;
import com.huawei.health.health.constants.ObserveLabels;
import com.huawei.hihealth.device.open.data.MeasureRecord;
import com.huawei.hihealth.device.open.data.MeasureResult;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes8.dex */
public class dic {
    private static dic e;
    private List<String> c = new ArrayList(10);
    private List<String> d = new ArrayList(10);

    public static dic c() {
        if (e == null) {
            e = new dic();
        }
        return e;
    }

    public dic() {
        this.c.add("BODY_WEIGHT");
        this.c.add("BODYFAT_RATE");
        this.d.add("BLOODPRESSURE_SYSTOLIC");
        this.d.add("BLOODPRESSURE_DIASTOLIC");
    }

    public HealthData d(MeasureResult measureResult) {
        MeasureRecord measureRecord;
        ArrayList<String> dataTypes;
        if (measureResult != null && measureResult.getRecords() != null && measureResult.getRecords().size() != 0 && (dataTypes = (measureRecord = measureResult.getRecords().get(0)).getDataTypes()) != null && !koq.b(dataTypes)) {
            if (dataTypes.containsAll(this.c) && this.c.containsAll(dataTypes)) {
                return d(measureRecord);
            }
            if (dataTypes.containsAll(this.d) && this.d.containsAll(dataTypes)) {
                return e(measureRecord);
            }
            if (b(dataTypes)) {
                der derVar = new der();
                Number value = measureRecord.getValue(ObserveLabels.HEART_RATE);
                if (dataTypes.contains(ObserveLabels.HEART_RATE) && value != null) {
                    derVar.setHeartRate(Integer.valueOf(value.intValue()));
                } else {
                    derVar.setHeartRate(-1);
                }
                return derVar;
            }
            if (dataTypes.contains("SEQUENCE_NUMBER")) {
                return d(measureRecord, dataTypes);
            }
        }
        return null;
    }

    private ckm d(MeasureRecord measureRecord) {
        ckm ckmVar = new ckm();
        Number value = this.c.size() >= 1 ? measureRecord.getValue(this.c.get(0)) : null;
        if (value != null) {
            ckmVar.setWeight(value.floatValue());
        }
        Number value2 = this.c.size() >= 2 ? measureRecord.getValue(this.c.get(1)) : null;
        if (value2 != null) {
            ckmVar.setBodyFatRat(value2.floatValue());
        }
        Date measureTime = measureRecord.getMeasureTime();
        if (measureTime != null) {
            ckmVar.setStartTime(measureTime.getTime());
            ckmVar.setEndTime(measureTime.getTime());
        }
        return ckmVar;
    }

    private deo e(MeasureRecord measureRecord) {
        deo deoVar = new deo();
        if ((this.d.size() >= 1 ? measureRecord.getValue(this.d.get(0)) : null) != null) {
            deoVar.setSystolic((short) r1.floatValue());
        }
        if ((this.d.size() >= 2 ? measureRecord.getValue(this.d.get(1)) : null) != null) {
            deoVar.setDiastolic((short) r2.floatValue());
        }
        Date measureTime = measureRecord.getMeasureTime();
        if (measureTime != null) {
            deoVar.setStartTime(measureTime.getTime());
            deoVar.setEndTime(measureTime.getTime());
        }
        return deoVar;
    }

    private deb d(MeasureRecord measureRecord, List<String> list) {
        float f;
        Iterator<String> it = list.iterator();
        while (true) {
            if (!it.hasNext()) {
                f = 0.0f;
                break;
            }
            String next = it.next();
            Number value = measureRecord.getValue(next);
            if (next.startsWith("BLOOD_SUGAR") && value != null) {
                f = value.floatValue();
                break;
            }
        }
        Number value2 = measureRecord.getValue("SEQUENCE_NUMBER");
        deb debVar = new deb();
        if (value2 != null) {
            int intValue = value2.intValue();
            debVar.setBloodSugar(f);
            Date measureTime = measureRecord.getMeasureTime();
            if (measureTime != null) {
                debVar.setStartTime(measureTime.getTime());
                debVar.setEndTime(measureTime.getTime());
            }
            debVar.setSequenceNumber(intValue);
        }
        return debVar;
    }

    private boolean b(List list) {
        return (list.contains(ObserveLabels.HEART_RATE) || list.contains("RRI_SQI_RESULT")) || (list.contains("ACC_RESULT") || list.contains("VOICE_PROMPTS_RESULT"));
    }
}
