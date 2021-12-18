package com.example.demo.config;


import com.example.demo.processor.XProcessor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import javax.sql.DataSource;

@Configuration
@EnableBatchProcessing
public class XProcessProcedureConfig {

    public static final String JOB_NAME = "X_JOB_EXECUTE_FUNCTION";
    public static final String STEP_NAME = "X_STEP_EXECUTE_FUNCTION";
    public static final String PROCESSOR_NAME = "X_PROCESSOR_FUNCTION";

    public final JobBuilderFactory jobBuilderFactory;
    public final StepBuilderFactory stepBuilderFactory;

    @Autowired
    public XProcessProcedureConfig(JobBuilderFactory jobBuilderFactory, StepBuilderFactory stepBuilderFactory) {
        this.jobBuilderFactory = jobBuilderFactory;
        this.stepBuilderFactory = stepBuilderFactory;
    }


    @Bean(name = JOB_NAME)
    public Job job(@Qualifier(STEP_NAME) Step step) {
        return jobBuilderFactory.get(JOB_NAME)
                .incrementer(new RunIdIncrementer())
                .flow(step)
                .end()
                .build();
    }

    @Bean(name = STEP_NAME)
    public Step step(@Qualifier(PROCESSOR_NAME) Tasklet task) {
        return stepBuilderFactory.get(STEP_NAME).tasklet(task).build();
    }

    @Bean(name = PROCESSOR_NAME)
    @StepScope()
    public Tasklet task(
            @Autowired DataSource dataSource,
            @Value("#{jobParameters['batchDate']}") String batchDate,
            @Value("#{jobParameters['sqlScript']}") String sqlScript
    ) {
        return new XProcessor(dataSource, batchDate, sqlScript);
    }

}

