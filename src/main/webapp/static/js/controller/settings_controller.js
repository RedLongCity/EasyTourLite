'use strict';

App.controller('SettingsController', ['$scope', 'SettingsService', 'MailAddress', function ($scope, SettingsService, MailAddress) {

        var self = this;
        self.shortStatus = "true";
        self.globalStatus = "true";
        self.shortDelay = 12;
        self.globalDelay = new String();
        self.globalDelay = 1;
        self.globalSuspended = false;
        self.shortSuspended = false;
        self.requestPullSize = 10;
        self.responsePullSize = 10;
        self.freezzeeTime = 5000;
        self.revelance = 24;
        self.address = {id: null, name: '', emailAddress: ''};
        self.addresses = [];

        self.globalDelaysArray_Human = [1, 10, 15, 30, 60, 120, 180, 300];

        self.fetchShortStatus = function () {
            SettingsService.getShort_Status()
                    .then(
                            function (d) {
                                self.shortStatus = d;
                            },
                            function (errResponse) {
                                console.error('Error while fetching short status');
                            }
                    );
        };

        self.fetchShortSuspended = function () {
            SettingsService.getShort_Suspended()
                    .then(
                            function (d) {
                                self.shortSuspended = d;
                            },
                            function (errResponse) {
                                console.error('Error while fetching short suspended');
                            }
                    );
        };

        self.fetchGlobalStatus = function () {
            SettingsService.getGlobal_Status()
                    .then(
                            function (d) {
                                self.globalStatus = d;
                            },
                            function (errResponse) {
                                console.error('Error while fetching global status');
                            }
                    );
        };

        self.fetchGlobalSuspended = function () {
            SettingsService.getGlobal_Suspended()
                    .then(
                            function (d) {
                                self.globalSuspended = d;
                            },
                            function (errResponse) {
                                console.error('Error while fetching global suspended');
                            }
                    );
        };

        self.fetchShortDelay = function () {
            SettingsService.getShort_Delay()
                    .then(
                            function (d) {
                                self.shortDelay = d;
                            },
                            function (errResponse) {
                                console.error('Error while fetching short delay');
                            }
                    );
        };

        self.fetchGlobalDelay = function () {
            SettingsService.getGlobal_Delay()
                    .then(
                            function (d) {
                                self.globalDelay = d;
                            },
                            function (errResponse) {
                                console.error('Error while fetching global suspended');
                            }
                    );
        };

        self.fetchRequestPullSize = function () {
            SettingsService.getRequestPullSize()
                    .then(
                            function (d) {
                                self.requestPullSize = d;
                            },
                            function (errResponse) {
                                console.error('Error while fetching request pull size');
                            }
                    );
        };

        self.fetchResponsePullSize = function () {
            SettingsService.getResponsePullSize()
                    .then(
                            function (d) {
                                self.responsePullSize = d;
                            },
                            function (errResponse) {
                                console.error('Error while fetching response pull size');
                            }
                    );
        };

        self.fetchFreezzeeTimeDelay = function () {
            SettingsService.getFreezzeeTimeDelay()
                    .then(
                            function (d) {
                                self.freezzeeTime = d;
                            },
                            function (errResponse) {
                                console.error('Error while fetching freezzee time delay');
                            }
                    );
        };

        self.fetchRevelance = function () {
            SettingsService.getRevelance()
                    .then(
                            function (d) {
                                self.revelance = d;
                            },
                            function (errResponse) {
                                console.error('Error while fetching freezzee time delay');
                            }
                    );
        };


        self.getAllMailAddresses = function () {
            MailAddress.getAll()
                    .then(
                            function (d) {
                                self.addresses = d;
                            },
                            function (errResponse) {
                                console.error('Error while fetching addresses');
                            }
                    );
        };

        self.getMailAddress = function (id) {
            MailAddress.get(id)
                    .then(
                            function (d) {
                                self.address = d;
                            },
                            function (errResponse) {
                                console.error('Error while fetching address');
                            }
                    );
        };

        self.saveMailAddress = function (address) {
            MailAddress.save(address)
                    .then(
                            self.getAllMailAddresses,
                            function (errResponse) {
                                console.error('Error while save address');
                            }
                    );
        };

        self.updateMailAddress = function (address, id) {
            MailAddress.update(address, id)
                    .then(
                            self.getAllMailAddresses,
                            function (errResponse) {
                                console.error('Error while update address');
                            }
                    );
        };

        self.deleteAllMailAddresses = function () {
            MailAddress.deleteAll()
                    .then(
                            self.getAllMailAddresses,
                            function (errResponse) {
                                console.error('Error while deleting addresses');
                            }
                    );
        };

        self.deleteMailAddress = function (id) {
            MailAddress.deleteMailAddress(id)
                    .then(
                            self.getAllMailAddresses,
                            function (errResponse) {
                                console.error('Error while deleting address');
                            }
                    );
        };

        self.submit = function () {
            if (self.address.id === null) {
                self.saveMailAddress(self.address);
                console.log('Saving New Mail', self.address);
            } else {
                self.updateMailAddress(self.address, self.address.id);
                console.log('Address updated with id ', self.address.id);
            }
            self.reset();
        };

        self.edit = function (id) {
            console.log('id to be edited', id);
            for (var i = 0; i < self.addresses.length; i++) {
                if (self.addresses[i].id === id) {
                    self.address = angular.copy(self.addresses[i]);
                    console.log('address: ', self.address);
                    break;
                }
            }
        };

        self.remove = function (id) {
            console.log('id to be deleted', id);
            if (self.address.id === id) {
                self.reset();
            }
            self.deleteMailAddress(id);
        };

        self.reset = function () {
            self.address = {id: null, name: '', emailAddress: ''};
        };

        self.setShortDelay = function () {
            SettingsService.setShortDelay(self.shortDelay).then(
                    self.fetchShortDelay,
                    function (errResponse) {
                        console.error('Error while setting short delay');
                    }
            );
        };

        self.setGlobalDelay = function (delay) {
            SettingsService.setGlobalDelay(delay).then(
                    self.fetchGlobalDelay,
                    function (errResponse) {
                        console.error('Error while setting global delay');
                    }
            );
        };

        self.setRequestPullSize = function (size) {
            SettingsService.setRequestPullSize(size).then(
                    self.fetchRequestPullSize,
                    function (errResponse) {
                        console.error('Error while setting request pull size');
                    }
            );
        };

        self.setResponsePullSize = function (size) {
            SettingsService.setResponsePullSize(size).then(
                    self.fetchResponsePullSize,
                    function (errResponse) {
                        console.error('Error while setting request pull size');
                    }
            );
        };

        self.setFreezzeeTime = function (time) {
            SettingsService.setFreezzeeTime(time).then(
                    self.fetchFreezzeeTimeDelay,
                    function (errResponse) {
                        console.error('Error while setting freezzee time');
                    }
            );
        };

        self.setRevelance = function (revelance) {
            SettingsService.setRevelance(revelance).then(
                    self.fetchRevelance,
                    function (errResponse) {
                        console.error('Error while setting revelance');
                    }
            );
        };

        self.stopShort = function () {
            SettingsService.stopShort();
        };

        self.stopGlobal = function () {
            SettingsService.stopGlobal();
        };

        self.resumeShort = function () {
            SettingsService.resumeShort();
        };

        self.resumeGlobal = function () {
            SettingsService.resumeGlobal();
        };



        self.fetchShortStatus();
        self.fetchGlobalStatus();
        self.fetchShortDelay();
        self.fetchGlobalDelay();
        self.fetchGlobalSuspended();
        self.fetchShortSuspended();
        self.getAllMailAddresses();
        self.fetchRequestPullSize();
        self.fetchResponsePullSize();
        self.fetchFreezzeeTimeDelay();
        self.fetchRevelance();
    }]);