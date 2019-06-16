package learn.spring.cloud.jwtauthsevice;

import sun.misc.BASE64Encoder;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.security.PublicKey;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;

public class CrtTest {
    /** logger */
    private final static Logger LOGGER  = LoggerFactory.getLogger(CrtTest.class);
    @Test
    public void test() throws CertificateException, FileNotFoundException {

        CertificateFactory cf = CertificateFactory.getInstance("X.509");
        X509Certificate cert = (X509Certificate)cf.generateCertificate(
                new FileInputStream(getClass().getResource("/auth.crt").getPath()));
        PublicKey publicKey = cert.getPublicKey();
        System.out.println("public key origin");
        System.out.println(publicKey);
        System.out.println("end");
        BASE64Encoder encoder = new BASE64Encoder();
        String str = encoder.encode(publicKey.getEncoded());
        System.out.println("----public key----");
        System.out.println(str);
        System.out.println("----public key end----");
    }
}
