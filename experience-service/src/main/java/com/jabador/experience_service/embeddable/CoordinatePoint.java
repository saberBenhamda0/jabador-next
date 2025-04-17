package com.jabador.experience_service.embeddable;

import jakarta.persistence.Embeddable;
import lombok.*;

@Data
@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CoordinatePoint {

    private double x;

    private double y;
}
