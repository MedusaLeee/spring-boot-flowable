package com.viathink.flowable.config;

import com.viathink.flowable.core.flowable.DefaultProcessDiagramGenerator;
import org.flowable.spring.SpringProcessEngineConfiguration;
import org.flowable.spring.boot.EngineConfigurationConfigurer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

/**
 * 工作流配置
 */
@Component
public class FlowableConfig implements EngineConfigurationConfigurer<SpringProcessEngineConfiguration> {

    DataSource flowableDataSource;

    @Autowired
    public FlowableConfig(DataSource flowableDataSource) {
        this.flowableDataSource = flowableDataSource;
    }

    @Override
    public void configure(SpringProcessEngineConfiguration springProcessEngineConfiguration) {
        springProcessEngineConfiguration.setDataSource(flowableDataSource);
        springProcessEngineConfiguration.setActivityFontName("宋体");
        springProcessEngineConfiguration.setLabelFontName("宋体");
        springProcessEngineConfiguration.setProcessDiagramGenerator(new DefaultProcessDiagramGenerator());
    }
}
