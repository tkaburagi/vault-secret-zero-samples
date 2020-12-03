package run.kabuctl.vaultsecretzero;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.vault.core.VaultKeyValueOperations;
import org.springframework.vault.core.VaultKeyValueOperationsSupport;
import org.springframework.vault.core.VaultOperations;
import org.springframework.vault.support.VaultResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.io.File;

@RestController
public class AppController {
    @Autowired
    private VaultOperations vaultOperations;

    @GetMapping (value = "/")
    public String hello() {
        VaultKeyValueOperations operations = vaultOperations.opsForKeyValue("kv/test", VaultKeyValueOperationsSupport.KeyValueBackend.unversioned());
        VaultResponse response = operations.get("test");
        return response.getData().toString();
    }

    @GetMapping (value = "rm-roleid")
    public String removeRoleId() {
        File file = new File("/role-id");
        file.delete();
        return "Removed Role ID";
    }
}
