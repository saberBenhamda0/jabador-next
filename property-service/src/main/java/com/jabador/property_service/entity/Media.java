package com.jabador.property_service.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Media {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String type;

    private String url;

    private String caption;

    private boolean isPrimary;

    private int imagesOrder;
}

/*
{
  "type": "image/avif",
  "url": "https://res.cloudinary.com/dvcditlcb/image/upload/v1738771434/jabadoor/propery/dcf6wsrdbaqycvuv1vug.avif",
  "caption": "054461f1-5e9d-4d8f-9d59-d64c9cba5c26.avif",
  "isPrimary": true,
  "order": 1,
  "_id": "67a38bee73195c5a51b2d567"
},
 */
