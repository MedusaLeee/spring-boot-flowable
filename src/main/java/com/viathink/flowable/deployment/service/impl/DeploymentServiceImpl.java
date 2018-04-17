package com.viathink.flowable.deployment.service.impl;

import com.viathink.flowable.deployment.dto.DeployCreateForm;
import com.viathink.flowable.deployment.service.DeploymentService;
import org.flowable.engine.RepositoryService;
import org.flowable.engine.common.api.repository.EngineResource;
import org.flowable.engine.repository.Deployment;
import org.flowable.engine.repository.ProcessDefinition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.InputStream;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class DeploymentServiceImpl implements DeploymentService {
    private final RepositoryService repositoryService;

    @Autowired
    public DeploymentServiceImpl(RepositoryService repositoryService) {
        this.repositoryService = repositoryService;
    }

    @Override
    @Transactional
    public Deployment createDeployment(DeployCreateForm deployCreateForm) {
        Deployment deployment = repositoryService.createDeployment()
                .name(deployCreateForm.getName())
                .tenantId(deployCreateForm.getTenantId())
                .addString(deployCreateForm.getBpmnName(), deployCreateForm.getBpmn())
                .deploy();
        return deployment;
    }

    @Override
    @Transactional
    public InputStream getDeploymentBpmnStream(String deploymentId) {
        Deployment deployment = repositoryService.createDeploymentQuery().deploymentId(deploymentId).singleResult();
        ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery()
                .deploymentId(deploymentId)
                .singleResult();
        String bpmnName = processDefinition.getDiagramResourceName();
//        Map<String, EngineResource> resourceMap = deployment.getResources();
//        String bpmnName = resourceMap.entrySet().stream()
//                .filter(it -> it.getValue().getName().lastIndexOf(".png") > 0)
//                .map(it -> it.getValue().getName())
//                .collect(Collectors.joining());
        InputStream inputStream = repositoryService.getResourceAsStream(deploymentId, bpmnName);
        return inputStream;
    }
}
