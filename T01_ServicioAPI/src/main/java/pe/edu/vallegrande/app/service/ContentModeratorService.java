package pe.edu.vallegrande.app.service;

import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.json.JSONObject;

@Slf4j
@Service
public class ContentModeratorService {

	public static void main(String[] args) throws Exception {
        contentModerator();
    }

	//@Override
    public static JSONObject contentModerator() throws Exception {
        OkHttpClient client = new OkHttpClient().newBuilder().build();
        MediaType mediaType = MediaType.parse("text/plain; charset=utf-8");
        RequestBody body = RequestBody.create(mediaType, "Is this a crap email abcdef@abcd.com, phone: 6657789887, IP: 255.255.255.255, 1 Microsoft Way, Redmond, WA 98052");
        Request request = new Request.Builder()
                .url("https://eastus.api.cognitive.microsoft.com/contentmoderator/moderate/v1.0/ProcessText/Screen?classify=True")
                .method("POST", body)
                .addHeader("Host", "eastus.api.cognitive.microsoft.com")
                .addHeader("Content-Type", "text/plain")
                .addHeader("Ocp-Apim-Subscription-Key", "fcbf1ee9c86540af97e7bc8aa121b2da")
                .build();
        Response response = client.newCall(request).execute();

        JSONObject jsonObject = new JSONObject(response.body().string());
        log.info("Mostrando JSON " + jsonObject);
        return jsonObject;
    }
}
