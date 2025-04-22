package com.jabadoor.api_gateway.constants;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Sevices {

    public List<Map<String, String>> getServices(){
        List<Map<String, String>> services = new ArrayList<>();

        Map<String, String> experienceService = new HashMap<>();
        experienceService.put("variableName", "experienceService");
        experienceService.put("preRouteName", "experience");
        services.add(experienceService);

        Map<String, String> userService = new HashMap<>();
        experienceService.put("variableName", "userService");
        experienceService.put("preRouteName", "user");
        services.add(userService);

        Map<String, String> propertyService = new HashMap<>();
        experienceService.put("variableName", "propertyService");
        experienceService.put("preRouteName", "property");
        services.add(propertyService);

        return services;

    }

}
