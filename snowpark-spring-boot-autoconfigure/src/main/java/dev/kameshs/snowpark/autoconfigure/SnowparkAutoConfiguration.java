package dev.kameshs.snowpark.autoconfigure;

import com.snowflake.snowpark_java.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

/**
 * SnowparkAutoConfiguration allows users to configure Snowpark Java session via application
 * properties
 *
 * @see SnowflakeConnectionProperties
 */
@AutoConfiguration
@ConditionalOnClass(Session.class)
@EnableConfigurationProperties(SnowflakeConnectionProperties.class)
public class SnowparkAutoConfiguration {
  final Logger LOG = LoggerFactory.getLogger(SnowparkAutoConfiguration.class);

  final SnowflakeConnectionProperties snowflakeConnectionProperties;

  public SnowparkAutoConfiguration(SnowflakeConnectionProperties snowflakeConnectionProperties) {
    this.snowflakeConnectionProperties = snowflakeConnectionProperties;
  }

  @Bean
  @ConditionalOnMissingBean
  public SnowflakeConnectionConfig snowflakeConnectionConfig() throws Exception {
    LOG.debug("Building Connection Config");
    SnowflakeConnectionConfig config = new SnowflakeConnectionConfig();

    String accountId =
        snowflakeConnectionProperties.getAccountIdentifier() == null
            ? System.getenv("SNOWFLAKE_ACCOUNT_ID")
            : snowflakeConnectionProperties.getAccountIdentifier();
    String authenticator =
        snowflakeConnectionProperties.getAuthenticator() == null
            ? System.getenv("SNOWFLAKE_AUTHENTICATOR")
            : snowflakeConnectionProperties.getAuthenticator();
    String userName =
        snowflakeConnectionProperties.getUserName() == null
            ? System.getenv("SNOWFLAKE_USERNAME")
            : snowflakeConnectionProperties.getUserName();
    String password =
        snowflakeConnectionProperties.getPassword() == null
            ? System.getenv("SNOWFLAKE_PASSWORD")
            : snowflakeConnectionProperties.getPassword();
    String privateKeyFile =
        snowflakeConnectionProperties.getPrivateKeyFile() == null
            ? System.getenv("SNOWFLAKE_PRIVATE_KEY_FILE")
            : snowflakeConnectionProperties.getPrivateKeyFile();
    String privateKeyFilePassword =
        snowflakeConnectionProperties.getPrivateKeyFilePassword() == null
            ? System.getenv("SNOWFLAKE_PRIVATE_KEY_PASSPHRASE")
            : snowflakeConnectionProperties.getPrivateKeyFilePassword();
    String role =
        snowflakeConnectionProperties.getRole() == null
            ? System.getenv("SNOWFLAKE_ROLE")
            : snowflakeConnectionProperties.getRole();
    String database =
        snowflakeConnectionProperties.getDatabase() == null
            ? System.getenv("SNOWFLAKE_DATABASE")
            : snowflakeConnectionProperties.getDatabase();
    String schema =
        snowflakeConnectionProperties.getSchema() == null
            ? System.getenv("SNOWFLAKE_SCHEMA")
            : snowflakeConnectionProperties.getSchema();
    String warehouse =
        snowflakeConnectionProperties.getWarehouse() == null
            ? System.getenv("SNOWFLAKE_WAREHOUSE")
            : snowflakeConnectionProperties.getWarehouse();

    String url = String.format("https://%s.snowflakecomputing.com:443", accountId);
    LOG.trace("URL:{}", url);
    config.put("URL", url);
    if (authenticator != null) {
      config.put("AUTHENTICATOR", authenticator);
    }
    config.put("USER", userName);
    if (password != null && !snowflakeConnectionProperties.isUsePrivateKey()) {
      config.put("PASSWORD", password);
    }

    if (privateKeyFile != null) {
      config.put("PRIVATE_KEY_FILE", privateKeyFile);
    }

    if (privateKeyFilePassword != null) {
      config.put("PRIVATE_KEY_FILE_PWD", privateKeyFilePassword);
    }

    if (privateKeyFile != null && privateKeyFilePassword != null) {
      config.put("AUTHENTICATOR", "SNOWFLAKE_JWT");
    }
    if (database != null) {
      config.put("DB", database);
    }
    if (role != null) {
      config.put("ROLE", role);
    }
    if (schema != null) {
      config.put("SCHEMA", schema);
    }
    if (warehouse != null) {
      config.put("WAREHOUSE", warehouse);
    }

    return config;
  }

  @Bean
  @ConditionalOnMissingBean
  public Session buildSnowparkSession(SnowflakeConnectionConfig config) {
    LOG.debug("Building Connection with config {}", config);
    return Session.builder().configs(config).create();
  }

}
