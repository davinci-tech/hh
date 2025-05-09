package org.apache.commons.io.input;

import com.huawei.agconnect.apms.instrument.URLConnectionInstrumentation;
import com.huawei.indoorequip.datastruct.MachineControlPointResponse;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.text.MessageFormat;
import java.util.Locale;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.io.ByteOrderMark;
import org.apache.commons.io.Charsets;
import org.apache.commons.io.build.AbstractStreamBuilder;
import org.apache.commons.io.input.BOMInputStream;

/* loaded from: classes10.dex */
public class XmlStreamReader extends Reader {
    private static final ByteOrderMark[] BOMS;
    private static final Pattern CHARSET_PATTERN;
    private static final String EBCDIC = "CP1047";
    public static final Pattern ENCODING_PATTERN;
    private static final String HTTP_EX_1 = "Illegal encoding, CT-MIME [{0}] CT-Enc [{1}] BOM [{2}] XML guess [{3}] XML prolog [{4}], BOM must be null";
    private static final String HTTP_EX_2 = "Illegal encoding, CT-MIME [{0}] CT-Enc [{1}] BOM [{2}] XML guess [{3}] XML prolog [{4}], encoding mismatch";
    private static final String HTTP_EX_3 = "Illegal encoding, CT-MIME [{0}] CT-Enc [{1}] BOM [{2}] XML guess [{3}] XML prolog [{4}], Illegal MIME";
    private static final String RAW_EX_1 = "Illegal encoding, BOM [{0}] XML guess [{1}] XML prolog [{2}] encoding mismatch";
    private static final String RAW_EX_2 = "Illegal encoding, BOM [{0}] XML guess [{1}] XML prolog [{2}] unknown BOM";
    private static final String US_ASCII;
    private static final String UTF_16;
    private static final String UTF_16BE;
    private static final String UTF_16LE;
    private static final String UTF_32 = "UTF-32";
    private static final String UTF_32BE = "UTF-32BE";
    private static final String UTF_32LE = "UTF-32LE";
    private static final String UTF_8;
    private static final ByteOrderMark[] XML_GUESS_BYTES;
    private final String defaultEncoding;
    private final String encoding;
    private final Reader reader;

    public static class Builder extends AbstractStreamBuilder<XmlStreamReader, Builder> {
        private String httpContentType;
        private boolean nullCharset = true;
        private boolean lenient = true;

        @Override // org.apache.commons.io.function.IOSupplier
        public XmlStreamReader get() throws IOException {
            String name = this.nullCharset ? null : getCharset().name();
            if (this.httpContentType == null) {
                return new XmlStreamReader(getInputStream(), this.lenient, name);
            }
            return new XmlStreamReader(getInputStream(), this.httpContentType, this.lenient, name);
        }

        @Override // org.apache.commons.io.build.AbstractStreamBuilder
        public Builder setCharset(Charset charset) {
            this.nullCharset = charset == null;
            return (Builder) super.setCharset(charset);
        }

        @Override // org.apache.commons.io.build.AbstractStreamBuilder
        public Builder setCharset(String str) {
            this.nullCharset = str == null;
            return (Builder) super.setCharset(Charsets.toCharset(str, getCharsetDefault()));
        }

        public Builder setHttpContentType(String str) {
            this.httpContentType = str;
            return this;
        }

        public Builder setLenient(boolean z) {
            this.lenient = z;
            return this;
        }
    }

    static {
        String name = StandardCharsets.UTF_8.name();
        UTF_8 = name;
        US_ASCII = StandardCharsets.US_ASCII.name();
        String name2 = StandardCharsets.UTF_16BE.name();
        UTF_16BE = name2;
        String name3 = StandardCharsets.UTF_16LE.name();
        UTF_16LE = name3;
        UTF_16 = StandardCharsets.UTF_16.name();
        BOMS = new ByteOrderMark[]{ByteOrderMark.UTF_8, ByteOrderMark.UTF_16BE, ByteOrderMark.UTF_16LE, ByteOrderMark.UTF_32BE, ByteOrderMark.UTF_32LE};
        XML_GUESS_BYTES = new ByteOrderMark[]{new ByteOrderMark(name, 60, 63, 120, 109), new ByteOrderMark(name2, 0, 60, 0, 63), new ByteOrderMark(name3, 60, 0, 63, 0), new ByteOrderMark(UTF_32BE, 0, 0, 0, 60, 0, 0, 0, 63, 0, 0, 0, 120, 0, 0, 0, 109), new ByteOrderMark(UTF_32LE, 60, 0, 0, 0, 63, 0, 0, 0, 120, 0, 0, 0, 109, 0, 0, 0), new ByteOrderMark(EBCDIC, 76, 111, MachineControlPointResponse.OP_CODE_EXTENSION_SET_TARGETED_EXPENDED_ENERGY, 148)};
        CHARSET_PATTERN = Pattern.compile("charset=[\"']?([.[^; \"']]*)[\"']?");
        ENCODING_PATTERN = Pattern.compile("^<\\?xml\\s+(?:version\\s*=\\s*(?:(?:\"1\\.[0-9]+\")|(?:'1.[0-9]+'))\\s+)??encoding\\s*=\\s*((?:\"[A-Za-z0-9][A-Za-z0-9._+:-]*\")|(?:'[A-Za-z0-9][A-Za-z0-9._+:-]*'))", 8);
    }

    public static Builder builder() {
        return new Builder();
    }

    static String getContentTypeEncoding(String str) {
        int indexOf;
        if (str == null || (indexOf = str.indexOf(";")) <= -1) {
            return null;
        }
        Matcher matcher = CHARSET_PATTERN.matcher(str.substring(indexOf + 1));
        String group = matcher.find() ? matcher.group(1) : null;
        if (group != null) {
            return group.toUpperCase(Locale.ROOT);
        }
        return null;
    }

    static String getContentTypeMime(String str) {
        if (str == null) {
            return null;
        }
        int indexOf = str.indexOf(";");
        if (indexOf >= 0) {
            str = str.substring(0, indexOf);
        }
        return str.trim();
    }

    /* JADX WARN: Code restructure failed: missing block: B:11:0x0039, code lost:
    
        throw new java.io.IOException("Unexpected end of XML stream");
     */
    /* JADX WARN: Code restructure failed: missing block: B:14:0x0052, code lost:
    
        throw new java.io.IOException("XML prolog or ROOT element not found on first " + r7 + " bytes");
     */
    /* JADX WARN: Code restructure failed: missing block: B:9:0x0030, code lost:
    
        if (r3 != (-1)) goto L12;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static java.lang.String getXmlProlog(java.io.InputStream r9, java.lang.String r10) throws java.io.IOException {
        /*
            if (r10 == 0) goto L99
            byte[] r0 = org.apache.commons.io.IOUtils.byteArray()
            r1 = 8192(0x2000, float:1.148E-41)
            r9.mark(r1)
            r2 = 0
            int r3 = r9.read(r0, r2, r1)
            r4 = -1
            java.lang.String r5 = ""
            r8 = r1
            r7 = r2
            r6 = r4
        L16:
            if (r3 == r4) goto L2e
            if (r6 != r4) goto L2e
            if (r7 >= r1) goto L2e
            int r7 = r7 + r3
            int r8 = r8 - r3
            int r3 = r9.read(r0, r7, r8)
            java.lang.String r5 = new java.lang.String
            r5.<init>(r0, r2, r7, r10)
            r6 = 62
            int r6 = r5.indexOf(r6)
            goto L16
        L2e:
            if (r6 != r4) goto L53
            if (r3 != r4) goto L3a
            java.io.IOException r9 = new java.io.IOException
            java.lang.String r10 = "Unexpected end of XML stream"
            r9.<init>(r10)
            throw r9
        L3a:
            java.io.IOException r9 = new java.io.IOException
            java.lang.StringBuilder r10 = new java.lang.StringBuilder
            java.lang.String r0 = "XML prolog or ROOT element not found on first "
            r10.<init>(r0)
            r10.append(r7)
            java.lang.String r0 = " bytes"
            r10.append(r0)
            java.lang.String r10 = r10.toString()
            r9.<init>(r10)
            throw r9
        L53:
            if (r7 <= 0) goto L99
            r9.reset()
            java.io.BufferedReader r9 = new java.io.BufferedReader
            java.io.StringReader r10 = new java.io.StringReader
            r0 = 1
            int r6 = r6 + r0
            java.lang.String r1 = r5.substring(r2, r6)
            r10.<init>(r1)
            r9.<init>(r10)
            java.lang.StringBuilder r10 = new java.lang.StringBuilder
            r10.<init>()
            java.util.stream.Stream r9 = r9.lines()
            org.apache.commons.io.input.XmlStreamReader$$ExternalSyntheticLambda0 r1 = new org.apache.commons.io.input.XmlStreamReader$$ExternalSyntheticLambda0
            r1.<init>()
            org.apache.commons.io.function.IOConsumer.forEach(r9, r1)
            java.util.regex.Pattern r9 = org.apache.commons.io.input.XmlStreamReader.ENCODING_PATTERN
            java.util.regex.Matcher r9 = r9.matcher(r10)
            boolean r10 = r9.find()
            if (r10 == 0) goto L99
            java.lang.String r9 = r9.group(r0)
            java.util.Locale r10 = java.util.Locale.ROOT
            java.lang.String r9 = r9.toUpperCase(r10)
            int r10 = r9.length()
            int r10 = r10 - r0
            java.lang.String r9 = r9.substring(r0, r10)
            goto L9a
        L99:
            r9 = 0
        L9a:
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.io.input.XmlStreamReader.getXmlProlog(java.io.InputStream, java.lang.String):java.lang.String");
    }

    static /* synthetic */ void lambda$getXmlProlog$0(StringBuilder sb, String str) throws IOException {
        sb.append(str);
        sb.append(' ');
    }

    static boolean isAppXml(String str) {
        return str != null && (str.equals("application/xml") || str.equals("application/xml-dtd") || str.equals("application/xml-external-parsed-entity") || (str.startsWith("application/") && str.endsWith("+xml")));
    }

    static boolean isTextXml(String str) {
        return str != null && (str.equals("text/xml") || str.equals("text/xml-external-parsed-entity") || (str.startsWith("text/") && str.endsWith("+xml")));
    }

    @Deprecated
    public XmlStreamReader(File file) throws IOException {
        this(((File) Objects.requireNonNull(file, "file")).toPath());
    }

    @Deprecated
    public XmlStreamReader(InputStream inputStream) throws IOException {
        this(inputStream, true);
    }

    @Deprecated
    public XmlStreamReader(InputStream inputStream, boolean z) throws IOException {
        this(inputStream, z, (String) null);
    }

    @Deprecated
    public XmlStreamReader(InputStream inputStream, boolean z, String str) throws IOException {
        this.defaultEncoding = str;
        BOMInputStream bOMInputStream = new BOMInputStream(new BufferedInputStream((InputStream) Objects.requireNonNull(inputStream, "inputStream"), 8192), false, BOMS);
        BOMInputStream bOMInputStream2 = new BOMInputStream(bOMInputStream, true, XML_GUESS_BYTES);
        String processHttpStream = processHttpStream(bOMInputStream, bOMInputStream2, z);
        this.encoding = processHttpStream;
        this.reader = new InputStreamReader(bOMInputStream2, processHttpStream);
    }

    @Deprecated
    public XmlStreamReader(InputStream inputStream, String str) throws IOException {
        this(inputStream, str, true);
    }

    @Deprecated
    public XmlStreamReader(InputStream inputStream, String str, boolean z) throws IOException {
        this(inputStream, str, z, null);
    }

    @Deprecated
    public XmlStreamReader(InputStream inputStream, String str, boolean z, String str2) throws IOException {
        this.defaultEncoding = str2;
        BOMInputStream bOMInputStream = new BOMInputStream(new BufferedInputStream((InputStream) Objects.requireNonNull(inputStream, "inputStream"), 8192), false, BOMS);
        BOMInputStream bOMInputStream2 = new BOMInputStream(bOMInputStream, true, XML_GUESS_BYTES);
        String processHttpStream = processHttpStream(bOMInputStream, bOMInputStream2, z, str);
        this.encoding = processHttpStream;
        this.reader = new InputStreamReader(bOMInputStream2, processHttpStream);
    }

    @Deprecated
    public XmlStreamReader(Path path) throws IOException {
        this(Files.newInputStream((Path) Objects.requireNonNull(path, "file"), new OpenOption[0]));
    }

    public XmlStreamReader(URL url) throws IOException {
        this(URLConnectionInstrumentation.openConnection(((URL) Objects.requireNonNull(url, "url")).openConnection()), (String) null);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public XmlStreamReader(URLConnection uRLConnection, String str) throws IOException {
        Objects.requireNonNull(uRLConnection, "urlConnection");
        this.defaultEncoding = str;
        String contentType = uRLConnection.getContentType();
        BOMInputStream bOMInputStream = ((BOMInputStream.Builder) BOMInputStream.builder().setInputStream(new BufferedInputStream(uRLConnection.getInputStream(), 8192))).setInclude(false).setByteOrderMarks(BOMS).get();
        BOMInputStream bOMInputStream2 = ((BOMInputStream.Builder) BOMInputStream.builder().setInputStream(new BufferedInputStream(bOMInputStream, 8192))).setInclude(true).setByteOrderMarks(XML_GUESS_BYTES).get();
        if ((uRLConnection instanceof HttpURLConnection) || contentType != null) {
            this.encoding = processHttpStream(bOMInputStream, bOMInputStream2, true, contentType);
        } else {
            this.encoding = processHttpStream(bOMInputStream, bOMInputStream2, true);
        }
        this.reader = new InputStreamReader(bOMInputStream2, this.encoding);
    }

    String calculateHttpEncoding(String str, String str2, String str3, boolean z, String str4) throws IOException {
        if (z && str3 != null) {
            return str3;
        }
        String contentTypeMime = getContentTypeMime(str4);
        String contentTypeEncoding = getContentTypeEncoding(str4);
        boolean isAppXml = isAppXml(contentTypeMime);
        boolean isTextXml = isTextXml(contentTypeMime);
        if (!isAppXml && !isTextXml) {
            throw new XmlStreamReaderException(MessageFormat.format(HTTP_EX_3, contentTypeMime, contentTypeEncoding, str, str2, str3), contentTypeMime, contentTypeEncoding, str, str2, str3);
        }
        if (contentTypeEncoding == null) {
            if (isAppXml) {
                return calculateRawEncoding(str, str2, str3);
            }
            String str5 = this.defaultEncoding;
            return str5 == null ? US_ASCII : str5;
        }
        if (contentTypeEncoding.equals(UTF_16BE) || contentTypeEncoding.equals(UTF_16LE)) {
            if (str == null) {
                return contentTypeEncoding;
            }
            throw new XmlStreamReaderException(MessageFormat.format(HTTP_EX_1, contentTypeMime, contentTypeEncoding, str, str2, str3), contentTypeMime, contentTypeEncoding, str, str2, str3);
        }
        String str6 = UTF_16;
        if (contentTypeEncoding.equals(str6)) {
            if (str == null || !str.startsWith(str6)) {
                throw new XmlStreamReaderException(MessageFormat.format(HTTP_EX_2, contentTypeMime, contentTypeEncoding, str, str2, str3), contentTypeMime, contentTypeEncoding, str, str2, str3);
            }
            return str;
        }
        if (contentTypeEncoding.equals(UTF_32BE) || contentTypeEncoding.equals(UTF_32LE)) {
            if (str == null) {
                return contentTypeEncoding;
            }
            throw new XmlStreamReaderException(MessageFormat.format(HTTP_EX_1, contentTypeMime, contentTypeEncoding, str, str2, str3), contentTypeMime, contentTypeEncoding, str, str2, str3);
        }
        if (!contentTypeEncoding.equals(UTF_32)) {
            return contentTypeEncoding;
        }
        if (str == null || !str.startsWith(UTF_32)) {
            throw new XmlStreamReaderException(MessageFormat.format(HTTP_EX_2, contentTypeMime, contentTypeEncoding, str, str2, str3), contentTypeMime, contentTypeEncoding, str, str2, str3);
        }
        return str;
    }

    String calculateRawEncoding(String str, String str2, String str3) throws IOException {
        if (str == null) {
            if (str2 != null && str3 != null) {
                return (str3.equals(UTF_16) && (str2.equals(UTF_16BE) || str2.equals(UTF_16LE))) ? str2 : str3;
            }
            String str4 = this.defaultEncoding;
            return str4 == null ? UTF_8 : str4;
        }
        String str5 = UTF_8;
        if (str.equals(str5)) {
            if (str2 != null && !str2.equals(str5)) {
                throw new XmlStreamReaderException(MessageFormat.format(RAW_EX_1, str, str2, str3), str, str2, str3);
            }
            if (str3 == null || str3.equals(str5)) {
                return str;
            }
            throw new XmlStreamReaderException(MessageFormat.format(RAW_EX_1, str, str2, str3), str, str2, str3);
        }
        if (str.equals(UTF_16BE) || str.equals(UTF_16LE)) {
            if (str2 != null && !str2.equals(str)) {
                throw new XmlStreamReaderException(MessageFormat.format(RAW_EX_1, str, str2, str3), str, str2, str3);
            }
            if (str3 == null || str3.equals(UTF_16) || str3.equals(str)) {
                return str;
            }
            throw new XmlStreamReaderException(MessageFormat.format(RAW_EX_1, str, str2, str3), str, str2, str3);
        }
        if (str.equals(UTF_32BE) || str.equals(UTF_32LE)) {
            if (str2 != null && !str2.equals(str)) {
                throw new XmlStreamReaderException(MessageFormat.format(RAW_EX_1, str, str2, str3), str, str2, str3);
            }
            if (str3 == null || str3.equals(UTF_32) || str3.equals(str)) {
                return str;
            }
            throw new XmlStreamReaderException(MessageFormat.format(RAW_EX_1, str, str2, str3), str, str2, str3);
        }
        throw new XmlStreamReaderException(MessageFormat.format(RAW_EX_2, str, str2, str3), str, str2, str3);
    }

    @Override // java.io.Reader, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.reader.close();
    }

    private String doLenientDetection(String str, XmlStreamReaderException xmlStreamReaderException) throws IOException {
        if (str != null && str.startsWith("text/html")) {
            try {
                return calculateHttpEncoding(xmlStreamReaderException.getBomEncoding(), xmlStreamReaderException.getXmlGuessEncoding(), xmlStreamReaderException.getXmlEncoding(), true, "text/xml" + str.substring(9));
            } catch (XmlStreamReaderException e) {
                xmlStreamReaderException = e;
            }
        }
        String xmlEncoding = xmlStreamReaderException.getXmlEncoding();
        if (xmlEncoding == null) {
            xmlEncoding = xmlStreamReaderException.getContentTypeEncoding();
        }
        if (xmlEncoding != null) {
            return xmlEncoding;
        }
        String str2 = this.defaultEncoding;
        return str2 == null ? UTF_8 : str2;
    }

    public String getDefaultEncoding() {
        return this.defaultEncoding;
    }

    public String getEncoding() {
        return this.encoding;
    }

    private String processHttpStream(BOMInputStream bOMInputStream, BOMInputStream bOMInputStream2, boolean z) throws IOException {
        String bOMCharsetName = bOMInputStream.getBOMCharsetName();
        String bOMCharsetName2 = bOMInputStream2.getBOMCharsetName();
        try {
            return calculateRawEncoding(bOMCharsetName, bOMCharsetName2, getXmlProlog(bOMInputStream2, bOMCharsetName2));
        } catch (XmlStreamReaderException e) {
            if (z) {
                return doLenientDetection(null, e);
            }
            throw e;
        }
    }

    private String processHttpStream(BOMInputStream bOMInputStream, BOMInputStream bOMInputStream2, boolean z, String str) throws IOException {
        String bOMCharsetName = bOMInputStream.getBOMCharsetName();
        String bOMCharsetName2 = bOMInputStream2.getBOMCharsetName();
        try {
            return calculateHttpEncoding(bOMCharsetName, bOMCharsetName2, getXmlProlog(bOMInputStream2, bOMCharsetName2), z, str);
        } catch (XmlStreamReaderException e) {
            if (z) {
                return doLenientDetection(str, e);
            }
            throw e;
        }
    }

    @Override // java.io.Reader
    public int read(char[] cArr, int i, int i2) throws IOException {
        return this.reader.read(cArr, i, i2);
    }
}
