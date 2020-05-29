/**
 * 
 */

$(document).ready(function() {

	console.log("jQuery-All");
	
	$('header').addClass('container');
	$('header').css("background-color", "rgb(#f7b733)");
	$('footer').addClass('container');
	
	//all bodys
	$('body').css("background", "linear-gradient(to right, rgba(252,74,26,0.6), rgba(247,183,51,0.8))");
	
	//home-principal
	$('#hometapas').addClass('container px-2');
	$('#hometapas').css("background", "linear-gradient(to right, rgba(255,255,255,0), rgba(255,255,255,0)");
	
	//datatable and categorias
	$('#tapasTable').css({"border-radius": "15px", "font-size": "auto", "border": "0.7px solid white" });
	$('#categoriastapas').css({"border-radius": "15px", "margin-top": "10%", "padding-top": "auto" });
	
	//listado de bares
	$('.card').css("background", "linear-gradient(to right, rgba(255,255,255,0.5), rgba(255,255,255,0)");
	
	

});