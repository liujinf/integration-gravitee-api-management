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
package io.gravitee.repository.mongodb.management.internal.flow;

import io.gravitee.repository.mongodb.management.internal.model.FlowMongo;
import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * @author Guillaume CUSNIEUX (guillaume.cusnieux at graviteesource.com)
 * @author GraviteeSource Team
 */
@Repository
public interface FlowMongoRepository extends MongoRepository<FlowMongo, String> {
    @Query(value = "{ 'referenceType': ?0, 'referenceId': {$in: ?1} }", sort = "{referenceId: -1, order: -1}")
    List<FlowMongo> findAll(String referenceType, String... referenceIds);

    @Query(value = "{ 'referenceType': ?0}", sort = "{referenceId: -1, order: -1}")
    List<FlowMongo> findAll(String referenceType);

    @Query(value = "{ 'referenceId': ?0, 'referenceType': ?1}", fields = "{ _id : 1 }", delete = true)
    List<FlowMongo> deleteByReferenceIdAndReferenceType(String referenceId, String referenceTYpe);

    @Query(value = "{ 'referenceType': ?0, 'referenceId': ?1 }", sort = "{referenceId: -1, order: -1}")
    List<FlowMongo> findByReference(String referenceType, String referenceId);
}
