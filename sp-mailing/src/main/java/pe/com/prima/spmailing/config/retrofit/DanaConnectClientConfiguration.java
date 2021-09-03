package pe.com.prima.spmailing.config.retrofit;

import okhttp3.Credentials;
import okhttp3.Request;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pe.com.prima.apolo.retrofit.ApoloRetrofit;
import pe.com.prima.spmailing.proxy.thirdparty.dana.DanaConnectSendClient;
import pe.com.prima.spmailing.proxy.thirdparty.dana.DanaConnectUploadClient;
import retrofit2.Retrofit;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;

@Configuration
public class DanaConnectClientConfiguration {

    private final String msDanaBaseUrl;
    private final String credentials;

    public DanaConnectClientConfiguration(
            @Value("${dana.proxy.baseUrl}") String msDanaBaseUrl,
            @Value("${dana.basic-auth.username}") String msDanaBasicAuthUserName,
            @Value("${dana.basic-auth.password}") String msDanaBasicAuthPassword
    ) {
        this.msDanaBaseUrl = msDanaBaseUrl;
        this.credentials = Credentials.basic(msDanaBasicAuthUserName, msDanaBasicAuthPassword);
    }

    @Bean
    DanaConnectSendClient msDanaConnectDataExt() {
        Retrofit retrofit = new ApoloRetrofit.Builder()
                .baseUrl(this.msDanaBaseUrl)
                .addInterceptor(chain -> {
                    Request request = chain.request();
                    Request authenticatedRequest = request.newBuilder()
                            .header("Authorization", credentials).build();
                    return chain.proceed(authenticatedRequest);
                })
                .build();
        return retrofit.create(DanaConnectSendClient.class);
    }

    @Bean
    DanaConnectUploadClient msDanaConnectUploadClient() {
        Retrofit retrofit = new ApoloRetrofit.Builder()
                .baseUrl(this.msDanaBaseUrl)
                .addInterceptor(chain -> {
                    Request request = chain.request();
                    Request authenticatedRequest = request.newBuilder()
                            .header("Authorization", credentials).build();
                    return chain.proceed(authenticatedRequest);
                })
                .converterFactory(SimpleXmlConverterFactory.create())
                .build();
        return retrofit.create(DanaConnectUploadClient.class);
    }
}
