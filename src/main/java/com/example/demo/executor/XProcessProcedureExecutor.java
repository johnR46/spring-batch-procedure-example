package com.example.demo.executor;

import com.example.demo.config.XProcessProcedureConfig;
import com.example.demo.dto.BaseReq;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;


@Component
public class XProcessProcedureExecutor {
    private final JobLauncher jobLauncher;
    private final Job job;

    @Autowired
    public XProcessProcedureExecutor(JobLauncher jobLauncher, @Qualifier(XProcessProcedureConfig.JOB_NAME) Job job) {
        this.jobLauncher = jobLauncher;
        this.job = job;
    }


    public void runJob(BaseReq req) throws Exception {
        try {
            jobLauncher.run(job, buildJobParameters(req));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public JobParameters buildJobParameters(BaseReq req) {
        JobParametersBuilder jobParametersBuilder = new JobParametersBuilder();
        jobParametersBuilder.addString("uuid", java.util.UUID.randomUUID().toString());
        jobParametersBuilder.addString("batchDate", req.getBatchDate());
        return jobParametersBuilder.toJobParameters();
    }


}
