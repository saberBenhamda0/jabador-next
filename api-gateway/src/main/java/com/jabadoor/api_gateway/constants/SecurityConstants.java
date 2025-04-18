package com.jabadoor.api_gateway.constants;

import org.springframework.stereotype.Service;

@Service
public interface SecurityConstants {

    final String JWT_HEADER = "authorization";
    final String JWT_SIGNIN_KEY = "3066cb78578b3ff3db293e3d0cc0da9371dc20957c16752ce011e27a0aabaa2ad2dcb6f17598b9645a89e933e69";
    final int EXPIRRD_DURATION = 3600000;

}
