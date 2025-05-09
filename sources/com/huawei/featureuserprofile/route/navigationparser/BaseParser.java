package com.huawei.featureuserprofile.route.navigationparser;

import android.util.Xml;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

/* loaded from: classes3.dex */
public abstract class BaseParser<T> {
    private static final String NAME_SPACE = null;
    private static final Map<String, String> TIME_PATTERN_REGEX = new LinkedHashMap<String, String>() { // from class: com.huawei.featureuserprofile.route.navigationparser.BaseParser.3
        {
            put("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", "\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}:\\d{2}\\.\\d{3}Z");
            put("yyyy-MM-dd'T'HH:mm:ss'Z'", "\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}:\\d{2}Z");
            put("yyyy-MM-dd'T'HH:mm:ss.SSSX", "\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}:\\d{2}\\.\\d{3}\\+\\d{2}:\\d{2}");
        }
    };
    private static final String UTC = "UTC";
    private static final int XML_START_LAYER = 1;

    protected boolean notEndTag(int i) {
        return (i == 3 || i == 1) ? false : true;
    }

    protected abstract T parse(XmlPullParser xmlPullParser) throws XmlPullParserException, IOException, ParseException;

    public T trackParseInput(InputStream inputStream) throws XmlPullParserException, IOException, ParseException {
        try {
            XmlPullParser newPullParser = Xml.newPullParser();
            newPullParser.setFeature("http://xmlpull.org/v1/doc/features.html#process-namespaces", true);
            newPullParser.setInput(inputStream, null);
            newPullParser.nextTag();
            return parse(newPullParser);
        } finally {
            inputStream.close();
        }
    }

    protected String readText(XmlPullParser xmlPullParser) throws IOException, XmlPullParserException {
        if (xmlPullParser.next() != 4) {
            return "";
        }
        String text = xmlPullParser.getText();
        xmlPullParser.nextTag();
        return text;
    }

    protected String readTextAndChildText(XmlPullParser xmlPullParser, String str) throws IOException, XmlPullParserException {
        xmlPullParser.require(2, NAME_SPACE, str);
        StringBuilder sb = new StringBuilder();
        while (notEndTag(xmlPullParser.next())) {
            if (xmlPullParser.getEventType() == 4) {
                sb.append(xmlPullParser.getText());
            } else {
                nextTag(xmlPullParser);
            }
        }
        xmlPullParser.require(3, NAME_SPACE, str);
        return sb.toString();
    }

    protected String readContent(XmlPullParser xmlPullParser, String str) throws XmlPullParserException, IOException {
        String str2 = NAME_SPACE;
        xmlPullParser.require(2, str2, str);
        String readText = readText(xmlPullParser);
        xmlPullParser.require(3, str2, str);
        return readText;
    }

    public long readTime(XmlPullParser xmlPullParser, String str) throws XmlPullParserException, IOException, ParseException {
        String str2 = NAME_SPACE;
        xmlPullParser.require(2, str2, str);
        long parseFileTime = parseFileTime(readText(xmlPullParser));
        xmlPullParser.require(3, str2, str);
        return parseFileTime;
    }

    private long parseFileTime(String str) throws ParseException {
        for (Map.Entry<String, String> entry : TIME_PATTERN_REGEX.entrySet()) {
            if (str.matches(entry.getValue())) {
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat(entry.getKey(), Locale.ROOT);
                simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
                Date parse = simpleDateFormat.parse(str);
                if (parse != null) {
                    return parse.getTime();
                }
            }
        }
        return 0L;
    }

    protected void nextTag(XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
        if (xmlPullParser.getEventType() != 2) {
            throw new IllegalStateException();
        }
        int i = 1;
        while (i != 0) {
            int next = xmlPullParser.next();
            if (next == 2) {
                i++;
            } else if (next == 3) {
                i--;
            }
        }
    }
}
