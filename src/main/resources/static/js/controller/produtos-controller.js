appCarrinho.controller ("produtosController", function  ($scope, $http, $window){

	$scope.produtos = [];
	$scope.carrinho = [];
	
	$scope.listarProdutos = function(){
		$http({method:'GET', url:'/produto'})
		.then(function (response){
			$scope.produtos = response.data;
		} , function (response){
			erro("Error: " + status);
		});
	};
	
	$scope.adicionarProduto = function(produto){
		$http({method:'POST', url:'/pedido',data:produto})
		.then(function (response){
			$scope.carrinho = response;
			$window.location.href = '/#/carrinho'; 
		} , function (response){
			erro("Error: " + status);
		});
		
	};
	
});