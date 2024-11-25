package com.project.nameMaker.common.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;

import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.io.BufferedReader;

@Slf4j
public class CSVToH2 {

    // H2 데이터베이스 URL
    //@Value(value = "${spring.datasource.url}")
    private static String jdbcURL = "jdbc:h2:tcp://localhost/~/testcase";

    // H2 데이터베이스 username
    //@Value(value = "${spring.datasource.username}")
    private static String username = "sa";

    // H2 데이터베이스 password
    //@Value(value = "${spring.datasource.password}")
    private static String password = "";

    // csv 파일 경로와 파일명
    private static final String CSV_FILE_PATH = "/Users/oh/Desktop/study/datafile/name_stats_set.csv";

    public static void main(String[] args) {
        try (Connection connection = DriverManager.getConnection(jdbcURL, username, password);
             BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(CSV_FILE_PATH), StandardCharsets.UTF_8))) {

            String line;
            String sql = "INSERT INTO NAME_STATS (YEAR_RANK, NAME, YEAR_COUNT, GENDER, YEARS, TOTAL_RANK) VALUES (?, ?, ?, ?, ?, 0)";
            PreparedStatement statement = connection.prepareStatement(sql);

            line = br.readLine();
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                log.info("==1== {}", values[0]);
                log.info("==2== {}", values[1]);
                log.info("==3== {}", values[3]);
                log.info("==4== {}", values[4]);
                log.info("==5== {}", values[5]);

                statement.setInt(1, Integer.parseInt(values[0]));
                statement.setString(2, values[1]);
                statement.setInt(3, Integer.parseInt(values[3]));
                statement.setString(4, values[4]);
                statement.setInt(5, Integer.parseInt(values[5]));

                statement.executeUpdate();
            }

            log.info("CSV 데이터를 성공적으로 삽입했습니다.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
