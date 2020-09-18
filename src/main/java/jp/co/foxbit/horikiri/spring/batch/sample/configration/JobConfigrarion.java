package jp.co.foxbit.horikiri.spring.batch.sample.configration;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import jp.co.foxbit.horikiri.spring.batch.sample.chunk.InformationProcessor;
import jp.co.foxbit.horikiri.spring.batch.sample.chunk.InformationReader;
import jp.co.foxbit.horikiri.spring.batch.sample.chunk.InformationWriter;
import jp.co.foxbit.horikiri.spring.batch.sample.listener.JobListener;
import jp.co.foxbit.horikiri.spring.batch.sample.model.Information;
import jp.co.foxbit.horikiri.spring.batch.sample.tasklet.MessageTasklet;

@Configuration
@EnableBatchProcessing
public class JobConfigrarion {

    @Autowired
    private MessageTasklet messageTasklet;

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Bean
    public ItemReader<Information> reader() {

        return new InformationReader();
    }

    @Bean
    public ItemProcessor<Information, Information> processor() {

        return new InformationProcessor();
    }

    @Bean
    public ItemWriter<Information> writer() {

        return new InformationWriter(); 
    }

    @Bean
    public JobExecutionListener listener() {

        return new JobListener();
    }

    @Bean
    public Step messageStep() {

        return stepBuilderFactory.get("messageStep")
                .tasklet(messageTasklet)
                .build();
  }

    @Bean
    public Step informationStep() {

        return stepBuilderFactory.get("InformationStep").<Information, Information>chunk(2).reader(reader())
                .processor(processor()).writer(writer()).build();
    }

    @Bean
    public Job informationJob() {

      return jobBuilderFactory
      .get("helloWorldJob")
      .listener(listener())
      .start(messageStep())
      .next(informationStep())
      .build();
    
    }
}
