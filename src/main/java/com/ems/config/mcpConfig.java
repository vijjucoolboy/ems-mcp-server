package com.ems.config;

import com.ems.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.ai.support.ToolCallbacks;
import org.springframework.ai.tool.ToolCallback;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
@AllArgsConstructor
public class mcpConfig {

    private EmployeeService employeeService;

    public List<ToolCallback> emsTools(){
        return List.of(ToolCallbacks.from(employeeService));
    }


}
