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
package io.gravitee.rest.api.service.exceptions;

import static java.util.Collections.singletonMap;

import io.gravitee.common.http.HttpStatusCode;
import java.util.Map;

/**
 * @author GraviteeSource Team
 */
public class NativePlanAuthenticationConflictException extends AbstractManagementException {

    private final boolean planToPublishIsKeyless;

    public NativePlanAuthenticationConflictException(boolean planToPublishIsKeyless) {
        this.planToPublishIsKeyless = planToPublishIsKeyless;
    }

    @Override
    public String getMessage() {
        return planToPublishIsKeyless
            ? "A plan with authentication is already published for the Native API."
            : "A Keyless plan for the Native API is already published.";
    }

    @Override
    public int getHttpStatusCode() {
        return HttpStatusCode.BAD_REQUEST_400;
    }

    @Override
    public String getTechnicalCode() {
        return "plan.native.authentication.conflict";
    }

    @Override
    public Map<String, String> getParameters() {
        return singletonMap("planToPublishIsKeyless", Boolean.valueOf(planToPublishIsKeyless).toString());
    }
}
