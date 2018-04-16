package com.viathink.flowable.deployment.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class DeployCreateForm {
    // 部署名称
    @NotBlank(message = "不能为空")
    private String name;
    // 租户
    @NotBlank(message = "不能为空")
    private String tenantId;
    // bpmn的名字，示例 countersign.bpmn
    @NotBlank(message = "不能为空")
    private String bpmnName;
    // bpmn的xml字符串
    @NotBlank(message = "不能为空")
    private String bpmn;
}
