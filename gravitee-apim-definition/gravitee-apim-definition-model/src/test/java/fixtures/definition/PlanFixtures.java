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
package fixtures.definition;

import io.gravitee.definition.model.federation.FederatedPlan;
import io.gravitee.definition.model.federation.SubscriptionParameter;
import io.gravitee.definition.model.v4.plan.Plan;
import io.gravitee.definition.model.v4.plan.PlanMode;
import io.gravitee.definition.model.v4.plan.PlanSecurity;
import io.gravitee.definition.model.v4.plan.PlanStatus;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

public class PlanFixtures {

    private static final Supplier<Plan.PlanBuilder> BASE_V4 = () ->
        Plan.builder().id("my-plan").name("My plan").status(PlanStatus.PUBLISHED).mode(PlanMode.STANDARD);

    private static final Supplier<io.gravitee.definition.model.Plan.PlanBuilder> BASE_V2 = () ->
        io.gravitee.definition.model.Plan.builder().id("my-plan").name("My plan").status("PUBLISHED");

    private PlanFixtures() {}

    public static Plan aKeylessV4() {
        return (Plan) BASE_V4.get().id("keyless").name("Keyless").security(PlanSecurity.builder().type("key-less").build()).build();
    }

    public static Plan anApiKeyV4() {
        return (Plan) BASE_V4.get().id("apikey").name("API Key").security(PlanSecurity.builder().type("api-key").build()).build();
    }

    public static Plan aPushPlan() {
        return (Plan) BASE_V4.get().id("push").name("Push Plan").mode(PlanMode.PUSH).build();
    }

    public static io.gravitee.definition.model.Plan aKeylessV2() {
        return BASE_V2.get().id("keyless").name("Keyless").security("key-less").securityDefinition("{\"nice\": \"config\"}").build();
    }

    public static io.gravitee.definition.model.Plan anApiKeyV2() {
        return BASE_V2.get().id("apikey").name("API Key").security("api-key").build();
    }

    public static io.gravitee.definition.model.Plan aKeylessV1() {
        return BASE_V2.get().id("keyless").name("Keyless").security("key-less").paths(Map.of("/", List.of())).build();
    }

    public static FederatedPlan aFederatedPlan() {
        return FederatedPlan
            .builder()
            .id("my-plan")
            .mode(PlanMode.STANDARD)
            .providerId("provider-id")
            .status(PlanStatus.PUBLISHED)
            .security(PlanSecurity.builder().type("api-key").build())
            .build();
    }

    public static SubscriptionParameter subscriptionParameter() {
        return new SubscriptionParameter.ApiKey(aFederatedPlan());
    }

    public static Plan anMtlsPlanV4() {
        return (Plan) BASE_V4.get().id("mtls").name("mTLS Plan").security(PlanSecurity.builder().type("mtls").build()).build();
    }
}
