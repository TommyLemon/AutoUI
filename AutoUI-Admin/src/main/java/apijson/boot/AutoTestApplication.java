/*Copyright ©2026 TommyLemon(https://github.com/TommyLemon)

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.*/

package apijson.boot;

import apijson.fastjson2.APIJSONApplication;
import apijson.fastjson2.APIJSONCreator;
import apijson.orm.AbstractParser;
import apijson.orm.AbstractVerifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Map;
import java.util.regex.Pattern;

import apijson.Log;
import apijson.StringUtil;
import apijson.demo.DemoFunctionParser;
import apijson.demo.DemoParser;
import apijson.demo.DemoSQLConfig;
import apijson.demo.DemoSQLExecutor;
import apijson.demo.DemoVerifier;


/**
 * SpringBoot Application 主应用程序启动类
 * 右键这个类 > Run As > Java Application
 * 具体见 SpringBoot 文档
 * https://www.springcloud.cc/spring-boot.html#using-boot-locating-the-main-class
 *
 * @author Lemon
 */
@Configuration
@EnableScheduling
@SpringBootApplication
public class AutoTestApplication implements WebServerFactoryCustomizer<ConfigurableServletWebServerFactory> {
    private static final String TAG = "AutoTestApplication";

    // 全局 ApplicationContext 实例，方便 getBean 拿到 Spring/SpringBoot 注入的类实例
    private static ApplicationContext APPLICATION_CONTEXT;
    public static ApplicationContext getApplicationContext() {
        return APPLICATION_CONTEXT;
    }

    public static void main(String[] args) throws Exception {
        APPLICATION_CONTEXT = SpringApplication.run(AutoTestApplication.class, args);

        try {
            DemoSQLExecutor.REDIS_TEMPLATE.discard();
        } catch (Throwable e) {
            e.printStackTrace();
        }

        // 上线生产环境前改为 false，可不输出 APIJSONORM 的日志 以及 SQLException 的原始(敏感)信息
        Log.DEBUG = true; // 是否开启调试模式（打印详细日志、返回详细调试信息等）
        AbstractParser.IS_PRINT_BIG_LOG = true; // 是否打印大日志
        APIJSONApplication.init();
        AutoTestController.init();
        System.out.println("\n\n<<<<<<<<< Chrome/Firefox 打开 http://localhost:8080 即可调试(端口号根据项目配置而定) ^_^ >>>>>>>>>\n");
    }

    // SpringBoot 2.x 自定义端口方式
    @Override
    public void customize(ConfigurableServletWebServerFactory server) {
        server.setPort(8080); // 9090);
    }

    // 支持 APIAuto 中 JavaScript 代码跨域请求
    @Bean
    public WebMvcConfigurer corsConfig() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOriginPatterns("*")
                        .allowedMethods("*")
                        .allowCredentials(true)
                        .exposedHeaders(AutoTestController.APIJSON_DELEGATE_ID)  // Cookie 和 Set-Cookie 怎么设置都没用 ,Cookie,Set-Cookie")   // .exposedHeaders("*")
                        .maxAge(3600);
            }
        };
    }

    static {
        Map<String, Pattern> COMPILE_MAP = AbstractVerifier.COMPILE_MAP;
        COMPILE_MAP.put("PHONE", StringUtil.PATTERN_PHONE);
        COMPILE_MAP.put("EMAIL", StringUtil.PATTERN_EMAIL);
        COMPILE_MAP.put("ID_CARD", StringUtil.PATTERN_ID_CARD);

        // 使用本项目的自定义处理类
        APIJSONApplication.DEFAULT_APIJSON_CREATOR = new APIJSONCreator<Long>() {

            @Override
            public DemoParser createParser() {
                return new DemoParser();
            }

            @Override
            public DemoFunctionParser createFunctionParser() {
                return new DemoFunctionParser();
            }

            @Override
            public DemoVerifier createVerifier() {
                return new DemoVerifier();
            }

            @Override
            public DemoSQLConfig createSQLConfig() {
                return new DemoSQLConfig();
            }

            @Override
            public DemoSQLExecutor createSQLExecutor() {
                return new DemoSQLExecutor();
            }

        };

        // APIJSON 配置 >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
    }

}
