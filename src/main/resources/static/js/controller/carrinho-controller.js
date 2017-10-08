appCarrinho.controller ("carrinhoController", function  ($scope, $http, $window){
	
	$scope.carrinho;
	$scope.qtdItens = 0;
	
	$scope.getCarrinho = function(){
		$http({method:'GET', url:'/carrinho'})
		.then(function (response){
			$scope.carrinho = response.data;
			$scope.qtdItens = $scope.carrinho.itemPedido.length;
		} , function (response){
		});
	};
	
	$scope.removerCarrinho = function(id){
		$http({method:'DELETE', url:'/carrinho/'+id})
		.then(function (response){
			$scope.getCarrinho();
		} , function (response){
		});	
	};
	
	$scope.removerItem = function(id){
		$http({method:'DELETE', url:'/pedido/'+id})
		.then(function (response){
			$scope.getCarrinho();
		} , function (response){
		});	
	};
	
	$scope.alterarItem = function(pedido){
		$http({method:'PUT', url:'/pedido/'+pedido.id, data:pedido})
		.then(function (response){
			$scope.getCarrinho();
		} , function (response){
		});	
	};
	
	
});