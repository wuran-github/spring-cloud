package learn.spring.cloud.userservice.config;

import learn.spring.cloud.userservice.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

import java.io.IOException;
import java.security.cert.CertificateException;
@Configuration
public class JwtConfig {
    @Autowired
    JwtAccessTokenConverter jwtAccessTokenConverter;

    @Bean
    public TokenStore tokenStore(){
        return new JwtTokenStore(jwtAccessTokenConverter);
    }

    @Bean
    protected JwtAccessTokenConverter jwtTokenEnhancer() throws IOException, CertificateException {
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        //使用最正规的证书签名方式
//        Resource resource = new ClassPathResource("auth.crt");
//        String publicKey = JwtUtil.extractPublicKey(resource.getInputStream());
//        converter.setVerifierKey(publicKey);

        //使用普通字符串签名
        converter.setSigningKey("auth-key");
        return converter;
    }
}
