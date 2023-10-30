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
package io.gravitee.rest.api.management.v2.rest.resource;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Response;

/**
 * @author Florent CHAMFROY (florent.chamfroy at graviteesource.com)
 * @author GraviteeSource Team
 */
@Path("/")
public class OpenAPIResource {

    @GET
    @Produces("text/html")
    public Response getOpenApiDocumentation() {
        return Response.ok(this.getClass().getClassLoader().getResourceAsStream("openapi/index.html")).build();
    }

    @GET
    @Path("/index-apis.html")
    @Produces("text/html")
    public Response getAPIsOpenApiDocumentation() {
        return Response.ok(this.getClass().getClassLoader().getResourceAsStream("openapi/index-apis.html")).build();
    }

    @GET
    @Path("/openapi.yaml")
    @Produces("application/yaml")
    public Response getAPIsOpenApi() {
        return Response.ok(this.getClass().getClassLoader().getResourceAsStream("openapi/openapi-apis.yaml")).build();
    }

    @GET
    @Path("/index-plugins.html")
    @Produces("text/html")
    public Response getPluginsOpenApiDocumentation() {
        return Response.ok(this.getClass().getClassLoader().getResourceAsStream("openapi/index-plugins.html")).build();
    }

    @GET
    @Path("/openapi-plugins.yaml")
    @Produces("application/yaml")
    public Response getPluginsOpenApi() {
        return Response.ok(this.getClass().getClassLoader().getResourceAsStream("openapi/openapi-plugins.yaml")).build();
    }

    @GET
    @Path("/index-ui.html")
    @Produces("text/html")
    public Response getUIOpenApiDocumentation() {
        return Response.ok(this.getClass().getClassLoader().getResourceAsStream("openapi/index-ui.html")).build();
    }

    @GET
    @Path("/openapi-ui.yaml")
    @Produces("application/yaml")
    public Response getUIOpenApi() {
        return Response.ok(this.getClass().getClassLoader().getResourceAsStream("openapi/openapi-ui.yaml")).build();
    }
}
