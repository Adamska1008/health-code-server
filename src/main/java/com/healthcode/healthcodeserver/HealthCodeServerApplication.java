package com.healthcode.healthcodeserver;

import com.healthcode.healthcodeserver.util.RedisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.auditing.ReactiveIsNewAwareAuditingHandler;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class HealthCodeServerApplication {
  private static Logger logger = LoggerFactory.getLogger(HealthCodeServerApplication.class);
  private static RedisUtil redisUtilStatic;
  @Autowired
  RedisUtil redisUtil;

  @PostConstruct
  public void init() {
    redisUtilStatic = redisUtil;
  }

  public static void main(String[] args) {
    SpringApplication.run(HealthCodeServerApplication.class, args);
  }

  @Bean
  CommandLineRunner redis_example_data_insert() {
    return args -> {
      logger.info("inserting redis data");
      insertData();
      logger.info("insert redis data finished");
    };
  }

  static void insertData() {
    String openIdA = "oHbzp53Ax8GW3td4vmkV2Pp3H904";
    String openIdB = "oHbzp5yIBhroac3en14rw9zhYVcI";

    redisUtilStatic.addFamilyBindingApplication(
            openIdA, "1597569829328482306");
    redisUtilStatic.addFamilyBindingApplication(
            openIdB, "1597569829328263884");

    redisUtilStatic.addRemoteReport(
            openIdA, "4136878935465423441");
    redisUtilStatic.addRemoteReport(
            openIdA, "1599660788505706498");
    redisUtilStatic.addRemoteReport(
            openIdA, "3216548713254565441");
    redisUtilStatic.addRemoteReport(
            openIdA, "8795648488494496455");
    redisUtilStatic.addRemoteReport(
            openIdB, "1596867897987297281");
    redisUtilStatic.addRemoteReport(
            openIdB, "1597117024788553730");
    redisUtilStatic.addRemoteReport(
            openIdB, "1364952356413874464");
    redisUtilStatic.addRemoteReport(
            openIdB, "3168426845618415956");

    redisUtilStatic.addVenueCodeApplication(
            openIdA, "2479569829328482306");
    redisUtilStatic.addVenueCodeApplication(
            openIdA, "1654645615654781436");
    redisUtilStatic.addVenueCodeApplication(
            openIdA, "5464984894564614825");
    redisUtilStatic.addVenueCodeApplication(
            openIdA, "9746542345545186158");
    redisUtilStatic.addVenueCodeApplication(
            openIdB, "4684861378766789451");
    redisUtilStatic.addVenueCodeApplication(
            openIdB, "6318986535956381841");
    redisUtilStatic.addVenueCodeApplication(
            openIdB, "8613497621594791856");
    redisUtilStatic.addVenueCodeApplication(
            openIdB, "3564861398764368785");


    redisUtilStatic.addAbnormalInfo(
            openIdA, "341414893546542344");
    redisUtilStatic.addAbnormalInfo(
            openIdA, "341414893541241244");
    redisUtilStatic.addAbnormalInfo(
            openIdA, "341411234546542344");
    redisUtilStatic.addAbnormalInfo(
            openIdA, "341414123445542344");
    redisUtilStatic.addAbnormalInfo(
            openIdB, "457342192126124100");
    redisUtilStatic.addAbnormalInfo(
            openIdB, "457342192208112410");
    redisUtilStatic.addAbnormalInfo(
            openIdB, "457342192208115100");
    redisUtilStatic.addAbnormalInfo(
            openIdB, "457312342208107100");
  }
}
