package org.landasource.rempi.manager.core.schedule;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;

/**
 * Created by david on 2015-01-20.
 */
@Configuration
@ConditionalOnProperty(name = "quartz.enabled")
public class SchedulerConfig {

	// @Bean
	// public JobFactory jobFactory(final ApplicationContext applicationContext)
	// {
	// final AutowiringSpringBeanJobFactory jobFactory = new
	// AutowiringSpringBeanJobFactory();
	// jobFactory.setApplicationContext(applicationContext);
	// return jobFactory;
	// }
	//
	// @Bean
	// public SchedulerFactoryBean schedulerFactoryBean(final DataSource
	// dataSource, final JobFactory jobFactory, @Qualifier("sampleJobTrigger")
	// final Trigger sampleJobTrigger)
	// throws IOException {
	// final SchedulerFactoryBean factory = new SchedulerFactoryBean();
	// // this allows to update triggers in DB when updating settings in config
	// // file:
	// factory.setOverwriteExistingJobs(true);
	// factory.setDataSource(dataSource);
	// factory.setJobFactory(jobFactory);
	//
	// factory.setQuartzProperties(quartzProperties());
	// factory.setTriggers(sampleJobTrigger);
	//
	// return factory;
	// }
	//
	// @Bean
	// public Properties quartzProperties() throws IOException {
	// final PropertiesFactoryBean propertiesFactoryBean = new
	// PropertiesFactoryBean();
	// propertiesFactoryBean.setLocation(new
	// ClassPathResource("/quartz.properties"));
	// propertiesFactoryBean.afterPropertiesSet();
	// return propertiesFactoryBean.getObject();
	// }
	//
	// @Bean
	// public JobDetailFactoryBean sampleJobDetail() {
	// return createJobDetail(SampleJob.class);
	// }
	//
	// @Bean(name = "sampleJobTrigger")
	// public SimpleTriggerFactoryBean
	// sampleJobTrigger(@Qualifier("sampleJobDetail") final JobDetail jobDetail,
	// @Value("${samplejob.frequency}") final long frequency) {
	// return createTrigger(jobDetail, frequency);
	// }
	//
	// private static JobDetailFactoryBean createJobDetail(final Class jobClass)
	// {
	// final JobDetailFactoryBean factoryBean = new JobDetailFactoryBean();
	// factoryBean.setJobClass(jobClass);
	// // job has to be durable to be stored in DB:
	// factoryBean.setDurability(true);
	// return factoryBean;
	// }
	//
	// private static SimpleTriggerFactoryBean createTrigger(final JobDetail
	// jobDetail, final long pollFrequencyMs) {
	// final SimpleTriggerFactoryBean factoryBean = new
	// SimpleTriggerFactoryBean();
	// factoryBean.setJobDetail(jobDetail);
	//
	// factoryBean.setStartDelay(0L);
	// factoryBean.setRepeatInterval(pollFrequencyMs);
	// factoryBean.setRepeatCount(SimpleTrigger.REPEAT_INDEFINITELY);
	// // in case of misfire, ignore all missed triggers and continue :
	// factoryBean.setMisfireInstruction(SimpleTrigger.MISFIRE_INSTRUCTION_RESCHEDULE_NEXT_WITH_REMAINING_COUNT);
	// return factoryBean;
	// }
}