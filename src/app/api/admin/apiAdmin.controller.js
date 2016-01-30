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
class ApiAdminController {
  constructor (resolvedApi, $state, $scope, $mdDialog, $rootScope, ApiService, NotificationService) {
    'ngInject';
    this.$scope = $scope;
    this.$state = $state;
    this.$mdDialog = $mdDialog;
    this.$rootScope = $rootScope;
    this.api = resolvedApi.data;
    this.ApiService = ApiService;
    this.NotificationService = NotificationService;
    this.apiJustDeployed = false;
    this.apiIsSynchronized = true;
    this.init();
    this.checkAPISynchronization(this.api);

    var that = this;
    $scope.$on('$stateChangeSuccess', function (ev, to, toParams, from) {
      // check authorization
      if (!$scope.$parent.apisCtrl.isOwner(that.api)) {
        $state.go('home');
      }

      if (from.name.startsWith('apis.list.')) {
        $scope.$parent.previousState = from.name;
      }

      if ($state.current.name.includes('general')) {
        $scope.selectedTab = 0;
      } else if ($state.current.name.includes('policies')) {
        $scope.selectedTab = 1;
      } else if ($state.current.name.includes('documentation')) {
        $scope.selectedTab = 2;
      } else if ($state.current.name.endsWith('apikeys')) {
        $scope.selectedTab = 3;
      } else if ($state.current.name.endsWith('members')) {
        $scope.selectedTab = 4;
      } else if ($state.current.name.endsWith('properties')) {
        $scope.selectedTab = 5;
      } else if ($state.current.name.includes('analytics')) {
        $scope.selectedTab = 6;
      } else if ($state.current.name.endsWith('monitoring')) {
        $scope.selectedTab = 7;
      } else if ($state.current.name.endsWith('descriptor')) {
        $scope.selectedTab = 8;
      } else if ($state.current.name.endsWith('events')) {
        $scope.selectedTab = 9;
      }
    });
  }
  
  init() {
    var self = this;
    this.$rootScope.$on("apiChangeSuccess", function() {
        self.checkAPISynchronization(self.api);
    });
  }
  
  checkAPISynchronization(api) {
    this.ApiService.isAPISynchronized(api.id).then(response => {
      this.apiJustDeployed = false;
      if (response.data.is_synchronized) {
        this.apiIsSynchronized = true;
      } else {
        this.apiIsSynchronized = false;
      }
    });
  }
  
  showDeployAPIConfirm(ev, api) {
    var confirm = this.$mdDialog.confirm()
      .title('Would you like to deploy your API?')
      .ariaLabel('deploy-api')
      .ok('OK')
      .cancel('Cancel')
      .targetEvent(ev);
    var self = this;
    this.$mdDialog.show(confirm).then(function() {
      self.deploy(api);
    }, function() {
      self.$mdDialog.cancel();
    });
  }
  
  deploy(api) {
    this.ApiService.deploy(api.id).then((deployedApi) => {
      this.NotificationService.show("API deployed");
      this.api = deployedApi.data;
      this.apiJustDeployed = true;
    });
  }
}

export default ApiAdminController;
