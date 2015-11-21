var petStoreApp = angular.module("petStoreApp", [ 'ui.bootstrap', 'ngRoute', 'ngResource' ]);

petStoreApp.config(function($routeProvider) {
	$routeProvider.when('/pets', {
		controller : 'PetsCtrl',
		templateUrl : 'views/pets.html'	
	}).when('/findpet', {
		templateUrl : 'views/petbyid.html'	
	}).when('/pet/:id', {
		controller : 'PetByIdCtrl',
		templateUrl : 'views/petbyid.html'	
	}).when('/delete/:id', {
		controller : 'DeletePetByIdCtrl',
		templateUrl : 'views/pets.html'	
	}).otherwise({
		controller : 'NewPetCtrl',
		templateUrl : 'views/newpet.html'
	})
});

petStoreApp.controller("PetsCtrl", [ '$scope','petservice', function($scope, petservice) {	
	petservice.getPets( $scope );		
} ]);

petStoreApp.controller("NewPetCtrl", [ '$scope','petservice', function($scope, petservice) {				
	
	petservice.getPets( $scope );	
	
	$scope.createNewPet = function(){
		var newPet =
		{
			  "name": $scope.name,
			  "category": {
			    "name": $scope.categoryname
			  },
			    "photoUrls": [
			        $scope.photourl1,$scope.photourl2
			  ],
			  "tags": [
			    {
			     "name": $scope.tagname1
			    },
			    {
				 "name": $scope.tagname2
				}
			  ],
			  "status": $scope.status
		};
		
		petservice.createPet ( newPet, $scope );
					
		$scope.pets.push( newPet );

		$scope.name='';
		$scope.categoryname='';
		$scope.photourl1='';
		$scope.photourl2='';
		$scope.tagname1='';
		$scope.tagname2='';
		$scope.status='AVAILABLE';
	};		
} ]);

petStoreApp.controller("PetByIdCtrl", [ '$scope','petservice', '$routeParams', function($scope, petservice, $routeParams) {	
	var id;
	$scope.findAPet = function(){
		id = $scope.id;
	};
	if(id = ''){
		id = $routeParams.id;
	}
	petservice.getPet(id, $scope);	
} ]);

petStoreApp.controller("DeletePetByIdCtrl", [ '$scope','petservice', '$routeParams', function($scope, petservice, $routeParams) {	
	petservice.deletePet($routeParams.id, $scope);	
} ]);


petStoreApp.factory( 'petservice', [ '$resource', function( $resource ){
	return new Pet( $resource );
}] );

function Pet( resource ) {
	
	this.resource = resource; 
	
	this.createPet = function ( pet, scope ) {
		// 
		// Save Action Method
		//
		var Pet = resource('http://localhost:8080/v1/pet');		
		Pet.save(pet, function(response){
			scope.message = response.message;
		});		
	}
	
	this.getPet = function ( id, scope ) {
		//
		// GET Action Method
		//
		var Pet = resource('http://localhost:8080/v1/pet/:id', {id:'@id'});
		Pet.get( {id:id}, function(pet){
			scope.pet = pet;
		})
	}
	
	this.deletePet = function ( id, scope ) {
		//
		// DELETE Action Method
		//
		var Pet = resource('http://localhost:8080/v1/pet/:id', {id:'@id'});
		Pet.remove( {id:id}, function(pet){
			scope.pet = pet;
		})
	}
	
	this.getPets = function( scope ) {
		//
		// Query Action Method
		//
		var Pets = resource('http://localhost:8080/v1/pet/all');
		Pets.query(function(pets){
			scope.pets = pets;
		});
	}
}
