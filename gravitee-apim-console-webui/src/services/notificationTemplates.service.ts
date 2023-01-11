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

import { IHttpPromise } from 'angular';

class NotificationTemplatesService {
  /* @ngInject */
  constructor(private $http: ng.IHttpService, private Constants) {}

  getNotificationTemplates(hook?: string, scope?: string): IHttpPromise<any> {
    return this.$http.get(`${this.Constants.org.baseURL}/configuration/notification-templates/`, { params: { hook, scope } });
  }

  getNotificationTemplate(notificationTemplateId: string): IHttpPromise<any> {
    return this.$http.get(`${this.Constants.org.baseURL}/configuration/notification-templates/` + notificationTemplateId);
  }

  create(notificationTemplate: any): IHttpPromise<any> {
    return this.$http.post(`${this.Constants.org.baseURL}/configuration/notification-templates/`, notificationTemplate);
  }

  update(notificationTemplate: any): IHttpPromise<any> {
    return this.$http.put(
      `${this.Constants.org.baseURL}/configuration/notification-templates/` + notificationTemplate.id,
      notificationTemplate,
    );
  }
}

export default NotificationTemplatesService;
