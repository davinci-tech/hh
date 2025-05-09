package com.huawei.featureuserprofile.route.navigationgenerator;

import com.huawei.featureuserprofile.route.hwgpxmodel.xmlparser.Obj2XmlSerializer;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.route.TrackInfoModel;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import org.xmlpull.v1.XmlPullParserException;

/* loaded from: classes7.dex */
public abstract class BaseGenerator<T> {
    private static final String GPX = "gpx";
    private static final String KML = "kml";
    private static final String TAG = "BaseGenerator";
    private static final String TCX = "tcx";

    public static File makeTrackFile(TrackInfoModel trackInfoModel, String str, String str2) {
        char c;
        str.hashCode();
        int hashCode = str.hashCode();
        if (hashCode == 102575) {
            if (str.equals("gpx")) {
                c = 0;
            }
            c = 65535;
        } else if (hashCode != 106314) {
            if (hashCode == 114665 && str.equals("tcx")) {
                c = 2;
            }
            c = 65535;
        } else {
            if (str.equals("kml")) {
                c = 1;
            }
            c = 65535;
        }
        if (c == 0) {
            return new GpxFileGenerator().makeGpxFile(trackInfoModel, str2);
        }
        if (c == 1) {
            return new KmlFileGenerator().makeKmlFile(trackInfoModel, str2);
        }
        if (c == 2) {
            return new TcxFileGenerator().makeTcxFile(trackInfoModel, str2);
        }
        LogUtil.b(TAG, "File generation in this format is not supported.");
        return null;
    }

    protected File makeFile(T t, String str) {
        Obj2XmlSerializer obj2XmlSerializer = new Obj2XmlSerializer();
        File file = new File(str);
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file, true);
            try {
                BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream);
                try {
                    bufferedOutputStream.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>".getBytes(Charset.defaultCharset()));
                    obj2XmlSerializer.toXml(t, bufferedOutputStream);
                    bufferedOutputStream.close();
                    fileOutputStream.close();
                    return file;
                } finally {
                }
            } catch (Throwable th) {
                try {
                    fileOutputStream.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
                throw th;
            }
        } catch (IOException | XmlPullParserException unused) {
            if (!file.exists() || file.delete()) {
                return null;
            }
            LogUtil.b(TAG, "delete trackFile error.");
            return null;
        }
    }
}
