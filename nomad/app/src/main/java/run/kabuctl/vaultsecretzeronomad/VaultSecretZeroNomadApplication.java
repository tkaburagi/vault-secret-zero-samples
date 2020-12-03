package run.kabuctl.vaultsecretzeronomad;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@SpringBootApplication
public class VaultSecretZeroNomadApplication {

    public static void main(String[] args) {
        SpringApplication.run(VaultSecretZeroNomadApplication.class, args);
    }

}

@RestController
class AppController {

    @GetMapping(value = "/")
    public String hello() {
        return getKvSecret();
    }

    public String getKvSecret() {
        String secrets_dir = System.getenv("NOMAD_SECRETS_DIR");
        String task_dir = System.getenv("NOMAD_TASK_DIR");

        String task_dir_replaced = task_dir.replace("vault-secret-zero/local", "vault-secret-zero/");

        System.out.println(" SECRETS_DIR ="  + secrets_dir);
        System.out.println(" NOMAD_TASK_DIR ="  + task_dir_replaced);


        Path path = Paths.get(task_dir_replaced + secrets_dir + "/test-secret");

        String contents = null;
        try {
            contents = Files.readString(path, StandardCharsets.ISO_8859_1);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return contents;
    }
}
