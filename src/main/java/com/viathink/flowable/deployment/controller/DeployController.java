package com.viathink.flowable.deployment.controller;

import com.alibaba.fastjson.JSONObject;
import com.viathink.flowable.common.exception.CommonException;
import com.viathink.flowable.deployment.dto.DeployCreateForm;
import com.viathink.flowable.deployment.service.DeploymentService;
import org.flowable.bpmn.exceptions.XMLException;
import org.flowable.engine.repository.Deployment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

@RestController
public class DeployController {
    private Logger logger = LoggerFactory.getLogger(DeployController.class);

    private final DeploymentService deploymentService;

    @Autowired
    public DeployController(DeploymentService deploymentService) {
        this.deploymentService = deploymentService;
    }

    @PostMapping(value = "/deployments", consumes = {MediaType.APPLICATION_JSON_UTF8_VALUE}, produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public JSONObject createDeploy(@Valid @RequestBody DeployCreateForm deployCreateForm) {
        try {
            Deployment deployment = deploymentService.createDeployment(deployCreateForm);
            logger.debug(deployment.toString());
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("id", deployment.getId());
            jsonObject.put("name", deployment.getName());
            jsonObject.put("tenantId", deployment.getTenantId());
            return jsonObject;
        } catch (XMLException e) {
            throw new CommonException("bpmn格式错误");
        }
    }

    @GetMapping(value = "/deployments/{deploymentId}/png", produces = {MediaType.IMAGE_PNG_VALUE})
    public void getDeploymentBpmn(HttpServletResponse response, @PathVariable String deploymentId) throws IOException {

        InputStream in = deploymentService.getDeploymentBpmnStream(deploymentId);
        OutputStream os = response.getOutputStream();
        response.setHeader(HttpHeaders.CONTENT_TYPE, MediaType.IMAGE_PNG_VALUE);
        byte[] b = new byte[1024];
        int len = -1;
        while ((len = in.read(b, 0, 1024)) != -1) {
            os.write(b, 0, len);
        }
        in.close();
        os.flush();
        os.close();
    }
}
