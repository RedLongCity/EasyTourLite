App.factory('SettingsService', ['$http', '$q', 'UrlService', function ($http, $q, UrlService) {

        var SERVER_URL = UrlService.getServerUrl();
        var SERVER_URL_JSON = UrlService.getServerUrlJson();

        return{

            getShort_Delay: function () {
                return $http.get(SERVER_URL_JSON + "/getshortdelay")
                        .then(
                                function (response) {
                                    return response.data;
                                },
                                function (errResponse) {
                                    console.error('Error while getting shortdelay');
                                    return $q.reject(errResponse);
                                }
                        );
            },

            getShort_Status: function () {
                return $http.get(SERVER_URL_JSON + "/getshortstatus")
                        .then(
                                function (response) {
                                    return response.data;
                                },
                                function (errResponse) {
                                    console.error('Error while getting short status');
                                    return $q.reject(errResponse);
                                }
                        );
            },
            getShort_Suspended: function () {
                return $http.get(SERVER_URL_JSON + "/getshortsuspended")
                        .then(
                                function (response) {
                                    return response.data;
                                },
                                function (errResponse) {
                                    console.error('Error while getting short suspended');
                                    return $q.reject(errResponse);
                                }
                        );
            },

            getGlobal_Delay: function () {
                return $http.get(SERVER_URL_JSON + "/getglobaldelay")
                        .then(
                                function (response) {
                                    return response.data;
                                },
                                function (errResponse) {
                                    console.error('Error while getting global delay');
                                    return $q.reject(errResponse);
                                }
                        );
            },

            getGlobal_Status: function () {
                return $http.get(SERVER_URL_JSON + "/getglobalstatus")
                        .then(
                                function (response) {
                                    return response.data;
                                },
                                function (errResponse) {
                                    console.error('Error while getting global status');
                                    return $q.reject(errResponse);
                                }
                        );
            },

            getGlobal_Suspended: function () {
                return $http.get(SERVER_URL_JSON + "/getglobalsuspended")
                        .then(
                                function (response) {
                                    return response.data;
                                },
                                function (errResponse) {
                                    console.error('Error while getting global suspended');
                                    return $q.reject(errResponse);
                                }
                        );
            },
            getRequestPullSize: function () {
                return $http.get(SERVER_URL_JSON + "/getrequestpullsize")
                        .then(
                                function (response) {
                                    return response.data;
                                },
                                function (errResponse) {
                                    console.error('Error while getting request pull size ');
                                    return $q.reject(errResponse);
                                }
                        );
            },
            getResponsePullSize: function () {
                return $http.get(SERVER_URL_JSON + "/getresponsepullsize")
                        .then(
                                function (response) {
                                    return response.data;
                                },
                                function (errResponse) {
                                    console.error('Error while getting response pull size ');
                                    return $q.reject(errResponse);
                                }
                        );
            },

            getFreezzeeTimeDelay: function () {
                return $http.get(SERVER_URL_JSON + "/getfreezzeetime")
                        .then(
                                function (response) {
                                    return response.data;
                                },
                                function (errResponse) {
                                    console.error('Error while getting freezzee time ');
                                    return $q.reject(errResponse);
                                }
                        );
            },

            getRevelance: function () {
                return $http.get(SERVER_URL_JSON + "/getrevelance")
                        .then(
                                function (response) {
                                    return response.data;
                                },
                                function (errResponse) {
                                    console.error('Error while getting revelance ');
                                    return $q.reject(errResponse);
                                }
                        );
            },
            stopShort: function () {
                return $http.get(SERVER_URL + "/stopshort")
            },

            resumeShort: function () {
                return $http.get(SERVER_URL + "/resumeshort")
            },

            stopGlobal: function () {
                return $http.get(SERVER_URL + "/stopglobal")
            },

            resumeGlobal: function () {
                return $http.get(SERVER_URL + "/resumeglobal")
            },

            setGlobalDelay: function (delay) {
                return $http.get(SERVER_URL + "/setglobaldelay?delay=" + delay)
            },

            setShortDelay: function (delay) {
                return $http.get(SERVER_URL + "/setshortdelay?delay=" + delay)
            },
            setRequestPullSize: function (size) {
                return $http.get(SERVER_URL + "/setrequestpullsize?size=" + size)
            },
            setResponsePullSize: function (size) {
                return $http.get(SERVER_URL + "/setresponsepullsize?size=" + size)
            },
            setFreezzeeTime: function (time) {
                return $http.get(SERVER_URL + "/setfreezzeetime?time=" + time)
            },
            setRevelance: function (revelance) {
                return $http.get(SERVER_URL + "/setrevelance?revelance=" + revelance)
            }
        }
    }]);