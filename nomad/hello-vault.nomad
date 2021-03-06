job "vault-secret-zero" {
  datacenters = ["dc1"]

  type = "service"

  group "vault-secret-zero" {
    task "vault-secret-zero" {
      driver = "java"
      config {
        jar_path    = "/Users/kabu/hashicorp/vault/secret-zero/nomad/app/target/vault-secret-zero-nomad-0.0.1-SNAPSHOT.jar"
        jvm_options = ["-Xmx2048m", "-Xms256m"]
      }
      vault {
        policies = ["kv-full"]
        change_mode = "restart"
      }
      template {
        data = <<EOH
          "{{with secret "kv/test/test"}}{{.Data.password}}{{end}}"
          EOH
        destination = "${NOMAD_SECRETS_DIR}/test-secret"
      }
      resources {
        cpu = 500
        memory = 1024

        network {
          mbits = 10
          port "http" {}
        }
      }
    }
  }
}
