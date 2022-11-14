App.factory('Session', ['$http', '$q', 'UrlService', function ($http, $q, UrlService) {

        var SERVER_URL_JSON = UrlService.getServerUrlJson();

        return {

            fetchAll: function () {
                return $http.get(SERVER_URL_JSON + '/hotsession')
                        .then(
                                function (response) {
                                    return response.data;
                                },
                                function (errResponse) {
                                    console.error('Error while fetching session');
                                    return $q.reject(errResponse);
                                }
                        );
            },

            fetch: function (id) {
                return $http.get(SERVER_URL_JSON + "/hotsession/" + id)
                        .then(
                                function (response) {
                                    return response.data;
                                },
                                function (errResponse) {
                                    console.error('Error while getting session');
                                    return $q.reject(errResponse);
                                }
                        );
            },

            deleteAll: function () {
                return $http.delete(SERVER_URL_JSON + "/hotsession")
                        .then(
                                function (response) {
                                    return response.data;
                                },
                                function (errResponse) {
                                    console.error('Error while fetching sessions');
                                    return $q.reject(errResponse);
                                }
                        );
            },

            delete: function (id) {
                return $http.delete(SERVER_URL_JSON + "/hotsession/" + id)
                        .then(
                                function (response) {
                                    return response.data;
                                },
                                function (errResponse) {
                                    console.error('Error while deleting session');
                                    return $q.reject(errResponse);
                                }
                        );
            },

        };

    }]);