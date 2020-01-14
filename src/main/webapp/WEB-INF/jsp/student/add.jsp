<%@page contentType="text/html; charset=utf-8" %><%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %><%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ include file="../include/css.jsp" %>
<%@ include file="../pageAdmin/include/menu.jsp" %>
<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.6.7/angular.js"></script>
<div id="content">
    <div id="content-header">
        <div id="breadcrumb"><a href="<c:url value="/admin/index"/>" title="Go to Home" class="tip-bottom"><i class="icon-home"></i> Home</a> </div>
    </div>
    <div class="container-fluid">
        <div class="row-fluid">
            <div class="span12">
                <c:if test="${result.code==0}">
                    <button class="close" data-dismiss="alert">×</button><strong>Error!</strong> ${result.messing}</div>
                </c:if>
            <div class="widget-box" style="margin: 20px" ng-app="listStudent" ng-controller="studentController">{{message}}
                <div class="widget-content nopadding">
                    <form ng-submit="submitEmployee()" novalidate>
                        <table class="table table-bordered table-striped">
                            <tr>
                                <td style="width: 20%"></td>
                                <td><input type="hidden" ng-model="studentForm.id"  /></td>
                            </tr>
                            <tr>
                                <td style="width: 20%">Mã Sinh Viên</td>
                                <td>
                                    <input type="text" ng-model="studentForm.code" ng-required="true"  />
                                    <span class="error" ng-show="studentForm.code.$error.required">required</span>
                                </td>
                            </tr>
                            <tr>
                                <td>Tên Sinh Viên</td>
                                <td>
                                    <input type="text" ng-model="studentForm.name" ng-required="true" />
                                    <span class="error" ng-show="studentForm.name.$error.required">required</span>
                                </td>
                            </tr>
                            <tr>
                                <td>Mật Khẩu</td>
                                <td>
                                    <input type="password" ng-model="studentForm.password" ng-required="true" ng-minlength="8" />
                                    <span class="error" ng-show="studentForm.password.$error.required">required</span>
                                    <span class="error" ng-show="studentForm.password.$error.minlength">words must be more than 6</span>
                                    <span class="valid" ng-show="studentForm.password.$valid">OK</span>
                                </td>
                            </tr>
                            <tr>
                                <td>Ghi Chú</td>
                                <td><textarea ng-model="studentForm.noti"></textarea></td>
                            </tr>
                            <tr>
                                <td>Trạng Thái</td>
                                <td>
                                    <select ng-model="studentForm.status" >
                                        <option ng-repeat="option in select.availableOptions" ng-value="option.value">{{option.name}}</option>
                                    </select>
                                </td>
                            </tr>
                            <tr>
                                <td colspan="2">
                                    <input type="submit" value="Submit" class="blue-button" ng-disabled="studentForm.$invalid" />
                                </td>
                            </tr>
                        </table>
                    </form>          
                </div>
            </div>
        </div>
    </div>
</div>
<%@ include file="../include/page.jsp" %>
<%@ include file="../include/footer.jsp" %>
<script src="<c:url value='/js/jquery.min.js'/>"></script>
<script src="<c:url value='/js/jquery.validate.js'/>"></script>
<script src="<c:url value='/js/jquery.ui.custom.js'/>"></script>
<script src="<c:url value='/js/bootstrap.min.js'/>"></script>
<script src="<c:url value='/js/maruti.popover.js'/>"></script>
<script>
                                        var app = angular.module('listStudent', []);
                                        app.controller('studentController', function ($scope, $http, $filter) {
                                            $scope.studentForm = {
                                                id: "",
                                                code: "",
                                                name: "",
                                                password: "",
                                                noti: "",
                                                status: 0
                                            };

                                            $scope.select = {
                                                model: null,
                                                availableOptions: [
                                                    {value: 1, name: 'Đang Học'},
                                                    {value: 0, name: 'Nghỉ Học'}
                                                ]
                                            };

                                            // Show more error infos.
                                            function printErrorInfo() {
                                                console.log($scope.studentForm.$error);
                                                if ($scope.studentForm.$error.minlength) {
                                                    console.log('$error.minlength? ' + $scope.myForm.$error.minlength[0].$invalid);
                                                }
                                                if ($scope.studentForm.$error.maxlength) {
                                                    console.log('$error.maxlength? ' + $scope.myForm.$error.maxlength[0].$invalid);
                                                }
                                            }

                                            $scope.submitEmployee = function () {

                                                if ($scope.studentForm.$invalid) {
                                                                alert("Something invalid!");
 
                                                                printErrorInfo();
 
                                                                // Cancel submit
                                                                event.preventDefault();
                                                                return false;
                                                        }
 

                                                $http({
                                                    method: "POST",
                                                    url: "/page-admin/student/add",
                                                    data: $scope.studentForm,
                                                    headers: {
                                                        Authorization: "${token}"
                                                    }
                                                }).then(
                                                        function Succes(resp) {
                                                            $scope.message = resp.data.message;
                                                            $scope.code = resp.data.code;
                                                            if ($scope.code === 1)
                                                            {
                                                                window.location = urlBase + '/page-admin/student/view';
                                                            }
                                                        }, function Error(resp) {
                                                    console.log("Error: " + resp.status + " : " + resp.data);
                                                    console.log(resp.data);
                                                    $scope.message = "Thêm Mới Thất Bại";
                                                });
                                            };
                                        });
</script>
