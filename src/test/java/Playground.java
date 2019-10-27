import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Arrays;

public class Playground {
    public static void main(String[] args) throws IOException, URISyntaxException {
        File peersFile = new File(Playground.class.getResource("/peers.json").toURI());

        Gson gson = new Gson();
        JsonObject[] entries = gson.fromJson(new FileReader(peersFile), JsonObject[].class);

        Arrays.stream(entries).forEach((entry) -> {
            System.out.println(entry.get("alias"));
        });

    }
}
