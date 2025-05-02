package com.jabador.experience_service.embeddable;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Embeddable
public class GroupSize {

    private int min;

    private int max;

    private boolean privateGroup;

    private int privateGroupMinSize;

    private int privateGroupMaxSize;
    
}
