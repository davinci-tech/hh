package com.huawei.secure.android.common.xml;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

/* loaded from: classes6.dex */
public class DocumentBuilderFactorySecurity {
    public static DocumentBuilderFactory getInstance() throws ParserConfigurationException, NullPointerException {
        DocumentBuilderFactory newInstance = DocumentBuilderFactory.newInstance();
        newInstance.setFeature("http://xml.org/sax/features/namespaces", true);
        newInstance.setFeature("http://xml.org/sax/features/validation", false);
        newInstance.setExpandEntityReferences(false);
        return newInstance;
    }
}
