package com.huawei.ui.device.declaration.xmlparser.parser;

import java.io.IOException;
import java.io.InputStream;
import org.xmlpull.v1.XmlPullParserException;

/* loaded from: classes6.dex */
public interface XmlParser<T> {
    T parse(InputStream inputStream) throws XmlPullParserException, IOException;
}
