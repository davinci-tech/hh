package com.huawei.featureuserprofile.route.hwgpxmodel.xmlparser;

import com.huawei.featureuserprofile.route.hwgpxmodel.xmlparser.utils.ReflectUtils;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;
import org.xmlpull.v1.XmlSerializer;

/* loaded from: classes3.dex */
public class Obj2XmlSerializer {
    public <T> void toXml(T t, OutputStream outputStream) throws IOException, XmlPullParserException {
        XmlSerializer newSerializer = XmlPullParserFactory.newInstance().newSerializer();
        newSerializer.setOutput(outputStream, "UTF-8");
        createObjTag(t, null, newSerializer);
        newSerializer.endDocument();
    }

    private <T> void createObjTag(T t, String str, XmlSerializer xmlSerializer) throws IOException {
        if (str == null) {
            str = getXmlName(t.getClass());
        }
        startTag(str, xmlSerializer);
        List<List<Field>> classTagAndAttrFields = getClassTagAndAttrFields(t.getClass());
        Iterator<Field> it = classTagAndAttrFields.get(0).iterator();
        while (it.hasNext()) {
            createAttribute(it.next(), t, xmlSerializer);
        }
        Iterator<Field> it2 = classTagAndAttrFields.get(1).iterator();
        while (it2.hasNext()) {
            createFieldTag(it2.next(), t, xmlSerializer);
        }
        endTag(str, xmlSerializer);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private <T> void createFieldTag(Field field, T t, XmlSerializer xmlSerializer) throws IOException {
        Object fieldValue = ReflectUtils.getFieldValue(t, field);
        if (fieldValue == null) {
            return;
        }
        if (!(fieldValue instanceof Double) || Math.abs(((Double) fieldValue).doubleValue() - Double.MIN_VALUE) >= 1.0E-6d) {
            String xmlName = getXmlName(field);
            if (ReflectUtils.isSimpleType(fieldValue.getClass())) {
                startTag(xmlName, String.valueOf(fieldValue), xmlSerializer);
                endTag(xmlName, xmlSerializer);
                return;
            }
            if (fieldValue instanceof List) {
                Iterator it = ((List) fieldValue).iterator();
                while (it.hasNext()) {
                    createObjTag(it.next(), null, xmlSerializer);
                }
            } else if (fieldValue instanceof Map) {
                handleTag(xmlSerializer, (Map) fieldValue);
            } else if (fieldValue instanceof Collection) {
                startTag(xmlName, fieldValue.toString(), xmlSerializer);
                endTag(xmlName, xmlSerializer);
            } else {
                createObjTag(fieldValue, xmlName, xmlSerializer);
            }
        }
    }

    private void handleTag(XmlSerializer xmlSerializer, Map map) throws IOException {
        for (Object obj : map.entrySet()) {
            if (obj instanceof Map.Entry) {
                Map.Entry entry = (Map.Entry) obj;
                String valueOf = String.valueOf(entry.getKey());
                startTag(valueOf, String.valueOf(entry.getValue()), xmlSerializer);
                endTag(valueOf, xmlSerializer);
            }
        }
    }

    private void startTag(String str, XmlSerializer xmlSerializer) throws IOException {
        startTag(str, null, xmlSerializer);
    }

    private void startTag(String str, String str2, XmlSerializer xmlSerializer) throws IOException {
        xmlSerializer.startTag(null, str);
        if (str2 != null) {
            xmlSerializer.text(str2);
        }
    }

    private <T> void createAttribute(Field field, T t, XmlSerializer xmlSerializer) throws IOException {
        String xmlName = getXmlName(field);
        Object fieldValue = ReflectUtils.getFieldValue(t, field);
        if (fieldValue == null) {
            return;
        }
        xmlSerializer.attribute(null, xmlName, String.valueOf(fieldValue));
    }

    private void endTag(String str, XmlSerializer xmlSerializer) throws IOException {
        xmlSerializer.endTag(null, str);
    }

    private String getXmlName(Class<?> cls) {
        Tag tag = (Tag) cls.getAnnotation(Tag.class);
        if (tag != null && tag.value() != null) {
            return tag.value();
        }
        return cls.getSimpleName();
    }

    private String getXmlName(Field field) {
        Tag tag = (Tag) field.getAnnotation(Tag.class);
        if (tag != null && tag.value() != null) {
            return tag.value();
        }
        Attribute attribute = (Attribute) field.getAnnotation(Attribute.class);
        if (attribute != null && attribute.value() != null) {
            return attribute.value();
        }
        return field.getName();
    }

    private List<List<Field>> getClassTagAndAttrFields(Class<?> cls) {
        List<Field> classFields = ReflectUtils.getClassFields(cls);
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        for (Field field : classFields) {
            if (field.getAnnotation(Ignore.class) == null) {
                if (field.getAnnotation(Attribute.class) != null) {
                    arrayList.add(field);
                } else {
                    arrayList2.add(field);
                }
            }
        }
        sortTagFields(arrayList2);
        return Arrays.asList(arrayList, arrayList2);
    }

    private void sortTagFields(List<Field> list) {
        Collections.sort(list, new Comparator<Field>() { // from class: com.huawei.featureuserprofile.route.hwgpxmodel.xmlparser.Obj2XmlSerializer.1
            @Override // java.util.Comparator
            public int compare(Field field, Field field2) {
                Tag tag = (Tag) field.getAnnotation(Tag.class);
                int order = tag != null ? tag.order() : 0;
                Tag tag2 = (Tag) field2.getAnnotation(Tag.class);
                return Integer.compare(order, tag2 != null ? tag2.order() : 0);
            }
        });
    }
}
