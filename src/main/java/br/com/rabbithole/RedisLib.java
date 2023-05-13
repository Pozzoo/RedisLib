package br.com.rabbithole;

import br.com.rabbithole.configurations.RedisConfig;
import br.com.rabbithole.configurations.RedisConfiguration;
import br.com.rabbithole.core.builder.commands.generics.Get;
import br.com.rabbithole.core.builder.commands.generics.sets.Set;
import br.com.rabbithole.core.builder.options.SetOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.JedisPool;

import java.util.Optional;

/**
 * @author Felipe Ros
 * @Usage Classe de acesso a Biblioteca.
 * @since 1.0
 */
public class RedisLib {
    private static RedisConfiguration redisConfiguration;
    private static Logger logger;

    public static void init(RedisConfig redisConfig) {
        redisConfiguration = new RedisConfiguration(redisConfig);
        logger = LoggerFactory.getLogger(redisConfig.getPrefix() + " - ");
        System.out.println("Iniciado com Sucesso!");
    }

    //TODO: APENAS PARA TESTE!
    public static void main(String[] args) {
        RedisLib.init(new RedisConfig("Test", "localhost", 6379, "default", "1234", 100));

        var setQueryResult = new Set.Builder()
                .setKey("Foo2")
                .setValue("Bar")
                .execute();

        var getQueryResult = new Get.Builder()
                .setKey("Foo2")
                        .execute();

        setQueryResult.ifPresent((var) -> {
            System.out.println("Resultado: " + var);
        });

        getQueryResult.ifPresent((var) -> {
            System.out.println("Resultado: " + var);
        });
    }

    public static JedisPool getJedis() {
        return redisConfiguration.getJedis();
    }

    public static Logger getLogger() {
        return logger;
    }
}
