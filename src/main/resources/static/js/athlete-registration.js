
var app = angular.module("athlete-info-app", []);
app.config(['$locationProvider', function ($locationProvider) {
        $locationProvider.html5Mode({
            enabled: true,
            requireBase: false
        });
    }]);
app.controller("athlete-info-controller", function ($scope, $http, $location) {

    //Athlete info
    $scope.id;
    $scope.idNumber = "";
    $scope.name;
    $scope.surname;
    $scope.birthDate;
    $scope.gender;
    $scope.bloodType;
    $scope.city;
    $scope.school;
    $scope.club;
    $scope.agency;
    $scope.size;
    $scope.jerseyNumber;
    $scope.motherName;
    $scope.fatherName;
    $scope.phoneNumber;
    $scope.email;
    $scope.motherPhoneNumber;
    $scope.fatherPhoneNumber;

    //protector info
    $scope.protectorRelation;
    $scope.protectorName;
    $scope.protectorHousePhone;
    $scope.protectorMobilePhone;
    $scope.protectorEmail;

    //Emergency
    $scope.emergencyRelation;
    $scope.emergencyName;
    $scope.emergencyHousePhone;
    $scope.emergencyMobilePhone;
    $scope.emergencyEmail;


    //Pre Registration
    $scope.preRegistrationDate = new Date();
    $scope.preRegistrationCamp;
    $scope.preRegistrationPriceOffer;
    $scope.preRegistrationNote;


    //
    $scope.recordButtonText = "Yeni sporcu kaydet";
    $scope.recordButtonsEnabled = true;
    $scope.tcNumberValid = true;

    $scope.tcNumberElementClass = "danger";

    var isDataLoadedFromDB = false;

    $scope.idNumberValidate = function () {
        console.log("id number : " + $scope.idNumber);
        if ($scope.idNumber && $scope.idNumber.length === 11) {
            var digits = [];
            for (var i = 0; i < 11; i++) {
                digits.push(parseInt($scope.idNumber.charAt(i)));
            }

            var val1 = ((digits[0] + digits[2] + digits[4] + digits[6] + digits[8]) * 7) - (digits[1] + digits[3] + digits[5] + digits[7]);
            var d10 = val1 % 10;

            if (d10 !== digits[9]) {
                console.log("invalid TC number : " + $scope.idNumber);
                return;
            }

            var val2 = 0;
            for (var i = 0; i < 10; i++) {
                val2 += digits[i];
            }

            var d11 = val2 % 10;

            if (d11 !== digits[10]) {
                console.log("invalid TC number : " + $scope.idNumber);
                return;
            }

            $scope.tcNumberElementClass = "success";
            $scope.tcNumberValid = false;
            console.log("---> TC number : " + $scope.idNumber);
            loadAthleteFromDB($scope.idNumber);

        } else {

            if (isDataLoadedFromDB) {
                clearForm();
                isDataLoadedFromDB = true;
            }

            $scope.tcNumberElementClass = "danger";
            $scope.tcNumberValid = false;
            console.log("invalid TC number : " + $scope.idNumber);
            return;
        }

    };

    $scope.printDate = function () {
        console.log("Birth date : " + $scope.birthDate);
    };

    $scope.saveAthlete = function () {

        $http({
            method: 'POST',
            url: '/athlete',
            data: getPostData(),
            headers: {'Content-Type': 'application/json'}
        }).then(function successCallback(data, status, headers, config) {

            showToastMessage(ToastMessageType.SUCCESS, "Sporcu verisi kaydedildi.");

            loadAthleteFromDB($scope.idNumber);
            console.log("save athlete is successful : " + JSON.stringify(status));
        }, function errorCallback(data, status, headers, config) {

            showToastMessage(ToastMessageType.ERROR, "Bir sorun oluştu, sporcu verisi kaydedilemedi.");

            console.log("save athlete is unsuccessful : " + JSON.stringify(status));
            console.log("Error data : " + JSON.stringify(data));
        });

    };

    $scope.doPreRegistration = function () {
        $http({
            method: 'POST',
            url: '/registrations',
            data: getPostDataPreRegistration(),
            headers: {'Content-Type': 'application/json'}
        }).then(function successCallback(data, status, headers, config) {

            showToastMessage(ToastMessageType.SUCCESS, "Ön kayıt işlemi başarılı.");

            loadAthleteFromDB($scope.idNumber);
            console.log("save registration is successful : " + JSON.stringify(status));
        }, function errorCallback(data, status, headers, config) {

            showToastMessage(ToastMessageType.ERROR, "Bir sorun oluştu, ön kayıt işlemi yapılamadı.");

            console.log("save registration is unsuccessful : " + JSON.stringify(status));
            console.log("Error data : " + JSON.stringify(data));
        });
    };


    function loadAthleteFromDB(tcNumber) {

        $http({
            method: 'GET',
            url: '/athlete?id=' +  tcNumber
        }).then(function successCallback(data, status, headers, config) {

            const athlete = data.data[0];

            if (athlete) {
                $scope.id = athlete.id;
                $scope.idNumber = athlete.idNumber;
                $scope.name = athlete.name;
                $scope.surname = athlete.surname;
                $scope.birthDate = athlete.birthDate;
                $scope.gender = athlete.gender;
                $scope.bloodType = athlete.bloodType;
                $scope.city = athlete.city;
                $scope.school = athlete.school;
                $scope.club = athlete.club;
                $scope.agency = athlete.agency;
                $scope.size = athlete.size;
                $scope.jerseyNumber = athlete.jerseyNumber;
                $scope.motherName = athlete.motherNameSurname;
                $scope.fatherName = athlete.fatherNameSurname;
                $scope.phoneNumber = athlete.phoneNumber;
                $scope.email = athlete.email;
                $scope.motherPhoneNumber = athlete.motherPhoneNumber;
                $scope.fatherPhoneNumber = athlete.fatherPhoneNumber;
                ////////////////////////////////////////////////////////////////
                $scope.protectorRelation = athlete.protectorRelationship;
                $scope.protectorName = athlete.protectorNameSurname;
                $scope.protectorHousePhone = athlete.protectorPhoneNumber;
                $scope.protectorMobilePhone = athlete.protectorMobilePhoneNumber;
                $scope.protectorEmail = athlete.protectorEmail;
                /////////////////////////////////////////////////////////////////
                $scope.emergencyRelation = athlete.emergencyContactRelationship;
                $scope.emergencyName = athlete.emergencyContactNameSurname;
                $scope.emergencyHousePhone = athlete.emergencyContactPhoneNumber;
                $scope.emergencyMobilePhone = athlete.emergencyContactMobilePhoneNumber;
                $scope.emergencyEmail = athlete.emergencyContactEmail;


                $scope.recordButtonText = "Sporcu verisini güncelle";
                $scope.recordButtonsEnabled = false;
                isDataLoadedFromDB = true;
            }


            console.log("get athlete is successful : " + JSON.stringify(status));
            console.log("Error data : " + JSON.stringify(data));
        }, function errorCallback(data, status, headers, config) {

            showToastMessage(ToastMessageType.ERROR, "Bir sorun oluştu, sporcu verisi veri tabanından getirilemedi.");

            console.log("get athlete is unsuccessful : " + JSON.stringify(status));
            console.log("Error data : " + JSON.stringify(data));
        });

    }
    ;

    $scope.motherIsProtector = function () {
        $scope.protectorRelation = "Anne";
        $scope.protectorName = $scope.motherName;
        $scope.protectorMobilePhone = $scope.motherPhoneNumber;
    };

    $scope.fatherIsProtector = function () {
        $scope.protectorRelation = "Baba";
        $scope.protectorName = $scope.fatherName;
        $scope.protectorMobilePhone = $scope.fatherPhoneNumber;
    };


    function getPostData() {
        return {
            id: $scope.id,
            idNumber: $scope.idNumber,
            name: $scope.name,
            surname: $scope.surname,
            birthDate: $scope.birthDate,
            gender: $scope.gender,
            bloodType: $scope.bloodType,
            city: $scope.city,
            school: $scope.school,
            club: $scope.club,
            agency: $scope.agency,
            size: $scope.size,
            jerseyNumber: $scope.jerseyNumber,
            motherNameSurname: $scope.motherName,
            fatherNameSurname: $scope.fatherName,
            phoneNumber: $scope.phoneNumber,
            email: $scope.email,
            motherPhoneNumber: $scope.motherPhoneNumber,
            fatherPhoneNumber: $scope.fatherPhoneNumber,
            ///////////////////////////////////////////////////
            protectorRelationship: $scope.protectorRelation,
            protectorNameSurname: $scope.protectorName,
            protectorPhoneNumber: $scope.protectorHousePhone,
            protectorMobilePhoneNumber: $scope.protectorMobilePhone,
            protectorEmail: $scope.protectorEmail,
            /////////////////////////////////////////////////////
            emergencyContactRelationship: $scope.emergencyRelation,
            emergencyContactNameSurname: $scope.emergencyName,
            emergencyContactPhoneNumber: $scope.emergencyHousePhone,
            emergencyContactMobilePhoneNumber: $scope.emergencyMobilePhone,
            emergencyContactEmail: $scope.emergencyEmail
        };
    }
    ;

    function getPostDataPreRegistration() {
        return{
            //id: $scope.registrationId,
            registrationType: "PRE_REGISTRATION",
            preRegistrationDate: $scope.preRegistrationDate,
            athlete: {id: $scope.id},
            camp: {id: $scope.preRegistrationCamp},
            preRegistrationPriceOffer: $scope.preRegistrationPriceOffer,
            preRegistrationNote: $scope.preRegistrationNote
        };
    }
    ;

    function clearForm() {
        $scope.id = "";
        //$scope.idNumber = "";
        $scope.name = "";
        $scope.surname = "";
        $scope.birthDate = "";
        $scope.gender = "";
        $scope.bloodType = "";
        $scope.city = "";
        $scope.school = "";
        $scope.club = "";
        $scope.agency = "";
        $scope.size = "";
        $scope.jerseyNumber = "";
        $scope.motherName = "";
        $scope.fatherName = "";
        $scope.phoneNumber = "";
        $scope.email = "";
        $scope.motherPhoneNumber = "";
        $scope.fatherPhoneNumber = "";
        //////////////////////////////////////////////////
        $scope.protectorRelation = "";
        $scope.protectorName = "";
        $scope.protectorHousePhone = "";
        $scope.protectorMobilePhone = "";
        $scope.protectorEmailAddress = "";
        ///////////////////////////////////////////////////
        $scope.emergencyRelation = "";
        $scope.emergencyName = "";
        $scope.emergencyHousePhone = "";
        $scope.emergencyMobilePhone = "";
        $scope.emergencyEmail = "";

        $scope.recordButtonText = "Yeni sporcu kaydet";
    }


    const ToastMessageType = {
        INFO: "info",
        SUCCESS: "success",
        WARNING: "warning",
        ERROR: "error"
    };

    toastr.options = {
        "closeButton": false,
        "debug": false,
        "newestOnTop": false,
        "progressBar": false,
        "positionClass": "toast-top-right",
        "preventDuplicates": false,
        "onclick": null,
        "showDuration": "300",
        "hideDuration": "1000",
        "timeOut": "5000",
        "extendedTimeOut": "1000",
        "showEasing": "swing",
        "hideEasing": "linear",
        "showMethod": "fadeIn",
        "hideMethod": "fadeOut"
    };

    function showToastMessage(messageType, message) {
        toastr[messageType](message);
    }


    if ($location.search().id) {
        $scope.idNumber = $location.search().id;
        $scope.idNumberValidate();
        $location.search({});
    } else {
        console.log("Query Parameter");
    }


});
