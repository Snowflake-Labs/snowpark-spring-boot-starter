package dev.kameshs.snowpark;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

import com.snowflake.snowpark_java.Session;
import dev.kameshs.snowpark.autoconfigure.SnowflakeConnectionConfig;
import dev.kameshs.snowpark.autoconfigure.SnowparkAutoConfiguration;
import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.AutoConfigurations;
import org.springframework.boot.test.context.runner.ApplicationContextRunner;

public class SnowflakeAutoConfigurationTest {
  private final ApplicationContextRunner contextRunner =
      new ApplicationContextRunner()
          .withConfiguration(AutoConfigurations.of(SnowparkAutoConfiguration.class));

  @Test
  void contextLoadWithPassword() {
    this.contextRunner
        .withPropertyValues(
            "snowflake.connection.account-identifier=sfdevrel",
            "snowflake.connection.user-name=kameshs")
        .run(
            context -> {
              SnowflakeConnectionConfig config = context.getBean(SnowflakeConnectionConfig.class);
              assertThat(config.get("USER")).isEqualTo("kameshs");
              assertThat(config.get("URL"))
                  .isEqualTo("https://sfdevrel.snowflakecomputing.com:443");
            });
  }

  @Test
  void contextLoadWithKeyPair() {
    String pkPath = String.format("%s/.ssh/snowflake_kameshs_devrel.p8", System.getenv("HOME"));
    this.contextRunner
        .withPropertyValues(
            "snowflake.connection.account-identifier=sfdevrel",
            "snowflake.connection.user-name=kameshs",
            "snowflake.connection.private-key-file=" + pkPath,
            "snowflake.connection.use-private-key=true")
            .withSystemProperties()
        .run(
            context -> {
              SnowflakeConnectionConfig config = context.getBean(SnowflakeConnectionConfig.class);
              assertThat(config.get("USER")).isEqualTo("kameshs");
              assertThat(config.get("URL"))
                  .isEqualTo("https://sfdevrel.snowflakecomputing.com:443");
              assertThat(context).hasSingleBean(Session.class);
            });
  }
}
