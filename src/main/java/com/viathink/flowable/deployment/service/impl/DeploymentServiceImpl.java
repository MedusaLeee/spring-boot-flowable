package com.viathink.flowable.deployment.service.impl;

import com.viathink.flowable.deployment.dto.DeployCreateForm;
import com.viathink.flowable.deployment.service.DeploymentService;
import org.flowable.engine.RepositoryService;
import org.flowable.engine.repository.Deployment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
}
