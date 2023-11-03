/*
 * Copyright (C) 2015 The Gravitee team (http://gravitee.io)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import { BaseApplication } from '../application';
import { BasePlan } from '../plan';
import { HttpMethod } from '../api';

export interface ConnectionLog {
  apiId: string;
  requestId: string;
  timestamp: string;
  clientIdentifier: string;
  transactionId: string;
  method: HttpMethod;
  status: number;
  requestEnded: boolean;

  application: BaseApplication;
  plan: BasePlan;
}

export interface ConnectionLogDetail {
  apiId: string;
  requestId: string;
  timestamp: string;
  clientIdentifier: string;
  requestEnded: boolean;
  entrypointRequest: ConnectionLogDetailRequest;
  endpointRequest: ConnectionLogDetailRequest;
  entrypointResponse: ConnectionLogDetailResponse;
  endpointResponse: ConnectionLogDetailResponse;
}

export interface ConnectionLogDetailRequest {
  method: string;
  uri: string;
  headers: Record<string, string[]>;
}

export interface ConnectionLogDetailResponse {
  status: number;
  headers: Record<string, string[]>;
}
