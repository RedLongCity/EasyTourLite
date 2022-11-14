'use strict';

App.controller('UrlController', ['$scope', 'UrlService', function ($scope, UrlService) {
        var self = this;
        self.serverUrl;
        self.jsonUrl;

        self.fetchServerUrl = function () {
           self.serverUrl = UrlService.getServerUrl();
        };

        self.fetchJsonUrl = function () {
          self.jsonUrl = UrlService.getServerUrlJson();
        };

        self.fetchServerUrl();
        self.fetchJsonUrl();
    }]);


