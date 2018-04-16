package com.viathink.flowable.deployment.service;

import com.viathink.flowable.deployment.dto.DeployCreateForm;
import org.flowable.engine.repository.Deployment;

public interface DeploymentService {
    public Deployment createDeployment(DeployCreateForm deployCreateForm);
}
