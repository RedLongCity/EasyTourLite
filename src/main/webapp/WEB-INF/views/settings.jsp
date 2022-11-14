<%-- 
    Document   : settings
    Created on : 27.09.2017, 20:13:17
    Author     : redlongcity
--%>

<%@ page contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">

        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

        <meta name="description" content="">

        <meta name="author" content="">

        <title>Settings</title>

        <!-- Bootstrap core CSS -->
        <link href="static/dist/css/bootstrap.min.css" rel="stylesheet">

        <!--Fonts-->
        <link href="https://fonts.googleapis.com/css?family=Montserrat:300,300i,400,400i,500,500i,600,600i,700,700i" rel="stylesheet">

        <!-- Icons -->
        <link href="static/css/font-awesome.css" rel="stylesheet">

        <!-- Custom styles for this template -->
        <link href="static/css/style.css" rel="stylesheet">

    </head>
    <body ng-app="myApp" class="ng-cloak">
        <div class="container-fluid" id="wrapper" ng-controller="SettingsController as ctrl">
            <div class="row">
                <nav class="sidebar col-xs-12 col-sm-4 col-lg-3 col-xl-2 bg-faded sidebar-style-1" ng-controller="UrlController as url">
                    <h1 class="site-title"><a ng-href="{{url.serverUrl}}/search"><em class="fa fa-globe" aria-hidden="true"></em> Easy Tours</a></h1>

                    <a href="#menu-toggle" class="btn btn-default" id="menu-toggle"><em class="fa fa-bars"></em></a>

                    <ul class="nav nav-pills flex-column sidebar-nav">
                        <li class="nav-item"><a class="nav-link" ng-href="{{url.serverUrl}}/search"><em class="fa fa-search" aria-hidden="true"></em> Searching <span class="sr-only">(current)</span></a></li>
                        <li class="nav-item"><a class="nav-link" ng-href="{{url.serverUrl}}/statistics"><em class="fa fa-bar-chart"></em> Statistics</a></li>
                        <li class="nav-item"><a class="nav-link active" ng-href="{{url.serverUrl}}/settings"><em class="fa fa-cogs" aria-hidden="true"></em> Settings</a></li>
                    </ul>

                    <a href="#" class="logout-button"><em class="fa fa-power-off"></em> Signout</a></nav>

                <main class="col-xs-12 col-sm-8 offset-sm-4 col-lg-9 offset-lg-3 col-xl-10 offset-xl-2 pt-3 pl-4">
                    <header class="page-header row justify-center">
                        <div class="col-md-6 col-lg-8" >
                            <h1 class="float-left text-center text-md-left">Settings</h1>
                        </div>

                        <div class="dropdown user-dropdown col-md-6 col-lg-4 text-center text-md-right"><a class="btn btn-stripped dropdown-toggle" href="https://example.com" id="dropdownMenuLink" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                <img src="static/images/profile-pic.jpg" alt="profile photo" class="circle float-left profile-photo" width="50" height="auto">

                                <div class="username mt-1">
                                    <h4 class="mb-1">Admin</h4>

                                    <h6 class="text-muted">Super Admin</h6>
                                </div>
                            </a>

                            <div class="dropdown-menu dropdown-menu-right" style="margin-right: 1.5rem;" aria-labelledby="dropdownMenuLink"><a class="dropdown-item" href="#"><em class="fa fa-user-circle mr-1"></em> View Profile</a>
                                <a class="dropdown-item" href="#"><em class="fa fa-sliders mr-1"></em> Preferences</a>
                                <a class="dropdown-item" href="#"><em class="fa fa-power-off mr-1"></em> Logout</a></div>
                        </div>

                        <div class="clear"></div>
                    </header>

                    <section class="row">
                        <div class="col-sm-12">
                            <section class="row">
                                <div class="col-md-12 col-lg-6 col-xl-4">

                                    <div class="card mb-4 text-center">
                                        <div class="card-block pull-right">
                                            <h3 class="card-title">Global Updating</h3>


                                            <div class="btn-group btn-lg" role="group" >
                                                <button type="button" class="btn btn-sm btn-success"
                                                        ng-click="ctrl.resumeGlobal()">
                                                    <em class="fa fa-rocket" aria-hidden="true"></em> Start
                                                </button>

                                                <button type="button" class="btn btn-sm btn-primary"
                                                        ng-click='ctrl.fetchGlobalStatus(); ctrl.fetchGlobalSuspended()'>
                                                    <em class="fa fa-refresh" aria-hidden="true"></em> Refresh
                                                </button>

                                                <button type="button" class="btn btn-sm btn-danger"
                                                        ng-click='ctrl.stopGlobal()'>
                                                    <em class="fa fa-power-off" aria-hidden="true"></em> Stop
                                                </button>

                                            </div>
                                            <h4>Global Job Running: <span ng-bind="ctrl.globalStatus"></span></h4>
                                            <h4>Global Job Suspended <span ng-bind="ctrl.globalSuspended"></span></h4>
                                        </div>
                                    </div>
                                </div>

                                <div class="col-md-12 col-lg-6 col-xl-4">

                                    <div class="card mb-4 text-center">
                                        <div class="card-block pull-right">
                                            <h3 class="card-title">Short Updating</h3>


                                            <div class="btn-group btn-lg" role="group" >
                                                <button type="button" class="btn btn-sm btn-success"
                                                        ng-click='ctrl.resumeShort()'>
                                                    <em class="fa fa-rocket" aria-hidden="true"></em> Start
                                                </button>

                                                <button type="button" class="btn btn-sm btn-primary"
                                                        ng-click='ctrl.fetchShortStatus(); ctrl.fetchShortSuspended()'>
                                                    <em class="fa fa-refresh" aria-hidden="true"></em> Refresh
                                                </button>

                                                <button type="button" class="btn btn-sm btn-danger"
                                                        ng-click='ctrl.stopShort()'>
                                                    <em class="fa fa-power-off" aria-hidden="true"></em> Stop
                                                </button>

                                            </div>
                                            <h4>Short Job Running: <span ng-bind="ctrl.shortStatus"></span></h4>
                                            <h4>Short Job Suspended <span ng-bind="ctrl.shortSuspended"></span></h4>
                                        </div>
                                    </div>
                                </div>

                                <div class="col-md-12 col-lg-6 col-xl-4">

                                    <div class="card mb-4 text-center">
                                        <div class="card-block pull-right">
                                            <h3 class="card-title">Global Delay</h3>

                                            <section class='text-center'>
                                                <div class="btn-group btn-lg" role="group" >
                                                    <div class="btn-group">
                                                        <button type="button" class="btn btn-sm btn-primary dropdown-toggle" data-toggle="dropdown">
                                                            <em class="fa fa-globe fa-lg"></em> Every: <span ng-bind="ctrl.globalDelay"></span> min
                                                        </button>
                                                        <div class="dropdown-menu">
                                                            <div ng-repeat="c in ctrl.globalDelaysArray_Human">
                                                                <button class="dropdown-item" ng-click="ctrl.globalDelay = c; ctrl.setGlobalDelay(c)">
                                                                    Every: <span ng-bind="c"></span> min</button>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div> 
                                            </section>
                                        </div>
                                    </div>

                                    <div class="card mb-4 text-center">
                                        <div class="card-block pull-right">
                                            <h3 class="card-title">Short Delay</h3>

                                            <section class='text-center'>
                                                <input type="number" ng-model="ctrl.shortDelay">
                                                <button type="button" class="btn btn-primary btn-sm" ng-click="ctrl.setShortDelay()">
                                                    Save
                                                </button>

                                            </section>
                                        </div>
                                    </div>
                                </div>
                            </section>                         
                            <section class="row">
                                <div class="col-md-12 col-lg-6 col-xl-4">

                                    <div class="card mb-4 text-center">
                                        <div class="card-block pull-right">
                                            <h3 class="card-title">Mail Addresses</h3>

                                            <div class="table-responsive">
                                                <table class="table table-striped">
                                                    <thead>
                                                        <tr>
                                                            <th>#</th>

                                                            <th>ID</th>

                                                            <th>Name</th>

                                                            <th>Email</th>
                                                        </tr>
                                                    </thead>

                                                    <tbody>
                                                        <tr ng-repeat="m in ctrl.addresses" ng-click="ctrl.edit(m.id)" 
                                                            data-toggle="modal" data-target="#modal">

                                                            <td><span ng-bind="$index"></span></td>
                                                            <td><span ng-bind="m.id"></span></td>
                                                            <td><span ng-bind="m.name"></span></td>
                                                            <td><span ng-bind="m.emailAddress"></span></td>

                                                        </tr>
                                                    </tbody>
                                                </table>
                                            </div>

                                            <button type="button" class="btn btn-sm btn-success"
                                                    data-toggle="modal" data-target="#modal" ng-click="ctrl.reset()">
                                                <em class="fa fa-plus" aria-hidden="true"></em> Add
                                            </button>
                                            <button type="button" class="btn btn-sm btn-primary" ng-click="ctrl.getAllMailAddresses()">
                                                <em class="fa fa-refresh" aria-hidden="true"></em> Refresh
                                            </button>

                                        </div>
                                    </div>
                                </div>

                                <div class="col-md-12 col-lg-6 col-xl-4">
                                    <div class="card mb-4 text-center">
                                        <div class="card-block pull-right">
                                            <h3 class="card-title">Constants</h3>
                                            <h5>Request Pull Size:</h5>
                                            <input type="number" ng-model="ctrl.requestPullSize">
                                            <button type="button" class="btn btn-primary btn-sm" ng-click="ctrl.setRequestPullSize(ctrl.requestPullSize)">
                                                Save
                                            </button>

                                            <div class="divider"></div>


                                            <h5>Response Pull Size:</h5>
                                            <input type="number" ng-model="ctrl.responsePullSize">
                                            <button type="button" class="btn btn-primary btn-sm" ng-click="ctrl.setResponsePullSize(ctrl.responsePullSize)">
                                                Save
                                            </button>

                                            <div class="divider"></div>

                                            <h5>Freezzee Time:</h5>
                                            <input type="number" ng-model="ctrl.freezzeeTime">
                                            <button type="button" class="btn btn-primary btn-sm" ng-click="ctrl.setFreezzeeTime(ctrl.freezzeeTime)">
                                                Save
                                            </button>

                                            <div class="divider"></div>

                                            <h5>Revelance:</h5>
                                            <input type="number" ng-model="ctrl.revelance">
                                            <button type="button" class="btn btn-primary btn-sm" ng-click="ctrl.setRevelance(ctrl.revelance)">
                                                Save
                                            </button>


                                        </div>
                                    </div>
                                </div>
                            </section>                         
                        </div>
                    </section>
            </div>
        </section>
        <!-- Modal -->
        <div class="modal fade" id="modal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="exampleModalLabel">Edit Address</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <div class="container-fluid">
                            <div class="row">
                                <div class="col-md-12">
                                    Name:
                                </div>
                                <div class="col-md-12">
                                    <input type="text" ng-model="ctrl.address.name">
                                </div>
                                <div class="col-md-12">
                                    Email: 
                                </div>
                                <div class="col-md-12">
                                    <input type="email" ng-model="ctrl.address.emailAddress"
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-primary btn-sm" ng-click="ctrl.reset()"> 
                            <em class="fa fa-refresh" aria-hidden="true"></em> Refresh</button>
                        <button type="button" class="btn btn-primary btn-sm" data-dismiss="modal" ng-click="ctrl.submit()">
                            {{!ctrl.address.id?'Save':'Update'}}</button>
                        <button type="button" class="btn btn-danger btn-sm" data-dismiss="modal" ng-click="ctrl.remove(ctrl.address.id)">Delete</button>
                        <button type="button" class="btn btn-secondary btn-sm" data-dismiss="modal">Close</button>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <section class="row">
        <div class="col-12 mt-1 mb-4">Template by <a href="https://www.medialoot.com">Medialoot</a></div>
    </section>

</main>
</div>
</div>

<!-- Bootstrap core JavaScript
================================================== -->
<!-- Placed at the end of the document so the pages load faster -->
<script src="static/js/jquery-3.2.1.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/tether/1.4.0/js/tether.min.js" integrity="sha384-DztdAPBWPRXSA/3eYEEUWrWCy7G5KFbe8fFjk5JAIxUYHKkDx6Qin1DkWx51bBrb" crossorigin="anonymous"></script>
<script src="static/dist/js/bootstrap.min.js"></script>
<script src="static/js/bootstrap-datepicker.js"></script>
<script src="static/js/chart.min.js"></script>
<script src="static/js/chart-data.js"></script>
<script src="static/js/easypiechart.js"></script>
<script src="static/js/easypiechart-data.js"></script>
<script src="static/js/custom.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.4/angular.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.4/angular-resource.js"></script>
<script src="<c:url value='/static/js/app.js' />"></script>
<script src="<c:url value='/static/js/service/settings_service.js' />"></script>
<script src="<c:url value='/static/js/service/mail_address_service.js' />"></script>
<script src="<c:url value='/static/js/controller/settings_controller.js' />"></script>
<script src="<c:url value='/static/js/service/url_service.js' />"></script>
<script src="<c:url value='/static/js/controller/url_controller.js' />"></script>
</body>
</html>
