/*
 * Copyright © 2015 The Gravitee team (http://gravitee.io)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.gravitee.apim.infra.domain_service.installation;

import io.gravitee.apim.core.installation.domain_service.InstallationTypeDomainService;
import io.gravitee.apim.core.installation.model.InstallationType;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * @author Guillaume LAMIRAND (guillaume.lamirand at graviteesource.com)
 * @author GraviteeSource Team
 */
@Service
@Slf4j
public class InstallationTypeDomainServiceImpl implements InstallationTypeDomainService {

    @Value("${installation.type:" + InstallationType.Labels.STANDALONE + "}")
    private String installationTypeAsString;

    private InstallationType installationType;

    @PostConstruct
    public void afterPropertiesSet() {
        installationType = InstallationType.fromLabel(installationTypeAsString);
    }

    @Override
    public InstallationType get() {
        return installationType;
    }

    @Override
    public boolean isMultiTenant() {
        return installationType == InstallationType.MULTI_TENANT;
    }
}
