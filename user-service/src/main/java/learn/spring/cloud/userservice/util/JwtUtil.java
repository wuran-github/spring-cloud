package learn.spring.cloud.userservice.util;

import sun.misc.BASE64Encoder;

import java.io.InputStream;
import java.security.PublicKey;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;

public class JwtUtil {
    public static String extractPublicKey(InputStream inputStream) throws CertificateException {
        CertificateFactory cf = CertificateFactory.getInstance("X.509");
        X509Certificate cert = (X509Certificate)cf.generateCertificate(
                inputStream);
        PublicKey publicKey = cert.getPublicKey();
        BASE64Encoder encoder = new BASE64Encoder();
        String str = encoder.encode(publicKey.getEncoded());
        str = str.replaceAll("\\r","").replaceAll("\\n","").trim();
        return str;
    }
}
