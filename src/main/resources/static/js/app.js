//Criacao do modulo principal da aplicacao
var appCarrinho = angular.module("appCarrinho", [ 'ngRoute' ]);

appCarrinho.config(function($routeProvider, $locationProvider) {

	$routeProvider
	.when("/produtos", {
		templateUrl : 'view/produtos.html',
		controller : 'produtosController'
	}).when("/carrinho", {
		templateUrl : 'view/carrinho.html',
		controller : 'carrinhoController'
	}).when("/contato", {
		templateUrl : 'view/contato.html'
	}).when("/resultado", {
		templateUrl : 'view/resultado.html'
	}).when("/", {
		templateUrl : 'view/home.html'
	}).otherwise({
		rediretTo : '/'
	});
	
	$locationProvider.html5Mode(true);
	
});
