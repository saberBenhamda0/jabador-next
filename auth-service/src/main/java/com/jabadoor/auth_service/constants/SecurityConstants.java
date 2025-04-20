package com.jabadoor.auth_service.constants;

import lombok.experimental.NonFinal;

public interface SecurityConstants {

    final String JWT_KEY = "3066cb78578b3ff3db293e3d0cc0da9371dc20957c16752ce011e27a0aabaa2ad2dcb6f17598b9645a89e933e69";
    final String JWT_HEADER = "authorization";
    final int duration = 360000;
}
