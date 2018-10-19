
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
    $scope.preRegistrationCamp = 0;
    $scope.preRegistrationPriceOffer;
    $scope.preRegistrationNote;

    //Registration
    $scope.recordDate = new Date();
    $scope.camp = 0;
    $scope.price;
    $scope.advancePayment;
    $scope.numberOfInstallment;
    $scope.joiningDate;
    $scope.leavingDate;
    $scope.transportationType;
    $scope.transportationNote;
    $scope.form;
    $scope.agreement;
    $scope.healthReport;

    //Others
    $scope.recordButtonText = "Yeni sporcu kaydet";
    $scope.recordButtonsEnabled = true;
    $scope.tcNumberValid = true;

    $scope.tcNumberElementClass = "danger";
    
    // 0 : none, 1 : pre registration form, 2 : registration form
    $scope.showForm = 0;

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

            fillAthleteForm(athlete);

            console.log("get athlete is successful : " + JSON.stringify(status));
            console.log("Error data : " + JSON.stringify(data));
        }, function errorCallback(data, status, headers, config) {

            showToastMessage(ToastMessageType.ERROR, "Bir sorun oluştu, sporcu verisi veri tabanından getirilemedi.");

            console.log("get athlete is unsuccessful : " + JSON.stringify(status));
            console.log("Error data : " + JSON.stringify(data));
        });

    }
    
    function fillAthleteForm(athlete){
        if (athlete) {
            $scope.id = athlete.id;
            $scope.idNumber = athlete.idNumber;
            $scope.name = athlete.name;
            $scope.surname = athlete.surname;
            $scope.birthDate = new Date(Date.parse(athlete.birthDate));
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
    }
    
    function loadRegistrationFromDB(regId,successCalback){
        
        $http({
            method: 'GET',
            url: '/registrations?id=' +  regId
        }).then(function successCallback(data, status, headers, config) {

            const reg = data.data[0];

            if(successCalback){
                successCalback(reg);
            }

            console.log("get registration is successful : " + JSON.stringify(status));
            console.log("Registration data : " + JSON.stringify(data));
            
        }, function errorCallback(data, status, headers, config) {

            showToastMessage(ToastMessageType.ERROR, "Bir sorun oluştu, kayıt verisi veri tabanından getirilemedi.");

            console.log("get registration is unsuccessful : " + JSON.stringify(status));
            console.log("Error data : " + JSON.stringify(data));
        });
        
    }
    

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

    function showRegistrationForm(){
        $scope.showForm = 2;
    }

    function preRegToReg(reg){
        console.log("Pre reg to reg");
        if (reg) {
            $scope.preRegistrationDate = new Date(Date.parse(reg.preRegistrationDate));
            $scope.preRegistrationCamp = reg.camp.id;
            $scope.preRegistrationPriceOffer = reg.preRegistrationPriceOffer;
            $scope.preRegistrationNote = reg.preRegistrationNote;

            if(reg.registrationType === "PRE_REGISTRATION" ){
                $scope.recordDate = new Date();
                $scope.price = reg.preRegistrationPriceOffer;
            }else{
                $scope.recordDate = reg.registrationDate;
                $scope.price = reg.price;
            }

            $scope.camp = reg.camp.id;
            $scope.advancePayment = reg.advancePayment;
            $scope.numberOfInstallment = reg.numberOfInstallments;
            $scope.joiningDate = new Date(Date.parse(reg.joiningDate));
            $scope.leavingDate = new Date(Date.parse(reg.leavingDate));
            $scope.transportationType = reg.transportaionType;
            $scope.transportationNote = reg.transportaionType;
            $scope.form = reg.form;
            $scope.agreement = reg.agreement;
            $scope.healthReport = reg.healthReport;

            fillAthleteForm(reg.athlete);
            showRegistrationForm();
        }
    }

    ////////////////////////// Installment management
    $scope.installments = [];
    $scope.editedInstallment = {};
    
    $scope.PaymentType = {
        advanced : "ADVANCE",
        installment : "INSTALLMENT"
    };
    
    
    $scope.createInstallment = function(){
        const numInstallments = $scope.numberOfInstallment;
        const advancePayment = $scope.advancePayment;
        
        if(numInstallments <= 0){
            showToastMessage(ToastMessageType.ERROR,"Taksit sayısı 0 dan büyük olmalı.");
        }else if($scope.preRegistrationPriceOffer <= 0){
            showToastMessage(ToastMessageType.ERROR,"Fiyat teklifi 0 dan büyük olmalı.");
        }else if (advancePayment < 0 ){
            showToastMessage(ToastMessageType.ERROR,"Peşinat 0 dan büyük olmalı.");
        }else if(advancePayment > $scope.preRegistrationPriceOffer){
            showToastMessage(ToastMessageType.ERROR,"Peşinat fiyat teklifinden büyük olamaz");
        }
        
        
        $scope.installments = []; //Clears previous installments
        var curentDate =  moment().add(1, 'months');
        
        if($scope.advancePayment > 0){
            $scope.installments.push(createPayment(0, moment().format("DD.MM.YYYY"), advancePayment,$scope.PaymentType.advanced));
        }

        if($scope.advancePayment === $scope.preRegistrationPriceOffer){
            return;
        }

        const price = Math.floor((($scope.preRegistrationPriceOffer - $scope.advancePayment) / $scope.numberOfInstallment));
        var i;
        for(i = 0; i < numInstallments - 1 ; i++){
            $scope.installments.push(createPayment(i+1,curentDate.format("DD.MM.YYYY"),price, $scope.PaymentType.installment ));
            curentDate = curentDate.add(1, 'months');
        }
        
        const lastPrice = price + (($scope.preRegistrationPriceOffer - $scope.advancePayment) - price * numInstallments);
        $scope.installments.push(createPayment(i+1,curentDate.format("DD.MM.YYYY"),lastPrice, $scope.PaymentType.installment ));
        
    };
    
    
    $scope.editInstallment = function($index){
        $scope.installmentIndex = $index;
        angular.copy($scope.installments[$index], $scope.editedInstallment);
    };
    
    
    $scope.saveInstallment = function(){
        angular.copy($scope.editedInstallment, $scope.installments[$scope.installmentIndex]) ;
    };
    
    
    function createPayment(order, date, price,type){
        return{
            order :order,
            date : date,
            amount : price,
            type : type
        };
    }
    
    /////////////////////////


    if ($location.search().id) {
        $scope.idNumber = $location.search().id;
        $scope.idNumberValidate();
        $location.search({});
    } if($location.search().pre_reg_id){
        $scope.preRegistrationId = $location.search().pre_reg_id;
        loadRegistrationFromDB($scope.preRegistrationId,preRegToReg);
        $location.search({});
    } else {
        console.log("Query Parameter");
    }


});
