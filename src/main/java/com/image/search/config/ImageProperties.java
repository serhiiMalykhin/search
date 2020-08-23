/*
 * Copyright 2020 Expedia, Inc. All rights reserved.
 * EXPEDIA PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.image.search.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
public class ImageProperties {

    @Value("${agileengine.url.base}")
    private String base;

    @Value("${agileengine.url.auth}")
    private String auth;

    @Value("${agileengine.url.images}")
    private String images;

    @Value("${agileengine.url.search}")
    private String search;

    @Value("${agileengine.auth.apiKey}")
    private String apiKey;

}
