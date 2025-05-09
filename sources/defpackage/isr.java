package defpackage;

import android.content.Context;
import android.text.TextUtils;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.dictionary.HiHealthDictManager;
import com.huawei.hihealth.dictionary.model.HiHealthDictField;
import com.huawei.hihealth.dictionary.utils.DicDataTypeUtil;
import com.huawei.hihealthservice.sync.dataswitch.HiHealthDataBuildService;
import com.huawei.hwcloudmodel.model.samplepoint.SamplePoint;
import com.huawei.hwcloudmodel.model.unite.HealthDetail;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes7.dex */
public class isr {
    public static HiHealthDataBuildService e(SamplePoint samplePoint) {
        String value = samplePoint.getValue();
        String fieldsMetadata = samplePoint.getFieldsMetadata();
        String fieldsModifyTime = samplePoint.getFieldsModifyTime();
        LogUtil.c("HiH_HiHealthDataBuildFactory", "value = ", value, ", metadata = ", fieldsMetadata, ", modifyTime = ", fieldsModifyTime);
        if (TextUtils.isEmpty(value) && TextUtils.isEmpty(fieldsMetadata)) {
            return new a();
        }
        if (TextUtils.isEmpty(value) && !TextUtils.isEmpty(fieldsMetadata)) {
            return new c(fieldsMetadata, fieldsModifyTime);
        }
        if (!TextUtils.isEmpty(value) && TextUtils.isEmpty(fieldsMetadata)) {
            return new d(value, fieldsModifyTime);
        }
        if (!TextUtils.isEmpty(value) && !TextUtils.isEmpty(fieldsMetadata)) {
            return new b(value, fieldsMetadata, fieldsModifyTime);
        }
        return new e();
    }

    static class a implements HiHealthDataBuildService {
        private a() {
        }

        @Override // com.huawei.hihealthservice.sync.dataswitch.HiHealthDataBuildService
        public List<HiHealthData> build(SamplePoint samplePoint, ikv ikvVar, HealthDetail healthDetail) {
            return new ArrayList();
        }
    }

    static class c implements HiHealthDataBuildService {

        /* renamed from: a, reason: collision with root package name */
        private String f13587a;
        private String c;

        c(String str, String str2) {
            this.f13587a = str;
            this.c = str2;
        }

        /* JADX WARN: Removed duplicated region for block: B:12:? A[RETURN, SYNTHETIC] */
        /* JADX WARN: Removed duplicated region for block: B:9:0x0055  */
        @Override // com.huawei.hihealthservice.sync.dataswitch.HiHealthDataBuildService
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public java.util.List<com.huawei.hihealth.HiHealthData> build(com.huawei.hwcloudmodel.model.samplepoint.SamplePoint r11, defpackage.ikv r12, com.huawei.hwcloudmodel.model.unite.HealthDetail r13) {
            /*
                r10 = this;
                org.json.JSONObject r5 = new org.json.JSONObject     // Catch: org.json.JSONException -> L39
                java.lang.String r0 = r10.f13587a     // Catch: org.json.JSONException -> L39
                r5.<init>(r0)     // Catch: org.json.JSONException -> L39
                int r0 = r5.length()     // Catch: org.json.JSONException -> L39
                java.util.ArrayList r8 = new java.util.ArrayList     // Catch: org.json.JSONException -> L39
                r8.<init>(r0)     // Catch: org.json.JSONException -> L39
                java.util.HashMap r9 = new java.util.HashMap     // Catch: org.json.JSONException -> L37
                r9.<init>(r0)     // Catch: org.json.JSONException -> L37
                r1 = r10
                r2 = r11
                r3 = r12
                r4 = r13
                r6 = r8
                r7 = r9
                r1.d(r2, r3, r4, r5, r6, r7)     // Catch: org.json.JSONException -> L37
                java.lang.String r0 = r10.c     // Catch: org.json.JSONException -> L37
                boolean r0 = android.text.TextUtils.isEmpty(r0)     // Catch: org.json.JSONException -> L37
                if (r0 != 0) goto L52
                org.json.JSONObject r5 = new org.json.JSONObject     // Catch: org.json.JSONException -> L37
                java.lang.String r0 = r10.c     // Catch: org.json.JSONException -> L37
                r5.<init>(r0)     // Catch: org.json.JSONException -> L37
                r1 = r10
                r2 = r11
                r3 = r12
                r4 = r13
                r6 = r8
                r7 = r9
                r1.b(r2, r3, r4, r5, r6, r7)     // Catch: org.json.JSONException -> L37
                goto L52
            L37:
                r0 = move-exception
                goto L3b
            L39:
                r0 = move-exception
                r8 = 0
            L3b:
                java.lang.Class r1 = r10.getClass()
                java.lang.String r1 = r1.getName()
                java.lang.String r2 = " exception "
                java.lang.String r0 = r0.getMessage()
                java.lang.Object[] r0 = new java.lang.Object[]{r1, r2, r0}
                java.lang.String r1 = "HiH_HiHealthDataBuildFactory"
                health.compact.a.hwlogsmodel.ReleaseLogUtil.d(r1, r0)
            L52:
                if (r8 == 0) goto L55
                goto L5a
            L55:
                java.util.ArrayList r8 = new java.util.ArrayList
                r8.<init>()
            L5a:
                return r8
            */
            throw new UnsupportedOperationException("Method not decompiled: isr.c.build(com.huawei.hwcloudmodel.model.samplepoint.SamplePoint, ikv, com.huawei.hwcloudmodel.model.unite.HealthDetail):java.util.List");
        }

        private void d(SamplePoint samplePoint, ikv ikvVar, HealthDetail healthDetail, JSONObject jSONObject, List<HiHealthData> list, Map<String, HiHealthData> map) throws JSONException {
            Iterator<String> keys = jSONObject.keys();
            while (keys.hasNext()) {
                String next = keys.next();
                if (!jSONObject.isNull(next)) {
                    if (!map.containsKey(next)) {
                        HiHealthDictField d = HiHealthDictManager.d((Context) null).d(healthDetail.getType().intValue(), next);
                        if (d == null) {
                            if (!"extendAttribute".equals(next)) {
                                LogUtil.b("HiH_HiHealthDataBuildFactory", "cloudType = ", healthDetail.getType(), ", key = ", next, " exception happen");
                            }
                        } else {
                            HiHealthData hiHealthData = new HiHealthData();
                            hiHealthData.setType(d.c());
                            hiHealthData.setMetaData(jSONObject.getString(next));
                            hiHealthData.setTimeInterval(samplePoint.getStartTime().longValue(), samplePoint.getEndTime().longValue());
                            hiHealthData.setTimeZone(healthDetail.getTimeZone());
                            ikv.b(hiHealthData, ikvVar);
                            list.add(hiHealthData);
                            map.put(next, hiHealthData);
                        }
                    } else {
                        map.get(next).setMetaData(jSONObject.getString(next));
                    }
                }
            }
        }

        private void b(SamplePoint samplePoint, ikv ikvVar, HealthDetail healthDetail, JSONObject jSONObject, List<HiHealthData> list, Map<String, HiHealthData> map) throws JSONException {
            Iterator<String> keys = jSONObject.keys();
            while (keys.hasNext()) {
                String next = keys.next();
                if (!jSONObject.isNull(next)) {
                    if (!map.containsKey(next)) {
                        HiHealthDictField d = HiHealthDictManager.d((Context) null).d(healthDetail.getType().intValue(), next);
                        if (d == null) {
                            if (!"extendAttribute".equals(next)) {
                                LogUtil.b("HiH_HiHealthDataBuildFactory", "cloudType = ", healthDetail.getType(), ", key = ", next, " exception happen");
                            }
                        } else {
                            int c = d.c();
                            HiHealthData hiHealthData = new HiHealthData();
                            hiHealthData.setType(c);
                            hiHealthData.setModifiedTime(jSONObject.getLong(next));
                            hiHealthData.setTimeInterval(samplePoint.getStartTime().longValue(), samplePoint.getEndTime().longValue());
                            hiHealthData.setTimeZone(healthDetail.getTimeZone());
                            ikv.b(hiHealthData, ikvVar);
                            list.add(hiHealthData);
                            map.put(next, hiHealthData);
                        }
                    } else {
                        map.get(next).setModifiedTime(jSONObject.getLong(next));
                    }
                }
            }
        }
    }

    static class d implements HiHealthDataBuildService {
        private String b;
        private String e;

        d(String str, String str2) {
            this.b = str;
            this.e = str2;
        }

        /* JADX WARN: Removed duplicated region for block: B:21:0x00b4  */
        /* JADX WARN: Removed duplicated region for block: B:23:? A[RETURN, SYNTHETIC] */
        @Override // com.huawei.hihealthservice.sync.dataswitch.HiHealthDataBuildService
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public java.util.List<com.huawei.hihealth.HiHealthData> build(com.huawei.hwcloudmodel.model.samplepoint.SamplePoint r14, defpackage.ikv r15, com.huawei.hwcloudmodel.model.unite.HealthDetail r16) {
            /*
                r13 = this;
                r8 = r13
                java.lang.String r0 = "WEIGHT_BODYFAT"
                java.lang.String r1 = r14.getKey()
                boolean r0 = r0.equals(r1)
                java.lang.String r9 = "HiH_HiHealthDataBuildFactory"
                if (r0 == 0) goto L5e
                java.util.ArrayList r0 = new java.util.ArrayList
                r1 = 1
                r0.<init>(r1)
                java.lang.String r1 = r14.getValue()     // Catch: java.lang.NumberFormatException -> L54
                java.lang.Double r1 = java.lang.Double.valueOf(r1)     // Catch: java.lang.NumberFormatException -> L54
                com.huawei.hihealth.HiHealthData r2 = new com.huawei.hihealth.HiHealthData
                r2.<init>()
                com.huawei.hihealth.dictionary.utils.DicDataTypeUtil$DataType r3 = com.huawei.hihealth.dictionary.utils.DicDataTypeUtil.DataType.BODY_FAT_RATE
                int r3 = r3.value()
                r2.setType(r3)
                java.lang.Long r3 = r14.getStartTime()
                long r3 = r3.longValue()
                java.lang.Long r5 = r14.getEndTime()
                long r5 = r5.longValue()
                r2.setTimeInterval(r3, r5)
                java.lang.String r3 = r16.getTimeZone()
                r2.setTimeZone(r3)
                double r3 = r1.doubleValue()
                r2.setValue(r3)
                r10 = r15
                defpackage.ikv.b(r2, r15)
                r0.add(r2)
                return r0
            L54:
                java.lang.String r1 = "WEIGHT_BODYFAT samplePoint.getValue() exception"
                java.lang.Object[] r1 = new java.lang.Object[]{r1}
                health.compact.a.hwlogsmodel.ReleaseLogUtil.e(r9, r1)
                return r0
            L5e:
                r10 = r15
                org.json.JSONObject r5 = new org.json.JSONObject     // Catch: org.json.JSONException -> L9a
                java.lang.String r0 = r8.b     // Catch: org.json.JSONException -> L9a
                r5.<init>(r0)     // Catch: org.json.JSONException -> L9a
                int r0 = r5.length()     // Catch: org.json.JSONException -> L9a
                java.util.ArrayList r11 = new java.util.ArrayList     // Catch: org.json.JSONException -> L9a
                r11.<init>(r0)     // Catch: org.json.JSONException -> L9a
                java.util.HashMap r12 = new java.util.HashMap     // Catch: org.json.JSONException -> L98
                r12.<init>(r0)     // Catch: org.json.JSONException -> L98
                r1 = r13
                r2 = r14
                r3 = r15
                r4 = r16
                r6 = r11
                r7 = r12
                r1.d(r2, r3, r4, r5, r6, r7)     // Catch: org.json.JSONException -> L98
                java.lang.String r0 = r8.e     // Catch: org.json.JSONException -> L98
                boolean r0 = android.text.TextUtils.isEmpty(r0)     // Catch: org.json.JSONException -> L98
                if (r0 != 0) goto Lb1
                org.json.JSONObject r5 = new org.json.JSONObject     // Catch: org.json.JSONException -> L98
                java.lang.String r0 = r8.e     // Catch: org.json.JSONException -> L98
                r5.<init>(r0)     // Catch: org.json.JSONException -> L98
                r1 = r13
                r2 = r14
                r3 = r15
                r4 = r16
                r6 = r11
                r7 = r12
                r1.b(r2, r3, r4, r5, r6, r7)     // Catch: org.json.JSONException -> L98
                goto Lb1
            L98:
                r0 = move-exception
                goto L9c
            L9a:
                r0 = move-exception
                r11 = 0
            L9c:
                java.lang.Class r1 = r13.getClass()
                java.lang.String r1 = r1.getName()
                java.lang.String r2 = " exception "
                java.lang.String r0 = r0.getMessage()
                java.lang.Object[] r0 = new java.lang.Object[]{r1, r2, r0}
                health.compact.a.hwlogsmodel.ReleaseLogUtil.d(r9, r0)
            Lb1:
                if (r11 == 0) goto Lb4
                goto Lb9
            Lb4:
                java.util.ArrayList r11 = new java.util.ArrayList
                r11.<init>()
            Lb9:
                return r11
            */
            throw new UnsupportedOperationException("Method not decompiled: isr.d.build(com.huawei.hwcloudmodel.model.samplepoint.SamplePoint, ikv, com.huawei.hwcloudmodel.model.unite.HealthDetail):java.util.List");
        }

        private void d(SamplePoint samplePoint, ikv ikvVar, HealthDetail healthDetail, JSONObject jSONObject, List<HiHealthData> list, Map<String, HiHealthData> map) throws JSONException {
            String string = (healthDetail.getType().intValue() != DicDataTypeUtil.DataType.WEIGHT_BODYFAT_BROAD.value() || jSONObject.isNull("extendAttribute")) ? null : jSONObject.getString("extendAttribute");
            Iterator<String> keys = jSONObject.keys();
            while (keys.hasNext()) {
                String next = keys.next();
                if (!jSONObject.isNull(next)) {
                    if (!map.containsKey(next)) {
                        HiHealthDictField d = HiHealthDictManager.d((Context) null).d(healthDetail.getType().intValue(), next);
                        if (d == null) {
                            if (!"extendAttribute".equals(next)) {
                                LogUtil.b("HiH_HiHealthDataBuildFactory", "cloudType = ", healthDetail.getType(), ", key = ", next, " exception happen");
                            }
                        } else {
                            int c = d.c();
                            HiHealthData hiHealthData = new HiHealthData();
                            hiHealthData.setType(c);
                            hiHealthData.setMetaData(string);
                            hiHealthData.setValue(jSONObject.getDouble(next));
                            hiHealthData.setTimeInterval(samplePoint.getStartTime().longValue(), samplePoint.getEndTime().longValue());
                            hiHealthData.setTimeZone(healthDetail.getTimeZone());
                            if ("conflictFlag".equals(next)) {
                                if (!jSONObject.isNull("conflictUserInfo")) {
                                    hiHealthData.setMetaData(jSONObject.getString("conflictUserInfo"));
                                } else {
                                    ReleaseLogUtil.e("HiH_HiHealthDataBuildFactory", "conflictUserInfo is null");
                                }
                            }
                            ikv.b(hiHealthData, ikvVar);
                            list.add(hiHealthData);
                            map.put(next, hiHealthData);
                        }
                    } else {
                        map.get(next).setValue(jSONObject.getDouble(next));
                    }
                }
            }
        }

        private void b(SamplePoint samplePoint, ikv ikvVar, HealthDetail healthDetail, JSONObject jSONObject, List<HiHealthData> list, Map<String, HiHealthData> map) throws JSONException {
            Iterator<String> keys = jSONObject.keys();
            while (keys.hasNext()) {
                String next = keys.next();
                if (!jSONObject.isNull(next)) {
                    if (!map.containsKey(next)) {
                        HiHealthDictField d = HiHealthDictManager.d((Context) null).d(healthDetail.getType().intValue(), next);
                        if (d == null) {
                            if (!"extendAttribute".equals(next)) {
                                LogUtil.b("HiH_HiHealthDataBuildFactory", "cloudType = ", healthDetail.getType(), ", key = ", next, " exception happen");
                            }
                        } else {
                            int c = d.c();
                            HiHealthData hiHealthData = new HiHealthData();
                            hiHealthData.setType(c);
                            hiHealthData.setModifiedTime(jSONObject.getLong(next));
                            hiHealthData.setTimeInterval(samplePoint.getStartTime().longValue(), samplePoint.getEndTime().longValue());
                            hiHealthData.setTimeZone(healthDetail.getTimeZone());
                            ikv.b(hiHealthData, ikvVar);
                            list.add(hiHealthData);
                            map.put(next, hiHealthData);
                        }
                    } else {
                        map.get(next).setModifiedTime(jSONObject.getLong(next));
                    }
                }
            }
        }
    }

    static class b implements HiHealthDataBuildService {

        /* renamed from: a, reason: collision with root package name */
        private String f13586a;
        private String b;
        private String c;

        b(String str, String str2, String str3) {
            this.f13586a = str;
            this.c = str2;
            this.b = str3;
        }

        /* JADX WARN: Removed duplicated region for block: B:12:? A[RETURN, SYNTHETIC] */
        /* JADX WARN: Removed duplicated region for block: B:9:0x006b  */
        @Override // com.huawei.hihealthservice.sync.dataswitch.HiHealthDataBuildService
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public java.util.List<com.huawei.hihealth.HiHealthData> build(com.huawei.hwcloudmodel.model.samplepoint.SamplePoint r11, defpackage.ikv r12, com.huawei.hwcloudmodel.model.unite.HealthDetail r13) {
            /*
                r10 = this;
                org.json.JSONObject r5 = new org.json.JSONObject     // Catch: org.json.JSONException -> L4f
                java.lang.String r0 = r10.f13586a     // Catch: org.json.JSONException -> L4f
                r5.<init>(r0)     // Catch: org.json.JSONException -> L4f
                org.json.JSONObject r0 = new org.json.JSONObject     // Catch: org.json.JSONException -> L4f
                java.lang.String r1 = r10.c     // Catch: org.json.JSONException -> L4f
                r0.<init>(r1)     // Catch: org.json.JSONException -> L4f
                int r1 = r5.length()     // Catch: org.json.JSONException -> L4f
                int r2 = r0.length()     // Catch: org.json.JSONException -> L4f
                int r1 = r1 + r2
                java.util.ArrayList r8 = new java.util.ArrayList     // Catch: org.json.JSONException -> L4f
                r8.<init>(r1)     // Catch: org.json.JSONException -> L4f
                java.util.HashMap r9 = new java.util.HashMap     // Catch: org.json.JSONException -> L4d
                r9.<init>(r1)     // Catch: org.json.JSONException -> L4d
                r1 = r10
                r2 = r11
                r3 = r12
                r4 = r13
                r6 = r8
                r7 = r9
                r1.d(r2, r3, r4, r5, r6, r7)     // Catch: org.json.JSONException -> L4d
                r1 = r10
                r2 = r11
                r3 = r12
                r4 = r13
                r5 = r0
                r6 = r8
                r7 = r9
                r1.b(r2, r3, r4, r5, r6, r7)     // Catch: org.json.JSONException -> L4d
                java.lang.String r0 = r10.b     // Catch: org.json.JSONException -> L4d
                boolean r0 = android.text.TextUtils.isEmpty(r0)     // Catch: org.json.JSONException -> L4d
                if (r0 != 0) goto L68
                org.json.JSONObject r5 = new org.json.JSONObject     // Catch: org.json.JSONException -> L4d
                java.lang.String r0 = r10.b     // Catch: org.json.JSONException -> L4d
                r5.<init>(r0)     // Catch: org.json.JSONException -> L4d
                r1 = r10
                r2 = r11
                r3 = r12
                r4 = r13
                r6 = r8
                r7 = r9
                r1.c(r2, r3, r4, r5, r6, r7)     // Catch: org.json.JSONException -> L4d
                goto L68
            L4d:
                r0 = move-exception
                goto L51
            L4f:
                r0 = move-exception
                r8 = 0
            L51:
                java.lang.Class r1 = r10.getClass()
                java.lang.String r1 = r1.getName()
                java.lang.String r2 = " exception "
                java.lang.String r0 = r0.getMessage()
                java.lang.Object[] r0 = new java.lang.Object[]{r1, r2, r0}
                java.lang.String r1 = "HiH_HiHealthDataBuildFactory"
                health.compact.a.hwlogsmodel.ReleaseLogUtil.d(r1, r0)
            L68:
                if (r8 == 0) goto L6b
                goto L70
            L6b:
                java.util.ArrayList r8 = new java.util.ArrayList
                r8.<init>()
            L70:
                return r8
            */
            throw new UnsupportedOperationException("Method not decompiled: isr.b.build(com.huawei.hwcloudmodel.model.samplepoint.SamplePoint, ikv, com.huawei.hwcloudmodel.model.unite.HealthDetail):java.util.List");
        }

        private void b(SamplePoint samplePoint, ikv ikvVar, HealthDetail healthDetail, JSONObject jSONObject, List<HiHealthData> list, Map<String, HiHealthData> map) throws JSONException {
            Iterator<String> keys = jSONObject.keys();
            while (keys.hasNext()) {
                String next = keys.next();
                if (!jSONObject.isNull(next)) {
                    if (!map.containsKey(next)) {
                        HiHealthDictField d = HiHealthDictManager.d((Context) null).d(healthDetail.getType().intValue(), next);
                        if (d == null) {
                            if (!"extendAttribute".equals(next)) {
                                LogUtil.b("HiH_HiHealthDataBuildFactory", "cloudType = ", healthDetail.getType(), ", key = ", next, " exception happen");
                            }
                        } else {
                            HiHealthData hiHealthData = new HiHealthData();
                            hiHealthData.setType(d.c());
                            hiHealthData.setMetaData(jSONObject.getString(next));
                            hiHealthData.setTimeInterval(samplePoint.getStartTime().longValue(), samplePoint.getEndTime().longValue());
                            hiHealthData.setTimeZone(healthDetail.getTimeZone());
                            ikv.b(hiHealthData, ikvVar);
                            list.add(hiHealthData);
                            map.put(next, hiHealthData);
                        }
                    } else {
                        map.get(next).setMetaData(jSONObject.getString(next));
                    }
                }
            }
        }

        private void d(SamplePoint samplePoint, ikv ikvVar, HealthDetail healthDetail, JSONObject jSONObject, List<HiHealthData> list, Map<String, HiHealthData> map) throws JSONException {
            Iterator<String> keys = jSONObject.keys();
            while (keys.hasNext()) {
                String next = keys.next();
                if (!jSONObject.isNull(next)) {
                    if (!map.containsKey(next)) {
                        HiHealthDictField d = HiHealthDictManager.d((Context) null).d(healthDetail.getType().intValue(), next);
                        if (d == null) {
                            if (!"extendAttribute".equals(next)) {
                                LogUtil.b("HiH_HiHealthDataBuildFactory", "cloudType = ", healthDetail.getType(), ", key = ", next, " exception happen");
                            }
                        } else {
                            int c = d.c();
                            HiHealthData hiHealthData = new HiHealthData();
                            hiHealthData.setType(c);
                            hiHealthData.setValue(jSONObject.getDouble(next));
                            hiHealthData.setTimeInterval(samplePoint.getStartTime().longValue(), samplePoint.getEndTime().longValue());
                            hiHealthData.setTimeZone(healthDetail.getTimeZone());
                            ikv.b(hiHealthData, ikvVar);
                            list.add(hiHealthData);
                            map.put(next, hiHealthData);
                        }
                    } else {
                        map.get(next).setValue(jSONObject.getDouble(next));
                    }
                }
            }
        }

        private void c(SamplePoint samplePoint, ikv ikvVar, HealthDetail healthDetail, JSONObject jSONObject, List<HiHealthData> list, Map<String, HiHealthData> map) throws JSONException {
            Iterator<String> keys = jSONObject.keys();
            while (keys.hasNext()) {
                String next = keys.next();
                if (!jSONObject.isNull(next)) {
                    if (!map.containsKey(next)) {
                        HiHealthDictField d = HiHealthDictManager.d((Context) null).d(healthDetail.getType().intValue(), next);
                        if (d == null) {
                            if (!"extendAttribute".equals(next)) {
                                LogUtil.b("HiH_HiHealthDataBuildFactory", "cloudType = ", healthDetail.getType(), ", key = ", next, " exception happen");
                            }
                        } else {
                            int c = d.c();
                            HiHealthData hiHealthData = new HiHealthData();
                            hiHealthData.setType(c);
                            hiHealthData.setModifiedTime(jSONObject.getLong(next));
                            hiHealthData.setTimeInterval(samplePoint.getStartTime().longValue(), samplePoint.getEndTime().longValue());
                            hiHealthData.setTimeZone(healthDetail.getTimeZone());
                            ikv.b(hiHealthData, ikvVar);
                            list.add(hiHealthData);
                            map.put(next, hiHealthData);
                        }
                    } else {
                        map.get(next).setModifiedTime(jSONObject.getLong(next));
                    }
                }
            }
        }
    }

    static class e implements HiHealthDataBuildService {
        private e() {
        }

        @Override // com.huawei.hihealthservice.sync.dataswitch.HiHealthDataBuildService
        public List<HiHealthData> build(SamplePoint samplePoint, ikv ikvVar, HealthDetail healthDetail) {
            return new ArrayList();
        }
    }
}
