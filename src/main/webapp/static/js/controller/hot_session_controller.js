'use strict';

App.controller('SessionController', ['$scope', 'Session', function ($scope, Session) {
        var self = this;
        self.sessions = [];
        self.date_from;
        self.date_till;
        self.date_before;
        self.session = {id: null, request: null};

        self.fetchAllSessions = function () {
            Session.fetchAll()
                    .then(
                            function (d) {
                                self.sessions = d;
                            },
                            function (errResponse) {
                                console.error('Error while fetching sessions');
                            }
                    );
        };

        self.fetchSession = function (id) {
            Session.fetch(id)
                    .then(
                            function (d) {
                                self.session = d;
                            },
                            function (errResponse) {
                                console.error('Error while fetching session');
                            }
                    );
        };


        self.deleteAllSessions = function () {
            Session.deleteAll().then(
                    self.fetchAllSessions(),
                    function (errResponse) {
                        console.error('Error while deleting sessions');
                    }
            );
        };

        self.deleteSession = function (id) {
            Session.delete(id).then(
                    self.fetchAllSessions(),
                    function (errResponse) {
                        console.error('Error while deleting session');
                    }
            );
        };
        self.fetchAllSessions();
    }]);
