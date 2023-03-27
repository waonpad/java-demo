package com.example.demo.db.migration.batch;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.example.demo.db.migration.service.DBMigrateService;

import jakarta.transaction.Transactional;

@Component
public class DBMigrateBatchRunner implements ApplicationRunner {

    @Autowired
    private DBMigrateService dbMigrateService;

    @Override
    @Transactional(rollbackOn = Exception.class)
    public void run(ApplicationArguments args) throws Exception {
        dbMigrateService.migrate();
    }
}
