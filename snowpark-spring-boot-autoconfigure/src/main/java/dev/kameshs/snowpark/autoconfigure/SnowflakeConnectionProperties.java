package dev.kameshs.snowpark.autoconfigure;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "snowflake.connection")
public class SnowflakeConnectionProperties {

  // TODO: Add other properties matching Snowflake Connection
  // TODO: Update comments
  private String accountIdentifier;
  private String authenticator;
  private String userName;
  private String password;
  private String warehouse;
  private String database;
  private String role;
  private String schema;
  private String privateKeyFile;
  private String privateKeyFilePassword;
  private boolean usePrivateKey;

  public String getAccountIdentifier() {
    return accountIdentifier;
  }

  public void setAccountIdentifier(String accountIdentifier) {
    this.accountIdentifier = accountIdentifier;
  }

  public String getAuthenticator() {
    return authenticator;
  }

  public void setAuthenticator(String authenticator) {
    this.authenticator = authenticator;
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getWarehouse() {
    return warehouse;
  }

  public void setWarehouse(String warehouse) {
    this.warehouse = warehouse;
  }

  public String getDatabase() {
    return database;
  }

  public void setDatabase(String database) {
    this.database = database;
  }

  public String getRole() {
    return role;
  }

  public void setRole(String role) {
    this.role = role;
  }

  public String getSchema() {
    return schema;
  }

  public void setSchema(String schema) {
    this.schema = schema;
  }

  public String getPrivateKeyFile() {
    return privateKeyFile;
  }

  public void setPrivateKeyFile(String privateKeyFile) {
    this.privateKeyFile = privateKeyFile;
  }

  public String getPrivateKeyFilePassword() {
    return privateKeyFilePassword;
  }

  public void setPrivateKeyFilePassword(String privateKeyFilePassword) {
    this.privateKeyFilePassword = privateKeyFilePassword;
  }

    public boolean isUsePrivateKey() {
        return usePrivateKey;
    }

    public void setUsePrivateKey(boolean usePrivateKey) {
        this.usePrivateKey = usePrivateKey;
    }
}
