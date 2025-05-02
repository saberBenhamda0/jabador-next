package com.jabador.experience_service.embeddable;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Embeddable
public class Details {

    private int prepTime;

    private Duration duration; // yes

    private String startDate;

    private String endDate;

    private GroupSize groupSize;

    private String primaryLanguague; // yes

    private boolean isWheelchairAccesible; // yes

    private String skillLevel; // yes
}
